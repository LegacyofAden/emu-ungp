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
package org.l2junity.gameserver.model.spawns;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.datatables.SpawnTable;
import org.l2junity.gameserver.instancemanager.DBSpawnManager;
import org.l2junity.gameserver.instancemanager.ZoneManager;
import org.l2junity.gameserver.model.ChanceLocation;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2MonsterInstance;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.l2junity.gameserver.model.holders.MinionHolder;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.interfaces.IParameterized;
import org.l2junity.gameserver.model.zone.type.SpawnTerritory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnAfraid
 */
public class NpcSpawnTemplate implements Cloneable, IParameterized<StatsSet> {
	private static final Logger LOGGER = LoggerFactory.getLogger(NpcSpawnTemplate.class);

	private final int _id;
	private final int _count;
	private final Duration _respawnTime;
	private final Duration _respawnTimeRandom;
	private List<ChanceLocation> _locations;
	private SpawnTerritory _zone;
	private StatsSet _parameters;
	private final boolean _spawnAnimation;
	private final boolean _saveInDB;
	private final String _dbName;
	private List<MinionHolder> _minions;
	private final SpawnTemplate _spawnTemplate;
	private final SpawnGroup _group;
	private final Set<Npc> _spawnedNpcs = ConcurrentHashMap.newKeySet();

	private NpcSpawnTemplate(NpcSpawnTemplate template) {
		_spawnTemplate = template._spawnTemplate;
		_group = template._group;
		_id = template._id;
		_count = template._count;
		_respawnTime = template._respawnTime;
		_respawnTimeRandom = template._respawnTimeRandom;
		_spawnAnimation = template._spawnAnimation;
		_saveInDB = template._saveInDB;
		_dbName = template._dbName;
		_locations = template._locations;
		_zone = template._zone;
		_parameters = template._parameters;
		_minions = template._minions;
	}

	public NpcSpawnTemplate(SpawnTemplate spawnTemplate, SpawnGroup group, StatsSet set) {
		_spawnTemplate = spawnTemplate;
		_group = group;
		_id = set.getInt("id");
		_count = set.getInt("count", 1);
		_respawnTime = set.getDuration("respawnTime", null);
		_respawnTimeRandom = set.getDuration("respawnRandom", null);
		_spawnAnimation = set.getBoolean("spawnAnimation", false);
		_saveInDB = set.getBoolean("dbSave", false);
		_dbName = set.getString("dbName", null);
		_parameters = mergeParameters(spawnTemplate, group);

		final int x = set.getInt("x", Integer.MAX_VALUE);
		final int y = set.getInt("y", Integer.MAX_VALUE);
		final int z = set.getInt("z", Integer.MAX_VALUE);
		final boolean xDefined = x != Integer.MAX_VALUE;
		final boolean yDefined = y != Integer.MAX_VALUE;
		final boolean zDefined = z != Integer.MAX_VALUE;
		if (xDefined && yDefined && zDefined) {
			_locations = new ArrayList<>();
			_locations.add(new ChanceLocation(x, y, z, set.getInt("heading", 0), 100));
		} else {
			if (xDefined || yDefined || zDefined) {
				throw new IllegalStateException(String.format("Spawn with partially declared and x: %s y: %s z: %s!", processParam(x), processParam(y), processParam(z)));
			}

			final String zoneName = set.getString("zone", null);
			if (zoneName != null) {
				final SpawnTerritory zone = ZoneManager.getInstance().getSpawnTerritory(zoneName);
				if (zone == null) {
					throw new NullPointerException("Spawn with non existing zone requested " + zoneName);
				}
				_zone = zone;
			}
		}

		mergeParameters(spawnTemplate, group);
	}

	private StatsSet mergeParameters(SpawnTemplate spawnTemplate, SpawnGroup group) {
		if ((_parameters == null) && (spawnTemplate.getParameters() == null) && (group.getParameters() == null)) {
			return null;
		}

		final StatsSet set = new StatsSet();
		if (spawnTemplate.getParameters() != null) {
			set.merge(spawnTemplate.getParameters());
		}
		if (group.getParameters() != null) {
			set.merge(group.getParameters());
		}
		if (_parameters != null) {
			set.merge(_parameters);
		}
		return set;
	}

	public void addSpawnLocation(ChanceLocation loc) {
		if (_locations == null) {
			_locations = new ArrayList<>();
		}
		_locations.add(loc);
	}

	public SpawnTemplate getSpawnTemplate() {
		return _spawnTemplate;
	}

	public SpawnGroup getGroup() {
		return _group;
	}

	private String processParam(int value) {
		return value != Integer.MAX_VALUE ? Integer.toString(value) : "undefined";
	}

	public int getId() {
		return _id;
	}

	public int getCount() {
		return _count;
	}

	public Duration getRespawnTime() {
		return _respawnTime;
	}

	public Duration getRespawnTimeRandom() {
		return _respawnTimeRandom;
	}

	public List<ChanceLocation> getLocation() {
		return _locations;
	}

	public SpawnTerritory getZone() {
		return _zone;
	}

	@Override
	public StatsSet getParameters() {
		return _parameters;
	}

	@Override
	public void setParameters(StatsSet parameters) {
		if (_parameters == null) {
			_parameters = parameters;
		} else {
			_parameters.merge(parameters);
		}
	}

	public boolean hasSpawnAnimation() {
		return _spawnAnimation;
	}

	public boolean hasDBSave() {
		return _saveInDB;
	}

	public String getDBName() {
		return _dbName;
	}

	public List<MinionHolder> getMinions() {
		return _minions != null ? _minions : Collections.emptyList();
	}

	public void addMinion(MinionHolder minion) {
		if (_minions == null) {
			_minions = new ArrayList<>();
		}
		_minions.add(minion);
	}

	public Set<Npc> getSpawnedNpcs() {
		return _spawnedNpcs;
	}

	public final Location getSpawnLocation() {
		if (_locations != null) {
			final double locRandom = (100 * Rnd.nextDouble());
			float cumulativeChance = 0;
			for (ChanceLocation loc : _locations) {
				if (locRandom <= (cumulativeChance += loc.getChance())) {
					return loc;
				}
			}
			LOGGER.warn("Couldn't match location by chance turning first..");
			return null;
		} else if (_zone != null) {
			final Location loc = _zone.getRandomPoint();
			loc.setHeading(Rnd.get(65535));
			return loc;
		} else if (!_group.getTerritories().isEmpty()) {
			final SpawnTerritory territory = _group.getTerritories().get(Rnd.get(_group.getTerritories().size()));
			for (int i = 0; i < 100; i++) {
				final Location loc = territory.getRandomPoint();
				if (_group.getBannedTerritories().isEmpty() || _group.getBannedTerritories().stream().allMatch(bannedTerritory -> !bannedTerritory.isInsideZone(loc.getX(), loc.getY(), loc.getZ()))) {
					return loc;
				}
			}
		} else if (!_spawnTemplate.getTerritories().isEmpty()) {
			final SpawnTerritory territory = _spawnTemplate.getTerritories().get(Rnd.get(_spawnTemplate.getTerritories().size()));
			for (int i = 0; i < 100; i++) {
				final Location loc = territory.getRandomPoint();
				if (_spawnTemplate.getBannedTerritories().isEmpty() || _spawnTemplate.getBannedTerritories().stream().allMatch(bannedTerritory -> !bannedTerritory.isInsideZone(loc.getX(), loc.getY(), loc.getZ()))) {
					return loc;
				}
			}
		}
		return null;
	}

	public void spawn() {
		spawn(null);
	}

	public void spawn(Instance instance) {
		try {
			final L2NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(_id);
			if (npcTemplate == null) {
				LOGGER.warn("Attempting to spawn unexisting npc id: {} file: {} spawn: {} group: {}", _id, _spawnTemplate.getFileName(), _spawnTemplate.getName(), _group.getName());
				return;
			}

			if (npcTemplate.isType("L2Defender")) {
				LOGGER.warn("Attempting to spawn npc id: {} type: {} file: {} spawn: {} group: {}", _id, npcTemplate.getType(), _spawnTemplate.getFileName(), _spawnTemplate.getName(), _group.getName());
				return;
			}

			for (int i = 0; i < _count; i++) {
				spawnNpc(npcTemplate, instance);
			}
		} catch (Exception e) {
			LOGGER.warn("Couldn't spawn npc {}", _id, e);
		}
	}

	/**
	 * @param npcTemplate
	 * @param instance
	 * @throws ClassCastException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 */
	private void spawnNpc(L2NpcTemplate npcTemplate, Instance instance) throws SecurityException, ClassNotFoundException, NoSuchMethodException, ClassCastException {
		final L2Spawn spawn = new L2Spawn(npcTemplate);
		final Location loc = getSpawnLocation();
		if (loc == null) {
			LOGGER.warn("Couldn't initialize new spawn, no location found!");
			return;
		}

		spawn.setInstanceId(instance != null ? instance.getId() : 0);
		spawn.setAmount(1);
		spawn.setXYZ(loc);
		spawn.setHeading(loc.getHeading());
		spawn.setLocation(loc);
		int respawn = 0, respawnRandom = 0;
		if (_respawnTime != null) {
			respawn = (int) _respawnTime.getSeconds();
		}
		if (_respawnTimeRandom != null) {
			respawnRandom = (int) _respawnTimeRandom.getSeconds();
		}

		if (respawn > 0) {
			spawn.setRespawnDelay(respawn, respawnRandom);
			spawn.startRespawn();
		} else {
			spawn.stopRespawn();
		}

		spawn.setSpawnTemplate(this);

		if (_saveInDB) {
			if (!DBSpawnManager.getInstance().isDefined(_id)) {
				final Npc spawnedNpc = DBSpawnManager.getInstance().addNewSpawn(spawn, true);
				if ((spawnedNpc != null) && spawnedNpc.isMonster() && (_minions != null)) {
					((L2MonsterInstance) spawnedNpc).getMinionList().spawnMinions(_minions);
				}

				_spawnedNpcs.add(spawnedNpc);
			}
		} else {
			final Npc npc = spawn.doSpawn(_spawnAnimation);
			if (npc.isMonster() && (_minions != null)) {
				((L2MonsterInstance) npc).getMinionList().spawnMinions(_minions);
			}
			_spawnedNpcs.add(npc);

			SpawnTable.getInstance().addNewSpawn(spawn, false);
		}
	}

	public void despawn() {
		_spawnedNpcs.forEach(npc ->
		{
			npc.getSpawn().stopRespawn();
			SpawnTable.getInstance().deleteSpawn(npc.getSpawn(), false);
			npc.deleteMe();
		});
		_spawnedNpcs.clear();
	}

	public boolean isInMyTerritory(ILocational loc) {
		if (_zone != null) {
			return _zone.isInsideZone(loc.getX(), loc.getY(), loc.getZ());
		} else if (!_group.getTerritories().isEmpty()) {
			for (SpawnTerritory territory : _group.getTerritories()) {
				if (((territory.isInsideZone(loc.getX(), loc.getY(), loc.getZ()) && (_group.getBannedTerritories().isEmpty())) || _group.getBannedTerritories().stream().allMatch(bannedTerritory -> !bannedTerritory.isInsideZone(loc.getX(), loc.getY(), loc.getZ())))) {
					return true;
				}
			}
			return false;
		} else if (!_spawnTemplate.getTerritories().isEmpty()) {
			for (SpawnTerritory territory : _spawnTemplate.getTerritories()) {
				if (((territory.isInsideZone(loc.getX(), loc.getY(), loc.getZ()) && (_spawnTemplate.getBannedTerritories().isEmpty())) || _spawnTemplate.getBannedTerritories().stream().allMatch(bannedTerritory -> !bannedTerritory.isInsideZone(loc.getX(), loc.getY(), loc.getZ())))) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public void notifySpawnNpc(Npc npc) {
		_spawnTemplate.notifyEvent(event -> event.onSpawnNpc(_spawnTemplate, _group, npc));
	}

	public void notifyDespawnNpc(Npc npc) {
		_spawnTemplate.notifyEvent(event -> event.onSpawnDespawnNpc(_spawnTemplate, _group, npc));
	}

	public void notifyNpcDeath(Npc npc, Creature killer) {
		_spawnTemplate.notifyEvent(event -> event.onSpawnNpcDeath(_spawnTemplate, _group, npc, killer));
	}

	@Override
	public NpcSpawnTemplate clone() {
		return new NpcSpawnTemplate(this);
	}
}