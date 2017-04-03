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
import org.l2junity.gameserver.enums.MountType;
import org.l2junity.gameserver.model.PetData;
import org.l2junity.gameserver.model.PetLevelData;
import org.l2junity.gameserver.model.StatsSet;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * This class parse and hold all pet parameters.<br>
 * TODO: load and use all pet parameters.
 *
 * @author Zoey76 (rework)
 */
@Slf4j
@StartupComponent("Data")
public final class PetDataTable implements IGameXmlReader {
	@Getter(lazy = true)
	private static final PetDataTable instance = new PetDataTable();

	private final Map<Integer, PetData> _pets = new HashMap<>();

	private PetDataTable() {
		_pets.clear();
		parseDatapackDirectory("data/stats/pets", false);
		log.info("Loaded {} Pets.", _pets.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		NamedNodeMap attrs;
		Node n = doc.getFirstChild();
		for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
			if (d.getNodeName().equals("pet")) {
				int npcId = parseInteger(d.getAttributes(), "id");
				int itemId = parseInteger(d.getAttributes(), "itemId");
				// index ignored for now
				PetData data = new PetData(npcId, itemId);
				for (Node p = d.getFirstChild(); p != null; p = p.getNextSibling()) {
					if (p.getNodeName().equals("set")) {
						attrs = p.getAttributes();
						String type = attrs.getNamedItem("name").getNodeValue();
						if ("syncLevel".equals(type)) {
							data.setSyncLevel(parseInteger(attrs, "val") == 1);
						}
						// evolve ignored
					} else if (p.getNodeName().equals("stats")) {
						for (Node s = p.getFirstChild(); s != null; s = s.getNextSibling()) {
							if (s.getNodeName().equals("stat")) {
								final int level = Integer.parseInt(s.getAttributes().getNamedItem("level").getNodeValue());
								final StatsSet set = new StatsSet();
								for (Node bean = s.getFirstChild(); bean != null; bean = bean.getNextSibling()) {
									if (bean.getNodeName().equals("set")) {
										attrs = bean.getAttributes();
										if (attrs.getNamedItem("name").getNodeValue().equals("speed_on_ride")) {
											set.set("walkSpeedOnRide", attrs.getNamedItem("walk").getNodeValue());
											set.set("runSpeedOnRide", attrs.getNamedItem("run").getNodeValue());
											set.set("slowSwimSpeedOnRide", attrs.getNamedItem("slowSwim").getNodeValue());
											set.set("fastSwimSpeedOnRide", attrs.getNamedItem("fastSwim").getNodeValue());
											if (attrs.getNamedItem("slowFly") != null) {
												set.set("slowFlySpeedOnRide", attrs.getNamedItem("slowFly").getNodeValue());
											}
											if (attrs.getNamedItem("fastFly") != null) {
												set.set("fastFlySpeedOnRide", attrs.getNamedItem("fastFly").getNodeValue());
											}
										} else {
											set.set(attrs.getNamedItem("name").getNodeValue(), attrs.getNamedItem("val").getNodeValue());
										}
									}
								}
								data.addNewStat(level, new PetLevelData(set));
							}
						}
					}
				}
				_pets.put(npcId, data);
			}
		}
	}

	public int getPetCount() {
		return _pets.size();
	}

	/**
	 * @param itemId
	 * @return
	 */
	public PetData getPetDataByItemId(int itemId) {
		for (PetData data : _pets.values()) {
			if (data.getItemId() == itemId) {
				return data;
			}
		}
		return null;
	}

	/**
	 * Gets the pet level data.
	 *
	 * @param petId    the pet Id.
	 * @param petLevel the pet level.
	 * @return the pet's parameters for the given Id and level.
	 */
	public PetLevelData getPetLevelData(int petId, int petLevel) {
		final PetData pd = getPetData(petId);
		if (pd != null) {
			return pd.getPetLevelData(petLevel);
		}
		return null;
	}

	/**
	 * Gets the pet data.
	 *
	 * @param petId the pet Id.
	 * @return the pet data
	 */
	public PetData getPetData(int petId) {
		if (!_pets.containsKey(petId)) {
			log.info("Missing pet data for npcid: {}", petId);
		}
		return _pets.get(petId);
	}

	/**
	 * Gets the pet items by npc.
	 *
	 * @param npcId the NPC ID to get its summoning item
	 * @return summoning item for the given NPC ID
	 */
	public int getPetItemsByNpc(int npcId) {
		return _pets.get(npcId).getItemId();
	}

	/**
	 * Checks if is mountable.
	 *
	 * @param npcId the NPC Id to verify.
	 * @return {@code true} if the given Id is from a mountable pet, {@code false} otherwise.
	 */
	public static boolean isMountable(int npcId) {
		return MountType.findByNpcId(npcId) != MountType.NONE;
	}
}