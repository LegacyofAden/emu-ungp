package org.l2junity.loginserver.manager;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.concurrent.CloseableReentrantLock;
import org.l2junity.commons.model.SessionInfo;
import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.startup.StartupComponent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author ANZO
 * @since 04.04.2017
 */
@Slf4j
@StartupComponent("Service")
public class SessionManager {
	@Getter(lazy = true)
	private static final SessionManager instance = new SessionManager();

	private Map<String, SessionInfo> sessions = new ConcurrentHashMap<>();
	private CloseableReentrantLock lock = new CloseableReentrantLock();

	private SessionManager() {
		ThreadPool.getInstance().scheduleGeneralAtFixedRate(new CleanExpiredSessions(), 30, 30, TimeUnit.SECONDS);
	}

	public SessionInfo openSession(String accountName) {
		SessionInfo sessionInfo = new SessionInfo(accountName);
		try (CloseableReentrantLock tempLock = lock.open()) {
			sessions.put(accountName, sessionInfo);
		}
		return sessionInfo;
	}

	public SessionInfo getSession(String accountName) {
		return sessions.get(accountName);
	}

	private class CleanExpiredSessions implements Runnable {
		@Override
		public void run() {
			long currentMillis = System.currentTimeMillis();
			try (CloseableReentrantLock tempLock = lock.open()) {
				for (Map.Entry<String, SessionInfo> entry : sessions.entrySet()) {
					if (entry.getValue().getSessionExpire() >= currentMillis) {
						sessions.remove(entry.getKey());
					}
				}
			}
		}
	}
}
