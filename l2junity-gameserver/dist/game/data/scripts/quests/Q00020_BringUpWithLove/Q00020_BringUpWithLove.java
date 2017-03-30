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
package quests.Q00020_BringUpWithLove;

import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

/**
 * Bring Up With Love (20)
 * @author Adry_85
 */
public class Q00020_BringUpWithLove extends Quest
{
	// NPC
	private static final int TUNATUN = 31537;
	// Items
	private static final int WATER_CRYSTAL = 9553;
	private static final int INNOCENCE_JEWEL = 15533;
	// Misc
	private static final int MIN_LEVEL = 82;
	
	public Q00020_BringUpWithLove()
	{
		super(20);
		addStartNpc(TUNATUN);
		addTalkId(TUNATUN);
	}
	
	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return null;
		}
		
		String htmltext = null;
		switch (event)
		{
			case "31537-02.htm":
			case "31537-03.htm":
			case "31537-04.htm":
			case "31537-05.htm":
			case "31537-06.htm":
			case "31537-07.htm":
			case "31537-08.htm":
			case "31537-09.htm":
			case "31537-10.htm":
			case "31537-12.htm":
			{
				htmltext = event;
				break;
			}
			case "31537-11.html":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "31537-16.html":
			{
				if (st.isCond(2) && hasQuestItems(player, INNOCENCE_JEWEL))
				{
					if ((player.getLevel() >= MIN_LEVEL))
					{
						giveItems(player, WATER_CRYSTAL, 1);
						takeItems(player, INNOCENCE_JEWEL, -1);
						addExp(player, 26_950_000);
						addSp(player, 6_468);
						st.exitQuest(false, true);
						htmltext = event;
					}
					else
					{
						htmltext = getNoQuestLevelRewardMsg(player);
					}
				}
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onTalk(Npc npc, PlayerInstance player)
	{
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);
		if (st == null)
		{
			return htmltext;
		}
		
		switch (st.getState())
		{
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
			case State.CREATED:
				htmltext = player.getLevel() >= MIN_LEVEL ? "31537-01.htm" : "31537-13.html";
				break;
			case State.STARTED:
				switch (st.getCond())
				{
					case 1:
					{
						htmltext = "31537-14.html";
						break;
					}
					case 2:
					{
						htmltext = (!hasQuestItems(player, INNOCENCE_JEWEL)) ? "31537-14.html" : "31537-15.html";
						break;
					}
				}
				break;
		}
		return htmltext;
	}
	
	public static void checkJewelOfInnocence(PlayerInstance player)
	{
		final QuestState st = player.getQuestState(Q00020_BringUpWithLove.class.getSimpleName());
		if ((st != null) && st.isCond(1) && !hasQuestItems(player, INNOCENCE_JEWEL) && (getRandom(100) < 5))
		{
			giveItems(player, INNOCENCE_JEWEL, 1);
			st.setCond(2, true);
		}
	}
}
