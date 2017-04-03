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
package org.l2junity.gameserver.model.stats.finalizers;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.stats.BaseStats;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.model.stats.IStatsFunction;

import java.util.OptionalDouble;

/**
 * @author UnAfraid
 */
public class MCritRateFinalizer implements IStatsFunction {
	@Override
	public double calc(Creature creature, OptionalDouble base, DoubleStat stat) {
		throwIfPresent(base);

		double baseValue = calcEquippedItemsBaseValue(creature, stat);
		if (creature.isPlayer()) {
			baseValue += calcEnchantBodyPart(creature, ItemTemplate.SLOT_LEGS);
		}

		final double witBonus = creature.getWIT() > 0 ? BaseStats.WIT.calcBonus(creature) : 1.;
		baseValue *= witBonus * 10;
		return validateValue(creature, DoubleStat.defaultValue(creature, stat, baseValue), 0, PlayerConfig.MAX_MCRIT_RATE);
	}

	@Override
	public double calcEnchantBodyPartBonus(int enchantLevel, boolean isBlessed) {
		if (isBlessed) {
			return (0.5 * Math.max(enchantLevel - 3, 0)) + (0.5 * Math.max(enchantLevel - 6, 0));
		}

		return (0.34 * Math.max(enchantLevel - 3, 0)) + (0.34 * Math.max(enchantLevel - 6, 0));
	}
}
