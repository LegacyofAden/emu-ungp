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
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class PartySmallWindowAdd extends GameServerPacket {
	private final Player _member;
	private final Party _party;

	public PartySmallWindowAdd(Player member, Party party) {
		_member = member;
		_party = party;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PARTY_SMALL_WINDOW_ADD.writeId(body);

		body.writeD(_party.getLeaderObjectId()); // c3
		body.writeD(_party.getDistributionType().getId()); // c3
		body.writeD(_member.getObjectId());
		body.writeS(_member.getName());

		body.writeD((int) _member.getCurrentCp()); // c4
		body.writeD(_member.getMaxCp()); // c4
		body.writeD((int) _member.getCurrentHp());
		body.writeD(_member.getMaxHp());
		body.writeD((int) _member.getCurrentMp());
		body.writeD(_member.getMaxMp());
		body.writeD(_member.getVitalityPoints());
		body.writeC(_member.getLevel());
		body.writeH(_member.getClassId().getId());
		body.writeC(0x00);
		body.writeH(_member.getRace().ordinal());
	}
}