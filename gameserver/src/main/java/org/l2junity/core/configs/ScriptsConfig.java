package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.nio.file.Path;

/**
 * @author ANZO
 * @since 27.03.2017
 */
@ConfigFile(name = "configs/scripts.properties")
public class ScriptsConfig {
	@ConfigComments(comment = {"Folder, contains data script."})
	@ConfigProperty(name = "data.root", value = "./data")
	public static Path DATA_ROOT;

	@ConfigComments(comment = {"Enable HTML compression."})
	@ConfigProperty(name = "html.compression.enable", value = "true")
	public static boolean HTML_COMPRESSION_ENABLE;
}
