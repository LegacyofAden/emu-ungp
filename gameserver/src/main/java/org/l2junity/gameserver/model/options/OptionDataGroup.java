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
package org.l2junity.gameserver.model.options;

import org.l2junity.commons.util.Rnd;

import java.util.List;

/**
 * @author Pere
 */
public final class OptionDataGroup {
	private final List<OptionDataCategory> _categories;

	public OptionDataGroup(List<OptionDataCategory> categories) {
		_categories = categories;
	}

	Options getRandomEffect() {
		Options result = null;
		do {
			double random = Rnd.get() * 100.0;
			for (OptionDataCategory category : _categories) {
				if (category.getChance() >= random) {
					result = category.getRandomOptions();
					break;
				}

				random -= category.getChance();
			}
		}
		while (result == null);
		// Should never get there
		return result;
	}
}