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
package org.l2junity.gameserver.model.items.instance;

import org.l2junity.gameserver.engines.IdFactory;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.OlympiadConfig;
import org.l2junity.gameserver.data.xml.impl.AppearanceItemData;
import org.l2junity.gameserver.data.xml.impl.EnchantItemOptionsData;
import org.l2junity.gameserver.data.xml.impl.EnsoulData;
import org.l2junity.gameserver.data.xml.impl.OptionData;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.enums.*;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.ItemsOnGroundManager;
import org.l2junity.gameserver.instancemanager.SiegeGuardManager;
import org.l2junity.gameserver.model.*;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ensoul.EnsoulOption;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerAugment;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemDrop;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerItemPickup;
import org.l2junity.gameserver.model.events.impl.item.OnItemBypassEvent;
import org.l2junity.gameserver.model.events.impl.item.OnItemTalk;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.Armor;
import org.l2junity.gameserver.model.items.EtcItem;
import org.l2junity.gameserver.model.items.L2Item;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.appearance.AppearanceStone;
import org.l2junity.gameserver.model.items.enchant.attribute.AttributeHolder;
import org.l2junity.gameserver.model.items.type.EtcItemType;
import org.l2junity.gameserver.model.items.type.ItemType;
import org.l2junity.gameserver.model.options.EnchantOptions;
import org.l2junity.gameserver.model.options.Options;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.variables.ItemVariables;
import org.l2junity.gameserver.network.client.send.*;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.GMAudit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.l2junity.gameserver.model.itemcontainer.Inventory.ADENA_ID;

/**
 * This class manages items.
 *
 * @version $Revision: 1.4.2.1.2.11 $ $Date: 2005/03/31 16:07:50 $
 */
public final class ItemInstance extends WorldObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemInstance.class);
	private static final Logger _logItems = LoggerFactory.getLogger("item");

	/**
	 * ID of the owner
	 */
	private int _ownerId;

	/**
	 * ID of who dropped the item last, used for knownlist
	 */
	private int _dropperObjectId = 0;

	/**
	 * Quantity of the item
	 */
	private long _count = 1;
	/**
	 * Initial Quantity of the item
	 */
	private long _initCount;
	/**
	 * Remaining time (in miliseconds)
	 */
	private long _time;
	/**
	 * Quantity of the item can decrease
	 */
	private boolean _decrease = false;

	/**
	 * ID of the item
	 */
	private final int _itemId;

	/**
	 * Object L2Item associated to the item
	 */
	private final L2Item _item;

	/**
	 * Location of the item : Inventory, PaperDoll, WareHouse
	 */
	private ItemLocation _loc;

	/**
	 * Slot where item is stored : Paperdoll slot, inventory order ...
	 */
	private int _locData;

	/**
	 * Level of enchantment of the item
	 */
	private int _enchantLevel;

	/**
	 * Wear Item
	 */
	private boolean _wear;

	/**
	 * Augmented Item
	 */
	private VariationInstance _augmentation = null;

	/**
	 * Shadow item
	 */
	private int _mana = -1;
	private boolean _consumingMana = false;
	private static final int MANA_CONSUMPTION_RATE = 60000;

	/**
	 * Custom item types (used loto, race tickets)
	 */
	private int _type1;
	private int _type2;

	private long _dropTime;

	private boolean _published = false;

	private boolean _protected;

	public static final int UNCHANGED = 0;
	public static final int ADDED = 1;
	public static final int REMOVED = 3;
	public static final int MODIFIED = 2;

	//@formatter:off
	public static final int[] DEFAULT_ENCHANT_OPTIONS = new int[]{0, 0, 0};
	//@formatter:on

	private int _lastChange = 2; // 1 ??, 2 modified, 3 removed
	private boolean _existsInDb; // if a record exists in DB.
	private boolean _storedInDb; // if DB data is up-to-date.

	private final ReentrantLock _dbLock = new ReentrantLock();

	private Map<AttributeType, AttributeHolder> _elementals = null;

	private ScheduledFuture<?> itemLootShedule = null;
	private ScheduledFuture<?> _lifeTimeTask;
	private ScheduledFuture<?> _appearanceLifeTimeTask;

	private final DropProtection _dropProtection = new DropProtection();

	private int _shotsMask = 0;

	private final List<Options> _enchantOptions = new ArrayList<>();
	private final Map<Integer, EnsoulOption> _ensoulOptions = new LinkedHashMap<>(3);
	private final Map<Integer, EnsoulOption> _ensoulSpecialOptions = new LinkedHashMap<>(3);

	/**
	 * Constructor of the L2ItemInstance from the objectId and the itemId.
	 *
	 * @param objectId : int designating the ID of the object in the world
	 * @param itemId   : int designating the ID of the item
	 */
	public ItemInstance(int objectId, int itemId) {
		super(objectId);
		setInstanceType(InstanceType.L2ItemInstance);
		_itemId = itemId;
		_item = ItemTable.getInstance().getTemplate(itemId);
		if ((_itemId == 0) || (_item == null)) {
			throw new IllegalArgumentException();
		}
		super.setName(_item.getName());
		_loc = ItemLocation.VOID;
		_type1 = 0;
		_type2 = 0;
		_dropTime = 0;
		_mana = _item.getDuration();
		_time = _item.getTime() == -1 ? -1 : System.currentTimeMillis() + ((long) _item.getTime() * 60 * 1000);
		scheduleLifeTimeTask();
	}

	/**
	 * Constructor of the L2ItemInstance from the objetId and the description of the item given by the L2Item.
	 *
	 * @param objectId : int designating the ID of the object in the world
	 * @param item     : L2Item containing informations of the item
	 */
	public ItemInstance(int objectId, L2Item item) {
		super(objectId);
		setInstanceType(InstanceType.L2ItemInstance);
		_itemId = item.getId();
		_item = item;
		if (_itemId == 0) {
			throw new IllegalArgumentException();
		}
		super.setName(_item.getName());
		_loc = ItemLocation.VOID;
		_mana = _item.getDuration();
		_time = _item.getTime() == -1 ? -1 : System.currentTimeMillis() + ((long) _item.getTime() * 60 * 1000);
		scheduleLifeTimeTask();
	}

	/**
	 * @param rs
	 * @throws SQLException
	 */
	public ItemInstance(ResultSet rs) throws SQLException {
		this(rs.getInt("object_id"), ItemTable.getInstance().getTemplate(rs.getInt("item_id")));
		_count = rs.getLong("count");
		_ownerId = rs.getInt("owner_id");
		_loc = ItemLocation.valueOf(rs.getString("loc"));
		_locData = rs.getInt("loc_data");
		_enchantLevel = rs.getInt("enchant_level");
		_type1 = rs.getInt("custom_type1");
		_type2 = rs.getInt("custom_type2");
		_mana = rs.getInt("mana_left");
		_time = rs.getLong("time");
		_existsInDb = true;
		_storedInDb = true;

		if (isEquipable()) {
			restoreAttributes();
			restoreSpecialAbilities();
		}
	}

	/**
	 * Constructor overload.<br>
	 * Sets the next free object ID in the ID factory.
	 *
	 * @param itemId the item template ID
	 */
	public ItemInstance(int itemId) {
		this(IdFactory.getInstance().getNextId(), itemId);
	}

	/**
	 * Remove a L2ItemInstance from the world and send server->client GetItem packets.<BR>
	 * <BR>
	 * <B><U> Actions</U> :</B><BR>
	 * <BR>
	 * <li>Send a Server->Client Packet GetItem to player that pick up and its _knowPlayers member</li>
	 * <li>Remove the L2Object from the world</li><BR>
	 * <BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T REMOVE the object from _allObjects of L2World </B></FONT><BR>
	 * <BR>
	 * <B><U> Assert </U> :</B><BR>
	 * <BR>
	 * <li>this instanceof L2ItemInstance</li>
	 * <li>_worldRegion != null <I>(L2Object is visible at the beginning)</I></li><BR>
	 * <BR>
	 * <B><U> Example of use </U> :</B><BR>
	 * <BR>
	 * <li>Do Pickup Item : PCInstance and Pet</li><BR>
	 * <BR>
	 *
	 * @param player Player that pick up the item
	 */
	public final void pickupMe(Creature player) {
		assert getWorldRegion() != null;

		WorldRegion oldregion = getWorldRegion();

		// Create a server->client GetItem packet to pick up the L2ItemInstance
		player.broadcastPacket(new GetItem(this, player.getObjectId()));

		synchronized (this) {
			setSpawned(false);
		}

		// if this item is a mercenary ticket, remove the spawns!
		final Castle castle = CastleManager.getInstance().getCastle(this);
		if ((castle != null) && (SiegeGuardManager.getInstance().getSiegeGuardByItem(castle.getResidenceId(), getId()) != null)) {
			SiegeGuardManager.getInstance().removeTicket(this);
			ItemsOnGroundManager.getInstance().removeObject(this);
		}

		// outside of synchronized to avoid deadlocks
		// Remove the L2ItemInstance from the world
		World.getInstance().removeVisibleObject(this, oldregion);

		if (player.isPlayer()) {
			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerItemPickup(player.getActingPlayer(), this), getItem());
		}
	}

	/**
	 * Sets the ownerID of the item
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param owner_id  : int designating the ID of the owner
	 * @param creator   : L2PcInstance Player requesting the item creation
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 */
	public void setOwnerId(String process, int owner_id, Player creator, Object reference) {
		setOwnerId(owner_id);

		if (GeneralConfig.LOG_ITEMS) {
			if (!GeneralConfig.LOG_ITEMS_SMALL_LOG || (GeneralConfig.LOG_ITEMS_SMALL_LOG && (getItem().isEquipable() || (getItem().getId() == ADENA_ID)))) {
				if (getEnchantLevel() > 0) {
					_logItems.info("SETOWNER:{}, item {}:+{} {}({}), {}, {}", process, getObjectId(), getEnchantLevel(), getItem().getName(), _count, creator, reference);
				} else {
					_logItems.info("SETOWNER:{}, item {}:{}({}), {}, {}", process, getObjectId(), getItem().getName(), _count, creator, reference);
				}
			}
		}

		if (creator != null) {
			if (creator.isGM()) {
				String referenceName = "no-reference";
				if (reference instanceof WorldObject) {
					referenceName = (((WorldObject) reference).getName() != null ? ((WorldObject) reference).getName() : "no-name");
				} else if (reference instanceof String) {
					referenceName = (String) reference;
				}
				String targetName = (creator.getTarget() != null ? creator.getTarget().getName() : "no-target");
				if (GeneralConfig.GMAUDIT) {
					GMAudit.auditGMAction(creator.getName() + " [" + creator.getObjectId() + "]", process + "(id: " + getId() + " name: " + getName() + ")", targetName, "L2Object referencing this action is: " + referenceName);
				}
			}
		}
	}

	/**
	 * Sets the ownerID of the item
	 *
	 * @param owner_id : int designating the ID of the owner
	 */
	public void setOwnerId(int owner_id) {
		if (owner_id != _ownerId) {
			// Remove any inventory skills from the old owner.
			removeSkillsFromOwner();

			_ownerId = owner_id;
			_storedInDb = false;

			// Give any inventory skills to the new owner only if the item is in inventory
			// else the skills will be given when location is set to inventory.
			giveSkillsToOwner();
		}
	}

	/**
	 * Returns the ownerID of the item
	 *
	 * @return int : ownerID of the item
	 */
	public int getOwnerId() {
		return _ownerId;
	}

	/**
	 * Sets the location of the item
	 *
	 * @param loc : ItemLocation (enumeration)
	 */
	public void setItemLocation(ItemLocation loc) {
		setItemLocation(loc, 0);
	}

	/**
	 * Sets the location of the item.<BR>
	 * <BR>
	 * <U><I>Remark :</I></U> If loc and loc_data different from database, say datas not up-to-date
	 *
	 * @param loc      : ItemLocation (enumeration)
	 * @param loc_data : int designating the slot where the item is stored or the village for freights
	 */
	public void setItemLocation(ItemLocation loc, int loc_data) {
		if ((loc == _loc) && (loc_data == _locData)) {
			return;
		}

		// Remove any inventory skills from the old owner.
		removeSkillsFromOwner();

		_loc = loc;
		_locData = loc_data;
		_storedInDb = false;

		// Give any inventory skills to the new owner only if the item is in inventory
		// else the skills will be given when location is set to inventory.
		giveSkillsToOwner();
	}

	public ItemLocation getItemLocation() {
		return _loc;
	}

	/**
	 * Sets the quantity of the item.<BR>
	 * <BR>
	 *
	 * @param count the new count to set
	 */
	public void setCount(long count) {
		if (_count != count) {
			_count = count >= -1 ? count : 0;
			_storedInDb = false;
		}
	}

	/**
	 * @return Returns the count.
	 */
	public long getCount() {
		return _count;
	}

	/**
	 * Sets the quantity of the item.<BR>
	 * <BR>
	 * <U><I>Remark :</I></U> If loc and loc_data different from database, say datas not up-to-date
	 *
	 * @param process   : String Identifier of process triggering this action
	 * @param count     : int
	 * @param creator   : L2PcInstance Player requesting the item creation
	 * @param reference : Object Object referencing current action like NPC selling item or previous item in transformation
	 */
	public void changeCount(String process, long count, Player creator, Object reference) {
		if (count == 0) {
			return;
		}
		long old = _count;
		long max = ItemContainer.getMaximumAllowedCount(getId());

		if ((count > 0) && (_count > (max - count))) {
			setCount(max);
		} else {
			setCount(_count + count);
		}

		if (_count < 0) {
			setCount(0);
		}

		_storedInDb = false;

		if (GeneralConfig.LOG_ITEMS && (process != null)) {
			if (!GeneralConfig.LOG_ITEMS_SMALL_LOG || (GeneralConfig.LOG_ITEMS_SMALL_LOG && (_item.isEquipable() || (_item.getId() == ADENA_ID)))) {
				if (getEnchantLevel() > 0) {
					_logItems.info("CHANGE:{}, item {}:+{} {}({}), PrevCount({}), {}, {}", process, getObjectId(), getEnchantLevel(), getItem().getName(), _count, old, creator, reference);
				} else {
					_logItems.info("CHANGE:{}, item {}:{}({}), PrevCount({}), {}, {}", process, getObjectId(), getItem().getName(), _count, old, creator, reference);
				}
			}
		}

		if (creator != null) {
			if (creator.isGM()) {
				String referenceName = "no-reference";
				if (reference instanceof WorldObject) {
					referenceName = (((WorldObject) reference).getName() != null ? ((WorldObject) reference).getName() : "no-name");
				} else if (reference instanceof String) {
					referenceName = (String) reference;
				}
				String targetName = (creator.getTarget() != null ? creator.getTarget().getName() : "no-target");
				if (GeneralConfig.GMAUDIT) {
					GMAudit.auditGMAction(creator.getName() + " [" + creator.getObjectId() + "]", process + "(id: " + getId() + " objId: " + getObjectId() + " name: " + getName() + " count: " + count + ")", targetName, "L2Object referencing this action is: " + referenceName);
				}
			}
		}
	}

	// No logging (function designed for shots only)
	public void changeCountWithoutTrace(int count, Player creator, Object reference) {
		changeCount(null, count, creator, reference);
	}

	/**
	 * Return true if item can be enchanted
	 *
	 * @return boolean
	 */
	public boolean isEnchantable() {
		return ((getItemLocation() == ItemLocation.INVENTORY) || (getItemLocation() == ItemLocation.PAPERDOLL)) && getItem().isEnchantable();
	}

	/**
	 * Returns if item is equipable
	 *
	 * @return boolean
	 */
	public boolean isEquipable() {
		return _item.getBodyPart() != L2Item.SLOT_NONE;
	}

	/**
	 * Returns if item is equipped
	 *
	 * @return boolean
	 */
	public boolean isEquipped() {
		return (_loc == ItemLocation.PAPERDOLL) || (_loc == ItemLocation.PET_EQUIP);
	}

	/**
	 * Returns the slot where the item is stored
	 *
	 * @return int
	 */
	public int getLocationSlot() {
		assert (_loc == ItemLocation.PAPERDOLL) || (_loc == ItemLocation.PET_EQUIP) || (_loc == ItemLocation.INVENTORY) || (_loc == ItemLocation.MAIL) || (_loc == ItemLocation.FREIGHT) || (_loc == ItemLocation.LEASE);
		return _locData;
	}

	/**
	 * Returns the characteristics of the item
	 *
	 * @return L2Item
	 */
	public L2Item getItem() {
		return _item;
	}

	public int getCustomType1() {
		return _type1;
	}

	public int getCustomType2() {
		return _type2;
	}

	public void setCustomType1(int newtype) {
		_type1 = newtype;
	}

	public void setCustomType2(int newtype) {
		_type2 = newtype;
	}

	public void setDropTime(long time) {
		_dropTime = time;
	}

	public long getDropTime() {
		return _dropTime;
	}

	/**
	 * @return the total weight of the item (item weight multiplied by item count).<br>
	 * If the weight count overflows the long's maximum value it will return {@link Long#MAX_VALUE}.
	 */
	public long getTotalWeight() {
		try {
			return Math.multiplyExact(getItem().getWeight(), getCount());
		} catch (ArithmeticException ae) {
			return Long.MAX_VALUE;
		}
	}

	/**
	 * @return the type of item.
	 */
	public ItemType getItemType() {
		return _item.getItemType();
	}

	/**
	 * Gets the item ID.
	 *
	 * @return the item ID
	 */
	@Override
	public int getId() {
		return _itemId;
	}

	/**
	 * @return the display Id of the item.
	 */
	public int getDisplayId() {
		return getItem().getDisplayId();
	}

	/**
	 * @return {@code true} if item is an EtcItem, {@code false} otherwise.
	 */
	public boolean isEtcItem() {
		return (_item instanceof EtcItem);
	}

	/**
	 * @return {@code true} if item is a Weapon/Shield, {@code false} otherwise.
	 */
	public boolean isWeapon() {
		return (_item instanceof Weapon);
	}

	/**
	 * @return {@code true} if item is an Armor, {@code false} otherwise.
	 */
	public boolean isArmor() {
		return (_item instanceof Armor);
	}

	/**
	 * @return the characteristics of the L2EtcItem, {@code false} otherwise.
	 */
	public EtcItem getEtcItem() {
		return (_item instanceof EtcItem) ? (EtcItem) _item : null;
	}

	/**
	 * @return the characteristics of the L2Weapon.
	 */
	public Weapon getWeaponItem() {
		return (_item instanceof Weapon) ? (Weapon) _item : null;
	}

	/**
	 * @return the characteristics of the L2Armor.
	 */
	public Armor getArmorItem() {
		return (_item instanceof Armor) ? (Armor) _item : null;
	}

	/**
	 * @return the quantity of crystals for crystallization.
	 */
	public final int getCrystalCount() {
		return _item.getCrystalCount(_enchantLevel);
	}

	/**
	 * @return the reference price of the item.
	 */
	public long getReferencePrice() {
		return _item.getReferencePrice();
	}

	/**
	 * @return the name of the item.
	 */
	public String getItemName() {
		return _item.getName();
	}

	/**
	 * @return the reuse delay of this item.
	 */
	public int getReuseDelay() {
		return _item.getReuseDelay();
	}

	/**
	 * @return the shared reuse item group.
	 */
	public int getSharedReuseGroup() {
		return _item.getSharedReuseGroup();
	}

	/**
	 * @return the last change of the item
	 */
	public int getLastChange() {
		return _lastChange;
	}

	/**
	 * Sets the last change of the item
	 *
	 * @param lastChange : int
	 */
	public void setLastChange(int lastChange) {
		_lastChange = lastChange;
	}

	/**
	 * Returns if item is stackable
	 *
	 * @return boolean
	 */
	public boolean isStackable() {
		return _item.isStackable();
	}

	/**
	 * Returns if item is dropable
	 *
	 * @return boolean
	 */
	public boolean isDropable() {
		return !isAugmented() && _item.isDropable();
	}

	/**
	 * Returns if item is destroyable
	 *
	 * @return boolean
	 */
	public boolean isDestroyable() {
		return _item.isDestroyable();
	}

	/**
	 * Returns if item is tradeable
	 *
	 * @return boolean
	 */
	public boolean isTradeable() {
		return !isAugmented() && _item.isTradeable();
	}

	/**
	 * Returns if item is sellable
	 *
	 * @return boolean
	 */
	public boolean isSellable() {
		return !isAugmented() && _item.isSellable();
	}

	/**
	 * @param isPrivateWareHouse
	 * @return if item can be deposited in warehouse or freight
	 */
	public boolean isDepositable(boolean isPrivateWareHouse) {
		// equipped, hero and quest items
		if (isEquipped() || !_item.isDepositable()) {
			return false;
		}
		if (!isPrivateWareHouse) {
			// augmented not tradeable
			if (!isTradeable() || isShadowItem()) {
				return false;
			}
		}

		return true;
	}

	public boolean isPotion() {
		return _item.isPotion();
	}

	public boolean isElixir() {
		return _item.isElixir();
	}

	public boolean isScroll() {
		return _item.isScroll();
	}

	public boolean isHeroItem() {
		return _item.isHeroItem();
	}

	public boolean isCommonItem() {
		return _item.isCommon();
	}

	/**
	 * Returns whether this item is pvp or not
	 *
	 * @return boolean
	 */
	public boolean isPvp() {
		return _item.isPvpItem();
	}

	public boolean isOlyRestrictedItem() {
		return getItem().isOlyRestrictedItem();
	}

	/**
	 * @param player
	 * @param allowAdena
	 * @param allowNonTradeable
	 * @return if item is available for manipulation
	 */
	public boolean isAvailable(Player player, boolean allowAdena, boolean allowNonTradeable) {
		final Summon pet = player.getPet();

		return ((!isEquipped()) // Not equipped
				&& ((getItem().getType2() != L2Item.TYPE2_MONEY) || (getItem().getType1() != L2Item.TYPE1_SHIELD_ARMOR)) // not money, not shield
				&& ((pet == null) || (getObjectId() != pet.getControlObjectId())) // Not Control item of currently summoned pet
				&& !(player.isProcessingItem(getObjectId())) // Not momentarily used enchant scroll
				&& (allowAdena || (getId() != Inventory.ADENA_ID)) // Not Adena
				&& (!player.isCastingNow(s -> s.getSkill().getItemConsumeId() != getId())) && (allowNonTradeable || (isTradeable() && (!((getItem().getItemType() == EtcItemType.PET_COLLAR) && player.havePetInvItems())))));
	}

	/**
	 * Returns the level of enchantment of the item
	 *
	 * @return int
	 */
	public int getEnchantLevel() {
		return _enchantLevel;
	}

	/**
	 * @return {@code true} if item is enchanted, {@code false} otherwise
	 */
	public boolean isEnchanted() {
		return _enchantLevel > 0;
	}

	/**
	 * @param enchantLevel the enchant value to set
	 */
	public void setEnchantLevel(int enchantLevel) {
		if (_enchantLevel == enchantLevel) {
			return;
		}
		clearEnchantStats();
		_enchantLevel = enchantLevel;
		applyEnchantStats();
		_storedInDb = false;
	}

	/**
	 * Returns whether this item is augmented or not
	 *
	 * @return true if augmented
	 */
	public boolean isAugmented() {
		return _augmentation != null;
	}

	/**
	 * Returns the augmentation object for this item
	 *
	 * @return augmentation
	 */
	public VariationInstance getAugmentation() {
		return _augmentation;
	}

	/**
	 * Sets a new augmentation
	 *
	 * @param augmentation
	 * @param updateDatabase
	 * @return return true if successfully
	 */
	public boolean setAugmentation(VariationInstance augmentation, boolean updateDatabase) {
		// there shall be no previous augmentation..
		if (_augmentation != null) {
			LOGGER.info("Warning: Augment set for ({}) {} owner: {}", getObjectId(), getName(), getOwnerId());
			return false;
		}

		_augmentation = augmentation;
		if (updateDatabase) {
			updateItemOptions();
		}
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerAugment(getActingPlayer(), this, augmentation, true), getItem());
		return true;
	}

	/**
	 * Remove the augmentation
	 */
	public void removeAugmentation() {
		if (_augmentation == null) {
			return;
		}

		// Copy augmentation before removing it.
		final VariationInstance augment = _augmentation;
		_augmentation = null;

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM item_variations WHERE itemId = ?")) {
			ps.setInt(1, getObjectId());
			ps.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Could not remove augmentation for item: {} from DB:", toString(), e);
		}

		// Notify to scripts.
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerAugment(getActingPlayer(), this, augment, false), getItem());
	}

	public void restoreAttributes() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps1 = con.prepareStatement("SELECT mineralId,option1,option2 FROM item_variations WHERE itemId=?");
			 PreparedStatement ps2 = con.prepareStatement("SELECT elemType,elemValue FROM item_elementals WHERE itemId=?")) {
			ps1.setInt(1, getObjectId());
			try (ResultSet rs = ps1.executeQuery()) {
				if (rs.next()) {
					int mineralId = rs.getInt("mineralId");
					int option1 = rs.getInt("option1");
					int option2 = rs.getInt("option2");
					if ((option1 != -1) && (option2 != -1)) {
						_augmentation = new VariationInstance(mineralId, option1, option2);
					}
				}
			}

			ps2.setInt(1, getObjectId());
			try (ResultSet rs = ps2.executeQuery()) {
				while (rs.next()) {
					final byte attributeType = rs.getByte(1);
					final int attributeValue = rs.getInt(2);
					if ((attributeType != -1) && (attributeValue != -1)) {
						applyAttribute(new AttributeHolder(AttributeType.findByClientId(attributeType), attributeValue));
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Could not restore augmentation and elemental data for item {} from DB: ", toString(), e);
		}
	}

	public void updateItemOptions() {
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			updateItemOptions(con);
		} catch (SQLException e) {
			LOGGER.error("Could not update atributes for item: " + this + " from DB:", e);
		}
	}

	private void updateItemOptions(Connection con) {
		try (PreparedStatement ps = con.prepareStatement("REPLACE INTO item_variations VALUES(?,?,?,?)")) {
			ps.setInt(1, getObjectId());
			ps.setInt(2, _augmentation != null ? _augmentation.getMineralId() : 0);
			ps.setInt(3, _augmentation != null ? _augmentation.getOption1Id() : -1);
			ps.setInt(4, _augmentation != null ? _augmentation.getOption2Id() : -1);
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not update atributes for item: {} from DB:", toString(), e);
		}
	}

	public void updateItemElementals() {
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			updateItemElements(con);
		} catch (SQLException e) {
			LOGGER.error("Could not update elementals for item: " + this + " from DB:", e);
		}
	}

	private void updateItemElements(Connection con) {
		try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_elementals WHERE itemId = ?")) {
			ps.setInt(1, getObjectId());
			ps.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Could not update elementals for item: {} from DB:", toString(), e);
		}

		if (_elementals == null) {
			return;
		}

		try (PreparedStatement ps = con.prepareStatement("INSERT INTO item_elementals VALUES(?,?,?)")) {
			for (AttributeHolder attribute : _elementals.values()) {
				ps.setInt(1, getObjectId());
				ps.setByte(2, attribute.getType().getClientId());
				ps.setInt(3, attribute.getValue());
				ps.executeUpdate();
				ps.clearParameters();
			}
		} catch (SQLException e) {
			LOGGER.error("Could not update elementals for item: {} from DB:", toString(), e);
		}
	}

	public Collection<AttributeHolder> getAttributes() {
		return _elementals != null ? _elementals.values() : null;
	}

	public boolean hasAttributes() {
		return (_elementals != null) && !_elementals.isEmpty();
	}

	public AttributeHolder getAttribute(AttributeType type) {
		return _elementals != null ? _elementals.get(type) : null;
	}

	public AttributeHolder getAttackAttribute() {
		if (isWeapon()) {
			if (getItem().getAttributes() != null) {
				return getItem().getAttributes().stream().findFirst().orElse(null);
			} else if (_elementals != null) {
				return _elementals.values().stream().findFirst().orElse(null);
			}
		}
		return null;
	}

	public AttributeType getAttackAttributeType() {
		final AttributeHolder holder = getAttackAttribute();
		return holder != null ? holder.getType() : AttributeType.NONE;
	}

	public int getAttackAttributePower() {
		final AttributeHolder holder = getAttackAttribute();
		return holder != null ? holder.getValue() : 0;
	}

	public int getDefenceAttribute(AttributeType element) {
		if (isArmor()) {
			if (getItem().getAttributes() != null) {
				final AttributeHolder attribute = getItem().getAttribute(element);
				if (attribute != null) {
					return attribute.getValue();
				}
			} else if (_elementals != null) {
				final AttributeHolder attribute = getAttribute(element);
				if (attribute != null) {
					return attribute.getValue();
				}
			}
		}
		return 0;
	}

	private synchronized void applyAttribute(AttributeHolder holder) {
		if (_elementals == null) {
			_elementals = new LinkedHashMap<>(3);
			_elementals.put(holder.getType(), holder);
		} else {
			final AttributeHolder attribute = getAttribute(holder.getType());
			if (attribute != null) {
				attribute.setValue(holder.getValue());
			} else {
				_elementals.put(holder.getType(), holder);
			}
		}
	}

	/**
	 * Add elemental attribute to item and save to db
	 *
	 * @param holder
	 * @param updateDatabase
	 */
	public void setAttribute(AttributeHolder holder, boolean updateDatabase) {
		applyAttribute(holder);
		if (updateDatabase) {
			updateItemElementals();
		}
	}

	/**
	 * Remove elemental from item
	 *
	 * @param type byte element to remove
	 */
	public void clearAttribute(AttributeType type) {
		if ((_elementals == null) || (getAttribute(type) == null)) {
			return;
		}

		synchronized (_elementals) {
			_elementals.remove(type);
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM item_elementals WHERE itemId = ? AND elemType = ?")) {
			ps.setInt(1, getObjectId());
			ps.setByte(2, type.getClientId());
			ps.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Could not remove elemental enchant for item: {} from DB:", toString(), e);
		}
	}

	public void clearAllAttributes() {
		if (_elementals == null) {
			return;
		}

		synchronized (_elementals) {
			_elementals.clear();
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM item_elementals WHERE itemId = ?")) {
			ps.setInt(1, getObjectId());
			ps.executeUpdate();
		} catch (Exception e) {
			LOGGER.error("Could not remove all elemental enchant for item: {} from DB:", toString(), e);
		}
	}

	/**
	 * Returns true if this item is a shadow item Shadow items have a limited life-time
	 *
	 * @return
	 */
	public boolean isShadowItem() {
		return (_mana >= 0);
	}

	/**
	 * Returns the remaining mana of this shadow item
	 *
	 * @return lifeTime
	 */
	public int getMana() {
		return _mana;
	}

	/**
	 * Decreases the mana of this shadow item, sends a inventory update schedules a new consumption task if non is running optionally one could force a new task
	 *
	 * @param resetConsumingMana if true forces a new consumption task if item is equipped
	 */
	public void decreaseMana(boolean resetConsumingMana) {
		decreaseMana(resetConsumingMana, 1);
	}

	/**
	 * Decreases the mana of this shadow item, sends a inventory update schedules a new consumption task if non is running optionally one could force a new task
	 *
	 * @param resetConsumingMana if forces a new consumption task if item is equipped
	 * @param count              how much mana decrease
	 */
	public void decreaseMana(boolean resetConsumingMana, int count) {
		if (!isShadowItem()) {
			return;
		}

		if ((_mana - count) >= 0) {
			_mana -= count;
		} else {
			_mana = 0;
		}

		if (_storedInDb) {
			_storedInDb = false;
		}
		if (resetConsumingMana) {
			_consumingMana = false;
		}

		final Player player = getActingPlayer();
		if (player != null) {
			SystemMessage sm;
			switch (_mana) {
				case 10:
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_REMAINING_MANA_IS_NOW_10);
					sm.addItemName(_item);
					player.sendPacket(sm);
					break;
				case 5:
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_REMAINING_MANA_IS_NOW_5);
					sm.addItemName(_item);
					player.sendPacket(sm);
					break;
				case 1:
					sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_REMAINING_MANA_IS_NOW_1_IT_WILL_DISAPPEAR_SOON);
					sm.addItemName(_item);
					player.sendPacket(sm);
					break;
			}

			if (_mana == 0) // The life time has expired
			{
				sm = SystemMessage.getSystemMessage(SystemMessageId.S1_S_REMAINING_MANA_IS_NOW_0_AND_THE_ITEM_HAS_DISAPPEARED);
				sm.addItemName(_item);
				player.sendPacket(sm);

				// unequip
				if (isEquipped()) {
					ItemInstance[] unequiped = player.getInventory().unEquipItemInSlotAndRecord(getLocationSlot());
					InventoryUpdate iu = new InventoryUpdate();
					for (ItemInstance item : unequiped) {
						item.unChargeAllShots();
						iu.addModifiedItem(item);
					}
					player.sendInventoryUpdate(iu);
					player.broadcastUserInfo();
				}

				if (getItemLocation() != ItemLocation.WAREHOUSE) {
					// destroy
					player.getInventory().destroyItem("L2ItemInstance", this, player, null);

					// send update
					InventoryUpdate iu = new InventoryUpdate();
					iu.addRemovedItem(this);
					player.sendInventoryUpdate(iu);
				} else {
					player.getWarehouse().destroyItem("L2ItemInstance", this, player, null);
				}
			} else {
				// Reschedule if still equipped
				if (!_consumingMana && isEquipped()) {
					scheduleConsumeManaTask();
				}
				if (getItemLocation() != ItemLocation.WAREHOUSE) {
					InventoryUpdate iu = new InventoryUpdate();
					iu.addModifiedItem(this);
					player.sendInventoryUpdate(iu);
				}
			}
		}
	}

	public void scheduleConsumeManaTask() {
		if (_consumingMana) {
			return;
		}
		_consumingMana = true;
		ThreadPool.getInstance().scheduleEffect(() -> decreaseMana(true), MANA_CONSUMPTION_RATE, TimeUnit.MILLISECONDS);
	}

	/**
	 * Returns false cause item can't be attacked
	 *
	 * @return boolean false
	 */
	@Override
	public boolean isAutoAttackable(Creature attacker) {
		return false;
	}

	/**
	 * Updates the database.<BR>
	 */
	public void updateDatabase() {
		updateDatabase(false);
	}

	/**
	 * Updates the database.<BR>
	 *
	 * @param force if the update should necessarilly be done.
	 */
	public void updateDatabase(boolean force) {
		_dbLock.lock();

		try {
			if (_existsInDb) {
				if ((_ownerId == 0) || (_loc == ItemLocation.VOID) || (_loc == ItemLocation.REFUND) || ((_count == 0) && (_loc != ItemLocation.LEASE))) {
					removeFromDb();
				} else if (!GeneralConfig.LAZY_ITEMS_UPDATE || force) {
					updateInDb();
				}
			} else {
				if ((_ownerId == 0) || (_loc == ItemLocation.VOID) || (_loc == ItemLocation.REFUND) || ((_count == 0) && (_loc != ItemLocation.LEASE))) {
					return;
				}
				insertIntoDb();
			}
		} finally {
			_dbLock.unlock();
		}
	}

	/**
	 * Init a dropped L2ItemInstance and add it in the world as a visible object.<BR>
	 * <BR>
	 * <B><U> Actions</U> :</B><BR>
	 * <BR>
	 * <li>Set the x,y,z position of the L2ItemInstance dropped and update its _worldregion</li>
	 * <li>Add the L2ItemInstance dropped to _visibleObjects of its L2WorldRegion</li>
	 * <li>Add the L2ItemInstance dropped in the world as a <B>visible</B> object</li><BR>
	 * <BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T ADD the object to _allObjects of L2World </B></FONT><BR>
	 * <BR>
	 * <B><U> Assert </U> :</B><BR>
	 * <BR>
	 * <li>_worldRegion == null <I>(L2Object is invisible at the beginning)</I></li><BR>
	 * <BR>
	 * <B><U> Example of use </U> :</B><BR>
	 * <BR>
	 * <li>Drop item</li>
	 * <li>Call Pet</li><BR>
	 */
	public class ItemDropTask implements Runnable {
		private double _x, _y, _z;
		private final Creature _dropper;
		private final ItemInstance _itеm;

		public ItemDropTask(ItemInstance item, Creature dropper, double x, double y, double z) {
			_x = x;
			_y = y;
			_z = z;
			_dropper = dropper;
			_itеm = item;
		}

		@Override
		public final void run() {
			assert _itеm.getWorldRegion() == null;

			if (_dropper != null) {
				final Instance instance = _dropper.getInstanceWorld();
				final Location dropDest = GeoData.getInstance().moveCheck(_dropper.getX(), _dropper.getY(), _dropper.getZ(), _x, _y, _z, instance);
				_x = dropDest.getX();
				_y = dropDest.getY();
				_z = dropDest.getZ();
				setInstance(instance); // Inherit instancezone when dropped in visible world
			} else {
				setInstance(null); // No dropper? Make it a global item...
			}

			synchronized (_itеm) {
				// Set the x,y,z position of the L2ItemInstance dropped and update its _worldregion
				_itеm.setSpawned(true);
				_itеm.setXYZ(_x, _y, _z);
			}

			_itеm.setDropTime(System.currentTimeMillis());
			_itеm.setDropperObjectId(_dropper != null ? _dropper.getObjectId() : 0); // Set the dropper Id for the knownlist packets in sendInfo

			// Add the L2ItemInstance dropped in the world as a visible object
			World.getInstance().addVisibleObject(_itеm, _itеm.getWorldRegion());
			if (GeneralConfig.SAVE_DROPPED_ITEM) {
				ItemsOnGroundManager.getInstance().save(_itеm);
			}
			_itеm.setDropperObjectId(0); // Set the dropper Id back to 0 so it no longer shows the drop packet
		}
	}

	public final void dropMe(Creature dropper, double x, double y, double z) {
		ThreadPool.getInstance().executeGeneral(new ItemDropTask(this, dropper, x, y, z));
		if ((dropper != null) && dropper.isPlayer()) {
			// Notify to scripts
			EventDispatcher.getInstance().notifyEventAsync(new OnPlayerItemDrop(dropper.getActingPlayer(), this, new Location(x, y, z)), getItem());
		}
	}

	/**
	 * Update the database with values of the item
	 */
	private void updateInDb() {
		assert _existsInDb;

		if (_wear) {
			return;
		}

		if (_storedInDb) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("UPDATE items SET owner_id=?,count=?,loc=?,loc_data=?,enchant_level=?,custom_type1=?,custom_type2=?,mana_left=?,time=? " + "WHERE object_id = ?")) {
			ps.setInt(1, _ownerId);
			ps.setLong(2, _count);
			ps.setString(3, _loc.name());
			ps.setInt(4, _locData);
			ps.setInt(5, getEnchantLevel());
			ps.setInt(6, getCustomType1());
			ps.setInt(7, getCustomType2());
			ps.setInt(8, getMana());
			ps.setLong(9, getTime());
			ps.setInt(10, getObjectId());
			ps.executeUpdate();
			_existsInDb = true;
			_storedInDb = true;
		} catch (Exception e) {
			LOGGER.error("Could not update item " + this + " in DB: Reason: " + e.getMessage(), e);
		}
	}

	/**
	 * Insert the item in database
	 */
	private void insertIntoDb() {
		assert !_existsInDb && (getObjectId() != 0);

		if (_wear) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("INSERT INTO items (owner_id,item_id,count,loc,loc_data,enchant_level,object_id,custom_type1,custom_type2,mana_left,time) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?)")) {
			ps.setInt(1, _ownerId);
			ps.setInt(2, _itemId);
			ps.setLong(3, _count);
			ps.setString(4, _loc.name());
			ps.setInt(5, _locData);
			ps.setInt(6, getEnchantLevel());
			ps.setInt(7, getObjectId());
			ps.setInt(8, _type1);
			ps.setInt(9, _type2);
			ps.setInt(10, getMana());
			ps.setLong(11, getTime());

			ps.executeUpdate();
			_existsInDb = true;
			_storedInDb = true;

			if (_augmentation != null) {
				updateItemOptions(con);
			}
			if (_elementals != null) {
				updateItemElements(con);
			}
			if ((_ensoulOptions != null) || (_ensoulSpecialOptions != null)) {
				updateSpecialAbilities(con);
			}
		} catch (Exception e) {
			LOGGER.error("Could not insert item " + this + " into DB: Reason: " + e.getMessage(), e);
		}
	}

	/**
	 * Delete item from database
	 */
	private void removeFromDb() {
		assert _existsInDb;

		if (_wear) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			try (PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE object_id = ?")) {
				ps.setInt(1, getObjectId());
				ps.executeUpdate();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_variations WHERE itemId = ?")) {
				ps.setInt(1, getObjectId());
				ps.executeUpdate();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_elementals WHERE itemId = ?")) {
				ps.setInt(1, getObjectId());
				ps.executeUpdate();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_special_abilities WHERE objectId = ?")) {
				ps.setInt(1, getObjectId());
				ps.executeUpdate();
			}

			try (PreparedStatement ps = con.prepareStatement("DELETE FROM item_variables WHERE id = ?")) {
				ps.setInt(1, getObjectId());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			LOGGER.error("Could not delete item {} in DB", this, e);
		} finally {
			_existsInDb = false;
			_storedInDb = false;
		}
	}

	/**
	 * Returns the item in String format
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return _item + "[" + getObjectId() + "]";
	}

	public void resetOwnerTimer() {
		if (itemLootShedule != null) {
			itemLootShedule.cancel(true);
			itemLootShedule = null;
		}
	}

	public void setItemLootShedule(ScheduledFuture<?> sf) {
		itemLootShedule = sf;
	}

	public ScheduledFuture<?> getItemLootShedule() {
		return itemLootShedule;
	}

	public void setProtected(boolean isProtected) {
		_protected = isProtected;
	}

	public boolean isProtected() {
		return _protected;
	}

	public void setCountDecrease(boolean decrease) {
		_decrease = decrease;
	}

	public boolean getCountDecrease() {
		return _decrease;
	}

	public void setInitCount(int InitCount) {
		_initCount = InitCount;
	}

	public long getInitCount() {
		return _initCount;
	}

	public void restoreInitCount() {
		if (_decrease) {
			setCount(_initCount);
		}
	}

	public boolean isTimeLimitedItem() {
		return (_time > 0);
	}

	/**
	 * Returns (current system time + time) of this time limited item
	 *
	 * @return Time
	 */
	public long getTime() {
		return _time;
	}

	public long getRemainingTime() {
		return _time - System.currentTimeMillis();
	}

	public void endOfLife() {
		final Player player = getActingPlayer();
		if (player != null) {
			if (isEquipped()) {
				final ItemInstance[] unequiped = player.getInventory().unEquipItemInSlotAndRecord(getLocationSlot());
				final InventoryUpdate iu = new InventoryUpdate();
				for (ItemInstance item : unequiped) {
					item.unChargeAllShots();
					iu.addModifiedItem(item);
				}
				player.sendInventoryUpdate(iu);
			}

			if (getItemLocation() != ItemLocation.WAREHOUSE) {
				// destroy
				player.getInventory().destroyItem("L2ItemInstance", this, player, null);

				// send update
				InventoryUpdate iu = new InventoryUpdate();
				iu.addRemovedItem(this);
				player.sendInventoryUpdate(iu);
			} else {
				player.getWarehouse().destroyItem("L2ItemInstance", this, player, null);
			}
			player.sendPacket(SystemMessageId.THE_LIMITED_TIME_ITEM_HAS_DISAPPEARED_BECAUSE_THE_REMAINING_TIME_RAN_OUT);
		}
	}

	public void scheduleLifeTimeTask() {
		if (!isTimeLimitedItem()) {
			return;
		} else if (getRemainingTime() <= 0) {
			endOfLife();
		} else {
			if (_lifeTimeTask != null) {
				_lifeTimeTask.cancel(true);
			}
			_lifeTimeTask = ThreadPool.getInstance().scheduleGeneral(new ScheduleLifeTimeTask(this), getRemainingTime(), TimeUnit.MILLISECONDS);
		}
	}

	static class ScheduleLifeTimeTask implements Runnable {
		private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleLifeTimeTask.class);
		private final ItemInstance _limitedItem;

		ScheduleLifeTimeTask(ItemInstance item) {
			_limitedItem = item;
		}

		@Override
		public void run() {
			try {
				if (_limitedItem != null) {
					_limitedItem.endOfLife();
				}
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
	}

	public void setDropperObjectId(int id) {
		_dropperObjectId = id;
	}

	@Override
	public void sendInfo(Player activeChar) {
		if (_dropperObjectId != 0) {
			activeChar.sendPacket(new DropItem(this, _dropperObjectId));
		} else {
			activeChar.sendPacket(new SpawnItem(this));
		}
	}

	public final DropProtection getDropProtection() {
		return _dropProtection;
	}

	public boolean isPublished() {
		return _published;
	}

	public void publish() {
		_published = true;
	}

	@Override
	public boolean decayMe() {
		if (GeneralConfig.SAVE_DROPPED_ITEM) {
			ItemsOnGroundManager.getInstance().removeObject(this);
		}

		return super.decayMe();
	}

	public boolean isQuestItem() {
		return getItem().isQuestItem();
	}

	public boolean isElementable() {
		if ((getItemLocation() == ItemLocation.INVENTORY) || (getItemLocation() == ItemLocation.PAPERDOLL)) {
			return getItem().isElementable();
		}
		return false;
	}

	public boolean isFreightable() {
		return getItem().isFreightable();
	}

	public int useSkillDisTime() {
		return getItem().useSkillDisTime();
	}

	public int getOlyEnchantLevel() {
		Player player = getActingPlayer();
		int enchant = getEnchantLevel();

		if (player == null) {
			return enchant;
		}

		if (player.isInOlympiadMode() && (OlympiadConfig.ALT_OLY_ENCHANT_LIMIT >= 0) && (enchant > OlympiadConfig.ALT_OLY_ENCHANT_LIMIT)) {
			enchant = OlympiadConfig.ALT_OLY_ENCHANT_LIMIT;
		}

		return enchant;
	}

	public boolean hasPassiveSkills() {
		return (getItemType() == EtcItemType.ENCHT_ATTR_RUNE) && (getItemLocation() == ItemLocation.INVENTORY) && (getOwnerId() > 0) && (getItem().getSkills(ItemSkillType.NORMAL) != null);
	}

	public void giveSkillsToOwner() {
		if (!hasPassiveSkills()) {
			return;
		}

		final Player player = getActingPlayer();
		if (player != null) {
			getItem().forEachSkill(ItemSkillType.NORMAL, holder ->
			{
				final Skill skill = holder.getSkill();
				if (skill.isPassive()) {
					player.addSkill(skill, false);
				}
			});
		}
	}

	public void removeSkillsFromOwner() {
		if (!hasPassiveSkills()) {
			return;
		}

		final Player player = getActingPlayer();
		if (player != null) {
			getItem().forEachSkill(ItemSkillType.NORMAL, holder ->
			{
				final Skill skill = holder.getSkill();
				if (skill.isPassive()) {
					player.removeSkill(skill, false, skill.isPassive());
				}
			});
		}
	}

	@Override
	public boolean isItem() {
		return true;
	}

	@Override
	public ItemInstance asItem() {
		return this;
	}

	@Override
	public Player getActingPlayer() {
		return World.getInstance().getPlayer(getOwnerId());
	}

	public int getEquipReuseDelay() {
		return _item.getEquipReuseDelay();
	}

	/**
	 * @param activeChar
	 * @param command
	 */
	public void onBypassFeedback(Player activeChar, String command) {
		String event = null;

		// If no command, open default html and replace objectId.
		if (command == null) {
			final NpcHtmlMessage html = new NpcHtmlMessage(0, getId());
			html.setFile(activeChar.getLang(), getItem().getHtml());
			html.replace("%objectId%", getObjectId());
			activeChar.sendPacket(html);
		} else if (command.startsWith("Quest")) {
			String questName = command.substring(6);
			int idx = questName.indexOf(' ');
			if (idx > 0) {
				event = questName.substring(idx).trim();
			}
		} else if (command.startsWith("html")) {
			final String path = command.substring(5);

			// Check if nobody is trying to get out of html folder
			if (path.indexOf("..") == -1) {
				final NpcHtmlMessage html = new NpcHtmlMessage(0, getId());
				html.setFile(activeChar.getLang(), "item/" + path);
				html.replace("%objectId%", getObjectId());
				activeChar.sendPacket(html);
			}
		}

		if (event != null) {
			EventDispatcher.getInstance().notifyEventAsync(new OnItemBypassEvent(this, activeChar, event), getItem());
		} else {
			EventDispatcher.getInstance().notifyEventAsync(new OnItemTalk(this, activeChar), getItem());
		}
	}

	@Override
	public boolean isChargedShot(ShotType type) {
		return (_shotsMask & type.getMask()) == type.getMask();
	}

	@Override
	public void setChargedShot(ShotType type, boolean charged) {
		if (charged) {
			_shotsMask |= type.getMask();
		} else {
			_shotsMask &= ~type.getMask();
		}
	}

	public void unChargeAllShots() {
		_shotsMask = 0;
	}

	/**
	 * Returns enchant effect object for this item
	 *
	 * @return enchanteffect
	 */
	public int[] getEnchantOptions() {
		final EnchantOptions op = EnchantItemOptionsData.getInstance().getOptions(this);
		if (op != null) {
			return op.getOptions();
		}
		return DEFAULT_ENCHANT_OPTIONS;
	}

	public Collection<EnsoulOption> getSpecialAbilities() {
		return Collections.unmodifiableCollection(_ensoulOptions.values());
	}

	public EnsoulOption getSpecialAbility(int index) {
		return _ensoulOptions.get(index);
	}

	public Collection<EnsoulOption> getAdditionalSpecialAbilities() {
		return Collections.unmodifiableCollection(_ensoulSpecialOptions.values());
	}

	public EnsoulOption getAdditionalSpecialAbility(int index) {
		return _ensoulSpecialOptions.get(index);
	}

	public void addSpecialAbility(EnsoulOption option, int position, int type, boolean updateInDB) {
		if (type == 1) // Adding regular ability
		{
			final EnsoulOption oldOption = _ensoulOptions.put(position, option);
			if (oldOption != null) {
				removeSpecialAbility(oldOption);
			}
		} else if (type == 2) // Adding special ability
		{
			final EnsoulOption oldOption = _ensoulSpecialOptions.put(position, option);
			if (oldOption != null) {
				removeSpecialAbility(oldOption);
			}
		}

		if (updateInDB) {
			updateSpecialAbilities();
		}
	}

	public void clearSpecialAbilities() {
		_ensoulOptions.values().forEach(this::clearSpecialAbility);
		_ensoulSpecialOptions.values().forEach(this::clearSpecialAbility);
	}

	public void applySpecialAbilities() {
		if (!isEquipped()) {
			return;
		}

		_ensoulOptions.values().forEach(this::applySpecialAbility);
		_ensoulSpecialOptions.values().forEach(this::applySpecialAbility);
	}

	private void removeSpecialAbility(EnsoulOption option) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("DELETE FROM item_special_abilities WHERE objectId = ? AND optionId = ?")) {
			ps.setInt(1, getObjectId());
			ps.setInt(2, option.getId());
			ps.execute();

			final Skill skill = option.getSkill();
			if (skill != null) {
				final Player player = getActingPlayer();
				if (player != null) {
					player.removeSkill(skill.getId());
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Couldn't remove special ability for item: {}", this, e);
		}
	}

	private void applySpecialAbility(EnsoulOption option) {
		final Skill skill = option.getSkill();
		if (skill != null) {
			final Player player = getActingPlayer();
			if (player != null) {
				if (player.getSkillLevel(skill.getId()) != skill.getLevel()) {
					player.addSkill(skill, false);
				}
			}
		}
	}

	private void clearSpecialAbility(EnsoulOption option) {
		final Skill skill = option.getSkill();
		if (skill != null) {
			final Player player = getActingPlayer();
			if (player != null) {
				player.removeSkill(skill, false, true);
			}
		}
	}

	private void restoreSpecialAbilities() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("SELECT * FROM item_special_abilities WHERE objectId = ? ORDER BY position")) {
			ps.setInt(1, getObjectId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					final int optionId = rs.getInt("optionId");
					final int type = rs.getInt("type");
					final int position = rs.getInt("position");
					final EnsoulOption option = EnsoulData.getInstance().getOption(optionId);
					if (option != null) {
						addSpecialAbility(option, position, type, false);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Couldn't restore special abilities for item: {}", this, e);
		}
	}

	public void updateSpecialAbilities() {
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			updateSpecialAbilities(con);
		} catch (Exception e) {
			LOGGER.warn("Couldn't update item special abilities", e);
		}
	}

	private void updateSpecialAbilities(Connection con) {
		try (PreparedStatement ps = con.prepareStatement("INSERT INTO item_special_abilities (`objectId`, `type`, `optionId`, `position`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE type = ?, optionId = ?, position = ?")) {
			ps.setInt(1, getObjectId());
			for (Entry<Integer, EnsoulOption> entry : _ensoulOptions.entrySet()) {
				ps.setInt(2, 1); // regular options
				ps.setInt(3, entry.getValue().getId());
				ps.setInt(4, entry.getKey());

				ps.setInt(5, 1); // regular options
				ps.setInt(6, entry.getValue().getId());
				ps.setInt(7, entry.getKey());
				ps.execute();
			}

			for (Entry<Integer, EnsoulOption> entry : _ensoulSpecialOptions.entrySet()) {
				ps.setInt(2, 2); // special options
				ps.setInt(3, entry.getValue().getId());
				ps.setInt(4, entry.getKey());

				ps.setInt(5, 2); // special options
				ps.setInt(6, entry.getValue().getId());
				ps.setInt(7, entry.getKey());
				ps.execute();
			}
		} catch (Exception e) {
			LOGGER.warn("Couldn't update item special abilities", e);
		}
	}

	/**
	 * Clears all the enchant bonuses if item is enchanted and containing bonuses for enchant value.
	 */
	public void clearEnchantStats() {
		final Player player = getActingPlayer();
		if (player != null) {
			_enchantOptions.forEach(op -> op.remove(player));
		}

		_enchantOptions.clear();
	}

	/**
	 * Clears and applies all the enchant bonuses if item is enchanted and containing bonuses for enchant value.
	 */
	public void applyEnchantStats() {
		final Player player = getActingPlayer();
		if (!isEquipped() || (player == null) || (getEnchantOptions() == DEFAULT_ENCHANT_OPTIONS)) {
			return;
		}

		for (int id : getEnchantOptions()) {
			final Options options = OptionData.getInstance().getOptions(id);
			if (options != null) {
				options.apply(player);
				_enchantOptions.add(options);
			} else if (id != 0) {
				LOGGER.info("applyEnchantStats: Couldn't find option: " + id);
			}
		}

		player.broadcastUserInfo();
	}

	@Override
	public void setHeading(int heading) {
	}

	public void deleteMe() {
		if ((_lifeTimeTask != null) && !_lifeTimeTask.isDone()) {
			_lifeTimeTask.cancel(false);
			_lifeTimeTask = null;
		}

		if ((_appearanceLifeTimeTask != null) && !_appearanceLifeTimeTask.isDone()) {
			_appearanceLifeTimeTask.cancel(false);
			_appearanceLifeTimeTask = null;
		}
	}

	public final ItemVariables getVariables() {
		final ItemVariables vars = getScript(ItemVariables.class);
		return vars != null ? vars : addScript(new ItemVariables(getObjectId()));
	}

	public int getVisualId() {
		final int visualId = getVariables().getInt(ItemVariables.VISUAL_ID, 0);
		if (visualId > 0) {
			final int appearanceStoneId = getVariables().getInt(ItemVariables.VISUAL_APPEARANCE_STONE_ID, 0);
			if (appearanceStoneId > 0) {
				final AppearanceStone stone = AppearanceItemData.getInstance().getStone(appearanceStoneId);
				if (stone != null) {
					final Player player = getActingPlayer();
					if (player != null) {
						if (!stone.getRaces().isEmpty() && !stone.getRaces().contains(player.getRace())) {
							return 0;
						}
						if (!stone.getRacesNot().isEmpty() && stone.getRacesNot().contains(player.getRace())) {
							return 0;
						}
					}
				}
			}
		}
		return visualId;
	}

	public void setVisualId(int visualId) {
		getVariables().set(ItemVariables.VISUAL_ID, visualId);
	}

	public long getVisualLifeTime() {
		return getVariables().getLong(ItemVariables.VISUAL_APPEARANCE_LIFE_TIME, 0);
	}

	public void scheduleVisualLifeTime() {
		if (_appearanceLifeTimeTask != null) {
			_appearanceLifeTimeTask.cancel(false);
		}
		if (getVisualLifeTime() > 0) {
			final long time = getVisualLifeTime() - System.currentTimeMillis();
			if (time > 0) {
				_appearanceLifeTimeTask = ThreadPool.getInstance().scheduleGeneral(this::onVisualLifeTimeEnd, time, TimeUnit.MILLISECONDS);
			} else {
				ThreadPool.getInstance().executeGeneral(this::onVisualLifeTimeEnd);
			}
		}
	}

	private void onVisualLifeTimeEnd() {
		final ItemVariables vars = getVariables();
		vars.remove(ItemVariables.VISUAL_ID);
		vars.remove(ItemVariables.VISUAL_APPEARANCE_STONE_ID);
		vars.remove(ItemVariables.VISUAL_APPEARANCE_LIFE_TIME);
		vars.storeMe();

		final Player player = getActingPlayer();
		if (player != null) {
			final InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(this);
			player.broadcastUserInfo(UserInfoType.APPAREANCE);
			player.sendInventoryUpdate(iu);

			if (isEnchanted()) {
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_S2_HAS_BEEN_RESTORED_TO_ITS_PREVIOUS_APPEARANCE_AS_ITS_TEMPORARY_MODIFICATION_HAS_EXPIRED).addInt(getEnchantLevel()).addItemName(this));
			} else {
				player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_RESTORED_TO_ITS_PREVIOUS_APPEARANCE_AS_ITS_TEMPORARY_MODIFICATION_HAS_EXPIRED).addItemName(this));
			}
		}
	}
}
