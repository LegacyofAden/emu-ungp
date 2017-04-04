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
package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.enums.TeleportType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.teleporter.TeleportHolder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class TeleportersData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final TeleportersData instance = new TeleportersData();

	private final Map<Integer, Map<String, TeleportHolder>> _teleporters = new HashMap<>();

	private TeleportersData() {
		reload();
	}

	public void reload() {
		_teleporters.clear();
		parseDatapackDirectory("data/teleporters", true);
		log.info("Loaded: {} npc teleporters.", _teleporters.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", (list) ->
		{
			forEach(list, "npc", (npc) ->
			{
				final Map<String, TeleportHolder> teleList = new HashMap<>();
				// Parse npc node child
				final int npcId = parseInteger(npc.getAttributes(), "id");
				forEach(npc, (node) ->
				{
					switch (node.getNodeName()) {
						case "teleport": {
							final NamedNodeMap nodeAttrs = node.getAttributes();
							// Parse attributes
							final TeleportType type = parseEnum(nodeAttrs, TeleportType.class, "type");
							final String name = parseString(nodeAttrs, "name", type.name());
							// Parse locations
							final TeleportHolder holder = new TeleportHolder(name, type);
							forEach(node, "location", (location) -> holder.registerLocation(new StatsSet(parseAttributes(location))));
							// Register holder
							if (teleList.putIfAbsent(name, holder) != null) {
								log.warn("Duplicate teleport list ({}) has been found for NPC: {}", name, npcId);
							}
							break;
						}
						case "npcs": {
							forEach(node, "npc", (npcNode) ->
							{
								final int id = parseInteger(npcNode.getAttributes(), "id");
								registerTeleportList(id, teleList);
							});
							break;
						}
					}
				});
				registerTeleportList(npcId, teleList);
			});
		});
	}

	public int getTeleporterCount() {
		return _teleporters.size();
	}

	/**
	 * Register teleport data to global teleport list holder. Also show warning when any duplicate occurs.
	 *
	 * @param npcId    template id of teleporter
	 * @param teleList teleport data to register
	 */
	private void registerTeleportList(int npcId, Map<String, TeleportHolder> teleList) {
		_teleporters.put(npcId, teleList);
	}

	/**
	 * Gets teleport data for specified NPC and list name
	 *
	 * @param npcId    template id of teleporter
	 * @param listName name of teleport list
	 * @return {@link TeleportHolder} if found otherwise {@code null}
	 */
	public TeleportHolder getHolder(int npcId, String listName) {
		return _teleporters.getOrDefault(npcId, Collections.emptyMap()).get(listName);
	}
}
