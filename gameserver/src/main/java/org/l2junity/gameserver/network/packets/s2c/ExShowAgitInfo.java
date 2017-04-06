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
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.model.entity.ClanHall;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

/**
 * @author KenM
 */
public class ExShowAgitInfo extends GameServerPacket {
	public static final ExShowAgitInfo STATIC_PACKET = new ExShowAgitInfo();

	private ExShowAgitInfo() {
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_AGIT_INFO.writeId(body);

		final Collection<ClanHall> clanHalls = ClanHallData.getInstance().getClanHalls();
		body.writeD(clanHalls.size());
		clanHalls.forEach(clanHall ->
		{
			body.writeD(clanHall.getResidenceId());
			body.writeS(clanHall.getOwnerId() <= 0 ? "" : ClanTable.getInstance().getClan(clanHall.getOwnerId()).getName()); // owner clan name
			body.writeS(clanHall.getOwnerId() <= 0 ? "" : ClanTable.getInstance().getClan(clanHall.getOwnerId()).getLeaderName()); // leader name
			body.writeD(clanHall.getType().getClientVal()); // Clan hall type
		});
	}
}
