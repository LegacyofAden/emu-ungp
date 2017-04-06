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
 * Send Private (Friend) Message
 *
 * @author Tempy
 */
public class L2FriendSay extends GameServerPacket {
	private final String _sender, _receiver, _message;

	public L2FriendSay(String sender, String reciever, String message) {
		_sender = sender;
		_receiver = reciever;
		_message = message;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.L2_FRIEND_SAY.writeId(body);

		body.writeD(0); // ??
		body.writeS(_receiver);
		body.writeS(_sender);
		body.writeS(_message);
	}
}