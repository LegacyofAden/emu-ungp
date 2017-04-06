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
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.announce.Announcement;
import org.l2junity.gameserver.model.announce.AnnouncementType;
import org.l2junity.gameserver.model.announce.AutoAnnouncement;
import org.l2junity.gameserver.model.announce.IAnnouncement;
import org.l2junity.gameserver.network.packets.s2c.CreatureSay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Loads announcements from database.
 *
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public final class AnnouncementsTable {
	@Getter(lazy = true)
	private static final AnnouncementsTable instance = new AnnouncementsTable();

	private final Map<Integer, IAnnouncement> _announcements = new ConcurrentSkipListMap<>();

	protected AnnouncementsTable() {
		_announcements.clear();
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement st = con.createStatement();
			 ResultSet rset = st.executeQuery("SELECT * FROM announcements")) {
			while (rset.next()) {
				final AnnouncementType type = AnnouncementType.findById(rset.getInt("type"));
				final Announcement announce;
				switch (type) {
					case NORMAL:
					case CRITICAL: {
						announce = new Announcement(rset);
						break;
					}
					case AUTO_NORMAL:
					case AUTO_CRITICAL: {
						announce = new AutoAnnouncement(rset);
						break;
					}
					default: {
						continue;
					}
				}
				_announcements.put(announce.getId(), announce);
			}
		} catch (Exception e) {
			log.error("Failed loading announcements: {}", e);
		}
	}

	/**
	 * Sending all announcements to the player
	 *
	 * @param player
	 */
	public void showAnnouncements(Player player) {
		sendAnnouncements(player, AnnouncementType.NORMAL);
		sendAnnouncements(player, AnnouncementType.CRITICAL);
		sendAnnouncements(player, AnnouncementType.EVENT);
	}

	/**
	 * Sends all announcements to the player by the specified type
	 *
	 * @param player
	 * @param type
	 */
	public void sendAnnouncements(Player player, AnnouncementType type) {
		for (IAnnouncement announce : _announcements.values()) {
			if (announce.isValid() && (announce.getType() == type)) {
				player.sendPacket(new CreatureSay(0, type == AnnouncementType.CRITICAL ? ChatType.CRITICAL_ANNOUNCE : ChatType.ANNOUNCEMENT, player.getName(), announce.getContent()));
			}
		}
	}

	/**
	 * Adds announcement
	 *
	 * @param announce
	 */
	public void addAnnouncement(IAnnouncement announce) {
		if (announce.storeMe()) {
			_announcements.put(announce.getId(), announce);
		}
	}

	/**
	 * Removes announcement by id
	 *
	 * @param id
	 * @return {@code true} if announcement exists and was deleted successfully, {@code false} otherwise.
	 */
	public boolean deleteAnnouncement(int id) {
		final IAnnouncement announce = _announcements.remove(id);
		return (announce != null) && announce.deleteMe();
	}

	/**
	 * @param id
	 * @return {@link IAnnouncement} by id
	 */
	public IAnnouncement getAnnounce(int id) {
		return _announcements.get(id);
	}

	/**
	 * @return {@link Collection} containing all announcements
	 */
	public Collection<IAnnouncement> getAllAnnouncements() {
		return _announcements.values();
	}
}
