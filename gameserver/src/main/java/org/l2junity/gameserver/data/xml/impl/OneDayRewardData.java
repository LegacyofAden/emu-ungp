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
package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.model.OneDayRewardDataHolder;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sdw
 */
@Slf4j
@StartupComponent(value = "Data")
public class OneDayRewardData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final OneDayRewardData instance = new OneDayRewardData();

	private final Map<Integer, List<OneDayRewardDataHolder>> _oneDayReward = new LinkedHashMap<>();

	private OneDayRewardData() {
		_oneDayReward.clear();
		parseDatapackFile("data/OneDayReward.xml");
		log.info("Loaded {} one day rewards.", _oneDayReward.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "reward", rewardNode ->
		{
			final StatsSet set = new StatsSet(parseAttributes(rewardNode));

			final List<ItemHolder> items = new ArrayList<>(1);
			forEach(rewardNode, "items", itemsNode -> forEach(itemsNode, "item", itemNode ->
			{
				final int itemId = parseInteger(itemNode.getAttributes(), "id");
				final int itemCount = parseInteger(itemNode.getAttributes(), "count");
				items.add(new ItemHolder(itemId, itemCount));
			}));

			set.set("items", items);

			final List<ClassId> classRestriction = new ArrayList<>(1);
			forEach(rewardNode, "classId", classRestrictionNode ->
			{
				classRestriction.add(ClassId.getClassId(Integer.parseInt(classRestrictionNode.getTextContent())));
			});
			set.set("classRestriction", classRestriction);

			// Initial values in case handler doesn't exists
			set.set("handler", "");
			set.set("params", StatsSet.EMPTY_STATSET);

			// Parse handler and parameters
			forEach(rewardNode, "handler", handlerNode ->
			{
				set.set("handler", parseString(handlerNode.getAttributes(), "name"));

				final StatsSet params = new StatsSet();
				set.set("params", params);
				forEach(handlerNode, "param", paramNode -> params.set(parseString(paramNode.getAttributes(), "name"), paramNode.getTextContent()));
			});

			final OneDayRewardDataHolder holder = new OneDayRewardDataHolder(set);
			_oneDayReward.computeIfAbsent(holder.getId(), k -> new ArrayList<>()).add(holder);
		}));
	}

	public int getRewardCount() {
		return _oneDayReward.size();
	}

	public Collection<OneDayRewardDataHolder> getOneDayRewardData() {
		return _oneDayReward.values()
				.stream()
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public Collection<OneDayRewardDataHolder> getOneDayRewardData(Player player) {
		return _oneDayReward.values()
				.stream()
				.flatMap(List::stream)
				.filter(o -> o.isDisplayable(player))
				.collect(Collectors.toList());
	}

	public Collection<OneDayRewardDataHolder> getOneDayRewardData(int id) {
		return _oneDayReward.get(id);
	}
}
