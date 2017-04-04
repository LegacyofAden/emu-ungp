package org.l2junity.commons.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author PointerRage
 *
 */
@Slf4j
public abstract class ExecDelayedQueue<T> implements Runnable {
    private final static int NONE = 0;
    private final static int QUEUED = 1;
    private final static int RUNNING = 2;
    private final static int BLOCKED = 3;

    private final Queue<T> queue = new ArrayDeque<>();
    private final Lock lock = new ReentrantLock();
    private final AtomicInteger state = new AtomicInteger(NONE);

    private final ScheduledExecutorService executor;

    private final long delay;
    private final TimeUnit timeUnit;

    public ExecDelayedQueue(ScheduledExecutorService executor, long delay, TimeUnit timeUnit) {
        this.executor = executor;
        this.delay = delay;
        this.timeUnit = timeUnit;
    }

    @Override
    public final void run() {
        while (state.compareAndSet(QUEUED, RUNNING)) {
            try {
                processQueue();
            } finally {
                state.compareAndSet(RUNNING, NONE);
            }
        }
    }

    protected void processQueue() {
        T delayed;
        while ((delayed = queue.poll()) != null) {
            try {
                runDelayed(delayed);
            } catch (Throwable e) {
                log.error("{}", delayed.getClass().getCanonicalName(), e);
            }
        }
    }

    protected abstract void runDelayed(T delayed);

    public boolean add(T delayed) {
        if(state.get() == BLOCKED) {
            return false;
        }

        lock.lock();
        try {
            if(!queue.add(delayed)) {
                return false;
            }
        } finally {
            lock.unlock();
        }

        if(state.getAndSet(QUEUED) == NONE)
            executor.schedule(this, delay, timeUnit);

        return true;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void block() {
        state.set(BLOCKED);
    }

    public boolean isBlocked() {
        return state.get() == BLOCKED;
    }
}
