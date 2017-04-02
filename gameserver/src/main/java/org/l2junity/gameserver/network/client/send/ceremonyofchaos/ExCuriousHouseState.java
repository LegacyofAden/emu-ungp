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
package org.l2junity.gameserver.network.client.send.ceremonyofchaos;

import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;

/**
 * @author Sdw
 */
public class ExCuriousHouseState implements IClientOutgoingPacket {
	public static final ExCuriousHouseState IDLE_PACKET = new ExCuriousHouseState(0);
	public static final ExCuriousHouseState REGISTRATION_PACKET = new ExCuriousHouseState(1);
	public static final ExCuriousHouseState PREPARE_PACKET = new ExCuriousHouseState(2);
	public static final ExCuriousHouseState STARTING_PACKET = new ExCuriousHouseState(3);

	private final int _state;

	public ExCuriousHouseState(int state) {
		_state = state;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_CURIOUS_HOUSE_STATE.writeId(packet);
		packet.writeD(_state);
		return true;
	}
}