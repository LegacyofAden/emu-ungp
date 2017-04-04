package org.l2junity.commons.threading;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author ANZO
 * @since 23.03.2017
 */
@Slf4j
public class AbstractPeriodicTaskManager implements Runnable {
	private final long period;
	private ScheduledFuture<?> task;

	protected AbstractPeriodicTaskManager(long period) {
		if (period <= 0) {
			throw new IllegalArgumentException("period must be positive");
		}
		this.period = period;
		task = ThreadPool.getInstance().scheduleGeneralAtFixedRate(this, 0, 1000 + this.period, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
	}

	public void cancel() {
		task.cancel(true);
	}
}
