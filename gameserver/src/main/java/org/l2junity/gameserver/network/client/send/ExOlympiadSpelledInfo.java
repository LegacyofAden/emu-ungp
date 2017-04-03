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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.BuffInfo;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author godson
 */
public class ExOlympiadSpelledInfo implements IClientOutgoingPacket {
	private final int _playerId;
	private final List<BuffInfo> _effects = new ArrayList<>();

	public ExOlympiadSpelledInfo(Player player) {
		_playerId = player.getObjectId();
	}

	public void addSkill(BuffInfo info) {
		_effects.add(info);
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_OLYMPIAD_SPELLED_INFO.writeId(packet);

		packet.writeD(_playerId);
		packet.writeD(_effects.size());
		for (BuffInfo info : _effects) {
			if ((info != null) && info.isInUse()) {
				packet.writeD(info.getSkill().getDisplayId());
				packet.writeH(info.getSkill().getDisplayLevel());
				packet.writeH(0x00); // Sub level
				packet.writeD(info.getSkill().getAbnormalType().getClientId());
				writeOptionalD(packet, info.getSkill().isAura() ? -1 : info.getTime());
			}
		}
		return true;
	}
}
