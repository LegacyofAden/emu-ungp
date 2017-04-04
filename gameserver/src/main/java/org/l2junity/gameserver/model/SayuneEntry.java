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

import org.l2junity.gameserver.model.interfaces.ILocational;

import java.util.LinkedList;
import java.util.List;

/**
 * @author UnAfraid
 */
public class SayuneEntry implements ILocational {
	private boolean _isSelector = false;
	private final int _id;
	private double _x;
	private double _y;
	private double _z;
	private final List<SayuneEntry> _innerEntries = new LinkedList<>();

	public SayuneEntry(int id) {
		_id = id;
	}

	public SayuneEntry(boolean isSelector, int id, double x, double y, double z) {
		_isSelector = isSelector;
		_id = id;
		_x = x;
		_y = y;
		_z = z;
	}

	public int getId() {
		return _id;
	}

	@Override
	public double getX() {
		return _x;
	}

	@Override
	public double getY() {
		return _y;
	}

	@Override
	public double getZ() {
		return _z;
	}

	@Override
	public int getHeading() {
		return 0;
	}

	public boolean isSelector() {
		return _isSelector;
	}

	public List<SayuneEntry> getInnerEntries() {
		return _innerEntries;
	}

	public SayuneEntry addInnerEntry(SayuneEntry innerEntry) {
		_innerEntries.add(innerEntry);
		return innerEntry;
	}
}
