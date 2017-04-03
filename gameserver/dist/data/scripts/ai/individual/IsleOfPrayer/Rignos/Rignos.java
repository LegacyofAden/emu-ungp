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
package ai.individual.IsleOfPrayer.Rignos;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;

/**
 * Rignos AI.
 *
 * @author St3eT
 */
public class Rignos extends AbstractNpcAI {
	// NPC
	private static final int RIGNOS = 32349; // Rignos
	// Item
	private static final int STAMP = 10013; // Race Stamp
	private static final int KEY = 9694; // Secret Key
	// Skill
	private static final SkillHolder TIMER = new SkillHolder(5239, 5); // Event Timer
	// Misc
	private static final int MIN_LV = 78;

	private Rignos() {
		addStartNpc(RIGNOS);
		addTalkId(RIGNOS);
		addFirstTalkId(RIGNOS);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		switch (event) {
			case "32349-03.html": {
				return event;
			}
			case "startRace": {
				if (npc.isScriptValue(0)) {
					npc.setScriptValue(1);
					startQuestTimer("TIME_OUT", 1800000, npc, null);
					npc.doInstantCast(player, TIMER);
					final Summon pet = player.getPet();
					if (pet != null) {
						npc.doInstantCast(player, TIMER);
					}
					player.getServitors().values().forEach(s ->
					{
						npc.doInstantCast(player, TIMER);
					});

					if (hasQuestItems(player, STAMP)) {
						takeItems(player, STAMP, -1);
					}
				}
				break;
			}
			case "exchange": {
				if (getQuestItemsCount(player, STAMP) >= 4) {
					giveItems(player, KEY, 3);
					takeItems(player, STAMP, -1);
				}
				break;
			}
			case "TIME_OUT": {
				npc.setScriptValue(0);
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public String onFirstTalk(Npc npc, Player player) {
		String htmltext = (npc.isScriptValue(0) && (player.getLevel() >= MIN_LV)) ? "32349.html" : "32349-02.html";
		if (getQuestItemsCount(player, STAMP) >= 4) {
			htmltext = "32349-01.html";
		}
		return htmltext;
	}

	public static void main(String[] args) {
		new Rignos();
	}
}