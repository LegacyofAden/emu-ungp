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
import org.l2junity.gameserver.enums.Faction;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.holders.FactionHolder;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sdw
 */
@Slf4j
@StartupComponent("Data")
public class FactionData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final FactionData instance = new FactionData();

	private final Map<Faction, FactionHolder> _factions = new HashMap<>();

	private FactionData() {
		_factions.clear();
		parseDatapackFile("data/FactionData.xml");
		LOGGER.info("Loaded {} factions.", _factions.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "faction", factionNode ->
		{
			final FactionHolder holder = new FactionHolder(new StatsSet(parseAttributes(factionNode)));

			forEach(factionNode, "point", pointNode ->
			{
				holder.addPointsForLevel(parseInteger(pointNode.getAttributes(), "level"), parseInteger(pointNode.getAttributes(), "val"));
			});

			_factions.put(holder.getName(), holder);
		}));
	}

	public int getLoadedElementsCount() {
		return _factions.size();
	}

	public Collection<FactionHolder> getFactions() {
		return _factions.values();
	}

	public FactionHolder getFaction(Faction faction) {
		return _factions.get(faction);
	}
}
