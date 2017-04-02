package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/grandboss.properties")
public class GrandBossConfig {
	@ConfigProperty(name = "AntharasWaitTime", value = "20")
	@ConfigComments(comment = {
			"Delay of appearance time of Antharas. Value is minute. Range 3-60"
	})
	public static int ANTHARAS_WAIT_TIME;

	@ConfigProperty(name = "IntervalOfAntharasSpawn", value = "264")
	@ConfigComments(comment = {
			"Interval time of Antharas. Value is hour. Range 1-480"
	})
	public static int ANTHARAS_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfAntharasSpawn", value = "72")
	@ConfigComments(comment = {
			"Random interval. Range 1-192"
	})
	public static int ANTHARAS_SPAWN_RANDOM;

	@ConfigProperty(name = "ValakasWaitTime", value = "30")
	@ConfigComments(comment = {
			"Delay of appearance time of Valakas. Value is minute. Range 3-60"
	})
	public static int VALAKAS_WAIT_TIME;

	@ConfigProperty(name = "IntervalOfValakasSpawn", value = "264")
	@ConfigComments(comment = {
			"Interval time of Valakas. Value is hour. Range 1-480"
	})
	public static int VALAKAS_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfValakasSpawn", value = "72")
	@ConfigComments(comment = {
			"Random interval. Range 1-192"
	})
	public static int VALAKAS_SPAWN_RANDOM;

	@ConfigProperty(name = "IntervalOfBaiumSpawn", value = "168")
	@ConfigComments(comment = {
			"Interval time of Baium. Value is hour. Range 1-480"
	})
	public static int BAIUM_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfBaiumSpawn", value = "48")
	@ConfigComments(comment = {
			"Random interval. Range 1-192"
	})
	public static int BAIUM_SPAWN_RANDOM;

	@ConfigProperty(name = "IntervalOfCoreSpawn", value = "60")
	@ConfigComments(comment = {
			"Interval time of Core. Value is hour. Range 1-480"
	})
	public static int CORE_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfCoreSpawn", value = "24")
	@ConfigComments(comment = {
			"Random interval. Range 1-192"
	})
	public static int CORE_SPAWN_RANDOM;

	@ConfigProperty(name = "IntervalOfOrfenSpawn", value = "48")
	@ConfigComments(comment = {
			"Interval time of Orfen. Value is hour. Range 1-480"
	})
	public static int ORFEN_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfOrfenSpawn", value = "20")
	@ConfigComments(comment = {
			"Random interval. Range 1-192"
	})
	public static int ORFEN_SPAWN_RANDOM;

	@ConfigProperty(name = "IntervalOfQueenAntSpawn", value = "36")
	@ConfigComments(comment = {
			"Interval time of QueenAnt. Value is hour. Range 1-480"
	})
	public static int QUEEN_ANT_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfQueenAntSpawn", value = "17")
	@ConfigComments(comment = {
			"Random interval. Range 1-192"
	})
	public static int QUEEN_ANT_SPAWN_RANDOM;

	@ConfigProperty(name = "BelethMinPlayers", value = "36")
	@ConfigComments(comment = {
			"Minimal count of players for enter to Beleth. Retail: 36"
	})
	public static int BELETH_MIN_PLAYERS;

	@ConfigProperty(name = "IntervalOfBelethSpawn", value = "192")
	@ConfigComments(comment = {
			"Interval time of Beleth. Value is hour. Range 1-480. Retail: 192"
	})
	public static int BELETH_SPAWN_INTERVAL;

	@ConfigProperty(name = "RandomOfBelethSpawn", value = "148")
	@ConfigComments(comment = {
			"Random interval. Range 1-192. Retail: 148"
	})
	public static int BELETH_SPAWN_RANDOM;
}
