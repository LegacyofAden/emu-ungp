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

import org.l2junity.core.configs.GameserverConfig;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExIsCharNameCreatable;
import org.l2junity.gameserver.util.Util;


/**
 * @author UnAfraid
 */
public class RequestCharacterNameCreatable extends GameClientPacket {
	private String _name;
	private int result;

	public static int CHARACTER_CREATE_FAILED = 1;
	public static int NAME_ALREADY_EXISTS = 2;
	public static int INVALID_LENGTH = 3;
	public static int INVALID_NAME = 4;
	public static int CANNOT_CREATE_SERVER = 5;

	@Override
	public void readImpl() {
		_name = readS();
	}

	@Override
	public void runImpl() {
		final int charId = CharNameTable.getInstance().getIdByName(_name);

		if (!Util.isAlphaNumeric(_name) || !isValidName(_name)) {
			result = INVALID_NAME;
		} else if (charId > 0) {
			result = NAME_ALREADY_EXISTS;
		} else if (_name.length() > 16) {
			result = INVALID_LENGTH;
		} else {
			result = -1;
		}

		getClient().sendPacket(new ExIsCharNameCreatable(result));
	}

	private boolean isValidName(String text) {
		return GameserverConfig.CHARNAME_TEMPLATE_PATTERN.matcher(text).matches();
	}
}