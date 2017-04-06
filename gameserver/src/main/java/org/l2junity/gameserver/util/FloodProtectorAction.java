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
package org.l2junity.gameserver.util;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.instancemanager.PunishmentManager;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.punishment.PunishmentAffect;
import org.l2junity.gameserver.model.punishment.PunishmentTask;
import org.l2junity.gameserver.model.punishment.PunishmentType;
import org.l2junity.gameserver.network.Disconnection;
import org.l2junity.gameserver.network.GameClient;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Flood protector implementation.
 *
 * @author fordfrog
 */
@Slf4j
public final class FloodProtectorAction {
	/**
	 * Client for this instance of flood protector.
	 */
	private final GameClient client;
	/**
	 * Configuration of this instance of flood protector.
	 */
	private final FloodProtectorConfigEntry config;
	/**
	 * Next game tick when new request is allowed.
	 */
	private volatile int nextGameTick = GameTimeManager.getInstance().getGameTicks();
	/**
	 * Request counter.
	 */
	private final AtomicInteger count = new AtomicInteger(0);
	/**
	 * Flag determining whether exceeding request has been logged.
	 */
	private boolean logged;
	/**
	 * Flag determining whether punishment application is in progress so that we do not apply punisment multiple times (flooding).
	 */
	private volatile boolean punishmentInProgress;

	/**
	 * Creates new instance of FloodProtectorAction.
	 *
	 * @param client the game client for which flood protection is being created
	 * @param config flood protector configuration
	 */
	public FloodProtectorAction(final GameClient client, final FloodProtectorConfigEntry config) {
		super();
		this.client = client;
		this.config = config;
	}

	/**
	 * Checks whether the request is flood protected or not.
	 *
	 * @param command command issued or short command description
	 * @return true if action is allowed, otherwise false
	 */
	public boolean tryPerformAction(final String command) {
		if (config == null) {
			return true;
		}

		final int curTick = GameTimeManager.getInstance().getGameTicks();

		if ((client.getActiveChar() != null) && client.getActiveChar().canOverrideCond(PcCondOverride.FLOOD_CONDITIONS)) {
			return true;
		}

		if ((curTick < nextGameTick) || punishmentInProgress) {
			if (config.LOG_FLOODING && !logged && log.isWarnEnabled()) {
				log(" called command ", command, " ~", String.valueOf((config.FLOOD_PROTECTION_INTERVAL - (nextGameTick - curTick)) * GameTimeManager.MILLIS_IN_TICK), " ms after previous command");
				logged = true;
			}

			count.incrementAndGet();

			if (!punishmentInProgress && (config.PUNISHMENT_LIMIT > 0) && (count.get() >= config.PUNISHMENT_LIMIT) && (config.PUNISHMENT_TYPE != null)) {
				punishmentInProgress = true;

				if ("kick".equals(config.PUNISHMENT_TYPE)) {
					kickPlayer();
				} else if ("ban".equals(config.PUNISHMENT_TYPE)) {
					banAccount();
				} else if ("jail".equals(config.PUNISHMENT_TYPE)) {
					jailChar();
				}

				punishmentInProgress = false;
			}
			return false;
		}

		if (count.get() > 0) {
			if (config.LOG_FLOODING && log.isWarnEnabled()) {
				log(" issued ", String.valueOf(count), " extra requests within ~", String.valueOf(config.FLOOD_PROTECTION_INTERVAL * GameTimeManager.MILLIS_IN_TICK), " ms");
			}
		}

		nextGameTick = curTick + config.FLOOD_PROTECTION_INTERVAL;
		logged = false;
		count.set(0);
		return true;
	}

	/**
	 * Kick player from game (close network connection).
	 */
	private void kickPlayer() {
		Disconnection.of(client).defaultSequence(false);

		if (log.isWarnEnabled()) {
			log("kicked for flooding");
		}
	}

	/**
	 * Bans char account and logs out the char.
	 */
	private void banAccount() {
		PunishmentManager.getInstance().startPunishment(new PunishmentTask(client.getAccountName(), PunishmentAffect.ACCOUNT, PunishmentType.BAN, System.currentTimeMillis() + config.PUNISHMENT_TIME, "", getClass().getSimpleName()));
		if (log.isWarnEnabled()) {
			log(" banned for flooding ", config.PUNISHMENT_TIME <= 0 ? "forever" : "for " + (config.PUNISHMENT_TIME / 60000) + " mins");
		}
	}

	/**
	 * Jails char.
	 */
	private void jailChar() {
		if (client.getActiveChar() != null) {
			int charId = client.getActiveChar().getObjectId();
			if (charId > 0) {
				PunishmentManager.getInstance().startPunishment(new PunishmentTask(charId, PunishmentAffect.CHARACTER, PunishmentType.JAIL, System.currentTimeMillis() + config.PUNISHMENT_TIME, "", getClass().getSimpleName()));
			}

			if (log.isWarnEnabled()) {
				log(" jailed for flooding ", config.PUNISHMENT_TIME <= 0 ? "forever" : "for " + (config.PUNISHMENT_TIME / 60000) + " mins");
			}
		}
	}

	private void log(String... lines) {
		final StringBuilder output = new StringBuilder(100);
		output.append(config.FLOOD_PROTECTOR_TYPE);
		output.append(": ");
		String address = null;
		try {
			if (!client.isDetached()) {
				address = client.getIP();
			}
		} catch (Exception e) {
		}

		switch (client.getState()) {
			case IN_GAME:
				if (client.getActiveChar() != null) {
					output.append(client.getActiveChar().getName());
					output.append("(");
					output.append(client.getActiveChar().getObjectId());
					output.append(") ");
				}
				break;
			case AUTHENTICATED:
				if (client.getAccountName() != null) {
					output.append(client.getAccountName());
					output.append(" ");
				}
				break;
			case CONNECTED:
				if (address != null) {
					output.append(address);
				}
				break;
			default:
				throw new IllegalStateException("Missing state on switch");
		}

		Arrays.stream(lines).forEach(output::append);

		log.warn(output.toString());
	}
}