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

/**
 * @author mrTJO
 */
public class ExCubeGameExtendedChangePoints extends GameServerPacket {
	int _timeLeft;
	int _bluePoints;
	int _redPoints;
	boolean _isRedTeam;
	Player _player;
	int _playerPoints;

	/**
	 * Update a Secret Point Counter (used by client when receive ExCubeGameEnd)
	 *
	 * @param timeLeft     Time Left before Minigame's End
	 * @param bluePoints   Current Blue Team Points
	 * @param redPoints    Current Blue Team points
	 * @param isRedTeam    Is Player from Red Team?
	 * @param player       Player Instance
	 * @param playerPoints Current Player Points
	 */
	public ExCubeGameExtendedChangePoints(int timeLeft, int bluePoints, int redPoints, boolean isRedTeam, Player player, int playerPoints) {
		_timeLeft = timeLeft;
		_bluePoints = bluePoints;
		_redPoints = redPoints;
		_isRedTeam = isRedTeam;
		_player = player;
		_playerPoints = playerPoints;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BLOCK_UP_SET_STATE.writeId(body);

		body.writeD(0x00);

		body.writeD(_timeLeft);
		body.writeD(_bluePoints);
		body.writeD(_redPoints);

		body.writeD(_isRedTeam ? 0x01 : 0x00);
		body.writeD(_player.getObjectId());
		body.writeD(_playerPoints);
	}
}