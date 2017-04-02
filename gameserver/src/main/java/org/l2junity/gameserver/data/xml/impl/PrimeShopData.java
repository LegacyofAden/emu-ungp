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
import org.l2junity.gameserver.datatables.ItemTable;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.items.L2Item;
import org.l2junity.gameserver.model.primeshop.PrimeShopGroup;
import org.l2junity.gameserver.model.primeshop.PrimeShopItem;
import org.l2junity.gameserver.network.client.send.primeshop.ExBRProductInfo;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gnacik, UnAfraid
 */
@Slf4j
@StartupComponent(value = "Data", dependency = ItemTable.class)
public class PrimeShopData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final PrimeShopData instance = new PrimeShopData();

	private final Map<Integer, PrimeShopGroup> _primeItems = new LinkedHashMap<>();

	private PrimeShopData() {
		_primeItems.clear();
		parseDatapackFile("data/PrimeShop.xml");

		if (!_primeItems.isEmpty()) {
			log.info("Loaded {} items", _primeItems.size());
		} else {
			log.info("System is disabled.");
		}
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				NamedNodeMap at = n.getAttributes();
				Node attribute = at.getNamedItem("enabled");
				if ((attribute != null) && Boolean.parseBoolean(attribute.getNodeValue())) {
					for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
						if ("item".equalsIgnoreCase(d.getNodeName())) {
							NamedNodeMap attrs = d.getAttributes();
							Node att;
							StatsSet set = new StatsSet();
							for (int i = 0; i < attrs.getLength(); i++) {
								att = attrs.item(i);
								set.set(att.getNodeName(), att.getNodeValue());
							}

							List<PrimeShopItem> items = new ArrayList<>();
							for (Node b = d.getFirstChild(); b != null; b = b.getNextSibling()) {
								if ("item".equalsIgnoreCase(b.getNodeName())) {
									attrs = b.getAttributes();

									final int itemId = parseInteger(attrs, "itemId");
									final int count = parseInteger(attrs, "count");

									final L2Item item = ItemTable.getInstance().getTemplate(itemId);
									if (item == null) {
										log.error("Item template null for itemId: {} brId: {}", itemId, set.getInt("id"));
										return;
									}

									items.add(new PrimeShopItem(itemId, count, item.getWeight(), item.isTradeable() ? 1 : 0));
								}
							}

							_primeItems.put(set.getInt("id"), new PrimeShopGroup(set, items));
						}
					}
				}
			}
		}
	}

	public int getItemCount() {
		return _primeItems.size();
	}

	public void showProductInfo(PlayerInstance player, int brId) {
		final PrimeShopGroup item = _primeItems.get(brId);

		if ((player == null) || (item == null)) {
			return;
		}

		player.sendPacket(new ExBRProductInfo(item, player));
	}

	public PrimeShopGroup getItem(int brId) {
		return _primeItems.get(brId);
	}

	public Map<Integer, PrimeShopGroup> getPrimeItems() {
		return _primeItems;
	}
}