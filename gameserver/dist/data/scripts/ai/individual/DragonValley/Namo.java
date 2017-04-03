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
package ai.individual.DragonValley;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Namo AI.
 *
 * @author Gladicek
 */
public final class Namo extends AbstractNpcAI {
	// NPC
	private static final int NAMO = 33973;

	private Namo() {
		addSpawnId(NAMO);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		if (event.equals("NPC_SHOUT")) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THIS_PLACE_SWARMS_WITH_DRAGONS_BY_DAY_AND_UNDEAD_BY_NIGHT);
			getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 3000, npc, null);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 3000, npc, null);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Namo();
	}
}