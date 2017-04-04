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
package org.l2junity.gameserver.model.skills.conditiontypes;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.ControllableAirShipInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * @author Sdw
 */
public class CanRefuelAirshipSkillCondition implements ISkillCondition {
	private final int _amount;

	public CanRefuelAirshipSkillCondition(StatsSet params) {
		_amount = params.getInt("amount");
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		boolean canRefuelAirship = true;
		final Player player = caster.getActingPlayer();
		if ((player == null) || (player.getAirShip() == null) || !(player.getAirShip() instanceof ControllableAirShipInstance) || ((player.getAirShip().getFuel() + _amount) > player.getAirShip().getMaxFuel())) {
			canRefuelAirship = false;
		}
		return canRefuelAirship;
	}
}
