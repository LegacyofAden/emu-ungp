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
package org.l2junity.gameserver.model.holders;

import org.l2junity.gameserver.enums.ItemSkillType;

/**
 * @author UnAfraid
 */
public class ItemSkillHolder extends SkillHolder {
	private static final long serialVersionUID = 5212672298536237528L;
	private final ItemSkillType _type;
	private final int _chance;
	private final int _value;

	public ItemSkillHolder(int skillId, int skillLvl, int skillSubLevel, ItemSkillType type, int chance, int value) {
		super(skillId, skillLvl, skillSubLevel);
		_type = type;
		_chance = chance;
		_value = value;
	}

	public ItemSkillType getType() {
		return _type;
	}

	public int getChance() {
		return _chance;
	}

	public int getValue() {
		return _value;
	}
}
