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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.enums.MatchingRoomType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.matching.MatchingRoom;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameClientPacket;


/**
 * @author Sdw
 */
public class RequestExOustFromMpccRoom extends GameClientPacket {
	private int _objectId;

	@Override
	public void readImpl() {
		_objectId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();

		if (activeChar == null) {
			return;
		}

		final MatchingRoom room = activeChar.getMatchingRoom();

		if ((room != null) && (room.getLeader() == activeChar) && (room.getRoomType() == MatchingRoomType.COMMAND_CHANNEL)) {
			Player player = WorldManager.getInstance().getPlayer(_objectId);

			if (player != null) {
				room.deleteMember(player, true);
			}
		}
	}
}
