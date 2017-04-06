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

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.handler.IPlayerActionHandler;
import org.l2junity.gameserver.handler.PlayerActionHandler;
import org.l2junity.gameserver.model.ActionDataHolder;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * Servitor move to target player action handler.
 *
 * @author Nik
 */
public final class ServitorMove implements IPlayerActionHandler {
	@Override
	public void useAction(Player activeChar, ActionDataHolder data, boolean ctrlPressed, boolean shiftPressed) {
		if (!activeChar.hasServitors()) {
			activeChar.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_A_SERVITOR);
			return;
		}

		if (activeChar.getTarget() != null) {
			activeChar.getServitors().values().stream().filter(s -> (s != activeChar.getTarget()) && !s.isMovementDisabled()).forEach(s ->
			{
				if (s.isBetrayed()) {
					activeChar.sendPacket(SystemMessageId.YOUR_PET_SERVITOR_IS_UNRESPONSIVE_AND_WILL_NOT_OBEY_ANY_ORDERS);
					return;
				}

				s.setFollowStatus(false);
				s.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, activeChar.getTarget().getLocation());
			});
		}
	}

	public static void main(String[] args) {
		PlayerActionHandler.getInstance().registerHandler(new ServitorMove());
	}
}
