package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/siegecastle.properties")
public class SiegeCastleConfig {
	@ConfigProperty(name = "SiegeLength", value = "120")
	@ConfigComments(comment = {
			"Length of siege before the count down (in minutes).",
			"Default: 120"
	})
	public static int SIEGE_LENGTH;

	@ConfigProperty(name = "MaxFlags", value = "1")
	@ConfigComments(comment = {
			"Maximum number of flags per clan.",
			"Default: 1"
	})
	public static int MAX_FLAGS;

	@ConfigProperty(name = "SiegeClanMinLevel", value = "5")
	@ConfigComments(comment = {
			"Minimum level to register.",
			"Default: 5"
	})
	public static int SIEGE_CLAN_MIN_LEVEL;

	@ConfigProperty(name = "AttackerMaxClans", value = "500")
	@ConfigComments(comment = {
			"Max number of clans that can register on attacker side.",
			"Default: 500"
	})
	public static int ATTACKER_MAX_CLANS;

	@ConfigProperty(name = "DefenderMaxClans", value = "500")
	@ConfigComments(comment = {
			"Max number of clans that can register on defender side.",
			"Default: 500"
	})
	public static int DEFENDER_MAX_CLANS;

	@ConfigProperty(name = "AttackerRespawn", value = "0")
	@ConfigComments(comment = {
			"Respawn times (in milliseconds).",
			"Default: 0"
	})
	public static int ATTACKER_RESPAWN;

	@ConfigProperty(name = "BloodAllianceReward", value = "1")
	@ConfigComments(comment = {
			"Reward successful siege defense with blood alliance in clan warehouse",
			"Default: 1"
	})
	public static int BLOOD_ALLIANCE_REWARD;
}