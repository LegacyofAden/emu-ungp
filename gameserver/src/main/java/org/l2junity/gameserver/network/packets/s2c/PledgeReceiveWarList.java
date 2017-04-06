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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanWar;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

/**
 * @author -Wooden-
 */
public class PledgeReceiveWarList extends GameServerPacket {
	private final Clan _clan;
	private final int _tab;
	private final Collection<ClanWar> _clanList;

	public PledgeReceiveWarList(Clan clan, int tab) {
		_clan = clan;
		_tab = tab;
		_clanList = clan.getWarList().values();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_RECEIVE_WAR_LIST.writeId(body);

		body.writeD(_tab); // page
		body.writeD(_clanList.size());
		for (ClanWar clanWar : _clanList) {
			final Clan clan = clanWar.getOpposingClan(_clan);

			if (clan == null) {
				continue;
			}

			body.writeS(clan.getName());
			body.writeD(clanWar.getState().ordinal()); // type: 0 = Declaration, 1 = Blood Declaration, 2 = In War, 3 = Victory, 4 = Defeat, 5 = Tie, 6 = Error
			body.writeD(clanWar.getRemainingTime()); // Time if friends to start remaining
			body.writeD(clanWar.getKillDifference(_clan)); // Score
			body.writeD(0); // @TODO: Recent change in points
			body.writeD(clanWar.getKillToStart()); // Friends to start war left
		}
	}
}