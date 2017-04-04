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
package ai.individual.Other.GrandBossTeleporters;

import ai.AbstractNpcAI;
import ai.individual.ForgeOfTheGods.Valakas;
import org.l2junity.core.configs.GrandBossConfig;
import org.l2junity.gameserver.data.xml.impl.DoorData;
import org.l2junity.gameserver.instancemanager.GrandBossManager;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.GrandBossInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;

/**
 * Grand Bosses teleport AI.<br>
 * Original python script by Emperorc.
 *
 * @author Plim
 */
public final class GrandBossTeleporters extends AbstractNpcAI {
	// NPCs
	private static final int[] NPCs =
			{
					31384, // Gatekeeper of Fire Dragon : Opening some doors
					31385, // Heart of Volcano : Teleport into Lair of Valakas
					31540, // Watcher of Valakas Klein : Teleport into Hall of Flames
					31686, // Gatekeeper of Fire Dragon : Opens doors to Heart of Volcano
					31687, // Gatekeeper of Fire Dragon : Opens doors to Heart of Volcano
					31759, // Teleportation Cubic : Teleport out of Lair of Valakas
			};
	// Items
	private static final int VACUALITE_FLOATING_STONE = 7267;
	private static final Location ENTER_HALL_OF_FLAMES = new Location(183813, -115157, -3303);
	private static final Location TELEPORT_INTO_VALAKAS_LAIR = new Location(204328, -111874, 70);
	private static final Location TELEPORT_OUT_OF_VALAKAS_LAIR = new Location(150037, -57720, -2976);

	private static int playerCount = 0;

	private GrandBossTeleporters() {
		addStartNpc(NPCs);
		addTalkId(NPCs);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		String htmltext = "";
		final QuestState st = getQuestState(player, false);

		if (hasQuestItems(player, VACUALITE_FLOATING_STONE)) {
			player.teleToLocation(ENTER_HALL_OF_FLAMES);
			st.set("allowEnter", "1");
		} else {
			htmltext = "31540-06.htm";
		}
		return htmltext;
	}

	@Override
	public String onTalk(Npc npc, Player player) {
		String htmltext = "";
		final QuestState st = getQuestState(player, true);

		switch (npc.getId()) {
			case 31385: {
				if (valakasAI() != null) {
					int status = GrandBossManager.getInstance().getBossStatus(29028);

					if ((status == 0) || (status == 1)) {
						if (playerCount >= 200) {
							htmltext = "31385-03.htm";
						} else if (st.getInt("allowEnter") == 1) {
							st.unset("allowEnter");
							player.teleToLocation(TELEPORT_INTO_VALAKAS_LAIR.getX() + getRandom(600), TELEPORT_INTO_VALAKAS_LAIR.getY() + getRandom(600), TELEPORT_INTO_VALAKAS_LAIR.getZ());

							playerCount++;

							if (status == 0) {
								GrandBossInstance valakas = GrandBossManager.getInstance().getBoss(29028);
								valakasAI().startQuestTimer("beginning", GrandBossConfig.VALAKAS_WAIT_TIME * 60000, valakas, null);
								GrandBossManager.getInstance().setBossStatus(29028, 1);
							}
						} else {
							htmltext = "31385-04.htm";
						}
					} else if (status == 2) {
						htmltext = "31385-02.htm";
					} else {
						htmltext = "31385-01.htm";
					}
				} else {
					htmltext = "31385-01.htm";
				}
				break;
			}
			case 31384: {
				DoorData.getInstance().getDoor(24210004).openMe();
				break;
			}
			case 31686: {
				DoorData.getInstance().getDoor(24210006).openMe();
				break;
			}
			case 31687: {
				DoorData.getInstance().getDoor(24210005).openMe();
				break;
			}
			case 31540: {
				if (playerCount < 50) {
					htmltext = "31540-01.htm";
				} else if (playerCount < 100) {
					htmltext = "31540-02.htm";
				} else if (playerCount < 150) {
					htmltext = "31540-03.htm";
				} else if (playerCount < 200) {
					htmltext = "31540-04.htm";
				} else {
					htmltext = "31540-05.htm";
				}
				break;
			}
			case 31759: {
				player.teleToLocation(TELEPORT_OUT_OF_VALAKAS_LAIR.getX() + getRandom(500), TELEPORT_OUT_OF_VALAKAS_LAIR.getY() + getRandom(500), TELEPORT_OUT_OF_VALAKAS_LAIR.getZ());
				break;
			}
		}
		return htmltext;
	}

	private Quest valakasAI() {
		return QuestManager.getInstance().getQuest(Valakas.class.getSimpleName());
	}

	public static void main(String[] args) {
		new GrandBossTeleporters();
	}
}
