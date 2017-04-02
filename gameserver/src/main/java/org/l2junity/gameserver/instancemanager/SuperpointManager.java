/*
 * Copyright (C) 2004-2017 L2J Unity
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
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2MonsterInstance;
import org.l2junity.gameserver.model.debugger.DebugType;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcMoveNodeArrived;
import org.l2junity.gameserver.model.superpoint.Superpoint;
import org.l2junity.gameserver.model.superpoint.SuperpointInfo;
import org.l2junity.gameserver.model.superpoint.SuperpointNode;
import org.l2junity.gameserver.network.client.send.ExServerPrimitive;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;
import org.w3c.dom.Document;

import java.awt.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Sdw
 */
@Slf4j
@StartupComponent("Data")
public class SuperpointManager implements IGameXmlReader {
	@Getter(lazy = true)
	private static final SuperpointManager instance = new SuperpointManager();

	private final static Map<String, Superpoint> _routes = new HashMap<>();
	private final Map<Integer, SuperpointInfo> _activeRoutes = new HashMap<>();

	private SuperpointManager() {
		_routes.clear();
		parseDatapackDirectory("data/routes", true);
		log.info("Loaded {} superpoints.", _routes.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "route", routeNode ->
		{
			final String alias = parseString(routeNode.getAttributes(), "alias");
			final Superpoint point = new Superpoint(new StatsSet(parseAttributes(routeNode)));
			forEach(routeNode, "node", nodeNode ->
			{
				point.addNode(new SuperpointNode(new StatsSet(parseAttributes(nodeNode))));
			});
			_routes.put(alias, point);
		}));
	}

	/**
	 * Start to move given NPC by given route
	 *
	 * @param npc       NPC to move
	 * @param routeName name of route to move by
	 */
	public void startMoving(final Npc npc, final String routeName) {
		if (_routes.containsKey(routeName) && (npc != null) && !npc.isDead()) // check, if these route and NPC present
		{
			if (!_activeRoutes.containsKey(npc.getObjectId())) // new walk task
			{
				if ((npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_ACTIVE) || (npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE)) {
					final SuperpointInfo walk = new SuperpointInfo(_routes.get(routeName));

					if (npc.isDebug()) {
						walk.setLastAction(System.currentTimeMillis());
					}

					SuperpointNode node = walk.getCurrentNode();

					if ((npc.getX() == node.getX()) && (npc.getY() == node.getY())) {
						walk.calculateNextNode(npc);
						node = walk.getCurrentNode();
						npc.sendDebugMessage("Route '" + routeName + "': spawn point is same with first waypoint, adjusted to next", DebugType.WALKER);
					}

					if (!npc.isInRadius3d(node, 3000)) {
						final String message = "Route '" + routeName + "': NPC (id=" + npc.getId() + ", x=" + npc.getX() + ", y=" + npc.getY() + ", z=" + npc.getZ() + ") is too far from starting point (node x=" + node.getX() + ", y=" + node.getY() + ", z=" + node.getZ() + ", range=" + npc.distance3d(node) + "), walking will not start";
						log.warn(message);
						npc.sendDebugMessage(message, DebugType.WALKER);
						return;
					}

					npc.sendDebugMessage("Starting to move at route '" + routeName + "'", DebugType.WALKER);
					npc.setIsRunning(walk.isRunning());
					final ExServerPrimitive primitive = npc.isDebug() ? new ExServerPrimitive(routeName, npc) : null;
					if (primitive != null) {
						primitive.addLine(walk.getCurrentNodeId() + " -> " + (walk.getCurrentNodeId() + 1), Color.GREEN, true, npc, node);
						npc.sendDebugPacket(primitive, DebugType.WALKER);
					}
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, node);
					walk.setWalkCheckTask(ThreadPool.getInstance().scheduleGeneralAtFixedRate(() ->
					{
						startMoving(npc, routeName);
					}, 60000, 60000, TimeUnit.MILLISECONDS));

					_activeRoutes.put(npc.getObjectId(), walk); // register route
				} else {
					npc.sendDebugMessage("Failed to start moving along route '" + routeName + "', scheduled", DebugType.WALKER);
					ThreadPool.getInstance().scheduleGeneral(() ->
					{
						startMoving(npc, routeName);
					}, 60000, TimeUnit.MILLISECONDS);
				}
			} else {
				if (_activeRoutes.containsKey(npc.getObjectId()) && ((npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_ACTIVE) || (npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE) || (npc.getAI().getIntention() == CtrlIntention.AI_INTENTION_MOVE_TO))) {
					final SuperpointInfo walk = _activeRoutes.get(npc.getObjectId());
					if (walk == null) {
						return;
					}

					// Prevent call simultaneously from scheduled task and onArrived() or temporarily stop walking for resuming in future
					if (walk.isBlocked() || walk.isSuspended()) {
						npc.sendDebugMessage("Failed to continue moving along route '" + routeName + "' (operation is blocked)", DebugType.WALKER);
						return;
					}

					walk.setBlocked(true);
					final SuperpointNode node = walk.getCurrentNode();
					npc.sendDebugMessage("Route '" + routeName + "', continuing to node " + walk.getCurrentNodeId(), DebugType.WALKER);
					npc.setIsRunning(walk.isRunning());
					final ExServerPrimitive primitive = npc.isDebug() ? new ExServerPrimitive(routeName, npc) : null;
					if (primitive != null) {
						primitive.addLine((walk.getCurrentNodeId() - 1) + " -> " + walk.getCurrentNodeId(), Color.GREEN, true, npc, node);
						npc.sendDebugPacket(primitive, DebugType.WALKER);
					}
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, node);
					walk.setBlocked(false);
					walk.setStoppedByAttack(false);
				} else {
					npc.sendDebugMessage("Failed to continue moving along route '" + routeName + "' (wrong AI state - " + npc.getAI().getIntention() + ")", DebugType.WALKER);

				}
			}
		}
	}

	/**
	 * Resumes previously stopped moving
	 *
	 * @param npc NPC to resume
	 */
	public void resumeMoving(final Npc npc) {
		final SuperpointInfo walk = _activeRoutes.get(npc.getObjectId());
		if (walk != null) {
			walk.setSuspended(false);
			walk.setStoppedByAttack(false);
			startMoving(npc, walk.getRoute().getAlias());
		}
	}

	/**
	 * @param npc
	 * @return name of route
	 */
	public String getRouteName(Npc npc) {
		return _activeRoutes.containsKey(npc.getObjectId()) ? _activeRoutes.get(npc.getObjectId()).getRoute().getAlias() : "";
	}

	/**
	 * @param npc NPC to check
	 * @return {@code true} if given NPC, or its leader is controlled by Walking Manager and moves currently.
	 */
	public boolean isOnWalk(Npc npc) {
		if (npc.isMonster()) {
			if (((L2MonsterInstance) npc).getLeader() != null) {
				npc = ((L2MonsterInstance) npc).getLeader();
			}
		}

		if (!isRegistered(npc)) {
			return false;
		}

		final SuperpointInfo walk = _activeRoutes.get(npc.getObjectId());
		return !walk.isStoppedByAttack() && !walk.isSuspended();
	}

	/**
	 * Pause NPC moving until it will be resumed
	 *
	 * @param npc             NPC to pause moving
	 * @param suspend         {@code true} if moving was temporarily suspended for some reasons of AI-controlling script
	 * @param stoppedByAttack {@code true} if moving was suspended because of NPC was attacked or desired to attack
	 */
	public void stopMoving(Npc npc, boolean suspend, boolean stoppedByAttack) {
		if (npc.isMonster()) {
			if (((L2MonsterInstance) npc).getLeader() != null) {
				npc = ((L2MonsterInstance) npc).getLeader();
			}
		}

		if (!isRegistered(npc)) {
			return;
		}

		final SuperpointInfo walk = _activeRoutes.get(npc.getObjectId());

		walk.setSuspended(suspend);
		walk.setStoppedByAttack(stoppedByAttack);

		npc.stopMove(null);
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
	}

	/**
	 * Manage "node arriving"-related tasks: schedule move to next node; send ON_NODE_ARRIVED event to Quest script
	 *
	 * @param npc NPC to manage
	 */
	public void onArrived(final Npc npc) {
		final SuperpointInfo walk = _activeRoutes.get(npc.getObjectId());
		if (walk != null) {
			// Notify quest
			EventDispatcher.getInstance().notifyEventAsync(new OnNpcMoveNodeArrived(npc), npc);

			// Opposite should not happen... but happens sometime
			if ((walk.getCurrentNodeId() >= 0) && (walk.getCurrentNodeId() < walk.getRoute().getNodeSize())) {
				final SuperpointNode node = walk.getRoute().getNode(walk.getCurrentNodeId());
				if (npc.isInRadius2d(node, 10)) {
					npc.sendDebugMessage("Route '" + walk.getRoute().getAlias() + "', arrived to node " + walk.getCurrentNodeId(), DebugType.WALKER);
					npc.sendDebugMessage("Done in " + ((System.currentTimeMillis() - walk.getLastAction()) / 1000) + " s", DebugType.WALKER);

					if (!walk.calculateNextNode(npc)) {
						return;
					}

					walk.setBlocked(true); // prevents to be ran from walk check task, if there is delay in this node.

					if (node.getFStringId() > -1) {
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.getNpcStringId(node.getFStringId()));
					}

					if (node.getSocialId() > -1) {
						npc.broadcastSocialAction(node.getSocialId());
					}

					if (npc.isDebug()) {
						walk.setLastAction(System.currentTimeMillis());
					}

					if (_activeRoutes.containsKey(npc.getObjectId())) {
						if (node.getDelay() > 0) {
							ThreadPool.getInstance().scheduleGeneral(() ->
							{
								walk.setBlocked(false);
								startMoving(npc, walk.getRoute().getAlias());
							}, node.getDelay() * 1000L, TimeUnit.MILLISECONDS);
						} else {
							walk.setBlocked(false);
							startMoving(npc, walk.getRoute().getAlias());
						}
					}
				}
			}
		}
	}

	/**
	 * Cancel NPC moving permanently
	 *
	 * @param npc NPC to cancel
	 */
	public synchronized void cancelMoving(Npc npc) {
		final SuperpointInfo walk = _activeRoutes.remove(npc.getObjectId());
		if (walk != null) {
			walk.getWalkCheckTask().cancel(true);
		}
	}

	/**
	 * Manage "on death"-related tasks: permanently cancel moving of died NPC
	 *
	 * @param npc NPC to manage
	 */
	public void onDeath(Npc npc) {
		cancelMoving(npc);
	}

	/**
	 * Manage "on spawn"-related tasks: start NPC moving, if there is route attached to its spawn point
	 *
	 * @param npc NPC to manage
	 */
	public void onSpawn(Npc npc) {
		String routeName = npc.getParameters().getString("SP_NAME", null);
		if (routeName != null) {
			startMoving(npc, routeName);
		}

		routeName = npc.getParameters().getString("SuperPointName", null);
		if (routeName != null) {
			startMoving(npc, routeName);
		}
	}

	/**
	 * @param npc NPC to check
	 * @return {@code true} if given NPC controlled by Walking Manager.
	 */
	public boolean isRegistered(Npc npc) {
		return _activeRoutes.containsKey(npc.getObjectId());
	}

	public Superpoint getRoute(String route) {
		return _routes.get(route);
	}
}
