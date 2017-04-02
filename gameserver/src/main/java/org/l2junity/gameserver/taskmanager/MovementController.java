/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.taskmanager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.threading.AbstractPeriodicTaskManager;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.model.actor.Creature;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Despite the name the class is not identical to the original l2jfree class, however serves the same purpose.<br>
 * I just could not stand GameTimeManager handling movement process.
 *
 * @author lord_rex (the copy-paste work as always, MUHAHA)
 * @author Forsaiken (GameTimeController concept)
 * @author NB4L1 (original l2jfree concept)
 */

@Slf4j
@StartupComponent("Service")
public final class MovementController extends AbstractPeriodicTaskManager {
	@Getter(lazy = true)
	private static final MovementController instance = new MovementController();

	private static final Set<Creature> _movingObjects = ConcurrentHashMap.newKeySet();

	private MovementController() {
		super(100);
	}

	/**
	 * Registers a moving object into the controller.
	 *
	 * @param creature
	 */
	public void registerMovingObject(Creature creature) {
		if (creature == null) {
			return;
		}

		_movingObjects.add(creature);
	}

	@Override
	public void run() {
		try {
			_movingObjects.removeIf(Creature::updatePosition);
		} catch (final Throwable e) {
			log.error("Error while running MovementController task", e);
		}
	}
}