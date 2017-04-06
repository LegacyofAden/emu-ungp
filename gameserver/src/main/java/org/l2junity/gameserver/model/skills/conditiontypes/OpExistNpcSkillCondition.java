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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

import java.util.List;

/**
 * @author UnAfraid
 */
public class OpExistNpcSkillCondition implements ISkillCondition {
	private final List<Integer> _npcIds;
	private final int _range;
	private final boolean _isAround;

	public OpExistNpcSkillCondition(StatsSet params) {
		_npcIds = params.getList("npcIds", Integer.class);
		_range = params.getInt("range");
		_isAround = params.getBoolean("isAround");
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		final List<Npc> npcs = caster.getWorld().getVisibleObjects(caster, Npc.class, _range);
		return _isAround == npcs.stream().anyMatch(npc -> _npcIds.contains(npc.getId()));
	}
}
