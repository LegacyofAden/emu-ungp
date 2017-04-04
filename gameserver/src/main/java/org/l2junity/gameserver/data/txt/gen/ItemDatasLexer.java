// Generated from org\l2junity\gameserver\data\txt\gen\ItemDatas.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Utils;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ItemDatasLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
			T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
			T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
			T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
			T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
			T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, T__44 = 45,
			T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, T__49 = 50, T__50 = 51, T__51 = 52,
			T__52 = 53, T__53 = 54, T__54 = 55, T__55 = 56, T__56 = 57, T__57 = 58, T__58 = 59,
			T__59 = 60, T__60 = 61, T__61 = 62, T__62 = 63, T__63 = 64, T__64 = 65, T__65 = 66,
			T__66 = 67, T__67 = 68, T__68 = 69, T__69 = 70, T__70 = 71, T__71 = 72, T__72 = 73,
			T__73 = 74, T__74 = 75, T__75 = 76, T__76 = 77, T__77 = 78, T__78 = 79, T__79 = 80,
			T__80 = 81, T__81 = 82, T__82 = 83, T__83 = 84, T__84 = 85, T__85 = 86, T__86 = 87,
			T__87 = 88, T__88 = 89, T__89 = 90, T__90 = 91, T__91 = 92, T__92 = 93, T__93 = 94,
			T__94 = 95, T__95 = 96, T__96 = 97, T__97 = 98, T__98 = 99, T__99 = 100, T__100 = 101,
			T__101 = 102, T__102 = 103, T__103 = 104, T__104 = 105, T__105 = 106, T__106 = 107,
			T__107 = 108, T__108 = 109, T__109 = 110, T__110 = 111, T__111 = 112, T__112 = 113,
			T__113 = 114, T__114 = 115, T__115 = 116, T__116 = 117, T__117 = 118, T__118 = 119,
			T__119 = 120, T__120 = 121, T__121 = 122, T__122 = 123, T__123 = 124, T__124 = 125,
			T__125 = 126, T__126 = 127, T__127 = 128, T__128 = 129, WEAPON = 130, ARMOR = 131,
			ETCITEM = 132, ASSET = 133, ACCESSARY = 134, QUESTITEM = 135, POTION = 136, ARROW = 137,
			SCRL_ENCHANT_AM = 138, SCRL_ENCHANT_WP = 139, SCROLL = 140, MATERIAL = 141, RECIPE = 142,
			PET_COLLAR = 143, CASTLE_GUARD = 144, LOTTO = 145, RACE_TICKET = 146, DYE = 147,
			SEED = 148, SEED2 = 149, CROP = 150, MATURECROP = 151, HARVEST = 152, TICKET_OF_LORD = 153,
			LURE = 154, BLESS_SCRL_ENCHANT_AM = 155, BLESS_SCRL_ENCHANT_WP = 156, COUPON = 157,
			ELIXIR = 158, SCRL_ENCHANT_ATTR = 159, SCRL_INC_ENCHANT_PROP_WP = 160, SCRL_INC_ENCHANT_PROP_AM = 161,
			BOLT = 162, ANCIENT_CRYSTAL_ENCHANT_WP = 163, ANCIENT_CRYSTAL_ENCHANT_AM = 164,
			RUNE_SELECT = 165, RUNE = 166, ACTION_NONE = 167, ACTION_EQUIP = 168, ACTION_PEEL = 169,
			ACTION_SKILL_REDUCE = 170, ACTION_SHOW_HTML = 171, ACTION_SOULSHOT = 172, ACTION_RECIPE = 173,
			ACTION_SKILL_MAINTAIN = 174, ACTION_SPIRITSHOT = 175, ACTION_DICE = 176, ACTION_CALC = 177,
			ACTION_SEED = 178, ACTION_HARVEST = 179, ACTION_CAPSULE = 180, ACTION_XMAS_OPEN = 181,
			ACTION_SHOW_SSQ_STATUS = 182, ACTION_FISHINGSHOT = 183, ACTION_SUMMON_SOULSHOT = 184,
			ACTION_SUMMON_SPIRITSHOT = 185, ACTION_CALL_SKILL = 186, ACTION_SHOW_ADVENTURER_GUIDE_BOOK = 187,
			ACTION_KEEP_EXP = 188, ACTION_CREATE_MPCC = 189, ACTION_NICK_COLOR = 190, ACTION_HIDE_NAME = 191,
			ACTION_START_QUEST = 192, CONSUME_TYPE_NORMAL = 193, CONSUME_TYPE_STACKABLE = 194,
			CONSUME_TYPE_ASSET = 195, CRYSTAL_FREE = 196, EVENT = 197, D = 198, C = 199, B = 200,
			A = 201, S80 = 202, S84 = 203, S = 204, CATEGORY = 205, BOOLEAN = 206, INTEGER = 207,
			DOUBLE = 208, FIRE = 209, WATER = 210, EARTH = 211, WIND = 212, UNHOLY = 213, HOLY = 214,
			SEMICOLON = 215, NONE = 216, FAIRY = 217, ANIMAL = 218, HUMANOID = 219, PLANT = 220,
			UNDEAD = 221, CONSTRUCT = 222, BEAST = 223, BUG = 224, ELEMENTAL = 225, DEMONIC = 226,
			GIANT = 227, DRAGON = 228, DIVINE = 229, SUMMON = 230, PET = 231, HOLYTHING = 232,
			DWARF = 233, MERCHANT = 234, ELF = 235, KAMAEL = 236, ORC = 237, MERCENARY = 238,
			HUMAN = 239, BOSS = 240, ZZOLDAGU = 241, WORLD_TRAP = 242, MONRACE = 243, DARKELF = 244,
			GUARD = 245, TELEPORTER = 246, WAREHOUSE_KEEPER = 247, WARRIOR = 248, CITIZEN = 249,
			TREASURE = 250, FIELDBOSS = 251, BLACKSMITH = 252, GUILD_MASTER = 253, GUILD_COACH = 254,
			SWORD = 255, BLUNT = 256, BOW = 257, POLE = 258, DAGGER = 259, DUAL = 260, FIST = 261,
			DUALFIST = 262, FISHINGROD = 263, RAPIER = 264, ANCIENTSWORD = 265, CROSSBOW = 266,
			FLAG = 267, OWNTHING = 268, DUALDAGGER = 269, ETC = 270, LIGHT = 271, HEAVY = 272,
			MAGIC = 273, SIGIL = 274, RHAND = 275, LRHAND = 276, LHAND = 277, CHEST = 278, LEGS = 279,
			FEET = 280, HEAD = 281, GLOVES = 282, ONEPIECE = 283, REAR = 284, LEAR = 285, RFINGER = 286,
			LFINGER = 287, NECK = 288, BACK = 289, UNDERWEAR = 290, HAIR = 291, HAIR2 = 292, HAIRALL = 293,
			ALLDRESS = 294, RBRACELET = 295, LBRACELET = 296, WAIST = 297, DECO1 = 298, STEEL = 299,
			FINE_STEEL = 300, WOOD = 301, CLOTH = 302, LEATHER = 303, BONE = 304, BRONZE = 305,
			ORIHARUKON = 306, MITHRIL = 307, DAMASCUS = 308, ADAMANTAITE = 309, BLOOD_STEEL = 310,
			PAPER = 311, GOLD = 312, LIQUID = 313, FISH = 314, SILVER = 315, CHRYSOLITE = 316,
			CRYSTAL = 317, HORN = 318, SCALE_OF_DRAGON = 319, COTTON = 320, DYESTUFF = 321,
			COBWEB = 322, RUNE_XP = 323, RUNE_SP = 324, RUNE_REMOVE_PENALTY = 325, NAME = 326,
			WS = 327, LINE_COMMENT = 328, STAR_COMMENT = 329;
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16",
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24",
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32",
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40",
			"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "T__48",
			"T__49", "T__50", "T__51", "T__52", "T__53", "T__54", "T__55", "T__56",
			"T__57", "T__58", "T__59", "T__60", "T__61", "T__62", "T__63", "T__64",
			"T__65", "T__66", "T__67", "T__68", "T__69", "T__70", "T__71", "T__72",
			"T__73", "T__74", "T__75", "T__76", "T__77", "T__78", "T__79", "T__80",
			"T__81", "T__82", "T__83", "T__84", "T__85", "T__86", "T__87", "T__88",
			"T__89", "T__90", "T__91", "T__92", "T__93", "T__94", "T__95", "T__96",
			"T__97", "T__98", "T__99", "T__100", "T__101", "T__102", "T__103", "T__104",
			"T__105", "T__106", "T__107", "T__108", "T__109", "T__110", "T__111",
			"T__112", "T__113", "T__114", "T__115", "T__116", "T__117", "T__118",
			"T__119", "T__120", "T__121", "T__122", "T__123", "T__124", "T__125",
			"T__126", "T__127", "T__128", "WEAPON", "ARMOR", "ETCITEM", "ASSET", "ACCESSARY",
			"QUESTITEM", "POTION", "ARROW", "SCRL_ENCHANT_AM", "SCRL_ENCHANT_WP",
			"SCROLL", "MATERIAL", "RECIPE", "PET_COLLAR", "CASTLE_GUARD", "LOTTO",
			"RACE_TICKET", "DYE", "SEED", "SEED2", "CROP", "MATURECROP", "HARVEST",
			"TICKET_OF_LORD", "LURE", "BLESS_SCRL_ENCHANT_AM", "BLESS_SCRL_ENCHANT_WP",
			"COUPON", "ELIXIR", "SCRL_ENCHANT_ATTR", "SCRL_INC_ENCHANT_PROP_WP", "SCRL_INC_ENCHANT_PROP_AM",
			"BOLT", "ANCIENT_CRYSTAL_ENCHANT_WP", "ANCIENT_CRYSTAL_ENCHANT_AM", "RUNE_SELECT",
			"RUNE", "ACTION_NONE", "ACTION_EQUIP", "ACTION_PEEL", "ACTION_SKILL_REDUCE",
			"ACTION_SHOW_HTML", "ACTION_SOULSHOT", "ACTION_RECIPE", "ACTION_SKILL_MAINTAIN",
			"ACTION_SPIRITSHOT", "ACTION_DICE", "ACTION_CALC", "ACTION_SEED", "ACTION_HARVEST",
			"ACTION_CAPSULE", "ACTION_XMAS_OPEN", "ACTION_SHOW_SSQ_STATUS", "ACTION_FISHINGSHOT",
			"ACTION_SUMMON_SOULSHOT", "ACTION_SUMMON_SPIRITSHOT", "ACTION_CALL_SKILL",
			"ACTION_SHOW_ADVENTURER_GUIDE_BOOK", "ACTION_KEEP_EXP", "ACTION_CREATE_MPCC",
			"ACTION_NICK_COLOR", "ACTION_HIDE_NAME", "ACTION_START_QUEST", "CONSUME_TYPE_NORMAL",
			"CONSUME_TYPE_STACKABLE", "CONSUME_TYPE_ASSET", "CRYSTAL_FREE", "EVENT",
			"D", "C", "B", "A", "S80", "S84", "S", "CATEGORY", "BOOLEAN", "INTEGER",
			"DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", "DECIMAL",
			"MINUS", "BINARY_DIGIT", "DIGIT", "NON_ZERO_DIGIT", "ZERO_DIGIT", "SEMICOLON",
			"NONE", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", "CONSTRUCT",
			"BEAST", "BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", "DIVINE", "SUMMON",
			"PET", "HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", "ORC", "MERCENARY",
			"HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", "MONRACE", "DARKELF", "GUARD",
			"TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", "CITIZEN", "TREASURE", "FIELDBOSS",
			"BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", "SWORD", "BLUNT", "BOW",
			"POLE", "DAGGER", "DUAL", "FIST", "DUALFIST", "FISHINGROD", "RAPIER",
			"ANCIENTSWORD", "CROSSBOW", "FLAG", "OWNTHING", "DUALDAGGER", "ETC", "LIGHT",
			"HEAVY", "MAGIC", "SIGIL", "RHAND", "LRHAND", "LHAND", "CHEST", "LEGS",
			"FEET", "HEAD", "GLOVES", "ONEPIECE", "REAR", "LEAR", "RFINGER", "LFINGER",
			"NECK", "BACK", "UNDERWEAR", "HAIR", "HAIR2", "HAIRALL", "ALLDRESS", "RBRACELET",
			"LBRACELET", "WAIST", "DECO1", "STEEL", "FINE_STEEL", "WOOD", "CLOTH",
			"LEATHER", "BONE", "BRONZE", "ORIHARUKON", "MITHRIL", "DAMASCUS", "ADAMANTAITE",
			"BLOOD_STEEL", "PAPER", "GOLD", "LIQUID", "FISH", "SILVER", "CHRYSOLITE",
			"CRYSTAL", "HORN", "SCALE_OF_DRAGON", "COTTON", "DYESTUFF", "COBWEB",
			"RUNE_XP", "RUNE_SP", "RUNE_REMOVE_PENALTY", "NAME", "WS", "LINE_COMMENT",
			"STAR_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
			null, "'set_begin'", "'set_end'", "'slot_chest'", "'='", "'{'", "'}'",
			"'slot_legs'", "'slot_feet'", "'slot_head'", "'slot_gloves'", "'slot_lhand'",
			"'slot_additional'", "'set_skill'", "'set_effect_skill'", "'set_additional_effect_skill'",
			"'set_additional2_condition'", "'set_additional2_effect_skill'", "'str_inc'",
			"'con_inc'", "'dex_inc'", "'int_inc'", "'men_inc'", "'wit_inc'", "'item_begin'",
			"'item_end'", "'can_move'", "'is_premium'", "'is_olympiad_can_use'", "'use_condition'",
			"'equip_condition'", "'item_equip_option'", "'ec_race'", "'uc_race'",
			"'uc_transmode_exclude'", "'ec_category'", "'uc_category'", "'ec_hero'",
			"'ec_castle'", "'ec_sex'", "'ec_agit'", "'ec_castle_num'", "'ec_academy'",
			"'ec_social_class'", "'uc_level'", "'uc_requiredlevel'", "'ec_requiredlevel'",
			"'uc_restart_point'", "'ec_nobless'", "'ec_clan_leader'", "'ec_subjob'",
			"'uc_transmode_include'", "'uc_inzone_num'", "'ec_inzone_num'", "'ec_chao'",
			"'uc_in_residence_siege_field'", "'ec_fortress'", "'ec_agit_num'", "'for_npc'",
			"'unequip_skill'", "'html'", "'base_attribute_defend'", "'category'",
			"'enchant_enable'", "'elemental_enable'", "'enchanted'", "'mp_consume'",
			"'magical_damage'", "'durability'", "'damaged'", "'magic_weapon'", "'physical_defense'",
			"'magical_defense'", "'mp_bonus'", "'weapon_type'", "'is_trade'", "'is_drop'",
			"'is_destruct'", "'is_private_store'", "'keep_type'", "'physical_damage'",
			"'random_damage'", "'critical'", "'hit_modify'", "'attack_range'", "'damage_range'",
			"'attack_speed'", "'avoid_modify'", "'dual_fhit_rate'", "'shield_defense'",
			"'shield_defense_rate'", "'reuse_delay'", "'initial_count'", "'soulshot_count'",
			"'spiritshot_count'", "'reduced_soulshot'", "'reduced_spiritshot'", "'reduced_mp_consume'",
			"'immediate_effect'", "'ex_immediate_effect'", "'use_skill_distime'",
			"'drop_period'", "'duration'", "'period'", "'equip_reuse_delay'", "'capsuled_items'",
			"'price'", "'default_price'", "'item_skill'", "'critical_attack_skill'",
			"'attack_skill'", "'magic_skill'", "'item_skill_enchanted_four'", "'crystal_type'",
			"'crystal_count'", "'material_type'", "'consume_type'", "'default_action'",
			"'recipe_id'", "'blessed'", "'weight'", "'item_multi_skill_list'", "'delay_share_group'",
			"'etcitem_type'", "'armor_type'", "'slot_bit_type'", "'item_type'", "'['",
			"']'", "'base_attribute_attack'", "'weapon'", "'armor'", "'etcitem'",
			"'asset'", "'accessary'", "'questitem'", "'potion'", "'arrow'", "'scrl_enchant_am'",
			"'scrl_enchant_wp'", "'scroll'", "'material'", "'recipe'", "'pet_collar'",
			"'castle_guard'", "'lotto'", "'race_ticket'", "'dye'", "'seed'", "'seed2'",
			"'crop'", "'maturecrop'", "'harvest'", "'ticket_of_lord'", "'lure'", "'bless_scrl_enchant_am'",
			"'bless_scrl_enchant_wp'", "'coupon'", "'elixir'", "'scrl_enchant_attr'",
			"'scrl_inc_enchant_prop_wp'", "'scrl_inc_enchant_prop_am'", "'bolt'",
			"'ancient_crystal_enchant_wp'", "'ancient_crystal_enchant_am'", "'rune_select'",
			"'rune'", "'action_none'", "'action_equip'", "'action_peel'", "'action_skill_reduce'",
			"'action_show_html'", "'action_soulshot'", "'action_recipe'", "'action_skill_maintain'",
			"'action_spiritshot'", "'action_dice'", "'action_calc'", "'action_seed'",
			"'action_harvest'", "'action_capsule'", "'action_xmas_open'", "'action_show_ssq_status'",
			"'action_fishingshot'", "'action_summon_soulshot'", "'action_summon_spiritshot'",
			"'action_call_skill'", "'action_show_adventurer_guide_book'", "'action_keep_exp'",
			"'action_create_mpcc'", "'action_nick_color'", "'action_hide_name'", "'action_start_quest'",
			"'consume_type_normal'", "'consume_type_stackable'", "'consume_type_asset'",
			"'crystal_free'", "'event'", "'d'", "'c'", "'b'", "'a'", "'s80'", "'s84'",
			"'s'", null, null, null, null, "'fire'", "'water'", "'earth'", "'wind'",
			"'unholy'", "'holy'", "';'", "'none'", "'fairy'", "'animal'", "'humanoid'",
			"'plant'", "'undead'", "'construct'", "'beast'", "'bug'", "'elemental'",
			"'demonic'", "'giant'", "'dragon'", "'divine'", "'summon'", "'pet'", "'holything'",
			"'dwarf'", "'merchant'", "'elf'", "'kamael'", "'orc'", "'mercenary'",
			"'human'", "'boss'", "'zzoldagu'", "'world_trap'", "'monrace'", "'darkelf'",
			"'guard'", "'teleporter'", "'warehouse_keeper'", "'warrior'", "'citizen'",
			"'treasure'", "'fieldboss'", "'blacksmith'", "'guild_master'", "'guild_coach'",
			"'sword'", "'blunt'", "'bow'", "'pole'", "'dagger'", "'dual'", "'fist'",
			"'dualfist'", "'fishingrod'", "'rapier'", "'ancientsword'", "'crossbow'",
			"'flag'", "'ownthing'", "'dualdagger'", "'etc'", "'light'", "'heavy'",
			"'magic'", "'sigil'", "'rhand'", "'lrhand'", "'lhand'", "'chest'", "'legs'",
			"'feet'", "'head'", "'gloves'", "'onepiece'", "'rear'", "'lear'", "'rfinger'",
			"'lfinger'", "'neck'", "'back'", "'underwear'", "'hair'", "'hair2'", "'hairall'",
			"'alldress'", "'rbracelet'", "'lbracelet'", "'waist'", "'deco1'", "'steel'",
			"'fine_steel'", "'wood'", "'cloth'", "'leather'", "'bone'", "'bronze'",
			"'oriharukon'", "'mithril'", "'damascus'", "'adamantaite'", "'blood_steel'",
			"'paper'", "'gold'", "'liquid'", "'fish'", "'silver'", "'chrysolite'",
			"'crystal'", "'horn'", "'scale_of_dragon'", "'cotton'", "'dyestuff'",
			"'cobweb'", "'rune_xp'", "'rune_sp'", "'rune_remove_penalty'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, "WEAPON",
			"ARMOR", "ETCITEM", "ASSET", "ACCESSARY", "QUESTITEM", "POTION", "ARROW",
			"SCRL_ENCHANT_AM", "SCRL_ENCHANT_WP", "SCROLL", "MATERIAL", "RECIPE",
			"PET_COLLAR", "CASTLE_GUARD", "LOTTO", "RACE_TICKET", "DYE", "SEED", "SEED2",
			"CROP", "MATURECROP", "HARVEST", "TICKET_OF_LORD", "LURE", "BLESS_SCRL_ENCHANT_AM",
			"BLESS_SCRL_ENCHANT_WP", "COUPON", "ELIXIR", "SCRL_ENCHANT_ATTR", "SCRL_INC_ENCHANT_PROP_WP",
			"SCRL_INC_ENCHANT_PROP_AM", "BOLT", "ANCIENT_CRYSTAL_ENCHANT_WP", "ANCIENT_CRYSTAL_ENCHANT_AM",
			"RUNE_SELECT", "RUNE", "ACTION_NONE", "ACTION_EQUIP", "ACTION_PEEL", "ACTION_SKILL_REDUCE",
			"ACTION_SHOW_HTML", "ACTION_SOULSHOT", "ACTION_RECIPE", "ACTION_SKILL_MAINTAIN",
			"ACTION_SPIRITSHOT", "ACTION_DICE", "ACTION_CALC", "ACTION_SEED", "ACTION_HARVEST",
			"ACTION_CAPSULE", "ACTION_XMAS_OPEN", "ACTION_SHOW_SSQ_STATUS", "ACTION_FISHINGSHOT",
			"ACTION_SUMMON_SOULSHOT", "ACTION_SUMMON_SPIRITSHOT", "ACTION_CALL_SKILL",
			"ACTION_SHOW_ADVENTURER_GUIDE_BOOK", "ACTION_KEEP_EXP", "ACTION_CREATE_MPCC",
			"ACTION_NICK_COLOR", "ACTION_HIDE_NAME", "ACTION_START_QUEST", "CONSUME_TYPE_NORMAL",
			"CONSUME_TYPE_STACKABLE", "CONSUME_TYPE_ASSET", "CRYSTAL_FREE", "EVENT",
			"D", "C", "B", "A", "S80", "S84", "S", "CATEGORY", "BOOLEAN", "INTEGER",
			"DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", "SEMICOLON",
			"NONE", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", "CONSTRUCT",
			"BEAST", "BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", "DIVINE", "SUMMON",
			"PET", "HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", "ORC", "MERCENARY",
			"HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", "MONRACE", "DARKELF", "GUARD",
			"TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", "CITIZEN", "TREASURE", "FIELDBOSS",
			"BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", "SWORD", "BLUNT", "BOW",
			"POLE", "DAGGER", "DUAL", "FIST", "DUALFIST", "FISHINGROD", "RAPIER",
			"ANCIENTSWORD", "CROSSBOW", "FLAG", "OWNTHING", "DUALDAGGER", "ETC", "LIGHT",
			"HEAVY", "MAGIC", "SIGIL", "RHAND", "LRHAND", "LHAND", "CHEST", "LEGS",
			"FEET", "HEAD", "GLOVES", "ONEPIECE", "REAR", "LEAR", "RFINGER", "LFINGER",
			"NECK", "BACK", "UNDERWEAR", "HAIR", "HAIR2", "HAIRALL", "ALLDRESS", "RBRACELET",
			"LBRACELET", "WAIST", "DECO1", "STEEL", "FINE_STEEL", "WOOD", "CLOTH",
			"LEATHER", "BONE", "BRONZE", "ORIHARUKON", "MITHRIL", "DAMASCUS", "ADAMANTAITE",
			"BLOOD_STEEL", "PAPER", "GOLD", "LIQUID", "FISH", "SILVER", "CHRYSOLITE",
			"CRYSTAL", "HORN", "SCALE_OF_DRAGON", "COTTON", "DYESTUFF", "COBWEB",
			"RUNE_XP", "RUNE_SP", "RUNE_REMOVE_PENALTY", "NAME", "WS", "LINE_COMMENT",
			"STAR_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;

	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ItemDatasLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "ItemDatas.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	private static final int _serializedATNSegments = 2;
	private static final String _serializedATNSegment0 =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\u014b\u10ba\b\1\4" +
					"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n" +
					"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22" +
					"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31" +
					"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t" +
					" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t" +
					"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64" +
					"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t" +
					"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4" +
					"I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\t" +
					"T\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_" +
					"\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k" +
					"\tk\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv" +
					"\4w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t" +
					"\u0080\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084" +
					"\4\u0085\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089" +
					"\t\u0089\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d" +
					"\4\u008e\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092" +
					"\t\u0092\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096" +
					"\4\u0097\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b" +
					"\t\u009b\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f" +
					"\4\u00a0\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4" +
					"\t\u00a4\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8" +
					"\4\u00a9\t\u00a9\4\u00aa\t\u00aa\4\u00ab\t\u00ab\4\u00ac\t\u00ac\4\u00ad" +
					"\t\u00ad\4\u00ae\t\u00ae\4\u00af\t\u00af\4\u00b0\t\u00b0\4\u00b1\t\u00b1" +
					"\4\u00b2\t\u00b2\4\u00b3\t\u00b3\4\u00b4\t\u00b4\4\u00b5\t\u00b5\4\u00b6" +
					"\t\u00b6\4\u00b7\t\u00b7\4\u00b8\t\u00b8\4\u00b9\t\u00b9\4\u00ba\t\u00ba" +
					"\4\u00bb\t\u00bb\4\u00bc\t\u00bc\4\u00bd\t\u00bd\4\u00be\t\u00be\4\u00bf" +
					"\t\u00bf\4\u00c0\t\u00c0\4\u00c1\t\u00c1\4\u00c2\t\u00c2\4\u00c3\t\u00c3" +
					"\4\u00c4\t\u00c4\4\u00c5\t\u00c5\4\u00c6\t\u00c6\4\u00c7\t\u00c7\4\u00c8" +
					"\t\u00c8\4\u00c9\t\u00c9\4\u00ca\t\u00ca\4\u00cb\t\u00cb\4\u00cc\t\u00cc" +
					"\4\u00cd\t\u00cd\4\u00ce\t\u00ce\4\u00cf\t\u00cf\4\u00d0\t\u00d0\4\u00d1" +
					"\t\u00d1\4\u00d2\t\u00d2\4\u00d3\t\u00d3\4\u00d4\t\u00d4\4\u00d5\t\u00d5" +
					"\4\u00d6\t\u00d6\4\u00d7\t\u00d7\4\u00d8\t\u00d8\4\u00d9\t\u00d9\4\u00da" +
					"\t\u00da\4\u00db\t\u00db\4\u00dc\t\u00dc\4\u00dd\t\u00dd\4\u00de\t\u00de" +
					"\4\u00df\t\u00df\4\u00e0\t\u00e0\4\u00e1\t\u00e1\4\u00e2\t\u00e2\4\u00e3" +
					"\t\u00e3\4\u00e4\t\u00e4\4\u00e5\t\u00e5\4\u00e6\t\u00e6\4\u00e7\t\u00e7" +
					"\4\u00e8\t\u00e8\4\u00e9\t\u00e9\4\u00ea\t\u00ea\4\u00eb\t\u00eb\4\u00ec" +
					"\t\u00ec\4\u00ed\t\u00ed\4\u00ee\t\u00ee\4\u00ef\t\u00ef\4\u00f0\t\u00f0" +
					"\4\u00f1\t\u00f1\4\u00f2\t\u00f2\4\u00f3\t\u00f3\4\u00f4\t\u00f4\4\u00f5" +
					"\t\u00f5\4\u00f6\t\u00f6\4\u00f7\t\u00f7\4\u00f8\t\u00f8\4\u00f9\t\u00f9" +
					"\4\u00fa\t\u00fa\4\u00fb\t\u00fb\4\u00fc\t\u00fc\4\u00fd\t\u00fd\4\u00fe" +
					"\t\u00fe\4\u00ff\t\u00ff\4\u0100\t\u0100\4\u0101\t\u0101\4\u0102\t\u0102" +
					"\4\u0103\t\u0103\4\u0104\t\u0104\4\u0105\t\u0105\4\u0106\t\u0106\4\u0107" +
					"\t\u0107\4\u0108\t\u0108\4\u0109\t\u0109\4\u010a\t\u010a\4\u010b\t\u010b" +
					"\4\u010c\t\u010c\4\u010d\t\u010d\4\u010e\t\u010e\4\u010f\t\u010f\4\u0110" +
					"\t\u0110\4\u0111\t\u0111\4\u0112\t\u0112\4\u0113\t\u0113\4\u0114\t\u0114" +
					"\4\u0115\t\u0115\4\u0116\t\u0116\4\u0117\t\u0117\4\u0118\t\u0118\4\u0119" +
					"\t\u0119\4\u011a\t\u011a\4\u011b\t\u011b\4\u011c\t\u011c\4\u011d\t\u011d" +
					"\4\u011e\t\u011e\4\u011f\t\u011f\4\u0120\t\u0120\4\u0121\t\u0121\4\u0122" +
					"\t\u0122\4\u0123\t\u0123\4\u0124\t\u0124\4\u0125\t\u0125\4\u0126\t\u0126" +
					"\4\u0127\t\u0127\4\u0128\t\u0128\4\u0129\t\u0129\4\u012a\t\u012a\4\u012b" +
					"\t\u012b\4\u012c\t\u012c\4\u012d\t\u012d\4\u012e\t\u012e\4\u012f\t\u012f" +
					"\4\u0130\t\u0130\4\u0131\t\u0131\4\u0132\t\u0132\4\u0133\t\u0133\4\u0134" +
					"\t\u0134\4\u0135\t\u0135\4\u0136\t\u0136\4\u0137\t\u0137\4\u0138\t\u0138" +
					"\4\u0139\t\u0139\4\u013a\t\u013a\4\u013b\t\u013b\4\u013c\t\u013c\4\u013d" +
					"\t\u013d\4\u013e\t\u013e\4\u013f\t\u013f\4\u0140\t\u0140\4\u0141\t\u0141" +
					"\4\u0142\t\u0142\4\u0143\t\u0143\4\u0144\t\u0144\4\u0145\t\u0145\4\u0146" +
					"\t\u0146\4\u0147\t\u0147\4\u0148\t\u0148\4\u0149\t\u0149\4\u014a\t\u014a" +
					"\4\u014b\t\u014b\4\u014c\t\u014c\4\u014d\t\u014d\4\u014e\t\u014e\4\u014f" +
					"\t\u014f\4\u0150\t\u0150\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4" +
					"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3" +
					"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n" +
					"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3" +
					"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r" +
					"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3" +
					"\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3" +
					"\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3" +
					"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3" +
					"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3" +
					"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3" +
					"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3" +
					"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3" +
					"\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3" +
					"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3" +
					"\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3" +
					"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3" +
					"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3" +
					"\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3" +
					"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3" +
					"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3" +
					"\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3" +
					"\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3" +
					"\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3" +
					"!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#" +
					"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$" +
					"\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&" +
					"\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3" +
					")\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3" +
					"+\3+\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3" +
					",\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\3" +
					".\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3/\3" +
					"\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3" +
					"\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3" +
					"\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3" +
					"\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3" +
					"\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3" +
					"\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3" +
					"\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3" +
					"\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\3" +
					"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3" +
					"8\38\39\39\39\39\39\39\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3:\3:\3:\3" +
					":\3:\3:\3;\3;\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3" +
					"<\3<\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3" +
					">\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3@\3" +
					"@\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3A\3" +
					"A\3B\3B\3B\3B\3B\3B\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3D\3" +
					"D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3E\3E\3E\3E\3E\3E\3E\3E\3E\3" +
					"E\3E\3F\3F\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3" +
					"H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3" +
					"I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3" +
					"K\3K\3K\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3" +
					"M\3M\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3N\3O\3O\3O\3O\3O\3O\3O\3O\3O\3" +
					"O\3O\3O\3O\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3" +
					"Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3R\3" +
					"R\3R\3S\3S\3S\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3U\3" +
					"U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3" +
					"V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3" +
					"X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3" +
					"Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3" +
					"[\3[\3[\3[\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\" +
					"\3\\\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3^\3" +
					"^\3^\3^\3^\3^\3^\3^\3^\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3" +
					"_\3_\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3a\3a\3a\3a\3" +
					"a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3b\3b\3b\3b\3b\3b\3b\3b\3" +
					"b\3b\3b\3b\3b\3b\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3" +
					"c\3c\3c\3c\3c\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3" +
					"d\3d\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3f\3f\3f\3" +
					"f\3f\3f\3f\3f\3f\3f\3f\3f\3g\3g\3g\3g\3g\3g\3g\3g\3g\3h\3h\3h\3h\3h\3" +
					"h\3h\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3j\3j\3j\3" +
					"j\3j\3j\3j\3j\3j\3j\3j\3j\3j\3j\3j\3k\3k\3k\3k\3k\3k\3l\3l\3l\3l\3l\3" +
					"l\3l\3l\3l\3l\3l\3l\3l\3l\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3n\3n\3n\3" +
					"n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3o\3o\3o\3o\3" +
					"o\3o\3o\3o\3o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3q\3q\3" +
					"q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3" +
					"q\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3s\3s\3s\3s\3s\3s\3s\3s\3s\3" +
					"s\3s\3s\3s\3s\3t\3t\3t\3t\3t\3t\3t\3t\3t\3t\3t\3t\3t\3t\3u\3u\3u\3u\3" +
					"u\3u\3u\3u\3u\3u\3u\3u\3u\3v\3v\3v\3v\3v\3v\3v\3v\3v\3v\3v\3v\3v\3v\3" +
					"v\3w\3w\3w\3w\3w\3w\3w\3w\3w\3w\3x\3x\3x\3x\3x\3x\3x\3x\3y\3y\3y\3y\3" +
					"y\3y\3y\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3z\3" +
					"z\3z\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3|\3|\3|\3" +
					"|\3|\3|\3|\3|\3|\3|\3|\3|\3|\3}\3}\3}\3}\3}\3}\3}\3}\3}\3}\3}\3~\3~\3" +
					"~\3~\3~\3~\3~\3~\3~\3~\3~\3~\3~\3~\3\177\3\177\3\177\3\177\3\177\3\177" +
					"\3\177\3\177\3\177\3\177\3\u0080\3\u0080\3\u0081\3\u0081\3\u0082\3\u0082" +
					"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082" +
					"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082" +
					"\3\u0082\3\u0082\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083" +
					"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0085\3\u0085\3\u0085" +
					"\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0086\3\u0086\3\u0086\3\u0086" +
					"\3\u0086\3\u0086\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087" +
					"\3\u0087\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088" +
					"\3\u0088\3\u0088\3\u0088\3\u0088\3\u0089\3\u0089\3\u0089\3\u0089\3\u0089" +
					"\3\u0089\3\u0089\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008b" +
					"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b" +
					"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008c\3\u008c\3\u008c" +
					"\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c" +
					"\3\u008c\3\u008c\3\u008c\3\u008c\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d" +
					"\3\u008d\3\u008d\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e" +
					"\3\u008e\3\u008e\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f" +
					"\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090" +
					"\3\u0090\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091" +
					"\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0092\3\u0092\3\u0092" +
					"\3\u0092\3\u0092\3\u0092\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093" +
					"\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094\3\u0094" +
					"\3\u0094\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0096\3\u0096\3\u0096" +
					"\3\u0096\3\u0096\3\u0096\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0098" +
					"\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098" +
					"\3\u0098\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099" +
					"\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a" +
					"\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009b\3\u009b\3\u009b" +
					"\3\u009b\3\u009b\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c" +
					"\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c" +
					"\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009d\3\u009d\3\u009d" +
					"\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d" +
					"\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d" +
					"\3\u009d\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009f" +
					"\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u00a0\3\u00a0\3\u00a0" +
					"\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0" +
					"\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a1\3\u00a1\3\u00a1" +
					"\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1" +
					"\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1" +
					"\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2" +
					"\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2" +
					"\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2" +
					"\3\u00a2\3\u00a2\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a4\3\u00a4" +
					"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4" +
					"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4" +
					"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a5\3\u00a5" +
					"\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5" +
					"\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5" +
					"\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a6\3\u00a6" +
					"\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6" +
					"\3\u00a6\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a8\3\u00a8\3\u00a8" +
					"\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8" +
					"\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9" +
					"\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa" +
					"\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00ab\3\u00ab" +
					"\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab" +
					"\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab" +
					"\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac" +
					"\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ad" +
					"\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad" +
					"\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ae\3\u00ae\3\u00ae" +
					"\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae" +
					"\3\u00ae\3\u00ae\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af" +
					"\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af" +
					"\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00b0\3\u00b0\3\u00b0" +
					"\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0" +
					"\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b1\3\u00b1\3\u00b1" +
					"\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1" +
					"\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2" +
					"\3\u00b2\3\u00b2\3\u00b2\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3" +
					"\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b4\3\u00b4\3\u00b4" +
					"\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4" +
					"\3\u00b4\3\u00b4\3\u00b4\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5" +
					"\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5" +
					"\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6" +
					"\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b7" +
					"\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7" +
					"\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7" +
					"\3\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8" +
					"\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8" +
					"\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b8\3\u00b9\3\u00b9\3\u00b9\3\u00b9" +
					"\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9" +
					"\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9" +
					"\3\u00b9\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba" +
					"\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba" +
					"\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u00bb" +
					"\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb" +
					"\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bc" +
					"\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc" +
					"\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc" +
					"\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc" +
					"\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bd\3\u00bd\3\u00bd" +
					"\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3\u00bd" +
					"\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be" +
					"\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be" +
					"\3\u00be\3\u00be\3\u00be\3\u00be\3\u00be\3\u00bf\3\u00bf\3\u00bf\3\u00bf" +
					"\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf" +
					"\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00c0\3\u00c0\3\u00c0\3\u00c0" +
					"\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0" +
					"\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1" +
					"\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1" +
					"\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c1\3\u00c2\3\u00c2\3\u00c2\3\u00c2" +
					"\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2" +
					"\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c2\3\u00c3\3\u00c3" +
					"\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3" +
					"\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3\3\u00c3" +
					"\3\u00c3\3\u00c3\3\u00c3\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4" +
					"\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c4" +
					"\3\u00c4\3\u00c4\3\u00c4\3\u00c4\3\u00c5\3\u00c5\3\u00c5\3\u00c5\3\u00c5" +
					"\3\u00c5\3\u00c5\3\u00c5\3\u00c5\3\u00c5\3\u00c5\3\u00c5\3\u00c5\3\u00c6" +
					"\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c6\3\u00c7\3\u00c7\3\u00c8\3\u00c8" +
					"\3\u00c9\3\u00c9\3\u00ca\3\u00ca\3\u00cb\3\u00cb\3\u00cb\3\u00cb\3\u00cc" +
					"\3\u00cc\3\u00cc\3\u00cc\3\u00cd\3\u00cd\3\u00ce\3\u00ce\3\u00ce\3\u00cf" +
					"\3\u00cf\3\u00d0\5\u00d0\u0cfe\n\u00d0\3\u00d0\3\u00d0\3\u00d1\3\u00d1" +
					"\3\u00d1\6\u00d1\u0d05\n\u00d1\r\u00d1\16\u00d1\u0d06\3\u00d2\3\u00d2" +
					"\3\u00d2\3\u00d2\3\u00d2\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3" +
					"\3\u00d4\3\u00d4\3\u00d4\3\u00d4\3\u00d4\3\u00d4\3\u00d5\3\u00d5\3\u00d5" +
					"\3\u00d5\3\u00d5\3\u00d6\3\u00d6\3\u00d6\3\u00d6\3\u00d6\3\u00d6\3\u00d6" +
					"\3\u00d7\3\u00d7\3\u00d7\3\u00d7\3\u00d7\3\u00d8\3\u00d8\3\u00d8\7\u00d8" +
					"\u0d2e\n\u00d8\f\u00d8\16\u00d8\u0d31\13\u00d8\5\u00d8\u0d33\n\u00d8\3" +
					"\u00d9\3\u00d9\3\u00da\3\u00da\3\u00db\3\u00db\3\u00dc\3\u00dc\3\u00dd" +
					"\3\u00dd\3\u00de\3\u00de\3\u00df\3\u00df\3\u00df\3\u00df\3\u00df\3\u00e0" +
					"\3\u00e0\3\u00e0\3\u00e0\3\u00e0\3\u00e0\3\u00e1\3\u00e1\3\u00e1\3\u00e1" +
					"\3\u00e1\3\u00e1\3\u00e1\3\u00e2\3\u00e2\3\u00e2\3\u00e2\3\u00e2\3\u00e2" +
					"\3\u00e2\3\u00e2\3\u00e2\3\u00e3\3\u00e3\3\u00e3\3\u00e3\3\u00e3\3\u00e3" +
					"\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e4\3\u00e5\3\u00e5" +
					"\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e5\3\u00e6" +
					"\3\u00e6\3\u00e6\3\u00e6\3\u00e6\3\u00e6\3\u00e7\3\u00e7\3\u00e7\3\u00e7" +
					"\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8\3\u00e8" +
					"\3\u00e8\3\u00e9\3\u00e9\3\u00e9\3\u00e9\3\u00e9\3\u00e9\3\u00e9\3\u00e9" +
					"\3\u00ea\3\u00ea\3\u00ea\3\u00ea\3\u00ea\3\u00ea\3\u00eb\3\u00eb\3\u00eb" +
					"\3\u00eb\3\u00eb\3\u00eb\3\u00eb\3\u00ec\3\u00ec\3\u00ec\3\u00ec\3\u00ec" +
					"\3\u00ec\3\u00ec\3\u00ed\3\u00ed\3\u00ed\3\u00ed\3\u00ed\3\u00ed\3\u00ed" +
					"\3\u00ee\3\u00ee\3\u00ee\3\u00ee\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef" +
					"\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00ef\3\u00f0\3\u00f0\3\u00f0\3\u00f0" +
					"\3\u00f0\3\u00f0\3\u00f1\3\u00f1\3\u00f1\3\u00f1\3\u00f1\3\u00f1\3\u00f1" +
					"\3\u00f1\3\u00f1\3\u00f2\3\u00f2\3\u00f2\3\u00f2\3\u00f3\3\u00f3\3\u00f3" +
					"\3\u00f3\3\u00f3\3\u00f3\3\u00f3\3\u00f4\3\u00f4\3\u00f4\3\u00f4\3\u00f5" +
					"\3\u00f5\3\u00f5\3\u00f5\3\u00f5\3\u00f5\3\u00f5\3\u00f5\3\u00f5\3\u00f5" +
					"\3\u00f6\3\u00f6\3\u00f6\3\u00f6\3\u00f6\3\u00f6\3\u00f7\3\u00f7\3\u00f7" +
					"\3\u00f7\3\u00f7\3\u00f8\3\u00f8\3\u00f8\3\u00f8\3\u00f8\3\u00f8\3\u00f8" +
					"\3\u00f8\3\u00f8\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00f9" +
					"\3\u00f9\3\u00f9\3\u00f9\3\u00f9\3\u00fa\3\u00fa\3\u00fa\3\u00fa\3\u00fa" +
					"\3\u00fa\3\u00fa\3\u00fa\3\u00fb\3\u00fb\3\u00fb\3\u00fb\3\u00fb\3\u00fb" +
					"\3\u00fb\3\u00fb\3\u00fc\3\u00fc\3\u00fc\3\u00fc\3\u00fc\3\u00fc\3\u00fd" +
					"\3\u00fd\3\u00fd\3\u00fd\3\u00fd\3\u00fd\3\u00fd\3\u00fd\3\u00fd\3\u00fd" +
					"\3\u00fd\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe" +
					"\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe\3\u00fe" +
					"\3\u00ff\3\u00ff\3\u00ff\3\u00ff\3\u00ff\3\u00ff\3\u00ff\3\u00ff\3\u0100" +
					"\3\u0100\3\u0100\3\u0100\3\u0100\3\u0100\3\u0100\3\u0100\3\u0101\3\u0101" +
					"\3\u0101\3\u0101\3\u0101\3\u0101\3\u0101\3\u0101\3\u0101\3\u0102\3\u0102" +
					"\3\u0102\3\u0102\3\u0102\3\u0102\3\u0102\3\u0102\3\u0102\3\u0102\3\u0103" +
					"\3\u0103\3\u0103\3\u0103\3\u0103\3\u0103\3\u0103\3\u0103\3\u0103\3\u0103" +
					"\3\u0103\3\u0104\3\u0104\3\u0104\3\u0104\3\u0104\3\u0104\3\u0104\3\u0104" +
					"\3\u0104\3\u0104\3\u0104\3\u0104\3\u0104\3\u0105\3\u0105\3\u0105\3\u0105" +
					"\3\u0105\3\u0105\3\u0105\3\u0105\3\u0105\3\u0105\3\u0105\3\u0105\3\u0106" +
					"\3\u0106\3\u0106\3\u0106\3\u0106\3\u0106\3\u0107\3\u0107\3\u0107\3\u0107" +
					"\3\u0107\3\u0107\3\u0108\3\u0108\3\u0108\3\u0108\3\u0109\3\u0109\3\u0109" +
					"\3\u0109\3\u0109\3\u010a\3\u010a\3\u010a\3\u010a\3\u010a\3\u010a\3\u010a" +
					"\3\u010b\3\u010b\3\u010b\3\u010b\3\u010b\3\u010c\3\u010c\3\u010c\3\u010c" +
					"\3\u010c\3\u010d\3\u010d\3\u010d\3\u010d\3\u010d\3\u010d\3\u010d\3\u010d" +
					"\3\u010d\3\u010e\3\u010e\3\u010e\3\u010e\3\u010e\3\u010e\3\u010e\3\u010e" +
					"\3\u010e\3\u010e\3\u010e\3\u010f\3\u010f\3\u010f\3\u010f\3\u010f\3\u010f" +
					"\3\u010f\3\u0110\3\u0110\3\u0110\3\u0110\3\u0110\3\u0110\3\u0110\3\u0110" +
					"\3\u0110\3\u0110\3\u0110\3\u0110\3\u0110\3\u0111\3\u0111\3\u0111\3\u0111" +
					"\3\u0111\3\u0111\3\u0111\3\u0111\3\u0111\3\u0112\3\u0112\3\u0112\3\u0112" +
					"\3\u0112\3\u0113\3\u0113\3\u0113\3\u0113\3\u0113\3\u0113\3\u0113\3\u0113" +
					"\3\u0113\3\u0114\3\u0114\3\u0114\3\u0114\3\u0114\3\u0114\3\u0114\3\u0114" +
					"\3\u0114\3\u0114\3\u0114\3\u0115\3\u0115\3\u0115\3\u0115\3\u0116\3\u0116" +
					"\3\u0116\3\u0116\3\u0116\3\u0116\3\u0117\3\u0117\3\u0117\3\u0117\3\u0117" +
					"\3\u0117\3\u0118\3\u0118\3\u0118\3\u0118\3\u0118\3\u0118\3\u0119\3\u0119" +
					"\3\u0119\3\u0119\3\u0119\3\u0119\3\u011a\3\u011a\3\u011a\3\u011a\3\u011a" +
					"\3\u011a\3\u011b\3\u011b\3\u011b\3\u011b\3\u011b\3\u011b\3\u011b\3\u011c" +
					"\3\u011c\3\u011c\3\u011c\3\u011c\3\u011c\3\u011d\3\u011d\3\u011d\3\u011d" +
					"\3\u011d\3\u011d\3\u011e\3\u011e\3\u011e\3\u011e\3\u011e\3\u011f\3\u011f" +
					"\3\u011f\3\u011f\3\u011f\3\u0120\3\u0120\3\u0120\3\u0120\3\u0120\3\u0121" +
					"\3\u0121\3\u0121\3\u0121\3\u0121\3\u0121\3\u0121\3\u0122\3\u0122\3\u0122" +
					"\3\u0122\3\u0122\3\u0122\3\u0122\3\u0122\3\u0122\3\u0123\3\u0123\3\u0123" +
					"\3\u0123\3\u0123\3\u0124\3\u0124\3\u0124\3\u0124\3\u0124\3\u0125\3\u0125" +
					"\3\u0125\3\u0125\3\u0125\3\u0125\3\u0125\3\u0125\3\u0126\3\u0126\3\u0126" +
					"\3\u0126\3\u0126\3\u0126\3\u0126\3\u0126\3\u0127\3\u0127\3\u0127\3\u0127" +
					"\3\u0127\3\u0128\3\u0128\3\u0128\3\u0128\3\u0128\3\u0129\3\u0129\3\u0129" +
					"\3\u0129\3\u0129\3\u0129\3\u0129\3\u0129\3\u0129\3\u0129\3\u012a\3\u012a" +
					"\3\u012a\3\u012a\3\u012a\3\u012b\3\u012b\3\u012b\3\u012b\3\u012b\3\u012b" +
					"\3\u012c\3\u012c\3\u012c\3\u012c\3\u012c\3\u012c\3\u012c\3\u012c\3\u012d" +
					"\3\u012d\3\u012d\3\u012d\3\u012d\3\u012d\3\u012d\3\u012d\3\u012d\3\u012e" +
					"\3\u012e\3\u012e\3\u012e\3\u012e\3\u012e\3\u012e\3\u012e\3\u012e\3\u012e" +
					"\3\u012f\3\u012f\3\u012f\3\u012f\3\u012f\3\u012f\3\u012f\3\u012f\3\u012f" +
					"\3\u012f\3\u0130\3\u0130\3\u0130\3\u0130\3\u0130\3\u0130\3\u0131\3\u0131" +
					"\3\u0131\3\u0131\3\u0131\3\u0131\3\u0132\3\u0132\3\u0132\3\u0132\3\u0132" +
					"\3\u0132\3\u0133\3\u0133\3\u0133\3\u0133\3\u0133\3\u0133\3\u0133\3\u0133" +
					"\3\u0133\3\u0133\3\u0133\3\u0134\3\u0134\3\u0134\3\u0134\3\u0134\3\u0135" +
					"\3\u0135\3\u0135\3\u0135\3\u0135\3\u0135\3\u0136\3\u0136\3\u0136\3\u0136" +
					"\3\u0136\3\u0136\3\u0136\3\u0136\3\u0137\3\u0137\3\u0137\3\u0137\3\u0137" +
					"\3\u0138\3\u0138\3\u0138\3\u0138\3\u0138\3\u0138\3\u0138\3\u0139\3\u0139" +
					"\3\u0139\3\u0139\3\u0139\3\u0139\3\u0139\3\u0139\3\u0139\3\u0139\3\u0139" +
					"\3\u013a\3\u013a\3\u013a\3\u013a\3\u013a\3\u013a\3\u013a\3\u013a\3\u013b" +
					"\3\u013b\3\u013b\3\u013b\3\u013b\3\u013b\3\u013b\3\u013b\3\u013b\3\u013c" +
					"\3\u013c\3\u013c\3\u013c\3\u013c\3\u013c\3\u013c\3\u013c\3\u013c\3\u013c" +
					"\3\u013c\3\u013c\3\u013d\3\u013d\3\u013d\3\u013d\3\u013d\3\u013d\3\u013d" +
					"\3\u013d\3\u013d\3\u013d\3\u013d\3\u013d\3\u013e\3\u013e\3\u013e\3\u013e" +
					"\3\u013e\3\u013e\3\u013f\3\u013f\3\u013f\3\u013f\3\u013f\3\u0140\3\u0140" +
					"\3\u0140\3\u0140\3\u0140\3\u0140\3\u0140\3\u0141\3\u0141\3\u0141\3\u0141" +
					"\3\u0141\3\u0142\3\u0142\3\u0142\3\u0142\3\u0142\3\u0142\3\u0142\3\u0143" +
					"\3\u0143\3\u0143\3\u0143\3\u0143\3\u0143\3\u0143\3\u0143\3\u0143\3\u0143" +
					"\3\u0143\3\u0144\3\u0144\3\u0144\3\u0144\3\u0144\3\u0144\3\u0144\3\u0144" +
					"\3\u0145\3\u0145\3\u0145\3\u0145\3\u0145\3\u0146\3\u0146\3\u0146\3\u0146" +
					"\3\u0146\3\u0146\3\u0146\3\u0146\3\u0146\3\u0146\3\u0146\3\u0146\3\u0146" +
					"\3\u0146\3\u0146\3\u0146\3\u0147\3\u0147\3\u0147\3\u0147\3\u0147\3\u0147" +
					"\3\u0147\3\u0148\3\u0148\3\u0148\3\u0148\3\u0148\3\u0148\3\u0148\3\u0148" +
					"\3\u0148\3\u0149\3\u0149\3\u0149\3\u0149\3\u0149\3\u0149\3\u0149\3\u014a" +
					"\3\u014a\3\u014a\3\u014a\3\u014a\3\u014a\3\u014a\3\u014a\3\u014b\3\u014b" +
					"\3\u014b\3\u014b\3\u014b\3\u014b\3\u014b\3\u014b\3\u014c\3\u014c\3\u014c" +
					"\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c" +
					"\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014c\3\u014d" +
					"\6\u014d\u108c\n\u014d\r\u014d\16\u014d\u108d\3\u014d\7\u014d\u1091\n" +
					"\u014d\f\u014d\16\u014d\u1094\13\u014d\3\u014e\6\u014e\u1097\n\u014e\r" +
					"\u014e\16\u014e\u1098\3\u014e\3\u014e\3\u014f\3\u014f\3\u014f\3\u014f" +
					"\7\u014f\u10a1\n\u014f\f\u014f\16\u014f\u10a4\13\u014f\3\u014f\5\u014f" +
					"\u10a7\n\u014f\3\u014f\3\u014f\3\u014f\3\u014f\3\u0150\3\u0150\3\u0150" +
					"\3\u0150\7\u0150\u10b1\n\u0150\f\u0150\16\u0150\u10b4\13\u0150\3\u0150" +
					"\3\u0150\3\u0150\3\u0150\3\u0150\3\u10b2\2\u0151\3\3\5\4\7\5\t\6\13\7" +
					"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25" +
					")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O" +
					")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081" +
					"B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095" +
					"L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\u00a3S\u00a5T\u00a7U\u00a9" +
					"V\u00abW\u00adX\u00afY\u00b1Z\u00b3[\u00b5\\\u00b7]\u00b9^\u00bb_\u00bd" +
					"`\u00bfa\u00c1b\u00c3c\u00c5d\u00c7e\u00c9f\u00cbg\u00cdh\u00cfi\u00d1" +
					"j\u00d3k\u00d5l\u00d7m\u00d9n\u00dbo\u00ddp\u00dfq\u00e1r\u00e3s\u00e5" +
					"t\u00e7u\u00e9v\u00ebw\u00edx\u00efy\u00f1z\u00f3{\u00f5|\u00f7}\u00f9" +
					"~\u00fb\177\u00fd\u0080\u00ff\u0081\u0101\u0082\u0103\u0083\u0105\u0084" +
					"\u0107\u0085\u0109\u0086\u010b\u0087\u010d\u0088\u010f\u0089\u0111\u008a" +
					"\u0113\u008b\u0115\u008c\u0117\u008d\u0119\u008e\u011b\u008f\u011d\u0090" +
					"\u011f\u0091\u0121\u0092\u0123\u0093\u0125\u0094\u0127\u0095\u0129\u0096" +
					"\u012b\u0097\u012d\u0098\u012f\u0099\u0131\u009a\u0133\u009b\u0135\u009c" +
					"\u0137\u009d\u0139\u009e\u013b\u009f\u013d\u00a0\u013f\u00a1\u0141\u00a2" +
					"\u0143\u00a3\u0145\u00a4\u0147\u00a5\u0149\u00a6\u014b\u00a7\u014d\u00a8" +
					"\u014f\u00a9\u0151\u00aa\u0153\u00ab\u0155\u00ac\u0157\u00ad\u0159\u00ae" +
					"\u015b\u00af\u015d\u00b0\u015f\u00b1\u0161\u00b2\u0163\u00b3\u0165\u00b4" +
					"\u0167\u00b5\u0169\u00b6\u016b\u00b7\u016d\u00b8\u016f\u00b9\u0171\u00ba" +
					"\u0173\u00bb\u0175\u00bc\u0177\u00bd\u0179\u00be\u017b\u00bf\u017d\u00c0" +
					"\u017f\u00c1\u0181\u00c2\u0183\u00c3\u0185\u00c4\u0187\u00c5\u0189\u00c6" +
					"\u018b\u00c7\u018d\u00c8\u018f\u00c9\u0191\u00ca\u0193\u00cb\u0195\u00cc" +
					"\u0197\u00cd\u0199\u00ce\u019b\u00cf\u019d\u00d0\u019f\u00d1\u01a1\u00d2" +
					"\u01a3\u00d3\u01a5\u00d4\u01a7\u00d5\u01a9\u00d6\u01ab\u00d7\u01ad\u00d8" +
					"\u01af\2\u01b1\2\u01b3\2\u01b5\2\u01b7\2\u01b9\2\u01bb\u00d9\u01bd\u00da" +
					"\u01bf\u00db\u01c1\u00dc\u01c3\u00dd\u01c5\u00de\u01c7\u00df\u01c9\u00e0" +
					"\u01cb\u00e1\u01cd\u00e2\u01cf\u00e3\u01d1\u00e4\u01d3\u00e5\u01d5\u00e6" +
					"\u01d7\u00e7\u01d9\u00e8\u01db\u00e9\u01dd\u00ea\u01df\u00eb\u01e1\u00ec" +
					"\u01e3\u00ed\u01e5\u00ee\u01e7\u00ef\u01e9\u00f0\u01eb\u00f1\u01ed\u00f2" +
					"\u01ef\u00f3\u01f1\u00f4\u01f3\u00f5\u01f5\u00f6\u01f7\u00f7\u01f9\u00f8" +
					"\u01fb\u00f9\u01fd\u00fa\u01ff\u00fb\u0201\u00fc\u0203\u00fd\u0205\u00fe" +
					"\u0207\u00ff\u0209\u0100\u020b\u0101\u020d\u0102\u020f\u0103\u0211\u0104" +
					"\u0213\u0105\u0215\u0106\u0217\u0107\u0219\u0108\u021b\u0109\u021d\u010a" +
					"\u021f\u010b\u0221\u010c\u0223\u010d\u0225\u010e\u0227\u010f\u0229\u0110" +
					"\u022b\u0111\u022d\u0112\u022f\u0113\u0231\u0114\u0233\u0115\u0235\u0116" +
					"\u0237\u0117\u0239\u0118\u023b\u0119\u023d\u011a\u023f\u011b\u0241\u011c" +
					"\u0243\u011d\u0245\u011e\u0247\u011f\u0249\u0120\u024b\u0121\u024d\u0122" +
					"\u024f\u0123\u0251\u0124\u0253\u0125\u0255\u0126\u0257\u0127\u0259\u0128" +
					"\u025b\u0129\u025d\u012a\u025f\u012b\u0261\u012c\u0263\u012d\u0265\u012e" +
					"\u0267\u012f\u0269\u0130\u026b\u0131\u026d\u0132\u026f\u0133\u0271\u0134" +
					"\u0273\u0135\u0275\u0136\u0277\u0137\u0279\u0138\u027b\u0139\u027d\u013a" +
					"\u027f\u013b\u0281\u013c\u0283\u013d\u0285\u013e\u0287\u013f\u0289\u0140" +
					"\u028b\u0141\u028d\u0142\u028f\u0143\u0291\u0144\u0293\u0145\u0295\u0146" +
					"\u0297\u0147\u0299\u0148\u029b\u0149\u029d\u014a\u029f\u014b\3\2\n\3\2" +
					"\62\63\3\2\62;\3\2\63;\3\2\62\62\6\2\62;C\\aac|\n\2)),,/\60\62<C\\aac" +
					"|\u0080\u0080\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u10bd\2\3\3\2\2\2\2\5" +
					"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2" +
					"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33" +
					"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2" +
					"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2" +
					"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2" +
					"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K" +
					"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2" +
					"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2" +
					"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q" +
					"\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2" +
					"\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087" +
					"\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2" +
					"\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099" +
					"\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2" +
					"\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab" +
					"\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2" +
					"\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2\2\2\u00bd" +
					"\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5\3\2\2" +
					"\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2\2\2\u00cf" +
					"\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7\3\2\2" +
					"\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2\2\2\u00df\3\2\2\2\2\u00e1" +
					"\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9\3\2\2" +
					"\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2\2\2\u00ef\3\2\2\2\2\u00f1\3\2\2\2\2\u00f3" +
					"\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7\3\2\2\2\2\u00f9\3\2\2\2\2\u00fb\3\2\2" +
					"\2\2\u00fd\3\2\2\2\2\u00ff\3\2\2\2\2\u0101\3\2\2\2\2\u0103\3\2\2\2\2\u0105" +
					"\3\2\2\2\2\u0107\3\2\2\2\2\u0109\3\2\2\2\2\u010b\3\2\2\2\2\u010d\3\2\2" +
					"\2\2\u010f\3\2\2\2\2\u0111\3\2\2\2\2\u0113\3\2\2\2\2\u0115\3\2\2\2\2\u0117" +
					"\3\2\2\2\2\u0119\3\2\2\2\2\u011b\3\2\2\2\2\u011d\3\2\2\2\2\u011f\3\2\2" +
					"\2\2\u0121\3\2\2\2\2\u0123\3\2\2\2\2\u0125\3\2\2\2\2\u0127\3\2\2\2\2\u0129" +
					"\3\2\2\2\2\u012b\3\2\2\2\2\u012d\3\2\2\2\2\u012f\3\2\2\2\2\u0131\3\2\2" +
					"\2\2\u0133\3\2\2\2\2\u0135\3\2\2\2\2\u0137\3\2\2\2\2\u0139\3\2\2\2\2\u013b" +
					"\3\2\2\2\2\u013d\3\2\2\2\2\u013f\3\2\2\2\2\u0141\3\2\2\2\2\u0143\3\2\2" +
					"\2\2\u0145\3\2\2\2\2\u0147\3\2\2\2\2\u0149\3\2\2\2\2\u014b\3\2\2\2\2\u014d" +
					"\3\2\2\2\2\u014f\3\2\2\2\2\u0151\3\2\2\2\2\u0153\3\2\2\2\2\u0155\3\2\2" +
					"\2\2\u0157\3\2\2\2\2\u0159\3\2\2\2\2\u015b\3\2\2\2\2\u015d\3\2\2\2\2\u015f" +
					"\3\2\2\2\2\u0161\3\2\2\2\2\u0163\3\2\2\2\2\u0165\3\2\2\2\2\u0167\3\2\2" +
					"\2\2\u0169\3\2\2\2\2\u016b\3\2\2\2\2\u016d\3\2\2\2\2\u016f\3\2\2\2\2\u0171" +
					"\3\2\2\2\2\u0173\3\2\2\2\2\u0175\3\2\2\2\2\u0177\3\2\2\2\2\u0179\3\2\2" +
					"\2\2\u017b\3\2\2\2\2\u017d\3\2\2\2\2\u017f\3\2\2\2\2\u0181\3\2\2\2\2\u0183" +
					"\3\2\2\2\2\u0185\3\2\2\2\2\u0187\3\2\2\2\2\u0189\3\2\2\2\2\u018b\3\2\2" +
					"\2\2\u018d\3\2\2\2\2\u018f\3\2\2\2\2\u0191\3\2\2\2\2\u0193\3\2\2\2\2\u0195" +
					"\3\2\2\2\2\u0197\3\2\2\2\2\u0199\3\2\2\2\2\u019b\3\2\2\2\2\u019d\3\2\2" +
					"\2\2\u019f\3\2\2\2\2\u01a1\3\2\2\2\2\u01a3\3\2\2\2\2\u01a5\3\2\2\2\2\u01a7" +
					"\3\2\2\2\2\u01a9\3\2\2\2\2\u01ab\3\2\2\2\2\u01ad\3\2\2\2\2\u01bb\3\2\2" +
					"\2\2\u01bd\3\2\2\2\2\u01bf\3\2\2\2\2\u01c1\3\2\2\2\2\u01c3\3\2\2\2\2\u01c5" +
					"\3\2\2\2\2\u01c7\3\2\2\2\2\u01c9\3\2\2\2\2\u01cb\3\2\2\2\2\u01cd\3\2\2" +
					"\2\2\u01cf\3\2\2\2\2\u01d1\3\2\2\2\2\u01d3\3\2\2\2\2\u01d5\3\2\2\2\2\u01d7" +
					"\3\2\2\2\2\u01d9\3\2\2\2\2\u01db\3\2\2\2\2\u01dd\3\2\2\2\2\u01df\3\2\2" +
					"\2\2\u01e1\3\2\2\2\2\u01e3\3\2\2\2\2\u01e5\3\2\2\2\2\u01e7\3\2\2\2\2\u01e9" +
					"\3\2\2\2\2\u01eb\3\2\2\2\2\u01ed\3\2\2\2\2\u01ef\3\2\2\2\2\u01f1\3\2\2" +
					"\2\2\u01f3\3\2\2\2\2\u01f5\3\2\2\2\2\u01f7\3\2\2\2\2\u01f9\3\2\2\2\2\u01fb" +
					"\3\2\2\2\2\u01fd\3\2\2\2\2\u01ff\3\2\2\2\2\u0201\3\2\2\2\2\u0203\3\2\2" +
					"\2\2\u0205\3\2\2\2\2\u0207\3\2\2\2\2\u0209\3\2\2\2\2\u020b\3\2\2\2\2\u020d" +
					"\3\2\2\2\2\u020f\3\2\2\2\2\u0211\3\2\2\2\2\u0213\3\2\2\2\2\u0215\3\2\2" +
					"\2\2\u0217\3\2\2\2\2\u0219\3\2\2\2\2\u021b\3\2\2\2\2\u021d\3\2\2\2\2\u021f" +
					"\3\2\2\2\2\u0221\3\2\2\2\2\u0223\3\2\2\2\2\u0225\3\2\2\2\2\u0227\3\2\2" +
					"\2\2\u0229\3\2\2\2\2\u022b\3\2\2\2\2\u022d\3\2\2\2\2\u022f\3\2\2\2\2\u0231" +
					"\3\2\2\2\2\u0233\3\2\2\2\2\u0235\3\2\2\2\2\u0237\3\2\2\2\2\u0239\3\2\2" +
					"\2\2\u023b\3\2\2\2\2\u023d\3\2\2\2\2\u023f\3\2\2\2\2\u0241\3\2\2\2\2\u0243" +
					"\3\2\2\2\2\u0245\3\2\2\2\2\u0247\3\2\2\2\2\u0249\3\2\2\2\2\u024b\3\2\2" +
					"\2\2\u024d\3\2\2\2\2\u024f\3\2\2\2\2\u0251\3\2\2\2\2\u0253\3\2\2\2\2\u0255" +
					"\3\2\2\2\2\u0257\3\2\2\2\2\u0259\3\2\2\2\2\u025b\3\2\2\2\2\u025d\3\2\2" +
					"\2\2\u025f\3\2\2\2\2\u0261\3\2\2\2\2\u0263\3\2\2\2\2\u0265\3\2\2\2\2\u0267" +
					"\3\2\2\2\2\u0269\3\2\2\2\2\u026b\3\2\2\2\2\u026d\3\2\2\2\2\u026f\3\2\2" +
					"\2\2\u0271\3\2\2\2\2\u0273\3\2\2\2\2\u0275\3\2\2\2\2\u0277\3\2\2\2\2\u0279" +
					"\3\2\2\2\2\u027b\3\2\2\2\2\u027d\3\2\2\2\2\u027f\3\2\2\2\2\u0281\3\2\2" +
					"\2\2\u0283\3\2\2\2\2\u0285\3\2\2\2\2\u0287\3\2\2\2\2\u0289\3\2\2\2\2\u028b" +
					"\3\2\2\2\2\u028d\3\2\2\2\2\u028f\3\2\2\2\2\u0291\3\2\2\2\2\u0293\3\2\2" +
					"\2\2\u0295\3\2\2\2\2\u0297\3\2\2\2\2\u0299\3\2\2\2\2\u029b\3\2\2\2\2\u029d" +
					"\3\2\2\2\2\u029f\3\2\2\2\3\u02a1\3\2\2\2\5\u02ab\3\2\2\2\7\u02b3\3\2\2" +
					"\2\t\u02be\3\2\2\2\13\u02c0\3\2\2\2\r\u02c2\3\2\2\2\17\u02c4\3\2\2\2\21" +
					"\u02ce\3\2\2\2\23\u02d8\3\2\2\2\25\u02e2\3\2\2\2\27\u02ee\3\2\2\2\31\u02f9" +
					"\3\2\2\2\33\u0309\3\2\2\2\35\u0313\3\2\2\2\37\u0324\3\2\2\2!\u0340\3\2" +
					"\2\2#\u035a\3\2\2\2%\u0377\3\2\2\2\'\u037f\3\2\2\2)\u0387\3\2\2\2+\u038f" +
					"\3\2\2\2-\u0397\3\2\2\2/\u039f\3\2\2\2\61\u03a7\3\2\2\2\63\u03b2\3\2\2" +
					"\2\65\u03bb\3\2\2\2\67\u03c4\3\2\2\29\u03cf\3\2\2\2;\u03e3\3\2\2\2=\u03f1" +
					"\3\2\2\2?\u0401\3\2\2\2A\u0413\3\2\2\2C\u041b\3\2\2\2E\u0423\3\2\2\2G" +
					"\u0438\3\2\2\2I\u0444\3\2\2\2K\u0450\3\2\2\2M\u0458\3\2\2\2O\u0462\3\2" +
					"\2\2Q\u0469\3\2\2\2S\u0471\3\2\2\2U\u047f\3\2\2\2W\u048a\3\2\2\2Y\u049a" +
					"\3\2\2\2[\u04a3\3\2\2\2]\u04b4\3\2\2\2_\u04c5\3\2\2\2a\u04d6\3\2\2\2c" +
					"\u04e1\3\2\2\2e\u04f0\3\2\2\2g\u04fa\3\2\2\2i\u050f\3\2\2\2k\u051d\3\2" +
					"\2\2m\u052b\3\2\2\2o\u0533\3\2\2\2q\u054f\3\2\2\2s\u055b\3\2\2\2u\u0567" +
					"\3\2\2\2w\u056f\3\2\2\2y\u057d\3\2\2\2{\u0582\3\2\2\2}\u0598\3\2\2\2\177" +
					"\u05a1\3\2\2\2\u0081\u05b0\3\2\2\2\u0083\u05c1\3\2\2\2\u0085\u05cb\3\2" +
					"\2\2\u0087\u05d6\3\2\2\2\u0089\u05e5\3\2\2\2\u008b\u05f0\3\2\2\2\u008d" +
					"\u05f8\3\2\2\2\u008f\u0605\3\2\2\2\u0091\u0616\3\2\2\2\u0093\u0626\3\2" +
					"\2\2\u0095\u062f\3\2\2\2\u0097\u063b\3\2\2\2\u0099\u0644\3\2\2\2\u009b" +
					"\u064c\3\2\2\2\u009d\u0658\3\2\2\2\u009f\u0669\3\2\2\2\u00a1\u0673\3\2" +
					"\2\2\u00a3\u0683\3\2\2\2\u00a5\u0691\3\2\2\2\u00a7\u069a\3\2\2\2\u00a9" +
					"\u06a5\3\2\2\2\u00ab\u06b2\3\2\2\2\u00ad\u06bf\3\2\2\2\u00af\u06cc\3\2" +
					"\2\2\u00b1\u06d9\3\2\2\2\u00b3\u06e8\3\2\2\2\u00b5\u06f7\3\2\2\2\u00b7" +
					"\u070b\3\2\2\2\u00b9\u0717\3\2\2\2\u00bb\u0725\3\2\2\2\u00bd\u0734\3\2" +
					"\2\2\u00bf\u0745\3\2\2\2\u00c1\u0756\3\2\2\2\u00c3\u0769\3\2\2\2\u00c5" +
					"\u077c\3\2\2\2\u00c7\u078d\3\2\2\2\u00c9\u07a1\3\2\2\2\u00cb\u07b3\3\2" +
					"\2\2\u00cd\u07bf\3\2\2\2\u00cf\u07c8\3\2\2\2\u00d1\u07cf\3\2\2\2\u00d3" +
					"\u07e1\3\2\2\2\u00d5\u07f0\3\2\2\2\u00d7\u07f6\3\2\2\2\u00d9\u0804\3\2" +
					"\2\2\u00db\u080f\3\2\2\2\u00dd\u0825\3\2\2\2\u00df\u0832\3\2\2\2\u00e1" +
					"\u083e\3\2\2\2\u00e3\u0858\3\2\2\2\u00e5\u0865\3\2\2\2\u00e7\u0873\3\2" +
					"\2\2\u00e9\u0881\3\2\2\2\u00eb\u088e\3\2\2\2\u00ed\u089d\3\2\2\2\u00ef" +
					"\u08a7\3\2\2\2\u00f1\u08af\3\2\2\2\u00f3\u08b6\3\2\2\2\u00f5\u08cc\3\2" +
					"\2\2\u00f7\u08de\3\2\2\2\u00f9\u08eb\3\2\2\2\u00fb\u08f6\3\2\2\2\u00fd" +
					"\u0904\3\2\2\2\u00ff\u090e\3\2\2\2\u0101\u0910\3\2\2\2\u0103\u0912\3\2" +
					"\2\2\u0105\u0928\3\2\2\2\u0107\u092f\3\2\2\2\u0109\u0935\3\2\2\2\u010b" +
					"\u093d\3\2\2\2\u010d\u0943\3\2\2\2\u010f\u094d\3\2\2\2\u0111\u0957\3\2" +
					"\2\2\u0113\u095e\3\2\2\2\u0115\u0964\3\2\2\2\u0117\u0974\3\2\2\2\u0119" +
					"\u0984\3\2\2\2\u011b\u098b\3\2\2\2\u011d\u0994\3\2\2\2\u011f\u099b\3\2" +
					"\2\2\u0121\u09a6\3\2\2\2\u0123\u09b3\3\2\2\2\u0125\u09b9\3\2\2\2\u0127" +
					"\u09c5\3\2\2\2\u0129\u09c9\3\2\2\2\u012b\u09ce\3\2\2\2\u012d\u09d4\3\2" +
					"\2\2\u012f\u09d9\3\2\2\2\u0131\u09e4\3\2\2\2\u0133\u09ec\3\2\2\2\u0135" +
					"\u09fb\3\2\2\2\u0137\u0a00\3\2\2\2\u0139\u0a16\3\2\2\2\u013b\u0a2c\3\2" +
					"\2\2\u013d\u0a33\3\2\2\2\u013f\u0a3a\3\2\2\2\u0141\u0a4c\3\2\2\2\u0143" +
					"\u0a65\3\2\2\2\u0145\u0a7e\3\2\2\2\u0147\u0a83\3\2\2\2\u0149\u0a9e\3\2" +
					"\2\2\u014b\u0ab9\3\2\2\2\u014d\u0ac5\3\2\2\2\u014f\u0aca\3\2\2\2\u0151" +
					"\u0ad6\3\2\2\2\u0153\u0ae3\3\2\2\2\u0155\u0aef\3\2\2\2\u0157\u0b03\3\2" +
					"\2\2\u0159\u0b14\3\2\2\2\u015b\u0b24\3\2\2\2\u015d\u0b32\3\2\2\2\u015f" +
					"\u0b48\3\2\2\2\u0161\u0b5a\3\2\2\2\u0163\u0b66\3\2\2\2\u0165\u0b72\3\2" +
					"\2\2\u0167\u0b7e\3\2\2\2\u0169\u0b8d\3\2\2\2\u016b\u0b9c\3\2\2\2\u016d" +
					"\u0bad\3\2\2\2\u016f\u0bc4\3\2\2\2\u0171\u0bd7\3\2\2\2\u0173\u0bee\3\2" +
					"\2\2\u0175\u0c07\3\2\2\2\u0177\u0c19\3\2\2\2\u0179\u0c3b\3\2\2\2\u017b" +
					"\u0c4b\3\2\2\2\u017d\u0c5e\3\2\2\2\u017f\u0c70\3\2\2\2\u0181\u0c81\3\2" +
					"\2\2\u0183\u0c94\3\2\2\2\u0185\u0ca8\3\2\2\2\u0187\u0cbf\3\2\2\2\u0189" +
					"\u0cd2\3\2\2\2\u018b\u0cdf\3\2\2\2\u018d\u0ce5\3\2\2\2\u018f\u0ce7\3\2" +
					"\2\2\u0191\u0ce9\3\2\2\2\u0193\u0ceb\3\2\2\2\u0195\u0ced\3\2\2\2\u0197" +
					"\u0cf1\3\2\2\2\u0199\u0cf5\3\2\2\2\u019b\u0cf7\3\2\2\2\u019d\u0cfa\3\2" +
					"\2\2\u019f\u0cfd\3\2\2\2\u01a1\u0d01\3\2\2\2\u01a3\u0d08\3\2\2\2\u01a5" +
					"\u0d0d\3\2\2\2\u01a7\u0d13\3\2\2\2\u01a9\u0d19\3\2\2\2\u01ab\u0d1e\3\2" +
					"\2\2\u01ad\u0d25\3\2\2\2\u01af\u0d32\3\2\2\2\u01b1\u0d34\3\2\2\2\u01b3" +
					"\u0d36\3\2\2\2\u01b5\u0d38\3\2\2\2\u01b7\u0d3a\3\2\2\2\u01b9\u0d3c\3\2" +
					"\2\2\u01bb\u0d3e\3\2\2\2\u01bd\u0d40\3\2\2\2\u01bf\u0d45\3\2\2\2\u01c1" +
					"\u0d4b\3\2\2\2\u01c3\u0d52\3\2\2\2\u01c5\u0d5b\3\2\2\2\u01c7\u0d61\3\2" +
					"\2\2\u01c9\u0d68\3\2\2\2\u01cb\u0d72\3\2\2\2\u01cd\u0d78\3\2\2\2\u01cf" +
					"\u0d7c\3\2\2\2\u01d1\u0d86\3\2\2\2\u01d3\u0d8e\3\2\2\2\u01d5\u0d94\3\2" +
					"\2\2\u01d7\u0d9b\3\2\2\2\u01d9\u0da2\3\2\2\2\u01db\u0da9\3\2\2\2\u01dd" +
					"\u0dad\3\2\2\2\u01df\u0db7\3\2\2\2\u01e1\u0dbd\3\2\2\2\u01e3\u0dc6\3\2" +
					"\2\2\u01e5\u0dca\3\2\2\2\u01e7\u0dd1\3\2\2\2\u01e9\u0dd5\3\2\2\2\u01eb" +
					"\u0ddf\3\2\2\2\u01ed\u0de5\3\2\2\2\u01ef\u0dea\3\2\2\2\u01f1\u0df3\3\2" +
					"\2\2\u01f3\u0dfe\3\2\2\2\u01f5\u0e06\3\2\2\2\u01f7\u0e0e\3\2\2\2\u01f9" +
					"\u0e14\3\2\2\2\u01fb\u0e1f\3\2\2\2\u01fd\u0e30\3\2\2\2\u01ff\u0e38\3\2" +
					"\2\2\u0201\u0e40\3\2\2\2\u0203\u0e49\3\2\2\2\u0205\u0e53\3\2\2\2\u0207" +
					"\u0e5e\3\2\2\2\u0209\u0e6b\3\2\2\2\u020b\u0e77\3\2\2\2\u020d\u0e7d\3\2" +
					"\2\2\u020f\u0e83\3\2\2\2\u0211\u0e87\3\2\2\2\u0213\u0e8c\3\2\2\2\u0215" +
					"\u0e93\3\2\2\2\u0217\u0e98\3\2\2\2\u0219\u0e9d\3\2\2\2\u021b\u0ea6\3\2" +
					"\2\2\u021d\u0eb1\3\2\2\2\u021f\u0eb8\3\2\2\2\u0221\u0ec5\3\2\2\2\u0223" +
					"\u0ece\3\2\2\2\u0225\u0ed3\3\2\2\2\u0227\u0edc\3\2\2\2\u0229\u0ee7\3\2" +
					"\2\2\u022b\u0eeb\3\2\2\2\u022d\u0ef1\3\2\2\2\u022f\u0ef7\3\2\2\2\u0231" +
					"\u0efd\3\2\2\2\u0233\u0f03\3\2\2\2\u0235\u0f09\3\2\2\2\u0237\u0f10\3\2" +
					"\2\2\u0239\u0f16\3\2\2\2\u023b\u0f1c\3\2\2\2\u023d\u0f21\3\2\2\2\u023f" +
					"\u0f26\3\2\2\2\u0241\u0f2b\3\2\2\2\u0243\u0f32\3\2\2\2\u0245\u0f3b\3\2" +
					"\2\2\u0247\u0f40\3\2\2\2\u0249\u0f45\3\2\2\2\u024b\u0f4d\3\2\2\2\u024d" +
					"\u0f55\3\2\2\2\u024f\u0f5a\3\2\2\2\u0251\u0f5f\3\2\2\2\u0253\u0f69\3\2" +
					"\2\2\u0255\u0f6e\3\2\2\2\u0257\u0f74\3\2\2\2\u0259\u0f7c\3\2\2\2\u025b" +
					"\u0f85\3\2\2\2\u025d\u0f8f\3\2\2\2\u025f\u0f99\3\2\2\2\u0261\u0f9f\3\2" +
					"\2\2\u0263\u0fa5\3\2\2\2\u0265\u0fab\3\2\2\2\u0267\u0fb6\3\2\2\2\u0269" +
					"\u0fbb\3\2\2\2\u026b\u0fc1\3\2\2\2\u026d\u0fc9\3\2\2\2\u026f\u0fce\3\2" +
					"\2\2\u0271\u0fd5\3\2\2\2\u0273\u0fe0\3\2\2\2\u0275\u0fe8\3\2\2\2\u0277" +
					"\u0ff1\3\2\2\2\u0279\u0ffd\3\2\2\2\u027b\u1009\3\2\2\2\u027d\u100f\3\2" +
					"\2\2\u027f\u1014\3\2\2\2\u0281\u101b\3\2\2\2\u0283\u1020\3\2\2\2\u0285" +
					"\u1027\3\2\2\2\u0287\u1032\3\2\2\2\u0289\u103a\3\2\2\2\u028b\u103f\3\2" +
					"\2\2\u028d\u104f\3\2\2\2\u028f\u1056\3\2\2\2\u0291\u105f\3\2\2\2\u0293" +
					"\u1066\3\2\2\2\u0295\u106e\3\2\2\2\u0297\u1076\3\2\2\2\u0299\u108b\3\2" +
					"\2\2\u029b\u1096\3\2\2\2\u029d\u109c\3\2\2\2\u029f\u10ac\3\2\2\2\u02a1" +
					"\u02a2\7u\2\2\u02a2\u02a3\7g\2\2\u02a3\u02a4\7v\2\2\u02a4\u02a5\7a\2\2" +
					"\u02a5\u02a6\7d\2\2\u02a6\u02a7\7g\2\2\u02a7\u02a8\7i\2\2\u02a8\u02a9" +
					"\7k\2\2\u02a9\u02aa\7p\2\2\u02aa\4\3\2\2\2\u02ab\u02ac\7u\2\2\u02ac\u02ad" +
					"\7g\2\2\u02ad\u02ae\7v\2\2\u02ae\u02af\7a\2\2\u02af\u02b0\7g\2\2\u02b0" +
					"\u02b1\7p\2\2\u02b1\u02b2\7f\2\2\u02b2\6\3\2\2\2\u02b3\u02b4\7u\2\2\u02b4" +
					"\u02b5\7n\2\2\u02b5\u02b6\7q\2\2\u02b6\u02b7\7v\2\2\u02b7\u02b8\7a\2\2" +
					"\u02b8\u02b9\7e\2\2\u02b9\u02ba\7j\2\2\u02ba\u02bb\7g\2\2\u02bb\u02bc" +
					"\7u\2\2\u02bc\u02bd\7v\2\2\u02bd\b\3\2\2\2\u02be\u02bf\7?\2\2\u02bf\n" +
					"\3\2\2\2\u02c0\u02c1\7}\2\2\u02c1\f\3\2\2\2\u02c2\u02c3\7\177\2\2\u02c3" +
					"\16\3\2\2\2\u02c4\u02c5\7u\2\2\u02c5\u02c6\7n\2\2\u02c6\u02c7\7q\2\2\u02c7" +
					"\u02c8\7v\2\2\u02c8\u02c9\7a\2\2\u02c9\u02ca\7n\2\2\u02ca\u02cb\7g\2\2" +
					"\u02cb\u02cc\7i\2\2\u02cc\u02cd\7u\2\2\u02cd\20\3\2\2\2\u02ce\u02cf\7" +
					"u\2\2\u02cf\u02d0\7n\2\2\u02d0\u02d1\7q\2\2\u02d1\u02d2\7v\2\2\u02d2\u02d3" +
					"\7a\2\2\u02d3\u02d4\7h\2\2\u02d4\u02d5\7g\2\2\u02d5\u02d6\7g\2\2\u02d6" +
					"\u02d7\7v\2\2\u02d7\22\3\2\2\2\u02d8\u02d9\7u\2\2\u02d9\u02da\7n\2\2\u02da" +
					"\u02db\7q\2\2\u02db\u02dc\7v\2\2\u02dc\u02dd\7a\2\2\u02dd\u02de\7j\2\2" +
					"\u02de\u02df\7g\2\2\u02df\u02e0\7c\2\2\u02e0\u02e1\7f\2\2\u02e1\24\3\2" +
					"\2\2\u02e2\u02e3\7u\2\2\u02e3\u02e4\7n\2\2\u02e4\u02e5\7q\2\2\u02e5\u02e6" +
					"\7v\2\2\u02e6\u02e7\7a\2\2\u02e7\u02e8\7i\2\2\u02e8\u02e9\7n\2\2\u02e9" +
					"\u02ea\7q\2\2\u02ea\u02eb\7x\2\2\u02eb\u02ec\7g\2\2\u02ec\u02ed\7u\2\2" +
					"\u02ed\26\3\2\2\2\u02ee\u02ef\7u\2\2\u02ef\u02f0\7n\2\2\u02f0\u02f1\7" +
					"q\2\2\u02f1\u02f2\7v\2\2\u02f2\u02f3\7a\2\2\u02f3\u02f4\7n\2\2\u02f4\u02f5" +
					"\7j\2\2\u02f5\u02f6\7c\2\2\u02f6\u02f7\7p\2\2\u02f7\u02f8\7f\2\2\u02f8" +
					"\30\3\2\2\2\u02f9\u02fa\7u\2\2\u02fa\u02fb\7n\2\2\u02fb\u02fc\7q\2\2\u02fc" +
					"\u02fd\7v\2\2\u02fd\u02fe\7a\2\2\u02fe\u02ff\7c\2\2\u02ff\u0300\7f\2\2" +
					"\u0300\u0301\7f\2\2\u0301\u0302\7k\2\2\u0302\u0303\7v\2\2\u0303\u0304" +
					"\7k\2\2\u0304\u0305\7q\2\2\u0305\u0306\7p\2\2\u0306\u0307\7c\2\2\u0307" +
					"\u0308\7n\2\2\u0308\32\3\2\2\2\u0309\u030a\7u\2\2\u030a\u030b\7g\2\2\u030b" +
					"\u030c\7v\2\2\u030c\u030d\7a\2\2\u030d\u030e\7u\2\2\u030e\u030f\7m\2\2" +
					"\u030f\u0310\7k\2\2\u0310\u0311\7n\2\2\u0311\u0312\7n\2\2\u0312\34\3\2" +
					"\2\2\u0313\u0314\7u\2\2\u0314\u0315\7g\2\2\u0315\u0316\7v\2\2\u0316\u0317" +
					"\7a\2\2\u0317\u0318\7g\2\2\u0318\u0319\7h\2\2\u0319\u031a\7h\2\2\u031a" +
					"\u031b\7g\2\2\u031b\u031c\7e\2\2\u031c\u031d\7v\2\2\u031d\u031e\7a\2\2" +
					"\u031e\u031f\7u\2\2\u031f\u0320\7m\2\2\u0320\u0321\7k\2\2\u0321\u0322" +
					"\7n\2\2\u0322\u0323\7n\2\2\u0323\36\3\2\2\2\u0324\u0325\7u\2\2\u0325\u0326" +
					"\7g\2\2\u0326\u0327\7v\2\2\u0327\u0328\7a\2\2\u0328\u0329\7c\2\2\u0329" +
					"\u032a\7f\2\2\u032a\u032b\7f\2\2\u032b\u032c\7k\2\2\u032c\u032d\7v\2\2" +
					"\u032d\u032e\7k\2\2\u032e\u032f\7q\2\2\u032f\u0330\7p\2\2\u0330\u0331" +
					"\7c\2\2\u0331\u0332\7n\2\2\u0332\u0333\7a\2\2\u0333\u0334\7g\2\2\u0334" +
					"\u0335\7h\2\2\u0335\u0336\7h\2\2\u0336\u0337\7g\2\2\u0337\u0338\7e\2\2" +
					"\u0338\u0339\7v\2\2\u0339\u033a\7a\2\2\u033a\u033b\7u\2\2\u033b\u033c" +
					"\7m\2\2\u033c\u033d\7k\2\2\u033d\u033e\7n\2\2\u033e\u033f\7n\2\2\u033f" +
					" \3\2\2\2\u0340\u0341\7u\2\2\u0341\u0342\7g\2\2\u0342\u0343\7v\2\2\u0343" +
					"\u0344\7a\2\2\u0344\u0345\7c\2\2\u0345\u0346\7f\2\2\u0346\u0347\7f\2\2" +
					"\u0347\u0348\7k\2\2\u0348\u0349\7v\2\2\u0349\u034a\7k\2\2\u034a\u034b" +
					"\7q\2\2\u034b\u034c\7p\2\2\u034c\u034d\7c\2\2\u034d\u034e\7n\2\2\u034e" +
					"\u034f\7\64\2\2\u034f\u0350\7a\2\2\u0350\u0351\7e\2\2\u0351\u0352\7q\2" +
					"\2\u0352\u0353\7p\2\2\u0353\u0354\7f\2\2\u0354\u0355\7k\2\2\u0355\u0356" +
					"\7v\2\2\u0356\u0357\7k\2\2\u0357\u0358\7q\2\2\u0358\u0359\7p\2\2\u0359" +
					"\"\3\2\2\2\u035a\u035b\7u\2\2\u035b\u035c\7g\2\2\u035c\u035d\7v\2\2\u035d" +
					"\u035e\7a\2\2\u035e\u035f\7c\2\2\u035f\u0360\7f\2\2\u0360\u0361\7f\2\2" +
					"\u0361\u0362\7k\2\2\u0362\u0363\7v\2\2\u0363\u0364\7k\2\2\u0364\u0365" +
					"\7q\2\2\u0365\u0366\7p\2\2\u0366\u0367\7c\2\2\u0367\u0368\7n\2\2\u0368" +
					"\u0369\7\64\2\2\u0369\u036a\7a\2\2\u036a\u036b\7g\2\2\u036b\u036c\7h\2" +
					"\2\u036c\u036d\7h\2\2\u036d\u036e\7g\2\2\u036e\u036f\7e\2\2\u036f\u0370" +
					"\7v\2\2\u0370\u0371\7a\2\2\u0371\u0372\7u\2\2\u0372\u0373\7m\2\2\u0373" +
					"\u0374\7k\2\2\u0374\u0375\7n\2\2\u0375\u0376\7n\2\2\u0376$\3\2\2\2\u0377" +
					"\u0378\7u\2\2\u0378\u0379\7v\2\2\u0379\u037a\7t\2\2\u037a\u037b\7a\2\2" +
					"\u037b\u037c\7k\2\2\u037c\u037d\7p\2\2\u037d\u037e\7e\2\2\u037e&\3\2\2" +
					"\2\u037f\u0380\7e\2\2\u0380\u0381\7q\2\2\u0381\u0382\7p\2\2\u0382\u0383" +
					"\7a\2\2\u0383\u0384\7k\2\2\u0384\u0385\7p\2\2\u0385\u0386\7e\2\2\u0386" +
					"(\3\2\2\2\u0387\u0388\7f\2\2\u0388\u0389\7g\2\2\u0389\u038a\7z\2\2\u038a" +
					"\u038b\7a\2\2\u038b\u038c\7k\2\2\u038c\u038d\7p\2\2\u038d\u038e\7e\2\2" +
					"\u038e*\3\2\2\2\u038f\u0390\7k\2\2\u0390\u0391\7p\2\2\u0391\u0392\7v\2" +
					"\2\u0392\u0393\7a\2\2\u0393\u0394\7k\2\2\u0394\u0395\7p\2\2\u0395\u0396" +
					"\7e\2\2\u0396,\3\2\2\2\u0397\u0398\7o\2\2\u0398\u0399\7g\2\2\u0399\u039a" +
					"\7p\2\2\u039a\u039b\7a\2\2\u039b\u039c\7k\2\2\u039c\u039d\7p\2\2\u039d" +
					"\u039e\7e\2\2\u039e.\3\2\2\2\u039f\u03a0\7y\2\2\u03a0\u03a1\7k\2\2\u03a1" +
					"\u03a2\7v\2\2\u03a2\u03a3\7a\2\2\u03a3\u03a4\7k\2\2\u03a4\u03a5\7p\2\2" +
					"\u03a5\u03a6\7e\2\2\u03a6\60\3\2\2\2\u03a7\u03a8\7k\2\2\u03a8\u03a9\7" +
					"v\2\2\u03a9\u03aa\7g\2\2\u03aa\u03ab\7o\2\2\u03ab\u03ac\7a\2\2\u03ac\u03ad" +
					"\7d\2\2\u03ad\u03ae\7g\2\2\u03ae\u03af\7i\2\2\u03af\u03b0\7k\2\2\u03b0" +
					"\u03b1\7p\2\2\u03b1\62\3\2\2\2\u03b2\u03b3\7k\2\2\u03b3\u03b4\7v\2\2\u03b4" +
					"\u03b5\7g\2\2\u03b5\u03b6\7o\2\2\u03b6\u03b7\7a\2\2\u03b7\u03b8\7g\2\2" +
					"\u03b8\u03b9\7p\2\2\u03b9\u03ba\7f\2\2\u03ba\64\3\2\2\2\u03bb\u03bc\7" +
					"e\2\2\u03bc\u03bd\7c\2\2\u03bd\u03be\7p\2\2\u03be\u03bf\7a\2\2\u03bf\u03c0" +
					"\7o\2\2\u03c0\u03c1\7q\2\2\u03c1\u03c2\7x\2\2\u03c2\u03c3\7g\2\2\u03c3" +
					"\66\3\2\2\2\u03c4\u03c5\7k\2\2\u03c5\u03c6\7u\2\2\u03c6\u03c7\7a\2\2\u03c7" +
					"\u03c8\7r\2\2\u03c8\u03c9\7t\2\2\u03c9\u03ca\7g\2\2\u03ca\u03cb\7o\2\2" +
					"\u03cb\u03cc\7k\2\2\u03cc\u03cd\7w\2\2\u03cd\u03ce\7o\2\2\u03ce8\3\2\2" +
					"\2\u03cf\u03d0\7k\2\2\u03d0\u03d1\7u\2\2\u03d1\u03d2\7a\2\2\u03d2\u03d3" +
					"\7q\2\2\u03d3\u03d4\7n\2\2\u03d4\u03d5\7{\2\2\u03d5\u03d6\7o\2\2\u03d6" +
					"\u03d7\7r\2\2\u03d7\u03d8\7k\2\2\u03d8\u03d9\7c\2\2\u03d9\u03da\7f\2\2" +
					"\u03da\u03db\7a\2\2\u03db\u03dc\7e\2\2\u03dc\u03dd\7c\2\2\u03dd\u03de" +
					"\7p\2\2\u03de\u03df\7a\2\2\u03df\u03e0\7w\2\2\u03e0\u03e1\7u\2\2\u03e1" +
					"\u03e2\7g\2\2\u03e2:\3\2\2\2\u03e3\u03e4\7w\2\2\u03e4\u03e5\7u\2\2\u03e5" +
					"\u03e6\7g\2\2\u03e6\u03e7\7a\2\2\u03e7\u03e8\7e\2\2\u03e8\u03e9\7q\2\2" +
					"\u03e9\u03ea\7p\2\2\u03ea\u03eb\7f\2\2\u03eb\u03ec\7k\2\2\u03ec\u03ed" +
					"\7v\2\2\u03ed\u03ee\7k\2\2\u03ee\u03ef\7q\2\2\u03ef\u03f0\7p\2\2\u03f0" +
					"<\3\2\2\2\u03f1\u03f2\7g\2\2\u03f2\u03f3\7s\2\2\u03f3\u03f4\7w\2\2\u03f4" +
					"\u03f5\7k\2\2\u03f5\u03f6\7r\2\2\u03f6\u03f7\7a\2\2\u03f7\u03f8\7e\2\2" +
					"\u03f8\u03f9\7q\2\2\u03f9\u03fa\7p\2\2\u03fa\u03fb\7f\2\2\u03fb\u03fc" +
					"\7k\2\2\u03fc\u03fd\7v\2\2\u03fd\u03fe\7k\2\2\u03fe\u03ff\7q\2\2\u03ff" +
					"\u0400\7p\2\2\u0400>\3\2\2\2\u0401\u0402\7k\2\2\u0402\u0403\7v\2\2\u0403" +
					"\u0404\7g\2\2\u0404\u0405\7o\2\2\u0405\u0406\7a\2\2\u0406\u0407\7g\2\2" +
					"\u0407\u0408\7s\2\2\u0408\u0409\7w\2\2\u0409\u040a\7k\2\2\u040a\u040b" +
					"\7r\2\2\u040b\u040c\7a\2\2\u040c\u040d\7q\2\2\u040d\u040e\7r\2\2\u040e" +
					"\u040f\7v\2\2\u040f\u0410\7k\2\2\u0410\u0411\7q\2\2\u0411\u0412\7p\2\2" +
					"\u0412@\3\2\2\2\u0413\u0414\7g\2\2\u0414\u0415\7e\2\2\u0415\u0416\7a\2" +
					"\2\u0416\u0417\7t\2\2\u0417\u0418\7c\2\2\u0418\u0419\7e\2\2\u0419\u041a" +
					"\7g\2\2\u041aB\3\2\2\2\u041b\u041c\7w\2\2\u041c\u041d\7e\2\2\u041d\u041e" +
					"\7a\2\2\u041e\u041f\7t\2\2\u041f\u0420\7c\2\2\u0420\u0421\7e\2\2\u0421" +
					"\u0422\7g\2\2\u0422D\3\2\2\2\u0423\u0424\7w\2\2\u0424\u0425\7e\2\2\u0425" +
					"\u0426\7a\2\2\u0426\u0427\7v\2\2\u0427\u0428\7t\2\2\u0428\u0429\7c\2\2" +
					"\u0429\u042a\7p\2\2\u042a\u042b\7u\2\2\u042b\u042c\7o\2\2\u042c\u042d" +
					"\7q\2\2\u042d\u042e\7f\2\2\u042e\u042f\7g\2\2\u042f\u0430\7a\2\2\u0430" +
					"\u0431\7g\2\2\u0431\u0432\7z\2\2\u0432\u0433\7e\2\2\u0433\u0434\7n\2\2" +
					"\u0434\u0435\7w\2\2\u0435\u0436\7f\2\2\u0436\u0437\7g\2\2\u0437F\3\2\2" +
					"\2\u0438\u0439\7g\2\2\u0439\u043a\7e\2\2\u043a\u043b\7a\2\2\u043b\u043c" +
					"\7e\2\2\u043c\u043d\7c\2\2\u043d\u043e\7v\2\2\u043e\u043f\7g\2\2\u043f" +
					"\u0440\7i\2\2\u0440\u0441\7q\2\2\u0441\u0442\7t\2\2\u0442\u0443\7{\2\2" +
					"\u0443H\3\2\2\2\u0444\u0445\7w\2\2\u0445\u0446\7e\2\2\u0446\u0447\7a\2" +
					"\2\u0447\u0448\7e\2\2\u0448\u0449\7c\2\2\u0449\u044a\7v\2\2\u044a\u044b" +
					"\7g\2\2\u044b\u044c\7i\2\2\u044c\u044d\7q\2\2\u044d\u044e\7t\2\2\u044e" +
					"\u044f\7{\2\2\u044fJ\3\2\2\2\u0450\u0451\7g\2\2\u0451\u0452\7e\2\2\u0452" +
					"\u0453\7a\2\2\u0453\u0454\7j\2\2\u0454\u0455\7g\2\2\u0455\u0456\7t\2\2" +
					"\u0456\u0457\7q\2\2\u0457L\3\2\2\2\u0458\u0459\7g\2\2\u0459\u045a\7e\2" +
					"\2\u045a\u045b\7a\2\2\u045b\u045c\7e\2\2\u045c\u045d\7c\2\2\u045d\u045e" +
					"\7u\2\2\u045e\u045f\7v\2\2\u045f\u0460\7n\2\2\u0460\u0461\7g\2\2\u0461" +
					"N\3\2\2\2\u0462\u0463\7g\2\2\u0463\u0464\7e\2\2\u0464\u0465\7a\2\2\u0465" +
					"\u0466\7u\2\2\u0466\u0467\7g\2\2\u0467\u0468\7z\2\2\u0468P\3\2\2\2\u0469" +
					"\u046a\7g\2\2\u046a\u046b\7e\2\2\u046b\u046c\7a\2\2\u046c\u046d\7c\2\2" +
					"\u046d\u046e\7i\2\2\u046e\u046f\7k\2\2\u046f\u0470\7v\2\2\u0470R\3\2\2" +
					"\2\u0471\u0472\7g\2\2\u0472\u0473\7e\2\2\u0473\u0474\7a\2\2\u0474\u0475" +
					"\7e\2\2\u0475\u0476\7c\2\2\u0476\u0477\7u\2\2\u0477\u0478\7v\2\2\u0478" +
					"\u0479\7n\2\2\u0479\u047a\7g\2\2\u047a\u047b\7a\2\2\u047b\u047c\7p\2\2" +
					"\u047c\u047d\7w\2\2\u047d\u047e\7o\2\2\u047eT\3\2\2\2\u047f\u0480\7g\2" +
					"\2\u0480\u0481\7e\2\2\u0481\u0482\7a\2\2\u0482\u0483\7c\2\2\u0483\u0484" +
					"\7e\2\2\u0484\u0485\7c\2\2\u0485\u0486\7f\2\2\u0486\u0487\7g\2\2\u0487" +
					"\u0488\7o\2\2\u0488\u0489\7{\2\2\u0489V\3\2\2\2\u048a\u048b\7g\2\2\u048b" +
					"\u048c\7e\2\2\u048c\u048d\7a\2\2\u048d\u048e\7u\2\2\u048e\u048f\7q\2\2" +
					"\u048f\u0490\7e\2\2\u0490\u0491\7k\2\2\u0491\u0492\7c\2\2\u0492\u0493" +
					"\7n\2\2\u0493\u0494\7a\2\2\u0494\u0495\7e\2\2\u0495\u0496\7n\2\2\u0496" +
					"\u0497\7c\2\2\u0497\u0498\7u\2\2\u0498\u0499\7u\2\2\u0499X\3\2\2\2\u049a" +
					"\u049b\7w\2\2\u049b\u049c\7e\2\2\u049c\u049d\7a\2\2\u049d\u049e\7n\2\2" +
					"\u049e\u049f\7g\2\2\u049f\u04a0\7x\2\2\u04a0\u04a1\7g\2\2\u04a1\u04a2" +
					"\7n\2\2\u04a2Z\3\2\2\2\u04a3\u04a4\7w\2\2\u04a4\u04a5\7e\2\2\u04a5\u04a6" +
					"\7a\2\2\u04a6\u04a7\7t\2\2\u04a7\u04a8\7g\2\2\u04a8\u04a9\7s\2\2\u04a9" +
					"\u04aa\7w\2\2\u04aa\u04ab\7k\2\2\u04ab\u04ac\7t\2\2\u04ac\u04ad\7g\2\2" +
					"\u04ad\u04ae\7f\2\2\u04ae\u04af\7n\2\2\u04af\u04b0\7g\2\2\u04b0\u04b1" +
					"\7x\2\2\u04b1\u04b2\7g\2\2\u04b2\u04b3\7n\2\2\u04b3\\\3\2\2\2\u04b4\u04b5" +
					"\7g\2\2\u04b5\u04b6\7e\2\2\u04b6\u04b7\7a\2\2\u04b7\u04b8\7t\2\2\u04b8" +
					"\u04b9\7g\2\2\u04b9\u04ba\7s\2\2\u04ba\u04bb\7w\2\2\u04bb\u04bc\7k\2\2" +
					"\u04bc\u04bd\7t\2\2\u04bd\u04be\7g\2\2\u04be\u04bf\7f\2\2\u04bf\u04c0" +
					"\7n\2\2\u04c0\u04c1\7g\2\2\u04c1\u04c2\7x\2\2\u04c2\u04c3\7g\2\2\u04c3" +
					"\u04c4\7n\2\2\u04c4^\3\2\2\2\u04c5\u04c6\7w\2\2\u04c6\u04c7\7e\2\2\u04c7" +
					"\u04c8\7a\2\2\u04c8\u04c9\7t\2\2\u04c9\u04ca\7g\2\2\u04ca\u04cb\7u\2\2" +
					"\u04cb\u04cc\7v\2\2\u04cc\u04cd\7c\2\2\u04cd\u04ce\7t\2\2\u04ce\u04cf" +
					"\7v\2\2\u04cf\u04d0\7a\2\2\u04d0\u04d1\7r\2\2\u04d1\u04d2\7q\2\2\u04d2" +
					"\u04d3\7k\2\2\u04d3\u04d4\7p\2\2\u04d4\u04d5\7v\2\2\u04d5`\3\2\2\2\u04d6" +
					"\u04d7\7g\2\2\u04d7\u04d8\7e\2\2\u04d8\u04d9\7a\2\2\u04d9\u04da\7p\2\2" +
					"\u04da\u04db\7q\2\2\u04db\u04dc\7d\2\2\u04dc\u04dd\7n\2\2\u04dd\u04de" +
					"\7g\2\2\u04de\u04df\7u\2\2\u04df\u04e0\7u\2\2\u04e0b\3\2\2\2\u04e1\u04e2" +
					"\7g\2\2\u04e2\u04e3\7e\2\2\u04e3\u04e4\7a\2\2\u04e4\u04e5\7e\2\2\u04e5" +
					"\u04e6\7n\2\2\u04e6\u04e7\7c\2\2\u04e7\u04e8\7p\2\2\u04e8\u04e9\7a\2\2" +
					"\u04e9\u04ea\7n\2\2\u04ea\u04eb\7g\2\2\u04eb\u04ec\7c\2\2\u04ec\u04ed" +
					"\7f\2\2\u04ed\u04ee\7g\2\2\u04ee\u04ef\7t\2\2\u04efd\3\2\2\2\u04f0\u04f1" +
					"\7g\2\2\u04f1\u04f2\7e\2\2\u04f2\u04f3\7a\2\2\u04f3\u04f4\7u\2\2\u04f4" +
					"\u04f5\7w\2\2\u04f5\u04f6\7d\2\2\u04f6\u04f7\7l\2\2\u04f7\u04f8\7q\2\2" +
					"\u04f8\u04f9\7d\2\2\u04f9f\3\2\2\2\u04fa\u04fb\7w\2\2\u04fb\u04fc\7e\2" +
					"\2\u04fc\u04fd\7a\2\2\u04fd\u04fe\7v\2\2\u04fe\u04ff\7t\2\2\u04ff\u0500" +
					"\7c\2\2\u0500\u0501\7p\2\2\u0501\u0502\7u\2\2\u0502\u0503\7o\2\2\u0503" +
					"\u0504\7q\2\2\u0504\u0505\7f\2\2\u0505\u0506\7g\2\2\u0506\u0507\7a\2\2" +
					"\u0507\u0508\7k\2\2\u0508\u0509\7p\2\2\u0509\u050a\7e\2\2\u050a\u050b" +
					"\7n\2\2\u050b\u050c\7w\2\2\u050c\u050d\7f\2\2\u050d\u050e\7g\2\2\u050e" +
					"h\3\2\2\2\u050f\u0510\7w\2\2\u0510\u0511\7e\2\2\u0511\u0512\7a\2\2\u0512" +
					"\u0513\7k\2\2\u0513\u0514\7p\2\2\u0514\u0515\7|\2\2\u0515\u0516\7q\2\2" +
					"\u0516\u0517\7p\2\2\u0517\u0518\7g\2\2\u0518\u0519\7a\2\2\u0519\u051a" +
					"\7p\2\2\u051a\u051b\7w\2\2\u051b\u051c\7o\2\2\u051cj\3\2\2\2\u051d\u051e" +
					"\7g\2\2\u051e\u051f\7e\2\2\u051f\u0520\7a\2\2\u0520\u0521\7k\2\2\u0521" +
					"\u0522\7p\2\2\u0522\u0523\7|\2\2\u0523\u0524\7q\2\2\u0524\u0525\7p\2\2" +
					"\u0525\u0526\7g\2\2\u0526\u0527\7a\2\2\u0527\u0528\7p\2\2\u0528\u0529" +
					"\7w\2\2\u0529\u052a\7o\2\2\u052al\3\2\2\2\u052b\u052c\7g\2\2\u052c\u052d" +
					"\7e\2\2\u052d\u052e\7a\2\2\u052e\u052f\7e\2\2\u052f\u0530\7j\2\2\u0530" +
					"\u0531\7c\2\2\u0531\u0532\7q\2\2\u0532n\3\2\2\2\u0533\u0534\7w\2\2\u0534" +
					"\u0535\7e\2\2\u0535\u0536\7a\2\2\u0536\u0537\7k\2\2\u0537\u0538\7p\2\2" +
					"\u0538\u0539\7a\2\2\u0539\u053a\7t\2\2\u053a\u053b\7g\2\2\u053b\u053c" +
					"\7u\2\2\u053c\u053d\7k\2\2\u053d\u053e\7f\2\2\u053e\u053f\7g\2\2\u053f" +
					"\u0540\7p\2\2\u0540\u0541\7e\2\2\u0541\u0542\7g\2\2\u0542\u0543\7a\2\2" +
					"\u0543\u0544\7u\2\2\u0544\u0545\7k\2\2\u0545\u0546\7g\2\2\u0546\u0547" +
					"\7i\2\2\u0547\u0548\7g\2\2\u0548\u0549\7a\2\2\u0549\u054a\7h\2\2\u054a" +
					"\u054b\7k\2\2\u054b\u054c\7g\2\2\u054c\u054d\7n\2\2\u054d\u054e\7f\2\2" +
					"\u054ep\3\2\2\2\u054f\u0550\7g\2\2\u0550\u0551\7e\2\2\u0551\u0552\7a\2" +
					"\2\u0552\u0553\7h\2\2\u0553\u0554\7q\2\2\u0554\u0555\7t\2\2\u0555\u0556" +
					"\7v\2\2\u0556\u0557\7t\2\2\u0557\u0558\7g\2\2\u0558\u0559\7u\2\2\u0559" +
					"\u055a\7u\2\2\u055ar\3\2\2\2\u055b\u055c\7g\2\2\u055c\u055d\7e\2\2\u055d" +
					"\u055e\7a\2\2\u055e\u055f\7c\2\2\u055f\u0560\7i\2\2\u0560\u0561\7k\2\2" +
					"\u0561\u0562\7v\2\2\u0562\u0563\7a\2\2\u0563\u0564\7p\2\2\u0564\u0565" +
					"\7w\2\2\u0565\u0566\7o\2\2\u0566t\3\2\2\2\u0567\u0568\7h\2\2\u0568\u0569" +
					"\7q\2\2\u0569\u056a\7t\2\2\u056a\u056b\7a\2\2\u056b\u056c\7p\2\2\u056c" +
					"\u056d\7r\2\2\u056d\u056e\7e\2\2\u056ev\3\2\2\2\u056f\u0570\7w\2\2\u0570" +
					"\u0571\7p\2\2\u0571\u0572\7g\2\2\u0572\u0573\7s\2\2\u0573\u0574\7w\2\2" +
					"\u0574\u0575\7k\2\2\u0575\u0576\7r\2\2\u0576\u0577\7a\2\2\u0577\u0578" +
					"\7u\2\2\u0578\u0579\7m\2\2\u0579\u057a\7k\2\2\u057a\u057b\7n\2\2\u057b" +
					"\u057c\7n\2\2\u057cx\3\2\2\2\u057d\u057e\7j\2\2\u057e\u057f\7v\2\2\u057f" +
					"\u0580\7o\2\2\u0580\u0581\7n\2\2\u0581z\3\2\2\2\u0582\u0583\7d\2\2\u0583" +
					"\u0584\7c\2\2\u0584\u0585\7u\2\2\u0585\u0586\7g\2\2\u0586\u0587\7a\2\2" +
					"\u0587\u0588\7c\2\2\u0588\u0589\7v\2\2\u0589\u058a\7v\2\2\u058a\u058b" +
					"\7t\2\2\u058b\u058c\7k\2\2\u058c\u058d\7d\2\2\u058d\u058e\7w\2\2\u058e" +
					"\u058f\7v\2\2\u058f\u0590\7g\2\2\u0590\u0591\7a\2\2\u0591\u0592\7f\2\2" +
					"\u0592\u0593\7g\2\2\u0593\u0594\7h\2\2\u0594\u0595\7g\2\2\u0595\u0596" +
					"\7p\2\2\u0596\u0597\7f\2\2\u0597|\3\2\2\2\u0598\u0599\7e\2\2\u0599\u059a" +
					"\7c\2\2\u059a\u059b\7v\2\2\u059b\u059c\7g\2\2\u059c\u059d\7i\2\2\u059d" +
					"\u059e\7q\2\2\u059e\u059f\7t\2\2\u059f\u05a0\7{\2\2\u05a0~\3\2\2\2\u05a1" +
					"\u05a2\7g\2\2\u05a2\u05a3\7p\2\2\u05a3\u05a4\7e\2\2\u05a4\u05a5\7j\2\2" +
					"\u05a5\u05a6\7c\2\2\u05a6\u05a7\7p\2\2\u05a7\u05a8\7v\2\2\u05a8\u05a9" +
					"\7a\2\2\u05a9\u05aa\7g\2\2\u05aa\u05ab\7p\2\2\u05ab\u05ac\7c\2\2\u05ac" +
					"\u05ad\7d\2\2\u05ad\u05ae\7n\2\2\u05ae\u05af\7g\2\2\u05af\u0080\3\2\2" +
					"\2\u05b0\u05b1\7g\2\2\u05b1\u05b2\7n\2\2\u05b2\u05b3\7g\2\2\u05b3\u05b4" +
					"\7o\2\2\u05b4\u05b5\7g\2\2\u05b5\u05b6\7p\2\2\u05b6\u05b7\7v\2\2\u05b7" +
					"\u05b8\7c\2\2\u05b8\u05b9\7n\2\2\u05b9\u05ba\7a\2\2\u05ba\u05bb\7g\2\2" +
					"\u05bb\u05bc\7p\2\2\u05bc\u05bd\7c\2\2\u05bd\u05be\7d\2\2\u05be\u05bf" +
					"\7n\2\2\u05bf\u05c0\7g\2\2\u05c0\u0082\3\2\2\2\u05c1\u05c2\7g\2\2\u05c2" +
					"\u05c3\7p\2\2\u05c3\u05c4\7e\2\2\u05c4\u05c5\7j\2\2\u05c5\u05c6\7c\2\2" +
					"\u05c6\u05c7\7p\2\2\u05c7\u05c8\7v\2\2\u05c8\u05c9\7g\2\2\u05c9\u05ca" +
					"\7f\2\2\u05ca\u0084\3\2\2\2\u05cb\u05cc\7o\2\2\u05cc\u05cd\7r\2\2\u05cd" +
					"\u05ce\7a\2\2\u05ce\u05cf\7e\2\2\u05cf\u05d0\7q\2\2\u05d0\u05d1\7p\2\2" +
					"\u05d1\u05d2\7u\2\2\u05d2\u05d3\7w\2\2\u05d3\u05d4\7o\2\2\u05d4\u05d5" +
					"\7g\2\2\u05d5\u0086\3\2\2\2\u05d6\u05d7\7o\2\2\u05d7\u05d8\7c\2\2\u05d8" +
					"\u05d9\7i\2\2\u05d9\u05da\7k\2\2\u05da\u05db\7e\2\2\u05db\u05dc\7c\2\2" +
					"\u05dc\u05dd\7n\2\2\u05dd\u05de\7a\2\2\u05de\u05df\7f\2\2\u05df\u05e0" +
					"\7c\2\2\u05e0\u05e1\7o\2\2\u05e1\u05e2\7c\2\2\u05e2\u05e3\7i\2\2\u05e3" +
					"\u05e4\7g\2\2\u05e4\u0088\3\2\2\2\u05e5\u05e6\7f\2\2\u05e6\u05e7\7w\2" +
					"\2\u05e7\u05e8\7t\2\2\u05e8\u05e9\7c\2\2\u05e9\u05ea\7d\2\2\u05ea\u05eb" +
					"\7k\2\2\u05eb\u05ec\7n\2\2\u05ec\u05ed\7k\2\2\u05ed\u05ee\7v\2\2\u05ee" +
					"\u05ef\7{\2\2\u05ef\u008a\3\2\2\2\u05f0\u05f1\7f\2\2\u05f1\u05f2\7c\2" +
					"\2\u05f2\u05f3\7o\2\2\u05f3\u05f4\7c\2\2\u05f4\u05f5\7i\2\2\u05f5\u05f6" +
					"\7g\2\2\u05f6\u05f7\7f\2\2\u05f7\u008c\3\2\2\2\u05f8\u05f9\7o\2\2\u05f9" +
					"\u05fa\7c\2\2\u05fa\u05fb\7i\2\2\u05fb\u05fc\7k\2\2\u05fc\u05fd\7e\2\2" +
					"\u05fd\u05fe\7a\2\2\u05fe\u05ff\7y\2\2\u05ff\u0600\7g\2\2\u0600\u0601" +
					"\7c\2\2\u0601\u0602\7r\2\2\u0602\u0603\7q\2\2\u0603\u0604\7p\2\2\u0604" +
					"\u008e\3\2\2\2\u0605\u0606\7r\2\2\u0606\u0607\7j\2\2\u0607\u0608\7{\2" +
					"\2\u0608\u0609\7u\2\2\u0609\u060a\7k\2\2\u060a\u060b\7e\2\2\u060b\u060c" +
					"\7c\2\2\u060c\u060d\7n\2\2\u060d\u060e\7a\2\2\u060e\u060f\7f\2\2\u060f" +
					"\u0610\7g\2\2\u0610\u0611\7h\2\2\u0611\u0612\7g\2\2\u0612\u0613\7p\2\2" +
					"\u0613\u0614\7u\2\2\u0614\u0615\7g\2\2\u0615\u0090\3\2\2\2\u0616\u0617" +
					"\7o\2\2\u0617\u0618\7c\2\2\u0618\u0619\7i\2\2\u0619\u061a\7k\2\2\u061a" +
					"\u061b\7e\2\2\u061b\u061c\7c\2\2\u061c\u061d\7n\2\2\u061d\u061e\7a\2\2" +
					"\u061e\u061f\7f\2\2\u061f\u0620\7g\2\2\u0620\u0621\7h\2\2\u0621\u0622" +
					"\7g\2\2\u0622\u0623\7p\2\2\u0623\u0624\7u\2\2\u0624\u0625\7g\2\2\u0625" +
					"\u0092\3\2\2\2\u0626\u0627\7o\2\2\u0627\u0628\7r\2\2\u0628\u0629\7a\2" +
					"\2\u0629\u062a\7d\2\2\u062a\u062b\7q\2\2\u062b\u062c\7p\2\2\u062c\u062d" +
					"\7w\2\2\u062d\u062e\7u\2\2\u062e\u0094\3\2\2\2\u062f\u0630\7y\2\2\u0630" +
					"\u0631\7g\2\2\u0631\u0632\7c\2\2\u0632\u0633\7r\2\2\u0633\u0634\7q\2\2" +
					"\u0634\u0635\7p\2\2\u0635\u0636\7a\2\2\u0636\u0637\7v\2\2\u0637\u0638" +
					"\7{\2\2\u0638\u0639\7r\2\2\u0639\u063a\7g\2\2\u063a\u0096\3\2\2\2\u063b" +
					"\u063c\7k\2\2\u063c\u063d\7u\2\2\u063d\u063e\7a\2\2\u063e\u063f\7v\2\2" +
					"\u063f\u0640\7t\2\2\u0640\u0641\7c\2\2\u0641\u0642\7f\2\2\u0642\u0643" +
					"\7g\2\2\u0643\u0098\3\2\2\2\u0644\u0645\7k\2\2\u0645\u0646\7u\2\2\u0646" +
					"\u0647\7a\2\2\u0647\u0648\7f\2\2\u0648\u0649\7t\2\2\u0649\u064a\7q\2\2" +
					"\u064a\u064b\7r\2\2\u064b\u009a\3\2\2\2\u064c\u064d\7k\2\2\u064d\u064e" +
					"\7u\2\2\u064e\u064f\7a\2\2\u064f\u0650\7f\2\2\u0650\u0651\7g\2\2\u0651" +
					"\u0652\7u\2\2\u0652\u0653\7v\2\2\u0653\u0654\7t\2\2\u0654\u0655\7w\2\2" +
					"\u0655\u0656\7e\2\2\u0656\u0657\7v\2\2\u0657\u009c\3\2\2\2\u0658\u0659" +
					"\7k\2\2\u0659\u065a\7u\2\2\u065a\u065b\7a\2\2\u065b\u065c\7r\2\2\u065c" +
					"\u065d\7t\2\2\u065d\u065e\7k\2\2\u065e\u065f\7x\2\2\u065f\u0660\7c\2\2" +
					"\u0660\u0661\7v\2\2\u0661\u0662\7g\2\2\u0662\u0663\7a\2\2\u0663\u0664" +
					"\7u\2\2\u0664\u0665\7v\2\2\u0665\u0666\7q\2\2\u0666\u0667\7t\2\2\u0667" +
					"\u0668\7g\2\2\u0668\u009e\3\2\2\2\u0669\u066a\7m\2\2\u066a\u066b\7g\2" +
					"\2\u066b\u066c\7g\2\2\u066c\u066d\7r\2\2\u066d\u066e\7a\2\2\u066e\u066f" +
					"\7v\2\2\u066f\u0670\7{\2\2\u0670\u0671\7r\2\2\u0671\u0672\7g\2\2\u0672" +
					"\u00a0\3\2\2\2\u0673\u0674\7r\2\2\u0674\u0675\7j\2\2\u0675\u0676\7{\2" +
					"\2\u0676\u0677\7u\2\2\u0677\u0678\7k\2\2\u0678\u0679\7e\2\2\u0679\u067a" +
					"\7c\2\2\u067a\u067b\7n\2\2\u067b\u067c\7a\2\2\u067c\u067d\7f\2\2\u067d" +
					"\u067e\7c\2\2\u067e\u067f\7o\2\2\u067f\u0680\7c\2\2\u0680\u0681\7i\2\2" +
					"\u0681\u0682\7g\2\2\u0682\u00a2\3\2\2\2\u0683\u0684\7t\2\2\u0684\u0685" +
					"\7c\2\2\u0685\u0686\7p\2\2\u0686\u0687\7f\2\2\u0687\u0688\7q\2\2\u0688" +
					"\u0689\7o\2\2\u0689\u068a\7a\2\2\u068a\u068b\7f\2\2\u068b\u068c\7c\2\2" +
					"\u068c\u068d\7o\2\2\u068d\u068e\7c\2\2\u068e\u068f\7i\2\2\u068f\u0690" +
					"\7g\2\2\u0690\u00a4\3\2\2\2\u0691\u0692\7e\2\2\u0692\u0693\7t\2\2\u0693" +
					"\u0694\7k\2\2\u0694\u0695\7v\2\2\u0695\u0696\7k\2\2\u0696\u0697\7e\2\2" +
					"\u0697\u0698\7c\2\2\u0698\u0699\7n\2\2\u0699\u00a6\3\2\2\2\u069a\u069b" +
					"\7j\2\2\u069b\u069c\7k\2\2\u069c\u069d\7v\2\2\u069d\u069e\7a\2\2\u069e" +
					"\u069f\7o\2\2\u069f\u06a0\7q\2\2\u06a0\u06a1\7f\2\2\u06a1\u06a2\7k\2\2" +
					"\u06a2\u06a3\7h\2\2\u06a3\u06a4\7{\2\2\u06a4\u00a8\3\2\2\2\u06a5\u06a6" +
					"\7c\2\2\u06a6\u06a7\7v\2\2\u06a7\u06a8\7v\2\2\u06a8\u06a9\7c\2\2\u06a9" +
					"\u06aa\7e\2\2\u06aa\u06ab\7m\2\2\u06ab\u06ac\7a\2\2\u06ac\u06ad\7t\2\2" +
					"\u06ad\u06ae\7c\2\2\u06ae\u06af\7p\2\2\u06af\u06b0\7i\2\2\u06b0\u06b1" +
					"\7g\2\2\u06b1\u00aa\3\2\2\2\u06b2\u06b3\7f\2\2\u06b3\u06b4\7c\2\2\u06b4" +
					"\u06b5\7o\2\2\u06b5\u06b6\7c\2\2\u06b6\u06b7\7i\2\2\u06b7\u06b8\7g\2\2" +
					"\u06b8\u06b9\7a\2\2\u06b9\u06ba\7t\2\2\u06ba\u06bb\7c\2\2\u06bb\u06bc" +
					"\7p\2\2\u06bc\u06bd\7i\2\2\u06bd\u06be\7g\2\2\u06be\u00ac\3\2\2\2\u06bf" +
					"\u06c0\7c\2\2\u06c0\u06c1\7v\2\2\u06c1\u06c2\7v\2\2\u06c2\u06c3\7c\2\2" +
					"\u06c3\u06c4\7e\2\2\u06c4\u06c5\7m\2\2\u06c5\u06c6\7a\2\2\u06c6\u06c7" +
					"\7u\2\2\u06c7\u06c8\7r\2\2\u06c8\u06c9\7g\2\2\u06c9\u06ca\7g\2\2\u06ca" +
					"\u06cb\7f\2\2\u06cb\u00ae\3\2\2\2\u06cc\u06cd\7c\2\2\u06cd\u06ce\7x\2" +
					"\2\u06ce\u06cf\7q\2\2\u06cf\u06d0\7k\2\2\u06d0\u06d1\7f\2\2\u06d1\u06d2" +
					"\7a\2\2\u06d2\u06d3\7o\2\2\u06d3\u06d4\7q\2\2\u06d4\u06d5\7f\2\2\u06d5" +
					"\u06d6\7k\2\2\u06d6\u06d7\7h\2\2\u06d7\u06d8\7{\2\2\u06d8\u00b0\3\2\2" +
					"\2\u06d9\u06da\7f\2\2\u06da\u06db\7w\2\2\u06db\u06dc\7c\2\2\u06dc\u06dd" +
					"\7n\2\2\u06dd\u06de\7a\2\2\u06de\u06df\7h\2\2\u06df\u06e0\7j\2\2\u06e0" +
					"\u06e1\7k\2\2\u06e1\u06e2\7v\2\2\u06e2\u06e3\7a\2\2\u06e3\u06e4\7t\2\2" +
					"\u06e4\u06e5\7c\2\2\u06e5\u06e6\7v\2\2\u06e6\u06e7\7g\2\2\u06e7\u00b2" +
					"\3\2\2\2\u06e8\u06e9\7u\2\2\u06e9\u06ea\7j\2\2\u06ea\u06eb\7k\2\2\u06eb" +
					"\u06ec\7g\2\2\u06ec\u06ed\7n\2\2\u06ed\u06ee\7f\2\2\u06ee\u06ef\7a\2\2" +
					"\u06ef\u06f0\7f\2\2\u06f0\u06f1\7g\2\2\u06f1\u06f2\7h\2\2\u06f2\u06f3" +
					"\7g\2\2\u06f3\u06f4\7p\2\2\u06f4\u06f5\7u\2\2\u06f5\u06f6\7g\2\2\u06f6" +
					"\u00b4\3\2\2\2\u06f7\u06f8\7u\2\2\u06f8\u06f9\7j\2\2\u06f9\u06fa\7k\2" +
					"\2\u06fa\u06fb\7g\2\2\u06fb\u06fc\7n\2\2\u06fc\u06fd\7f\2\2\u06fd\u06fe" +
					"\7a\2\2\u06fe\u06ff\7f\2\2\u06ff\u0700\7g\2\2\u0700\u0701\7h\2\2\u0701" +
					"\u0702\7g\2\2\u0702\u0703\7p\2\2\u0703\u0704\7u\2\2\u0704\u0705\7g\2\2" +
					"\u0705\u0706\7a\2\2\u0706\u0707\7t\2\2\u0707\u0708\7c\2\2\u0708\u0709" +
					"\7v\2\2\u0709\u070a\7g\2\2\u070a\u00b6\3\2\2\2\u070b\u070c\7t\2\2\u070c" +
					"\u070d\7g\2\2\u070d\u070e\7w\2\2\u070e\u070f\7u\2\2\u070f\u0710\7g\2\2" +
					"\u0710\u0711\7a\2\2\u0711\u0712\7f\2\2\u0712\u0713\7g\2\2\u0713\u0714" +
					"\7n\2\2\u0714\u0715\7c\2\2\u0715\u0716\7{\2\2\u0716\u00b8\3\2\2\2\u0717" +
					"\u0718\7k\2\2\u0718\u0719\7p\2\2\u0719\u071a\7k\2\2\u071a\u071b\7v\2\2" +
					"\u071b\u071c\7k\2\2\u071c\u071d\7c\2\2\u071d\u071e\7n\2\2\u071e\u071f" +
					"\7a\2\2\u071f\u0720\7e\2\2\u0720\u0721\7q\2\2\u0721\u0722\7w\2\2\u0722" +
					"\u0723\7p\2\2\u0723\u0724\7v\2\2\u0724\u00ba\3\2\2\2\u0725\u0726\7u\2" +
					"\2\u0726\u0727\7q\2\2\u0727\u0728\7w\2\2\u0728\u0729\7n\2\2\u0729\u072a" +
					"\7u\2\2\u072a\u072b\7j\2\2\u072b\u072c\7q\2\2\u072c\u072d\7v\2\2\u072d" +
					"\u072e\7a\2\2\u072e\u072f\7e\2\2\u072f\u0730\7q\2\2\u0730\u0731\7w\2\2" +
					"\u0731\u0732\7p\2\2\u0732\u0733\7v\2\2\u0733\u00bc\3\2\2\2\u0734\u0735" +
					"\7u\2\2\u0735\u0736\7r\2\2\u0736\u0737\7k\2\2\u0737\u0738\7t\2\2\u0738" +
					"\u0739\7k\2\2\u0739\u073a\7v\2\2\u073a\u073b\7u\2\2\u073b\u073c\7j\2\2" +
					"\u073c\u073d\7q\2\2\u073d\u073e\7v\2\2\u073e\u073f\7a\2\2\u073f\u0740" +
					"\7e\2\2\u0740\u0741\7q\2\2\u0741\u0742\7w\2\2\u0742\u0743\7p\2\2\u0743" +
					"\u0744\7v\2\2\u0744\u00be\3\2\2\2\u0745\u0746\7t\2\2\u0746\u0747\7g\2" +
					"\2\u0747\u0748\7f\2\2\u0748\u0749\7w\2\2\u0749\u074a\7e\2\2\u074a\u074b" +
					"\7g\2\2\u074b\u074c\7f\2\2\u074c\u074d\7a\2\2\u074d\u074e\7u\2\2\u074e" +
					"\u074f\7q\2\2\u074f\u0750\7w\2\2\u0750\u0751\7n\2\2\u0751\u0752\7u\2\2" +
					"\u0752\u0753\7j\2\2\u0753\u0754\7q\2\2\u0754\u0755\7v\2\2\u0755\u00c0" +
					"\3\2\2\2\u0756\u0757\7t\2\2\u0757\u0758\7g\2\2\u0758\u0759\7f\2\2\u0759" +
					"\u075a\7w\2\2\u075a\u075b\7e\2\2\u075b\u075c\7g\2\2\u075c\u075d\7f\2\2" +
					"\u075d\u075e\7a\2\2\u075e\u075f\7u\2\2\u075f\u0760\7r\2\2\u0760\u0761" +
					"\7k\2\2\u0761\u0762\7t\2\2\u0762\u0763\7k\2\2\u0763\u0764\7v\2\2\u0764" +
					"\u0765\7u\2\2\u0765\u0766\7j\2\2\u0766\u0767\7q\2\2\u0767\u0768\7v\2\2" +
					"\u0768\u00c2\3\2\2\2\u0769\u076a\7t\2\2\u076a\u076b\7g\2\2\u076b\u076c" +
					"\7f\2\2\u076c\u076d\7w\2\2\u076d\u076e\7e\2\2\u076e\u076f\7g\2\2\u076f" +
					"\u0770\7f\2\2\u0770\u0771\7a\2\2\u0771\u0772\7o\2\2\u0772\u0773\7r\2\2" +
					"\u0773\u0774\7a\2\2\u0774\u0775\7e\2\2\u0775\u0776\7q\2\2\u0776\u0777" +
					"\7p\2\2\u0777\u0778\7u\2\2\u0778\u0779\7w\2\2\u0779\u077a\7o\2\2\u077a" +
					"\u077b\7g\2\2\u077b\u00c4\3\2\2\2\u077c\u077d\7k\2\2\u077d\u077e\7o\2" +
					"\2\u077e\u077f\7o\2\2\u077f\u0780\7g\2\2\u0780\u0781\7f\2\2\u0781\u0782" +
					"\7k\2\2\u0782\u0783\7c\2\2\u0783\u0784\7v\2\2\u0784\u0785\7g\2\2\u0785" +
					"\u0786\7a\2\2\u0786\u0787\7g\2\2\u0787\u0788\7h\2\2\u0788\u0789\7h\2\2" +
					"\u0789\u078a\7g\2\2\u078a\u078b\7e\2\2\u078b\u078c\7v\2\2\u078c\u00c6" +
					"\3\2\2\2\u078d\u078e\7g\2\2\u078e\u078f\7z\2\2\u078f\u0790\7a\2\2\u0790" +
					"\u0791\7k\2\2\u0791\u0792\7o\2\2\u0792\u0793\7o\2\2\u0793\u0794\7g\2\2" +
					"\u0794\u0795\7f\2\2\u0795\u0796\7k\2\2\u0796\u0797\7c\2\2\u0797\u0798" +
					"\7v\2\2\u0798\u0799\7g\2\2\u0799\u079a\7a\2\2\u079a\u079b\7g\2\2\u079b" +
					"\u079c\7h\2\2\u079c\u079d\7h\2\2\u079d\u079e\7g\2\2\u079e\u079f\7e\2\2" +
					"\u079f\u07a0\7v\2\2\u07a0\u00c8\3\2\2\2\u07a1\u07a2\7w\2\2\u07a2\u07a3" +
					"\7u\2\2\u07a3\u07a4\7g\2\2\u07a4\u07a5\7a\2\2\u07a5\u07a6\7u\2\2\u07a6" +
					"\u07a7\7m\2\2\u07a7\u07a8\7k\2\2\u07a8\u07a9\7n\2\2\u07a9\u07aa\7n\2\2" +
					"\u07aa\u07ab\7a\2\2\u07ab\u07ac\7f\2\2\u07ac\u07ad\7k\2\2\u07ad\u07ae" +
					"\7u\2\2\u07ae\u07af\7v\2\2\u07af\u07b0\7k\2\2\u07b0\u07b1\7o\2\2\u07b1" +
					"\u07b2\7g\2\2\u07b2\u00ca\3\2\2\2\u07b3\u07b4\7f\2\2\u07b4\u07b5\7t\2" +
					"\2\u07b5\u07b6\7q\2\2\u07b6\u07b7\7r\2\2\u07b7\u07b8\7a\2\2\u07b8\u07b9" +
					"\7r\2\2\u07b9\u07ba\7g\2\2\u07ba\u07bb\7t\2\2\u07bb\u07bc\7k\2\2\u07bc" +
					"\u07bd\7q\2\2\u07bd\u07be\7f\2\2\u07be\u00cc\3\2\2\2\u07bf\u07c0\7f\2" +
					"\2\u07c0\u07c1\7w\2\2\u07c1\u07c2\7t\2\2\u07c2\u07c3\7c\2\2\u07c3\u07c4" +
					"\7v\2\2\u07c4\u07c5\7k\2\2\u07c5\u07c6\7q\2\2\u07c6\u07c7\7p\2\2\u07c7" +
					"\u00ce\3\2\2\2\u07c8\u07c9\7r\2\2\u07c9\u07ca\7g\2\2\u07ca\u07cb\7t\2" +
					"\2\u07cb\u07cc\7k\2\2\u07cc\u07cd\7q\2\2\u07cd\u07ce\7f\2\2\u07ce\u00d0" +
					"\3\2\2\2\u07cf\u07d0\7g\2\2\u07d0\u07d1\7s\2\2\u07d1\u07d2\7w\2\2\u07d2" +
					"\u07d3\7k\2\2\u07d3\u07d4\7r\2\2\u07d4\u07d5\7a\2\2\u07d5\u07d6\7t\2\2" +
					"\u07d6\u07d7\7g\2\2\u07d7\u07d8\7w\2\2\u07d8\u07d9\7u\2\2\u07d9\u07da" +
					"\7g\2\2\u07da\u07db\7a\2\2\u07db\u07dc\7f\2\2\u07dc\u07dd\7g\2\2\u07dd" +
					"\u07de\7n\2\2\u07de\u07df\7c\2\2\u07df\u07e0\7{\2\2\u07e0\u00d2\3\2\2" +
					"\2\u07e1\u07e2\7e\2\2\u07e2\u07e3\7c\2\2\u07e3\u07e4\7r\2\2\u07e4\u07e5" +
					"\7u\2\2\u07e5\u07e6\7w\2\2\u07e6\u07e7\7n\2\2\u07e7\u07e8\7g\2\2\u07e8" +
					"\u07e9\7f\2\2\u07e9\u07ea\7a\2\2\u07ea\u07eb\7k\2\2\u07eb\u07ec\7v\2\2" +
					"\u07ec\u07ed\7g\2\2\u07ed\u07ee\7o\2\2\u07ee\u07ef\7u\2\2\u07ef\u00d4" +
					"\3\2\2\2\u07f0\u07f1\7r\2\2\u07f1\u07f2\7t\2\2\u07f2\u07f3\7k\2\2\u07f3" +
					"\u07f4\7e\2\2\u07f4\u07f5\7g\2\2\u07f5\u00d6\3\2\2\2\u07f6\u07f7\7f\2" +
					"\2\u07f7\u07f8\7g\2\2\u07f8\u07f9\7h\2\2\u07f9\u07fa\7c\2\2\u07fa\u07fb" +
					"\7w\2\2\u07fb\u07fc\7n\2\2\u07fc\u07fd\7v\2\2\u07fd\u07fe\7a\2\2\u07fe" +
					"\u07ff\7r\2\2\u07ff\u0800\7t\2\2\u0800\u0801\7k\2\2\u0801\u0802\7e\2\2" +
					"\u0802\u0803\7g\2\2\u0803\u00d8\3\2\2\2\u0804\u0805\7k\2\2\u0805\u0806" +
					"\7v\2\2\u0806\u0807\7g\2\2\u0807\u0808\7o\2\2\u0808\u0809\7a\2\2\u0809" +
					"\u080a\7u\2\2\u080a\u080b\7m\2\2\u080b\u080c\7k\2\2\u080c\u080d\7n\2\2" +
					"\u080d\u080e\7n\2\2\u080e\u00da\3\2\2\2\u080f\u0810\7e\2\2\u0810\u0811" +
					"\7t\2\2\u0811\u0812\7k\2\2\u0812\u0813\7v\2\2\u0813\u0814\7k\2\2\u0814" +
					"\u0815\7e\2\2\u0815\u0816\7c\2\2\u0816\u0817\7n\2\2\u0817\u0818\7a\2\2" +
					"\u0818\u0819\7c\2\2\u0819\u081a\7v\2\2\u081a\u081b\7v\2\2\u081b\u081c" +
					"\7c\2\2\u081c\u081d\7e\2\2\u081d\u081e\7m\2\2\u081e\u081f\7a\2\2\u081f" +
					"\u0820\7u\2\2\u0820\u0821\7m\2\2\u0821\u0822\7k\2\2\u0822\u0823\7n\2\2" +
					"\u0823\u0824\7n\2\2\u0824\u00dc\3\2\2\2\u0825\u0826\7c\2\2\u0826\u0827" +
					"\7v\2\2\u0827\u0828\7v\2\2\u0828\u0829\7c\2\2\u0829\u082a\7e\2\2\u082a" +
					"\u082b\7m\2\2\u082b\u082c\7a\2\2\u082c\u082d\7u\2\2\u082d\u082e\7m\2\2" +
					"\u082e\u082f\7k\2\2\u082f\u0830\7";
	private static final String _serializedATNSegment1 =
			"n\2\2\u0830\u0831\7n\2\2\u0831\u00de\3\2\2\2\u0832\u0833\7o\2\2\u0833" +
					"\u0834\7c\2\2\u0834\u0835\7i\2\2\u0835\u0836\7k\2\2\u0836\u0837\7e\2\2" +
					"\u0837\u0838\7a\2\2\u0838\u0839\7u\2\2\u0839\u083a\7m\2\2\u083a\u083b" +
					"\7k\2\2\u083b\u083c\7n\2\2\u083c\u083d\7n\2\2\u083d\u00e0\3\2\2\2\u083e" +
					"\u083f\7k\2\2\u083f\u0840\7v\2\2\u0840\u0841\7g\2\2\u0841\u0842\7o\2\2" +
					"\u0842\u0843\7a\2\2\u0843\u0844\7u\2\2\u0844\u0845\7m\2\2\u0845\u0846" +
					"\7k\2\2\u0846\u0847\7n\2\2\u0847\u0848\7n\2\2\u0848\u0849\7a\2\2\u0849" +
					"\u084a\7g\2\2\u084a\u084b\7p\2\2\u084b\u084c\7e\2\2\u084c\u084d\7j\2\2" +
					"\u084d\u084e\7c\2\2\u084e\u084f\7p\2\2\u084f\u0850\7v\2\2\u0850\u0851" +
					"\7g\2\2\u0851\u0852\7f\2\2\u0852\u0853\7a\2\2\u0853\u0854\7h\2\2\u0854" +
					"\u0855\7q\2\2\u0855\u0856\7w\2\2\u0856\u0857\7t\2\2\u0857\u00e2\3\2\2" +
					"\2\u0858\u0859\7e\2\2\u0859\u085a\7t\2\2\u085a\u085b\7{\2\2\u085b\u085c" +
					"\7u\2\2\u085c\u085d\7v\2\2\u085d\u085e\7c\2\2\u085e\u085f\7n\2\2\u085f" +
					"\u0860\7a\2\2\u0860\u0861\7v\2\2\u0861\u0862\7{\2\2\u0862\u0863\7r\2\2" +
					"\u0863\u0864\7g\2\2\u0864\u00e4\3\2\2\2\u0865\u0866\7e\2\2\u0866\u0867" +
					"\7t\2\2\u0867\u0868\7{\2\2\u0868\u0869\7u\2\2\u0869\u086a\7v\2\2\u086a" +
					"\u086b\7c\2\2\u086b\u086c\7n\2\2\u086c\u086d\7a\2\2\u086d\u086e\7e\2\2" +
					"\u086e\u086f\7q\2\2\u086f\u0870\7w\2\2\u0870\u0871\7p\2\2\u0871\u0872" +
					"\7v\2\2\u0872\u00e6\3\2\2\2\u0873\u0874\7o\2\2\u0874\u0875\7c\2\2\u0875" +
					"\u0876\7v\2\2\u0876\u0877\7g\2\2\u0877\u0878\7t\2\2\u0878\u0879\7k\2\2" +
					"\u0879\u087a\7c\2\2\u087a\u087b\7n\2\2\u087b\u087c\7a\2\2\u087c\u087d" +
					"\7v\2\2\u087d\u087e\7{\2\2\u087e\u087f\7r\2\2\u087f\u0880\7g\2\2\u0880" +
					"\u00e8\3\2\2\2\u0881\u0882\7e\2\2\u0882\u0883\7q\2\2\u0883\u0884\7p\2" +
					"\2\u0884\u0885\7u\2\2\u0885\u0886\7w\2\2\u0886\u0887\7o\2\2\u0887\u0888" +
					"\7g\2\2\u0888\u0889\7a\2\2\u0889\u088a\7v\2\2\u088a\u088b\7{\2\2\u088b" +
					"\u088c\7r\2\2\u088c\u088d\7g\2\2\u088d\u00ea\3\2\2\2\u088e\u088f\7f\2" +
					"\2\u088f\u0890\7g\2\2\u0890\u0891\7h\2\2\u0891\u0892\7c\2\2\u0892\u0893" +
					"\7w\2\2\u0893\u0894\7n\2\2\u0894\u0895\7v\2\2\u0895\u0896\7a\2\2\u0896" +
					"\u0897\7c\2\2\u0897\u0898\7e\2\2\u0898\u0899\7v\2\2\u0899\u089a\7k\2\2" +
					"\u089a\u089b\7q\2\2\u089b\u089c\7p\2\2\u089c\u00ec\3\2\2\2\u089d\u089e" +
					"\7t\2\2\u089e\u089f\7g\2\2\u089f\u08a0\7e\2\2\u08a0\u08a1\7k\2\2\u08a1" +
					"\u08a2\7r\2\2\u08a2\u08a3\7g\2\2\u08a3\u08a4\7a\2\2\u08a4\u08a5\7k\2\2" +
					"\u08a5\u08a6\7f\2\2\u08a6\u00ee\3\2\2\2\u08a7\u08a8\7d\2\2\u08a8\u08a9" +
					"\7n\2\2\u08a9\u08aa\7g\2\2\u08aa\u08ab\7u\2\2\u08ab\u08ac\7u\2\2\u08ac" +
					"\u08ad\7g\2\2\u08ad\u08ae\7f\2\2\u08ae\u00f0\3\2\2\2\u08af\u08b0\7y\2" +
					"\2\u08b0\u08b1\7g\2\2\u08b1\u08b2\7k\2\2\u08b2\u08b3\7i\2\2\u08b3\u08b4" +
					"\7j\2\2\u08b4\u08b5\7v\2\2\u08b5\u00f2\3\2\2\2\u08b6\u08b7\7k\2\2\u08b7" +
					"\u08b8\7v\2\2\u08b8\u08b9\7g\2\2\u08b9\u08ba\7o\2\2\u08ba\u08bb\7a\2\2" +
					"\u08bb\u08bc\7o\2\2\u08bc\u08bd\7w\2\2\u08bd\u08be\7n\2\2\u08be\u08bf" +
					"\7v\2\2\u08bf\u08c0\7k\2\2\u08c0\u08c1\7a\2\2\u08c1\u08c2\7u\2\2\u08c2" +
					"\u08c3\7m\2\2\u08c3\u08c4\7k\2\2\u08c4\u08c5\7n\2\2\u08c5\u08c6\7n\2\2" +
					"\u08c6\u08c7\7a\2\2\u08c7\u08c8\7n\2\2\u08c8\u08c9\7k\2\2\u08c9\u08ca" +
					"\7u\2\2\u08ca\u08cb\7v\2\2\u08cb\u00f4\3\2\2\2\u08cc\u08cd\7f\2\2\u08cd" +
					"\u08ce\7g\2\2\u08ce\u08cf\7n\2\2\u08cf\u08d0\7c\2\2\u08d0\u08d1\7{\2\2" +
					"\u08d1\u08d2\7a\2\2\u08d2\u08d3\7u\2\2\u08d3\u08d4\7j\2\2\u08d4\u08d5" +
					"\7c\2\2\u08d5\u08d6\7t\2\2\u08d6\u08d7\7g\2\2\u08d7\u08d8\7a\2\2\u08d8" +
					"\u08d9\7i\2\2\u08d9\u08da\7t\2\2\u08da\u08db\7q\2\2\u08db\u08dc\7w\2\2" +
					"\u08dc\u08dd\7r\2\2\u08dd\u00f6\3\2\2\2\u08de\u08df\7g\2\2\u08df\u08e0" +
					"\7v\2\2\u08e0\u08e1\7e\2\2\u08e1\u08e2\7k\2\2\u08e2\u08e3\7v\2\2\u08e3" +
					"\u08e4\7g\2\2\u08e4\u08e5\7o\2\2\u08e5\u08e6\7a\2\2\u08e6\u08e7\7v\2\2" +
					"\u08e7\u08e8\7{\2\2\u08e8\u08e9\7r\2\2\u08e9\u08ea\7g\2\2\u08ea\u00f8" +
					"\3\2\2\2\u08eb\u08ec\7c\2\2\u08ec\u08ed\7t\2\2\u08ed\u08ee\7o\2\2\u08ee" +
					"\u08ef\7q\2\2\u08ef\u08f0\7t\2\2\u08f0\u08f1\7a\2\2\u08f1\u08f2\7v\2\2" +
					"\u08f2\u08f3\7{\2\2\u08f3\u08f4\7r\2\2\u08f4\u08f5\7g\2\2\u08f5\u00fa" +
					"\3\2\2\2\u08f6\u08f7\7u\2\2\u08f7\u08f8\7n\2\2\u08f8\u08f9\7q\2\2\u08f9" +
					"\u08fa\7v\2\2\u08fa\u08fb\7a\2\2\u08fb\u08fc\7d\2\2\u08fc\u08fd\7k\2\2" +
					"\u08fd\u08fe\7v\2\2\u08fe\u08ff\7a\2\2\u08ff\u0900\7v\2\2\u0900\u0901" +
					"\7{\2\2\u0901\u0902\7r\2\2\u0902\u0903\7g\2\2\u0903\u00fc\3\2\2\2\u0904" +
					"\u0905\7k\2\2\u0905\u0906\7v\2\2\u0906\u0907\7g\2\2\u0907\u0908\7o\2\2" +
					"\u0908\u0909\7a\2\2\u0909\u090a\7v\2\2\u090a\u090b\7{\2\2\u090b\u090c" +
					"\7r\2\2\u090c\u090d\7g\2\2\u090d\u00fe\3\2\2\2\u090e\u090f\7]\2\2\u090f" +
					"\u0100\3\2\2\2\u0910\u0911\7_\2\2\u0911\u0102\3\2\2\2\u0912\u0913\7d\2" +
					"\2\u0913\u0914\7c\2\2\u0914\u0915\7u\2\2\u0915\u0916\7g\2\2\u0916\u0917" +
					"\7a\2\2\u0917\u0918\7c\2\2\u0918\u0919\7v\2\2\u0919\u091a\7v\2\2\u091a" +
					"\u091b\7t\2\2\u091b\u091c\7k\2\2\u091c\u091d\7d\2\2\u091d\u091e\7w\2\2" +
					"\u091e\u091f\7v\2\2\u091f\u0920\7g\2\2\u0920\u0921\7a\2\2\u0921\u0922" +
					"\7c\2\2\u0922\u0923\7v\2\2\u0923\u0924\7v\2\2\u0924\u0925\7c\2\2\u0925" +
					"\u0926\7e\2\2\u0926\u0927\7m\2\2\u0927\u0104\3\2\2\2\u0928\u0929\7y\2" +
					"\2\u0929\u092a\7g\2\2\u092a\u092b\7c\2\2\u092b\u092c\7r\2\2\u092c\u092d" +
					"\7q\2\2\u092d\u092e\7p\2\2\u092e\u0106\3\2\2\2\u092f\u0930\7c\2\2\u0930" +
					"\u0931\7t\2\2\u0931\u0932\7o\2\2\u0932\u0933\7q\2\2\u0933\u0934\7t\2\2" +
					"\u0934\u0108\3\2\2\2\u0935\u0936\7g\2\2\u0936\u0937\7v\2\2\u0937\u0938" +
					"\7e\2\2\u0938\u0939\7k\2\2\u0939\u093a\7v\2\2\u093a\u093b\7g\2\2\u093b" +
					"\u093c\7o\2\2\u093c\u010a\3\2\2\2\u093d\u093e\7c\2\2\u093e\u093f\7u\2" +
					"\2\u093f\u0940\7u\2\2\u0940\u0941\7g\2\2\u0941\u0942\7v\2\2\u0942\u010c" +
					"\3\2\2\2\u0943\u0944\7c\2\2\u0944\u0945\7e\2\2\u0945\u0946\7e\2\2\u0946" +
					"\u0947\7g\2\2\u0947\u0948\7u\2\2\u0948\u0949\7u\2\2\u0949\u094a\7c\2\2" +
					"\u094a\u094b\7t\2\2\u094b\u094c\7{\2\2\u094c\u010e\3\2\2\2\u094d\u094e" +
					"\7s\2\2\u094e\u094f\7w\2\2\u094f\u0950\7g\2\2\u0950\u0951\7u\2\2\u0951" +
					"\u0952\7v\2\2\u0952\u0953\7k\2\2\u0953\u0954\7v\2\2\u0954\u0955\7g\2\2" +
					"\u0955\u0956\7o\2\2\u0956\u0110\3\2\2\2\u0957\u0958\7r\2\2\u0958\u0959" +
					"\7q\2\2\u0959\u095a\7v\2\2\u095a\u095b\7k\2\2\u095b\u095c\7q\2\2\u095c" +
					"\u095d\7p\2\2\u095d\u0112\3\2\2\2\u095e\u095f\7c\2\2\u095f\u0960\7t\2" +
					"\2\u0960\u0961\7t\2\2\u0961\u0962\7q\2\2\u0962\u0963\7y\2\2\u0963\u0114" +
					"\3\2\2\2\u0964\u0965\7u\2\2\u0965\u0966\7e\2\2\u0966\u0967\7t\2\2\u0967" +
					"\u0968\7n\2\2\u0968\u0969\7a\2\2\u0969\u096a\7g\2\2\u096a\u096b\7p\2\2" +
					"\u096b\u096c\7e\2\2\u096c\u096d\7j\2\2\u096d\u096e\7c\2\2\u096e\u096f" +
					"\7p\2\2\u096f\u0970\7v\2\2\u0970\u0971\7a\2\2\u0971\u0972\7c\2\2\u0972" +
					"\u0973\7o\2\2\u0973\u0116\3\2\2\2\u0974\u0975\7u\2\2\u0975\u0976\7e\2" +
					"\2\u0976\u0977\7t\2\2\u0977\u0978\7n\2\2\u0978\u0979\7a\2\2\u0979\u097a" +
					"\7g\2\2\u097a\u097b\7p\2\2\u097b\u097c\7e\2\2\u097c\u097d\7j\2\2\u097d" +
					"\u097e\7c\2\2\u097e\u097f\7p\2\2\u097f\u0980\7v\2\2\u0980\u0981\7a\2\2" +
					"\u0981\u0982\7y\2\2\u0982\u0983\7r\2\2\u0983\u0118\3\2\2\2\u0984\u0985" +
					"\7u\2\2\u0985\u0986\7e\2\2\u0986\u0987\7t\2\2\u0987\u0988\7q\2\2\u0988" +
					"\u0989\7n\2\2\u0989\u098a\7n\2\2\u098a\u011a\3\2\2\2\u098b\u098c\7o\2" +
					"\2\u098c\u098d\7c\2\2\u098d\u098e\7v\2\2\u098e\u098f\7g\2\2\u098f\u0990" +
					"\7t\2\2\u0990\u0991\7k\2\2\u0991\u0992\7c\2\2\u0992\u0993\7n\2\2\u0993" +
					"\u011c\3\2\2\2\u0994\u0995\7t\2\2\u0995\u0996\7g\2\2\u0996\u0997\7e\2" +
					"\2\u0997\u0998\7k\2\2\u0998\u0999\7r\2\2\u0999\u099a\7g\2\2\u099a\u011e" +
					"\3\2\2\2\u099b\u099c\7r\2\2\u099c\u099d\7g\2\2\u099d\u099e\7v\2\2\u099e" +
					"\u099f\7a\2\2\u099f\u09a0\7e\2\2\u09a0\u09a1\7q\2\2\u09a1\u09a2\7n\2\2" +
					"\u09a2\u09a3\7n\2\2\u09a3\u09a4\7c\2\2\u09a4\u09a5\7t\2\2\u09a5\u0120" +
					"\3\2\2\2\u09a6\u09a7\7e\2\2\u09a7\u09a8\7c\2\2\u09a8\u09a9\7u\2\2\u09a9" +
					"\u09aa\7v\2\2\u09aa\u09ab\7n\2\2\u09ab\u09ac\7g\2\2\u09ac\u09ad\7a\2\2" +
					"\u09ad\u09ae\7i\2\2\u09ae\u09af\7w\2\2\u09af\u09b0\7c\2\2\u09b0\u09b1" +
					"\7t\2\2\u09b1\u09b2\7f\2\2\u09b2\u0122\3\2\2\2\u09b3\u09b4\7n\2\2\u09b4" +
					"\u09b5\7q\2\2\u09b5\u09b6\7v\2\2\u09b6\u09b7\7v\2\2\u09b7\u09b8\7q\2\2" +
					"\u09b8\u0124\3\2\2\2\u09b9\u09ba\7t\2\2\u09ba\u09bb\7c\2\2\u09bb\u09bc" +
					"\7e\2\2\u09bc\u09bd\7g\2\2\u09bd\u09be\7a\2\2\u09be\u09bf\7v\2\2\u09bf" +
					"\u09c0\7k\2\2\u09c0\u09c1\7e\2\2\u09c1\u09c2\7m\2\2\u09c2\u09c3\7g\2\2" +
					"\u09c3\u09c4\7v\2\2\u09c4\u0126\3\2\2\2\u09c5\u09c6\7f\2\2\u09c6\u09c7" +
					"\7{\2\2\u09c7\u09c8\7g\2\2\u09c8\u0128\3\2\2\2\u09c9\u09ca\7u\2\2\u09ca" +
					"\u09cb\7g\2\2\u09cb\u09cc\7g\2\2\u09cc\u09cd\7f\2\2\u09cd\u012a\3\2\2" +
					"\2\u09ce\u09cf\7u\2\2\u09cf\u09d0\7g\2\2\u09d0\u09d1\7g\2\2\u09d1\u09d2" +
					"\7f\2\2\u09d2\u09d3\7\64\2\2\u09d3\u012c\3\2\2\2\u09d4\u09d5\7e\2\2\u09d5" +
					"\u09d6\7t\2\2\u09d6\u09d7\7q\2\2\u09d7\u09d8\7r\2\2\u09d8\u012e\3\2\2" +
					"\2\u09d9\u09da\7o\2\2\u09da\u09db\7c\2\2\u09db\u09dc\7v\2\2\u09dc\u09dd" +
					"\7w\2\2\u09dd\u09de\7t\2\2\u09de\u09df\7g\2\2\u09df\u09e0\7e\2\2\u09e0" +
					"\u09e1\7t\2\2\u09e1\u09e2\7q\2\2\u09e2\u09e3\7r\2\2\u09e3\u0130\3\2\2" +
					"\2\u09e4\u09e5\7j\2\2\u09e5\u09e6\7c\2\2\u09e6\u09e7\7t\2\2\u09e7\u09e8" +
					"\7x\2\2\u09e8\u09e9\7g\2\2\u09e9\u09ea\7u\2\2\u09ea\u09eb\7v\2\2\u09eb" +
					"\u0132\3\2\2\2\u09ec\u09ed\7v\2\2\u09ed\u09ee\7k\2\2\u09ee\u09ef\7e\2" +
					"\2\u09ef\u09f0\7m\2\2\u09f0\u09f1\7g\2\2\u09f1\u09f2\7v\2\2\u09f2\u09f3" +
					"\7a\2\2\u09f3\u09f4\7q\2\2\u09f4\u09f5\7h\2\2\u09f5\u09f6\7a\2\2\u09f6" +
					"\u09f7\7n\2\2\u09f7\u09f8\7q\2\2\u09f8\u09f9\7t\2\2\u09f9\u09fa\7f\2\2" +
					"\u09fa\u0134\3\2\2\2\u09fb\u09fc\7n\2\2\u09fc\u09fd\7w\2\2\u09fd\u09fe" +
					"\7t\2\2\u09fe\u09ff\7g\2\2\u09ff\u0136\3\2\2\2\u0a00\u0a01\7d\2\2\u0a01" +
					"\u0a02\7n\2\2\u0a02\u0a03\7g\2\2\u0a03\u0a04\7u\2\2\u0a04\u0a05\7u\2\2" +
					"\u0a05\u0a06\7a\2\2\u0a06\u0a07\7u\2\2\u0a07\u0a08\7e\2\2\u0a08\u0a09" +
					"\7t\2\2\u0a09\u0a0a\7n\2\2\u0a0a\u0a0b\7a\2\2\u0a0b\u0a0c\7g\2\2\u0a0c" +
					"\u0a0d\7p\2\2\u0a0d\u0a0e\7e\2\2\u0a0e\u0a0f\7j\2\2\u0a0f\u0a10\7c\2\2" +
					"\u0a10\u0a11\7p\2\2\u0a11\u0a12\7v\2\2\u0a12\u0a13\7a\2\2\u0a13\u0a14" +
					"\7c\2\2\u0a14\u0a15\7o\2\2\u0a15\u0138\3\2\2\2\u0a16\u0a17\7d\2\2\u0a17" +
					"\u0a18\7n\2\2\u0a18\u0a19\7g\2\2\u0a19\u0a1a\7u\2\2\u0a1a\u0a1b\7u\2\2" +
					"\u0a1b\u0a1c\7a\2\2\u0a1c\u0a1d\7u\2\2\u0a1d\u0a1e\7e\2\2\u0a1e\u0a1f" +
					"\7t\2\2\u0a1f\u0a20\7n\2\2\u0a20\u0a21\7a\2\2\u0a21\u0a22\7g\2\2\u0a22" +
					"\u0a23\7p\2\2\u0a23\u0a24\7e\2\2\u0a24\u0a25\7j\2\2\u0a25\u0a26\7c\2\2" +
					"\u0a26\u0a27\7p\2\2\u0a27\u0a28\7v\2\2\u0a28\u0a29\7a\2\2\u0a29\u0a2a" +
					"\7y\2\2\u0a2a\u0a2b\7r\2\2\u0a2b\u013a\3\2\2\2\u0a2c\u0a2d\7e\2\2\u0a2d" +
					"\u0a2e\7q\2\2\u0a2e\u0a2f\7w\2\2\u0a2f\u0a30\7r\2\2\u0a30\u0a31\7q\2\2" +
					"\u0a31\u0a32\7p\2\2\u0a32\u013c\3\2\2\2\u0a33\u0a34\7g\2\2\u0a34\u0a35" +
					"\7n\2\2\u0a35\u0a36\7k\2\2\u0a36\u0a37\7z\2\2\u0a37\u0a38\7k\2\2\u0a38" +
					"\u0a39\7t\2\2\u0a39\u013e\3\2\2\2\u0a3a\u0a3b\7u\2\2\u0a3b\u0a3c\7e\2" +
					"\2\u0a3c\u0a3d\7t\2\2\u0a3d\u0a3e\7n\2\2\u0a3e\u0a3f\7a\2\2\u0a3f\u0a40" +
					"\7g\2\2\u0a40\u0a41\7p\2\2\u0a41\u0a42\7e\2\2\u0a42\u0a43\7j\2\2\u0a43" +
					"\u0a44\7c\2\2\u0a44\u0a45\7p\2\2\u0a45\u0a46\7v\2\2\u0a46\u0a47\7a\2\2" +
					"\u0a47\u0a48\7c\2\2\u0a48\u0a49\7v\2\2\u0a49\u0a4a\7v\2\2\u0a4a\u0a4b" +
					"\7t\2\2\u0a4b\u0140\3\2\2\2\u0a4c\u0a4d\7u\2\2\u0a4d\u0a4e\7e\2\2\u0a4e" +
					"\u0a4f\7t\2\2\u0a4f\u0a50\7n\2\2\u0a50\u0a51\7a\2\2\u0a51\u0a52\7k\2\2" +
					"\u0a52\u0a53\7p\2\2\u0a53\u0a54\7e\2\2\u0a54\u0a55\7a\2\2\u0a55\u0a56" +
					"\7g\2\2\u0a56\u0a57\7p\2\2\u0a57\u0a58\7e\2\2\u0a58\u0a59\7j\2\2\u0a59" +
					"\u0a5a\7c\2\2\u0a5a\u0a5b\7p\2\2\u0a5b\u0a5c\7v\2\2\u0a5c\u0a5d\7a\2\2" +
					"\u0a5d\u0a5e\7r\2\2\u0a5e\u0a5f\7t\2\2\u0a5f\u0a60\7q\2\2\u0a60\u0a61" +
					"\7r\2\2\u0a61\u0a62\7a\2\2\u0a62\u0a63\7y\2\2\u0a63\u0a64\7r\2\2\u0a64" +
					"\u0142\3\2\2\2\u0a65\u0a66\7u\2\2\u0a66\u0a67\7e\2\2\u0a67\u0a68\7t\2" +
					"\2\u0a68\u0a69\7n\2\2\u0a69\u0a6a\7a\2\2\u0a6a\u0a6b\7k\2\2\u0a6b\u0a6c" +
					"\7p\2\2\u0a6c\u0a6d\7e\2\2\u0a6d\u0a6e\7a\2\2\u0a6e\u0a6f\7g\2\2\u0a6f" +
					"\u0a70\7p\2\2\u0a70\u0a71\7e\2\2\u0a71\u0a72\7j\2\2\u0a72\u0a73\7c\2\2" +
					"\u0a73\u0a74\7p\2\2\u0a74\u0a75\7v\2\2\u0a75\u0a76\7a\2\2\u0a76\u0a77" +
					"\7r\2\2\u0a77\u0a78\7t\2\2\u0a78\u0a79\7q\2\2\u0a79\u0a7a\7r\2\2\u0a7a" +
					"\u0a7b\7a\2\2\u0a7b\u0a7c\7c\2\2\u0a7c\u0a7d\7o\2\2\u0a7d\u0144\3\2\2" +
					"\2\u0a7e\u0a7f\7d\2\2\u0a7f\u0a80\7q\2\2\u0a80\u0a81\7n\2\2\u0a81\u0a82" +
					"\7v\2\2\u0a82\u0146\3\2\2\2\u0a83\u0a84\7c\2\2\u0a84\u0a85\7p\2\2\u0a85" +
					"\u0a86\7e\2\2\u0a86\u0a87\7k\2\2\u0a87\u0a88\7g\2\2\u0a88\u0a89\7p\2\2" +
					"\u0a89\u0a8a\7v\2\2\u0a8a\u0a8b\7a\2\2\u0a8b\u0a8c\7e\2\2\u0a8c\u0a8d" +
					"\7t\2\2\u0a8d\u0a8e\7{\2\2\u0a8e\u0a8f\7u\2\2\u0a8f\u0a90\7v\2\2\u0a90" +
					"\u0a91\7c\2\2\u0a91\u0a92\7n\2\2\u0a92\u0a93\7a\2\2\u0a93\u0a94\7g\2\2" +
					"\u0a94\u0a95\7p\2\2\u0a95\u0a96\7e\2\2\u0a96\u0a97\7j\2\2\u0a97\u0a98" +
					"\7c\2\2\u0a98\u0a99\7p\2\2\u0a99\u0a9a\7v\2\2\u0a9a\u0a9b\7a\2\2\u0a9b" +
					"\u0a9c\7y\2\2\u0a9c\u0a9d\7r\2\2\u0a9d\u0148\3\2\2\2\u0a9e\u0a9f\7c\2" +
					"\2\u0a9f\u0aa0\7p\2\2\u0aa0\u0aa1\7e\2\2\u0aa1\u0aa2\7k\2\2\u0aa2\u0aa3" +
					"\7g\2\2\u0aa3\u0aa4\7p\2\2\u0aa4\u0aa5\7v\2\2\u0aa5\u0aa6\7a\2\2\u0aa6" +
					"\u0aa7\7e\2\2\u0aa7\u0aa8\7t\2\2\u0aa8\u0aa9\7{\2\2\u0aa9\u0aaa\7u\2\2" +
					"\u0aaa\u0aab\7v\2\2\u0aab\u0aac\7c\2\2\u0aac\u0aad\7n\2\2\u0aad\u0aae" +
					"\7a\2\2\u0aae\u0aaf\7g\2\2\u0aaf\u0ab0\7p\2\2\u0ab0\u0ab1\7e\2\2\u0ab1" +
					"\u0ab2\7j\2\2\u0ab2\u0ab3\7c\2\2\u0ab3\u0ab4\7p\2\2\u0ab4\u0ab5\7v\2\2" +
					"\u0ab5\u0ab6\7a\2\2\u0ab6\u0ab7\7c\2\2\u0ab7\u0ab8\7o\2\2\u0ab8\u014a" +
					"\3\2\2\2\u0ab9\u0aba\7t\2\2\u0aba\u0abb\7w\2\2\u0abb\u0abc\7p\2\2\u0abc" +
					"\u0abd\7g\2\2\u0abd\u0abe\7a\2\2\u0abe\u0abf\7u\2\2\u0abf\u0ac0\7g\2\2" +
					"\u0ac0\u0ac1\7n\2\2\u0ac1\u0ac2\7g\2\2\u0ac2\u0ac3\7e\2\2\u0ac3\u0ac4" +
					"\7v\2\2\u0ac4\u014c\3\2\2\2\u0ac5\u0ac6\7t\2\2\u0ac6\u0ac7\7w\2\2\u0ac7" +
					"\u0ac8\7p\2\2\u0ac8\u0ac9\7g\2\2\u0ac9\u014e\3\2\2\2\u0aca\u0acb\7c\2" +
					"\2\u0acb\u0acc\7e\2\2\u0acc\u0acd\7v\2\2\u0acd\u0ace\7k\2\2\u0ace\u0acf" +
					"\7q\2\2\u0acf\u0ad0\7p\2\2\u0ad0\u0ad1\7a\2\2\u0ad1\u0ad2\7p\2\2\u0ad2" +
					"\u0ad3\7q\2\2\u0ad3\u0ad4\7p\2\2\u0ad4\u0ad5\7g\2\2\u0ad5\u0150\3\2\2" +
					"\2\u0ad6\u0ad7\7c\2\2\u0ad7\u0ad8\7e\2\2\u0ad8\u0ad9\7v\2\2\u0ad9\u0ada" +
					"\7k\2\2\u0ada\u0adb\7q\2\2\u0adb\u0adc\7p\2\2\u0adc\u0add\7a\2\2\u0add" +
					"\u0ade\7g\2\2\u0ade\u0adf\7s\2\2\u0adf\u0ae0\7w\2\2\u0ae0\u0ae1\7k\2\2" +
					"\u0ae1\u0ae2\7r\2\2\u0ae2\u0152\3\2\2\2\u0ae3\u0ae4\7c\2\2\u0ae4\u0ae5" +
					"\7e\2\2\u0ae5\u0ae6\7v\2\2\u0ae6\u0ae7\7k\2\2\u0ae7\u0ae8\7q\2\2\u0ae8" +
					"\u0ae9\7p\2\2\u0ae9\u0aea\7a\2\2\u0aea\u0aeb\7r\2\2\u0aeb\u0aec\7g\2\2" +
					"\u0aec\u0aed\7g\2\2\u0aed\u0aee\7n\2\2\u0aee\u0154\3\2\2\2\u0aef\u0af0" +
					"\7c\2\2\u0af0\u0af1\7e\2\2\u0af1\u0af2\7v\2\2\u0af2\u0af3\7k\2\2\u0af3" +
					"\u0af4\7q\2\2\u0af4\u0af5\7p\2\2\u0af5\u0af6\7a\2\2\u0af6\u0af7\7u\2\2" +
					"\u0af7\u0af8\7m\2\2\u0af8\u0af9\7k\2\2\u0af9\u0afa\7n\2\2\u0afa\u0afb" +
					"\7n\2\2\u0afb\u0afc\7a\2\2\u0afc\u0afd\7t\2\2\u0afd\u0afe\7g\2\2\u0afe" +
					"\u0aff\7f\2\2\u0aff\u0b00\7w\2\2\u0b00\u0b01\7e\2\2\u0b01\u0b02\7g\2\2" +
					"\u0b02\u0156\3\2\2\2\u0b03\u0b04\7c\2\2\u0b04\u0b05\7e\2\2\u0b05\u0b06" +
					"\7v\2\2\u0b06\u0b07\7k\2\2\u0b07\u0b08\7q\2\2\u0b08\u0b09\7p\2\2\u0b09" +
					"\u0b0a\7a\2\2\u0b0a\u0b0b\7u\2\2\u0b0b\u0b0c\7j\2\2\u0b0c\u0b0d\7q\2\2" +
					"\u0b0d\u0b0e\7y\2\2\u0b0e\u0b0f\7a\2\2\u0b0f\u0b10\7j\2\2\u0b10\u0b11" +
					"\7v\2\2\u0b11\u0b12\7o\2\2\u0b12\u0b13\7n\2\2\u0b13\u0158\3\2\2\2\u0b14" +
					"\u0b15\7c\2\2\u0b15\u0b16\7e\2\2\u0b16\u0b17\7v\2\2\u0b17\u0b18\7k\2\2" +
					"\u0b18\u0b19\7q\2\2\u0b19\u0b1a\7p\2\2\u0b1a\u0b1b\7a\2\2\u0b1b\u0b1c" +
					"\7u\2\2\u0b1c\u0b1d\7q\2\2\u0b1d\u0b1e\7w\2\2\u0b1e\u0b1f\7n\2\2\u0b1f" +
					"\u0b20\7u\2\2\u0b20\u0b21\7j\2\2\u0b21\u0b22\7q\2\2\u0b22\u0b23\7v\2\2" +
					"\u0b23\u015a\3\2\2\2\u0b24\u0b25\7c\2\2\u0b25\u0b26\7e\2\2\u0b26\u0b27" +
					"\7v\2\2\u0b27\u0b28\7k\2\2\u0b28\u0b29\7q\2\2\u0b29\u0b2a\7p\2\2\u0b2a" +
					"\u0b2b\7a\2\2\u0b2b\u0b2c\7t\2\2\u0b2c\u0b2d\7g\2\2\u0b2d\u0b2e\7e\2\2" +
					"\u0b2e\u0b2f\7k\2\2\u0b2f\u0b30\7r\2\2\u0b30\u0b31\7g\2\2\u0b31\u015c" +
					"\3\2\2\2\u0b32\u0b33\7c\2\2\u0b33\u0b34\7e\2\2\u0b34\u0b35\7v\2\2\u0b35" +
					"\u0b36\7k\2\2\u0b36\u0b37\7q\2\2\u0b37\u0b38\7p\2\2\u0b38\u0b39\7a\2\2" +
					"\u0b39\u0b3a\7u\2\2\u0b3a\u0b3b\7m\2\2\u0b3b\u0b3c\7k\2\2\u0b3c\u0b3d" +
					"\7n\2\2\u0b3d\u0b3e\7n\2\2\u0b3e\u0b3f\7a\2\2\u0b3f\u0b40\7o\2\2\u0b40" +
					"\u0b41\7c\2\2\u0b41\u0b42\7k\2\2\u0b42\u0b43\7p\2\2\u0b43\u0b44\7v\2\2" +
					"\u0b44\u0b45\7c\2\2\u0b45\u0b46\7k\2\2\u0b46\u0b47\7p\2\2\u0b47\u015e" +
					"\3\2\2\2\u0b48\u0b49\7c\2\2\u0b49\u0b4a\7e\2\2\u0b4a\u0b4b\7v\2\2\u0b4b" +
					"\u0b4c\7k\2\2\u0b4c\u0b4d\7q\2\2\u0b4d\u0b4e\7p\2\2\u0b4e\u0b4f\7a\2\2" +
					"\u0b4f\u0b50\7u\2\2\u0b50\u0b51\7r\2\2\u0b51\u0b52\7k\2\2\u0b52\u0b53" +
					"\7t\2\2\u0b53\u0b54\7k\2\2\u0b54\u0b55\7v\2\2\u0b55\u0b56\7u\2\2\u0b56" +
					"\u0b57\7j\2\2\u0b57\u0b58\7q\2\2\u0b58\u0b59\7v\2\2\u0b59\u0160\3\2\2" +
					"\2\u0b5a\u0b5b\7c\2\2\u0b5b\u0b5c\7e\2\2\u0b5c\u0b5d\7v\2\2\u0b5d\u0b5e" +
					"\7k\2\2\u0b5e\u0b5f\7q\2\2\u0b5f\u0b60\7p\2\2\u0b60\u0b61\7a\2\2\u0b61" +
					"\u0b62\7f\2\2\u0b62\u0b63\7k\2\2\u0b63\u0b64\7e\2\2\u0b64\u0b65\7g\2\2" +
					"\u0b65\u0162\3\2\2\2\u0b66\u0b67\7c\2\2\u0b67\u0b68\7e\2\2\u0b68\u0b69" +
					"\7v\2\2\u0b69\u0b6a\7k\2\2\u0b6a\u0b6b\7q\2\2\u0b6b\u0b6c\7p\2\2\u0b6c" +
					"\u0b6d\7a\2\2\u0b6d\u0b6e\7e\2\2\u0b6e\u0b6f\7c\2\2\u0b6f\u0b70\7n\2\2" +
					"\u0b70\u0b71\7e\2\2\u0b71\u0164\3\2\2\2\u0b72\u0b73\7c\2\2\u0b73\u0b74" +
					"\7e\2\2\u0b74\u0b75\7v\2\2\u0b75\u0b76\7k\2\2\u0b76\u0b77\7q\2\2\u0b77" +
					"\u0b78\7p\2\2\u0b78\u0b79\7a\2\2\u0b79\u0b7a\7u\2\2\u0b7a\u0b7b\7g\2\2" +
					"\u0b7b\u0b7c\7g\2\2\u0b7c\u0b7d\7f\2\2\u0b7d\u0166\3\2\2\2\u0b7e\u0b7f" +
					"\7c\2\2\u0b7f\u0b80\7e\2\2\u0b80\u0b81\7v\2\2\u0b81\u0b82\7k\2\2\u0b82" +
					"\u0b83\7q\2\2\u0b83\u0b84\7p\2\2\u0b84\u0b85\7a\2\2\u0b85\u0b86\7j\2\2" +
					"\u0b86\u0b87\7c\2\2\u0b87\u0b88\7t\2\2\u0b88\u0b89\7x\2\2\u0b89\u0b8a" +
					"\7g\2\2\u0b8a\u0b8b\7u\2\2\u0b8b\u0b8c\7v\2\2\u0b8c\u0168\3\2\2\2\u0b8d" +
					"\u0b8e\7c\2\2\u0b8e\u0b8f\7e\2\2\u0b8f\u0b90\7v\2\2\u0b90\u0b91\7k\2\2" +
					"\u0b91\u0b92\7q\2\2\u0b92\u0b93\7p\2\2\u0b93\u0b94\7a\2\2\u0b94\u0b95" +
					"\7e\2\2\u0b95\u0b96\7c\2\2\u0b96\u0b97\7r\2\2\u0b97\u0b98\7u\2\2\u0b98" +
					"\u0b99\7w\2\2\u0b99\u0b9a\7n\2\2\u0b9a\u0b9b\7g\2\2\u0b9b\u016a\3\2\2" +
					"\2\u0b9c\u0b9d\7c\2\2\u0b9d\u0b9e\7e\2\2\u0b9e\u0b9f\7v\2\2\u0b9f\u0ba0" +
					"\7k\2\2\u0ba0\u0ba1\7q\2\2\u0ba1\u0ba2\7p\2\2\u0ba2\u0ba3\7a\2\2\u0ba3" +
					"\u0ba4\7z\2\2\u0ba4\u0ba5\7o\2\2\u0ba5\u0ba6\7c\2\2\u0ba6\u0ba7\7u\2\2" +
					"\u0ba7\u0ba8\7a\2\2\u0ba8\u0ba9\7q\2\2\u0ba9\u0baa\7r\2\2\u0baa\u0bab" +
					"\7g\2\2\u0bab\u0bac\7p\2\2\u0bac\u016c\3\2\2\2\u0bad\u0bae\7c\2\2\u0bae" +
					"\u0baf\7e\2\2\u0baf\u0bb0\7v\2\2\u0bb0\u0bb1\7k\2\2\u0bb1\u0bb2\7q\2\2" +
					"\u0bb2\u0bb3\7p\2\2\u0bb3\u0bb4\7a\2\2\u0bb4\u0bb5\7u\2\2\u0bb5\u0bb6" +
					"\7j\2\2\u0bb6\u0bb7\7q\2\2\u0bb7\u0bb8\7y\2\2\u0bb8\u0bb9\7a\2\2\u0bb9" +
					"\u0bba\7u\2\2\u0bba\u0bbb\7u\2\2\u0bbb\u0bbc\7s\2\2\u0bbc\u0bbd\7a\2\2" +
					"\u0bbd\u0bbe\7u\2\2\u0bbe\u0bbf\7v\2\2\u0bbf\u0bc0\7c\2\2\u0bc0\u0bc1" +
					"\7v\2\2\u0bc1\u0bc2\7w\2\2\u0bc2\u0bc3\7u\2\2\u0bc3\u016e\3\2\2\2\u0bc4" +
					"\u0bc5\7c\2\2\u0bc5\u0bc6\7e\2\2\u0bc6\u0bc7\7v\2\2\u0bc7\u0bc8\7k\2\2" +
					"\u0bc8\u0bc9\7q\2\2\u0bc9\u0bca\7p\2\2\u0bca\u0bcb\7a\2\2\u0bcb\u0bcc" +
					"\7h\2\2\u0bcc\u0bcd\7k\2\2\u0bcd\u0bce\7u\2\2\u0bce\u0bcf\7j\2\2\u0bcf" +
					"\u0bd0\7k\2\2\u0bd0\u0bd1\7p\2\2\u0bd1\u0bd2\7i\2\2\u0bd2\u0bd3\7u\2\2" +
					"\u0bd3\u0bd4\7j\2\2\u0bd4\u0bd5\7q\2\2\u0bd5\u0bd6\7v\2\2\u0bd6\u0170" +
					"\3\2\2\2\u0bd7\u0bd8\7c\2\2\u0bd8\u0bd9\7e\2\2\u0bd9\u0bda\7v\2\2\u0bda" +
					"\u0bdb\7k\2\2\u0bdb\u0bdc\7q\2\2\u0bdc\u0bdd\7p\2\2\u0bdd\u0bde\7a\2\2" +
					"\u0bde\u0bdf\7u\2\2\u0bdf\u0be0\7w\2\2\u0be0\u0be1\7o\2\2\u0be1\u0be2" +
					"\7o\2\2\u0be2\u0be3\7q\2\2\u0be3\u0be4\7p\2\2\u0be4\u0be5\7a\2\2\u0be5" +
					"\u0be6\7u\2\2\u0be6\u0be7\7q\2\2\u0be7\u0be8\7w\2\2\u0be8\u0be9\7n\2\2" +
					"\u0be9\u0bea\7u\2\2\u0bea\u0beb\7j\2\2\u0beb\u0bec\7q\2\2\u0bec\u0bed" +
					"\7v\2\2\u0bed\u0172\3\2\2\2\u0bee\u0bef\7c\2\2\u0bef\u0bf0\7e\2\2\u0bf0" +
					"\u0bf1\7v\2\2\u0bf1\u0bf2\7k\2\2\u0bf2\u0bf3\7q\2\2\u0bf3\u0bf4\7p\2\2" +
					"\u0bf4\u0bf5\7a\2\2\u0bf5\u0bf6\7u\2\2\u0bf6\u0bf7\7w\2\2\u0bf7\u0bf8" +
					"\7o\2\2\u0bf8\u0bf9\7o\2\2\u0bf9\u0bfa\7q\2\2\u0bfa\u0bfb\7p\2\2\u0bfb" +
					"\u0bfc\7a\2\2\u0bfc\u0bfd\7u\2\2\u0bfd\u0bfe\7r\2\2\u0bfe\u0bff\7k\2\2" +
					"\u0bff\u0c00\7t\2\2\u0c00\u0c01\7k\2\2\u0c01\u0c02\7v\2\2\u0c02\u0c03" +
					"\7u\2\2\u0c03\u0c04\7j\2\2\u0c04\u0c05\7q\2\2\u0c05\u0c06\7v\2\2\u0c06" +
					"\u0174\3\2\2\2\u0c07\u0c08\7c\2\2\u0c08\u0c09\7e\2\2\u0c09\u0c0a\7v\2" +
					"\2\u0c0a\u0c0b\7k\2\2\u0c0b\u0c0c\7q\2\2\u0c0c\u0c0d\7p\2\2\u0c0d\u0c0e" +
					"\7a\2\2\u0c0e\u0c0f\7e\2\2\u0c0f\u0c10\7c\2\2\u0c10\u0c11\7n\2\2\u0c11" +
					"\u0c12\7n\2\2\u0c12\u0c13\7a\2\2\u0c13\u0c14\7u\2\2\u0c14\u0c15\7m\2\2" +
					"\u0c15\u0c16\7k\2\2\u0c16\u0c17\7n\2\2\u0c17\u0c18\7n\2\2\u0c18\u0176" +
					"\3\2\2\2\u0c19\u0c1a\7c\2\2\u0c1a\u0c1b\7e\2\2\u0c1b\u0c1c\7v\2\2\u0c1c" +
					"\u0c1d\7k\2\2\u0c1d\u0c1e\7q\2\2\u0c1e\u0c1f\7p\2\2\u0c1f\u0c20\7a\2\2" +
					"\u0c20\u0c21\7u\2\2\u0c21\u0c22\7j\2\2\u0c22\u0c23\7q\2\2\u0c23\u0c24" +
					"\7y\2\2\u0c24\u0c25\7a\2\2\u0c25\u0c26\7c\2\2\u0c26\u0c27\7f\2\2\u0c27" +
					"\u0c28\7x\2\2\u0c28\u0c29\7g\2\2\u0c29\u0c2a\7p\2\2\u0c2a\u0c2b\7v\2\2" +
					"\u0c2b\u0c2c\7w\2\2\u0c2c\u0c2d\7t\2\2\u0c2d\u0c2e\7g\2\2\u0c2e\u0c2f" +
					"\7t\2\2\u0c2f\u0c30\7a\2\2\u0c30\u0c31\7i\2\2\u0c31\u0c32\7w\2\2\u0c32" +
					"\u0c33\7k\2\2\u0c33\u0c34\7f\2\2\u0c34\u0c35\7g\2\2\u0c35\u0c36\7a\2\2" +
					"\u0c36\u0c37\7d\2\2\u0c37\u0c38\7q\2\2\u0c38\u0c39\7q\2\2\u0c39\u0c3a" +
					"\7m\2\2\u0c3a\u0178\3\2\2\2\u0c3b\u0c3c\7c\2\2\u0c3c\u0c3d\7e\2\2\u0c3d" +
					"\u0c3e\7v\2\2\u0c3e\u0c3f\7k\2\2\u0c3f\u0c40\7q\2\2\u0c40\u0c41\7p\2\2" +
					"\u0c41\u0c42\7a\2\2\u0c42\u0c43\7m\2\2\u0c43\u0c44\7g\2\2\u0c44\u0c45" +
					"\7g\2\2\u0c45\u0c46\7r\2\2\u0c46\u0c47\7a\2\2\u0c47\u0c48\7g\2\2\u0c48" +
					"\u0c49\7z\2\2\u0c49\u0c4a\7r\2\2\u0c4a\u017a\3\2\2\2\u0c4b\u0c4c\7c\2" +
					"\2\u0c4c\u0c4d\7e\2\2\u0c4d\u0c4e\7v\2\2\u0c4e\u0c4f\7k\2\2\u0c4f\u0c50" +
					"\7q\2\2\u0c50\u0c51\7p\2\2\u0c51\u0c52\7a\2\2\u0c52\u0c53\7e\2\2\u0c53" +
					"\u0c54\7t\2\2\u0c54\u0c55\7g\2\2\u0c55\u0c56\7c\2\2\u0c56\u0c57\7v\2\2" +
					"\u0c57\u0c58\7g\2\2\u0c58\u0c59\7a\2\2\u0c59\u0c5a\7o\2\2\u0c5a\u0c5b" +
					"\7r\2\2\u0c5b\u0c5c\7e\2\2\u0c5c\u0c5d\7e\2\2\u0c5d\u017c\3\2\2\2\u0c5e" +
					"\u0c5f\7c\2\2\u0c5f\u0c60\7e\2\2\u0c60\u0c61\7v\2\2\u0c61\u0c62\7k\2\2" +
					"\u0c62\u0c63\7q\2\2\u0c63\u0c64\7p\2\2\u0c64\u0c65\7a\2\2\u0c65\u0c66" +
					"\7p\2\2\u0c66\u0c67\7k\2\2\u0c67\u0c68\7e\2\2\u0c68\u0c69\7m\2\2\u0c69" +
					"\u0c6a\7a\2\2\u0c6a\u0c6b\7e\2\2\u0c6b\u0c6c\7q\2\2\u0c6c\u0c6d\7n\2\2" +
					"\u0c6d\u0c6e\7q\2\2\u0c6e\u0c6f\7t\2\2\u0c6f\u017e\3\2\2\2\u0c70\u0c71" +
					"\7c\2\2\u0c71\u0c72\7e\2\2\u0c72\u0c73\7v\2\2\u0c73\u0c74\7k\2\2\u0c74" +
					"\u0c75\7q\2\2\u0c75\u0c76\7p\2\2\u0c76\u0c77\7a\2\2\u0c77\u0c78\7j\2\2" +
					"\u0c78\u0c79\7k\2\2\u0c79\u0c7a\7f\2\2\u0c7a\u0c7b\7g\2\2\u0c7b\u0c7c" +
					"\7a\2\2\u0c7c\u0c7d\7p\2\2\u0c7d\u0c7e\7c\2\2\u0c7e\u0c7f\7o\2\2\u0c7f" +
					"\u0c80\7g\2\2\u0c80\u0180\3\2\2\2\u0c81\u0c82\7c\2\2\u0c82\u0c83\7e\2" +
					"\2\u0c83\u0c84\7v\2\2\u0c84\u0c85\7k\2\2\u0c85\u0c86\7q\2\2\u0c86\u0c87" +
					"\7p\2\2\u0c87\u0c88\7a\2\2\u0c88\u0c89\7u\2\2\u0c89\u0c8a\7v\2\2\u0c8a" +
					"\u0c8b\7c\2\2\u0c8b\u0c8c\7t\2\2\u0c8c\u0c8d\7v\2\2\u0c8d\u0c8e\7a\2\2" +
					"\u0c8e\u0c8f\7s\2\2\u0c8f\u0c90\7w\2\2\u0c90\u0c91\7g\2\2\u0c91\u0c92" +
					"\7u\2\2\u0c92\u0c93\7v\2\2\u0c93\u0182\3\2\2\2\u0c94\u0c95\7e\2\2\u0c95" +
					"\u0c96\7q\2\2\u0c96\u0c97\7p\2\2\u0c97\u0c98\7u\2\2\u0c98\u0c99\7w\2\2" +
					"\u0c99\u0c9a\7o\2\2\u0c9a\u0c9b\7g\2\2\u0c9b\u0c9c\7a\2\2\u0c9c\u0c9d" +
					"\7v\2\2\u0c9d\u0c9e\7{\2\2\u0c9e\u0c9f\7r\2\2\u0c9f\u0ca0\7g\2\2\u0ca0" +
					"\u0ca1\7a\2\2\u0ca1\u0ca2\7p\2\2\u0ca2\u0ca3\7q\2\2\u0ca3\u0ca4\7t\2\2" +
					"\u0ca4\u0ca5\7o\2\2\u0ca5\u0ca6\7c\2\2\u0ca6\u0ca7\7n\2\2\u0ca7\u0184" +
					"\3\2\2\2\u0ca8\u0ca9\7e\2\2\u0ca9\u0caa\7q\2\2\u0caa\u0cab\7p\2\2\u0cab" +
					"\u0cac\7u\2\2\u0cac\u0cad\7w\2\2\u0cad\u0cae\7o\2\2\u0cae\u0caf\7g\2\2" +
					"\u0caf\u0cb0\7a\2\2\u0cb0\u0cb1\7v\2\2\u0cb1\u0cb2\7{\2\2\u0cb2\u0cb3" +
					"\7r\2\2\u0cb3\u0cb4\7g\2\2\u0cb4\u0cb5\7a\2\2\u0cb5\u0cb6\7u\2\2\u0cb6" +
					"\u0cb7\7v\2\2\u0cb7\u0cb8\7c\2\2\u0cb8\u0cb9\7e\2\2\u0cb9\u0cba\7m\2\2" +
					"\u0cba\u0cbb\7c\2\2\u0cbb\u0cbc\7d\2\2\u0cbc\u0cbd\7n\2\2\u0cbd\u0cbe" +
					"\7g\2\2\u0cbe\u0186\3\2\2\2\u0cbf\u0cc0\7e\2\2\u0cc0\u0cc1\7q\2\2\u0cc1" +
					"\u0cc2\7p\2\2\u0cc2\u0cc3\7u\2\2\u0cc3\u0cc4\7w\2\2\u0cc4\u0cc5\7o\2\2" +
					"\u0cc5\u0cc6\7g\2\2\u0cc6\u0cc7\7a\2\2\u0cc7\u0cc8\7v\2\2\u0cc8\u0cc9" +
					"\7{\2\2\u0cc9\u0cca\7r\2\2\u0cca\u0ccb\7g\2\2\u0ccb\u0ccc\7a\2\2\u0ccc" +
					"\u0ccd\7c\2\2\u0ccd\u0cce\7u\2\2\u0cce\u0ccf\7u\2\2\u0ccf\u0cd0\7g\2\2" +
					"\u0cd0\u0cd1\7v\2\2\u0cd1\u0188\3\2\2\2\u0cd2\u0cd3\7e\2\2\u0cd3\u0cd4" +
					"\7t\2\2\u0cd4\u0cd5\7{\2\2\u0cd5\u0cd6\7u\2\2\u0cd6\u0cd7\7v\2\2\u0cd7" +
					"\u0cd8\7c\2\2\u0cd8\u0cd9\7n\2\2\u0cd9\u0cda\7a\2\2\u0cda\u0cdb\7h\2\2" +
					"\u0cdb\u0cdc\7t\2\2\u0cdc\u0cdd\7g\2\2\u0cdd\u0cde\7g\2\2\u0cde\u018a" +
					"\3\2\2\2\u0cdf\u0ce0\7g\2\2\u0ce0\u0ce1\7x\2\2\u0ce1\u0ce2\7g\2\2\u0ce2" +
					"\u0ce3\7p\2\2\u0ce3\u0ce4\7v\2\2\u0ce4\u018c\3\2\2\2\u0ce5\u0ce6\7f\2" +
					"\2\u0ce6\u018e\3\2\2\2\u0ce7\u0ce8\7e\2\2\u0ce8\u0190\3\2\2\2\u0ce9\u0cea" +
					"\7d\2\2\u0cea\u0192\3\2\2\2\u0ceb\u0cec\7c\2\2\u0cec\u0194\3\2\2\2\u0ced" +
					"\u0cee\7u\2\2\u0cee\u0cef\7:\2\2\u0cef\u0cf0\7\62\2\2\u0cf0\u0196\3\2" +
					"\2\2\u0cf1\u0cf2\7u\2\2\u0cf2\u0cf3\7:\2\2\u0cf3\u0cf4\7\66\2\2\u0cf4" +
					"\u0198\3\2\2\2\u0cf5\u0cf6\7u\2\2\u0cf6\u019a\3\2\2\2\u0cf7\u0cf8\7B\2" +
					"\2\u0cf8\u0cf9\5\u0299\u014d\2\u0cf9\u019c\3\2\2\2\u0cfa\u0cfb\5\u01b3" +
					"\u00da\2\u0cfb\u019e\3\2\2\2\u0cfc\u0cfe\5\u01b1\u00d9\2\u0cfd\u0cfc\3" +
					"\2\2\2\u0cfd\u0cfe\3\2\2\2\u0cfe\u0cff\3\2\2\2\u0cff\u0d00\5\u01af\u00d8" +
					"\2\u0d00\u01a0\3\2\2\2\u0d01\u0d02\5\u019f\u00d0\2\u0d02\u0d04\7\60\2" +
					"\2\u0d03\u0d05\5\u01b5\u00db\2\u0d04\u0d03\3\2\2\2\u0d05\u0d06\3\2\2\2" +
					"\u0d06\u0d04\3\2\2\2\u0d06\u0d07\3\2\2\2\u0d07\u01a2\3\2\2\2\u0d08\u0d09" +
					"\7h\2\2\u0d09\u0d0a\7k\2\2\u0d0a\u0d0b\7t\2\2\u0d0b\u0d0c\7g\2\2\u0d0c" +
					"\u01a4\3\2\2\2\u0d0d\u0d0e\7y\2\2\u0d0e\u0d0f\7c\2\2\u0d0f\u0d10\7v\2" +
					"\2\u0d10\u0d11\7g\2\2\u0d11\u0d12\7t\2\2\u0d12\u01a6\3\2\2\2\u0d13\u0d14" +
					"\7g\2\2\u0d14\u0d15\7c\2\2\u0d15\u0d16\7t\2\2\u0d16\u0d17\7v\2\2\u0d17" +
					"\u0d18\7j\2\2\u0d18\u01a8\3\2\2\2\u0d19\u0d1a\7y\2\2\u0d1a\u0d1b\7k\2" +
					"\2\u0d1b\u0d1c\7p\2\2\u0d1c\u0d1d\7f\2\2\u0d1d\u01aa\3\2\2\2\u0d1e\u0d1f" +
					"\7w\2\2\u0d1f\u0d20\7p\2\2\u0d20\u0d21\7j\2\2\u0d21\u0d22\7q\2\2\u0d22" +
					"\u0d23\7n\2\2\u0d23\u0d24\7{\2\2\u0d24\u01ac\3\2\2\2\u0d25\u0d26\7j\2" +
					"\2\u0d26\u0d27\7q\2\2\u0d27\u0d28\7n\2\2\u0d28\u0d29\7{\2\2\u0d29\u01ae" +
					"\3\2\2\2\u0d2a\u0d33\5\u01b9\u00dd\2\u0d2b\u0d2f\5\u01b7\u00dc\2\u0d2c" +
					"\u0d2e\5\u01b5\u00db\2\u0d2d\u0d2c\3\2\2\2\u0d2e\u0d31\3\2\2\2\u0d2f\u0d2d" +
					"\3\2\2\2\u0d2f\u0d30\3\2\2\2\u0d30\u0d33\3\2\2\2\u0d31\u0d2f\3\2\2\2\u0d32" +
					"\u0d2a\3\2\2\2\u0d32\u0d2b\3\2\2\2\u0d33\u01b0\3\2\2\2\u0d34\u0d35\7/" +
					"\2\2\u0d35\u01b2\3\2\2\2\u0d36\u0d37\t\2\2\2\u0d37\u01b4\3\2\2\2\u0d38" +
					"\u0d39\t\3\2\2\u0d39\u01b6\3\2\2\2\u0d3a\u0d3b\t\4\2\2\u0d3b\u01b8\3\2" +
					"\2\2\u0d3c\u0d3d\t\5\2\2\u0d3d\u01ba\3\2\2\2\u0d3e\u0d3f\7=\2\2\u0d3f" +
					"\u01bc\3\2\2\2\u0d40\u0d41\7p\2\2\u0d41\u0d42\7q\2\2\u0d42\u0d43\7p\2" +
					"\2\u0d43\u0d44\7g\2\2\u0d44\u01be\3\2\2\2\u0d45\u0d46\7h\2\2\u0d46\u0d47" +
					"\7c\2\2\u0d47\u0d48\7k\2\2\u0d48\u0d49\7t\2\2\u0d49\u0d4a\7{\2\2\u0d4a" +
					"\u01c0\3\2\2\2\u0d4b\u0d4c\7c\2\2\u0d4c\u0d4d\7p\2\2\u0d4d\u0d4e\7k\2" +
					"\2\u0d4e\u0d4f\7o\2\2\u0d4f\u0d50\7c\2\2\u0d50\u0d51\7n\2\2\u0d51\u01c2" +
					"\3\2\2\2\u0d52\u0d53\7j\2\2\u0d53\u0d54\7w\2\2\u0d54\u0d55\7o\2\2\u0d55" +
					"\u0d56\7c\2\2\u0d56\u0d57\7p\2\2\u0d57\u0d58\7q\2\2\u0d58\u0d59\7k\2\2" +
					"\u0d59\u0d5a\7f\2\2\u0d5a\u01c4\3\2\2\2\u0d5b\u0d5c\7r\2\2\u0d5c\u0d5d" +
					"\7n\2\2\u0d5d\u0d5e\7c\2\2\u0d5e\u0d5f\7p\2\2\u0d5f\u0d60\7v\2\2\u0d60" +
					"\u01c6\3\2\2\2\u0d61\u0d62\7w\2\2\u0d62\u0d63\7p\2\2\u0d63\u0d64\7f\2" +
					"\2\u0d64\u0d65\7g\2\2\u0d65\u0d66\7c\2\2\u0d66\u0d67\7f\2\2\u0d67\u01c8" +
					"\3\2\2\2\u0d68\u0d69\7e\2\2\u0d69\u0d6a\7q\2\2\u0d6a\u0d6b\7p\2\2\u0d6b" +
					"\u0d6c\7u\2\2\u0d6c\u0d6d\7v\2\2\u0d6d\u0d6e\7t\2\2\u0d6e\u0d6f\7w\2\2" +
					"\u0d6f\u0d70\7e\2\2\u0d70\u0d71\7v\2\2\u0d71\u01ca\3\2\2\2\u0d72\u0d73" +
					"\7d\2\2\u0d73\u0d74\7g\2\2\u0d74\u0d75\7c\2\2\u0d75\u0d76\7u\2\2\u0d76" +
					"\u0d77\7v\2\2\u0d77\u01cc\3\2\2\2\u0d78\u0d79\7d\2\2\u0d79\u0d7a\7w\2" +
					"\2\u0d7a\u0d7b\7i\2\2\u0d7b\u01ce\3\2\2\2\u0d7c\u0d7d\7g\2\2\u0d7d\u0d7e" +
					"\7n\2\2\u0d7e\u0d7f\7g\2\2\u0d7f\u0d80\7o\2\2\u0d80\u0d81\7g\2\2\u0d81" +
					"\u0d82\7p\2\2\u0d82\u0d83\7v\2\2\u0d83\u0d84\7c\2\2\u0d84\u0d85\7n\2\2" +
					"\u0d85\u01d0\3\2\2\2\u0d86\u0d87\7f\2\2\u0d87\u0d88\7g\2\2\u0d88\u0d89" +
					"\7o\2\2\u0d89\u0d8a\7q\2\2\u0d8a\u0d8b\7p\2\2\u0d8b\u0d8c\7k\2\2\u0d8c" +
					"\u0d8d\7e\2\2\u0d8d\u01d2\3\2\2\2\u0d8e\u0d8f\7i\2\2\u0d8f\u0d90\7k\2" +
					"\2\u0d90\u0d91\7c\2\2\u0d91\u0d92\7p\2\2\u0d92\u0d93\7v\2\2\u0d93\u01d4" +
					"\3\2\2\2\u0d94\u0d95\7f\2\2\u0d95\u0d96\7t\2\2\u0d96\u0d97\7c\2\2\u0d97" +
					"\u0d98\7i\2\2\u0d98\u0d99\7q\2\2\u0d99\u0d9a\7p\2\2\u0d9a\u01d6\3\2\2" +
					"\2\u0d9b\u0d9c\7f\2\2\u0d9c\u0d9d\7k\2\2\u0d9d\u0d9e\7x\2\2\u0d9e\u0d9f" +
					"\7k\2\2\u0d9f\u0da0\7p\2\2\u0da0\u0da1\7g\2\2\u0da1\u01d8\3\2\2\2\u0da2" +
					"\u0da3\7u\2\2\u0da3\u0da4\7w\2\2\u0da4\u0da5\7o\2\2\u0da5\u0da6\7o\2\2" +
					"\u0da6\u0da7\7q\2\2\u0da7\u0da8\7p\2\2\u0da8\u01da\3\2\2\2\u0da9\u0daa" +
					"\7r\2\2\u0daa\u0dab\7g\2\2\u0dab\u0dac\7v\2\2\u0dac\u01dc\3\2\2\2\u0dad" +
					"\u0dae\7j\2\2\u0dae\u0daf\7q\2\2\u0daf\u0db0\7n\2\2\u0db0\u0db1\7{\2\2" +
					"\u0db1\u0db2\7v\2\2\u0db2\u0db3\7j\2\2\u0db3\u0db4\7k\2\2\u0db4\u0db5" +
					"\7p\2\2\u0db5\u0db6\7i\2\2\u0db6\u01de\3\2\2\2\u0db7\u0db8\7f\2\2\u0db8" +
					"\u0db9\7y\2\2\u0db9\u0dba\7c\2\2\u0dba\u0dbb\7t\2\2\u0dbb\u0dbc\7h\2\2" +
					"\u0dbc\u01e0\3\2\2\2\u0dbd\u0dbe\7o\2\2\u0dbe\u0dbf\7g\2\2\u0dbf\u0dc0" +
					"\7t\2\2\u0dc0\u0dc1\7e\2\2\u0dc1\u0dc2\7j\2\2\u0dc2\u0dc3\7c\2\2\u0dc3" +
					"\u0dc4\7p\2\2\u0dc4\u0dc5\7v\2\2\u0dc5\u01e2\3\2\2\2\u0dc6\u0dc7\7g\2" +
					"\2\u0dc7\u0dc8\7n\2\2\u0dc8\u0dc9\7h\2\2\u0dc9\u01e4\3\2\2\2\u0dca\u0dcb" +
					"\7m\2\2\u0dcb\u0dcc\7c\2\2\u0dcc\u0dcd\7o\2\2\u0dcd\u0dce\7c\2\2\u0dce" +
					"\u0dcf\7g\2\2\u0dcf\u0dd0\7n\2\2\u0dd0\u01e6\3\2\2\2\u0dd1\u0dd2\7q\2" +
					"\2\u0dd2\u0dd3\7t\2\2\u0dd3\u0dd4\7e\2\2\u0dd4\u01e8\3\2\2\2\u0dd5\u0dd6" +
					"\7o\2\2\u0dd6\u0dd7\7g\2\2\u0dd7\u0dd8\7t\2\2\u0dd8\u0dd9\7e\2\2\u0dd9" +
					"\u0dda\7g\2\2\u0dda\u0ddb\7p\2\2\u0ddb\u0ddc\7c\2\2\u0ddc\u0ddd\7t\2\2" +
					"\u0ddd\u0dde\7{\2\2\u0dde\u01ea\3\2\2\2\u0ddf\u0de0\7j\2\2\u0de0\u0de1" +
					"\7w\2\2\u0de1\u0de2\7o\2\2\u0de2\u0de3\7c\2\2\u0de3\u0de4\7p\2\2\u0de4" +
					"\u01ec\3\2\2\2\u0de5\u0de6\7d\2\2\u0de6\u0de7\7q\2\2\u0de7\u0de8\7u\2" +
					"\2\u0de8\u0de9\7u\2\2\u0de9\u01ee\3\2\2\2\u0dea\u0deb\7|\2\2\u0deb\u0dec" +
					"\7|\2\2\u0dec\u0ded\7q\2\2\u0ded\u0dee\7n\2\2\u0dee\u0def\7f\2\2\u0def" +
					"\u0df0\7c\2\2\u0df0\u0df1\7i\2\2\u0df1\u0df2\7w\2\2\u0df2\u01f0\3\2\2" +
					"\2\u0df3\u0df4\7y\2\2\u0df4\u0df5\7q\2\2\u0df5\u0df6\7t\2\2\u0df6\u0df7" +
					"\7n\2\2\u0df7\u0df8\7f\2\2\u0df8\u0df9\7a\2\2\u0df9\u0dfa\7v\2\2\u0dfa" +
					"\u0dfb\7t\2\2\u0dfb\u0dfc\7c\2\2\u0dfc\u0dfd\7r\2\2\u0dfd\u01f2\3\2\2" +
					"\2\u0dfe\u0dff\7o\2\2\u0dff\u0e00\7q\2\2\u0e00\u0e01\7p\2\2\u0e01\u0e02" +
					"\7t\2\2\u0e02\u0e03\7c\2\2\u0e03\u0e04\7e\2\2\u0e04\u0e05\7g\2\2\u0e05" +
					"\u01f4\3\2\2\2\u0e06\u0e07\7f\2\2\u0e07\u0e08\7c\2\2\u0e08\u0e09\7t\2" +
					"\2\u0e09\u0e0a\7m\2\2\u0e0a\u0e0b\7g\2\2\u0e0b\u0e0c\7n\2\2\u0e0c\u0e0d" +
					"\7h\2\2\u0e0d\u01f6\3\2\2\2\u0e0e\u0e0f\7i\2\2\u0e0f\u0e10\7w\2\2\u0e10" +
					"\u0e11\7c\2\2\u0e11\u0e12\7t\2\2\u0e12\u0e13\7f\2\2\u0e13\u01f8\3\2\2" +
					"\2\u0e14\u0e15\7v\2\2\u0e15\u0e16\7g\2\2\u0e16\u0e17\7n\2\2\u0e17\u0e18" +
					"\7g\2\2\u0e18\u0e19\7r\2\2\u0e19\u0e1a\7q\2\2\u0e1a\u0e1b\7t\2\2\u0e1b" +
					"\u0e1c\7v\2\2\u0e1c\u0e1d\7g\2\2\u0e1d\u0e1e\7t\2\2\u0e1e\u01fa\3\2\2" +
					"\2\u0e1f\u0e20\7y\2\2\u0e20\u0e21\7c\2\2\u0e21\u0e22\7t\2\2\u0e22\u0e23" +
					"\7g\2\2\u0e23\u0e24\7j\2\2\u0e24\u0e25\7q\2\2\u0e25\u0e26\7w\2\2\u0e26" +
					"\u0e27\7u\2\2\u0e27\u0e28\7g\2\2\u0e28\u0e29\7a\2\2\u0e29\u0e2a\7m\2\2" +
					"\u0e2a\u0e2b\7g\2\2\u0e2b\u0e2c\7g\2\2\u0e2c\u0e2d\7r\2\2\u0e2d\u0e2e" +
					"\7g\2\2\u0e2e\u0e2f\7t\2\2\u0e2f\u01fc\3\2\2\2\u0e30\u0e31\7y\2\2\u0e31" +
					"\u0e32\7c\2\2\u0e32\u0e33\7t\2\2\u0e33\u0e34\7t\2\2\u0e34\u0e35\7k\2\2" +
					"\u0e35\u0e36\7q\2\2\u0e36\u0e37\7t\2\2\u0e37\u01fe\3\2\2\2\u0e38\u0e39" +
					"\7e\2\2\u0e39\u0e3a\7k\2\2\u0e3a\u0e3b\7v\2\2\u0e3b\u0e3c\7k\2\2\u0e3c" +
					"\u0e3d\7|\2\2\u0e3d\u0e3e\7g\2\2\u0e3e\u0e3f\7p\2\2\u0e3f\u0200\3\2\2" +
					"\2\u0e40\u0e41\7v\2\2\u0e41\u0e42\7t\2\2\u0e42\u0e43\7g\2\2\u0e43\u0e44" +
					"\7c\2\2\u0e44\u0e45\7u\2\2\u0e45\u0e46\7w\2\2\u0e46\u0e47\7t\2\2\u0e47" +
					"\u0e48\7g\2\2\u0e48\u0202\3\2\2\2\u0e49\u0e4a\7h\2\2\u0e4a\u0e4b\7k\2" +
					"\2\u0e4b\u0e4c\7g\2\2\u0e4c\u0e4d\7n\2\2\u0e4d\u0e4e\7f\2\2\u0e4e\u0e4f" +
					"\7d\2\2\u0e4f\u0e50\7q\2\2\u0e50\u0e51\7u\2\2\u0e51\u0e52\7u\2\2\u0e52" +
					"\u0204\3\2\2\2\u0e53\u0e54\7d\2\2\u0e54\u0e55\7n\2\2\u0e55\u0e56\7c\2" +
					"\2\u0e56\u0e57\7e\2\2\u0e57\u0e58\7m\2\2\u0e58\u0e59\7u\2\2\u0e59\u0e5a" +
					"\7o\2\2\u0e5a\u0e5b\7k\2\2\u0e5b\u0e5c\7v\2\2\u0e5c\u0e5d\7j\2\2\u0e5d" +
					"\u0206\3\2\2\2\u0e5e\u0e5f\7i\2\2\u0e5f\u0e60\7w\2\2\u0e60\u0e61\7k\2" +
					"\2\u0e61\u0e62\7n\2\2\u0e62\u0e63\7f\2\2\u0e63\u0e64\7a\2\2\u0e64\u0e65" +
					"\7o\2\2\u0e65\u0e66\7c\2\2\u0e66\u0e67\7u\2\2\u0e67\u0e68\7v\2\2\u0e68" +
					"\u0e69\7g\2\2\u0e69\u0e6a\7t\2\2\u0e6a\u0208\3\2\2\2\u0e6b\u0e6c\7i\2" +
					"\2\u0e6c\u0e6d\7w\2\2\u0e6d\u0e6e\7k\2\2\u0e6e\u0e6f\7n\2\2\u0e6f\u0e70" +
					"\7f\2\2\u0e70\u0e71\7a\2\2\u0e71\u0e72\7e\2\2\u0e72\u0e73\7q\2\2\u0e73" +
					"\u0e74\7c\2\2\u0e74\u0e75\7e\2\2\u0e75\u0e76\7j\2\2\u0e76\u020a\3\2\2" +
					"\2\u0e77\u0e78\7u\2\2\u0e78\u0e79\7y\2\2\u0e79\u0e7a\7q\2\2\u0e7a\u0e7b" +
					"\7t\2\2\u0e7b\u0e7c\7f\2\2\u0e7c\u020c\3\2\2\2\u0e7d\u0e7e\7d\2\2\u0e7e" +
					"\u0e7f\7n\2\2\u0e7f\u0e80\7w\2\2\u0e80\u0e81\7p\2\2\u0e81\u0e82\7v\2\2" +
					"\u0e82\u020e\3\2\2\2\u0e83\u0e84\7d\2\2\u0e84\u0e85\7q\2\2\u0e85\u0e86" +
					"\7y\2\2\u0e86\u0210\3\2\2\2\u0e87\u0e88\7r\2\2\u0e88\u0e89\7q\2\2\u0e89" +
					"\u0e8a\7n\2\2\u0e8a\u0e8b\7g\2\2\u0e8b\u0212\3\2\2\2\u0e8c\u0e8d\7f\2" +
					"\2\u0e8d\u0e8e\7c\2\2\u0e8e\u0e8f\7i\2\2\u0e8f\u0e90\7i\2\2\u0e90\u0e91" +
					"\7g\2\2\u0e91\u0e92\7t\2\2\u0e92\u0214\3\2\2\2\u0e93\u0e94\7f\2\2\u0e94" +
					"\u0e95\7w\2\2\u0e95\u0e96\7c\2\2\u0e96\u0e97\7n\2\2\u0e97\u0216\3\2\2" +
					"\2\u0e98\u0e99\7h\2\2\u0e99\u0e9a\7k\2\2\u0e9a\u0e9b\7u\2\2\u0e9b\u0e9c" +
					"\7v\2\2\u0e9c\u0218\3\2\2\2\u0e9d\u0e9e\7f\2\2\u0e9e\u0e9f\7w\2\2\u0e9f" +
					"\u0ea0\7c\2\2\u0ea0\u0ea1\7n\2\2\u0ea1\u0ea2\7h\2\2\u0ea2\u0ea3\7k\2\2" +
					"\u0ea3\u0ea4\7u\2\2\u0ea4\u0ea5\7v\2\2\u0ea5\u021a\3\2\2\2\u0ea6\u0ea7" +
					"\7h\2\2\u0ea7\u0ea8\7k\2\2\u0ea8\u0ea9\7u\2\2\u0ea9\u0eaa\7j\2\2\u0eaa" +
					"\u0eab\7k\2\2\u0eab\u0eac\7p\2\2\u0eac\u0ead\7i\2\2\u0ead\u0eae\7t\2\2" +
					"\u0eae\u0eaf\7q\2\2\u0eaf\u0eb0\7f\2\2\u0eb0\u021c\3\2\2\2\u0eb1\u0eb2" +
					"\7t\2\2\u0eb2\u0eb3\7c\2\2\u0eb3\u0eb4\7r\2\2\u0eb4\u0eb5\7k\2\2\u0eb5" +
					"\u0eb6\7g\2\2\u0eb6\u0eb7\7t\2\2\u0eb7\u021e\3\2\2\2\u0eb8\u0eb9\7c\2" +
					"\2\u0eb9\u0eba\7p\2\2\u0eba\u0ebb\7e\2\2\u0ebb\u0ebc\7k\2\2\u0ebc\u0ebd" +
					"\7g\2\2\u0ebd\u0ebe\7p\2\2\u0ebe\u0ebf\7v\2\2\u0ebf\u0ec0\7u\2\2\u0ec0" +
					"\u0ec1\7y\2\2\u0ec1\u0ec2\7q\2\2\u0ec2\u0ec3\7t\2\2\u0ec3\u0ec4\7f\2\2" +
					"\u0ec4\u0220\3\2\2\2\u0ec5\u0ec6\7e\2\2\u0ec6\u0ec7\7t\2\2\u0ec7\u0ec8" +
					"\7q\2\2\u0ec8\u0ec9\7u\2\2\u0ec9\u0eca\7u\2\2\u0eca\u0ecb\7d\2\2\u0ecb" +
					"\u0ecc\7q\2\2\u0ecc\u0ecd\7y\2\2\u0ecd\u0222\3\2\2\2\u0ece\u0ecf\7h\2" +
					"\2\u0ecf\u0ed0\7n\2\2\u0ed0\u0ed1\7c\2\2\u0ed1\u0ed2\7i\2\2\u0ed2\u0224" +
					"\3\2\2\2\u0ed3\u0ed4\7q\2\2\u0ed4\u0ed5\7y\2\2\u0ed5\u0ed6\7p\2\2\u0ed6" +
					"\u0ed7\7v\2\2\u0ed7\u0ed8\7j\2\2\u0ed8\u0ed9\7k\2\2\u0ed9\u0eda\7p\2\2" +
					"\u0eda\u0edb\7i\2\2\u0edb\u0226\3\2\2\2\u0edc\u0edd\7f\2\2\u0edd\u0ede" +
					"\7w\2\2\u0ede\u0edf\7c\2\2\u0edf\u0ee0\7n\2\2\u0ee0\u0ee1\7f\2\2\u0ee1" +
					"\u0ee2\7c\2\2\u0ee2\u0ee3\7i\2\2\u0ee3\u0ee4\7i\2\2\u0ee4\u0ee5\7g\2\2" +
					"\u0ee5\u0ee6\7t\2\2\u0ee6\u0228\3\2\2\2\u0ee7\u0ee8\7g\2\2\u0ee8\u0ee9" +
					"\7v\2\2\u0ee9\u0eea\7e\2\2\u0eea\u022a\3\2\2\2\u0eeb\u0eec\7n\2\2\u0eec" +
					"\u0eed\7k\2\2\u0eed\u0eee\7i\2\2\u0eee\u0eef\7j\2\2\u0eef\u0ef0\7v\2\2" +
					"\u0ef0\u022c\3\2\2\2\u0ef1\u0ef2\7j\2\2\u0ef2\u0ef3\7g\2\2\u0ef3\u0ef4" +
					"\7c\2\2\u0ef4\u0ef5\7x\2\2\u0ef5\u0ef6\7{\2\2\u0ef6\u022e\3\2\2\2\u0ef7" +
					"\u0ef8\7o\2\2\u0ef8\u0ef9\7c\2\2\u0ef9\u0efa\7i\2\2\u0efa\u0efb\7k\2\2" +
					"\u0efb\u0efc\7e\2\2\u0efc\u0230\3\2\2\2\u0efd\u0efe\7u\2\2\u0efe\u0eff" +
					"\7k\2\2\u0eff\u0f00\7i\2\2\u0f00\u0f01\7k\2\2\u0f01\u0f02\7n\2\2\u0f02" +
					"\u0232\3\2\2\2\u0f03\u0f04\7t\2\2\u0f04\u0f05\7j\2\2\u0f05\u0f06\7c\2" +
					"\2\u0f06\u0f07\7p\2\2\u0f07\u0f08\7f\2\2\u0f08\u0234\3\2\2\2\u0f09\u0f0a" +
					"\7n\2\2\u0f0a\u0f0b\7t\2\2\u0f0b\u0f0c\7j\2\2\u0f0c\u0f0d\7c\2\2\u0f0d" +
					"\u0f0e\7p\2\2\u0f0e\u0f0f\7f\2\2\u0f0f\u0236\3\2\2\2\u0f10\u0f11\7n\2" +
					"\2\u0f11\u0f12\7j\2\2\u0f12\u0f13\7c\2\2\u0f13\u0f14\7p\2\2\u0f14\u0f15" +
					"\7f\2\2\u0f15\u0238\3\2\2\2\u0f16\u0f17\7e\2\2\u0f17\u0f18\7j\2\2\u0f18" +
					"\u0f19\7g\2\2\u0f19\u0f1a\7u\2\2\u0f1a\u0f1b\7v\2\2\u0f1b\u023a\3\2\2" +
					"\2\u0f1c\u0f1d\7n\2\2\u0f1d\u0f1e\7g\2\2\u0f1e\u0f1f\7i\2\2\u0f1f\u0f20" +
					"\7u\2\2\u0f20\u023c\3\2\2\2\u0f21\u0f22\7h\2\2\u0f22\u0f23\7g\2\2\u0f23" +
					"\u0f24\7g\2\2\u0f24\u0f25\7v\2\2\u0f25\u023e\3\2\2\2\u0f26\u0f27\7j\2" +
					"\2\u0f27\u0f28\7g\2\2\u0f28\u0f29\7c\2\2\u0f29\u0f2a\7f\2\2\u0f2a\u0240" +
					"\3\2\2\2\u0f2b\u0f2c\7i\2\2\u0f2c\u0f2d\7n\2\2\u0f2d\u0f2e\7q\2\2\u0f2e" +
					"\u0f2f\7x\2\2\u0f2f\u0f30\7g\2\2\u0f30\u0f31\7u\2\2\u0f31\u0242\3\2\2" +
					"\2\u0f32\u0f33\7q\2\2\u0f33\u0f34\7p\2\2\u0f34\u0f35\7g\2\2\u0f35\u0f36" +
					"\7r\2\2\u0f36\u0f37\7k\2\2\u0f37\u0f38\7g\2\2\u0f38\u0f39\7e\2\2\u0f39" +
					"\u0f3a\7g\2\2\u0f3a\u0244\3\2\2\2\u0f3b\u0f3c\7t\2\2\u0f3c\u0f3d\7g\2" +
					"\2\u0f3d\u0f3e\7c\2\2\u0f3e\u0f3f\7t\2\2\u0f3f\u0246\3\2\2\2\u0f40\u0f41" +
					"\7n\2\2\u0f41\u0f42\7g\2\2\u0f42\u0f43\7c\2\2\u0f43\u0f44\7t\2\2\u0f44" +
					"\u0248\3\2\2\2\u0f45\u0f46\7t\2\2\u0f46\u0f47\7h\2\2\u0f47\u0f48\7k\2" +
					"\2\u0f48\u0f49\7p\2\2\u0f49\u0f4a\7i\2\2\u0f4a\u0f4b\7g\2\2\u0f4b\u0f4c" +
					"\7t\2\2\u0f4c\u024a\3\2\2\2\u0f4d\u0f4e\7n\2\2\u0f4e\u0f4f\7h\2\2\u0f4f" +
					"\u0f50\7k\2\2\u0f50\u0f51\7p\2\2\u0f51\u0f52\7i\2\2\u0f52\u0f53\7g\2\2" +
					"\u0f53\u0f54\7t\2\2\u0f54\u024c\3\2\2\2\u0f55\u0f56\7p\2\2\u0f56\u0f57" +
					"\7g\2\2\u0f57\u0f58\7e\2\2\u0f58\u0f59\7m\2\2\u0f59\u024e\3\2\2\2\u0f5a" +
					"\u0f5b\7d\2\2\u0f5b\u0f5c\7c\2\2\u0f5c\u0f5d\7e\2\2\u0f5d\u0f5e\7m\2\2" +
					"\u0f5e\u0250\3\2\2\2\u0f5f\u0f60\7w\2\2\u0f60\u0f61\7p\2\2\u0f61\u0f62" +
					"\7f\2\2\u0f62\u0f63\7g\2\2\u0f63\u0f64\7t\2\2\u0f64\u0f65\7y\2\2\u0f65" +
					"\u0f66\7g\2\2\u0f66\u0f67\7c\2\2\u0f67\u0f68\7t\2\2\u0f68\u0252\3\2\2" +
					"\2\u0f69\u0f6a\7j\2\2\u0f6a\u0f6b\7c\2\2\u0f6b\u0f6c\7k\2\2\u0f6c\u0f6d" +
					"\7t\2\2\u0f6d\u0254\3\2\2\2\u0f6e\u0f6f\7j\2\2\u0f6f\u0f70\7c\2\2\u0f70" +
					"\u0f71\7k\2\2\u0f71\u0f72\7t\2\2\u0f72\u0f73\7\64\2\2\u0f73\u0256\3\2" +
					"\2\2\u0f74\u0f75\7j\2\2\u0f75\u0f76\7c\2\2\u0f76\u0f77\7k\2\2\u0f77\u0f78" +
					"\7t\2\2\u0f78\u0f79\7c\2\2\u0f79\u0f7a\7n\2\2\u0f7a\u0f7b\7n\2\2\u0f7b" +
					"\u0258\3\2\2\2\u0f7c\u0f7d\7c\2\2\u0f7d\u0f7e\7n\2\2\u0f7e\u0f7f\7n\2" +
					"\2\u0f7f\u0f80\7f\2\2\u0f80\u0f81\7t\2\2\u0f81\u0f82\7g\2\2\u0f82\u0f83" +
					"\7u\2\2\u0f83\u0f84\7u\2\2\u0f84\u025a\3\2\2\2\u0f85\u0f86\7t\2\2\u0f86" +
					"\u0f87\7d\2\2\u0f87\u0f88\7t\2\2\u0f88\u0f89\7c\2\2\u0f89\u0f8a\7e\2\2" +
					"\u0f8a\u0f8b\7g\2\2\u0f8b\u0f8c\7n\2\2\u0f8c\u0f8d\7g\2\2\u0f8d\u0f8e" +
					"\7v\2\2\u0f8e\u025c\3\2\2\2\u0f8f\u0f90\7n\2\2\u0f90\u0f91\7d\2\2\u0f91" +
					"\u0f92\7t\2\2\u0f92\u0f93\7c\2\2\u0f93\u0f94\7e\2\2\u0f94\u0f95\7g\2\2" +
					"\u0f95\u0f96\7n\2\2\u0f96\u0f97\7g\2\2\u0f97\u0f98\7v\2\2\u0f98\u025e" +
					"\3\2\2\2\u0f99\u0f9a\7y\2\2\u0f9a\u0f9b\7c\2\2\u0f9b\u0f9c\7k\2\2\u0f9c" +
					"\u0f9d\7u\2\2\u0f9d\u0f9e\7v\2\2\u0f9e\u0260\3\2\2\2\u0f9f\u0fa0\7f\2" +
					"\2\u0fa0\u0fa1\7g\2\2\u0fa1\u0fa2\7e\2\2\u0fa2\u0fa3\7q\2\2\u0fa3\u0fa4" +
					"\7\63\2\2\u0fa4\u0262\3\2\2\2\u0fa5\u0fa6\7u\2\2\u0fa6\u0fa7\7v\2\2\u0fa7" +
					"\u0fa8\7g\2\2\u0fa8\u0fa9\7g\2\2\u0fa9\u0faa\7n\2\2\u0faa\u0264\3\2\2" +
					"\2\u0fab\u0fac\7h\2\2\u0fac\u0fad\7k\2\2\u0fad\u0fae\7p\2\2\u0fae\u0faf" +
					"\7g\2\2\u0faf\u0fb0\7a\2\2\u0fb0\u0fb1\7u\2\2\u0fb1\u0fb2\7v\2\2\u0fb2" +
					"\u0fb3\7g\2\2\u0fb3\u0fb4\7g\2\2\u0fb4\u0fb5\7n\2\2\u0fb5\u0266\3\2\2" +
					"\2\u0fb6\u0fb7\7y\2\2\u0fb7\u0fb8\7q\2\2\u0fb8\u0fb9\7q\2\2\u0fb9\u0fba" +
					"\7f\2\2\u0fba\u0268\3\2\2\2\u0fbb\u0fbc\7e\2\2\u0fbc\u0fbd\7n\2\2\u0fbd" +
					"\u0fbe\7q\2\2\u0fbe\u0fbf\7v\2\2\u0fbf\u0fc0\7j\2\2\u0fc0\u026a\3\2\2" +
					"\2\u0fc1\u0fc2\7n\2\2\u0fc2\u0fc3\7g\2\2\u0fc3\u0fc4\7c\2\2\u0fc4\u0fc5" +
					"\7v\2\2\u0fc5\u0fc6\7j\2\2\u0fc6\u0fc7\7g\2\2\u0fc7\u0fc8\7t\2\2\u0fc8" +
					"\u026c\3\2\2\2\u0fc9\u0fca\7d\2\2\u0fca\u0fcb\7q\2\2\u0fcb\u0fcc\7p\2" +
					"\2\u0fcc\u0fcd\7g\2\2\u0fcd\u026e\3\2\2\2\u0fce\u0fcf\7d\2\2\u0fcf\u0fd0" +
					"\7t\2\2\u0fd0\u0fd1\7q\2\2\u0fd1\u0fd2\7p\2\2\u0fd2\u0fd3\7|\2\2\u0fd3" +
					"\u0fd4\7g\2\2\u0fd4\u0270\3\2\2\2\u0fd5\u0fd6\7q\2\2\u0fd6\u0fd7\7t\2" +
					"\2\u0fd7\u0fd8\7k\2\2\u0fd8\u0fd9\7j\2\2\u0fd9\u0fda\7c\2\2\u0fda\u0fdb" +
					"\7t\2\2\u0fdb\u0fdc\7w\2\2\u0fdc\u0fdd\7m\2\2\u0fdd\u0fde\7q\2\2\u0fde" +
					"\u0fdf\7p\2\2\u0fdf\u0272\3\2\2\2\u0fe0\u0fe1\7o\2\2\u0fe1\u0fe2\7k\2" +
					"\2\u0fe2\u0fe3\7v\2\2\u0fe3\u0fe4\7j\2\2\u0fe4\u0fe5\7t\2\2\u0fe5\u0fe6" +
					"\7k\2\2\u0fe6\u0fe7\7n\2\2\u0fe7\u0274\3\2\2\2\u0fe8\u0fe9\7f\2\2\u0fe9" +
					"\u0fea\7c\2\2\u0fea\u0feb\7o\2\2\u0feb\u0fec\7c\2\2\u0fec\u0fed\7u\2\2" +
					"\u0fed\u0fee\7e\2\2\u0fee\u0fef\7w\2\2\u0fef\u0ff0\7u\2\2\u0ff0\u0276" +
					"\3\2\2\2\u0ff1\u0ff2\7c\2\2\u0ff2\u0ff3\7f\2\2\u0ff3\u0ff4\7c\2\2\u0ff4" +
					"\u0ff5\7o\2\2\u0ff5\u0ff6\7c\2\2\u0ff6\u0ff7\7p\2\2\u0ff7\u0ff8\7v\2\2" +
					"\u0ff8\u0ff9\7c\2\2\u0ff9\u0ffa\7k\2\2\u0ffa\u0ffb\7v\2\2\u0ffb\u0ffc" +
					"\7g\2\2\u0ffc\u0278\3\2\2\2\u0ffd\u0ffe\7d\2\2\u0ffe\u0fff\7n\2\2\u0fff" +
					"\u1000\7q\2\2\u1000\u1001\7q\2\2\u1001\u1002\7f\2\2\u1002\u1003\7a\2\2" +
					"\u1003\u1004\7u\2\2\u1004\u1005\7v\2\2\u1005\u1006\7g\2\2\u1006\u1007" +
					"\7g\2\2\u1007\u1008\7n\2\2\u1008\u027a\3\2\2\2\u1009\u100a\7r\2\2\u100a" +
					"\u100b\7c\2\2\u100b\u100c\7r\2\2\u100c\u100d\7g\2\2\u100d\u100e\7t\2\2" +
					"\u100e\u027c\3\2\2\2\u100f\u1010\7i\2\2\u1010\u1011\7q\2\2\u1011\u1012" +
					"\7n\2\2\u1012\u1013\7f\2\2\u1013\u027e\3\2\2\2\u1014\u1015\7n\2\2\u1015" +
					"\u1016\7k\2\2\u1016\u1017\7s\2\2\u1017\u1018\7w\2\2\u1018\u1019\7k\2\2" +
					"\u1019\u101a\7f\2\2\u101a\u0280\3\2\2\2\u101b\u101c\7h\2\2\u101c\u101d" +
					"\7k\2\2\u101d\u101e\7u\2\2\u101e\u101f\7j\2\2\u101f\u0282\3\2\2\2\u1020" +
					"\u1021\7u\2\2\u1021\u1022\7k\2\2\u1022\u1023\7n\2\2\u1023\u1024\7x\2\2" +
					"\u1024\u1025\7g\2\2\u1025\u1026\7t\2\2\u1026\u0284\3\2\2\2\u1027\u1028" +
					"\7e\2\2\u1028\u1029\7j\2\2\u1029\u102a\7t\2\2\u102a\u102b\7{\2\2\u102b" +
					"\u102c\7u\2\2\u102c\u102d\7q\2\2\u102d\u102e\7n\2\2\u102e\u102f\7k\2\2" +
					"\u102f\u1030\7v\2\2\u1030\u1031\7g\2\2\u1031\u0286\3\2\2\2\u1032\u1033" +
					"\7e\2\2\u1033\u1034\7t\2\2\u1034\u1035\7{\2\2\u1035\u1036\7u\2\2\u1036" +
					"\u1037\7v\2\2\u1037\u1038\7c\2\2\u1038\u1039\7n\2\2\u1039\u0288\3\2\2" +
					"\2\u103a\u103b\7j\2\2\u103b\u103c\7q\2\2\u103c\u103d\7t\2\2\u103d\u103e" +
					"\7p\2\2\u103e\u028a\3\2\2\2\u103f\u1040\7u\2\2\u1040\u1041\7e\2\2\u1041" +
					"\u1042\7c\2\2\u1042\u1043\7n\2\2\u1043\u1044\7g\2\2\u1044\u1045\7a\2\2" +
					"\u1045\u1046\7q\2\2\u1046\u1047\7h\2\2\u1047\u1048\7a\2\2\u1048\u1049" +
					"\7f\2\2\u1049\u104a\7t\2\2\u104a\u104b\7c\2\2\u104b\u104c\7i\2\2\u104c" +
					"\u104d\7q\2\2\u104d\u104e\7p\2\2\u104e\u028c\3\2\2\2\u104f\u1050\7e\2" +
					"\2\u1050\u1051\7q\2\2\u1051\u1052\7v\2\2\u1052\u1053\7v\2\2\u1053\u1054" +
					"\7q\2\2\u1054\u1055\7p\2\2\u1055\u028e\3\2\2\2\u1056\u1057\7f\2\2\u1057" +
					"\u1058\7{\2\2\u1058\u1059\7g\2\2\u1059\u105a\7u\2\2\u105a\u105b\7v\2\2" +
					"\u105b\u105c\7w\2\2\u105c\u105d\7h\2\2\u105d\u105e\7h\2\2\u105e\u0290" +
					"\3\2\2\2\u105f\u1060\7e\2\2\u1060\u1061\7q\2\2\u1061\u1062\7d\2\2\u1062" +
					"\u1063\7y\2\2\u1063\u1064\7g\2\2\u1064\u1065\7d\2\2\u1065\u0292\3\2\2" +
					"\2\u1066\u1067\7t\2\2\u1067\u1068\7w\2\2\u1068\u1069\7p\2\2\u1069\u106a" +
					"\7g\2\2\u106a\u106b\7a\2\2\u106b\u106c\7z\2\2\u106c\u106d\7r\2\2\u106d" +
					"\u0294\3\2\2\2\u106e\u106f\7t\2\2\u106f\u1070\7w\2\2\u1070\u1071\7p\2" +
					"\2\u1071\u1072\7g\2\2\u1072\u1073\7a\2\2\u1073\u1074\7u\2\2\u1074\u1075" +
					"\7r\2\2\u1075\u0296\3\2\2\2\u1076\u1077\7t\2\2\u1077\u1078\7w\2\2\u1078" +
					"\u1079\7p\2\2\u1079\u107a\7g\2\2\u107a\u107b\7a\2\2\u107b\u107c\7t\2\2" +
					"\u107c\u107d\7g\2\2\u107d\u107e\7o\2\2\u107e\u107f\7q\2\2\u107f\u1080" +
					"\7x\2\2\u1080\u1081\7g\2\2\u1081\u1082\7a\2\2\u1082\u1083\7r\2\2\u1083" +
					"\u1084\7g\2\2\u1084\u1085\7p\2\2\u1085\u1086\7c\2\2\u1086\u1087\7n\2\2" +
					"\u1087\u1088\7v\2\2\u1088\u1089\7{\2\2\u1089\u0298\3\2\2\2\u108a\u108c" +
					"\t\6\2\2\u108b\u108a\3\2\2\2\u108c\u108d\3\2\2\2\u108d\u108b\3\2\2\2\u108d" +
					"\u108e\3\2\2\2\u108e\u1092\3\2\2\2\u108f\u1091\t\7\2\2\u1090\u108f\3\2" +
					"\2\2\u1091\u1094\3\2\2\2\u1092\u1090\3\2\2\2\u1092\u1093\3\2\2\2\u1093" +
					"\u029a\3\2\2\2\u1094\u1092\3\2\2\2\u1095\u1097\t\b\2\2\u1096\u1095\3\2" +
					"\2\2\u1097\u1098\3\2\2\2\u1098\u1096\3\2\2\2\u1098\u1099\3\2\2\2\u1099" +
					"\u109a\3\2\2\2\u109a\u109b\b\u014e\2\2\u109b\u029c\3\2\2\2\u109c\u109d" +
					"\7\61\2\2\u109d\u109e\7\61\2\2\u109e\u10a2\3\2\2\2\u109f\u10a1\n\t\2\2" +
					"\u10a0\u109f\3\2\2\2\u10a1\u10a4\3\2\2\2\u10a2\u10a0\3\2\2\2\u10a2\u10a3" +
					"\3\2\2\2\u10a3\u10a6\3\2\2\2\u10a4\u10a2\3\2\2\2\u10a5\u10a7\7\17\2\2" +
					"\u10a6\u10a5\3\2\2\2\u10a6\u10a7\3\2\2\2\u10a7\u10a8\3\2\2\2\u10a8\u10a9" +
					"\7\f\2\2\u10a9\u10aa\3\2\2\2\u10aa\u10ab\b\u014f\2\2\u10ab\u029e\3\2\2" +
					"\2\u10ac\u10ad\7\61\2\2\u10ad\u10ae\7,\2\2\u10ae\u10b2\3\2\2\2\u10af\u10b1" +
					"\13\2\2\2\u10b0\u10af\3\2\2\2\u10b1\u10b4\3\2\2\2\u10b2\u10b3\3\2\2\2" +
					"\u10b2\u10b0\3\2\2\2\u10b3\u10b5\3\2\2\2\u10b4\u10b2\3\2\2\2\u10b5\u10b6" +
					"\7,\2\2\u10b6\u10b7\7\61\2\2\u10b7\u10b8\3\2\2\2\u10b8\u10b9\b\u0150\2" +
					"\2\u10b9\u02a0\3\2\2\2\r\2\u0cfd\u0d06\u0d2f\u0d32\u108d\u1092\u1098\u10a2" +
					"\u10a6\u10b2\3\b\2\2";
	public static final String _serializedATN = Utils.join(
			new String[]{
					_serializedATNSegment0,
					_serializedATNSegment1
			},
			""
	);
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}