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
package org.l2junity.gameserver.model.holders;

import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * @author Sdw
 */
public class NpcLogListHolder {
	private final int _id;
	private final boolean _isNpcString;
	private final int _count;

	public NpcLogListHolder(NpcStringId npcStringId, int count) {
		_id = npcStringId.getId();
		_isNpcString = true;
		_count = count;
	}

	public NpcLogListHolder(int id, boolean isNpcString, int count) {
		_id = id;
		_isNpcString = isNpcString;
		_count = count;
	}

	public int getId() {
		return _id;
	}

	public boolean isNpcString() {
		return _isNpcString;
	}

	public int getCount() {
		return _count;
	}
}
