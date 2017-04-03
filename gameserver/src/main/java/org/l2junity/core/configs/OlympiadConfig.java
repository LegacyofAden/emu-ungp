package org.l2junity.core.configs;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.config.annotation.ConfigAfterLoad;
import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;
import org.l2junity.gameserver.model.holders.ItemHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@Slf4j
@ConfigFile(name = "configs/olympiad.properties")
public class OlympiadConfig {
	@ConfigProperty(name = "CurrentCycle", value = "1")
	@ConfigComments(comment = {
			"Current cycle of olympiad at server first start."
	})
	public static int CURRENT_CYCLE;

	@ConfigProperty(name = "Period", value = "0")
	@ConfigComments(comment = {
			"Period of olympiad at server first start."
	})
	public static int PERIOD;

	@ConfigProperty(name = "OlympiadEnd", value = "0")
	@ConfigComments(comment = {
			"Olympiad end at server first start."
	})
	public static long OLYMPIAD_END;

	@ConfigProperty(name = "ValidationEnd", value = "0")
	@ConfigComments(comment = {
			"Olympiad validation end at server first start."
	})
	public static long VALIDATION_END;

	@ConfigProperty(name = "NextWeeklyChange", value = "0")
	@ConfigComments(comment = {
			"Olympiad next weekly change at server first start."
	})
	public static long NEXT_WEEKLY_CHANGE;

	@ConfigProperty(name = "AltOlyStartTime", value = "18")
	@ConfigComments(comment = {
			"Olympiad Start Time in Military hours Default 6pm (18)"
	})
	public static int ALT_OLY_START_TIME;

	@ConfigProperty(name = "AltOlyMin", value = "00")
	@ConfigComments(comment = {
			"Olympiad Start Time for Min's, Default 00 so at the start of the hour."
	})
	public static int ALT_OLY_MIN;

	@ConfigProperty(name = "AltOlyMaxBuffs", value = "5")
	@ConfigComments(comment = {
			"Maximum number of buffs."
	})
	public static int ALT_OLY_MAX_BUFFS;

	@ConfigProperty(name = "AltOlyPeriod", value = "1")
	@ConfigComments(comment = {
			"1 = Period will be in months.",
			"2 = Period will be in weeks.",
			"3 = Period will be in days."
	})
	public static int ALT_OLY_PERIOD;

	@ConfigProperty(name = "AltOlyPeriodMultiplier", value = "1")
	@ConfigComments(comment = {
			"Every how many months/weeks/days olympaid period will end."
	})
	public static int ALT_OLY_PERIOD_MULTIPLIER;

	@ConfigProperty(name = "AltOlyCPeriod", value = "21600000")
	@ConfigComments(comment = {
			"Olympiad Competition Period, Default 6 hours.",
			"(If set different, should be increment by 10mins)"
	})
	public static long ALT_OLY_CPERIOD;

	@ConfigProperty(name = "AltOlyBattle", value = "300000")
	@ConfigComments(comment = {
			"Olympiad Battle Period, Default 5 minutes."
	})
	public static long ALT_OLY_BATTLE;

	@ConfigProperty(name = "AltOlyWPeriod", value = "604800000")
	@ConfigComments(comment = {
			"Olympiad Weekly Period, Default 1 week",
			"Used for adding points to nobles"
	})
	public static long ALT_OLY_WPERIOD;

	@ConfigProperty(name = "AltOlyVPeriod", value = "86400000")
	@ConfigComments(comment = {
			"Olympiad Validation Period, Default 24 Hours."
	})
	public static long ALT_OLY_VPERIOD;

	@ConfigProperty(name = "AltOlyStartPoints", value = "10")
	@ConfigComments(comment = {
			"Points for reaching Noblesse for the first time"
	})
	public static int ALT_OLY_START_POINTS;

	@ConfigProperty(name = "AltOlyWeeklyPoints", value = "10")
	@ConfigComments(comment = {
			"Points every week"
	})
	public static int ALT_OLY_WEEKLY_POINTS;

	@ConfigProperty(name = "AltOlyClassedParticipants", value = "11")
	@ConfigComments(comment = {
			"Required number of participants for the class based games"
	})
	public static int ALT_OLY_CLASSED;

	@ConfigProperty(name = "AltOlyNonClassedParticipants", value = "11")
	@ConfigComments(comment = {
			"Required number of participants for the non-class based games"
	})
	public static int ALT_OLY_NONCLASSED;

	@ConfigProperty(name = "AltOlyRegistrationDisplayNumber", value = "100")
	@ConfigComments(comment = {
			"Number used for displaying amount of registered participants, messages 'Fewer than ...' or 'More than ...'.",
			"0 for displaying digits instead of text phrase (old style)."
	})
	public static int ALT_OLY_REG_DISPLAY;

	@ConfigProperty(name = "AltOlyClassedReward", value = "13722,50")
	@ConfigComments(comment = {
			"Reward for the class based games",
			"Format: itemId1,itemNum1;itemId2,itemNum2..."
	})
	public static String _ALT_OLY_CLASSED_REWARD;
	public static List<ItemHolder> ALT_OLY_CLASSED_REWARD = new ArrayList<>();

	@ConfigProperty(name = "AltOlyNonClassedReward", value = "13722,40")
	@ConfigComments(comment = {
			"Reward for the non-class based games",
			"Format: itemId1,itemNum1;itemId2,itemNum2..."
	})
	public static String _ALT_OLY_NONCLASSED_REWARD;
	public static List<ItemHolder> ALT_OLY_NONCLASSED_REWARD = new ArrayList<>();

	@ConfigProperty(name = "AltOlyTeamReward", value = "13722,85")
	@ConfigComments(comment = {
			"Reward for the 3x3 teams games",
			"Format: itemId1,itemNum1;itemId2,itemNum2..."
	})
	public static String _ALT_OLY_TEAM_REWARD;
	public static List<ItemHolder> ALT_OLY_TEAM_REWARD = new ArrayList<>();

	@ConfigProperty(name = "AltOlyCompRewItem", value = "45584")
	@ConfigComments(comment = {
			"ItemId used for exchanging to the points."
	})
	public static int ALT_OLY_COMP_RITEM;

	@ConfigProperty(name = "AltOlyMinMatchesForPoints", value = "10")
	@ConfigComments(comment = {
			"The minimal matches you need to participate to receive point rewards"
	})
	public static int ALT_OLY_MIN_MATCHES;

	@ConfigProperty(name = "AltOlyMarkPerPoint", value = "20")
	@ConfigComments(comment = {
			"Rate to exchange points to reward item."
	})
	public static int ALT_OLY_MARK_PER_POINT;

	@ConfigProperty(name = "AltOlyHeroPoints", value = "30")
	@ConfigComments(comment = {
			"Noblesse points awarded to Heroes."
	})
	public static int ALT_OLY_HERO_POINTS;

	@ConfigProperty(name = "AltOlyRank1Points", value = "60")
	@ConfigComments(comment = {
			"Noblesse points awarded to Rank 1 members."
	})
	public static int ALT_OLY_RANK1_POINTS;

	@ConfigProperty(name = "AltOlyRank2Points", value = "50")
	@ConfigComments(comment = {
			"Noblesse points awarded to Rank 2 members."
	})
	public static int ALT_OLY_RANK2_POINTS;

	@ConfigProperty(name = "AltOlyRank3Points", value = "45")
	@ConfigComments(comment = {
			"Noblesse points awarded to Rank 3 members."
	})
	public static int ALT_OLY_RANK3_POINTS;

	@ConfigProperty(name = "AltOlyRank4Points", value = "40")
	@ConfigComments(comment = {
			"Noblesse points awarded to Rank 4 members."
	})
	public static int ALT_OLY_RANK4_POINTS;

	@ConfigProperty(name = "AltOlyRank5Points", value = "30")
	@ConfigComments(comment = {
			"Noblesse points awarded to Rank 5 members."
	})
	public static int ALT_OLY_RANK5_POINTS;

	@ConfigProperty(name = "AltOlyMaxPoints", value = "10")
	@ConfigComments(comment = {
			"Maximum points that player can gain/lose on a match."
	})
	public static int ALT_OLY_MAX_POINTS;

	@ConfigProperty(name = "AltOlyDividerClassed", value = "5")
	@ConfigComments(comment = {
			"Divider for points in classed and non-classed games"
	})
	public static int ALT_OLY_DIVIDER_CLASSED;

	@ConfigProperty(name = "AltOlyDividerNonClassed", value = "5")
	public static int ALT_OLY_DIVIDER_NON_CLASSED;

	@ConfigProperty(name = "AltOlyMaxWeeklyMatches", value = "70")
	@ConfigComments(comment = {
			"Maximum number of matches a Noblesse character can join per week"
	})
	public static int ALT_OLY_MAX_WEEKLY_MATCHES;

	@ConfigProperty(name = "AltOlyMaxWeeklyMatchesNonClassed", value = "60")
	@ConfigComments(comment = {
			"Maximum number of Class-Irrelevant Individual matches a character can join per week"
	})
	public static int ALT_OLY_MAX_WEEKLY_MATCHES_NON_CLASSED;

	@ConfigProperty(name = "AltOlyMaxWeeklyMatchesClassed", value = "30")
	@ConfigComments(comment = {
			"Maximum number of Class Individual matches a character can join per week"
	})
	public static int ALT_OLY_MAX_WEEKLY_MATCHES_CLASSED;

	@ConfigProperty(name = "AltOlyMaxWeeklyMatchesTeam", value = "10")
	@ConfigComments(comment = {
			"Maximum number of Class-Irrelevant Team matches a character can join per week"
	})
	public static int ALT_OLY_MAX_WEEKLY_MATCHES_TEAM;

	@ConfigProperty(name = "AltOlyLogFights", value = "false")
	@ConfigComments(comment = {
			"Log all Olympiad fights and outcome to olympiad.csv file."
	})
	public static boolean ALT_OLY_LOG_FIGHTS;

	@ConfigProperty(name = "AltOlyShowMonthlyWinners", value = "true")
	@ConfigComments(comment = {
			"Hero tables show last month's winners or current status."
	})
	public static boolean ALT_OLY_SHOW_MONTHLY_WINNERS;

	@ConfigProperty(name = "AltOlyAnnounceGames", value = "true")
	@ConfigComments(comment = {
			"Olympiad Managers announce each start of fight."
	})
	public static boolean ALT_OLY_ANNOUNCE_GAMES;

	@ConfigProperty(name = "AltOlyRestrictedItems", value = "6611,6612,6613,6614,6615,6616,6617,6618,6619,6620,6621,9388,9389,9390,17049,17050,17051,17052,17053,17054,17055,17056,17057,17058,17059,17060,17061,20759,20775,20776,20777,20778,14774")
	@ConfigComments(comment = {
			"Restrict specified items in Olympiad. ItemID's need to be separated with a comma (ex. 1,200,350)",
			"Equipped items will be moved to inventory during port."
	})
	public static List<Integer> LIST_OLY_RESTRICTED_ITEMS = new ArrayList<>();

	@ConfigProperty(name = "AltOlyEnchantLimit", value = "-1")
	@ConfigComments(comment = {
			"Enchant limit for items during Olympiad battles. Disabled = -1."
	})
	public static int ALT_OLY_ENCHANT_LIMIT;

	@ConfigProperty(name = "AltOlyWaitTime", value = "60")
	@ConfigComments(comment = {
			"Time to wait before teleported to arena."
	})
	public static int ALT_OLY_WAIT_TIME;

	@ConfigAfterLoad
	@SuppressWarnings("unused")
	protected void afterLoad() {
		ALT_OLY_CLASSED_REWARD = parseItemsList(_ALT_OLY_CLASSED_REWARD);
		ALT_OLY_NONCLASSED_REWARD = parseItemsList(_ALT_OLY_NONCLASSED_REWARD);
		ALT_OLY_TEAM_REWARD = parseItemsList(_ALT_OLY_TEAM_REWARD);
	}

	/**
	 * Parse a config value from its string representation to a two-dimensional int array.<br>
	 * The format of the value to be parsed should be as follows: "item1Id,item1Amount;item2Id,item2Amount;...itemNId,itemNAmount".
	 *
	 * @param line the value of the parameter to parse
	 * @return the parsed list or {@code null} if nothing was parsed
	 */
	private static List<ItemHolder> parseItemsList(String line) {
		final String[] propertySplit = line.split(";");
		if (propertySplit.length == 0) {
			// nothing to do here
			return null;
		}

		String[] valueSplit;
		final List<ItemHolder> result = new ArrayList<>(propertySplit.length);
		for (String value : propertySplit) {
			valueSplit = value.split(",");
			if (valueSplit.length != 2) {
				log.warn("parseItemsList[Config.load()]: invalid entry -> \"{}\", should be itemId,itemNumber. Skipping to the next entry in the list.", valueSplit[0]);
				continue;
			}

			int itemId = -1;
			try {
				itemId = Integer.parseInt(valueSplit[0]);
			} catch (NumberFormatException e) {
				log.warn("parseItemsList[Config.load()]: invalid itemId -> \"{}\", value must be an integer. Skipping to the next entry in the list.", valueSplit[0]);
				continue;
			}
			int count = -1;
			try {
				count = Integer.parseInt(valueSplit[1]);
			} catch (NumberFormatException e) {
				log.warn("parseItemsList[Config.load()]: invalid item number -> \"{}\", value must be an integer. Skipping to the next entry in the list.", valueSplit[1]);
				continue;
			}
			if ((itemId > 0) && (count > 0)) {
				result.add(new ItemHolder(itemId, count));
			}
		}
		return result;
	}
}