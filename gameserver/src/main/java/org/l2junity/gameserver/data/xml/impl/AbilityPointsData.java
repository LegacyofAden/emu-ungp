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
import org.l2junity.gameserver.model.holders.RangeAbilityPointsHolder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public final class AbilityPointsData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final AbilityPointsData instance = new AbilityPointsData();

	private final List<RangeAbilityPointsHolder> _points = new ArrayList<>();

	private AbilityPointsData() {
		reload();
	}

	protected void reload() {
		_points.clear();
		parseDatapackFile("config/xml/AbilityPoints.xml");
		LOGGER.info("Loaded: {} range fees.", _points.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("points".equalsIgnoreCase(d.getNodeName())) {
						final NamedNodeMap attrs = d.getAttributes();
						final int from = parseInteger(attrs, "from");
						final int to = parseInteger(attrs, "to");
						final int costs = parseInteger(attrs, "costs");
						_points.add(new RangeAbilityPointsHolder(from, to, costs));
					}
				}
			}
		}
	}

	public int getPointsCount() {
		return _points.size();
	}

	public RangeAbilityPointsHolder getHolder(int points) {
		for (RangeAbilityPointsHolder holder : _points) {
			if ((holder.getMin() <= points) && (holder.getMax() >= points)) {
				return holder;
			}
		}
		return null;
	}

	public long getPrice(int points) {
		points++; // for next point
		final RangeAbilityPointsHolder holder = getHolder(points);
		if (holder == null) {
			final RangeAbilityPointsHolder prevHolder = getHolder(points - 1);
			if (prevHolder != null) {
				return prevHolder.getSP();
			}

			// No data found
			return points >= 13 ? 1_000_000_000 : points >= 9 ? 750_000_000 : points >= 5 ? 500_000_000 : 250_000_000;
		}
		return holder.getSP();
	}
}
