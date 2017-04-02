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
package handlers.effecthandlers.pump;

import org.l2junity.commons.util.MathUtil;
import org.l2junity.gameserver.enums.Position;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Sdw
 */
public class PumpCriticalRatePositionBonus extends AbstractEffect {
	protected final double _amount;
	protected final Position _position;

	public PumpCriticalRatePositionBonus(StatsSet params) {
		_amount = params.getDouble("amount", 0);
		_position = params.getEnum("position", Position.class, Position.FRONT);
	}

	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill) {
		target.getStat().mergePositionTypeValue(DoubleStat.CRITICAL_RATE, _position, (_amount / 100) + 1, MathUtil::mul);
	}

	@Override
	public void pumpEnd(Creature caster, Creature target, Skill skill) {
		target.getStat().mergePositionTypeValue(DoubleStat.CRITICAL_RATE, _position, (-_amount / 100) - 1, MathUtil::div);
	}
}
