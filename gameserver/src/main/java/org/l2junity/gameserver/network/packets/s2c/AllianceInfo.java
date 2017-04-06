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
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanInfo;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.c2s.RequestAllyInfo;

import java.util.Collection;

/**
 * Sent in response to {@link RequestAllyInfo}, if applicable.<BR>
 *
 * @author afk5min
 */
public class AllianceInfo extends GameServerPacket {
	private final String _name;
	private final int _total;
	private final int _online;
	private final String _leaderC;
	private final String _leaderP;
	private final ClanInfo[] _allies;

	public AllianceInfo(int allianceId) {
		final Clan leader = ClanTable.getInstance().getClan(allianceId);
		_name = leader.getAllyName();
		_leaderC = leader.getName();
		_leaderP = leader.getLeaderName();

		final Collection<Clan> allies = ClanTable.getInstance().getClanAllies(allianceId);
		_allies = new ClanInfo[allies.size()];
		int idx = 0, total = 0, online = 0;
		for (final Clan clan : allies) {
			final ClanInfo ci = new ClanInfo(clan);
			_allies[idx++] = ci;
			total += ci.getTotal();
			online += ci.getOnline();
		}

		_total = total;
		_online = online;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ALLIANCE_INFO.writeId(body);

		body.writeS(_name);
		body.writeD(_total);
		body.writeD(_online);
		body.writeS(_leaderC);
		body.writeS(_leaderP);

		body.writeD(_allies.length);
		for (final ClanInfo aci : _allies) {
			body.writeS(aci.getClan().getName());
			body.writeD(0x00);
			body.writeD(aci.getClan().getLevel());
			body.writeS(aci.getClan().getLeaderName());
			body.writeD(aci.getTotal());
			body.writeD(aci.getOnline());
		}
	}

	public String getName() {
		return _name;
	}

	public int getTotal() {
		return _total;
	}

	public int getOnline() {
		return _online;
	}

	public String getLeaderC() {
		return _leaderC;
	}

	public String getLeaderP() {
		return _leaderP;
	}

	public ClanInfo[] getAllies() {
		return _allies;
	}
}