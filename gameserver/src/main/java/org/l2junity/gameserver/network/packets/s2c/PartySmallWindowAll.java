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
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class PartySmallWindowAll extends GameServerPacket {
	private final Party _party;
	private final Player _exclude;

	public PartySmallWindowAll(Player exclude, Party party) {
		_exclude = exclude;
		_party = party;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PARTY_SMALL_WINDOW_ALL.writeId(body);

		body.writeD(_party.getLeaderObjectId());
		body.writeC(_party.getDistributionType().getId());
		body.writeC(_party.getMemberCount() - 1);

		for (Player member : _party.getMembers()) {
			if ((member != null) && (member != _exclude)) {
				body.writeD(member.getObjectId());
				body.writeS(member.getName());

				body.writeD((int) member.getCurrentCp()); // c4
				body.writeD(member.getMaxCp()); // c4

				body.writeD((int) member.getCurrentHp());
				body.writeD(member.getMaxHp());
				body.writeD((int) member.getCurrentMp());
				body.writeD(member.getMaxMp());
				body.writeD(member.getVitalityPoints());
				body.writeC(member.getLevel());
				body.writeH(member.getClassId().getId());
				body.writeC(0x01); // Unk
				body.writeH(member.getRace().ordinal());
				final Summon pet = member.getPet();
				body.writeD(member.getServitors().size() + (pet != null ? 1 : 0)); // Summon size, one only atm
				if (pet != null) {
					body.writeD(pet.getObjectId());
					body.writeD(pet.getId() + 1000000);
					body.writeC(pet.getSummonType());
					body.writeS(pet.getName());
					body.writeD((int) pet.getCurrentHp());
					body.writeD(pet.getMaxHp());
					body.writeD((int) pet.getCurrentMp());
					body.writeD(pet.getMaxMp());
					body.writeC(pet.getLevel());
				}
				member.getServitors().values().forEach(s ->
				{
					body.writeD(s.getObjectId());
					body.writeD(s.getId() + 1000000);
					body.writeC(s.getSummonType());
					body.writeS(s.getName());
					body.writeD((int) s.getCurrentHp());
					body.writeD(s.getMaxHp());
					body.writeD((int) s.getCurrentMp());
					body.writeD(s.getMaxMp());
					body.writeC(s.getLevel());
				});
			}
		}
	}
}
