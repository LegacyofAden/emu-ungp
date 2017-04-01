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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * Transformation effect implementation.
 * @author nBd
 */
public final class PumpTransform extends AbstractEffect
{
	private final List<Integer> _id;
	
	public PumpTransform(StatsSet params)
	{
		final String ids = params.getString("transformationId", null);
		if ((ids != null) && !ids.isEmpty())
		{
			_id = new ArrayList<>();
			for (String id : ids.split(";"))
			{
				_id.add(Integer.parseInt(id));
			}
		}
		else
		{
			_id = Collections.emptyList();
		}
	}
	
	@Override
	public boolean checkPumpCondition(Creature caster, Creature target, Skill skill)
	{
		return !target.isDoor();
	}
	
	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill)
	{
		if (!_id.isEmpty())
		{
			target.transform(_id.get(Rnd.get(_id.size())), true);
		}
	}
	
	@Override
	public void pumpEnd(Creature caster, Creature target, Skill skill)
	{
		target.stopTransformation(false);
	}
}
