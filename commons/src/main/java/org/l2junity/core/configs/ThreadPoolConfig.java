package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 12.02.2017
 */
@ConfigFile(name = "configs/threadpool.properties")
public class ThreadPoolConfig {
	@ConfigProperty(name = "effects.core.pool.size", value = "10")
	public static int EFFECTS_CORE_POOL_SIZE;

	@ConfigProperty(name = "general.core.pool.size", value = "15")
	public static int GENERAL_CORE_POOL_SIZE;

	@ConfigProperty(name = "ai.core.pool.size", value = "30")
	public static int AI_CORE_POOL_SIZE;
}
