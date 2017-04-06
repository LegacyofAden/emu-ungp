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
import org.l2junity.gameserver.data.xml.impl.BeautyShopData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.beautyshop.BeautyItem;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Map;

/**
 * @author Sdw
 */
public class ExResponseBeautyList extends GameServerPacket {
	private final Player _activeChar;
	private final int _type;
	private final Map<Integer, BeautyItem> _beautyItem;

	public final static int SHOW_FACESHAPE = 1;
	public final static int SHOW_HAIRSTYLE = 0;

	public ExResponseBeautyList(Player activeChar, int type) {
		_activeChar = activeChar;
		_type = type;
		if (type == SHOW_HAIRSTYLE) {
			_beautyItem = BeautyShopData.getInstance().getBeautyData(activeChar.getRace(), activeChar.getAppearance().getSexType()).getHairList();
		} else {
			_beautyItem = BeautyShopData.getInstance().getBeautyData(activeChar.getRace(), activeChar.getAppearance().getSexType()).getFaceList();
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_RESPONSE_BEAUTY_LIST.writeId(body);

		body.writeQ(_activeChar.getAdena());
		body.writeQ(_activeChar.getBeautyTickets());
		body.writeD(_type);
		body.writeD(_beautyItem.size());
		for (BeautyItem item : _beautyItem.values()) {
			body.writeD(item.getId());
			body.writeD(1); // Limit
		}
		body.writeD(0);
	}
}