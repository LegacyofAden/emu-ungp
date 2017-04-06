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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.data.sql.impl.CrestTable;
import org.l2junity.gameserver.model.Crest;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExPledgeEmblem;
import org.l2junity.network.PacketReader;

/**
 * @author -Wooden-, Sdw
 */
public final class RequestExPledgeCrestLarge extends GameClientPacket {
	private int _crestId;
	private int _clanId;

	@Override
	public void readImpl() {
		_crestId = readD();
		_clanId = readD();
	}

	@Override
	public void runImpl() {
		final Crest crest = CrestTable.getInstance().getCrest(_crestId);
		final byte[] data = crest != null ? crest.getData() : null;
		if (data != null) {
			for (int i = 0; i <= 4; i++) {
				if (i < 4) {
					final byte[] fullChunk = new byte[14336];
					System.arraycopy(data, (14336 * i), fullChunk, 0, 14336);
					getClient().sendPacket(new ExPledgeEmblem(_crestId, fullChunk, _clanId, i));
				} else {
					final byte[] lastChunk = new byte[8320];
					System.arraycopy(data, (14336 * i), lastChunk, 0, 8320);
					getClient().sendPacket(new ExPledgeEmblem(_crestId, lastChunk, _clanId, i));
				}
			}
		}
	}
}