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

import org.l2junity.commons.util.Rnd;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author PointerRage
 */
@EqualsAndHashCode
@ToString
public class Vector2 {
	public static Vector2 createRandomNormal2() {
		return new Vector2(Rnd.nextSignum() * Rnd.nextFloat(), Rnd.nextSignum() * Rnd.nextFloat());
	}

	@Getter
	@Setter
	protected double x, y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2(Vector2 vector) {
		this.x = vector.x;
		this.y = vector.y;
	}

	public int getIntX() {
		return (int) x;
	}

	public int getIntY() {
		return (int) y;
	}

	public int getRoundX() {
		return (int) Math.round(x);
	}

	public int getRoundY() {
		return (int) Math.round(y);
	}

	public void scale() {
		this.x = Math.scalb(x, 2);
		this.y = Math.scalb(y, 2);
	}

	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setZero() {
		set(0f, 0f);
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public void normalize() {
		final double magnitude = magnitude();
		x /= magnitude;
		y /= magnitude;
	}

	public void add(Vector2 vector) {
		this.x += vector.x;
		this.y += vector.y;
	}

	public void substract(Vector2 vector) {
		this.x -= vector.x;
		this.y -= vector.y;
	}

	public void multiply(float value) {
		this.x *= value;
		this.y *= value;
	}

	public void divide(float value) {
		this.x /= value;
		this.y /= value;
	}

	public double distance(Vector2 vector) {
		Vector2 sub = vector.clone();
		sub.substract(this);
		return sub.magnitude();
	}

	public double dot(Vector2 vector) {
		return this.x * vector.x + this.y * vector.y;
	}

	public double angle(Vector2 vector) {
		final double angle = Math.acos(dot(vector) / magnitude() * vector.magnitude());
		if (Double.isNaN(angle)) {
			return 0d;
		}
		return angle;
	}

	public void multiplyAndAdd(Vector2 v2, float mul2) {
		this.x += mul2 * v2.x;
		this.y += mul2 * v2.y;
	}

	public void multiplyAndAdd(float mul1, Vector2 v2, float mul2) {
		this.x = mul1 * x + mul2 * v2.x;
		this.y = mul1 * y + mul2 * v2.y;
	}

	@Override
	public Vector2 clone() {
		return new Vector2(this);
	}

	public final Point2D toPoint2() {
		return new Point2D(x, y);
	}
}
