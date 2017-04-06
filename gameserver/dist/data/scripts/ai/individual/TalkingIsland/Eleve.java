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
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;
import org.l2junity.gameserver.util.Util;

/**
 * Eleve AI.
 *
 * @author St3eT
 */
public final class Eleve extends AbstractNpcAI {
	// NPC
	private static final int ELEVE = 33246;

	private Eleve() {
		addSpawnId(ELEVE);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		if (event.equals("NPC_MOVE")) {
			if (getRandomBoolean()) {
				addMoveToDesire(npc, GeoData.getInstance().moveCheck(npc.getLocation(), Util.getRandomPosition(npc.getSpawn(), 0, 500), npc.getInstanceWorld()), 23);
			}
			getTimers().addTimer("NPC_MOVE", (10 + getRandom(5)) * 1000, npc, null);
		} else if (event.equals("NPC_SHOUT")) {
			final int rand = getRandom(3);
			if (rand == 0) {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.DON_T_KNOW_WHAT_TO_DO_LOOK_AT_THE_MAP);
			} else if (rand == 1) {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.DO_YOU_SEE_A_SCROLL_ICON_GO_THAT_LOCATION);
			}
			getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		getTimers().addTimer("NPC_MOVE", (10 + getRandom(5)) * 1000, npc, null);
		getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Eleve();
	}
}