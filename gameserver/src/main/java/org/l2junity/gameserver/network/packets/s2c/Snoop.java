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
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class Snoop extends GameServerPacket {
	private final int _convoId;
	private final String _name;
	private final ChatType _type;
	private final String _speaker;
	private final String _msg;

	public Snoop(int id, String name, ChatType type, String speaker, String msg) {
		_convoId = id;
		_name = name;
		_type = type;
		_speaker = speaker;
		_msg = msg;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SNOOP.writeId(body);

		body.writeD(_convoId);
		body.writeS(_name);
		body.writeD(0x00); // ??
		body.writeD(_type.getClientId());
		body.writeS(_speaker);
		body.writeS(_msg);
	}
}