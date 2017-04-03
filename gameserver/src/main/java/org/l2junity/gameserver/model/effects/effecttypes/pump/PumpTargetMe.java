/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.effects.effecttypes.pump;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * Target Me effect implementation.
 *
 * @author -Nemesiss-
 */
public final class PumpTargetMe extends AbstractEffect {
	public PumpTargetMe(StatsSet params) {
	}

	@Override
	public void pumpEnd(Creature caster, Creature target, Skill skill) {
		if (target.isPlayable()) {
			((Playable) target).setLockedTarget(null);
		}
	}

	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill) {
		if (target.isPlayable()) {
			if (target.getTarget() != caster) {
				target.setTarget(caster);
			}

			((Playable) target).setLockedTarget(caster);
		}
	}
}
