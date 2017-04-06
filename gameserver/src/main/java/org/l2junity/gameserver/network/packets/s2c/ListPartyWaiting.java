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
import org.l2junity.gameserver.enums.PartyMatchingRoomLevelType;
import org.l2junity.gameserver.instancemanager.MatchingRoomManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.matching.MatchingRoom;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Gnacik
 */
public class ListPartyWaiting extends GameServerPacket {
	private final List<MatchingRoom> _rooms = new LinkedList<>();
	private final int _size;

	private static final int NUM_PER_PAGE = 64;

	public ListPartyWaiting(PartyMatchingRoomLevelType type, int location, int page, int requestorLevel) {
		final List<MatchingRoom> rooms = MatchingRoomManager.getInstance().getPartyMathchingRooms(location, type, requestorLevel);

		_size = rooms.size();
		final int startIndex = (page - 1) * NUM_PER_PAGE;
		int chunkSize = _size - startIndex;
		if (chunkSize > NUM_PER_PAGE) {
			chunkSize = NUM_PER_PAGE;
		}
		for (int i = startIndex; i < (startIndex + chunkSize); i++) {
			_rooms.add(rooms.get(i));
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.LIST_PARTY_WATING.writeId(body);

		body.writeD(_size);
		body.writeD(_rooms.size());
		for (MatchingRoom room : _rooms) {
			body.writeD(room.getId());
			body.writeS(room.getTitle());
			body.writeD(room.getLocation());
			body.writeD(room.getMinLvl());
			body.writeD(room.getMaxLvl());
			body.writeD(room.getMaxMembers());
			body.writeS(room.getLeader().getName());
			body.writeD(room.getMembersCount());
			for (Player member : room.getMembers()) {
				body.writeD(member.getClassId().getId());
				body.writeS(member.getName());
			}
		}
		body.writeD(WorldManager.getInstance().getPartyCount());
		body.writeD(WorldManager.getInstance().getPartyMemberCount());
	}
}