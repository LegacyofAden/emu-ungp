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
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.data.xml.impl.ClanHallData;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.MapRegion;
import org.l2junity.gameserver.model.TeleportWhereType;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.entity.ClanHall;
import org.l2junity.gameserver.model.entity.Fort;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.zone.type.RespawnZone;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Nyaran
 */
@Slf4j
@StartupComponent("Data")
public final class MapRegionManager implements IGameXmlReader {
	@Getter(lazy = true)
	private static final MapRegionManager instance = new MapRegionManager();

	private final Map<String, MapRegion> _regions = new HashMap<>();
	private final String defaultRespawn = "talking_island_town";

	protected MapRegionManager() {
		_regions.clear();
		parseDatapackDirectory("data/mapregion", false);
		log.info("Loaded {} map regions.", _regions.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		NamedNodeMap attrs;
		String name;
		String town;
		int locId;
		int bbs;

		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equalsIgnoreCase(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					if ("region".equalsIgnoreCase(d.getNodeName())) {
						attrs = d.getAttributes();
						name = attrs.getNamedItem("name").getNodeValue();
						town = attrs.getNamedItem("town").getNodeValue();
						locId = parseInteger(attrs, "locId");
						bbs = parseInteger(attrs, "bbs");

						MapRegion region = new MapRegion(name, town, locId, bbs);
						for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
							attrs = c.getAttributes();
							if ("respawnPoint".equalsIgnoreCase(c.getNodeName())) {
								int spawnX = parseInteger(attrs, "X");
								int spawnY = parseInteger(attrs, "Y");
								int spawnZ = parseInteger(attrs, "Z");

								boolean other = parseBoolean(attrs, "isOther", false);
								boolean chaotic = parseBoolean(attrs, "isChaotic", false);
								boolean banish = parseBoolean(attrs, "isBanish", false);

								if (other) {
									region.addOtherSpawn(spawnX, spawnY, spawnZ);
								} else if (chaotic) {
									region.addChaoticSpawn(spawnX, spawnY, spawnZ);
								} else if (banish) {
									region.addBanishSpawn(spawnX, spawnY, spawnZ);
								} else {
									region.addSpawn(spawnX, spawnY, spawnZ);
								}
							} else if ("map".equalsIgnoreCase(c.getNodeName())) {
								region.addMap(parseInteger(attrs, "X"), parseInteger(attrs, "Y"));
							} else if ("banned".equalsIgnoreCase(c.getNodeName())) {
								region.addBannedRace(attrs.getNamedItem("race").getNodeValue(), attrs.getNamedItem("point").getNodeValue());
							}
						}
						_regions.put(name, region);
					}
				}
			}
		}
	}

	/**
	 * @param locX
	 * @param locY
	 * @return
	 */
	public final MapRegion getMapRegion(double locX, double locY) {
		for (MapRegion region : _regions.values()) {
			if (region.isZoneInRegion(getMapRegionX((int) locX), getMapRegionY((int) locY))) {
				return region;
			}
		}
		return null;
	}

	/**
	 * @param locX
	 * @param locY
	 * @return
	 */
	public final int getMapRegionLocId(double locX, double locY) {
		MapRegion region = getMapRegion(locX, locY);
		if (region != null) {
			return region.getLocId();
		}
		return 0;
	}

	/**
	 * @param obj
	 * @return
	 */
	public final MapRegion getMapRegion(WorldObject obj) {
		return getMapRegion(obj.getX(), obj.getY());
	}

	/**
	 * @param obj
	 * @return
	 */
	public final int getMapRegionLocId(WorldObject obj) {
		return getMapRegionLocId(obj.getX(), obj.getY());
	}

	/**
	 * @param posX
	 * @return
	 */
	public final int getMapRegionX(int posX) {
		return (posX >> 15) + 9 + 11;// + centerTileX;
	}

	/**
	 * @param posY
	 * @return
	 */
	public final int getMapRegionY(int posY) {
		return (posY >> 15) + 10 + 8;// + centerTileX;
	}

	/**
	 * Get town name by character position
	 *
	 * @param activeChar
	 * @return
	 */
	public String getClosestTownName(Creature activeChar) {
		MapRegion region = getMapRegion(activeChar);

		if (region == null) {
			return "Aden Castle Town";
		}

		return region.getTown();
	}

	/**
	 * @param activeChar
	 * @param teleportWhere
	 * @return
	 */
	public Location getTeleToLocation(Creature activeChar, TeleportWhereType teleportWhere) {
		if (activeChar.isPlayer()) {
			final PlayerInstance player = activeChar.getActingPlayer();

			Castle castle = null;
			Fort fort = null;
			ClanHall clanhall = null;

			if ((player.getClan() != null) && !player.isFlyingMounted() && !player.isFlying()) // flying players in gracia cant use teleports to aden continent
			{
				// If teleport to clan hall
				if (teleportWhere == TeleportWhereType.CLANHALL) {
					clanhall = ClanHallData.getInstance().getClanHallByClan(player.getClan());
					if ((clanhall != null) && !player.isFlyingMounted()) {
						return clanhall.getOwnerLocation();
					}
				}

				// If teleport to castle
				if (teleportWhere == TeleportWhereType.CASTLE) {
					castle = CastleManager.getInstance().getCastleByOwner(player.getClan());
					// Otherwise check if player is on castle or fortress ground
					// and player's clan is defender
					if (castle == null) {
						castle = CastleManager.getInstance().getCastle(player);
						if (!((castle != null) && castle.getSiege().isInProgress() && (castle.getSiege().getDefenderClan(player.getClan()) != null))) {
							castle = null;
						}
					}

					if ((castle != null) && (castle.getResidenceId() > 0)) {
						if (player.getReputation() < 0) {
							return castle.getResidenceZone().getChaoticSpawnLoc();
						}
						return castle.getResidenceZone().getSpawnLoc();
					}
				}

				// If teleport to fortress
				if (teleportWhere == TeleportWhereType.FORTRESS) {
					fort = FortManager.getInstance().getFortByOwner(player.getClan());
					// Otherwise check if player is on castle or fortress ground
					// and player's clan is defender
					if (fort == null) {
						fort = FortManager.getInstance().getFort(player);
						if (!((fort != null) && fort.getSiege().isInProgress() && (fort.getOwnerClan() == player.getClan()))) {
							fort = null;
						}
					}

					if ((fort != null) && (fort.getResidenceId() > 0)) {
						if (player.getReputation() < 0) {
							return fort.getResidenceZone().getChaoticSpawnLoc();
						}
						return fort.getResidenceZone().getSpawnLoc();
					}
				}

				// If teleport to SiegeHQ
				if (teleportWhere == TeleportWhereType.SIEGEFLAG) {
					castle = CastleManager.getInstance().getCastle(player);
					fort = FortManager.getInstance().getFort(player);
					if (castle != null) {
						if (castle.getSiege().isInProgress()) {
							// Check if player's clan is attacker
							Set<Npc> flags = castle.getSiege().getFlag(player.getClan());
							if ((flags != null) && !flags.isEmpty()) {
								// Spawn to flag - Need more work to get player to the nearest flag
								return flags.stream().findAny().get().getLocation();
							}
						}

					} else if (fort != null) {
						if (fort.getSiege().isInProgress()) {
							// Check if player's clan is attacker
							Set<Npc> flags = fort.getSiege().getFlag(player.getClan());
							if ((flags != null) && !flags.isEmpty()) {
								// Spawn to flag - Need more work to get player to the nearest flag
								return flags.stream().findAny().get().getLocation();
							}
						}
					}
				}
			}

			// Karma player land out of city
			if (player.getReputation() < 0) {
				try {
					final RespawnZone zone = ZoneManager.getInstance().getZone(player, RespawnZone.class);
					if (zone != null) {
						return getRestartRegion(activeChar, zone.getRespawnPoint((PlayerInstance) activeChar)).getChaoticSpawnLoc();
					}
					return getMapRegion(activeChar).getChaoticSpawnLoc();
				} catch (Exception e) {
					if (player.isFlyingMounted()) {
						return _regions.get("union_base_of_kserth").getChaoticSpawnLoc();
					}
					return _regions.get(defaultRespawn).getChaoticSpawnLoc();
				}
			}

			// Checking if needed to be respawned in "far" town from the castle;
			castle = CastleManager.getInstance().getCastle(player);
			if (castle != null) {
				if (castle.getSiege().isInProgress()) {
					// Check if player's clan is participating
					if ((castle.getSiege().checkIsDefender(player.getClan()) || castle.getSiege().checkIsAttacker(player.getClan()))) {
						return castle.getResidenceZone().getOtherSpawnLoc();
					}
				}
			}

			// Checking if in an instance
			final Instance inst = player.getInstanceWorld();
			if (inst != null) {
				final Location loc = inst.getExitLocation(player);
				if (loc != null) {
					return loc;
				}
			}
		}

		// Get the nearest town
		try {
			RespawnZone zone = ZoneManager.getInstance().getZone(activeChar, RespawnZone.class);
			if (zone != null) {
				return getRestartRegion(activeChar, zone.getRespawnPoint((PlayerInstance) activeChar)).getSpawnLoc();
			}
			return getMapRegion(activeChar).getSpawnLoc();
		} catch (Exception e) {
			// Port to the default respawn if no closest town found.
			return _regions.get(defaultRespawn).getSpawnLoc();
		}
	}

	/**
	 * @param activeChar
	 * @param point
	 * @return
	 */
	public MapRegion getRestartRegion(Creature activeChar, String point) {
		try {
			PlayerInstance player = ((PlayerInstance) activeChar);
			MapRegion region = _regions.get(point);

			if (region.getBannedRace().containsKey(player.getRace())) {
				getRestartRegion(player, region.getBannedRace().get(player.getRace()));
			}
			return region;
		} catch (Exception e) {
			return _regions.get(defaultRespawn);
		}
	}

	/**
	 * @param regionName the map region name.
	 * @return if exists the map region identified by that name, null otherwise.
	 */
	public MapRegion getMapRegionByName(String regionName) {
		return _regions.get(regionName);
	}

	public int getBBs(ILocational loc) {
		final MapRegion region = getMapRegion(loc.getX(), loc.getY());
		return region != null ? region.getBbs() : _regions.get(defaultRespawn).getBbs();
	}
}
