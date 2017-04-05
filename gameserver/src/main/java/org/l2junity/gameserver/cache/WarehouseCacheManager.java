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
package org.l2junity.gameserver.cache;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.model.actor.instance.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author -Nemesiss-
 */
public class WarehouseCacheManager {
	protected final Map<Player, Long> _cachedWh = new ConcurrentHashMap<>();
	protected final long _cacheTime = GeneralConfig.WAREHOUSE_CACHE_TIME * 60000L;

	protected WarehouseCacheManager() {
		ThreadPool.getInstance().scheduleGeneralAtFixedRate(new CacheScheduler(), 120000, 60000, TimeUnit.MILLISECONDS);
	}

	public void addCacheTask(Player pc) {
		_cachedWh.put(pc, System.currentTimeMillis());
	}

	public void remCacheTask(Player pc) {
		_cachedWh.remove(pc);
	}

	public class CacheScheduler implements Runnable {
		@Override
		public void run() {
			long cTime = System.currentTimeMillis();
			for (Player pc : _cachedWh.keySet()) {
				if ((cTime - _cachedWh.get(pc)) > _cacheTime) {
					pc.clearWarehouse();
					_cachedWh.remove(pc);
				}
			}
		}
	}

	public static WarehouseCacheManager getInstance() {
		return SingletonHolder._instance;
	}

	private static class SingletonHolder {
		protected static final WarehouseCacheManager _instance = new WarehouseCacheManager();
	}
}
