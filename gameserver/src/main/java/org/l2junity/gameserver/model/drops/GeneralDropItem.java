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

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.L2JModsConfig;
import org.l2junity.core.configs.NpcConfig;
import org.l2junity.core.configs.RatesConfig;
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.items.L2Item;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author NosBit
 */
public class GeneralDropItem implements IDropItem {
	private final int _itemId;
	private final long _min;
	private final long _max;
	private final double _chance;

	/**
	 * @param itemId the item id
	 * @param min    the min count
	 * @param max    the max count
	 * @param chance the chance of this drop item
	 */
	public GeneralDropItem(int itemId, long min, long max, double chance) {
		_itemId = itemId;
		_min = min;
		_max = max;
		_chance = chance;
	}

	protected double getChanceMultiplier(Creature killer) {
		return 1.;
	}

	protected double getAmountMultiplier(Creature killer) {
		return 1.;
	}

	protected double getGlobalChanceMultiplier() {
		return 1.;
	}

	protected double getGlobalAmountMultiplier() {
		return 1.;
	}

	private long getMinMax(Creature victim, Creature killer, long val) {
		double multiplier = getAmountMultiplier(killer);

		// individual drop amount
		Float individualDropAmountMultiplier = RatesConfig.RATE_DROP_AMOUNT_MULTIPLIER.get(getItemId());
		if (individualDropAmountMultiplier != null) {
			// individual amount list multiplier
			multiplier *= individualDropAmountMultiplier;
		} else {
			final L2Item item = ItemTable.getInstance().getTemplate(getItemId());
			// global amount multiplier
			if ((item != null) && item.hasExImmediateEffect()) {
				// global herb amount multiplier
				multiplier *= RatesConfig.RATE_HERB_DROP_AMOUNT_MULTIPLIER;
			} else {
				// drop type specific amount multiplier
				multiplier *= getGlobalAmountMultiplier();
			}
		}

		// global champions amount multiplier
		if (victim.isChampion()) {
			multiplier *= getItemId() != Inventory.ADENA_ID ? L2JModsConfig.L2JMOD_CHAMPION_REWARDS : L2JModsConfig.L2JMOD_CHAMPION_ADENAS_REWARDS;
		}

		return (long) (val * multiplier);
	}

	/**
	 * Gets the item id
	 *
	 * @return the item id
	 */
	public int getItemId() {
		return _itemId;
	}

	/**
	 * Gets the min drop count
	 *
	 * @return the min
	 */
	public long getMin() {
		return _min;
	}

	/**
	 * Gets the min drop count
	 *
	 * @param victim the victim
	 * @param killer the killer
	 * @return the min modified by any rates.
	 */
	public long getMin(Creature victim, Creature killer) {
		return getMinMax(victim, killer, getMin());
	}

	/**
	 * Gets the max drop count
	 *
	 * @return the max
	 */
	public long getMax() {
		return _max;
	}

	/**
	 * Gets the max drop count
	 *
	 * @param victim the victim
	 * @param killer the killer
	 * @return the max modified by any rates.
	 */
	public long getMax(Creature victim, Creature killer) {
		return getMinMax(victim, killer, getMax());
	}

	/**
	 * Gets the chance of this drop item.
	 *
	 * @return the chance
	 */
	public double getChance() {
		return _chance;
	}

	/**
	 * Gets the chance of this drop item.
	 *
	 * @param victim the victim
	 * @param killer the killer
	 * @return the chance modified by any rates.
	 */
	public double getChance(Creature victim, Creature killer) {
		double multiplier = 1;

		// individual drop chance
		Float individualDropChanceMultiplier = RatesConfig.RATE_DROP_CHANCE_MULTIPLIER.get(getItemId());
		if (individualDropChanceMultiplier != null) {
			multiplier *= individualDropChanceMultiplier;
		} else {
			final L2Item item = ItemTable.getInstance().getTemplate(getItemId());
			if ((item != null) && item.hasExImmediateEffect()) {
				multiplier *= RatesConfig.RATE_HERB_DROP_CHANCE_MULTIPLIER;
			} else {
				multiplier *= getGlobalChanceMultiplier();
			}
		}

		if (victim.isChampion()) {
			multiplier *= L2JModsConfig.L2JMOD_CHAMPION_REWARDS;
		}

		return (getChance() * getChanceMultiplier(killer) * multiplier);
	}

	/*
	 * (non-Javadoc)
	 * @see org.l2junity.gameserver.model.drop.IDropItem#calculateDrops(org.l2junity.gameserver.model.actor.L2Character, org.l2junity.gameserver.model.actor.L2Character)
	 */
	@Override
	public Collection<ItemHolder> calculateDrops(Creature victim, Creature killer) {
		final int levelDifference = victim.getLevel() - killer.getLevel();
		final double levelGapChanceToDrop;
		if (getItemId() == Inventory.ADENA_ID) {
			levelGapChanceToDrop = CommonUtil.map(levelDifference, -NpcConfig.DROP_ADENA_MAX_LEVEL_DIFFERENCE, -NpcConfig.DROP_ADENA_MIN_LEVEL_DIFFERENCE, NpcConfig.DROP_ADENA_MIN_LEVEL_GAP_CHANCE, 100.0);
		} else {
			levelGapChanceToDrop = CommonUtil.map(levelDifference, -NpcConfig.DROP_ITEM_MAX_LEVEL_DIFFERENCE, -NpcConfig.DROP_ITEM_MIN_LEVEL_DIFFERENCE, NpcConfig.DROP_ITEM_MIN_LEVEL_GAP_CHANCE, 100.0);
		}

		// There is a chance of level gap that it wont drop this item
		if (levelGapChanceToDrop < (Rnd.nextDouble() * 100)) {
			return null;
		}

		final double chance = getChance(victim, killer);
		int successes;
		if (!L2JModsConfig.L2JMOD_OLD_DROP_BEHAVIOR) {
			successes = chance > (Rnd.nextDouble() * 100) ? 1 : 0;
		} else {
			successes = (int) (chance / 100);
			successes += (chance % 100) > (Rnd.nextDouble() * 100) ? 1 : 0;
		}

		if (successes > 0) {
			final long count = Rnd.get(getMin(victim, killer), getMax(victim, killer)) * successes;
			if (count > 0) {
				final Collection<ItemHolder> items = new ArrayList<>(1);
				items.add(new ItemHolder(getItemId(), count));
				return items;
			}
		}

		return null;
	}
}
