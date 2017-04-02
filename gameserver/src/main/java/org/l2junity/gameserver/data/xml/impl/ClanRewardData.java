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
import org.l2junity.commons.util.IXmlReader;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.enums.ClanRewardType;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.pledge.ClanRewardBonus;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class ClanRewardData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ClanRewardData instance = new ClanRewardData();

	private final Map<ClanRewardType, List<ClanRewardBonus>> _clanRewards = new ConcurrentHashMap<>();

	private ClanRewardData() {
		parseDatapackFile("configs/xml/ClanReward.xml");
		for (ClanRewardType type : ClanRewardType.values()) {
			LOGGER.info("Loaded: {} rewards for {}", _clanRewards.containsKey(type) ? _clanRewards.get(type).size() : 0, type);
		}
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc.getFirstChild(), IXmlReader::isNode, listNode ->
		{
			switch (listNode.getNodeName()) {
				case "membersOnline": {
					parseMembersOnline(listNode);
					break;
				}
				case "huntingBonus": {
					parseHuntingBonus(listNode);
					break;
				}
			}
		});
	}

	public int getLoadedElementsCount() {
		return _clanRewards.size();
	}

	private void parseMembersOnline(Node node) {
		forEach(node, IXmlReader::isNode, memberNode ->
		{
			if ("players".equalsIgnoreCase(memberNode.getNodeName())) {
				final NamedNodeMap attrs = memberNode.getAttributes();
				int requiredAmount = parseInteger(attrs, "size");
				int level = parseInteger(attrs, "level");
				final ClanRewardBonus bonus = new ClanRewardBonus(ClanRewardType.MEMBERS_ONLINE, level, requiredAmount);
				forEach(memberNode, IXmlReader::isNode, skillNode ->
				{
					if ("skill".equalsIgnoreCase(skillNode.getNodeName())) {
						final NamedNodeMap skillAttr = skillNode.getAttributes();
						final int skillId = parseInteger(skillAttr, "id");
						final int skillLevel = parseInteger(skillAttr, "level");
						bonus.setSkillReward(new SkillHolder(skillId, skillLevel));
					}
				});
				_clanRewards.computeIfAbsent(bonus.getType(), key -> new ArrayList<>()).add(bonus);
			}
		});
	}

	private void parseHuntingBonus(Node node) {
		forEach(node, IXmlReader::isNode, memberNode ->
		{
			if ("hunting".equalsIgnoreCase(memberNode.getNodeName())) {
				final NamedNodeMap attrs = memberNode.getAttributes();
				int requiredAmount = parseInteger(attrs, "points");
				int level = parseInteger(attrs, "level");
				final ClanRewardBonus bonus = new ClanRewardBonus(ClanRewardType.HUNTING_MONSTERS, level, requiredAmount);
				forEach(memberNode, IXmlReader::isNode, itemsNode ->
				{
					if ("item".equalsIgnoreCase(itemsNode.getNodeName())) {
						final NamedNodeMap itemsAttr = itemsNode.getAttributes();
						final int id = parseInteger(itemsAttr, "id");
						final int count = parseInteger(itemsAttr, "count");
						bonus.setItemReward(new ItemHolder(id, count));
					}
				});
				_clanRewards.computeIfAbsent(bonus.getType(), key -> new ArrayList<>()).add(bonus);
			}
		});
	}

	public List<ClanRewardBonus> getClanRewardBonuses(ClanRewardType type) {
		return _clanRewards.get(type);
	}

	public ClanRewardBonus getHighestReward(ClanRewardType type) {
		ClanRewardBonus selectedBonus = null;
		for (ClanRewardBonus currentBonus : _clanRewards.get(type)) {
			if ((selectedBonus == null) || (selectedBonus.getLevel() < currentBonus.getLevel())) {
				selectedBonus = currentBonus;
			}
		}
		return selectedBonus;
	}

	public Collection<List<ClanRewardBonus>> getClanRewardBonuses() {
		return _clanRewards.values();
	}
}
