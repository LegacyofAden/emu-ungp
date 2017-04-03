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

import org.l2junity.gameserver.enums.PartyDistributionType;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.network.PacketReader;

/**
 * @author JIV
 */
public class RequestPartyLootModification implements IClientIncomingPacket {
	private int _partyDistributionTypeId;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_partyDistributionTypeId = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}

		final PartyDistributionType partyDistributionType = PartyDistributionType.findById(_partyDistributionTypeId);
		if (partyDistributionType == null) {
			return;
		}

		final Party party = activeChar.getParty();
		if ((party == null) || !party.isLeader(activeChar) || (partyDistributionType == party.getDistributionType())) {
			return;
		}
		party.requestLootChange(partyDistributionType);
	}

}
