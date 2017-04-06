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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.enums.TaxType;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

/**
 * @author KenM
 */
@Slf4j
public class ExShowCastleInfo extends GameServerPacket {
	public static final ExShowCastleInfo STATIC_PACKET = new ExShowCastleInfo();

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_CASTLE_INFO.writeId(body);

		final Collection<Castle> castles = CastleManager.getInstance().getCastles();
		body.writeD(castles.size());
		for (Castle castle : castles) {
			body.writeD(castle.getResidenceId());
			if (castle.getOwnerId() > 0) {
				if (ClanTable.getInstance().getClan(castle.getOwnerId()) != null) {
					body.writeS(ClanTable.getInstance().getClan(castle.getOwnerId()).getName());
				} else {
					log.warn("Castle owner with no name! Castle: " + castle.getName() + " has an OwnerId = " + castle.getOwnerId() + " who does not have a  name!");
					body.writeS("");
				}
			} else {
				body.writeS("");
			}
			body.writeD(castle.getTaxPercent(TaxType.BUY));
			body.writeD((int) (castle.getSiege().getSiegeDate().getTimeInMillis() / 1000));
		}
	}
}