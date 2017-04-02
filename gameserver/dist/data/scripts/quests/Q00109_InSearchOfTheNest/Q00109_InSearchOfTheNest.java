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
package quests.Q00109_InSearchOfTheNest;

import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

/**
 * In Search of the Nest (109)
 *
 * @author Adry_85, Gladicek
 */
public class Q00109_InSearchOfTheNest extends Quest {
	// NPCs
	private static final int PIERCE = 31553;
	private static final int SCOUTS_CORPSE = 32015;
	private static final int KAHMAN = 31554;
	// Items
	private static final int SCOUTS_NOTE = 14858;
	// Misc
	private static final int MIN_LEVEL = 81;

	public Q00109_InSearchOfTheNest() {
		super(109);
		addStartNpc(PIERCE);
		addTalkId(PIERCE, SCOUTS_CORPSE, KAHMAN);
		addCondMinLevel(MIN_LEVEL, "31553-04.htm");
		registerQuestItems(SCOUTS_NOTE);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player) {
		QuestState st = getQuestState(player, false);
		if (st == null) {
			return getNoQuestMsg(player);
		}

		String htmltext = null;

		switch (event) {
			case "31553-02.html": {
				st.startQuest();
				break;
			}
			case "32015-02.html": {
				if (st.isCond(1)) {
					giveItems(player, SCOUTS_NOTE, 1);
					st.setCond(2, true);
				}
				break;
			}
			case "31553-06.html": {
				if (st.isCond(2)) {
					takeItems(player, SCOUTS_NOTE, -1);
					st.setCond(3, true);
				}
				break;
			}
			case "31554-02.html": {
				if (st.isCond(3)) {
					giveAdena(player, 900_990, true);
					addExp(player, 8_550_000);
					addSp(player, 2052);
					st.exitQuest(false, true);
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, PlayerInstance player) {
		String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);
		if (st == null) {
			return htmltext;
		}

		switch (st.getState()) {
			case State.CREATED: {
				if (npc.getId() == PIERCE) {
					htmltext = "31553-01.htm";
				}
				break;
			}
			case State.STARTED: {
				switch (npc.getId()) {
					case PIERCE: {
						switch (st.getCond()) {
							case 1:
								htmltext = "31553-03.html";
								break;
							case 2:
								htmltext = "31553-05.html";
								break;
							case 3:
								htmltext = "31553-07.html";
								break;
						}
						break;
					}
					case SCOUTS_CORPSE: {
						if (st.isCond(1)) {
							htmltext = "32015-01.html";
						} else if (st.isCond(2)) {
							htmltext = "32015-03.html";
						}
						break;
					}
					case KAHMAN: {
						if (st.isCond(3)) {
							htmltext = "31554-01.html";
						}
						break;
					}
				}
				break;
			}
			case State.COMPLETED:
				htmltext = getAlreadyCompletedMsg(player);
				break;
		}
		return htmltext;
	}
}
