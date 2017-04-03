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
package quests.Q10738_AnInnerBeauty;

import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import quests.Q10737_GrakonsWarehouse.Q10737_GrakonsWarehouse;

/**
 * An Inner Beauty (10738)
 *
 * @author Sdw
 */
public final class Q10738_AnInnerBeauty extends Quest {
	// NPC's
	private static final int GRAKON = 33947;
	private static final int EVNA = 33935;
	// Items
	private static final int GRAKON_S_NOTE = 39521;
	// Misc
	private static final int MIN_LEVEL = 5;
	private static final int MAX_LEVEL = 20;

	public Q10738_AnInnerBeauty() {
		super(10738);
		addStartNpc(GRAKON);
		addTalkId(GRAKON, EVNA);
		addCondRace(Race.ERTHEIA, "");
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "33947-00.htm");
		addCondCompletedQuest(Q10737_GrakonsWarehouse.class.getSimpleName(), "33947-00.htm");
		registerQuestItems(GRAKON_S_NOTE);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = event;
		switch (event) {
			case "33947-02.htm":
			case "33947-03.htm":
			case "33935-02.html":
				break;
			case "33947-04.htm": {
				qs.startQuest();
				giveItems(player, GRAKON_S_NOTE, 1);
				break;
			}
			case "33935-03.html": {
				if (qs.isStarted()) {
					if ((player.getLevel() >= MIN_LEVEL)) {
						addExp(player, 2625);
						addSp(player, 1);
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

		if (qs.isCompleted()) {
			htmltext = getAlreadyCompletedMsg(player);
		}

		if (npc.getId() == GRAKON) {
			if (qs.isCreated()) {
				htmltext = "33947-01.htm";
			} else if (qs.isStarted()) {
				htmltext = "33947-05.html";
			}
		} else if (npc.getId() == EVNA) {
			if (qs.isStarted()) {
				htmltext = "33935-01.html";
			}
		}
		return htmltext;
	}
}
