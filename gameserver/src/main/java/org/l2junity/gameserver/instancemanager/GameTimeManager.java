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
package org.l2junity.gameserver.instancemanager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.DateTimeConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.OnDayNightChange;
import org.l2junity.gameserver.network.client.send.ClientSetTime;
import org.l2junity.gameserver.util.Broadcast;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

/**
 * A manager that controls game time updates.
 *
 * @author NosKun
 */
@Slf4j
@StartupComponent("Service")
// TODO: To AbstractPeriodicManager
public final class GameTimeManager {
	@Getter(lazy = true)
	private static final GameTimeManager instance = new GameTimeManager();

	public static final int TICKS_PER_SECOND = 10;
	public static final int MILLIS_IN_TICK = 1_000 / TICKS_PER_SECOND;

	private static final LocalTime INITIAL = LocalTime.of(23, 45);
	private static final LocalTime SUNSET = LocalTime.MIDNIGHT;
	private static final LocalTime SUNRISE = LocalTime.of(6, 0);

	private final long _ticksReferenceTime = System.currentTimeMillis();

	private volatile LocalTime _gameTime = INITIAL;

	protected GameTimeManager() {
		updateGameTime(t -> loadData());
		final int millisPerMinute = 60_000 / DateTimeConfig.TIME_MULTIPLIER;
		ThreadPool.getInstance().scheduleGeneralAtFixedRate(() -> updateGameTime(t -> t.plusMinutes(1)), millisPerMinute, millisPerMinute, TimeUnit.MILLISECONDS);
		log.info("Initialized.");
	}

	private LocalTime loadData() {
		if (DateTimeConfig.SAVE_TIME) {
			try {
				return LocalTime.ofSecondOfDay(GlobalVariablesManager.getInstance().getLong(GlobalVariablesManager.GTM_GAME_TIME_VAR, 0));
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return INITIAL;
	}

	private void saveData() {
		if (DateTimeConfig.SAVE_TIME) {
			GlobalVariablesManager.getInstance().set(GlobalVariablesManager.GTM_GAME_TIME_VAR, _gameTime.toSecondOfDay());
		}
	}

	private synchronized void updateGameTime(UnaryOperator<LocalTime> updateFunction) {
		final boolean wasNight = isNight();
		final LocalTime oldGameTime = _gameTime;
		_gameTime = updateFunction.apply(_gameTime);

		if (_gameTime.isBefore(oldGameTime)) {
			log.info("An in-game day passed - it's now: {}", _gameTime);
		}

		final boolean isNight = isNight();
		if (wasNight != isNight) {
			log.info("Day-night state is: {}", isNight ? "[sunset]" : "[sunrise]");
			EventDispatcher.getInstance().notifyEventAsync(new OnDayNightChange(isNight));
		}

		if (oldGameTime.getHour() != _gameTime.getHour()) {
			saveData();
		}
	}

	public LocalTime getGameTime() {
		return _gameTime;
	}

	public void setGameTime(LocalTime gameTime) {
		updateGameTime(t -> gameTime);
		Broadcast.toAllOnlinePlayers(new ClientSetTime());
	}

	public int getGameTimeInMinutesOfDay() {
		final LocalTime gameTime = _gameTime;
		return gameTime.getHour() * 60 + gameTime.getMinute();
	}

	public boolean isNight() {
		final LocalTime gameTime = _gameTime;
		if (gameTime.equals(SUNSET)) {
			return true;
		}

		if (SUNSET.isBefore(SUNRISE)) {
			return gameTime.isAfter(SUNSET) && gameTime.isBefore(SUNRISE);
		}
		return gameTime.isAfter(SUNSET) || gameTime.isBefore(SUNRISE);
	}

	public int getGameTicks() {
		return (int) ((System.currentTimeMillis() - _ticksReferenceTime) / MILLIS_IN_TICK);
	}
}