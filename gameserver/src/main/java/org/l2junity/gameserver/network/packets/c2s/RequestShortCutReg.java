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

import org.l2junity.gameserver.enums.ShortcutType;
import org.l2junity.gameserver.model.Shortcut;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ShortCutRegister;


public final class RequestShortCutReg extends GameClientPacket {
	private ShortcutType _type;
	private int _id;
	private int _slot;
	private int _page;
	private int _lvl;
	private int _subLvl;
	private int _characterType; // 1 - player, 2 - pet

	@Override
	public void readImpl() {
		final int typeId = readD();
		_type = ShortcutType.values()[(typeId < 1) || (typeId > 6) ? 0 : typeId];
		final int slot = readD();
		_slot = slot % 12;
		_page = slot / 12;
		_id = readD();
		_lvl = readH();
		_subLvl = readH(); // Sublevel
		_characterType = readD();
	}

	@Override
	public void runImpl() {
		if ((getClient().getActiveChar() == null) || (_page > 10) || (_page < 0)) {
			return;
		}

		final Shortcut sc = new Shortcut(_slot, _page, _type, _id, _lvl, _subLvl, _characterType);
		getClient().getActiveChar().registerShortCut(sc);
		getClient().sendPacket(new ShortCutRegister(sc));
	}
}
