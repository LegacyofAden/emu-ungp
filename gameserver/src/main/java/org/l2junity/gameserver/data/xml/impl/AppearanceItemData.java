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
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.holders.AppearanceHolder;
import org.l2junity.gameserver.model.items.appearance.AppearanceStone;
import org.l2junity.gameserver.model.items.appearance.AppearanceTargetType;
import org.l2junity.gameserver.model.items.type.CrystalType;
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
public class AppearanceItemData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final AppearanceItemData instance = new AppearanceItemData();

	private final Map<Integer, AppearanceStone> _stones = new HashMap<>();

	private AppearanceItemData() {
		reload();
	}

	public void reload() {
		parseDatapackFile("data/AppearanceStones.xml");
		log.info("Loaded: {} Stones", _stones.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("appearance_stone".equalsIgnoreCase(d.getNodeName())) {
						final AppearanceStone stone = new AppearanceStone(new StatsSet(parseAttributes(d)));
						for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
							switch (c.getNodeName()) {
								case "grade": {
									final CrystalType type = CrystalType.valueOf(c.getTextContent());
									stone.addCrystalType(type);
									break;
								}
								case "targetType": {
									final AppearanceTargetType type = AppearanceTargetType.valueOf(c.getTextContent());
									stone.addTargetType(type);
									break;
								}
								case "bodyPart": {
									final int part = ItemTable._slots.get(c.getTextContent());
									stone.addBodyPart(part);
									break;
								}
								case "race": {
									final Race race = Race.valueOf(c.getTextContent());
									stone.addRace(race);
									break;
								}
								case "raceNot": {
									final Race raceNot = Race.valueOf(c.getTextContent());
									stone.addRaceNot(raceNot);
									break;
								}
								case "visual": {
									stone.addVisualId(new AppearanceHolder(new StatsSet(parseAttributes(c))));
								}
							}
						}
						_stones.put(stone.getId(), stone);
					}
				}
			}
		}
	}

	public int getLoadedElementsCount() {
		return _stones.size();
	}

	public AppearanceStone getStone(int stone) {
		return _stones.get(stone);
	}
}
