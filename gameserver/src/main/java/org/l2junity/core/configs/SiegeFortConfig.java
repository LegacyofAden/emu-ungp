package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/siegefort.properties")
public class SiegeFortConfig {
	@ConfigProperty(name = "SiegeLength", value = "60")
	@ConfigComments(comment = {
			"Length of siege before the count down (in minutes).",
			"Default: 6"
	})
	public static int SIEGE_LENGTH;

	@ConfigProperty(name = "SuspiciousMerchantRespawnDelay", value = "180")
	@ConfigComments(comment = {
			"This defines how long you need to wait until the suspicious merchant will spawn after siege ends (in minutes).",
			"Keep in mind when merchant spawns, the fort can be immediately sieged.",
			"Default: 180"
	})
	public static int SUSPICIOUS_MERCHANT_RESPAWN_DELAY;

	@ConfigProperty(name = "CountDownLength", value = "5")
	@ConfigComments(comment = {
			"This defines how long you have to kill all commanders once you kill the first one (in minutes).",
			"After that time (if all commanders not killed) all commanders and doors get respawned.",
			"Default: 10"
	})
	public static int COUNT_DOWN_LENGTH;

	@ConfigProperty(name = "MaxFlags", value = "1")
	@ConfigComments(comment = {
			"Maximum number of flags per clan.",
			"Default: 1"
	})
	public static int MAX_FLAGS;

	@ConfigProperty(name = "SiegeClanMinLevel", value = "4")
	@ConfigComments(comment = {
			"Minimum level to register.",
			"Default: 4"
	})
	public static int SIEGE_CLAN_MIN_LEVEL;

	@ConfigProperty(name = "AttackerMaxClans", value = "500")
	@ConfigComments(comment = {
			"Max number of clans that can register on attacker side.",
			"Default: 500"
	})
	public static int ATTACKER_MAX_CLANS;

	@ConfigProperty(name = "JustToTerritory", value = "true")
	@ConfigComments(comment = {
			"This option, if enabled, will enable register Fortress Siege to Castle owners just in territory.",
			"Default: true"
	})
	public static boolean JUST_TO_TERRITORY;
}