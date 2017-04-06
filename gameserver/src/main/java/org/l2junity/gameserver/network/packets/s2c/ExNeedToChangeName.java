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

/**
 * Dialog with input field<br>
 * type 0 = char name (Selection screen)<br>
 * type 1 = clan name
 *
 * @author JIV
 */
public class ExNeedToChangeName extends GameServerPacket {
	private final int _type, _subType;
	private final String _name;

	public ExNeedToChangeName(int type, int subType, String name) {
		super();
		_type = type;
		_subType = subType;
		_name = name;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_NEED_TO_CHANGE_NAME.writeId(body);

		body.writeD(_type);
		body.writeD(_subType);
		body.writeS(_name);
	}
}