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
package org.l2junity.gameserver.datatables;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.idfactory.IdFactory;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.IXmlReader;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.engines.items.DocumentItem;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.instance.L2EventMonsterInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.item.OnItemCreate;
import org.l2junity.gameserver.model.items.Armor;
import org.l2junity.gameserver.model.items.EtcItem;
import org.l2junity.gameserver.model.items.L2Item;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.util.GMAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static org.l2junity.gameserver.model.itemcontainer.Inventory.ADENA_ID;

/**
 * This class serves as a container for all item templates in the game.
 */

@Slf4j
@StartupComponent("Data")
public class ItemTable {
	@Getter(lazy = true)
	private static final ItemTable instance = new ItemTable();

	private static Logger LOGGER_ITEMS = LoggerFactory.getLogger("item");

	public static final Map<String, Integer> _slots = new HashMap<>();

	private L2Item[] _allTemplates;

	static {
		_slots.put("shirt", L2Item.SLOT_UNDERWEAR);
		_slots.put("lbracelet", L2Item.SLOT_L_BRACELET);
		_slots.put("rbracelet", L2Item.SLOT_R_BRACELET);
		_slots.put("talisman", L2Item.SLOT_DECO);
		_slots.put("chest", L2Item.SLOT_CHEST);
		_slots.put("fullarmor", L2Item.SLOT_FULL_ARMOR);
		_slots.put("head", L2Item.SLOT_HEAD);
		_slots.put("hair", L2Item.SLOT_HAIR);
		_slots.put("hairall", L2Item.SLOT_HAIRALL);
		_slots.put("underwear", L2Item.SLOT_UNDERWEAR);
		_slots.put("back", L2Item.SLOT_BACK);
		_slots.put("neck", L2Item.SLOT_NECK);
		_slots.put("legs", L2Item.SLOT_LEGS);
		_slots.put("feet", L2Item.SLOT_FEET);
		_slots.put("gloves", L2Item.SLOT_GLOVES);
		_slots.put("chest,legs", L2Item.SLOT_CHEST | L2Item.SLOT_LEGS);
		_slots.put("belt", L2Item.SLOT_BELT);
		_slots.put("rhand", L2Item.SLOT_R_HAND);
		_slots.put("lhand", L2Item.SLOT_L_HAND);
		_slots.put("lrhand", L2Item.SLOT_LR_HAND);
		_slots.put("rear;lear", L2Item.SLOT_R_EAR | L2Item.SLOT_L_EAR);
		_slots.put("rfinger;lfinger", L2Item.SLOT_R_FINGER | L2Item.SLOT_L_FINGER);
		_slots.put("wolf", L2Item.SLOT_WOLF);
		_slots.put("greatwolf", L2Item.SLOT_GREATWOLF);
		_slots.put("hatchling", L2Item.SLOT_HATCHLING);
		_slots.put("strider", L2Item.SLOT_STRIDER);
		_slots.put("babypet", L2Item.SLOT_BABYPET);
		_slots.put("brooch", L2Item.SLOT_BROOCH);
		_slots.put("brooch_jewel", L2Item.SLOT_BROOCH_JEWEL);
		_slots.put("none", L2Item.SLOT_NONE);

		// retail compatibility
		_slots.put("onepiece", L2Item.SLOT_FULL_ARMOR);
		_slots.put("hair2", L2Item.SLOT_HAIR2);
		_slots.put("dhair", L2Item.SLOT_HAIRALL);
		_slots.put("alldress", L2Item.SLOT_ALLDRESS);
		_slots.put("deco1", L2Item.SLOT_DECO);
		_slots.put("waist", L2Item.SLOT_BELT);
	}

	protected ItemTable() {
		reload();
	}

	public void reload() {
		final List<L2Item> allItems = loadItems();
		int highest = 0;
		int etcItems = 0;
		int armors = 0;
		int weapons = 0;
		for (L2Item item : allItems) {
			if (highest < item.getId()) {
				highest = item.getId();
			}

			if (item instanceof EtcItem) {
				etcItems++;
			} else if (item instanceof Armor) {
				armors++;
			} else {
				weapons++;
			}
		}

		// Build lookup table.
		_allTemplates = new L2Item[highest + 1];
		allItems.forEach(i -> _allTemplates[i.getId()] = i);

		log.info("Highest Item Id used: {}.", highest);
		log.info("Loaded {} EtcItem(s).", etcItems);
		log.info("Loaded {} Armor(s).", armors);
		log.info("Loaded {} Weapon(s).", weapons);
		log.info("Loaded {} Item(s) in total.", (etcItems + armors + weapons));
	}

	private static List<L2Item> loadItems() {
		final List<L2Item> list = new ArrayList<>();

		list.addAll(loadItems(Paths.get("data/stats/items")));

		if (GeneralConfig.CUSTOM_ITEMS_LOAD) {
			list.addAll(loadItems(Paths.get("data/stats/items/custom")));
		}

		return list;
	}

	private static List<L2Item> loadItems(final Path path) {
		final List<L2Item> list = new ArrayList<>();
		try {
			Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1/* Non-recursive load, because of custom sub-directory. */, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (!IXmlReader.XML_FILTER.accept(file)) {
						return FileVisitResult.CONTINUE;
					}

					final DocumentItem document = new DocumentItem(file.toFile());
					document.parse();
					list.addAll(document.getItemList());
					return super.visitFile(file, attrs);
				}
			});
		} catch (final IOException e) {
			log.warn("Failed to load diractory: " + path, e);
		}

		return list;
	}

	/**
	 * Returns the item corresponding to the item ID
	 *
	 * @param id : int designating the item
	 * @return L2Item
	 */
	public L2Item getTemplate(int id) {
		if ((id >= _allTemplates.length) || (id < 0)) {
			return null;
		}

		return _allTemplates[id];
	}

	/**
	 * Create the L2ItemInstance corresponding to the Item Identifier and quantitiy add logs the activity. <B><U> Actions</U> :</B>
	 * <li>Create and Init the L2ItemInstance corresponding to the Item Identifier and quantity</li>
	 * <li>Add the L2ItemInstance object to _allObjects of L2world</li>
	 * <li>Logs Item creation according to log settings</li>
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param itemId    : int Item Identifier of the item to be created
	 * @param count     : int Quantity of items to be created for stackable items
	 * @param actor     : L2PcInstance Player requesting the item creation
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the new item
	 */
	public ItemInstance createItem(String process, int itemId, long count, PlayerInstance actor, Object reference) {
		// Create and Init the L2ItemInstance corresponding to the Item Identifier
		ItemInstance item = new ItemInstance(IdFactory.getInstance().getNextId(), itemId);

		if (process.equalsIgnoreCase("loot")) {
			ScheduledFuture<?> itemLootShedule;
			if ((reference instanceof Attackable) && ((Attackable) reference).isRaid()) // loot privilege for raids
			{
				Attackable raid = (Attackable) reference;
				// if in CommandChannel and was killing a World/RaidBoss
				if ((raid.getFirstCommandChannelAttacked() != null) && !PlayerConfig.AUTO_LOOT_RAIDS) {
					item.setOwnerId(raid.getFirstCommandChannelAttacked().getLeaderObjectId());
					itemLootShedule = ThreadPool.getInstance().scheduleGeneral(new ResetOwner(item), PlayerConfig.LOOT_RAIDS_PRIVILEGE_INTERVAL, TimeUnit.MILLISECONDS);
					item.setItemLootShedule(itemLootShedule);
					item.setInstance(actor.getInstanceWorld());
				}
			} else if (!PlayerConfig.AUTO_LOOT || ((reference instanceof L2EventMonsterInstance) && ((L2EventMonsterInstance) reference).eventDropOnGround())) {
				item.setOwnerId(actor.getObjectId());
				itemLootShedule = ThreadPool.getInstance().scheduleGeneral(new ResetOwner(item), 15000, TimeUnit.MILLISECONDS);
				item.setItemLootShedule(itemLootShedule);
				item.setInstance(actor.getInstanceWorld());
			}
		}

		if (GeneralConfig.DEBUG) {
			log.debug("Item created: {}", item);
		}

		// Add the L2ItemInstance object to _allObjects of L2world
		World.getInstance().addObject(item);

		// Set Item parameters
		if (item.isStackable() && (count > 1)) {
			item.setCount(count);
		}

		if (GeneralConfig.LOG_ITEMS && !process.equals("Reset")) {
			if (!GeneralConfig.LOG_ITEMS_SMALL_LOG || (GeneralConfig.LOG_ITEMS_SMALL_LOG && (item.isEquipable() || (item.getId() == ADENA_ID)))) {
				if (item.getEnchantLevel() > 0) {
					LOGGER_ITEMS.info("CREATE:{}, item {}:+{} {}({}), {}, {}", process, item.getObjectId(), item.getEnchantLevel(), item.getItem().getName(), item.getCount(), actor, reference);
				} else {
					LOGGER_ITEMS.info("CREATE:{}, item {}:{}({}), {}, {}", process, item.getObjectId(), item.getItem().getName(), item.getCount(), actor, reference);
				}
			}
		}

		if (actor != null) {
			if (actor.isGM()) {
				String referenceName = "no-reference";
				if (reference instanceof WorldObject) {
					referenceName = (((WorldObject) reference).getName() != null ? ((WorldObject) reference).getName() : "no-name");
				} else if (reference instanceof String) {
					referenceName = (String) reference;
				}
				String targetName = (actor.getTarget() != null ? actor.getTarget().getName() : "no-target");
				if (GeneralConfig.GMAUDIT) {
					GMAudit.auditGMAction(actor.getName() + " [" + actor.getObjectId() + "]", process + "(id: " + itemId + " count: " + count + " name: " + item.getItemName() + " objId: " + item.getObjectId() + ")", targetName, "L2Object referencing this action is: " + referenceName);
				}
			}
		}

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnItemCreate(process, item, actor, reference), item.getItem());
		return item;
	}

	public ItemInstance createItem(String process, int itemId, int count, PlayerInstance actor) {
		return createItem(process, itemId, count, actor, null);
	}

	/**
	 * Destroys the L2ItemInstance.<br>
	 * <B><U> Actions</U> :</B>
	 * <ul>
	 * <li>Sets L2ItemInstance parameters to be unusable</li>
	 * <li>Removes the L2ItemInstance object to _allObjects of L2world</li>
	 * <li>Logs Item deletion according to log settings</li>
	 * </ul>
	 *
	 * @param process   a string identifier of process triggering this action.
	 * @param item      the item instance to be destroyed.
	 * @param actor     the player requesting the item destroy.
	 * @param reference the object referencing current action like NPC selling item or previous item in transformation.
	 */
	public void destroyItem(String process, ItemInstance item, PlayerInstance actor, Object reference) {
		synchronized (item) {
			long old = item.getCount();
			item.setCount(0);
			item.setOwnerId(0);
			item.setItemLocation(ItemLocation.VOID);
			item.setLastChange(ItemInstance.REMOVED);

			World.getInstance().removeObject(item);
			IdFactory.getInstance().releaseId(item.getObjectId());

			if (GeneralConfig.LOG_ITEMS) {
				if (!GeneralConfig.LOG_ITEMS_SMALL_LOG || (GeneralConfig.LOG_ITEMS_SMALL_LOG && (item.isEquipable() || (item.getId() == ADENA_ID)))) {
					if (item.getEnchantLevel() > 0) {
						LOGGER_ITEMS.info("DELETE:{}, item {}:+{} {}({}), PrevCount({}), {}, {}", process, item.getObjectId(), item.getEnchantLevel(), item.getItem().getName(), item.getCount(), old, actor, reference);
					} else {
						LOGGER_ITEMS.info("DELETE:{}, item {}:{}({}), PrevCount({}), {}, {}", process, item.getObjectId(), item.getItem().getName(), item.getCount(), old, actor, reference);
					}
				}
			}

			if (actor != null) {
				if (actor.isGM()) {
					String referenceName = "no-reference";
					if (reference instanceof WorldObject) {
						referenceName = (((WorldObject) reference).getName() != null ? ((WorldObject) reference).getName() : "no-name");
					} else if (reference instanceof String) {
						referenceName = (String) reference;
					}
					String targetName = (actor.getTarget() != null ? actor.getTarget().getName() : "no-target");
					if (GeneralConfig.GMAUDIT) {
						GMAudit.auditGMAction(actor.getName() + " [" + actor.getObjectId() + "]", process + "(id: " + item.getId() + " count: " + item.getCount() + " itemObjId: " + item.getObjectId() + ")", targetName, "L2Object referencing this action is: " + referenceName);
					}
				}
			}

			// if it's a pet control item, delete the pet as well
			if (item.getItem().isPetItem()) {
				try (Connection con = DatabaseFactory.getInstance().getConnection();
					 PreparedStatement statement = con.prepareStatement("DELETE FROM pets WHERE item_obj_id=?")) {
					// Delete the pet in db
					statement.setInt(1, item.getObjectId());
					statement.execute();
				} catch (Exception e) {
					log.warn("could not delete pet objectid:", e);
				}
			}
		}
	}

	protected static class ResetOwner implements Runnable {
		ItemInstance _item;

		public ResetOwner(ItemInstance item) {
			_item = item;
		}

		@Override
		public void run() {
			_item.setOwnerId(0);
			_item.setItemLootShedule(null);
		}
	}

	public L2Item[] getAllItems() {
		return _allTemplates;
	}

	public int getArraySize() {
		return _allTemplates.length;
	}
}
