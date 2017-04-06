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
import org.l2junity.gameserver.instancemanager.InstanceManager;
import org.l2junity.gameserver.instancemanager.MatchingRoomManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @author Gnacik
 */
public class ExListPartyMatchingWaitingRoom extends GameServerPacket {
	private static final int NUM_PER_PAGE = 64;
	private final int _size;
	private final List<Player> _players = new LinkedList<>();

	public ExListPartyMatchingWaitingRoom(Player player, int page, int minLevel, int maxLevel, List<ClassId> classIds, String query) {
		final List<Player> players = MatchingRoomManager.getInstance().getPlayerInWaitingList(minLevel, maxLevel, classIds, query);

		_size = players.size();
		final int startIndex = (page - 1) * NUM_PER_PAGE;
		int chunkSize = _size - startIndex;
		if (chunkSize > NUM_PER_PAGE) {
			chunkSize = NUM_PER_PAGE;
		}
		for (int i = startIndex; i < (startIndex + chunkSize); i++) {
			_players.add(players.get(i));
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_LIST_PARTY_MATCHING_WAITING_ROOM.writeId(body);

		body.writeD(_size);
		body.writeD(_players.size());
		for (Player player : _players) {
			body.writeS(player.getName());
			body.writeD(player.getClassId().getId());
			body.writeD(player.getLevel());
			final Instance instance = InstanceManager.getInstance().getPlayerInstance(player, false);
			body.writeD((instance != null) && (instance.getTemplateId() >= 0) ? instance.getTemplateId() : -1);
			final Map<Integer, Long> _instanceTimes = InstanceManager.getInstance().getAllInstanceTimes(player);
			body.writeD(_instanceTimes.size());
			for (Entry<Integer, Long> entry : _instanceTimes.entrySet()) {
				final long instanceTime = TimeUnit.MILLISECONDS.toSeconds(entry.getValue() - System.currentTimeMillis());
				body.writeD(entry.getKey());
				body.writeD((int) instanceTime);
			}
		}
	}
}