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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.model.BlockList;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.FriendAddRequest;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestFriendInvite extends GameClientPacket {
	private String _name;

	@Override
	public void readImpl() {
		_name = readS();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final Player friend = WorldManager.getInstance().getPlayer(_name);

		// Target is not found in the game.
		if ((friend == null) || !friend.isOnline() || friend.isInvisible()) {
			getClient().sendPacket(SystemMessageId.THE_USER_WHO_REQUESTED_TO_BECOME_FRIENDS_IS_NOT_FOUND_IN_THE_GAME);
			return;
		}
		// You cannot add yourself to your own friend list.
		if (friend == activeChar) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIEND_LIST);
			return;
		}
		// Target is in olympiad.
		if (activeChar.isInOlympiadMode() || friend.isInOlympiadMode()) {
			getClient().sendPacket(SystemMessageId.A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS);
			return;
		}

		// Cannot request friendship in Ceremony of Chaos event.
		if (activeChar.isOnEvent(CeremonyOfChaosEvent.class)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_INVITE_A_FRIEND_OR_PARTY_WHILE_PARTICIPATING_IN_THE_CEREMONY_OF_CHAOS);
			return;
		}

		// Target blocked active player.
		if (BlockList.isBlocked(friend, activeChar)) {
			activeChar.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.FRIEND_INVITATION_OF_C1_IS_REFUSED).addString(_name));
			return;
		}
		SystemMessage sm;
		// Target is blocked.
		if (BlockList.isBlocked(activeChar, friend)) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_BLOCKED_C1);
			sm.addCharName(friend);
			getClient().sendPacket(sm);
			return;
		}
		// Target already in friend list.
		if (activeChar.getFriendList().contains(friend.getObjectId())) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.THIS_PLAYER_IS_ALREADY_REGISTERED_ON_YOUR_FRIENDS_LIST);
			sm.addString(_name);
			getClient().sendPacket(sm);
			return;
		}
		// Target is busy.
		if (friend.isProcessingRequest()) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ON_ANOTHER_TASK_PLEASE_TRY_AGAIN_LATER);
			sm.addString(_name);
			getClient().sendPacket(sm);
			return;
		}
		// Friend request sent.
		activeChar.onTransactionRequest(friend);
		friend.sendPacket(new FriendAddRequest(activeChar.getName()));
		sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_VE_REQUESTED_C1_TO_BE_ON_YOUR_FRIENDS_LIST);
		sm.addString(_name);
		getClient().sendPacket(sm);
	}
}
