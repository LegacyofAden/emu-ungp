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
import org.l2junity.gameserver.instancemanager.CastleManorManager;
import org.l2junity.gameserver.model.L2Seed;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author l3x
 */
public final class ExShowManorDefaultInfo extends GameServerPacket {
	private final List<L2Seed> _crops;
	private final boolean _hideButtons;

	public ExShowManorDefaultInfo(boolean hideButtons) {
		_crops = CastleManorManager.getInstance().getCrops();
		_hideButtons = hideButtons;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_MANOR_DEFAULT_INFO.writeId(body);

		body.writeC(_hideButtons ? 0x01 : 0x00); // Hide "Seed Purchase" and "Crop Sales" buttons
		body.writeD(_crops.size());
		for (L2Seed crop : _crops) {
			body.writeD(crop.getCropId()); // crop Id
			body.writeD(crop.getLevel()); // level
			body.writeD((int) crop.getSeedReferencePrice()); // seed price
			body.writeD((int) crop.getCropReferencePrice()); // crop price
			body.writeC(1); // Reward 1 type
			body.writeD(crop.getReward(1)); // Reward 1 itemId
			body.writeC(1); // Reward 2 type
			body.writeD(crop.getReward(2)); // Reward 2 itemId
		}
	}
}