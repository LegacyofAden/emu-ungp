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
package quests.Q10321_QualificationsOfTheSeeker;

import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.packets.s2c.TutorialShowHtml;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;
import quests.Q10320_LetsGoToTheCentralSquare.Q10320_LetsGoToTheCentralSquare;

/**
 * Qualifications Of The Seeker (10321)
 *
 * @author ivantotov, Gladicek
 */
public final class Q10321_QualificationsOfTheSeeker extends Quest {
	// NPCs
	private static final int SHANNON = 32974;
	private static final int THEODORE = 32975;
	// Misc
	private static final int MAX_LEVEL = 20;

	public Q10321_QualificationsOfTheSeeker() {
		super(10321);
		addStartNpc(THEODORE);
		addTalkId(THEODORE, SHANNON);
		addCondMaxLevel(MAX_LEVEL, "32975-01a.htm");
		addCondCompletedQuest(Q10320_LetsGoToTheCentralSquare.class.getSimpleName(), "32975-01a.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = null;
		switch (event) {
			case "32975-03.htm": {
				qs.startQuest();
				player.sendPacket(new TutorialShowHtml(npc.getObjectId(), "..\\L2Text\\QT_027_Quest_01.htm", TutorialShowHtml.LARGE_WINDOW));
				htmltext = event;
				break;
			}
			case "32975-02.htm": {
				htmltext = event;
				break;
			}
			case "32974-02.htm": {
				if (qs.isStarted()) {
					addExp(player, 300);
					addSp(player, 6);
					qs.exitQuest(false, true);
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.HM_DON_T_JUST_GO_I_STILL_HAVE_TONS_TO_TEACH_YOU);
					htmltext = event;
				}
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player) {
		final QuestState qs = getQuestState(player, true);
		String htmltext = null;

		switch (qs.getState()) {
			case State.CREATED: {
				htmltext = npc.getId() == THEODORE ? "32975-01.htm" : "32974-04.htm";
				break;
			}
			case State.STARTED: {
				htmltext = npc.getId() == THEODORE ? "32975-04.htm" : "32974-01.htm";
				break;
			}
			case State.COMPLETED: {
				htmltext = npc.getId() == THEODORE ? "32975-05.htm" : "32974-03.htm";
				break;
			}
		}
		return htmltext;
	}
}