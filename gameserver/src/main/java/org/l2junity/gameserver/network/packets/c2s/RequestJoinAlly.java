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

import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerInviteToAlly;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.AskJoinAlly;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

public final class RequestJoinAlly extends GameClientPacket {
	private int _objectId;

	@Override
	public void readImpl() {
		_objectId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final Player target = WorldManager.getInstance().getPlayer(_objectId);

		if (target == null) {
			activeChar.sendPacket(SystemMessageId.YOU_HAVE_INVITED_THE_WRONG_TARGET);
			return;
		}

		final Clan clan = activeChar.getClan();

		if (clan == null) {
			activeChar.sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER_AND_CANNOT_PERFORM_THIS_ACTION);
			return;
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerInviteToAlly(activeChar, target), activeChar, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (!clan.checkAllyJoinCondition(activeChar, target)) {
			return;
		}
		if (!activeChar.getRequest().setRequest(target, this)) {
			return;
		}

		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_LEADER_S2_HAS_REQUESTED_AN_ALLIANCE);
		sm.addString(activeChar.getClan().getAllyName());
		sm.addString(activeChar.getName());
		target.sendPacket(sm);
		target.sendPacket(new AskJoinAlly(activeChar.getObjectId(), activeChar.getClan().getAllyName(), activeChar.getName()));
	}
}
