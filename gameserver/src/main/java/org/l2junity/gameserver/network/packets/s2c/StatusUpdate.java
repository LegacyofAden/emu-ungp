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
import org.l2junity.gameserver.enums.StatusUpdateType;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class StatusUpdate extends GameServerPacket {
	private final int _objectId;
	private int _casterObjectId = 0;
	private final boolean _isPlayable;
	private boolean _isVisible = false;
	private final Map<StatusUpdateType, Integer> _updates = new LinkedHashMap<>();

	/**
	 * Create {@link StatusUpdate} packet for given {@link WorldObject}.
	 *
	 * @param object
	 */
	public StatusUpdate(WorldObject object) {
		_objectId = object.getObjectId();
		_isPlayable = object.isPlayable();
	}

	public void addUpdate(StatusUpdateType type, int level) {
		_updates.put(type, level);

		if (_isPlayable) {
			switch (type) {
				case CUR_HP:
				case CUR_MP:
				case CUR_CP: {
					_isVisible = true;
				}
			}
		}
	}

	public void addCaster(WorldObject object) {
		_casterObjectId = object.getObjectId();
	}

	public boolean hasUpdates() {
		return !_updates.isEmpty();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.STATUS_UPDATE.writeId(body);

		body.writeD(_objectId); // casterId
		body.writeD(_isVisible ? _casterObjectId : 0x00);
		body.writeC(_isVisible ? 0x01 : 0x00);
		body.writeC(_updates.size());
		for (Entry<StatusUpdateType, Integer> entry : _updates.entrySet()) {
			body.writeC(entry.getKey().getClientId());
			body.writeD(entry.getValue());
		}
	}
}
