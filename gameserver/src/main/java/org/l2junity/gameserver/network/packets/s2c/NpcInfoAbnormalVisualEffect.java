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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.skills.AbnormalVisualEffect;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Set;

/**
 * @author Sdw
 */
public class NpcInfoAbnormalVisualEffect extends GameServerPacket {
	private final Npc _npc;

	public NpcInfoAbnormalVisualEffect(Npc npc) {
		_npc = npc;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.NPC_INFO_ABNORMAL_VISUAL_EFFECT.writeId(body);

		body.writeD(_npc.getObjectId());
		body.writeD(_npc.getTransformationDisplayId());

		final Set<AbnormalVisualEffect> abnormalVisualEffects = _npc.getEffectList().getCurrentAbnormalVisualEffects();
		body.writeD(abnormalVisualEffects.size());
		for (AbnormalVisualEffect abnormalVisualEffect : abnormalVisualEffects) {
			body.writeH(abnormalVisualEffect.getClientId());
		}
	}
}