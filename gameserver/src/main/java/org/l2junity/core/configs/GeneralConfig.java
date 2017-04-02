package org.l2junity.core.configs;

import org.l2junity.commons.config.annotation.ConfigAfterLoad;
import org.l2junity.commons.config.annotation.ConfigComments;
import org.l2junity.commons.config.annotation.ConfigFile;
import org.l2junity.commons.config.annotation.ConfigProperty;
import org.l2junity.commons.lang.management.DeadlockDetector;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.enums.IllegalActionPunishmentType;
import org.l2junity.gameserver.util.Broadcast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ANZO
 * @since 02.04.2017
 */
@ConfigFile(name = "configs/general.properties")
public class GeneralConfig {
	@ConfigProperty(name = "DefaultAccessLevel", value = "0")
	@ConfigComments(comment = {
			"Default Access Level. For example if you set 7, everyone will have 7 access level."
	})
	public static int DEFAULT_ACCESS_LEVEL;

	@ConfigProperty(name = "ServerListBrackets", value = "false")
	@ConfigComments(comment = {
			"Setting for serverList",
			"Displays [] in front of server name on character selection"
	})
	public static boolean SERVER_LIST_BRACKET;

	@ConfigProperty(name = "ServerListType", value = "Normal")
	@ConfigComments(comment = {
			"Displays server type next to the server name on character selection.",
			"Notes:",
			"	Accepted Values: Normal, Relax, Test, Broad, Restricted, Event, Free, World, New, Classic"
	})
	public static String _SERVER_LIST_TYPE;
	public static int SERVER_LIST_TYPE;

	@ConfigProperty(name = "ServerListAge", value = "0")
	@ConfigComments(comment = {
			"Displays server minimum age to the server name on character selection.",
			"Notes:",
			"	Accepted values: 0, 15, 18"
	})
	public static int SERVER_LIST_AGE;

	@ConfigProperty(name = "ServerGMOnly", value = "false")
	@ConfigComments(comment = {
			"If True, only accounts with GM access can enter the server."
	})
	public static boolean SERVER_GMONLY;

	@ConfigProperty(name = "GameGuardEnforce", value = "false")
	@ConfigComments(comment = {
			"Enforce gameguard for clients. Sends a gameguard query on character login."
	})
	public static boolean GAMEGUARD_ENFORCE;

	@ConfigProperty(name = "GameGuardProhibitAction", value = "false")
	@ConfigComments(comment = {
			"Don't allow player to perform trade, talk with npc, or move until gameguard reply is received."
	})
	public static boolean GAMEGUARD_PROHIBITACTION;

	@ConfigProperty(name = "LogChat", value = "false")
	@ConfigComments(comment = {
			"Logging settings. The following four settings, while enabled, will increase writing to your hard drive(s) considerably. Depending on the size of your server, the amount of players, and other factors, you may suffer a noticable performance hit."
	})
	public static boolean LOG_CHAT;

	@ConfigProperty(name = "LogAutoAnnouncements", value = "false")
	public static boolean LOG_AUTO_ANNOUNCEMENTS;

	@ConfigProperty(name = "LogItems", value = "false")
	public static boolean LOG_ITEMS;

	@ConfigProperty(name = "LogItemsSmallLog", value = "false")
	@ConfigComments(comment = {
			"Log only Adena and equippable items if LogItems is enabled"
	})
	public static boolean LOG_ITEMS_SMALL_LOG;

	@ConfigProperty(name = "LogItemEnchants", value = "false")
	public static boolean LOG_ITEM_ENCHANTS;

	@ConfigProperty(name = "LogSkillEnchants", value = "false")
	public static boolean LOG_SKILL_ENCHANTS;

	@ConfigProperty(name = "GMAudit", value = "false")
	public static boolean GMAUDIT;

	@ConfigProperty(name = "SkillCheckEnable", value = "false")
	@ConfigComments(comment = {
			"Check players for non-allowed skills"
	})
	public static boolean SKILL_CHECK_ENABLE;

	@ConfigProperty(name = "SkillCheckRemove", value = "false")
	@ConfigComments(comment = {
			"If true, remove invalid skills from player and database.",
			"Report only, if false."
	})
	public static boolean SKILL_CHECK_REMOVE;

	@ConfigProperty(name = "SkillCheckGM", value = "true")
	@ConfigComments(comment = {
			"Check also GM characters (only if SkillCheckEnable = True)"
	})
	public static boolean SKILL_CHECK_GM;

	@ConfigProperty(name = "InstanceDebug", value = "false")
	@ConfigComments(comment = {
			"Instances debugging"
	})
	public static boolean DEBUG_INSTANCES;

	@ConfigProperty(name = "HtmlActionCacheDebug", value = "false")
	@ConfigComments(comment = {
			"Html action cache debugging"
	})
	public static boolean HTML_ACTION_CACHE_DEBUG;

	@ConfigProperty(name = "PacketHandlerDebug", value = "false")
	@ConfigComments(comment = {
			"Packet handler debug output"
	})
	public static boolean PACKET_HANDLER_DEBUG;

	@ConfigProperty(name = "Developer", value = "false")
	public static boolean DEVELOPER;

	@ConfigProperty(name = "AltDevNoSpawns", value = "false")
	@ConfigComments(comment = {
			"Don't load spawntable."
	})
	public static boolean ALT_DEV_NO_SPAWNS;

	@ConfigProperty(name = "AltDevShowQuestsLoadInLogs", value = "false")
	@ConfigComments(comment = {
			"Show quests while loading them."
	})
	public static boolean ALT_DEV_SHOW_QUESTS_LOAD_IN_LOGS;

	@ConfigProperty(name = "AltDevShowScriptsLoadInLogs", value = "false")
	@ConfigComments(comment = {
			"Show scripts while loading them."
	})
	public static boolean ALT_DEV_SHOW_SCRIPTS_LOAD_IN_LOGS;

	@ConfigProperty(name = "UrgentPacketThreadCoreSize", value = "2")
	public static int IO_PACKET_THREAD_CORE_SIZE;

	@ConfigProperty(name = "DeadLockDetector", value = "true")
	@ConfigComments(comment = {
			"Dead Lock Detector (a separate thread for detecting deadlocks).",
			"For improved crash logs and automatic restart in deadlock case if enabled.",
			"Check interval is in seconds."
	})
	public static boolean DEADLOCK_DETECTOR;

	@ConfigProperty(name = "DeadLockCheckInterval", value = "20")
	public static int DEADLOCK_CHECK_INTERVAL;

	@ConfigProperty(name = "RestartOnDeadlock", value = "false")
	public static boolean RESTART_ON_DEADLOCK;

	@ConfigProperty(name = "AllowDiscardItem", value = "true")
	@ConfigComments(comment = {
			"Items on ground management.",
			"Allow players to drop items on the ground."
	})
	public static boolean ALLOW_DISCARDITEM;

	@ConfigProperty(name = "AutoDestroyDroppedItemAfter", value = "600")
	@ConfigComments(comment = {
			"Delete dropped reward items from world after a specified amount of seconds. Disabled = 0."
	})
	public static int AUTODESTROY_ITEM_AFTER;

	@ConfigProperty(name = "AutoDestroyHerbTime", value = "60")
	@ConfigComments(comment = {
			"Time in seconds after which dropped herb will be auto-destroyed"
	})
	public static int HERB_AUTO_DESTROY_TIME;

	@ConfigProperty(name = "ListOfProtectedItems", value = "0")
	@ConfigComments(comment = {
			"List of item id that will not be destroyed (separated by ",
			" like 57,5575,6673).",
			"Notes:",
			"	Make sure the lists do NOT CONTAIN trailing spaces or spaces between the numbers!",
			"	Items on this list will be protected regardless of the following options."
	})
	public static List<Integer> LIST_PROTECTED_ITEMS = new ArrayList<>();

	@ConfigProperty(name = "DatabaseCleanUp", value = "true")
	@ConfigComments(comment = {
			"Cleans up the server database on startup.",
			"The bigger the database is, the longer it will take to clean up the database(the slower the server will start).",
			"Sometimes this ends up with 0 elements cleaned up, and a lot of wasted time on the server startup.",
			"If you want a faster server startup, set this to 'false', but its recommended to clean up the database from time to time."
	})
	public static boolean DATABASE_CLEAN_UP;

	@ConfigProperty(name = "CharacterDataStoreInterval", value = "15")
	@ConfigComments(comment = {
			"This is the interval (in minutes), that the gameserver will update a players information such as location.",
			"The higher you set this number, there will be less character information saving so you will have less accessing of the database and your hard drive(s).",
			"The lower you set this number, there will be more frequent character information saving so you will have more access to the database and your hard drive(s).",
			"A value of 0 disables periodic saving.",
			"Independent of this setting the character is always saved after leaving the world."
	})
	public static int CHAR_DATA_STORE_INTERVAL;

	@ConfigProperty(name = "ClanVariablesStoreInterval", value = "15")
	@ConfigComments(comment = {
			"This is the interval (in minutes), that the game server will update a clan's variables information into the database.",
			"The higher you set this number, there will be less clan's variables information saving so you will have less accessing of the database and your hard drive(s).",
			"The lower you set this number, there will be more frequent clan's variables information saving so you will have more access to the database and your hard drive(s).",
			"A value of 0 disables periodic saving."
	})
	public static int CLAN_VARIABLES_STORE_INTERVAL;

	@ConfigProperty(name = "LazyItemsUpdate", value = "false")
	@ConfigComments(comment = {
			"This enables the server to only update items when saving the character.",
			"Enabling this greatly reduces DB usage and improves performance.",
			"WARNING: This option causes item loss during crashes."
	})
	public static boolean LAZY_ITEMS_UPDATE;

	@ConfigProperty(name = "UpdateItemsOnCharStore", value = "false")
	@ConfigComments(comment = {
			"When enabled, this forces (even if using lazy item updates) the items owned by the character to be updated into DB when saving its character."
	})
	public static boolean UPDATE_ITEMS_ON_CHAR_STORE;

	@ConfigProperty(name = "DestroyPlayerDroppedItem", value = "false")
	@ConfigComments(comment = {
			"Also delete from world misc. items dropped by players (all except equip-able items).",
			"Notes:",
			"	Works only if AutoDestroyDroppedItemAfter is greater than 0."
	})
	public static boolean DESTROY_DROPPED_PLAYER_ITEM;

	@ConfigProperty(name = "DestroyEquipableItem", value = "false")
	@ConfigComments(comment = {
			"Destroy dropped equippable items (armor, weapon, jewelry).",
			"Notes:",
			"	Works only if DestroyPlayerDroppedItem = True"
	})
	public static boolean DESTROY_EQUIPABLE_PLAYER_ITEM;

	@ConfigProperty(name = "SaveDroppedItem", value = "false")
	@ConfigComments(comment = {
			"Save dropped items into the database for restoring after restart."
	})
	public static boolean SAVE_DROPPED_ITEM;

	@ConfigProperty(name = "EmptyDroppedItemTableAfterLoad", value = "false")
	@ConfigComments(comment = {
			"Enable/Disable the emptying of the stored dropped items table after items are loaded into memory (safety setting).",
			"If the server crashed before saving items, on next start old items will be restored and players may already have picked up some of them so this will prevent duplicates."
	})
	public static boolean EMPTY_DROPPED_ITEM_TABLE_AFTER_LOAD;

	@ConfigProperty(name = "SaveDroppedItemInterval", value = "60")
	@ConfigComments(comment = {
			"Time interval in minutes to save in DB items on ground. Disabled = 0.",
			"Notes:",
			"	If SaveDroppedItemInterval is disabled, items will be saved into the database only at server shutdown."
	})
	public static int SAVE_DROPPED_ITEM_INTERVAL;

	@ConfigProperty(name = "ClearDroppedItemTable", value = "false")
	@ConfigComments(comment = {
			"Delete all saved items from the database on next restart?",
			"Notes:",
			"	Works only if SaveDroppedItem = False."
	})
	public static boolean CLEAR_DROPPED_ITEM_TABLE;

	@ConfigProperty(name = "AutoDeleteInvalidQuestData", value = "false")
	@ConfigComments(comment = {
			"Delete invalid quest from players."
	})
	public static boolean AUTODELETE_INVALID_QUEST_DATA;

	@ConfigProperty(name = "MultipleItemDrop", value = "true")
	@ConfigComments(comment = {
			"Allow creating multiple non-stackable items at one time?"
	})
	public static boolean MULTIPLE_ITEM_DROP;

	@ConfigProperty(name = "ForceInventoryUpdate", value = "false")
	@ConfigComments(comment = {
			"Forces full item inventory packet to be sent for any item change.",
			"Notes:",
			"	This can increase network traffic"
	})
	public static boolean FORCE_INVENTORY_UPDATE;

	@ConfigProperty(name = "LazyCache", value = "true")
	@ConfigComments(comment = {
			"True = Load html's into cache only on first time html is requested.",
			"False = Load all html's into cache on server startup."
	})
	public static boolean LAZY_CACHE;

	@ConfigProperty(name = "CacheCharNames", value = "true")
	@ConfigComments(comment = {
			"Cache all character names in to memory on server startup",
			"False - names are loaded from Db when they are requested",
			"True - decrease Db usage , increase memory consumption"
	})
	public static boolean CACHE_CHAR_NAMES;

	@ConfigProperty(name = "MinNPCAnimation", value = "10")
	@ConfigComments(comment = {
			"Minimum and maximum variables in seconds for npc animation delay.",
			"You must keep MinNPCAnimation < = MaxNPCAnimation."
	})
	public static int MIN_NPC_ANIMATION;

	@ConfigProperty(name = "MaxNPCAnimation", value = "20")
	public static int MAX_NPC_ANIMATION;

	@ConfigProperty(name = "MinMonsterAnimation", value = "5")
	public static int MIN_MONSTER_ANIMATION;

	@ConfigProperty(name = "MaxMonsterAnimation", value = "20")
	public static int MAX_MONSTER_ANIMATION;

	@ConfigProperty(name = "EnableFallingDamage", value = "true")
	@ConfigComments(comment = {
			"Allow characters to receive damage from falling.",
			"CoordSynchronize = 2 is recommended."
	})
	public static boolean ENABLE_FALLING_DAMAGE;

	@ConfigProperty(name = "GridsAlwaysOn", value = "false")
	@ConfigComments(comment = {
			"Grid options: Grids can turn themselves on and off.  This also affects the loading and processing of all AI tasks and (in the future) geodata within this grid.",
			"Turn on for a grid with a person in it is immediate, but it then turns on the 8 neighboring grids based on the specified number of seconds.",
			"Turn off for a grid and neighbors occurs after the specified number of seconds have passed during which a grid has had no players in or in any of its neighbors.",
			"The always on option allows to ignore all this and let all grids be active at all times (not suggested)."
	})
	public static boolean GRIDS_ALWAYS_ON;

	@ConfigProperty(name = "GridNeighborTurnOnTime", value = "1")
	public static int GRID_NEIGHBOR_TURNON_TIME;

	@ConfigProperty(name = "GridNeighborTurnOffTime", value = "90")
	public static int GRID_NEIGHBOR_TURNOFF_TIME;

	@ConfigProperty(name = "PeaceZoneMode", value = "0")
	@ConfigComments(comment = {
			"Peace Zone Modes:",
			"0 = Peace All the Time",
			"1 = PVP During Siege for siege participants",
			"2 = PVP All the Time"
	})
	public static int PEACE_ZONE_MODE;

	@ConfigProperty(name = "GlobalChat", value = "ON")
	@ConfigComments(comment = {
			"Global Chat.",
			"Available Options: ON, OFF, GM, GLOBAL"
	})
	public static String DEFAULT_GLOBAL_CHAT;

	@ConfigProperty(name = "TradeChat", value = "ON")
	@ConfigComments(comment = {
			"Trade Chat.",
			"Available Options: ON, OFF, GM, GLOBAL"
	})
	public static String DEFAULT_TRADE_CHAT;

	@ConfigProperty(name = "MinimumChatLevel", value = "20")
	@ConfigComments(comment = {
			"Minimum level for chat, 0 = disable"
	})
	public static int MINIMUM_CHAT_LEVEL;

	@ConfigProperty(name = "MinimumTradeChatLevel", value = "84")
	@ConfigComments(comment = {
			"Minimum level for trade chat, 0 = disable"
	})
	public static int MINIMUM_TRADE_CHAT_LEVEL;

	@ConfigProperty(name = "AllowWarehouse", value = "true")
	@ConfigComments(comment = {
			"If you are experiencing problems with Warehouse transactions, feel free to disable them here."
	})
	public static boolean ALLOW_WAREHOUSE;

	@ConfigProperty(name = "WarehouseCache", value = "false")
	@ConfigComments(comment = {
			"Enable Warehouse Cache. If warehouse is not used will server clear memory used by this warehouse."
	})
	public static boolean WAREHOUSE_CACHE;

	@ConfigProperty(name = "WarehouseCacheTime", value = "15")
	@ConfigComments(comment = {
			"How long warehouse should be stored in memory."
	})
	public static int WAREHOUSE_CACHE_TIME;

	@ConfigProperty(name = "AllowRefund", value = "true")
	public static boolean ALLOW_REFUND;

	@ConfigProperty(name = "AllowMail", value = "true")
	public static boolean ALLOW_MAIL;

	@ConfigProperty(name = "AllowAttachments", value = "true")
	public static boolean ALLOW_ATTACHMENTS;

	@ConfigProperty(name = "AllowWear", value = "true")
	@ConfigComments(comment = {
			"If True player can try on weapon and armor in shop."
	})
	public static boolean ALLOW_WEAR;

	@ConfigProperty(name = "WearDelay", value = "5")
	public static int WEAR_DELAY;

	@ConfigProperty(name = "WearPrice", value = "10")
	@ConfigComments(comment = {
			"Adena cost to try on an item."
	})
	public static int WEAR_PRICE;

	@ConfigProperty(name = "DefaultFinishTime", value = "5")
	@ConfigComments(comment = {
			"When is instance finished, is set time to destruction currency instance.",
			"Time in minutes."
	})
	public static int INSTANCE_FINISH_TIME;

	@ConfigProperty(name = "RestorePlayerInstance", value = "false")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"Instances",
			"---------------------------------------------------------------------------",
			"Restores the player to their previous instance (ie. an instanced area/dungeon) on EnterWorld."
	})
	public static boolean RESTORE_PLAYER_INSTANCE;

	@ConfigProperty(name = "EjectDeadPlayerTime", value = "1")
	@ConfigComments(comment = {
			"When a player dies, is removed from instance after a fixed period of time.",
			"Time in minutes."
	})
	public static int EJECT_DEAD_PLAYER_TIME;

	@ConfigProperty(name = "AllowLottery", value = "true")
	public static boolean ALLOW_LOTTERY;

	@ConfigProperty(name = "AllowRace", value = "true")
	public static boolean ALLOW_RACE;

	@ConfigProperty(name = "AllowWater", value = "true")
	public static boolean ALLOW_WATER;

	@ConfigProperty(name = "AllowRentPet", value = "false")
	@ConfigComments(comment = {
			"Enable pets for rent (wyvern & strider) from pet managers."
	})
	public static boolean ALLOW_RENTPET;

	@ConfigProperty(name = "AllowFishing", value = "true")
	public static boolean ALLOWFISHING;

	@ConfigProperty(name = "AllowBoat", value = "true")
	public static boolean ALLOW_BOAT;

	@ConfigProperty(name = "BoatBroadcastRadius", value = "20000")
	@ConfigComments(comment = {
			"Boat broadcast radius.",
			"If players getting annoyed by boat shouts then radius can be decreased."
	})
	public static int BOAT_BROADCAST_RADIUS;

	@ConfigProperty(name = "AllowCursedWeapons", value = "true")
	public static boolean ALLOW_CURSED_WEAPONS;

	@ConfigProperty(name = "AllowManor", value = "true")
	public static boolean ALLOW_MANOR;

	@ConfigProperty(name = "ShowServerNews", value = "false")
	@ConfigComments(comment = {
			"Show 'data/html/servnews.htm' when a character enters world."
	})
	public static boolean SERVER_NEWS;

	@ConfigProperty(name = "EnableCommunityBoard", value = "true")
	@ConfigComments(comment = {
			"Enable the Community Board."
	})
	public static boolean ENABLE_COMMUNITY_BOARD;

	@ConfigProperty(name = "BBSDefault", value = "_bbshome")
	@ConfigComments(comment = {
			"Default Community Board page."
	})
	public static String BBS_DEFAULT;

	@ConfigProperty(name = "UseChatFilter", value = "false")
	@ConfigComments(comment = {
			"Enable chat filter"
	})
	public static boolean USE_SAY_FILTER;

	@ConfigProperty(name = "ChatFilterChars", value = "^_^")
	@ConfigComments(comment = {
			"Replace filter words with following chars"
	})
	public static String CHAT_FILTER_CHARS;

	@ConfigProperty(name = "BanChatChannels", value = "GENERAL,SHOUT,WORLD,TRADE,HERO_VOICE")
	@ConfigComments(comment = {
			"Banchat for channels, split \";\"",
			"GENERAL (white)",
			"SHOUT (!)",
			"TELL (\")",
			"PARTY (#)",
			"CLAN (@)",
			"GM (//gmchat)",
			"PETITION_PLAYER (*)",
			"PETITION_GM (*)",
			"TRADE (+)",
			"ALLIANCE ($)",
			"ANNOUNCEMENT",
			"BOAT",
			"FRIEND",
			"MSNCHAT",
			"PARTYMATCH_ROOM",
			"PARTYROOM_COMMANDER (Yellow)",
			"PARTYROOM_ALL (Red)",
			"HERO_VOICE (%)",
			"CRITICAL_ANNOUNCE",
			"SCREEN_ANNOUNCE",
			"BATTLEFIELD",
			"MPCC_ROOM",
			"NPC_GENERAL",
			"NPC_SHOUT",
			"NEW_TELL",
			"WORLD (&)",
	})
	public static List<ChatType> BAN_CHAT_CHANNELS = new ArrayList<>();

	@ConfigProperty(name = "WorldChatMinLevel", value = "94")
	@ConfigComments(comment = {
			"---------------------------------------------------------------------------",
			"World chat settings",
			"---------------------------------------------------------------------------",
			"The minimum level to use this chat"
	})
	public static int WORLD_CHAT_MIN_LEVEL;

	@ConfigProperty(name = "WorldChatPointsPerDay", value = "3")
	@ConfigComments(comment = {
			"The amount of points player will have at his disposal every day"
	})
	public static int WORLD_CHAT_POINTS_PER_DAY;

	@ConfigProperty(name = "WorldChatInterval", value = "20000")
	public static long WORLD_CHAT_INTERVAL;

	@ConfigProperty(name = "AltManorRefreshTime", value = "20")
	@ConfigComments(comment = {
			"Manor refresh time in military hours."
	})
	public static int ALT_MANOR_REFRESH_TIME;

	@ConfigProperty(name = "AltManorRefreshMin", value = "00")
	@ConfigComments(comment = {
			"Manor refresh time (minutes)."
	})
	public static int ALT_MANOR_REFRESH_MIN;

	@ConfigProperty(name = "AltManorApproveTime", value = "4")
	@ConfigComments(comment = {
			"Manor period approve time in military hours."
	})
	public static int ALT_MANOR_APPROVE_TIME;

	@ConfigProperty(name = "AltManorApproveMin", value = "30")
	@ConfigComments(comment = {
			"Manor period approve time (minutes)."
	})
	public static int ALT_MANOR_APPROVE_MIN;

	@ConfigProperty(name = "AltManorMaintenanceMin", value = "6")
	@ConfigComments(comment = {
			"Manor maintenance time (minutes)."
	})
	public static int ALT_MANOR_MAINTENANCE_MIN;

	@ConfigProperty(name = "AltManorSaveAllActions", value = "false")
	@ConfigComments(comment = {
			"Manor Save Type.",
			"True = Save data into the database after every action"
	})
	public static boolean ALT_MANOR_SAVE_ALL_ACTIONS;

	@ConfigProperty(name = "AltManorSavePeriodRate", value = "2")
	@ConfigComments(comment = {
			"Manor Save Period (used only if AltManorSaveAllActions = False)"
	})
	public static int ALT_MANOR_SAVE_PERIOD_RATE;

	@ConfigProperty(name = "AltLotteryPrize", value = "50000")
	@ConfigComments(comment = {
			"Initial Lottery prize."
	})
	public static long ALT_LOTTERY_PRIZE;

	@ConfigProperty(name = "AltLotteryTicketPrice", value = "2000")
	@ConfigComments(comment = {
			"Lottery Ticket Price"
	})
	public static long ALT_LOTTERY_TICKET_PRICE;

	@ConfigProperty(name = "AltLottery5NumberRate", value = "0.6")
	@ConfigComments(comment = {
			"What part of jackpot amount should receive characters who pick 5 wining numbers"
	})
	public static float ALT_LOTTERY_5_NUMBER_RATE;

	@ConfigProperty(name = "AltLottery4NumberRate", value = "0.2")
	@ConfigComments(comment = {
			"What part of jackpot amount should receive characters who pick 4 wining numbers"
	})
	public static float ALT_LOTTERY_4_NUMBER_RATE;

	@ConfigProperty(name = "AltLottery3NumberRate", value = "0.2")
	@ConfigComments(comment = {
			"What part of jackpot amount should receive characters who pick 3 wining numbers"
	})
	public static float ALT_LOTTERY_3_NUMBER_RATE;

	@ConfigProperty(name = "AltLottery2and1NumberPrize", value = "200")
	@ConfigComments(comment = {
			"How much Adena receive characters who pick two or less of the winning number"
	})
	public static long ALT_LOTTERY_2_AND_1_NUMBER_PRIZE;

	@ConfigProperty(name = "AltItemAuctionEnabled", value = "true")
	@ConfigComments(comment = {
			""
	})
	public static boolean ALT_ITEM_AUCTION_ENABLED;

	@ConfigProperty(name = "AltItemAuctionExpiredAfter", value = "14")
	@ConfigComments(comment = {
			"Number of days before auction cleared from database with all bids."
	})
	public static int ALT_ITEM_AUCTION_EXPIRED_AFTER;

	@ConfigProperty(name = "AltItemAuctionTimeExtendsOnBid", value = "0")
	@ConfigComments(comment = {
			"Auction extends to specified amount of seconds if one or more new bids added.",
			"By default auction extends only two times, by 5 and 3 minutes, this custom value used after it.",
			"Values higher than 60s is not recommended."
	})
	public static long ALT_ITEM_AUCTION_TIME_EXTENDS_ON_BID;

	@ConfigProperty(name = "TimeOfAttack", value = "50")
	public static int FS_TIME_ATTACK;

	@ConfigProperty(name = "TimeOfCoolDown", value = "5")
	public static int FS_TIME_COOLDOWN;

	@ConfigProperty(name = "TimeOfEntry", value = "3")
	public static int FS_TIME_ENTRY;

	@ConfigProperty(name = "TimeOfWarmUp", value = "2")
	public static int FS_TIME_WARMUP;

	@ConfigProperty(name = "NumberOfNecessaryPartyMembers", value = "4")
	public static int FS_PARTY_MEMBER_COUNT;

	@ConfigProperty(name = "RiftMinPartySize", value = "2")
	@ConfigComments(comment = {
			"Minimal party size to enter rift. Min = 2, Max = 9.",
			"If while inside the rift, the party becomes smaller, all members will be teleported back."
	})
	public static int RIFT_MIN_PARTY_SIZE;

	@ConfigProperty(name = "RiftSpawnDelay", value = "10000")
	@ConfigComments(comment = {
			"Time in ms the party has to wait until the mobs spawn when entering a room. C4 retail: 10s"
	})
	public static int RIFT_SPAWN_DELAY;

	@ConfigProperty(name = "MaxRiftJumps", value = "4")
	@ConfigComments(comment = {
			"Number of maximum jumps between rooms allowed, after this time party will be teleported back"
	})
	public static int RIFT_MAX_JUMPS;

	@ConfigProperty(name = "AutoJumpsDelayMin", value = "480")
	@ConfigComments(comment = {
			"Time between automatic jumps in seconds"
	})
	public static int RIFT_AUTO_JUMPS_TIME_MIN;

	@ConfigProperty(name = "AutoJumpsDelayMax", value = "600")
	public static int RIFT_AUTO_JUMPS_TIME_MAX;

	@ConfigProperty(name = "BossRoomTimeMultiply", value = "1.5")
	@ConfigComments(comment = {
			"Time Multiplier for stay in the boss room"
	})
	public static float RIFT_BOSS_ROOM_TIME_MUTIPLY;

	@ConfigProperty(name = "RecruitCost", value = "18")
	@ConfigComments(comment = {
			"Cost in dimension fragments to enter the rift, each party member must own this amount"
	})
	public static int RIFT_ENTER_COST_RECRUIT;

	@ConfigProperty(name = "SoldierCost", value = "21")
	public static int RIFT_ENTER_COST_SOLDIER;

	@ConfigProperty(name = "OfficerCost", value = "24")
	public static int RIFT_ENTER_COST_OFFICER;

	@ConfigProperty(name = "CaptainCost", value = "27")
	public static int RIFT_ENTER_COST_CAPTAIN;

	@ConfigProperty(name = "CommanderCost", value = "30")
	public static int RIFT_ENTER_COST_COMMANDER;

	@ConfigProperty(name = "HeroCost", value = "33")
	public static int RIFT_ENTER_COST_HERO;

	@ConfigProperty(name = "DefaultPunish", value = "KICK")
	@ConfigComments(comment = {
			"Player punishment for illegal actions:",
			"BROADCAST - broadcast warning to gms only",
			"KICK - kick player(default)",
			"KICKBAN - kick & ban player",
			"JAIL - jail player (define minutes of jail with param: 0 = infinite)"
	})
	public static IllegalActionPunishmentType DEFAULT_PUNISH;

	@ConfigProperty(name = "DefaultPunishParam", value = "0")
	@ConfigComments(comment = {
			"This setting typically specifies the duration of the above punishment."
	})
	public static int DEFAULT_PUNISH_PARAM;

	@ConfigProperty(name = "OnlyGMItemsFree", value = "true")
	@ConfigComments(comment = {
			"Apply default punish if player buy items for zero Adena."
	})
	public static boolean ONLY_GM_ITEMS_FREE;

	@ConfigProperty(name = "JailIsPvp", value = "false")
	@ConfigComments(comment = {
			"Jail is a PvP zone."
	})
	public static boolean JAIL_IS_PVP;

	@ConfigProperty(name = "JailDisableChat", value = "true")
	@ConfigComments(comment = {
			"Disable all chat in jail (except normal one)"
	})
	public static boolean JAIL_DISABLE_CHAT;

	@ConfigProperty(name = "JailDisableTransaction", value = "false")
	@ConfigComments(comment = {
			"Disable all transaction in jail",
			"Trade/Store/Drop"
	})
	public static boolean JAIL_DISABLE_TRANSACTION;

	@ConfigProperty(name = "CustomSpawnlistTable", value = "false")
	public static boolean CUSTOM_SPAWNLIST_TABLE;

	@ConfigProperty(name = "CustomNpcData", value = "false")
	public static boolean CUSTOM_NPC_DATA;

	@ConfigProperty(name = "CustomTeleportTable", value = "false")
	public static boolean CUSTOM_TELEPORT_TABLE;

	@ConfigProperty(name = "CustomNpcBufferTables", value = "false")
	public static boolean CUSTOM_NPCBUFFER_TABLES;

	@ConfigProperty(name = "CustomSkillsLoad", value = "false")
	public static boolean CUSTOM_SKILLS_LOAD;

	@ConfigProperty(name = "CustomItemsLoad", value = "false")
	public static boolean CUSTOM_ITEMS_LOAD;

	@ConfigProperty(name = "CustomMultisellLoad", value = "false")
	public static boolean CUSTOM_MULTISELL_LOAD;

	@ConfigProperty(name = "CustomBuyListLoad", value = "false")
	public static boolean CUSTOM_BUYLIST_LOAD;

	@ConfigProperty(name = "AltBirthdayGift", value = "22187")
	@ConfigComments(comment = {
			"Gift sent with Mail System"
	})
	public static int ALT_BIRTHDAY_GIFT;

	@ConfigProperty(name = "AltBirthdayMailSubject", value = "Happy Birthday!")
	@ConfigComments(comment = {
			"Mail Subject"
	})
	public static String ALT_BIRTHDAY_MAIL_SUBJECT;

	@ConfigProperty(name = "AltBirthdayMailText", value = "Hello Adventurer!! Seeing as you're one year older now, I thought I would send you some birthday cheer :) Please find your birthday pack attached. May these gifts bring you joy and happiness on this very special day.\\n\\nSincerely, Alegria")
	@ConfigComments(comment = {
			"Mail Content",
			"$c1: Player name",
			"$s1: Age"
	})
	public static String ALT_BIRTHDAY_MAIL_TEXT;

	@ConfigProperty(name = "EnableBlockCheckerEvent", value = "true")
	@ConfigComments(comment = {
			"Enable the Handy's Block Checker event"
	})
	public static boolean ENABLE_BLOCK_CHECKER_EVENT;

	@ConfigProperty(name = "BlockCheckerMinTeamMembers", value = "2")
	@ConfigComments(comment = {
			"Minimum number of members on each team before",
			"be able to start the event",
			"Min: 1",
			"Max: 6",
			"Retail: 2"
	})
	public static int MIN_BLOCK_CHECKER_TEAM_MEMBERS;

	@ConfigProperty(name = "HBCEFairPlay", value = "true")
	@ConfigComments(comment = {
			"Fair play",
			"Players can choose what team to play. However, by",
			"enabling this property to true, the teams will be",
			"balanced in the teleport to the arena"
	})
	public static boolean HBCE_FAIR_PLAY;

	@ConfigProperty(name = "NormalEnchantCostMultipiler", value = "1")
	@ConfigComments(comment = {
			"Enchant Skill Details Settings"
	})
	public static int NORMAL_ENCHANT_COST_MULTIPLIER;

	@ConfigProperty(name = "SafeEnchantCostMultipiler", value = "5")
	public static int SAFE_ENCHANT_COST_MULTIPLIER;

	@ConfigProperty(name = "EnableBotReportButton", value = "true")
	@ConfigComments(comment = {
			"Enable the bot report button on the desired game servers."
	})
	public static boolean BOTREPORT_ENABLE;

	@ConfigProperty(name = "BotReportPointsResetHour", value = "00,00")
	@ConfigComments(comment = {
			"Report points restart hour. Format: HH:MM ( PM mode, 24 hours clock)"
	})
	public static String[] BOTREPORT_RESETPOINT_HOUR;

	@ConfigProperty(name = "BotReportDelay", value = "30")
	@ConfigComments(comment = {
			"Delay between reports from the same player (in minutes)"
	})
	public static long BOTREPORT_REPORT_DELAY;

	@ConfigProperty(name = "AllowReportsFromSameClanMembers", value = "false")
	@ConfigComments(comment = {
			"Allow players from the same clan to report the same bot"
	})
	public static boolean BOTREPORT_ALLOW_REPORTS_FROM_SAME_CLAN_MEMBERS;

	@ConfigProperty(name = "AccountVariablesClassWhitelist")
	@ConfigComments(comment = {
			"An array of fully qualified classe names that are allowed to present in AccountVariables's database",
			"Used to prevent from code injection",
			"Example: org.l2junity.gameserver.model.holders.ItemHolder,org.l2junity.gameserver.model.holders.SkillHolder"
	})
	public static String[] ACCOUNT_VARIABLES_CLASS_WHITELIST;

	@ConfigProperty(name = "Debug", value = "false")
	public static boolean DEBUG;

	@ConfigAfterLoad
	@SuppressWarnings("unused")
	protected void afterLoad() {
		SERVER_LIST_TYPE = getServerTypeId(_SERVER_LIST_TYPE.split(","));

		DeadlockDetector.ALLOWED = DEADLOCK_DETECTOR;
		DeadlockDetector.RESTART_ON_DEADLOCK = RESTART_ON_DEADLOCK;
		DeadlockDetector.CHECK_INTERVAL = DEADLOCK_CHECK_INTERVAL;

		if (RESTART_ON_DEADLOCK) {
			DeadlockDetector.RESTART_EVENT = () ->
			{
				Broadcast.toAllOnlinePlayers("Server has stability issues - restarting now.");
			};
		}
	}

	public static int getServerTypeId(String[] serverTypes) {
		int serverType = 0;
		for (String cType : serverTypes) {
			switch (cType.trim().toLowerCase()) {
				case "normal":
					serverType |= 0x01;
					break;
				case "relax":
					serverType |= 0x02;
					break;
				case "test":
					serverType |= 0x04;
					break;
				case "broad":
					serverType |= 0x08;
					break;
				case "restricted":
					serverType |= 0x10;
					break;
				case "event":
					serverType |= 0x20;
					break;
				case "free":
					serverType |= 0x40;
					break;
				case "world":
					serverType |= 0x100;
					break;
				case "new":
					serverType |= 0x200;
					break;
				case "classic":
					serverType |= 0x400;
					break;
			}
		}
		return serverType;
	}
}
