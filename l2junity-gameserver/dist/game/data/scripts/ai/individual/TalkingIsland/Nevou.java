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

import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.instancemanager.SuperpointManager;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

import ai.AbstractNpcAI;

/**
 * Nevou AI.
 * @author Gladicek
 */
public final class Nevou extends AbstractNpcAI
{
	// NPC
	private static final int NEVOU = 33101;
	
	private Nevou()
	{
		addSpawnId(NEVOU);
	}
	
	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, PlayerInstance player)
	{
		if (event.equals("NPC_SHOUT"))
		{
			switch (getRandom(3))
			{
				case 0:
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.KIDS_THESE_DAYS);
					break;
				case 1:
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.WHEN_I_WAS_YOUNG);
					break;
			}
			getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		}
	}
	
	@Override
	public String onSpawn(Npc npc)
	{
		getTimers().addTimer("NPC_SHOUT", (10 + getRandom(5)) * 1000, npc, null);
		SuperpointManager.getInstance().startMoving(npc, "si_town_05");
		return super.onSpawn(npc);
	}
	
	public static void main(String[] args)
	{
		new Nevou();
	}
}