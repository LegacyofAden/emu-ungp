package org.l2junity.commons.network;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.NetworkConfig;
import org.l2junity.core.startup.StartupComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@StartupComponent("Network")
public class IOExecutorService extends AbstractExecutorService {
	@Getter(lazy = true)
	private static final IOExecutorService instance = new IOExecutorService();
	private static final AtomicInteger THREAD_FACTORY_COUNT = new AtomicInteger();

	public static ThreadFactory ioThreadFactory(final String name, final int priority) {
		return r -> {
			Thread thread = new Thread(r);
			thread.setName(name + "-" + THREAD_FACTORY_COUNT.incrementAndGet());
			thread.setPriority(priority);
			return thread;
		};
	}

	private final IOExecThread[] _executorThreads;
	private final AtomicBoolean _shutdown;
	private boolean _isThreadsStarted;
	private final AtomicInteger _activeTask;
	private final AtomicLong _executedTask;

	IOExecutorService() {
		_shutdown = new AtomicBoolean(false);
		_activeTask = new AtomicInteger(0);
		_executedTask = new AtomicLong(0);
		ArrayList<IOExecThread> executorThreads = new ArrayList<IOExecThread>();
		for (int i = 0; i < NetworkConfig.IO_EXECUTION_THREAD_NUM; i++) {
			IOExecThread execThread = new IOExecThread();
			execThread.setName("IOExecThread-" + i);
			executorThreads.add(execThread);
		}
		_executorThreads = executorThreads.toArray(new IOExecThread[executorThreads.size()]);
		_isThreadsStarted = false;
		startup();
	}

	private void startup() {
		if (!_isThreadsStarted) {
			for (Thread executorThread : _executorThreads) {
				executorThread.start();
				try {
					Thread.sleep(Math.min(16, NetworkConfig.IO_EXECUTION_FILL_DELAY));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			_isThreadsStarted = true;
		}
	}

	public int getActiveTaskCount() {
		return _activeTask.get();
	}

	public long getExecutedTaskCount() {
		return _executedTask.get();
	}

	public int getQueuedTaskCount() {
		int count = 0;
		for (IOExecThread execThread : _executorThreads) {
			count += execThread.getTaskCount();
		}
		return count;
	}

	public int getThreadCount() {
		return _executorThreads.length;
	}

	@Override
	public void shutdown() {
		_shutdown.set(true);
	}

	@Override
	public List<Runnable> shutdownNow() {
		_shutdown.set(true);
		ArrayList<Runnable> result = new ArrayList<Runnable>();
		Runnable r;
		for (IOExecThread execThread : _executorThreads) {
			while ((r = execThread.pollTask()) != null) {
				result.add(r);
			}
		}
		return result;
	}

	@Override
	public boolean isShutdown() {
		return _shutdown.get();
	}

	@Override
	public boolean isTerminated() {
		return _shutdown.get();
	}

	private boolean isAllThreadFinished() {
		for (IOExecThread execThread : _executorThreads) {
			if (!execThread.isFinished()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		final long maxTime = System.currentTimeMillis() + unit.toMillis(timeout);
		shutdown();

		while (System.currentTimeMillis() < maxTime && !isAllThreadFinished()) {
			Thread.sleep(NetworkConfig.IO_EXECUTION_FILL_DELAY * 16);
		}
		return isAllThreadFinished();
	}

	private void doExec(Runnable r) {
		_activeTask.incrementAndGet();
		try {
			r.run();
			_executedTask.incrementAndGet();
		} catch (Throwable th) {
			log.error("Exception in IOExecutorService", th);
		} finally {
			_activeTask.decrementAndGet();
		}
	}

	@Override
	public void execute(Runnable r) {
		int minTasks = Integer.MAX_VALUE;
		int minIdx = 0;
		for (int idx = 0; idx < _executorThreads.length; idx++) {
			int currTasks = 0;
			if ((currTasks = _executorThreads[idx].getTaskCount()) == 0) {
				_executorThreads[idx].put(r);
				return;
			}
			if (currTasks < minTasks) {
				minTasks = currTasks;
				minIdx = idx;
			}
		}
		if (minTasks < Integer.MAX_VALUE) {
			_executorThreads[minIdx].put(r);
		} else {
			doExec(r);
		}
	}

	private class IOExecThread extends Thread {
		private final Queue<Runnable> _workingQueue;
		private final AtomicInteger _taskCount;
		private boolean _isFinished;

		private IOExecThread() {
			_workingQueue = new ConcurrentLinkedQueue<Runnable>();
			_taskCount = new AtomicInteger(0);
			_isFinished = false;
			setPriority(Thread.NORM_PRIORITY);
		}

		public boolean isFinished() {
			return _isFinished;
		}

		private Runnable pollTask() {
			return _workingQueue.poll();
		}

		//TODO [izen] test for if(_taskCount.cas(0, 1)) put(r)

		public int getTaskCount() {
			return _taskCount.get();
		}

		public void put(Runnable r) {
			_workingQueue.add(r);
			_taskCount.incrementAndGet();
		}

		@Override
		public void run() {
			while (!_shutdown.get()) {
				int execCount = 0;
				try {
					if (_taskCount.get() > 0) {
						Runnable r = null;
						while ((r = pollTask()) != null) {
							execCount++;
							doExec(r);
						}
					}
				} catch (Exception ex) {
					log.error("Exception in IO executor thread", ex);
				} finally {
					if (execCount == 0 || _taskCount.addAndGet(-execCount) == 0) {
						try {
							Thread.sleep(NetworkConfig.IO_EXECUTION_FILL_DELAY);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
			_isFinished = true;
		}
	}
}

