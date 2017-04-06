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
package quests.Q10761_AnOrcInLove;

import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.NpcLogListHolder;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

import java.util.HashSet;
import java.util.Set;

/**
 * An Orc in Love (10761)
 *
 * @author malyelfik
 */
public class Q10761_AnOrcInLove extends Quest {
	// NPC
	private static final int VORBOS = 33966;
	// Monsters
	private static final int[] MONSTERS =
			{
					20494, // Turek War Hound
					20495, // Turek Orc Prefect
					20496, // Turek Orc Archer
					20497, // Turek Orc Skirmisher
					20498, // Turek Orc Supplier
					20499, // Turek Orc Footman
					20500, // Turek Orc Sentinel
					20501, // Turek Orc Priest
					20546, // Turek Orc Elder
			};
	// Misc
	private static final int MIN_LEVEL = 30;
	private static final String KILL_COUNT_VAR = "KillCount";

	public Q10761_AnOrcInLove() {
		super(10761);
		addStartNpc(VORBOS);
		addTalkId(VORBOS);
		addKillId(MONSTERS);
		addCondRace(Race.ERTHEIA, "33966-00.htm");
		addCondMinLevel(MIN_LEVEL, "33966-00.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = event;
		switch (event) {
			case "33966-02.htm":
			case "33966-03.htm":
			case "33966-04.htm":
				break;
			case "33966-05.htm": {
				qs.startQuest();
				break;
			}
			case "33966-08.html": {
				if (qs.isCond(2)) {
					if (player.getLevel() >= MIN_LEVEL) {
						giveStoryQuestReward(npc, player);
						addExp(player, 706_841);
						addSp(player, 85);
						qs.exitQuest(false, true);
					} else {
						htmltext = getNoQuestLevelRewardMsg(player);
					}
				}
				break;
			}
			default:
				htmltext = null;
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player) {
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);

		switch (qs.getState()) {
			case State.CREATED:
				htmltext = "33966-01.htm";
				break;
			case State.STARTED:
				htmltext = (qs.isCond(1)) ? "33966-06.html" : "33966-07.html";
				break;
			case State.COMPLETED:
				htmltext = getAlreadyCompletedMsg(player);
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon) {
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isCond(1)) {
			int killCount = qs.getInt(KILL_COUNT_VAR);
			if (killCount < 30) {
				qs.set(KILL_COUNT_VAR, ++killCount);
				if (killCount >= 30) {
					qs.setCond(2, true);
				} else {
					sendNpcLogList(killer);
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}

	@Override
	public Set<NpcLogListHolder> getNpcLogList(Player player) {
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isCond(1)) {
			final int killCount = qs.getInt(KILL_COUNT_VAR);
			if (killCount > 0) {
				final Set<NpcLogListHolder> holder = new HashSet<>(1);
				holder.add(new NpcLogListHolder(NpcStringId.KILL_TUREK_ORCS, killCount));
				return holder;
			}
		}
		return super.getNpcLogList(player);
	}
}