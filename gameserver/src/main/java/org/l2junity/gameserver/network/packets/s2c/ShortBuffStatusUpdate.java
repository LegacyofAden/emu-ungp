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

public class ShortBuffStatusUpdate extends GameServerPacket {
	public static final ShortBuffStatusUpdate RESET_SHORT_BUFF = new ShortBuffStatusUpdate(0, 0, 0, 0);

	private final int _skillId;
	private final int _skillLvl;
	private final int _skillSubLvl;
	private final int _duration;

	public ShortBuffStatusUpdate(int skillId, int skillLvl, int skillSubLvl, int duration) {
		_skillId = skillId;
		_skillLvl = skillLvl;
		_skillSubLvl = skillSubLvl;
		_duration = duration;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SHORT_BUFF_STATUS_UPDATE.writeId(body);

		body.writeD(_skillId);
		body.writeH(_skillLvl);
		body.writeH(_skillSubLvl);
		body.writeD(_duration);
	}
}