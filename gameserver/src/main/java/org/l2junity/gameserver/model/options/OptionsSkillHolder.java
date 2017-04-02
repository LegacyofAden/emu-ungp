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
package org.l2junity.gameserver.model.options;

import org.l2junity.gameserver.model.holders.SkillHolder;

/**
 * @author UnAfraid
 */
public class OptionsSkillHolder extends SkillHolder {
	private static final long serialVersionUID = 7684788619832085612L;
	private final OptionsSkillType _type;
	private final double _chance;

	/**
	 * @param skillId
	 * @param skillLvl
	 * @param type
	 * @param chance
	 */
	public OptionsSkillHolder(int skillId, int skillLvl, double chance, OptionsSkillType type) {
		super(skillId, skillLvl);
		_chance = chance;
		_type = type;
	}

	public OptionsSkillType getSkillType() {
		return _type;
	}

	public double getChance() {
		return _chance;
	}
}
