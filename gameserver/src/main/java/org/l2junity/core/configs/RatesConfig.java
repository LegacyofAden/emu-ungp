package org.l2junity.core.configs;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@Slf4j
@ConfigFile(name = "configs/rates.properties")
public class RatesConfig {
	@ConfigProperty(name = "RateXp", value = "1")
	@ConfigComments(comment = {
			"Experience multiplier"
	})
	public static float RATE_XP;

	@ConfigProperty(name = "RateSp", value = "1")
	@ConfigComments(comment = {
			"Skill points multiplier"
	})
	public static float RATE_SP;

	@ConfigProperty(name = "RatePartyXp", value = "1")
	@ConfigComments(comment = {
			"Experience multiplier (Party)"
	})
	public static float RATE_PARTY_XP;

	@ConfigProperty(name = "RatePartySp", value = "1")
	@ConfigComments(comment = {
			"Skill points multiplier (Party)"
	})
	public static float RATE_PARTY_SP;

	@ConfigProperty(name = "RateInstanceXp", value = "-1")
	@ConfigComments(comment = {
			"Instance rates",
			"Those rates are used as absolute rate within instances, does not applies on top of RateXp for example!",
			"Instance Experience multiplier"
	})
	public static float RATE_INSTANCE_XP;

	@ConfigProperty(name = "RateInstanceSp", value = "-1")
	@ConfigComments(comment = {
			"Instance Skill points multiplier"
	})
	public static float RATE_INSTANCE_SP;

	@ConfigProperty(name = "RateInstancePartyXp", value = "-1")
	@ConfigComments(comment = {
			"Instance Experience multiplier (Party)"
	})
	public static float RATE_INSTANCE_PARTY_XP;

	@ConfigProperty(name = "RateInstancePartySp", value = "-1")
	@ConfigComments(comment = {
			"Instance Skill Point multiplier (Party)"
	})
	public static float RATE_INSTANCE_PARTY_SP;

	@ConfigProperty(name = "RateHellboundTrustIncrease", value = "1")
	@ConfigComments(comment = {
			"Hellbound trust increase/decrease multipliers"
	})
	public static float RATE_HB_TRUST_INCREASE;

	@ConfigProperty(name = "RateHellboundTrustDecrease", value = "1")
	public static float RATE_HB_TRUST_DECREASE;

	@ConfigProperty(name = "RateRaidbossPointsReward", value = "1")
	@ConfigComments(comment = {
			"Raidboss points multipler"
	})
	public static float RATE_RAIDBOSS_POINTS;

	@ConfigProperty(name = "RateExtractable", value = "1.")
	@ConfigComments(comment = {
			"Modify the rate of reward of all extractable items and skills."
	})
	public static float RATE_EXTRACTABLE;

	@ConfigProperty(name = "RateDropManor", value = "1")
	public static int RATE_DROP_MANOR;

	@ConfigProperty(name = "RateQuestDrop", value = "1")
	@ConfigComments(comment = {
			"Quest item drop multiplier"
	})
	public static float RATE_QUEST_DROP;

	@ConfigProperty(name = "RateQuestReward", value = "1")
	@ConfigComments(comment = {
			"Default reward multiplier",
			"When UseRewardMultipliers=False - default multiplier is used for any reward",
			"When UseRewardMultipliers=True  - default multiplier is used for all items not affected by additional multipliers"
	})
	public static float RATE_QUEST_REWARD;

	@ConfigProperty(name = "RateQuestRewardXP", value = "1")
	@ConfigComments(comment = {
			"Exp/SP reward multipliers"
	})
	public static float RATE_QUEST_REWARD_XP;

	@ConfigProperty(name = "RateQuestRewardSP", value = "1")
	public static float RATE_QUEST_REWARD_SP;

	@ConfigProperty(name = "RateQuestRewardAdena", value = "1")
	@ConfigComments(comment = {
			"Adena reward multiplier"
	})
	public static float RATE_QUEST_REWARD_ADENA;

	@ConfigProperty(name = "UseQuestRewardMultipliers", value = "false")
	@ConfigComments(comment = {
			"Use additional item multipliers?"
	})
	public static boolean RATE_QUEST_REWARD_USE_MULTIPLIERS;

	@ConfigProperty(name = "RateQuestRewardPotion", value = "1")
	@ConfigComments(comment = {
			"Additional quest-reward multipliers based on item type"
	})
	public static float RATE_QUEST_REWARD_POTION;

	@ConfigProperty(name = "RateQuestRewardScroll", value = "1")
	public static float RATE_QUEST_REWARD_SCROLL;

	@ConfigProperty(name = "RateQuestRewardRecipe", value = "1")
	public static float RATE_QUEST_REWARD_RECIPE;

	@ConfigProperty(name = "RateQuestRewardMaterial", value = "1")
	public static float RATE_QUEST_REWARD_MATERIAL;

	@ConfigProperty(name = "BaseLuckDropChanceMultiplier", value = "0.025")
	@ConfigComments(comment = {
			"BASE chance multiplier for luck drop (fortune pocket), this base rate is applied to items chance"
	})
	public static float BASE_LUCK_DROP_CHANCE_MULTIPLIER;

	@ConfigProperty(name = "DeathDropAmountMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the amount of items dropped from monster on ground when it dies."
	})
	public static float RATE_DEATH_DROP_AMOUNT_MULTIPLIER;

	@ConfigProperty(name = "CorpseDropAmountMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the amount of items looted from monster when a skill like Sweeper(Spoil) is used."
	})
	public static float RATE_CORPSE_DROP_AMOUNT_MULTIPLIER;

	@ConfigProperty(name = "HerbDropAmountMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the amount of items dropped from monster on ground when it dies."
	})
	public static float RATE_HERB_DROP_AMOUNT_MULTIPLIER;

	@ConfigProperty(name = "LuckDropAmountMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the amount of items dropped from monster when lucky."
	})
	public static float RATE_LUCK_DROP_AMOUNT_MULTIPLIER;

	@ConfigProperty(name = "DeathDropChanceMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the chance of items that can be dropped from monster on ground when it dies."
	})
	public static float RATE_DEATH_DROP_CHANCE_MULTIPLIER;

	@ConfigProperty(name = "CorpseDropChanceMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the chance of items that can be looted from monster when a skill like Sweeper(Spoil) is used."
	})
	public static float RATE_CORPSE_DROP_CHANCE_MULTIPLIER;

	@ConfigProperty(name = "HerbDropChanceMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the chance of items that can be dropped from monster on ground when it dies."
	})
	public static float RATE_HERB_DROP_CHANCE_MULTIPLIER;

	@ConfigProperty(name = "LuckDropChanceMultiplier", value = "1")
	@ConfigComments(comment = {
			"Multiplies the chance of items that can be dropped from monster when lucky."
	})
	public static float RATE_LUCK_DROP_CHANCE_MULTIPLIER;

	@ConfigProperty(name = "DropAmountMultiplierByItemId", value = "57:1")
	@ConfigComments(comment = {
			"List of items affected by custom drop rate by id, used now for Adena rate too.",
			"Usage: itemId1:multiplier1,itemId2:multiplier2,...",
			"Note: Make sure the lists do NOT CONTAIN trailing spaces or spaces between the numbers!",
			"Example for Raid boss 1x jewelry: 6656:1,6657:1,6658:1,6659:1,6660:1,6661:1,6662:1,8191:1,10170:1,10314:1"
	})
	public static Map<Integer, Float> RATE_DROP_AMOUNT_MULTIPLIER = new HashMap<>();

	@ConfigProperty(name = "DropChanceMultiplierByItemId", value = "57:1")
	@ConfigComments(comment = {
			"List of items affected by custom drop rate by id, used now for Adena rate too.",
			"Usage: itemId1:multiplier1,itemId2:multiplier2,...",
			"Note: Make sure the lists do NOT CONTAIN trailing spaces or spaces between the numbers!",
			"Example for Raid boss 1x jewelry: 6656:1,6657:1,6658:1,6659:1,6660:1,6661:1,6662:1,8191:1,10170:1,10314:1"
	})
	public static Map<Integer, Float> RATE_DROP_CHANCE_MULTIPLIER = new HashMap<>();

	@ConfigProperty(name = "RateKarmaLost", value = "-1")
	@ConfigComments(comment = {
			"Karma decreasing rate",
			"Note: -1 means RateXp so it means it will use retail rate for decreasing karma upon death or receiving exp by farming mobs."
	})
	public static float RATE_KARMA_LOST;

	@ConfigProperty(name = "RateKarmaExpLost", value = "1")
	public static float RATE_KARMA_EXP_LOST;

	@ConfigProperty(name = "RateSiegeGuardsPrice", value = "1")
	public static float RATE_SIEGE_GUARDS_PRICE;

	@ConfigProperty(name = "PlayerDropLimit", value = "0")
	public static int PLAYER_DROP_LIMIT;

	@ConfigProperty(name = "PlayerRateDrop", value = "0")
	@ConfigComments(comment = {
			"in %"
	})
	public static int PLAYER_RATE_DROP;

	@ConfigProperty(name = "PlayerRateDropItem", value = "0")
	@ConfigComments(comment = {
			"in %"
	})
	public static int PLAYER_RATE_DROP_ITEM;

	@ConfigProperty(name = "PlayerRateDropEquip", value = "0")
	@ConfigComments(comment = {
			"in %"
	})
	public static int PLAYER_RATE_DROP_EQUIP;

	@ConfigProperty(name = "PlayerRateDropEquipWeapon", value = "0")
	@ConfigComments(comment = {
			"in %"
	})
	public static int PLAYER_RATE_DROP_EQUIP_WEAPON;

	@ConfigProperty(name = "PetXpRate", value = "1")
	public static float PET_XP_RATE;

	@ConfigProperty(name = "PetFoodRate", value = "1")
	public static int PET_FOOD_RATE;

	@ConfigProperty(name = "SinEaterXpRate", value = "1")
	public static float SINEATER_XP_RATE;

	@ConfigProperty(name = "KarmaDropLimit", value = "10")
	public static int KARMA_DROP_LIMIT;

	@ConfigProperty(name = "KarmaRateDrop", value = "40")
	public static int KARMA_RATE_DROP;

	@ConfigProperty(name = "KarmaRateDropItem", value = "50")
	public static int KARMA_RATE_DROP_ITEM;

	@ConfigProperty(name = "KarmaRateDropEquip", value = "40")
	public static int KARMA_RATE_DROP_EQUIP;

	@ConfigProperty(name = "KarmaRateDropEquipWeapon", value = "10")
	public static int KARMA_RATE_DROP_EQUIP_WEAPON;

	@ConfigProperty(name = "RateVitalityExpMultiplier", value = "2.")
	@ConfigComments(comment = {
			"The following configures the XP multiplier of each vitality level. Basically, you have",
			"Take care setting these values according to your server rates, as the can lead to huge differences!",
			"Example with a server rate 15x and vitality = 2. => final server rate = 30 (15x2)!"
	})
	public static float RATE_VITALITY_EXP_MULTIPLIER;

	@ConfigProperty(name = "VitalityMaxItemsAllowed", value = "999")
	@ConfigComments(comment = {
			"Maximum vitality items allowed to be used for a week by a player."
	})
	public static int VITALITY_MAX_ITEMS_ALLOWED;

	@ConfigProperty(name = "RateVitalityLost", value = "1.")
	@ConfigComments(comment = {
			"This option manages the multiplier of vitality amount that is lost when a player kills mobs or raids.",
			"Default value is 1."
	})
	public static float RATE_VITALITY_LOST;

	@ConfigProperty(name = "RateVitalityGain", value = "0.1")
	@ConfigComments(comment = {
			"This option manages the multiplier of vitality amount that is gained instead of lost when a player kills mobs or raids during vitality recovery effect.",
			"Default value is 0.1 (10% of original vitality to be lost, independant of the above config)"
	})
	public static float RATE_VITALITY_GAIN;
}