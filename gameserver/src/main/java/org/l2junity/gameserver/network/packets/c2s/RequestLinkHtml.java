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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;
import org.l2junity.gameserver.util.Util;


/**
 * Lets drink to code!
 *
 * @author zabbix, HorridoJoho
 */
@Slf4j
public final class RequestLinkHtml extends GameClientPacket {
	private String _link;

	@Override
	public void readImpl() {
		_link = readS();
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (_link.isEmpty()) {
			log.warn("Player " + player.getName() + " sent empty html link!");
			return;
		}

		if (_link.contains("..")) {
			log.warn("Player " + player.getName() + " sent invalid html link: link " + _link);
			return;
		}

		int htmlObjectId = player.validateHtmlAction("link " + _link);
		if (htmlObjectId == -1) {
			log.warn("Player " + player.getName() + " sent non cached  html link: link " + _link);
			return;
		}

		if ((htmlObjectId > 0) && !Util.isInsideRangeOfObjectId(player, htmlObjectId, Npc.INTERACTION_DISTANCE)) {
			// No logging here, this could be a common case
			return;
		}

		final NpcHtmlMessage html = new NpcHtmlMessage(htmlObjectId);
		html.setFile(player.getLang(), "" + _link);
		player.sendPacket(html);
	}
}