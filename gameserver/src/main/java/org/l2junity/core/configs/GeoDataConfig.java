package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.nio.file.Path;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/geodata.properties")
public class GeoDataConfig {
	@ConfigProperty(name = "PathFinding", value = "0")
	@ConfigComments(comment = {
			"Pathfinding options:",
			"0 = Disabled",
			"1 = Enabled using path node files",
			"2 = Enabled using geodata cells at runtime"
	})
	public static int PATHFINDING;

	@ConfigProperty(name = "PathnodeDirectory", value = "./data/pathnode")
	@ConfigComments(comment = {
			"Pathnode directory"
	})
	public static Path PATHNODE_PATH;

	@ConfigProperty(name = "PathFindBuffers", value = "100x6;128x6;192x6;256x4;320x4;384x4;500x2")
	@ConfigComments(comment = {
			"Pathfinding array buffers configuration"
	})
	public static String PATHFIND_BUFFERS;

	@ConfigProperty(name = "LowWeight", value = "0.5")
	@ConfigComments(comment = {
			"Weight for nodes without obstacles far from walls"
	})
	public static float LOW_WEIGHT;

	@ConfigProperty(name = "MediumWeight", value = "2")
	@ConfigComments(comment = {
			"Weight for nodes near walls"
	})
	public static float MEDIUM_WEIGHT;

	@ConfigProperty(name = "HighWeight", value = "3")
	@ConfigComments(comment = {
			"Weight for nodes with obstacles"
	})
	public static float HIGH_WEIGHT;

	@ConfigProperty(name = "AdvancedDiagonalStrategy", value = "true")
	@ConfigComments(comment = {
			"Angle paths will be more 'smart', but in cost of higher CPU utilization"
	})
	public static boolean ADVANCED_DIAGONAL_STRATEGY;

	@ConfigProperty(name = "DiagonalWeight", value = "0.707")
	@ConfigComments(comment = {
			"Weight for diagonal movement. Used only with AdvancedDiagonalStrategy = True"
	})
	public static float DIAGONAL_WEIGHT;

	@ConfigProperty(name = "MaxPostfilterPasses", value = "3")
	@ConfigComments(comment = {
			"Maximum number of LOS postfilter passes, 0 will disable postfilter."
	})
	public static int MAX_POSTFILTER_PASSES;

	@ConfigProperty(name = "DebugPath", value = "false")
	@ConfigComments(comment = {
			"Path debug function.",
			"Nodes known to pathfinder will be displayed as adena, constructed path as antidots.",
			"Number of the items show node cost * 10",
			"Potions display path after first stage filter",
			"Red potions - actual waypoints. Green potions - nodes removed by LOS postfilter",
			"This function FOR DEBUG PURPOSES ONLY, never use it on the live server !"
	})
	public static boolean DEBUG_PATH;

	@ConfigProperty(name = "ForceGeoData", value = "true")
	@ConfigComments(comment = {
			"True = Loads GeoData buffer's content into physical memory.",
			"False = Does not necessarily imply that the GeoData buffer's content is not resident in physical memory."
	})
	public static boolean FORCE_GEODATA;

	@ConfigProperty(name = "CoordSynchronize", value = "-1")
	@ConfigComments(comment = {
			"This setting controls Client <--> Server Player coordinates synchronization:",
			"-1 - Will synchronize only Z from Client --> Server. Default when no geodata.",
			"1 - Synchronization Client --> Server only. Using this option (without geodata) makes it more difficult for players to bypass obstacles.",
			"2 - Intended for geodata (at least with cell-level pathfinding, otherwise can you try -1).",
			"Server sends validation packet if client goes too far from server calculated coordinates."
	})
	public static int COORD_SYNCHRONIZE;

	@ConfigProperty(name = "GeoDataPath", value = "./data/geodata")
	@ConfigComments(comment = {
			"Geodata files folder"
	})
	public static Path GEODATA_PATH;

	@ConfigProperty(name = "TryLoadUnspecifiedRegions", value = "true")
	@ConfigComments(comment = {
			"True: Try to load regions not specified below(won't disturb server startup when file does not exist)",
			"False: Don't load any regions other than the ones specified with True below"
	})
	public static boolean TRY_LOAD_UNSPECIFIED_REGIONS;
}
