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
package ai.individual.KeucereusAllianceBase.Nottingale;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.instancemanager.AirShipManager;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.RadarControl;
import quests.Q10273_GoodDayToFly.Q10273_GoodDayToFly;

import java.util.HashMap;
import java.util.Map;

/**
 * Nottingale AI.
 *
 * @author xban1x
 */
public final class Nottingale extends AbstractNpcAI {
	// NPC
	private static final int NOTTINGALE = 32627;
	// Misc
	private static final Map<Integer, RadarControl> RADARS = new HashMap<>();

	static {
		RADARS.put(2, new RadarControl(0, 2, -184545, 243120, 1581));
		RADARS.put(5, new RadarControl(0, 1, -192361, 254528, 3598));
		RADARS.put(6, new RadarControl(0, 1, -174600, 219711, 4424));
		RADARS.put(7, new RadarControl(0, 1, -181989, 208968, 4424));
		RADARS.put(8, new RadarControl(0, 1, -252898, 235845, 5343));
		RADARS.put(9, new RadarControl(0, 1, -212819, 209813, 4288));
		RADARS.put(10, new RadarControl(0, 1, -246899, 251918, 4352));
	}

	public Nottingale() {
		addStartNpc(NOTTINGALE);
		addTalkId(NOTTINGALE);
		addFirstTalkId(NOTTINGALE);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		String htmltext = null;
		switch (event) {
			case "32627-02.html":
			case "32627-03.html":
			case "32627-04.html": {
				if (player.getClan() != null) {
					if (player.hasClanPrivilege(ClanPrivilege.CL_SUMMON_AIRSHIP) && AirShipManager.getInstance().hasAirShipLicense(player.getClanId()) && !AirShipManager.getInstance().hasAirShip(player.getClanId())) {
						htmltext = event;
					} else {
						if (player.hasQuestCompleted(Q10273_GoodDayToFly.class.getSimpleName())) {
							htmltext = event;
						} else {
							player.sendPacket(RADARS.get(2));
							htmltext = "32627-01.html";
						}
					}
				} else {
					if (player.hasQuestCompleted(Q10273_GoodDayToFly.class.getSimpleName())) {
						htmltext = event;
					} else {
						player.sendPacket(RADARS.get(2));
						htmltext = "32627-01.html";
					}
				}
				break;
			}
			case "32627-05.html":
			case "32627-06.html":
			case "32627-07.html":
			case "32627-08.html":
			case "32627-09.html":
			case "32627-10.html": {
				player.sendPacket(RADARS.get(Integer.valueOf(event.substring(6, 8))));
				htmltext = event;
				break;
			}
		}
		return htmltext;
	}

	public static void main(String[] args) {
		new Nottingale();
	}
}
