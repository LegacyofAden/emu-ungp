/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.model.debugger;

import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnAfraid
 */
public class Debugger {
	private final int _objectId;
	private final String _name;
	private final Set<Integer> _types = ConcurrentHashMap.newKeySet();

	public Debugger(Player player) {
		_objectId = player.getObjectId();
		_name = player.getName();
	}

	public int getObjectId() {
		return _objectId;
	}

	public String getName() {
		return _name;
	}

	public Player getPlayer() {
		return World.getInstance().getPlayer(_objectId);
	}

	public boolean hasDebugType(DebugType... types) {
		if (types != null) {
			for (DebugType type : types) {
				if (_types.contains(type.ordinal())) {
					return true;
				}
			}
		}
		return false;
	}

	public void addDebugType(DebugType... types) {
		if (types != null) {
			for (DebugType type : types) {
				_types.add(type.ordinal());
			}
		}
	}

	public void sendPacket(IClientOutgoingPacket... packets) {
		final Player player = getPlayer();
		if (player != null) {
			for (IClientOutgoingPacket packet : packets) {
				player.sendPacket(packet);
			}
		}
	}

	public void sendMessage(String message) {
		final Player player = getPlayer();
		if (player != null) {
			player.sendMessage(message);
		}
	}
}
