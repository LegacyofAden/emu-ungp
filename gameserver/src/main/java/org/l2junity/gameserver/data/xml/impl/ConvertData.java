/*
 * Copyright (C) 2004-2017 L2J Unity
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
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sdw
 */
@Slf4j
@StartupComponent("Data")
public class ConvertData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ConvertData instance = new ConvertData();

	private final Map<Integer, Integer> _convertData = new HashMap<>();

	protected ConvertData() {
		_convertData.clear();
		parseDatapackFile("data/ConvertData.xml");
		log.info("Loaded {} weapon conversion data.", _convertData.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "convert", convertNode ->
		{
			_convertData.put(parseInteger(convertNode.getAttributes(), "input_item"), parseInteger(convertNode.getAttributes(), "output_item"));
		}));
	}

	public int getConversionId(int weaponId) {
		return _convertData.getOrDefault(weaponId, 0);
	}

	public int getLoadedElementsCount() {
		return _convertData.size();
	}
}