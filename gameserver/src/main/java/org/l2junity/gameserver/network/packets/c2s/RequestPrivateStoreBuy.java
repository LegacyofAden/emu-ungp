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

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.ItemRequest;
import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

@Slf4j
public final class RequestPrivateStoreBuy extends GameClientPacket {
	private static final int BATCH_LENGTH = 20; // length of the one item

	private int _storePlayerId;
	private Set<ItemRequest> _items = null;

	@Override
	public void readImpl() {
		_storePlayerId = readD();
		int count = readD();
		if ((count <= 0) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}
		_items = new HashSet<>();

		for (int i = 0; i < count; i++) {
			int objectId = readD();
			long cnt = readQ();
			long price = readQ();

			if ((objectId < 1) || (cnt < 1) || (price < 0)) {
				_items = null;
				return;
			}

			_items.add(new ItemRequest(objectId, cnt, price));
		}
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (_items == null) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("privatestorebuy")) {
			player.sendMessage("You are buying items too fast.");
			return;
		}

		WorldObject object = player.getWorld().getPlayer(_storePlayerId);
		if ((object == null) || player.isCursedWeaponEquipped()) {
			return;
		}

		Player storePlayer = (Player) object;
		if (!player.isInRadius3d(storePlayer, INTERACTION_DISTANCE)) {
			return;
		}

		if (player.getInstanceWorld() != storePlayer.getInstanceWorld()) {
			return;
		}

		if (!((storePlayer.getPrivateStoreType() == PrivateStoreType.SELL) || (storePlayer.getPrivateStoreType() == PrivateStoreType.PACKAGE_SELL))) {
			return;
		}

		TradeList storeList = storePlayer.getSellList();
		if (storeList == null) {
			return;
		}

		if (!player.getAccessLevel().allowTransaction()) {
			player.sendMessage("Transactions are disabled for your Access Level.");
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (storePlayer.getPrivateStoreType() == PrivateStoreType.PACKAGE_SELL) {
			if (storeList.getItemCount() > _items.size()) {
				String msgErr = "[RequestPrivateStoreBuy] player " + getClient().getActiveChar().getName() + " tried to buy less items than sold by package-sell, ban this player for bot usage!";
				Util.handleIllegalPlayerAction(getClient().getActiveChar(), msgErr, GeneralConfig.DEFAULT_PUNISH);
				return;
			}
		}

		int result = storeList.privateStoreBuy(player, _items);
		if (result > 0) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			if (result > 1) {
				log.warn("PrivateStore buy has failed due to invalid list or request. Player: " + player.getName() + ", Private store of: " + storePlayer.getName());
			}
			return;
		}

		if (storeList.getItemCount() == 0) {
			storePlayer.setPrivateStoreType(PrivateStoreType.NONE);
			storePlayer.broadcastUserInfo();
		}
	}
}
