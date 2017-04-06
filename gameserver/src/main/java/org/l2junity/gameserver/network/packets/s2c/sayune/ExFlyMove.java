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
import org.l2junity.gameserver.model.SayuneEntry;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author UnAfraid
 */
public class ExFlyMove extends GameServerPacket {
	private final int _objectId;
	private final SayuneType _type;
	private final int _mapId;
	private final List<SayuneEntry> _locations;

	public ExFlyMove(Player activeChar, SayuneType type, int mapId, List<SayuneEntry> locations) {
		_objectId = activeChar.getObjectId();
		_type = type;
		_mapId = mapId;
		_locations = locations;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_FLY_MOVE.writeId(body);

		body.writeD(_objectId);

		body.writeD(_type.ordinal());
		body.writeD(0x00); // ??
		body.writeD(_mapId);

		body.writeD(_locations.size());
		for (SayuneEntry loc : _locations) {
			body.writeD(loc.getId());
			body.writeD(0x00); // ??
			body.writeD((int) loc.getX());
			body.writeD((int) loc.getY());
			body.writeD((int) loc.getZ());
		}
	}
}
