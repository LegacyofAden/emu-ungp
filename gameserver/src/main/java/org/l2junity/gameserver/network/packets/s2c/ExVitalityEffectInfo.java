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
import org.l2junity.core.configs.RatesConfig;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExVitalityEffectInfo extends GameServerPacket {
	private final int _vitalityBonus;
	private final int _vitalityItemsRemaining;
	private final int _points;

	public ExVitalityEffectInfo(Player cha) {
		_points = cha.getVitalityPoints();
		_vitalityBonus = (int) cha.getStat().getVitalityExpBonus() * 100;
		_vitalityItemsRemaining = cha.getVitalityItemsUsed() - RatesConfig.VITALITY_MAX_ITEMS_ALLOWED;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_VITALITY_EFFECT_INFO.writeId(body);

		body.writeD(_points);
		body.writeD(_vitalityBonus); // Vitality Bonus
		body.writeH(0x00); // Vitality additional bonus in %
		body.writeH(_vitalityItemsRemaining); // How much vitality items remaining for use
		body.writeH(RatesConfig.VITALITY_MAX_ITEMS_ALLOWED); // Max number of items for use
	}
}