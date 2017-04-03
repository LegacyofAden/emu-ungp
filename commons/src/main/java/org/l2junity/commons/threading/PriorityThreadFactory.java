/*
 * Copyright (C) 2004-2017 L2J Unity
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
package org.l2junity.commons.threading;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityThreadFactory implements ThreadFactory {
	private final int priority;
	private final String name;
	private final AtomicInteger _threadNumber = new AtomicInteger(1);
	private final ThreadGroup group;

	public PriorityThreadFactory(String name, int priority) {
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