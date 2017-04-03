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
package ai.individual.KeucereusAllianceBase;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.datatables.SpawnTable;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.enums.Movie;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Lindvior Scene AI.
 *
 * @author nonom
 */
public final class Lindvior extends AbstractNpcAI {
	private static final int LINDVIOR_CAMERA = 18669;
	private static final int TOMARIS = 32552;
	private static final int ARTIUS = 32559;

	private static final int RESET_HOUR = 18;
	private static final int RESET_MIN = 58;
	private static final int RESET_DAY_1 = Calendar.TUESDAY;
	private static final int RESET_DAY_2 = Calendar.FRIDAY;

	private static boolean ALT_MODE = false;
	private static int ALT_MODE_MIN = 60; // schedule delay in minutes if ALT_MODE enabled

	private Npc _lindviorCamera = null;
	private Npc _tomaris = null;
	private Npc _artius = null;

	public Lindvior() {
		scheduleNextLindviorVisit();
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		switch (event) {
			case "tomaris_shout1": {
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.HUH_THE_SKY_LOOKS_FUNNY_WHAT_S_THAT);
				break;
			}
			case "artius_shout": {
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.A_POWERFUL_SUBORDINATE_IS_BEING_HELD_BY_THE_BARRIER_ORB_THIS_REACTION_MEANS);
				break;
			}
			case "tomaris_shout2": {
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.BE_CAREFUL_SOMETHING_S_COMING);
				break;
			}
			case "lindvior_scene": {
				if (npc != null) {
					playMovie(World.getInstance().getVisibleObjects(npc, Player.class, 4000), Movie.SC_LINDVIOR);
				}
				break;
			}
			case "start": {
				_lindviorCamera = SpawnTable.getInstance().getAnySpawn(LINDVIOR_CAMERA).getLastSpawn();
				_tomaris = SpawnTable.getInstance().getAnySpawn(TOMARIS).getLastSpawn();
				_artius = SpawnTable.getInstance().getAnySpawn(ARTIUS).getLastSpawn();

				startQuestTimer("tomaris_shout1", 1000, _tomaris, null);
				startQuestTimer("artius_shout", 60000, _artius, null);
				startQuestTimer("tomaris_shout2", 90000, _tomaris, null);
				startQuestTimer("lindvior_scene", 120000, _lindviorCamera, null);
				scheduleNextLindviorVisit();
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	public void scheduleNextLindviorVisit() {
		final long delay = (ALT_MODE) ? ALT_MODE_MIN * 60000 : scheduleNextLindviorDate();
		startQuestTimer("start", delay, null, null);
	}

	protected long scheduleNextLindviorDate() {
		GregorianCalendar date = new GregorianCalendar();
		date.set(Calendar.MINUTE, RESET_MIN);
		date.set(Calendar.HOUR_OF_DAY, RESET_HOUR);
		if (System.currentTimeMillis() >= date.getTimeInMillis()) {
			date.add(Calendar.DAY_OF_WEEK, 1);
		}

		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek <= RESET_DAY_1) {
			date.add(Calendar.DAY_OF_WEEK, RESET_DAY_1 - dayOfWeek);
		} else if (dayOfWeek <= RESET_DAY_2) {
			date.add(Calendar.DAY_OF_WEEK, RESET_DAY_2 - dayOfWeek);
		} else {
			date.add(Calendar.DAY_OF_WEEK, 1 + RESET_DAY_1);
		}
		return date.getTimeInMillis() - System.currentTimeMillis();
	}

	public static void main(String[] args) {
		new Lindvior();
	}
}
