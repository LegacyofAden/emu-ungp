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
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

/**
 * Marin Smith AI.
 *
 * @author Gladicek
 */
public final class MarinSmith extends AbstractNpcAI {
	// NPCs
	private static final int MARIN_SMITH = 33285;

	private MarinSmith() {
		addSpawnId(MARIN_SMITH);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		if (event.equals("SPAM_TEXT") && (npc != null)) {
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.HMM_IS_THIS_STILL_A_DECENT_WEAPON, 1000);
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public String onSpawn(Npc npc) {
		startQuestTimer("SPAM_TEXT", 6000, npc, null, true);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new MarinSmith();
	}
}