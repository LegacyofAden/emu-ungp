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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.TradeItem;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author daemon
 */
public class TradeUpdate extends AbstractItemPacket {
	private final TradeItem _item;

	public TradeUpdate(Player player, TradeItem item) {
		_item = new TradeItem(item, player.getInventory().getItemByObjectId(item.getObjectId()).getCount() - item.getCount(), item.getPrice());
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.TRADE_UPDATE.writeId(body);

		body.writeH(1);
		body.writeH((_item.getCount() > 0) && _item.getItem().isStackable() ? 3 : 2);
		writeItem(body, _item);
	}
}
