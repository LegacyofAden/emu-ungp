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
package org.l2junity.gameserver.network.client.send.friend;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;

/**
 * Support for "Chat with Friends" dialog. <br />
 * Inform player about friend online status change
 *
 * @author JIV
 */
public class L2FriendStatus implements IClientOutgoingPacket {
	public static final int MODE_OFFLINE = 0;
	public static final int MODE_ONLINE = 1;
	public static final int MODE_LEVEL = 2;
	public static final int MODE_CLASS = 3;

	private final int _type;
	private final int _objectId;
	private final int _classId;
	private final int _level;
	private final String _name;

	public L2FriendStatus(Player player, int type) {
		_objectId = player.getObjectId();
		_classId = player.getActiveClass();
		_level = player.getLevel();
		_name = player.getName();
		_type = type;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.FRIEND_STATUS.writeId(packet);

		packet.writeD(_type);
		packet.writeS(_name);
		switch (_type) {
			case MODE_OFFLINE: {
				packet.writeD(_objectId);
				break;
			}
			case MODE_LEVEL: {
				packet.writeD(_level);
				break;
			}
			case MODE_CLASS: {
				packet.writeD(_classId);
				break;
			}
		}
		return true;
	}
}
