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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.instancemanager.AirShipManager;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.VehiclePathPoint;
import org.l2junity.gameserver.model.actor.instance.AirShipInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldData;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public class MoveToLocationAirShip extends GameClientPacket {
	public static final int MIN_Z = -895;
	public static final int MAX_Z = 6105;
	public static final int STEP = 300;

	private int _command;
	private int _param1;
	private int _param2 = 0;

	@Override
	public void readImpl() {
		_command = readD();
		_param1 = readD();
		if (getAvailableBytes() > 0) {
			_param2 = readD();
		}
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!activeChar.isInAirShip()) {
			return;
		}

		final AirShipInstance ship = activeChar.getAirShip();
		if (!ship.isCaptain(activeChar)) {
			return;
		}

		double z = ship.getZ();

		switch (_command) {
			case 0:
				if (!ship.canBeControlled()) {
					return;
				}
				if (_param1 < WorldData.GRACIA_MAX_X) {
					ship.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(_param1, _param2, z));
				}
				break;
			case 1:
				if (!ship.canBeControlled()) {
					return;
				}
				ship.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
				break;
			case 2:
				if (!ship.canBeControlled()) {
					return;
				}
				if (z < WorldData.GRACIA_MAX_Z) {
					z = Math.min(z + STEP, WorldData.GRACIA_MAX_Z);
					ship.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(ship.getX(), ship.getY(), z));
				}
				break;
			case 3:
				if (!ship.canBeControlled()) {
					return;
				}
				if (z > WorldData.GRACIA_MIN_Z) {
					z = Math.max(z - STEP, WorldData.GRACIA_MIN_Z);
					ship.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(ship.getX(), ship.getY(), z));
				}
				break;
			case 4:
				if (!ship.isInDock() || ship.isMoving()) {
					return;
				}

				final VehiclePathPoint[] dst = AirShipManager.getInstance().getTeleportDestination(ship.getDockId(), _param1);
				if (dst == null) {
					return;
				}

				// Consume fuel, if needed
				final int fuelConsumption = AirShipManager.getInstance().getFuelConsumption(ship.getDockId(), _param1);
				if (fuelConsumption > 0) {
					if (fuelConsumption > ship.getFuel()) {
						activeChar.sendPacket(SystemMessageId.YOUR_AIRSHIP_CANNOT_TELEPORT_BECAUSE_DUE_TO_LOW_FUEL);
						return;
					}
					ship.setFuel(ship.getFuel() - fuelConsumption);
				}

				ship.executePath(dst);
				break;
		}
	}
}
