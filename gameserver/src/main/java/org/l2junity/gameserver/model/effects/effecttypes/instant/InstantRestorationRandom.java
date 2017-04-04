/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.commons.util.Rnd;
import org.l2junity.core.configs.RatesConfig;
import org.l2junity.gameserver.model.ExtractableProductItem;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import java.util.ArrayList;
import java.util.List;

/**
 * Restoration Random effect implementation.
 *
 * @author Zoey76
 */
public final class InstantRestorationRandom extends AbstractEffect {
	final List<ExtractableProductItem> _products = new ArrayList<>();

	public InstantRestorationRandom(StatsSet params) {
		for (StatsSet group : params.getList("items", StatsSet.class)) {
			final List<ItemHolder> items = new ArrayList<>();
			for (StatsSet item : group.getList(".", StatsSet.class)) {
				items.add(new ItemHolder(item.getInt(".id"), item.getInt(".count")));
			}
			_products.add(new ExtractableProductItem(items, group.getFloat(".chance")));
		}
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		final double rndNum = 100 * Rnd.nextDouble();
		double chance = 0;
		double chanceFrom = 0;
		final List<ItemHolder> creationList = new ArrayList<>();

		// Explanation for future changes:
		// You get one chance for the current skill, then you can fall into
		// one of the "areas" like in a roulette.
		// Example: for an item like Id1,A1,30;Id2,A2,50;Id3,A3,20;
		// #---#-----#--#
		// 0--30----80-100
		// If you get chance equal 45% you fall into the second zone 30-80.
		// Meaning you get the second production list.
		// Calculate extraction
		for (ExtractableProductItem expi : _products) {
			chance = expi.getChance();
			if ((rndNum >= chanceFrom) && (rndNum <= (chance + chanceFrom))) {
				creationList.addAll(expi.getItems());
				break;
			}
			chanceFrom += chance;
		}

		if (creationList.isEmpty()) {
			casterPlayer.sendPacket(SystemMessageId.THERE_WAS_NOTHING_FOUND_INSIDE);
			return;
		}

		for (ItemHolder createdItem : creationList) {
			if ((createdItem.getId() <= 0) || (createdItem.getCount() <= 0)) {
				continue;
			}
			casterPlayer.addItem("Extract", createdItem.getId(), (long) (createdItem.getCount() * RatesConfig.RATE_EXTRACTABLE), caster, true);
		}
	}
}
