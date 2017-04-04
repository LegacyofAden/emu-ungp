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

import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public class GMViewPledgeInfo implements IClientOutgoingPacket {
	private final Clan _clan;
	private final Player _activeChar;

	public GMViewPledgeInfo(Clan clan, Player activeChar) {
		_clan = clan;
		_activeChar = activeChar;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.GM_VIEW_PLEDGE_INFO.writeId(packet);

		packet.writeD(0x00);
		packet.writeS(_activeChar.getName());
		packet.writeD(_clan.getId());
		packet.writeD(0x00);
		packet.writeS(_clan.getName());
		packet.writeS(_clan.getLeaderName());

		packet.writeD(_clan.getCrestId()); // -> no, it's no longer used (nuocnam) fix by game
		packet.writeD(_clan.getLevel());
		packet.writeD(_clan.getCastleId());
		packet.writeD(_clan.getHideoutId());
		packet.writeD(_clan.getFortId());
		packet.writeD(_clan.getRank());
		packet.writeD(_clan.getReputationScore());
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(_clan.getAllyId()); // c2
		packet.writeS(_clan.getAllyName()); // c2
		packet.writeD(_clan.getAllyCrestId()); // c2
		packet.writeD(_clan.isAtWar() ? 1 : 0); // c3
		packet.writeD(0x00); // T3 Unknown

		packet.writeD(_clan.getMembers().size());
		for (ClanMember member : _clan.getMembers()) {
			if (member != null) {
				packet.writeS(member.getName());
				packet.writeD(member.getLevel());
				packet.writeD(member.getClassId());
				packet.writeD(member.getSex() ? 1 : 0);
				packet.writeD(member.getRaceOrdinal());
				packet.writeD(member.isOnline() ? member.getObjectId() : 0);
				packet.writeD(member.getSponsor() != 0 ? 1 : 0);
			}
		}
		return true;
	}
}
