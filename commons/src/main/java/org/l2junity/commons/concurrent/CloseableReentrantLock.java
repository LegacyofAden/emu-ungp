/*
 * Copyright © 2016 BDO-Emu authors. All rights reserved.
 * Viewing, editing, running and distribution of this software strongly prohibited.
 * Author: xTz, Anton Lasevich, Tibald
 */

package org.l2junity.commons.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tsa
 * @since 20.11.2016
 */
public class CloseableReentrantLock extends ReentrantLock implements AutoCloseable {
	public CloseableReentrantLock open() {
		this.lock();
		return this;
	}

	@Override
	public void close() {
		this.unlock();
	}
}
