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
package quests.Q10274_CollectingInTheAir;

import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.model.skills.Skill;

import quests.Q10273_GoodDayToFly.Q10273_GoodDayToFly;

/**
 * Collecting in the Air (10274)
 * @author nonom
 */
public class Q10274_CollectingInTheAir extends Quest
{
	// NPC
	private static final int LEKON = 32557;
	// Items
	private static final int SCROLL = 13844;
	private static final int RED = 13858;
	private static final int BLUE = 13859;
	private static final int GREEN = 13860;
	// Monsters
	private static final int MOBS[] =
	{
		18684, // Red Star Stone
		18685, // Red Star Stone
		18686, // Red Star Stone
		18687, // Blue Star Stone
		18688, // Blue Star Stone
		18689, // Blue Star Stone
		18690, // Green Star Stone
		18691, // Green Star Stone
		18692, // Green Star Stone
	};
	
	public Q10274_CollectingInTheAir()
	{
		super(10274);
		addStartNpc(LEKON);
		addTalkId(LEKON);
		addSkillSeeId(MOBS);
		registerQuestItems(SCROLL, RED, BLUE, GREEN);
	}
	
	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player)
	{
		final QuestState st = getQuestState(player, false);
		if (st == null)
		{
			return getNoQuestMsg(player);
		}
		
		if (event.equals("32557-03.html"))
		{
			st.startQuest();
			giveItems(player, SCROLL, 8);
		}
		return event;
	}
	
	@Override
	public String onSkillSee(Npc npc, PlayerInstance caster, Skill skill, WorldObject[] targets, boolean isSummon)
	{
		final QuestState st = getQuestState(caster, false);
		if ((st == null) || !st.isStarted())
		{
			return null;
		}
		
		if (st.isCond(1) && (skill.getId() == 2630))
		{
			switch (npc.getId())
			{
				case 18684:
				case 18685:
				case 18686:
					giveItems(caster, RED, 1);
					break;
				case 18687:
				case 18688:
				case 18689:
					giveItems(caster, BLUE, 1);
					break;
				case 18690:
				case 18691:
				case 18692:
					giveItems(caster, GREEN, 1);
					break;
			}
			playSound(caster, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			npc.doDie(caster);
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}
	
	@Override
	public String onTalk(Npc npc, PlayerInstance player)
	{
		String htmltext = getNoQuestMsg(player);
		QuestState st = getQuestState(player, true);
		if (st == null)
		{
			return htmltext;
		}
		
		switch (st.getState())
		{
			case State.COMPLETED:
				htmltext = "32557-0a.html";
				break;
			case State.CREATED:
				st = player.getQuestState(Q10273_GoodDayToFly.class.getSimpleName());
				if (st == null)
				{
					htmltext = "32557-00.html";
				}
				else
				{
					htmltext = ((player.getLevel() >= 75) && st.isCompleted()) ? "32557-01.htm" : "32557-00.html";
				}
				break;
			case State.STARTED:
				if ((getQuestItemsCount(player, RED) + getQuestItemsCount(player, BLUE) + getQuestItemsCount(player, GREEN)) >= 8)
				{
					htmltext = "32557-05.html";
					giveItems(player, 13728, 1);
					addExp(player, 25160);
					addSp(player, 2525); // TODO Incorrect SP reward.
					st.exitQuest(false, true);
				}
				else
				{
					htmltext = "32557-04.html";
				}
				break;
		}
		return htmltext;
	}
}
