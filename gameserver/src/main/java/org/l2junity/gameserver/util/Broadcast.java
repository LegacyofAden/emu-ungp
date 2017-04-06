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
package org.l2junity.gameserver.util;

import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.s2c.CreatureSay;
import org.l2junity.gameserver.network.packets.s2c.ExShowScreenMessage;

public final class Broadcast {

	/**
	 * Send a packet to all L2PcInstance in the _KnownPlayers of the L2Character that have the Character targeted.<BR>
	 * <B><U> Concept</U> :</B><BR>
	 * L2PcInstance in the detection area of the L2Character are identified in <B>_knownPlayers</B>.<BR>
	 * In order to inform other players of state modification on the L2Character, server just need to go through _knownPlayers to send Server->Client Packet<BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T SEND Server->Client packet to this L2Character (to do this use method toSelfAndKnownPlayers)</B></FONT><BR>
	 *
	 * @param character
	 * @param mov
	 */
	public static void toPlayersTargettingMyself(Creature character, GameServerPacket mov) {
		character.getWorld().forEachVisibleObject(character, Player.class, player ->
		{
			if (player.getTarget() == character) {
				player.sendPacket(mov);
			}
		});

	}

	/**
	 * Send a packet to all L2PcInstance in the _KnownPlayers of the L2Character.<BR>
	 * <B><U> Concept</U> :</B><BR>
	 * L2PcInstance in the detection area of the L2Character are identified in <B>_knownPlayers</B>.<BR>
	 * In order to inform other players of state modification on the L2Character, server just need to go through _knownPlayers to send Server->Client Packet<BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T SEND Server->Client packet to this L2Character (to do this use method toSelfAndKnownPlayers)</B></FONT><BR>
	 *
	 * @param character
	 * @param mov
	 */
	public static void toKnownPlayers(Creature character, GameServerPacket mov) {
		character.getWorld().forEachVisibleObject(character, Player.class, player -> player.sendPacket(mov));
	}

	/**
	 * Send a packet to all L2PcInstance in the _KnownPlayers (in the specified radius) of the L2Character.<BR>
	 * <B><U> Concept</U> :</B><BR>
	 * L2PcInstance in the detection area of the L2Character are identified in <B>_knownPlayers</B>.<BR>
	 * In order to inform other players of state modification on the L2Character, server just needs to go through _knownPlayers to send Server->Client Packet and check the distance between the targets.<BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T SEND Server->Client packet to this L2Character (to do this use method toSelfAndKnownPlayers)</B></FONT><BR>
	 *
	 * @param character
	 * @param packet
	 * @param radius
	 */
	public static void toKnownPlayersInRadius(Creature character, GameServerPacket packet, int radius) {
		if (radius < 0) {
			radius = 1500;
		}

		character.getWorld().forEachVisibleObjectInRadius(character, Player.class, radius, packet::sendTo);
	}

	/**
	 * Send a packet to all L2PcInstance in the _KnownPlayers of the L2Character and to the specified character.<BR>
	 * <B><U> Concept</U> :</B><BR>
	 * L2PcInstance in the detection area of the L2Character are identified in <B>_knownPlayers</B>.<BR>
	 * In order to inform other players of state modification on the L2Character, server just need to go through _knownPlayers to send Server->Client Packet<BR>
	 *
	 * @param character
	 * @param packet
	 */
	public static void toSelfAndKnownPlayers(Creature character, GameServerPacket packet) {
		if (character instanceof Player) {
			character.sendPacket(packet);
		}

		toKnownPlayers(character, packet);
	}

	// To improve performance we are comparing values of radius^2 instead of calculating sqrt all the time
	public static void toSelfAndKnownPlayersInRadius(Creature character, GameServerPacket packet, int radius) {
		if (radius < 0) {
			radius = 600;
		}

		if (character instanceof Player) {
			character.sendPacket(packet);
		}

		character.getWorld().forEachVisibleObjectInRadius(character, Player.class, radius, packet::sendTo);
	}

	/**
	 * Send a packet to all L2PcInstance present in the game.<BR>
	 * <B><U> Concept</U> :</B><BR>
	 * In order to inform other players of state modification on the L2Character, server just need to go through _allPlayers to send Server->Client Packet<BR>
	 * <FONT COLOR=#FF0000><B> <U>Caution</U> : This method DOESN'T SEND Server->Client packet to this L2Character (to do this use method toSelfAndKnownPlayers)</B></FONT><BR>
	 *
	 * @param packet
	 */
	public static void toAllOnlinePlayers(GameServerPacket packet) {
		for (Player player : WorldManager.getInstance().getAllPlayers()) {
			if (player.isOnline()) {
				player.sendPacket(packet);
			}
		}
	}

	public static void toAllOnlinePlayers(String text) {
		toAllOnlinePlayers(text, false);
	}

	public static void toAllOnlinePlayers(String text, boolean isCritical) {
		toAllOnlinePlayers(new CreatureSay(0, isCritical ? ChatType.CRITICAL_ANNOUNCE : ChatType.ANNOUNCEMENT, "", text));
	}

	public static void toAllOnlinePlayersOnScreen(String text) {
		toAllOnlinePlayers(new ExShowScreenMessage(text, 10000));
	}
}
