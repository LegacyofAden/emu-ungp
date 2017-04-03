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

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author PointerRage
 *
 */
@EqualsAndHashCode
public class Point2D implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	public static double distanceSquared(Point2D point1, Point2D point2) {
		return point1.getDistance2Squared(point2);
	}

	public static double distance(Point2D point1, Point2D point2) {
		return point1.getDistance2(point2);
	}

	public static boolean distance2LessThan(Point2D point1, Point2D point2, double distance) {
		return point1.isDistance2LessThan(point2, distance);
	}

	@Getter
	protected double x;
	@Getter
	protected double y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point2D(Point2D point) {
		this.x = point.x;
		this.y = point.y;
	}

	public final void setX(double x) {
		this.x = x;
	}

	public final void setY(double y) {
		this.y = y;
	}

	public final void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public final void set(Point2D point) {
		this.x = point.x;
		this.y = point.y;
	}

	public final Point2D add(int x, int y) {
		setXY(this.x + x, this.y + y);
		return this;
	}

	public final double getDistance2Squared(double x, double y) {
		final double dx = this.x - x;
		final double dy = this.y - y;
		return (dx * dx) + (dy * dy);
	}

	public final double getDistance2Squared(Point2D point) {
		return getDistance2Squared(point.getX(), point.getY());
	}

	public final double getDistance2(double x, double y) {
		return Math.sqrt(getDistance2Squared(x, y));
	}

	public final double getDistance2(Point2D point) {
		return getDistance2(point.getX(), point.getY());
	}

	public final boolean isDistance2LessThan(double x, double y, double distance) {
		return getDistance2Squared(x, y) < distance * distance;
	}

	public final boolean isDistance2LessThan(Point2D point, double distance) {
		return isDistance2LessThan(point.getX(), point.getY(), distance);
	}

	public final Vector2 toVector2() {
		return new Vector2(x, y);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	@Override
	public Point2D clone() {
		return new Point2D(this);
	}
}
