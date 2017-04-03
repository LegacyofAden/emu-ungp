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

import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.stats.BooleanStat;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExAskJoinMPCC;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * Format: (ch) S<br>
 * D0 0D 00 5A 00 77 00 65 00 72 00 67 00 00 00
 *
 * @author chris_00
 */
public final class RequestExAskJoinMPCC implements IClientIncomingPacket {
	private String _name;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_name = packet.readS();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}

		final Player player = World.getInstance().getPlayer(_name);
		if (player == null) {
			return;
		}
		// invite yourself? ;)
		if (activeChar.isInParty() && player.isInParty() && activeChar.getParty().equals(player.getParty())) {
			return;
		}

		SystemMessage sm;
		// activeChar is in a Party?
		if (activeChar.isInParty()) {
			Party activeParty = activeChar.getParty();
			// activeChar is PartyLeader? && activeChars Party is already in a CommandChannel?
			if (activeParty.getLeader().equals(activeChar)) {
				// if activeChars Party is in CC, is activeChar CCLeader?
				if (activeParty.isInCommandChannel() && activeParty.getCommandChannel().getLeader().equals(activeChar)) {
					// in CC and the CCLeader
					// target in a party?
					if (player.isInParty()) {
						// targets party already in a CChannel?
						if (player.getParty().isInCommandChannel()) {
							sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_PARTY_IS_ALREADY_A_MEMBER_OF_THE_COMMAND_CHANNEL);
							sm.addString(player.getName());
							activeChar.sendPacket(sm);
						} else {
							// ready to open a new CC
							// send request to targets Party's PartyLeader
							askJoinMPCC(activeChar, player);
						}
					} else {
						activeChar.sendMessage(player.getName() + " doesn't have party and cannot be invited to Command Channel.");
					}

				} else if (activeParty.isInCommandChannel() && !activeParty.getCommandChannel().getLeader().equals(activeChar)) {
					// in CC, but not the CCLeader
					sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_DO_NOT_HAVE_AUTHORITY_TO_INVITE_SOMEONE_TO_THE_COMMAND_CHANNEL);
					activeChar.sendPacket(sm);
				} else {
					// target in a party?
					if (player.isInParty()) {
						// targets party already in a CChannel?
						if (player.getParty().isInCommandChannel()) {
							sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_PARTY_IS_ALREADY_A_MEMBER_OF_THE_COMMAND_CHANNEL);
							sm.addString(player.getName());
							activeChar.sendPacket(sm);
						} else {
							// ready to open a new CC
							// send request to targets Party's PartyLeader
							askJoinMPCC(activeChar, player);
						}
					} else {
						activeChar.sendMessage(player.getName() + " doesn't have party and cannot be invited to Command Channel.");
					}
				}
			} else {
				activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_AUTHORITY_TO_INVITE_SOMEONE_TO_THE_COMMAND_CHANNEL);
			}
		}
	}

	private void askJoinMPCC(Player requestor, Player target) {
		boolean hasRight = false;
		if (requestor.isClanLeader() && (requestor.getClan().getLevel() >= 5)) {
			// Clan leader of lvl5 Clan or higher.
			hasRight = true;
		} else if (requestor.getInventory().getItemByItemId(8871) != null) {
			// 8871 Strategy Guide.
			// TODO: Should destroyed after successful invite?
			hasRight = true;
		} else if (requestor.getStat().has(BooleanStat.CAN_CREATE_COMMAND_CHANNEL)) {
			// At least Baron or higher and the skill Clan Imperium
			hasRight = true;
		}

		if (!hasRight) {
			requestor.sendPacket(SystemMessageId.COMMAND_CHANNELS_CAN_ONLY_BE_FORMED_BY_A_PARTY_LEADER_WHO_IS_ALSO_THE_LEADER_OF_A_LEVEL_5_CLAN);
			return;
		}

		// Get the target's party leader, and do whole actions on him.
		final Player targetLeader = target.getParty().getLeader();
		SystemMessage sm;
		if (!targetLeader.isProcessingRequest()) {
			requestor.onTransactionRequest(targetLeader);
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_INVITING_YOU_TO_A_COMMAND_CHANNEL_DO_YOU_ACCEPT);
			sm.addString(requestor.getName());
			targetLeader.sendPacket(sm);
			targetLeader.sendPacket(new ExAskJoinMPCC(requestor.getName()));

			requestor.sendMessage("You invited " + targetLeader.getName() + " to your Command Channel.");
		} else {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ON_ANOTHER_TASK_PLEASE_TRY_AGAIN_LATER);
			sm.addString(targetLeader.getName());
			requestor.sendPacket(sm);
		}
	}
}
