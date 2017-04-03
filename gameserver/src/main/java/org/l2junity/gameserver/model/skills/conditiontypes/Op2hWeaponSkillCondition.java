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
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.Weapon;
import org.l2junity.gameserver.model.items.type.WeaponType;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * @author UnAfraid
 */
public class Op2hWeaponSkillCondition implements ISkillCondition {
	private final List<WeaponType> _weaponTypes = new ArrayList<>();

	public Op2hWeaponSkillCondition(StatsSet params) {
		final List<String> weaponTypes = params.getList("weaponType", String.class);
		if (weaponTypes != null) {
			weaponTypes.stream().map(WeaponType::valueOf).forEach(_weaponTypes::add);
		}
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		final Weapon weapon = caster.getActiveWeaponItem();
		if (weapon == null) {
			return false;
		}
		return _weaponTypes.stream().anyMatch(weaponType -> (weapon.getItemType() == weaponType) && ((weapon.getBodyPart() & ItemTemplate.SLOT_LR_HAND) != 0));
	}
}
