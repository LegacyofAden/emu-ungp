/*
 * Copyright (C) 2004-2013 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.multibox;

import org.l2junity.gameserver.network.GameClient;


import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnAfraid
 */
public class MultiboxHolder {
	private final MultiboxSettings _settings;
	private final Map<Integer, Set<GameClient>> _clients = new ConcurrentHashMap<>();

	public MultiboxHolder(MultiboxSettings settings) {
		_settings = settings;
	}

	/**
	 * @return
	 */
	public MultiboxSettings getSettings() {
		return _settings;
	}

	/**
	 * @param client
	 * @return
	 */
	public boolean canAddClient(GameClient client) {
		if (client == null) {
			return false;
		} else if (!getSettings().isEnabled()) {
			return true;
		}

		int hash = getSettings().getProtectionType().generateHash(client);
		if (hash == 0) {
			return true;
		}

		if (_clients.containsKey(hash)) {
			int whiteListed = getSettings().getWhitelistBonus(hash);
			int maxConnections = ((whiteListed > 0) ? whiteListed : getSettings().getMaxClients());
			return ((maxConnections < 1) || (maxConnections > _clients.get(hash).size()));
		}
		return true;
	}

	/**
	 * @param client
	 * @return
	 */
	public boolean addClient(GameClient client) {
		if (client == null) {
			return false;
		} else if (!getSettings().isEnabled()) {
			return true;
		}

		boolean canAddClient = canAddClient(client);
		if (canAddClient) {
			int hash = getSettings().getProtectionType().generateHash(client);
			if (hash == 0) {
				return true;
			}

			_clients.computeIfAbsent(hash, key -> ConcurrentHashMap.newKeySet()).add(client);
		}
		return canAddClient;
	}

	/**
	 * @param client
	 */
	public void removeClient(GameClient client) {
		if ((client == null) || !getSettings().isEnabled()) {
			return;
		}

		int hash = getSettings().getProtectionType().generateHash(client);
		if (hash == 0) {
			return;
		}

		final Set<GameClient> dualboxClients = _clients.get(hash);
		if ((dualboxClients != null) && dualboxClients.contains(client)) {
			dualboxClients.remove(client);
			if (dualboxClients.isEmpty()) {
				_clients.remove(hash);
			}
		}
	}

	/**
	 * @param client
	 * @return
	 */
	public int getCurrentConnections(GameClient client) {
		if ((client != null) && getSettings().isEnabled()) {
			int key = getSettings().getProtectionType().generateHash(client);
			if (_clients.containsKey(key)) {
				return _clients.get(key).size();
			}
		}
		return 0;
	}

	/**
	 * Clears all the clients from the holder.
	 */
	public void clear() {
		_clients.clear();
	}

	/**
	 * @return map containing all multibox clients.
	 */
	public Map<Integer, Set<GameClient>> getAllClients() {
		return _clients;
	}

	/**
	 * Clearing all un-active registered clients.
	 */
	public void clearDisconnected() {
		if (_clients.isEmpty() || !getSettings().isEnabled() || !getSettings().clearOnDisconnected()) {
			return;
		}

		final Iterator<Set<GameClient>> it = _clients.values().iterator();
		while (it.hasNext()) {
			final Set<GameClient> clients = it.next();
			clients.removeIf(client -> (client == null) || (client.getActiveChar() == null));
			if (clients.isEmpty()) {
				it.remove();
			}
		}
	}
}
