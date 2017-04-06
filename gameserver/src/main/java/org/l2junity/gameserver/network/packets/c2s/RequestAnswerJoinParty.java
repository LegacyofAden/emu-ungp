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
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.Party.MessageType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.PartyRequest;
import org.l2junity.gameserver.model.matching.MatchingRoom;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.JoinParty;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


public final class RequestAnswerJoinParty extends GameClientPacket {
	private int _response;

	@Override
	public void readImpl() {
		_response = readD();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final PartyRequest request = player.getRequest(PartyRequest.class);
		if ((request == null) || request.isProcessing() || !player.removeRequest(request.getClass())) {
			return;
		}
		request.setProcessing(true);

		final Player requestor = request.getActiveChar();
		if (requestor == null) {
			return;
		}

		final Party party = request.getParty();
		final Party requestorParty = requestor.getParty();
		if ((requestorParty != null) && (requestorParty != party)) {
			return;
		}

		requestor.sendPacket(new JoinParty(_response));

		if (_response == 1) {
			if (party.getMemberCount() >= PlayerConfig.ALT_PARTY_MAX_MEMBERS) {
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_PARTY_IS_FULL);
				player.sendPacket(sm);
				requestor.sendPacket(sm);
				return;
			}

			// Assign the party to the leader upon accept of his partner
			if (requestorParty == null) {
				requestor.setParty(party);
			}

			player.joinParty(party);

			final MatchingRoom requestorRoom = requestor.getMatchingRoom();

			if (requestorRoom != null) {
				requestorRoom.addMember(player);
			}
		} else if (_response == -1) {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_SET_TO_REFUSE_PARTY_REQUESTS_AND_CANNOT_RECEIVE_A_PARTY_REQUEST);
			sm.addPcName(player);
			requestor.sendPacket(sm);

			if (party.getMemberCount() == 1) {
				party.removePartyMember(requestor, MessageType.NONE);
			}
		} else {
			if (party.getMemberCount() == 1) {
				party.removePartyMember(requestor, MessageType.NONE);
			}
		}

		party.setPendingInvitation(false);
		request.setProcessing(false);
	}
}
