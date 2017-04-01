/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.effecthandlers.tick;

import org.l2junity.gameserver.enums.StatModifierType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.Formulas;

/**
 * Dam Over Time effect implementation.
 */
public final class TickHpFatal extends AbstractEffect
{
	private final double _power;
	private final StatModifierType _mode;
	
	public TickHpFatal(StatsSet params)
	{
		_power = params.getDouble("power");
		_mode = params.getEnum("mode", StatModifierType.class, StatModifierType.DIFF);
		setTicks(params.getInt("ticks"));
	}
	
	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill)
	{
		if (skill.isMagic())
		{
			// TODO: M.Crit can occur even if this skill is resisted. Only then m.crit damage is applied and not debuff
			final boolean mcrit = Formulas.calcCrit(skill.getMagicCriticalRate(), caster, target, skill);
			if (mcrit)
			{
				double damage = _power * 10; // Tests show that 10 times HP DOT is taken during magic critical.
				caster.doAttack(damage, target, skill, true, false, true, false);
			}
		}
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.DMG_OVER_TIME;
	}
	
	@Override
	public void tick(Creature caster, Creature target, Skill skill)
	{
		if (target.isDead())
		{
			return;
		}
		
		double damage = 0;
		
		switch (_mode)
		{
			case DIFF:
			{
				damage = _power * getTicksMultiplier();
				break;
			}
			case PER:
			{
				damage = target.getCurrentHp() * _power * getTicksMultiplier();
				break;
			}
		}
		
		caster.doAttack(Math.abs(damage), target, skill, true, false, false, false);
	}
}
