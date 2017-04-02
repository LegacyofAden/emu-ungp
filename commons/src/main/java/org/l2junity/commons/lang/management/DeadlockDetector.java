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
package org.l2junity.commons.lang.management;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.util.CommonUtil;
import org.l2junity.core.startup.StartupComponent;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@StartupComponent("BeforeStart")
public final class DeadlockDetector extends Thread {
	@Getter(lazy = true)
	private static final DeadlockDetector instance = new DeadlockDetector();

	private final Set<Long> _logged = new HashSet<>();

	public static boolean ALLOWED = false;
	public static boolean RESTART_ON_DEADLOCK = true;
	public static long CHECK_INTERVAL = 20_000;
	public static Runnable RESTART_EVENT = null;

	protected DeadlockDetector() {
		super("DeadlockDetector");
		setPriority(Thread.MAX_PRIORITY);
		setDaemon(true);

		if (!ALLOWED) {
			log.info("Disabled.");
			return;
		}

		start();

		log.info("Initialized.");
	}

	@Override
	public void run() {
		final long[] ids = findDeadlockedThreadIds();
		if (ids == null) {
			return;
		}

		final List<Thread> deadlocked = new ArrayList<>();

		for (final long id : ids) {
			if (_logged.add(id)) {
				deadlocked.add(findThreadById(id));
			}
		}

		if (!deadlocked.isEmpty()) {
			CommonUtil.printSection("Deadlocked Thread(s)");
			for (final Thread thread : deadlocked) {
				log.error("{}", ManagementFactory.getThreadMXBean().getThreadInfo(thread.getId()));
			}

			if (RESTART_EVENT != null) {
				RESTART_EVENT.run();
			}

			new Halt().start();

			if (RESTART_ON_DEADLOCK) {
				ShutdownManager.getInstance().restart("DeadlockDetector");
			}
		}

		try {
			Thread.sleep(CHECK_INTERVAL);
		} catch (InterruptedException e) {
			log.warn("", e);
		}
	}

	private static long[] findDeadlockedThreadIds() {
		if (ManagementFactory.getThreadMXBean().isSynchronizerUsageSupported()) {
			return ManagementFactory.getThreadMXBean().findDeadlockedThreads();
		}
		return ManagementFactory.getThreadMXBean().findMonitorDeadlockedThreads();
	}

	private static Thread findThreadById(long id) {
		for (final Thread thread : Thread.getAllStackTraces().keySet()) {
			if (thread.getId() == id) {
				return thread;
			}
		}

		throw new IllegalStateException("Deadlocked Thread not found!");
	}

	protected static final class Halt extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(40_000);
			} catch (final InterruptedException e) {
				log.error("", e);
			} finally {
				ShutdownManager.getInstance().halt(DeadlockDetector.class.getSimpleName());
			}
		}
	}
}
