package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@ConfigFile(name = "configs/datetime.properties")
public class DateTimeConfig {
	@ConfigProperty(name = "SaveTime", value = "false")
	@ConfigComments(comment = {
			"Whether to store datetime in the database."
	})
	public static boolean SAVE_TIME;

	@ConfigProperty(name = "TimeMultiplier", value = "6")
	@ConfigComments(comment = {
			"How many times in-game time is faster than real time."
	})
	public static int TIME_MULTIPLIER;
}
