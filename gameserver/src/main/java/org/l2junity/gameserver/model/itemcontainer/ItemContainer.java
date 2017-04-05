/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.itemcontainer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.core.configs.RatesConfig;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.world.ItemStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Advi
 */
public abstract class ItemContainer {
	protected static final Logger _log = LoggerFactory.getLogger(ItemContainer.class);

	protected final Map<Integer, ItemInstance> _items = new ConcurrentHashMap<>();

	protected ItemContainer() {
	}

	protected abstract Creature getOwner();

	protected abstract ItemLocation getBaseLocation();

	public String getName() {
		return "ItemContainer";
	}

	/**
	 * @return int the owner object Id
	 */
	public int getOwnerId() {
		return getOwner() == null ? 0 : getOwner().getObjectId();
	}

	/**
	 * @return the quantity of items in the inventory
	 */
	public int getSize() {
		return _items.size();
	}

	/**
	 * @param filter
	 * @param filters
	 * @return the quantity of items in the inventory
	 */
	@SafeVarargs
	public final int getSize(Predicate<ItemInstance> filter, Predicate<ItemInstance>... filters) {
		for (Predicate<ItemInstance> additionalFilter : filters) {
			filter = filter.and(additionalFilter);
		}
		return (int) _items.values().stream().filter(filter).count();
	}

	/**
	 * Gets the items in inventory.
	 *
	 * @return the items in inventory.
	 */
	public Collection<ItemInstance> getItems() {
		return getItems(i -> true);
	}

	/**
	 * Gets the items in inventory filtered by filter.
	 *
	 * @param filter  the filter
	 * @param filters multiple filters
	 * @return the filtered items in inventory
	 */
	@SafeVarargs
	public final Collection<ItemInstance> getItems(Predicate<ItemInstance> filter, Predicate<ItemInstance>... filters) {
		for (Predicate<ItemInstance> additionalFilter : filters) {
			filter = filter.and(additionalFilter);
		}
		return _items.values().stream().filter(filter).collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * @param itemId the item Id
	 * @return the item from inventory by itemId
	 */
	public ItemInstance getItemByItemId(int itemId) {
		return _items.values().stream().filter(item -> item.getId() == itemId).findFirst().orElse(null);
	}

	/**
	 * @param itemId the item Id
	 * @return the items list from inventory by using its itemId
	 */
	public Collection<ItemInstance> getItemsByItemId(int itemId) {
		return getItems(i -> i.getId() == itemId);
	}

	/**
	 * @param objectId the item object Id
	 * @return item from inventory by objectId
	 */
	public ItemInstance getItemByObjectId(int objectId) {
		return _items.get(objectId);
	}

	/**
	 * Gets the inventory item count by item Id and enchant level including equipped items.
	 *
	 * @param itemId       the item Id
	 * @param enchantLevel the item enchant level, use -1 to match any enchant level
	 * @return the inventory item count
	 */
	public long getInventoryItemCount(int itemId, int enchantLevel) {
		return getInventoryItemCount(itemId, enchantLevel, true);
	}

	/**
	 * Gets the inventory item count by item Id and enchant level, may include equipped items.
	 *
	 * @param itemId          the item Id
	 * @param enchantLevel    the item enchant level, use -1 to match any enchant level
	 * @param includeEquipped if {@code true} includes equipped items in the result
	 * @return the inventory item count
	 */
	public long getInventoryItemCount(int itemId, int enchantLevel, boolean includeEquipped) {
		long count = 0;

		for (ItemInstance item : _items.values()) {
			if ((item.getId() == itemId) && ((item.getEnchantLevel() == enchantLevel) || (enchantLevel < 0)) && (includeEquipped || !item.isEquipped())) {
				if (item.isStackable()) {
					return item.getCount();
				}
				count++;
			}
		}
		return count;
	}

	/**
	 * @return true if player got item for self resurrection
	 */
	public final boolean haveItemForSelfResurrection() {
		return _items.values().stream().anyMatch(item -> item.getItem().isAllowSelfResurrection());
	}

	/**
	 * Adds item to inventory
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param item      : L2ItemInstance to be added
	 * @param actor     : L2PcInstance Player requesting the item add
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the new item or the updated item in inventory
	 */
	public ItemInstance addItem(String process, ItemInstance item, Player actor, Object reference) {
		ItemInstance olditem = getItemByItemId(item.getId());

		// If stackable item is found in inventory just add to current quantity
		if ((olditem != null) && olditem.isStackable()) {
			long count = item.getCount();
			if (!validateCount(item.getId(), (olditem.getCount() + count))) {
				return null;
			}

			olditem.changeCount(process, count, actor, reference);
			olditem.setLastChange(ItemInstance.MODIFIED);

			// And destroys the item
			ItemTable.getInstance().destroyItem(process, item, actor, reference);
			item.updateDatabase();
			item = olditem;

			// Updates database
			float adenaRate = RatesConfig.RATE_DROP_AMOUNT_MULTIPLIER.getOrDefault(Inventory.ADENA_ID, 1f);
			if ((item.getId() == Inventory.ADENA_ID) && (count < (10000 * adenaRate))) {
				// Small adena changes won't be saved to database all the time
				if ((GameTimeManager.getInstance().getGameTicks() % 5) == 0) {
					item.updateDatabase();
				}
			} else {
				item.updateDatabase();
			}
		}
		// If item hasn't be found in inventory, create new one
		else {
			item.setOwnerId(process, getOwnerId(), actor, reference);
			item.setItemLocation(getBaseLocation());
			item.setLastChange((ItemInstance.ADDED));

			// Add item in inventory
			addItem(item);

			// Updates database
			item.updateDatabase();
		}

		refreshWeight();
		return item;
	}

	/**
	 * Adds item to inventory
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param itemId    : int Item Identifier of the item to be added
	 * @param count     : int Quantity of items to be added
	 * @param actor     : L2PcInstance Player requesting the item add
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the new item or the updated item in inventory
	 */
	public ItemInstance addItem(String process, int itemId, long count, Player actor, Object reference) {
		ItemInstance item = getItemByItemId(itemId);

		// If stackable item is found in inventory just add to current quantity
		if ((item != null) && item.isStackable()) {
			if (!validateCount(itemId, (item.getCount() + count))) {
				return null;
			}

			item.changeCount(process, count, actor, reference);
			item.setLastChange(ItemInstance.MODIFIED);
			// Updates database
			// If Adena drop rate is not present it will be x1.
			float adenaRate = RatesConfig.RATE_DROP_AMOUNT_MULTIPLIER.getOrDefault(Inventory.ADENA_ID, 1f);
			if ((itemId == Inventory.ADENA_ID) && (count < (10000 * adenaRate))) {
				// Small adena changes won't be saved to database all the time
				if ((GameTimeManager.getInstance().getGameTicks() % 5) == 0) {
					item.updateDatabase();
				}
			} else {
				item.updateDatabase();
			}
		}
		// If item hasn't be found in inventory, create new one
		else {
			if (!validateCount(itemId, count)) {
				return null;
			}

			for (int i = 0; i < count; i++) {
				ItemTemplate template = ItemTable.getInstance().getTemplate(itemId);
				if (template == null) {
					_log.warn((actor != null ? "[" + actor.getName() + "] " : "") + "Invalid ItemId requested: ", itemId);
					return null;
				}

				item = ItemTable.getInstance().createItem(process, itemId, template.isStackable() ? count : 1, actor, reference);
				item.setOwnerId(getOwnerId());
				item.setItemLocation(getBaseLocation());
				item.setLastChange(ItemInstance.ADDED);

				// Add item in inventory
				addItem(item);
				// Updates database
				item.updateDatabase();

				// If stackable, end loop as entire count is included in 1 instance of item
				if (template.isStackable() || !GeneralConfig.MULTIPLE_ITEM_DROP) {
					break;
				}
			}
		}

		refreshWeight();
		return item;
	}

	/**
	 * Transfers item to another inventory
	 *
	 * @param process   string Identifier of process triggering this action
	 * @param objectId  Item Identifier of the item to be transfered
	 * @param count     Quantity of items to be transfered
	 * @param target    the item container where the item will be moved.
	 * @param actor     Player requesting the item transfer
	 * @param reference Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the new item or the updated item in inventory
	 */
	public ItemInstance transferItem(String process, int objectId, long count, ItemContainer target, Player actor, Object reference) {
		if (target == null) {
			return null;
		}

		ItemInstance sourceitem = getItemByObjectId(objectId);
		if (sourceitem == null) {
			return null;
		}
		ItemInstance targetitem = sourceitem.isStackable() ? target.getItemByItemId(sourceitem.getId()) : null;

		if (count < 0) {
			throw new IllegalArgumentException("Negative counts while transferring an item are not permitted! Process: " + process + " SourceItem: " + String.valueOf(sourceitem) + " TargetItem: " + String.valueOf(targetitem) + " Count: " + count + " Actor: " + String.valueOf(actor) + " Reference: " + String.valueOf(reference));
		}

		synchronized (sourceitem) {
			// check if this item still present in this container
			if (getItemByObjectId(objectId) != sourceitem) {
				return null;
			}

			// Check if requested quantity is available
			if (count > sourceitem.getCount()) {
				count = sourceitem.getCount();
			}

			// If possible, move entire item object
			if ((sourceitem.getCount() == count) && (targetitem == null) && !sourceitem.isStackable()) {
				removeItem(sourceitem);
				target.addItem(process, sourceitem, actor, reference);
				targetitem = sourceitem;
			} else {
				if (sourceitem.getCount() > count) // If possible, only update counts
				{
					sourceitem.changeCount(process, -count, actor, reference);
				} else
				// Otherwise destroy old item
				{
					removeItem(sourceitem);
					ItemTable.getInstance().destroyItem(process, sourceitem, actor, reference);
				}

				if (targetitem != null) // If possible, only update counts
				{
					targetitem.changeCount(process, count, actor, reference);
				} else
				// Otherwise add new item
				{
					targetitem = target.addItem(process, sourceitem.getId(), count, actor, reference);
				}
			}

			// Updates database
			sourceitem.updateDatabase(true);
			if ((targetitem != sourceitem) && (targetitem != null)) {
				targetitem.updateDatabase();
			}
			if (sourceitem.isAugmented()) {
				sourceitem.getAugmentation().removeBonus(actor);
			}
			refreshWeight();
			target.refreshWeight();
		}
		return targetitem;
	}

	/**
	 * Detaches the item from this item container so it can be used as a single instance.
	 *
	 * @param process     string Identifier of process triggering this action
	 * @param item        the item instance to be detached
	 * @param count       the count of items to be detached
	 * @param newLocation the new item location
	 * @param actor       Player requesting the item detach
	 * @param reference   Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return the detached item instance if operation completes successfully, {@code null} if the item does not exist in this container anymore or item count is not available
	 */
	public ItemInstance detachItem(String process, ItemInstance item, long count, ItemLocation newLocation, Player actor, Object reference) {
		if (item == null) {
			return null;
		}

		if (count < 0) {
			throw new IllegalArgumentException("Negative counts while detaching an item are not permitted! Process: " + process + " Item: " + item + " Count: " + count + " Actor: " + String.valueOf(actor) + " Reference: " + String.valueOf(reference));
		}

		synchronized (item) {
			if (!_items.containsKey(item.getObjectId())) {
				return null;
			}

			if (count > item.getCount()) {
				return null;
			}

			if (count == item.getCount()) {
				removeItem(item);
			} else {
				item.changeCount(process, -count, actor, reference);
				item.updateDatabase(true);
				item = ItemTable.getInstance().createItem(process, item.getId(), count, actor, reference);
				item.setOwnerId(getOwnerId());
			}
			item.setItemLocation(newLocation);
			item.updateDatabase(true);
		}

		refreshWeight();

		return item;
	}

	/**
	 * Detaches the item from this item container so it can be used as a single instance.
	 *
	 * @param process      string Identifier of process triggering this action
	 * @param itemObjectId the item object id to be detached
	 * @param count        the count of items to be detached
	 * @param newLocation  the new item location
	 * @param actor        Player requesting the item detach
	 * @param reference    Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return the detached item instance if operation completes successfully, {@code null} if the item does not exist in this container anymore or item count is not available
	 */
	public ItemInstance detachItem(String process, int itemObjectId, long count, ItemLocation newLocation, Player actor, Object reference) {
		final ItemInstance itemInstance = getItemByObjectId(itemObjectId);
		if (itemInstance == null) {
			return null;
		}

		return detachItem(process, itemInstance, count, newLocation, actor, reference);
	}

	/**
	 * Destroy item from inventory and updates database
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param item      : L2ItemInstance to be destroyed
	 * @param actor     : L2PcInstance Player requesting the item destroy
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the destroyed item or the updated item in inventory
	 */
	public ItemInstance destroyItem(String process, ItemInstance item, Player actor, Object reference) {
		return this.destroyItem(process, item, item.getCount(), actor, reference);
	}

	/**
	 * Destroy item from inventory and updates database
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param item      : L2ItemInstance to be destroyed
	 * @param count
	 * @param actor     : L2PcInstance Player requesting the item destroy
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the destroyed item or the updated item in inventory
	 */
	public ItemInstance destroyItem(String process, ItemInstance item, long count, Player actor, Object reference) {
		if (count < 0) {
			throw new IllegalArgumentException("Negative counts while destroying an item are not permitted! Process: " + process + " Item: " + item + " Count: " + count + " Actor: " + String.valueOf(actor) + " Reference: " + String.valueOf(reference));
		}

		synchronized (item) {
			// Adjust item quantity
			if (item.getCount() > count) {
				item.changeCount(process, -count, actor, reference);
				item.setLastChange(ItemInstance.MODIFIED);

				// don't update often for untraced items
				if ((process != null) || ((GameTimeManager.getInstance().getGameTicks() % 10) == 0)) {
					item.updateDatabase();
				}

				refreshWeight();
			} else {
				if (item.getCount() < count) {
					return null;
				}

				boolean removed = removeItem(item);
				if (!removed) {
					return null;
				}

				ItemTable.getInstance().destroyItem(process, item, actor, reference);

				item.updateDatabase();
				refreshWeight();

				item.deleteMe();
			}
		}
		return item;
	}

	/**
	 * Destroy item from inventory by using its <B>objectID</B> and updates database
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param objectId  : int Item Instance identifier of the item to be destroyed
	 * @param count     : int Quantity of items to be destroyed
	 * @param actor     : L2PcInstance Player requesting the item destroy
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the destroyed item or the updated item in inventory
	 */
	public ItemInstance destroyItem(String process, int objectId, long count, Player actor, Object reference) {
		ItemInstance item = getItemByObjectId(objectId);
		if (item == null) {
			return null;
		}
		return this.destroyItem(process, item, count, actor, reference);
	}

	/**
	 * Destroy item from inventory by using its <B>itemId</B> and updates database
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param itemId    : int Item identifier of the item to be destroyed
	 * @param count     : int Quantity of items to be destroyed
	 * @param actor     : L2PcInstance Player requesting the item destroy
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the destroyed item or the updated item in inventory
	 */
	public ItemInstance destroyItemByItemId(String process, int itemId, long count, Player actor, Object reference) {
		ItemInstance item = getItemByItemId(itemId);
		if (item == null) {
			return null;
		}
		return destroyItem(process, item, count, actor, reference);
	}

	/**
	 * Destroy all items from inventory and updates database
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param actor     : L2PcInstance Player requesting the item destroy
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 */
	public void destroyAllItems(String process, Player actor, Object reference) {
		for (ItemInstance item : _items.values()) {
			destroyItem(process, item, actor, reference);
		}
	}

	/**
	 * @return warehouse Adena.
	 */
	public long getAdena() {
		for (ItemInstance item : _items.values()) {
			if (item.getId() == Inventory.ADENA_ID) {
				return item.getCount();
			}
		}
		return 0;
	}

	public long getBeautyTickets() {
		for (ItemInstance item : _items.values()) {
			if (item.getId() == Inventory.BEAUTY_TICKET_ID) {
				return item.getCount();
			}
		}
		return 0;
	}

	public long getFortuneReadingTickets() {
		for (ItemInstance item : _items.values()) {
			if (item.getId() == Inventory.FORTUNE_READING_TICKET) {
				return item.getCount();
			}
		}
		return 0;
	}

	public long getLuxuryFortuneReadingTickets() {
		for (ItemInstance item : _items.values()) {
			if (item.getId() == Inventory.LUXURY_FORTUNE_READING_TICKET) {
				return item.getCount();
			}
		}
		return 0;
	}

	/**
	 * Adds item to inventory for further adjustments.
	 *
	 * @param item : L2ItemInstance to be added from inventory
	 */
	protected void addItem(ItemInstance item) {
		ItemStorage.getInstance().add(item);
		_items.put(item.getObjectId(), item);
	}

	/**
	 * Removes item from inventory for further adjustments.
	 *
	 * @param item : L2ItemInstance to be removed from inventory
	 * @return
	 */
	protected boolean removeItem(ItemInstance item) {
		if(_items.remove(item.getObjectId()) != null) {
			ItemStorage.getInstance().remove(item);
			return true;
		}
		return false;
	}

	/**
	 * Refresh the weight of equipment loaded
	 */
	protected void refreshWeight() {
	}

	/**
	 * Delete item object from world
	 */
	public void deleteMe() {
		if (getOwner() != null) {
			for (ItemInstance item : _items.values()) {
				item.updateDatabase(true);
				item.deleteMe();
				ItemStorage.getInstance().remove(item);
			}
		}
		_items.clear();
	}

	/**
	 * Update database with items in inventory
	 */
	public void updateDatabase() {
		if (getOwner() != null) {
			for (ItemInstance item : _items.values()) {
				item.updateDatabase(true);
			}
		}
	}

	/**
	 * Get back items in container from database
	 */
	public void restore() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("SELECT * FROM items WHERE owner_id=? AND (loc=?)")) {
			ps.setInt(1, getOwnerId());
			ps.setString(2, getBaseLocation().name());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					final ItemInstance item = new ItemInstance(rs);
					
					final Player owner = getOwner() != null ? getOwner().getActingPlayer() : null;
					// If stackable item is found in inventory just add to current quantity
					if (item.isStackable() && (getItemByItemId(item.getId()) != null)) {
						addItem("Restore", item, owner, null);
					} else {
						addItem(item);
					}
				}
			}
			refreshWeight();
		} catch (Exception e) {
			_log.warn("could not restore container:", e);
		}
	}

	public boolean validateCapacity(long slots) {
		return true;
	}

	public boolean validateWeight(long weight) {
		return true;
	}

	/**
	 * If the item is stackable validates 1 slot, if the item isn't stackable validates the item count.
	 *
	 * @param itemId the item Id to verify
	 * @param count  amount of item's weight to validate
	 * @return {@code true} if the item doesn't exists or it validates its slot count
	 */
	public boolean validateCapacityByItemId(int itemId, long count) {
		final ItemTemplate template = ItemTable.getInstance().getTemplate(itemId);
		return (template == null) || (template.isStackable() ? validateCapacity(1) : validateCapacity(count));
	}

	/**
	 * @param itemId the item Id to verify
	 * @param count  amount of item's weight to validate
	 * @return {@code true} if the item doesn't exists or it validates its weight
	 */
	public boolean validateWeightByItemId(int itemId, long count) {
		final ItemTemplate template = ItemTable.getInstance().getTemplate(itemId);
		return (template == null) || validateWeight(template.getWeight() * count);
	}

	/**
	 * @param itemId the item id to check.
	 * @param count  the count to check.
	 * @return {code true} if given count is within positiv max allowed value.
	 */
	public static boolean validateCount(int itemId, long count) {
		return (count >= 0) && (count <= getMaximumAllowedCount(itemId));
	}

	public static long getMaximumAllowedCount(int itemId) {
		if (itemId == Inventory.ADENA_ID) {
			return PlayerConfig.MAX_ADENA;
		}

		return Integer.MAX_VALUE;
	}
}
