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

import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.instancemanager.FortManager;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.ClanInvitationRequest;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.*;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestAnswerJoinPledge extends GameClientPacket {
	private int _answer;

	@Override
	public void readImpl() {
		_answer = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final ClanInvitationRequest request = activeChar.getRequest(ClanInvitationRequest.class);
		if ((request == null) || request.isProcessing() || !activeChar.removeRequest(request.getClass())) {
			return;
		}
		request.setProcessing(true);

		final Player requestor = request.getActiveChar();
		if (requestor == null) {
			return;
		}

		final Clan clan = request.getClan();
		final Clan requestorClan = requestor.getClan();
		if ((requestorClan == null) || (requestorClan != clan)) {
			return;
		}

		if (_answer == 0) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_DIDN_T_RESPOND_TO_S1_S_INVITATION_JOINING_HAS_BEEN_CANCELLED);
			sm.addString(requestor.getName());
			activeChar.sendPacket(sm);
			sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DID_NOT_RESPOND_INVITATION_TO_THE_CLAN_HAS_BEEN_CANCELLED);
			sm.addString(activeChar.getName());
			requestor.sendPacket(sm);
		} else {
			// we must double check this cause during response time conditions can be changed, i.e. another player could join clan
			if (clan.checkClanJoinCondition(requestor, activeChar, request.getPledgeType())) {
				activeChar.sendPacket(new JoinPledge(requestor.getClanId()));

				activeChar.setPledgeType(request.getPledgeType());
				if (request.getPledgeType() == Clan.SUBUNIT_ACADEMY) {
					activeChar.setPowerGrade(9); // adademy
					activeChar.setLvlJoinedAcademy(activeChar.getLevel());
				} else {
					activeChar.setPowerGrade(5); // new member starts at 5, not confirmed
				}

				clan.addClanMember(activeChar);
				activeChar.setClanPrivileges(activeChar.getClan().getRankPrivs(activeChar.getPowerGrade()));
				activeChar.sendPacket(SystemMessageId.ENTERED_THE_CLAN);

				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_JOINED_THE_CLAN);
				sm.addString(activeChar.getName());
				clan.broadcastToOnlineMembers(sm);

				if (clan.getCastleId() > 0) {
					CastleManager.getInstance().getCastleByOwner(clan).giveResidentialSkills(activeChar);
				}
				if (clan.getFortId() > 0) {
					FortManager.getInstance().getFortByOwner(clan).giveResidentialSkills(activeChar);
				}
				activeChar.sendSkillList();

				clan.broadcastToOtherOnlineMembers(new PledgeShowMemberListAdd(activeChar), activeChar);
				clan.broadcastToOnlineMembers(new PledgeShowInfoUpdate(clan));
				clan.broadcastToOnlineMembers(new ExPledgeCount(clan));

				// this activates the clan tab on the new member
				PledgeShowMemberListAll.sendAllTo(activeChar);
				activeChar.setClanJoinExpiryTime(0);
				activeChar.broadcastUserInfo();
			}
		}

		activeChar.getRequest().onRequestResponse();
	}
}
