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

import java.util.Arrays;
import java.util.List;

import org.l2junity.gameserver.enums.SpeedType;
import org.l2junity.gameserver.enums.StatModifierType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Sdw
 */
public final class PumpSpeed extends AbstractEffect
{
	private final double _amount;
	private final StatModifierType _mode;
	private List<SpeedType> _speedType;
	
	public PumpSpeed(StatsSet params)
	{
		_amount = params.getDouble("amount", 0);
		_mode = params.getEnum("mode", StatModifierType.class, StatModifierType.DIFF);
		_speedType = params.getEnumList("weaponType", SpeedType.class);
		if (_speedType == null)
		{
			_speedType = Arrays.asList(SpeedType.ALL);
		}
	}
	
	@Override
	public void pump(Creature target, Skill skill)
	{
		switch (_mode)
		{
			case DIFF:
			{
				for (SpeedType type : _speedType)
				{
					switch (type)
					{
						case RUN:
						{
							target.getStat().mergeAdd(DoubleStat.RUN_SPEED, _amount);
							break;
						}
						case WALK:
						{
							target.getStat().mergeAdd(DoubleStat.WALK_SPEED, _amount);
							break;
						}
						case SWIM_RUN:
						{
							target.getStat().mergeAdd(DoubleStat.SWIM_RUN_SPEED, _amount);
							break;
						}
						case SWIM_WALK:
						{
							target.getStat().mergeAdd(DoubleStat.SWIM_WALK_SPEED, _amount);
							break;
						}
						case FLY_RUN:
						{
							target.getStat().mergeAdd(DoubleStat.FLY_RUN_SPEED, _amount);
							break;
						}
						case FLY_WALK:
						{
							target.getStat().mergeAdd(DoubleStat.FLY_WALK_SPEED, _amount);
							break;
						}
						default:
						{
							target.getStat().mergeAdd(DoubleStat.RUN_SPEED, _amount);
							target.getStat().mergeAdd(DoubleStat.WALK_SPEED, _amount);
							target.getStat().mergeAdd(DoubleStat.SWIM_RUN_SPEED, _amount);
							target.getStat().mergeAdd(DoubleStat.SWIM_WALK_SPEED, _amount);
							target.getStat().mergeAdd(DoubleStat.FLY_RUN_SPEED, _amount);
							target.getStat().mergeAdd(DoubleStat.FLY_WALK_SPEED, _amount);
							break;
						}
					}
				}
				break;
			}
			case PER:
			{
				for (SpeedType type : _speedType)
				{
					switch (type)
					{
						case RUN:
						{
							target.getStat().mergeMul(DoubleStat.RUN_SPEED, (_amount / 100) + 1);
							break;
						}
						case WALK:
						{
							target.getStat().mergeMul(DoubleStat.WALK_SPEED, (_amount / 100) + 1);
							break;
						}
						case SWIM_RUN:
						{
							target.getStat().mergeMul(DoubleStat.SWIM_RUN_SPEED, (_amount / 100) + 1);
							break;
						}
						case SWIM_WALK:
						{
							target.getStat().mergeMul(DoubleStat.SWIM_WALK_SPEED, (_amount / 100) + 1);
							break;
						}
						case FLY_RUN:
						{
							target.getStat().mergeMul(DoubleStat.FLY_RUN_SPEED, (_amount / 100) + 1);
							break;
						}
						case FLY_WALK:
						{
							target.getStat().mergeMul(DoubleStat.FLY_WALK_SPEED, (_amount / 100) + 1);
							break;
						}
						default:
						{
							target.getStat().mergeMul(DoubleStat.RUN_SPEED, (_amount / 100) + 1);
							target.getStat().mergeMul(DoubleStat.WALK_SPEED, (_amount / 100) + 1);
							target.getStat().mergeMul(DoubleStat.SWIM_RUN_SPEED, (_amount / 100) + 1);
							target.getStat().mergeMul(DoubleStat.SWIM_WALK_SPEED, (_amount / 100) + 1);
							target.getStat().mergeMul(DoubleStat.FLY_RUN_SPEED, (_amount / 100) + 1);
							target.getStat().mergeMul(DoubleStat.FLY_WALK_SPEED, (_amount / 100) + 1);
							break;
						}
					}
				}
				break;
			}
		}
	}
}
