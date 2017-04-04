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
package quests.Q00040_ASpecialOrder;

import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

/**
 * A Special Order (40)
 *
 * @author St3eT
 */
public final class Q00040_ASpecialOrder extends Quest {
	// NPCs
	private static final int HELVETIA = 30081;
	private static final int O_FULLE = 31572;
	private static final int GESTO = 30511;
	// Items
	private static final int FISH_CHEST = 12764;
	private static final int SEED_JAR = 12765;
	private static final int ELCYUM_CRYSTAL = 36514;
	private static final int WONDEROUS_CUBIC = 10632;
	// Misc
	private static final int MIN_LEVEL = 40;

	public Q00040_ASpecialOrder() {
		super(40);
		addStartNpc(HELVETIA);
		addTalkId(HELVETIA, O_FULLE, GESTO);
		registerQuestItems(FISH_CHEST, SEED_JAR);
		addCondMinLevel(MIN_LEVEL, "30081-10.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState st = getQuestState(player, false);
		if (st == null) {
			return null;
		}

		String htmltext = null;
		switch (event) {
			case "30081-05.html": {
				htmltext = event;
				break;
			}
			case "30081-02.htm": {
				st.startQuest();
				htmltext = event;
				break;
			}
			case "31572-02.html": {
				if (st.isCond(1)) {
					st.setCond(2, true);
					giveItems(player, FISH_CHEST, 1);
					htmltext = event;
				}
				break;
			}
			case "30511-02.html": {
				if (st.isCond(2)) {
					st.setCond(3, true);
					giveItems(player, SEED_JAR, 1);
					htmltext = event;
				}
				break;
			}
			case "30081-06.html": {
				if (st.isCond(3)) {
					st.setCond(4, true);
					takeItems(player, -1, FISH_CHEST, SEED_JAR);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player, boolean isSimulated) {
		String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);

		switch (st.getState()) {
			case State.CREATED: {
				if (npc.getId() == HELVETIA) {
					htmltext = "30081-01.htm";
				}
				break;
			}
			case State.STARTED: {
				switch (st.getCond()) {
					case 1: {
						if (npc.getId() == HELVETIA) {
							htmltext = "30081-03.html";
						} else if (npc.getId() == O_FULLE) {
							htmltext = "31572-01.html";
						}
						break;
					}
					case 2: {
						switch (npc.getId()) {
							case HELVETIA:
								htmltext = "30081-03.html";
								break;
							case O_FULLE:
								htmltext = "31572-03.html";
								break;
							case GESTO:
								htmltext = "30511-01.html";
								break;
						}
						break;
					}
					case 3: {
						if (npc.getId() == HELVETIA) {
							htmltext = "30081-04.html";
						} else if (npc.getId() == GESTO) {
							htmltext = "30511-03.html";
						}
						break;
					}
					case 4: {
						if (npc.getId() == HELVETIA) {
							if (hasQuestItems(player, ELCYUM_CRYSTAL)) {
								if (!isSimulated) {
									if ((player.getLevel() >= MIN_LEVEL)) {
										takeItems(player, ELCYUM_CRYSTAL, 1);
										giveItems(player, WONDEROUS_CUBIC, 1);
										st.exitQuest(false, true);
									} else {
										htmltext = getNoQuestLevelRewardMsg(player);
										break;
									}
								}
								htmltext = "30081-08.html";
							} else {
								htmltext = "30081-07.html";
							}
						}
						break;
					}
				}
				break;
			}
			case State.COMPLETED: {
				if (npc.getId() == HELVETIA) {
					htmltext = getAlreadyCompletedMsg(player);
				}
				break;
			}
		}
		return htmltext;
	}
}