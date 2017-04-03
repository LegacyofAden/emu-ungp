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
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.nio.file.Path;

/**
 * This class holds the Experience points for each level for players and pets.
 *
 * @author mrTJO
 */
@Slf4j
@StartupComponent("Data")
public final class ExperienceData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ExperienceData instance = new ExperienceData();

	private byte MAX_LEVEL;
	private byte MAX_PET_LEVEL;
	private long[] EXPERIENCE_TO_LEVEL;
	private double[] TRAINING_RATE;

	protected ExperienceData() {
		parseDatapackFile("data/stats/experience.xml");

		LOGGER.info("Loaded {} levels.", EXPERIENCE_TO_LEVEL.length - 1);
		LOGGER.info("Max Player Level is: {}", (MAX_LEVEL - 1));
		LOGGER.info("Max Pet Level is: {}", (MAX_PET_LEVEL - 1));
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		final Node table = doc.getFirstChild();

		MAX_LEVEL = (byte) (parseByte(table.getAttributes(), "maxLevel") + 1);
		MAX_PET_LEVEL = (byte) (parseByte(table.getAttributes(), "maxPetLevel") + 1);
		EXPERIENCE_TO_LEVEL = new long[MAX_LEVEL + 1];
		TRAINING_RATE = new double[MAX_LEVEL + 1];

		forEach(table, "experience", expNode ->
		{
			final byte level = parseByte(expNode.getAttributes(), "level");
			EXPERIENCE_TO_LEVEL[level] = parseLong(expNode.getAttributes(), "tolevel");
			TRAINING_RATE[level] = parseDouble(expNode.getAttributes(), "trainingRate");
		});
	}

	public int getLoadedElementsCount() {
		return EXPERIENCE_TO_LEVEL.length;
	}

	/**
	 * Gets the exp for level.
	 *
	 * @param level the level required.
	 * @return the experience points required to reach the given level.
	 */
	public long getExpForLevel(int level) {
		return EXPERIENCE_TO_LEVEL[level];
	}

	public double getTrainingRate(int level) {
		return TRAINING_RATE[level];
	}

	/**
	 * Gets the max level.
	 *
	 * @return the maximum level acquirable by a player.
	 */
	public byte getMaxLevel() {
		return MAX_LEVEL;
	}

	/**
	 * Gets the max pet level.
	 *
	 * @return the maximum level acquirable by a pet.
	 */
	public byte getMaxPetLevel() {
		return MAX_PET_LEVEL;
	}
}
