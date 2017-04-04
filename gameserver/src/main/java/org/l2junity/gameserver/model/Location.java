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

import java.io.Serializable;

import org.l2junity.commons.math.Point3D;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.interfaces.IPositionable;

import lombok.Getter;
import lombok.Setter;

/**
 * Location data transfer object.<br>
 * Contains coordinates data, heading and instance Id.
 *
 * @author Zoey76
 */
public class Location extends Point3D implements IPositionable, Serializable {
	private static final long serialVersionUID = 9053071440080626121L;

	@Getter @Setter private int heading;

	public Location(double x, double y, double z) {
		this(x, y, z, 0);
	}

	public Location(double x, double y, double z, int heading) {
		super(x, y, z);
		this.heading = heading;
	}

	public Location(ILocational loc) {
		this(loc.getX(), loc.getY(), loc.getZ(), loc.getHeading());
	}

	public Location(StatsSet set) {
		super(set.getInt("x"), set.getInt("y"), set.getInt("z"));
		heading = set.getInt("heading", 0);
	}

	/**
	 * Set the x, y, z coordinates.
	 *
	 * @param loc The location.
	 */
	@Override
	public void setXYZ(ILocational loc) {
		setXYZ(loc.getX(), loc.getY(), loc.getZ());
	}

	@Override
	public void setLocation(Location loc) {
		x = loc.getX();
		y = loc.getY();
		z = loc.getZ();
		heading = loc.getHeading();
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof ILocational)) {
			final ILocational loc = (ILocational) obj;
			return (getX() == loc.getX()) && (getY() == loc.getY()) && (getZ() == loc.getZ()) && (getHeading() == loc.getHeading());
		}
		return false;
	}

	@Override
	public String toString() {
		return "[" + getClass().getSimpleName() + "] X: " + getX() + " Y: " + getY() + " Z: " + getZ() + " Heading: " + heading;
	}
}
