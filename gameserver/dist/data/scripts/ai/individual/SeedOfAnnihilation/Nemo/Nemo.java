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
package ai.individual.SeedOfAnnihilation.Nemo;

import ai.AbstractNpcAI;
import ai.individual.SeedOfAnnihilation.Maguen;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

/**
 * Nemo AI.
 *
 * @author St3eT
 */
public final class Nemo extends AbstractNpcAI {
	// NPCs
	private static final int NEMO = 32735; // Nemo
	private static final int MAGUEN = 18839; // Wild Maguen
	// Items
	private static final int COLLECTOR = 15487; // Maguen Plasma Collector
	// Misc
	private static final int MAXIMUM_MAGUEN = 18; // Maximum maguens in one time

	public Nemo() {
		addStartNpc(NEMO);
		addFirstTalkId(NEMO);
		addTalkId(NEMO);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		String htmltext = null;
		switch (event) {
			case "32735-01.html": {
				htmltext = event;
				break;
			}
			case "giveCollector": {
				if (hasQuestItems(player, COLLECTOR)) {
					htmltext = "32735-03.html";
				} else if (!player.isInventoryUnder80(false)) {
					htmltext = "32735-04.html";
				} else {
					htmltext = "32735-02.html";
					giveItems(player, COLLECTOR, 1);
				}
				break;
			}
			case "summonMaguen": {
				if ((player.getVariables().getInt("TEST_MAGUEN", 0) == 0) && (npc.getScriptValue() < MAXIMUM_MAGUEN)) {
					final Npc maguen = addSpawn(MAGUEN, npc.getLocation(), true, 60000, true);
					maguen.getVariables().set("SUMMON_PLAYER", player);
					maguen.getVariables().set("SPAWNED_NPC", npc);
					maguen.getVariables().set("TEST_MAGUEN", 1);
					player.getVariables().set("TEST_MAGUEN", 1);
					maguen.setTitle(player.getName());
					maguen.setIsRunning(true);
					maguen.getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, player);
					maguen.broadcastStatusUpdate();
					showOnScreenMsg(player, NpcStringId.MAGUEN_APPEARANCE, 2, 4000);
					maguenAi().startQuestTimer("DIST_CHECK_TIMER", 1000, maguen, player);
					npc.setScriptValue(npc.getScriptValue() + 1);
					htmltext = "32735-05.html";
				} else {
					htmltext = "32735-06.html";
				}
				break;
			}
			case "DECREASE_COUNT": {
				final Npc spawnedNpc = npc.getVariables().getObject("SPAWNED_NPC", Npc.class);
				if ((spawnedNpc != null) && (spawnedNpc.getScriptValue() > 0)) {
					player.getVariables().remove("TEST_MAGUEN");
					spawnedNpc.setScriptValue(spawnedNpc.getScriptValue() - 1);
				}
			}
		}
		return htmltext;
	}

	private Quest maguenAi() {
		return QuestManager.getInstance().getQuest(Maguen.class.getSimpleName());
	}

	public static void main(String[] args) {
		new Nemo();
	}
}