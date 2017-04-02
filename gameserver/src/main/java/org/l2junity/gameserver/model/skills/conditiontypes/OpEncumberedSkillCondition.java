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
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.skills.ISkillCondition;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * TODO: Verify me, also should Quest items be counted?
 *
 * @author UnAfraid
 */
public class OpEncumberedSkillCondition implements ISkillCondition {
	private final int _slotsPercent;
	private final int _weightPercent;

	public OpEncumberedSkillCondition(StatsSet params) {
		_slotsPercent = params.getInt("slotsPercent");
		_weightPercent = params.getInt("weightPercent");
	}

	@Override
	public boolean canUse(Creature caster, Skill skill, WorldObject target) {
		if (!caster.isPlayer()) {
			return false;
		}

		final PlayerInstance player = caster.getActingPlayer();

		// GMs in diet mode should skip this check.
		if (player.getDietMode()) {
			return true;
		}

		final int currentSlotsPercent = calcPercent(player.getInventoryLimit(), player.getInventory().getSize(item -> !item.isQuestItem()));
		final int currentWeightPercent = calcPercent(player.getMaxLoad(), player.getCurrentLoad());
		return (currentSlotsPercent >= _slotsPercent) && (currentWeightPercent >= _weightPercent);
	}

	private int calcPercent(int max, int current) {
		return 100 - ((current * 100) / max);
	}
}
