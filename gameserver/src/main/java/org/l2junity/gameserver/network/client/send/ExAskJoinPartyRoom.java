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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * @author KenM
 */
public class ExAskJoinPartyRoom implements IClientOutgoingPacket {
	private final String _charName;
	private final String _roomName;

	public ExAskJoinPartyRoom(Player player) {
		_charName = player.getName();
		_roomName = player.getMatchingRoom().getTitle();
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_ASK_JOIN_PARTY_ROOM.writeId(packet);

		packet.writeS(_charName);
		packet.writeS(_roomName);
		return true;
	}
}
