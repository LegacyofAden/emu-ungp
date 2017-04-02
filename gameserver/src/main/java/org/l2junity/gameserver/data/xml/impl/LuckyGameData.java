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
import org.l2junity.gameserver.model.holders.ItemChanceHolder;
import org.l2junity.gameserver.model.holders.ItemPointHolder;
import org.l2junity.gameserver.model.holders.LuckyGameDataHolder;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Sdw
 */
@Slf4j
@StartupComponent("Data")
public class LuckyGameData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final LuckyGameData instance = new LuckyGameData();

	private final Map<Integer, LuckyGameDataHolder> _luckyGame = new HashMap<>();

	final AtomicInteger _serverPlay = new AtomicInteger();

	private LuckyGameData() {
		_luckyGame.clear();
		parseDatapackFile("data/LuckyGameData.xml");
		log.info("Loaded {} lucky game data.", _luckyGame.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "luckygame", rewardNode ->
		{
			final LuckyGameDataHolder holder = new LuckyGameDataHolder(new StatsSet(parseAttributes(rewardNode)));

			forEach(rewardNode, "common_reward", commonRewardNode -> forEach(commonRewardNode, "item", itemNode ->
			{
				holder.addCommonReward(new ItemChanceHolder(new StatsSet(parseAttributes(itemNode))));
			}));

			forEach(rewardNode, "unique_reward", uniqueRewardNode -> forEach(uniqueRewardNode, "item", itemNode ->
			{
				holder.addUniqueReward(new ItemPointHolder(new StatsSet(parseAttributes(itemNode))));
			}));

			forEach(rewardNode, "modify_reward", uniqueRewardNode ->
			{
				holder.setMinModifyRewardGame(parseInteger(uniqueRewardNode.getAttributes(), "min_game"));
				holder.setMaxModifyRewardGame(parseInteger(uniqueRewardNode.getAttributes(), "max_game"));
				forEach(uniqueRewardNode, "item", itemNode ->
				{
					holder.addModifyReward(new ItemChanceHolder(new StatsSet(parseAttributes(itemNode))));
				});
			});

			_luckyGame.put(parseInteger(rewardNode.getAttributes(), "index"), holder);
		}));
	}

	public int getLuckyGameCount() {
		return _luckyGame.size();
	}

	public LuckyGameDataHolder getLuckyGameDataByIndex(int index) {
		return _luckyGame.get(index);
	}

	public int increaseGame() {
		return _serverPlay.incrementAndGet();
	}

	public int getServerPlay() {
		return _serverPlay.get();
	}
}
