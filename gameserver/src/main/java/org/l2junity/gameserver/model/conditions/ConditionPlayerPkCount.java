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
package org.l2junity.gameserver.model.conditions;

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * The Class ConditionPlayerPkCount.
 */
public class ConditionPlayerPkCount extends Condition {
	public final int _pk;

	/**
	 * Instantiates a new condition player pk count.
	 *
	 * @param pk the pk
	 */
	public ConditionPlayerPkCount(int pk) {
		_pk = pk;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item) {
		if (effector.getActingPlayer() == null) {
			return false;
		}
		return effector.getActingPlayer().getPkKills() <= _pk;
	}
}
