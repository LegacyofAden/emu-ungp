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
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zabbix
 */
public class PartyMemberPosition extends GameServerPacket {
	private final Map<Integer, Location> locations = new HashMap<>();

	public PartyMemberPosition(Party party) {
		reuse(party);
	}

	public void reuse(Party party) {
		locations.clear();
		for (Player member : party.getMembers()) {
			if (member == null) {
				continue;
			}
			locations.put(member.getObjectId(), member.getLocation());
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PARTY_MEMBER_POSITION.writeId(body);

		body.writeD(locations.size());
		for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
			Location loc = entry.getValue();
			body.writeD(entry.getKey());
			body.writeD((int) loc.getX());
			body.writeD((int) loc.getY());
			body.writeD((int) loc.getZ());
		}
	}
}
