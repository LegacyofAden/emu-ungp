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
import org.l2junity.gameserver.model.ensoul.EnsoulFee;
import org.l2junity.gameserver.model.ensoul.EnsoulOption;
import org.l2junity.gameserver.model.ensoul.EnsoulStone;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.type.CrystalType;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class EnsoulData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final EnsoulData instance = new EnsoulData();

	private final Map<CrystalType, EnsoulFee> _ensoulFees = new EnumMap<>(CrystalType.class);
	private final Map<Integer, EnsoulOption> _ensoulOptions = new HashMap<>();
	private final Map<Integer, EnsoulStone> _ensoulStones = new HashMap<>();

	private EnsoulData() {
		parseDatapackDirectory("data/stats/ensoul", true);
		log.info("Loaded: {} fees", _ensoulFees.size());
		log.info("Loaded: {} options", _ensoulOptions.size());
		log.info("Loaded: {} stones", _ensoulStones.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, IXmlReader::isNode, ensoulNode ->
		{
			switch (ensoulNode.getNodeName()) {
				case "fee": {
					parseFees(ensoulNode);
					break;
				}
				case "option": {
					parseOptions(ensoulNode);
					break;
				}
				case "stone": {
					parseStones(ensoulNode);
					break;
				}
			}
		}));
	}

	private void parseFees(Node ensoulNode) {
		final CrystalType type = parseEnum(ensoulNode.getAttributes(), CrystalType.class, "crystalType");
		final EnsoulFee fee = new EnsoulFee(type);
		forEach(ensoulNode, IXmlReader::isNode, feeNode ->
		{
			switch (feeNode.getNodeName()) {
				case "first": {
					parseFee(feeNode, fee, 0);
					break;
				}
				case "secondary": {
					parseFee(feeNode, fee, 1);
					break;
				}
				case "third": {
					parseFee(feeNode, fee, 2);
					break;
				}
				case "reFirst": {
					parseReFee(feeNode, fee, 0);
					break;
				}
				case "reSecondary": {
					parseReFee(feeNode, fee, 1);
					break;
				}
				case "reThird": {
					parseReFee(feeNode, fee, 2);
					break;
				}
			}
		});
	}

	private void parseFee(Node ensoulNode, EnsoulFee fee, int index) {
		final NamedNodeMap attrs = ensoulNode.getAttributes();
		final int id = parseInteger(attrs, "itemId");
		final int count = parseInteger(attrs, "count");
		fee.setEnsoul(index, new ItemHolder(id, count));
		_ensoulFees.put(fee.getCrystalType(), fee);
	}

	private void parseReFee(Node ensoulNode, EnsoulFee fee, int index) {
		final NamedNodeMap attrs = ensoulNode.getAttributes();
		final int id = parseInteger(attrs, "itemId");
		final int count = parseInteger(attrs, "count");
		fee.setResoul(index, new ItemHolder(id, count));
	}

	private void parseOptions(Node ensoulNode) {
		final NamedNodeMap attrs = ensoulNode.getAttributes();
		final int id = parseInteger(attrs, "id");
		final String name = parseString(attrs, "name");
		final String desc = parseString(attrs, "desc");
		final int skillId = parseInteger(attrs, "skillId");
		final int skillLevel = parseInteger(attrs, "skillLevel");
		final EnsoulOption option = new EnsoulOption(id, name, desc, skillId, skillLevel);
		_ensoulOptions.put(option.getId(), option);
	}

	private void parseStones(Node ensoulNode) {
		final NamedNodeMap attrs = ensoulNode.getAttributes();
		final int id = parseInteger(attrs, "id");
		final int slotType = parseInteger(attrs, "slotType");
		final EnsoulStone stone = new EnsoulStone(id, slotType);
		forEach(ensoulNode, "option", optionNode -> stone.addOption(parseInteger(optionNode.getAttributes(), "id")));
		_ensoulStones.put(stone.getId(), stone);
	}

	public int getLoadedElementsCount() {
		return _ensoulFees.size() + _ensoulOptions.size() + _ensoulStones.size();
	}

	public ItemHolder getEnsoulFee(CrystalType type, int index) {
		final EnsoulFee fee = _ensoulFees.get(type);
		return fee != null ? fee.getEnsoul(index) : null;
	}

	public ItemHolder getResoulFee(CrystalType type, int index) {
		final EnsoulFee fee = _ensoulFees.get(type);
		return fee != null ? fee.getResoul(index) : null;
	}

	public EnsoulOption getOption(int id) {
		return _ensoulOptions.get(id);
	}

	public EnsoulStone getStone(int id) {
		return _ensoulStones.get(id);
	}
}