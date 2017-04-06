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
import org.l2junity.gameserver.data.xml.impl.ActionData;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.handler.IPlayerActionHandler;
import org.l2junity.gameserver.handler.PlayerActionHandler;
import org.l2junity.gameserver.model.ActionDataHolder;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerUseAction;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ExBasicActionList;
import org.l2junity.gameserver.network.packets.s2c.RecipeShopManageList;

import java.util.Arrays;

/**
 * This class manages the action use request packet.
 *
 * @author Zoey76
 */
@Slf4j
public final class RequestActionUse extends GameClientPacket {
	private int _actionId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;

	@Override
	public void readImpl() {
		_actionId = readD();
		_ctrlPressed = (readD() == 1);
		_shiftPressed = (readC() == 1);
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerUseAction(activeChar, _actionId, _ctrlPressed, _shiftPressed), activeChar, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isSpawnProtected() && (_actionId != 10) && (_actionId != 28)) {
			activeChar.onActionRequest();
		}

		// Don't do anything if player is dead or confused
		if ((activeChar.isFakeDeath() && (_actionId != 0)) || activeChar.isDead() || activeChar.isControlBlocked()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Don't allow to do some action if player is transformed
		if (activeChar.isTransformed()) {
			int[] allowedActions = activeChar.isTransformed() ? ExBasicActionList.ACTIONS_ON_TRANSFORM : ExBasicActionList.DEFAULT_ACTION_LIST;
			if (!(Arrays.binarySearch(allowedActions, _actionId) >= 0)) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				log.warn("Player " + activeChar + " used action which he does not have! Id = " + _actionId + " transform: " + String.valueOf(activeChar.getTransformation().orElse(null)));
				return;
			}
		}

		final ActionDataHolder actionHolder = ActionData.getInstance().getActionData(_actionId);
		if (actionHolder != null) {
			final IPlayerActionHandler actionHandler = PlayerActionHandler.getInstance().getHandler(actionHolder.getHandler());
			if (actionHandler != null) {
				actionHandler.useAction(activeChar, actionHolder, _ctrlPressed, _shiftPressed);
				return;
			}
			log.warn("Couldnt find handler with name: {}", actionHolder.getHandler());
			return;
		}

		switch (_actionId) {
			case 51: // General Manufacture
				// Player shouldn't be able to set stores if he/she is alike dead (dead or fake death)
				if (activeChar.isAlikeDead()) {
					getClient().sendPacket(ActionFailed.STATIC_PACKET);
					return;
				}

				if (activeChar.isSellingBuffs()) {
					getClient().sendPacket(ActionFailed.STATIC_PACKET);
					return;
				}

				if (activeChar.getPrivateStoreType() != PrivateStoreType.NONE) {
					activeChar.setPrivateStoreType(PrivateStoreType.NONE);
					activeChar.broadcastUserInfo();
				}
				if (activeChar.isSitting()) {
					activeChar.standUp();
				}

				getClient().sendPacket(new RecipeShopManageList(activeChar, false));
				break;
			default:
				log.warn(activeChar.getName() + ": unhandled action type " + _actionId);
				break;
		}
	}
}
