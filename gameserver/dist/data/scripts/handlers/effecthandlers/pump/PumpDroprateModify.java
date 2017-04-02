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
package handlers.effecthandlers.pump;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Nik
 */
public class PumpDroprateModify extends AbstractEffect {
	private final int _dropRate;
	private final int _spoilRate;
	private final int _adenaRate;

	public PumpDroprateModify(StatsSet params) {
		_dropRate = params.getInt("drop", 0);
		_spoilRate = params.getInt("spoil", 0);
		_adenaRate = params.getInt("adena", 0);
	}

	@Override
	public void pump(Creature target, Skill skill) {
		if (_dropRate > 0) {
			target.getStat().mergeAdd(DoubleStat.BONUS_DROP, _dropRate);
		}
		if (_spoilRate > 0) {
			target.getStat().mergeAdd(DoubleStat.BONUS_SPOIL, _spoilRate);
		}
		if (_adenaRate > 0) {
			target.getStat().mergeAdd(DoubleStat.BONUS_ADENA, _adenaRate);
		}
	}
}
