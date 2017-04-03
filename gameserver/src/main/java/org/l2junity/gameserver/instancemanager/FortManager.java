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
import org.l2junity.gameserver.data.xml.impl.DoorData;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.data.xml.impl.StaticObjectData;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.entity.Fort;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

@Slf4j
@StartupComponent(value = "Data", dependency = {StaticObjectData.class, DoorData.class, NpcData.class, SkillTreesData.class})
public final class FortManager {
	@Getter(lazy = true)
	private static final FortManager instance = new FortManager();

	private final Map<Integer, Fort> _forts = new ConcurrentSkipListMap<>();

	protected FortManager() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement s = con.createStatement();
			 ResultSet rs = s.executeQuery("SELECT id FROM fort ORDER BY id")) {
			while (rs.next()) {
				final int fortId = rs.getInt("id");
				_forts.computeIfAbsent(fortId, Fort::new);
			}

			log.info(getClass().getSimpleName() + ": Loaded: " + getForts().size() + " fortress");
			for (Fort fort : getForts()) {
				fort.getSiege().loadSiegeGuard();
			}
		} catch (Exception e) {
			log.warn("Failed to load fortress: ", e);
		}
	}

	public final Fort findNearestFort(WorldObject obj) {
		return findNearestFort(obj, Long.MAX_VALUE);
	}

	public final Fort findNearestFort(WorldObject obj, long maxDistance) {
		Fort nearestFort = getFort(obj);
		if (nearestFort == null) {
			for (Fort fort : getForts()) {
				double distance = fort.getDistance(obj);
				if (maxDistance > distance) {
					maxDistance = (long) distance;
					nearestFort = fort;
				}
			}
		}
		return nearestFort;
	}

	public final Fort getFortById(int fortId) {
		for (Fort f : getForts()) {
			if (f.getResidenceId() == fortId) {
				return f;
			}
		}
		return null;
	}

	public final Fort getFortByOwner(Clan clan) {
		for (Fort f : getForts()) {
			if (f.getOwnerClan() == clan) {
				return f;
			}
		}
		return null;
	}

	public final Fort getFort(String name) {
		for (Fort f : getForts()) {
			if (f.getName().equalsIgnoreCase(name.trim())) {
				return f;
			}
		}
		return null;
	}

	public final Fort getFort(double x, double y, double z) {
		for (Fort f : getForts()) {
			if (f.checkIfInZone(x, y, z)) {
				return f;
			}
		}
		return null;
	}

	public final Fort getFort(WorldObject activeObject) {
		return getFort(activeObject.getX(), activeObject.getY(), activeObject.getZ());
	}

	public final Collection<Fort> getForts() {
		return _forts.values();
	}
}