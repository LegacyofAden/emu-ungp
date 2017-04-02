package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/zone/graciaseeds.properties")
public class GraciaSeedsConfig {
	@ConfigProperty(name = "TiatKillCountForNextState", value = "10")
	@ConfigComments(comment = {
			"Count of Kills which needed for Stage 2"
	})
	public static int SOD_TIAT_KILL_COUNT;

	@ConfigProperty(name = "Stage2Length", value = "720")
	@ConfigComments(comment = {
			"Length of Stage 2 before the Defense state starts (in minutes)."
	})
	public static long SOD_STAGE_2_LENGTH;
}
