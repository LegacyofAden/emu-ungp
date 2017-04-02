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
package org.l2junity.commons.idfactory;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.concurrent.CloseableReentrantLock;
import org.l2junity.commons.sql.DatabaseFactory;
import org.l2junity.core.startup.StartupComponent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

@Slf4j
@StartupComponent(value = "Database", dependency = DatabaseFactory.class)
public abstract class AbstractIdFactory {
	private final IdFactoryAllocator allocator = new IdFactoryAllocator(Integer.MAX_VALUE);
	private final CloseableReentrantLock lock = new CloseableReentrantLock();
	private final Condition availableCond = lock.newCondition();

	private int allocate() {
		try (CloseableReentrantLock temp = lock.open()) {
			final int index = allocator.nextFreeIndex();
			if (index == -1) {
				throw new AllocationException("No available indexes in pool.");
			}
			allocator.markUsed(index);
			return index;
		}
	}

	private int allocate(final long time, final TimeUnit unit) throws InterruptedException {
		try (CloseableReentrantLock temp = lock.open()) {
			int index = allocator.nextFreeIndex(availableCond);
			if (index == -1) {
				if (availableCond.await(time, unit)) {
					index = allocator.nextFreeIndex();
				} else {
					throw new AllocationException("No available indexes in pool.");
				}
			}
			allocator.markUsed(index);
			return index;
		}
	}

	public void releaseId(final int id) {
		try (CloseableReentrantLock temp = lock.open()) {
			allocator.markFree(id);
		}
	}

	public void lock(final int... ids) {
		try (CloseableReentrantLock temp = lock.open()) {
			for (int id : ids) {
				allocator.markUsed(id);
			}
		}
	}

	public int getNextId() {
		return allocate();
	}

	public abstract void lockIds();
}