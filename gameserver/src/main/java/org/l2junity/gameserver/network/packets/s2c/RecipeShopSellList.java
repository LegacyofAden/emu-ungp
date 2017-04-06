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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Map.Entry;

public class RecipeShopSellList extends GameServerPacket {
	private final Player _buyer, _manufacturer;

	public RecipeShopSellList(Player buyer, Player manufacturer) {
		_buyer = buyer;
		_manufacturer = manufacturer;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.RECIPE_SHOP_SELL_LIST.writeId(body);

		body.writeD(_manufacturer.getObjectId());
		body.writeD((int) _manufacturer.getCurrentMp());// Creator's MP
		body.writeD(_manufacturer.getMaxMp());// Creator's MP
		body.writeQ(_buyer.getAdena());// Buyer Adena
		if (!_manufacturer.hasManufactureShop()) {
			body.writeD(0x00);
		} else {
			body.writeD(_manufacturer.getManufactureItems().size());
			for (Entry<Integer, Long> item : _manufacturer.getManufactureItems().entrySet()) {
				body.writeD(item.getKey());
				body.writeD(0x00); // CanCreate?
				body.writeQ(item.getValue());
			}
		}
	}
}
