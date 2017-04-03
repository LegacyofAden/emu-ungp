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
 * Mei AI.
 *
 * @author Gladicek
 */
public final class Mei extends AbstractNpcAI {
	// NPC
	private static final int MEI = 33280;
	private static final int ROTINA = 33027;

	private Mei() {
		addSpawnId(MEI);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		if (event.equals("NPC_SHOUT")) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.IF_YOU_IGNORE_THE_TRAINING_GROUNDS_YOU_LL_REGRET_IT);
			getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		} else if (event.equals("NPC_FOLLOW")) {
			addSpawn(ROTINA, npc.getX() + 10, npc.getY() + 10, npc.getZ() + 10, 0, false, 0);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		getTimers().addTimer("NPC_FOLLOW", 100, npc, null);
		SuperpointManager.getInstance().startMoving(npc, "si_town_deco02");
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Mei();
	}
}