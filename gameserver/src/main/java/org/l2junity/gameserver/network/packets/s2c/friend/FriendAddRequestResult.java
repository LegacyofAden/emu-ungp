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
package org.l2junity.gameserver.network.packets.s2c.friend;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class FriendAddRequestResult extends GameServerPacket {
	private final int _result;
	private final int _charId;
	private final String _charName;
	private final int _isOnline;
	private final int _charObjectId;
	private final int _charLevel;
	private final int _charClassId;

	public FriendAddRequestResult(Player activeChar, int result) {
		_result = result;
		_charId = activeChar.getObjectId();
		_charName = activeChar.getName();
		_isOnline = activeChar.isOnlineInt();
		_charObjectId = activeChar.getObjectId();
		_charLevel = activeChar.getLevel();
		_charClassId = activeChar.getActiveClass();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.FRIEND_ADD_REQUEST_RESULT.writeId(body);

		body.writeD(_result);
		body.writeD(_charId);
		body.writeS(_charName);
		body.writeD(_isOnline);
		body.writeD(_charObjectId);
		body.writeD(_charLevel);
		body.writeD(_charClassId);
		body.writeH(0x00); // Always 0 on retail
	}
}