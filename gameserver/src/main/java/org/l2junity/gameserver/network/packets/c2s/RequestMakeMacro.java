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
import org.l2junity.gameserver.enums.MacroType;
import org.l2junity.gameserver.model.Macro;
import org.l2junity.gameserver.model.MacroCmd;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class RequestMakeMacro extends GameClientPacket {
	private Macro _macro;
	private int _commandsLenght = 0;

	private static final int MAX_MACRO_LENGTH = 12;

	@Override
	public void readImpl() {
		final int id = readD();
		final String name = readS();
		final String desc = readS();
		final String acronym = readS();
		final int icon = readD();
		int count = readC();
		if (count > MAX_MACRO_LENGTH) {
			count = MAX_MACRO_LENGTH;
		}

		final List<MacroCmd> commands = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			final int entry = readC();
			final int type = readC(); // 1 = skill, 3 = action, 4 = shortcut
			if ((type < 1) || (type > 6)) {
				log.warn("Client: {} Sent unhandled macro type: {} within packet: {}", getClient(), type, getClass().getSimpleName());
				return;
			}
			final int skillId = readD(); // skill or page number for shortcuts
			final int shortcutId = readC();
			String command = readS();
			_commandsLenght += command.length();
			commands.add(new MacroCmd(entry, MacroType.values()[type], skillId, shortcutId, command));
		}
		_macro = new Macro(id, icon, name, desc, acronym, commands);
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}
		if (_commandsLenght > 255) {
			// Invalid macro. Refer to the Help file for instructions.
			player.sendPacket(SystemMessageId.INVALID_MACRO_REFER_TO_THE_HELP_FILE_FOR_INSTRUCTIONS);
			return;
		}
		if (player.getMacros().getAllMacroses().size() > 48) {
			// You may create up to 48 macros.
			player.sendPacket(SystemMessageId.YOU_MAY_CREATE_UP_TO_48_MACROS);
			return;
		}
		if (_macro.getName().isEmpty()) {
			// Enter the name of the macro.
			player.sendPacket(SystemMessageId.ENTER_THE_NAME_OF_THE_MACRO);
			return;
		}
		if (_macro.getDescr().length() > 32) {
			// Macro descriptions may contain up to 32 characters.
			player.sendPacket(SystemMessageId.MACRO_DESCRIPTIONS_MAY_CONTAIN_UP_TO_32_CHARACTERS);
			return;
		}
		player.registerMacro(_macro);
	}
}