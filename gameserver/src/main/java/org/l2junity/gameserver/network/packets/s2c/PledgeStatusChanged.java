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
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class PledgeStatusChanged extends GameServerPacket {
	private final Clan _clan;

	public PledgeStatusChanged(Clan clan) {
		_clan = clan;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_STATUS_CHANGED.writeId(body);

		body.writeD(0x00);
		body.writeD(_clan.getLeaderId());
		body.writeD(_clan.getId());
		body.writeD(_clan.getCrestId());
		body.writeD(_clan.getAllyId());
		body.writeD(_clan.getAllyCrestId());
		body.writeD(_clan.getCrestLargeId());
		body.writeD(0x00); // pledge type ?
	}
}