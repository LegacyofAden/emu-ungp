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
import org.l2junity.gameserver.model.itemcontainer.PcInventory;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author l3x
 */
public final class ExShowSellCropList extends GameServerPacket {
	private final int _manorId;
	private final Map<Integer, ItemInstance> _cropsItems = new HashMap<>();
	private final Map<Integer, CropProcure> _castleCrops = new HashMap<>();

	public ExShowSellCropList(PcInventory inventory, int manorId) {
		_manorId = manorId;
		for (int cropId : CastleManorManager.getInstance().getCropIds()) {
			final ItemInstance item = inventory.getItemByItemId(cropId);
			if (item != null) {
				_cropsItems.put(cropId, item);
			}
		}

		for (CropProcure crop : CastleManorManager.getInstance().getCropProcure(_manorId, false)) {
			if (_cropsItems.containsKey(crop.getId()) && (crop.getAmount() > 0)) {
				_castleCrops.put(crop.getId(), crop);
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_SELL_CROP_LIST.writeId(body);

		body.writeD(_manorId); // manor id
		body.writeD(_cropsItems.size()); // size
		for (ItemInstance item : _cropsItems.values()) {
			final L2Seed seed = CastleManorManager.getInstance().getSeedByCrop(item.getId());
			body.writeD(item.getObjectId()); // Object id
			body.writeD(item.getId()); // crop id
			body.writeD(seed.getLevel()); // seed level
			body.writeC(0x01);
			body.writeD(seed.getReward(1)); // reward 1 id
			body.writeC(0x01);
			body.writeD(seed.getReward(2)); // reward 2 id
			if (_castleCrops.containsKey(item.getId())) {
				final CropProcure crop = _castleCrops.get(item.getId());
				body.writeD(_manorId); // manor
				body.writeQ(crop.getAmount()); // buy residual
				body.writeQ(crop.getPrice()); // buy price
				body.writeC(crop.getReward()); // reward
			} else {
				body.writeD(0xFFFFFFFF); // manor
				body.writeQ(0x00); // buy residual
				body.writeQ(0x00); // buy price
				body.writeC(0x00); // reward
			}
			body.writeQ(item.getCount()); // my crops
		}
	}
}