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
 *
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class Vector3 extends Vector2 implements Cloneable {
	public static Vector3 createRandomNormal3() {
		return new Vector3(Rnd.nextSignum() * Rnd.nextFloat(), Rnd.nextSignum() * Rnd.nextFloat(), Rnd.nextSignum() * Rnd.nextFloat());
	}

	@Getter
	@Setter
	protected double z;

	public Vector3(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public Vector3(Vector3 vector3) {
		super(vector3);
		this.z = vector3.z;
	}

	public int getIntZ() {
		return (int) z;
	}

	public int getRoundZ() {
		return (int) Math.round(z);
	}

	@Override
	public void scale() {
		super.scale();
		this.z = Math.scalb(z, 2);
	}

	public void set(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void setZero() {
		set(0f, 0f, 0f);
	}

	@Override
	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	@Override
	public void normalize() {
		final double magnitude = magnitude();
		x /= magnitude;
		y /= magnitude;
		z /= magnitude;
	}

	public void add(Vector3 vector3) {
		this.x += vector3.x;
		this.y += vector3.y;
		this.z += vector3.z;
	}

	public void substract(Vector3 vector3) {
		this.x -= vector3.x;
		this.y -= vector3.y;
		this.z -= vector3.z;
	}

	@Override
	public void multiply(float value) {
		this.x *= value;
		this.y *= value;
		this.z *= value;
	}

	@Override
	public void divide(float value) {
		this.x /= value;
		this.y /= value;
		this.z /= value;
	}

	public double distance(Vector3 vector3) {
		Vector3 sub = vector3.clone();
		sub.substract(this);
		return sub.magnitude();
	}

	public void cross(Vector3 vector3) {
		final double cx = x, cy = y, cz = z;

		this.x = cy * vector3.z - cz * vector3.y;
		this.y = cz * vector3.x - cx * vector3.z;
		this.z = cx * vector3.y - cy * vector3.x;
	}

	public double dot(Vector3 vector3) {
		return this.x * vector3.x + this.y * vector3.y + this.z * vector3.z;
	}

	public double angle(Vector3 vector3) {
		final double angle = Math.acos(dot(vector3) / magnitude() * vector3.magnitude());
		if (Double.isNaN(angle)) {
			return 0d;
		}
		return angle;
	}

	public void multiplyAndAdd(Vector3 v2, double mul2) {
		this.x += mul2 * v2.x;
		this.y += mul2 * v2.y;
		this.z += mul2 * v2.z;
	}

	public void multiplyAndAdd(double mul1, Vector3 v2, double mul2) {
		this.x = mul1 * x + mul2 * v2.x;
		this.y = mul1 * y + mul2 * v2.y;
		this.z = mul1 * z + mul2 * v2.z;
	}

	@Override
	public Vector3 clone() {
		return new Vector3(this);
	}

	public final Point3D toPoint3() {
		return new Point3D(getRoundX(), getRoundY(), getRoundZ());
	}

	public final Vector2 toVector2() {
		return new Vector2(this.x, this.y);
	}
}
