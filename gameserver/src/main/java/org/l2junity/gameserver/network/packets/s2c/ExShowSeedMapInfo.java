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
import org.l2junity.gameserver.instancemanager.GraciaSeedsManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class ExShowSeedMapInfo extends GameServerPacket {
	public static final ExShowSeedMapInfo STATIC_PACKET = new ExShowSeedMapInfo();

	private ExShowSeedMapInfo() {
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_SEED_MAP_INFO.writeId(body);

		body.writeD(2); // seed count

		// Seed of Destruction
		body.writeD(-246857); // x coord
		body.writeD(251960); // y coord
		body.writeD(4331); // z coord
		body.writeD(2770 + GraciaSeedsManager.getInstance().getSoDState()); // sys msg id

		// Seed of Infinity
		body.writeD(-213770); // x coord
		body.writeD(210760); // y coord
		body.writeD(4400); // z coord
		// Manager not implemented yet
		body.writeD(2766); // sys msg id
	}
}
