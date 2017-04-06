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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class PlaySound extends GameServerPacket {
	private final int _unknown1;
	private final String _soundFile;
	private final int _unknown3;
	private final int _unknown4;
	private final int _x;
	private final int _y;
	private final int _z;
	private final int _unknown8;

	public PlaySound(String soundFile) {
		_unknown1 = 0;
		_soundFile = soundFile;
		_unknown3 = 0;
		_unknown4 = 0;
		_x = 0;
		_y = 0;
		_z = 0;
		_unknown8 = 0;
	}

	public PlaySound(int unknown1, String soundFile, int unknown3, int unknown4, double x, double y, double z) {
		_unknown1 = unknown1;
		_soundFile = soundFile;
		_unknown3 = unknown3;
		_unknown4 = unknown4;
		_x = (int) x;
		_y = (int) y;
		_z = (int) z;
		_unknown8 = 0;
	}

	public String getSoundName() {
		return _soundFile;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLAY_SOUND.writeId(body);

		body.writeD(_unknown1); // unknown 0 for quest and ship;
		body.writeS(_soundFile);
		body.writeD(_unknown3); // unknown 0 for quest; 1 for ship;
		body.writeD(_unknown4); // 0 for quest; objectId of ship
		body.writeD(_x); // x
		body.writeD(_y); // y
		body.writeD(_z); // z
		body.writeD(_unknown8);
	}
}