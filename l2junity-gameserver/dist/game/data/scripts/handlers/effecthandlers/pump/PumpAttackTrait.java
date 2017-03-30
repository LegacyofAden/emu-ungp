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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.TraitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Attack Trait effect implementation.
 * @author NosBit
 */
public final class PumpAttackTrait extends AbstractEffect
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PumpAttackTrait.class);

	private final Map<TraitType, Float> _attackTraits = new HashMap<>();
	
	public PumpAttackTrait(StatsSet params)
	{
		if (params.isEmpty())
		{
			LOGGER.warn(getClass().getSimpleName() + ": this effect must have parameters!");
			return;
		}
		
		for (Entry<String, Object> param : params.getSet().entrySet())
		{
			_attackTraits.put(TraitType.valueOf(param.getKey()), (Float.parseFloat((String) param.getValue()) + 100) / 100);
		}
	}
	
	@Override
	public void pump(Creature target, Skill skill)
	{
		for (Entry<TraitType, Float> trait : _attackTraits.entrySet())
		{
			target.getStat().mergeAttackTrait(trait.getKey(), trait.getValue());
		}
	}
}
