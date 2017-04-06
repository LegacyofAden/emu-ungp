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
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class ExPutItemResultForVariationCancel extends GameServerPacket {
	private final int _itemObjId;
	private final int _itemId;
	private final int _itemAug1;
	private final int _itemAug2;
	private final long _price;

	public ExPutItemResultForVariationCancel(ItemInstance item, long price) {
		_itemObjId = item.getObjectId();
		_itemId = item.getDisplayId();
		_price = price;
		_itemAug1 = item.getAugmentation().getOption1Id();
		_itemAug2 = item.getAugmentation().getOption2Id();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PUT_ITEM_RESULT_FOR_VARIATION_CANCEL.writeId(body);

		body.writeD(_itemObjId);
		body.writeD(_itemId);
		body.writeD(_itemAug1);
		body.writeD(_itemAug2);
		body.writeQ(_price);
		body.writeD(0x01);
	}
}