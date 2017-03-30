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

import java.util.List;

import org.l2junity.gameserver.instancemanager.CastleManorManager;
import org.l2junity.gameserver.model.L2Seed;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * @author l3x
 */
public final class ExShowManorDefaultInfo implements IClientOutgoingPacket
{
	private final List<L2Seed> _crops;
	private final boolean _hideButtons;
	
	public ExShowManorDefaultInfo(boolean hideButtons)
	{
		_crops = CastleManorManager.getInstance().getCrops();
		_hideButtons = hideButtons;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_SHOW_MANOR_DEFAULT_INFO.writeId(packet);
		
		packet.writeC(_hideButtons ? 0x01 : 0x00); // Hide "Seed Purchase" and "Crop Sales" buttons
		packet.writeD(_crops.size());
		for (L2Seed crop : _crops)
		{
			packet.writeD(crop.getCropId()); // crop Id
			packet.writeD(crop.getLevel()); // level
			packet.writeD((int) crop.getSeedReferencePrice()); // seed price
			packet.writeD((int) crop.getCropReferencePrice()); // crop price
			packet.writeC(1); // Reward 1 type
			packet.writeD(crop.getReward(1)); // Reward 1 itemId
			packet.writeC(1); // Reward 2 type
			packet.writeD(crop.getReward(2)); // Reward 2 itemId
		}
		return true;
	}
}