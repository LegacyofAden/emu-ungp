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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.List;

/**
 * Format: (ch) d[ddddd]
 *
 * @author -Wooden-
 */
public class ExCursedWeaponLocation implements IClientOutgoingPacket {
	private final List<CursedWeaponInfo> _cursedWeaponInfo;

	public ExCursedWeaponLocation(List<CursedWeaponInfo> cursedWeaponInfo) {
		_cursedWeaponInfo = cursedWeaponInfo;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_CURSED_WEAPON_LOCATION.writeId(packet);

		if (!_cursedWeaponInfo.isEmpty()) {
			packet.writeD(_cursedWeaponInfo.size());
			for (CursedWeaponInfo w : _cursedWeaponInfo) {
				packet.writeD(w.id);
				packet.writeD(w.activated);

				packet.writeD((int) w.pos.getX());
				packet.writeD((int) w.pos.getY());
				packet.writeD((int) w.pos.getZ());
			}
		} else {
			packet.writeD(0);
		}
		return true;
	}

	public static class CursedWeaponInfo {
		public Location pos;
		public int id;
		public int activated; // 0 - not activated ? 1 - activated

		public CursedWeaponInfo(Location p, int ID, int status) {
			pos = p;
			id = ID;
			activated = status;
		}

	}
}
