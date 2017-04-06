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
import org.l2junity.gameserver.enums.PartyDistributionType;
import org.l2junity.gameserver.model.BlockList;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.PartyRequest;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerInviteToParty;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.AskJoinParty;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestJoinParty extends GameClientPacket {
	private String _name;
	private int _partyDistributionTypeId;

	@Override
	public void readImpl() {
		_name = readS();
		_partyDistributionTypeId = readD();
	}

	@Override
	public void runImpl() {
		final Player requestor = getClient().getActiveChar();
		final Player target = WorldManager.getInstance().getPlayer(_name);

		if (requestor == null) {
			return;
		}

		if (target == null) {
			requestor.sendPacket(SystemMessageId.YOU_MUST_FIRST_SELECT_A_USER_TO_INVITE_TO_YOUR_PARTY);
			return;
		}

		if ((target.getClient() == null) || target.getClient().isDetached()) {
			requestor.sendMessage("Player is in offline mode.");
			return;
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerInviteToParty(requestor, target), requestor, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (requestor.isPartyBanned()) {
			requestor.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_PARTICIPATING_IN_A_PARTY_IS_NOT_ALLOWED);
			requestor.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (target.isPartyBanned()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_AND_CANNOT_JOIN_A_PARTY);
			sm.addCharName(target);
			requestor.sendPacket(sm);
			return;
		}

		if (!target.isVisibleFor(requestor)) {
			requestor.sendPacket(SystemMessageId.THAT_IS_AN_INCORRECT_TARGET);
			return;
		}

		if (requestor.isOnEvent(CeremonyOfChaosEvent.class)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_INVITE_A_FRIEND_OR_PARTY_WHILE_PARTICIPATING_IN_THE_CEREMONY_OF_CHAOS);
			return;
		}

		SystemMessage sm;
		if (target.isInParty()) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_A_MEMBER_OF_ANOTHER_PARTY_AND_CANNOT_BE_INVITED);
			sm.addString(target.getName());
			requestor.sendPacket(sm);
			return;
		}

		if (BlockList.isBlocked(target, requestor)) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_PLACED_YOU_ON_HIS_HER_IGNORE_LIST);
			sm.addCharName(target);
			requestor.sendPacket(sm);
			return;
		}

		if (target == requestor) {
			requestor.sendPacket(SystemMessageId.YOU_HAVE_INVITED_THE_WRONG_TARGET);
			return;
		}

		if (target.isCursedWeaponEquipped() || requestor.isCursedWeaponEquipped()) {
			requestor.sendPacket(SystemMessageId.INVALID_TARGET);
			return;
		}

		if (target.isJailed() || requestor.isJailed()) {
			requestor.sendMessage("You cannot invite a player while is in Jail.");
			return;
		}

		if (target.isInOlympiadMode() || requestor.isInOlympiadMode()) {
			if ((target.isInOlympiadMode() != requestor.isInOlympiadMode()) || (target.getOlympiadGameId() != requestor.getOlympiadGameId()) || (target.getOlympiadSide() != requestor.getOlympiadSide())) {
				requestor.sendPacket(SystemMessageId.A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS);
				return;
			}
		}

		sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_BEEN_INVITED_TO_THE_PARTY);
		sm.addCharName(target);
		requestor.sendPacket(sm);

		if (!requestor.isInParty()) {
			createNewParty(target, requestor);
		} else {
			addTargetToParty(target, requestor);
		}
	}

	/**
	 * @param target
	 * @param requestor
	 */
	private void addTargetToParty(Player target, Player requestor) {
		final Party party = requestor.getParty();

		// summary of ppl already in party and ppl that get invitation
		if (!party.isLeader(requestor)) {
			requestor.sendPacket(SystemMessageId.ONLY_THE_LEADER_CAN_GIVE_OUT_INVITATIONS);
		} else if (party.getMemberCount() >= PlayerConfig.ALT_PARTY_MAX_MEMBERS) {
			requestor.sendPacket(SystemMessageId.THE_PARTY_IS_FULL);
		} else if (party.getPendingInvitation() && !party.isInvitationRequestExpired()) {
			requestor.sendPacket(SystemMessageId.WAITING_FOR_ANOTHER_REPLY);
		} else if (!target.hasRequest(PartyRequest.class)) {
			final PartyRequest request = new PartyRequest(requestor, target, party);
			request.scheduleTimeout(30 * 1000);
			requestor.addRequest(request);
			target.addRequest(request);
			target.sendPacket(new AskJoinParty(requestor.getName(), party.getDistributionType()));
			party.setPendingInvitation(true);
		} else {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ON_ANOTHER_TASK_PLEASE_TRY_AGAIN_LATER);
			sm.addString(target.getName());
			requestor.sendPacket(sm);
		}
	}

	/**
	 * @param target
	 * @param requestor
	 */
	private void createNewParty(Player target, Player requestor) {
		final PartyDistributionType partyDistributionType = PartyDistributionType.findById(_partyDistributionTypeId);
		if (partyDistributionType == null) {
			return;
		}

		if (!target.hasRequest(PartyRequest.class)) {
			final Party party = new Party(requestor, partyDistributionType);
			party.setPendingInvitation(true);
			final PartyRequest request = new PartyRequest(requestor, target, party);
			request.scheduleTimeout(30 * 1000);
			requestor.addRequest(request);
			target.addRequest(request);
			target.sendPacket(new AskJoinParty(requestor.getName(), partyDistributionType));
		} else {
			requestor.sendPacket(SystemMessageId.WAITING_FOR_ANOTHER_REPLY);
		}
	}
}
