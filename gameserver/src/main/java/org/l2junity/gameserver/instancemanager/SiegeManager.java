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
import org.l2junity.commons.util.PropertiesParser;
import org.l2junity.core.configs.SiegeCastleConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.TowerSpawn;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.entity.Siege;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.skills.CommonSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@Slf4j
@StartupComponent("Service")
public final class SiegeManager {
	@Getter(lazy = true)
	private static final SiegeManager instance = new SiegeManager();

	private final Map<Integer, List<TowerSpawn>> _controlTowers = new HashMap<>();
	private final Map<Integer, List<TowerSpawn>> _flameTowers = new HashMap<>();

	protected SiegeManager() {
		// TODO: Must be controlled by AI.obj
		/*for (Castle castle : CastleManager.getInstance().getCastles()) {
			final List<TowerSpawn> controlTowers = new ArrayList<>();
			for (int i = 1; i < 0xFF; i++) {
				final String settingsKeyName = castle.getName() + "ControlTower" + i;
				if (!siegeSettings.containsKey(settingsKeyName)) {
					break;
				}

				final StringTokenizer st = new StringTokenizer(siegeSettings.getString(settingsKeyName, ""), ",");
				try {
					final int x = Integer.parseInt(st.nextToken());
					final int y = Integer.parseInt(st.nextToken());
					final int z = Integer.parseInt(st.nextToken());
					final int npcId = Integer.parseInt(st.nextToken());

					controlTowers.add(new TowerSpawn(npcId, new Location(x, y, z)));
				} catch (Exception e) {
					log.warn(getClass().getSimpleName() + ": Error while loading control tower(s) for " + castle.getName() + " castle.");
				}
			}

			final List<TowerSpawn> flameTowers = new ArrayList<>();
			for (int i = 1; i < 0xFF; i++) {
				final String settingsKeyName = castle.getName() + "FlameTower" + i;
				if (!siegeSettings.containsKey(settingsKeyName)) {
					break;
				}

				final StringTokenizer st = new StringTokenizer(siegeSettings.getString(settingsKeyName, ""), ",");
				try {
					final int x = Integer.parseInt(st.nextToken());
					final int y = Integer.parseInt(st.nextToken());
					final int z = Integer.parseInt(st.nextToken());
					final int npcId = Integer.parseInt(st.nextToken());
					final List<Integer> zoneList = new ArrayList<>();

					while (st.hasMoreTokens()) {
						zoneList.add(Integer.parseInt(st.nextToken()));
					}

					flameTowers.add(new TowerSpawn(npcId, new Location(x, y, z), zoneList));
				} catch (Exception e) {
					log.warn(getClass().getSimpleName() + ": Error while loading flame tower(s) for " + castle.getName() + " castle.");
				}
			}
			_controlTowers.put(castle.getResidenceId(), controlTowers);
			_flameTowers.put(castle.getResidenceId(), flameTowers);

			if (castle.getOwnerId() != 0) {
				loadTrapUpgrade(castle.getResidenceId());
			}
		}*/
	}

	public final void addSiegeSkills(PlayerInstance character) {
		character.addSkill(CommonSkill.IMPRIT_OF_LIGHT.getSkill(), false);
		character.addSkill(CommonSkill.IMPRIT_OF_DARKNESS.getSkill(), false);
		character.addSkill(CommonSkill.BUILD_HEADQUARTERS.getSkill(), false);
		if (character.isNoble()) {
			character.addSkill(CommonSkill.BUILD_ADVANCED_HEADQUARTERS.getSkill(), false);
		}
		if (character.getClan().getCastleId() > 0) {
			character.addSkill(CommonSkill.OUTPOST_CONSTRUCTION.getSkill(), false);
			character.addSkill(CommonSkill.OUTPOST_DEMOLITION.getSkill(), false);
		}
	}

	/**
	 * @param clan     The L2Clan of the player
	 * @param castleid
	 * @return true if the clan is registered or owner of a castle
	 */
	public final boolean checkIsRegistered(L2Clan clan, int castleid) {
		if (clan == null) {
			return false;
		}

		if (clan.getCastleId() > 0) {
			return true;
		}

		boolean register = false;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("SELECT clan_id FROM siege_clans where clan_id=? and castle_id=?")) {
			statement.setInt(1, clan.getId());
			statement.setInt(2, castleid);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					register = true;
				}
			}
		} catch (Exception e) {
			log.warn(getClass().getSimpleName() + ": Exception: checkIsRegistered(): " + e.getMessage(), e);
		}
		return register;
	}

	public final void removeSiegeSkills(PlayerInstance character) {
		character.removeSkill(CommonSkill.IMPRIT_OF_LIGHT.getSkill(), false);
		character.removeSkill(CommonSkill.IMPRIT_OF_DARKNESS.getSkill(), false);
		character.removeSkill(CommonSkill.BUILD_HEADQUARTERS.getSkill(), false);
		character.removeSkill(CommonSkill.BUILD_ADVANCED_HEADQUARTERS.getSkill(), false);
		character.removeSkill(CommonSkill.OUTPOST_CONSTRUCTION.getSkill(), false);
		character.removeSkill(CommonSkill.OUTPOST_DEMOLITION.getSkill(), false);
	}

	public final List<TowerSpawn> getControlTowers(int castleId) {
		return _controlTowers.get(castleId);
	}

	public final List<TowerSpawn> getFlameTowers(int castleId) {
		return _flameTowers.get(castleId);
	}

	public final int getAttackerMaxClans() {
		return SiegeCastleConfig.ATTACKER_MAX_CLANS;
	}

	public final int getAttackerRespawnDelay() {
		return SiegeCastleConfig.ATTACKER_RESPAWN;
	}

	public final int getDefenderMaxClans() {
		return SiegeCastleConfig.DEFENDER_MAX_CLANS;
	}

	public final int getFlagMaxCount() {
		return SiegeCastleConfig.MAX_FLAGS;
	}

	public final Siege getSiege(ILocational loc) {
		return getSiege(loc.getX(), loc.getY(), loc.getZ());
	}

	public final Siege getSiege(WorldObject activeObject) {
		return getSiege(activeObject.getX(), activeObject.getY(), activeObject.getZ());
	}

	public final Siege getSiege(double x, double y, double z) {
		for (Castle castle : CastleManager.getInstance().getCastles()) {
			if (castle.getSiege().checkIfInZone(x, y, z)) {
				return castle.getSiege();
			}
		}
		return null;
	}

	public final int getSiegeClanMinLevel() {
		return SiegeCastleConfig.SIEGE_CLAN_MIN_LEVEL;
	}

	public final int getSiegeLength() {
		return SiegeCastleConfig.SIEGE_LENGTH;
	}

	public final int getBloodAllianceReward() {
		return SiegeCastleConfig.BLOOD_ALLIANCE_REWARD;
	}

	public final List<Siege> getSieges() {
		List<Siege> sieges = new LinkedList<>();
		for (Castle castle : CastleManager.getInstance().getCastles()) {
			sieges.add(castle.getSiege());
		}
		return sieges;
	}

	private void loadTrapUpgrade(int castleId) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("SELECT * FROM castle_trapupgrade WHERE castleId=?")) {
			ps.setInt(1, castleId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					_flameTowers.get(castleId).get(rs.getInt("towerIndex")).setUpgradeLevel(rs.getInt("level"));
				}
			}
		} catch (Exception e) {
			log.warn("Exception: loadTrapUpgrade(): " + e.getMessage(), e);
		}
	}
}