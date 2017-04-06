/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.usercommandhandlers;

import org.l2junity.gameserver.handler.IUserCommandHandler;
import org.l2junity.gameserver.handler.UserCommandHandler;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.Calendar;

/**
 * My Birthday user command.
 *
 * @author JIV
 */
public class MyBirthday implements IUserCommandHandler {
	private static final int[] COMMAND_IDS =
			{
					126
			};

	@Override
	public boolean useUserCommand(int id, Player activeChar) {
		if (id != COMMAND_IDS[0]) {
			return false;
		}

		Calendar date = activeChar.getCreateDate();

		SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_S_BIRTHDAY_IS_S3_S4_S2);
		sm.addPcName(activeChar);
		sm.addString(Integer.toString(date.get(Calendar.YEAR)));
		sm.addString(Integer.toString(date.get(Calendar.MONTH) + 1));
		sm.addString(Integer.toString(date.get(Calendar.DATE)));

		activeChar.sendPacket(sm);
		return true;
	}

	@Override
	public int[] getUserCommandList() {
		return COMMAND_IDS;
	}

	public static void main(String[] args) {
		UserCommandHandler.getInstance().registerHandler(new MyBirthday());
	}
}