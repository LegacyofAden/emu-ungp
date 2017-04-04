package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/pvp.properties")
public class PvpConfig {
	@ConfigProperty(name = "CanGMDropEquipment", value = "false")
	public static boolean KARMA_DROP_GM;

	@ConfigProperty(name = "AwardPKKillPVPPoint", value = "false")
	@ConfigComments(comment = {
			"Should we award a pvp point for killing a player with karma?"
	})
	public static boolean KARMA_AWARD_PK_KILL;

	@ConfigProperty(name = "MinimumPKRequiredToDrop", value = "6")
	public static int KARMA_PK_LIMIT;

	@ConfigProperty(name = "ListOfPetItems", value = "2375,3500,3501,3502,4422,4423,4424,4425,6648,6649,6650")
	@ConfigComments(comment = {
			"Warning: Make sure the lists do NOT CONTAIN",
			"trailing spaces or spaces between the numbers!",
			"List of pet items we cannot drop."
	})
	public static int[] KARMA_LIST_NONDROPPABLE_PET_ITEMS;

	@ConfigProperty(name = "ListOfNonDroppableItems", value = "57,1147,425,1146,461,10,2368,7,6,2370,2369,6842,6611,6612,6613,6614,6615,6616,6617,6618,6619,6620,6621,7694,8181,5575,7694,9388,9389,9390")
	@ConfigComments(comment = {
			"Lists of items which should NEVER be dropped (note, Adena will",
			"never be dropped) whether on this list or not"
	})
	public static int[] KARMA_LIST_NONDROPPABLE_ITEMS;

	@ConfigProperty(name = "PvPVsNormalTime", value = "120000")
	@ConfigComments(comment = {
			"How much time one stays in PvP mode after hitting an innocent (in ms)"
	})
	public static int PVP_NORMAL_TIME;

	@ConfigProperty(name = "PvPVsPvPTime", value = "60000")
	@ConfigComments(comment = {
			"Length one stays in PvP mode after hitting a purple player (in ms)"
	})
	public static int PVP_PVP_TIME;

	@ConfigProperty(name = "MaxReputation", value = "500")
	@ConfigComments(comment = {
			"Max count of positive reputation"
	})
	public static int MAX_REPUTATION;

	@ConfigProperty(name = "ReputationIncrease", value = "100")
	@ConfigComments(comment = {
			"Reputation increase for kill one PK"
	})
	public static int REPUTATION_INCREASE;
}