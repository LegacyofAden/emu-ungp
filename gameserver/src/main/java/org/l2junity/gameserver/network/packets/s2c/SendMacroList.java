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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.enums.MacroUpdateType;
import org.l2junity.gameserver.model.Macro;
import org.l2junity.gameserver.model.MacroCmd;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class SendMacroList extends GameServerPacket {
	private final int _count;
	private final Macro _macro;
	private final MacroUpdateType _updateType;

	public SendMacroList(int count, Macro macro, MacroUpdateType updateType) {
		_count = count;
		_macro = macro;
		_updateType = updateType;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MACRO_LIST.writeId(body);

		body.writeC(_updateType.getId());
		body.writeD(_updateType != MacroUpdateType.LIST ? _macro.getId() : 0x00); // modified, created or deleted macro's id
		body.writeC(_count); // count of Macros
		body.writeC(_macro != null ? 1 : 0); // unknown

		if ((_macro != null) && (_updateType != MacroUpdateType.DELETE)) {
			body.writeD(_macro.getId()); // Macro ID
			body.writeS(_macro.getName()); // Macro Name
			body.writeS(_macro.getDescr()); // Desc
			body.writeS(_macro.getAcronym()); // acronym
			body.writeD(_macro.getIcon()); // icon

			body.writeC(_macro.getCommands().size()); // count

			int i = 1;
			for (MacroCmd cmd : _macro.getCommands()) {
				body.writeC(i++); // command count
				body.writeC(cmd.getType().ordinal()); // type 1 = skill, 3 = action, 4 = shortcut
				body.writeD(cmd.getD1()); // skill id
				body.writeC(cmd.getD2()); // shortcut id
				body.writeS(cmd.getCmd()); // command name
			}
		}
	}
}