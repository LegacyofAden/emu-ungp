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
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExRotation;
import org.l2junity.gameserver.network.packets.s2c.SocialAction;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


/**
 * @author JIV
 */
public class AnswerCoupleAction extends GameClientPacket {
	private int _charObjId;
	private int _actionId;
	private int _answer;

	@Override
	public void readImpl() {
		_actionId = readD();
		_answer = readD();
		_charObjId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		final Player target = WorldManager.getInstance().getMainWorld().getPlayer(_charObjId);
		if ((activeChar == null) || (target == null)) {
			return;
		}
		if ((target.getMultiSocialTarget() != activeChar.getObjectId()) || (target.getMultiSociaAction() != _actionId)) {
			return;
		}
		if (_answer == 0) // cancel
		{
			target.sendPacket(SystemMessageId.THE_COUPLE_ACTION_WAS_DENIED);
		} else if (_answer == 1) // approve
		{
			final int distance = (int) activeChar.distance3d(target);
			if ((distance > 125) || (distance < 15) || (activeChar.getObjectId() == target.getObjectId())) {
				getClient().sendPacket(SystemMessageId.THE_REQUEST_CANNOT_BE_COMPLETED_BECAUSE_THE_TARGET_DOES_NOT_MEET_LOCATION_REQUIREMENTS);
				target.sendPacket(SystemMessageId.THE_REQUEST_CANNOT_BE_COMPLETED_BECAUSE_THE_TARGET_DOES_NOT_MEET_LOCATION_REQUIREMENTS);
				return;
			}
			int heading = Util.calculateHeadingFrom(activeChar, target);
			activeChar.broadcastPacket(new ExRotation(activeChar.getObjectId(), heading));
			activeChar.setHeading(heading);
			heading = Util.calculateHeadingFrom(target, activeChar);
			target.setHeading(heading);
			target.broadcastPacket(new ExRotation(target.getObjectId(), heading));
			activeChar.broadcastPacket(new SocialAction(activeChar.getObjectId(), _actionId));
			target.broadcastPacket(new SocialAction(_charObjId, _actionId));
		} else if (_answer == -1) // refused
		{
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION);
			sm.addPcName(activeChar);
			target.sendPacket(sm);
		}
		target.setMultiSocialAction(0, 0);
	}
}