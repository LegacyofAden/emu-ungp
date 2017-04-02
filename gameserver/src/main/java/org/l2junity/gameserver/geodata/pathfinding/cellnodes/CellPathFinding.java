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
package org.l2junity.gameserver.geodata.pathfinding.cellnodes;

import org.l2junity.gameserver.engines.IdFactory;
import org.l2junity.core.configs.GeoDataConfig;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.geodata.pathfinding.AbstractNode;
import org.l2junity.gameserver.geodata.pathfinding.AbstractNodeLoc;
import org.l2junity.gameserver.geodata.pathfinding.PathFinding;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Sami, DS Credits to Diamond
 */
public final class CellPathFinding extends PathFinding {
	private static final Logger LOGGER = LoggerFactory.getLogger(CellPathFinding.class);

	private BufferInfo[] _allBuffers;
	private int _findSuccess = 0;
	private int _findFails = 0;
	private int _postFilterUses = 0;
	private int _postFilterPlayableUses = 0;
	private int _postFilterPasses = 0;
	private long _postFilterElapsed = 0;

	private List<ItemInstance> _debugItems = null;

	public CellPathFinding() {
	}

	@Override
	public void load() {
		if (GeoDataConfig.PATHFINDING != 2) {
			return;
		}

		try {
			String[] array = GeoDataConfig.PATHFIND_BUFFERS.split(";");

			_allBuffers = new BufferInfo[array.length];

			String buf;
			String[] args;
			for (int i = 0; i < array.length; i++) {
				buf = array[i];
				args = buf.split("x");
				if (args.length != 2) {
					throw new Exception("Invalid buffer definition: " + buf);
				}

				_allBuffers[i] = new BufferInfo(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			}
		} catch (Exception e) {
			LOGGER.warn("CellPathFinding: Problem during buffer init: " + e.getMessage(), e);
			throw new Error("CellPathFinding: load aborted");
		}
	}

	@Override
	public boolean pathNodesExist(short regionoffset) {
		return false;
	}

	@Override
	public List<AbstractNodeLoc> findPath(double x, double y, double z, double tx, double ty, double tz, Instance instance, boolean playable) {
		int gx = GeoData.getInstance().getGeoX(x);
		int gy = GeoData.getInstance().getGeoY(y);
		if (!GeoData.getInstance().hasGeo(x, y)) {
			return null;
		}
		double gz = GeoData.getInstance().getHeight(x, y, z);
		int gtx = GeoData.getInstance().getGeoX(tx);
		int gty = GeoData.getInstance().getGeoY(ty);
		if (!GeoData.getInstance().hasGeo(tx, ty)) {
			return null;
		}
		double gtz = GeoData.getInstance().getHeight(tx, ty, tz);
		CellNodeBuffer buffer = alloc(64 + (2 * Math.max(Math.abs(gx - gtx), Math.abs(gy - gty))), playable);
		if (buffer == null) {
			return null;
		}

		boolean debug = playable && GeoDataConfig.DEBUG_PATH;

		if (debug) {
			if (_debugItems == null) {
				_debugItems = new CopyOnWriteArrayList<>();
			} else {
				for (ItemInstance item : _debugItems) {
					if (item == null) {
						continue;
					}
					item.decayMe();
				}

				_debugItems.clear();
			}
		}

		List<AbstractNodeLoc> path = null;
		try {
			CellNode result = buffer.findPath(gx, gy, (int) gz, gtx, gty, (int) gtz);

			if (debug) {
				for (CellNode n : buffer.debugPath()) {
					if (n.getCost() < 0) {
						dropDebugItem(1831, (int) (-n.getCost() * 10), n.getLoc());
					} else {
						// known nodes
						dropDebugItem(Inventory.ADENA_ID, (int) (n.getCost() * 10), n.getLoc());
					}
				}
			}

			if (result == null) {
				_findFails++;
				return null;
			}

			path = constructPath(result);
		} catch (Exception e) {
			LOGGER.warn("", e);
			return null;
		} finally {
			buffer.free();
		}

		if ((path.size() < 3) || (GeoDataConfig.MAX_POSTFILTER_PASSES <= 0)) {
			_findSuccess++;
			return path;
		}

		long timeStamp = System.currentTimeMillis();
		_postFilterUses++;
		if (playable) {
			_postFilterPlayableUses++;
		}

		double currentX, currentY, currentZ;
		ListIterator<AbstractNodeLoc> middlePoint;
		boolean remove;
		int pass = 0;
		do {
			pass++;
			_postFilterPasses++;

			remove = false;
			middlePoint = path.listIterator();
			currentX = x;
			currentY = y;
			currentZ = z;

			while (middlePoint.hasNext()) {
				AbstractNodeLoc locMiddle = middlePoint.next();
				if (!middlePoint.hasNext()) {
					break;
				}

				AbstractNodeLoc locEnd = path.get(middlePoint.nextIndex());
				if (GeoData.getInstance().canMove(currentX, currentY, currentZ, locEnd.getX(), locEnd.getY(), locEnd.getZ(), instance)) {
					middlePoint.remove();
					remove = true;
					if (debug) {
						dropDebugItem(735, 1, locMiddle);
					}
				} else {
					currentX = locMiddle.getX();
					currentY = locMiddle.getY();
					currentZ = locMiddle.getZ();
				}
			}
		}
		// only one postfilter pass for AI
		while (playable && remove && (path.size() > 2) && (pass < GeoDataConfig.MAX_POSTFILTER_PASSES));

		if (debug) {
			path.forEach(n -> dropDebugItem(65, 1, n));
		}

		_findSuccess++;
		_postFilterElapsed += System.currentTimeMillis() - timeStamp;
		return path;
	}

	private List<AbstractNodeLoc> constructPath(AbstractNode<NodeLoc> node) {
		LinkedList<AbstractNodeLoc> path = new LinkedList<>();
		int previousDirectionX = Integer.MIN_VALUE;
		int previousDirectionY = Integer.MIN_VALUE;
		int directionX, directionY;

		while (node.getParent() != null) {
			if (!GeoDataConfig.ADVANCED_DIAGONAL_STRATEGY && (node.getParent().getParent() != null)) {
				int tmpX = node.getLoc().getNodeX() - node.getParent().getParent().getLoc().getNodeX();
				int tmpY = node.getLoc().getNodeY() - node.getParent().getParent().getLoc().getNodeY();
				if (Math.abs(tmpX) == Math.abs(tmpY)) {
					directionX = tmpX;
					directionY = tmpY;
				} else {
					directionX = node.getLoc().getNodeX() - node.getParent().getLoc().getNodeX();
					directionY = node.getLoc().getNodeY() - node.getParent().getLoc().getNodeY();
				}
			} else {
				directionX = node.getLoc().getNodeX() - node.getParent().getLoc().getNodeX();
				directionY = node.getLoc().getNodeY() - node.getParent().getLoc().getNodeY();
			}

			// only add a new route point if moving direction changes
			if ((directionX != previousDirectionX) || (directionY != previousDirectionY)) {
				previousDirectionX = directionX;
				previousDirectionY = directionY;

				path.addFirst(node.getLoc());
				node.setLoc(null);
			}

			node = node.getParent();
		}

		return path;
	}

	private CellNodeBuffer alloc(int size, boolean playable) {
		CellNodeBuffer current = null;
		for (BufferInfo i : _allBuffers) {
			if (i.mapSize >= size) {
				for (CellNodeBuffer buf : i.bufs) {
					if (buf.lock()) {
						i.uses++;
						if (playable) {
							i.playableUses++;
						}
						i.elapsed += buf.getElapsedTime();
						current = buf;
						break;
					}
				}
				if (current != null) {
					break;
				}

				// not found, allocate temporary buffer
				current = new CellNodeBuffer(i.mapSize);
				current.lock();
				if (i.bufs.size() < i.count) {
					i.bufs.add(current);
					i.uses++;
					if (playable) {
						i.playableUses++;
					}
					break;
				}

				i.overflows++;
				if (playable) {
					i.playableOverflows++;
					// System.err.println("Overflow, size requested: " + size + " playable:"+playable);
				}
			}
		}

		return current;
	}

	private void dropDebugItem(int itemId, int num, AbstractNodeLoc loc) {
		final ItemInstance item = new ItemInstance(IdFactory.getInstance().getNextId(), itemId);
		item.setCount(num);
		item.spawnMe(loc.getX(), loc.getY(), loc.getZ());
		_debugItems.add(item);
	}

	private static final class BufferInfo {
		final int mapSize;
		final int count;
		ArrayList<CellNodeBuffer> bufs;
		int uses = 0;
		int playableUses = 0;
		int overflows = 0;
		int playableOverflows = 0;
		long elapsed = 0;

		public BufferInfo(int size, int cnt) {
			mapSize = size;
			count = cnt;
			bufs = new ArrayList<>(count);
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder(100);
			sb.append(mapSize);
			sb.append("x");
			sb.append(mapSize);
			sb.append(" num:");
			sb.append(bufs.size());
			sb.append("/");
			sb.append(count);
			sb.append(" uses:");
			sb.append(uses);
			sb.append("/");
			sb.append(playableUses);
			if (uses > 0) {
				sb.append(" total/avg(ms):");
				sb.append(elapsed);
				sb.append("/");
				sb.append(String.format("%1.2f", (double) elapsed / uses));
			}

			sb.append(" ovf:");
			sb.append(overflows);
			sb.append("/");
			sb.append(playableOverflows);

			return sb.toString();
		}
	}

	@Override
	public String[] getStat() {
		final String[] result = new String[_allBuffers.length + 1];
		for (int i = 0; i < _allBuffers.length; i++) {
			result[i] = _allBuffers[i].toString();
		}

		final StringBuilder sb = new StringBuilder(128);
		sb.append("LOS postfilter uses:");
		sb.append(_postFilterUses);
		sb.append("/");
		sb.append(_postFilterPlayableUses);
		if (_postFilterUses > 0) {
			sb.append(" total/avg(ms):");
			sb.append(_postFilterElapsed);
			sb.append("/");
			sb.append(String.format("%1.2f", (double) _postFilterElapsed / _postFilterUses));
			sb.append(" passes total/avg:");
			sb.append(_postFilterPasses);
			sb.append("/");
			sb.append(String.format("%1.1f", (double) _postFilterPasses / _postFilterUses));
			sb.append(System.lineSeparator());
		}
		sb.append("Pathfind success/fail:");
		sb.append(_findSuccess);
		sb.append("/");
		sb.append(_findFails);
		result[result.length - 1] = sb.toString();

		return result;
	}
}
