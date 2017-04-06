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
package org.l2junity.gameserver.network.packets.s2c.sayune;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.enums.SayuneType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExFlyMoveBroadcast extends GameServerPacket {
	private final int _objectId;
	private final int _mapId;
	private final ILocational _currentLoc;
	private final ILocational _targetLoc;
	private final SayuneType _type;

	public ExFlyMoveBroadcast(Player activeChar, SayuneType type, int mapId, ILocational targetLoc) {
		_objectId = activeChar.getObjectId();
		_type = type;
		_mapId = mapId;
		_currentLoc = activeChar;
		_targetLoc = targetLoc;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_FLY_MOVE_BROADCAST.writeId(body);

		body.writeD(_objectId);

		body.writeD(_type.ordinal());
		body.writeD(_mapId);

		body.writeD((int) _targetLoc.getX());
		body.writeD((int) _targetLoc.getY());
		body.writeD((int) _targetLoc.getZ());
		body.writeD(0x00); // ?
		body.writeD((int) _currentLoc.getX());
		body.writeD((int) _currentLoc.getY());
		body.writeD((int) _currentLoc.getZ());
	}
}
