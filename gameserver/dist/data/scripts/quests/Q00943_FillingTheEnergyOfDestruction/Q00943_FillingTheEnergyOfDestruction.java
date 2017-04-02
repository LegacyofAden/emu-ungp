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
package quests.Q00943_FillingTheEnergyOfDestruction;

import org.l2junity.gameserver.enums.QuestType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;

/**
 * Filling the Energy of Destruction (943)
 *
 * @author St3eT
 */
public final class Q00943_FillingTheEnergyOfDestruction extends Quest {
	// NPCs
	private static final int SEED_TALISMAN = 33715;
	private static final int[] BOSSES =
			{
					29195, // Istina (common)
					29196, // Istina (extreme)
					29194, // Octavis (common)
					29212, // Octavis (extreme)
					25779, // Spezion (normal)
					25867, // Spezion (extreme)
					29213, // Baylor
					29218, // Balok
					25825, // Ron
					29236, // Tauti (common)
					29237, // Tauti (extreme)
			};
	// Items
	private static final int TWISTED_MAGIC = 35668;
	private static final int ENERGY_OF_DESTRUCTION = 35562;
	// Misc
	private static final int MIN_LEVEL = 90;

	public Q00943_FillingTheEnergyOfDestruction() {
		super(943);
		addStartNpc(SEED_TALISMAN);
		addTalkId(SEED_TALISMAN);
		addKillId(BOSSES);
		registerQuestItems(TWISTED_MAGIC);
		addCondMinLevel(MIN_LEVEL, "33715-08.html");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player) {
		final QuestState st = getQuestState(player, false);

		if (st == null) {
			return null;
		}

		String htmltext = null;
		switch (event) {
			case "33715-02.htm": {
				htmltext = event;
				break;
			}
			case "33715-03.htm": {
				st.startQuest();
				htmltext = event;
				break;
			}
			case "33715-06.html": {
				st.exitQuest(QuestType.DAILY, true);
				giveItems(player, ENERGY_OF_DESTRUCTION, 1);
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, PlayerInstance player) {
		String htmltext = getNoQuestMsg(player);
		final QuestState st = getQuestState(player, true);

		if (npc.getId() == SEED_TALISMAN) {
			switch (st.getState()) {
				case State.CREATED: {
					htmltext = "33715-01.htm";
					break;
				}
				case State.STARTED: {
					htmltext = st.isCond(1) ? "33715-04.html" : "33715-05.html";
					break;
				}
				case State.COMPLETED: {
					if (st.isNowAvailable()) {
						st.setState(State.CREATED);
						htmltext = "33715-01.htm";
					} else {
						htmltext = "33715-07.html";
					}
					break;
				}
			}
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, PlayerInstance player, boolean isSummon) {
		executeForEachPlayer(player, npc, isSummon, true, true);
		return super.onKill(npc, player, isSummon);
	}

	@Override
	public void actionForEachPlayer(PlayerInstance player, Npc npc, boolean isSummon) {
		final QuestState st = getQuestState(player, true);
		if ((st != null) && st.isCond(1) && (npc.distance2d(player) <= 1500)) {
			st.setCond(2, true);
			giveItems(player, TWISTED_MAGIC, 1);
		}
	}
}