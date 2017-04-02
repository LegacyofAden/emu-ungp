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
import org.l2junity.gameserver.enums.CategoryType;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Loads the category data with Class or NPC IDs.
 *
 * @author NosBit, xban1x
 */
@Slf4j
@StartupComponent("Data")
public final class CategoryData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final CategoryData instance = new CategoryData();

	private final Map<CategoryType, Set<Integer>> _categories = new EnumMap<>(CategoryType.class);

	private CategoryData() {
		_categories.clear();
		parseDatapackFile("data/categoryData.xml");
		log.info("Loaded {} Categories.", _categories.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node node = doc.getFirstChild(); node != null; node = node.getNextSibling()) {
			if ("list".equalsIgnoreCase(node.getNodeName())) {
				for (Node list_node = node.getFirstChild(); list_node != null; list_node = list_node.getNextSibling()) {
					if ("category".equalsIgnoreCase(list_node.getNodeName())) {
						final NamedNodeMap attrs = list_node.getAttributes();
						final CategoryType categoryType = CategoryType.findByName(attrs.getNamedItem("name").getNodeValue());
						if (categoryType == null) {
							log.warn("Can't find category by name: {}", attrs.getNamedItem("name").getNodeValue());
							continue;
						}

						final Set<Integer> ids = new HashSet<>();
						for (Node category_node = list_node.getFirstChild(); category_node != null; category_node = category_node.getNextSibling()) {
							if ("id".equalsIgnoreCase(category_node.getNodeName())) {
								ids.add(Integer.parseInt(category_node.getTextContent()));
							}
						}
						_categories.put(categoryType, ids);
					}
				}
			}
		}
	}

	public int getLoadedElementsCount() {
		return _categories.size();
	}

	/**
	 * Checks if ID is in category.
	 *
	 * @param type The category type
	 * @param id   The id to be checked
	 * @return {@code true} if id is in category, {@code false} if id is not in category or category was not found
	 */
	public boolean isInCategory(CategoryType type, int id) {
		final Set<Integer> category = getCategoryByType(type);
		if (category == null) {
			log.warn("Can't find category type: {}", type);
			return false;
		}
		return category.contains(id);
	}

	/**
	 * Gets the category by category type.
	 *
	 * @param type The category type
	 * @return A {@code Set} containing all the IDs in category if category is found, {@code null} if category was not found
	 */
	public Set<Integer> getCategoryByType(CategoryType type) {
		return _categories.get(type);
	}
}
