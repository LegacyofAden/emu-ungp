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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author l3x
 */
public class ExShowCropSetting extends GameServerPacket {
	private final int _manorId;
	private final Set<L2Seed> _seeds;
	private final Map<Integer, CropProcure> _current = new HashMap<>();
	private final Map<Integer, CropProcure> _next = new HashMap<>();

	public ExShowCropSetting(int manorId) {
		final CastleManorManager manor = CastleManorManager.getInstance();
		_manorId = manorId;
		_seeds = manor.getSeedsForCastle(_manorId);
		for (L2Seed s : _seeds) {
			// Current period
			CropProcure cp = manor.getCropProcure(manorId, s.getCropId(), false);
			if (cp != null) {
				_current.put(s.getCropId(), cp);
			}
			// Next period
			cp = manor.getCropProcure(manorId, s.getCropId(), true);
			if (cp != null) {
				_next.put(s.getCropId(), cp);
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_CROP_SETTING.writeId(body);

		body.writeD(_manorId); // manor id
		body.writeD(_seeds.size()); // size

		for (L2Seed s : _seeds) {
			body.writeD(s.getCropId()); // crop id
			body.writeD(s.getLevel()); // seed level
			body.writeC(1);
			body.writeD(s.getReward(1)); // reward 1 id
			body.writeC(1);
			body.writeD(s.getReward(2)); // reward 2 id
			body.writeD(s.getCropLimit()); // next sale limit
			body.writeD(0); // ???
			body.writeD(s.getCropMinPrice()); // min crop price
			body.writeD((int) s.getCropMaxPrice()); // max crop price
			// Current period
			if (_current.containsKey(s.getCropId())) {
				final CropProcure cp = _current.get(s.getCropId());
				body.writeQ(cp.getStartAmount()); // buy
				body.writeQ(cp.getPrice()); // price
				body.writeC(cp.getReward()); // reward
			} else {
				body.writeQ(0);
				body.writeQ(0);
				body.writeC(0);
			}
			// Next period
			if (_next.containsKey(s.getCropId())) {
				final CropProcure cp = _next.get(s.getCropId());
				body.writeQ(cp.getStartAmount()); // buy
				body.writeQ(cp.getPrice()); // price
				body.writeC(cp.getReward()); // reward
			} else {
				body.writeQ(0);
				body.writeQ(0);
				body.writeC(0);
			}
		}
		_next.clear();
		_current.clear();
	}
}