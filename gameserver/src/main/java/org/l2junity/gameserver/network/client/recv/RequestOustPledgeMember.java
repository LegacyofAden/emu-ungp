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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExPledgeCount;
import org.l2junity.gameserver.network.client.send.PledgeShowMemberListDelete;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestOustPledgeMember implements IClientIncomingPacket {
	private String _target;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_target = packet.readS();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}
		if (activeChar.getClan() == null) {
			client.sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER_AND_CANNOT_PERFORM_THIS_ACTION);
			return;
		}
		if (!activeChar.hasClanPrivilege(ClanPrivilege.CL_DISMISS)) {
			client.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return;
		}
		if (activeChar.getName().equalsIgnoreCase(_target)) {
			client.sendPacket(SystemMessageId.YOU_CANNOT_DISMISS_YOURSELF);
			return;
		}

		Clan clan = activeChar.getClan();

		ClanMember member = clan.getClanMember(_target);
		if (member == null) {
			_log.warn("Target (" + _target + ") is not member of the clan");
			return;
		}
		if (member.isOnline() && member.getPlayerInstance().isInCombat()) {
			client.sendPacket(SystemMessageId.A_CLAN_MEMBER_MAY_NOT_BE_DISMISSED_DURING_COMBAT);
			return;
		}

		// this also updates the database
		clan.removeClanMember(member.getObjectId(), System.currentTimeMillis() + (PlayerConfig.ALT_CLAN_JOIN_DAYS * 86400000L)); // 24*60*60*1000 = 86400000
		clan.setCharPenaltyExpiryTime(System.currentTimeMillis() + (PlayerConfig.ALT_CLAN_JOIN_DAYS * 86400000L)); // 24*60*60*1000 = 86400000
		clan.updateClanInDB();

		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.CLAN_MEMBER_S1_HAS_BEEN_EXPELLED);
		sm.addString(member.getName());
		clan.broadcastToOnlineMembers(sm);
		client.sendPacket(SystemMessageId.YOU_HAVE_SUCCEEDED_IN_EXPELLING_THE_CLAN_MEMBER);
		client.sendPacket(SystemMessageId.AFTER_A_CLAN_MEMBER_IS_DISMISSED_FROM_A_CLAN_THE_CLAN_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_ACCEPTING_A_NEW_MEMBER);

		// Remove the Player From the Member list
		clan.broadcastToOnlineMembers(new PledgeShowMemberListDelete(_target));
		clan.broadcastToOnlineMembers(new ExPledgeCount(clan));

		if (member.isOnline()) {
			Player player = member.getPlayerInstance();
			player.sendPacket(SystemMessageId.YOU_HAVE_RECENTLY_BEEN_DISMISSED_FROM_A_CLAN_YOU_ARE_NOT_ALLOWED_TO_JOIN_ANOTHER_CLAN_FOR_24_HOURS);
		}
	}
}
