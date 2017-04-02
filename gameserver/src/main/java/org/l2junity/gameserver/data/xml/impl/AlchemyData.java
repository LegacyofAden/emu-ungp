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
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.alchemy.AlchemyCraftData;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sdw
 */
public class AlchemyData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final AlchemyData instance = new AlchemyData();

	private static final Logger LOGGER = LoggerFactory.getLogger(AlchemyData.class);

	private final Map<Long, AlchemyCraftData> _alchemy = new HashMap<>();

	protected AlchemyData() {
	}

	protected void load() throws Exception {
		_alchemy.clear();
		parseDatapackFile("data/AlchemyData.xml");
		LOGGER.info("Loaded {} alchemy craft skills.", _alchemy.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		StatsSet set;
		Node att;
		NamedNodeMap attrs;
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("skill".equalsIgnoreCase(d.getNodeName())) {
						attrs = d.getAttributes();
						set = new StatsSet();
						for (int i = 0; i < attrs.getLength(); i++) {
							att = attrs.item(i);
							set.set(att.getNodeName(), att.getNodeValue());
						}

						final AlchemyCraftData alchemyCraft = new AlchemyCraftData(set);

						for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
							if ("ingredients".equalsIgnoreCase(c.getNodeName())) {
								for (Node b = c.getFirstChild(); b != null; b = b.getNextSibling()) {
									if ("item".equalsIgnoreCase(b.getNodeName())) {
										int ingId = Integer.parseInt(b.getAttributes().getNamedItem("id").getNodeValue());
										int ingCount = Integer.parseInt(b.getAttributes().getNamedItem("count").getNodeValue());
										alchemyCraft.addIngredient(new ItemHolder(ingId, ingCount));
									}
								}
							} else if ("production".equalsIgnoreCase(c.getNodeName())) {
								for (Node b = c.getFirstChild(); b != null; b = b.getNextSibling()) {
									if ("item".equalsIgnoreCase(b.getNodeName())) {
										final String type = b.getAttributes().getNamedItem("type").getNodeValue();
										int prodId = Integer.parseInt(b.getAttributes().getNamedItem("id").getNodeValue());
										int prodCount = Integer.parseInt(b.getAttributes().getNamedItem("count").getNodeValue());

										if (type.equalsIgnoreCase("ON_SUCCESS")) {
											alchemyCraft.setProductionSuccess(new ItemHolder(prodId, prodCount));
										} else if (type.equalsIgnoreCase("ON_FAILURE")) {
											alchemyCraft.setProductionFailure(new ItemHolder(prodId, prodCount));
										}
									}
								}
							}
						}
						_alchemy.put(SkillData.getSkillHashCode(set.getInt("id"), set.getInt("level")), alchemyCraft);
					}
				}
			}
		}
	}

	public int getLoadedElementsCount() {
		return _alchemy.size();
	}

	public AlchemyCraftData getCraftData(int skillId, int skillLevel) {
		return _alchemy.get(SkillData.getSkillHashCode(skillId, skillLevel));
	}
}
