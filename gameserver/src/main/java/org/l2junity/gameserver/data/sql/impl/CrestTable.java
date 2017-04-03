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
package org.l2junity.gameserver.data.sql.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.Crest;
import org.l2junity.gameserver.model.Crest.CrestType;
import org.l2junity.gameserver.model.Clan;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Loads and saves crests from database.
 *
 * @author NosBit
 */
@Slf4j
@StartupComponent(value = "Data", dependency = {ClanTable.class})
public final class CrestTable {
	@Getter(lazy = true)
	private static final CrestTable instance = new CrestTable();

	private final Map<Integer, Crest> _crests = new ConcurrentHashMap<>();
	private final AtomicInteger _nextId = new AtomicInteger(1);

	protected CrestTable() {
		reload();
	}

	public void reload() {
		_crests.clear();
		final Set<Integer> crestsInUse = new HashSet<>();
		for (Clan clan : ClanTable.getInstance().getClans()) {
			if (clan.getCrestId() != 0) {
				crestsInUse.add(clan.getCrestId());
			}

			if (clan.getCrestLargeId() != 0) {
				crestsInUse.add(clan.getCrestLargeId());
			}

			if (clan.getAllyCrestId() != 0) {
				crestsInUse.add(clan.getAllyCrestId());
			}
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			 ResultSet rs = statement.executeQuery("SELECT `crest_id`, `data`, `type` FROM `crests` ORDER BY `crest_id` DESC")) {
			while (rs.next()) {
				final int id = rs.getInt("crest_id");

				if (_nextId.get() <= id) {
					_nextId.set(id + 1);
				}

				// delete all unused crests except the last one we dont want to reuse
				// a crest id because client will display wrong crest if its reused
				if (!crestsInUse.contains(id) && (id != (_nextId.get() - 1))) {
					rs.deleteRow();
					continue;
				}

				final byte[] data = rs.getBytes("data");
				final CrestType crestType = CrestType.getById(rs.getInt("type"));
				if (crestType != null) {
					_crests.put(id, new Crest(id, data, crestType));
				} else {
					log.warn("Unknown crest type found in database. Type:" + rs.getInt("type"));
				}
			}

		} catch (SQLException e) {
			log.warn("There was an error while loading crests from database:", e);
		}

		log.info("Loaded {} Crests.", _crests.size());

		for (Clan clan : ClanTable.getInstance().getClans()) {
			if (clan.getCrestId() != 0) {
				if (getCrest(clan.getCrestId()) == null) {
					log.info("Removing non-existent crest for clan {}, crestId: {}", clan, clan.getCrestId());
					clan.setCrestId(0);
					clan.changeClanCrest(0);
				}
			}

			if (clan.getCrestLargeId() != 0) {
				if (getCrest(clan.getCrestLargeId()) == null) {
					log.info("Removing non-existent large crest for clan {}, crestLargeId: {}", clan, clan.getCrestLargeId());
					clan.setCrestLargeId(0);
					clan.changeLargeCrest(0);
				}
			}

			if (clan.getAllyCrestId() != 0) {
				if (getCrest(clan.getAllyCrestId()) == null) {
					log.info("Removing non-existent ally crest for clan {}, allyCrestId: {}", clan.getAllyCrestId());
					clan.setAllyCrestId(0);
					clan.changeAllyCrest(0, true);
				}
			}
		}
	}

	/**
	 * @param crestId The crest id
	 * @return {@code L2Crest} if crest is found, {@code null} if crest was not found.
	 */
	public Crest getCrest(int crestId) {
		return _crests.get(crestId);
	}

	/**
	 * Creates a {@code L2Crest} object and inserts it in database and cache.
	 *
	 * @param data
	 * @param crestType
	 * @return {@code L2Crest} on success, {@code null} on failure.
	 */
	public Crest createCrest(byte[] data, CrestType crestType) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("INSERT INTO `crests`(`crest_id`, `data`, `type`) VALUES(?, ?, ?)")) {
			final Crest crest = new Crest(getNextId(), data, crestType);
			statement.setInt(1, crest.getId());
			statement.setBytes(2, crest.getData());
			statement.setInt(3, crest.getType().getId());
			statement.executeUpdate();
			_crests.put(crest.getId(), crest);
			return crest;
		} catch (SQLException e) {
			log.warn("There was an error while saving crest in database: {}", e);
		}
		return null;
	}

	/**
	 * Removes crest from database and cache.
	 *
	 * @param crestId the id of crest to be removed.
	 */
	public void removeCrest(int crestId) {
		_crests.remove(crestId);

		// avoid removing last crest id we dont want to lose index...
		// because client will display wrong crest if its reused
		if (crestId == (_nextId.get() - 1)) {
			return;
		}

		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement("DELETE FROM `crests` WHERE `crest_id` = ?")) {
			statement.setInt(1, crestId);
			statement.executeUpdate();
		} catch (SQLException e) {
			log.warn("There was an error while deleting crest from database: {}", e);
		}
	}

	/**
	 * @return The next crest id.
	 */
	public int getNextId() {
		return _nextId.getAndIncrement();
	}
}
