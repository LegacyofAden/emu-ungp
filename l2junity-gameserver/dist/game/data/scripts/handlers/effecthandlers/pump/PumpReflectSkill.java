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
package handlers.effecthandlers.pump;

import org.l2junity.gameserver.enums.BasicProperty;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Sdw
 */
public class PumpReflectSkill extends AbstractEffect
{
	private final DoubleStat _stat;
	private final double _amount;
	
	public PumpReflectSkill(StatsSet params)
	{
		_stat = params.getEnum("type", BasicProperty.class) == BasicProperty.PHYSICAL ? DoubleStat.REFLECT_SKILL_PHYSIC : DoubleStat.REFLECT_SKILL_MAGIC;
		_amount = params.getDouble("amount", 0);
	}
	
	@Override
	public void pump(Creature target, Skill skill)
	{
		target.getStat().mergeAdd(_stat, _amount);
	}
}
