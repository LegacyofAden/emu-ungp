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
package org.l2junity.gameserver.model.skills.conditiontypes;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * @author Sdw
 */
public class OpEnchantRangeSkillCondition implements ISkillCondition {
	private final int _minEnchant;
	private final int _maxEnchant;
	private final OpEnchantRangeType _type;

	public OpEnchantRangeSkillCondition(StatsSet params) {
		_minEnchant = params.getInt("minEnchant");
		_maxEnchant = params.getInt("maxEnchant");
		_type = params.getEnum("type", OpEnchantRangeType.class);
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		if ((target != null) && target.isItem()) {
			final ItemInstance item = (ItemInstance) target;

			switch (_type) {
				case NONE: {
					return (item.getEnchantLevel() >= _minEnchant) && (item.getEnchantLevel() <= _maxEnchant);
				}
				case NORMAL: {
					return item.isWeapon() && (item.getWeaponItem().isMagicWeapon() || ((item.getEnchantLevel() >= _minEnchant) && (item.getEnchantLevel() <= _maxEnchant)));
				}
				case MAGIC: {
					return item.isWeapon() && (!item.getWeaponItem().isMagicWeapon() || ((item.getEnchantLevel() >= _minEnchant) && (item.getEnchantLevel() <= _maxEnchant)));
				}
			}
		}
		return false;
	}

	public enum OpEnchantRangeType {
		NONE,
		NORMAL,
		MAGIC;
	}
}
