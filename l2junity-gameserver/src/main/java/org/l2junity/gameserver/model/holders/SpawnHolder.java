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
package org.l2junity.gameserver.model.holders;

import org.l2junity.gameserver.model.Location;

/**
 * @author St3eT
 */
public final class SpawnHolder extends Location
{
	private static final long serialVersionUID = 7136684341706210901L;
	private final int _npcId;
	private final int _respawnDelay;
	private final boolean _spawnAnimation;
	
	public SpawnHolder(int npcId, int x, int y, int z, int heading, boolean spawnAnimation)
	{
		super(x, y, z, heading);
		_npcId = npcId;
		_respawnDelay = 0;
		_spawnAnimation = spawnAnimation;
	}
	
	public SpawnHolder(int npcId, int x, int y, int z, int heading, int respawn, boolean spawnAnimation)
	{
		super(x, y, z, heading);
		_npcId = npcId;
		_respawnDelay = respawn;
		_spawnAnimation = spawnAnimation;
	}
	
	public SpawnHolder(int npcId, Location loc, boolean spawnAnimation)
	{
		super(loc.getX(), loc.getY(), loc.getZ(), loc.getHeading());
		_npcId = npcId;
		_respawnDelay = 0;
		_spawnAnimation = spawnAnimation;
	}
	
	public SpawnHolder(int npcId, Location loc, int respawn, boolean spawnAnimation)
	{
		super(loc.getX(), loc.getY(), loc.getZ(), loc.getHeading());
		_npcId = npcId;
		_respawnDelay = respawn;
		_spawnAnimation = spawnAnimation;
	}
	
	public final int getNpcId()
	{
		return _npcId;
	}
	
	public final boolean isSpawnAnimation()
	{
		return _spawnAnimation;
	}
	
	public int getRespawnDelay()
	{
		return _respawnDelay;
	}
}