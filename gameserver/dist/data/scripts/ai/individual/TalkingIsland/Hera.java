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
import org.l2junity.gameserver.instancemanager.SuperpointManager;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Hera AI.
 *
 * @author St3eT
 */
public final class Hera extends AbstractNpcAI {
	// NPC
	private static final int HERA = 33208;

	private Hera() {
		addSpawnId(HERA);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		if (event.equals("NPC_SHOUT")) {
			npc.broadcastSay(ChatType.NPC_GENERAL, getRandomBoolean() ? NpcStringId.DOES_THE_GODDESS_SEE_WHAT_SHE_HAS_DONE : NpcStringId.WHY_HAVE_THE_HEROES_ABANDONED_US);
			getTimers().addTimer("NPC_SHOUT", 10000 + (getRandom(5) * 1000), npc, null);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		getTimers().addTimer("NPC_SHOUT", 10000 + (getRandom(5) * 1000), npc, null);
		SuperpointManager.getInstance().startMoving(npc, "si_town_03");
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Hera();
	}
}