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
import org.l2junity.gameserver.enums.PcCafeConsumeType;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author KenM
 * @author UnAfraid
 */
public class ExPCCafePointInfo extends GameServerPacket {
	private final long _points;
	private final long _mAddPoint;
	private final int _mPeriodType;
	private final int _remainTime;
	private final PcCafeConsumeType _pointType;
	private final int _time;

	public ExPCCafePointInfo(long points, long pointsToAdd, PcCafeConsumeType type) {
		_points = points;
		_mAddPoint = pointsToAdd;
		_mPeriodType = 1;
		_remainTime = 0;
		_pointType = type;
		_time = 0;

	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PCCAFE_POINT_INFO.writeId(body);

		body.writeD((int) _points); // Total Points
		body.writeD((int) _mAddPoint); // Earned Points
		body.writeC(_mPeriodType); // period(0=don't show window,1=acquisition,2=use points)
		body.writeD(_remainTime); // period hours left
		body.writeC(_pointType.ordinal()); // points inc display color(0=yellow, 1=cyan-blue, 2=red, all other black)
		body.writeD(_time); // value is in seconds * 3
	}
}