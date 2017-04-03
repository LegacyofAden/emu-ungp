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
 * The Class ConditionPlayerFlyMounted.
 *
 * @author kerberos
 */
public class ConditionPlayerFlyMounted extends Condition {
	private final boolean _val;

	/**
	 * Instantiates a new condition player fly mounted.
	 *
	 * @param val the val
	 */
	public ConditionPlayerFlyMounted(boolean val) {
		_val = val;
	}

	@Override
	public boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item) {
		return (effector.getActingPlayer() == null) || (effector.getActingPlayer().isFlyingMounted() == _val);
	}
}
