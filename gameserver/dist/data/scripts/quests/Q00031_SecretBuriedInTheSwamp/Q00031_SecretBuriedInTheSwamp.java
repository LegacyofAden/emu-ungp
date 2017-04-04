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
package quests.Q00031_SecretBuriedInTheSwamp;

import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

import java.util.Arrays;
import java.util.List;

/**
 * Secret Buried in the Swamp (31)
 *
 * @author janiko, Gladicek
 */
public final class Q00031_SecretBuriedInTheSwamp extends Quest {
	// NPCs
	private static final int ABERCROMBIE = 31555;
	private static final int FORGOTTEN_MONUMENT_1 = 31661;
	private static final int FORGOTTEN_MONUMENT_2 = 31662;
	private static final int FORGOTTEN_MONUMENT_3 = 31663;
	private static final int FORGOTTEN_MONUMENT_4 = 31664;
	private static final int CORPSE_OF_DWARF = 31665;
	// Items
	private static final int KRORINS_JOURNAL = 7252;
	// Misc
	private static final int MIN_LVL = 66;
	// Monuments
	private static final List<Integer> MONUMENTS = Arrays.asList(FORGOTTEN_MONUMENT_1, FORGOTTEN_MONUMENT_2, FORGOTTEN_MONUMENT_3, FORGOTTEN_MONUMENT_4);

	public Q00031_SecretBuriedInTheSwamp() {
		super(31);
		addStartNpc(ABERCROMBIE);
		addTalkId(ABERCROMBIE, CORPSE_OF_DWARF);
		addTalkId(MONUMENTS);
		registerQuestItems(KRORINS_JOURNAL);
		addCondMinLevel(MIN_LVL, "31555-03.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState st = getQuestState(player, false);
		String htmltext = null;
		if (st == null) {
			return htmltext;
		}

		switch (event) {
			case "31555-02.html": {
				st.startQuest();
				htmltext = event;
				break;
			}
			case "31665-02.html": {
				if (st.isCond(1)) {
					st.setCond(2, true);
					giveItems(player, KRORINS_JOURNAL, 1);
					htmltext = event;
				}
				break;
			}
			case "31555-05.html": {
				if (st.isCond(2) && hasQuestItems(player, KRORINS_JOURNAL)) {
					takeItems(player, KRORINS_JOURNAL, -1);
					st.setCond(3, true);
					htmltext = event;
				}
				break;
			}
			case "31661-02.html":
			case "31662-02.html":
			case "31663-02.html":
			case "31664-02.html": {
				if (MONUMENTS.contains(npc.getId()) && st.isCond(MONUMENTS.indexOf(npc.getId()) + 3)) {
					st.setCond(st.getCond() + 1, true);
					htmltext = event;
				}
				break;
			}
			case "31555-08.html": {
				if (st.isCond(7)) {
					if (player.getLevel() >= MIN_LVL) {
						giveAdena(player, 343_430, true);
						addExp(player, 1_650_970);
						addSp(player, 396);
						st.exitQuest(false, true);
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
		final QuestState st = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);

		switch (st.getState()) {
			case State.CREATED: {
				if (npc.getId() == ABERCROMBIE) {
					htmltext = "31555-03.htm";
				}
				break;
			}
			case State.STARTED: {
				switch (npc.getId()) {
					case ABERCROMBIE: {
						switch (st.getCond()) {
							case 1: {
								htmltext = "31555-02.html";
								break;
							}
							case 2: {
								if (hasQuestItems(player, KRORINS_JOURNAL)) {
									htmltext = "31555-04.html";
								}
								break;
							}
							case 3: {
								htmltext = "31555-06.html";
								break;
							}
							case 7: {
								htmltext = "31555-07.html";
								break;
							}
						}
						break;
					}
					case CORPSE_OF_DWARF: {
						if (st.isCond(1)) {
							htmltext = "31665-01.html";
						} else if (st.isCond(2)) {
							htmltext = "31665-03.html";
						}
						break;
					}
					case FORGOTTEN_MONUMENT_1:
					case FORGOTTEN_MONUMENT_2:
					case FORGOTTEN_MONUMENT_3:
					case FORGOTTEN_MONUMENT_4: {
						final int loc = MONUMENTS.indexOf(npc.getId()) + 3;
						if (st.isCond(loc)) {
							htmltext = npc.getId() + "-01.html";
						} else if (st.isCond(loc + 1)) {
							htmltext = npc.getId() + "-03.html";
						}
						break;
					}
				}
			}
			case State.COMPLETED: {
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
}
