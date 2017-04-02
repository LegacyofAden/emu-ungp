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
package org.l2junity.gameserver.model;

import org.l2junity.gameserver.instancemanager.HandysBlockCheckerManager;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.entity.BlockCheckerEngine;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xban1x
 */
public final class ArenaParticipantsHolder {
	private final int _arena;
	private final List<PlayerInstance> _redPlayers;
	private final List<PlayerInstance> _bluePlayers;
	private final BlockCheckerEngine _engine;

	public ArenaParticipantsHolder(int arena) {
		_arena = arena;
		_redPlayers = new ArrayList<>(6);
		_bluePlayers = new ArrayList<>(6);
		_engine = new BlockCheckerEngine(this, _arena);
	}

	public List<PlayerInstance> getRedPlayers() {
		return _redPlayers;
	}

	public List<PlayerInstance> getBluePlayers() {
		return _bluePlayers;
	}

	public List<PlayerInstance> getAllPlayers() {
		List<PlayerInstance> all = new ArrayList<>(12);
		all.addAll(_redPlayers);
		all.addAll(_bluePlayers);
		return all;
	}

	public void addPlayer(PlayerInstance player, int team) {
		if (team == 0) {
			_redPlayers.add(player);
		} else {
			_bluePlayers.add(player);
		}
	}

	public void removePlayer(PlayerInstance player, int team) {
		if (team == 0) {
			_redPlayers.remove(player);
		} else {
			_bluePlayers.remove(player);
		}
	}

	public int getPlayerTeam(PlayerInstance player) {
		if (_redPlayers.contains(player)) {
			return 0;
		} else if (_bluePlayers.contains(player)) {
			return 1;
		} else {
			return -1;
		}
	}

	public int getRedTeamSize() {
		return _redPlayers.size();
	}

	public int getBlueTeamSize() {
		return _bluePlayers.size();
	}

	public void broadCastPacketToTeam(IClientOutgoingPacket packet) {
		for (PlayerInstance p : _redPlayers) {
			p.sendPacket(packet);
		}
		for (PlayerInstance p : _bluePlayers) {
			p.sendPacket(packet);
		}
	}

	public void clearPlayers() {
		_redPlayers.clear();
		_bluePlayers.clear();
	}

	public BlockCheckerEngine getEvent() {
		return _engine;
	}

	public void updateEvent() {
		_engine.updatePlayersOnStart(this);
	}

	public void checkAndShuffle() {
		final int redSize = _redPlayers.size();
		final int blueSize = _bluePlayers.size();
		if (redSize > (blueSize + 1)) {
			broadCastPacketToTeam(SystemMessage.getSystemMessage(SystemMessageId.TEAM_MEMBERS_WERE_MODIFIED_BECAUSE_THE_TEAMS_WERE_UNBALANCED));
			final int needed = redSize - (blueSize + 1);
			for (int i = 0; i < (needed + 1); i++) {
				final PlayerInstance plr = _redPlayers.get(i);
				if (plr == null) {
					continue;
				}
				HandysBlockCheckerManager.getInstance().changePlayerToTeam(plr, _arena, 1);
			}
		} else if (blueSize > (redSize + 1)) {
			broadCastPacketToTeam(SystemMessage.getSystemMessage(SystemMessageId.TEAM_MEMBERS_WERE_MODIFIED_BECAUSE_THE_TEAMS_WERE_UNBALANCED));
			final int needed = blueSize - (redSize + 1);
			for (int i = 0; i < (needed + 1); i++) {
				final PlayerInstance plr = _bluePlayers.get(i);
				if (plr == null) {
					continue;
				}
				HandysBlockCheckerManager.getInstance().changePlayerToTeam(plr, _arena, 0);
			}
		}
	}
}
