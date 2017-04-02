package org.l2junity.commons.threading;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.text.StrBuilder;
import org.l2junity.core.configs.ThreadPoolConfig;
import org.l2junity.core.startup.StartupComponent;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartupComponent("Threading")
public final class ThreadPool {
	@Getter(lazy = true)
	private static final ThreadPool instance = new ThreadPool();

	private final ScheduledExecutorService effectsScheduledThreadPool;
	private final ScheduledExecutorService generalScheduledThreadPool;

	private boolean _shutdown;

	private ThreadPool() {
		final ScheduledThreadPoolExecutor effectsScheduledThreadPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(ThreadPoolConfig.EFFECTS_CORE_POOL_SIZE, new PriorityThreadFactory("EffectsPool", Thread.MIN_PRIORITY));
		effectsScheduledThreadPool.setRemoveOnCancelPolicy(false);
		this.effectsScheduledThreadPool = effectsScheduledThreadPool;

		final ScheduledThreadPoolExecutor generalScheduledThreadPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(ThreadPoolConfig.GENERAL_CORE_POOL_SIZE, new PriorityThreadFactory("GeneralPool", Thread.NORM_PRIORITY));
		generalScheduledThreadPool.setKeepAliveTime(100, TimeUnit.MILLISECONDS);
		generalScheduledThreadPool.setRemoveOnCancelPolicy(true);
		this.generalScheduledThreadPool = generalScheduledThreadPool;

		scheduleGeneralAtFixedRate(() -> effectsScheduledThreadPool.purge(), 5, 5, TimeUnit.MINUTES);
		log.info("ThreadPool initialized.");
	}

	/**
	 * Schedules an effect task to be executed after the given delay.
	 * 
	 * @param task
	 *            the task to execute
	 * @param delay
	 *            the delay in the given time unit
	 * @param unit
	 *            the time unit of the delay parameter
	 * @return a ScheduledFuture representing pending completion of the task,
	 *         and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleEffect(Runnable task, long delay, TimeUnit unit) {
		try {
			return effectsScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Schedules an effect task to be executed at fixed rate.
	 * 
	 * @param task
	 *            the task to execute
	 * @param initialDelay
	 *            the initial delay in the given time unit
	 * @param period
	 *            the period between executions in the given time unit
	 * @param unit
	 *            the time unit of the initialDelay and period parameters
	 * @return a ScheduledFuture representing pending completion of the task,
	 *         and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleEffectAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
		try {
			return effectsScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Schedules a general task to be executed after the given delay.
	 * 
	 * @param task
	 *            the task to execute
	 * @param delay
	 *            the delay in the given time unit
	 * @param unit
	 *            the time unit of the delay parameter
	 * @return a ScheduledFuture representing pending completion of the task,
	 *         and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleGeneral(Runnable task, long delay, TimeUnit unit) {
		try {
			return generalScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Schedules a general task to be executed at fixed rate.
	 * 
	 * @param task
	 *            the task to execute
	 * @param initialDelay
	 *            the initial delay in the given time unit
	 * @param period
	 *            the period between executions in the given time unit
	 * @param unit
	 *            the time unit of the initialDelay and period parameters
	 * @return a ScheduledFuture representing pending completion of the task,
	 *         and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
		try {
			return generalScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Executes an general task sometime in future in another thread.
	 * 
	 * @param task
	 *            the task to execute
	 * @return a Future representing pending completion of the task
	 */
	public Future<?> executeGeneral(Runnable task) {
		try {
			return generalScheduledThreadPool.submit(new RunnableWrapper(task));
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	public String getStats() {
		final ScheduledThreadPoolExecutor effectsScheduledThreadPool = (ScheduledThreadPoolExecutor) this.effectsScheduledThreadPool;
		final ScheduledThreadPoolExecutor generalScheduledThreadPool = (ScheduledThreadPoolExecutor) this.generalScheduledThreadPool;

		StrBuilder builder = new StrBuilder();
		builder.appendln("STP:");
		builder.appendln(" + Effects:");
		builder.appendln(" |- ActiveThreads:   " + effectsScheduledThreadPool.getActiveCount());
		builder.appendln(" |- getCorePoolSize: " + effectsScheduledThreadPool.getCorePoolSize());
		builder.appendln(" |- PoolSize:        " + effectsScheduledThreadPool.getPoolSize());
		builder.appendln(" |- MaximumPoolSize: " + effectsScheduledThreadPool.getMaximumPoolSize());
		builder.appendln(" |- CompletedTasks:  " + effectsScheduledThreadPool.getCompletedTaskCount());
		builder.appendln(" |- ScheduledTasks:  " + effectsScheduledThreadPool.getQueue().size());
		builder.appendln(" | -------");
		builder.appendln(" + General:");
		builder.appendln(" |- ActiveThreads:   " + generalScheduledThreadPool.getActiveCount());
		builder.appendln(" |- getCorePoolSize: " + generalScheduledThreadPool.getCorePoolSize());
		builder.appendln(" |- PoolSize:        " + generalScheduledThreadPool.getPoolSize());
		builder.appendln(" |- MaximumPoolSize: " + generalScheduledThreadPool.getMaximumPoolSize());
		builder.appendln(" |- CompletedTasks:  " + generalScheduledThreadPool.getCompletedTaskCount());
		builder.appendln(" |- ScheduledTasks:  " + generalScheduledThreadPool.getQueue().size());
		builder.appendln(" | -------");
		return builder.toString();
	}

	public void shutdown() {
		_shutdown = true;
		try {
			effectsScheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);
			generalScheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);
			effectsScheduledThreadPool.shutdown();
			generalScheduledThreadPool.shutdown();
			log.info("All ThreadPools are now stopped");
		} catch (InterruptedException e) {
			log.warn("There has been a problem shutting down the thread pool manager!", e);
		}
	}

	public boolean isShutdown() {
		return _shutdown;
	}
}
