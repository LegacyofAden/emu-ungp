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
package quests.Q10417_DaimonTheWhiteEyed;

import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import quests.Q10416_InSearchOfTheEyeOfArgos.Q10416_InSearchOfTheEyeOfArgos;

/**
 * Daimon the White-eyed (10417)
 *
 * @author St3eT
 */
public final class Q10417_DaimonTheWhiteEyed extends Quest {
	// NPCs
	private static final int EYE_OF_ARGOS = 31683;
	private static final int JANITT = 33851;
	private static final int DAIMON_THE_WHITEEYED = 27499;
	// Items
	private static final int EAA = 730; // Scroll: Enchant Armor (A-grade)
	// Misc
	private static final int MIN_LEVEL = 70;
	private static final int MAX_LEVEL = 75;

	public Q10417_DaimonTheWhiteEyed() {
		super(10417);
		addStartNpc(EYE_OF_ARGOS);
		addTalkId(EYE_OF_ARGOS, JANITT);
		addKillId(DAIMON_THE_WHITEEYED);
		addCondNotRace(Race.ERTHEIA, "31683-09.html");
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "31683-08.htm");
		addCondCompletedQuest(Q10416_InSearchOfTheEyeOfArgos.class.getSimpleName(), "31683-08.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState st = getQuestState(player, false);
		if (st == null) {
			return null;
		}

		String htmltext = null;
		switch (event) {
			case "31683-02.htm":
			case "31683-03.htm": {
				htmltext = event;
				break;
			}
			case "31683-04.htm": {
				st.startQuest();
				htmltext = event;
				break;
			}
			case "31683-07.html": {
				if (st.isCond(2)) {
					st.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "31683-03.html": {
				if (st.isCond(3)) {
					st.exitQuest(false, true);
					giveItems(player, EAA, 5);
					giveStoryQuestReward(npc, player);
					if (player.getLevel() > MIN_LEVEL) {
						addExp(player, 2_721_600);
						addSp(player, 653);
					}
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player) {
		final QuestState st = getQuestState(player, true);
		String htmltext = null;

		if (st.getState() == State.CREATED) {
			if (npc.getId() == EYE_OF_ARGOS) {
				htmltext = "31683-01.htm";
			}
		} else if (st.getState() == State.STARTED) {
			switch (st.getCond()) {
				case 1:
					htmltext = npc.getId() == EYE_OF_ARGOS ? "31683-05.html" : "33851-01.html";
					break;
				case 2:
					htmltext = npc.getId() == EYE_OF_ARGOS ? "31683-06.html" : "33851-01.html";
					break;
				case 3:
					htmltext = npc.getId() == EYE_OF_ARGOS ? "31683-07.html" : "33851-02.html";
					break;
			}
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon) {
		final QuestState st = getQuestState(killer, false);

		if ((st != null) && st.isCond(1)) {
			st.setCond(2, true);
		}
		return super.onKill(npc, killer, isSummon);
	}
}