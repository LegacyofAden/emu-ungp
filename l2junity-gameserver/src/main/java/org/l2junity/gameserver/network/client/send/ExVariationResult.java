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

import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * Format: (ch)ddd
 */
public class ExVariationResult implements IClientOutgoingPacket
{
	private final int _option1;
	private final int _option2;
	private final int _success;
	
	public ExVariationResult(int option1, int option2, boolean success)
	{
		_option1 = option1;
		_option2 = option2;
		_success = success ? 0x01 : 0x00;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_VARIATION_RESULT.writeId(packet);
		
		packet.writeD(_option1);
		packet.writeD(_option2);
		packet.writeD(_success);
		return true;
	}
}
