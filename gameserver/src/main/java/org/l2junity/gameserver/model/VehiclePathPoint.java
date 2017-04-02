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
package org.l2junity.gameserver.model;

public final class VehiclePathPoint extends Location {
	private static final long serialVersionUID = 7529959885098271654L;
	private final int _moveSpeed;
	private final int _rotationSpeed;

	public VehiclePathPoint(Location loc) {
		this(loc.getX(), loc.getY(), loc.getZ());
	}

	public VehiclePathPoint(double x, double y, double z) {
		super(x, y, z);
		_moveSpeed = 350;
		_rotationSpeed = 4000;
	}

	public VehiclePathPoint(double x, double y, double z, int moveSpeed, int rotationSpeed) {
		super(x, y, z);
		_moveSpeed = moveSpeed;
		_rotationSpeed = rotationSpeed;
	}

	public int getMoveSpeed() {
		return _moveSpeed;
	}

	public int getRotationSpeed() {
		return _rotationSpeed;
	}
}
