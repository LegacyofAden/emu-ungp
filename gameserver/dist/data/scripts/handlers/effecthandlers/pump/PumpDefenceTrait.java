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
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.TraitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Defence Trait effect implementation.
 *
 * @author NosBit
 */
public final class PumpDefenceTrait extends AbstractEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger(PumpDefenceTrait.class);

	private final Map<TraitType, Float> _defenceTraits = new HashMap<>();

	public PumpDefenceTrait(StatsSet params) {
		if (params.isEmpty()) {
			LOGGER.warn(getClass().getSimpleName() + ": must have parameters.");
			return;
		}

		for (Entry<String, Object> param : params.getSet().entrySet()) {
			try {
				final TraitType traitType = TraitType.valueOf(param.getKey());
				final float value = Float.parseFloat((String) param.getValue());
				if (value == 0) {
					continue;
				}
				_defenceTraits.put(traitType, (value + 100) / 100);
			} catch (NumberFormatException e) {
				LOGGER.warn(getClass().getSimpleName() + ": value of " + param.getKey() + " must be float value " + param.getValue() + " found.");
			} catch (Exception e) {
				LOGGER.warn(getClass().getSimpleName() + ": value of L2TraitType enum required but found: " + param.getKey());
			}
		}
	}

	@Override
	public void pump(Creature target, Skill skill) {
		for (Entry<TraitType, Float> trait : _defenceTraits.entrySet()) {
			if (trait.getValue() < 2.0f) {
				target.getStat().mergeDefenceTrait(trait.getKey(), trait.getValue());
			} else {
				target.getStat().mergeInvulnerableTrait(trait.getKey());
			}
		}
	}
}
