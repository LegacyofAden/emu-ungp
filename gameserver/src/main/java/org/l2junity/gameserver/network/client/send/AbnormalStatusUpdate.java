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

import org.l2junity.gameserver.model.skills.BuffInfo;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.ArrayList;
import java.util.List;

public class AbnormalStatusUpdate implements IClientOutgoingPacket {
	private final List<BuffInfo> _effects = new ArrayList<>();

	public void addSkill(BuffInfo info) {
		if (!info.getSkill().isHealingPotionSkill()) {
			_effects.add(info);
		}
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.ABNORMAL_STATUS_UPDATE.writeId(packet);

		packet.writeH(_effects.size());
		for (BuffInfo info : _effects) {
			if ((info != null) && info.isInUse()) {
				packet.writeD(info.getSkill().getDisplayId());
				packet.writeH(info.getSkill().getDisplayLevel());
				packet.writeH(info.getSkill().getSubLevel());
				packet.writeD(info.getSkill().getAbnormalType().getClientId());
				writeOptionalD(packet, info.getSkill().isAura() ? -1 : info.getTime());
			}
		}
		return true;
	}
}
