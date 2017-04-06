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

public class AskJoinAlly extends GameServerPacket {
	private final String _requestorName;
	private final int _requestorObjId;
	private final String _requestorAllyName;

	/**
	 * @param requestorObjId
	 * @param requestorAllyName
	 * @param requestorName
	 */
	public AskJoinAlly(int requestorObjId, String requestorAllyName, String requestorName) {
		_requestorName = requestorName;
		_requestorObjId = requestorObjId;
		_requestorAllyName = requestorAllyName;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ASK_JOIN_ALLIANCE.writeId(body);

		body.writeD(_requestorObjId);
		body.writeS(_requestorAllyName);
		body.writeS(null); // TODO: Find me!
		body.writeS(_requestorName);
	}
}