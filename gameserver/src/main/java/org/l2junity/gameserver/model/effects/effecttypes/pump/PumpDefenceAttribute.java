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
package org.l2junity.gameserver.model.effects.effecttypes.pump;

import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Sdw
 */
public class PumpDefenceAttribute extends AbstractEffect {
	private final AttributeType _attribute;
	private final double _amount;

	public PumpDefenceAttribute(StatsSet params) {
		_amount = params.getDouble("amount", 0);
		_attribute = params.getEnum("attribute", AttributeType.class, AttributeType.FIRE);
	}

	@Override
	public void pump(Creature target, Skill skill) {
		DoubleStat stat = DoubleStat.FIRE_RES;

		switch (_attribute) {
			case WATER: {
				stat = DoubleStat.WATER_RES;
				break;
			}
			case WIND: {
				stat = DoubleStat.WIND_RES;
				break;
			}
			case EARTH: {
				stat = DoubleStat.EARTH_RES;
				break;
			}
			case HOLY: {
				stat = DoubleStat.HOLY_RES;
				break;
			}
			case DARK: {
				stat = DoubleStat.DARK_RES;
				break;
			}
		}
		target.getStat().mergeAdd(stat, _amount);
	}
}
