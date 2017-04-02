package org.l2junity.commons.threading;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.text.StrBuilder;
import org.l2junity.core.configs.ThreadPoolConfig;
import org.l2junity.core.startup.StartupComponent;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@StartupComponent("Threading")
public final class ThreadPool {
	@Getter(lazy = true)
	private static final ThreadPool instance = new ThreadPool();

	private ScheduledThreadPoolExecutor effectsScheduledThreadPool;
	private ScheduledThreadPoolExecutor generalScheduledThreadPool;
	private ScheduledThreadPoolExecutor aiScheduledThreadPool;

	private boolean _shutdown;

	private ThreadPool() {
		effectsScheduledThreadPool = new ScheduledThreadPoolExecutor(ThreadPoolConfig.EFFECTS_CORE_POOL_SIZE * Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("EffectsSTPool", Thread.MIN_PRIORITY));
		generalScheduledThreadPool = new ScheduledThreadPoolExecutor(ThreadPoolConfig.GENERAL_CORE_POOL_SIZE * Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("GeneralSTPool", Thread.NORM_PRIORITY));
		aiScheduledThreadPool = new ScheduledThreadPoolExecutor(ThreadPoolConfig.AI_CORE_POOL_SIZE * Runtime.getRuntime().availableProcessors(), new PriorityThreadFactory("AISTPool", Thread.MAX_PRIORITY));

		scheduleGeneralAtFixedRate(new PurgeTask(), 5, 5, TimeUnit.MINUTES);
		log.info("ThreadPool initialized.");
	}

	/**
	 * Schedules an effect task to be executed after the given delay.
	 *
	 * @param task  the task to execute
	 * @param delay the delay in the given time unit
	 * @param unit  the time unit of the delay parameter
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
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
	 * @param task         the task to execute
	 * @param initialDelay the initial delay in the given time unit
	 * @param period       the period between executions in the given time unit
	 * @param unit         the time unit of the initialDelay and period parameters
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
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
	 * @param task  the task to execute
	 * @param delay the delay in the given time unit
	 * @param unit  the time unit of the delay parameter
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
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
	 * @param task         the task to execute
	 * @param initialDelay the initial delay in the given time unit
	 * @param period       the period between executions in the given time unit
	 * @param unit         the time unit of the initialDelay and period parameters
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleGeneralAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
		try {
			return generalScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Schedules an AI task to be executed after the given delay.
	 *
	 * @param task  the task to execute
	 * @param delay the delay in the given time unit
	 * @param unit  the time unit of the delay parameter
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleAi(Runnable task, long delay, TimeUnit unit) {
		try {
			return aiScheduledThreadPool.schedule(new RunnableWrapper(task), delay, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Schedules a general task to be executed at fixed rate.
	 *
	 * @param task         the task to execute
	 * @param initialDelay the initial delay in the given time unit
	 * @param period       the period between executions in the given time unit
	 * @param unit         the time unit of the initialDelay and period parameters
	 * @return a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation
	 */
	public ScheduledFuture<?> scheduleAiAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
		try {
			return aiScheduledThreadPool.scheduleAtFixedRate(new RunnableWrapper(task), initialDelay, period, unit);
		} catch (RejectedExecutionException e) {
			return null;
		}
	}

	/**
	 * Executes an AI task sometime in future in another thread.
	 *
	 * @param task the task to execute
	 */
	public void executeAi(Runnable task) {
		try {
			aiScheduledThreadPool.execute(new RunnableWrapper(task));
		} catch (RejectedExecutionException e) {
		}
	}

	/**
	 * Executes an general task sometime in future in another thread.
	 *
	 * @param task the task to execute
	 */
	public void executeGeneral(Runnable task) {
		try {
			generalScheduledThreadPool.execute(new RunnableWrapper(task));
		} catch (RejectedExecutionException e) {
		}
	}

	public boolean hasTasks() {
		return !aiScheduledThreadPool.getQueue().isEmpty();
	}

	public String getStats() {
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
		builder.appendln(" + AI:");
		builder.appendln(" |- ActiveThreads:   " + aiScheduledThreadPool.getActiveCount());
		builder.appendln(" |- getCorePoolSize: " + aiScheduledThreadPool.getCorePoolSize());
		builder.appendln(" |- PoolSize:        " + aiScheduledThreadPool.getPoolSize());
		builder.appendln(" |- MaximumPoolSize: " + aiScheduledThreadPool.getMaximumPoolSize());
		builder.appendln(" |- CompletedTasks:  " + aiScheduledThreadPool.getCompletedTaskCount());
		builder.appendln(" |- ScheduledTasks:  " + aiScheduledThreadPool.getQueue().size());
		builder.appendln(" | -------");
		return builder.toString();
	}

	private static class PriorityThreadFactory implements ThreadFactory {
		private final int priority;
		private final String name;
		private final AtomicInteger _threadNumber = new AtomicInteger(1);
		private final ThreadGroup group;

		PriorityThreadFactory(String name, int priority) {
			this.priority = priority;
			this.name = name;
			group = new ThreadGroup(this.name);
		}

		@Override
		public Thread newThread(Runnable runnable) {
			Thread t = new Thread(group, runnable, name + "-" + _threadNumber.getAndIncrement());
			t.setPriority(priority);
			return t;
		}

		public ThreadGroup getGroup() {
			return group;
		}
	}

	public void shutdown() {
		_shutdown = true;
		try {
			aiScheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);
			effectsScheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);
			generalScheduledThreadPool.awaitTermination(1, TimeUnit.SECONDS);
			aiScheduledThreadPool.shutdown();
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

	private class PurgeTask implements Runnable {
		@Override
		public void run() {
			effectsScheduledThreadPool.purge();
			generalScheduledThreadPool.purge();
			aiScheduledThreadPool.purge();
		}
	}

	private class RunnableWrapper implements Runnable {
		private final Runnable _r;

		private RunnableWrapper(final Runnable r) {
			_r = r;
		}

		@Override
		public final void run() {
			try {
				_r.run();
			} catch (Exception e) {
				log.error("Error while running RunnableWrapper:", e);
				throw new RuntimeException(e);
			}
		}
	}
}
