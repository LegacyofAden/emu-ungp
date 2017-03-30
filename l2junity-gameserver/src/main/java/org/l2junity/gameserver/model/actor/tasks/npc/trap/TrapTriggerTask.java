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
package org.l2junity.gameserver.model.actor.tasks.npc.trap;

import java.util.concurrent.TimeUnit;

import org.l2junity.commons.util.concurrent.ThreadPool;
import org.l2junity.gameserver.model.actor.instance.L2TrapInstance;

/**
 * Trap trigger task.
 * @author Zoey76
 */
public class TrapTriggerTask implements Runnable
{
	private final L2TrapInstance _trap;
	
	public TrapTriggerTask(L2TrapInstance trap)
	{
		_trap = trap;
	}
	
	@Override
	public void run()
	{
		try
		{
			_trap.doCast(_trap.getSkill());
			ThreadPool.schedule(new TrapUnsummonTask(_trap), _trap.getSkill().getHitTime() + 300, TimeUnit.MILLISECONDS);
		}
		catch (Exception e)
		{
			_trap.unSummon();
		}
	}
}
