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
package org.l2junity.gameserver.network.client.send.mentoring;

import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author UnAfraid
 */
public class ListMenteeWaiting implements IClientOutgoingPacket {
	private final int PLAYERS_PER_PAGE = 64;
	private final List<Player> _possibleCandiates = new ArrayList<>();
	private final int _page;

	public ListMenteeWaiting(int page, int minLevel, int maxLevel) {
		_page = page;
		for (Player player : World.getInstance().getPlayers()) {
			if ((player.getLevel() >= minLevel) && (player.getLevel() <= maxLevel) && !player.isMentee() && !player.isMentor() && !player.isAwakenedClass()) {
				_possibleCandiates.add(player);
			}
		}
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.LIST_MENTEE_WAITING.writeId(packet);

		packet.writeD(0x01); // always 1 in retail
		if (_possibleCandiates.isEmpty()) {
			packet.writeD(0x00);
			packet.writeD(0x00);
			return true;
		}

		packet.writeD(_possibleCandiates.size());
		packet.writeD(_possibleCandiates.size() % PLAYERS_PER_PAGE);

		for (Player player : _possibleCandiates) {
			if ((1 <= (PLAYERS_PER_PAGE * _page)) && (1 > (PLAYERS_PER_PAGE * (_page - 1)))) {
				packet.writeS(player.getName());
				packet.writeD(player.getActiveClass());
				packet.writeD(player.getLevel());
			}
		}
		return true;
	}
}
