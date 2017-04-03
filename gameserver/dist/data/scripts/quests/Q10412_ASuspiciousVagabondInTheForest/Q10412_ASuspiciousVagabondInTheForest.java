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
package quests.Q10412_ASuspiciousVagabondInTheForest;

import org.l2junity.gameserver.enums.CategoryType;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

/**
 * A Suspicious Vagabond in the Forest (10412)
 *
 * @author St3eT
 */
public final class Q10412_ASuspiciousVagabondInTheForest extends Quest {
	// NPCs
	private static final int HATUBA = 33849;
	private static final int VAGABOND = 33850; // Suspicious Vagabond Mortally Endangered
	// Items
	private static final int EAA = 730; // Scroll: Enchant Armor (A-grade)
	// Misc
	private static final int MIN_LEVEL = 65;
	private static final int MAX_LEVEL = 70;

	public Q10412_ASuspiciousVagabondInTheForest() {
		super(10412);
		addStartNpc(HATUBA);
		addTalkId(HATUBA, VAGABOND);
		addCondNotRace(Race.ERTHEIA, "33849-08.html");
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "33849-09.htm");
		addCondInCategory(CategoryType.MAGE_GROUP, "33849-09.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState st = getQuestState(player, false);
		if (st == null) {
			return null;
		}

		String htmltext = null;
		switch (event) {
			case "33849-02.htm":
			case "33849-03.htm": {
				htmltext = event;
				break;
			}
			case "33849-04.htm": {
				st.startQuest();
				htmltext = event;
				break;
			}
			case "33849-07.html": {
				if (st.isCond(2)) {
					st.exitQuest(false, true);
					giveItems(player, EAA, 3);
					giveStoryQuestReward(npc, player);
					if (player.getLevel() >= MIN_LEVEL) {
						addExp(player, 942_690);
						addSp(player, 226);
					}
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player, boolean isSimulated) {
		final QuestState st = getQuestState(player, true);
		String htmltext = null;

		switch (st.getState()) {
			case State.CREATED: {
				if (npc.getId() == HATUBA) {
					htmltext = "33849-01.htm";
				}
				break;
			}
			case State.STARTED: {
				if (npc.getId() == HATUBA) {
					htmltext = st.isCond(1) ? "33849-05.html" : "33849-06.html";
				} else if ((npc.getId() == VAGABOND) && st.isCond(1)) {
					if (!isSimulated) {
						st.setCond(2, true);
					}
					htmltext = "33850-01.html";
				}
				break;
			}
			case State.COMPLETED: {
				if (npc.getId() == HATUBA) {
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
		}
		return htmltext;
	}
}