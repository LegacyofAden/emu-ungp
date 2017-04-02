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
package org.l2junity.gameserver.network.client.send.fishing;

import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;

/**
 * @author Sdw
 */
public class ExUserInfoFishing implements IClientOutgoingPacket {
	private final PlayerInstance _activeChar;
	private final boolean _isFishing;
	private final ILocational _baitLocation;

	public ExUserInfoFishing(PlayerInstance activeChar, boolean isFishing, ILocational baitLocation) {
		_activeChar = activeChar;
		_isFishing = isFishing;
		_baitLocation = baitLocation;
	}

	public ExUserInfoFishing(PlayerInstance activeChar, boolean isFishing) {
		_activeChar = activeChar;
		_isFishing = isFishing;
		_baitLocation = null;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_USER_INFO_FISHING.writeId(packet);

		packet.writeD(_activeChar.getObjectId());
		packet.writeC(_isFishing ? 1 : 0);
		if (_baitLocation == null) {
			packet.writeD(0);
			packet.writeD(0);
			packet.writeD(0);
		} else {
			packet.writeD((int) _baitLocation.getX());
			packet.writeD((int) _baitLocation.getY());
			packet.writeD((int) _baitLocation.getZ());
		}
		return true;
	}
}
