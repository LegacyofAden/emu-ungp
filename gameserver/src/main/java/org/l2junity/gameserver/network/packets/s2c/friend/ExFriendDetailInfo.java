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
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Calendar;

/**
 * @author Sdw
 */
public class ExFriendDetailInfo extends GameServerPacket {
	private final int _objectId;
	private final Player _friend;
	private final String _name;
	private final int _lastAccess;

	public ExFriendDetailInfo(Player player, String name) {
		_objectId = player.getObjectId();
		_name = name;
		_friend = WorldManager.getInstance().getPlayer(_name);
		_lastAccess = _friend.isBlocked(player) ? 0 : _friend.isOnline() ? (int) System.currentTimeMillis() : (int) (System.currentTimeMillis() - _friend.getLastAccess()) / 1000;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_FRIEND_DETAIL_INFO.writeId(body);

		body.writeD(_objectId);

		if (_friend == null) {
			body.writeS(_name);
			body.writeD(0);
			body.writeD(0);
			body.writeH(0);
			body.writeH(0);
			body.writeD(0);
			body.writeD(0);
			body.writeS("");
			body.writeD(0);
			body.writeD(0);
			body.writeS("");
			body.writeD(1);
			body.writeS(""); // memo
		} else {
			body.writeS(_friend.getName());
			body.writeD(_friend.isOnlineInt());
			body.writeD(_friend.getObjectId());
			body.writeH(_friend.getLevel());
			body.writeH(_friend.getClassId().getId());
			body.writeD(_friend.getClanId());
			body.writeD(_friend.getClanCrestId());
			body.writeS(_friend.getClan() != null ? _friend.getClan().getName() : "");
			body.writeD(_friend.getAllyId());
			body.writeD(_friend.getAllyCrestId());
			body.writeS(_friend.getClan() != null ? _friend.getClan().getAllyName() : "");
			Calendar createDate = _friend.getCreateDate();
			body.writeC(createDate.get(Calendar.MONTH) + 1);
			body.writeC(createDate.get(Calendar.DAY_OF_MONTH));
			body.writeD(_lastAccess);
			body.writeS(""); // memo
		}
	}
}