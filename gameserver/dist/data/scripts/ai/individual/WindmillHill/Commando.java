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
package ai.individual.WindmillHill;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Commando AI.
 *
 * @author St3eT, Gladicek
 */
public final class Commando extends AbstractNpcAI {
	// NPCs
	private static final int COMMANDO_1 = 33422;
	private static final int COMMANDO_2 = 33423;

	private Commando() {
		addSpawnId(COMMANDO_1, COMMANDO_2);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, PlayerInstance player) {
		if (event.equals("NPC_SHOUT1")) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.WE_RE_COLLECTING_SEEKER_OF_ADEN);
			getTimers().addTimer("NPC_SHOUT1", (10 + getRandom(5)) * 1000, npc, null);
		} else if (event.equals("NPC_SHOUT2")) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.AH_THIS_IS_THE_WINDMILL_HILL);
			getTimers().addTimer("NPC_SHOUT2", (10 + getRandom(5)) * 1000, npc, null);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		if (npc.getId() == COMMANDO_1) {
			getTimers().addTimer("NPC_SHOUT1", (10 + getRandom(5)) * 1000, npc, null);
		} else if (npc.getId() == COMMANDO_2) {
			getTimers().addTimer("NPC_SHOUT2", (10 + getRandom(5)) * 1000, npc, null);
		}
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Commando();
	}
}