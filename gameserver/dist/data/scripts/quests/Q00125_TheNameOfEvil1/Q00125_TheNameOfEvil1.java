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
package quests.Q00125_TheNameOfEvil1;

import org.l2junity.core.configs.RatesConfig;
import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.client.send.MagicSkillUse;
import quests.Q00124_MeetingTheElroki.Q00124_MeetingTheElroki;

import java.util.HashMap;
import java.util.Map;

/**
 * The Name of Evil - 1 (125)
 *
 * @author Adry_85, Gladicek
 */
public class Q00125_TheNameOfEvil1 extends Quest {
	// NPCs
	private static final int MUSHIKA = 32114;
	private static final int KARAKAWEI = 32117;
	private static final int ULU_KAIMU = 32119;
	private static final int BALU_KAIMU = 32120;
	private static final int CHUTA_KAIMU = 32121;
	// Items
	private static final int ORNITHOMIMUS_CLAW = 8779;
	private static final int DEINONYCHUS_BONE = 8780;
	private static final int EPITAPH_OF_WISDOM = 8781;
	private static final int GAZKH_FRAGMENT = 8782;
	// Misc
	private static final int MIN_LEVEL = 76;

	private static final Map<Integer, Integer> ORNITHOMIMUS = new HashMap<>();
	private static final Map<Integer, Integer> DEINONYCHUS = new HashMap<>();

	static {
		ORNITHOMIMUS.put(22200, 661);
		ORNITHOMIMUS.put(22201, 330);
		ORNITHOMIMUS.put(22202, 661);
		ORNITHOMIMUS.put(22219, 327);
		ORNITHOMIMUS.put(22224, 327);
		DEINONYCHUS.put(22203, 651);
		DEINONYCHUS.put(22204, 326);
		DEINONYCHUS.put(22205, 651);
		DEINONYCHUS.put(22220, 319);
		DEINONYCHUS.put(22225, 319);
	}

	public Q00125_TheNameOfEvil1() {
		super(125);
		addStartNpc(MUSHIKA);
		addTalkId(MUSHIKA, KARAKAWEI, ULU_KAIMU, BALU_KAIMU, CHUTA_KAIMU);
		addKillId(ORNITHOMIMUS.keySet());
		addKillId(DEINONYCHUS.keySet());
		addCondMinLevel(MIN_LEVEL, "32114-01a.htm");
		addCondCompletedQuest(Q00124_MeetingTheElroki.class.getSimpleName(), "32114-01b.htm");
		registerQuestItems(ORNITHOMIMUS_CLAW, DEINONYCHUS_BONE, EPITAPH_OF_WISDOM, GAZKH_FRAGMENT);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		QuestState st = getQuestState(player, false);
		if (st == null) {
			return getNoQuestMsg(player);
		}

		String htmltext = event;
		switch (event) {
			case "32114-05.html": {
				st.startQuest();
				break;
			}
			case "32114-08.html": {
				if (st.isCond(1)) {
					giveItems(player, GAZKH_FRAGMENT, 1);
					st.setCond(2, true);
				}
				break;
			}
			case "32117-09.html": {
				if (st.isCond(2)) {
					st.setCond(3, true);
				}
				break;
			}
			case "32117-15.html": {
				if (st.isCond(4)) {
					st.setCond(5, true);
				}
				break;
			}
			case "T_One": {
				st.set("T", "1");
				htmltext = "32119-04.html";
				break;
			}
			case "E_One": {
				st.set("E", "1");
				htmltext = "32119-05.html";
				break;
			}
			case "P_One": {
				st.set("P", "1");
				htmltext = "32119-06.html";
				break;
			}
			case "U_One": {
				st.set("U", "1");
				if (st.isCond(5) && (st.getInt("T") > 0) && (st.getInt("E") > 0) && (st.getInt("P") > 0) && (st.getInt("U") > 0)) {
					htmltext = "32119-08.html";
					st.setMemoState(1);
				} else {
					st.unset("T");
					st.unset("E");
					st.unset("P");
					st.unset("U");
					htmltext = "32119-07.html";
				}
				break;
			}
			case "32119-07.html": {
				st.unset("T");
				st.unset("E");
				st.unset("P");
				st.unset("U");
				break;
			}
			case "32119-18.html": {
				if (st.isCond(5)) {
					st.setCond(6, true);
					st.setMemoState(0);
				}
				break;
			}
			case "T_Two": {
				st.set("T", "1");
				htmltext = "32120-04.html";
				break;
			}
			case "O_Two":
				st.set("O", "1");
				htmltext = "32120-05.html";
				break;
			case "O2_Two": {
				st.set("O2", "1");
				htmltext = "32120-06.html";
				break;
			}
			case "N_Two": {
				st.set("N", "1");
				if (st.isCond(6) && (st.getInt("T") > 0) && (st.getInt("O") > 0) && (st.getInt("O2") > 0) && (st.getInt("N") > 0)) {
					htmltext = "32120-08.html";
					st.setMemoState(1);
				} else {
					st.unset("T");
					st.unset("O");
					st.unset("O2");
					st.unset("N");
					htmltext = "32120-07.html";
				}
				break;
			}
			case "32120-07.html": {
				st.unset("T");
				st.unset("O");
				st.unset("O2");
				st.unset("N");
				break;
			}
			case "32120-17.html": {
				if (st.isCond(6)) {
					st.setCond(7, true);
					st.setMemoState(0);
				}
				break;
			}
			case "W_Three": {
				st.set("W", "1");
				htmltext = "32121-04.html";
				break;
			}
			case "A_Three": {
				st.set("A", "1");
				htmltext = "32121-05.html";
				break;
			}
			case "G_Three": {
				st.set("G", "1");
				htmltext = "32121-06.html";
				break;
			}
			case "U_Three": {
				st.set("U", "1");
				if (st.isCond(7) && (st.getInt("W") > 0) && (st.getInt("A") > 0) && (st.getInt("G") > 0) && (st.getInt("U") > 0)) {
					htmltext = "32121-08.html";
					st.setMemoState(1);
				} else {
					st.unset("W");
					st.unset("A");
					st.unset("G");
					st.unset("U");
					htmltext = "32121-07.html";
				}
				break;
			}
			case "32121-07.html": {
				st.unset("W");
				st.unset("A");
				st.unset("G");
				st.unset("U");
				break;
			}
			case "32121-11.html": {
				st.setMemoState(2);
				break;
			}
			case "32121-16.html": {
				st.setMemoState(3);
				break;
			}
			case "32121-18.html": {
				if (st.isCond(7) && hasQuestItems(player, GAZKH_FRAGMENT)) {
					giveItems(player, EPITAPH_OF_WISDOM, 1);
					takeItems(player, GAZKH_FRAGMENT, -1);
					st.setCond(8, true);
					st.setMemoState(0);
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, Player player, boolean isSummon) {
		final Player partyMember = getRandomPartyMember(player, 3);
		if (partyMember == null) {
			return null;
		}

		final QuestState st = getQuestState(partyMember, false);
		int npcId = npc.getId();
		if (ORNITHOMIMUS.containsKey(npcId)) {
			if (getQuestItemsCount(player, ORNITHOMIMUS_CLAW) < 2) {
				float chance = ORNITHOMIMUS.get(npcId) * RatesConfig.RATE_QUEST_DROP;
				if (getRandom(1000) < chance) {
					giveItems(player, ORNITHOMIMUS_CLAW, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		} else if (DEINONYCHUS.containsKey(npcId)) {
			if (getQuestItemsCount(player, DEINONYCHUS_BONE) < 2) {
				float chance = DEINONYCHUS.get(npcId) * RatesConfig.RATE_QUEST_DROP;
				if (getRandom(1000) < chance) {
					giveItems(player, DEINONYCHUS_BONE, 1);
					playSound(player, QuestSound.ITEMSOUND_QUEST_ITEMGET);
				}
			}
		}

		if ((getQuestItemsCount(player, ORNITHOMIMUS_CLAW) == 2) && (getQuestItemsCount(player, DEINONYCHUS_BONE) == 2)) {
			st.setCond(4, true);
		}
		return super.onKill(npc, player, isSummon);
	}

	@Override
	public String onTalk(Npc npc, Player player, boolean isSimulated) {
		String htmltext = getNoQuestMsg(player);
		QuestState st = getQuestState(player, true);
		if (st == null) {
			return htmltext;
		}

		switch (st.getState()) {
			case State.CREATED: {
				if (npc.getId() == MUSHIKA) {
					htmltext = "32114-01.htm";
				}
				break;
			}
			case State.STARTED: {
				switch (npc.getId()) {
					case MUSHIKA: {
						switch (st.getCond()) {
							case 1:
								htmltext = "32114-09.html";
								break;
							case 2:
								htmltext = "32114-10.html";
								break;
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
								htmltext = "32114-11.html";
								break;
							case 8: {
								if (hasQuestItems(player, EPITAPH_OF_WISDOM)) {
									if (player.getLevel() >= MIN_LEVEL) {
										addExp(player, 898_056);
										addSp(player, 215);
										htmltext = "32114-12.html";
										st.exitQuest(false, true);
									} else {
										htmltext = getNoQuestLevelRewardMsg(player);
									}
								}
								break;
							}
						}
						break;
					}
					case KARAKAWEI: {
						switch (st.getCond()) {
							case 1:
								htmltext = "32117-01.html";
								break;
							case 2:
								htmltext = "32117-02.html";
								break;
							case 3:
								htmltext = "32117-10.html";
								break;
							case 4: {
								if ((getQuestItemsCount(player, ORNITHOMIMUS_CLAW) >= 2) && (getQuestItemsCount(player, DEINONYCHUS_BONE) >= 2)) {
									takeItems(player, ORNITHOMIMUS_CLAW, -1);
									takeItems(player, DEINONYCHUS_BONE, -1);
									htmltext = "32117-11.html";
								}
								break;
							}
							case 5:
								htmltext = "32117-16.html";
								break;
							case 6:
							case 7:
								htmltext = "32117-17.html";
								break;
							case 8:
								htmltext = "32117-18.html";
								break;
						}
						break;
					}
					case ULU_KAIMU: {
						switch (st.getCond()) {
							case 1:
							case 2:
							case 3:
							case 4:
								htmltext = "32119-01.html";
								break;
							case 5: {
								if (!isSimulated) {
									if (st.getMemoState() == 0) {
										htmltext = "32119-02.html";
										npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
										st.unset("T");
										st.unset("E");
										st.unset("P");
										st.unset("U");
									} else {
										htmltext = "32119-09.html";
									}
									break;
								}
							}
							case 6:
								htmltext = "32119-18.html";
								break;
							default:
								htmltext = "32119-19.html";
								break;
						}
					}
					case BALU_KAIMU: {
						switch (st.getCond()) {
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
								htmltext = "32120-01.html";
								break;
							case 6: {
								if (!isSimulated) {
									if (st.getMemoState() == 0) {
										htmltext = "32120-02.html";
										npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
										st.unset("T");
										st.unset("O");
										st.unset("O2");
										st.unset("N");
									} else {
										htmltext = "32120-09.html";
									}
								}
								break;
							}
							case 7:
								htmltext = "32120-17.html";
								break;
							default:
								htmltext = "32119-18.html";
								break;
						}
						break;
					}
					case CHUTA_KAIMU: {
						switch (st.getCond()) {
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
								htmltext = "32121-01.html";
								break;
							case 7: {
								switch (st.getMemoState()) {
									case 0: {
										if (!isSimulated) {
											npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
											st.unset("W");
											st.unset("A");
											st.unset("G");
											st.unset("U");
										}
										htmltext = "32121-02.html";
										break;
									}
									case 1:
										htmltext = "32121-09.html";
										break;
									case 2:
										htmltext = "32121-19.html";
										break;
									case 3:
										htmltext = "32121-20.html";
										break;
								}
								break;
							}
							case 8:
								htmltext = "32121-21.html";
								break;
						}
						break;
					}
				}
				break;
			}
			case State.COMPLETED: {
				htmltext = getAlreadyCompletedMsg(player);
				break;
			}
		}
		return htmltext;
	}
}
