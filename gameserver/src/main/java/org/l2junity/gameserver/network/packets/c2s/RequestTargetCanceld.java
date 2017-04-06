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

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.TargetUnselected;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


public final class RequestTargetCanceld extends GameClientPacket {
	private int _unselect;

	@Override
	public void readImpl() {
		_unselect = readH();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (activeChar.isLockedTarget()) {
			activeChar.sendPacket(SystemMessageId.FAILED_TO_REMOVE_ENMITY);
			return;
		}

		if (_unselect == 0) {
			// Try to abort cast, if that fails, then cancel target.
			final boolean castAborted = activeChar.abortCast();
			if (!castAborted && (activeChar.getTarget() != null)) {
				activeChar.setTarget(null);
			}
		} else if (activeChar.getTarget() != null) {
			activeChar.setTarget(null);
		} else if (activeChar.isInAirShip()) {
			activeChar.broadcastPacket(new TargetUnselected(activeChar));
		}
	}
}
