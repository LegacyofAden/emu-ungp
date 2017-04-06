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
package org.l2junity.gameserver.network.packets.s2c.fishing;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author -Wooden-
 */
public class ExFishingStart extends GameServerPacket {
	private final Player _player;
	private final int _fishType;
	private final int _baitType;
	private final ILocational _baitLocation;

	/**
	 * @param player
	 * @param fishType
	 * @param baitType     - 0 = newbie, 1 = normal, 2 = night
	 * @param baitLocation
	 */
	public ExFishingStart(Player player, int fishType, int baitType, ILocational baitLocation) {
		_player = player;
		_fishType = fishType;
		_baitType = baitType;
		_baitLocation = baitLocation;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_FISHING_START.writeId(body);
		body.writeD(_player.getObjectId());
		body.writeC(_fishType);
		body.writeD((int) _baitLocation.getX());
		body.writeD((int) _baitLocation.getY());
		body.writeD((int) _baitLocation.getZ());
		body.writeC(_baitType);
	}
}
