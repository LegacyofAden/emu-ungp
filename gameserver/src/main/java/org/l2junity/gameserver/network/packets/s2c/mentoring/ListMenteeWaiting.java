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
package org.l2junity.gameserver.network.packets.s2c.mentoring;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author UnAfraid
 */
public class ListMenteeWaiting extends GameServerPacket {
	private final int PLAYERS_PER_PAGE = 64;
	private final List<Player> _possibleCandiates = new ArrayList<>();
	private final int _page;

	public ListMenteeWaiting(int page, int minLevel, int maxLevel) {
		_page = page;
		for (Player player : WorldManager.getInstance().getAllPlayers()) {
			if ((player.getLevel() >= minLevel) && (player.getLevel() <= maxLevel) && !player.isMentee() && !player.isMentor() && !player.isAwakenedClass()) {
				_possibleCandiates.add(player);
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.LIST_MENTEE_WAITING.writeId(body);

		body.writeD(0x01); // always 1 in retail
		if (_possibleCandiates.isEmpty()) {
			body.writeD(0x00);
			body.writeD(0x00);
			return;
		}

		body.writeD(_possibleCandiates.size());
		body.writeD(_possibleCandiates.size() % PLAYERS_PER_PAGE);

		for (Player player : _possibleCandiates) {
			if ((1 <= (PLAYERS_PER_PAGE * _page)) && (1 > (PLAYERS_PER_PAGE * (_page - 1)))) {
				body.writeS(player.getName());
				body.writeD(player.getActiveClass());
				body.writeD(player.getLevel());
			}
		}
	}
}
