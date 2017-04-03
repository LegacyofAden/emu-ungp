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
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.instancemanager.tasks.GrandBossManagerStoreTask;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.instance.GrandBossInstance;
import org.l2junity.gameserver.model.interfaces.IStorable;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Grand Boss manager.
 *
 * @author DaRkRaGe Revised by Emperorc
 */
@Slf4j
@StartupComponent(value = "Service", dependency = NpcData.class)
public final class GrandBossManager implements IStorable {
	@Getter(lazy = true)
	private static final GrandBossManager instance = new GrandBossManager();

	// SQL queries
	private static final String UPDATE_GRAND_BOSS_DATA = "UPDATE grandboss_data set loc_x = ?, loc_y = ?, loc_z = ?, heading = ?, respawn_time = ?, currentHP = ?, currentMP = ?, status = ? where boss_id = ?";
	private static final String UPDATE_GRAND_BOSS_DATA2 = "UPDATE grandboss_data set status = ? where boss_id = ?";

	private static Map<Integer, GrandBossInstance> _bosses = new ConcurrentHashMap<>();

	private static Map<Integer, StatsSet> _storedInfo = new HashMap<>();

	private final Map<Integer, Integer> _bossStatus = new HashMap<>();

	private GrandBossManager() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement s = con.createStatement();
			 ResultSet rs = s.executeQuery("SELECT * from grandboss_data ORDER BY boss_id")) {
			while (rs.next()) {
				// Read all info from DB, and store it for AI to read and decide what to do
				// faster than accessing DB in real time
				StatsSet info = new StatsSet();
				int bossId = rs.getInt("boss_id");
				info.set("loc_x", rs.getInt("loc_x"));
				info.set("loc_y", rs.getInt("loc_y"));
				info.set("loc_z", rs.getInt("loc_z"));
				info.set("heading", rs.getInt("heading"));
				info.set("respawn_time", rs.getLong("respawn_time"));
				info.set("currentHP", rs.getDouble("currentHP"));
				info.set("currentMP", rs.getDouble("currentMP"));
				int status = rs.getInt("status");
				_bossStatus.put(bossId, status);
				_storedInfo.put(bossId, info);
				log.info("{}({}) status is {}", NpcData.getInstance().getTemplate(bossId).getName(), bossId, status);
				if (status > 0) {
					log.info("Next spawn date of {} is {}", NpcData.getInstance().getTemplate(bossId).getName(), new Date(info.getLong("respawn_time")));
				}
			}
			log.info("Loaded {} Instances", _storedInfo.size());
		} catch (SQLException e) {
			log.warn("Could not load grandboss_data table: {}", e.getMessage(), e);
		} catch (Exception e) {
			log.warn("Error while initializing GrandBossManager: {}", e.getMessage(), e);
		}
		ThreadPool.getInstance().scheduleGeneralAtFixedRate(new GrandBossManagerStoreTask(), 5 * 60 * 1000, 5 * 60 * 1000, TimeUnit.MILLISECONDS);
	}

	public int getBossStatus(int bossId) {
		return _bossStatus.get(bossId);
	}

	public void setBossStatus(int bossId, int status) {
		_bossStatus.put(bossId, status);
		log.info("{}: Updated {}({}) status to {}", this, NpcData.getInstance().getTemplate(bossId).getName(), bossId, status);
		updateDb(bossId, true);
	}

	/**
	 * Adds a GrandBossInstance to the list of bosses.
	 *
	 * @param boss
	 */
	public void addBoss(GrandBossInstance boss) {
		if (boss != null) {
			_bosses.put(boss.getId(), boss);
		}
	}

	public GrandBossInstance getBoss(int bossId) {
		return _bosses.get(bossId);
	}

	public StatsSet getStatsSet(int bossId) {
		return _storedInfo.get(bossId);
	}

	public void setStatsSet(int bossId, StatsSet info) {
		_storedInfo.put(bossId, info);
		updateDb(bossId, false);
	}

	@Override
	public boolean storeMe() {
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			for (Entry<Integer, StatsSet> e : _storedInfo.entrySet()) {
				final GrandBossInstance boss = _bosses.get(e.getKey());
				StatsSet info = e.getValue();
				if ((boss == null) || (info == null)) {
					try (PreparedStatement update = con.prepareStatement(UPDATE_GRAND_BOSS_DATA2)) {
						update.setInt(1, _bossStatus.get(e.getKey()));
						update.setInt(2, e.getKey());
						update.executeUpdate();
						update.clearParameters();
					}
				} else {
					try (PreparedStatement update = con.prepareStatement(UPDATE_GRAND_BOSS_DATA)) {
						update.setInt(1, (int) boss.getX());
						update.setInt(2, (int) boss.getY());
						update.setInt(3, (int) boss.getZ());
						update.setInt(4, boss.getHeading());
						update.setLong(5, info.getLong("respawn_time"));
						double hp = boss.getCurrentHp();
						double mp = boss.getCurrentMp();
						if (boss.isDead()) {
							hp = boss.getMaxHp();
							mp = boss.getMaxMp();
						}
						update.setDouble(6, hp);
						update.setDouble(7, mp);
						update.setInt(8, _bossStatus.get(e.getKey()));
						update.setInt(9, e.getKey());
						update.executeUpdate();
						update.clearParameters();
					}
				}
			}
		} catch (SQLException e) {
			log.warn("Couldn't store grandbosses to database: {}", e.getMessage(), e);
			return false;
		}
		return true;
	}

	private void updateDb(int bossId, boolean statusOnly) {
		try (Connection con = DatabaseFactory.getInstance().getConnection()) {
			GrandBossInstance boss = _bosses.get(bossId);
			StatsSet info = _storedInfo.get(bossId);

			if (statusOnly || (boss == null) || (info == null)) {
				try (PreparedStatement ps = con.prepareStatement(UPDATE_GRAND_BOSS_DATA2)) {
					ps.setInt(1, _bossStatus.get(bossId));
					ps.setInt(2, bossId);
					ps.executeUpdate();
				}
			} else {
				try (PreparedStatement ps = con.prepareStatement(UPDATE_GRAND_BOSS_DATA)) {
					ps.setInt(1, (int) boss.getX());
					ps.setInt(2, (int) boss.getY());
					ps.setInt(3, (int) boss.getZ());
					ps.setInt(4, boss.getHeading());
					ps.setLong(5, info.getLong("respawn_time"));
					double hp = boss.getCurrentHp();
					double mp = boss.getCurrentMp();
					if (boss.isDead()) {
						hp = boss.getMaxHp();
						mp = boss.getMaxMp();
					}
					ps.setDouble(6, hp);
					ps.setDouble(7, mp);
					ps.setInt(8, _bossStatus.get(bossId));
					ps.setInt(9, bossId);
					ps.executeUpdate();
				}
			}
		} catch (SQLException e) {
			log.warn("Couldn't update grandbosses to database:{}", e.getMessage(), e);
		}
	}

	/**
	 * Saves all Grand Boss info and then clears all info from memory, including all schedules.
	 */
	public void cleanUp() {
		storeMe();

		_bosses.clear();
		_storedInfo.clear();
		_bossStatus.clear();
	}
}
