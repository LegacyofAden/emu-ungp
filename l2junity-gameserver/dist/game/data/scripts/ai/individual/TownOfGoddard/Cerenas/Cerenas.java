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
package ai.individual.TownOfGoddard.Cerenas;

import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;

import ai.AbstractNpcAI;
import quests.Q10369_NoblesseSoulTesting.Q10369_NoblesseSoulTesting;

/**
 * Cerenas AI.
 * @author Gladicek
 */
public final class Cerenas extends AbstractNpcAI
{
	// NPC
	private static final int CERENAS = 31281;
	// Item
	private static final int NOBLESSE_TIARA = 7694;
	
	private Cerenas()
	{
		addStartNpc(CERENAS);
		addTalkId(CERENAS);
	}
	
	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player)
	{
		String htmltext = null;
		
		if (event.equals("tiara"))
		{
			if (player.hasQuestCompleted(Q10369_NoblesseSoulTesting.class.getSimpleName()))
			{
				if (!hasQuestItems(player, NOBLESSE_TIARA))
				{
					giveItems(player, NOBLESSE_TIARA, 1);
				}
				else
				{
					htmltext = "31281-02.html";
				}
			}
			else
			{
				htmltext = "31281-01.html";
			}
		}
		return htmltext;
	}
	
	public static void main(String[] args)
	{
		new Cerenas();
	}
}