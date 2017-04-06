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
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.LinkedList;
import java.util.List;

/**
 * Support for "Chat with Friends" dialog. <br />
 * This packet is sent only at login.
 *
 * @author Tempy
 */
public class L2FriendList extends GameServerPacket {
	private final List<FriendInfo> _info = new LinkedList<>();

	private static class FriendInfo {
		int _objId;
		String _name;
		int _level;
		int _classId;
		boolean _online;

		public FriendInfo(int objId, String name, boolean online, int level, int classId) {
			_objId = objId;
			_name = name;
			_online = online;
			_level = level;
			_classId = classId;
		}
	}

	public L2FriendList(Player player) {
		for (int objId : player.getFriendList()) {
			String name = CharNameTable.getInstance().getNameById(objId);
			Player player1 = WorldManager.getInstance().getPlayer(objId);
			boolean online = false;
			int level = 0;
			int classId = 0;

			if (player1 != null) {
				online = true;
				level = player1.getLevel();
				classId = player1.getClassId().getId();
			} else {
				level = CharNameTable.getInstance().getLevelById(objId);
				classId = CharNameTable.getInstance().getClassIdById(objId);
			}
			_info.add(new FriendInfo(objId, name, online, level, classId));
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.L2_FRIEND_LIST.writeId(body);

		body.writeD(_info.size());
		for (FriendInfo info : _info) {
			body.writeD(info._objId); // character id
			body.writeS(info._name);
			body.writeD(info._online ? 0x01 : 0x00); // online
			body.writeD(info._online ? info._objId : 0x00); // object id if online
			body.writeD(info._level);
			body.writeD(info._classId);
			body.writeH(0x00);
		}
	}
}