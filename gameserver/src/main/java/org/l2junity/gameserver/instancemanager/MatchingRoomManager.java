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
package org.l2junity.gameserver.instancemanager;

import org.l2junity.gameserver.enums.MatchingRoomType;
import org.l2junity.gameserver.enums.PartyMatchingRoomLevelType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.matching.MatchingRoom;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Sdw
 */
public class MatchingRoomManager {
	private volatile Set<Player> _waitingList;

	private static final Map<MatchingRoomType, Map<Integer, MatchingRoom>> _rooms = new ConcurrentHashMap<>(2);

	private final AtomicInteger _id = new AtomicInteger(0);

	public void addToWaitingList(Player player) {
		if (_waitingList == null) {
			synchronized (this) {
				if (_waitingList == null) {
					_waitingList = ConcurrentHashMap.newKeySet(1);
				}
			}
		}
		_waitingList.add(player);
	}

	public void removeFromWaitingList(Player player) {
		getPlayerInWaitingList().remove(player);
	}

	public Set<Player> getPlayerInWaitingList() {
		return _waitingList == null ? Collections.emptySet() : _waitingList;
	}

	public List<Player> getPlayerInWaitingList(int minLevel, int maxLevel, List<ClassId> classIds, String query) {
		return _waitingList == null ? Collections.emptyList() : _waitingList.stream().filter(p -> (p.getLevel() >= minLevel) && (p.getLevel() <= maxLevel)).filter(p -> (classIds == null) || classIds.contains(p.getClassId())).filter(p -> query.isEmpty() || p.getName().toLowerCase().contains(query)).collect(Collectors.toList());
	}

	public int addMatchingRoom(MatchingRoom room) {
		final int roomId = _id.incrementAndGet();
		_rooms.computeIfAbsent(room.getRoomType(), k -> new ConcurrentHashMap<>()).put(roomId, room);
		return roomId;
	}

	public void removeMatchingRoom(MatchingRoom room) {
		_rooms.getOrDefault(room.getRoomType(), Collections.emptyMap()).remove(room.getId());
	}

	public Map<Integer, MatchingRoom> getPartyMathchingRooms() {
		return _rooms.get(MatchingRoomType.PARTY);
	}

	public List<MatchingRoom> getPartyMathchingRooms(int location, PartyMatchingRoomLevelType type, int requestorLevel) {
		//@formatter:off
		return _rooms.getOrDefault(MatchingRoomType.PARTY, Collections.emptyMap()).values().stream()
				.filter(room -> (location < 0) || (room.getLocation() == location))
				.filter(room -> (type == PartyMatchingRoomLevelType.ALL) || ((room.getMinLvl() >= requestorLevel) && (room.getMaxLvl() <= requestorLevel)))
				.collect(Collectors.toList());
		//@formatter:on
	}

	public Map<Integer, MatchingRoom> getCCMathchingRooms() {
		return _rooms.get(MatchingRoomType.COMMAND_CHANNEL);
	}

	public List<MatchingRoom> getCCMathchingRooms(int location, int level) {
		//@formatter:off
		return _rooms.getOrDefault(MatchingRoomType.COMMAND_CHANNEL, Collections.emptyMap()).values().stream()
				.filter(r -> r.getLocation() == location)
				.filter(r -> (r.getMinLvl() <= level) && (r.getMaxLvl() >= level))
				.collect(Collectors.toList());
		//@formatter:on
	}

	public MatchingRoom getCCMatchingRoom(int roomId) {
		return _rooms.getOrDefault(MatchingRoomType.COMMAND_CHANNEL, Collections.emptyMap()).get(roomId);
	}

	public MatchingRoom getPartyMathchingRoom(int location, int level) {
		//@formatter:off
		return _rooms.getOrDefault(MatchingRoomType.PARTY, Collections.emptyMap()).values().stream()
				.filter(r -> r.getLocation() == location)
				.filter(r -> (r.getMinLvl() <= level) && (r.getMaxLvl() >= level))
				.findFirst()
				.orElse(null);
		//@formatter:on
	}

	public MatchingRoom getPartyMathchingRoom(int roomId) {
		return _rooms.getOrDefault(MatchingRoomType.PARTY, Collections.emptyMap()).get(roomId);
	}

	public static MatchingRoomManager getInstance() {
		return SingletonHolder._instance;
	}

	private static class SingletonHolder {
		protected static final MatchingRoomManager _instance = new MatchingRoomManager();
	}
}
