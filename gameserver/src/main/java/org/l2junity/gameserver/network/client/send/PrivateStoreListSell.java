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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public class PrivateStoreListSell extends AbstractItemPacket {
	private final Player _player;
	private final Player _seller;

	public PrivateStoreListSell(Player player, Player seller) {
		_player = player;
		_seller = seller;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.PRIVATE_STORE_LIST.writeId(packet);

		packet.writeD(_seller.getObjectId());
		packet.writeD(_seller.getSellList().isPackaged() ? 1 : 0);
		packet.writeQ(_player.getAdena());
		packet.writeD(0x00);
		packet.writeD(_seller.getSellList().getItems().length);
		for (TradeItem item : _seller.getSellList().getItems()) {
			writeItem(packet, item);
			packet.writeQ(item.getPrice());
			packet.writeQ(item.getItem().getReferencePrice() * 2);
		}
		return true;
	}
}
