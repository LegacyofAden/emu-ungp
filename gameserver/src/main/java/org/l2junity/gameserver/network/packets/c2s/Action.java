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
import org.l2junity.core.configs.NpcConfig;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

@Slf4j
public final class Action extends GameClientPacket {
	private int _objectId;
	private int _originX;
	private int _originY;
	private int _originZ;
	private int _actionId;

	@Override
	public void readImpl() {
		_objectId = readD(); // Target object Identifier
		_originX = readD();
		_originY = readD();
		_originZ = readD();
		_actionId = readC(); // Action identifier : 0-Simple click, 1-Shift click
	}

	@Override
	public void runImpl() {
		// Get the current L2PcInstance of the player
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (activeChar.inObserverMode()) {
			activeChar.sendPacket(SystemMessageId.OBSERVERS_CANNOT_PARTICIPATE);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final WorldObject obj;
		if (activeChar.getTargetId() == _objectId) {
			obj = activeChar.getTarget();
		} else if (activeChar.isInAirShip() && (activeChar.getAirShip().getHelmObjectId() == _objectId)) {
			obj = activeChar.getAirShip();
		} else {
			obj = activeChar.getWorld().findObject(_objectId);
		}

		// If object requested does not exist, add warn msg into logs
		if (obj == null) {
			// pressing e.g. pickup many times quickly would get you here
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if ((!obj.isTargetable() || activeChar.isTargetingDisabled()) && !activeChar.canOverrideCond(PcCondOverride.TARGET_ALL)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Players can't interact with objects in the other instances
		if (obj.getInstanceWorld() != activeChar.getInstanceWorld()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Only GMs can directly interact with invisible characters
		if (!obj.isVisibleFor(activeChar)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Check if the target is valid, if the player haven't a shop or isn't the requester of a transaction (ex : FriendInvite, JoinAlly, JoinParty...)
		if (activeChar.getActiveRequester() != null) {
			// Actions prohibited when in trade
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		switch (_actionId) {
			case 0: {
				obj.onAction(activeChar);
				break;
			}
			case 1: {
				if (!activeChar.isGM() && !(obj.isNpc() && NpcConfig.ALT_GAME_VIEWNPC)) {
					obj.onAction(activeChar, false);
				} else {
					obj.onActionShift(activeChar);
				}
				break;
			}
			default: {
				// Invalid action detected (probably client cheating), log this
				log.warn(getClass().getSimpleName() + ": Character: " + activeChar.getName() + " requested invalid action: " + _actionId);
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				break;
			}
		}
	}
}
