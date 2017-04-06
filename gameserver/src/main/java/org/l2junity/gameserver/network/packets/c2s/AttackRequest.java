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

import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.network.PacketReader;

public final class AttackRequest extends GameClientPacket {
	// cddddc
	private int _objectId;
	@SuppressWarnings("unused")
	private int _originX;
	@SuppressWarnings("unused")
	private int _originY;
	@SuppressWarnings("unused")
	private int _originZ;
	@SuppressWarnings("unused")
	private int _attackId;

	@Override
	public void readImpl() {
		_objectId = readD();
		_originX = readD();
		_originY = readD();
		_originZ = readD();
		_attackId = readC(); // 0 for simple click 1 for shift-click
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		// avoid using expensive operations if not needed
		final WorldObject target;
		if (activeChar.getTargetId() == _objectId) {
			target = activeChar.getTarget();
		} else {
			target = activeChar.getWorld().findObject(_objectId);
		}

		if (target == null) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if ((!target.isTargetable() || activeChar.isTargetingDisabled()) && !activeChar.canOverrideCond(PcCondOverride.TARGET_ALL)) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		// Players can't attack objects in the other instances
		else if (target.getInstanceWorld() != activeChar.getInstanceWorld()) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		// Only GMs can directly attack invisible characters
		else if (!target.isVisibleFor(activeChar)) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.getTarget() != target) {
			target.onAction(activeChar);
		} else {
			if ((target.getObjectId() != activeChar.getObjectId()) && (activeChar.getPrivateStoreType() == PrivateStoreType.NONE) && (activeChar.getActiveRequester() == null)) {
				target.onForcedAttack(activeChar);
			} else {
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
}
