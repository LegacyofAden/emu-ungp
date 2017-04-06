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
package org.l2junity.gameserver.network.packets.c2s.friend;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.friend.FriendAddRequestResult;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Slf4j
public final class RequestAnswerFriendInvite extends GameClientPacket {
	private int _response;

	@Override
	public void readImpl() {
		readC(); // Unk. Always seem to be 1.
		_response = readD();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final Player requestor = player.getActiveRequester();
		if (requestor == null) {
			return;
		}

		if (player == requestor) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST);
			return;
		}

		if (player.getFriendList().contains(requestor.getObjectId()) //
				|| requestor.getFriendList().contains(player.getObjectId())) {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_ON_YOUR_FRIEND_LIST);
			sm.addCharName(player);
			requestor.sendPacket(sm);
			return;
		}

		if (_response == 1) {
			// Player added to your friend list
			SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_ADDED_TO_YOUR_FRIENDS_LIST);
			msg.addString(player.getName());
			requestor.sendPacket(msg);
			requestor.getFriendList().add(player.getObjectId());

			// has joined as friend.
			msg = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_JOINED_AS_A_FRIEND);
			msg.addString(requestor.getName());
			player.sendPacket(msg);
			player.getFriendList().add(requestor.getObjectId());

			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement statement = con.prepareStatement("INSERT INTO character_friends (charId, friendId) VALUES (?, ?), (?, ?)")) {
				statement.setInt(1, requestor.getObjectId());
				statement.setInt(2, player.getObjectId());
				statement.setInt(3, player.getObjectId());
				statement.setInt(4, requestor.getObjectId());
				statement.execute();
				msg = SystemMessage.getSystemMessage(SystemMessageId.THAT_PERSON_HAS_BEEN_SUCCESSFULLY_ADDED_TO_YOUR_FRIEND_LIST);
				requestor.sendPacket(msg);

				// Send notifications for both player in order to show them online
				player.sendPacket(new FriendAddRequestResult(requestor, 1));
				requestor.sendPacket(new FriendAddRequestResult(player, 1));
			} catch (Exception e) {
				log.error("Could not add friend objectid: " + e.getMessage(), e);
			}
		} else {
			SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_FAILED_TO_ADD_A_FRIEND_TO_YOUR_FRIENDS_LIST);
			requestor.sendPacket(msg);
		}

		player.setActiveRequester(null);
		requestor.onTransactionResponse();
	}
}