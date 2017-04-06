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

import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.SiegeDefenderList;
import org.l2junity.network.PacketReader;

public final class RequestConfirmSiegeWaitingList extends GameClientPacket {
	private int _approved;
	private int _castleId;
	private int _clanId;

	@Override
	public void readImpl() {
		_castleId = readD();
		_clanId = readD();
		_approved = readD();
	}

	@Override
	public void runImpl() {
		Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		// Check if the player has a clan
		if (activeChar.getClan() == null) {
			return;
		}

		Castle castle = CastleManager.getInstance().getCastleById(_castleId);
		if (castle == null) {
			return;
		}

		// Check if leader of the clan who owns the castle?
		if ((castle.getOwnerId() != activeChar.getClanId()) || (!activeChar.isClanLeader())) {
			return;
		}

		Clan clan = ClanTable.getInstance().getClan(_clanId);
		if (clan == null) {
			return;
		}

		if (!castle.getSiege().getIsRegistrationOver()) {
			if (_approved == 1) {
				if (castle.getSiege().checkIsDefenderWaiting(clan)) {
					castle.getSiege().approveSiegeDefenderClan(_clanId);
				} else {
					return;
				}
			} else {
				if ((castle.getSiege().checkIsDefenderWaiting(clan)) || (castle.getSiege().checkIsDefender(clan))) {
					castle.getSiege().removeSiegeClan(_clanId);
				}
			}
		}

		// Update the defender list
		getClient().sendPacket(new SiegeDefenderList(castle));
	}
}
