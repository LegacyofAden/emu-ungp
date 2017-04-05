package org.l2junity.commons.network;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public class PacketExecQueue<TClient extends Client> implements Queue<ReceivablePacket<TClient>>, Runnable, Executor {
	private final Executor _executor;
	private final Queue<ReceivablePacket<TClient>> _queue;
	private final AtomicReference<EQueueState> _state;

	private volatile long _lastAddTime;

	private enum EQueueState {
		NONE,
		QUEUED,
		RUNNING;
	}

	public PacketExecQueue(Executor executor) {
		_executor = executor;
		_queue = new ArrayDeque<>();
		_state = new AtomicReference<>(EQueueState.NONE);
	}

	private void exec() {
		if (_lastAddTime == 0) {
			_lastAddTime = System.currentTimeMillis();
		}
		_executor.execute(this);
	}

	@Override
	public void run() {
		while (_state.compareAndSet(EQueueState.QUEUED, EQueueState.RUNNING)) {
			try {
				Runnable r = null;
				while ((r = poll()) != null) {
					r.run();
				}
			} finally {
				_state.compareAndSet(EQueueState.RUNNING, EQueueState.NONE);
			}
		}
	}

	@Override
	public int size() {
		return _queue.size();
	}

	@Override
	public boolean isEmpty() {
		return _queue.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<ReceivablePacket<TClient>> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <E> E[] toArray(E[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends ReceivablePacket<TClient>> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		synchronized (_queue) {
			_queue.clear();
		}
	}

	@Override
	public void execute(Runnable command) {
		if (command != null && command instanceof ReceivablePacket) {
			add((ReceivablePacket<TClient>) command);
		}
	}

	@Override
	public boolean add(ReceivablePacket<TClient> packet) {
		synchronized (_queue) {
			if (!_queue.add(packet)) {
				return false;
			}
		}

		if (_state.getAndSet(EQueueState.QUEUED) == EQueueState.NONE) {
			exec();
		}

		return true;
	}

	@Override
	public boolean offer(ReceivablePacket<TClient> packet) {
		synchronized (_queue) {
			return _queue.offer(packet);
		}
	}

	@Override
	public ReceivablePacket<TClient> remove() {
		synchronized (_queue) {
			return _queue.remove();
		}
	}

	@Override
	public ReceivablePacket<TClient> poll() {
		synchronized (_queue) {
			return _queue.poll();
		}
	}

	@Override
	public ReceivablePacket<TClient> element() {
		synchronized (_queue) {
			return _queue.element();
		}
	}

	@Override
	public ReceivablePacket<TClient> peek() {
		synchronized (_queue) {
			return _queue.peek();
		}
	}
}
