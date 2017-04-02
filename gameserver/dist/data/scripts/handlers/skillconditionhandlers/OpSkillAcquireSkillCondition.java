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
package handlers.skillconditionhandlers;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * @author Sdw
 */
public class OpSkillAcquireSkillCondition implements ISkillCondition {
	final int _skillId;
	private final boolean _hasLearned;

	public OpSkillAcquireSkillCondition(StatsSet params) {
		_skillId = params.getInt("skillId");
		_hasLearned = params.getBoolean("hasLearned");
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		if (target.isPlayer()) {
			final int skillLevel = target.getActingPlayer().getSkillLevel(_skillId);
			return _hasLearned ? skillLevel != 0 : skillLevel == 0;
		}
		return target.isPlayable() || target.isPet();
	}
}
