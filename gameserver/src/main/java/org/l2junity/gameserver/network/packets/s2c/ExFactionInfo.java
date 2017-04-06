/*
 * Copyright (C) 2004-2016 L2J Unity
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
import org.l2junity.gameserver.data.xml.impl.FactionData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.FactionHolder;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExFactionInfo extends GameServerPacket {
	private final Player _player;
	private final boolean _open;

	public ExFactionInfo(Player player, boolean open) {
		_player = player;
		_open = open;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_FACTION_INFO.writeId(body);

		body.writeD(_player.getObjectId());
		body.writeC(_open ? 0x01 : 0x00);
		body.writeD(_player.getFactionsPoints().size());
		_player.getFactionsPoints().forEach((key, value) -> {
			final FactionHolder holder = FactionData.getInstance().getFaction(key);
			if (holder != null) {
				body.writeC(holder.getId());
				body.writeH(holder.getLevel(value));
				body.writeE(holder.getProgressToNextLevel(value));
			}
		});
	}
}
