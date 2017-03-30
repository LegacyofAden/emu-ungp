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
package ai.individual.RuinsOfDespair;

import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

import ai.AbstractNpcAI;

/**
 * Guard AI.
 * @author St3eT, Gladicek
 */
public final class RoDGuard extends AbstractNpcAI
{
	// NPCs
	private static final int GUARD_1 = 33432;
	private static final int GUARD_2 = 33429;
	
	private RoDGuard()
	{
		addSpawnId(GUARD_1, GUARD_2);
	}
	
	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, PlayerInstance player)
	{
		if (event.equals("NPC_SHOUT1"))
		{
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THIS_IS_THE_RUINS_OF_AGONY_WHERE_POSLOF_IS);
			getTimers().addTimer("NPC_SHOUT1", (10 + getRandom(5)) * 1000, npc, null);
		}
		else if (event.equals("NPC_SHOUT2"))
		{
			npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.IT_S_DANGEROUS_SO_BE_CAREFUL);
			getTimers().addTimer("NPC_SHOUT2", (10 + getRandom(5)) * 1000, npc, null);
		}
	}
	
	@Override
	public String onSpawn(Npc npc)
	{
		if (npc.getId() == GUARD_1)
		{
			getTimers().addTimer("NPC_SHOUT1", (10 + getRandom(5)) * 1000, npc, null);
		}
		else if (npc.getId() == GUARD_2)
		{
			getTimers().addTimer("NPC_SHOUT2", (10 + getRandom(5)) * 1000, npc, null);
		}
		return super.onSpawn(npc);
	}
	
	public static void main(String[] args)
	{
		new RoDGuard();
	}
}