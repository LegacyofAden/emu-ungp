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
package ai.individual.TalkingIsland.Apprentice;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Apprentice AI.
 *
 * @author St3eT
 */
public final class Apprentice extends AbstractNpcAI {
	// NPCs
	private static final int APPRENTICE = 33124;
	// Skill
	private static final SkillHolder KUKURU = new SkillHolder(9204, 1); // Kukuru

	private Apprentice() {
		addSpawnId(APPRENTICE);
		addStartNpc(APPRENTICE);
		addTalkId(APPRENTICE);
		addFirstTalkId(APPRENTICE);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		if (event.equals("rideKukuru")) {
			if (!player.isTransformed()) {
				npc.doInstantCast(player, KUKURU);
			} else {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.YOU_CAN_T_RIDE_A_KUKURI_NOW);
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		if (event.equals("SPAM_TEXT") && (npc != null)) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.TRY_RIDING_A_KUKURI, 1000);
		}
	}

	@Override
	public String onSpawn(Npc npc) {
		getTimers().addRepeatingTimer("SPAM_TEXT", 12000, npc, null);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Apprentice();
	}
}
