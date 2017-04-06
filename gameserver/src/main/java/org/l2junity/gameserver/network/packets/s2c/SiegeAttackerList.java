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
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.SiegeClan;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * Populates the Siege Attacker List in the SiegeInfo Window<BR>
 * <BR>
 * c = ca<BR>
 * d = CastleID<BR>
 * d = unknow (0x00)<BR>
 * d = unknow (0x01)<BR>
 * d = unknow (0x00)<BR>
 * d = Number of Attackers Clans?<BR>
 * d = Number of Attackers Clans<BR>
 * { //repeats<BR>
 * d = ClanID<BR>
 * S = ClanName<BR>
 * S = ClanLeaderName<BR>
 * d = ClanCrestID<BR>
 * d = signed time (seconds)<BR>
 * d = AllyID<BR>
 * S = AllyName<BR>
 * S = AllyLeaderName<BR>
 * d = AllyCrestID<BR>
 *
 * @author KenM
 */
public final class SiegeAttackerList extends GameServerPacket {
	private final Castle _castle;

	public SiegeAttackerList(Castle castle) {
		_castle = castle;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CASTLE_SIEGE_ATTACKER_LIST.writeId(body);

		body.writeD(_castle.getResidenceId());
		body.writeD(0x00); // 0
		body.writeD(0x01); // 1
		body.writeD(0x00); // 0
		int size = _castle.getSiege().getAttackerClans().size();
		if (size > 0) {
			Clan clan;

			body.writeD(size);
			body.writeD(size);
			for (SiegeClan siegeclan : _castle.getSiege().getAttackerClans()) {
				clan = ClanTable.getInstance().getClan(siegeclan.getClanId());
				if (clan == null) {
					continue;
				}

				body.writeD(clan.getId());
				body.writeS(clan.getName());
				body.writeS(clan.getLeaderName());
				body.writeD(clan.getCrestId());
				body.writeD(0x00); // signed time (seconds) (not storated by L2J)
				body.writeD(clan.getAllyId());
				body.writeS(clan.getAllyName());
				body.writeS(""); // AllyLeaderName
				body.writeD(clan.getAllyCrestId());
			}
		} else {
			body.writeD(0x00);
			body.writeD(0x00);
		}
	}
}
