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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author mrTJO
 */
public class ExCubeGameTeamList extends GameServerPacket {
	// Players Lists
	private final List<Player> _bluePlayers;
	private final List<Player> _redPlayers;

	// Common Values
	private final int _roomNumber;

	/**
	 * Show Minigame Waiting List to Player
	 *
	 * @param redPlayers  Red Players List
	 * @param bluePlayers Blue Players List
	 * @param roomNumber  Arena/Room ID
	 */
	public ExCubeGameTeamList(List<Player> redPlayers, List<Player> bluePlayers, int roomNumber) {
		_redPlayers = redPlayers;
		_bluePlayers = bluePlayers;
		_roomNumber = roomNumber - 1;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BLOCK_UP_SET_LIST.writeId(body);

		body.writeD(0x00);

		body.writeD(_roomNumber);
		body.writeD(0xffffffff);

		body.writeD(_bluePlayers.size());
		for (Player player : _bluePlayers) {
			body.writeD(player.getObjectId());
			body.writeS(player.getName());
		}
		body.writeD(_redPlayers.size());
		for (Player player : _redPlayers) {
			body.writeD(player.getObjectId());
			body.writeS(player.getName());
		}
	}
}