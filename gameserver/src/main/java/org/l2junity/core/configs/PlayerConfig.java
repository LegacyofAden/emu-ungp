package org.l2junity.core.configs;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.config.annotation.ConfigAfterLoad;
import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ANZO
 * @since 02.04.2017
 */

@Slf4j
@ConfigFile(name = "configs/player.properties")
public class PlayerConfig {
	@ConfigProperty(name = "Delevel", value = "true", comment =
			{
					"This option, if enabled, will force a character to de-level if the characters' experience is below their level after losing experience on death. If this is set to False, the character will not de-level even if their Experience is below their level after death."
			})
	public static boolean ALT_GAME_DELEVEL;

	@ConfigProperty(name = "DecreaseSkillOnDelevel", value = "true", comment =
			{
					"This option enable check for all player skills for skill level.",
					"If player level is lower than skill learn level - 9, skill level is decreased to next possible level.",
					"If there is no possible level, skill is removed from player."
			})
	public static boolean DECREASE_SKILL_LEVEL;

	@ConfigProperty(name = "AltWeightLimit", value = "1", comment =
			{
					"Weight limit multiplier. Example: Setting this to 5 will give players 5x the normal weight limit."
			})
	public static double ALT_WEIGHT_LIMIT;

	@ConfigProperty(name = "RunSpeedBoost", value = "0", comment =
			{
					"Run speed modifier. Example: Setting this to 5 will give players +5 to their running speed."
			})
	public static int RUN_SPD_BOOST;

	@ConfigProperty(name = "DeathPenaltyChance", value = "20", comment =
			{
					"Chance of receiving the Death Penalty debuff when killed by a mob."
			})
	public static int DEATH_PENALTY_CHANCE;

	@ConfigProperty(name = "RespawnRestoreCP", value = "0", comment =
			{
					"Percent of HP, MP, and CP which is restored on character revival.",
					"Use 0 to disable restore"
			})
	public static double RESPAWN_RESTORE_CP;

	@ConfigProperty(name = "RespawnRestoreHP", value = "65")
	public static double RESPAWN_RESTORE_HP;

	@ConfigProperty(name = "RespawnRestoreMP", value = "0")
	public static double RESPAWN_RESTORE_MP;

	@ConfigProperty(name = "AltGameTiredness", value = "false", comment =
			{
					"Decrease CP by 10 every normal hit a player do"
			})
	public static boolean ALT_GAME_TIREDNESS;

	@ConfigProperty(name = "EnableModifySkillDuration", value = "false", comment =
			{
					"When this is enabled it will read the 'SkillDurationList' option.",
					"This will basically overlook the 'time = x' in the skill XMLs so that you do not need to modify the L2J Datapack XMLs to increase skill duration."
			})
	public static boolean ENABLE_MODIFY_SKILL_DURATION;

	@ConfigProperty(name = "SkillDurationList", value = "", comment =
			{
					"Skill duration list",
					"Format: skillid,newtime;skillid2,newtime2...",
					"Example: ",
					"	This enable 1h(3600) duration for songs, the \\ indicates new line,",
					"	and is only set for formating purposes.",
					"	SkillDurationList = 264:3600,265:3600,266:3600,267:3600,268:3600,\\",
					"	269:3600,270:3600,304:3600,305:1200,306:3600,308:3600,349:3600,\\",
					"	363:3600,364:3600",
			})
	public static Map<Integer, Integer> SKILL_DURATION_LIST = new HashMap<>();

	@ConfigProperty(name = "EnableModifySkillReuse", value = "false", comment =
			{
					"When this is enabled it will read the 'SkillReuseList' option."
			})
	public static boolean ENABLE_MODIFY_SKILL_REUSE;

	@ConfigProperty(name = "SkillReuseList", value = "", comment =
			{
					"Format: skillid:newDelayTime,skillid:newDelayTime2 (See skillDuration for examples)"
			})
	public static Map<Integer, Integer> SKILL_REUSE_LIST = new HashMap<>();

	@ConfigProperty(name = "AutoLearnSkills", value = "false", comment =
			{
					"If it's true all class skills will be delivered upon level up and login."
			})
	public static boolean AUTO_LEARN_SKILLS;

	@ConfigProperty(name = "AutoLearnForgottenScrollSkills", value = "false", comment =
			{
					"If it's true skills from forgotten scrolls will be delivered upon level up and login, require AutoLearnSkills."
			})
	public static boolean AUTO_LEARN_FS_SKILLS;

	@ConfigProperty(name = "AutoLootHerbs", value = "false")
	public static boolean AUTO_LOOT_HERBS;

	@ConfigProperty(name = "MaxBuffAmount", value = "20", comment =
			{
					"Maximum number of buffs and songs/dances.",
					"Remember that Divine Inspiration will give players 4 additional buff slots on top of the number specified in 'maxbuffamount'."
			})
	public static byte BUFFS_MAX_AMOUNT;

	@ConfigProperty(name = "MaxTriggeredBuffAmount", value = "12")
	public static byte TRIGGERED_BUFFS_MAX_AMOUNT;

	@ConfigProperty(name = "MaxDanceAmount", value = "12")
	public static byte DANCES_MAX_AMOUNT;

	@ConfigProperty(name = "DanceCancelBuff", value = "false", comment =
			{
					"Allow players to cancel dances/songs via Alt+click on buff icon"
			})
	public static boolean DANCE_CANCEL_BUFF;

	@ConfigProperty(name = "DanceConsumeAdditionalMP", value = "true", comment =
			{
					"This option enables/disables additional MP consume for dances and songs."
			})
	public static boolean DANCE_CONSUME_ADDITIONAL_MP;

	@ConfigProperty(name = "AltStoreDances", value = "false", comment =
			{
					"Allow players to have all dances/songs stored when logout."
			})
	public static boolean ALT_STORE_DANCES;

	@ConfigProperty(name = "AutoLearnDivineInspiration", value = "false", comment =
			{
					"This option allows a player to automatically learn Divine Inspiration.",
					"This is not included in AutoLearnSkills above."
			})
	public static boolean AUTO_LEARN_DIVINE_INSPIRATION;

	@ConfigProperty(name = "AltGameCancelByHitBow", value = "bow", comment =
			{
					"This is to allow a character to be canceled during bow use, skill use, or both.",
					"Available Options: bow, cast, all"
			})
	public static boolean ALT_GAME_CANCEL_BOW;

	@ConfigProperty(name = "AltGameCancelByHitCast", value = "cast", comment =
			{
					"This is to allow a character to be canceled during bow use, skill use, or both.",
					"Available Options: bow, cast, all"
			})
	public static boolean ALT_GAME_CANCEL_CAST;

	@ConfigProperty(name = "MagicFailures", value = "true", comment =
			{
					"This option, if enabled, will allow magic to fail, and if disabled magic damage will always succeed with a 100% chance."
			})
	public static boolean ALT_GAME_MAGICFAILURES;

	@ConfigProperty(name = "PlayerFakeDeathUpProtection", value = "0", comment =
			{
					"Protection from aggressive mobs after getting up from fake death.",
					"The value is specified in seconds."
			})
	public static int PLAYER_FAKEDEATH_UP_PROTECTION;

	@ConfigProperty(name = "StoreSkillCooltime", value = "true", comment =
			{
					"This option is to enable or disable the storage of buffs/debuffs among other effects."
			})
	public static boolean STORE_SKILL_COOLTIME;

	@ConfigProperty(name = "SubclassStoreSkillCooltime", value = "false", comment =
			{
					"This option is to enable or disable the storage of buffs/debuffs among other effects during",
					"a subclass change"
			})
	public static boolean SUBCLASS_STORE_SKILL_COOLTIME;

	@ConfigProperty(name = "SummonStoreSkillCooltime", value = "true", comment =
			{
					"This option is to enable or disable the storage of buffs/debuffs among other effects on pets/invocations"
			})
	public static boolean SUMMON_STORE_SKILL_COOLTIME;

	@ConfigProperty(name = "EffectTickRatio", value = "666", comment =
			{
					"This is the value ticks are multiplied with to result in interval per tick in milliseconds.",
					"Note: Editing this will not affect how much the over-time effects heals since heal scales with that value too."
			})
	public static long EFFECT_TICK_RATIO;

	@ConfigProperty(name = "LifeCrystalNeeded", value = "true")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"Class, Sub-class and skill learning options",
			"---------------------------------------------------------------------------",
			"Require life crystal needed to learn clan skills."
	})
	public static boolean LIFE_CRYSTAL_NEEDED;

	@ConfigProperty(name = "EnchantSkillSpBookNeeded", value = "true")
	@ConfigComments(comment = {
			"Require book needed to enchant skills."
	})
	public static boolean ES_SP_BOOK_NEEDED;

	@ConfigProperty(name = "DivineInspirationSpBookNeeded", value = "true")
	@ConfigComments(comment = {
			"Require spell book needed to learn Divine Inspiration."
	})
	public static boolean DIVINE_SP_BOOK_NEEDED;

	@ConfigProperty(name = "AltSubClassWithoutQuests", value = "false", comment =
			{
					"Allow player to sub-class without checking for unique quest items."
			})
	public static boolean ALT_GAME_SUBCLASS_WITHOUT_QUESTS;

	@ConfigProperty(name = "AltTransformationWithoutQuest", value = "false", comment =
			{
					"Allow player to learn transformations without quest."
			})
	public static boolean ALLOW_TRANSFORM_WITHOUT_QUEST;

	@ConfigProperty(name = "FeeDeleteTransferSkills", value = "10000000", comment =
			{
					"Fee to remove Transfer skills."
			})
	public static int FEE_DELETE_TRANSFER_SKILLS;

	@ConfigProperty(name = "FeeDeleteSubClassSkills", value = "10000000", comment =
			{
					"Fee to remove Sub-Class skills."
			})
	public static int FEE_DELETE_SUBCLASS_SKILLS;

	@ConfigProperty(name = "FeeDeleteDualClassSkills", value = "20000000", comment =
			{
					"Fee to remove Dual-Class skills."
			})
	public static int FEE_DELETE_DUALCLASS_SKILLS;

	@ConfigProperty(name = "RestoreServitorOnReconnect", value = "true", comment =
			{
					"Servitor summons on login if player had it summoned before logout"
			})
	public static boolean RESTORE_SERVITOR_ON_RECONNECT;

	@ConfigProperty(name = "RestorePetOnReconnect", value = "true", comment =
			{
					"Pet summons on login if player had it summoned before logout"
			})
	public static boolean RESTORE_PET_ON_RECONNECT;

	@ConfigProperty(name = "MaxExpBonus", value = "0", comment =
			{
					"Maximum Exp Bonus.",
					"from vitality + nevit's hunting bonus, and etc.."
			})
	public static double MAX_BONUS_EXP;

	@ConfigProperty(name = "MaxSpBonus", value = "0", comment =
			{
					"Maximum Sp Bonus.",
					"from vitality + nevit's hunting bonus, and etc.."
			})
	public static double MAX_BONUS_SP;

	@ConfigProperty(name = "MaxRunSpeed", value = "300", comment =
			{
					"Maximum character running speed."
			})
	public static int MAX_RUN_SPEED;

	@ConfigProperty(name = "MaxPCritRate", value = "500", comment =
			{
					"Maximum character Physical Critical Rate. (10 = 1%)"
			})
	public static int MAX_PCRIT_RATE;

	@ConfigProperty(name = "MaxMCritRate", value = "500", comment =
			{
					"Maximum character Magic Critical Rate. (10 = 1%)"
			})
	public static int MAX_MCRIT_RATE;

	@ConfigProperty(name = "MaxPAtkSpeed", value = "1500", comment =
			{
					"Maximum character Attack Speed."
			})
	public static int MAX_PATK_SPEED;

	@ConfigProperty(name = "MaxMAtkSpeed", value = "1999", comment =
			{
					"Maximum character Cast Speed."
			})
	public static int MAX_MATK_SPEED;

	@ConfigProperty(name = "MaxEvasion", value = "250", comment =
			{
					"Maximum character Evasion."
			})
	public static int MAX_EVASION;

	@ConfigProperty(name = "MaxHp", value = "150000", comment =
			{
					"Maximum character HP. Retail: 150 000"
			})
	public static int MAX_HP;

	@ConfigProperty(name = "MaxBaseStat", value = "200", comment =
			{
					"Maximum character STR/DEX/CON/INT/WIT/MEN/CHA/LUC. Retail: 200"
			})
	public static int MAX_BASE_STAT;

	@ConfigProperty(name = "MinAbnormalStateSuccessRate", value = "10", comment =
			{
					"Minimum and Maximum Abnormal State Success Rate.",
					"This affect all skills/effects chances, except in skills where minChance or maxChance parameters are defined."
			})
	public static int MIN_ABNORMAL_STATE_SUCCESS_RATE;

	@ConfigProperty(name = "MaxAbnormalStateSuccessRate", value = "90")
	public static int MAX_ABNORMAL_STATE_SUCCESS_RATE;

	@ConfigProperty(name = "MaxSp", value = "50000000000", comment =
			{
					"Maximum amount of SP a character can posses.",
					"Current retail limit is 50 billion, use -1 to set it to unlimited."
			})
	public static long MAX_SP;

	@ConfigProperty(name = "MaxSubclass", value = "3", comment =
			{
					"Maximum number of allowed subclasses for every player.",
					"Do not use more than 3!"
			})
	public static byte MAX_SUBCLASS;

	@ConfigProperty(name = "BaseSubclassLevel", value = "40", comment =
			{
					"Starting level for subclasses."
			})
	public static byte BASE_SUBCLASS_LEVEL;

	@ConfigProperty(name = "BaseDualclassLevel", value = "85", comment =
			{
					"Starting level for dualclasses after reawaking."
			})
	public static byte BASE_DUALCLASS_LEVEL;

	@ConfigProperty(name = "MaxSubclassLevel", value = "80", comment =
			{
					"Maximum subclass level."
			})
	public static byte MAX_SUBCLASS_LEVEL;

	@ConfigProperty(name = "MaxPvtStoreSellSlotsDwarf", value = "4", comment =
			{
					"Maximum number of allowed slots for Private Stores Sell.",
					"Other means all the other races aside from Dwarf."
			})
	public static int MAX_PVTSTORESELL_SLOTS_DWARF;

	@ConfigProperty(name = "MaxPvtStoreSellSlotsOther", value = "3")
	public static int MAX_PVTSTORESELL_SLOTS_OTHER;

	@ConfigProperty(name = "MaxPvtStoreBuySlotsDwarf", value = "5", comment =
			{
					"Maximum number of allowed slots for Private Stores Buy.",
					"Other means all the other races aside from Dwarf."
			})
	public static int MAX_PVTSTOREBUY_SLOTS_DWARF;

	@ConfigProperty(name = "MaxPvtStoreBuySlotsOther", value = "4")
	public static int MAX_PVTSTOREBUY_SLOTS_OTHER;

	@ConfigProperty(name = "MaximumSlotsForNoDwarf", value = "80", comment =
			{
					"This will control the inventory space limit (NOT WEIGHT LIMIT)."
			})
	public static int INVENTORY_MAXIMUM_NO_DWARF;

	@ConfigProperty(name = "MaximumSlotsForDwarf", value = "100")
	public static int INVENTORY_MAXIMUM_DWARF;

	@ConfigProperty(name = "MaximumSlotsForGMPlayer", value = "250")
	public static int INVENTORY_MAXIMUM_GM;

	@ConfigProperty(name = "MaximumSlotsForQuestItems", value = "100")
	public static int INVENTORY_MAXIMUM_QUEST_ITEMS;

	@ConfigProperty(name = "MaximumWarehouseSlotsForDwarf", value = "120", comment =
			{
					"This will control a character's warehouse capacity.",
					"Notes:",
					"	This must be LESS then 300 or the client will crash."
			})
	public static int WAREHOUSE_SLOTS_DWARF;

	@ConfigProperty(name = "MaximumWarehouseSlotsForNoDwarf", value = "100")
	public static int WAREHOUSE_SLOTS_NO_DWARF;

	@ConfigProperty(name = "MaximumWarehouseSlotsForClan", value = "200")
	public static int WAREHOUSE_SLOTS_CLAN;

	@ConfigProperty(name = "MaximumFreightSlots", value = "200", comment =
			{
					"Freight",
					"Maximum items that can be placed in Freight"
			})
	public static int ALT_FREIGHT_SLOTS;

	@ConfigProperty(name = "WarehouseDepositPrice", value = "30", comment =
			{
					"Price per item deposited in Warehouse.",
					"Note: price will not change visually."
			})
	public static int WH_DEPOSIT_PRICE_PER_ITEM;

	@ConfigProperty(name = "FreightPrice", value = "1000", comment =
			{
					"The price for each item that's deposited",
					"Note: price will not change visually."
			})
	public static int ALT_FREIGHT_PRICE;

	@ConfigProperty(name = "MessageFee", value = "100", comment =
			{
					"Price per message sent via mail.",
					"Note: price will not change visually."
			})
	public static int ALT_MESSAGE_FEE;

	@ConfigProperty(name = "MessageFeePerSlot", value = "1000", comment =
			{
					"Price per attachement sent in a mail message.",
					"Note: price will not change visually."
			})
	public static int ALT_MESSAGE_FEE_PER_SLOT;

	@ConfigProperty(name = "MentorPenaltyForMenteeComplete", value = "2", comment =
			{
					"When a mentee leave his mentor or get kicked by it mentor receives 7 days penalty"
			})
	public static long MENTOR_PENALTY_FOR_MENTEE_COMPLETE;

	@ConfigProperty(name = "MentorPenaltyForMenteeLeave", value = "2", comment =
			{
					"When a mentee leave his mentor or get kicked by it mentor receives 7 days penalty."
			})
	public static long MENTOR_PENALTY_FOR_MENTEE_LEAVE;

	@ConfigProperty(name = "KarmaAmount", value = "720", comment =
			{
					"Base karma amount used in karma gain/drop formula"
			})
	public static int KARMA_AMOUNT;

	@ConfigProperty(name = "AltKarmaPlayerCanBeKilledInPeaceZone", value = "false", comment =
			{
					"Karma player can be killed in Peace zone."
			})
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_BE_KILLED_IN_PEACEZONE;

	@ConfigProperty(name = "AltKarmaPlayerCanShop", value = "true", comment =
			{
					"Karma player can shop."
			})
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_SHOP;

	@ConfigProperty(name = "AltKarmaPlayerCanTeleport", value = "true", comment =
			{
					"Karma player can use escape and recall skills."
			})
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_TELEPORT;

	@ConfigProperty(name = "AltKarmaPlayerCanUseGK", value = "false", comment =
			{
					"Karma player can use GateKeeper."
			})
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_USE_GK;

	@ConfigProperty(name = "AltKarmaPlayerCanTrade", value = "true", comment =
			{
					"Karma player can trade."
			})
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_TRADE;

	@ConfigProperty(name = "AltKarmaPlayerCanUseWareHouse", value = "true", comment =
			{
					"Karma player can use warehouse."
			})
	public static boolean ALT_GAME_KARMA_PLAYER_CAN_USE_WAREHOUSE;

	@ConfigProperty(name = "MaxPersonalFamePoints", value = "100000", comment =
			{
					"The maximum number of Fame points a player can have"
			})
	public static int MAX_PERSONAL_FAME_POINTS;

	@ConfigProperty(name = "FortressZoneFameTaskFrequency", value = "300", comment =
			{
					"How frequently the player gets Fame points while in a Fortress Siege zone"
			})
	public static int FORTRESS_ZONE_FAME_TASK_FREQUENCY;

	@ConfigProperty(name = "FortressZoneFameAquirePoints", value = "31", comment =
			{
					"How much Fame aquired while in a Fortress Siege Zone"
			})
	public static int FORTRESS_ZONE_FAME_AQUIRE_POINTS;

	@ConfigProperty(name = "CastleZoneFameTaskFrequency", value = "300", comment =
			{
					"How frequently the player gets Fame points while in a Castle Siege zone"
			})
	public static int CASTLE_ZONE_FAME_TASK_FREQUENCY;

	@ConfigProperty(name = "CastleZoneFameAquirePoints", value = "1250", comment =
			{
					"How much Fame acquired while in a Castle Siege Zone"
			})
	public static int CASTLE_ZONE_FAME_AQUIRE_POINTS;

	@ConfigProperty(name = "FameForDeadPlayers", value = "true", comment =
			{
					"Dead players can receive fame."
			})
	public static boolean FAME_FOR_DEAD_PLAYERS;

	@ConfigProperty(name = "CraftingEnabled", value = "true", comment =
			{
					"Option to enable or disable crafting."
			})
	public static boolean IS_CRAFTING_ENABLED;

	@ConfigProperty(name = "DwarfRecipeLimit", value = "100", comment =
			{
					"Limits for recipes"
			})
	public static int DWARF_RECIPE_LIMIT;

	@ConfigProperty(name = "CommonRecipeLimit", value = "100")
	public static int COMMON_RECIPE_LIMIT;

	@ConfigProperty(name = "AltClanLeaderInstantActivation", value = "false", comment =
			{
					"---------------------------------------------------------------------------",
					"Clan",
					"---------------------------------------------------------------------------",
					"When enabled all clan leader requests will be performed instantly."
			})
	public static boolean ALT_CLAN_LEADER_INSTANT_ACTIVATION;

	@ConfigProperty(name = "DaysBeforeJoinAClan", value = "1", comment =
			{
					"Number of days you have to wait before joining another clan."
			})
	public static int ALT_CLAN_JOIN_DAYS;

	@ConfigProperty(name = "DaysBeforeCreateAClan", value = "10", comment =
			{
					"Number of days you have to wait before creating a new clan."
			})
	public static int ALT_CLAN_CREATE_DAYS;

	@ConfigProperty(name = "DaysToPassToDissolveAClan", value = "7", comment =
			{
					"Number of days it takes to dissolve a clan."
			})
	public static int ALT_CLAN_DISSOLVE_DAYS;

	@ConfigProperty(name = "DaysBeforeJoinAllyWhenLeaved", value = "1", comment =
			{
					"Number of days before joining a new alliance when clan voluntarily leave an alliance.",
					"DEFAULT NEEDS TO BE VERIFIED, MUST BE CHANGED HERE AND IN CONFIG.JAVA IF NOT CORRECT"
			})
	public static int ALT_ALLY_JOIN_DAYS_WHEN_LEAVED;

	@ConfigProperty(name = "DaysBeforeJoinAllyWhenDismissed", value = "1", comment =
			{
					"Number of days before joining a new alliance when clan was dismissed from an alliance.",
					"DEFAULT NEEDS TO BE VERIFIED, MUST BE CHANGED HERE AND IN CONFIG.JAVA IF NOT CORRECT"
			})
	public static int ALT_ALLY_JOIN_DAYS_WHEN_DISMISSED;

	@ConfigProperty(name = "DaysBeforeAcceptNewClanWhenDismissed", value = "1", comment =
			{
					"Number of days before accepting a new clan for alliance when clan was dismissed from an alliance.",
					"DEFAULT NEEDS TO BE VERIFIED, MUST BE CHANGED HERE AND IN CONFIG.JAVA IF NOT CORRECT"
			})
	public static int ALT_ACCEPT_CLAN_DAYS_WHEN_DISMISSED;

	@ConfigProperty(name = "DaysBeforeCreateNewAllyWhenDissolved", value = "1", comment =
			{
					"Number of days before creating a new alliance after dissolving an old alliance.",
					"DEFAULT NEEDS TO BE VERIFIED, MUST BE CHANGED HERE AND IN CONFIG.JAVA IF NOT CORRECT"
			})
	public static int ALT_CREATE_ALLY_DAYS_WHEN_DISSOLVED;

	@ConfigProperty(name = "AltMaxNumOfClansInAlly", value = "3", comment =
			{
					"Maximum number of clans in alliance."
			})
	public static int ALT_MAX_NUM_OF_CLANS_IN_ALLY;

	@ConfigProperty(name = "AltClanMembersForWar", value = "15", comment =
			{
					"Number of members needed to request a clan war."
			})
	public static int ALT_CLAN_MEMBERS_FOR_WAR;

	@ConfigProperty(name = "AltMembersCanWithdrawFromClanWH", value = "false", comment =
			{
					"Allow clan members to withdraw from the clan warehouse."
			})
	public static boolean ALT_MEMBERS_CAN_WITHDRAW_FROM_CLANWH;

	@ConfigProperty(name = "AltClanMembersTimeForBonus", value = "1800000")
	@ConfigComments(comment = {
			"The the time that player must be online to be counted as online player and registered for clan bonus."
	})
	public static long ALT_CLAN_MEMBERS_TIME_FOR_BONUS;

	@ConfigProperty(name = "RemoveCastleCirclets", value = "true")
	@ConfigComments(comment = {
			"Remove castle circlets after a clan loses their castle or a player leaves a clan."
	})
	public static boolean REMOVE_CASTLE_CIRCLETS;

	@ConfigProperty(name = "AltPartyMaxMembers", value = "7", comment =
			{
					"Maximal count of players in one party.",
					"WARNING: More than 7 can cause client UI problems."
			})
	public static int ALT_PARTY_MAX_MEMBERS;

	@ConfigProperty(name = "AltPartyRange", value = "1600", comment =
			{
					"CONFUSING(nothing to do with party) -> When you made damage to a mob",
					"and are inside this range, you will be considered as player to reward.",
					"Checks for party range to mob to calculate rewards(exp, items)."
			})
	public static int ALT_PARTY_RANGE;

	@ConfigProperty(name = "AltPartyRange2", value = "1400", comment =
			{
					"1. Used for Adena distribution in party",
					"2. Used to handle random and by turn party loot"
			})
	public static int ALT_PARTY_RANGE2;

	@ConfigProperty(name = "AltLeavePartyLeader", value = "false", comment =
			{
					"If true, when party leader leaves party, next member in party will be the leader.",
					"If false the party be will dispersed."
			})
	public static boolean ALT_LEAVE_PARTY_LEADER;

	@ConfigProperty(name = "InitialEquipmentEvent", value = "false", comment =
			{
					"Initial Equipment Events is to enable a special settings for the items that a new character starts with."
			})
	public static boolean INITIAL_EQUIPMENT_EVENT;

	@ConfigProperty(name = "StartingAdena", value = "0", comment =
			{
					"This is the amount of Adena that a new character starts their character with."
			})
	public static long STARTING_ADENA;

	@ConfigProperty(name = "StartingLevel", value = "1", comment =
			{
					"This is the starting level of the new character."
			})
	public static byte STARTING_LEVEL;

	@ConfigProperty(name = "StartingSP", value = "0", comment =
			{
					"This is the amount of SP that a new character starts their character with."
			})
	public static int STARTING_SP;

	@ConfigProperty(name = "MaxAdena", value = "99900000000")
	@ConfigComments(comment = {
			"This is the maximum amount of Adena that character can have in his inventory or warehouse.",
			"The maximum input amount is 9,223,372,036,854,775,807. (nine quintillion",
			"two hundred twenty three quadrillion three hundred seventy two trillion thirty six billion",
			"eight hundred fifty four million seven hundred seventy five thousand eight hundred seven)",
			"Setting negative values (-1 or others) will result in maximum amount available.",
			"Big values do not cause critical errors, although only 16 digits are visible in the inventory",
			"(example: 3,372,036,854,775,807 is visible out of 9,223,372,036,854,775,807)"
	})
	public static long MAX_ADENA;

	@ConfigProperty(name = "AutoLoot", value = "false")
	@ConfigComments(comment = {
			"This option, when set to True, will enable automatically picking up items.",
			"If set False it will force the player to pickup dropped items from mobs.",
			"This excludes herbs mentioned above and items from Raid/GrandBosses with minions."
	})
	public static boolean AUTO_LOOT;

	@ConfigProperty(name = "AutoLootRaids", value = "false")
	@ConfigComments(comment = {
			"This option, when set to True, will enable automatically picking up items from Raid/GrandBosses with minions.",
			"If set False it will force the player to pickup dropped items from bosses.",
			"This excludes herbs mentioned above and items from mobs."
	})
	public static boolean AUTO_LOOT_RAIDS;

	@ConfigProperty(name = "RaidLootRightsInterval", value = "900")
	@ConfigComments(comment = {
			"Delay for raid drop items loot privilege",
			"Require Command Channel , check next option",
			"Value is in seconds"
	})
	public static int LOOT_RAIDS_PRIVILEGE_INTERVAL;

	@ConfigProperty(name = "RaidLootRightsCCSize", value = "45", comment =
			{
					"Minimal size of Command Channel for apply raid loot privilege"
			})
	public static int LOOT_RAIDS_PRIVILEGE_CC_SIZE;

	@ConfigProperty(name = "UnstuckInterval", value = "300", comment =
			{
					"This is the time in seconds that it will take for the player command '/unstuck' to activate."
			})
	public static int UNSTUCK_INTERVAL;

	@ConfigProperty(name = "TeleportWatchdogTimeout", value = "0", comment =
			{
					"Teleport Watchdog Timeout (seconds)",
					"Player forced to appear if remain in teleported state longer than timeout",
					"Does not set too low, recommended value 60s.",
					"This time is in seconds, leave it at 0 if you want this feature disabled."
			})
	public static int TELEPORT_WATCHDOG_TIMEOUT;

	@ConfigProperty(name = "PlayerSpawnProtection", value = "600", comment =
			{
					"After a player teleports, this is the time the player is protected.",
					"This time is in seconds, leave it at 0 if you want this feature disabled.",
					"Retail (Since GE): 600 (10 minutes)"
			})
	public static int PLAYER_SPAWN_PROTECTION;

	@ConfigProperty(name = "PlayerSpawnProtectionAllowedItems", value = "0", comment =
			{
					"Spawn protection should disappear with any action with the exception",
					"of the item usage from items in this list.",
					"Format: itemId,itemId,itemId,....",
			})
	public static List<Integer> SPAWN_PROTECTION_ALLOWED_ITEMS = new ArrayList<>();

	@ConfigProperty(name = "PlayerTeleportProtection", value = "0", comment =
			{
					"Teleport spawn protection time. It will protect the player in the",
					"teleport spawn for the given time. 0 to disable feature"
			})
	public static int PLAYER_TELEPORT_PROTECTION;

	@ConfigProperty(name = "RandomRespawnInTownEnabled", value = "true", comment =
			{
					"If enabled, players respawn in town on different locations defined in zone.xml for given town.",
					"If disabled the first spawn location from zone.xml is used."
			})
	public static boolean RANDOM_RESPAWN_IN_TOWN_ENABLED;

	@ConfigProperty(name = "OffsetOnTeleportEnabled", value = "true", comment =
			{
					"This will allow a random offset from the base teleport location coordinates based on a maximum offset."
			})
	public static boolean OFFSET_ON_TELEPORT_ENABLED;

	@ConfigProperty(name = "MaxOffsetOnTeleport", value = "50", comment =
			{
					"Maximum offset for base teleport location when OffsetOnTeleportEnabled is enabled ."
			})
	public static int MAX_OFFSET_ON_TELEPORT;

	@ConfigProperty(name = "PetitioningAllowed", value = "true", comment =
			{
					"This option is to enable or disable the use of in game petitions.",
					"The MaxPetitionsPerPlayer is the amount of petitions a player can make.",
					"The MaximumPendingPetitions is the total amount of petitions in the server.",
					"Note:",
					"	Logically, MaximumPendingPetitions must be higher then MaxPetitionsPerPlayer."
			})
	public static boolean PETITIONING_ALLOWED;

	@ConfigProperty(name = "MaxPetitionsPerPlayer", value = "5")
	public static int MAX_PETITIONS_PER_PLAYER;

	@ConfigProperty(name = "MaxPetitionsPending", value = "25")
	public static int MAX_PETITIONS_PENDING;

	@ConfigProperty(name = "AltFreeTeleporting", value = "false", comment =
			{
					"Free teleporting around the world."
			})
	public static boolean ALT_GAME_FREE_TELEPORT;

	@ConfigProperty(name = "DeleteCharAfterDays", value = "1", comment =
			{
					"Allow character deletion after days set below. To disallow character deletion, set this equal to 0."
			})
	public static int DELETE_DAYS;

	@ConfigProperty(name = "AltGameExponentXp", value = "0", comment =
			{
					"Alternative Xp/Sp rewards, if not 0, then calculated as 2^((mob.level-player.level) / coef). Coef are the 2 numbers set below.",
					"A few examples for 'AltGameExponentXp = 5.' and 'AltGameExponentSp = 3.':",
					"	diff = 0 (player and mob has the same level), XP bonus rate = 1, SP bonus rate = 1",
					"	diff = 3 (mob is 3 levels above), XP bonus rate = 1.52, SP bonus rate = 2",
					"	diff = 5 (mob is 5 levels above), XP bonus rate = 2, SP bonus rate = 3.17",
					"	diff = -8 (mob is 8 levels below), XP bonus rate = 0.4, SP bonus rate = 0.16"
			})
	public static float ALT_GAME_EXPONENT_XP;

	@ConfigProperty(name = "AltGameExponentSp", value = "0")
	public static float ALT_GAME_EXPONENT_SP;

	@ConfigProperty(name = "PartyXpCutoffMethod", value = "highfive", comment =
			{
					"PARTY XP DISTRIBUTION",
					"With 'auto method' member is cut from Exp/SP distribution when his share is lower than party bonus acquired for him (30% for 2 member party).",
					"In that case he will not receive any Exp/SP from party and is not counted for party bonus.",
					"If you don't want to have a cutoff point for party members' XP distribution, set the first option to 'none'.",
					"Available Options: highfive, auto, level, percentage, none"
			})
	public static String PARTY_XP_CUTOFF_METHOD;

	@ConfigProperty(name = "PartyXpCutoffPercent", value = "3.0", comment =
			{
					"This option takes effect when 'percentage' method is chosen. Don't use high values for this!"
			})
	public static double PARTY_XP_CUTOFF_PERCENT;

	@ConfigProperty(name = "PartyXpCutoffLevel", value = "20", comment =
			{
					"This option takes effect when 'level' method is chosen. Don't use low values for this!"
			})
	public static int PARTY_XP_CUTOFF_LEVEL;

	@ConfigProperty(name = "PartyXpCutoffGaps", value = "0,9;10,14;15,99", comment =
			{
					"This option takes effect when \"highfive\" method is chosen.",
					"Each pair of numbers represent a level range.",
					"If the gap is between the first pair, there is no penalty.",
					"If the gap is between the second pair, the lowest party member will gain only 30% of the XP that others receive.",
					"If the gap is between the last pair, the lowest party member will not receive any XP."
			})
	public static String _PARTY_XP_CUTOFF_GAPS;
	public static int[][] PARTY_XP_CUTOFF_GAPS;

	@ConfigProperty(name = "PartyXpCutoffGapPercent", value = "100;30;0", comment =
			{
					"This option takes effect when \"highfive\" method is chosen.",
					"Each number represent the XP percent gain at that level gap.",
					"For the first gap, the lowest party member will gain 100% XP as there is no penalty.",
					"For the second gap, the lowest party member will gain only 30% of the XP that others receive.",
					"For the last gap, the lowest party member will not receive any XP."
			})
	public static String _PARTY_XP_CUTOFF_GAP_PERCENTS;
	public static int[] PARTY_XP_CUTOFF_GAP_PERCENTS;

	@ConfigProperty(name = "ExpertisePenalty", value = "true", comment =
			{
					"Expertise penalty",
					"If disabled, player will not receive penalty for equip higher grade items"
			})
	public static boolean EXPERTISE_PENALTY;

	@ConfigProperty(name = "StoreRecipeShopList", value = "false", comment =
			{
					"Store/Restore Dwarven Manufacture list",
					"Keep manufacture shoplist after relog"
			})
	public static boolean STORE_RECIPE_SHOPLIST;

	@ConfigProperty(name = "StoreCharUiSettings", value = "true", comment =
			{
					"Player can in client define his own key mapping and for save it must be stored server side."
			})
	public static boolean STORE_UI_SETTINGS;

	@ConfigProperty(name = "ForbiddenNames", value = "annou,ammou,amnou,anmou,anou,amou", comment =
			{
					"Character name restriction",
					"Disallow characters to have a name which contains the words.",
					"Split them with ','. Example: announcements,announce..."
			})
	public static String[] FORBIDDEN_NAMES;

	@ConfigProperty(name = "SilenceModeExclude", value = "false", comment =
			{
					"If enabled, when character in silence (block PMs) mode sends a PM to a character, silence mode no longer blocks this character,",
					"allowing both characters send each other PMs even with enabled silence mode.",
					"The exclude list is cleared each time the character goes into silence mode."
			})
	public static boolean SILENCE_MODE_EXCLUDE;

	@ConfigProperty(name = "GoDVideoIntro", value = "true", comment =
			{
					"Show Goddess of Destruction video introduction for newly created character"
			})
	public static boolean SHOW_GOD_VIDEO_INTRO;

	@ConfigProperty(name = "HpRegenMultiplier", value = "1.0", comment =
			{
					"Multiplier of HP, MP, and CP regeneration for players.",
					"Example: Setting HP to 10 will cause player HP to regenerate 90% slower than normal."
			})
	public static double HP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "MpRegenMultiplier", value = "1.0")
	public static double MP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "CpRegenMultiplier", value = "1.0")
	public static double CP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "EnchantChanceElementStone", value = "50", comment =
			{
					"---------------------------------------------------------------------------",
					"Enchanting",
					"---------------------------------------------------------------------------",
					"This controls the chance an item has to break if it is enchanted.",
					"This chance is in %, so if you set this to 100%, enchants will always succeed.",
					"DEFAULT NEEDS TO BE VERIFIED, MUST BE CHANGED HERE AND IN CONFIG.JAVA IF NOT CORRECT"
			})
	public static double ENCHANT_CHANCE_ELEMENT_STONE;

	@ConfigProperty(name = "EnchantChanceElementCrystal", value = "30")
	public static double ENCHANT_CHANCE_ELEMENT_CRYSTAL;

	@ConfigProperty(name = "EnchantChanceElementJewel", value = "20")
	public static double ENCHANT_CHANCE_ELEMENT_JEWEL;

	@ConfigProperty(name = "EnchantChanceElementEnergy", value = "10")
	public static double ENCHANT_CHANCE_ELEMENT_ENERGY;

	@ConfigProperty(name = "EnchantBlackList", value = "7816,7817,7818,7819,7820,7821,7822,7823,7824,7825,7826,7827,7828,7829,7830,7831,13293,13294,13296", comment =
			{
					"List of non-enchantable items.",
					"Currently apprentice, travelers weapons and Pailaka items"
			})
	public static int[] ENCHANT_BLACKLIST;

	@ConfigProperty(name = "AugmentationNGSkillChance", value = "15")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"Augmenting",
			"---------------------------------------------------------------------------",
			"These control the chance to get a skill in the augmentation process."
	})
	public static int AUGMENTATION_NG_SKILL_CHANCE;

	@ConfigProperty(name = "AugmentationNGGlowChance", value = "0", comment =
			{
					"These control the chance to get a glow effect in the augmentation process.",
					"Notes:",
					"	No/Mid Grade Life Stone can not have glow effect if you do not get a skill or base stat modifier."
			})
	public static int AUGMENTATION_NG_GLOW_CHANCE;

	@ConfigProperty(name = "AugmentationMidSkillChance", value = "30")
	public static int AUGMENTATION_MID_SKILL_CHANCE;

	@ConfigProperty(name = "AugmentationMidGlowChance", value = "40")
	public static int AUGMENTATION_MID_GLOW_CHANCE;

	@ConfigProperty(name = "AugmentationHighSkillChance", value = "45")
	public static int AUGMENTATION_HIGH_SKILL_CHANCE;

	@ConfigProperty(name = "AugmentationHighGlowChance", value = "70")
	public static int AUGMENTATION_HIGH_GLOW_CHANCE;

	@ConfigProperty(name = "AugmentationTopSkillChance", value = "60")
	public static int AUGMENTATION_TOP_SKILL_CHANCE;

	@ConfigProperty(name = "AugmentationTopGlowChance", value = "100")
	public static int AUGMENTATION_TOP_GLOW_CHANCE;

	@ConfigProperty(name = "AugmentationBaseStatChance", value = "1", comment =
			{
					"This controls the chance to get a base stat modifier in the augmentation process.",
					"Notes:",
					"	This has no dependency on the grade of Life Stone."
			})
	public static int AUGMENTATION_BASESTAT_CHANCE;

	@ConfigProperty(name = "AugmentationAccSkillChance", value = "0", comment =
			{
					"Accessory augmentation skills currently disabled"
			})
	public static int AUGMENTATION_ACC_SKILL_CHANCE;

	@ConfigProperty(name = "RetailLikeAugmentation", value = "true", comment =
			{
					"This will enable retail like weapon augmentation, but then you cant change",
					"weapon glow, base stat chance, because it wouldnt be retail like again."
			})
	public static boolean RETAIL_LIKE_AUGMENTATION;

	@ConfigProperty(name = "RetailLikeAugmentationNoGradeChance", value = "55,35,7,3", comment =
			{
					"This will have effect ONLY when RetailLikeAugmentation is True. The sum of 4 numbers must be 100!",
					"You can change probability (in %) of augment color chances - in order yellow, blue, purple, red",
					"Purple and Red always give skill. Default is 55%,35%,7%,3% for all lifestone grades (ie 7+3=10%",
					"for skill, not counting blue ones, that are very rare and not useful anyway)."
			})
	public static int[] RETAIL_LIKE_AUGMENTATION_NG_CHANCE;

	@ConfigProperty(name = "RetailLikeAugmentationMidGradeChance", value = "55,35,7,3", comment =
			{
					"This will have effect ONLY when RetailLikeAugmentation is True. The sum of 4 numbers must be 100!",
					"You can change probability (in %) of augment color chances - in order yellow, blue, purple, red",
					"Purple and Red always give skill. Default is 55%,35%,7%,3% for all lifestone grades (ie 7+3=10%",
					"for skill, not counting blue ones, that are very rare and not useful anyway)."
			})
	public static int[] RETAIL_LIKE_AUGMENTATION_MID_CHANCE;

	@ConfigProperty(name = "RetailLikeAugmentationHighGradeChance", value = "55,35,7,3", comment =
			{
					"This will have effect ONLY when RetailLikeAugmentation is True. The sum of 4 numbers must be 100!",
					"You can change probability (in %) of augment color chances - in order yellow, blue, purple, red",
					"Purple and Red always give skill. Default is 55%,35%,7%,3% for all lifestone grades (ie 7+3=10%",
					"for skill, not counting blue ones, that are very rare and not useful anyway)."
			})
	public static int[] RETAIL_LIKE_AUGMENTATION_HIGH_CHANCE;

	@ConfigProperty(name = "RetailLikeAugmentationTopGradeChance", value = "55,35,7,3", comment =
			{
					"This will have effect ONLY when RetailLikeAugmentation is True. The sum of 4 numbers must be 100!",
					"You can change probability (in %) of augment color chances - in order yellow, blue, purple, red",
					"Purple and Red always give skill. Default is 55%,35%,7%,3% for all lifestone grades (ie 7+3=10%",
					"for skill, not counting blue ones, that are very rare and not useful anyway)."
			})
	public static int[] RETAIL_LIKE_AUGMENTATION_TOP_CHANCE;

	@ConfigProperty(name = "RetailLikeAugmentationAccessory", value = "true")
	@ConfigComments(comment = {
			"This will enable retail like accessory augmentation, but then you cant change skill chances for accessory augments"
	})
	public static boolean RETAIL_LIKE_AUGMENTATION_ACCESSORY;

	@ConfigProperty(name = "AugmentationBlackList", value = "6656,6657,6658,6659,6660,6661,6662,8191,10170,10314,13740,13741,13742,13743,13744,13745,13746,13747,13748,14592,14593,14594,14595,14596,14597,14598,14599,14600,14664,14665,14666,14667,14668,14669,14670,14671,14672,14801,14802,14803,14804,14805,14806,14807,14808,14809,15282,15283,15284,15285,15286,15287,15288,15289,15290,15291,15292,15293,15294,15295,15296,15297,15298,15299,16025,16026,21712,22173,22174,22175", comment =
			{
					"List of non-augmentable items, currently contains only Grand Boss jewels",
					"Shadow, common, time-limited, hero, pvp, wear items are hardcoded, as well as all etcitems.",
					"Rods can't be augmented too."
			})
	public static int[] AUGMENTATION_BLACKLIST;

	@ConfigProperty(name = "AltAllowAugmentPvPItems", value = "false")
	@ConfigComments(comment = {
			"Allows alternative augmentation of PvP items."
	})
	public static boolean ALT_ALLOW_AUGMENT_PVP_ITEMS;

	@ConfigProperty(name = "EnableVitality", value = "true", comment =
			{
					"Enables vitality system"
			})
	public static boolean ENABLE_VITALITY;

	@ConfigProperty(name = "StartingVitalityPoints", value = "140000", comment =
			{
					"Option to set a lower vitality at character creation.",
					"Vitality needs to be enabled, and startingpoints needs to be lower",
					"than max-vitality points."
			})
	public static int STARTING_VITALITY_POINTS;

	@ConfigProperty(name = "NpcTalkBlockingTime", value = "0", comment =
			{
					"Npc talk blockage. When a player talks to a NPC, he must wait some secs",
					"before being able to walk again. In seconds",
					"Set to 0 to disable it"
			})
	public static int PLAYER_MOVEMENT_BLOCK_TIME;

	@ConfigProperty(name = "AbilityMaxPoints", value = "16")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"Ability Settings:",
			"---------------------------------------------------------------------------",
			"The maximum ability points character could possibly have."
	})
	public static int ABILITY_MAX_POINTS;

	@ConfigProperty(name = "AbilityPointsResetAdena", value = "10000000")
	@ConfigComments(comment = {
			"Adena needed to reset used ability point."
	})
	public static long ABILITY_POINTS_RESET_ADENA;

	// hard coded config, handled in loadImpl
	public static int MAX_ITEM_IN_PACKET;

	@ConfigAfterLoad
	@SuppressWarnings("unused")
	protected void afterLoad() {
		final String[] gaps = _PARTY_XP_CUTOFF_GAPS.split(";");
		PARTY_XP_CUTOFF_GAPS = new int[gaps.length][2];
		for (int i = 0; i < gaps.length; i++) {
			PARTY_XP_CUTOFF_GAPS[i] = new int[]
					{
							Integer.parseInt(gaps[i].split(",")[0]),
							Integer.parseInt(gaps[i].split(",")[1])
					};
		}

		final String[] percents = _PARTY_XP_CUTOFF_GAP_PERCENTS.split(";");
		PARTY_XP_CUTOFF_GAP_PERCENTS = new int[percents.length];
		for (int i = 0; i < percents.length; i++) {
			PARTY_XP_CUTOFF_GAP_PERCENTS[i] = Integer.parseInt(percents[i]);
		}

		MAX_ITEM_IN_PACKET = Math.max(INVENTORY_MAXIMUM_NO_DWARF, Math.max(INVENTORY_MAXIMUM_DWARF, INVENTORY_MAXIMUM_GM));
	}
}