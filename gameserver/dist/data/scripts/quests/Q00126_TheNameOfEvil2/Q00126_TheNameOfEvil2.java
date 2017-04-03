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
package quests.Q00126_TheNameOfEvil2;

import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.client.send.MagicSkillUse;
import quests.Q00125_TheNameOfEvil1.Q00125_TheNameOfEvil1;

/**
 * The Name of Evil - 2 (126)
 *
 * @author Adry_85, Gladicek
 */
public class Q00126_TheNameOfEvil2 extends Quest {
	// NPCs
	private static final int SHILENS_STONE_STATUE = 32109;
	private static final int MUSHIKA = 32114;
	private static final int ASAMAH = 32115;
	private static final int ULU_KAIMU = 32119;
	private static final int BALU_KAIMU = 32120;
	private static final int CHUTA_KAIMU = 32121;
	private static final int WARRIORS_GRAVE = 32122;
	// Items
	private static final int GAZKH_FRAGMENT = 8782;
	private static final int BONE_POWDER = 8783;
	// Reward
	private static final int ENCHANT_WEAPON_A = 729;
	// Misc
	private static final int MIN_LEVEL = 77;

	public Q00126_TheNameOfEvil2() {
		super(126);
		addStartNpc(ASAMAH);
		addTalkId(ASAMAH, ULU_KAIMU, BALU_KAIMU, CHUTA_KAIMU, WARRIORS_GRAVE, SHILENS_STONE_STATUE, MUSHIKA);
		addCondMinLevel(MIN_LEVEL, "32115-0.htm");
		addCondCompletedQuest(Q00125_TheNameOfEvil1.class.getSimpleName(), "32115-0b.htm");
		registerQuestItems(GAZKH_FRAGMENT, BONE_POWDER);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		String htmltext = event;

		QuestState st = getQuestState(player, false);
		if (st == null) {
			return getNoQuestMsg(player);
		}

		switch (event) {
			case "32115-1.html": {
				st.startQuest();
				break;
			}
			case "32115-1b.html": {
				if (st.isCond(1)) {
					st.setCond(2, true);
				}
				break;
			}
			case "32119-3.html": {
				if (st.isCond(2)) {
					st.setCond(3, true);
				}
				break;
			}
			case "32119-4.html": {
				if (st.isCond(3)) {
					st.setCond(4, true);
				}
				break;
			}
			case "32119-4a.html":
			case "32119-5b.html": {
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_1ST);
				break;
			}
			case "32119-5.html": {
				if (st.isCond(4)) {
					st.setCond(5, true);
				}
				break;
			}
			case "32120-3.html": {
				if (st.isCond(5)) {
					st.setCond(6, true);
				}
				break;
			}
			case "32120-4.html": {
				if (st.isCond(6)) {
					st.setCond(7, true);
				}
				break;
			}
			case "32120-4a.html":
			case "32120-5b.html": {
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_2ND);
				break;
			}
			case "32120-5.html": {
				if (st.isCond(7)) {
					st.setCond(8, true);
				}
				break;
			}
			case "32121-3.html": {
				if (st.isCond(8)) {
					st.setCond(9, true);
				}
				break;
			}
			case "32121-4.html": {
				if (st.isCond(9)) {
					st.setCond(10, true);
				}
				break;
			}
			case "32121-4a.html":
			case "32121-5b.html": {
				playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_3RD);
				break;
			}
			case "32121-5.html": {
				if (st.isCond(10)) {
					giveItems(player, GAZKH_FRAGMENT, 1);
					st.setCond(11, true);
				}
				break;
			}
			case "32122-2a.html": {
				npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
				break;
			}
			case "32122-2d.html": {
				if ((st.isCond(11) & hasAtLeastOneQuestItem(player, GAZKH_FRAGMENT))) {
					takeItems(player, GAZKH_FRAGMENT, -1);
				}
				break;
			}
			case "32122-3.html": {
				if (st.isCond(12)) {
					st.setCond(13, true);
				}
				break;
			}
			case "32122-4.html": {
				if (st.isCond(13)) {
					st.setCond(14, true);
				}
				break;
			}
			case "DO_One": {
				st.set("DO", "1");
				htmltext = "32122-4d.html";
				break;
			}
			case "MI_One": {
				st.set("MI", "1");
				htmltext = "32122-4f.html";
				break;
			}
			case "FA_One": {
				st.set("FA", "1");
				htmltext = "32122-4h.html";
				break;
			}
			case "SOL_One": {
				st.set("SOL", "1");
				htmltext = "32122-4j.html";
				break;
			}
			case "FA2_One": {
				st.set("FA2", "1");
				if (st.isCond(14) && (st.getInt("DO") > 0) && (st.getInt("MI") > 0) && (st.getInt("FA") > 0) && (st.getInt("SOL") > 0) && (st.getInt("FA2") > 0)) {
					htmltext = "32122-4n.html";
					st.setCond(15, true);
				} else {
					st.unset("DO");
					st.unset("MI");
					st.unset("FA");
					st.unset("SOL");
					st.unset("FA2");
					htmltext = "32122-4m.html";
				}
				break;
			}
			case "32122-4m.html": {
				st.unset("DO");
				st.unset("MI");
				st.unset("FA");
				st.unset("SOL");
				st.unset("FA2");
				break;
			}
			case "FA_Two": {
				st.set("FA", "1");
				htmltext = "32122-5a.html";
				break;
			}
			case "SOL_Two": {
				st.set("SOL", "1");
				htmltext = "32122-5c.html";
				break;
			}
			case "TI_Two": {
				st.set("TI", "1");
				htmltext = "32122-5e.html";
				break;
			}
			case "SOL2_Two": {
				st.set("SOL2", "1");
				htmltext = "32122-5g.html";
				break;
			}
			case "FA2_Two": {
				st.set("FA2", "1");
				if (st.isCond(15) && (st.getInt("FA") > 0) && (st.getInt("SOL") > 0) && (st.getInt("TI") > 0) && (st.getInt("SOL2") > 0) && (st.getInt("FA2") > 0)) {
					htmltext = "32122-5j.html";
					st.setCond(16, true);
				} else {
					st.unset("FA");
					st.unset("SOL");
					st.unset("TI");
					st.unset("SOL2");
					st.unset("FA2");
					htmltext = "32122-5i.html";
				}
				break;
			}
			case "32122-5i.html": {
				st.unset("FA");
				st.unset("SOL");
				st.unset("TI");
				st.unset("SOL2");
				st.unset("FA2");
				break;
			}
			case "SOL_Three": {
				st.set("SOL", "1");
				htmltext = "32122-6a.html";
				break;
			}
			case "FA_Three": {
				st.set("FA", "1");
				htmltext = "32122-6c.html";
				break;
			}
			case "MI_Three": {
				st.set("MI", "1");
				htmltext = "32122-6e.html";
				break;
			}
			case "FA2_Three": {
				st.set("FA2", "1");
				htmltext = "32122-6g.html";
				break;
			}
			case "MI2_Three": {
				st.set("MI2", "1");
				if (st.isCond(16) && (st.getInt("SOL") > 0) && (st.getInt("FA") > 0) && (st.getInt("MI") > 0) && (st.getInt("FA2") > 0) && (st.getInt("MI2") > 0)) {
					htmltext = "32122-6j.html";
					st.setCond(17, true);
				} else {
					st.unset("SOL");
					st.unset("FA");
					st.unset("MI");
					st.unset("FA2");
					st.unset("MI2");
					htmltext = "32122-6i.html";
				}
				break;
			}
			case "32122-6i.html": {
				st.unset("SOL");
				st.unset("FA");
				st.unset("MI");
				st.unset("FA2");
				st.unset("MI2");
				break;
			}
			case "32122-7.html": {
				if (st.isCond(17)) {
					giveItems(player, BONE_POWDER, 1);
					playSound(player, QuestSound.ETCSOUND_ELROKI_SONG_FULL);
					npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
				}
				break;
			}
			case "32122-8.html": {
				if (st.isCond(17)) {
					st.setCond(18, true);
				}
				break;
			}
			case "32109-2.html": {
				if (st.isCond(18)) {
					st.setCond(19, true);
				}
				break;
			}
			case "32109-3.html": {
				if (st.isCond(19)) {
					takeItems(player, BONE_POWDER, -1);
					st.setCond(20, true);
				}
				break;
			}
			case "32115-4.html": {
				if (st.isCond(20)) {
					st.setCond(21, true);
				}
				break;
			}
			case "32115-5.html": {
				if (st.isCond(21)) {
					st.setCond(22, true);
				}
				break;
			}
			case "32114-2.html": {
				if (st.isCond(22)) {
					st.setCond(23, true);
				}
				break;
			}
			case "32114-3.html": {
				if (st.isCond(23)) {
					if (player.getLevel() >= MIN_LEVEL) {
						giveItems(player, ENCHANT_WEAPON_A, 1);
						giveAdena(player, 484_990, true);
						addExp(player, 2_264_190);
						addSp(player, 543);
						st.exitQuest(false, true);
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
		QuestState st = getQuestState(player, true);
		if (st == null) {
			return htmltext;
		}

		switch (st.getState()) {
			case State.CREATED: {
				if (npc.getId() == ASAMAH) {
					htmltext = "32115-0.htm";
				}
				break;
			}
			case State.STARTED: {
				switch (npc.getId()) {
					case ASAMAH: {
						switch (st.getCond()) {
							case 1:
								htmltext = "32115-1d.html";
								break;
							case 2:
								htmltext = "32115-1c.html";
								break;
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
							case 11:
							case 12:
							case 13:
							case 14:
							case 15:
							case 16:
							case 17:
							case 18:
							case 19:
								htmltext = "32115-2.html";
								break;
							case 20:
								htmltext = "32115-3.html";
								break;
							case 21:
								htmltext = "32115-4j.html";
								break;
							case 22:
								htmltext = "32115-5a.html";
								break;
						}
						break;
					}
					case ULU_KAIMU: {
						switch (st.getCond()) {
							case 1:
								htmltext = "32119-1.html";
								break;
							case 2: {
								if (!isSimulated) {
									npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
								}
								htmltext = "32119-2.html";
								break;
							}
							case 3:
								htmltext = "32119-3c.html";
								break;
							case 4:
								htmltext = "32119-4c.html";
								break;
							case 5:
								htmltext = "32119-5a.html";
								break;
						}
						break;
					}
					case BALU_KAIMU: {
						switch (st.getCond()) {
							case 1:
							case 2:
							case 3:
							case 4:
								htmltext = "32120-1.html";
								break;
							case 5: {
								if (!isSimulated) {
									npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
								}
								htmltext = "32120-2.html";
								break;
							}
							case 6:
								htmltext = "32120-3c.html";
								break;
							case 7:
								htmltext = "32120-4c.html";
								break;
							default:
								htmltext = "32120-5a.html";
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
							case 7:
								htmltext = "32121-1.html";
								break;
							case 8: {
								if (!isSimulated) {
									npc.broadcastPacket(new MagicSkillUse(npc, player, 5089, 1, 1000, 0));
								}
								htmltext = "32121-2.html";
								break;
							}
							case 9:
								htmltext = "32121-3e.html";
								break;
							case 10:
								htmltext = "32121-4e.html";
								break;
							default:
								htmltext = "32121-5a.html";
								break;
						}
						break;
					}
					case WARRIORS_GRAVE: {
						switch (st.getCond()) {
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
								htmltext = "32122-1.html";
								break;
							case 11: {
								if (!isSimulated) {
									st.setCond(12, true);
								}
								htmltext = "32122-2.html";
								break;
							}
							case 12:
								htmltext = "32122-2l.html";
								break;
							case 13:
								htmltext = "32122-3b.html";
								break;
							case 14: {
								if (!isSimulated) {
									st.unset("DO");
									st.unset("MI");
									st.unset("FA");
									st.unset("SOL");
									st.unset("FA2");
								}
								htmltext = "32122-4.html";
								break;
							}
							case 15: {
								if (!isSimulated) {
									st.unset("FA");
									st.unset("SOL");
									st.unset("TI");
									st.unset("SOL2");
									st.unset("FA2");
								}
								htmltext = "32122-5.html";
								break;
							}
							case 16: {
								if (!isSimulated) {
									st.unset("SOL");
									st.unset("FA");
									st.unset("MI");
									st.unset("FA2");
									st.unset("MI2");
								}
								htmltext = "32122-6.html";
								break;
							}
							case 17:
								htmltext = hasQuestItems(player, BONE_POWDER) ? "32122-7.html" : "32122-7b.html";
								break;
							case 18:
								htmltext = "32122-8.html";
								break;
							default:
								htmltext = "32122-9.html";
								break;
						}
						break;
					}
					case SHILENS_STONE_STATUE: {
						switch (st.getCond()) {
							case 1:
							case 2:
							case 3:
							case 4:
							case 5:
							case 6:
							case 7:
							case 8:
							case 9:
							case 10:
							case 11:
							case 12:
							case 13:
							case 14:
							case 15:
							case 16:
							case 17:
								htmltext = "32109-1a.html";
								break;
							case 18:
								if (hasQuestItems(player, BONE_POWDER)) {
									htmltext = "32109-1.html";
								}
								break;
							case 19:
								htmltext = "32109-2l.html";
								break;
							case 20:
								htmltext = "32109-5.html";
								break;
							default:
								htmltext = "32109-4.html";
								break;
						}
						break;
					}
					case MUSHIKA: {
						if (st.getCond() < 22) {
							htmltext = "32114-4.html";
						} else if (st.isCond(22)) {
							htmltext = "32114-1.html";
						} else {
							htmltext = "32114-2.html";
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
