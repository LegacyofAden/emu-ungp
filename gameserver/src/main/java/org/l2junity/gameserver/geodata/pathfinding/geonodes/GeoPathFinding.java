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
package org.l2junity.gameserver.geodata.pathfinding.geonodes;

import org.l2junity.commons.lang.management.ShutdownManager;
import org.l2junity.commons.lang.management.TerminationStatus;
import org.l2junity.commons.util.BasePathProvider;
import org.l2junity.core.configs.GeoDataConfig;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.geodata.pathfinding.AbstractNode;
import org.l2junity.gameserver.geodata.pathfinding.AbstractNodeLoc;
import org.l2junity.gameserver.geodata.pathfinding.PathFinding;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.world.WorldData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author -Nemesiss-
 */
public final class GeoPathFinding extends PathFinding {
	private static final Logger LOGGER = LoggerFactory.getLogger(GeoPathFinding.class);

	private static final String FILE_NAME_FORMAT = "%d_%d.pn";

	private final Map<Short, ByteBuffer> _pathNodes = new ConcurrentHashMap<>();
	private final Map<Short, IntBuffer> _pathNodesIndex = new ConcurrentHashMap<>();

	public GeoPathFinding() {
	}

	@Override
	public void load() {
		if (GeoDataConfig.PATHFINDING != 1) {
			return;
		}

		int loadedRegions = 0;
		try {
			for (int regionX = WorldData.TILE_X_MIN; regionX <= WorldData.TILE_X_MAX; regionX++) {
				for (int regionY = WorldData.TILE_Y_MIN; regionY <= WorldData.TILE_Y_MAX; regionY++) {
					final Path pnFilePath = BasePathProvider.resolveDatapackPath(GeoDataConfig.PATHNODE_PATH, String.format(FILE_NAME_FORMAT, regionX, regionY));
					LOGGER.debug("Loading " + pnFilePath.getFileName() + "...");
					loadPathNodeFile(pnFilePath, (byte) regionX, (byte) regionY);
					loadedRegions++;
				}
			}
		} catch (Exception e) {
			LOGGER.error("Failed to load pathnode!", e);
			ShutdownManager.getInstance().halt(TerminationStatus.ENVIRONMENT_MISSING_COMPONENT_OR_SERVICE);
		}

		LOGGER.info("Loaded " + loadedRegions + " pathnode region(s).");
	}

	@Override
	public boolean pathNodesExist(short regionoffset) {
		return _pathNodesIndex.containsKey(regionoffset);
	}

	@Override
	public List<AbstractNodeLoc> findPath(double x, double y, double z, double tx, double ty, double tz, Instance instance, boolean playable) {
		int gx = ((int) x - WorldData.MAP_MIN_X) >> 4;
		int gy = ((int) y - WorldData.MAP_MIN_Y) >> 4;
		short gz = (short) z;
		int gtx = ((int) tx - WorldData.MAP_MIN_X) >> 4;
		int gty = ((int) ty - WorldData.MAP_MIN_Y) >> 4;
		short gtz = (short) tz;

		GeoNode start = readNode(gx, gy, gz);
		GeoNode end = readNode(gtx, gty, gtz);
		if ((start == null) || (end == null)) {
			return null;
		}
		if (Math.abs(start.getLoc().getZ() - z) > 55) {
			return null; // not correct layer
		}
		if (Math.abs(end.getLoc().getZ() - tz) > 55) {
			return null; // not correct layer
		}
		if (start == end) {
			return null;
		}

		// TODO: Find closest path node we CAN access. Now only checks if we can not reach the closest
		Location temp = GeoData.getInstance().moveCheck(x, y, z, start.getLoc().getX(), start.getLoc().getY(), start.getLoc().getZ(), instance);
		if ((temp.getX() != start.getLoc().getX()) || (temp.getY() != start.getLoc().getY())) {
			return null; // cannot reach closest...
		}

		// TODO: Find closest path node around target, now only checks if final location can be reached
		temp = GeoData.getInstance().moveCheck(tx, ty, tz, end.getLoc().getX(), end.getLoc().getY(), end.getLoc().getZ(), instance);
		if ((temp.getX() != end.getLoc().getX()) || (temp.getY() != end.getLoc().getY())) {
			return null; // cannot reach closest...
		}

		// return searchAStar(start, end);
		return searchByClosest2(start, end);
	}

	public List<AbstractNodeLoc> searchByClosest2(GeoNode start, GeoNode end) {
		// Always continues checking from the closest to target non-blocked
		// node from to_visit list. There's extra length in path if needed
		// to go backwards/sideways but when moving generally forwards, this is extra fast
		// and accurate. And can reach insane distances (try it with 800 nodes..).
		// Minimum required node count would be around 300-400.
		// Generally returns a bit (only a bit) more intelligent looking routes than
		// the basic version. Not a true distance image (which would increase CPU
		// load) level of intelligence though.

		// List of Visited Nodes
		List<GeoNode> visited = new ArrayList<>(550);

		// List of Nodes to Visit
		LinkedList<GeoNode> to_visit = new LinkedList<>();
		to_visit.add(start);
		int targetX = end.getLoc().getNodeX();
		int targetY = end.getLoc().getNodeY();

		int dx, dy;
		boolean added;
		int i = 0;
		while (i < 550) {
			GeoNode node;
			try {
				node = to_visit.removeFirst();
			} catch (Exception e) {
				// No Path found
				return null;
			}
			if (node.equals(end)) {
				return constructPath2(node);
			}

			i++;
			visited.add(node);
			node.attachNeighbors();
			GeoNode[] neighbors = node.getNeighbors();
			if (neighbors == null) {
				continue;
			}
			for (GeoNode n : neighbors) {
				if ((visited.lastIndexOf(n) == -1) && !to_visit.contains(n)) {
					added = false;
					n.setParent(node);
					dx = targetX - n.getLoc().getNodeX();
					dy = targetY - n.getLoc().getNodeY();
					n.setCost((dx * dx) + (dy * dy));
					for (int index = 0; index < to_visit.size(); index++) {
						// supposed to find it quite early..
						if (to_visit.get(index).getCost() > n.getCost()) {
							to_visit.add(index, n);
							added = true;
							break;
						}
					}
					if (!added) {
						to_visit.addLast(n);
					}
				}
			}
		}
		// No Path found
		return null;
	}

	public List<AbstractNodeLoc> constructPath2(AbstractNode<GeoNodeLoc> node) {
		LinkedList<AbstractNodeLoc> path = new LinkedList<>();
		int previousDirectionX = -1000;
		int previousDirectionY = -1000;
		int directionX;
		int directionY;

		while (node.getParent() != null) {
			// only add a new route point if moving direction changes
			directionX = node.getLoc().getNodeX() - node.getParent().getLoc().getNodeX();
			directionY = node.getLoc().getNodeY() - node.getParent().getLoc().getNodeY();

			if ((directionX != previousDirectionX) || (directionY != previousDirectionY)) {
				previousDirectionX = directionX;
				previousDirectionY = directionY;
				path.addFirst(node.getLoc());
			}
			node = node.getParent();
		}
		return path;
	}

	@Override
	public GeoNode[] readNeighbors(GeoNode n, int idx) {
		int node_x = n.getLoc().getNodeX();
		int node_y = n.getLoc().getNodeY();
		// short node_z = n.getLoc().getZ();

		short regoffset = getRegionOffset(getRegionX(node_x), getRegionY(node_y));
		ByteBuffer pn = _pathNodes.get(regoffset);

		List<AbstractNode<GeoNodeLoc>> Neighbors = new ArrayList<>(8);
		GeoNode newNode;
		short new_node_x, new_node_y;

		// Region for sure will change, we must read from correct file
		byte neighbor = pn.get(idx++); // N
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) node_x;
			new_node_y = (short) (node_y - 1);
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // NE
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) (node_x + 1);
			new_node_y = (short) (node_y - 1);
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // E
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) (node_x + 1);
			new_node_y = (short) node_y;
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // SE
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) (node_x + 1);
			new_node_y = (short) (node_y + 1);
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // S
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) node_x;
			new_node_y = (short) (node_y + 1);
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // SW
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) (node_x - 1);
			new_node_y = (short) (node_y + 1);
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // W
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) (node_x - 1);
			new_node_y = (short) node_y;
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		neighbor = pn.get(idx++); // NW
		if (neighbor > 0) {
			neighbor--;
			new_node_x = (short) (node_x - 1);
			new_node_y = (short) (node_y - 1);
			newNode = readNode(new_node_x, new_node_y, neighbor);
			if (newNode != null) {
				Neighbors.add(newNode);
			}
		}
		GeoNode[] result = new GeoNode[Neighbors.size()];
		return Neighbors.toArray(result);
	}

	// Private

	private GeoNode readNode(short node_x, short node_y, byte layer) {
		short regoffset = getRegionOffset(getRegionX(node_x), getRegionY(node_y));
		if (!pathNodesExist(regoffset)) {
			return null;
		}
		short nbx = getNodeBlock(node_x);
		short nby = getNodeBlock(node_y);
		int idx = _pathNodesIndex.get(regoffset).get((nby << 8) + nbx);
		ByteBuffer pn = _pathNodes.get(regoffset);
		// reading
		byte nodes = pn.get(idx);
		idx += (layer * 10) + 1;// byte + layer*10byte
		if (nodes < layer) {
			LOGGER.warn("Nodes shouldn't be lower than layer!");
		}
		short node_z = pn.getShort(idx);
		idx += 2;
		return new GeoNode(new GeoNodeLoc(node_x, node_y, node_z), idx);
	}

	private GeoNode readNode(int gx, int gy, short z) {
		short node_x = getNodePos(gx);
		short node_y = getNodePos(gy);
		short regoffset = getRegionOffset(getRegionX(node_x), getRegionY(node_y));
		if (!pathNodesExist(regoffset)) {
			return null;
		}
		short nbx = getNodeBlock(node_x);
		short nby = getNodeBlock(node_y);
		int idx = _pathNodesIndex.get(regoffset).get((nby << 8) + nbx);
		ByteBuffer pn = _pathNodes.get(regoffset);
		// reading
		byte nodes = pn.get(idx++);
		int idx2 = 0; // create index to nearlest node by z
		short last_z = Short.MIN_VALUE;
		while (nodes > 0) {
			short node_z = pn.getShort(idx);
			if (Math.abs(last_z - z) > Math.abs(node_z - z)) {
				last_z = node_z;
				idx2 = idx + 2;
			}
			idx += 10; // short + 8 byte
			nodes--;
		}
		return new GeoNode(new GeoNodeLoc(node_x, node_y, last_z), idx2);
	}

	private void loadPathNodeFile(Path pnFilePath, byte rx, byte ry) {
		if ((rx < WorldData.TILE_X_MIN) || (rx > WorldData.TILE_X_MAX) || (ry < WorldData.TILE_Y_MIN) || (ry > WorldData.TILE_Y_MAX)) {
			LOGGER.warn("Failed to Load PathNode File: invalid region " + rx + "," + ry + System.lineSeparator());
			return;
		}
		short regionoffset = getRegionOffset(rx, ry);
		LOGGER.debug("Path Engine: - Loading: " + pnFilePath + " -> region offset: " + regionoffset + " X: " + rx + " Y: " + ry);
		int node = 0, size, index = 0;

		// Create a read-only memory-mapped file
		try (RandomAccessFile raf = new RandomAccessFile(pnFilePath.toFile(), "r");
			 FileChannel roChannel = raf.getChannel()) {
			size = (int) roChannel.size();
			MappedByteBuffer nodes;
			if (GeoDataConfig.FORCE_GEODATA) {
				// it is not guarantee, because the underlying operating system may have paged out some of the buffer's data
				nodes = roChannel.map(FileChannel.MapMode.READ_ONLY, 0, size).load();
			} else {
				nodes = roChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			}

			// Indexing pathnode files, so we will know where each block starts
			IntBuffer indexs = IntBuffer.allocate(65536);

			while (node < 65536) {
				byte layer = nodes.get(index);
				indexs.put(node++, index);
				index += (layer * 10) + 1;
			}
			_pathNodesIndex.put(regionoffset, indexs);
			_pathNodes.put(regionoffset, nodes);
		} catch (Exception e) {
			LOGGER.warn("Failed to Load PathNode File: " + pnFilePath + " : " + e.getMessage(), e);
		}
	}
}
