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
package quests.Q00451_LuciensAltar;

import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.enums.QuestType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.NpcLogListHolder;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

import java.util.HashSet;
import java.util.Set;

/**
 * Lucien's Altar (451)
 *
 * @author malyelfik
 */
public final class Q00451_LuciensAltar extends Quest {
	// NPCs
	private static final int DAICHIR = 30537;
	private static final int[] ALTARS =
			{
					32706,
					32707,
					32708,
					32709,
					32710
			};
	// Items
	private static final int REPLENISHED_BEAD = 14877;
	private static final int DISCHARGED_BEAD = 14878;
	// Misc
	private static final int MIN_LEVEL = 80;

	public Q00451_LuciensAltar() {
		super(451);
		addStartNpc(DAICHIR);
		addTalkId(ALTARS);
		addTalkId(DAICHIR);
		registerQuestItems(REPLENISHED_BEAD, DISCHARGED_BEAD);
		addCondMinLevel(MIN_LEVEL, "30537-02.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState st = getQuestState(player, false);

		if (st == null) {
			return null;
		}

		String htmltext = null;
		if (event.equals("30537-04.htm")) {
			htmltext = event;
		} else if (event.equals("30537-05.htm")) {
			st.startQuest();
			giveItems(player, REPLENISHED_BEAD, 5);
			htmltext = event;
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player) {
		String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);

		if (st == null) {
			return htmltext;
		}

		final int npcId = npc.getId();
		if (npcId == DAICHIR) {
			switch (st.getState()) {
				case State.COMPLETED: {
					if (!st.isNowAvailable()) {
						htmltext = "30537-03.html";
						break;
					}
					st.setState(State.CREATED);
				}
				case State.CREATED: {
					htmltext = (player.getLevel() >= MIN_LEVEL) ? "30537-01.htm" : "30537-02.htm";
					break;
				}
				case State.STARTED: {
					if (st.isCond(1)) {
						if (st.isSet("32706") || st.isSet("32707") || st.isSet("32708") || st.isSet("32709") || st.isSet("32710")) {
							htmltext = "30537-10.html";
						} else {
							htmltext = "30537-09.html";
						}
					} else {
						st.exitQuest(QuestType.DAILY, true);
						giveAdena(player, 742_800, true);
						if (player.getLevel() >= MIN_LEVEL) {
							addExp(player, 13_773_960);
							addSp(player, 3_305);
						}
						htmltext = "30537-08.html";
					}
					break;
				}
			}
		} else if (st.isCond(1) && hasQuestItems(player, REPLENISHED_BEAD)) {
			if (st.getInt(String.valueOf(npcId)) == 0) {
				st.set(String.valueOf(npcId), "1");
				takeItems(player, REPLENISHED_BEAD, 1);
				giveItems(player, DISCHARGED_BEAD, 1);
				playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);

				if (getQuestItemsCount(player, DISCHARGED_BEAD) >= 5) {
					st.setCond(2, true);
				}
				htmltext = "recharge.html";
			} else {
				htmltext = "findother.html";
			}
		}
		return htmltext;
	}

	@Override
	public Set<NpcLogListHolder> getNpcLogList(Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs != null) {
			final Set<NpcLogListHolder> npcLogList = new HashSet<>(1);
			npcLogList.add(new NpcLogListHolder(DISCHARGED_BEAD, false, (int) getQuestItemsCount(player, DISCHARGED_BEAD)));
			return npcLogList;
		}
		return super.getNpcLogList(player);
	}
}