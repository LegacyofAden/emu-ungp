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
package quests.Q10410_EmbryoInTheSwampOfScreams;

import java.util.HashSet;
import java.util.Set;

import org.l2junity.gameserver.enums.CategoryType;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.holders.NpcLogListHolder;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

import quests.Q10409_ASuspiciousVagabondInTheSwamp.Q10409_ASuspiciousVagabondInTheSwamp;

/**
 * Embryo in the Swamp of Screams (10410)
 * @author St3eT
 */
public final class Q10410_EmbryoInTheSwampOfScreams extends Quest
{
	// NPCs
	private static final int DOKARA = 33847;
	private static final int EMBRYO = 27508;
	private static final int[] MONSTERS =
	{
		21508, // Splinter Stakato
		21509, // Splinter Stakato Worker
		21510, // Splinter Stakato Soldier
		21511, // Splinter Stakato Drone
		21513, // Needle Stakato
		21514, // Needle Stakato Worker
		21515, // Needle Stakato Soldier
		21516, // Needle Stakato Drone
		21517, // Needle Stakato Drone
		21518, // Frenzied Stakato Soldier
	};
	// Items
	private static final int EAA = 730; // Scroll: Enchant Armor (A-grade)
	// Misc
	private static final int MIN_LEVEL = 65;
	private static final int MAX_LEVEL = 70;
	
	public Q10410_EmbryoInTheSwampOfScreams()
	{
		super(10410);
		addStartNpc(DOKARA);
		addTalkId(DOKARA);
		addKillId(MONSTERS);
		addKillId(EMBRYO);
		addCondNotRace(Race.ERTHEIA, "33847-09.html");
		addCondInCategory(CategoryType.FIGHTER_GROUP, "33847-08.htm");
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "33847-08.htm");
		addCondCompletedQuest(Q10409_ASuspiciousVagabondInTheSwamp.class.getSimpleName(), "33847-08.htm");
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
			case "33847-02.htm":
			case "33847-03.htm":
			{
				htmltext = event;
				break;
			}
			case "33847-04.htm":
			{
				st.startQuest();
				htmltext = event;
				break;
			}
			case "33847-07.html":
			{
				if (st.isCond(2))
				{
					st.exitQuest(false, true);
					giveItems(player, EAA, 5);
					giveStoryQuestReward(npc, player);
					if (player.getLevel() >= MIN_LEVEL)
					{
						addExp(player, 16_968_420);
						addSp(player, 4072);
					}
					htmltext = event;
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
		String htmltext = null;
		
		switch (st.getState())
		{
			case State.CREATED:
			{
				htmltext = "33847-01.htm";
				break;
			}
			case State.STARTED:
			{
				htmltext = st.isCond(1) ? "33847-05.html" : "33847-06.html";
				break;
			}
			case State.COMPLETED:
			{
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
	
	@Override
	public String onKill(Npc npc, PlayerInstance killer, boolean isSummon)
	{
		final QuestState st = getQuestState(killer, false);
		
		if ((st != null) && st.isCond(1))
		{
			if (npc.getId() == EMBRYO)
			{
				int count = st.getInt("KillCount");
				st.set("KillCount", ++count);
				if (count >= 50)
				{
					st.setCond(2, true);
				}
				else
				{
					sendNpcLogList(killer);
					playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
			else
			{
				final Npc embryo = addSpawn(EMBRYO, npc, false, 60000);
				addAttackPlayerDesire(embryo, killer);
				embryo.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.YOU_DARE_INTERFERE_WITH_EMBRYO_SURELY_YOU_WISH_FOR_DEATH);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}
	
	@Override
	public Set<NpcLogListHolder> getNpcLogList(PlayerInstance activeChar)
	{
		final QuestState st = getQuestState(activeChar, false);
		
		if ((st != null) && st.isCond(1))
		{
			final Set<NpcLogListHolder> npcLogList = new HashSet<>(1);
			npcLogList.add(new NpcLogListHolder(EMBRYO, false, st.getInt("KillCount")));
			return npcLogList;
		}
		return super.getNpcLogList(activeChar);
	}
}