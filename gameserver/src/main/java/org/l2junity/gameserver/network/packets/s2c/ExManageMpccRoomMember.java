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
import org.l2junity.gameserver.enums.ExManagePartyRoomMemberType;
import org.l2junity.gameserver.enums.MatchingMemberType;
import org.l2junity.gameserver.instancemanager.MapRegionManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.matching.CommandChannelMatchingRoom;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Gnacik
 */
public class ExManageMpccRoomMember extends GameServerPacket {
	private final Player _activeChar;
	private final MatchingMemberType _memberType;
	private final ExManagePartyRoomMemberType _type;

	public ExManageMpccRoomMember(Player player, CommandChannelMatchingRoom room, ExManagePartyRoomMemberType mode) {
		_activeChar = player;
		_memberType = room.getMemberType(player);
		_type = mode;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_MANAGE_PARTY_ROOM_MEMBER.writeId(body);

		body.writeD(_type.ordinal());
		body.writeD(_activeChar.getObjectId());
		body.writeS(_activeChar.getName());
		body.writeD(_activeChar.getClassId().getId());
		body.writeD(_activeChar.getLevel());
		body.writeD(MapRegionManager.getInstance().getBBs(_activeChar.getLocation()));
		body.writeD(_memberType.ordinal());
	}
}