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
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * D0 0F 00 5A 00 77 00 65 00 72 00 67 00 00 00
 *
 * @author -Wooden-
 */
public final class RequestExOustFromMPCC extends GameClientPacket {
	private String _name;

	@Override
	public void readImpl() {
		_name = readS();
	}

	@Override
	public void runImpl() {
		Player activeChar = getClient().getActiveChar();
		Player target = WorldManager.getInstance().getPlayer(_name);

		if ((target != null) && target.isInParty() && activeChar.isInParty() && activeChar.getParty().isInCommandChannel() && target.getParty().isInCommandChannel() && activeChar.getParty().getCommandChannel().getLeader().equals(activeChar) && activeChar.getParty().getCommandChannel().equals(target.getParty().getCommandChannel())) {
			if (activeChar.equals(target)) {
				return;
			}

			target.getParty().getCommandChannel().removeParty(target.getParty());

			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_WERE_DISMISSED_FROM_THE_COMMAND_CHANNEL);
			target.getParty().broadcastPacket(sm);

			// check if CC has not been canceled
			if (activeChar.getParty().isInCommandChannel()) {
				sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_PARTY_HAS_BEEN_DISMISSED_FROM_THE_COMMAND_CHANNEL);
				sm.addString(target.getParty().getLeader().getName());
				activeChar.getParty().getCommandChannel().broadcastPacket(sm);
			}
		} else {
			activeChar.sendPacket(SystemMessageId.YOUR_TARGET_CANNOT_BE_FOUND);
		}
	}
}
