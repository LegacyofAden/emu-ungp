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
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.gameserver.ai.BoatAI;
import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.Vehicle;
import org.l2junity.gameserver.model.actor.templates.CharTemplate;
import org.l2junity.gameserver.network.packets.s2c.VehicleDeparture;
import org.l2junity.gameserver.network.packets.s2c.VehicleInfo;
import org.l2junity.gameserver.network.packets.s2c.VehicleStarted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maktakien, DS
 */
public class BoatInstance extends Vehicle {
	protected static final Logger _logBoat = LoggerFactory.getLogger(BoatInstance.class);

	public BoatInstance(CharTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2BoatInstance);
		setAI(new BoatAI(this));
	}

	@Override
	public boolean isBoat() {
		return true;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public boolean moveToNextRoutePoint() {
		final boolean result = super.moveToNextRoutePoint();
		if (result) {
			broadcastPacket(new VehicleDeparture(this));
		}

		return result;
	}

	@Override
	public void oustPlayer(Player player) {
		super.oustPlayer(player);

		final Location loc = getOustLoc();
		if (player.isOnline()) {
			player.teleToLocation(loc.getX(), loc.getY(), loc.getZ());
		} else {
			player.setXYZInvisible(loc.getX(), loc.getY(), loc.getZ()); // disconnects handling
		}
	}

	@Override
	public void stopMove(Location loc) {
		super.stopMove(loc);

		broadcastPacket(new VehicleStarted(this, 0));
		broadcastPacket(new VehicleInfo(this));
	}

	@Override
	public void sendInfo(Player activeChar) {
		activeChar.sendPacket(new VehicleInfo(this));
	}
}
