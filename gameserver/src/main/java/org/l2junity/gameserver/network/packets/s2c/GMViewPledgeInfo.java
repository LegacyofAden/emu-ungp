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
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class GMViewPledgeInfo extends GameServerPacket {
	private final Clan _clan;
	private final Player _activeChar;

	public GMViewPledgeInfo(Clan clan, Player activeChar) {
		_clan = clan;
		_activeChar = activeChar;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.GM_VIEW_PLEDGE_INFO.writeId(body);

		body.writeD(0x00);
		body.writeS(_activeChar.getName());
		body.writeD(_clan.getId());
		body.writeD(0x00);
		body.writeS(_clan.getName());
		body.writeS(_clan.getLeaderName());

		body.writeD(_clan.getCrestId()); // -> no, it's no longer used (nuocnam) fix by game
		body.writeD(_clan.getLevel());
		body.writeD(_clan.getCastleId());
		body.writeD(_clan.getHideoutId());
		body.writeD(_clan.getFortId());
		body.writeD(_clan.getRank());
		body.writeD(_clan.getReputationScore());
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(_clan.getAllyId()); // c2
		body.writeS(_clan.getAllyName()); // c2
		body.writeD(_clan.getAllyCrestId()); // c2
		body.writeD(_clan.isAtWar() ? 1 : 0); // c3
		body.writeD(0x00); // T3 Unknown

		body.writeD(_clan.getMembers().size());
		for (ClanMember member : _clan.getMembers()) {
			if (member != null) {
				body.writeS(member.getName());
				body.writeD(member.getLevel());
				body.writeD(member.getClassId());
				body.writeD(member.getSex() ? 1 : 0);
				body.writeD(member.getRaceOrdinal());
				body.writeD(member.isOnline() ? member.getObjectId() : 0);
				body.writeD(member.getSponsor() != 0 ? 1 : 0);
			}
		}
	}
}
