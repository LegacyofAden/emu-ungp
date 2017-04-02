/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.model.holders;

import org.l2junity.gameserver.enums.SkillEnchantType;
import org.l2junity.gameserver.model.StatsSet;

import java.util.*;

/**
 * @author Sdw
 */
public class EnchantSkillHolder {
	private final int _level;
	private final int _enchantFailLevel;
	private final Map<SkillEnchantType, Long> _sp = new EnumMap<>(SkillEnchantType.class);
	private final Map<SkillEnchantType, Integer> _chance = new EnumMap<>(SkillEnchantType.class);
	private final Map<SkillEnchantType, Set<ItemHolder>> _requiredItems = new EnumMap<>(SkillEnchantType.class);

	public EnchantSkillHolder(StatsSet set) {
		_level = set.getInt("level");
		_enchantFailLevel = set.getInt("enchantFailLevel");
	}

	public int getLevel() {
		return _level;
	}

	public int getEnchantFailLevel() {
		return _enchantFailLevel;
	}

	public void addSp(SkillEnchantType type, long sp) {
		_sp.put(type, sp);
	}

	public long getSp(SkillEnchantType type) {
		return _sp.getOrDefault(type, 0L);
	}

	public void addChance(SkillEnchantType type, int chance) {
		_chance.put(type, chance);
	}

	public int getChance(SkillEnchantType type) {
		return _chance.getOrDefault(type, 100);
	}

	public void addRequiredItem(SkillEnchantType type, ItemHolder item) {
		_requiredItems.computeIfAbsent(type, k -> new HashSet<>()).add(item);
	}

	public Set<ItemHolder> getRequiredItems(SkillEnchantType type) {
		return _requiredItems.getOrDefault(type, Collections.emptySet());
	}
}