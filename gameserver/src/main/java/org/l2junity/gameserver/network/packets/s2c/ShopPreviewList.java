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
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.model.buylist.Product;
import org.l2junity.gameserver.model.buylist.ProductList;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.type.CrystalType;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

public class ShopPreviewList extends GameServerPacket {
	private final int _listId;
	private final Collection<Product> _list;
	private final long _money;
	private CrystalType _expertise;

	public ShopPreviewList(ProductList list, long currentMoney, CrystalType expertise) {
		_listId = list.getListId();
		_list = list.getProducts();
		_money = currentMoney;
		_expertise = expertise;
	}

	public ShopPreviewList(Collection<Product> lst, int listId, long currentMoney) {
		_listId = listId;
		_list = lst;
		_money = currentMoney;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SHOP_PREVIEW_LIST.writeId(body);

		body.writeD(5056);
		body.writeQ(_money); // current money
		body.writeD(_listId);

		int newlength = 0;
		for (Product product : _list) {
			if (!product.getItem().getCrystalType().isGreater(_expertise) && product.getItem().isEquipable()) {
				newlength++;
			}
		}
		body.writeH(newlength);

		for (Product product : _list) {
			if (!product.getItem().getCrystalType().isGreater(_expertise) && product.getItem().isEquipable()) {
				body.writeD(product.getItemId());
				body.writeH(product.getItem().getType2()); // item type2

				if (product.getItem().getType1() != ItemTemplate.TYPE1_ITEM_QUESTITEM_ADENA) {
					body.writeQ(product.getItem().getBodyPart()); // rev 415 slot 0006-lr.ear 0008-neck 0030-lr.finger 0040-head 0080-?? 0100-l.hand 0200-gloves 0400-chest 0800-pants 1000-feet 2000-?? 4000-r.hand 8000-r.hand
				} else {
					body.writeQ(0x00); // rev 415 slot 0006-lr.ear 0008-neck 0030-lr.finger 0040-head 0080-?? 0100-l.hand 0200-gloves 0400-chest 0800-pants 1000-feet 2000-?? 4000-r.hand 8000-r.hand
				}

				body.writeQ(GeneralConfig.WEAR_PRICE);
			}
		}
	}
}