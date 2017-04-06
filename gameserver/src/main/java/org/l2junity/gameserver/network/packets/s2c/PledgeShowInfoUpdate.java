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
import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class PledgeShowInfoUpdate extends GameServerPacket {
	private final Clan _clan;

	public PledgeShowInfoUpdate(Clan clan) {
		_clan = clan;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_SHOW_INFO_UPDATE.writeId(body);

		// sending empty data so client will ask all the info in response ;)
		body.writeD(_clan.getId());
		body.writeD(GameserverConfig.SERVER_ID);
		body.writeD(_clan.getCrestId());
		body.writeD(_clan.getLevel()); // clan level
		body.writeD(_clan.getCastleId());
		body.writeD(0x00); // castle state ?
		body.writeD(_clan.getHideoutId());
		body.writeD(_clan.getFortId());
		body.writeD(_clan.getRank());
		body.writeD(_clan.getReputationScore()); // clan reputation score
		body.writeD(0x00); // ?
		body.writeD(0x00); // ?
		body.writeD(_clan.getAllyId());
		body.writeS(_clan.getAllyName()); // c5
		body.writeD(_clan.getAllyCrestId()); // c5
		body.writeD(_clan.isAtWar() ? 1 : 0); // c5
		body.writeD(0x00); // TODO: Find me!
		body.writeD(0x00); // TODO: Find me!
	}
}