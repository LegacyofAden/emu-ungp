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
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.gameserver.data.sql.impl.CrestTable;
import org.l2junity.gameserver.model.Crest;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class AllyCrest extends GameServerPacket {
	private final int _crestId;
	private final byte[] _data;

	public AllyCrest(int crestId) {
		_crestId = crestId;
		final Crest crest = CrestTable.getInstance().getCrest(crestId);
		_data = crest != null ? crest.getData() : null;
	}

	public AllyCrest(int crestId, byte[] data) {
		_crestId = crestId;
		_data = data;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ALLIANCE_CREST.writeId(body);

		body.writeD(GameserverConfig.SERVER_ID);
		body.writeD(_crestId);
		if (_data != null) {
			body.writeD(_data.length);
			body.writeB(_data);
		} else {
			body.writeD(0);
		}
	}
}
