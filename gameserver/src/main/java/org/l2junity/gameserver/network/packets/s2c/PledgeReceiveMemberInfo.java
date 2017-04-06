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
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author -Wooden-
 */
public class PledgeReceiveMemberInfo extends GameServerPacket {
	private final ClanMember _member;

	public PledgeReceiveMemberInfo(ClanMember member) {
		_member = member;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_RECEIVE_MEMBER_INFO.writeId(body);

		body.writeD(_member.getPledgeType());
		body.writeS(_member.getName());
		body.writeS(_member.getTitle()); // title
		body.writeD(_member.getPowerGrade()); // power

		// clan or subpledge name
		if (_member.getPledgeType() != 0) {
			body.writeS((_member.getClan().getSubPledge(_member.getPledgeType())).getName());
		} else {
			body.writeS(_member.getClan().getName());
		}

		body.writeS(_member.getApprenticeOrSponsorName()); // name of this member's apprentice/sponsor
	}
}
