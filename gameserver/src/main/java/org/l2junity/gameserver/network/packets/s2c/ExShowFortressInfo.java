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
import org.l2junity.gameserver.instancemanager.FortManager;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

/**
 * @author KenM
 */
public class ExShowFortressInfo extends GameServerPacket {
	public static final ExShowFortressInfo STATIC_PACKET = new ExShowFortressInfo();

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_FORTRESS_INFO.writeId(body);

		final Collection<Fort> forts = FortManager.getInstance().getForts();
		body.writeD(forts.size());
		for (Fort fort : forts) {
			final Clan clan = fort.getOwnerClan();
			body.writeD(fort.getResidenceId());
			body.writeS(clan != null ? clan.getName() : "");
			body.writeD(fort.getSiege().isInProgress() ? 0x01 : 0x00);
			// Time of possession
			body.writeD(fort.getOwnedTime());
		}
	}
}