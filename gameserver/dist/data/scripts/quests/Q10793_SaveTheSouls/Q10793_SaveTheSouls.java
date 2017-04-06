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
package quests.Q10793_SaveTheSouls;

import org.l2junity.gameserver.enums.CategoryType;
import org.l2junity.gameserver.enums.QuestSound;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.NpcLogListHolder;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.model.quest.State;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

import java.util.HashSet;
import java.util.Set;

/**
 * Save the Souls (10793)
 *
 * @author malyelfik
 */
public final class Q10793_SaveTheSouls extends Quest {
	// NPC
	private static final int HATUBA = 33849;
	// Monsters
	private static final int[] MONSTERS =
			{
					18119, // Corpse of Deadman
					21547, // Corrupted Knight
					21548, // Resurrected Knight
					21549, // Corrupted Royal Guard
					21551, // Resurrected Royal Guard
					21553, // Trampled Man
					21555, // Slaughter Executioner
					21557, // Bone Snatcher
					21559, // Bone Maker
					21560, // Bone Shaper
					21561, // Sacrificed Man
			};
	// Misc
	private static final int MIN_LEVEL = 65;
	private static final int MAX_LEVEL = 70;
	private static final String KILL_COUNT_VAR = "KillCount";

	public Q10793_SaveTheSouls() {
		super(10793);
		addStartNpc(HATUBA);
		addTalkId(HATUBA);
		addKillId(MONSTERS);
		addCondRace(Race.ERTHEIA, "33849-00.htm");
		addCondLevel(MIN_LEVEL, MAX_LEVEL, "33849-01.htm");
		addCondStart(p -> p.isInCategory(CategoryType.MAGE_GROUP), "33849-01.htm");
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		final QuestState qs = getQuestState(player, false);
		if (qs == null) {
			return null;
		}

		String htmltext = event;
		switch (event) {
			case "33849-03.htm":
			case "33849-04.htm":
				break;
			case "33849-05.htm":
				qs.startQuest();
				break;
			case "33849-08.html": {
				if (qs.isCond(2)) {
					if ((player.getLevel() >= MIN_LEVEL)) {
						giveStoryQuestReward(npc, player);
						addExp(player, 86_636_593);
						addSp(player, 226);
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
		final QuestState qs = getQuestState(player, true);
		String htmltext = getNoQuestMsg(player);

		switch (qs.getState()) {
			case State.CREATED:
				htmltext = "33849-02.htm";
				break;
			case State.STARTED:
				htmltext = (qs.isCond(1)) ? "33849-06.html" : "33849-07.html";
				break;
			case State.COMPLETED:
				htmltext = getAlreadyCompletedMsg(player);
				break;
		}
		return htmltext;
	}

	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon) {
		final QuestState qs = getQuestState(killer, false);
		if ((qs != null) && qs.isCond(1)) {
			int count = qs.getInt(KILL_COUNT_VAR);
			qs.set(KILL_COUNT_VAR, ++count);
			if (count >= 200) {
				qs.setCond(2, true);
			} else {
				sendNpcLogList(killer);
				playSound(killer, QuestSound.ITEMSOUND_QUEST_ITEMGET);
			}
		}
		return super.onKill(npc, killer, isSummon);
	}

	@Override
	public Set<NpcLogListHolder> getNpcLogList(Player player) {
		final QuestState qs = getQuestState(player, false);
		if ((qs != null) && qs.isCond(1)) {
			final int killCount = qs.getInt(KILL_COUNT_VAR);
			if (killCount > 0) {
				final Set<NpcLogListHolder> holder = new HashSet<>();
				holder.add(new NpcLogListHolder(NpcStringId.KILL_THE_UNDEAD, killCount));
				return holder;
			}
		}
		return super.getNpcLogList(player);
	}
}
