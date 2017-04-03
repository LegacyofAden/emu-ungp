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
package quests.Q10701_TheRoadToDestruction;

import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

/**
 * The Road to Destruction (10701)
 *
 * @author Gladicek
 */

public final class Q10701_TheRoadToDestruction extends Quest {
	// NPCs
	private static final int KEUCEREUS = 32548;
	private static final int ALLENOS = 32526;
	// Item
	private static final int KEUCEREUS_INTRODUCTION_SOD = 38577;
	// Misc
	private static final int MIN_LEVEL = 93;

	public Q10701_TheRoadToDestruction() {
		super(10701);
		addStartNpc(KEUCEREUS);
		addTalkId(KEUCEREUS, ALLENOS);
		addCondMinLevel(MIN_LEVEL, "32548-06.htm");
		registerQuestItems(KEUCEREUS_INTRODUCTION_SOD);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = event;

		switch (event) {
			case "32548-02.htm":
			case "32548-03.htm": {
				htmltext = event;
				break;
			}
			case "32548-04.html": {
				qs.startQuest();
				giveItems(player, KEUCEREUS_INTRODUCTION_SOD, 1);
				break;
			}
			case "32526-02.html": {
				if (qs.isCond(1)) {
					if (player.getLevel() >= MIN_LEVEL) {
						giveAdena(player, 17612, true);
						addExp(player, 8_173_305);
						addSp(player, 1961);
						qs.exitQuest(false, true);
						htmltext = event;
					} else {
						htmltext = getNoQuestLevelRewardMsg(player);
					}
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player) {
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);

		if (qs == null) {
			return htmltext;
		}

		switch (qs.getState()) {
			case State.CREATED: {
				if (npc.getId() == KEUCEREUS) {
					htmltext = "32548-01.htm";
				}
				break;
			}
			case State.STARTED: {
				htmltext = npc.getId() == KEUCEREUS ? "32548-05.html" : "32526-01.html";
				break;
			}
			case State.COMPLETED: {
				if (npc.getId() == KEUCEREUS) {
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
		}
		return htmltext;
	}
}