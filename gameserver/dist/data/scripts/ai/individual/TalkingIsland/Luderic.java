/*
 * Copyright (C) 2004-2016 L2J Unity
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
package ai.individual.TalkingIsland;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Luderic AI.
 *
 * @author Gladicek
 */
public final class Luderic extends AbstractNpcAI {
	// NPCs
	private static final int LUDERIC = 33575;
	// Misc
	private static final NpcStringId[] LUDERIC_SHOUT =
			{
					NpcStringId.THERE_IS_A_DAY_WHERE_YOU_CAN_SEE_EVEN_THE_ADEN_CONTINENT_IF_THE_WEATHER_IS_GOOD,
					NpcStringId.IF_I_M_HERE_IT_FEELS_LIKE_TIME_HAS_STOPPED
			};

	private Luderic() {
		addSpawnId(LUDERIC);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		if (event.equals("SPAM_TEXT") && (npc != null)) {
			npc.broadcastSay(ChatType.NPC_GENERAL, LUDERIC_SHOUT[getRandom(2)], 1000);
		} else if (event.equals("SOCIAL_ACTION") && (npc != null)) {
			npc.broadcastSocialAction(1);
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public String onSpawn(Npc npc) {
		startQuestTimer("SPAM_TEXT", 7000, npc, null, true);
		startQuestTimer("SOCIAL_ACTION", 3000, npc, null, true);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Luderic();
	}
}