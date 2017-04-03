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
package org.l2junity.gameserver.model.drops.conditions;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;

/**
 * @author Sdw
 */
public class NpcLevelExDropCondition implements IExDropCondition {
	private final int _minLevel;
	private final int _maxLevel;

	public NpcLevelExDropCondition(StatsSet params) {
		_minLevel = params.getInt("minLevel");
		_maxLevel = params.getInt("maxLevel");
	}

	@Override
	public boolean test(Creature creature, WorldObject object) {
		return object.isNpc() && (((Creature) object).getLevel() >= _minLevel) && (((Creature) object).getLevel() < _maxLevel);
	}

}
