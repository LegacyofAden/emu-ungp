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
package org.l2junity.gameserver.model.drops;

import org.l2junity.gameserver.config.RatesConfig;
import org.l2junity.gameserver.model.actor.Creature;

/**
 * @author Sdw
 */
public class LuckDropItem extends GeneralDropItem
{
	/**
	 * @param itemId the item id
	 * @param min the min count
	 * @param max the max count
	 * @param chance the chance of this drop item
	 */
	public LuckDropItem(int itemId, long min, long max, double chance)
	{
		super(itemId, min, max, chance);
	}
	
	@Override
	protected double getChanceMultiplier(Creature killer)
	{
		return RatesConfig.BASE_LUCK_DROP_CHANCE_MULTIPLIER;
	}
	
	@Override
	protected double getGlobalAmountMultiplier()
	{
		return RatesConfig.RATE_LUCK_DROP_AMOUNT_MULTIPLIER;
	}
	
	@Override
	protected double getGlobalChanceMultiplier()
	{
		return RatesConfig.RATE_LUCK_DROP_CHANCE_MULTIPLIER;
	}
}
