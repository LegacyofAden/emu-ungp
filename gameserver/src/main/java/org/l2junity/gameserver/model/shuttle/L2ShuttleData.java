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
package org.l2junity.gameserver.model.shuttle;

import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.VehiclePathPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author UnAfraid
 */
public final class L2ShuttleData {
	private final int _id;
	private final Location _loc;
	private final List<Integer> _doors = new ArrayList<>(2);
	private final List<L2ShuttleStop> _stops = new ArrayList<>(2);
	private final List<VehiclePathPoint[]> _routes = new ArrayList<>(2);

	public L2ShuttleData(StatsSet set) {
		_id = set.getInt("id");
		_loc = new Location(set);
	}

	public int getId() {
		return _id;
	}

	public Location getLocation() {
		return _loc;
	}

	public void addDoor(int id) {
		_doors.add(id);
	}

	public List<Integer> getDoors() {
		return _doors;
	}

	public void addStop(L2ShuttleStop stop) {
		_stops.add(stop);
	}

	public List<L2ShuttleStop> getStops() {
		return _stops;
	}

	public void addRoute(VehiclePathPoint[] route) {
		_routes.add(route);
	}

	public List<VehiclePathPoint[]> getRoutes() {
		return _routes;
	}
}
