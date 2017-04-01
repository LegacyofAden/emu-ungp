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

import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public final class PledgeStatusChanged implements IClientOutgoingPacket
{
	private final L2Clan _clan;
	
	public PledgeStatusChanged(L2Clan clan)
	{
		_clan = clan;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.PLEDGE_STATUS_CHANGED.writeId(packet);
		
		packet.writeD(0x00);
		packet.writeD(_clan.getLeaderId());
		packet.writeD(_clan.getId());
		packet.writeD(_clan.getCrestId());
		packet.writeD(_clan.getAllyId());
		packet.writeD(_clan.getAllyCrestId());
		packet.writeD(_clan.getCrestLargeId());
		packet.writeD(0x00); // pledge type ?
		return true;
	}
}
