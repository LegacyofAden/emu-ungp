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
package org.l2junity.gameserver.network;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerLogout;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;

import java.util.concurrent.TimeUnit;

/**
 * @author NB4L1
 */
@Slf4j
public final class Disconnection {
	public static GameClient getClient(GameClient client, Player activeChar) {
		if (client != null) {
			return client;
		}

		if (activeChar != null) {
			return activeChar.getClient();
		}

		return null;
	}

	public static Player getActiveChar(GameClient client, Player activeChar) {
		if (activeChar != null) {
			return activeChar;
		}

		if (client != null) {
			return client.getActiveChar();
		}

		return null;
	}

	private final GameClient gameClient;
	private final Player activePlayer;

	private Disconnection(GameClient client) {
		this(client, null);
	}

	public static Disconnection of(GameClient client) {
		return new Disconnection(client);
	}

	private Disconnection(Player activeChar) {
		this(null, activeChar);
	}

	public static Disconnection of(Player activeChar) {
		return new Disconnection(activeChar);
	}

	private Disconnection(GameClient client, Player activePlayer) {
		gameClient = getClient(client, activePlayer);
		this.activePlayer = getActiveChar(client, activePlayer);

		if (gameClient != null) {
			gameClient.setActiveChar(null);
		}

		if (this.activePlayer != null) {
			this.activePlayer.setClient(null);
		}
	}

	public static Disconnection of(GameClient client, Player activeChar) {
		return new Disconnection(client, activeChar);
	}

	public Disconnection storeMe() {
		try {
			if (activePlayer != null) {
				activePlayer.storeMe();
			}
		} catch (final RuntimeException e) {
			log.warn("Error while storeMe()", e);
		}

		return this;
	}

	public Disconnection deleteMe() {
		try {
			if (activePlayer != null) {
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerLogout(activePlayer, gameClient), activePlayer);
				activePlayer.deleteMe();
			}
		} catch (final RuntimeException e) {
			log.warn("Error while deleteMe()", e);
		}

		return this;
	}

	public Disconnection close(boolean toLoginScreen) {
		if (gameClient != null) {
			gameClient.close(toLoginScreen);
		}

		return this;
	}

	public Disconnection close(GameServerPacket packet) {
		if (gameClient != null) {
			gameClient.close(packet);
		}

		return this;
	}

	public void defaultSequence(boolean toLoginScreen) {
		defaultSequence();
		close(toLoginScreen);
	}

	public void defaultSequence(GameServerPacket packet) {
		defaultSequence();
		close(packet);
	}

	private void defaultSequence() {
		storeMe();
		deleteMe();
	}

	public void onDisconnection() {
		if (activePlayer != null) {
			ThreadPool.getInstance().scheduleGeneral(this::defaultSequence, activePlayer.canLogout() ? 0 : AttackStanceTaskManager.COMBAT_TIME, TimeUnit.MILLISECONDS);
		}
	}
}