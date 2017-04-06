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

import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.PledgeReceivePowerInfo;


/**
 * Format: (ch) dS
 *
 * @author -Wooden-
 */
public final class RequestPledgeMemberPowerInfo extends GameClientPacket {
	@SuppressWarnings("unused")
	private int _unk1;
	private String _player;

	@Override
	public void readImpl() {
		_unk1 = readD();
		_player = readS();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		// do we need powers to do that??
		final Clan clan = activeChar.getClan();
		if (clan == null) {
			return;
		}

		final ClanMember member = clan.getClanMember(_player);
		if (member == null) {
			return;
		}
		activeChar.sendPacket(new PledgeReceivePowerInfo(member));
	}
}