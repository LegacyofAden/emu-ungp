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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public class CharSelected implements IClientOutgoingPacket {
	private final Player activePlayer;

	public CharSelected(Player cha) {
		activePlayer = cha;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.CHARACTER_SELECTED.writeId(packet);

		packet.writeS(activePlayer.getName());
		packet.writeD(activePlayer.getObjectId());
		packet.writeS(activePlayer.getTitle());
		packet.writeD(activePlayer.getClient().getSessionInfo().getPlayKey());
		packet.writeD(activePlayer.getClanId());
		packet.writeD(0x00); // ??
		packet.writeD(activePlayer.getAppearance().getSex() ? 1 : 0);
		packet.writeD(activePlayer.getRace().ordinal());
		packet.writeD(activePlayer.getClassId().getId());
		packet.writeD(0x01); // active ??
		packet.writeD((int) activePlayer.getX());
		packet.writeD((int) activePlayer.getY());
		packet.writeD((int) activePlayer.getZ());
		packet.writeF(activePlayer.getCurrentHp());
		packet.writeF(activePlayer.getCurrentMp());
		packet.writeQ(activePlayer.getSp());
		packet.writeQ(activePlayer.getExp());
		packet.writeD(activePlayer.getLevel());
		packet.writeD(activePlayer.getReputation());
		packet.writeD(activePlayer.getPkKills());
		packet.writeD(GameTimeManager.getInstance().getGameTimeInMinutesOfDay());
		packet.writeD(0x00);
		packet.writeD(activePlayer.getClassId().getId());

		packet.writeB(new byte[16]);

		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);

		packet.writeD(0x00);

		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);

		packet.writeB(new byte[28]);
		packet.writeD(0x00);
		return true;
	}
}
