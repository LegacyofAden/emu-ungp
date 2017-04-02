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

import handlers.effecthandlers.AbstractDoubleStatEffect;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Sdw
 */
public class PumpMaxMp extends AbstractDoubleStatEffect {
	private final boolean _heal;

	public PumpMaxMp(StatsSet params) {
		super(params, DoubleStat.MAX_MP);

		_heal = params.getBoolean("heal", false);
	}

	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill) {
		if (_heal) {
			switch (_mode) {
				case DIFF: // DIFF
				{
					target.setCurrentMp(target.getCurrentMp() + _amount);
					break;
				}
				case PER: // PER
				{
					target.setCurrentMp(target.getCurrentMp() + (target.getMaxMp() * (_amount / 100)));
					break;
				}
			}
		}
	}
}
