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
package quests.Q10461_TappingThePowerWithin;

import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.packets.s2c.ExShowScreenMessage;
import org.l2junity.gameserver.network.packets.s2c.TutorialShowHtml;
import org.l2junity.gameserver.network.packets.s2c.ability.ExShowAPListWnd;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

/**
 * Tapping The Power Within (10461)
 *
 * @author Gladicek
 */

public final class Q10461_TappingThePowerWithin extends Quest {
	// NPCs
	private static final int LIONEL_HUNTER = 33907;
	// Misc
	private static final int MIN_LEVEL = 99;

	public Q10461_TappingThePowerWithin() {
		super(10461);
		addStartNpc(LIONEL_HUNTER);
		addTalkId(LIONEL_HUNTER);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = event;

		switch (event) {
			case "33907-02.htm":
			case "33907-03.htm": {
				htmltext = event;
				break;
			}
			case "33907-04.htm": {
				qs.startQuest();
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.DOES_YOUR_FATE_NOT_WEIGH_UPON_YOU);
				player.sendPacket(new TutorialShowHtml(npc.getObjectId(), "..\\L2text\\QT_025_ability_01.htm", TutorialShowHtml.LARGE_WINDOW));
				break;
			}
			case "33907-06.html": {
				if (!player.isSubClassActive() || (player.isDualClassActive())) {
					showOnScreenMsg(player, NpcStringId.OPEN_THE_ABILITY_SCREEN_IN_THE_CHARACTER_INFORMATION_SCREEN_NPRESS_ADJUST_POINTS_TO_ADJUST_THE_ACQUIRED_SP_AND_ABILITY_POINTS, ExShowScreenMessage.TOP_CENTER, 5000);
					npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.THIS_IS_JUST_THE_BEGINNING);
					player.sendPacket(ExShowAPListWnd.STATIC_PACKET);
					addSp(player, 250_000_000);
					qs.exitQuest(false, true);
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
		String htmltext = getNoQuestMsg(player);

		if (qs == null) {
			return htmltext;
		}

		switch (qs.getState()) {
			case State.CREATED: {
				if (((player.getLevel() >= MIN_LEVEL) && player.isNoble())) {
					htmltext = "33907-01.htm";
					break;
				}
				htmltext = "33907-07.html";
				break;
			}
			case State.STARTED: {
				if (qs.isCond(1)) {
					htmltext = "33907-05.html";
				}
				break;
			}
			case State.COMPLETED: {
				htmltext = "33907-08.html";
				break;
			}
		}
		return htmltext;
	}
}