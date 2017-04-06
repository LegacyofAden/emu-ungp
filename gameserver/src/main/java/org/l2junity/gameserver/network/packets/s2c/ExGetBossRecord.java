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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Map;
import java.util.Map.Entry;

/**
 * @author KenM
 */
public class ExGetBossRecord extends GameServerPacket {
	private final Map<Integer, Integer> _bossRecordInfo;
	private final int _ranking;
	private final int _totalPoints;

	public ExGetBossRecord(int ranking, int totalScore, Map<Integer, Integer> list) {
		_ranking = ranking;
		_totalPoints = totalScore;
		_bossRecordInfo = list;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_GET_BOSS_RECORD.writeId(body);

		body.writeD(_ranking);
		body.writeD(_totalPoints);
		if (_bossRecordInfo == null) {
			body.writeD(0x00);
			body.writeD(0x00);
			body.writeD(0x00);
			body.writeD(0x00);
		} else {
			body.writeD(_bossRecordInfo.size()); // list size
			for (Entry<Integer, Integer> entry : _bossRecordInfo.entrySet()) {
				body.writeD(entry.getKey());
				body.writeD(entry.getValue());
				body.writeD(0x00); // ??
			}
		}
	}
}