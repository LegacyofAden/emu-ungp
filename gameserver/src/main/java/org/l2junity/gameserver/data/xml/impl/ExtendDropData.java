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
import org.l2junity.gameserver.model.drops.conditions.ExDropConditionType;
import org.l2junity.gameserver.model.drops.conditions.IExDropCondition;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.holders.ExtendDropDataHolder;
import org.l2junity.gameserver.model.holders.ExtendDropItemHolder;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.*;

/**
 * @author Sdw
 */

@Slf4j
@StartupComponent(value = "Data")
public class ExtendDropData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ExtendDropData instance = new ExtendDropData();

	private final Map<Integer, ExtendDropDataHolder> _extendDrop = new HashMap<>();

	private ExtendDropData() {
		_extendDrop.clear();
		parseDatapackFile("data/ExtendDrop.xml");
		log.info("Loaded {} ExtendDrop.", _extendDrop.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "drop", dropNode ->
		{
			final StatsSet set = new StatsSet(parseAttributes(dropNode));

			final List<ExtendDropItemHolder> items = new ArrayList<>(1);
			forEach(dropNode, "items", itemsNode -> forEach(itemsNode, "item", itemNode ->
			{
				final int itemId = parseInteger(itemNode.getAttributes(), "id");
				final int itemCount = parseInteger(itemNode.getAttributes(), "count");
				final int itemMaxCount = parseInteger(itemNode.getAttributes(), "maxCount");
				final double itemChance = parseDouble(itemNode.getAttributes(), "chance");
				final double itemAdditionalChance = parseDouble(itemNode.getAttributes(), "additionalChance");
				items.add(new ExtendDropItemHolder(itemId, itemCount, itemMaxCount, itemChance, itemAdditionalChance));
			}));
			set.set("items", items);

			final List<IExDropCondition> conditions = new ArrayList<>(1);
			forEach(dropNode, "conditions", conditionsNode -> forEach(conditionsNode, "condition", conditionNode ->
			{
				final ExDropConditionType exDropConditionType = parseEnum(conditionNode.getAttributes(), ExDropConditionType.class, "name");
				final StatsSet params = (StatsSet) parseValue(conditionNode);
				conditions.add(exDropConditionType.getNew(params));
			}));
			set.set("conditions", conditions);

			final Map<Long, SystemMessageId> systemMessages = new HashMap<>();
			forEach(dropNode, "systemMessages", systemMessagesNode -> forEach(systemMessagesNode, "systemMessage", systemMessageNode ->
			{
				final long amount = parseLong(systemMessageNode.getAttributes(), "amount");
				final SystemMessageId systemMessageId = SystemMessageId.getSystemMessageId(parseInteger(systemMessageNode.getAttributes(), "id"));
				systemMessages.put(amount, systemMessageId);
			}));
			set.set("systemMessages", systemMessages);

			_extendDrop.put(set.getInt("id"), new ExtendDropDataHolder(set));
		}));
	}

	private Object parseValue(Node node) {
		StatsSet statsSet = null;
		List<Object> list = null;
		Object text = null;
		for (node = node.getFirstChild(); node != null; node = node.getNextSibling()) {
			final String nodeName = node.getNodeName();
			switch (node.getNodeName()) {
				case "#text": {
					final String value = node.getNodeValue().trim();
					if (!value.isEmpty()) {
						text = value;
					}
					break;
				}
				case "item": {
					if (list == null) {
						list = new LinkedList<>();
					}

					final Object value = parseValue(node);
					if (value != null) {
						list.add(value);
					}
					break;
				}
				default: {
					final Object value = parseValue(node);
					if (value != null) {
						if (statsSet == null) {
							statsSet = new StatsSet();
						}

						statsSet.set(nodeName, value);
					}
				}
			}
		}
		if (list != null) {
			if (text != null) {
				throw new IllegalArgumentException("Text and list in same node are not allowed. Node[" + node + "]");
			}
			if (statsSet != null) {
				statsSet.set(".", list);
			} else {
				return list;
			}
		}
		if (text != null) {
			if (list != null) {
				throw new IllegalArgumentException("Text and list in same node are not allowed. Node[" + node + "]");
			}
			if (statsSet != null) {
				statsSet.set(".", text);
			} else {
				return text;
			}
		}
		return statsSet;
	}

	public int getLoadedElementsCount() {
		return _extendDrop.size();
	}

	public ExtendDropDataHolder getExtendDropById(int id) {
		return _extendDrop.getOrDefault(id, null);
	}
}
