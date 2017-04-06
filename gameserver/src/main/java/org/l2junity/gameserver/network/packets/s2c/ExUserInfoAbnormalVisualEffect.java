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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.AbnormalVisualEffect;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Set;

/**
 * @author Sdw
 */
public class ExUserInfoAbnormalVisualEffect extends GameServerPacket {
	private final Player _activeChar;

	public ExUserInfoAbnormalVisualEffect(Player cha) {
		_activeChar = cha;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_USER_INFO_ABNORMAL_VISUAL_EFFECT.writeId(body);

		body.writeD(_activeChar.getObjectId());
		body.writeD(_activeChar.getTransformationId());

		final Set<AbnormalVisualEffect> abnormalVisualEffects = _activeChar.getEffectList().getCurrentAbnormalVisualEffects();
		final boolean isInvisible = _activeChar.isInvisible();
		body.writeD(abnormalVisualEffects.size() + (isInvisible ? 1 : 0));
		for (AbnormalVisualEffect abnormalVisualEffect : abnormalVisualEffects) {
			body.writeH(abnormalVisualEffect.getClientId());
		}
		if (isInvisible) {
			body.writeH(AbnormalVisualEffect.STEALTH.getClientId());
		}
	}
}