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
package org.l2junity.gameserver.network.packets.s2c.shuttle;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.ShuttleInstance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExShuttleMove extends GameServerPacket {
	private final ShuttleInstance _shuttle;
	private final int _x, _y, _z;

	public ExShuttleMove(ShuttleInstance shuttle, double x, double y, double z) {
		_shuttle = shuttle;
		_x = (int) x;
		_y = (int) y;
		_z = (int) z;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SUTTLE_MOVE.writeId(body);

		body.writeD(_shuttle.getObjectId());
		body.writeD((int) _shuttle.getStat().getMoveSpeed());
		body.writeD((int) _shuttle.getStat().getRotationSpeed());
		body.writeD(_x);
		body.writeD(_y);
		body.writeD(_z);
	}
}
