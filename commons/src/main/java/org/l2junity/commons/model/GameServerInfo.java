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
package org.l2junity.commons.model;

import org.l2junity.commons.model.enums.AgeLimit;
import org.l2junity.commons.model.enums.ServerStatus;
import org.l2junity.commons.model.enums.ServerType;
import org.l2junity.commons.rmi.IGameServerRMI;

import java.net.InetAddress;
import java.util.Set;

/**
 * @author NosBit
 */
public class GameServerInfo {
	private final short id;
	private String name;
	private boolean showing;
	private AgeLimit ageLimit;
	private Set<ServerType> serverTypes;

	private InetAddress address;
	private int port;
	private int currentOnline;
	private int maxOnline;

	private ServerStatus status = ServerStatus.DOWN;
	private IGameServerRMI connection;

	public GameServerInfo(short id) {
		this.id = id;
	}

	public GameServerInfo(short id, String name, boolean showing, AgeLimit ageLimit, Set<ServerType> serverTypes) {
		this.id = id;
		this.name = name;
		this.showing = showing;
		this.ageLimit = ageLimit;
		this.serverTypes = serverTypes;
	}

	public void update(IGameServerRMI connection, GameServerInfo gameserver) {
		this.connection = connection;

		this.ageLimit = gameserver.getAgeLimit();
		this.serverTypes = gameserver.getServerTypes();
		this.address = gameserver.getAddress();
		this.port = gameserver.getPort();
		this.currentOnline = gameserver.getCurrentOnline();
		this.maxOnline = gameserver.getMaxOnline();
	}

	public short getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isShowing() {
		return showing;
	}

	public void setAgeLimit(AgeLimit ageLimit) {
		this.ageLimit = ageLimit;
	}

	public AgeLimit getAgeLimit() {
		return ageLimit;
	}

	public void setServerType(int type) {
		serverTypes.clear();
		for (ServerType serverType : ServerType.values()) {
			if ((type & serverType.getMask()) == serverType.getMask()) {
				serverTypes.add(serverType);
			}
		}
	}

	public Set<ServerType> getServerTypes() {
		return serverTypes;
	}

	public int getServerTypesMask() {
		return serverTypes.stream().mapToInt(ServerType::getMask).reduce((r, e) -> r | e).orElse(0);
	}

	public IGameServerRMI getConnection() {
		return connection;
	}

	public void setConnection(IGameServerRMI connection) {
		this.connection = connection;
	}

	public ServerStatus getStatus() {
		return status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getCurrentOnline() {
		return currentOnline;
	}

	public void setCurrentOnline(int currentOnline) {
		this.currentOnline = currentOnline;
	}

	public int getMaxOnline() {
		return maxOnline;
	}

	public void setMaxOnline(int maxOnline) {
		this.maxOnline = maxOnline;
	}
}