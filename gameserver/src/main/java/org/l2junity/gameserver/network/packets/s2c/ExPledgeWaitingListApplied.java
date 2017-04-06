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
import org.l2junity.gameserver.instancemanager.ClanEntryManager;
import org.l2junity.gameserver.model.clan.entry.PledgeApplicantInfo;
import org.l2junity.gameserver.model.clan.entry.PledgeRecruitInfo;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExPledgeWaitingListApplied extends GameServerPacket {
	private final PledgeApplicantInfo _pledgePlayerRecruitInfo;
	private final PledgeRecruitInfo _pledgeRecruitInfo;

	public ExPledgeWaitingListApplied(int clanId, int playerId) {
		_pledgePlayerRecruitInfo = ClanEntryManager.getInstance().getPlayerApplication(clanId, playerId);
		_pledgeRecruitInfo = ClanEntryManager.getInstance().getClanById(clanId);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PLEDGE_WAITING_LIST_APPLIED.writeId(body);

		body.writeD(_pledgeRecruitInfo.getClan().getId());
		body.writeS(_pledgeRecruitInfo.getClan().getName());
		body.writeS(_pledgeRecruitInfo.getClan().getLeaderName());
		body.writeD(_pledgeRecruitInfo.getClan().getLevel());
		body.writeD(_pledgeRecruitInfo.getClan().getMembersCount());
		body.writeD(_pledgeRecruitInfo.getKarma());
		body.writeS(_pledgeRecruitInfo.getInformation());
		body.writeS(_pledgePlayerRecruitInfo.getMessage());
	}
}
