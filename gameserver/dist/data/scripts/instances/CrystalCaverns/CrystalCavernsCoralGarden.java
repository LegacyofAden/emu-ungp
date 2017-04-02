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
package instances.CrystalCaverns;

import instances.AbstractInstance;
import org.l2junity.gameserver.instancemanager.SuperpointManager;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.network.client.send.ExSendUIEvent;
import org.l2junity.gameserver.network.client.send.ExShowScreenMessage;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Crystal Caverns - Coral Garden instance zone.
 *
 * @author St3eT
 */
public final class CrystalCavernsCoralGarden extends AbstractInstance {
	// NPCs
	private static final int ENTRANCE_PORTAL = 33522;
	private static final int MICHAELA_NORMAL = 25799;
	private static final int MICHAELA_WISE = 26116;
	private static final int MICHAELA_WEALTHY = 26115;
	private static final int MICHAELA_ARMED = 26114;
	private static final int GOLEM_1 = 19013; // Crystalline Golem
	private static final int GOLEM_2 = 19014; // Crystalline Golem
	// Skills
	// Location
	private static final Location BOSS_LOC = new Location(144307, 220032, -11824);
	// Misc
	private static final int TEMPLATE_ID = 165;
	private static final int BOSS_DOOR_ID = 24240026;
	private static final int PLAYER_MAX_DISTANCE = 250;

	public CrystalCavernsCoralGarden() {
		super(TEMPLATE_ID);
		addStartNpc(ENTRANCE_PORTAL);
		addTalkId(ENTRANCE_PORTAL);
		addFirstTalkId(GOLEM_1, GOLEM_2);
		addKillId(MICHAELA_NORMAL, MICHAELA_WISE, MICHAELA_WEALTHY, MICHAELA_ARMED);
		addAttackId(MICHAELA_NORMAL, MICHAELA_WISE, MICHAELA_WEALTHY, MICHAELA_ARMED);
		addRouteFinishedId(GOLEM_1, GOLEM_2);
		addInstanceEnterId(TEMPLATE_ID);
		addInstanceLeaveId(TEMPLATE_ID);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, PlayerInstance player) {
		final Instance instance = npc.getInstanceWorld();
		if (isInInstance(instance)) {
			final StatsSet npcVars = npc.getVariables();

			switch (event) {
				case "SUCCESS_TIMER": {
					showOnScreenMsg(instance, NpcStringId.GOLEM_LOCATION_SUCCESSFUL_ENTRY_ACCESSED, ExShowScreenMessage.MIDDLE_CENTER, 5000);
					break;
				}
				case "LOOP_TIMER": {
					player = npcVars.getObject("PLAYER_OBJECT", PlayerInstance.class);

					if ((player != null) && (npc.distance3d(player) > PLAYER_MAX_DISTANCE) && npcVars.getBoolean("NPC_FOLLOWING", true)) {
						SuperpointManager.getInstance().cancelMoving(npc);
						addMoveToDesire(npc, npc.getRandomPosition(100, 150), 23);
						npc.setIsRunning(true);
						npcVars.set("NPC_FOLLOWING", false);
						getTimers().cancelTimer("LOOP_TIMER", npc, null);
						getTimers().addTimer("FAIL_TIMER", 5000, npc, null);
					}
					break;
				}
				case "FAIL_TIMER": {
					final L2Spawn spawn = npc.getSpawn();

					if (!npcVars.getBoolean("NPC_FOLLOWING", true)) {
						npc.setIsRunning(false);
						npc.teleToLocation(npc.getSpawn().getX(), npc.getSpawn().getY(), npc.getSpawn().getZ());
						npc.setScriptValue(0);
						// TODO: reset attack count
						npc.setNameString(null);
						npc.setTitle(""); // TODO: replace me with npc.setTitleString(null); when support done
						npc.broadcastInfo();
					}
					npcVars.set("CAN_CALL_MONSTERS", ((spawn.getX() - ((npc.getX() * spawn.getX()) - npc.getX())) + (spawn.getY() - (npc.getY() * spawn.getY()) - npc.getY())) > (200 * 200));
					break;
				}
			}
		}
	}

	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player) {
		if (event.equals("enterInstance")) {
			enterInstance(player, npc, TEMPLATE_ID);
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public void onInstanceEnter(PlayerInstance player, Instance instance) {
		final int startTime = (int) (instance.getElapsedTime() / 1000);
		final int endTime = (int) (instance.getRemainingTime() / 1000);
		player.sendPacket(new ExSendUIEvent(player, false, true, startTime, endTime, NpcStringId.ELAPSED_TIME));
	}

	@Override
	public void onInstanceLeave(PlayerInstance player, Instance instance) {
		player.sendPacket(new ExSendUIEvent(player, true, true, 0, 0, NpcStringId.ELAPSED_TIME));
	}

	@Override
	public String onFirstTalk(Npc npc, PlayerInstance player) {
		final Instance instance = npc.getInstanceWorld();
		if (isInInstance(instance)) {
			final StatsSet npcParams = npc.getParameters();
			final StatsSet npcVars = npc.getVariables();

			if (npc.isScriptValue(0)) {
				npcVars.getBoolean("NPC_FOLLOWING", false);
				npc.setScriptValue(1);
				SuperpointManager.getInstance().startMoving(npc, npcParams.getString("SuperPointName", "none"));
				npcVars.set("PLAYER_OBJECT", player);
				npc.setNameString(NpcStringId.TRAITOR_CRYSTALLINE_GOLEM);
				npc.setTitle("Given to " + player.getName()); // TODO: replace with "npc.setTitleString(NpcStringId.GIVEN_TO_S1);" when support for params done
				npc.broadcastInfo();
				getTimers().addRepeatingTimer("LOOP_TIMER", 500, npc, null);
			}
		}
		return null;
	}

	@Override
	public void onRouteFinished(Npc npc) {
		final Instance instance = npc.getInstanceWorld();
		if (instance != null) {
			showOnScreenMsg(instance, NpcStringId.GOLEM_ENTERED_THE_REQUIRED_ZONE, ExShowScreenMessage.MIDDLE_CENTER, 5000);
			npc.deleteMe();

			if (instance.getAliveNpcs(GOLEM_1, GOLEM_2).isEmpty()) {
				instance.openCloseDoor(BOSS_DOOR_ID, true);

				final int random = getRandom(100);
				int bossId = -1;

				if (random < 55) {
					bossId = MICHAELA_NORMAL;
				} else if (random < 80) {
					bossId = MICHAELA_WISE;
				} else if (random < 95) {
					bossId = MICHAELA_WEALTHY;
				} else {
					bossId = MICHAELA_ARMED;
				}

				final Npc boss = addSpawn(bossId, BOSS_LOC, false, 0, false, instance.getId());
				getTimers().addTimer("SUCCESS_TIMER", 5000, boss, null);
			}
		}
	}

	@Override
	public String onKill(Npc npc, PlayerInstance killer, boolean isSummon) {
		final Instance instance = npc.getInstanceWorld();
		if (isInInstance(instance)) {
			switch (npc.getId()) {
				case MICHAELA_NORMAL:
				case MICHAELA_WISE:
				case MICHAELA_WEALTHY:
				case MICHAELA_ARMED: {
					instance.finishInstance();
					break;
				}
			}
		}
		return super.onKill(npc, killer, isSummon);
	}

	@Override
	public String onAttack(Npc npc, PlayerInstance attacker, int damage, boolean isSummon) {
		final Instance instance = npc.getInstanceWorld();
		if (isInInstance(instance)) {
			switch (npc.getId()) {
				case MICHAELA_NORMAL:
				case MICHAELA_WISE:
				case MICHAELA_WEALTHY:
				case MICHAELA_ARMED: {
					if (npc.isScriptValue(0)) {
						npc.setScriptValue(1);
						instance.openCloseDoor(BOSS_DOOR_ID, false);
					}
					break;
				}
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}

	public static void main(String[] args) {
		new CrystalCavernsCoralGarden();
	}
}