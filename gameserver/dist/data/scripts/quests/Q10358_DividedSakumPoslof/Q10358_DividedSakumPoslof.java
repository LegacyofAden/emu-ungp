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
package quests.Q10358_DividedSakumPoslof;

import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.NpcLogListHolder;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import quests.Q10337_SakumsImpact.Q10337_SakumsImpact;

import java.util.HashSet;
import java.util.Set;

/**
 * Divided Sakum, Poslof (10358)
 *
 * @author St3eT
 */
public final class Q10358_DividedSakumPoslof extends Quest {
	// NPCs
	private static final int LEF = 33510;
	private static final int ADVENTURER_GUIDE = 31795;
	private static final int ZOMBIE_WARRIOR = 20458;
	private static final int VEELEAN = 20402; // Veelan Bugbear Warrior
	private static final int POSLOF = 27452;
	// Items
	private static final int SAKUM_SKETCH = 17585;
	// Misc
	private static final int MIN_LEVEL = 33;
	private static final int MAX_LEVEL = 40;

	public Q10358_DividedSakumPoslof() {
		super(10358);
		addStartNpc(LEF);
		addTalkId(LEF, ADVENTURER_GUIDE);
		addKillId(ZOMBIE_WARRIOR, VEELEAN, POSLOF);
		registerQuestItems(SAKUM_SKETCH);
		addCondCompletedQuest(Q10337_SakumsImpact.class.getSimpleName(), "33510-09.htm");
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "33510-09.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState st = getQuestState(player, false);
		if (st == null) {
			return null;
		}

		String htmltext = null;
		switch (event) {
			case "33510-02.htm":
			case "31795-04.htm": {
				htmltext = event;
				break;
			}
			case "33510-03.htm": {
				st.startQuest();
				htmltext = event;
				break;
			}
			case "31795-05.htm": {
				if (st.isCond(4)) {
					if ((player.getLevel() >= MIN_LEVEL)) {
						addExp(player, 750_000);
						addSp(player, 180);
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
	public String onTalk(Npc npc, Player player, boolean isSimulated) {
		String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);

		switch (st.getState()) {
			case State.CREATED: {
				htmltext = npc.getId() == LEF ? "33510-01.htm" : "31795-02.htm";
				break;
			}
			case State.STARTED: {
				switch (st.getCond()) {
					case 1: {
						htmltext = npc.getId() == LEF ? "33510-04.htm" : "31795-01.htm";
						break;
					}
					case 2: {
						if (npc.getId() == LEF) {
							if (!isSimulated) {
								st.setCond(3);
								giveItems(player, SAKUM_SKETCH, 1);
							}
							htmltext = "33510-05.htm";
						} else if (npc.getId() == ADVENTURER_GUIDE) {
							htmltext = "31795-01.htm";
						}
						break;
					}
					case 3: {
						htmltext = npc.getId() == LEF ? "33510-06.htm" : "31795-01.htm";
						break;
					}
					case 4: {
						htmltext = npc.getId() == LEF ? "33510-07.htm" : "31795-03.htm";
						break;
					}
				}
				break;
			}
			case State.COMPLETED: {
				htmltext = npc.getId() == LEF ? "33510-08.htm" : "31795-06.htm";
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon) {
		final QuestState st = getQuestState(killer, false);

		if ((st != null) && st.isStarted()) {
			if (st.isCond(1)) {
				int killedZombies = st.getInt("killed_" + ZOMBIE_WARRIOR);
				int killedVeelans = st.getInt("killed_" + VEELEAN);

				if (npc.getId() == ZOMBIE_WARRIOR) {
					if (killedZombies < 20) {
						killedZombies++;
						st.set("killed_" + ZOMBIE_WARRIOR, killedZombies);
						playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
				} else {
					if (killedVeelans < 23) {
						killedVeelans++;
						st.set("killed_" + VEELEAN, killedVeelans);
						playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
					}
				}

				if ((killedZombies == 20) && (killedVeelans == 23)) {
					st.setCond(2, true);
				}
				sendNpcLogList(killer);
			} else if (st.isCond(3)) {
				st.set("killed_" + POSLOF, 1);
				st.setCond(4);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}

	@Override
	public Set<NpcLogListHolder> getNpcLogList(Player activeChar) {
		final QuestState st = getQuestState(activeChar, false);
		if ((st != null) && st.isStarted()) {
			if (st.isCond(1)) {
				final Set<NpcLogListHolder> npcLogList = new HashSet<>(2);
				npcLogList.add(new NpcLogListHolder(ZOMBIE_WARRIOR, false, st.getInt("killed_" + ZOMBIE_WARRIOR)));
				npcLogList.add(new NpcLogListHolder(VEELEAN, false, st.getInt("killed_" + VEELEAN)));
				return npcLogList;
			} else if (st.isCond(3)) {
				final Set<NpcLogListHolder> npcLogList = new HashSet<>(1);
				npcLogList.add(new NpcLogListHolder(POSLOF, false, st.getInt("killed_" + POSLOF)));
				return npcLogList;
			}
		}
		return super.getNpcLogList(activeChar);
	}
}