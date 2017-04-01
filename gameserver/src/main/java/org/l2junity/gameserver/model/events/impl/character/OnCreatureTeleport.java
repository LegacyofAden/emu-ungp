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
package org.l2junity.gameserver.model.events.impl.character;

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.IBaseEvent;
import org.l2junity.gameserver.model.instancezone.Instance;

/**
 * @author Nik
 */
public class OnCreatureTeleport implements IBaseEvent
{
	private final Creature _creature;
	private final double _destX;
	private final double _destY;
	private final double _destZ;
	private final int _destHeading;
	private final Instance _destInstance;
	
	public OnCreatureTeleport(Creature creature, double destX, double destY, double destZ, int destHeading, Instance destInstance)
	{
		_creature = creature;
		_destX = destX;
		_destY = destY;
		_destZ = destZ;
		_destHeading = destHeading;
		_destInstance = destInstance;
	}
	
	public Creature getCreature()
	{
		return _creature;
	}
	
	public double getDestX()
	{
		return _destX;
	}
	
	public double getDestY()
	{
		return _destY;
	}
	
	public double getDestZ()
	{
		return _destZ;
	}
	
	public int getDestHeading()
	{
		return _destHeading;
	}
	
	public Instance getDestInstance()
	{
		return _destInstance;
	}
	
	@Override
	public EventType getType()
	{
		return EventType.ON_CREATURE_TELEPORT;
	}
}
