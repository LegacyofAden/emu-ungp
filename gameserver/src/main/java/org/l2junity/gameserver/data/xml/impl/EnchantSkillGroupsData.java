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
import org.l2junity.gameserver.enums.SkillEnchantType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.holders.EnchantSkillHolder;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.skills.Skill;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.*;

/**
 * This class holds the Enchant Groups information.
 *
 * @author Micr0
 */
@Slf4j
@StartupComponent("Data")
public class EnchantSkillGroupsData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final EnchantSkillGroupsData instance = new EnchantSkillGroupsData();

	private final Map<Integer, EnchantSkillHolder> _enchantSkillHolders = new LinkedHashMap<>();
	private final Map<SkillHolder, Set<Integer>> _enchantSkillTrees = new HashMap<>();

	public static int MAX_ENCHANT_LEVEL;

	private EnchantSkillGroupsData() {
		_enchantSkillHolders.clear();
		parseDatapackFile("data/enchantSkillGroups.xml");
		MAX_ENCHANT_LEVEL = _enchantSkillHolders.size();
		LOGGER.info("Loaded {} enchant routes, max enchant set to {}", _enchantSkillHolders.size(), MAX_ENCHANT_LEVEL);
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "enchant", enchantNode ->
		{
			final EnchantSkillHolder enchantSkillHolder = new EnchantSkillHolder(new StatsSet(parseAttributes(enchantNode)));

			forEach(enchantNode, "sps", spsNode -> forEach(spsNode, "sp", spNode ->
			{
				enchantSkillHolder.addSp(parseEnum(spNode.getAttributes(), SkillEnchantType.class, "type"), parseInteger(spNode.getAttributes(), "amount"));
			}));

			forEach(enchantNode, "chances", chancesNode -> forEach(chancesNode, "chance", chanceNode ->
			{
				enchantSkillHolder.addChance(parseEnum(chanceNode.getAttributes(), SkillEnchantType.class, "type"), parseInteger(chanceNode.getAttributes(), "value"));
			}));

			forEach(enchantNode, "items", itemsNode -> forEach(itemsNode, "item", itemNode ->
			{
				enchantSkillHolder.addRequiredItem(parseEnum(itemNode.getAttributes(), SkillEnchantType.class, "type"), new ItemHolder(new StatsSet(parseAttributes(itemNode))));
			}));

			_enchantSkillHolders.put(parseInteger(enchantNode.getAttributes(), "level"), enchantSkillHolder);
		}));
	}

	public int getLoadedElementsCount() {
		return _enchantSkillHolders.size();
	}

	public void addRouteForSkill(int skillId, int level, int route) {
		addRouteForSkill(new SkillHolder(skillId, level), route);
	}

	public void addRouteForSkill(SkillHolder holder, int route) {
		_enchantSkillTrees.computeIfAbsent(holder, k -> new HashSet<>()).add(route);
	}

	public Set<Integer> getRouteForSkill(int skillId, int level) {
		return getRouteForSkill(skillId, level, 0);
	}

	public Set<Integer> getRouteForSkill(int skillId, int level, int subLevel) {
		return getRouteForSkill(new SkillHolder(skillId, level, subLevel));
	}

	public Set<Integer> getRouteForSkill(SkillHolder holder) {
		return _enchantSkillTrees.getOrDefault(holder, Collections.emptySet());
	}

	public boolean isEnchantable(Skill skill) {
		return isEnchantable(new SkillHolder(skill.getId(), skill.getLevel()));
	}

	public boolean isEnchantable(SkillHolder holder) {
		return _enchantSkillTrees.containsKey(holder);
	}

	public EnchantSkillHolder getEnchantSkillHolder(int level) {
		return _enchantSkillHolders.getOrDefault(level, null);
	}
}