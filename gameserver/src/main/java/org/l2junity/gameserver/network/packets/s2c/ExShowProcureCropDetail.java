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
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.CastleManorManager;
import org.l2junity.gameserver.model.CropProcure;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author l3x
 */
public class ExShowProcureCropDetail extends GameServerPacket {
	private final int _cropId;
	private final Map<Integer, CropProcure> _castleCrops = new HashMap<>();

	public ExShowProcureCropDetail(int cropId) {
		_cropId = cropId;

		for (Castle c : CastleManager.getInstance().getCastles()) {
			final CropProcure cropItem = CastleManorManager.getInstance().getCropProcure(c.getResidenceId(), cropId, false);
			if ((cropItem != null) && (cropItem.getAmount() > 0)) {
				_castleCrops.put(c.getResidenceId(), cropItem);
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_PROCURE_CROP_DETAIL.writeId(body);

		body.writeD(_cropId); // crop id
		body.writeD(_castleCrops.size()); // size

		for (Map.Entry<Integer, CropProcure> entry : _castleCrops.entrySet()) {
			final CropProcure crop = entry.getValue();
			body.writeD(entry.getKey()); // manor name
			body.writeQ(crop.getAmount()); // buy residual
			body.writeQ(crop.getPrice()); // buy price
			body.writeC(crop.getReward()); // reward type
		}
	}
}
