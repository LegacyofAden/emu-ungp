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
package org.l2junity.gameserver.network.client.recv.friend;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.recv.IClientIncomingPacket;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.friend.FriendRemove;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * This class ...
 *
 * @version $Revision: 1.3.4.2 $ $Date: 2005/03/27 15:29:30 $
 */
public final class RequestFriendDel implements IClientIncomingPacket {
	private String _name;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_name = packet.readS();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		SystemMessage sm;

		Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}

		int id = CharNameTable.getInstance().getIdByName(_name);

		if (id == -1) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_NOT_ON_YOUR_FRIEND_LIST);
			sm.addString(_name);
			activeChar.sendPacket(sm);
			return;
		}

		if (!activeChar.getFriendList().contains(id)) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_NOT_ON_YOUR_FRIEND_LIST);
			sm.addString(_name);
			activeChar.sendPacket(sm);
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM character_friends WHERE (charId=? AND friendId=?) OR (charId=? AND friendId=?)")) {
			statement.setInt(1, activeChar.getObjectId());
			statement.setInt(2, id);
			statement.setInt(3, id);
			statement.setInt(4, activeChar.getObjectId());
			statement.execute();

			// Player deleted from your friend list
			sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST);
			sm.addString(_name);
			activeChar.sendPacket(sm);

			activeChar.getFriendList().remove(Integer.valueOf(id));
			activeChar.sendPacket(new FriendRemove(_name, 1));

			Player player = WorldManager.getInstance().getPlayer(_name);
			if (player != null) {
				player.getFriendList().remove(Integer.valueOf(activeChar.getObjectId()));
				player.sendPacket(new FriendRemove(activeChar.getName(), 1));
			}
		} catch (Exception e) {
			_log.warn("could not del friend objectid: ", e);
		}
	}
}
