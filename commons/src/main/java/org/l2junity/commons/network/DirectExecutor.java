package org.l2junity.commons.network;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;

/**
 * @author ANZO
 * @since 24.03.2017
 */
@Slf4j
public class DirectExecutor implements Executor {
	@Override
	public void execute(Runnable command) {
		try {
			command.run();
		} catch (Throwable th) {
			log.error("Can't execute", th);
		}
	}
}
