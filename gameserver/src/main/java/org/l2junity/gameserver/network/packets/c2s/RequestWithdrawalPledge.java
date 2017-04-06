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

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExPledgeCount;
import org.l2junity.gameserver.network.packets.s2c.PledgeShowMemberListDelete;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestWithdrawalPledge extends GameClientPacket {
	@Override
	public void readImpl() {
	}

	@Override
	public void runImpl() {
		Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}
		if (activeChar.getClan() == null) {
			getClient().sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER_AND_CANNOT_PERFORM_THIS_ACTION);
			return;
		}
		if (activeChar.isClanLeader()) {
			getClient().sendPacket(SystemMessageId.A_CLAN_LEADER_CANNOT_WITHDRAW_FROM_THEIR_OWN_CLAN);
			return;
		}
		if (activeChar.isInCombat()) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_LEAVE_A_CLAN_WHILE_ENGAGED_IN_COMBAT);
			return;
		}

		Clan clan = activeChar.getClan();

		clan.removeClanMember(activeChar.getObjectId(), System.currentTimeMillis() + (PlayerConfig.ALT_CLAN_JOIN_DAYS * 86400000L)); // 24*60*60*1000 = 86400000

		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_WITHDRAWN_FROM_THE_CLAN);
		sm.addString(activeChar.getName());
		clan.broadcastToOnlineMembers(sm);

		// Remove the Player From the Member list
		clan.broadcastToOnlineMembers(new PledgeShowMemberListDelete(activeChar.getName()));
		clan.broadcastToOnlineMembers(new ExPledgeCount(clan));

		getClient().sendPacket(SystemMessageId.YOU_HAVE_WITHDRAWN_FROM_THE_CLAN);
		getClient().sendPacket(SystemMessageId.AFTER_LEAVING_OR_HAVING_BEEN_DISMISSED_FROM_A_CLAN_YOU_MUST_WAIT_AT_LEAST_A_DAY_BEFORE_JOINING_ANOTHER_CLAN);
	}
}
