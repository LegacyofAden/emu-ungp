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

import org.l2junity.gameserver.instancemanager.ClanEntryManager;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.clan.entry.PledgeApplicantInfo;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExPledgeWaitingList;
import org.l2junity.gameserver.network.client.send.ExPledgeWaitingUser;
import org.l2junity.network.PacketReader;

/**
 * @author Sdw
 */
public class RequestPledgeWaitingUser implements IClientIncomingPacket {
	private int _clanId;
	private int _playerId;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_clanId = packet.readD();
		_playerId = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final PlayerInstance activeChar = client.getActiveChar();
		if ((activeChar == null) || (activeChar.getClanId() != _clanId)) {
			return;
		}

		final PledgeApplicantInfo infos = ClanEntryManager.getInstance().getPlayerApplication(_clanId, _playerId);
		if (infos == null) {
			client.sendPacket(new ExPledgeWaitingList(_clanId));
		} else {
			client.sendPacket(new ExPledgeWaitingUser(infos));
		}
	}
}