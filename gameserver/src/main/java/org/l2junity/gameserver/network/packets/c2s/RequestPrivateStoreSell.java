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

import static org.l2junity.gameserver.model.actor.Npc.INTERACTION_DISTANCE;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.ItemRequest;
import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

@Slf4j
public final class RequestPrivateStoreSell extends GameClientPacket {
	private int _storePlayerId;
	private ItemRequest[] _items = null;

	@Override
	public void readImpl() {
		_storePlayerId = readD();
		int itemsCount = readD();
		if ((itemsCount <= 0) || (itemsCount > PlayerConfig.MAX_ITEM_IN_PACKET)) {
			return;
		}
		_items = new ItemRequest[itemsCount];

		for (int i = 0; i < itemsCount; i++) {
			final int slot = readD();
			final int itemId = readD();
			readH(); // TODO analyse this
			readH(); // TODO analyse this
			final long count = readQ();
			final long price = readQ();
			readD(); // visual id
			readD(); // option 1
			readD(); // option 2
			int soulCrystals = readC();
			for (int s = 0; s < soulCrystals; s++) {
				readD(); // soul crystal option
			}
			int soulCrystals2 = readC();
			for (int s = 0; s < soulCrystals2; s++) {
				readD(); // soul crystal option
			}
			if ((slot < 1) || (itemId < 1) || (count < 1) || (price < 0)) {
				_items = null;
				return;
			}
			_items[i] = new ItemRequest(slot, itemId, count, price);
		}

	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (_items == null) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Cannot set private store in Ceremony of Chaos event.
		if (player.isOnEvent(CeremonyOfChaosEvent.class)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_OR_WORKSHOP_IN_THE_CEREMONY_OF_CHAOS);
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("privatestoresell")) {
			player.sendMessage("You are selling items too fast.");
			return;
		}

		final Player storePlayer = player.getWorld().getPlayer(_storePlayerId);
		if ((storePlayer == null) || !player.isInRadius3d(storePlayer, INTERACTION_DISTANCE)) {
			return;
		}

		if (player.getInstanceWorld() != storePlayer.getInstanceWorld()) {
			return;
		}

		if ((storePlayer.getPrivateStoreType() != PrivateStoreType.BUY) || player.isCursedWeaponEquipped()) {
			return;
		}

		TradeList storeList = storePlayer.getBuyList();
		if (storeList == null) {
			return;
		}

		if (!player.getAccessLevel().allowTransaction()) {
			player.sendMessage("Transactions are disabled for your Access Level.");
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (!storeList.privateStoreSell(player, _items)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			log.warn("PrivateStore sell has failed due to invalid list or request. Player: " + player.getName() + ", Private store of: " + storePlayer.getName());
			return;
		}

		if (storeList.getItemCount() == 0) {
			storePlayer.setPrivateStoreType(PrivateStoreType.NONE);
			storePlayer.broadcastUserInfo();
		}
	}
}
