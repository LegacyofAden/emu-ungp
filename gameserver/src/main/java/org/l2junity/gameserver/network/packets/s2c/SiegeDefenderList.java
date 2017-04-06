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
import org.l2junity.gameserver.enums.SiegeClanType;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.SiegeClan;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * Populates the Siege Defender List in the SiegeInfo Window<BR>
 * <BR>
 * c = 0xcb<BR>
 * d = CastleID<BR>
 * d = unknow (0x00)<BR>
 * d = unknow (0x01)<BR>
 * d = unknow (0x00)<BR>
 * d = Number of Defending Clans?<BR>
 * d = Number of Defending Clans<BR>
 * { //repeats<BR>
 * d = ClanID<BR>
 * S = ClanName<BR>
 * S = ClanLeaderName<BR>
 * d = ClanCrestID<BR>
 * d = signed time (seconds)<BR>
 * d = Type -> Owner = 0x01 || Waiting = 0x02 || Accepted = 0x03<BR>
 * d = AllyID<BR>
 * S = AllyName<BR>
 * S = AllyLeaderName<BR>
 * d = AllyCrestID<BR>
 *
 * @author KenM
 */
public final class SiegeDefenderList extends GameServerPacket {
	private final Castle _castle;

	public SiegeDefenderList(Castle castle) {
		_castle = castle;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CASTLE_SIEGE_DEFENDER_LIST.writeId(body);

		body.writeD(_castle.getResidenceId());
		body.writeD(0x00); // Unknown
		body.writeD(0x01); // Unknown
		body.writeD(0x00); // Unknown

		final int size = _castle.getSiege().getDefenderWaitingClans().size() + _castle.getSiege().getDefenderClans().size() + (_castle.getOwner() != null ? 1 : 0);

		body.writeD(size);
		body.writeD(size);

		// Add owners
		final Clan ownerClan = _castle.getOwner();
		if (ownerClan != null) {
			body.writeD(ownerClan.getId());
			body.writeS(ownerClan.getName());
			body.writeS(ownerClan.getLeaderName());
			body.writeD(ownerClan.getCrestId());
			body.writeD(0x00); // signed time (seconds) (not storated by L2J)
			body.writeD(SiegeClanType.OWNER.ordinal());
			body.writeD(ownerClan.getAllyId());
			body.writeS(ownerClan.getAllyName());
			body.writeS(""); // AllyLeaderName
			body.writeD(ownerClan.getAllyCrestId());
		}

		// List of confirmed defenders
		for (SiegeClan siegeClan : _castle.getSiege().getDefenderClans()) {
			final Clan defendingClan = ClanTable.getInstance().getClan(siegeClan.getClanId());
			if ((defendingClan == null) || (defendingClan == _castle.getOwner())) {
				continue;
			}

			body.writeD(defendingClan.getId());
			body.writeS(defendingClan.getName());
			body.writeS(defendingClan.getLeaderName());
			body.writeD(defendingClan.getCrestId());
			body.writeD(0x00); // signed time (seconds) (not storated by L2J)
			body.writeD(SiegeClanType.DEFENDER.ordinal());
			body.writeD(defendingClan.getAllyId());
			body.writeS(defendingClan.getAllyName());
			body.writeS(""); // AllyLeaderName
			body.writeD(defendingClan.getAllyCrestId());
		}

		// List of not confirmed defenders
		for (SiegeClan siegeClan : _castle.getSiege().getDefenderWaitingClans()) {
			final Clan defendingClan = ClanTable.getInstance().getClan(siegeClan.getClanId());
			if (defendingClan == null) {
				continue;
			}

			body.writeD(defendingClan.getId());
			body.writeS(defendingClan.getName());
			body.writeS(defendingClan.getLeaderName());
			body.writeD(defendingClan.getCrestId());
			body.writeD(0x00); // signed time (seconds) (not storated by L2J)
			body.writeD(SiegeClanType.DEFENDER_PENDING.ordinal());
			body.writeD(defendingClan.getAllyId());
			body.writeS(defendingClan.getAllyName());
			body.writeS(""); // AllyLeaderName
			body.writeD(defendingClan.getAllyCrestId());
		}
	}
}