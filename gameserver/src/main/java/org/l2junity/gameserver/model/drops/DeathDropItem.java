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
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author NosBit
 */
public class DeathDropItem extends GeneralDropItem
{
	/**
	 * @param itemId the item id
	 * @param min the min count
	 * @param max the max count
	 * @param chance the chance of this drop item
	 */
	public DeathDropItem(int itemId, long min, long max, double chance)
	{
		super(itemId, min, max, chance);
	}
	
	@Override
	protected double getChanceMultiplier(Creature killer)
	{
		// Adena is handled as count.
		if (getItemId() == Inventory.ADENA_ID)
		{
			return 1d;
		}
		
		final Party party = killer.getParty();
		if (party != null)
		{
			return (party.getMembers().stream().mapToDouble(p -> p.getStat().getAdd(DoubleStat.BONUS_DROP) + 100).sum() / party.getMemberCount()) / 100;
		}
		return (killer.getStat().getAdd(DoubleStat.BONUS_DROP) + 100) / 100;
	}
	
	@Override
	protected double getAmountMultiplier(Creature killer)
	{
		// Non-Adena drops are handled as chance.
		if (getItemId() != Inventory.ADENA_ID)
		{
			return 1d;
		}
		
		final Party party = killer.getParty();
		if (party != null)
		{
			return (party.getMembers().stream().mapToDouble(p -> p.getStat().getAdd(DoubleStat.BONUS_ADENA) + 100).sum() / party.getMemberCount()) / 100;
		}
		return (killer.getStat().getAdd(DoubleStat.BONUS_ADENA) + 100) / 100;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.l2junity.gameserver.model.drops.GeneralDropItem#getGlobalAmountMultiplier()
	 */
	@Override
	protected double getGlobalAmountMultiplier()
	{
		return RatesConfig.RATE_DEATH_DROP_AMOUNT_MULTIPLIER;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.l2junity.gameserver.model.drops.GeneralDropItem#getGlobalChanceMultiplier()
	 */
	@Override
	protected double getGlobalChanceMultiplier()
	{
		return RatesConfig.RATE_DEATH_DROP_CHANCE_MULTIPLIER;
	}
}
