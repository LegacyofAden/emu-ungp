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
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.options.EnchantOptions;
import org.l2junity.gameserver.util.Util;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class EnchantItemOptionsData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final EnchantItemOptionsData instance = new EnchantItemOptionsData();

	private final Map<Integer, Map<Integer, EnchantOptions>> _data = new HashMap<>();

	private EnchantItemOptionsData() {
		_data.clear();
		parseDatapackFile("data/enchantItemOptions.xml");
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		int counter = 0;
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("item".equalsIgnoreCase(d.getNodeName())) {
						int itemId = parseInteger(d.getAttributes(), "id");
						if (!_data.containsKey(itemId)) {
							_data.put(itemId, new HashMap<Integer, EnchantOptions>());
						}
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling()) {
							if ("options".equalsIgnoreCase(cd.getNodeName())) {
								final EnchantOptions op = new EnchantOptions(parseInteger(cd.getAttributes(), "level"));
								_data.get(itemId).put(op.getLevel(), op);

								for (byte i = 0; i < 3; i++) {
									final Node att = cd.getAttributes().getNamedItem("option" + (i + 1));
									if ((att != null) && Util.isDigit(att.getNodeValue())) {
										op.setOption(i, parseInteger(att));
									}
								}
								counter++;
							}
						}
					}
				}
			}
		}
		log.info("Loaded: {} Items and {} Options.", _data.size(), counter);
	}

	public int getLoadedElementsCount() {
		return _data.size();
	}

	/**
	 * @param itemId
	 * @param enchantLevel
	 * @return enchant effects information.
	 */
	public EnchantOptions getOptions(int itemId, int enchantLevel) {
		if (!_data.containsKey(itemId) || !_data.get(itemId).containsKey(enchantLevel)) {
			return null;
		}
		return _data.get(itemId).get(enchantLevel);
	}

	/**
	 * @param item
	 * @return enchant effects information.
	 */
	public EnchantOptions getOptions(ItemInstance item) {
		return item != null ? getOptions(item.getId(), item.getEnchantLevel()) : null;
	}
}
