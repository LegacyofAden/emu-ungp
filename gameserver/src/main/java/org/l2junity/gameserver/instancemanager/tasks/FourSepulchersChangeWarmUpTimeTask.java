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
package org.l2junity.gameserver.instancemanager.tasks;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.instancemanager.FourSepulchersManager;

import java.util.Calendar;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Four Sepulchers change warm up time task.
 *
 * @author xban1x
 */
public final class FourSepulchersChangeWarmUpTimeTask implements Runnable {
	@Override
	public void run() {
		final FourSepulchersManager manager = FourSepulchersManager.getInstance();
		manager.setIsEntryTime(true);
		manager.setIsWarmUpTime(false);
		manager.setIsAttackTime(false);
		manager.setIsCoolDownTime(false);

		long interval = 0;
		// searching time when warmup time will be ended:
		// counting difference between time when warmup time ends and
		// current time
		// and then launching change time task
		if (manager.isFirstTimeRun()) {
			interval = manager.getWarmUpTimeEnd() - Calendar.getInstance().getTimeInMillis();
		} else {
			interval = GeneralConfig.FS_TIME_WARMUP * 60000L;
		}

		manager.setChangeAttackTimeTask(ThreadPool.getInstance().scheduleGeneral(new FourSepulchersChangeAttackTimeTask(), interval, TimeUnit.MILLISECONDS));
		final ScheduledFuture<?> changeWarmUpTimeTask = manager.getChangeWarmUpTimeTask();

		if (changeWarmUpTimeTask != null) {
			changeWarmUpTimeTask.cancel(true);
			manager.setChangeWarmUpTimeTask(null);
		}
	}
}
