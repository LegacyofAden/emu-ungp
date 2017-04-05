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
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.templates.L2CubicTemplate;
import org.l2junity.gameserver.model.cubic.CubicSkill;
import org.l2junity.gameserver.model.cubic.ICubicConditionHolder;
import org.l2junity.gameserver.model.cubic.conditions.HealthCondition;
import org.l2junity.gameserver.model.cubic.conditions.HpCondition;
import org.l2junity.gameserver.model.cubic.conditions.HpCondition.HpConditionType;
import org.l2junity.gameserver.model.cubic.conditions.RangeCondition;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class CubicData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final CubicData instance = new CubicData();

	private final Map<Integer, Map<Integer, L2CubicTemplate>> _cubics = new HashMap<>();

	protected CubicData() {
		_cubics.clear();
		parseDatapackDirectory("data/stats/cubics", true);
		log.info("Loaded {} cubics.", _cubics.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "cubic", cubicNode ->
		{
			parseTemplate(cubicNode, new L2CubicTemplate(new StatsSet(parseAttributes(cubicNode))));
		}));
	}

	/**
	 * @param cubicNode
	 * @param template
	 */
	private void parseTemplate(Node cubicNode, L2CubicTemplate template) {
		forEach(cubicNode, IXmlReader::isNode, innerNode ->
		{
			switch (innerNode.getNodeName()) {
				case "conditions": {
					parseConditions(innerNode, template, template);
					break;
				}
				case "skills": {
					parseSkills(innerNode, template);
					break;
				}
			}
		});
		_cubics.computeIfAbsent(template.getId(), key -> new HashMap<>()).put(template.getLevel(), template);
	}

	/**
	 * @param cubicNode
	 * @param template
	 * @param holder
	 */
	private void parseConditions(Node cubicNode, L2CubicTemplate template, ICubicConditionHolder holder) {
		forEach(cubicNode, IXmlReader::isNode, conditionNode ->
		{
			switch (conditionNode.getNodeName()) {
				case "hp": {
					final HpConditionType type = parseEnum(conditionNode.getAttributes(), HpConditionType.class, "type");
					final int hpPer = parseInteger(conditionNode.getAttributes(), "percent");
					holder.addCondition(new HpCondition(type, hpPer));
					break;
				}
				case "range": {
					final int range = parseInteger(conditionNode.getAttributes(), "value");
					holder.addCondition(new RangeCondition(range));
					break;
				}
				case "healthPercent": {
					final int min = parseInteger(conditionNode.getAttributes(), "min");
					final int max = parseInteger(conditionNode.getAttributes(), "max");
					holder.addCondition(new HealthCondition(min, max));
					break;
				}
				default: {
					log.warn("Attempting to use not implemented condition: {} for cubic id: {} level: {}", conditionNode.getNodeName(), template.getId(), template.getLevel());
					break;
				}
			}
		});

	}

	/**
	 * @param cubicNode
	 * @param template
	 */
	private void parseSkills(Node cubicNode, L2CubicTemplate template) {
		forEach(cubicNode, "skill", skillNode ->
		{
			final CubicSkill skill = new CubicSkill(new StatsSet(parseAttributes(skillNode)));
			forEach(cubicNode, "conditions", conditionNode -> parseConditions(cubicNode, template, skill));
			template.getSkills().add(skill);
		});
	}

	public int getLoadedElementsCount() {
		return _cubics.size();
	}

	/**
	 * @param id
	 * @param level
	 * @return the L2CubicTemplate for specified id and level
	 */
	public L2CubicTemplate getCubicTemplate(int id, int level) {
		return _cubics.getOrDefault(id, Collections.emptyMap()).get(level);
	}
}
