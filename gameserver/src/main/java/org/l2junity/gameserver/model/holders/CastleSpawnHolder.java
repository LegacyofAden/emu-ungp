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

import org.l2junity.gameserver.enums.CastleSide;
import org.l2junity.gameserver.model.Location;

/**
 * @author St3eT
 */
public class CastleSpawnHolder extends Location {
	private static final long serialVersionUID = -2901500054895111571L;
	private final int _npcId;
	private final CastleSide _side;

	public CastleSpawnHolder(int npcId, CastleSide side, int x, int y, int z, int heading) {
		super(x, y, z, heading);
		_npcId = npcId;
		_side = side;
	}

	public final int getNpcId() {
		return _npcId;
	}

	public final CastleSide getSide() {
		return _side;
	}
}
