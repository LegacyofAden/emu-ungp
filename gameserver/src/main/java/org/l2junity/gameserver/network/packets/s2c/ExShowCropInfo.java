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
import org.l2junity.gameserver.model.CropProcure;
import org.l2junity.gameserver.model.L2Seed;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author l3x
 */
public class ExShowCropInfo extends GameServerPacket {
	private final List<CropProcure> _crops;
	private final int _manorId;
	private final boolean _hideButtons;

	public ExShowCropInfo(int manorId, boolean nextPeriod, boolean hideButtons) {
		_manorId = manorId;
		_hideButtons = hideButtons;

		final CastleManorManager manor = CastleManorManager.getInstance();
		_crops = (nextPeriod && !manor.isManorApproved()) ? null : manor.getCropProcure(manorId, nextPeriod);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_CROP_INFO.writeId(body);

		body.writeC(_hideButtons ? 0x01 : 0x00); // Hide "Crop Sales" button
		body.writeD(_manorId); // Manor ID
		body.writeD(0x00);
		if (_crops != null) {
			body.writeD(_crops.size());
			for (CropProcure crop : _crops) {
				body.writeD(crop.getId()); // Crop id
				body.writeQ(crop.getAmount()); // Buy residual
				body.writeQ(crop.getStartAmount()); // Buy
				body.writeQ(crop.getPrice()); // Buy price
				body.writeC(crop.getReward()); // Reward
				final L2Seed seed = CastleManorManager.getInstance().getSeedByCrop(crop.getId());
				if (seed == null) {
					body.writeD(0); // Seed level
					body.writeC(0x01); // Reward 1
					body.writeD(0); // Reward 1 - item id
					body.writeC(0x01); // Reward 2
					body.writeD(0); // Reward 2 - item id
				} else {
					body.writeD(seed.getLevel()); // Seed level
					body.writeC(0x01); // Reward 1
					body.writeD(seed.getReward(1)); // Reward 1 - item id
					body.writeC(0x01); // Reward 2
					body.writeD(seed.getReward(2)); // Reward 2 - item id
				}
			}
		}
	}
}