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
import org.l2junity.gameserver.instancemanager.FortSiegeManager;
import org.l2junity.gameserver.model.FortSiegeSpawn;
import org.l2junity.gameserver.model.L2Spawn;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * TODO: Rewrite!!!!!!
 *
 * @author KenM
 */
public class ExShowFortressMapInfo extends GameServerPacket {
	private final Fort _fortress;

	public ExShowFortressMapInfo(Fort fortress) {
		_fortress = fortress;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_FORTRESS_MAP_INFO.writeId(body);

		body.writeD(_fortress.getResidenceId());
		body.writeD(_fortress.getSiege().isInProgress() ? 1 : 0); // fortress siege status
		body.writeD(_fortress.getFortSize()); // barracks count

		List<FortSiegeSpawn> commanders = FortSiegeManager.getInstance().getCommanderSpawnList(_fortress.getResidenceId());
		if ((commanders != null) && (commanders.size() != 0) && _fortress.getSiege().isInProgress()) {
			switch (commanders.size()) {
				case 3: {
					for (FortSiegeSpawn spawn : commanders) {
						if (isSpawned(spawn.getId())) {
							body.writeD(0);
						} else {
							body.writeD(1);
						}
					}
					break;
				}
				case 4: // TODO: change 4 to 5 once control room supported
				{
					int count = 0;
					for (FortSiegeSpawn spawn : commanders) {
						count++;
						if (count == 4) {
							body.writeD(1); // TODO: control room emulated
						}
						if (isSpawned(spawn.getId())) {
							body.writeD(0);
						} else {
							body.writeD(1);
						}
					}
					break;
				}
			}
		} else {
			for (int i = 0; i < _fortress.getFortSize(); i++) {
				body.writeD(0);
			}
		}
	}

	private boolean isSpawned(int npcId) {
		boolean ret = false;
		for (L2Spawn spawn : _fortress.getSiege().getCommanders()) {
			if (spawn.getId() == npcId) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
