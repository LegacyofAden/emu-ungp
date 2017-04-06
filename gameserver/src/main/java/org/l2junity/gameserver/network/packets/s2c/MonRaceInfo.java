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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class MonRaceInfo extends GameServerPacket {
	private final int _unknown1;
	private final int _unknown2;
	private final Npc[] _monsters;
	private final int[][] _speeds;

	public MonRaceInfo(int unknown1, int unknown2, Npc[] monsters, int[][] speeds) {
		/*
		 * -1 0 to initial the race 0 15322 to start race 13765 -1 in middle of race -1 0 to end the race
		 */
		_unknown1 = unknown1;
		_unknown2 = unknown2;
		_monsters = monsters;
		_speeds = speeds;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MON_RACE_INFO.writeId(body);

		body.writeD(_unknown1);
		body.writeD(_unknown2);
		body.writeD(0x08);

		for (int i = 0; i < 8; i++) {
			body.writeD(_monsters[i].getObjectId()); // npcObjectID
			body.writeD(_monsters[i].getTemplate().getId() + 1000000); // npcID
			body.writeD(14107); // origin X
			body.writeD(181875 + (58 * (7 - i))); // origin Y
			body.writeD(-3566); // origin Z
			body.writeD(12080); // end X
			body.writeD(181875 + (58 * (7 - i))); // end Y
			body.writeD(-3566); // end Z
			body.writeF(_monsters[i].getTemplate().getfCollisionHeight()); // coll. height
			body.writeF(_monsters[i].getTemplate().getfCollisionRadius()); // coll. radius
			body.writeD(120); // ?? unknown
			for (int j = 0; j < 20; j++) {
				if (_unknown1 == 0) {
					body.writeC(_speeds[i][j]);
				} else {
					body.writeC(0x00);
				}
			}
		}
	}
}
