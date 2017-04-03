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
import org.l2junity.commons.util.IXmlReader;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.templates.PcTemplate;
import org.l2junity.gameserver.model.base.ClassId;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads player's base stats.
 *
 * @author UnAfraid
 */
@Slf4j
@StartupComponent(value = "Data", dependency = ExperienceData.class)
public final class PlayerTemplateData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final PlayerTemplateData instance = new PlayerTemplateData();

	private final Map<Integer, PcTemplate> _playerTemplates = new HashMap<>();

	private PlayerTemplateData() {
		_playerTemplates.clear();
		parseDatapackDirectory("data/stats/chars/baseStats", false);
		log.info("Loaded {} character templates.", _playerTemplates.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		final StatsSet set = new StatsSet();
		forEach(doc, "list", listNode -> forEach(listNode, IXmlReader::isNode, firstNode ->
		{
			switch (firstNode.getNodeName()) {
				case "classId": {
					set.set("classId", Integer.parseInt(firstNode.getTextContent()));
					break;
				}
				case "staticData": {
					forEach(firstNode, IXmlReader::isNode, staticDataNode ->
					{
						switch (staticDataNode.getNodeName()) {
							case "creationPoints": {
								final List<Location> creationPoints = new ArrayList<>();
								forEach(staticDataNode, "node", innerNode ->
								{
									final int x = parseInteger(innerNode.getAttributes(), "x");
									final int y = parseInteger(innerNode.getAttributes(), "y");
									final int z = parseInteger(innerNode.getAttributes(), "z");
									creationPoints.add(new Location(x, y, z));
								});
								set.set("creationPoints", creationPoints);
								break;
							}
							case "basePDef": {
								forEach(staticDataNode, "chest|legs|head|feet|gloves|underwear|cloak", innerNode ->
								{
									set.set(staticDataNode.getNodeName() + innerNode.getNodeName(), innerNode.getTextContent());
								});
								break;
							}
							case "baseMDef": {
								forEach(staticDataNode, "rear|lear|rfinger|lfinger|neck", innerNode ->
								{
									set.set(staticDataNode.getNodeName() + innerNode.getNodeName(), innerNode.getTextContent());
								});
								break;
							}
							case "baseDamRange": {
								forEach(staticDataNode, "verticalDirection|horizontalDirection|distance|distance|width", innerNode ->
								{
									set.set(staticDataNode.getNodeName() + innerNode.getNodeName(), innerNode.getTextContent());
								});
								break;
							}
							case "baseMoveSpd": {
								forEach(staticDataNode, "walk|run|slowSwim|fastSwim", innerNode ->
								{
									set.set(staticDataNode.getNodeName() + innerNode.getNodeName(), innerNode.getTextContent());
								});
								break;
							}
							case "collisionMale": {
								forEach(staticDataNode, "radius|height", innerNode ->
								{
									set.set("collision_" + innerNode.getNodeName(), innerNode.getTextContent());
								});
								break;
							}
							case "collisionFemale": {
								forEach(staticDataNode, "radius|height", innerNode ->
								{
									set.set(staticDataNode.getNodeName() + innerNode.getNodeName(), innerNode.getTextContent());
								});
								break;
							}
							default: {
								set.set(staticDataNode.getNodeName(), staticDataNode.getTextContent());
								break;
							}
						}
					});

					// calculate total pdef and mdef from parts
					set.set("basePDef", (set.getInt("basePDefchest", 0) + set.getInt("basePDeflegs", 0) + set.getInt("basePDefhead", 0) + set.getInt("basePDeffeet", 0) + set.getInt("basePDefgloves", 0) + set.getInt("basePDefunderwear", 0) + set.getInt("basePDefcloak", 0)));
					set.set("baseMDef", (set.getInt("baseMDefrear", 0) + set.getInt("baseMDeflear", 0) + set.getInt("baseMDefrfinger", 0) + set.getInt("baseMDefrfinger", 0) + set.getInt("baseMDefneck", 0)));

					_playerTemplates.put(set.getInt("classId"), new PcTemplate(set));
					break;
				}
				case "lvlUpgainData": {
					forEach(firstNode, "level", lvlUpNode ->
					{
						final int level = parseInteger(lvlUpNode.getAttributes(), "val");
						final PcTemplate template = _playerTemplates.get(set.getInt("classId"));
						if (template == null) {
							log.warn("No template but parsing lvlUpgainData?? file: {}", path);
							return;
						}

						forEach(lvlUpNode, IXmlReader::isNode, innerNode ->
						{
							switch (innerNode.getNodeName()) {
								case "hp":
								case "mp":
								case "cp":
								case "hpRegen":
								case "mpRegen":
								case "cpRegen": {
									template.setUpgainValue(innerNode.getNodeName(), level, Double.parseDouble(innerNode.getTextContent()));
									break;
								}
							}
						});
					});
					break;
				}
			}
		}));
	}

	public int getTemplateCount() {
		return _playerTemplates.size();
	}

	public PcTemplate getTemplate(int classId) {
		return _playerTemplates.get(classId);
	}

	public PcTemplate getTemplate(ClassId classId) {
		return _playerTemplates.get(classId.getId());
	}
}
