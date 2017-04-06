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
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Map;

/**
 * * @author Gnacik
 */
public class ShopPreviewInfo extends GameServerPacket {
	private final Map<Integer, Integer> _itemlist;

	public ShopPreviewInfo(Map<Integer, Integer> itemlist) {
		_itemlist = itemlist;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SHOP_PREVIEW_INFO.writeId(body);

		body.writeD(Inventory.PAPERDOLL_TOTALSLOTS);
		// Slots
		body.writeD(getFromList(Inventory.PAPERDOLL_UNDER));
		body.writeD(getFromList(Inventory.PAPERDOLL_REAR));
		body.writeD(getFromList(Inventory.PAPERDOLL_LEAR));
		body.writeD(getFromList(Inventory.PAPERDOLL_NECK));
		body.writeD(getFromList(Inventory.PAPERDOLL_RFINGER));
		body.writeD(getFromList(Inventory.PAPERDOLL_LFINGER));
		body.writeD(getFromList(Inventory.PAPERDOLL_HEAD));
		body.writeD(getFromList(Inventory.PAPERDOLL_RHAND));
		body.writeD(getFromList(Inventory.PAPERDOLL_LHAND));
		body.writeD(getFromList(Inventory.PAPERDOLL_GLOVES));
		body.writeD(getFromList(Inventory.PAPERDOLL_CHEST));
		body.writeD(getFromList(Inventory.PAPERDOLL_LEGS));
		body.writeD(getFromList(Inventory.PAPERDOLL_FEET));
		body.writeD(getFromList(Inventory.PAPERDOLL_CLOAK));
		body.writeD(getFromList(Inventory.PAPERDOLL_RHAND));
		body.writeD(getFromList(Inventory.PAPERDOLL_HAIR));
		body.writeD(getFromList(Inventory.PAPERDOLL_HAIR2));
		body.writeD(getFromList(Inventory.PAPERDOLL_RBRACELET));
		body.writeD(getFromList(Inventory.PAPERDOLL_LBRACELET));
	}

	private int getFromList(int key) {
		return (_itemlist.getOrDefault(key, 0));
	}
}