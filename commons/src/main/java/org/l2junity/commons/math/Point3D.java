/*
 * Copyright (c) 2010-2016 fork2
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE 
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR 
 * IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.l2junity.commons.math;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author n3k0nation
 *
 */
@EqualsAndHashCode(callSuper = true)
public class Point3D extends Point2D {
	private static final long serialVersionUID = 1L;

	public static double distance3Squared(Point3D point1, Point3D point2) {
		return point1.getDistance3Squared(point2);
	}

	public static double distance3(Point3D point1, Point3D point2) {
		return point1.getDistance3(point2);
	}

	public static boolean distance3LessThan(Point3D point1, Point3D point2, double distance) {
		return point1.isDistance3LessThan(point2, distance);
	}

	@Getter
	@Setter
	protected double z;

	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public Point3D(double x, double y) {
		super(x, y);
	}

	public Point3D(Point3D point) {
		super(point);
		this.z = point.z;
	}

	public final void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public final void set(Point3D point) {
		this.x = point.x;
		this.y = point.y;
		this.z = point.z;
	}

	public final double getDistance3Squared(double x, double y, double z) {
		final double dx = this.x - x;
		final double dy = this.y - y;
		final double dz = this.z - z;
		return dx * dx + dy * dy + dz * dz;
	}

	public final double getDistance3Squared(Point3D point) {
		return getDistance3Squared(point.getX(), point.getY(), point.getZ());
	}

	public final double getDistance3(double x, double y, double z) {
		return Math.sqrt(getDistance3Squared(x, y, z));
	}

	public final double getDistance3(Point3D point) {
		return getDistance3(point.getX(), point.getY(), point.getZ());
	}

	public final boolean isDistance3LessThan(double x, double y, double z, double distance) {
		return getDistance3Squared(x, y, z) < distance * distance;
	}

	public final boolean isDistance3LessThan(Point3D point, double distance) {
		return isDistance3LessThan(point.getX(), point.getY(), point.getZ(), distance);
	}

	public final Vector3 toVector3() {
		return new Vector3(x, y, z);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	public boolean equalsWoZ(Point3D p) {
		return x == p.x && y == p.y && p.z >= z - 15 && p.z <= z + 15;
	}

	public boolean equals(int x, int y, int z) {
		return this.x == x && this.y == y && this.z == z;
	}

	@Override
	public Point3D clone() {
		return new Point3D(this);
	}
}