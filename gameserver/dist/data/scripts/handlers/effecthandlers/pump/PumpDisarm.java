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
package handlers.effecthandlers.pump;

import handlers.effecthandlers.AbstractBooleanStatEffect;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.BooleanStat;

/**
 * Disarm effect implementation.
 *
 * @author nBd
 */
public final class PumpDisarm extends AbstractBooleanStatEffect {
	public PumpDisarm(StatsSet params) {
		super(BooleanStat.DISARMED);
	}

	@Override
	public boolean checkPumpCondition(Creature caster, Creature target, Skill skill) {
		return target.isPlayer();
	}

	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill) {
		final PlayerInstance player = target.getActingPlayer();
		if (player != null) {
			player.unequipWeapon();
		}
	}
}
