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
import org.l2junity.gameserver.model.SayuneEntry;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class SayuneData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final SayuneData instance = new SayuneData();

	private final Map<Integer, SayuneEntry> _maps = new HashMap<>();

	private SayuneData() {
		reload();
	}

	private void reload() {
		parseDatapackFile("data/SayuneData.xml");
		log.info("Loaded: {} maps.", _maps.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("map".equalsIgnoreCase(d.getNodeName())) {
						final int id = parseInteger(d.getAttributes(), "id");
						final SayuneEntry map = new SayuneEntry(id);
						parseEntries(map, d);
						_maps.put(map.getId(), map);
					}
				}
			}
		}
	}

	private void parseEntries(SayuneEntry lastEntry, Node n) {
		NamedNodeMap attrs;
		for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
			if ("selector".equals(d.getNodeName()) || "choice".equals(d.getNodeName()) || "loc".equals(d.getNodeName())) {
				attrs = d.getAttributes();
				final int id = parseInteger(attrs, "id");
				final int x = parseInteger(attrs, "x");
				final int y = parseInteger(attrs, "y");
				final int z = parseInteger(attrs, "z");

				parseEntries(lastEntry.addInnerEntry(new SayuneEntry("selector".equals(d.getNodeName()), id, x, y, z)), d);
			}
		}
	}

	public SayuneEntry getMap(int id) {
		return _maps.get(id);
	}

	public Collection<SayuneEntry> getMaps() {
		return _maps.values();
	}
}
