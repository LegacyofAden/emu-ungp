package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ANZO
 * @since 01.04.2017
 */
@ConfigFile(name = "configs/npc.properties")
public class NpcConfig {
	@ConfigProperty(name = "AnnounceMammonSpawn", value = "false")
	@ConfigComments(comment = {
			"Global announcements will be made indicating Blacksmith/Merchant of Mammon",
			"Spawning points."
	})
	public static boolean ANNOUNCE_MAMMON_SPAWN;

	@ConfigProperty(name = "AltMobAgroInPeaceZone", value = "true")
	@ConfigComments(comment = {
			"True - Mobs can be aggressive while in peace zones.",
			"False - Mobs can NOT be aggressive while in peace zones."
	})
	public static boolean ALT_MOB_AGRO_IN_PEACEZONE;

	@ConfigProperty(name = "AltAttackableNpcs", value = "true")
	@ConfigComments(comment = {
			"Defines whether NPCs are attackable by default",
			"Retail: True"
	})
	public static boolean ALT_ATTACKABLE_NPCS;

	@ConfigProperty(name = "AltGameViewNpc", value = "false")
	@ConfigComments(comment = {
			"Allows non-GM players to view NPC stats via shift-click"
	})
	public static boolean ALT_GAME_VIEWNPC;

	@ConfigProperty(name = "MaxDriftRange", value = "300")
	@ConfigComments(comment = {
			"Maximum distance mobs can randomly go from spawn point.",
			"DEFAULT NEEDS TO BE VERIFIED, MUST BE CHANGED HERE AND IN CONFIG.JAVA IF NOT CORRECT"
	})
	public static int MAX_DRIFT_RANGE;

	@ConfigProperty(name = "ShowNpcLevel", value = "false")
	public static boolean SHOW_NPC_LVL;

	@ConfigProperty(name = "EnableRandomEnchantEffect", value = "false")
	@ConfigComments(comment = {
			"Custom random EnchantEffect",
			"All npcs with weapons get random weapon enchanted value",
			"Enchantment is only visual, range is 4-21"
	})
	public static boolean ENABLE_RANDOM_ENCHANT_EFFECT;

	@ConfigProperty(name = "MinNPCLevelForDmgPenalty", value = "78")
	@ConfigComments(comment = {
			"The minimum NPC level for the Gracia Epilogue rule:",
			"'The amount of damage inflicted on monsters will be lower if your character is 2 or more levels below that of the level 78+ monster.'",
			"Notes:",
			"	If you want to disable this feature then set it 99"
	})
	public static int MIN_NPC_LVL_DMG_PENALTY;

	@ConfigProperty(name = "DmgPenaltyForLvLDifferences", value = "1:0.8, 2:0.6, 3:0.5, 4:0.42, 5:0.36, 6:0.32, 7:0.28, 8:0.25")
	@ConfigComments(comment = {
			"The penalty in percent for -2 till -9 level differences"
	})
	public static Map<Integer, Float> NPC_DMG_PENALTY = new HashMap<>();

	@ConfigProperty(name = "CritDmgPenaltyForLvLDifferences", value = "1:0.8, 2:0.6, 3:0.5, 4:0.42, 5:0.36, 6:0.32, 7:0.28, 8:0.25")
	public static Map<Integer, Float> NPC_CRIT_DMG_PENALTY = new HashMap<>();

	@ConfigProperty(name = "SkillDmgPenaltyForLvLDifferences", value = "1:0.8, 2:0.6, 3:0.5, 4:0.42, 5:0.36, 6:0.32, 7:0.28, 8:0.25")
	public static Map<Integer, Float> NPC_SKILL_DMG_PENALTY = new HashMap<>();

	@ConfigProperty(name = "MinNPCLevelForMagicPenalty", value = "78")
	@ConfigComments(comment = {
			"The minimum NPC level for the Gracia Epilogue rule:",
			"'When a character's level is 3 or more levels lower than that of a monsters level the chance that the monster will be able to resist a magic spell will increase.'",
			"Notes:",
			"	If you want to disable this feature then set it 99"
	})
	public static int MIN_NPC_LVL_MAGIC_PENALTY;

	@ConfigProperty(name = "SkillChancePenaltyForLvLDifferences", value = "1:2.5, 2:3.0, 3:3.25, 4:3.5")
	@ConfigComments(comment = {
			"The penalty in percent for -3 till -6 level differences"
	})
	public static Map<Integer, Float> NPC_SKILL_CHANCE_PENALTY = new HashMap<>();

	@ConfigProperty(name = "DecayTimeTask", value = "5000")
	@ConfigComments(comment = {
			"Decay Time Task (don't set it too low!) (in milliseconds):"
	})
	public static int DECAY_TIME_TASK;

	@ConfigProperty(name = "DefaultCorpseTime", value = "7")
	@ConfigComments(comment = {
			"This is the default corpse time (in seconds)."
	})
	public static int DEFAULT_CORPSE_TIME;

	@ConfigProperty(name = "SpoiledCorpseExtendTime", value = "10")
	@ConfigComments(comment = {
			"This is the time that will be added to spoiled corpse time (in seconds)."
	})
	public static int SPOILED_CORPSE_EXTEND_TIME;

	@ConfigProperty(name = "CorpseConsumeSkillAllowedTimeBeforeDecay", value = "2000")
	@ConfigComments(comment = {
			"The time allowed to use a corpse consume skill before the corpse decays."
	})
	public static int CORPSE_CONSUME_SKILL_ALLOWED_TIME_BEFORE_DECAY;

	@ConfigProperty(name = "GuardAttackAggroMob", value = "false")
	@ConfigComments(comment = {
			"True - Allows guards to attack aggressive mobs within range."
	})
	public static boolean GUARD_ATTACK_AGGRO_MOB;

	@ConfigProperty(name = "AllowWyvernUpgrader", value = "false")
	@ConfigComments(comment = {
			"This option enables or disables the Wyvern manager located in every castle",
			"to train Wyverns and Striders from Hatchlings."
	})
	public static boolean ALLOW_WYVERN_UPGRADER;

	@ConfigProperty(name = "RaidHpRegenMultiplier", value = "1.0")
	@ConfigComments(comment = {
			"Multiplier of HP and MP regeneration for raid bosses.",
			"Example: Setting HP to 0.1 will cause raid boss HP to regenerate 90% slower than normal."
	})
	public static double RAID_HP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "RaidMpRegenMultiplier", value = "1.0")
	public static double RAID_MP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "RaidPDefenceMultiplier", value = "1.0")
	@ConfigComments(comment = {
			"Multiplier of physical and magical defense for raid bosses.",
			"Example: A setting of 0.1 will cause defense to be 90% lower than normal,",
			"while 1.1 will cause defense to be 10% higher than normal."
	})
	public static double RAID_PDEFENCE_MULTIPLIER;

	@ConfigProperty(name = "RaidMDefenceMultiplier", value = "1.0")
	public static double RAID_MDEFENCE_MULTIPLIER;

	@ConfigProperty(name = "RaidPAttackMultiplier", value = "1.0")
	@ConfigComments(comment = {
			"Multiplier of physical and magical attack for raid bosses.",
			"Example: A setting of 0.1 will cause attack to be 90% lower than normal,",
			"while 1.1 will cause attack to be 10% higher than normal."
	})
	public static double RAID_PATTACK_MULTIPLIER;

	@ConfigProperty(name = "RaidMAttackMultiplier", value = "1.0")
	public static double RAID_MATTACK_MULTIPLIER;

	@ConfigProperty(name = "RaidMinionRespawnTime", value = "300000")
	@ConfigComments(comment = {
			"Configure the interval at which raid boss minions will re-spawn.",
			"This time is in milliseconds, 1 minute is 60000 milliseconds."
	})
	public static double RAID_MINION_RESPAWN_TIMER;

	@ConfigProperty(name = "CustomMinionsRespawnTime", value = "22450:30,22371:120,22543:0,25545:0,22424:30,22425:30,22426:30,22427:30,22428:30,22429:30,22430:30,22432:30,22433:30,22434:30,22435:30,22436:30,22437:30,22438:30,25596:30,25605:0,25606:0,25607:0,25608:0")
	@ConfigComments(comment = {
			"Let's make handling of minions with non-standard static respawn easier - no additional code, just config.",
			"Format: minionId1:timeInSec1,minionId2:timeInSec2"
	})
	public static Map<Integer, Integer> MINIONS_RESPAWN_TIME = new HashMap<>();

	@ConfigProperty(name = "RaidMinRespawnMultiplier", value = "1.0")
	@ConfigComments(comment = {
			"Configure Minimum and Maximum time multiplier between raid boss re-spawn.",
			"By default 12Hours*1.0 for Minimum Time and 24Hours*1.0 for Maximum Time.",
			"Example: Setting RaidMaxRespawnMultiplier to 2 will make the time between",
			"re-spawn 24 hours to 48 hours."
	})
	public static float RAID_MIN_RESPAWN_MULTIPLIER;

	@ConfigProperty(name = "RaidMaxRespawnMultiplier", value = "1.0")
	public static float RAID_MAX_RESPAWN_MULTIPLIER;

	@ConfigProperty(name = "DisableRaidCurse", value = "false")
	@ConfigComments(comment = {
			"Disable Raid Curse if raid more than 8 levels lower.",
			"Caution: drop will be reduced or even absent if DeepBlue drop rules enabled."
	})
	public static boolean RAID_DISABLE_CURSE;

	@ConfigProperty(name = "RaidChaosTime", value = "10")
	@ConfigComments(comment = {
			"Configure the interval at which raid bosses and minions wont reconsider their target",
			"This time is in seconds, 1 minute is 60 seconds."
	})
	public static int RAID_CHAOS_TIME;

	@ConfigProperty(name = "GrandChaosTime", value = "10")
	public static int GRAND_CHAOS_TIME;

	@ConfigProperty(name = "MinionChaosTime", value = "10")
	public static int MINION_CHAOS_TIME;

	@ConfigProperty(name = "MaximumSlotsForPet", value = "12")
	@ConfigComments(comment = {
			"This will control the inventory space limit for pets (NOT WEIGHT LIMIT)."
	})
	public static int INVENTORY_MAXIMUM_PET;

	@ConfigProperty(name = "PetHpRegenMultiplier", value = "1.0")
	@ConfigComments(comment = {
			"HP/MP Regen Multiplier for Pets"
	})
	public static double PET_HP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "PetMpRegenMultiplier", value = "1.0")
	public static double PET_MP_REGEN_MULTIPLIER;

	@ConfigProperty(name = "DropAdenaMinLevelDifference", value = "8")
	@ConfigComments(comment = {
			"The min and max level difference used for level gap calculation",
			"this is only for how many levels higher the player is than the monster"
	})
	public static int DROP_ADENA_MIN_LEVEL_DIFFERENCE;

	@ConfigProperty(name = "DropAdenaMaxLevelDifference", value = "15")
	public static int DROP_ADENA_MAX_LEVEL_DIFFERENCE;

	@ConfigProperty(name = "DropAdenaMinLevelGapChance", value = "10")
	@ConfigComments(comment = {
			"This is the minimum level gap chance meaning for 10 that the monster will have 10% chance",
			"to allow dropping the item if level difference is bigger than DropAdenaMaxLevelDifference",
			"Note: This value is scalling from 100 to the specified value for DropAdenaMinLevelDifference to DropAdenaMaxLevelDifference limits"
	})
	public static double DROP_ADENA_MIN_LEVEL_GAP_CHANCE;

	@ConfigProperty(name = "DropItemMinLevelDifference", value = "5")
	@ConfigComments(comment = {
			"The min and max level difference used for level gap calculation",
			"this is only for how many levels higher the player is than the monster"
	})
	public static int DROP_ITEM_MIN_LEVEL_DIFFERENCE;

	@ConfigProperty(name = "DropItemMaxLevelDifference", value = "10")
	public static int DROP_ITEM_MAX_LEVEL_DIFFERENCE;

	@ConfigProperty(name = "DropItemMinLevelGapChance", value = "10")
	@ConfigComments(comment = {
			"This is the minimum level gap chance meaning for 10 that the monster will have 10% chance",
			"to allow dropping the item if level difference is bigger than DropAdenaMaxLevelDifference",
			"Note: This value is scalling from 100 to the specified value for DropAdenaMinLevelDifference to DropAdenaMaxLevelDifference limits"
	})
	public static double DROP_ITEM_MIN_LEVEL_GAP_CHANCE;

	@ConfigProperty(name = "VitalityConsumeByMob", value = "2250")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"Vitality",
			"---------------------------------------------------------------------------",
			"Vitality decrease multiplier for mob after awakening"
	})
	public static int VITALITY_CONSUME_BY_MOB;

	@ConfigProperty(name = "VitalityConsumeByBoss", value = "1125")
	@ConfigComments(comment = {
			"Vitality increase multiplier for raid and world boss after awakening"
	})
	public static int VITALITY_CONSUME_BY_BOSS;
}
