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
package org.l2junity.gameserver.model.announce;

import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.util.Broadcast;

import java.sql.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author UnAfraid
 */
public final class AutoAnnouncement extends Announcement implements Runnable {
	private static final String INSERT_QUERY = "INSERT INTO announcements (`type`, `content`, `author`, `initial`, `delay`, `repeat`) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_QUERY = "UPDATE announcements SET `type` = ?, `content` = ?, `author` = ?, `initial` = ?, `delay` = ?, `repeat` = ? WHERE id = ?";

	private long _initial;
	private long _delay;
	private int _repeat = -1;
	private int _currentState;
	private ScheduledFuture<?> _task;

	public AutoAnnouncement(AnnouncementType type, String content, String author, long initial, long delay, int repeat) {
		super(type, content, author);
		_initial = initial;
		_delay = delay;
		_repeat = repeat;
		restartMe();
	}

	public AutoAnnouncement(ResultSet rset) throws SQLException {
		super(rset);
		_initial = rset.getLong("initial");
		_delay = rset.getLong("delay");
		_repeat = rset.getInt("repeat");
		restartMe();
	}

	public long getInitial() {
		return _initial;
	}

	public void setInitial(long initial) {
		_initial = initial;
	}

	public long getDelay() {
		return _delay;
	}

	public void setDelay(long delay) {
		_delay = delay;
	}

	public int getRepeat() {
		return _repeat;
	}

	public void setRepeat(int repeat) {
		_repeat = repeat;
	}

	@Override
	public boolean storeMe() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement st = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
			st.setInt(1, getType().ordinal());
			st.setString(2, getContent());
			st.setString(3, getAuthor());
			st.setLong(4, getInitial());
			st.setLong(5, getDelay());
			st.setInt(6, getRepeat());
			st.execute();
			try (ResultSet rset = st.getGeneratedKeys()) {
				if (rset.next()) {
					_id = rset.getInt(1);
				}
			}
		} catch (Exception e) {
			_log.warn(getClass().getSimpleName() + ": Couldn't store announcement: ", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean updateMe() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement st = con.prepareStatement(UPDATE_QUERY)) {
			st.setInt(1, getType().ordinal());
			st.setString(2, getContent());
			st.setString(3, getAuthor());
			st.setLong(4, getInitial());
			st.setLong(5, getDelay());
			st.setLong(6, getRepeat());
			st.setLong(7, getId());
			st.execute();
		} catch (Exception e) {
			_log.warn(getClass().getSimpleName() + ": Couldn't update announcement: ", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteMe() {
		if ((_task != null) && !_task.isCancelled()) {
			_task.cancel(false);
		}
		return super.deleteMe();
	}

	public void restartMe() {
		if ((_task != null) && !_task.isCancelled()) {
			_task.cancel(false);
		}
		_currentState = _repeat;
		_task = ThreadPool.getInstance().scheduleGeneral(this, _initial, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
		if ((_currentState == -1) || (_currentState > 0)) {
			for (String content : getContent().split(System.lineSeparator())) {
				Broadcast.toAllOnlinePlayers(content, (getType() == AnnouncementType.AUTO_CRITICAL));
			}

			if (_currentState != -1) {
				_currentState--;
			}

			_task = ThreadPool.getInstance().scheduleGeneral(this, _delay, TimeUnit.MILLISECONDS);
		}
	}
}
