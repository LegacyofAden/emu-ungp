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
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.model.ClanMember;
import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.items.instance.ItemInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Slf4j
@StartupComponent(value = "Service", dependency = {ZoneManager.class, DoorData.class, SkillTreesData.class})
public final class CastleManager {
	@Getter(lazy = true)
	private static final CastleManager instance = new CastleManager();

	private final Map<Integer, Castle> _castles = new ConcurrentSkipListMap<>();
	private final Map<Integer, Long> _castleSiegeDate = new ConcurrentHashMap<>();

	private static final int _castleCirclets[] =
			{
					0,
					6838,
					6835,
					6839,
					6837,
					6840,
					6834,
					6836,
					8182,
					8183
			};

	protected CastleManager() {
		load();
	}

	private void load() {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 Statement s = con.createStatement();
			 ResultSet rs = s.executeQuery("SELECT id FROM castle ORDER BY id")) {
			while (rs.next()) {
				final int castleId = rs.getInt("id");
				_castles.put(castleId, new Castle(castleId));
			}
			log.info("Loaded: {} castles", getCastles().size());
		} catch (Exception e) {
			log.error("Exception: loadCastleData():", e);
		}
	}

	public final Castle findNearestCastle(WorldObject obj) {
		return findNearestCastle(obj, Long.MAX_VALUE);
	}

	public final Castle findNearestCastle(WorldObject obj, long maxDistance) {
		Castle nearestCastle = getCastle(obj);
		if (nearestCastle == null) {
			double distance;
			for (Castle castle : getCastles()) {
				distance = castle.getDistance(obj);
				if (maxDistance > distance) {
					maxDistance = (long) distance;
					nearestCastle = castle;
				}
			}
		}
		return nearestCastle;
	}

	public final Castle getCastleById(int castleId) {
		return _castles.get(castleId);
	}

	public final Castle getCastleByOwner(L2Clan clan) {
		for (Castle temp : getCastles()) {
			if (temp.getOwnerId() == clan.getId()) {
				return temp;
			}
		}
		return null;
	}

	public final Castle getCastle(String name) {
		for (Castle temp : getCastles()) {
			if (temp.getName().equalsIgnoreCase(name.trim())) {
				return temp;
			}
		}
		return null;
	}

	public final Castle getCastle(double x, double y, double z) {
		for (Castle temp : getCastles()) {
			if (temp.checkIfInZone(x, y, z)) {
				return temp;
			}
		}
		return null;
	}

	public final Castle getCastle(WorldObject activeObject) {
		return getCastle(activeObject.getX(), activeObject.getY(), activeObject.getZ());
	}

	public final Collection<Castle> getCastles() {
		return _castles.values();
	}

	public boolean isCastleInSiege(int castleId) {
		final Castle castle = getCastleById(castleId);
		return (castle != null) && castle.getSiege().isInProgress();
	}

	public boolean hasOwnedCastle() {
		boolean hasOwnedCastle = false;
		for (Castle castle : getCastles()) {
			if (castle.getOwnerId() > 0) {
				hasOwnedCastle = true;
				break;
			}
		}
		return hasOwnedCastle;
	}

	public int getCircletByCastleId(int castleId) {
		if ((castleId > 0) && (castleId < 10)) {
			return _castleCirclets[castleId];
		}

		return 0;
	}

	// remove this castle's circlets from the clan
	public void removeCirclet(L2Clan clan, int castleId) {
		for (ClanMember member : clan.getMembers()) {
			removeCirclet(member, castleId);
		}
	}

	public void removeCirclet(ClanMember member, int castleId) {
		if (member == null) {
			return;
		}
		Player player = member.getPlayerInstance();
		int circletId = getCircletByCastleId(castleId);

		if (circletId != 0) {
			// online-player circlet removal
			if (player != null) {
				try {
					ItemInstance circlet = player.getInventory().getItemByItemId(circletId);
					if (circlet != null) {
						if (circlet.isEquipped()) {
							player.getInventory().unEquipItemInSlot(circlet.getLocationSlot());
						}
						player.destroyItemByItemId("CastleCircletRemoval", circletId, 1, player, true);
					}
					return;
				} catch (NullPointerException e) {
					// continue removing offline
				}
			}
			// else offline-player circlet removal
			try (Connection con = DatabaseFactory.getInstance().getConnection();
				 PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE owner_id = ? and item_id = ?")) {
				ps.setInt(1, member.getObjectId());
				ps.setInt(2, circletId);
				ps.execute();
			} catch (Exception e) {
				log.warn("Failed to remove castle circlets offline for player {}: ", member.getName(), e);
			}
		}
	}

	public void registerSiegeDate(int castleId, long siegeDate) {
		_castleSiegeDate.put(castleId, siegeDate);
	}

	public int getSiegeDates(long siegeDate) {
		int count = 0;
		for (long date : _castleSiegeDate.values()) {
			if (Math.abs(date - siegeDate) < 1000) {
				count++;
			}
		}
		return count;
	}
}
