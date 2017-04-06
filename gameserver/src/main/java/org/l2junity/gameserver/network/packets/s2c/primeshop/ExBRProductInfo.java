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
package org.l2junity.gameserver.network.packets.s2c.primeshop;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.primeshop.PrimeShopGroup;
import org.l2junity.gameserver.model.primeshop.PrimeShopItem;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Gnacik
 */
public class ExBRProductInfo extends GameServerPacket {
	private final PrimeShopGroup _item;
	private final int _charPoints;
	private final long _charAdena;

	public ExBRProductInfo(PrimeShopGroup item, Player player) {
		_item = item;
		_charPoints = player.getPrimePoints();
		_charAdena = player.getAdena();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BR_PRODUCT_INFO.writeId(body);

		body.writeD(_item.getBrId());
		body.writeD(_item.getPrice());
		body.writeD(_item.getItems().size());
		for (PrimeShopItem item : _item.getItems()) {
			body.writeD(item.getId());
			body.writeD((int) item.getCount());
			body.writeD(item.getWeight());
			body.writeD(item.isTradable());
		}
		body.writeQ(_charAdena);
		body.writeQ(_charPoints);
		body.writeQ(0x00); // Hero coins
	}
}
