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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.Henna;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Zoey76
 */
public class HennaRemoveList extends GameServerPacket {
	private final Player _player;

	public HennaRemoveList(Player player) {
		_player = player;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.HENNA_UNEQUIP_LIST.writeId(body);

		body.writeQ(_player.getAdena());
		body.writeD(0x03); // seems to be max size
		body.writeD(3 - _player.getHennaEmptySlots());

		for (Henna henna : _player.getHennaList()) {
			if (henna != null) {
				body.writeD(henna.getDyeId());
				body.writeD(henna.getDyeItemId());
				body.writeQ(henna.getCancelCount());
				body.writeQ(henna.getCancelFee());
				body.writeD(0x00);
				body.writeD(0x00);
			}
		}
	}
}