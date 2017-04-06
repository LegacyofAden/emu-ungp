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
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.Clan.SubPledge;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

public class PledgeShowMemberListAll extends GameServerPacket {
	private final Clan _clan;
	private final SubPledge _pledge;
	private final String _name;
	private final String _leaderName;
	private final Collection<ClanMember> _members;
	private int _pledgeType;

	private PledgeShowMemberListAll(Clan clan, SubPledge pledge) {
		_clan = clan;
		_pledge = pledge;
		_leaderName = pledge == null ? clan.getLeaderName() : CharNameTable.getInstance().getNameById(pledge.getLeaderId());
		_name = pledge == null ? clan.getName() : pledge.getName();
		_members = _clan.getMembers();
		// TODO: _pledgeType
	}

	public static void sendAllTo(Player player) {
		final Clan clan = player.getClan();
		if (clan != null) {
			player.sendPacket(new PledgeShowMemberListAll(clan, null));
			for (SubPledge subPledge : clan.getAllSubPledges()) {
				player.sendPacket(new PledgeShowMemberListAll(clan, subPledge));
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		final int pledgeId = (_pledge == null ? 0x00 : _pledge.getId());
		GameServerPacketType.PLEDGE_SHOW_MEMBER_LIST_ALL.writeId(body);

		body.writeD(_pledge == null ? 0 : 1);
		body.writeD(_clan.getId());
		body.writeD(GameserverConfig.SERVER_ID);
		body.writeD(pledgeId);
		body.writeS(_name);
		body.writeS(_leaderName);

		body.writeD(_clan.getCrestId()); // crest id .. is used again
		body.writeD(_clan.getLevel());
		body.writeD(_clan.getCastleId());
		body.writeD(_clan.getHideoutId());
		body.writeD(_clan.getFortId());
		body.writeD(0x00);
		body.writeD(_clan.getRank());
		body.writeD(_clan.getReputationScore());
		body.writeD(0x00); // 0
		body.writeD(0x00); // 0
		body.writeD(_clan.getAllyId());
		body.writeS(_clan.getAllyName());
		body.writeD(_clan.getAllyCrestId());
		body.writeD(_clan.isAtWar() ? 1 : 0);// new c3
		body.writeD(0x00); // Territory castle ID
		body.writeD(_clan.getSubPledgeMembersCount(_pledgeType));

		for (ClanMember m : _members) {
			if (m.getPledgeType() != _pledgeType) {
				continue;
			}
			body.writeS(m.getName());
			body.writeD(m.getLevel());
			body.writeD(m.getClassId());
			final Player player = m.getPlayerInstance();
			if (player != null) {
				body.writeD(player.getAppearance().getSex() ? 1 : 0); // no visible effect
				body.writeD(player.getRace().ordinal());// body.writeD(1);
			} else {
				body.writeD(0x01); // no visible effect
				body.writeD(0x01); // body.writeD(1);
			}
			body.writeD(m.isOnline() ? m.getObjectId() : 0); // objectId = online 0 = offline
			body.writeD(m.getSponsor() != 0 ? 1 : 0);
			body.writeC(m.getOnlineStatus());
		}
	}
}