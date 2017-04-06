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

import java.util.Collection;

/**
 * @author UnAfraid
 */
public class ExBRProductList extends GameServerPacket {
	private final Player _activeChar;
	private final int _type;
	private final Collection<PrimeShopGroup> _primeList;

	public ExBRProductList(Player activeChar, int type, Collection<PrimeShopGroup> items) {
		_activeChar = activeChar;
		_type = type;
		_primeList = items;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BR_PRODUCT_LIST.writeId(body);

		body.writeQ(_activeChar.getAdena()); // Adena
		body.writeQ(0x00); // Hero coins
		body.writeC(_type); // Type 0 - Home, 1 - History, 2 - Favorites
		body.writeD(_primeList.size());
		for (PrimeShopGroup brItem : _primeList) {
			body.writeD(brItem.getBrId());
			body.writeC(brItem.getCat());
			body.writeC(brItem.getPaymentType()); // Payment Type: 0 - Prime Points, 1 - Adena, 2 - Hero Coins
			body.writeD(brItem.getPrice());
			body.writeC(brItem.getPanelType()); // Item Panel Type: 0 - None, 1 - Event, 2 - Sale, 3 - New, 4 - Best
			body.writeD(brItem.getRecommended()); // Recommended: (bit flags) 1 - Top, 2 - Left, 4 - Right
			body.writeD(brItem.getStartSale());
			body.writeD(brItem.getEndSale());
			body.writeC(brItem.getDaysOfWeek());
			body.writeC(brItem.getStartHour());
			body.writeC(brItem.getStartMinute());
			body.writeC(brItem.getStopHour());
			body.writeC(brItem.getStopMinute());
			body.writeD(brItem.getStock());
			body.writeD(brItem.getTotal());
			body.writeC(brItem.getSalePercent());
			body.writeC(brItem.getMinLevel());
			body.writeC(brItem.getMaxLevel());
			body.writeD(brItem.getMinBirthday());
			body.writeD(brItem.getMaxBirthday());
			body.writeD(brItem.getRestrictionDay());
			body.writeD(brItem.getAvailableCount());
			body.writeC(brItem.getItems().size());
			for (PrimeShopItem item : brItem.getItems()) {
				body.writeD(item.getId());
				body.writeD((int) item.getCount());
				body.writeD(item.getWeight());
				body.writeD(item.isTradable());
			}
		}
	}
}