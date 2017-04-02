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

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.BooleanStat;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Block Actions effect implementation.
 *
 * @author mkizub
 */
public final class PumpConditionBlockActSkill extends AbstractEffect {
	private final Set<Integer> _allowedSkills;

	public PumpConditionBlockActSkill(StatsSet params) {
		final String[] allowedSkills = params.getString("allowedSkills", "").split(";");
		_allowedSkills = Arrays.stream(allowedSkills).filter(s -> !s.isEmpty()).map(Integer::parseInt).collect(Collectors.toSet());
	}

	@Override
	public void pump(Creature target, Skill skill) {
		target.getStat().set(BooleanStat.BLOCK_ACTIONS);
		_allowedSkills.stream().forEach(target.getStat()::addBlockActionsAllowedSkill);
	}

	@Override
	public L2EffectType getEffectType() {
		return L2EffectType.BLOCK_ACTIONS;
	}
}
