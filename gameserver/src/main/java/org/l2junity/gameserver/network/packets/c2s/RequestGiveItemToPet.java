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
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.actor.instance.PetInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


@Slf4j
public final class RequestGiveItemToPet extends GameClientPacket {
	private int _objectId;
	private long _amount;

	@Override
	public void readImpl() {
		_objectId = readD();
		_amount = readQ();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if ((_amount <= 0) || (player == null) || !player.hasPet()) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("giveitemtopet")) {
			player.sendMessage("You are giving items to pet too fast.");
			return;
		}

		if (player.hasItemRequest()) {
			return;
		}

		// Alt game - Karma punishment
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_TRADE && (player.getReputation() < 0)) {
			return;
		}

		if (player.getPrivateStoreType() != PrivateStoreType.NONE) {
			player.sendMessage("You cannot exchange items while trading.");
			return;
		}

		final ItemInstance item = player.getInventory().getItemByObjectId(_objectId);
		if (item == null) {
			return;
		}

		if (_amount > item.getCount()) {
			Util.handleIllegalPlayerAction(player, getClass().getSimpleName() + ": Character " + player.getName() + " of account " + player.getAccountName() + " tried to get item with oid " + _objectId + " from pet but has invalid count " + _amount + " item count: " + item.getCount(), GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (item.isAugmented()) {
			return;
		}

		if (item.isHeroItem() || !item.isDropable() || !item.isDestroyable() || !item.isTradeable()) {
			player.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_THIS_ITEM);
			return;
		}

		final PetInstance pet = player.getPet();
		if (pet.isDead()) {
			player.sendPacket(SystemMessageId.YOUR_PET_IS_DEAD_AND_ANY_ATTEMPT_YOU_MAKE_TO_GIVE_IT_SOMETHING_GOES_UNRECOGNIZED);
			return;
		}

		if (!pet.getInventory().validateCapacity(item)) {
			player.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS);
			return;
		}

		if (!pet.getInventory().validateWeight(item, _amount)) {
			player.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS2);
			return;
		}

		if (player.transferItem("Transfer", _objectId, _amount, pet.getInventory(), pet) == null) {
			log.warn("Invalid item transfer request: " + pet.getName() + "(pet) --> " + player.getName());
		}
	}
}
