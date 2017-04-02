package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@ConfigFile(name = "configs/feature.properties")
public class FeatureConfig {
	@ConfigProperty(name = "CastleTeleportFunctionFeeRatio", value = "604800000")
	@ConfigComments(comment = {
			"Teleport Function price",
			"Price = 7 days"
	})
	public static long CS_TELE_FEE_RATIO;

	@ConfigProperty(name = "CastleTeleportFunctionFeeLvl1", value = "1000")
	public static int CS_TELE1_FEE;

	@ConfigProperty(name = "CastleTeleportFunctionFeeLvl2", value = "10000")
	public static int CS_TELE2_FEE;

	@ConfigProperty(name = "CastleMpRegenerationFunctionFeeRatio", value = "604800000")
	@ConfigComments(comment = {
			"MP Regeneration price",
			"Price = 7 day"
	})
	public static long CS_MPREG_FEE_RATIO;

	@ConfigProperty(name = "CastleMpRegenerationFeeLvl1", value = "45000")
	public static int CS_MPREG1_FEE;

	@ConfigProperty(name = "CastleMpRegenerationFeeLvl2", value = "65000")
	public static int CS_MPREG2_FEE;

	@ConfigProperty(name = "CastleHpRegenerationFunctionFeeRatio", value = "604800000")
	@ConfigComments(comment = {
			"Hp Regeneration price",
			"Price = 7 day"
	})
	public static long CS_HPREG_FEE_RATIO;

	@ConfigProperty(name = "CastleHpRegenerationFeeLvl1", value = "12000")
	public static int CS_HPREG1_FEE;

	@ConfigProperty(name = "CastleHpRegenerationFeeLvl2", value = "20000")
	public static int CS_HPREG2_FEE;

	@ConfigProperty(name = "CastleExpRegenerationFunctionFeeRatio", value = "604800000")
	@ConfigComments(comment = {
			"Exp Regeneration price",
			"Price = 7 day"
	})
	public static long CS_EXPREG_FEE_RATIO;

	@ConfigProperty(name = "CastleExpRegenerationFeeLvl1", value = "63000")
	public static int CS_EXPREG1_FEE;

	@ConfigProperty(name = "CastleExpRegenerationFeeLvl2", value = "70000")
	public static int CS_EXPREG2_FEE;

	@ConfigProperty(name = "CastleSupportFunctionFeeRatio", value = "604800000")
	@ConfigComments(comment = {
			"Support magic buff price",
			"Price = 7 day"
	})
	public static long CS_SUPPORT_FEE_RATIO;

	@ConfigProperty(name = "CastleSupportFeeLvl1", value = "49000")
	public static int CS_SUPPORT1_FEE;

	@ConfigProperty(name = "CastleSupportFeeLvl2", value = "120000")
	public static int CS_SUPPORT2_FEE;

	@ConfigProperty(name = "SiegeHourList", value = "16,20")
	@ConfigComments(comment = {
			"Siege Time rules",
			"Which hours can change Castle Lords"
	})
	public static List<Integer> SIEGE_HOUR_LIST = new ArrayList<>();

	@ConfigProperty(name = "BuyTaxForNeutralSide", value = "15")
	@ConfigComments(comment = {
			"Taxes for castles",
			"Buy tax in percent when is castle owned by npc's.",
			"Defualt: 15"
	})
	public static int CASTLE_BUY_TAX_NEUTRAL;

	@ConfigProperty(name = "BuyTaxForLightSide", value = "0")
	@ConfigComments(comment = {
			"Buy tax in percent when is castle owned by player's and castle is on light side.",
			"Defualt: 0"
	})
	public static int CASTLE_BUY_TAX_LIGHT;

	@ConfigProperty(name = "BuyTaxForDarkSide", value = "30")
	@ConfigComments(comment = {
			"Buy tax in percent when is castle owned by player's and castle is on dark side.",
			"Defualt: 30"
	})
	public static int CASTLE_BUY_TAX_DARK;

	@ConfigProperty(name = "SellTaxForNeutralSide", value = "15")
	@ConfigComments(comment = {
			"Sell tax in percent when is castle owned by npc's.",
			"Defualt: 15"
	})
	public static int CASTLE_SELL_TAX_NEUTRAL;

	@ConfigProperty(name = "SellTaxForLightSide", value = "0")
	@ConfigComments(comment = {
			"Sell tax in percent when is castle owned by player's and castle is on light side.",
			"Defualt: 0"
	})
	public static int CASTLE_SELL_TAX_LIGHT;

	@ConfigProperty(name = "SellTaxForDarkSide", value = "30")
	@ConfigComments(comment = {
			"Sell tax in percent when is castle owned by player's and castle is on dark side.",
			"Defualt: 30"
	})
	public static int CASTLE_SELL_TAX_DARK;

	@ConfigProperty(name = "OuterDoorUpgradePriceLvl2", value = "3000000")
	@ConfigComments(comment = {
			"Outer Door upgrade price"
	})
	public static int OUTER_DOOR_UPGRADE_PRICE2;

	@ConfigProperty(name = "OuterDoorUpgradePriceLvl3", value = "4000000")
	public static int OUTER_DOOR_UPGRADE_PRICE3;

	@ConfigProperty(name = "OuterDoorUpgradePriceLvl5", value = "5000000")
	public static int OUTER_DOOR_UPGRADE_PRICE5;

	@ConfigProperty(name = "InnerDoorUpgradePriceLvl2", value = "750000")
	@ConfigComments(comment = {
			"Inner Door upgrade price"
	})
	public static int INNER_DOOR_UPGRADE_PRICE2;

	@ConfigProperty(name = "InnerDoorUpgradePriceLvl3", value = "900000")
	public static int INNER_DOOR_UPGRADE_PRICE3;

	@ConfigProperty(name = "InnerDoorUpgradePriceLvl5", value = "1000000")
	public static int INNER_DOOR_UPGRADE_PRICE5;

	@ConfigProperty(name = "WallUpgradePriceLvl2", value = "1600000")
	@ConfigComments(comment = {
			"Wall upgrade price"
	})
	public static int WALL_UPGRADE_PRICE2;

	@ConfigProperty(name = "WallUpgradePriceLvl3", value = "1800000")
	public static int WALL_UPGRADE_PRICE3;

	@ConfigProperty(name = "WallUpgradePriceLvl5", value = "2000000")
	public static int WALL_UPGRADE_PRICE5;

	@ConfigProperty(name = "TrapUpgradePriceLvl1", value = "3000000")
	@ConfigComments(comment = {
			"Trap upgrade price"
	})
	public static int TRAP_UPGRADE_PRICE1;

	@ConfigProperty(name = "TrapUpgradePriceLvl2", value = "4000000")
	public static int TRAP_UPGRADE_PRICE2;

	@ConfigProperty(name = "TrapUpgradePriceLvl3", value = "5000000")
	public static int TRAP_UPGRADE_PRICE3;

	@ConfigProperty(name = "TrapUpgradePriceLvl4", value = "6000000")
	public static int TRAP_UPGRADE_PRICE4;

	@ConfigProperty(name = "FortressTeleportFunctionFeeRatio", value = "604800000")
	@ConfigComments(comment = {
			"Teleport Function price",
			"Price = 7 days"
	})
	public static long FS_TELE_FEE_RATIO;

	@ConfigProperty(name = "FortressTeleportFunctionFeeLvl1", value = "1000")
	public static int FS_TELE1_FEE;

	@ConfigProperty(name = "FortressTeleportFunctionFeeLvl2", value = "10000")
	public static int FS_TELE2_FEE;

	@ConfigProperty(name = "FortressMpRegenerationFunctionFeeRatio", value = "86400000")
	@ConfigComments(comment = {
			"MP Regeneration price",
			"Price = 1 day"
	})
	public static long FS_MPREG_FEE_RATIO;

	@ConfigProperty(name = "FortressMpRegenerationFeeLvl1", value = "6500")
	public static int FS_MPREG1_FEE;

	@ConfigProperty(name = "FortressMpRegenerationFeeLvl2", value = "9300")
	public static int FS_MPREG2_FEE;

	@ConfigProperty(name = "FortressHpRegenerationFunctionFeeRatio", value = "86400000")
	@ConfigComments(comment = {
			"Hp Regeneration price",
			"Price = 1 day"
	})
	public static long FS_HPREG_FEE_RATIO;

	@ConfigProperty(name = "FortressHpRegenerationFeeLvl1", value = "2000")
	public static int FS_HPREG1_FEE;

	@ConfigProperty(name = "FortressHpRegenerationFeeLvl2", value = "3500")
	public static int FS_HPREG2_FEE;

	@ConfigProperty(name = "FortressExpRegenerationFunctionFeeRatio", value = "86400000")
	@ConfigComments(comment = {
			"Exp Regeneration price",
			"Price = 1 day"
	})
	public static long FS_EXPREG_FEE_RATIO;

	@ConfigProperty(name = "FortressExpRegenerationFeeLvl1", value = "9000")
	public static int FS_EXPREG1_FEE;

	@ConfigProperty(name = "FortressExpRegenerationFeeLvl2", value = "10000")
	public static int FS_EXPREG2_FEE;

	@ConfigProperty(name = "FortressSupportFunctionFeeRatio", value = "86400000")
	@ConfigComments(comment = {
			"Support magic buff price",
			"Price = 1 day"
	})
	public static long FS_SUPPORT_FEE_RATIO;

	@ConfigProperty(name = "FortressSupportFeeLvl1", value = "7000")
	public static int FS_SUPPORT1_FEE;

	@ConfigProperty(name = "FortressSupportFeeLvl2", value = "17000")
	public static int FS_SUPPORT2_FEE;

	@ConfigProperty(name = "FortressBloodOathCount", value = "1")
	@ConfigComments(comment = {
			"The number of Blood Oath which given to the Fort owner clan when Fort Updater runs"
	})
	public static int FS_BLOOD_OATH_COUNT;

	@ConfigProperty(name = "FortressPeriodicUpdateFrequency", value = "360")
	@ConfigComments(comment = {
			"This is the time frequently when Fort owner gets Blood Oath, supply level raised and Fort fee is payed",
			"Default 360 mins"
	})
	public static int FS_UPDATE_FRQ;

	@ConfigProperty(name = "FortressMaxSupplyLevel", value = "6")
	@ConfigComments(comment = {
			"The maximum Fort supply level",
			"Max lvl what you can define here is 21!"
	})
	public static int FS_MAX_SUPPLY_LEVEL;

	@ConfigProperty(name = "FortressFeeForCastle", value = "25000")
	@ConfigComments(comment = {
			"Fort fee which payed to the Castle"
	})
	public static int FS_FEE_FOR_CASTLE;

	@ConfigProperty(name = "FortressMaximumOwnTime", value = "168")
	@ConfigComments(comment = {
			"The maximum time while a clan can own a fortress",
			"Deafault: 168 hours"
	})
	public static int FS_MAX_OWN_TIME;

	@ConfigProperty(name = "TakeFortPoints", value = "200")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"Clan Reputation Points",
			"---------------------------------------------------------------------------",
			"Reputation score gained by taking Fortress."
	})
	public static int TAKE_FORT_POINTS;

	@ConfigProperty(name = "LooseFortPoints", value = "0")
	@ConfigComments(comment = {
			"Reputation score reduced by loosing Fortress in battle."
	})
	public static int LOOSE_FORT_POINTS;

	@ConfigProperty(name = "TakeCastlePoints", value = "1500")
	@ConfigComments(comment = {
			"Reputation score gained by taking Castle."
	})
	public static int TAKE_CASTLE_POINTS;

	@ConfigProperty(name = "LooseCastlePoints", value = "3000")
	@ConfigComments(comment = {
			"Reputation score reduced by loosing Castle in battle."
	})
	public static int LOOSE_CASTLE_POINTS;

	@ConfigProperty(name = "CastleDefendedPoints", value = "750")
	@ConfigComments(comment = {
			"Reputation score gained by defended Castle."
	})
	public static int CASTLE_DEFENDED_POINTS;

	@ConfigProperty(name = "FestivalOfDarknessWin", value = "200")
	@ConfigComments(comment = {
			"Reputation score gained per clan members of festival winning party."
	})
	public static int FESTIVAL_WIN_POINTS;

	@ConfigProperty(name = "HeroPoints", value = "1000")
	@ConfigComments(comment = {
			"Reputation score gained for per hero clan members."
	})
	public static int HERO_POINTS;

	@ConfigProperty(name = "CreateRoyalGuardCost", value = "5000")
	@ConfigComments(comment = {
			"Reputation score reduced by creating Royal Guard."
	})
	public static int ROYAL_GUARD_COST;

	@ConfigProperty(name = "CreateKnightUnitCost", value = "10000")
	@ConfigComments(comment = {
			"Reputation score reduced by creating Knight Unit."
	})
	public static int KNIGHT_UNIT_COST;

	@ConfigProperty(name = "ReinforceKnightUnitCost", value = "5000")
	@ConfigComments(comment = {
			"Reputation score reduced by reinforcing Knight Unit (if clan level is 9 or more)."
	})
	public static int KNIGHT_REINFORCE_COST;

	@ConfigProperty(name = "KillBallistaPoints", value = "500")
	@ConfigComments(comment = {
			"Reputation score gained per killed ballista."
	})
	public static int BALLISTA_POINTS;

	@ConfigProperty(name = "BloodAlliancePoints", value = "500")
	@ConfigComments(comment = {
			"Reputation score gained for one Blood Alliance."
	})
	public static int BLOODALLIANCE_POINTS;

	@ConfigProperty(name = "BloodOathPoints", value = "200")
	@ConfigComments(comment = {
			"Reputation score gained for 10 Blood Oaths."
	})
	public static int BLOODOATH_POINTS;

	@ConfigProperty(name = "KnightsEpaulettePoints", value = "20")
	@ConfigComments(comment = {
			"Reputation score gained for 100 Knight's Epaulettes."
	})
	public static int KNIGHTSEPAULETTE_POINTS;

	@ConfigProperty(name = "ReputationScorePerKill", value = "1")
	@ConfigComments(comment = {
			"Reputation score gained/reduced per kill during a clan war or siege war."
	})
	public static int REPUTATION_SCORE_PER_KILL;

	@ConfigProperty(name = "CompleteAcademyMinPoints", value = "190")
	@ConfigComments(comment = {
			"Minimum Reputation score gained after completing 2nd class transfer under Academy."
	})
	public static int JOIN_ACADEMY_MIN_REP_SCORE;

	@ConfigProperty(name = "CompleteAcademyMaxPoints", value = "650")
	@ConfigComments(comment = {
			"Maximum Reputation score gained after completing 2nd class transfer under Academy."
	})
	public static int JOIN_ACADEMY_MAX_REP_SCORE;

	@ConfigProperty(name = "ClanLevel6Cost", value = "5000")
	@ConfigComments(comment = {
			"Reputation score reduced by increasing clan level."
	})
	public static int CLAN_LEVEL_6_COST;

	@ConfigProperty(name = "ClanLevel7Cost", value = "10000")
	public static int CLAN_LEVEL_7_COST;

	@ConfigProperty(name = "ClanLevel8Cost", value = "20000")
	public static int CLAN_LEVEL_8_COST;

	@ConfigProperty(name = "ClanLevel9Cost", value = "40000")
	public static int CLAN_LEVEL_9_COST;

	@ConfigProperty(name = "ClanLevel10Cost", value = "40000")
	public static int CLAN_LEVEL_10_COST;

	@ConfigProperty(name = "ClanLevel11Cost", value = "75000")
	public static int CLAN_LEVEL_11_COST;

	@ConfigProperty(name = "ClanLevel6Requirement", value = "30")
	@ConfigComments(comment = {
			"Number of clan members needed to increase clan level."
	})
	public static int CLAN_LEVEL_6_REQUIREMENT;

	@ConfigProperty(name = "ClanLevel7Requirement", value = "50")
	public static int CLAN_LEVEL_7_REQUIREMENT;

	@ConfigProperty(name = "ClanLevel8Requirement", value = "80")
	public static int CLAN_LEVEL_8_REQUIREMENT;

	@ConfigProperty(name = "ClanLevel9Requirement", value = "120")
	public static int CLAN_LEVEL_9_REQUIREMENT;

	@ConfigProperty(name = "ClanLevel10Requirement", value = "140")
	public static int CLAN_LEVEL_10_REQUIREMENT;

	@ConfigProperty(name = "ClanLevel11Requirement", value = "170")
	public static int CLAN_LEVEL_11_REQUIREMENT;

	@ConfigProperty(name = "AllowRideWyvernAlways", value = "false")
	@ConfigComments(comment = {
			"Allow riding wyvern ignoring 7 Signs status",
			"This will allow Castle Lords to ride wyvern even when Dusk has won Seal of Strife"
	})
	public static boolean ALLOW_WYVERN_ALWAYS;

	@ConfigProperty(name = "AllowRideWyvernDuringSiege", value = "true")
	@ConfigComments(comment = {
			"Allow riding wyvern during Castle/Fort Siege"
	})
	public static boolean ALLOW_WYVERN_DURING_SIEGE;
}
