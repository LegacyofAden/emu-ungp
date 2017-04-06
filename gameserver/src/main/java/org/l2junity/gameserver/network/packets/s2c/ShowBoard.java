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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

public class ShowBoard extends GameServerPacket {
	private final String _content;

	public ShowBoard(String htmlCode, String id) {
		_content = id + "\u0008" + htmlCode;
	}

	public ShowBoard(List<String> arg) {
		StringBuilder builder = new StringBuilder(256).append("1002\u0008");
		for (String str : arg) {
			builder.append(str).append("\u0008");
		}
		_content = builder.toString();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SHOW_BOARD.writeId(body);

		body.writeC(0x01); // c4 1 to show community 00 to hide
		body.writeS("bypass _bbshome"); // top
		body.writeS("bypass _bbsgetfav"); // favorite
		body.writeS("bypass _bbsloc"); // region
		body.writeS("bypass _bbsclan"); // clan
		body.writeS("bypass _bbsmemo"); // memo
		body.writeS("bypass _bbsmail"); // mail
		body.writeS("bypass _bbsfriends"); // friends
		body.writeS("bypass bbs_add_fav"); // add fav.
		body.writeS(_content);
	}
}
