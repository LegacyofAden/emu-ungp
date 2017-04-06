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
import org.l2junity.gameserver.model.beautyshop.BeautyData;
import org.l2junity.gameserver.model.beautyshop.BeautyItem;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sdw
 */
public class ExBeautyItemList extends GameServerPacket {
	private int _colorCount;
	private final BeautyData _beautyData;
	private final Map<Integer, List<BeautyItem>> _colorData = new HashMap<>();
	private static final int HAIR_TYPE = 0;
	private static final int FACE_TYPE = 1;
	private static final int COLOR_TYPE = 2;

	public ExBeautyItemList(Player activeChar) {
		_beautyData = BeautyShopData.getInstance().getBeautyData(activeChar.getRace(), activeChar.getAppearance().getSexType());

		for (BeautyItem hair : _beautyData.getHairList().values()) {
			List<BeautyItem> colors = new ArrayList<>();
			for (BeautyItem color : hair.getColors().values()) {
				colors.add(color);
				_colorCount++;
			}
			_colorData.put(hair.getId(), colors);
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BEAUTY_ITEM_LIST.writeId(body);

		body.writeD(HAIR_TYPE);
		body.writeD(_beautyData.getHairList().size());
		for (BeautyItem hair : _beautyData.getHairList().values()) {
			body.writeD(0); // ?
			body.writeD(hair.getId());
			body.writeD(hair.getAdena());
			body.writeD(hair.getResetAdena());
			body.writeD(hair.getBeautyShopTicket());
			body.writeD(1); // Limit
		}

		body.writeD(FACE_TYPE);
		body.writeD(_beautyData.getFaceList().size());
		for (BeautyItem face : _beautyData.getFaceList().values()) {
			body.writeD(0); // ?
			body.writeD(face.getId());
			body.writeD(face.getAdena());
			body.writeD(face.getResetAdena());
			body.writeD(face.getBeautyShopTicket());
			body.writeD(1); // Limit
		}

		body.writeD(COLOR_TYPE);
		body.writeD(_colorCount);
		for (int hairId : _colorData.keySet()) {
			for (BeautyItem color : _colorData.get(hairId)) {
				body.writeD(hairId);
				body.writeD(color.getId());
				body.writeD(color.getAdena());
				body.writeD(color.getResetAdena());
				body.writeD(color.getBeautyShopTicket());
				body.writeD(1);
			}
		}
	}
}