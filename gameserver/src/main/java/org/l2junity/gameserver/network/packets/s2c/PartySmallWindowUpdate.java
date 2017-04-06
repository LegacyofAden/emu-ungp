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
import org.l2junity.gameserver.enums.PartySmallWindowUpdateType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class PartySmallWindowUpdate extends AbstractMaskPacket<PartySmallWindowUpdateType> {
	private final Player _member;
	private int _flags = 0;

	public PartySmallWindowUpdate(Player member, boolean addAllFlags) {
		_member = member;
		if (addAllFlags) {
			for (PartySmallWindowUpdateType type : PartySmallWindowUpdateType.values()) {
				addComponentType(type);
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PARTY_SMALL_WINDOW_UPDATE.writeId(body);

		body.writeD(_member.getObjectId());
		body.writeH(_flags);
		if (containsMask(PartySmallWindowUpdateType.CURRENT_CP)) {
			body.writeD((int) _member.getCurrentCp()); // c4
		}
		if (containsMask(PartySmallWindowUpdateType.MAX_CP)) {
			body.writeD(_member.getMaxCp()); // c4
		}
		if (containsMask(PartySmallWindowUpdateType.CURRENT_HP)) {
			body.writeD((int) _member.getCurrentHp());
		}
		if (containsMask(PartySmallWindowUpdateType.MAX_HP)) {
			body.writeD(_member.getMaxHp());
		}
		if (containsMask(PartySmallWindowUpdateType.CURRENT_MP)) {
			body.writeD((int) _member.getCurrentMp());
		}
		if (containsMask(PartySmallWindowUpdateType.MAX_MP)) {
			body.writeD(_member.getMaxMp());
		}
		if (containsMask(PartySmallWindowUpdateType.LEVEL)) {
			body.writeC(_member.getLevel());
		}
		if (containsMask(PartySmallWindowUpdateType.CLASS_ID)) {
			body.writeH(_member.getClassId().getId());
		}
		if (containsMask(PartySmallWindowUpdateType.PARTY_SUBSTITUTE)) {
			body.writeC(0x00);
		}
		if (containsMask(PartySmallWindowUpdateType.VITALITY_POINTS)) {
			body.writeD(_member.getVitalityPoints());
		}
	}

	@Override
	protected void addMask(int mask) {
		_flags |= mask;
	}

	@Override
	public boolean containsMask(PartySmallWindowUpdateType component) {
		return containsMask(_flags, component);
	}

	@Override
	protected byte[] getMasks() {
		return new byte[0];
	}
}
