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

import java.util.ArrayList;
import java.util.List;

import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * Note: <b>There is known issue with this packet, it cannot be removed unless game client is restarted!</b>
 * @author UnAfraid
 */
public class ExShowTerritory implements IClientOutgoingPacket
{
	private final int _minZ;
	private final int _maxZ;
	private final List<ILocational> _vertices = new ArrayList<>();
	
	public ExShowTerritory(double minZ, double maxZ)
	{
		_minZ = (int) minZ;
		_maxZ = (int) maxZ;
	}
	
	public void addVertice(ILocational loc)
	{
		_vertices.add(loc);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_SHOW_TERRITORY.writeId(packet);
		
		packet.writeD(_vertices.size());
		packet.writeD(_minZ);
		packet.writeD(_maxZ);
		for (ILocational loc : _vertices)
		{
			packet.writeD((int) loc.getX());
			packet.writeD((int) loc.getY());
		}
		return true;
	}
}
