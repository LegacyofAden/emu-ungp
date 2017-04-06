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

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.s2c.CreatureSay;

import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Battlecruiser
 */
public abstract class AbstractPlayerGroup {
	/**
	 * @return a list of all members of this group
	 */
	public abstract List<Player> getMembers();

	/**
	 * @return a list of object IDs of the members of this group
	 */
	public List<Integer> getMembersObjectId() {
		final List<Integer> ids = new ArrayList<>();
		forEachMember(m ->
		{
			ids.add(m.getObjectId());
			return true;
		});
		return ids;
	}

	/**
	 * @return the leader of this group
	 */
	public abstract Player getLeader();

	/**
	 * Change the leader of this group to the specified player.
	 *
	 * @param leader the player to set as the new leader of this group
	 */
	public abstract void setLeader(Player leader);

	/**
	 * @return the leader's object ID
	 */
	public int getLeaderObjectId() {
		return getLeader().getObjectId();
	}

	/**
	 * Check if a given player is the leader of this group.
	 *
	 * @param player the player to check
	 * @return {@code true} if the specified player is the leader of this group, {@code false} otherwise
	 */
	public boolean isLeader(Player player) {
		return (getLeaderObjectId() == player.getObjectId());
	}

	/**
	 * @return the count of all players in this group
	 */
	public int getMemberCount() {
		return getMembers().size();
	}

	/**
	 * @return the level of this group
	 */
	public abstract int getLevel();

	/**
	 * Broadcast a packet to every member of this group.
	 *
	 * @param packet the packet to broadcast
	 */
	public void broadcastPacket(final GameServerPacket packet) {
		forEachMember(m ->
		{
			if (m != null) {
				m.sendPacket(packet);
			}
			return true;
		});
	}

	/**
	 * Broadcast a system message to this group.
	 *
	 * @param message the system message to broadcast
	 */
	public void broadcastMessage(SystemMessageId message) {
		broadcastPacket(SystemMessage.getSystemMessage(message));
	}

	/**
	 * Broadcast a text message to this group.
	 *
	 * @param text to broadcast
	 */
	public void broadcastString(String text) {
		broadcastPacket(SystemMessage.sendString(text));
	}

	public void broadcastCreatureSay(final CreatureSay msg, final Player broadcaster) {
		forEachMember(m ->
		{
			if ((m != null) && !BlockList.isBlocked(m, broadcaster)) {
				m.sendPacket(msg);
			}
			return true;
		});
	}

	/**
	 * Check if this group contains a given player.
	 *
	 * @param player the player to check
	 * @return {@code true} if this group contains the specified player, {@code false} otherwise
	 */
	public boolean containsPlayer(Player player) {
		return getMembers().contains(player);
	}

	/**
	 * @return a random member of this group
	 */
	public Player getRandomPlayer() {
		return getMembers().get(Rnd.get(getMemberCount()));
	}

	/**
	 * Iterates over the group and executes procedure on each member
	 *
	 * @param procedure the prodecure to be executed on each member.<br>
	 *                  If executing the procedure on a member returns {@code true}, the loop continues to the next member, otherwise it breaks the loop
	 * @return {@code true} if the procedure executed correctly, {@code false} if the loop was broken prematurely
	 */
	public boolean forEachMember(Function<Player, Boolean> procedure) {
		for (Player player : getMembers()) {
			if (!procedure.apply(player)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj instanceof AbstractPlayerGroup) {
			if (getLeaderObjectId() == ((AbstractPlayerGroup) obj).getLeaderObjectId()) {
				return true;
			}
		}

		return false;
	}
}
