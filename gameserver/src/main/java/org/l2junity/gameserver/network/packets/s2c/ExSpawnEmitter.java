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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author KenM
 */
public class ExSpawnEmitter extends GameServerPacket {
	private final int _playerObjectId;
	private final int _npcObjectId;

	public ExSpawnEmitter(int playerObjectId, int npcObjectId) {
		_playerObjectId = playerObjectId;
		_npcObjectId = npcObjectId;
	}

	public ExSpawnEmitter(Player player, Npc npc) {
		this(player.getObjectId(), npc.getObjectId());
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SPAWN_EMITTER.writeId(body);

		body.writeD(_npcObjectId);
		body.writeD(_playerObjectId);
		body.writeD(0x00); // ?
	}
}