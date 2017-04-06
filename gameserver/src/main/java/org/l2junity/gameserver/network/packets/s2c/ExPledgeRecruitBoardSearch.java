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
import org.l2junity.gameserver.model.clan.entry.PledgeRecruitInfo;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author Sdw
 */
public class ExPledgeRecruitBoardSearch extends GameServerPacket {
	final List<PledgeRecruitInfo> _clanList;
	final private int _currentPage;
	final private int _totalNumberOfPage;
	final private int _clanOnCurrentPage;
	final private int _startIndex;
	final private int _endIndex;

	final static int CLAN_PER_PAGE = 12;

	public ExPledgeRecruitBoardSearch(List<PledgeRecruitInfo> clanList, int currentPage) {
		_clanList = clanList;
		_currentPage = currentPage;
		_totalNumberOfPage = (int) Math.ceil((double) _clanList.size() / CLAN_PER_PAGE);
		_startIndex = (_currentPage - 1) * CLAN_PER_PAGE;
		_endIndex = (_startIndex + CLAN_PER_PAGE) > _clanList.size() ? _clanList.size() : _startIndex + CLAN_PER_PAGE;
		_clanOnCurrentPage = _endIndex - _startIndex;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PLEDGE_RECRUIT_BOARD_SEARCH.writeId(body);

		body.writeD(_currentPage);
		body.writeD(_totalNumberOfPage);
		body.writeD(_clanOnCurrentPage);

		for (int i = _startIndex; i < _endIndex; i++) {
			body.writeD(_clanList.get(i).getClanId());
			body.writeD(_clanList.get(i).getClan().getAllyId());
		}
		for (int i = _startIndex; i < _endIndex; i++) {
			final Clan clan = _clanList.get(i).getClan();
			body.writeD(clan.getAllyCrestId());
			body.writeD(clan.getCrestId());
			body.writeS(clan.getName());
			body.writeS(clan.getLeaderName());
			body.writeD(clan.getLevel());
			body.writeD(clan.getMembersCount());
			body.writeD(_clanList.get(i).getKarma());
			body.writeS(_clanList.get(i).getInformation());
			body.writeD(_clanList.get(i).getApplicationType());
			body.writeD(_clanList.get(i).getRecruitType());
		}
	}
}