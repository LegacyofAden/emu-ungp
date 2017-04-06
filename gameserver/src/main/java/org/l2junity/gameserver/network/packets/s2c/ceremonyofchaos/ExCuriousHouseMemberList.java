/*
 * Copyright (C) 2004-2015 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.network.packets.s2c.ceremonyofchaos;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosMember;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

/**
 * @author UnAfraid
 */
public class ExCuriousHouseMemberList extends GameServerPacket {
	private final int _id;
	private final int _maxPlayers;
	private final Collection<CeremonyOfChaosMember> _players;

	public ExCuriousHouseMemberList(int id, int maxPlayers, Collection<CeremonyOfChaosMember> players) {
		_id = id;
		_maxPlayers = maxPlayers;
		_players = players;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_CURIOUS_HOUSE_MEMBER_LIST.writeId(body);

		body.writeD(_id);
		body.writeD(_maxPlayers);
		body.writeD(_players.size());
		for (CeremonyOfChaosMember cocPlayer : _players) {
			final Player player = cocPlayer.getPlayer();
			body.writeD(cocPlayer.getObjectId());
			body.writeD(cocPlayer.getPosition());
			if (player != null) {
				body.writeD(player.getMaxHp());
				body.writeD(player.getMaxCp());
				body.writeD((int) player.getCurrentHp());
				body.writeD((int) player.getCurrentCp());
			} else {
				body.writeD(0x00);
				body.writeD(0x00);
				body.writeD(0x00);
				body.writeD(0x00);
			}
		}
	}
}