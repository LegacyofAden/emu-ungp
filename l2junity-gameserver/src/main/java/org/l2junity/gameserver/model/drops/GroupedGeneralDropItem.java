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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.config.L2JModsConfig;
import org.l2junity.gameserver.config.NpcConfig;
import org.l2junity.gameserver.config.RatesConfig;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.L2RaidBossInstance;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.L2Item;

/**
 * @author NosBit
 */
public class GroupedGeneralDropItem implements IDropItem
{
	private final double _chance;
	private List<GeneralDropItem> _items;
	
	/**
	 * @param chance the chance of this drop item.
	 */
	public GroupedGeneralDropItem(double chance)
	{
		_chance = chance;
	}
	
	protected double getChanceMultiplier(Creature killer)
	{
		return 1.;
	}
	
	protected double getGlobalChanceMultiplier()
	{
		return 1.;
	}
	
	/**
	 * Gets the chance of this drop item.
	 * @return the chance
	 */
	public double getChance()
	{
		return _chance;
	}
	
	/**
	 * Gets the chance of this drop item.
	 * @param victim the victim
	 * @param killer the killer
	 * @return the chance modified by any rates.
	 */
	public double getChance(Creature victim, Creature killer)
	{
		for (final GeneralDropItem gdi : getItems())
		{
			final L2Item item = ItemTable.getInstance().getTemplate(gdi.getItemId());
			if ((item == null) || !item.hasExImmediateEffect())
			{
				return getChance() * getChanceMultiplier(killer) * getGlobalChanceMultiplier();
			}
		}
		
		return getChance() * RatesConfig.RATE_HERB_DROP_CHANCE_MULTIPLIER;
	}
	
	/**
	 * Gets the items.
	 * @return the items
	 */
	public List<GeneralDropItem> getItems()
	{
		return _items;
	}
	
	/**
	 * Sets an item list to this drop item.
	 * @param items the item list
	 */
	public void setItems(List<GeneralDropItem> items)
	{
		_items = Collections.unmodifiableList(items);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.l2junity.gameserver.model.drop.IDropItem#calculateDrops(org.l2junity.gameserver.model.actor.L2Character, org.l2junity.gameserver.model.actor.L2Character)
	 */
	@Override
	public Collection<ItemHolder> calculateDrops(Creature victim, Creature killer)
	{
		final int levelDifference = victim.getLevel() - killer.getLevel();
		double chanceModifier;
		if (victim instanceof L2RaidBossInstance)
		{
			chanceModifier = Math.max(0, Math.min(1, (levelDifference * 0.15) + 1));
		}
		else
		{
			chanceModifier = 1;
			
			double levelGapChanceToDrop = CommonUtil.map(levelDifference, -NpcConfig.DROP_ITEM_MAX_LEVEL_DIFFERENCE, -NpcConfig.DROP_ITEM_MIN_LEVEL_DIFFERENCE, NpcConfig.DROP_ITEM_MIN_LEVEL_GAP_CHANCE, 100.0);
			// There is a chance of level gap that it wont drop this item
			if (levelGapChanceToDrop < (Rnd.nextDouble() * 100))
			{
				return null;
			}
		}
		
		final double chance = getChance(victim, killer) * chanceModifier;
		int successes;
		if (!L2JModsConfig.L2JMOD_OLD_DROP_BEHAVIOR)
		{
			successes = chance > (Rnd.nextDouble() * 100) ? 1 : 0;
		}
		else
		{
			successes = (int) (chance / 100);
			successes += (chance % 100) > (Rnd.nextDouble() * 100) ? 1 : 0;
		}
		
		if (successes > 0)
		{
			double totalChance = 0;
			final double random = (Rnd.nextDouble() * 100);
			for (GeneralDropItem item : getItems())
			{
				// Grouped item chance rates should not be modified.
				totalChance += item.getChance();
				if (totalChance > random)
				{
					final long count = Rnd.get(item.getMin(victim, killer), item.getMax(victim, killer)) * successes;
					if (count > 0)
					{
						final Collection<ItemHolder> items = new ArrayList<>(1);
						items.add(new ItemHolder(item.getItemId(), count));
						return items;
					}
				}
			}
		}
		
		return null;
	}
}
