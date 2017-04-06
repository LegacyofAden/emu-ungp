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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.instancemanager.FortManager;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExShowFortressMapInfo;
import org.l2junity.network.PacketReader;

/**
 * @author KenM
 */
@Slf4j
public class RequestFortressMapInfo extends GameClientPacket {
	private int _fortressId;

	@Override
	public void readImpl() {
		_fortressId = readD();
	}

	@Override
	public void runImpl() {
		final Fort fort = FortManager.getInstance().getFortById(_fortressId);
		if (fort == null) {
			log.warn("Fort is not found with id (" + _fortressId + ") in all forts with size of (" + FortManager.getInstance().getForts().size() + ") called by player (" + getClient().getActiveChar() + ")");

			if (getClient().getActiveChar() == null) {
				return;
			}

			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		getClient().sendPacket(new ExShowFortressMapInfo(fort));
	}
}
