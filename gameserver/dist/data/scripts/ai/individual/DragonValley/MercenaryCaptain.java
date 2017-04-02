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
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Mercenary Captain AI.
 *
 * @author Gladicek
 */
public final class MercenaryCaptain extends AbstractNpcAI {
	// NPC
	private static final int MERCENARY_CAPTAIN = 33970;

	private MercenaryCaptain() {
		addSpawnId(MERCENARY_CAPTAIN);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, PlayerInstance player) {
		if (event.equals("NPC_SHOUT")) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THE_EASTERN_PART_OF_DRAGON_VALLEY_IS_MUCH_MORE_DANGEROUS_THAN_THE_WEST_BE_CAREFUL);
			getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 3000, npc, null);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 3000, npc, null);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new MercenaryCaptain();
	}
}