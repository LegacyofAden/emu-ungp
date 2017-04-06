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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.Clan.SubPledge;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author -Wooden-
 */
@Slf4j
public class PledgeReceiveSubPledgeCreated extends GameServerPacket {
	private final SubPledge _subPledge;
	private final Clan _clan;

	public PledgeReceiveSubPledgeCreated(SubPledge subPledge, Clan clan) {
		_subPledge = subPledge;
		_clan = clan;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_RECEIVE_SUB_PLEDGE_CREATED.writeId(body);

		body.writeD(0x01);
		body.writeD(_subPledge.getId());
		body.writeS(_subPledge.getName());
		body.writeS(getLeaderName());
	}

	private String getLeaderName() {
		int LeaderId = _subPledge.getLeaderId();
		if ((_subPledge.getId() == Clan.SUBUNIT_ACADEMY) || (LeaderId == 0)) {
			return "";
		} else if (_clan.getClanMember(LeaderId) == null) {
			log.warn("SubPledgeLeader: " + LeaderId + " is missing from clan: " + _clan.getName() + "[" + _clan.getId() + "]");
			return "";
		} else {
			return _clan.getClanMember(LeaderId).getName();
		}
	}
}
