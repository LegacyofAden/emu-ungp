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

public class RecipeShopItemInfo extends GameServerPacket {
	private final Player _manufacturer;
	private final int _recipeId;
	private final Boolean _success;
	private final long _manufacturePrice;
	private final long _offeringMaximumAdena;

	public RecipeShopItemInfo(Player manufacturer, int recipeId, boolean success, long manufacturePrice, long offeringMaximumAdena) {
		_manufacturer = manufacturer;
		_recipeId = recipeId;
		_success = success;
		_manufacturePrice = manufacturePrice;
		_offeringMaximumAdena = offeringMaximumAdena;
	}

	public RecipeShopItemInfo(Player manufacturer, int recipeId, long manufacturePrice, long offeringMaximumAdena) {
		_manufacturer = manufacturer;
		_recipeId = recipeId;
		_success = null;
		_manufacturePrice = manufacturePrice;
		_offeringMaximumAdena = offeringMaximumAdena;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.RECIPE_SHOP_ITEM_INFO.writeId(body);

		body.writeD(_manufacturer.getObjectId());
		body.writeD(_recipeId);
		body.writeD((int) _manufacturer.getCurrentMp());
		body.writeD(_manufacturer.getMaxMp());
		body.writeD(_success == null ? -1 : (_success ? 1 : 0)); // item creation none/success/failed
		body.writeQ(_manufacturePrice);
		body.writeC(_offeringMaximumAdena > 0 ? 1 : 0); // Trigger offering window if 1
		body.writeQ(_offeringMaximumAdena);
	}
}
