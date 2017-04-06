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
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.GameServerPacket;

/**
 * @author KenM
 */
public final class ExPartyPetWindowAdd extends GameServerPacket {
	private final Summon _summon;

	public ExPartyPetWindowAdd(Summon summon) {
		_summon = summon;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PARTY_PET_WINDOW_ADD.writeId(body);

		body.writeD(_summon.getObjectId());
		body.writeD(_summon.getTemplate().getDisplayId() + 1000000);
		body.writeC(_summon.getSummonType());
		body.writeD(_summon.getOwner().getObjectId());
		body.writeD((int) _summon.getCurrentHp());
		body.writeD(_summon.getMaxHp());
		body.writeD((int) _summon.getCurrentMp());
		body.writeD(_summon.getMaxMp());
	}
}