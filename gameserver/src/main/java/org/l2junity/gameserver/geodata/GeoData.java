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
package org.l2junity.gameserver.geodata;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.lang.management.ShutdownManager;
import org.l2junity.commons.lang.management.TerminationStatus;
import org.l2junity.commons.util.BasePathProvider;
import org.l2junity.core.configs.GeoDataConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.impl.DoorData;
import org.l2junity.gameserver.data.xml.impl.FenceData;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.util.GeoUtils;
import org.l2junity.gameserver.util.LinePointIterator;
import org.l2junity.gameserver.util.LinePointIterator3D;
import org.l2junity.geodriver.Cell;
import org.l2junity.geodriver.GeoDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author -Nemesiss-, HorridoJoho
 */
@Slf4j
@StartupComponent("Data")
public final class GeoData {
	@Getter(lazy = true)
	private final static GeoData instance = new GeoData();

	private static final String FILE_NAME_FORMAT = "%d_%d.l2j";
	private static final int ELEVATED_SEE_OVER_DISTANCE = 2;
	private static final int MAX_SEE_OVER_HEIGHT = 48;
	private static final int SPAWN_Z_DELTA_LIMIT = 100;

	private final GeoDriver _driver = new GeoDriver();

	protected GeoData() {
		int loadedRegions = 0;
		try {
			for (int regionX = World.TILE_X_MIN; regionX <= World.TILE_X_MAX; regionX++) {
				for (int regionY = World.TILE_Y_MIN; regionY <= World.TILE_Y_MAX; regionY++) {
					final Path geoFilePath = Paths.get(GeoDataConfig.GEODATA_PATH.toString(), String.format(FILE_NAME_FORMAT, regionX, regionY));
					if (Files.exists(geoFilePath)) {
						log.debug("Loading " + geoFilePath.getFileName() + "...");
						_driver.loadRegion(geoFilePath, regionX, regionY);
						loadedRegions++;
					}
				}
			}
		} catch (Exception e) {
			log.error("Failed to load geodata!", e);
			ShutdownManager.getInstance().halt(TerminationStatus.ENVIRONMENT_MISSING_COMPONENT_OR_SERVICE);
		}

		log.info("Loaded {} geodata region(s).", loadedRegions);
	}

	public boolean hasGeoPos(int geoX, int geoY) {
		return _driver.hasGeoPos(geoX, geoY);
	}

	public boolean checkNearestNswe(int geoX, int geoY, double worldZ, int nswe) {
		return _driver.checkNearestNswe(geoX, geoY, (int) worldZ, nswe);
	}

	public boolean checkNearestNsweAntiCornerCut(int geoX, int geoY, double worldZ, int nswe) {
		boolean can = true;
		if ((nswe & Cell.NSWE_NORTH_EAST) == Cell.NSWE_NORTH_EAST) {
			// can = canEnterNeighbors(prevX, prevY - 1, prevGeoZ, Direction.EAST) && canEnterNeighbors(prevX + 1, prevY, prevGeoZ, Direction.NORTH);
			can = checkNearestNswe(geoX, geoY - 1, worldZ, Cell.NSWE_EAST) && checkNearestNswe(geoX + 1, geoY, worldZ, Cell.NSWE_NORTH);
		}

		if (can && ((nswe & Cell.NSWE_NORTH_WEST) == Cell.NSWE_NORTH_WEST)) {
			// can = canEnterNeighbors(prevX, prevY - 1, prevGeoZ, Direction.WEST) && canEnterNeighbors(prevX - 1, prevY, prevGeoZ, Direction.NORTH);
			can = checkNearestNswe(geoX, geoY - 1, worldZ, Cell.NSWE_WEST) && checkNearestNswe(geoX, geoY - 1, worldZ, Cell.NSWE_NORTH);
		}

		if (can && ((nswe & Cell.NSWE_SOUTH_EAST) == Cell.NSWE_SOUTH_EAST)) {
			// can = canEnterNeighbors(prevX, prevY + 1, prevGeoZ, Direction.EAST) && canEnterNeighbors(prevX + 1, prevY, prevGeoZ, Direction.SOUTH);
			can = checkNearestNswe(geoX, geoY + 1, worldZ, Cell.NSWE_EAST) && checkNearestNswe(geoX + 1, geoY, worldZ, Cell.NSWE_SOUTH);
		}

		if (can && ((nswe & Cell.NSWE_SOUTH_WEST) == Cell.NSWE_SOUTH_WEST)) {
			// can = canEnterNeighbors(prevX, prevY + 1, prevGeoZ, Direction.WEST) && canEnterNeighbors(prevX - 1, prevY, prevGeoZ, Direction.SOUTH);
			can = checkNearestNswe(geoX, geoY + 1, worldZ, Cell.NSWE_WEST) && checkNearestNswe(geoX - 1, geoY, worldZ, Cell.NSWE_SOUTH);
		}

		return can && checkNearestNswe(geoX, geoY, worldZ, nswe);
	}

	public int getNearestZ(int geoX, int geoY, double worldZ) {
		return _driver.getNearestZ(geoX, geoY, (int) worldZ);
	}

	public int getNextLowerZ(int geoX, int geoY, double worldZ) {
		return _driver.getNextLowerZ(geoX, geoY, (int) worldZ);
	}

	public int getNextHigherZ(int geoX, int geoY, double worldZ) {
		return _driver.getNextHigherZ(geoX, geoY, (int) worldZ);
	}

	public int getGeoX(double worldX) {
		return _driver.getGeoX((int) worldX);
	}

	public int getGeoY(double worldY) {
		return _driver.getGeoY((int) worldY);
	}

	public int getGeoZ(double worldZ) {
		return _driver.getGeoZ((int) worldZ);
	}

	public double getWorldX(int geoX) {
		return _driver.getWorldX(geoX);
	}

	public double getWorldY(int geoY) {
		return _driver.getWorldY(geoY);
	}

	public double getWorldZ(int geoZ) {
		return _driver.getWorldZ(geoZ);
	}

	// ///////////////////
	// L2J METHODS

	/**
	 * Gets the height.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the z coordinate
	 * @return the height
	 */
	public double getHeight(double x, double y, double z) {
		return getNearestZ(getGeoX(x), getGeoY(y), z);
	}

	/**
	 * Gets the spawn height.
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param z the the z coordinate
	 * @return the spawn height
	 */
	public double getSpawnHeight(double x, double y, double z) {
		final int geoX = getGeoX(x);
		final int geoY = getGeoY(y);

		if (!hasGeoPos(geoX, geoY)) {
			return z;
		}

		int nextLowerZ = getNextLowerZ(geoX, geoY, z + 100);
		return Math.abs(nextLowerZ - z) <= SPAWN_Z_DELTA_LIMIT ? nextLowerZ : z;
	}

	/**
	 * Gets the spawn height.
	 *
	 * @param location the location
	 * @return the spawn height
	 */
	public double getSpawnHeight(Location location) {
		return getSpawnHeight(location.getX(), location.getY(), location.getZ());
	}

	/**
	 * Can see target. Doors as target always return true. Checks doors between.
	 *
	 * @param cha    the character
	 * @param target the target
	 * @return {@code true} if the character can see the target (LOS), {@code false} otherwise
	 */
	public boolean canSeeTarget(WorldObject cha, WorldObject target) {
		if (target.isDoor()) {
			// can always see doors :o
			return true;
		}

		return canSeeTarget(cha.getX(), cha.getY(), cha.getZ(), cha.getInstanceWorld(), target.getX(), target.getY(), target.getZ(), target.getInstanceWorld());
	}

	/**
	 * Can see target. Checks doors between.
	 *
	 * @param cha           the character
	 * @param worldPosition the world position
	 * @return {@code true} if the character can see the target at the given world position, {@code false} otherwise
	 */
	public boolean canSeeTarget(WorldObject cha, ILocational worldPosition) {
		return canSeeTarget(cha.getX(), cha.getY(), cha.getZ(), cha.getInstanceWorld(), worldPosition.getX(), worldPosition.getY(), worldPosition.getZ());
	}

	/**
	 * Can see target. Checks doors between.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param z      the z coordinate
	 * @param world
	 * @param tx     the target's x coordinate
	 * @param ty     the target's y coordinate
	 * @param tz     the target's z coordinate
	 * @param tworld the target's instanceId
	 * @return
	 */
	public boolean canSeeTarget(double x, double y, double z, Instance world, double tx, double ty, double tz, Instance tworld) {
		return (world != tworld) ? false : canSeeTarget(x, y, z, world, tx, ty, tz);
	}

	/**
	 * Can see target. Checks doors between.
	 *
	 * @param x        the x coordinate
	 * @param y        the y coordinate
	 * @param z        the z coordinate
	 * @param instance
	 * @param tx       the target's x coordinate
	 * @param ty       the target's y coordinate
	 * @param tz       the target's z coordinate
	 * @return {@code true} if there is line of sight between the given coordinate sets, {@code false} otherwise
	 */
	public boolean canSeeTarget(double x, double y, double z, Instance instance, double tx, double ty, double tz) {
		if (DoorData.getInstance().checkIfDoorsBetween(x, y, z, tx, ty, tz, instance, true)) {
			return false;
		}
		if (FenceData.getInstance().checkIfFenceBetween(x, y, z, tx, ty, tz, instance)) {
			return false;
		}
		return canSeeTarget(x, y, z, tx, ty, tz);
	}

	private int getLosGeoZ(int prevX, int prevY, int prevGeoZ, int curX, int curY, int nswe) {
		if ((((nswe & Cell.NSWE_NORTH) != 0) && ((nswe & Cell.NSWE_SOUTH) != 0)) || (((nswe & Cell.NSWE_WEST) != 0) && ((nswe & Cell.NSWE_EAST) != 0))) {
			throw new RuntimeException("Multiple directions!");
		}

		if (checkNearestNsweAntiCornerCut(prevX, prevY, prevGeoZ, nswe)) {
			return getNearestZ(curX, curY, prevGeoZ);
		}
		return getNextHigherZ(curX, curY, prevGeoZ);
	}

	/**
	 * Can see target. Does not check doors between.
	 *
	 * @param x  the x coordinate
	 * @param y  the y coordinate
	 * @param z  the z coordinate
	 * @param tx the target's x coordinate
	 * @param ty the target's y coordinate
	 * @param tz the target's z coordinate
	 * @return {@code true} if there is line of sight between the given coordinate sets, {@code false} otherwise
	 */
	public boolean canSeeTarget(double x, double y, double z, double tx, double ty, double tz) {
		int geoX = getGeoX(x);
		int geoY = getGeoY(y);
		int tGeoX = getGeoX(tx);
		int tGeoY = getGeoY(ty);

		z = getNearestZ(geoX, geoY, z);
		tz = getNearestZ(tGeoX, tGeoY, tz);

		// fastpath
		if ((geoX == tGeoX) && (geoY == tGeoY)) {
			if (hasGeoPos(tGeoX, tGeoY)) {
				return z == tz;
			}

			return true;
		}

		if (tz > z) {
			double tmp = tx;
			tx = x;
			x = tmp;

			tmp = ty;
			ty = y;
			y = tmp;

			tmp = tz;
			tz = z;
			z = tmp;

			tmp = tGeoX;
			tGeoX = geoX;
			geoX = (int) tmp;

			tmp = tGeoY;
			tGeoY = geoY;
			geoY = (int) tmp;
		}

		LinePointIterator3D pointIter = new LinePointIterator3D(geoX, geoY, (int) z, tGeoX, tGeoY, (int) tz);
		// first point is guaranteed to be available, skip it, we can always see our own position
		pointIter.next();
		int prevX = pointIter.x();
		int prevY = pointIter.y();
		int prevZ = pointIter.z();
		int prevGeoZ = prevZ;
		int ptIndex = 0;
		while (pointIter.next()) {
			int curX = pointIter.x();
			int curY = pointIter.y();

			if ((curX == prevX) && (curY == prevY)) {
				continue;
			}

			int beeCurZ = pointIter.z();
			int curGeoZ = prevGeoZ;

			// check if the position has geodata
			if (hasGeoPos(curX, curY)) {
				int beeCurGeoZ = getNearestZ(curX, curY, beeCurZ);
				int nswe = GeoUtils.computeNswe(prevX, prevY, curX, curY);// .computeDirection(prevX, prevY, curX, curY);
				curGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, curX, curY, nswe);
				double maxHeight;
				if (ptIndex < ELEVATED_SEE_OVER_DISTANCE) {
					maxHeight = z + MAX_SEE_OVER_HEIGHT;
				} else {
					maxHeight = beeCurZ + MAX_SEE_OVER_HEIGHT;
				}

				boolean canSeeThrough = false;
				if ((curGeoZ <= maxHeight) && (curGeoZ <= beeCurGeoZ)) {
					if ((nswe & Cell.NSWE_NORTH_EAST) == Cell.NSWE_NORTH_EAST) {
						int northGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX, prevY - 1, Cell.NSWE_EAST);
						int eastGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX + 1, prevY, Cell.NSWE_NORTH);
						canSeeThrough = (northGeoZ <= maxHeight) && (eastGeoZ <= maxHeight) && (northGeoZ <= getNearestZ(prevX, prevY - 1, beeCurZ)) && (eastGeoZ <= getNearestZ(prevX + 1, prevY, beeCurZ));
					} else if ((nswe & Cell.NSWE_NORTH_WEST) == Cell.NSWE_NORTH_WEST) {
						int northGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX, prevY - 1, Cell.NSWE_WEST);
						int westGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX - 1, prevY, Cell.NSWE_NORTH);
						canSeeThrough = (northGeoZ <= maxHeight) && (westGeoZ <= maxHeight) && (northGeoZ <= getNearestZ(prevX, prevY - 1, beeCurZ)) && (westGeoZ <= getNearestZ(prevX - 1, prevY, beeCurZ));
					} else if ((nswe & Cell.NSWE_SOUTH_EAST) == Cell.NSWE_SOUTH_EAST) {
						int southGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX, prevY + 1, Cell.NSWE_EAST);
						int eastGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX + 1, prevY, Cell.NSWE_SOUTH);
						canSeeThrough = (southGeoZ <= maxHeight) && (eastGeoZ <= maxHeight) && (southGeoZ <= getNearestZ(prevX, prevY + 1, beeCurZ)) && (eastGeoZ <= getNearestZ(prevX + 1, prevY, beeCurZ));
					} else if ((nswe & Cell.NSWE_SOUTH_WEST) == Cell.NSWE_SOUTH_WEST) {
						int southGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX, prevY + 1, Cell.NSWE_WEST);
						int westGeoZ = getLosGeoZ(prevX, prevY, prevGeoZ, prevX - 1, prevY, Cell.NSWE_SOUTH);
						canSeeThrough = (southGeoZ <= maxHeight) && (westGeoZ <= maxHeight) && (southGeoZ <= getNearestZ(prevX, prevY + 1, beeCurZ)) && (westGeoZ <= getNearestZ(prevX - 1, prevY, beeCurZ));
					} else {
						canSeeThrough = true;
					}
				}

				if (!canSeeThrough) {
					return false;
				}
			}

			prevX = curX;
			prevY = curY;
			prevGeoZ = curGeoZ;
			++ptIndex;
		}

		return true;
	}

	/**
	 * Move check.
	 *
	 * @param x        the x coordinate
	 * @param y        the y coordinate
	 * @param z        the z coordinate
	 * @param tx       the target's x coordinate
	 * @param ty       the target's y coordinate
	 * @param tz       the target's z coordinate
	 * @param instance the instance
	 * @return the last Location (x,y,z) where player can walk - just before wall
	 */
	public Location moveCheck(double x, double y, double z, double tx, double ty, double tz, Instance instance) {
		int geoX = getGeoX(x);
		int geoY = getGeoY(y);
		z = getNearestZ(geoX, geoY, z);
		int tGeoX = getGeoX(tx);
		int tGeoY = getGeoY(ty);
		tz = getNearestZ(tGeoX, tGeoY, tz);

		if (DoorData.getInstance().checkIfDoorsBetween(x, y, z, tx, ty, tz, instance, false)) {
			return new Location(x, y, getHeight(x, y, z));
		}
		if (FenceData.getInstance().checkIfFenceBetween(x, y, z, tx, ty, tz, instance)) {
			return new Location(x, y, getHeight(x, y, z));
		}

		LinePointIterator pointIter = new LinePointIterator(geoX, geoY, tGeoX, tGeoY);
		// first point is guaranteed to be available
		pointIter.next();
		int prevX = pointIter.x();
		int prevY = pointIter.y();
		double prevZ = z;

		while (pointIter.next()) {
			int curX = pointIter.x();
			int curY = pointIter.y();
			int curZ = getNearestZ(curX, curY, prevZ);
			if (hasGeoPos(prevX, prevY)) {
				int nswe = GeoUtils.computeNswe(prevX, prevY, curX, curY);
				if (!checkNearestNsweAntiCornerCut(prevX, prevY, prevZ, nswe)) {
					// can't move, return previous location
					return new Location(getWorldX(prevX), getWorldY(prevY), prevZ);
				}
			}

			prevX = curX;
			prevY = curY;
			prevZ = curZ;
		}

		if (hasGeoPos(prevX, prevY) && (prevZ != tz)) {
			// different floors, return start location
			return new Location(x, y, z);
		}

		return new Location(tx, ty, tz);
	}

	public Location moveCheck(Location startLoc, Location endLoc, Instance instance) {
		return moveCheck(startLoc.getX(), startLoc.getY(), startLoc.getZ(), endLoc.getX(), endLoc.getY(), endLoc.getZ(), instance);
	}

	/**
	 * Checks if its possible to move from one location to another.
	 *
	 * @param fromX    the X coordinate to start checking from
	 * @param fromY    the Y coordinate to start checking from
	 * @param fromZ    the Z coordinate to start checking from
	 * @param toX      the X coordinate to end checking at
	 * @param toY      the Y coordinate to end checking at
	 * @param toZ      the Z coordinate to end checking at
	 * @param instance the instance
	 * @return {@code true} if the character at start coordinates can move to end coordinates, {@code false} otherwise
	 */
	public boolean canMove(double fromX, double fromY, double fromZ, double toX, double toY, double toZ, Instance instance) {
		int geoX = getGeoX(fromX);
		int geoY = getGeoY(fromY);
		fromZ = getNearestZ(geoX, geoY, fromZ);
		int tGeoX = getGeoX(toX);
		int tGeoY = getGeoY(toY);
		toZ = getNearestZ(tGeoX, tGeoY, toZ);

		if (DoorData.getInstance().checkIfDoorsBetween(fromX, fromY, fromZ, toX, toY, toZ, instance, false)) {
			return false;
		}
		if (FenceData.getInstance().checkIfFenceBetween(fromX, fromY, fromZ, toX, toY, toZ, instance)) {
			return false;
		}

		LinePointIterator pointIter = new LinePointIterator(geoX, geoY, tGeoX, tGeoY);
		// first point is guaranteed to be available
		pointIter.next();
		int prevX = pointIter.x();
		int prevY = pointIter.y();
		double prevZ = fromZ;

		while (pointIter.next()) {
			int curX = pointIter.x();
			int curY = pointIter.y();
			int curZ = getNearestZ(curX, curY, prevZ);
			if (hasGeoPos(prevX, prevY)) {
				int nswe = GeoUtils.computeNswe(prevX, prevY, curX, curY);
				if (!checkNearestNsweAntiCornerCut(prevX, prevY, prevZ, nswe)) {
					return false;
				}
			}

			prevX = curX;
			prevY = curY;
			prevZ = curZ;
		}

		if (hasGeoPos(prevX, prevY) && (prevZ != toZ)) {
			// different floors
			return false;
		}

		return true;
	}

	/**
	 * Checks if its possible to move from one location to another.
	 *
	 * @param from the {@code WorldObject} to start checking from
	 * @param toX  the X coordinate to end checking at
	 * @param toY  the Y coordinate to end checking at
	 * @param toZ  the Z coordinate to end checking at
	 * @return {@code true} if the character at start coordinates can move to end coordinates, {@code false} otherwise
	 */
	public boolean canMove(WorldObject from, double toX, double toY, double toZ) {
		return canMove(from.getX(), from.getY(), from.getZ(), toX, toY, toZ, from.getInstanceWorld());
	}

	/**
	 * Checks if its possible to move from one location to another.
	 *
	 * @param from the {@code WorldObject} to start checking from
	 * @param to   the {@code WorldObject} to end checking at
	 * @return {@code true} if the character at start coordinates can move to end coordinates, {@code false} otherwise
	 */
	public boolean canMove(WorldObject from, WorldObject to) {
		return canMove(from, to.getX(), to.getY(), to.getZ());
	}

	/**
	 * Checks the specified position for available geodata.
	 *
	 * @param x the X coordinate
	 * @param y the Y coordinate
	 * @return {@code true} if there is geodata for the given coordinates, {@code false} otherwise
	 */
	public boolean hasGeo(double x, double y) {
		return hasGeoPos(getGeoX(x), getGeoY(y));
	}
}
