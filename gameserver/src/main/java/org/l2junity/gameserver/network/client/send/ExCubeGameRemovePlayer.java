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
 * @author mrTJO
 */
public class ExCubeGameRemovePlayer implements IClientOutgoingPacket {
	Player _player;
	boolean _isRedTeam;

	/**
	 * Remove Player from Minigame Waiting List
	 *
	 * @param player    Player to Remove
	 * @param isRedTeam Is Player from Red Team?
	 */
	public ExCubeGameRemovePlayer(Player player, boolean isRedTeam) {
		_player = player;
		_isRedTeam = isRedTeam;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_BLOCK_UP_SET_LIST.writeId(packet);

		packet.writeD(0x02);

		packet.writeD(0xffffffff);

		packet.writeD(_isRedTeam ? 0x01 : 0x00);
		packet.writeD(_player.getObjectId());
		return true;
	}
}
