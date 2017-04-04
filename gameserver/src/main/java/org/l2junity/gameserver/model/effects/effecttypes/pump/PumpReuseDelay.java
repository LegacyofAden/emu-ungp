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
package org.l2junity.gameserver.model.effects.effecttypes.pump;

import org.l2junity.commons.util.MathUtil;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * @author Sdw
 */
public class PumpReuseDelay extends AbstractEffect {
	private final int _magicType;
	private final double _amount;

	public PumpReuseDelay(StatsSet params) {
		_magicType = params.getInt("magicType", 0);
		_amount = params.getDouble("amount", 0);
	}

	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill) {
		target.getStat().mergeReuseTypeValue(_magicType, (_amount / 100) + 1, MathUtil::mul);
	}

	@Override
	public void pumpEnd(Creature caster, Creature target, Skill skill) {
		target.getStat().mergeReuseTypeValue(_magicType, (_amount / 100) + 1, MathUtil::div);
	}
}
