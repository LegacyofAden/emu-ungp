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
package org.l2junity.gameserver.model.actor.transform;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.stats.DoubleStat;

import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
public final class TransformLevelData {
	private final int _level;
	private final double _levelMod;
	private Map<Integer, Double> _stats;

	public TransformLevelData(StatsSet set) {
		_level = set.getInt("val");
		_levelMod = set.getDouble("levelMod");
		addStats(DoubleStat.MAX_HP, set.getDouble("hp"));
		addStats(DoubleStat.MAX_MP, set.getDouble("mp"));
		addStats(DoubleStat.MAX_CP, set.getDouble("cp"));
		addStats(DoubleStat.REGENERATE_HP_RATE, set.getDouble("hpRegen"));
		addStats(DoubleStat.REGENERATE_MP_RATE, set.getDouble("mpRegen"));
		addStats(DoubleStat.REGENERATE_CP_RATE, set.getDouble("cpRegen"));
	}

	private void addStats(DoubleStat stat, double val) {
		if (_stats == null) {
			_stats = new HashMap<>();
		}
		_stats.put(stat.ordinal(), val);
	}

	public double getStats(DoubleStat stats, double defaultValue) {
		return _stats == null ? defaultValue : _stats.getOrDefault(stats.ordinal(), defaultValue);
	}

	public int getLevel() {
		return _level;
	}

	public double getLevelMod() {
		return _levelMod;
	}
}
