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

/**
 * @author -Wooden-
 */
public final class PledgeShowMemberListUpdate extends GameServerPacket {
	private final int _pledgeType;
	private int _hasSponsor;
	private final String _name;
	private final int _level;
	private final int _classId;
	private final int _objectId;
	private final int _onlineStatus;
	private final int _race;
	private final int _sex;

	public PledgeShowMemberListUpdate(Player player) {
		this(player.getClan().getClanMember(player.getObjectId()));
	}

	public PledgeShowMemberListUpdate(ClanMember member) {
		_name = member.getName();
		_level = member.getLevel();
		_classId = member.getClassId();
		_objectId = member.getObjectId();
		_pledgeType = member.getPledgeType();
		_race = member.getRaceOrdinal();
		_sex = member.getSex() ? 1 : 0;
		_onlineStatus = member.getOnlineStatus();
		if (_pledgeType == Clan.SUBUNIT_ACADEMY) {
			_hasSponsor = member.getSponsor() != 0 ? 1 : 0;
		} else {
			_hasSponsor = 0;
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_SHOW_MEMBER_LIST_UPDATE.writeId(body);

		body.writeS(_name);
		body.writeD(_level);
		body.writeD(_classId);
		body.writeD(_sex);
		body.writeD(_race);
		if (_onlineStatus > 0) {
			body.writeD(_objectId);
			body.writeD(_pledgeType);
		} else {
			// when going offline send as 0
			body.writeD(0);
			body.writeD(0);
		}
		body.writeD(_hasSponsor);
		body.writeC(_onlineStatus);
	}
}