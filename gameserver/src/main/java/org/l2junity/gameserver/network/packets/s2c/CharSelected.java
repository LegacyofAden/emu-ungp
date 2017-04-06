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
import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.GameServerPacket;

public class CharSelected extends GameServerPacket {
	private final Player activePlayer;

	public CharSelected(Player cha) {
		activePlayer = cha;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CHARACTER_SELECTED.writeId(body);

		body.writeS(activePlayer.getName());
		body.writeD(activePlayer.getObjectId());
		body.writeS(activePlayer.getTitle());
		body.writeD(activePlayer.getClient().getSessionInfo().getPlayKey());
		body.writeD(activePlayer.getClanId());
		body.writeD(0x00); // ??
		body.writeD(activePlayer.getAppearance().getSex() ? 1 : 0);
		body.writeD(activePlayer.getRace().ordinal());
		body.writeD(activePlayer.getClassId().getId());
		body.writeD(0x01); // active ??
		body.writeD((int) activePlayer.getX());
		body.writeD((int) activePlayer.getY());
		body.writeD((int) activePlayer.getZ());
		body.writeF(activePlayer.getCurrentHp());
		body.writeF(activePlayer.getCurrentMp());
		body.writeQ(activePlayer.getSp());
		body.writeQ(activePlayer.getExp());
		body.writeD(activePlayer.getLevel());
		body.writeD(activePlayer.getReputation());
		body.writeD(activePlayer.getPkKills());
		body.writeD(GameTimeManager.getInstance().getGameTimeInMinutesOfDay());
		body.writeD(0x00);
		body.writeD(activePlayer.getClassId().getId());

		body.writeB(new byte[16]);

		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);

		body.writeD(0x00);

		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);

		body.writeB(new byte[28]);
		body.writeD(0x00);
	}
}
