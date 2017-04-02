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
package handlers.playeractions;

import org.l2junity.gameserver.ai.CtrlEvent;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.ai.NextAction;
import org.l2junity.gameserver.enums.MountType;
import org.l2junity.gameserver.handler.IPlayerActionHandler;
import org.l2junity.gameserver.handler.PlayerActionHandler;
import org.l2junity.gameserver.model.ActionDataHolder;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.L2StaticObjectInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.ChairSit;

/**
 * Sit/Stand player action handler.
 *
 * @author UnAfraid
 */
public final class SitStand implements IPlayerActionHandler {
	@Override
	public void useAction(PlayerInstance activeChar, ActionDataHolder data, boolean ctrlPressed, boolean shiftPressed) {
		if (activeChar.isSitting() || !activeChar.isMoving() || activeChar.isFakeDeath()) {
			useSit(activeChar, activeChar.getTarget());
		} else {
			// Sit when arrive using next action.
			// Creating next action class.
			final NextAction nextAction = new NextAction(CtrlEvent.EVT_ARRIVED, CtrlIntention.AI_INTENTION_MOVE_TO, () -> useSit(activeChar, activeChar.getTarget()));

			// Binding next action to AI.
			activeChar.getAI().setNextAction(nextAction);
		}
	}

	/**
	 * Use the sit action.
	 *
	 * @param activeChar the player trying to sit
	 * @param target     the target to sit, throne, bench or chair
	 * @return {@code true} if the player can sit, {@code false} otherwise
	 */
	protected boolean useSit(PlayerInstance activeChar, WorldObject target) {
		if (activeChar.getMountType() != MountType.NONE) {
			return false;
		}

		if (!activeChar.isSitting() && (target instanceof L2StaticObjectInstance) && (((L2StaticObjectInstance) target).getType() == 1) && activeChar.isInRadius2d(target, L2StaticObjectInstance.INTERACTION_DISTANCE)) {
			final ChairSit cs = new ChairSit(activeChar, target.getId());
			activeChar.sendPacket(cs);
			activeChar.sitDown();
			activeChar.broadcastPacket(cs);
			return true;
		}

		if (activeChar.isFakeDeath()) {
			activeChar.stopFakeDeath(true);
		} else if (activeChar.isSitting()) {
			activeChar.standUp();
		} else {
			activeChar.sitDown();
		}
		return true;
	}

	public static void main(String[] args) {
		PlayerActionHandler.getInstance().registerHandler(new SitStand());
	}
}
