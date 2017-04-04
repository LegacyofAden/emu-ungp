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

import org.l2junity.gameserver.enums.SkillConditionCompanionType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * @author Sdw
 */
public class OpCompanionSkillCondition implements ISkillCondition {
	private final SkillConditionCompanionType _type;

	public OpCompanionSkillCondition(StatsSet params) {
		_type = params.getEnum("type", SkillConditionCompanionType.class);
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		if (target != null) {
			switch (_type) {
				case PET: {
					return target.isPet();
				}
				case MY_SUMMON: {
					return target.isSummon() && (caster.getServitor(target.getObjectId()) != null);
				}
			}
		}
		return false;
	}
}
