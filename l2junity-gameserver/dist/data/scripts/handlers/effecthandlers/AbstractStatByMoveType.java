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
package handlers.effecthandlers;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.model.stats.MoveType;

/**
 * StatByMoveType effect implementation.
 * @author UnAfraid
 */
public abstract class AbstractStatByMoveType extends AbstractEffect
{
	private final DoubleStat _stat;
	private final MoveType _type;
	private final double _value;
	
	public AbstractStatByMoveType(StatsSet params, DoubleStat stat)
	{
		_stat = stat;
		_type = params.getEnum("type", MoveType.class);
		_value = params.getDouble("value");
	}
	
	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill)
	{
		target.getStat().mergeMoveTypeValue(_stat, _type, _value);
	}
	
	@Override
	public void pumpEnd(Creature caster, Creature target, Skill skill)
	{
		target.getStat().mergeMoveTypeValue(_stat, _type, -_value);
	}
}
