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
package ai.individual.SeaOfSpores;

import ai.AbstractNpcAI;
import org.l2junity.core.configs.GrandBossConfig;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.instancemanager.GrandBossManager;
import org.l2junity.gameserver.instancemanager.ZoneManager;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.GrandBossInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillCaster;
import org.l2junity.gameserver.model.zone.ZoneType;
import org.l2junity.gameserver.network.client.send.PlaySound;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Orfen's AI
 *
 * @author Emperorc
 */
public final class Orfen extends AbstractNpcAI {
	private static final Location[] POS =
			{
					new Location(43728, 17220, -4342),
					new Location(55024, 17368, -5412),
					new Location(53504, 21248, -5486),
					new Location(53248, 24576, -5262)
			};

	private static final NpcStringId[] TEXT =
			{
					NpcStringId.S1_STOP_KIDDING_YOURSELF_ABOUT_YOUR_OWN_POWERLESSNESS,
					NpcStringId.S1_I_LL_MAKE_YOU_FEEL_WHAT_TRUE_FEAR_IS,
					NpcStringId.YOU_RE_REALLY_STUPID_TO_HAVE_CHALLENGED_ME_S1_GET_READY,
					NpcStringId.S1_DO_YOU_THINK_THAT_S_GOING_TO_WORK
			};

	private static final int ORFEN = 29014;
	// private static final int RAIKEL = 29015;
	private static final int RAIKEL_LEOS = 29016;
	// private static final int RIBA = 29017;
	private static final int RIBA_IREN = 29018;

	private static boolean _IsTeleported;
	private static Set<Attackable> _minions = ConcurrentHashMap.newKeySet();
	private static ZoneType ZONE;

	private static final byte ALIVE = 0;
	private static final byte DEAD = 1;

	private static final SkillHolder PARALYSIS = new SkillHolder(4064, 1);
	private static final SkillHolder BLOW = new SkillHolder(4067, 4);
	private static final SkillHolder ORFEN_HEAL = new SkillHolder(4516, 1);

	private Orfen() {
		int[] mobs =
				{
						ORFEN,
						RAIKEL_LEOS,
						RIBA_IREN
				};
		registerMobs(mobs);
		_IsTeleported = false;
		ZONE = ZoneManager.getInstance().getZoneById(12013);
		StatsSet info = GrandBossManager.getInstance().getStatsSet(ORFEN);
		int status = GrandBossManager.getInstance().getBossStatus(ORFEN);
		if (status == DEAD) {
			// load the unlock date and time for Orfen from DB
			long temp = info.getLong("respawn_time") - System.currentTimeMillis();
			// if Orfen is locked until a certain time, mark it so and start the unlock timer
			// the unlock time has not yet expired.
			if (temp > 0) {
				startQuestTimer("orfen_unlock", temp, null, null);
			} else {
				// the time has already expired while the server was offline. Immediately spawn Orfen.
				int i = getRandom(10);
				Location loc;
				if (i < 4) {
					loc = POS[1];
				} else if (i < 7) {
					loc = POS[2];
				} else {
					loc = POS[3];
				}
				GrandBossInstance orfen = (GrandBossInstance) addSpawn(ORFEN, loc, false, 0);
				GrandBossManager.getInstance().setBossStatus(ORFEN, ALIVE);
				spawnBoss(orfen);
			}
		} else {
			int loc_x = info.getInt("loc_x");
			int loc_y = info.getInt("loc_y");
			int loc_z = info.getInt("loc_z");
			int heading = info.getInt("heading");
			double hp = info.getDouble("currentHP");
			double mp = info.getDouble("currentMP");
			GrandBossInstance orfen = (GrandBossInstance) addSpawn(ORFEN, loc_x, loc_y, loc_z, heading, false, 0);
			orfen.setCurrentHpMp(hp, mp);
			spawnBoss(orfen);
		}
	}

	public void setSpawnPoint(Npc npc, int index) {
		((Attackable) npc).clearAggroList();
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
		L2Spawn spawn = npc.getSpawn();
		spawn.setLocation(POS[index]);
		npc.teleToLocation(POS[index], false);
	}

	public void spawnBoss(GrandBossInstance npc) {
		GrandBossManager.getInstance().addBoss(npc);
		npc.broadcastPacket(new PlaySound(1, "BS01_A", 1, npc.getObjectId(), npc.getX(), npc.getY(), npc.getZ()));
		startQuestTimer("check_orfen_pos", 10000, npc, null, true);
		// Spawn minions
		double x = npc.getX();
		double y = npc.getY();
		Attackable mob;
		mob = (Attackable) addSpawn(RAIKEL_LEOS, x + 100, y + 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		_minions.add(mob);
		mob = (Attackable) addSpawn(RAIKEL_LEOS, x + 100, y - 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		_minions.add(mob);
		mob = (Attackable) addSpawn(RAIKEL_LEOS, x - 100, y + 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		_minions.add(mob);
		mob = (Attackable) addSpawn(RAIKEL_LEOS, x - 100, y - 100, npc.getZ(), 0, false, 0);
		mob.setIsRaidMinion(true);
		_minions.add(mob);
		startQuestTimer("check_minion_loc", 10000, npc, null, true);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		if (event.equalsIgnoreCase("orfen_unlock")) {
			int i = getRandom(10);
			Location loc;
			if (i < 4) {
				loc = POS[1];
			} else if (i < 7) {
				loc = POS[2];
			} else {
				loc = POS[3];
			}
			GrandBossInstance orfen = (GrandBossInstance) addSpawn(ORFEN, loc, false, 0);
			GrandBossManager.getInstance().setBossStatus(ORFEN, ALIVE);
			spawnBoss(orfen);
		} else if (event.equalsIgnoreCase("check_orfen_pos")) {
			if ((_IsTeleported && (npc.getCurrentHp() > (npc.getMaxHp() * 0.95))) || (!ZONE.isInsideZone(npc) && !_IsTeleported)) {
				setSpawnPoint(npc, getRandom(3) + 1);
				_IsTeleported = false;
			} else if (_IsTeleported && !ZONE.isInsideZone(npc)) {
				setSpawnPoint(npc, 0);
			}
		} else if (event.equalsIgnoreCase("check_minion_loc")) {
			for (Attackable mob : _minions) {
				if (!npc.isInRadius2d(mob, 3000)) {
					mob.teleToLocation(npc.getLocation());
					((Attackable) npc).clearAggroList();
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
				}
			}
		} else if (event.equalsIgnoreCase("despawn_minions")) {
			for (Attackable mob : _minions) {
				mob.decayMe();
			}
			_minions.clear();
		} else if (event.equalsIgnoreCase("spawn_minion")) {
			Attackable mob = (Attackable) addSpawn(RAIKEL_LEOS, npc.getX(), npc.getY(), npc.getZ(), 0, false, 0);
			mob.setIsRaidMinion(true);
			_minions.add(mob);
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public String onSkillSee(Npc npc, Player caster, Skill skill, WorldObject[] targets, boolean isSummon) {
		if (npc.getId() == ORFEN) {
			Creature originalCaster = isSummon ? caster.getServitors().values().stream().findFirst().orElse(caster.getPet()) : caster;
			if ((skill.getEffectPoint() > 0) && (getRandom(5) == 0) && npc.isInRadius2d(originalCaster, 1000)) {
				npc.broadcastSay(ChatType.NPC_GENERAL, TEXT[getRandom(4)], caster.getName());
				originalCaster.teleToLocation(npc.getLocation());
				npc.setTarget(originalCaster);
				npc.doCast(PARALYSIS.getSkill());
			}
		}
		return super.onSkillSee(npc, caster, skill, targets, isSummon);
	}

	@Override
	public String onFactionCall(Npc npc, Npc caller, Player attacker, boolean isSummon) {
		if ((caller == null) || (npc == null) || npc.isCastingNow(SkillCaster::isAnyNormalType)) {
			return super.onFactionCall(npc, caller, attacker, isSummon);
		}
		int npcId = npc.getId();
		int callerId = caller.getId();
		if ((npcId == RAIKEL_LEOS) && (getRandom(20) == 0)) {
			npc.setTarget(attacker);
			npc.doCast(BLOW.getSkill());
		} else if (npcId == RIBA_IREN) {
			int chance = 1;
			if (callerId == ORFEN) {
				chance = 9;
			}
			if ((callerId != RIBA_IREN) && (caller.getCurrentHp() < (caller.getMaxHp() / 2.0)) && (getRandom(10) < chance)) {
				npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
				npc.setTarget(caller);
				npc.doCast(ORFEN_HEAL.getSkill());
			}
		}
		return super.onFactionCall(npc, caller, attacker, isSummon);
	}

	@Override
	public String onAttack(Npc npc, Player attacker, int damage, boolean isSummon) {
		int npcId = npc.getId();
		if (npcId == ORFEN) {
			if (!_IsTeleported && ((npc.getCurrentHp() - damage) < (npc.getMaxHp() / 2))) {
				_IsTeleported = true;
				setSpawnPoint(npc, 0);
			} else if (npc.isInRadius2d(attacker, 1000) && !npc.isInRadius2d(attacker, 300) && (getRandom(10) == 0)) {
				npc.broadcastSay(ChatType.NPC_GENERAL, TEXT[getRandom(3)], attacker.getName());
				attacker.teleToLocation(npc.getLocation());
				npc.setTarget(attacker);
				npc.doCast(PARALYSIS.getSkill());
			}
		} else if (npcId == RIBA_IREN) {
			if (!npc.isCastingNow(SkillCaster::isAnyNormalType) && ((npc.getCurrentHp() - damage) < (npc.getMaxHp() / 2.0))) {
				npc.setTarget(attacker);
				npc.doCast(ORFEN_HEAL.getSkill());
			}
		}
		return super.onAttack(npc, attacker, damage, isSummon);
	}

	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon) {
		if (npc.getId() == ORFEN) {
			npc.broadcastPacket(new PlaySound(1, "BS02_D", 1, npc.getObjectId(), npc.getX(), npc.getY(), npc.getZ()));
			GrandBossManager.getInstance().setBossStatus(ORFEN, DEAD);
			// Calculate Min and Max respawn times randomly.
			long respawnTime = GrandBossConfig.ORFEN_SPAWN_INTERVAL + getRandom(-GrandBossConfig.ORFEN_SPAWN_RANDOM, GrandBossConfig.ORFEN_SPAWN_RANDOM);
			respawnTime *= 3600000;
			startQuestTimer("orfen_unlock", respawnTime, null, null);
			// also save the respawn time so that the info is maintained past reboots
			StatsSet info = GrandBossManager.getInstance().getStatsSet(ORFEN);
			info.set("respawn_time", System.currentTimeMillis() + respawnTime);
			GrandBossManager.getInstance().setStatsSet(ORFEN, info);
			cancelQuestTimer("check_minion_loc", npc, null);
			cancelQuestTimer("check_orfen_pos", npc, null);
			startQuestTimer("despawn_minions", 20000, null, null);
			cancelQuestTimers("spawn_minion");
		} else if ((GrandBossManager.getInstance().getBossStatus(ORFEN) == ALIVE) && (npc.getId() == RAIKEL_LEOS)) {
			_minions.remove(npc);
			startQuestTimer("spawn_minion", 360000, npc, null);
		}
		return super.onKill(npc, killer, isSummon);
	}

	public static void main(String[] args) {
		new Orfen();
	}
}
