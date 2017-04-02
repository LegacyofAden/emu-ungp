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
import org.l2junity.gameserver.model.ActionKey;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UI Data parser.
 *
 * @author Zoey76
 */
@Slf4j
@StartupComponent("Data")
public class UIData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final UIData instance = new UIData();

	private final Map<Integer, List<ActionKey>> _storedKeys = new HashMap<>();
	private final Map<Integer, List<Integer>> _storedCategories = new HashMap<>();

	private UIData() {
		_storedKeys.clear();
		_storedCategories.clear();
		parseDatapackFile("data/ui/ui_en.xml");
		log.info("Loaded {} keys {} categories.", _storedKeys.size(), _storedCategories.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("category".equalsIgnoreCase(d.getNodeName())) {
						parseCategory(d);
					}
				}
			}
		}
	}

	private void parseCategory(Node n) {
		final int cat = parseInteger(n.getAttributes(), "id");
		for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
			if ("commands".equalsIgnoreCase(d.getNodeName())) {
				parseCommands(cat, d);
			} else if ("keys".equalsIgnoreCase(d.getNodeName())) {
				parseKeys(cat, d);
			}
		}
	}

	private void parseCommands(int cat, Node d) {
		for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
			if ("cmd".equalsIgnoreCase(c.getNodeName())) {
				addCategory(_storedCategories, cat, Integer.parseInt(c.getTextContent()));
			}
		}
	}

	private void parseKeys(int cat, Node d) {
		for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
			if ("key".equalsIgnoreCase(c.getNodeName())) {
				final ActionKey akey = new ActionKey(cat);
				for (int i = 0; i < c.getAttributes().getLength(); i++) {
					final Node att = c.getAttributes().item(i);
					final int val = Integer.parseInt(att.getNodeValue());
					switch (att.getNodeName()) {
						case "cmd": {
							akey.setCommandId(val);
							break;
						}
						case "key": {
							akey.setKeyId(val);
							break;
						}
						case "toggleKey1": {
							akey.setToogleKey1(val);
							break;
						}
						case "toggleKey2": {
							akey.setToogleKey2(val);
							break;
						}
						case "showType": {
							akey.setShowStatus(val);
							break;
						}
					}
				}
				addKey(_storedKeys, cat, akey);
			}
		}
	}

	/**
	 * Add a category to the stored categories.
	 *
	 * @param map the map to store the category
	 * @param cat the category
	 * @param cmd the command
	 */
	public static void addCategory(Map<Integer, List<Integer>> map, int cat, int cmd) {
		map.computeIfAbsent(cat, k -> new ArrayList<>()).add(cmd);
	}

	/**
	 * Create and insert an Action Key into the stored keys.
	 *
	 * @param map  the map to store the key
	 * @param cat  the category
	 * @param akey the action key
	 */
	public static void addKey(Map<Integer, List<ActionKey>> map, int cat, ActionKey akey) {
		map.computeIfAbsent(cat, k -> new ArrayList<>()).add(akey);
	}

	/**
	 * @return the categories
	 */
	public Map<Integer, List<Integer>> getCategories() {
		return _storedCategories;
	}

	/**
	 * @return the keys
	 */
	public Map<Integer, List<ActionKey>> getKeys() {
		return _storedKeys;
	}
}
