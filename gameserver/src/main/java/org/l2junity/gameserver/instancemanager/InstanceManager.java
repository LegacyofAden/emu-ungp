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
import org.l2junity.commons.util.IXmlReader;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.RatesConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.data.xml.impl.DoorData;
import org.l2junity.gameserver.data.xml.impl.SpawnsData;
import org.l2junity.gameserver.enums.InstanceReenterType;
import org.l2junity.gameserver.enums.InstanceRemoveBuffType;
import org.l2junity.gameserver.enums.InstanceTeleportType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.templates.DoorTemplate;
import org.l2junity.gameserver.model.holders.InstanceReenterTimeHolder;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.instancezone.InstanceTemplate;
import org.l2junity.gameserver.model.instancezone.conditions.Condition;
import org.l2junity.gameserver.model.spawns.SpawnTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Instance manager.
 *
 * @author evill33t, GodKratos, malyelfik
 */
@Slf4j
@StartupComponent(value = "Service", dependency = {DoorData.class, SpawnsData.class})
public final class InstanceManager implements IGameXmlReader {
	@Getter(lazy = true)
	private static final InstanceManager instance = new InstanceManager();

	// Database query
	private static final String DELETE_INSTANCE_TIME = "DELETE FROM character_instance_time WHERE charId=? AND instanceId=?";

	// Client instance names
	private final Map<Integer, String> _instanceNames = new HashMap<>();
	// Instance templates holder
	private final Map<Integer, InstanceTemplate> _instanceTemplates = new HashMap<>();
	// Created instance worlds
	private int _currentInstanceId = 0;
	private final Map<Integer, Instance> _instanceWorlds = new ConcurrentHashMap<>();
	// Player reenter times
	private final Map<Integer, Map<Integer, Long>> _playerInstanceTimes = new ConcurrentHashMap<>();

	protected InstanceManager() {
		_instanceNames.clear();
		try {
			// Load instance names
			parseDatapackFile("data/instancenames.xml");
			LOGGER.info("Loaded {} instance names.", _instanceNames.size());
			// Load instance templates
			_instanceTemplates.clear();
			parseDatapackDirectory("data/instances", true);
			LOGGER.info("Loaded {} instance templates.", _instanceTemplates.size());
		} catch (Exception e) {
			log.error("Error while initializing InstanceManager", e);
		}

		// Load player's reenter data
		_playerInstanceTimes.clear();
		restoreInstanceTimes();
		LOGGER.info("Loaded instance reenter times for {} players.", _playerInstanceTimes.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, IXmlReader::isNode, listNode ->
		{
			switch (listNode.getNodeName()) {
				case "list": {
					parseInstanceName(listNode);
					break;
				}
				case "instance": {
					parseInstanceTemplate(listNode, path);
					break;
				}
			}
		});
	}

	/**
	 * Read instance names from XML file.
	 *
	 * @param n starting XML tag
	 */
	private void parseInstanceName(Node n) {
		forEach(n, "instance", instanceNode ->
		{
			final NamedNodeMap attrs = instanceNode.getAttributes();
			_instanceNames.put(parseInteger(attrs, "id"), parseString(attrs, "name"));
		});
	}

	/**
	 * Parse instance template from XML file.
	 *
	 * @param instanceNode start XML tag
	 * @param path         currently parsed file
	 */
	private void parseInstanceTemplate(Node instanceNode, Path path) {
		// Parse "instance" node
		final int id = parseInteger(instanceNode.getAttributes(), "id");
		if (_instanceTemplates.containsKey(id)) {
			LOGGER.warn("Instance template with ID {} already exists", id);
			return;
		}

		final InstanceTemplate template = new InstanceTemplate(new StatsSet(parseAttributes(instanceNode)));

		// Update name if wasn't provided
		if (template.getName() == null) {
			template.setName(_instanceNames.get(id));
		}

		// Parse "instance" node children
		forEach(instanceNode, IXmlReader::isNode, innerNode ->
		{
			switch (innerNode.getNodeName()) {
				case "time": {
					final NamedNodeMap attrs = innerNode.getAttributes();
					template.setDuration(parseInteger(attrs, "duration", -1));
					template.setEmptyDestroyTime(parseInteger(attrs, "empty", -1));
					template.setEjectTime(parseInteger(attrs, "eject", -1));
					break;
				}
				case "misc": {
					final NamedNodeMap attrs = innerNode.getAttributes();
					template.allowPlayerSummon(parseBoolean(attrs, "allowPlayerSummon", false));
					template.setIsPvP(parseBoolean(attrs, "isPvP", false));
					break;
				}
				case "rates": {
					final NamedNodeMap attrs = innerNode.getAttributes();
					template.setExpRate(parseFloat(attrs, "exp", RatesConfig.RATE_INSTANCE_XP));
					template.setSPRate(parseFloat(attrs, "sp", RatesConfig.RATE_INSTANCE_SP));
					template.setExpPartyRate(parseFloat(attrs, "partyExp", RatesConfig.RATE_INSTANCE_PARTY_XP));
					template.setSPPartyRate(parseFloat(attrs, "partySp", RatesConfig.RATE_INSTANCE_PARTY_SP));
					break;
				}
				case "locations": {
					forEach(innerNode, IXmlReader::isNode, locationsNode ->
					{
						switch (locationsNode.getNodeName()) {
							case "enter": {
								final InstanceTeleportType type = parseEnum(locationsNode.getAttributes(), InstanceTeleportType.class, "type");
								final List<Location> locations = new ArrayList<>();
								forEach(locationsNode, "location", locationNode -> locations.add(parseLocation(locationNode)));
								template.setEnterLocation(type, locations);
								break;
							}
							case "exit": {
								final InstanceTeleportType type = parseEnum(locationsNode.getAttributes(), InstanceTeleportType.class, "type");
								if (type.equals(InstanceTeleportType.ORIGIN)) {
									template.setExitLocation(type, null);
								} else {
									final List<Location> locations = new ArrayList<>();
									forEach(locationsNode, "location", locationNode -> locations.add(parseLocation(locationNode)));
									if (locations.isEmpty()) {
										LOGGER.warn("Missing exit location data for instance {} ({})!", template.getName(), template.getId());
									} else {
										template.setExitLocation(type, locations);
									}
								}
								break;
							}
						}
					});
					break;
				}
				case "spawnlist": {
					final List<SpawnTemplate> spawns = new ArrayList<>();
					SpawnsData.getInstance().parseSpawn(innerNode, path, spawns);
					template.addSpawns(spawns);
					break;
				}
				case "doorlist": {
					for (Node doorNode = innerNode.getFirstChild(); doorNode != null; doorNode = doorNode.getNextSibling()) {
						if (doorNode.getNodeName().equals("door")) {
							final StatsSet parsedSet = DoorData.getInstance().parseDoor(doorNode);
							final StatsSet mergedSet = new StatsSet();
							final int doorId = parsedSet.getInt("id");
							final StatsSet templateSet = DoorData.getInstance().getDoorTemplate(doorId);
							if (templateSet != null) {
								mergedSet.merge(templateSet);
							} else {
								LOGGER.warn("Cannot find template for door: {}, instance: {} ({})", doorId, template.getName(), template.getId());
							}
							mergedSet.merge(parsedSet);

							try {
								template.addDoor(doorId, new DoorTemplate(mergedSet));
							} catch (Exception e) {
								LOGGER.warn("Cannot initialize template for door: {}, instance: {} ({})", doorId, template.getName(), template.getId(), e);
							}
						}
					}
					break;
				}
				case "removeBuffs": {
					final InstanceRemoveBuffType removeBuffType = parseEnum(innerNode.getAttributes(), InstanceRemoveBuffType.class, "type");
					final List<Integer> exceptionBuffList = new ArrayList<>();
					for (Node e = innerNode.getFirstChild(); e != null; e = e.getNextSibling()) {
						if (e.getNodeName().equals("skill")) {
							exceptionBuffList.add(parseInteger(e.getAttributes(), "id"));
						}
					}
					template.setRemoveBuff(removeBuffType, exceptionBuffList);
					break;
				}
				case "reenter": {
					final InstanceReenterType type = parseEnum(innerNode.getAttributes(), InstanceReenterType.class, "apply", InstanceReenterType.NONE);
					final List<InstanceReenterTimeHolder> data = new ArrayList<>();
					for (Node e = innerNode.getFirstChild(); e != null; e = e.getNextSibling()) {
						if (e.getNodeName().equals("reset")) {
							final NamedNodeMap attrs = e.getAttributes();
							final int time = parseInteger(attrs, "time", -1);
							if (time > 0) {
								data.add(new InstanceReenterTimeHolder(time));
							} else {
								final DayOfWeek day = parseEnum(attrs, DayOfWeek.class, "day");
								final int hour = parseInteger(attrs, "hour", -1);
								final int minute = parseInteger(attrs, "minute", -1);
								data.add(new InstanceReenterTimeHolder(day, hour, minute));
							}
						}
					}
					template.setReenterData(type, data);
					break;
				}
				case "parameters": {
					template.setParameters(parseParameters(innerNode));
					break;
				}
				case "conditions": {
					final List<Condition> conditions = new ArrayList<>();
					for (Node conditionNode = innerNode.getFirstChild(); conditionNode != null; conditionNode = conditionNode.getNextSibling()) {
						if (conditionNode.getNodeName().equals("condition")) {
							final NamedNodeMap attrs = conditionNode.getAttributes();
							final String type = parseString(attrs, "type");
							final boolean onlyLeader = parseBoolean(attrs, "onlyLeader", false);
							final boolean showMessageAndHtml = parseBoolean(attrs, "showMessageAndHtml", false);
							// Load parameters
							StatsSet params = null;
							for (Node f = conditionNode.getFirstChild(); f != null; f = f.getNextSibling()) {
								if (f.getNodeName().equals("param")) {
									if (params == null) {
										params = new StatsSet();
									}

									params.set(parseString(f.getAttributes(), "name"), parseString(f.getAttributes(), "value"));
								}
							}

							// If none parameters found then set empty StatSet
							if (params == null) {
								params = StatsSet.EMPTY_STATSET;
							}

							// Now when everything is loaded register condition to template
							try {
								final Class<?> clazz = Class.forName(Condition.class.getPackage().getName() + ".Condition" + type);
								final Constructor<?> constructor = clazz.getConstructor(InstanceTemplate.class, StatsSet.class, boolean.class, boolean.class);
								conditions.add((Condition) constructor.newInstance(template, params, onlyLeader, showMessageAndHtml));
							} catch (Exception ex) {
								LOGGER.warn("Unknown condition type {} for instance {} ({})!", type, template.getName(), id);
							}
						}
					}
					template.setConditions(conditions);
					break;
				}
			}
		});

		// Save template
		_instanceTemplates.put(id, template);
	}

	// --------------------------------------------------------------------
	// Instance data loader - END
	// --------------------------------------------------------------------

	/**
	 * Create new instance with default template.
	 *
	 * @return newly created default instance.
	 */
	public Instance createInstance() {
		return new Instance(getNewInstanceId(), new InstanceTemplate(StatsSet.EMPTY_STATSET), null);
	}

	/**
	 * Create new instance from given template.
	 *
	 * @param template template used for instance creation
	 * @param player   player who create instance.
	 * @return newly created instance if success, otherwise {@code null}
	 */
	public Instance createInstance(InstanceTemplate template, PlayerInstance player) {
		return (template != null) ? new Instance(getNewInstanceId(), template, player) : null;
	}

	/**
	 * Create new instance with template defined in datapack.
	 *
	 * @param id     template id of instance
	 * @param player player who create instance
	 * @return newly created instance if template was found, otherwise {@code null}
	 */
	public Instance createInstance(int id, PlayerInstance player) {
		if (!_instanceTemplates.containsKey(id)) {
			LOGGER.warn("Missing template for instance with id {}!", id);
			return null;
		}
		return new Instance(getNewInstanceId(), _instanceTemplates.get(id), player);
	}

	/**
	 * Get instance world with given ID.
	 *
	 * @param instanceId ID of instance
	 * @return instance itself if found, otherwise {@code null}
	 */
	public Instance getInstance(int instanceId) {
		return _instanceWorlds.get(instanceId);
	}

	/**
	 * Get all active instances.
	 *
	 * @return Collection of all instances
	 */
	public Collection<Instance> getInstances() {
		return _instanceWorlds.values();
	}

	/**
	 * Get instance world for player.
	 *
	 * @param player   player who wants to get instance world
	 * @param isInside when {@code true} find world where player is currently located, otherwise find world where player can enter
	 * @return instance if found, otherwise {@code null}
	 */
	public Instance getPlayerInstance(PlayerInstance player, boolean isInside) {
		return _instanceWorlds.values().stream().filter(i -> (isInside) ? i.containsPlayer(player) : i.isAllowed(player)).findFirst().orElse(null);
	}

	/**
	 * Get ID for newly created instance.
	 *
	 * @return instance id
	 */
	private synchronized int getNewInstanceId() {
		do {
			if (_currentInstanceId == Integer.MAX_VALUE) {
				if (GeneralConfig.DEBUG_INSTANCES) {
					LOGGER.info("Instance id owerflow, starting from zero.");
				}
				_currentInstanceId = 0;
			}
			_currentInstanceId++;
		}
		while (_instanceWorlds.containsKey(_currentInstanceId));
		return _currentInstanceId;
	}

	/**
	 * Register instance world.<br>
	 *
	 * @param instance instance which should be registered
	 */
	public void register(Instance instance) {
		final int instanceId = instance.getId();
		if (!_instanceWorlds.containsKey(instanceId)) {
			_instanceWorlds.put(instanceId, instance);
		}
	}

	/**
	 * Unregister instance world.<br>
	 * <b><font color=red>To remove instance world properly use {@link Instance#destroy()}.</font></b>
	 *
	 * @param instanceId ID of instance to unregister
	 */
	public void unregister(int instanceId) {
		if (_instanceWorlds.containsKey(instanceId)) {
			_instanceWorlds.remove(instanceId);
		}
	}

	/**
	 * Get instance name from file "instancenames.xml"
	 *
	 * @param templateId template ID of instance
	 * @return name of instance if found, otherwise {@code null}
	 */
	public String getInstanceName(int templateId) {
		return _instanceNames.get(templateId);
	}

	/**
	 * Restore instance reenter data for all players.
	 */
	private void restoreInstanceTimes() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement ps = con.createStatement();
			 ResultSet rs = ps.executeQuery("SELECT * FROM character_instance_time ORDER BY charId")) {
			while (rs.next()) {
				// Check if instance penalty passed
				final long time = rs.getLong("time");
				if (time > System.currentTimeMillis()) {
					// Load params
					final int charId = rs.getInt("charId");
					final int instanceId = rs.getInt("instanceId");
					// Set penalty
					setReenterPenalty(charId, instanceId, time);
				}
			}
		} catch (Exception e) {
			LOGGER.warn("Cannot restore players instance reenter data: ", e);
		}
	}

	/**
	 * Get all instance re-enter times for specified player.<br>
	 * This method also removes the penalties that have already expired.
	 *
	 * @param player instance of player who wants to get re-enter data
	 * @return map in form templateId, penaltyEndTime
	 */
	public Map<Integer, Long> getAllInstanceTimes(PlayerInstance player) {
		// When player don't have any instance penalty
		final Map<Integer, Long> instanceTimes = _playerInstanceTimes.get(player.getObjectId());
		if ((instanceTimes == null) || instanceTimes.isEmpty()) {
			return Collections.emptyMap();
		}

		// Find passed penalty
		final List<Integer> invalidPenalty = new ArrayList<>(instanceTimes.size());
		for (Entry<Integer, Long> entry : instanceTimes.entrySet()) {
			if (entry.getValue() <= System.currentTimeMillis()) {
				invalidPenalty.add(entry.getKey());
			}
		}

		// Remove them
		if (!invalidPenalty.isEmpty()) {
			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement ps = con.prepareStatement(DELETE_INSTANCE_TIME)) {
				for (Integer id : invalidPenalty) {
					ps.setInt(1, player.getObjectId());
					ps.setInt(2, id);
					ps.addBatch();
				}
				ps.executeBatch();
				invalidPenalty.forEach(instanceTimes::remove);
			} catch (Exception e) {
				LOGGER.warn("Cannot delete instance character reenter data: ", e);
			}
		}
		return instanceTimes;
	}

	/**
	 * Set re-enter penalty for specified player.<br>
	 * <font color=red><b>This method store penalty into memory only. Use {@link Instance#setReenterTime} to set instance penalty properly.</b></font>
	 *
	 * @param objectId object ID of player
	 * @param id       instance template id
	 * @param time     penalty time
	 */
	public void setReenterPenalty(int objectId, int id, long time) {
		_playerInstanceTimes.computeIfAbsent(objectId, k -> new ConcurrentHashMap<>()).put(id, time);
	}

	/**
	 * Get re-enter time to instance (by template ID) for player.<br>
	 * This method also removes penalty if expired.
	 *
	 * @param player player who wants to get re-enter time
	 * @param id     template ID of instance
	 * @return penalty end time if penalty is found, otherwise -1
	 */
	public long getInstanceTime(PlayerInstance player, int id) {
		// Check if exists reenter data for player
		final Map<Integer, Long> playerData = _playerInstanceTimes.get(player.getObjectId());
		if ((playerData == null) || !playerData.containsKey(id)) {
			return -1;
		}

		// If reenter time is higher then current, delete it
		final long time = playerData.get(id);
		if (time <= System.currentTimeMillis()) {
			deleteInstanceTime(player, id);
			return -1;
		}
		return time;
	}

	/**
	 * Remove re-enter penalty for specified instance from player.
	 *
	 * @param player player who wants to delete penalty
	 * @param id     template id of instance world
	 */
	public void deleteInstanceTime(PlayerInstance player, int id) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement(DELETE_INSTANCE_TIME)) {
			ps.setInt(1, player.getObjectId());
			ps.setInt(2, id);
			ps.execute();
			_playerInstanceTimes.get(player.getObjectId()).remove(id);
		} catch (Exception e) {
			LOGGER.warn("Could not delete character instance reenter data: ", e);
		}
	}

	/**
	 * Get instance template by template ID.
	 *
	 * @param id template id of instance
	 * @return instance template if found, otherwise {@code null}
	 */
	public InstanceTemplate getInstanceTemplate(int id) {
		return _instanceTemplates.get(id);
	}

	/**
	 * Get all instances template.
	 *
	 * @return Collection of all instance templates
	 */
	public Collection<InstanceTemplate> getInstanceTemplates() {
		return _instanceTemplates.values();
	}

	/**
	 * Get count of created instance worlds with same template ID.
	 *
	 * @param templateId template id of instance
	 * @return count of created instances
	 */
	public long getWorldCount(int templateId) {
		return _instanceWorlds.values().stream().filter(i -> i.getTemplateId() == templateId).count();
	}
}
