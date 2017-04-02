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
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Mephisto AI.
 *
 * @author Gladicek
 */
public final class Mephisto extends AbstractNpcAI {
	// NPC
	private static final int MEPHISTO = 33577;
	private static final int KANDRA = 33111;
	// Items
	private static final int WEAPON = 15304;
	// Distances
	private static final int MIN_DISTANCE = 70;
	private static final int MAX_DISTANCE = 200;

	private Mephisto() {
		addSpawnId(MEPHISTO);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, PlayerInstance player) {
		switch (event) {
			case "NPC_SHOUT": {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.ACCORDING_TO_THIS_RECORDS_ARE_BROKEN_EVERY_MONTH);
				getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
				break;
			}
			case "WALK_AROUND_KANDRA": {
				followNpc(npc, KANDRA, 115, MIN_DISTANCE, MAX_DISTANCE);
				break;
			}
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		npc.setRHandId(WEAPON);
		followNpc(npc, KANDRA, 115, MIN_DISTANCE, MAX_DISTANCE);
		getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		getTimers().addRepeatingTimer("WALK_AROUND_KANDRA", 1000, npc, null);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Mephisto();
	}
}