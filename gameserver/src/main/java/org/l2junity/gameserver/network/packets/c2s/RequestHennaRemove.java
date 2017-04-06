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
import org.l2junity.gameserver.model.items.Henna;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * @author Zoey76
 */
@Slf4j
public final class RequestHennaRemove extends GameClientPacket {
	private int _symbolId;

	@Override
	public void readImpl() {
		_symbolId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("HennaRemove")) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		Henna henna;
		boolean found = false;
		for (int i = 1; i <= 3; i++) {
			henna = activeChar.getHenna(i);
			if ((henna != null) && (henna.getDyeId() == _symbolId)) {
				if (activeChar.getAdena() >= henna.getCancelFee()) {
					activeChar.removeHenna(i);
				} else {
					activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
					getClient().sendPacket(ActionFailed.STATIC_PACKET);
				}
				found = true;
				break;
			}
		}
		// TODO: Test.
		if (!found) {
			log.warn(getClass().getSimpleName() + ": Player " + activeChar + " requested Henna Draw remove without any henna.");
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
		}
	}
}
