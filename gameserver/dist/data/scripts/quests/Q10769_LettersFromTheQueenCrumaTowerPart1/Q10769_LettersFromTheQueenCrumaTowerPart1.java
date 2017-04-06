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
package quests.Q10769_LettersFromTheQueenCrumaTowerPart1;

import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.network.packets.s2c.ExShowScreenMessage;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;
import quests.LetterQuest;

/**
 * Letters from the Queen: Cruma Tower, Part 1 (10769)
 *
 * @author malyelfik
 */
public final class Q10769_LettersFromTheQueenCrumaTowerPart1 extends LetterQuest {
	// NPCs
	private static final int SYLVAIN = 30070;
	private static final int LORAIN = 30673;
	// Items
	private static final int SOE_DION_TOWN = 39593;
	private static final int SOE_CRUMA_TOWER = 39594;
	// Location
	private static final Location TELEPORT_LOC = new Location(16014, 142326, -2688);
	// Misc
	private static final int MIN_LEVEL = 40;
	private static final int MAX_LEVEL = 45;

	public Q10769_LettersFromTheQueenCrumaTowerPart1() {
		super(10769);
		addTalkId(SYLVAIN, LORAIN);
		setIsErtheiaQuest(true);
		setLevel(MIN_LEVEL, MAX_LEVEL);
		setStartLocation(SOE_DION_TOWN, TELEPORT_LOC);
		setStartQuestSound("Npcdialog1.serenia_quest_3");
		registerQuestItems(SOE_DION_TOWN, SOE_CRUMA_TOWER);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = event;
		switch (event) {
			case "30070-02.html":
			case "30673-02.html":
				break;
			case "30070-03.html": {
				if (qs.isCond(1)) {
					qs.setCond(2, true);
					giveItems(player, SOE_CRUMA_TOWER, 1);
					showOnScreenMsg(player, NpcStringId.TRY_USING_THE_TELEPORT_SCROLL_SYLVAIN_GAVE_YOU_TO_GO_TO_CRUMA_TOWER, ExShowScreenMessage.TOP_CENTER, 5000);
				}
				break;
			}
			case "30673-03.html": {
				if (qs.isCond(2)) {
					if (player.getLevel() >= MIN_LEVEL) {
						giveStoryQuestReward(npc, player);
						addExp(player, 370_440);
						addSp(player, 88);
						showOnScreenMsg(player, NpcStringId.GROW_STRONGER_HERE_UNTIL_YOU_RECEIVE_THE_NEXT_LETTER_FROM_QUEEN_NAVARI_AT_LV_46, ExShowScreenMessage.TOP_CENTER, 8000);
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
		final QuestState qs = getQuestState(player, false);
		String htmltext = getNoQuestMsg(player);
		if (qs == null) {
			return htmltext;
		}

		if (qs.isStarted()) {
			if (npc.getId() == SYLVAIN) {
				htmltext = (qs.isCond(1)) ? "30070-01.html" : "30070-04.html";
			} else if (qs.isCond(2)) {
				htmltext = "30673-01.html";
			}
		}
		return htmltext;
	}
}
