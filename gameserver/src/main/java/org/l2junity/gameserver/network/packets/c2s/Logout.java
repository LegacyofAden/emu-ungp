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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.util.OfflineTradeUtil;

@Slf4j(topic = "accounting")
public final class Logout extends GameClientPacket {
	@Override
	public void readImpl() {
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			getClient().closeForce();
			return;
		}

		if (!player.canLogout()) {
			player.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		log.info("Logged out, {}", getClient());

		if (!OfflineTradeUtil.enteredOfflineMode(player)) {
			Disconnection.of(getClient(), player).defaultSequence(false);
		}
	}
}
