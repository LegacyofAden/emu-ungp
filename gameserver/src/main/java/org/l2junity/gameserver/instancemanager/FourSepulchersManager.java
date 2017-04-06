/*
 * Copyright (C) 2004-2015 L2J Unity
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
package org.l2junity.gameserver.instancemanager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.impl.DoorData;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.datatables.SpawnTable;
import org.l2junity.gameserver.instancemanager.tasks.FourSepulchersChangeAttackTimeTask;
import org.l2junity.gameserver.instancemanager.tasks.FourSepulchersChangeCoolDownTimeTask;
import org.l2junity.gameserver.instancemanager.tasks.FourSepulchersChangeEntryTimeTask;
import org.l2junity.gameserver.instancemanager.tasks.FourSepulchersChangeWarmUpTimeTask;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.DoorInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.instance.SepulcherMonsterInstance;
import org.l2junity.gameserver.model.actor.instance.SepulcherNpcInstance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Zoey76: TODO: Use Location DTO instead of array of int.
 *
 * @author sandman
 */
@Slf4j
@StartupComponent(value = "Service", dependency = {NpcData.class, SpawnTable.class})
public final class FourSepulchersManager {
	@Getter(lazy = true)
	private static final FourSepulchersManager instance = new FourSepulchersManager();

	private static final int QUEST_ID = 620;

	private static final int ENTRANCE_PASS = 7075;
	private static final int USED_PASS = 7261;
	private static final int CHAPEL_KEY = 7260;
	private static final int ANTIQUE_BROOCH = 7262;

	private boolean _firstTimeRun;
	private boolean _inEntryTime = false;
	private boolean _inWarmUpTime = false;
	private boolean _inAttackTime = false;
	private boolean _inCoolDownTime = false;

	private ScheduledFuture<?> _changeCoolDownTimeTask = null;
	private ScheduledFuture<?> _changeEntryTimeTask = null;
	private ScheduledFuture<?> _changeWarmUpTimeTask = null;
	private ScheduledFuture<?> _changeAttackTimeTask = null;

	// @formatter:off
	private final int[][] _startHallSpawn =
			{
					{181632, -85587, -7218},
					{179963, -88978, -7218},
					{173217, -86132, -7218},
					{175608, -82296, -7218}
			};

	private final int[][][] _shadowSpawnLoc =
			{
					{
							{25339, 191231, -85574, -7216, 33380},
							{25349, 189534, -88969, -7216, 32768},
							{25346, 173195, -76560, -7215, 49277},
							{25342, 175591, -72744, -7215, 49317}
					},
					{
							{25342, 191231, -85574, -7216, 33380},
							{25339, 189534, -88969, -7216, 32768},
							{25349, 173195, -76560, -7215, 49277},
							{25346, 175591, -72744, -7215, 49317}
					},
					{
							{25346, 191231, -85574, -7216, 33380},
							{25342, 189534, -88969, -7216, 32768},
							{25339, 173195, -76560, -7215, 49277},
							{25349, 175591, -72744, -7215, 49317}
					},
					{
							{25349, 191231, -85574, -7216, 33380},
							{25346, 189534, -88969, -7216, 32768},
							{25342, 173195, -76560, -7215, 49277},
							{25339, 175591, -72744, -7215, 49317}
					},
			};
	// @formatter:on

	protected Map<Integer, Boolean> _archonSpawned = new ConcurrentHashMap<>();
	protected Map<Integer, Boolean> _hallInUse = new ConcurrentHashMap<>();
	protected Map<Integer, Player> _challengers = new ConcurrentHashMap<>();
	protected Map<Integer, int[]> _startHallSpawns = new HashMap<>();
	protected Map<Integer, Integer> _hallGateKeepers = new HashMap<>();
	protected Map<Integer, Integer> _keyBoxNpc = new HashMap<>();
	protected Map<Integer, Integer> _victim = new HashMap<>();
	protected Map<Integer, L2Spawn> _executionerSpawns = new HashMap<>();
	protected Map<Integer, L2Spawn> _keyBoxSpawns = new HashMap<>();
	protected Map<Integer, L2Spawn> _mysteriousBoxSpawns = new HashMap<>();
	protected Map<Integer, L2Spawn> _shadowSpawns = new HashMap<>();
	protected Map<Integer, Set<L2Spawn>> _dukeFinalMobs = new HashMap<>();
	protected Map<Integer, Set<SepulcherMonsterInstance>> _dukeMobs = new HashMap<>();
	protected Map<Integer, Set<L2Spawn>> _emperorsGraveNpcs = new HashMap<>();
	protected Map<Integer, Set<L2Spawn>> _magicalMonsters = new HashMap<>();
	protected Map<Integer, Set<L2Spawn>> _physicalMonsters = new HashMap<>();
	protected Map<Integer, Set<SepulcherMonsterInstance>> _viscountMobs = new HashMap<>();

	protected Set<L2Spawn> _physicalSpawns;
	protected Set<L2Spawn> _magicalSpawns;
	protected Set<L2Spawn> _managers;
	protected Set<L2Spawn> _dukeFinalSpawns;
	protected Set<L2Spawn> _emperorsGraveSpawns;
	protected Set<Npc> _allMobs = ConcurrentHashMap.newKeySet();

	private long _attackTimeEnd = 0;
	private long _coolDownTimeEnd = 0;
	private long _entryTimeEnd = 0;
	private long _warmUpTimeEnd = 0;

	private final byte _newCycleMin = 55;

	protected FourSepulchersManager() {
		if (_changeCoolDownTimeTask != null) {
			_changeCoolDownTimeTask.cancel(true);
		}
		if (_changeEntryTimeTask != null) {
			_changeEntryTimeTask.cancel(true);
		}
		if (_changeWarmUpTimeTask != null) {
			_changeWarmUpTimeTask.cancel(true);
		}
		if (_changeAttackTimeTask != null) {
			_changeAttackTimeTask.cancel(true);
		}

		_changeCoolDownTimeTask = null;
		_changeEntryTimeTask = null;
		_changeWarmUpTimeTask = null;
		_changeAttackTimeTask = null;

		_inEntryTime = false;
		_inWarmUpTime = false;
		_inAttackTime = false;
		_inCoolDownTime = false;

		_firstTimeRun = true;
		initFixedInfo();
		loadMysteriousBox();
		initKeyBoxSpawns();
		loadPhysicalMonsters();
		loadMagicalMonsters();
		initLocationShadowSpawns();
		initExecutionerSpawns();
		loadDukeMonsters();
		loadEmperorsGraveMonsters();
		spawnManagers();
		timeSelector();
	}

	// phase select on server launch
	protected void timeSelector() {
		timeCalculator();
		long currentTime = Calendar.getInstance().getTimeInMillis();
		// if current time >= time of entry beginning and if current time < time of entry beginning + time of entry end
		if ((currentTime >= _coolDownTimeEnd) && (currentTime < _entryTimeEnd)) // entry time check
		{
			clean();
			_changeEntryTimeTask = ThreadPool.getInstance().scheduleGeneral(new FourSepulchersChangeEntryTimeTask(), 0, TimeUnit.MILLISECONDS);
			log.info("Beginning in Entry time");
		} else if ((currentTime >= _entryTimeEnd) && (currentTime < _warmUpTimeEnd)) // warmup time check
		{
			clean();
			_changeWarmUpTimeTask = ThreadPool.getInstance().scheduleGeneral(new FourSepulchersChangeWarmUpTimeTask(), 0, TimeUnit.MILLISECONDS);
			log.info("Beginning in WarmUp time");
		} else if ((currentTime >= _warmUpTimeEnd) && (currentTime < _attackTimeEnd)) // attack time check
		{
			clean();
			_changeAttackTimeTask = ThreadPool.getInstance().scheduleGeneral(new FourSepulchersChangeAttackTimeTask(), 0, TimeUnit.MILLISECONDS);
			log.info("Beginning in Attack time");
		} else
		// else cooldown time and without cleanup because it's already implemented
		{
			_changeCoolDownTimeTask = ThreadPool.getInstance().scheduleGeneral(new FourSepulchersChangeCoolDownTimeTask(), 0, TimeUnit.MILLISECONDS);
			log.info("Beginning in Cooldown time");
		}
	}

	// phase end times calculator
	protected void timeCalculator() {
		Calendar tmp = Calendar.getInstance();
		if (tmp.get(Calendar.MINUTE) < _newCycleMin) {
			tmp.set(Calendar.HOUR, Calendar.getInstance().get(Calendar.HOUR) - 1);
		}
		tmp.set(Calendar.MINUTE, _newCycleMin);
		_coolDownTimeEnd = tmp.getTimeInMillis();
		_entryTimeEnd = _coolDownTimeEnd + (GeneralConfig.FS_TIME_ENTRY * 60000L);
		_warmUpTimeEnd = _entryTimeEnd + (GeneralConfig.FS_TIME_WARMUP * 60000L);
		_attackTimeEnd = _warmUpTimeEnd + (GeneralConfig.FS_TIME_ATTACK * 60000L);
	}

	public void clean() {
		deleteAllMobs();
		closeAllDoors();

		_hallInUse.clear();
		_hallInUse.put(31921, false);
		_hallInUse.put(31922, false);
		_hallInUse.put(31923, false);
		_hallInUse.put(31924, false);

		if (_archonSpawned.size() != 0) {
			Set<Integer> npcIdSet = _archonSpawned.keySet();
			for (int npcId : npcIdSet) {
				_archonSpawned.put(npcId, false);
			}
		}
	}

	protected void spawnManagers() {
		_managers = ConcurrentHashMap.newKeySet();

		for (int i = 31921; i <= 31924; i++) {
			if ((i < 31921) || (i > 31924)) {
				continue;
			}

			try {
				final L2Spawn spawnDat = new L2Spawn(i);

				spawnDat.setAmount(1);
				spawnDat.setRespawnDelay(60);
				switch (i) {
					case 31921: // conquerors
						spawnDat.setXYZ(181061, -85595, -7200);
						spawnDat.setHeading(-32584);
						break;
					case 31922: // emperors
						spawnDat.setXYZ(179292, -88981, -7200);
						spawnDat.setHeading(-33272);
						break;
					case 31923: // sages
						spawnDat.setXYZ(173202, -87004, -7200);
						spawnDat.setHeading(-16248);
						break;
					case 31924: // judges
						spawnDat.setXYZ(175606, -82853, -7200);
						spawnDat.setHeading(-16248);
						break;
				}
				_managers.add(spawnDat);
				SpawnTable.getInstance().addNewSpawn(spawnDat, false);
				spawnDat.doSpawn();
				spawnDat.startRespawn();
				log.info("Spawned {}", spawnDat.getTemplate().getName());
			} catch (Exception e) {
				log.warn("Error while spawning managers: ", e);
			}
		}
	}

	protected void initFixedInfo() {
		_startHallSpawns.put(31921, _startHallSpawn[0]);
		_startHallSpawns.put(31922, _startHallSpawn[1]);
		_startHallSpawns.put(31923, _startHallSpawn[2]);
		_startHallSpawns.put(31924, _startHallSpawn[3]);

		_hallInUse.put(31921, false);
		_hallInUse.put(31922, false);
		_hallInUse.put(31923, false);
		_hallInUse.put(31924, false);

		_hallGateKeepers.put(31925, 25150012);
		_hallGateKeepers.put(31926, 25150013);
		_hallGateKeepers.put(31927, 25150014);
		_hallGateKeepers.put(31928, 25150015);
		_hallGateKeepers.put(31929, 25150016);
		_hallGateKeepers.put(31930, 25150002);
		_hallGateKeepers.put(31931, 25150003);
		_hallGateKeepers.put(31932, 25150004);
		_hallGateKeepers.put(31933, 25150005);
		_hallGateKeepers.put(31934, 25150006);
		_hallGateKeepers.put(31935, 25150032);
		_hallGateKeepers.put(31936, 25150033);
		_hallGateKeepers.put(31937, 25150034);
		_hallGateKeepers.put(31938, 25150035);
		_hallGateKeepers.put(31939, 25150036);
		_hallGateKeepers.put(31940, 25150022);
		_hallGateKeepers.put(31941, 25150023);
		_hallGateKeepers.put(31942, 25150024);
		_hallGateKeepers.put(31943, 25150025);
		_hallGateKeepers.put(31944, 25150026);

		_keyBoxNpc.put(18120, 31455);
		_keyBoxNpc.put(18121, 31455);
		_keyBoxNpc.put(18122, 31455);
		_keyBoxNpc.put(18123, 31455);
		_keyBoxNpc.put(18124, 31456);
		_keyBoxNpc.put(18125, 31456);
		_keyBoxNpc.put(18126, 31456);
		_keyBoxNpc.put(18127, 31456);
		_keyBoxNpc.put(18128, 31457);
		_keyBoxNpc.put(18129, 31457);
		_keyBoxNpc.put(18130, 31457);
		_keyBoxNpc.put(18131, 31457);
		_keyBoxNpc.put(18149, 31458);
		_keyBoxNpc.put(18150, 31459);
		_keyBoxNpc.put(18151, 31459);
		_keyBoxNpc.put(18152, 31459);
		_keyBoxNpc.put(18153, 31459);
		_keyBoxNpc.put(18154, 31460);
		_keyBoxNpc.put(18155, 31460);
		_keyBoxNpc.put(18156, 31460);
		_keyBoxNpc.put(18157, 31460);
		_keyBoxNpc.put(18158, 31461);
		_keyBoxNpc.put(18159, 31461);
		_keyBoxNpc.put(18160, 31461);
		_keyBoxNpc.put(18161, 31461);
		_keyBoxNpc.put(18162, 31462);
		_keyBoxNpc.put(18163, 31462);
		_keyBoxNpc.put(18164, 31462);
		_keyBoxNpc.put(18165, 31462);
		_keyBoxNpc.put(18183, 31463);
		_keyBoxNpc.put(18184, 31464);
		_keyBoxNpc.put(18212, 31465);
		_keyBoxNpc.put(18213, 31465);
		_keyBoxNpc.put(18214, 31465);
		_keyBoxNpc.put(18215, 31465);
		_keyBoxNpc.put(18216, 31466);
		_keyBoxNpc.put(18217, 31466);
		_keyBoxNpc.put(18218, 31466);
		_keyBoxNpc.put(18219, 31466);

		_victim.put(18150, 18158);
		_victim.put(18151, 18159);
		_victim.put(18152, 18160);
		_victim.put(18153, 18161);
		_victim.put(18154, 18162);
		_victim.put(18155, 18163);
		_victim.put(18156, 18164);
		_victim.put(18157, 18165);
	}

	private void loadMysteriousBox() {
		_mysteriousBoxSpawns.clear();

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY id")) {
			ps.setInt(1, 0);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					final L2Spawn spawnDat = new L2Spawn(rs.getInt("npc_templateid"));
					spawnDat.setAmount(rs.getInt("count"));
					spawnDat.setXYZ(rs.getInt("locx"), rs.getInt("locy"), rs.getInt("locz"));
					spawnDat.setHeading(rs.getInt("heading"));
					spawnDat.setRespawnDelay(rs.getInt("respawn_delay"));
					SpawnTable.getInstance().addNewSpawn(spawnDat, false);
					int keyNpcId = rs.getInt("key_npc_id");
					_mysteriousBoxSpawns.put(keyNpcId, spawnDat);
				}
			}
			log.info("loaded {} Mysterious-Box spawns.", _mysteriousBoxSpawns.size());
		} catch (Exception e) {
			// problem with initializing spawn, go to next one
			log.warn("LoadMysteriousBox: Spawn could not be initialized: ", e);
		}
	}

	private void initKeyBoxSpawns() {
		for (Entry<Integer, Integer> keyNpc : _keyBoxNpc.entrySet()) {
			try {
				final L2Spawn spawnDat = new L2Spawn(keyNpc.getValue());
				spawnDat.setAmount(1);
				spawnDat.setXYZ(0, 0, 0);
				spawnDat.setHeading(0);
				spawnDat.setRespawnDelay(3600);
				SpawnTable.getInstance().addNewSpawn(spawnDat, false);
				_keyBoxSpawns.put(keyNpc.getKey(), spawnDat);
			} catch (Exception e) {
				log.warn("InitKeyBoxSpawns: Spawn could not be initialized: ", e);
			}
		}
	}

	private void loadPhysicalMonsters() {
		_physicalMonsters.clear();

		int loaded = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id")) {
			ps1.setInt(1, 1);
			try (ResultSet rs1 = ps1.executeQuery();
				 PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist Where key_npc_id = ? and spawntype = ? ORDER BY id")) {
				while (rs1.next()) {
					int keyNpcId = rs1.getInt("key_npc_id");

					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 1);
					try (ResultSet rs2 = ps2.executeQuery()) {
						_physicalSpawns = ConcurrentHashMap.newKeySet();
						while (rs2.next()) {
							final L2Spawn spawnDat = new L2Spawn(rs2.getInt("npc_templateid"));
							spawnDat.setAmount(rs2.getInt("count"));
							spawnDat.setXYZ(rs2.getInt("locx"), rs2.getInt("locy"), rs2.getInt("locz"));
							spawnDat.setHeading(rs2.getInt("heading"));
							spawnDat.setRespawnDelay(rs2.getInt("respawn_delay"));
							SpawnTable.getInstance().addNewSpawn(spawnDat, false);
							_physicalSpawns.add(spawnDat);
							loaded++;
						}
					}
					ps2.clearParameters();
					_physicalMonsters.put(keyNpcId, _physicalSpawns);
				}
			}
			log.info("Loaded {} Physical type monsters spawns.", loaded);
		} catch (Exception e) {
			log.warn("FourSepulchersManager.LoadPhysicalMonsters: Spawn could not be initialized: ", e);
		}
	}

	private void loadMagicalMonsters() {
		_magicalMonsters.clear();

		int loaded = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id")) {
			ps1.setInt(1, 2);
			try (ResultSet rs1 = ps1.executeQuery();
				 PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? AND spawntype = ? ORDER BY id")) {
				while (rs1.next()) {
					int keyNpcId = rs1.getInt("key_npc_id");

					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 2);
					try (ResultSet rset2 = ps2.executeQuery()) {
						_magicalSpawns = ConcurrentHashMap.newKeySet();

						while (rset2.next()) {
							final L2Spawn spawnDat = new L2Spawn(rset2.getInt("npc_templateid"));
							spawnDat.setAmount(rset2.getInt("count"));
							spawnDat.setXYZ(rset2.getInt("locx"), rset2.getInt("locy"), rset2.getInt("locz"));
							spawnDat.setHeading(rset2.getInt("heading"));
							spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
							SpawnTable.getInstance().addNewSpawn(spawnDat, false);
							_magicalSpawns.add(spawnDat);
							loaded++;
						}
					}
					ps2.clearParameters();
					_magicalMonsters.put(keyNpcId, _magicalSpawns);
				}
			}
			log.info("Loaded {} Magical type monsters spawns.", loaded);
		} catch (Exception e) {
			log.warn("LoadMagicalMonsters: Spawn could not be initialized: ", e);
		}
	}

	private void loadDukeMonsters() {
		_dukeFinalMobs.clear();
		_archonSpawned.clear();

		int loaded = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id")) {
			ps1.setInt(1, 5);
			try (ResultSet rs1 = ps1.executeQuery();
				 PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? AND spawntype = ? ORDER BY id")) {
				while (rs1.next()) {
					int keyNpcId = rs1.getInt("key_npc_id");

					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 5);
					try (ResultSet rset2 = ps2.executeQuery()) {
						ps2.clearParameters();

						_dukeFinalSpawns = ConcurrentHashMap.newKeySet();

						while (rset2.next()) {
							final L2Spawn spawnDat = new L2Spawn(rset2.getInt("npc_templateid"));
							spawnDat.setAmount(rset2.getInt("count"));
							spawnDat.setXYZ(rset2.getInt("locx"), rset2.getInt("locy"), rset2.getInt("locz"));
							spawnDat.setHeading(rset2.getInt("heading"));
							spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
							SpawnTable.getInstance().addNewSpawn(spawnDat, false);
							_dukeFinalSpawns.add(spawnDat);
							loaded++;
						}
					}
					ps2.clearParameters();
					_dukeFinalMobs.put(keyNpcId, _dukeFinalSpawns);
					_archonSpawned.put(keyNpcId, false);
				}
			}
			log.info("Loaded {} Church of duke monsters spawns.", loaded);
		} catch (Exception e) {
			log.warn("LoadDukeMonsters: Spawn could not be initialized: ", e);
		}
	}

	private void loadEmperorsGraveMonsters() {
		_emperorsGraveNpcs.clear();

		int loaded = 0;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id")) {
			ps1.setInt(1, 6);
			try (ResultSet rs1 = ps1.executeQuery();
				 PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? and spawntype = ? ORDER BY id")) {
				while (rs1.next()) {
					int keyNpcId = rs1.getInt("key_npc_id");

					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 6);
					try (ResultSet rs2 = ps2.executeQuery()) {
						_emperorsGraveSpawns = ConcurrentHashMap.newKeySet();

						while (rs2.next()) {
							final L2Spawn spawnDat = new L2Spawn(rs2.getInt("npc_templateid"));
							spawnDat.setAmount(rs2.getInt("count"));
							spawnDat.setXYZ(rs2.getInt("locx"), rs2.getInt("locy"), rs2.getInt("locz"));
							spawnDat.setHeading(rs2.getInt("heading"));
							spawnDat.setRespawnDelay(rs2.getInt("respawn_delay"));
							SpawnTable.getInstance().addNewSpawn(spawnDat, false);
							_emperorsGraveSpawns.add(spawnDat);
							loaded++;
						}
					}
					ps2.clearParameters();
					_emperorsGraveNpcs.put(keyNpcId, _emperorsGraveSpawns);
				}
			}
			log.info("Loaded {} Emperor's grave NPC spawns.", loaded);
		} catch (Exception e) {
			log.warn("LoadEmperorsGraveMonsters: Spawn could not be initialized: ", e);
		}
	}

	protected void initLocationShadowSpawns() {
		int locNo = Rnd.get(4);
		final int[] gateKeeper =
				{
						31929,
						31934,
						31939,
						31944
				};

		_shadowSpawns.clear();

		for (int i = 0; i <= 3; i++) {
			try {
				final L2Spawn spawnDat = new L2Spawn(_shadowSpawnLoc[locNo][i][0]);
				spawnDat.setAmount(1);
				spawnDat.setXYZ(_shadowSpawnLoc[locNo][i][1], _shadowSpawnLoc[locNo][i][2], _shadowSpawnLoc[locNo][i][3]);
				spawnDat.setHeading(_shadowSpawnLoc[locNo][i][4]);
				SpawnTable.getInstance().addNewSpawn(spawnDat, false);
				int keyNpcId = gateKeeper[i];
				_shadowSpawns.put(keyNpcId, spawnDat);
			} catch (Exception e) {
				log.error("Error on InitLocationShadowSpawns", e);
			}
		}
	}

	protected void initExecutionerSpawns() {
		for (int keyNpcId : _victim.keySet()) {
			try {
				final L2Spawn spawnDat = new L2Spawn(_victim.get(keyNpcId));
				spawnDat.setAmount(1);
				spawnDat.setXYZ(0, 0, 0);
				spawnDat.setHeading(0);
				spawnDat.setRespawnDelay(3600);
				SpawnTable.getInstance().addNewSpawn(spawnDat, false);
				_executionerSpawns.put(keyNpcId, spawnDat);
			} catch (Exception e) {
				log.warn("InitExecutionerSpawns: Spawn could not be initialized: ", e);
			}
		}
	}

	public ScheduledFuture<?> getChangeAttackTimeTask() {
		return _changeAttackTimeTask;
	}

	public void setChangeAttackTimeTask(ScheduledFuture<?> task) {
		_changeAttackTimeTask = task;
	}

	public ScheduledFuture<?> getChangeCoolDownTimeTask() {
		return _changeCoolDownTimeTask;
	}

	public void setChangeCoolDownTimeTask(ScheduledFuture<?> task) {
		_changeCoolDownTimeTask = task;
	}

	public ScheduledFuture<?> getChangeEntryTimeTask() {
		return _changeEntryTimeTask;
	}

	public void setChangeEntryTimeTask(ScheduledFuture<?> task) {
		_changeEntryTimeTask = task;
	}

	public ScheduledFuture<?> getChangeWarmUpTimeTask() {
		return _changeWarmUpTimeTask;
	}

	public void setChangeWarmUpTimeTask(ScheduledFuture<?> task) {
		_changeWarmUpTimeTask = task;
	}

	public long getAttackTimeEnd() {
		return _attackTimeEnd;
	}

	public void setAttackTimeEnd(long attackTimeEnd) {
		_attackTimeEnd = attackTimeEnd;
	}

	public byte getCycleMin() {
		return _newCycleMin;
	}

	public long getEntrytTimeEnd() {
		return _entryTimeEnd;
	}

	public void setEntryTimeEnd(long entryTimeEnd) {
		_entryTimeEnd = entryTimeEnd;
	}

	public long getWarmUpTimeEnd() {
		return _warmUpTimeEnd;
	}

	public void setWarmUpTimeEnd(long warmUpTimeEnd) {
		_warmUpTimeEnd = warmUpTimeEnd;
	}

	public boolean isAttackTime() {
		return _inAttackTime;
	}

	public void setIsAttackTime(boolean attackTime) {
		_inAttackTime = attackTime;
	}

	public boolean isCoolDownTime() {
		return _inCoolDownTime;
	}

	public void setIsCoolDownTime(boolean isCoolDownTime) {
		_inCoolDownTime = isCoolDownTime;
	}

	public boolean isEntryTime() {
		return _inEntryTime;
	}

	public void setIsEntryTime(boolean entryTime) {
		_inEntryTime = entryTime;
	}

	public boolean isFirstTimeRun() {
		return _firstTimeRun;
	}

	public void setIsFirstTimeRun(boolean isFirstTimeRun) {
		_firstTimeRun = isFirstTimeRun;
	}

	public boolean isWarmUpTime() {
		return _inWarmUpTime;
	}

	public void setIsWarmUpTime(boolean isWarmUpTime) {
		_inWarmUpTime = isWarmUpTime;
	}

	public synchronized void tryEntry(Npc npc, Player player) {
		Quest hostQuest = QuestManager.getInstance().getQuest(QUEST_ID);
		if (hostQuest == null) {
			log.warn("Couldn't find quest: {}", QUEST_ID);
			return;
		}
		int npcId = npc.getId();
		switch (npcId) {
			// ID ok
			case 31921:
			case 31922:
			case 31923:
			case 31924:
				break;
			// ID not ok
			default:
				if (!player.isGM()) {
					log.warn("Player{} tried to cheat in four sepulchers.", player);
					Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " tried to enter four sepulchers with invalid npc id.", GeneralConfig.DEFAULT_PUNISH);
				}
				return;
		}

		if (_hallInUse.get(npcId).booleanValue()) {
			showHtmlFile(player, npcId + "-FULL.htm", npc, null);
			return;
		}

		if (GeneralConfig.FS_PARTY_MEMBER_COUNT > 1) {
			if (!player.isInParty() || (player.getParty().getMemberCount() < GeneralConfig.FS_PARTY_MEMBER_COUNT)) {
				showHtmlFile(player, npcId + "-SP.htm", npc, null);
				return;
			}

			if (!player.getParty().isLeader(player)) {
				showHtmlFile(player, npcId + "-NL.htm", npc, null);
				return;
			}

			for (Player mem : player.getParty().getMembers()) {
				QuestState qs = mem.getQuestState(hostQuest.getName());
				if ((qs == null) || (!qs.isStarted() && !qs.isCompleted())) {
					showHtmlFile(player, npcId + "-NS.htm", npc, mem);
					return;
				}
				if (mem.getInventory().getItemByItemId(ENTRANCE_PASS) == null) {
					showHtmlFile(player, npcId + "-SE.htm", npc, mem);
					return;
				}

				if (player.getWeightPenalty() >= 3) {
					mem.sendPacket(SystemMessageId.UNABLE_TO_PROCESS_THIS_REQUEST_UNTIL_YOUR_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY);
					return;
				}
			}
		} else if ((GeneralConfig.FS_PARTY_MEMBER_COUNT <= 1) && player.isInParty()) {
			if (!player.getParty().isLeader(player)) {
				showHtmlFile(player, npcId + "-NL.htm", npc, null);
				return;
			}
			for (Player mem : player.getParty().getMembers()) {
				QuestState qs = mem.getQuestState(hostQuest.getName());
				if ((qs == null) || (!qs.isStarted() && !qs.isCompleted())) {
					showHtmlFile(player, npcId + "-NS.htm", npc, mem);
					return;
				}
				if (mem.getInventory().getItemByItemId(ENTRANCE_PASS) == null) {
					showHtmlFile(player, npcId + "-SE.htm", npc, mem);
					return;
				}

				if (player.getWeightPenalty() >= 3) {
					mem.sendPacket(SystemMessageId.UNABLE_TO_PROCESS_THIS_REQUEST_UNTIL_YOUR_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY);
					return;
				}
			}
		} else {
			QuestState qs = player.getQuestState(hostQuest.getName());
			if ((qs == null) || (!qs.isStarted() && !qs.isCompleted())) {
				showHtmlFile(player, npcId + "-NS.htm", npc, player);
				return;
			}
			if (player.getInventory().getItemByItemId(ENTRANCE_PASS) == null) {
				showHtmlFile(player, npcId + "-SE.htm", npc, player);
				return;
			}

			if (player.getWeightPenalty() >= 3) {
				player.sendPacket(SystemMessageId.UNABLE_TO_PROCESS_THIS_REQUEST_UNTIL_YOUR_INVENTORY_S_WEIGHT_AND_SLOT_COUNT_ARE_LESS_THAN_80_PERCENT_OF_CAPACITY);
				return;
			}
		}

		if (!isEntryTime()) {
			showHtmlFile(player, npcId + "-NE.htm", npc, null);
			return;
		}

		showHtmlFile(player, npcId + "-OK.htm", npc, null);

		entry(npcId, player);
	}

	private void entry(int npcId, Player player) {
		int[] Location = _startHallSpawns.get(npcId);
		int driftx;
		int drifty;

		if (GeneralConfig.FS_PARTY_MEMBER_COUNT > 1) {
			final List<Player> members = new LinkedList<>();
			for (Player mem : player.getParty().getMembers()) {
				if (!mem.isDead() && Util.checkIfInRange(700, player, mem, true)) {
					members.add(mem);
				}
			}

			for (Player mem : members) {
				driftx = Rnd.get(-80, 80);
				drifty = Rnd.get(-80, 80);
				mem.teleToLocation(Location[0] + driftx, Location[1] + drifty, Location[2]);
				mem.destroyItemByItemId("Quest", ENTRANCE_PASS, 1, mem, true);
				if (mem.getInventory().getItemByItemId(ANTIQUE_BROOCH) == null) {
					mem.addItem("Quest", USED_PASS, 1, mem, true);
				}

				ItemInstance hallsKey = mem.getInventory().getItemByItemId(CHAPEL_KEY);
				if (hallsKey != null) {
					mem.destroyItemByItemId("Quest", CHAPEL_KEY, hallsKey.getCount(), mem, true);
				}
			}

			_challengers.put(npcId, player);

			_hallInUse.put(npcId, true);
		}
		if ((GeneralConfig.FS_PARTY_MEMBER_COUNT <= 1) && player.isInParty()) {
			final List<Player> members = new LinkedList<>();
			for (Player mem : player.getParty().getMembers()) {
				if (!mem.isDead() && Util.checkIfInRange(700, player, mem, true)) {
					members.add(mem);
				}
			}

			for (Player mem : members) {
				driftx = Rnd.get(-80, 80);
				drifty = Rnd.get(-80, 80);
				mem.teleToLocation(Location[0] + driftx, Location[1] + drifty, Location[2]);
				mem.destroyItemByItemId("Quest", ENTRANCE_PASS, 1, mem, true);
				if (mem.getInventory().getItemByItemId(ANTIQUE_BROOCH) == null) {
					mem.addItem("Quest", USED_PASS, 1, mem, true);
				}

				ItemInstance hallsKey = mem.getInventory().getItemByItemId(CHAPEL_KEY);
				if (hallsKey != null) {
					mem.destroyItemByItemId("Quest", CHAPEL_KEY, hallsKey.getCount(), mem, true);
				}
			}

			_challengers.put(npcId, player);

			_hallInUse.put(npcId, true);
		} else {
			driftx = Rnd.get(-80, 80);
			drifty = Rnd.get(-80, 80);
			player.teleToLocation(Location[0] + driftx, Location[1] + drifty, Location[2]);
			player.destroyItemByItemId("Quest", ENTRANCE_PASS, 1, player, true);
			if (player.getInventory().getItemByItemId(ANTIQUE_BROOCH) == null) {
				player.addItem("Quest", USED_PASS, 1, player, true);
			}

			ItemInstance hallsKey = player.getInventory().getItemByItemId(CHAPEL_KEY);
			if (hallsKey != null) {
				player.destroyItemByItemId("Quest", CHAPEL_KEY, hallsKey.getCount(), player, true);
			}

			_challengers.put(npcId, player);

			_hallInUse.put(npcId, true);
		}
	}

	public void spawnMysteriousBox(int npcId) {
		if (!isAttackTime()) {
			return;
		}

		L2Spawn spawnDat = _mysteriousBoxSpawns.get(npcId);
		if (spawnDat != null) {
			_allMobs.add(spawnDat.doSpawn());
			spawnDat.stopRespawn();
		}
	}

	public void spawnMonster(int npcId) {
		if (!isAttackTime()) {
			return;
		}

		Set<L2Spawn> monsterList;
		Set<SepulcherMonsterInstance> mobs = ConcurrentHashMap.newKeySet();
		L2Spawn keyBoxMobSpawn;

		if (Rnd.get(2) == 0) {
			monsterList = _physicalMonsters.get(npcId);
		} else {
			monsterList = _magicalMonsters.get(npcId);
		}

		if (monsterList != null) {
			boolean spawnKeyBoxMob = false;
			boolean spawnedKeyBoxMob = false;

			for (L2Spawn spawnDat : monsterList) {
				if (spawnedKeyBoxMob) {
					spawnKeyBoxMob = false;
				} else {
					switch (npcId) {
						case 31469:
						case 31474:
						case 31479:
						case 31484:
							if (Rnd.get(48) == 0) {
								spawnKeyBoxMob = true;
							}
							break;
						default:
							spawnKeyBoxMob = false;
					}
				}

				SepulcherMonsterInstance mob = null;

				if (spawnKeyBoxMob) {
					try {
						keyBoxMobSpawn = new L2Spawn(18149);
						keyBoxMobSpawn.setAmount(1);
						keyBoxMobSpawn.setXYZ(spawnDat);
						keyBoxMobSpawn.setRespawnDelay(3600);
						SpawnTable.getInstance().addNewSpawn(keyBoxMobSpawn, false);
						mob = (SepulcherMonsterInstance) keyBoxMobSpawn.doSpawn();
						keyBoxMobSpawn.stopRespawn();
					} catch (Exception e) {
						log.warn("SpawnMonster: Spawn could not be initialized: ", e);
					}

					spawnedKeyBoxMob = true;
				} else {
					mob = (SepulcherMonsterInstance) spawnDat.doSpawn();
					spawnDat.stopRespawn();
				}

				if (mob != null) {
					mob.mysteriousBoxId = npcId;
					switch (npcId) {
						case 31469:
						case 31474:
						case 31479:
						case 31484:
						case 31472:
						case 31477:
						case 31482:
						case 31487:
							mobs.add(mob);
					}
					_allMobs.add(mob);
				}
			}

			switch (npcId) {
				case 31469:
				case 31474:
				case 31479:
				case 31484:
					_viscountMobs.put(npcId, mobs);
					break;

				case 31472:
				case 31477:
				case 31482:
				case 31487:
					_dukeMobs.put(npcId, mobs);
					break;
			}
		}
	}

	public synchronized boolean isViscountMobsAnnihilated(int npcId) {
		Set<SepulcherMonsterInstance> mobs = _viscountMobs.get(npcId);
		if (mobs == null) {
			return true;
		}

		for (SepulcherMonsterInstance mob : mobs) {
			if (!mob.isDead()) {
				return false;
			}
		}

		return true;
	}

	public synchronized boolean isDukeMobsAnnihilated(int npcId) {
		Set<SepulcherMonsterInstance> mobs = _dukeMobs.get(npcId);
		if (mobs == null) {
			return true;
		}

		for (SepulcherMonsterInstance mob : mobs) {
			if (!mob.isDead()) {
				return false;
			}
		}

		return true;
	}

	public void spawnKeyBox(Npc activeChar) {
		if (!isAttackTime()) {
			return;
		}

		L2Spawn spawnDat = _keyBoxSpawns.get(activeChar.getId());
		if (spawnDat != null) {
			spawnDat.setAmount(1);
			spawnDat.setXYZ(activeChar);
			spawnDat.setHeading(activeChar.getHeading());
			spawnDat.setRespawnDelay(3600);
			_allMobs.add(spawnDat.doSpawn());
			spawnDat.stopRespawn();
		}
	}

	public void spawnExecutionerOfHalisha(Npc activeChar) {
		if (!isAttackTime()) {
			return;
		}

		L2Spawn spawnDat = _executionerSpawns.get(activeChar.getId());
		if (spawnDat != null) {
			spawnDat.setAmount(1);
			spawnDat.setXYZ(activeChar);
			spawnDat.setHeading(activeChar.getHeading());
			spawnDat.setRespawnDelay(3600);
			_allMobs.add(spawnDat.doSpawn());
			spawnDat.stopRespawn();
		}
	}

	public void spawnArchonOfHalisha(int npcId) {
		if (!isAttackTime()) {
			return;
		}

		if (_archonSpawned.get(npcId)) {
			return;
		}

		Set<L2Spawn> monsterList = _dukeFinalMobs.get(npcId);
		if (monsterList != null) {
			for (L2Spawn spawnDat : monsterList) {
				SepulcherMonsterInstance mob = (SepulcherMonsterInstance) spawnDat.doSpawn();
				spawnDat.stopRespawn();

				if (mob != null) {
					mob.mysteriousBoxId = npcId;
					_allMobs.add(mob);
				}
			}
			_archonSpawned.put(npcId, true);
		}
	}

	public void spawnEmperorsGraveNpc(int npcId) {
		if (!isAttackTime()) {
			return;
		}

		Set<L2Spawn> monsterList = _emperorsGraveNpcs.get(npcId);
		if (monsterList != null) {
			for (L2Spawn spawnDat : monsterList) {
				_allMobs.add(spawnDat.doSpawn());
				spawnDat.stopRespawn();
			}
		}
	}

	public void locationShadowSpawns() {
		int locNo = Rnd.get(4);
		final int[] gateKeeper =
				{
						31929,
						31934,
						31939,
						31944
				};

		for (int i = 0; i <= 3; i++) {
			int keyNpcId = gateKeeper[i];
			L2Spawn spawnDat = _shadowSpawns.get(keyNpcId);
			spawnDat.setXYZ(_shadowSpawnLoc[locNo][i][1], _shadowSpawnLoc[locNo][i][2], _shadowSpawnLoc[locNo][i][3]);
			spawnDat.setHeading(_shadowSpawnLoc[locNo][i][4]);
			_shadowSpawns.put(keyNpcId, spawnDat);
		}
	}

	public void spawnShadow(int npcId) {
		if (!isAttackTime()) {
			return;
		}

		L2Spawn spawnDat = _shadowSpawns.get(npcId);
		if (spawnDat != null) {
			SepulcherMonsterInstance mob = (SepulcherMonsterInstance) spawnDat.doSpawn();
			spawnDat.stopRespawn();

			if (mob != null) {
				mob.mysteriousBoxId = npcId;
				_allMobs.add(mob);
			}
		}
	}

	public void deleteAllMobs() {
		for (Npc mob : _allMobs) {
			if (mob == null) {
				continue;
			}

			try {
				if (mob.getSpawn() != null) {
					mob.getSpawn().stopRespawn();
				}
				mob.deleteMe();
			} catch (Exception e) {
				log.error("Failed deleting mob.", e);
			}
		}
		_allMobs.clear();
	}

	protected void closeAllDoors() {
		for (int doorId : _hallGateKeepers.values()) {
			try {
				DoorInstance door = DoorData.getInstance().getDoor(doorId);
				if (door != null) {
					door.closeMe();
				} else {
					log.warn("Attempted to close undefined door. doorId: {}", doorId);
				}
			} catch (Exception e) {
				log.error("Failed closing door", e);
			}
		}
	}

	protected byte minuteSelect(byte min) {
		if (((double) min % 5) != 0)// if doesn't divides on 5 fully
		{
			// mad table for selecting proper minutes...
			// may be there is a better way to do this
			switch (min) {
				case 6:
				case 7:
					min = 5;
					break;
				case 8:
				case 9:
				case 11:
				case 12:
					min = 10;
					break;
				case 13:
				case 14:
				case 16:
				case 17:
					min = 15;
					break;
				case 18:
				case 19:
				case 21:
				case 22:
					min = 20;
					break;
				case 23:
				case 24:
				case 26:
				case 27:
					min = 25;
					break;
				case 28:
				case 29:
				case 31:
				case 32:
					min = 30;
					break;
				case 33:
				case 34:
				case 36:
				case 37:
					min = 35;
					break;
				case 38:
				case 39:
				case 41:
				case 42:
					min = 40;
					break;
				case 43:
				case 44:
				case 46:
				case 47:
					min = 45;
					break;
				case 48:
				case 49:
				case 51:
				case 52:
					min = 50;
					break;
				case 53:
				case 54:
				case 56:
				case 57:
					min = 55;
					break;
			}
		}
		return min;
	}

	public void managerSay(byte min) {
		// for attack phase, sending message every 5 minutes
		if (_inAttackTime) {
			if (min < 5) {
				return; // do not shout when < 5 minutes
			}

			min = minuteSelect(min);

			NpcStringId msg = NpcStringId.MINUTE_S_HAVE_PASSED;

			if (min == 90) {
				msg = NpcStringId.GAME_OVER_THE_TELEPORT_WILL_APPEAR_MOMENTARILY;
			}

			for (L2Spawn temp : _managers) {
				if (temp == null) {
					log.warn("managerSay(): manager is null");
					continue;
				}
				if (!(temp.getLastSpawn() instanceof SepulcherNpcInstance)) {
					log.warn("managerSay(): manager is not Sepulcher instance");
					continue;
				}
				// hall not used right now, so its manager will not tell you anything :)
				// if you don't need this - delete next two lines.
				if (!_hallInUse.get(temp.getId()).booleanValue()) {
					continue;
				}

				((SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg);
			}
		} else if (_inEntryTime) {
			NpcStringId msg1 = NpcStringId.YOU_MAY_NOW_ENTER_THE_SEPULCHER;
			NpcStringId msg2 = NpcStringId.IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER;
			for (L2Spawn temp : _managers) {
				if (temp == null) {
					log.warn("Something goes wrong in managerSay()...");
					continue;
				}
				if (!(temp.getLastSpawn() instanceof SepulcherNpcInstance)) {
					log.warn("Something goes wrong in managerSay()...");
					continue;
				}
				((SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg1);
				((SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg2);
			}
		}
	}

	public Map<Integer, Integer> getHallGateKeepers() {
		return _hallGateKeepers;
	}

	public void showHtmlFile(Player player, String file, Npc npc, Player member) {
		final NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
		html.setFile(player.getLang(), "SepulcherNpc/" + file);
		if (member != null) {
			html.replace("%member%", member.getName());
		}
		player.sendPacket(html);
	}
}