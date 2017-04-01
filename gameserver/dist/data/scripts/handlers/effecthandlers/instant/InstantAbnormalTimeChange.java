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
package handlers.effecthandlers.instant;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.AbnormalType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.AbnormalStatusUpdate;
import org.l2junity.gameserver.network.client.send.ExAbnormalStatusUpdateFromTarget;

/**
 * @author Sdw
 */
public class InstantAbnormalTimeChange extends AbstractEffect
{
	private final Set<AbnormalType> _abnormals;
	private final int _time;
	private final int _mode;
	
	public InstantAbnormalTimeChange(StatsSet params)
	{
		String abnormals = params.getString("slot", null);
		if ((abnormals != null) && !abnormals.isEmpty())
		{
			_abnormals = new HashSet<>();
			for (String slot : abnormals.split(";"))
			{
				_abnormals.add(AbnormalType.getAbnormalType(slot));
			}
		}
		else
		{
			_abnormals = Collections.<AbnormalType> emptySet();
		}
		
		_time = params.getInt("time", -1);
		
		switch (params.getString("mode", "DEBUFF"))
		{
			case "DIFF":
			{
				_mode = 0;
				break;
			}
			case "DEBUFF":
			{
				_mode = 1;
				break;
			}
			default:
			{
				throw new IllegalArgumentException("Mode should be DIFF or DEBUFF for skill id:" + params.getInt("id"));
			}
		}
	}
	
	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item)
	{
		final Creature targetCreature = target.asCreature();
		if (targetCreature == null)
		{
			return;
		}
		
		final AbnormalStatusUpdate asu = new AbnormalStatusUpdate();
		switch (_mode)
		{
			case 0: // DIFF
			{
				if (_abnormals.isEmpty())
				{
					targetCreature.getEffectList().getEffects().stream().filter(b -> b.getSkill().canBeDispelled()).forEach(b ->
					{
						b.resetAbnormalTime(b.getTime() + _time);
						asu.addSkill(b);
					});
				}
				else
				{
					targetCreature.getEffectList().getEffects().stream().filter(b -> b.getSkill().canBeDispelled() && _abnormals.contains(b.getSkill().getAbnormalType())).forEach(b ->
					{
						b.resetAbnormalTime(b.getTime() + _time);
						asu.addSkill(b);
					});
				}
				break;
			}
			case 1: // DEBUFF
			{
				if (_abnormals.isEmpty())
				{
					targetCreature.getEffectList().getDebuffs().stream().filter(b -> b.getSkill().canBeDispelled()).forEach(b ->
					{
						b.resetAbnormalTime(b.getAbnormalTime());
						asu.addSkill(b);
					});
				}
				else
				{
					targetCreature.getEffectList().getDebuffs().stream().filter(b -> b.getSkill().canBeDispelled() && _abnormals.contains(b.getSkill().getAbnormalType())).forEach(b ->
					{
						b.resetAbnormalTime(b.getAbnormalTime());
						asu.addSkill(b);
					});
				}
				break;
			}
		}
		
		targetCreature.sendPacket(asu);
		
		final ExAbnormalStatusUpdateFromTarget upd = new ExAbnormalStatusUpdateFromTarget(targetCreature);
		
		// @formatter:off
		targetCreature.getStatus().getStatusListener().stream()
			.filter(Objects::nonNull)
			.filter(WorldObject::isPlayer)
			.map(Creature::getActingPlayer)
			.forEach(upd::sendTo);
		// @formatter:on
		
		if (targetCreature.isPlayer() && (targetCreature.getTarget() == targetCreature))
		{
			targetCreature.sendPacket(upd);
		}
	}
}
