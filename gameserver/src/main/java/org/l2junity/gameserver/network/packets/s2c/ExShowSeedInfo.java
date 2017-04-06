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
import org.l2junity.gameserver.model.SeedProduction;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author l3x
 */
public class ExShowSeedInfo extends GameServerPacket {
	private final List<SeedProduction> _seeds;
	private final int _manorId;
	private final boolean _hideButtons;

	public ExShowSeedInfo(int manorId, boolean nextPeriod, boolean hideButtons) {
		_manorId = manorId;
		_hideButtons = hideButtons;

		final CastleManorManager manor = CastleManorManager.getInstance();
		_seeds = (nextPeriod && !manor.isManorApproved()) ? null : manor.getSeedProduction(manorId, nextPeriod);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_SEED_INFO.writeId(body);

		body.writeC(_hideButtons ? 0x01 : 0x00); // Hide "Seed Purchase" button
		body.writeD(_manorId); // Manor ID
		body.writeD(0x00); // Unknown
		if (_seeds == null) {
			body.writeD(0);
			return;
		}

		body.writeD(_seeds.size());
		for (SeedProduction seed : _seeds) {
			body.writeD(seed.getId()); // Seed id
			body.writeQ(seed.getAmount()); // Left to buy
			body.writeQ(seed.getStartAmount()); // Started amount
			body.writeQ(seed.getPrice()); // Sell Price
			final L2Seed s = CastleManorManager.getInstance().getSeed(seed.getId());
			if (s == null) {
				body.writeD(0); // Seed level
				body.writeC(0x01); // Reward 1
				body.writeD(0); // Reward 1 - item id
				body.writeC(0x01); // Reward 2
				body.writeD(0); // Reward 2 - item id
			} else {
				body.writeD(s.getLevel()); // Seed level
				body.writeC(0x01); // Reward 1
				body.writeD(s.getReward(1)); // Reward 1 - item id
				body.writeC(0x01); // Reward 2
				body.writeD(s.getReward(2)); // Reward 2 - item id
			}
		}
	}
}