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

import org.l2junity.gameserver.enums.MatchingMemberType;
import org.l2junity.gameserver.instancemanager.MapRegionManager;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.matching.CommandChannelMatchingRoom;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * @author Sdw
 */
public class ExMPCCRoomMember implements IClientOutgoingPacket {
	private final CommandChannelMatchingRoom _room;
	private final MatchingMemberType _type;

	public ExMPCCRoomMember(PlayerInstance player, CommandChannelMatchingRoom room) {
		_room = room;
		_type = room.getMemberType(player);
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_MPCC_ROOM_MEMBER.writeId(packet);

		packet.writeD(_type.ordinal());
		packet.writeD(_room.getMembersCount());
		for (PlayerInstance member : _room.getMembers()) {
			packet.writeD(member.getObjectId());
			packet.writeS(member.getName());
			packet.writeD(member.getLevel());
			packet.writeD(member.getClassId().getId());
			packet.writeD(MapRegionManager.getInstance().getBBs(member.getLocation()));
			packet.writeD(_room.getMemberType(member).ordinal());
		}
		return true;
	}
}
