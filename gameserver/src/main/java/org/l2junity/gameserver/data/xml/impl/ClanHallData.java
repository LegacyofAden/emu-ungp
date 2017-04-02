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
import org.l2junity.gameserver.enums.ClanHallGrade;
import org.l2junity.gameserver.enums.ClanHallType;
import org.l2junity.gameserver.instancemanager.ZoneManager;
import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.instance.DoorInstance;
import org.l2junity.gameserver.model.entity.ClanHall;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author St3eT
 */

@Slf4j
@StartupComponent(value = "Data", dependency = {DoorData.class, ZoneManager.class, SkillTreesData.class})
public final class ClanHallData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ClanHallData instance = new ClanHallData();

	private static final Map<Integer, ClanHall> _clanHalls = new HashMap<>();

	protected ClanHallData() {
		parseDatapackDirectory("data/residences/clanHalls", true);
		log.info("Succesfully loaded {} Clan Halls.", _clanHalls.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		final List<DoorInstance> doors = new ArrayList<>();
		final List<Integer> npcs = new ArrayList<>();
		final StatsSet params = new StatsSet();

		for (Node listNode = doc.getFirstChild(); listNode != null; listNode = listNode.getNextSibling()) {
			if ("list".equals(listNode.getNodeName())) {
				for (Node clanHallNode = listNode.getFirstChild(); clanHallNode != null; clanHallNode = clanHallNode.getNextSibling()) {
					if ("clanHall".equals(clanHallNode.getNodeName())) {
						params.set("id", parseInteger(clanHallNode.getAttributes(), "id"));
						params.set("name", parseString(clanHallNode.getAttributes(), "name", "None"));
						params.set("grade", parseEnum(clanHallNode.getAttributes(), ClanHallGrade.class, "grade", ClanHallGrade.GRADE_NONE));
						params.set("type", parseEnum(clanHallNode.getAttributes(), ClanHallType.class, "type", ClanHallType.OTHER));

						for (Node tpNode = clanHallNode.getFirstChild(); tpNode != null; tpNode = tpNode.getNextSibling()) {
							switch (tpNode.getNodeName()) {
								case "auction": {
									final NamedNodeMap at = tpNode.getAttributes();
									params.set("minBid", parseInteger(at, "minBid"));
									params.set("lease", parseInteger(at, "lease"));
									params.set("deposit", parseInteger(at, "deposit"));
									break;
								}
								case "npcs": {
									for (Node npcNode = tpNode.getFirstChild(); npcNode != null; npcNode = npcNode.getNextSibling()) {
										if ("npc".equals(npcNode.getNodeName())) {
											final NamedNodeMap np = npcNode.getAttributes();
											final int npcId = parseInteger(np, "id");
											npcs.add(npcId);
										}
									}
									params.set("npcList", npcs);
									break;
								}
								case "doorlist": {
									for (Node npcNode = tpNode.getFirstChild(); npcNode != null; npcNode = npcNode.getNextSibling()) {
										if ("door".equals(npcNode.getNodeName())) {
											final NamedNodeMap np = npcNode.getAttributes();
											final int doorId = parseInteger(np, "id");
											final DoorInstance door = DoorData.getInstance().getDoor(doorId);
											if (door != null) {
												doors.add(door);
											}
										}
									}
									params.set("doorList", doors);
									break;
								}
								case "ownerRestartPoint": {
									final NamedNodeMap ol = tpNode.getAttributes();
									params.set("owner_loc", new Location(parseInteger(ol, "x"), parseInteger(ol, "y"), parseInteger(ol, "z")));
									break;
								}
								case "banishPoint": {
									final NamedNodeMap bl = tpNode.getAttributes();
									params.set("banish_loc", new Location(parseInteger(bl, "x"), parseInteger(bl, "y"), parseInteger(bl, "z")));
									break;
								}
							}
						}
					}
				}
			}
		}
		_clanHalls.put(params.getInt("id"), new ClanHall(params));
	}

	public int getLoadedElementsCount() {
		return _clanHalls.size();
	}

	public ClanHall getClanHallById(int clanHallId) {
		return _clanHalls.get(clanHallId);
	}

	public Collection<ClanHall> getClanHalls() {
		return _clanHalls.values();
	}

	public ClanHall getClanHallByNpcId(int npcId) {
		return _clanHalls.values().stream().filter(ch -> ch.getNpcs().contains(npcId)).findFirst().orElse(null);
	}

	public ClanHall getClanHallByClan(L2Clan clan) {
		return _clanHalls.values().stream().filter(ch -> ch.getOwner() == clan).findFirst().orElse(null);
	}

	public ClanHall getClanHallByDoorId(int doorId) {
		final DoorInstance door = DoorData.getInstance().getDoor(doorId);
		return _clanHalls.values().stream().filter(ch -> ch.getDoors().contains(door)).findFirst().orElse(null);
	}

	public List<ClanHall> getFreeAuctionableHall() {
		return _clanHalls.values().stream().filter(ch -> (ch.getType() == ClanHallType.AUCTIONABLE) && (ch.getOwner() == null)).sorted(Comparator.comparingInt(ClanHall::getResidenceId)).collect(Collectors.toList());
	}
}