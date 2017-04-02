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
package org.l2junity.gameserver.network.client.recv.adenadistribution;

import org.l2junity.gameserver.model.CommandChannel;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.request.AdenaDistributionRequest;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.recv.IClientIncomingPacket;
import org.l2junity.gameserver.network.client.send.adenadistribution.ExDivideAdenaStart;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.network.PacketReader;

import java.util.List;

/**
 * @author Sdw
 */
public class RequestDivideAdenaStart implements IClientIncomingPacket {
	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final PlayerInstance player = client.getActiveChar();
		if (player == null) {
			return;
		}

		final Party party = player.getParty();

		if (party == null) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_PROCEED_AS_YOU_ARE_NOT_IN_AN_ALLIANCE_OR_PARTY);
			return;
		}

		final CommandChannel commandChannel = party.getCommandChannel();

		if ((commandChannel != null) && !commandChannel.isLeader(player)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_PROCEED_AS_YOU_ARE_NOT_AN_ALLIANCE_LEADER_OR_PARTY_LEADER);
			return;
		} else if (!party.isLeader(player)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_PROCEED_AS_YOU_ARE_NOT_A_PARTY_LEADER);
			return;
		}

		final List<PlayerInstance> targets = commandChannel != null ? commandChannel.getMembers() : party.getMembers();

		if (player.getAdena() < targets.size()) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_PROCEED_AS_THERE_IS_INSUFFICIENT_ADENA);
			return;
		}

		if (targets.stream().anyMatch(t -> t.hasRequest(AdenaDistributionRequest.class))) {
			// Handle that case ?
			return;
		}

		final int adenaObjectId = player.getInventory().getAdenaInstance().getObjectId();

		targets.forEach(t ->
		{
			t.sendPacket(SystemMessageId.ADENA_DISTRIBUTION_HAS_STARTED);
			t.addRequest(new AdenaDistributionRequest(t, player, targets, adenaObjectId, player.getAdena()));
		});

		player.sendPacket(ExDivideAdenaStart.STATIC_PACKET);
	}
}
