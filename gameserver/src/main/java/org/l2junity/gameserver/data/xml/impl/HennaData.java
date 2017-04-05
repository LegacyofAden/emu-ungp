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
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.items.Henna;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class holds the henna related information.<br>
 * Cost and required amount to add the henna to the player.<br>
 * Cost and retrieved amount for removing the henna from the player.<br>
 * Allowed classes to wear each henna.
 *
 * @author Zoey76
 */
@Slf4j
@StartupComponent("Data")
public final class HennaData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final HennaData instance = new HennaData();

	private final Map<Integer, Henna> _hennaList = new HashMap<>();

	private HennaData() {
		_hennaList.clear();
		parseDatapackFile("data/stats/hennaList.xml");
		log.info("Loaded {} Henna data.", _hennaList.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equals(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("henna".equals(d.getNodeName())) {
						parseHenna(d);
					}
				}
			}
		}
	}

	/**
	 * Parses the henna.
	 *
	 * @param d the d
	 */
	private void parseHenna(Node d) {
		final StatsSet set = new StatsSet();
		final List<ClassId> wearClassIds = new ArrayList<>();
		NamedNodeMap attrs = d.getAttributes();
		Node attr;
		for (int i = 0; i < attrs.getLength(); i++) {
			attr = attrs.item(i);
			set.set(attr.getNodeName(), attr.getNodeValue());
		}

		for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
			final String name = c.getNodeName();
			attrs = c.getAttributes();
			switch (name) {
				case "stats": {
					for (int i = 0; i < attrs.getLength(); i++) {
						attr = attrs.item(i);
						set.set(attr.getNodeName(), attr.getNodeValue());
					}
					break;
				}
				case "wear": {
					attr = attrs.getNamedItem("count");
					set.set("wear_count", attr.getNodeValue());
					attr = attrs.getNamedItem("fee");
					set.set("wear_fee", attr.getNodeValue());
					break;
				}
				case "cancel": {
					attr = attrs.getNamedItem("count");
					set.set("cancel_count", attr.getNodeValue());
					attr = attrs.getNamedItem("fee");
					set.set("cancel_fee", attr.getNodeValue());
					break;
				}
				case "classId": {
					wearClassIds.add(ClassId.getClassId(Integer.parseInt(c.getTextContent())));
					break;
				}
			}
		}
		final Henna henna = new Henna(set);
		henna.setWearClassIds(wearClassIds);
		_hennaList.put(henna.getDyeId(), henna);
	}

	public int getLoadedElementsCount() {
		return _hennaList.size();
	}

	/**
	 * Gets the henna.
	 *
	 * @param id of the dye.
	 * @return the dye with that id.
	 */
	public Henna getHenna(int id) {
		return _hennaList.get(id);
	}

	/**
	 * Gets the henna list.
	 *
	 * @param classId the player's class Id.
	 * @return the list with all the allowed dyes.
	 */
	public List<Henna> getHennaList(ClassId classId) {
		final List<Henna> list = new ArrayList<>();
		for (Henna henna : _hennaList.values()) {
			if (henna.isAllowedClass(classId)) {
				list.add(henna);
			}
		}
		return list;
	}
}