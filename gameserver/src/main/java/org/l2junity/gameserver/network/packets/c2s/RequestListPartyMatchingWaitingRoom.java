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
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExListPartyMatchingWaitingRoom;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Gnacik
 */
public class RequestListPartyMatchingWaitingRoom extends GameClientPacket {
	private int _page;
	private int _minLevel;
	private int _maxLevel;
	private List<ClassId> _classId; // 1 - waitlist 0 - room waitlist
	private String _query;

	@Override
	public void readImpl() {
		_page = readD();
		_minLevel = readD();
		_maxLevel = readD();
		final int size = readD();

		if ((size > 0) && (size < 128)) {
			_classId = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				_classId.add(ClassId.getClassId(readD()));
			}
		}
		if (getAvailableBytes() > 0) {
			_query = readS();
		}
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		getClient().sendPacket(new ExListPartyMatchingWaitingRoom(activeChar, _page, _minLevel, _maxLevel, _classId, _query));
	}
}