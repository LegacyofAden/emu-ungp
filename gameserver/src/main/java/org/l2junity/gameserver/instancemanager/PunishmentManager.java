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
package org.l2junity.gameserver.instancemanager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.holders.PunishmentHolder;
import org.l2junity.gameserver.model.punishment.PunishmentAffect;
import org.l2junity.gameserver.model.punishment.PunishmentTask;
import org.l2junity.gameserver.model.punishment.PunishmentType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Service")
public final class PunishmentManager {
	@Getter(lazy = true)
	private static final PunishmentManager instance = new PunishmentManager();

	private final Map<PunishmentAffect, PunishmentHolder> _tasks = new ConcurrentHashMap<>();

	private PunishmentManager() {
		int initiated = 0;
		int expired = 0;

		// Load punishments.
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement st = con.createStatement();
			 ResultSet rset = st.executeQuery("SELECT * FROM punishments")) {
			while (rset.next()) {
				final int id = rset.getInt("id");
				final String key = rset.getString("key");
				final PunishmentAffect affect = PunishmentAffect.getByName(rset.getString("affect"));
				final PunishmentType type = PunishmentType.getByName(rset.getString("type"));
				final long expirationTime = rset.getLong("expiration");
				final String reason = rset.getString("reason");
				final String punishedBy = rset.getString("punishedBy");
				if ((type != null) && (affect != null)) {
					if ((expirationTime > 0) && (System.currentTimeMillis() > expirationTime)) // expired task.
					{
						expired++;
					} else {
						initiated++;
						_tasks.computeIfAbsent(affect, PunishmentHolder::new).addPunishment(new PunishmentTask(id, key, affect, type, expirationTime, reason, punishedBy, true));
					}
				}
			}
		} catch (Exception e) {
			log.warn("Error while loading punishments: ", e);
		}

		log.info("Loaded {} active and {} expired punishments.", initiated, expired);
	}

	public void startPunishment(PunishmentTask task) {
		_tasks.computeIfAbsent(task.getAffect(), PunishmentHolder::new).addPunishment(task);
	}

	public void stopPunishment(PunishmentAffect affect, PunishmentType type) {
		final PunishmentHolder holder = _tasks.get(affect);
		if (holder != null) {
			holder.stopPunishment(type);
		}
	}

	public void stopPunishment(Object key, PunishmentAffect affect, PunishmentType type) {
		final PunishmentTask task = getPunishment(key, affect, type);
		if (task != null) {
			_tasks.computeIfAbsent(affect, PunishmentHolder::new).stopPunishment(task);
		}
	}

	public boolean hasPunishment(Object key, PunishmentAffect affect, PunishmentType type) {
		final PunishmentHolder holder = _tasks.computeIfAbsent(affect, PunishmentHolder::new);
		return holder.hasPunishment(String.valueOf(key), type);
	}

	public long getPunishmentExpiration(Object key, PunishmentAffect affect, PunishmentType type) {
		final PunishmentTask punishTask = getPunishment(key, affect, type);
		return punishTask != null ? punishTask.getExpirationTime() : 0;
	}

	private PunishmentTask getPunishment(Object key, PunishmentAffect affect, PunishmentType type) {
		return _tasks.computeIfAbsent(affect, PunishmentHolder::new).getPunishment(String.valueOf(key), type);
	}
}
