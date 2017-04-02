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
package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.items.combination.CombinationItem;
import org.l2junity.gameserver.model.items.combination.CombinationItemReward;
import org.l2junity.gameserver.model.items.combination.CombinationItemType;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class CombinationItemsData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final CombinationItemsData instance = new CombinationItemsData();

	private final List<CombinationItem> _items = new ArrayList<>();

	private CombinationItemsData() {
		reload();
	}

	protected void reload() {
		_items.clear();
		parseDatapackFile("data/combinationItems.xml");
		LOGGER.info("Loaded: {} combination items", _items.size());
	}

	@Override
	public void parseDocument(Document doc, Path file) {
		forEach(doc, "list", listNode -> forEach(listNode, "item", itemNode ->
		{
			final CombinationItem item = new CombinationItem(new StatsSet(parseAttributes(itemNode)));

			forEach(itemNode, "reward", rewardNode ->
			{
				final int id = parseInteger(rewardNode.getAttributes(), "id");
				final int count = parseInteger(rewardNode.getAttributes(), "count", 1);
				final CombinationItemType type = parseEnum(rewardNode.getAttributes(), CombinationItemType.class, "type");
				item.addReward(new CombinationItemReward(id, count, type));
			});
			_items.add(item);
		}));
	}

	public int getLoadedElementsCount() {
		return _items.size();
	}

	public List<CombinationItem> getItems() {
		return _items;
	}

	public CombinationItem getItemsBySlots(int firstSlot, int secondSlot) {
		return _items.stream().filter(item -> (item.getItemOne() == firstSlot) && (item.getItemTwo() == secondSlot)).findFirst().orElse(null);
	}

	public List<CombinationItem> getItemsByFirstSlot(int id) {
		return _items.stream().filter(item -> item.getItemOne() == id).collect(Collectors.toList());
	}

	public List<CombinationItem> getItemsBySecondSlot(int id) {
		return _items.stream().filter(item -> item.getItemTwo() == id).collect(Collectors.toList());
	}
}