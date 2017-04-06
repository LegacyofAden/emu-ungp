/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.individual.FantasyIsle;

import ai.AbstractNpcAI;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.instancemanager.HandysBlockCheckerManager;
import org.l2junity.gameserver.model.ArenaParticipantsHolder;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.ExCubeGameChangeTimeToStart;
import org.l2junity.gameserver.network.packets.s2c.ExCubeGameRequestReady;
import org.l2junity.gameserver.network.packets.s2c.ExCubeGameTeamList;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handys Block Checker Event AI.
 *
 * @authors BiggBoss, Gigiikun
 */
public class HandysBlockCheckerEvent extends AbstractNpcAI {
	private static final Logger LOGGER = LoggerFactory.getLogger(HandysBlockCheckerEvent.class);

	// Arena Managers
	private static final int A_MANAGER_1 = 32521;
	private static final int A_MANAGER_2 = 32522;
	private static final int A_MANAGER_3 = 32523;
	private static final int A_MANAGER_4 = 32524;

	public HandysBlockCheckerEvent() {
		addFirstTalkId(A_MANAGER_1, A_MANAGER_2, A_MANAGER_3, A_MANAGER_4);
		HandysBlockCheckerManager.getInstance().startUpParticipantsQueue();
	}

	@Override
	public String onFirstTalk(Npc npc, Player player) {
		if ((npc == null) || (player == null)) {
			return null;
		}

		final int arena = npc.getId() - A_MANAGER_1;
		if (eventIsFull(arena)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_REGISTER_BECAUSE_CAPACITY_HAS_BEEN_EXCEEDED);
			return null;
		}

		if (HandysBlockCheckerManager.getInstance().arenaIsBeingUsed(arena)) {
			player.sendPacket(SystemMessageId.THE_MATCH_IS_BEING_PREPARED_PLEASE_TRY_AGAIN_LATER);
			return null;
		}

		if (HandysBlockCheckerManager.getInstance().addPlayerToArena(player, arena)) {
			ArenaParticipantsHolder holder = HandysBlockCheckerManager.getInstance().getHolder(arena);

			final ExCubeGameTeamList tl = new ExCubeGameTeamList(holder.getRedPlayers(), holder.getBluePlayers(), arena);

			player.sendPacket(tl);

			int countBlue = holder.getBlueTeamSize();
			int countRed = holder.getRedTeamSize();
			int minMembers = GeneralConfig.MIN_BLOCK_CHECKER_TEAM_MEMBERS;

			if ((countBlue >= minMembers) && (countRed >= minMembers)) {
				holder.updateEvent();
				holder.broadCastPacketToTeam(ExCubeGameRequestReady.STATIC_PACKET);
				holder.broadCastPacketToTeam(new ExCubeGameChangeTimeToStart(10));
			}
		}
		return null;
	}

	private boolean eventIsFull(int arena) {
		return HandysBlockCheckerManager.getInstance().getHolder(arena).getAllPlayers().size() == 12;
	}

	public static void main(String[] args) {
		if (GeneralConfig.ENABLE_BLOCK_CHECKER_EVENT) {
			new HandysBlockCheckerEvent();
			LOGGER.info("Handy's Block Checker Event is enabled");
		} else {
			LOGGER.info("Handy's Block Checker Event is disabled");
		}
	}
}
