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
package org.l2junity.gameserver.instancemanager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.GameServer;
import org.l2junity.gameserver.data.HtmRepository;
import org.l2junity.gameserver.data.xml.impl.MultiboxData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.Containers;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerLogout;
import org.l2junity.gameserver.model.events.listeners.ConsumerEventListener;
import org.l2junity.gameserver.model.multibox.MultiboxHolder;
import org.l2junity.gameserver.model.multibox.MultiboxSettings;
import org.l2junity.gameserver.model.multibox.MultiboxSourceType;
import org.l2junity.gameserver.model.olympiad.Olympiad;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Service")
public class MultiboxManager {
	@Getter(lazy = true)
	private static final MultiboxManager instance = new MultiboxManager();

	private final Map<Class, MultiboxHolder> _holders = new ConcurrentHashMap<>();

	private MultiboxManager() {
		Containers.Global().addListener(new ConsumerEventListener(Containers.Global(), EventType.ON_PLAYER_LOGOUT, event -> onPlayerLogout((OnPlayerLogout) event), this));
		registerManager(GameServer.class);
		registerManager(Olympiad.class);
	}

	/**
	 * @param clazz
	 */
	public void registerManager(Class clazz) {
		final MultiboxSettings settings = MultiboxData.getInstance().getSettings(clazz.getSimpleName());
		if (settings != null) {
			_holders.put(clazz, new MultiboxHolder(settings));
		} else {
			log.warn("Attempt to register manager ({}) with non-existent settings:", clazz.getSimpleName(), new UnsupportedOperationException());
		}
	}

	/**
	 * @param clazz
	 */
	public void unregisterManager(Class clazz) {
		_holders.remove(clazz);
	}

	/**
	 * @param clazz
	 */
	public void clearManager(Class clazz) {
		if (_holders.containsKey(clazz)) {
			_holders.get(clazz).clear();
		} else {
			log.warn("Attempt to clear non-existent manager ({}):", clazz.getSimpleName(), new UnsupportedOperationException());
		}
	}

	/**
	 * @param clazz
	 * @param client
	 * @return {@code true} if player does not excess maximum amount of dual boxes, {@code false} otherwise.
	 */
	public boolean canRegisterClient(Class clazz, L2GameClient client) {
		if (_holders.containsKey(clazz)) {
			return _holders.get(clazz).canAddClient(client);
		}
		log.warn("Attempt to check for registered client on non-existent manager ({}):", clazz.getSimpleName(), new UnsupportedOperationException());
		return false;
	}

	/**
	 * @param clazz
	 * @param client
	 * @return
	 */
	public boolean registerClient(Class clazz, L2GameClient client) {
		if (_holders.containsKey(clazz)) {
			return _holders.get(clazz).addClient(client);
		}
		log.warn("Attempt to register client on non-existent manager ({}):", clazz.getClass().getSimpleName(), new UnsupportedOperationException());
		return false;
	}

	/**
	 * @param clazz
	 * @param client
	 */
	public void unregisterClient(Class clazz, L2GameClient client) {
		if (_holders.containsKey(clazz)) {
			_holders.get(clazz).removeClient(client);
		} else {
			log.warn("Attempt to unregister client on non-existent manager ({}):", clazz.getClass().getSimpleName(), new UnsupportedOperationException());
		}
	}

	/**
	 * @param clazz
	 * @param client
	 * @return
	 */
	public int getCurrentConnections(Class clazz, L2GameClient client) {
		if (_holders.containsKey(clazz)) {
			return _holders.get(clazz).getCurrentConnections(client);
		}
		log.warn("Attempt to get current connections of client ({}) on non-existent manager ({}):", client, clazz.getSimpleName(), new UnsupportedOperationException());
		return 0;
	}

	/**
	 * @param clazz
	 * @return
	 */
	public int getMaximumConnectionsLimit(Class clazz) {
		if (_holders.containsKey(clazz)) {
			return _holders.get(clazz).getSettings().getMaxClients();
		}
		log.warn("Attempt to get max connections limit on non-existent manager ({}):", clazz.getSimpleName(), new UnsupportedOperationException());
		return 0;
	}

	private void onPlayerLogout(OnPlayerLogout event) {
		final Player player = event.getActiveChar();
		final L2GameClient client = event.getClient();
		if ((player == null) || (client == null)) {
			log.warn("Player or client is null, {}, {}", player, client);
			return;
		}

		for (MultiboxHolder holder : _holders.values()) {
			if (holder.getSettings().unregisterOnDisconnected()) {
				holder.removeClient(client);
			}
			holder.clearDisconnected();
		}
	}

	/**
	 * @param clazz
	 * @param client
	 * @return Set of multiboxes of current client.
	 */
	public Set<L2GameClient> getMultiboxesOf(Class clazz, L2GameClient client) {
		if (!_holders.containsKey(clazz)) {
			return Collections.emptySet();
		}

		final MultiboxHolder holder = _holders.get(clazz);
		final MultiboxSourceType type = holder.getSettings().getProtectionType();

		int hash = type.generateHash(client);
		if ((hash == 0) || !holder.getAllClients().containsKey(hash)) {
			return Collections.emptySet();
		}
		return Collections.unmodifiableSet(holder.getAllClients().get(hash));
	}

	/**
	 * @param clazz
	 * @param client
	 */
	public void sendDefaultRestrictionMessage(Class clazz, L2GameClient client) {
		final MultiboxHolder holder = _holders.get(clazz);
		if (holder == null) {
			log.warn("Attempt to send default restriction message on non-existent manager ({}):", clazz.getSimpleName(), new UnsupportedOperationException());
			return;
		}

		final String html = HtmRepository.getInstance().getCustomHtm(holder.getSettings().getRestrictionHtmlFile());
		if (html == null) {
			log.warn("Restriction html file is missing: {} !", holder.getSettings().getRestrictionHtmlFile());
			return;
		}

		final StringBuilder sb = new StringBuilder();
		final Set<L2GameClient> clients = getMultiboxesOf(clazz, client);
		if (!clients.isEmpty()) {
			sb.append("<table width=\"100%\">");
			for (L2GameClient multiboxClient : getMultiboxesOf(clazz, client)) {
				if (multiboxClient.getActiveChar() != null) {
					sb.append("<tr><td>Character: <font color=\"LEVEL\">" + multiboxClient.getActiveChar().getName() + "</font></td></tr>");
				} else {
					sb.append("<tr><td>Account: <font color=\"LEVEL\">" + multiboxClient.getAccountName() + "</font></td></tr>");
				}
			}
			sb.append("</table>");
		}
		final NpcHtmlMessage msg = new NpcHtmlMessage(html);
		msg.replace("%max%", Integer.toString(getMaximumConnectionsLimit(clazz)));
		msg.replace("%type%", holder.getSettings().getProtectionType().name());
		msg.replace("%clients%", sb.toString());
		client.sendPacket(msg);
	}
}
