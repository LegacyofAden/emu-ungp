package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/premium.properties")
public class PremiumConfig {
	@ConfigProperty(name = "RatePremiumXp", value = "1.0")
	@ConfigComments(comment = {
			"Experience multiplier",
			"Applies on top of the regular rate"
	})
	public static float RATE_PREMIUM_XP;

	@ConfigProperty(name = "RatePremiumSp", value = "1.0")
	@ConfigComments(comment = {
			"Skill points multiplier",
			"Applies on top of the regular rate"
	})
	public static float RATE_PREMIUM_SP;
}