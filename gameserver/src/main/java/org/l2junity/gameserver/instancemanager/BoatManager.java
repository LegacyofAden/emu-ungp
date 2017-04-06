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
package org.l2junity.gameserver.instancemanager;

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.VehiclePathPoint;
import org.l2junity.gameserver.model.actor.instance.BoatInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.CharTemplate;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;

import java.util.HashMap;
import java.util.Map;


public class BoatManager {
	private final Map<Integer, BoatInstance> _boats = new HashMap<>();
	private final boolean[] _docksBusy = new boolean[3];

	public static final int TALKING_ISLAND = 1;
	public static final int GLUDIN_HARBOR = 2;
	public static final int RUNE_HARBOR = 3;

	public static BoatManager getInstance() {
		return SingletonHolder._instance;
	}

	protected BoatManager() {
		for (int i = 0; i < _docksBusy.length; i++) {
			_docksBusy[i] = false;
		}
	}

	public BoatInstance getNewBoat(int boatId, int x, int y, int z, int heading) {
		if (!GeneralConfig.ALLOW_BOAT) {
			return null;
		}

		StatsSet npcDat = new StatsSet();
		npcDat.set("npcId", boatId);
		npcDat.set("level", 0);
		npcDat.set("jClass", "boat");

		npcDat.set("baseSTR", 0);
		npcDat.set("baseCON", 0);
		npcDat.set("baseDEX", 0);
		npcDat.set("baseINT", 0);
		npcDat.set("baseWIT", 0);
		npcDat.set("baseMEN", 0);

		npcDat.set("baseShldDef", 0);
		npcDat.set("baseShldRate", 0);
		npcDat.set("baseAccCombat", 38);
		npcDat.set("baseEvasRate", 38);
		npcDat.set("baseCritRate", 38);

		// npcDat.set("name", "");
		npcDat.set("collision_radius", 0);
		npcDat.set("collision_height", 0);
		npcDat.set("sex", "male");
		npcDat.set("type", "");
		npcDat.set("baseAtkRange", 0);
		npcDat.set("baseMpMax", 0);
		npcDat.set("baseCpMax", 0);
		npcDat.set("rewardExp", 0);
		npcDat.set("rewardSp", 0);
		npcDat.set("basePAtk", 0);
		npcDat.set("baseMAtk", 0);
		npcDat.set("basePAtkSpd", 0);
		npcDat.set("aggroRange", 0);
		npcDat.set("baseMAtkSpd", 0);
		npcDat.set("rhand", 0);
		npcDat.set("lhand", 0);
		npcDat.set("armor", 0);
		npcDat.set("baseWalkSpd", 0);
		npcDat.set("baseRunSpd", 0);
		npcDat.set("baseHpMax", 50000);
		npcDat.set("baseHpReg", 3.e-3f);
		npcDat.set("baseMpReg", 3.e-3f);
		npcDat.set("basePDef", 100);
		npcDat.set("baseMDef", 100);
		CharTemplate template = new CharTemplate(npcDat);
		BoatInstance boat = new BoatInstance(template);
		_boats.put(boat.getObjectId(), boat);
		boat.setHeading(heading);
		boat.setXYZInvisible(x, y, z);
		boat.spawnMe();
		return boat;
	}

	/**
	 * @param boatId
	 * @return
	 */
	public BoatInstance getBoat(int boatId) {
		return _boats.get(boatId);
	}

	/**
	 * Lock/unlock dock so only one ship can be docked
	 *
	 * @param h     Dock Id
	 * @param value True if dock is locked
	 */
	public void dockShip(int h, boolean value) {
		try {
			_docksBusy[h] = value;
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}

	/**
	 * Check if dock is busy
	 *
	 * @param h Dock Id
	 * @return Trye if dock is locked
	 */
	public boolean dockBusy(int h) {
		try {
			return _docksBusy[h];
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	/**
	 * Broadcast one packet in both path points
	 *
	 * @param point1
	 * @param point2
	 * @param packet
	 */
	public void broadcastPacket(VehiclePathPoint point1, VehiclePathPoint point2, GameServerPacket packet) {
		broadcastPacketsToPlayers(point1, point2, packet);
	}

	/**
	 * Broadcast several packets in both path points
	 *
	 * @param point1
	 * @param point2
	 * @param packets
	 */
	public void broadcastPackets(VehiclePathPoint point1, VehiclePathPoint point2, GameServerPacket... packets) {
		broadcastPacketsToPlayers(point1, point2, packets);
	}

	private void broadcastPacketsToPlayers(VehiclePathPoint point1, VehiclePathPoint point2, GameServerPacket... packets) {
		//FIXME n3k0: rework it to like this:
		//WorldManager.getInstance().getMainWorld().forEachVisibleObjectInRadius(point1, Player.class, GeneralConfig.BOAT_BROADCAST_RADIUS, player -> player.sendPacket(packets));

		for (Player player : WorldManager.getInstance().getMainWorld().getPlayers()) {
			double dx = player.getX() - point1.getX();
			double dy = player.getY() - point1.getY();
			if (Math.sqrt((dx * dx) + (dy * dy)) < GeneralConfig.BOAT_BROADCAST_RADIUS) {
				for (GameServerPacket p : packets) {
					player.sendPacket(p);
				}
			} else {
				dx = player.getX() - point2.getX();
				dy = player.getY() - point2.getY();
				if (Math.sqrt((dx * dx) + (dy * dy)) < GeneralConfig.BOAT_BROADCAST_RADIUS) {
					for (GameServerPacket p : packets) {
						player.sendPacket(p);
					}
				}
			}
		}
	}

	private static class SingletonHolder {
		protected static final BoatManager _instance = new BoatManager();
	}
}