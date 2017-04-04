package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/zone/trainingcamp.properties")
public class TrainingCampConfig {
	@ConfigProperty(name = "TrainingCampEnable", value = "false")
	@ConfigComments(comment = {
			"Enable or disable Training Camp"
	})
	public static boolean ENABLE;

	@ConfigProperty(name = "TrainingCampPremiumOnly", value = "true")
	@ConfigComments(comment = {
			"Only Premium account can access training camp"
	})
	public static boolean PREMIUM_ONLY;

	@ConfigProperty(name = "TrainingCampDuration", value = "18000")
	@ConfigComments(comment = {
			"Max duration for Training Camp in seconds. NA : 18000, RU : 36000"
	})
	public static int MAX_DURATION;

	@ConfigProperty(name = "TrainingCampMinLevel", value = "18")
	@ConfigComments(comment = {
			"Min level to enter Training Camp"
	})
	public static int MIN_LEVEL;

	@ConfigProperty(name = "TrainingCampMaxLevel", value = "127")
	@ConfigComments(comment = {
			"Max level to enter Training Camp"
	})
	public static int MAX_LEVEL;
}