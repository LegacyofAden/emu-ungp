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

import java.util.OptionalDouble;

import org.l2junity.gameserver.config.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.EnchantItemHPBonusData;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.L2PetInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.stats.BaseStats;
import org.l2junity.gameserver.model.stats.DoubleStat;
import org.l2junity.gameserver.model.stats.IStatsFunction;

/**
 * @author UnAfraid
 */
public class MaxHpFinalizer implements IStatsFunction
{
	@Override
	public double calc(Creature creature, OptionalDouble base, DoubleStat stat)
	{
		throwIfPresent(base);
		
		double baseValue = creature.getTemplate().getBaseValue(stat, 0);
		if (creature.isPet())
		{
			final L2PetInstance pet = (L2PetInstance) creature;
			baseValue = pet.getPetLevelData().getPetMaxHP();
		}
		else if (creature.isPlayer())
		{
			final PlayerInstance player = creature.getActingPlayer();
			if (player != null)
			{
				baseValue = player.getTemplate().getBaseHpMax(player.getLevel());
				
				// Apply enchanted item's bonus HP
				for (ItemInstance item : player.getInventory().getPaperdollItems(ItemInstance::isEnchanted))
				{
					baseValue += EnchantItemHPBonusData.getInstance().getHPBonus(item);
				}
			}
		}
		final double chaBonus = creature.isPlayer() ? BaseStats.CHA.calcBonus(creature) : 1.;
		final double conBonus = creature.getCON() > 1 ? BaseStats.CON.calcBonus(creature) : 1.;
		baseValue *= conBonus * chaBonus;
		return validateValue(creature, DoubleStat.defaultValue(creature, stat, baseValue), 1, creature.isPlayer() ? PlayerConfig.MAX_HP : Double.MAX_VALUE);
	}
}
