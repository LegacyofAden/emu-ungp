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
package ai.individual.SeedOfAnnihilation.Rumiese;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;

/**
 * Rumiese AI.
 *
 * @author St3eT
 */
public final class Rumiese extends AbstractNpcAI {
	// NPC
	private static final int RUMIESE = 33151;
	// Item
	private static final int CONTROL_DEVICE = 17608; // Energy Control Device

	public Rumiese() {
		addStartNpc(RUMIESE);
		addTalkId(RUMIESE);
		addFirstTalkId(RUMIESE);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		String htmltext = null;

		switch (event) {
			case "33151-01.html": {
				htmltext = event;
				break;
			}
			case "giveDevice": {
				if (!hasQuestItems(player, CONTROL_DEVICE)) {
					giveItems(player, CONTROL_DEVICE, 1);
					htmltext = "33151-02.html";
				} else {
					htmltext = "33151-03.html";
				}
				break;
			}
		}
		return htmltext;
	}

	public static void main(String[] args) {
		new Rumiese();
	}
}