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

import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;

import java.util.Calendar;

/**
 * @author Sdw
 */
public class ExFriendDetailInfo implements IClientOutgoingPacket {
	private final int _objectId;
	private final Player _friend;
	private final String _name;
	private final int _lastAccess;

	public ExFriendDetailInfo(Player player, String name) {
		_objectId = player.getObjectId();
		_name = name;
		_friend = World.getInstance().getPlayer(_name);
		_lastAccess = _friend.isBlocked(player) ? 0 : _friend.isOnline() ? (int) System.currentTimeMillis() : (int) (System.currentTimeMillis() - _friend.getLastAccess()) / 1000;
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_FRIEND_DETAIL_INFO.writeId(packet);

		packet.writeD(_objectId);

		if (_friend == null) {
			packet.writeS(_name);
			packet.writeD(0);
			packet.writeD(0);
			packet.writeH(0);
			packet.writeH(0);
			packet.writeD(0);
			packet.writeD(0);
			packet.writeS("");
			packet.writeD(0);
			packet.writeD(0);
			packet.writeS("");
			packet.writeD(1);
			packet.writeS(""); // memo
		} else {
			packet.writeS(_friend.getName());
			packet.writeD(_friend.isOnlineInt());
			packet.writeD(_friend.getObjectId());
			packet.writeH(_friend.getLevel());
			packet.writeH(_friend.getClassId().getId());
			packet.writeD(_friend.getClanId());
			packet.writeD(_friend.getClanCrestId());
			packet.writeS(_friend.getClan() != null ? _friend.getClan().getName() : "");
			packet.writeD(_friend.getAllyId());
			packet.writeD(_friend.getAllyCrestId());
			packet.writeS(_friend.getClan() != null ? _friend.getClan().getAllyName() : "");
			Calendar createDate = _friend.getCreateDate();
			packet.writeC(createDate.get(Calendar.MONTH) + 1);
			packet.writeC(createDate.get(Calendar.DAY_OF_MONTH));
			packet.writeD(_lastAccess);
			packet.writeS(""); // memo
		}
		return true;
	}
}
