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

import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.BypassHandler;
import org.l2junity.gameserver.handler.IBypassHandler;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;


public class RequestTutorialLinkHtml extends GameClientPacket {
	private String _bypass;

	@Override
	public void readImpl() {
		readD();
		_bypass = readS();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (_bypass.startsWith("admin_")) {
			AdminCommandHandler.getInstance().useAdminCommand(player, _bypass, true);
		} else {
			final IBypassHandler handler = BypassHandler.getInstance().getHandler(_bypass);
			if (handler != null) {
				handler.useBypass(_bypass, player, null);
			}
		}
	}
}
