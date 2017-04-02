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
package quests.Q00618_IntoTheFlame;

import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;

import java.util.HashMap;
import java.util.Map;

/**
 * Into The Flame (618)
 *
 * @author St3eT
 */
public class Q00618_IntoTheFlame extends Quest {
	// NPCs
	private static final int KLEIN = 31540;
	private static final int HILDA = 31271;
	// Monsters
	private static final Map<Integer, Integer> MONSTERS = new HashMap<>();
	// Items
	private static final int VACUALITE_ORE = 7265;
	private static final int VACUALITE = 7266;
	private static final int VACUALITE_FLOATING_STONE = 7267;
	// Misc
	private static final int MIN_LEVEL = 60;
	private static final int REQUIRED_COUNT = 50;

	static {
		MONSTERS.put(21274, 630);
		MONSTERS.put(21276, 630);
		MONSTERS.put(21282, 670);
		MONSTERS.put(21283, 670);
		MONSTERS.put(21284, 670);
		MONSTERS.put(21290, 710);
		MONSTERS.put(21291, 710);
		MONSTERS.put(21292, 710);
	}

	public Q00618_IntoTheFlame() {
		super(618);
		addStartNpc(KLEIN);
		addTalkId(HILDA, KLEIN);
		addKillId(MONSTERS.keySet());
		registerQuestItems(VACUALITE_ORE, VACUALITE);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player) {
		final QuestState st = getQuestState(player, false);
		if (st == null) {
			return null;
		}
		String htmltext = null;
		switch (event) {
			case "31540-03.htm":
				st.startQuest();
				htmltext = event;
				break;
			case "31540-05.html":
				if (!hasQuestItems(player, VACUALITE)) {
					htmltext = "31540-03.htm";
				} else {
					giveItems(player, VACUALITE_FLOATING_STONE, 1);
					st.exitQuest(true, true);
					htmltext = event;
				}
				break;
			case "31271-02.html":
				if (st.isCond(1)) {
					st.setCond(2, true);
					htmltext = event;
				}
				break;
			case "31271-05.html":
				if ((getQuestItemsCount(player, VACUALITE_ORE) == REQUIRED_COUNT) && st.isCond(3)) {
					takeItems(player, VACUALITE_ORE, -1);
					giveItems(player, VACUALITE, 1);
					st.setCond(4, true);
					htmltext = event;
				} else {
					htmltext = "31271-03.html";
				}
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, PlayerInstance player, boolean isPet) {
		final PlayerInstance member = getRandomPartyMember(player, 2);
		if (member != null) {
			final QuestState qs = getQuestState(member, false);
			if ((getQuestItemsCount(member, VACUALITE_ORE) < REQUIRED_COUNT) && (getRandom(1000) < MONSTERS.get(npc.getId()))) {
				giveItems(member, VACUALITE_ORE, 1);
				if (getQuestItemsCount(member, VACUALITE_ORE) >= REQUIRED_COUNT) {
					qs.setCond(3, true);
				} else {
					playSound(member, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}
		return super.onKill(npc, player, isPet);
	}

	@Override
	public String onTalk(Npc npc, PlayerInstance player) {
		String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);
		if (st == null) {
			return htmltext;
		}
		switch (npc.getId()) {
			case KLEIN: {
				if (st.isCreated()) {
					htmltext = (player.getLevel() < MIN_LEVEL) ? "31540-01.html" : "31540-02.htm";
				} else if (st.isStarted()) {
					htmltext = st.isCond(4) ? "31540-04.html" : "31540-03.htm";
				}
				break;
			}
			case HILDA: {
				switch (st.getCond()) {
					case 1:
						htmltext = "31271-01.html";
						break;
					case 2:
						htmltext = "31271-03.html";
						break;
					case 3:
						htmltext = "31271-04.html";
						break;
					case 4:
						htmltext = "31271-06.html";
						break;
				}
				break;
			}
		}
		return htmltext;
	}
}