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
package org.l2junity.gameserver.network.packets.c2s;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.ai.CtrlEvent;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.ai.NextAction;
import org.l2junity.gameserver.enums.ItemSkillType;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IItemHandler;
import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.instancemanager.FortSiegeManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerUseItem;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.model.holders.ItemSkillHolder;
import org.l2junity.gameserver.model.items.EtcItem;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.items.type.ActionType;
import org.l2junity.gameserver.model.stats.BooleanStat;
import org.l2junity.gameserver.model.world.ItemStorage;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExUseSharedGroupItem;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
public final class UseItem extends GameClientPacket {
	private int _objectId;
	private boolean _ctrlPressed;
	private int _itemId;

	@Override
	public void readImpl() {
		_objectId = readD();
		_ctrlPressed = readD() != 0;
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		// Flood protect UseItem
		if (!getClient().getFloodProtectors().getUseItem().tryPerformAction("use item")) {
			return;
		}

		if (activeChar.isSpawnProtected() && !PlayerConfig.SPAWN_PROTECTION_ALLOWED_ITEMS.contains(_itemId)) {
			activeChar.onActionRequest();
		}

		if (activeChar.getActiveTradeList() != null) {
			activeChar.cancelActiveTrade();
		}

		if (activeChar.getPrivateStoreType() != PrivateStoreType.NONE) {
			activeChar.sendPacket(SystemMessageId.WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM);
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final ItemInstance item = activeChar.getInventory().getItemByObjectId(_objectId);
		if (item == null) {
			// gm can use other player item
			if (activeChar.isGM()) {
				final ItemInstance obj = ItemStorage.getInstance().get(_objectId);
				if (obj != null) {
					AdminCommandHandler.getInstance().useAdminCommand(activeChar, "admin_use_item " + _objectId, true);
				}
			}
			return;
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerUseItem(activeChar, item, _ctrlPressed), activeChar, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (item.isQuestItem() && (item.getItem().getDefaultAction() != ActionType.NONE)) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_USE_QUEST_ITEMS);
			return;
		}

		// No UseItem is allowed while the player is in special conditions
		if ((activeChar.hasBlockActions() && !activeChar.getStat().isBlockedActionsAllowedItem(item)) || activeChar.isControlBlocked() || activeChar.isAlikeDead()) {
			return;
		}

		// Char cannot use item when dead
		if (activeChar.isDead() || !activeChar.getInventory().canManipulateWithItemId(item.getId())) {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_CANNOT_BE_USED_DUE_TO_UNSUITABLE_TERMS);
			sm.addItemName(item);
			activeChar.sendPacket(sm);
			return;
		}

		if (!item.isEquipped() && !item.getItem().checkCondition(activeChar, activeChar, true)) {
			return;
		}

		_itemId = item.getId();
		if (activeChar.isFishing() && ((_itemId < 6535) || (_itemId > 6540))) {
			// You cannot do anything else while fishing
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_FISHING3);
			return;
		}

		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT && (activeChar.getReputation() < 0)) {
			final List<ItemSkillHolder> skills = item.getItem().getSkills(ItemSkillType.NORMAL);
			if ((skills != null) && skills.stream().anyMatch(holder -> holder.getSkill().hasEffectType(L2EffectType.TELEPORT))) {
				return;
			}
		}

		// If the item has reuse time and it has not passed.
		// Message from reuse delay must come from item.
		final int reuseDelay = item.getReuseDelay();
		final int sharedReuseGroup = item.getSharedReuseGroup();
		if (reuseDelay > 0) {
			final long reuse = activeChar.getItemRemainingReuseTime(item.getObjectId());
			if (reuse > 0) {
				reuseData(activeChar, item, reuse);
				sendSharedGroupUpdate(activeChar, sharedReuseGroup, reuse, reuseDelay);
				return;
			}

			final long reuseOnGroup = activeChar.getReuseDelayOnGroup(sharedReuseGroup);
			if (reuseOnGroup > 0) {
				reuseData(activeChar, item, reuseOnGroup);
				sendSharedGroupUpdate(activeChar, sharedReuseGroup, reuseOnGroup, reuseDelay);
				return;
			}
		}

		// If item's default action is to show html, show item's main html.
		if (item.getItem().getDefaultAction() == ActionType.SHOW_HTML) {
			item.onBypassFeedback(activeChar, null);
			return;
		}

		if (item.isEquipable()) {
			// Don't allow to put formal wear while a cursed weapon is equipped.
			if (activeChar.isCursedWeaponEquipped() && (_itemId == 6408)) {
				return;
			}

			// Equip or unEquip
			if (FortSiegeManager.getInstance().isCombat(_itemId)) {
				return; // no message
			}

			if (activeChar.isCombatFlagEquipped()) {
				return;
			}

			if (activeChar.getInventory().isItemSlotBlocked(item.getItem().getBodyPart())) {
				activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
				return;
			}

			switch (item.getItem().getBodyPart()) {
				case ItemTemplate.SLOT_LR_HAND:
				case ItemTemplate.SLOT_L_HAND:
				case ItemTemplate.SLOT_R_HAND: {
					// Prevent players to equip weapon while wearing combat flag
					if ((activeChar.getActiveWeaponItem() != null) && (activeChar.getActiveWeaponItem().getId() == 9819)) {
						activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
						return;
					}

					if (activeChar.isMounted() || activeChar.getStat().has(BooleanStat.DISARMED)) {
						activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
						return;
					}

					// Don't allow weapon/shield equipment if a cursed weapon is equipped.
					if (activeChar.isCursedWeaponEquipped()) {
						return;
					}
					break;
				}
				case ItemTemplate.SLOT_DECO: {
					if (!item.isEquipped() && (activeChar.getInventory().getTalismanSlots() == 0)) {
						activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_MEET_THE_REQUIRED_CONDITION_TO_EQUIP_THAT_ITEM);
						return;
					}
					break;
				}
				case ItemTemplate.SLOT_BROOCH_JEWEL: {
					if (!item.isEquipped() && (activeChar.getInventory().getBroochJewelSlots() == 0)) {
						final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_CANNOT_EQUIP_S1_WITHOUT_EQUIPPING_A_BROOCH);
						sm.addItemName(item);
						activeChar.sendPacket(sm);
						return;
					}
					break;
				}
			}

			if (activeChar.isCastingNow()) {
				// Create and Bind the next action to the AI
				activeChar.getAI().setNextAction(new NextAction(CtrlEvent.EVT_FINISH_CASTING, CtrlIntention.AI_INTENTION_CAST, () -> activeChar.useEquippableItem(item, true)));
			} else if (activeChar.isAttackingNow()) {
				ThreadPool.getInstance().scheduleGeneral(() ->
				{
					// If character is still engaged in strike we should not change weapon
					if (activeChar.isAttackingNow()) {
						return;
					}

					// Equip or unEquip
					activeChar.useEquippableItem(item, false);
				}, activeChar.getAttackEndTime() - TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()), TimeUnit.MILLISECONDS);
			} else {
				activeChar.useEquippableItem(item, true);
			}
		} else {
			final EtcItem etcItem = item.getEtcItem();
			// TODO: Well aware this is crap, we need to be able to register handler based on their etcItemType to trigger such crap
			// Made as PoC for speed
			if (etcItem != null) {
				switch (etcItem.getItemType()) {
					case ENCHT_WP:
					case ENCHT_AM:
					case BLESS_ENCHT_WP:
					case BLESS_ENCHT_AM:
					case MULTI_ENCHT_WP:
					case MULTI_ENCHT_AM: {
						final IItemHandler handler = ItemHandler.getInstance().getHandler("EnchantScrolls");
						if (handler.useItem(activeChar, item, _ctrlPressed)) {
							if (reuseDelay > 0) {
								activeChar.addTimeStampItem(item, reuseDelay);
								sendSharedGroupUpdate(activeChar, sharedReuseGroup, reuseDelay, reuseDelay);
							}
						}
						return;
					}
				}
			}
			final IItemHandler handler = ItemHandler.getInstance().getHandler(etcItem);
			if (handler == null) {
				if ((etcItem != null) && (etcItem.getHandlerName() != null)) {
					log.warn("Unmanaged Item handler: " + etcItem.getHandlerName() + " for Item Id: " + _itemId + "!");
				}
			} else if (handler.useItem(activeChar, item, _ctrlPressed)) {
				// Item reuse time should be added if the item is successfully used.
				// Skill reuse delay is done at handlers.itemhandlers.ItemSkillsTemplate;
				if (reuseDelay > 0) {
					activeChar.addTimeStampItem(item, reuseDelay);
					sendSharedGroupUpdate(activeChar, sharedReuseGroup, reuseDelay, reuseDelay);
				}
			}
		}
	}

	private void reuseData(Player activeChar, ItemInstance item, long remainingTime) {
		final int hours = (int) (remainingTime / 3600000L);
		final int minutes = (int) (remainingTime % 3600000L) / 60000;
		final int seconds = (int) ((remainingTime / 1000) % 60);
		final SystemMessage sm;
		if (hours > 0) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_HOUR_S_S3_MINUTE_S_AND_S4_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
			sm.addItemName(item);
			sm.addInt(hours);
			sm.addInt(minutes);
		} else if (minutes > 0) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_MINUTE_S_S3_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
			sm.addItemName(item);
			sm.addInt(minutes);
		} else {
			sm = SystemMessage.getSystemMessage(SystemMessageId.THERE_ARE_S2_SECOND_S_REMAINING_IN_S1_S_RE_USE_TIME);
			sm.addItemName(item);
		}
		sm.addInt(seconds);
		activeChar.sendPacket(sm);
	}

	private void sendSharedGroupUpdate(Player activeChar, int group, long remaining, int reuse) {
		if (group > 0) {
			activeChar.sendPacket(new ExUseSharedGroupItem(_itemId, group, remaining, reuse));
		}
	}
}
