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
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.multibox.MultiboxSettings;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class MultiboxData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final MultiboxData instance = new MultiboxData();

	private final Map<String, MultiboxSettings> _data = new LinkedHashMap<>();

	private MultiboxData() {
		try {
			parseDatapackFile("config/MultiboxProtection.xml");
			LOGGER.info("Loaded {} settings.", _data.size());
		} catch (Exception e) {
			log.error("Error while loading MultiboxData", e);
		}
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "protection", protectionNode ->
		{
			final StatsSet set = new StatsSet(parseAttributes(protectionNode));
			final MultiboxSettings settings = _data.computeIfAbsent(set.getString("name"), MultiboxSettings::new);

			// Update the settings in case of reload
			settings.set(set);

			// Add some exceptions
			forEach(protectionNode, "whitelist", whitelistNode ->
			{
				final int source = settings.getProtectionType().generateHash(parseString(whitelistNode.getAttributes(), "source"));
				final int maxClients = parseInteger(whitelistNode.getAttributes(), "maxClients");
				settings.addToWhitelist(source, maxClients);
			});
		}));
	}

	public MultiboxSettings getSettings(String name) {
		return _data.get(name);
	}
}
