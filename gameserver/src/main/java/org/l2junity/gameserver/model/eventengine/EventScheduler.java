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
package org.l2junity.gameserver.model.eventengine;

import it.sauronsoftware.cron4j.PastPredictor;
import it.sauronsoftware.cron4j.Predictor;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.model.StatsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author UnAfraid
 */
public class EventScheduler {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventScheduler.class);
	private final AbstractEventManager<?> _eventManager;
	private final String _name;
	private final String _pattern;
	private final boolean _repeat;
	private List<EventMethodNotification> _notifications;
	private ScheduledFuture<?> _task;

	public EventScheduler(AbstractEventManager<?> manager, StatsSet set) {
		_eventManager = manager;
		_name = set.getString("name", "");
		_pattern = set.getString("minute", "*") + " " + set.getString("hour", "*") + " " + set.getString("dayOfMonth", "*") + " " + set.getString("month", "*") + " " + set.getString("dayOfWeek", "*");
		_repeat = set.getBoolean("repeat", false);
	}

	public String getName() {
		return _name;
	}

	public long getNextSchedule() {
		final Predictor predictor = new Predictor(_pattern);
		return predictor.nextMatchingTime();
	}

	public long getNextSchedule(long fromTime) {
		final Predictor predictor = new Predictor(_pattern, fromTime);
		return predictor.nextMatchingTime();
	}

	public long getPrevSchedule() {
		final PastPredictor predictor = new PastPredictor(_pattern);
		return predictor.prevMatchingTime();
	}

	public long getPrevSchedule(long fromTime) {
		final PastPredictor predictor = new PastPredictor(_pattern, fromTime);
		return predictor.prevMatchingTime();
	}

	public boolean isRepeating() {
		return _repeat;
	}

	public void addEventNotification(EventMethodNotification notification) {
		if (_notifications == null) {
			_notifications = new ArrayList<>();
		}
		_notifications.add(notification);
	}

	public List<EventMethodNotification> getEventNotifications() {
		return _notifications;
	}

	public void startScheduler() {
		if (_notifications == null) {
			LOGGER.info("Scheduler without notificator manager: {} pattern: {}", _eventManager.getClass().getSimpleName(), _pattern);
			return;
		}

		final Predictor predictor = new Predictor(_pattern);
		final long nextSchedule = predictor.nextMatchingTime();
		final long timeSchedule = nextSchedule - System.currentTimeMillis();
		if (timeSchedule <= (30 * 1000)) {
			LOGGER.warn("Wrong reschedule for {} end up run in {} seconds!", _eventManager.getClass().getSimpleName(), timeSchedule / 1000);
			ThreadPool.getInstance().scheduleGeneral(this::startScheduler, timeSchedule + 1000, TimeUnit.MILLISECONDS);
			return;
		}

		if (_task != null) {
			_task.cancel(false);
		}

		_task = ThreadPool.getInstance().scheduleGeneral(() ->
		{
			run();
			updateLastRun();

			if (isRepeating()) {
				ThreadPool.getInstance().scheduleGeneral(this::startScheduler, 1000, TimeUnit.MILLISECONDS);
			}
		}, timeSchedule, TimeUnit.MILLISECONDS);
	}

	public boolean updateLastRun() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement("INSERT INTO event_schedulers (eventName, schedulerName, lastRun) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE eventName = ?, schedulerName = ?, lastRun = ?")) {
			ps.setString(1, _eventManager.getName());
			ps.setString(2, _name);
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, _eventManager.getName());
			ps.setString(5, _name);
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.execute();
			return true;
		} catch (Exception e) {
			LOGGER.warn("Failed to insert/update information for scheduled task manager: {} scheduler: {}", _eventManager.getClass().getSimpleName(), _name, e);
		}
		return false;
	}

	public void stopScheduler() {
		if (_task != null) {
			_task.cancel(false);
			_task = null;
		}
	}

	public long getRemainingTime(TimeUnit unit) {
		return (_task != null) && !_task.isDone() ? _task.getDelay(unit) : 0;
	}

	public void run() {
		for (EventMethodNotification notification : _notifications) {
			try {
				notification.execute();
			} catch (Exception e) {
				LOGGER.warn("Failed to notify to event manager: {} method: {}", notification.getManager().getClass().getSimpleName(), notification.getMethod().getName());
			}
		}
	}
}
