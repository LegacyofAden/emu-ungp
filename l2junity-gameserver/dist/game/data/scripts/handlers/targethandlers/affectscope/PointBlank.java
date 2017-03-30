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
package handlers.targethandlers.affectscope;

import java.awt.Color;
import java.util.function.Consumer;

import org.l2junity.commons.lang.mutable.MutableInt;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.handler.IAffectScopeHandler;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.ExServerPrimitive;

import handlers.targethandlers.affectobject.ObjectDeadNpcBody;

/**
 * Point Blank affect scope implementation. Gathers targets in specific radius except initial target.
 * @author Nik
 */
public class PointBlank implements IAffectScopeHandler
{
	@Override
	public void forEachAffected(Creature activeChar, WorldObject target, Skill skill, Consumer<? super WorldObject> action)
	{
		final int affectRange = skill.getAffectRange();
		final int affectLimit = skill.getAffectLimit();
		
		// Target checks.
		final MutableInt affected = new MutableInt(0);
		
		// Check and add targets.
		if (skill.getTargetTypeHandler().isGround())
		{
			if (activeChar.isPlayable())
			{
				ILocational worldPosition = activeChar.getActingPlayer().getCurrentSkillWorldPosition();
				if (worldPosition != null)
				{
					World.getInstance().forEachVisibleObjectInRadius(activeChar, Creature.class, (int) (affectRange + activeChar.distance2d(worldPosition)), c ->
					{
						if (!c.isInRadius3d(worldPosition, affectRange))
						{
							return;
						}
						if ((affectLimit > 0) && (affected.intValue() >= affectLimit))
						{
							return;
						}
						// XXX : Find a proper way to fix, if it's not proper.
						if ((!c.isDead() || (skill.getAffectObjectHandler() instanceof ObjectDeadNpcBody)) && !skill.getAffectObjectHandler().checkAffectedObject(activeChar, c))
						{
							return;
						}
						if (!GeoData.getInstance().canSeeTarget(target, c))
						{
							return;
						}
						
						affected.increment();
						action.accept(c);
					});
				}
			}
		}
		else
		{
			World.getInstance().forEachVisibleObjectInRadius(target, Creature.class, affectRange, c ->
			{
				if ((affectLimit > 0) && (affected.intValue() >= affectLimit))
				{
					return;
				}
				// XXX : Find a proper way to fix, if it's not proper.
				if ((!c.isDead() || (skill.getAffectObjectHandler() instanceof ObjectDeadNpcBody)) && !skill.getAffectObjectHandler().checkAffectedObject(activeChar, c))
				{
					return;
				}
				if (!GeoData.getInstance().canSeeTarget(target, c))
				{
					return;
				}
				
				affected.increment();
				action.accept(c);
			});
		}
	}
	
	@Override
	public void drawEffected(Creature activeChar, WorldObject target, Skill skill)
	{
		final ExServerPrimitive packet = new ExServerPrimitive(getClass().getSimpleName() + "-" + activeChar.getObjectId(), activeChar);
		final int maxPoints = skill.getAffectRange() > 1000 ? 36 : skill.getAffectRange() > 100 ? 18 : 12;
		final ILocational[] locs = new ILocational[maxPoints];
		final double anglePoint = 360 / maxPoints;
		for (int i = 0; i < locs.length; i++)
		{
			double angle = (anglePoint * i);
			
			final double tx = target.getX() + (skill.getAffectRange() * Math.cos(Math.toRadians(angle)));
			final double ty = target.getY() + (skill.getAffectRange() * Math.sin(Math.toRadians(angle)));
			final double tz = GeoData.getInstance().getHeight(tx, ty, target.getZ());
			locs[i] = new Location(tx, ty, tz);
		}
		
		packet.addLine("X -> O (" + skill.getAffectRange() + " AOE)", Color.GREEN, true, activeChar, target);
		for (int i = 1; i < locs.length; i++)
		{
			packet.addLine((anglePoint * (i - 1)) + " -> " + (anglePoint * i), Color.GREEN, true, locs[i - 1], locs[i]);
		}
		packet.addLine((anglePoint * (locs.length - 1)) + " -> 360", Color.GREEN, true, locs[0], locs[locs.length - 1]);
		
		activeChar.sendDebugPacket(packet);
	}
}