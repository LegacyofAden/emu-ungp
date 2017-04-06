// Generated from org\l2junity\gameserver\data\txt\gen\ItemDatas.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.l2junity.gameserver.data.txt.model.constants.*;
import org.l2junity.gameserver.data.txt.model.constants.ItemTypes.ArmorType;
import org.l2junity.gameserver.data.txt.model.constants.ItemTypes.EtcItemType;
import org.l2junity.gameserver.data.txt.model.constants.ItemTypes.WeaponType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;
import org.l2junity.gameserver.data.txt.model.item.CapsuledItemData;
import org.l2junity.gameserver.data.txt.model.item.condition.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ItemDatasParser extends Parser {
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
	public static final int
			RULE_file = 0, RULE_set = 1, RULE_slot_chest = 2, RULE_slot_legs = 3,
			RULE_slot_feet = 4, RULE_slot_head = 5, RULE_slot_gloves = 6, RULE_slot_lhand = 7,
			RULE_slot_additional = 8, RULE_set_skill = 9, RULE_set_effect_skill = 10,
			RULE_set_additional_effect_skill = 11, RULE_set_additional2_condition = 12,
			RULE_set_additional2_effect_skill = 13, RULE_str_inc = 14, RULE_con_inc = 15,
			RULE_dex_inc = 16, RULE_int_inc = 17, RULE_men_inc = 18, RULE_wit_inc = 19,
			RULE_item = 20, RULE_can_move = 21, RULE_is_premium = 22, RULE_is_olympiad_can_use = 23,
			RULE_use_condition = 24, RULE_equip_condition = 25, RULE_item_equip_option = 26,
			RULE_condition = 27, RULE_ec_race_condition = 28, RULE_uc_race_condition = 29,
			RULE_uc_transmode_exclude_condition = 30, RULE_ec_category_condition = 31,
			RULE_uc_category_condition = 32, RULE_ec_hero_condition = 33, RULE_ec_castle_condition = 34,
			RULE_ec_sex_condition = 35, RULE_ec_agit_condition = 36, RULE_ec_castle_num_condition = 37,
			RULE_ec_academy_condition = 38, RULE_ec_social_class_condition = 39, RULE_uc_level_condition = 40,
			RULE_uc_required_level_condition = 41, RULE_ec_required_level_condition = 42,
			RULE_uc_restart_point_condition = 43, RULE_ec_nobless_condition = 44,
			RULE_ec_clan_leader_condition = 45, RULE_ec_subjob_condition = 46, RULE_uc_transmode_include_condition = 47,
			RULE_uc_inzone_num_condition = 48, RULE_ec_inzone_num_condition = 49,
			RULE_ec_chao_condtion = 50, RULE_uc_in_residence_siege_field_condition = 51,
			RULE_ec_fortress_condition = 52, RULE_ec_agit_num_condition = 53, RULE_for_npc = 54,
			RULE_unequip_skill = 55, RULE_html = 56, RULE_base_attribute_defend = 57,
			RULE_category = 58, RULE_enchant_enable = 59, RULE_elemental_enable = 60,
			RULE_enchanted = 61, RULE_mp_consume = 62, RULE_magical_damage = 63, RULE_durability = 64,
			RULE_damaged = 65, RULE_magic_weapon = 66, RULE_physical_defense = 67,
			RULE_magical_defense = 68, RULE_mp_bonus = 69, RULE_weapon_type_wrapper = 70,
			RULE_weapon_type = 71, RULE_is_trade = 72, RULE_is_drop = 73, RULE_is_destruct = 74,
			RULE_is_private_store = 75, RULE_keep_type = 76, RULE_physical_damage = 77,
			RULE_random_damage = 78, RULE_critical = 79, RULE_hit_modify = 80, RULE_attack_range = 81,
			RULE_damage_range = 82, RULE_attack_speed = 83, RULE_avoid_modify = 84,
			RULE_dual_fhit_rate = 85, RULE_shield_defense = 86, RULE_shield_defense_rate = 87,
			RULE_reuse_delay = 88, RULE_initial_count = 89, RULE_soulshot_count = 90,
			RULE_spiritshot_count = 91, RULE_reduced_soulshot = 92, RULE_reduced_spiritshot = 93,
			RULE_reduced_mp_consume = 94, RULE_immediate_effect = 95, RULE_ex_immediate_effect = 96,
			RULE_use_skill_distime = 97, RULE_drop_period = 98, RULE_duration = 99,
			RULE_period = 100, RULE_equip_reuse_delay = 101, RULE_capsuled_items = 102,
			RULE_capsuled_item = 103, RULE_price = 104, RULE_default_price = 105,
			RULE_item_skill = 106, RULE_critical_attack_skill = 107, RULE_attack_skill = 108,
			RULE_magic_skill = 109, RULE_item_skill_enchanted_four = 110, RULE_crystal_type_wrapper = 111,
			RULE_crystal_type = 112, RULE_crystal_count = 113, RULE_material_type_wrapper = 114,
			RULE_material_type = 115, RULE_consume_type_wrapper = 116, RULE_consume_type = 117,
			RULE_default_action_wrapper = 118, RULE_default_action = 119, RULE_recipe_id = 120,
			RULE_blessed = 121, RULE_weight = 122, RULE_item_multi_skill_list = 123,
			RULE_delay_share_group = 124, RULE_etcitem_type_wrapper = 125, RULE_etcitem_type = 126,
			RULE_armor_type_wrapper = 127, RULE_armor_type = 128, RULE_slot_bit_type_list = 129,
			RULE_slot_bit_type = 130, RULE_item_type = 131, RULE_item_class = 132,
			RULE_item_id = 133, RULE_identifier_object = 134, RULE_bool_object = 135,
			RULE_byte_object = 136, RULE_int_object = 137, RULE_long_object = 138,
			RULE_double_object = 139, RULE_string_object = 140, RULE_name_object = 141,
			RULE_category_object = 142, RULE_vector3D_object = 143, RULE_empty_list = 144,
			RULE_identifier_list = 145, RULE_int_list = 146, RULE_double_list = 147,
			RULE_base_attribute_attack = 148, RULE_attack_attribute = 149, RULE_attribute = 150,
			RULE_category_list = 151;
	public static final String[] ruleNames = {
			"file", "set", "slot_chest", "slot_legs", "slot_feet", "slot_head", "slot_gloves",
			"slot_lhand", "slot_additional", "set_skill", "set_effect_skill", "set_additional_effect_skill",
			"set_additional2_condition", "set_additional2_effect_skill", "str_inc",
			"con_inc", "dex_inc", "int_inc", "men_inc", "wit_inc", "item", "can_move",
			"is_premium", "is_olympiad_can_use", "use_condition", "equip_condition",
			"item_equip_option", "condition", "ec_race_condition", "uc_race_condition",
			"uc_transmode_exclude_condition", "ec_category_condition", "uc_category_condition",
			"ec_hero_condition", "ec_castle_condition", "ec_sex_condition", "ec_agit_condition",
			"ec_castle_num_condition", "ec_academy_condition", "ec_social_class_condition",
			"uc_level_condition", "uc_required_level_condition", "ec_required_level_condition",
			"uc_restart_point_condition", "ec_nobless_condition", "ec_clan_leader_condition",
			"ec_subjob_condition", "uc_transmode_include_condition", "uc_inzone_num_condition",
			"ec_inzone_num_condition", "ec_chao_condtion", "uc_in_residence_siege_field_condition",
			"ec_fortress_condition", "ec_agit_num_condition", "for_npc", "unequip_skill",
			"html", "base_attribute_defend", "category", "enchant_enable", "elemental_enable",
			"enchanted", "mp_consume", "magical_damage", "durability", "damaged",
			"magic_weapon", "physical_defense", "magical_defense", "mp_bonus", "weapon_type_wrapper",
			"weapon_type", "is_trade", "is_drop", "is_destruct", "is_private_store",
			"keep_type", "physical_damage", "random_damage", "critical", "hit_modify",
			"attack_range", "damage_range", "attack_speed", "avoid_modify", "dual_fhit_rate",
			"shield_defense", "shield_defense_rate", "reuse_delay", "initial_count",
			"soulshot_count", "spiritshot_count", "reduced_soulshot", "reduced_spiritshot",
			"reduced_mp_consume", "immediate_effect", "ex_immediate_effect", "use_skill_distime",
			"drop_period", "duration", "period", "equip_reuse_delay", "capsuled_items",
			"capsuled_item", "price", "default_price", "item_skill", "critical_attack_skill",
			"attack_skill", "magic_skill", "item_skill_enchanted_four", "crystal_type_wrapper",
			"crystal_type", "crystal_count", "material_type_wrapper", "material_type",
			"consume_type_wrapper", "consume_type", "default_action_wrapper", "default_action",
			"recipe_id", "blessed", "weight", "item_multi_skill_list", "delay_share_group",
			"etcitem_type_wrapper", "etcitem_type", "armor_type_wrapper", "armor_type",
			"slot_bit_type_list", "slot_bit_type", "item_type", "item_class", "item_id",
			"identifier_object", "bool_object", "byte_object", "int_object", "long_object",
			"double_object", "string_object", "name_object", "category_object", "vector3D_object",
			"empty_list", "identifier_list", "int_list", "double_list", "base_attribute_attack",
			"attack_attribute", "attribute", "category_list"
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
	public ATN getATN() {
		return _ATN;
	}

	public ItemDatasParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}

		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class, i);
		}

		public List<SetContext> set() {
			return getRuleContexts(SetContext.class);
		}

		public SetContext set(int i) {
			return getRuleContext(SetContext.class, i);
		}

		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_file;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterFile(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						setState(306);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
							case T__23: {
								setState(304);
								item();
							}
							break;
							case T__0: {
								setState(305);
								set();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					setState(308);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == T__0 || _la == T__23);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Slot_chestContext slot_chest() {
			return getRuleContext(Slot_chestContext.class, 0);
		}

		public Slot_additionalContext slot_additional() {
			return getRuleContext(Slot_additionalContext.class, 0);
		}

		public Set_skillContext set_skill() {
			return getRuleContext(Set_skillContext.class, 0);
		}

		public Set_effect_skillContext set_effect_skill() {
			return getRuleContext(Set_effect_skillContext.class, 0);
		}

		public Set_additional_effect_skillContext set_additional_effect_skill() {
			return getRuleContext(Set_additional_effect_skillContext.class, 0);
		}

		public Str_incContext str_inc() {
			return getRuleContext(Str_incContext.class, 0);
		}

		public Con_incContext con_inc() {
			return getRuleContext(Con_incContext.class, 0);
		}

		public Dex_incContext dex_inc() {
			return getRuleContext(Dex_incContext.class, 0);
		}

		public Int_incContext int_inc() {
			return getRuleContext(Int_incContext.class, 0);
		}

		public Men_incContext men_inc() {
			return getRuleContext(Men_incContext.class, 0);
		}

		public Wit_incContext wit_inc() {
			return getRuleContext(Wit_incContext.class, 0);
		}

		public Slot_legsContext slot_legs() {
			return getRuleContext(Slot_legsContext.class, 0);
		}

		public Slot_headContext slot_head() {
			return getRuleContext(Slot_headContext.class, 0);
		}

		public Slot_glovesContext slot_gloves() {
			return getRuleContext(Slot_glovesContext.class, 0);
		}

		public Slot_feetContext slot_feet() {
			return getRuleContext(Slot_feetContext.class, 0);
		}

		public Slot_lhandContext slot_lhand() {
			return getRuleContext(Slot_lhandContext.class, 0);
		}

		public Set_additional2_conditionContext set_additional2_condition() {
			return getRuleContext(Set_additional2_conditionContext.class, 0);
		}

		public Set_additional2_effect_skillContext set_additional2_effect_skill() {
			return getRuleContext(Set_additional2_effect_skillContext.class, 0);
		}

		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_set;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSet(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSet(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(310);
				match(T__0);
				setState(311);
				int_object();
				setState(312);
				slot_chest();
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__6) {
					{
						setState(313);
						slot_legs();
					}
				}

				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__8) {
					{
						setState(316);
						slot_head();
					}
				}

				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__9) {
					{
						setState(319);
						slot_gloves();
					}
				}

				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__7) {
					{
						setState(322);
						slot_feet();
					}
				}

				setState(326);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__10) {
					{
						setState(325);
						slot_lhand();
					}
				}

				setState(328);
				slot_additional();
				setState(329);
				set_skill();
				setState(330);
				set_effect_skill();
				setState(331);
				set_additional_effect_skill();
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == T__15) {
					{
						setState(332);
						set_additional2_condition();
						setState(333);
						set_additional2_effect_skill();
					}
				}

				setState(337);
				str_inc();
				setState(338);
				con_inc();
				setState(339);
				dex_inc();
				setState(340);
				int_inc();
				setState(341);
				men_inc();
				setState(342);
				wit_inc();
				setState(343);
				match(T__1);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_chestContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Slot_chestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_chest;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_chest(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_chest(this);
		}
	}

	public final Slot_chestContext slot_chest() throws RecognitionException {
		Slot_chestContext _localctx = new Slot_chestContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_slot_chest);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(345);
				match(T__2);
				setState(346);
				match(T__3);
				setState(347);
				match(T__4);
				setState(348);
				int_object();
				setState(349);
				match(T__5);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_legsContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Slot_legsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_legs;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_legs(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_legs(this);
		}
	}

	public final Slot_legsContext slot_legs() throws RecognitionException {
		Slot_legsContext _localctx = new Slot_legsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_slot_legs);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(351);
				match(T__6);
				setState(352);
				match(T__3);
				setState(353);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_feetContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Slot_feetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_feet;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_feet(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_feet(this);
		}
	}

	public final Slot_feetContext slot_feet() throws RecognitionException {
		Slot_feetContext _localctx = new Slot_feetContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_slot_feet);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(355);
				match(T__7);
				setState(356);
				match(T__3);
				setState(357);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_headContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Slot_headContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_head;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_head(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_head(this);
		}
	}

	public final Slot_headContext slot_head() throws RecognitionException {
		Slot_headContext _localctx = new Slot_headContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_slot_head);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(359);
				match(T__8);
				setState(360);
				match(T__3);
				setState(361);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_glovesContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Slot_glovesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_gloves;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_gloves(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_gloves(this);
		}
	}

	public final Slot_glovesContext slot_gloves() throws RecognitionException {
		Slot_glovesContext _localctx = new Slot_glovesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_slot_gloves);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(363);
				match(T__9);
				setState(364);
				match(T__3);
				setState(365);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_lhandContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Slot_lhandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_lhand;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_lhand(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_lhand(this);
		}
	}

	public final Slot_lhandContext slot_lhand() throws RecognitionException {
		Slot_lhandContext _localctx = new Slot_lhandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_slot_lhand);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(367);
				match(T__10);
				setState(368);
				match(T__3);
				setState(369);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_additionalContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Slot_additionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_additional;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_additional(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_additional(this);
		}
	}

	public final Slot_additionalContext slot_additional() throws RecognitionException {
		Slot_additionalContext _localctx = new Slot_additionalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_slot_additional);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(371);
				match(T__11);
				setState(372);
				match(T__3);
				setState(373);
				name_object();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_skillContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Set_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_set_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSet_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSet_skill(this);
		}
	}

	public final Set_skillContext set_skill() throws RecognitionException {
		Set_skillContext _localctx = new Set_skillContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_set_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(375);
				match(T__12);
				setState(376);
				match(T__3);
				setState(377);
				name_object();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_effect_skillContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Set_effect_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_set_effect_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSet_effect_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSet_effect_skill(this);
		}
	}

	public final Set_effect_skillContext set_effect_skill() throws RecognitionException {
		Set_effect_skillContext _localctx = new Set_effect_skillContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_set_effect_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(379);
				match(T__13);
				setState(380);
				match(T__3);
				setState(381);
				name_object();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_additional_effect_skillContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Set_additional_effect_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_set_additional_effect_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterSet_additional_effect_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitSet_additional_effect_skill(this);
		}
	}

	public final Set_additional_effect_skillContext set_additional_effect_skill() throws RecognitionException {
		Set_additional_effect_skillContext _localctx = new Set_additional_effect_skillContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_set_additional_effect_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(383);
				match(T__14);
				setState(384);
				match(T__3);
				setState(385);
				name_object();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_additional2_conditionContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Set_additional2_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_set_additional2_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterSet_additional2_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitSet_additional2_condition(this);
		}
	}

	public final Set_additional2_conditionContext set_additional2_condition() throws RecognitionException {
		Set_additional2_conditionContext _localctx = new Set_additional2_conditionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_set_additional2_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(387);
				match(T__15);
				setState(388);
				match(T__3);
				setState(389);
				int_object();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_additional2_effect_skillContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Set_additional2_effect_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_set_additional2_effect_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterSet_additional2_effect_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitSet_additional2_effect_skill(this);
		}
	}

	public final Set_additional2_effect_skillContext set_additional2_effect_skill() throws RecognitionException {
		Set_additional2_effect_skillContext _localctx = new Set_additional2_effect_skillContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_set_additional2_effect_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(391);
				match(T__16);
				setState(392);
				match(T__3);
				setState(393);
				name_object();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Str_incContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Str_incContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_str_inc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterStr_inc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitStr_inc(this);
		}
	}

	public final Str_incContext str_inc() throws RecognitionException {
		Str_incContext _localctx = new Str_incContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_str_inc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(395);
				match(T__17);
				setState(396);
				match(T__3);
				setState(397);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Con_incContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Con_incContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_con_inc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCon_inc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCon_inc(this);
		}
	}

	public final Con_incContext con_inc() throws RecognitionException {
		Con_incContext _localctx = new Con_incContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_con_inc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(399);
				match(T__18);
				setState(400);
				match(T__3);
				setState(401);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dex_incContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Dex_incContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_dex_inc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDex_inc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDex_inc(this);
		}
	}

	public final Dex_incContext dex_inc() throws RecognitionException {
		Dex_incContext _localctx = new Dex_incContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dex_inc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(403);
				match(T__19);
				setState(404);
				match(T__3);
				setState(405);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_incContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Int_incContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_int_inc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterInt_inc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitInt_inc(this);
		}
	}

	public final Int_incContext int_inc() throws RecognitionException {
		Int_incContext _localctx = new Int_incContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_int_inc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(407);
				match(T__20);
				setState(408);
				match(T__3);
				setState(409);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Men_incContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Men_incContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_men_inc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMen_inc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMen_inc(this);
		}
	}

	public final Men_incContext men_inc() throws RecognitionException {
		Men_incContext _localctx = new Men_incContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_men_inc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(411);
				match(T__21);
				setState(412);
				match(T__3);
				setState(413);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Wit_incContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Wit_incContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_wit_inc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterWit_inc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitWit_inc(this);
		}
	}

	public final Wit_incContext wit_inc() throws RecognitionException {
		Wit_incContext _localctx = new Wit_incContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_wit_inc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(415);
				match(T__22);
				setState(416);
				match(T__3);
				setState(417);
				int_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ItemContext extends ParserRuleContext {
		public Item_classContext item_class() {
			return getRuleContext(Item_classContext.class, 0);
		}

		public Item_idContext item_id() {
			return getRuleContext(Item_idContext.class, 0);
		}

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Item_typeContext item_type() {
			return getRuleContext(Item_typeContext.class, 0);
		}

		public Slot_bit_type_listContext slot_bit_type_list() {
			return getRuleContext(Slot_bit_type_listContext.class, 0);
		}

		public Armor_type_wrapperContext armor_type_wrapper() {
			return getRuleContext(Armor_type_wrapperContext.class, 0);
		}

		public Etcitem_type_wrapperContext etcitem_type_wrapper() {
			return getRuleContext(Etcitem_type_wrapperContext.class, 0);
		}

		public Delay_share_groupContext delay_share_group() {
			return getRuleContext(Delay_share_groupContext.class, 0);
		}

		public Item_multi_skill_listContext item_multi_skill_list() {
			return getRuleContext(Item_multi_skill_listContext.class, 0);
		}

		public Recipe_idContext recipe_id() {
			return getRuleContext(Recipe_idContext.class, 0);
		}

		public BlessedContext blessed() {
			return getRuleContext(BlessedContext.class, 0);
		}

		public WeightContext weight() {
			return getRuleContext(WeightContext.class, 0);
		}

		public Default_action_wrapperContext default_action_wrapper() {
			return getRuleContext(Default_action_wrapperContext.class, 0);
		}

		public Consume_type_wrapperContext consume_type_wrapper() {
			return getRuleContext(Consume_type_wrapperContext.class, 0);
		}

		public Initial_countContext initial_count() {
			return getRuleContext(Initial_countContext.class, 0);
		}

		public Soulshot_countContext soulshot_count() {
			return getRuleContext(Soulshot_countContext.class, 0);
		}

		public Spiritshot_countContext spiritshot_count() {
			return getRuleContext(Spiritshot_countContext.class, 0);
		}

		public Reduced_soulshotContext reduced_soulshot() {
			return getRuleContext(Reduced_soulshotContext.class, 0);
		}

		public Reduced_spiritshotContext reduced_spiritshot() {
			return getRuleContext(Reduced_spiritshotContext.class, 0);
		}

		public Reduced_mp_consumeContext reduced_mp_consume() {
			return getRuleContext(Reduced_mp_consumeContext.class, 0);
		}

		public Immediate_effectContext immediate_effect() {
			return getRuleContext(Immediate_effectContext.class, 0);
		}

		public Ex_immediate_effectContext ex_immediate_effect() {
			return getRuleContext(Ex_immediate_effectContext.class, 0);
		}

		public Drop_periodContext drop_period() {
			return getRuleContext(Drop_periodContext.class, 0);
		}

		public DurationContext duration() {
			return getRuleContext(DurationContext.class, 0);
		}

		public Use_skill_distimeContext use_skill_distime() {
			return getRuleContext(Use_skill_distimeContext.class, 0);
		}

		public PeriodContext period() {
			return getRuleContext(PeriodContext.class, 0);
		}

		public Equip_reuse_delayContext equip_reuse_delay() {
			return getRuleContext(Equip_reuse_delayContext.class, 0);
		}

		public PriceContext price() {
			return getRuleContext(PriceContext.class, 0);
		}

		public Default_priceContext default_price() {
			return getRuleContext(Default_priceContext.class, 0);
		}

		public Item_skillContext item_skill() {
			return getRuleContext(Item_skillContext.class, 0);
		}

		public Critical_attack_skillContext critical_attack_skill() {
			return getRuleContext(Critical_attack_skillContext.class, 0);
		}

		public Attack_skillContext attack_skill() {
			return getRuleContext(Attack_skillContext.class, 0);
		}

		public Magic_skillContext magic_skill() {
			return getRuleContext(Magic_skillContext.class, 0);
		}

		public Item_skill_enchanted_fourContext item_skill_enchanted_four() {
			return getRuleContext(Item_skill_enchanted_fourContext.class, 0);
		}

		public Capsuled_itemsContext capsuled_items() {
			return getRuleContext(Capsuled_itemsContext.class, 0);
		}

		public Material_type_wrapperContext material_type_wrapper() {
			return getRuleContext(Material_type_wrapperContext.class, 0);
		}

		public Crystal_type_wrapperContext crystal_type_wrapper() {
			return getRuleContext(Crystal_type_wrapperContext.class, 0);
		}

		public Crystal_countContext crystal_count() {
			return getRuleContext(Crystal_countContext.class, 0);
		}

		public Is_tradeContext is_trade() {
			return getRuleContext(Is_tradeContext.class, 0);
		}

		public Is_dropContext is_drop() {
			return getRuleContext(Is_dropContext.class, 0);
		}

		public Is_destructContext is_destruct() {
			return getRuleContext(Is_destructContext.class, 0);
		}

		public Is_private_storeContext is_private_store() {
			return getRuleContext(Is_private_storeContext.class, 0);
		}

		public Keep_typeContext keep_type() {
			return getRuleContext(Keep_typeContext.class, 0);
		}

		public Physical_damageContext physical_damage() {
			return getRuleContext(Physical_damageContext.class, 0);
		}

		public Random_damageContext random_damage() {
			return getRuleContext(Random_damageContext.class, 0);
		}

		public Weapon_type_wrapperContext weapon_type_wrapper() {
			return getRuleContext(Weapon_type_wrapperContext.class, 0);
		}

		public CriticalContext critical() {
			return getRuleContext(CriticalContext.class, 0);
		}

		public Hit_modifyContext hit_modify() {
			return getRuleContext(Hit_modifyContext.class, 0);
		}

		public Avoid_modifyContext avoid_modify() {
			return getRuleContext(Avoid_modifyContext.class, 0);
		}

		public Dual_fhit_rateContext dual_fhit_rate() {
			return getRuleContext(Dual_fhit_rateContext.class, 0);
		}

		public Shield_defenseContext shield_defense() {
			return getRuleContext(Shield_defenseContext.class, 0);
		}

		public Shield_defense_rateContext shield_defense_rate() {
			return getRuleContext(Shield_defense_rateContext.class, 0);
		}

		public Attack_rangeContext attack_range() {
			return getRuleContext(Attack_rangeContext.class, 0);
		}

		public Damage_rangeContext damage_range() {
			return getRuleContext(Damage_rangeContext.class, 0);
		}

		public Attack_speedContext attack_speed() {
			return getRuleContext(Attack_speedContext.class, 0);
		}

		public Reuse_delayContext reuse_delay() {
			return getRuleContext(Reuse_delayContext.class, 0);
		}

		public Mp_consumeContext mp_consume() {
			return getRuleContext(Mp_consumeContext.class, 0);
		}

		public Magical_damageContext magical_damage() {
			return getRuleContext(Magical_damageContext.class, 0);
		}

		public DurabilityContext durability() {
			return getRuleContext(DurabilityContext.class, 0);
		}

		public DamagedContext damaged() {
			return getRuleContext(DamagedContext.class, 0);
		}

		public Physical_defenseContext physical_defense() {
			return getRuleContext(Physical_defenseContext.class, 0);
		}

		public Magical_defenseContext magical_defense() {
			return getRuleContext(Magical_defenseContext.class, 0);
		}

		public Mp_bonusContext mp_bonus() {
			return getRuleContext(Mp_bonusContext.class, 0);
		}

		public CategoryContext category() {
			return getRuleContext(CategoryContext.class, 0);
		}

		public EnchantedContext enchanted() {
			return getRuleContext(EnchantedContext.class, 0);
		}

		public Base_attribute_attackContext base_attribute_attack() {
			return getRuleContext(Base_attribute_attackContext.class, 0);
		}

		public Base_attribute_defendContext base_attribute_defend() {
			return getRuleContext(Base_attribute_defendContext.class, 0);
		}

		public HtmlContext html() {
			return getRuleContext(HtmlContext.class, 0);
		}

		public Magic_weaponContext magic_weapon() {
			return getRuleContext(Magic_weaponContext.class, 0);
		}

		public Enchant_enableContext enchant_enable() {
			return getRuleContext(Enchant_enableContext.class, 0);
		}

		public Elemental_enableContext elemental_enable() {
			return getRuleContext(Elemental_enableContext.class, 0);
		}

		public Unequip_skillContext unequip_skill() {
			return getRuleContext(Unequip_skillContext.class, 0);
		}

		public For_npcContext for_npc() {
			return getRuleContext(For_npcContext.class, 0);
		}

		public Item_equip_optionContext item_equip_option() {
			return getRuleContext(Item_equip_optionContext.class, 0);
		}

		public Use_conditionContext use_condition() {
			return getRuleContext(Use_conditionContext.class, 0);
		}

		public Equip_conditionContext equip_condition() {
			return getRuleContext(Equip_conditionContext.class, 0);
		}

		public Is_olympiad_can_useContext is_olympiad_can_use() {
			return getRuleContext(Is_olympiad_can_useContext.class, 0);
		}

		public Can_moveContext can_move() {
			return getRuleContext(Can_moveContext.class, 0);
		}

		public Is_premiumContext is_premium() {
			return getRuleContext(Is_premiumContext.class, 0);
		}

		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem(this);
		}
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(419);
				match(T__23);
				setState(420);
				item_class();
				setState(421);
				item_id();
				setState(422);
				name_object();
				setState(423);
				item_type();
				setState(424);
				slot_bit_type_list();
				setState(425);
				armor_type_wrapper();
				setState(426);
				etcitem_type_wrapper();
				setState(427);
				delay_share_group();
				setState(428);
				item_multi_skill_list();
				setState(429);
				recipe_id();
				setState(430);
				blessed();
				setState(431);
				weight();
				setState(432);
				default_action_wrapper();
				setState(433);
				consume_type_wrapper();
				setState(434);
				initial_count();
				setState(435);
				soulshot_count();
				setState(436);
				spiritshot_count();
				setState(437);
				reduced_soulshot();
				setState(438);
				reduced_spiritshot();
				setState(439);
				reduced_mp_consume();
				setState(440);
				immediate_effect();
				setState(441);
				ex_immediate_effect();
				setState(442);
				drop_period();
				setState(443);
				duration();
				setState(444);
				use_skill_distime();
				setState(445);
				period();
				setState(446);
				equip_reuse_delay();
				setState(447);
				price();
				setState(448);
				default_price();
				setState(449);
				item_skill();
				setState(450);
				critical_attack_skill();
				setState(451);
				attack_skill();
				setState(452);
				magic_skill();
				setState(453);
				item_skill_enchanted_four();
				setState(454);
				capsuled_items();
				setState(455);
				material_type_wrapper();
				setState(456);
				crystal_type_wrapper();
				setState(457);
				crystal_count();
				setState(458);
				is_trade();
				setState(459);
				is_drop();
				setState(460);
				is_destruct();
				setState(461);
				is_private_store();
				setState(462);
				keep_type();
				setState(463);
				physical_damage();
				setState(464);
				random_damage();
				setState(465);
				weapon_type_wrapper();
				setState(466);
				critical();
				setState(467);
				hit_modify();
				setState(468);
				avoid_modify();
				setState(469);
				dual_fhit_rate();
				setState(470);
				shield_defense();
				setState(471);
				shield_defense_rate();
				setState(472);
				attack_range();
				setState(473);
				damage_range();
				setState(474);
				attack_speed();
				setState(475);
				reuse_delay();
				setState(476);
				mp_consume();
				setState(477);
				magical_damage();
				setState(478);
				durability();
				setState(479);
				damaged();
				setState(480);
				physical_defense();
				setState(481);
				magical_defense();
				setState(482);
				mp_bonus();
				setState(483);
				category();
				setState(484);
				enchanted();
				setState(485);
				base_attribute_attack();
				setState(486);
				base_attribute_defend();
				setState(487);
				html();
				setState(488);
				magic_weapon();
				setState(489);
				enchant_enable();
				setState(490);
				elemental_enable();
				setState(491);
				unequip_skill();
				setState(492);
				for_npc();
				setState(493);
				item_equip_option();
				setState(494);
				use_condition();
				setState(495);
				equip_condition();
				setState(496);
				is_olympiad_can_use();
				setState(497);
				can_move();
				setState(498);
				is_premium();
				setState(499);
				match(T__24);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Can_moveContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Can_moveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_can_move;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCan_move(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCan_move(this);
		}
	}

	public final Can_moveContext can_move() throws RecognitionException {
		Can_moveContext _localctx = new Can_moveContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_can_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(501);
				match(T__25);
				setState(502);
				match(T__3);
				setState(503);
				((Can_moveContext) _localctx).bo = bool_object();
				_localctx.value = ((Can_moveContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_premiumContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Is_premiumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_is_premium;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIs_premium(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIs_premium(this);
		}
	}

	public final Is_premiumContext is_premium() throws RecognitionException {
		Is_premiumContext _localctx = new Is_premiumContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_is_premium);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(506);
				match(T__26);
				setState(507);
				match(T__3);
				setState(508);
				((Is_premiumContext) _localctx).bo = bool_object();
				_localctx.value = ((Is_premiumContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_olympiad_can_useContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Is_olympiad_can_useContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_is_olympiad_can_use;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIs_olympiad_can_use(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIs_olympiad_can_use(this);
		}
	}

	public final Is_olympiad_can_useContext is_olympiad_can_use() throws RecognitionException {
		Is_olympiad_can_useContext _localctx = new Is_olympiad_can_useContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_is_olympiad_can_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(511);
				match(T__27);
				setState(512);
				match(T__3);
				setState(513);
				((Is_olympiad_can_useContext) _localctx).bo = bool_object();
				_localctx.value = ((Is_olympiad_can_useContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_conditionContext extends ParserRuleContext {
		public List<Condition> value = new ArrayList<>();
		public ConditionContext c;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}

		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class, i);
		}

		public Use_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_use_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterUse_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUse_condition(this);
		}
	}

	public final Use_conditionContext use_condition() throws RecognitionException {
		Use_conditionContext _localctx = new Use_conditionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_use_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(516);
				match(T__28);
				setState(517);
				match(T__3);
				setState(533);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
					case 1: {
						setState(518);
						empty_list();
					}
					break;
					case 2: {
						setState(519);
						match(T__4);
						setState(520);
						((Use_conditionContext) _localctx).c = condition();
						_localctx.value.add(((Use_conditionContext) _localctx).c.value);
						setState(528);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == SEMICOLON) {
							{
								{
									setState(522);
									match(SEMICOLON);
									setState(523);
									((Use_conditionContext) _localctx).c = condition();
									_localctx.value.add(((Use_conditionContext) _localctx).c.value);
								}
							}
							setState(530);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(531);
						match(T__5);
					}
					break;
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Equip_conditionContext extends ParserRuleContext {
		public List<Condition> value = new ArrayList<>();
		public ConditionContext c;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}

		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class, i);
		}

		public Equip_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_equip_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEquip_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEquip_condition(this);
		}
	}

	public final Equip_conditionContext equip_condition() throws RecognitionException {
		Equip_conditionContext _localctx = new Equip_conditionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_equip_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(535);
				match(T__29);
				setState(536);
				match(T__3);
				setState(552);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
					case 1: {
						setState(537);
						empty_list();
					}
					break;
					case 2: {
						setState(538);
						match(T__4);
						setState(539);
						((Equip_conditionContext) _localctx).c = condition();
						_localctx.value.add(((Equip_conditionContext) _localctx).c.value);
						setState(547);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == SEMICOLON) {
							{
								{
									setState(541);
									match(SEMICOLON);
									setState(542);
									((Equip_conditionContext) _localctx).c = condition();
									_localctx.value.add(((Equip_conditionContext) _localctx).c.value);
								}
							}
							setState(549);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(550);
						match(T__5);
					}
					break;
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_equip_optionContext extends ParserRuleContext {
		public List<String> value;
		public Identifier_listContext il;

		public Identifier_listContext identifier_list() {
			return getRuleContext(Identifier_listContext.class, 0);
		}

		public Item_equip_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_equip_option;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem_equip_option(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem_equip_option(this);
		}
	}

	public final Item_equip_optionContext item_equip_option() throws RecognitionException {
		Item_equip_optionContext _localctx = new Item_equip_optionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_item_equip_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(554);
				match(T__30);
				setState(555);
				match(T__3);
				setState(556);
				((Item_equip_optionContext) _localctx).il = identifier_list();
				_localctx.value = ((Item_equip_optionContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public Condition value;
		public Ec_race_conditionContext ecr_c;
		public Uc_transmode_exclude_conditionContext ute_c;
		public Ec_category_conditionContext ecc_c;
		public Uc_category_conditionContext ucc_c;
		public Uc_race_conditionContext ucr_c;
		public Ec_hero_conditionContext ech_c;
		public Ec_castle_conditionContext eccast_c;
		public Ec_sex_conditionContext ecs_c;
		public Ec_agit_conditionContext eca_c;
		public Ec_castle_num_conditionContext eccn_c;
		public Ec_academy_conditionContext ecacad_c;
		public Ec_social_class_conditionContext ecsc_c;
		public Uc_level_conditionContext ucl_c;
		public Uc_required_level_conditionContext ucrl_c;
		public Ec_required_level_conditionContext ecrl_c;
		public Uc_restart_point_conditionContext ucrp_c;
		public Ec_nobless_conditionContext ecn_c;
		public Ec_clan_leader_conditionContext eccl_c;
		public Ec_subjob_conditionContext ecsj_c;
		public Uc_transmode_include_conditionContext ucti_c;
		public Uc_inzone_num_conditionContext ucin_c;
		public Ec_inzone_num_conditionContext ecin_c;
		public Ec_chao_condtionContext ecchao_c;
		public Uc_in_residence_siege_field_conditionContext ucsf_c;
		public Ec_fortress_conditionContext ecf_c;
		public Ec_agit_num_conditionContext ecan_c;
		public Identifier_objectContext io;

		public Ec_race_conditionContext ec_race_condition() {
			return getRuleContext(Ec_race_conditionContext.class, 0);
		}

		public Uc_transmode_exclude_conditionContext uc_transmode_exclude_condition() {
			return getRuleContext(Uc_transmode_exclude_conditionContext.class, 0);
		}

		public Ec_category_conditionContext ec_category_condition() {
			return getRuleContext(Ec_category_conditionContext.class, 0);
		}

		public Uc_category_conditionContext uc_category_condition() {
			return getRuleContext(Uc_category_conditionContext.class, 0);
		}

		public Uc_race_conditionContext uc_race_condition() {
			return getRuleContext(Uc_race_conditionContext.class, 0);
		}

		public Ec_hero_conditionContext ec_hero_condition() {
			return getRuleContext(Ec_hero_conditionContext.class, 0);
		}

		public Ec_castle_conditionContext ec_castle_condition() {
			return getRuleContext(Ec_castle_conditionContext.class, 0);
		}

		public Ec_sex_conditionContext ec_sex_condition() {
			return getRuleContext(Ec_sex_conditionContext.class, 0);
		}

		public Ec_agit_conditionContext ec_agit_condition() {
			return getRuleContext(Ec_agit_conditionContext.class, 0);
		}

		public Ec_castle_num_conditionContext ec_castle_num_condition() {
			return getRuleContext(Ec_castle_num_conditionContext.class, 0);
		}

		public Ec_academy_conditionContext ec_academy_condition() {
			return getRuleContext(Ec_academy_conditionContext.class, 0);
		}

		public Ec_social_class_conditionContext ec_social_class_condition() {
			return getRuleContext(Ec_social_class_conditionContext.class, 0);
		}

		public Uc_level_conditionContext uc_level_condition() {
			return getRuleContext(Uc_level_conditionContext.class, 0);
		}

		public Uc_required_level_conditionContext uc_required_level_condition() {
			return getRuleContext(Uc_required_level_conditionContext.class, 0);
		}

		public Ec_required_level_conditionContext ec_required_level_condition() {
			return getRuleContext(Ec_required_level_conditionContext.class, 0);
		}

		public Uc_restart_point_conditionContext uc_restart_point_condition() {
			return getRuleContext(Uc_restart_point_conditionContext.class, 0);
		}

		public Ec_nobless_conditionContext ec_nobless_condition() {
			return getRuleContext(Ec_nobless_conditionContext.class, 0);
		}

		public Ec_clan_leader_conditionContext ec_clan_leader_condition() {
			return getRuleContext(Ec_clan_leader_conditionContext.class, 0);
		}

		public Ec_subjob_conditionContext ec_subjob_condition() {
			return getRuleContext(Ec_subjob_conditionContext.class, 0);
		}

		public Uc_transmode_include_conditionContext uc_transmode_include_condition() {
			return getRuleContext(Uc_transmode_include_conditionContext.class, 0);
		}

		public Uc_inzone_num_conditionContext uc_inzone_num_condition() {
			return getRuleContext(Uc_inzone_num_conditionContext.class, 0);
		}

		public Ec_inzone_num_conditionContext ec_inzone_num_condition() {
			return getRuleContext(Ec_inzone_num_conditionContext.class, 0);
		}

		public Ec_chao_condtionContext ec_chao_condtion() {
			return getRuleContext(Ec_chao_condtionContext.class, 0);
		}

		public Uc_in_residence_siege_field_conditionContext uc_in_residence_siege_field_condition() {
			return getRuleContext(Uc_in_residence_siege_field_conditionContext.class, 0);
		}

		public Ec_fortress_conditionContext ec_fortress_condition() {
			return getRuleContext(Ec_fortress_conditionContext.class, 0);
		}

		public Ec_agit_num_conditionContext ec_agit_num_condition() {
			return getRuleContext(Ec_agit_num_conditionContext.class, 0);
		}

		public Identifier_objectContext identifier_object() {
			return getRuleContext(Identifier_objectContext.class, 0);
		}

		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCondition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(559);
				match(T__4);
				setState(641);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
					case T__31: {
						setState(560);
						((ConditionContext) _localctx).ecr_c = ec_race_condition();
						_localctx.value = ((ConditionContext) _localctx).ecr_c.value;
					}
					break;
					case T__33: {
						setState(563);
						((ConditionContext) _localctx).ute_c = uc_transmode_exclude_condition();
						_localctx.value = ((ConditionContext) _localctx).ute_c.value;
					}
					break;
					case T__34: {
						setState(566);
						((ConditionContext) _localctx).ecc_c = ec_category_condition();
						_localctx.value = ((ConditionContext) _localctx).ecc_c.value;
					}
					break;
					case T__35: {
						setState(569);
						((ConditionContext) _localctx).ucc_c = uc_category_condition();
						_localctx.value = ((ConditionContext) _localctx).ucc_c.value;
					}
					break;
					case T__32: {
						setState(572);
						((ConditionContext) _localctx).ucr_c = uc_race_condition();
						_localctx.value = ((ConditionContext) _localctx).ucr_c.value;
					}
					break;
					case T__36: {
						setState(575);
						((ConditionContext) _localctx).ech_c = ec_hero_condition();
						_localctx.value = ((ConditionContext) _localctx).ech_c.value;
					}
					break;
					case T__37: {
						setState(578);
						((ConditionContext) _localctx).eccast_c = ec_castle_condition();
						_localctx.value = ((ConditionContext) _localctx).eccast_c.value;
					}
					break;
					case T__38: {
						setState(581);
						((ConditionContext) _localctx).ecs_c = ec_sex_condition();
						_localctx.value = ((ConditionContext) _localctx).ecs_c.value;
					}
					break;
					case T__39: {
						setState(584);
						((ConditionContext) _localctx).eca_c = ec_agit_condition();
						_localctx.value = ((ConditionContext) _localctx).eca_c.value;
					}
					break;
					case T__40: {
						setState(587);
						((ConditionContext) _localctx).eccn_c = ec_castle_num_condition();
						_localctx.value = ((ConditionContext) _localctx).eccn_c.value;
					}
					break;
					case T__41: {
						setState(590);
						((ConditionContext) _localctx).ecacad_c = ec_academy_condition();
						_localctx.value = ((ConditionContext) _localctx).ecacad_c.value;
					}
					break;
					case T__42: {
						setState(593);
						((ConditionContext) _localctx).ecsc_c = ec_social_class_condition();
						_localctx.value = ((ConditionContext) _localctx).ecsc_c.value;
					}
					break;
					case T__43: {
						setState(596);
						((ConditionContext) _localctx).ucl_c = uc_level_condition();
						_localctx.value = ((ConditionContext) _localctx).ucl_c.value;
					}
					break;
					case T__44: {
						setState(599);
						((ConditionContext) _localctx).ucrl_c = uc_required_level_condition();
						_localctx.value = ((ConditionContext) _localctx).ucrl_c.value;
					}
					break;
					case T__45: {
						setState(602);
						((ConditionContext) _localctx).ecrl_c = ec_required_level_condition();
						_localctx.value = ((ConditionContext) _localctx).ecrl_c.value;
					}
					break;
					case T__46: {
						setState(605);
						((ConditionContext) _localctx).ucrp_c = uc_restart_point_condition();
						_localctx.value = ((ConditionContext) _localctx).ucrp_c.value;
					}
					break;
					case T__47: {
						setState(608);
						((ConditionContext) _localctx).ecn_c = ec_nobless_condition();
						_localctx.value = ((ConditionContext) _localctx).ecn_c.value;
					}
					break;
					case T__48: {
						setState(611);
						((ConditionContext) _localctx).eccl_c = ec_clan_leader_condition();
						_localctx.value = ((ConditionContext) _localctx).eccl_c.value;
					}
					break;
					case T__49: {
						setState(614);
						((ConditionContext) _localctx).ecsj_c = ec_subjob_condition();
						_localctx.value = ((ConditionContext) _localctx).ecsj_c.value;
					}
					break;
					case T__50: {
						setState(617);
						((ConditionContext) _localctx).ucti_c = uc_transmode_include_condition();
						_localctx.value = ((ConditionContext) _localctx).ucti_c.value;
					}
					break;
					case T__51: {
						setState(620);
						((ConditionContext) _localctx).ucin_c = uc_inzone_num_condition();
						_localctx.value = ((ConditionContext) _localctx).ucin_c.value;
					}
					break;
					case T__52: {
						setState(623);
						((ConditionContext) _localctx).ecin_c = ec_inzone_num_condition();
						_localctx.value = ((ConditionContext) _localctx).ecin_c.value;
					}
					break;
					case T__53: {
						setState(626);
						((ConditionContext) _localctx).ecchao_c = ec_chao_condtion();
						_localctx.value = ((ConditionContext) _localctx).ecchao_c.value;
					}
					break;
					case T__54: {
						setState(629);
						((ConditionContext) _localctx).ucsf_c = uc_in_residence_siege_field_condition();
						_localctx.value = ((ConditionContext) _localctx).ucsf_c.value;
					}
					break;
					case T__55: {
						setState(632);
						((ConditionContext) _localctx).ecf_c = ec_fortress_condition();
						_localctx.value = ((ConditionContext) _localctx).ecf_c.value;
					}
					break;
					case T__56: {
						setState(635);
						((ConditionContext) _localctx).ecan_c = ec_agit_num_condition();
						_localctx.value = ((ConditionContext) _localctx).ecan_c.value;
					}
					break;
					case T__10:
					case NONE:
					case ORC:
					case BOW:
					case DAGGER:
					case RAPIER:
					case CROSSBOW:
					case GLOVES:
					case STEEL:
					case LEATHER:
					case ORIHARUKON:
					case NAME: {
						setState(638);
						((ConditionContext) _localctx).io = identifier_object();
						System.out.println(((ConditionContext) _localctx).io.value);
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				setState(643);
				match(T__5);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_race_conditionContext extends ParserRuleContext {
		public EcRace value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Ec_race_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_race_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_race_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_race_condition(this);
		}
	}

	public final Ec_race_conditionContext ec_race_condition() throws RecognitionException {
		Ec_race_conditionContext _localctx = new Ec_race_conditionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_ec_race_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(645);
				match(T__31);
				setState(646);
				match(SEMICOLON);
				setState(647);
				((Ec_race_conditionContext) _localctx).il = int_list();
				_localctx.value = new EcRace(((Ec_race_conditionContext) _localctx).il.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_race_conditionContext extends ParserRuleContext {
		public UcRace value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Uc_race_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_race_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterUc_race_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUc_race_condition(this);
		}
	}

	public final Uc_race_conditionContext uc_race_condition() throws RecognitionException {
		Uc_race_conditionContext _localctx = new Uc_race_conditionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_uc_race_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(650);
				match(T__32);
				setState(651);
				match(SEMICOLON);
				setState(652);
				((Uc_race_conditionContext) _localctx).il = int_list();
				_localctx.value = new UcRace(((Uc_race_conditionContext) _localctx).il.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_transmode_exclude_conditionContext extends ParserRuleContext {
		public UcTransmodeExclude value;
		public Identifier_listContext il;

		public Identifier_listContext identifier_list() {
			return getRuleContext(Identifier_listContext.class, 0);
		}

		public Uc_transmode_exclude_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_transmode_exclude_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterUc_transmode_exclude_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitUc_transmode_exclude_condition(this);
		}
	}

	public final Uc_transmode_exclude_conditionContext uc_transmode_exclude_condition() throws RecognitionException {
		Uc_transmode_exclude_conditionContext _localctx = new Uc_transmode_exclude_conditionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_uc_transmode_exclude_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(655);
				match(T__33);
				setState(656);
				match(SEMICOLON);
				setState(657);
				((Uc_transmode_exclude_conditionContext) _localctx).il = identifier_list();
				_localctx.value = new UcTransmodeExclude(((Uc_transmode_exclude_conditionContext) _localctx).il.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_category_conditionContext extends ParserRuleContext {
		public EcCategory value;
		public Category_listContext cl;

		public Category_listContext category_list() {
			return getRuleContext(Category_listContext.class, 0);
		}

		public Ec_category_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_category_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_category_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_category_condition(this);
		}
	}

	public final Ec_category_conditionContext ec_category_condition() throws RecognitionException {
		Ec_category_conditionContext _localctx = new Ec_category_conditionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_ec_category_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(660);
				match(T__34);
				setState(661);
				match(SEMICOLON);
				setState(662);
				((Ec_category_conditionContext) _localctx).cl = category_list();
				_localctx.value = new EcCategory(((Ec_category_conditionContext) _localctx).cl.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_category_conditionContext extends ParserRuleContext {
		public UcCategory value;
		public Category_listContext cl;

		public Category_listContext category_list() {
			return getRuleContext(Category_listContext.class, 0);
		}

		public Uc_category_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_category_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterUc_category_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUc_category_condition(this);
		}
	}

	public final Uc_category_conditionContext uc_category_condition() throws RecognitionException {
		Uc_category_conditionContext _localctx = new Uc_category_conditionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_uc_category_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(665);
				match(T__35);
				setState(666);
				match(SEMICOLON);
				setState(667);
				((Uc_category_conditionContext) _localctx).cl = category_list();
				_localctx.value = new UcCategory(((Uc_category_conditionContext) _localctx).cl.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_hero_conditionContext extends ParserRuleContext {
		public EcHero value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_hero_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_hero_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_hero_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_hero_condition(this);
		}
	}

	public final Ec_hero_conditionContext ec_hero_condition() throws RecognitionException {
		Ec_hero_conditionContext _localctx = new Ec_hero_conditionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_ec_hero_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(670);
				match(T__36);
				setState(671);
				match(SEMICOLON);
				setState(672);
				((Ec_hero_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcHero(((Ec_hero_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_castle_conditionContext extends ParserRuleContext {
		public EcCastle value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_castle_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_castle_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_castle_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_castle_condition(this);
		}
	}

	public final Ec_castle_conditionContext ec_castle_condition() throws RecognitionException {
		Ec_castle_conditionContext _localctx = new Ec_castle_conditionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_ec_castle_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(675);
				match(T__37);
				setState(676);
				match(SEMICOLON);
				setState(677);
				((Ec_castle_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcCastle(((Ec_castle_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_sex_conditionContext extends ParserRuleContext {
		public EcSex value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Ec_sex_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_sex_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_sex_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_sex_condition(this);
		}
	}

	public final Ec_sex_conditionContext ec_sex_condition() throws RecognitionException {
		Ec_sex_conditionContext _localctx = new Ec_sex_conditionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_ec_sex_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(680);
				match(T__38);
				setState(681);
				match(SEMICOLON);
				setState(682);
				((Ec_sex_conditionContext) _localctx).io = int_object();
				_localctx.value = new EcSex(((Ec_sex_conditionContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_agit_conditionContext extends ParserRuleContext {
		public EcAgit value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_agit_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_agit_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_agit_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_agit_condition(this);
		}
	}

	public final Ec_agit_conditionContext ec_agit_condition() throws RecognitionException {
		Ec_agit_conditionContext _localctx = new Ec_agit_conditionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_ec_agit_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(685);
				match(T__39);
				setState(686);
				match(SEMICOLON);
				setState(687);
				((Ec_agit_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcAgit(((Ec_agit_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_castle_num_conditionContext extends ParserRuleContext {
		public EcCastleNum value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Ec_castle_num_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_castle_num_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterEc_castle_num_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_castle_num_condition(this);
		}
	}

	public final Ec_castle_num_conditionContext ec_castle_num_condition() throws RecognitionException {
		Ec_castle_num_conditionContext _localctx = new Ec_castle_num_conditionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ec_castle_num_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(690);
				match(T__40);
				setState(691);
				match(SEMICOLON);
				setState(692);
				match(T__4);
				setState(693);
				((Ec_castle_num_conditionContext) _localctx).io = int_object();
				setState(694);
				match(T__5);
				_localctx.value = new EcCastleNum(((Ec_castle_num_conditionContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_academy_conditionContext extends ParserRuleContext {
		public EcAcademy value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_academy_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_academy_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_academy_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_academy_condition(this);
		}
	}

	public final Ec_academy_conditionContext ec_academy_condition() throws RecognitionException {
		Ec_academy_conditionContext _localctx = new Ec_academy_conditionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_ec_academy_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(697);
				match(T__41);
				setState(698);
				match(SEMICOLON);
				setState(699);
				((Ec_academy_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcAcademy(((Ec_academy_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_social_class_conditionContext extends ParserRuleContext {
		public EcSocialClass value;
		public Byte_objectContext bo;

		public Byte_objectContext byte_object() {
			return getRuleContext(Byte_objectContext.class, 0);
		}

		public Ec_social_class_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_social_class_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterEc_social_class_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitEc_social_class_condition(this);
		}
	}

	public final Ec_social_class_conditionContext ec_social_class_condition() throws RecognitionException {
		Ec_social_class_conditionContext _localctx = new Ec_social_class_conditionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_ec_social_class_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(702);
				match(T__42);
				setState(703);
				match(SEMICOLON);
				setState(704);
				((Ec_social_class_conditionContext) _localctx).bo = byte_object();
				_localctx.value = new EcSocialClass(((Ec_social_class_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_level_conditionContext extends ParserRuleContext {
		public UcLevel value;
		public Int_objectContext min;
		public Int_objectContext max;

		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}

		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class, i);
		}

		public Uc_level_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_level_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterUc_level_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUc_level_condition(this);
		}
	}

	public final Uc_level_conditionContext uc_level_condition() throws RecognitionException {
		Uc_level_conditionContext _localctx = new Uc_level_conditionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_uc_level_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(707);
				match(T__43);
				setState(708);
				match(SEMICOLON);
				setState(709);
				match(T__4);
				setState(710);
				((Uc_level_conditionContext) _localctx).min = int_object();
				setState(711);
				match(SEMICOLON);
				setState(712);
				((Uc_level_conditionContext) _localctx).max = int_object();
				setState(713);
				match(T__5);
				_localctx.value = new UcLevel(((Uc_level_conditionContext) _localctx).min.value, ((Uc_level_conditionContext) _localctx).max.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_required_level_conditionContext extends ParserRuleContext {
		public UcRequiredLevel value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Uc_required_level_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_required_level_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterUc_required_level_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitUc_required_level_condition(this);
		}
	}

	public final Uc_required_level_conditionContext uc_required_level_condition() throws RecognitionException {
		Uc_required_level_conditionContext _localctx = new Uc_required_level_conditionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_uc_required_level_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(716);
				match(T__44);
				setState(717);
				match(SEMICOLON);
				setState(718);
				((Uc_required_level_conditionContext) _localctx).io = int_object();
				_localctx.value = new UcRequiredLevel(((Uc_required_level_conditionContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_required_level_conditionContext extends ParserRuleContext {
		public EcRequiredLevel value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Ec_required_level_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_required_level_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterEc_required_level_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitEc_required_level_condition(this);
		}
	}

	public final Ec_required_level_conditionContext ec_required_level_condition() throws RecognitionException {
		Ec_required_level_conditionContext _localctx = new Ec_required_level_conditionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_ec_required_level_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(721);
				match(T__45);
				setState(722);
				match(SEMICOLON);
				setState(723);
				((Ec_required_level_conditionContext) _localctx).io = int_object();
				_localctx.value = new EcRequiredLevel(((Ec_required_level_conditionContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_restart_point_conditionContext extends ParserRuleContext {
		public UcRestartPoint value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Uc_restart_point_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_restart_point_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterUc_restart_point_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitUc_restart_point_condition(this);
		}
	}

	public final Uc_restart_point_conditionContext uc_restart_point_condition() throws RecognitionException {
		Uc_restart_point_conditionContext _localctx = new Uc_restart_point_conditionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_uc_restart_point_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(726);
				match(T__46);
				setState(727);
				match(SEMICOLON);
				setState(728);
				((Uc_restart_point_conditionContext) _localctx).io = int_object();
				_localctx.value = new UcRestartPoint(((Uc_restart_point_conditionContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_nobless_conditionContext extends ParserRuleContext {
		public EcNobless value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_nobless_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_nobless_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_nobless_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_nobless_condition(this);
		}
	}

	public final Ec_nobless_conditionContext ec_nobless_condition() throws RecognitionException {
		Ec_nobless_conditionContext _localctx = new Ec_nobless_conditionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_ec_nobless_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(731);
				match(T__47);
				setState(732);
				match(SEMICOLON);
				setState(733);
				((Ec_nobless_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcNobless(((Ec_nobless_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_clan_leader_conditionContext extends ParserRuleContext {
		public EcClanLeader value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_clan_leader_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_clan_leader_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterEc_clan_leader_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitEc_clan_leader_condition(this);
		}
	}

	public final Ec_clan_leader_conditionContext ec_clan_leader_condition() throws RecognitionException {
		Ec_clan_leader_conditionContext _localctx = new Ec_clan_leader_conditionContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_ec_clan_leader_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(736);
				match(T__48);
				setState(737);
				match(SEMICOLON);
				setState(738);
				((Ec_clan_leader_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcClanLeader(((Ec_clan_leader_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_subjob_conditionContext extends ParserRuleContext {
		public EcSubJob value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_subjob_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_subjob_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_subjob_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_subjob_condition(this);
		}
	}

	public final Ec_subjob_conditionContext ec_subjob_condition() throws RecognitionException {
		Ec_subjob_conditionContext _localctx = new Ec_subjob_conditionContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_ec_subjob_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(741);
				match(T__49);
				setState(742);
				match(SEMICOLON);
				setState(743);
				((Ec_subjob_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcSubJob(((Ec_subjob_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_transmode_include_conditionContext extends ParserRuleContext {
		public UcTransmodeInclude value;
		public Identifier_listContext il;

		public Identifier_listContext identifier_list() {
			return getRuleContext(Identifier_listContext.class, 0);
		}

		public Uc_transmode_include_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_transmode_include_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterUc_transmode_include_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitUc_transmode_include_condition(this);
		}
	}

	public final Uc_transmode_include_conditionContext uc_transmode_include_condition() throws RecognitionException {
		Uc_transmode_include_conditionContext _localctx = new Uc_transmode_include_conditionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_uc_transmode_include_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(746);
				match(T__50);
				setState(747);
				match(SEMICOLON);
				setState(748);
				((Uc_transmode_include_conditionContext) _localctx).il = identifier_list();
				_localctx.value = new UcTransmodeInclude(((Uc_transmode_include_conditionContext) _localctx).il.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_inzone_num_conditionContext extends ParserRuleContext {
		public UcInzoneNum value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Uc_inzone_num_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_inzone_num_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterUc_inzone_num_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUc_inzone_num_condition(this);
		}
	}

	public final Uc_inzone_num_conditionContext uc_inzone_num_condition() throws RecognitionException {
		Uc_inzone_num_conditionContext _localctx = new Uc_inzone_num_conditionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_uc_inzone_num_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(751);
				match(T__51);
				setState(752);
				match(SEMICOLON);
				setState(753);
				((Uc_inzone_num_conditionContext) _localctx).il = int_list();
				_localctx.value = new UcInzoneNum(((Uc_inzone_num_conditionContext) _localctx).il.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_inzone_num_conditionContext extends ParserRuleContext {
		public EcInzoneNum value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Ec_inzone_num_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_inzone_num_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterEc_inzone_num_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_inzone_num_condition(this);
		}
	}

	public final Ec_inzone_num_conditionContext ec_inzone_num_condition() throws RecognitionException {
		Ec_inzone_num_conditionContext _localctx = new Ec_inzone_num_conditionContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_ec_inzone_num_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(756);
				match(T__52);
				setState(757);
				match(SEMICOLON);
				setState(758);
				((Ec_inzone_num_conditionContext) _localctx).il = int_list();
				_localctx.value = new EcInzoneNum(((Ec_inzone_num_conditionContext) _localctx).il.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_chao_condtionContext extends ParserRuleContext {
		public EcChao value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_chao_condtionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_chao_condtion;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_chao_condtion(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_chao_condtion(this);
		}
	}

	public final Ec_chao_condtionContext ec_chao_condtion() throws RecognitionException {
		Ec_chao_condtionContext _localctx = new Ec_chao_condtionContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_ec_chao_condtion);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(761);
				match(T__53);
				setState(762);
				match(SEMICOLON);
				setState(763);
				((Ec_chao_condtionContext) _localctx).bo = bool_object();
				_localctx.value = new EcChao(((Ec_chao_condtionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Uc_in_residence_siege_field_conditionContext extends ParserRuleContext {
		public UcInResidenceSiegeFlag value;

		public Uc_in_residence_siege_field_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_uc_in_residence_siege_field_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterUc_in_residence_siege_field_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitUc_in_residence_siege_field_condition(this);
		}
	}

	public final Uc_in_residence_siege_field_conditionContext uc_in_residence_siege_field_condition() throws RecognitionException {
		Uc_in_residence_siege_field_conditionContext _localctx = new Uc_in_residence_siege_field_conditionContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_uc_in_residence_siege_field_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(766);
				match(T__54);
				_localctx.value = new UcInResidenceSiegeFlag();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_fortress_conditionContext extends ParserRuleContext {
		public EcFortress value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Ec_fortress_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_fortress_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_fortress_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_fortress_condition(this);
		}
	}

	public final Ec_fortress_conditionContext ec_fortress_condition() throws RecognitionException {
		Ec_fortress_conditionContext _localctx = new Ec_fortress_conditionContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_ec_fortress_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(769);
				match(T__55);
				setState(770);
				match(SEMICOLON);
				setState(771);
				((Ec_fortress_conditionContext) _localctx).bo = bool_object();
				_localctx.value = new EcFortress(((Ec_fortress_conditionContext) _localctx).bo.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ec_agit_num_conditionContext extends ParserRuleContext {
		public EcAgitNum value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Ec_agit_num_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ec_agit_num_condition;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEc_agit_num_condition(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEc_agit_num_condition(this);
		}
	}

	public final Ec_agit_num_conditionContext ec_agit_num_condition() throws RecognitionException {
		Ec_agit_num_conditionContext _localctx = new Ec_agit_num_conditionContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_ec_agit_num_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(774);
				match(T__56);
				setState(775);
				match(SEMICOLON);
				setState(776);
				match(T__4);
				setState(777);
				((Ec_agit_num_conditionContext) _localctx).io = int_object();
				setState(778);
				match(T__5);
				_localctx.value = new EcAgitNum(((Ec_agit_num_conditionContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_npcContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public For_npcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_for_npc;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterFor_npc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitFor_npc(this);
		}
	}

	public final For_npcContext for_npc() throws RecognitionException {
		For_npcContext _localctx = new For_npcContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_for_npc);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(781);
				match(T__57);
				setState(782);
				match(T__3);
				setState(783);
				((For_npcContext) _localctx).bo = bool_object();
				_localctx.value = ((For_npcContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unequip_skillContext extends ParserRuleContext {
		public List<String> value;
		public Identifier_listContext il;

		public Identifier_listContext identifier_list() {
			return getRuleContext(Identifier_listContext.class, 0);
		}

		public Unequip_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_unequip_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterUnequip_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUnequip_skill(this);
		}
	}

	public final Unequip_skillContext unequip_skill() throws RecognitionException {
		Unequip_skillContext _localctx = new Unequip_skillContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_unequip_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(786);
				match(T__58);
				setState(787);
				match(T__3);
				setState(788);
				((Unequip_skillContext) _localctx).il = identifier_list();
				_localctx.value = ((Unequip_skillContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HtmlContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public HtmlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_html;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterHtml(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitHtml(this);
		}
	}

	public final HtmlContext html() throws RecognitionException {
		HtmlContext _localctx = new HtmlContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_html);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(791);
				match(T__59);
				setState(792);
				match(T__3);
				setState(793);
				((HtmlContext) _localctx).no = name_object();
				_localctx.value = ((HtmlContext) _localctx).no.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attribute_defendContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Base_attribute_defendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_base_attribute_defend;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterBase_attribute_defend(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitBase_attribute_defend(this);
		}
	}

	public final Base_attribute_defendContext base_attribute_defend() throws RecognitionException {
		Base_attribute_defendContext _localctx = new Base_attribute_defendContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_base_attribute_defend);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(796);
				match(T__60);
				setState(797);
				match(T__3);
				setState(798);
				((Base_attribute_defendContext) _localctx).il = int_list();
				_localctx.value = ((Base_attribute_defendContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CategoryContext extends ParserRuleContext {
		public List<String> value = new ArrayList<>();
		;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_category;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCategory(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(801);
				match(T__61);
				setState(802);
				match(T__3);
				setState(803);
				empty_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enchant_enableContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Enchant_enableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_enchant_enable;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEnchant_enable(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEnchant_enable(this);
		}
	}

	public final Enchant_enableContext enchant_enable() throws RecognitionException {
		Enchant_enableContext _localctx = new Enchant_enableContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_enchant_enable);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(805);
				match(T__62);
				setState(806);
				match(T__3);
				setState(807);
				((Enchant_enableContext) _localctx).io = int_object();
				_localctx.value = ((Enchant_enableContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Elemental_enableContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Elemental_enableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_elemental_enable;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterElemental_enable(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitElemental_enable(this);
		}
	}

	public final Elemental_enableContext elemental_enable() throws RecognitionException {
		Elemental_enableContext _localctx = new Elemental_enableContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_elemental_enable);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(810);
				match(T__63);
				setState(811);
				match(T__3);
				setState(812);
				((Elemental_enableContext) _localctx).bo = bool_object();
				_localctx.value = ((Elemental_enableContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnchantedContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public EnchantedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_enchanted;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEnchanted(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEnchanted(this);
		}
	}

	public final EnchantedContext enchanted() throws RecognitionException {
		EnchantedContext _localctx = new EnchantedContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_enchanted);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(815);
				match(T__64);
				setState(816);
				match(T__3);
				setState(817);
				((EnchantedContext) _localctx).io = int_object();
				_localctx.value = ((EnchantedContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mp_consumeContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Mp_consumeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_mp_consume;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMp_consume(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMp_consume(this);
		}
	}

	public final Mp_consumeContext mp_consume() throws RecognitionException {
		Mp_consumeContext _localctx = new Mp_consumeContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_mp_consume);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(820);
				match(T__65);
				setState(821);
				match(T__3);
				setState(822);
				((Mp_consumeContext) _localctx).io = int_object();
				_localctx.value = ((Mp_consumeContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Magical_damageContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Magical_damageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_magical_damage;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMagical_damage(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMagical_damage(this);
		}
	}

	public final Magical_damageContext magical_damage() throws RecognitionException {
		Magical_damageContext _localctx = new Magical_damageContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_magical_damage);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(825);
				match(T__66);
				setState(826);
				match(T__3);
				setState(827);
				((Magical_damageContext) _localctx).io = int_object();
				_localctx.value = ((Magical_damageContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DurabilityContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public DurabilityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_durability;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDurability(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDurability(this);
		}
	}

	public final DurabilityContext durability() throws RecognitionException {
		DurabilityContext _localctx = new DurabilityContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_durability);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(830);
				match(T__67);
				setState(831);
				match(T__3);
				setState(832);
				((DurabilityContext) _localctx).io = int_object();
				_localctx.value = ((DurabilityContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DamagedContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public DamagedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_damaged;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDamaged(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDamaged(this);
		}
	}

	public final DamagedContext damaged() throws RecognitionException {
		DamagedContext _localctx = new DamagedContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_damaged);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(835);
				match(T__68);
				setState(836);
				match(T__3);
				setState(837);
				((DamagedContext) _localctx).bo = bool_object();
				_localctx.value = ((DamagedContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Magic_weaponContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Magic_weaponContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_magic_weapon;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMagic_weapon(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMagic_weapon(this);
		}
	}

	public final Magic_weaponContext magic_weapon() throws RecognitionException {
		Magic_weaponContext _localctx = new Magic_weaponContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_magic_weapon);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(840);
				match(T__69);
				setState(841);
				match(T__3);
				setState(842);
				((Magic_weaponContext) _localctx).bo = bool_object();
				_localctx.value = ((Magic_weaponContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Physical_defenseContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Physical_defenseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_physical_defense;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterPhysical_defense(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitPhysical_defense(this);
		}
	}

	public final Physical_defenseContext physical_defense() throws RecognitionException {
		Physical_defenseContext _localctx = new Physical_defenseContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_physical_defense);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(845);
				match(T__70);
				setState(846);
				match(T__3);
				setState(847);
				((Physical_defenseContext) _localctx).io = int_object();
				_localctx.value = ((Physical_defenseContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Magical_defenseContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Magical_defenseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_magical_defense;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMagical_defense(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMagical_defense(this);
		}
	}

	public final Magical_defenseContext magical_defense() throws RecognitionException {
		Magical_defenseContext _localctx = new Magical_defenseContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_magical_defense);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(850);
				match(T__71);
				setState(851);
				match(T__3);
				setState(852);
				((Magical_defenseContext) _localctx).io = int_object();
				_localctx.value = ((Magical_defenseContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mp_bonusContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Mp_bonusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_mp_bonus;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMp_bonus(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMp_bonus(this);
		}
	}

	public final Mp_bonusContext mp_bonus() throws RecognitionException {
		Mp_bonusContext _localctx = new Mp_bonusContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_mp_bonus);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(855);
				match(T__72);
				setState(856);
				match(T__3);
				setState(857);
				((Mp_bonusContext) _localctx).io = int_object();
				_localctx.value = ((Mp_bonusContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Weapon_type_wrapperContext extends ParserRuleContext {
		public WeaponType value;
		public Weapon_typeContext wt;

		public Weapon_typeContext weapon_type() {
			return getRuleContext(Weapon_typeContext.class, 0);
		}

		public Weapon_type_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_weapon_type_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterWeapon_type_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitWeapon_type_wrapper(this);
		}
	}

	public final Weapon_type_wrapperContext weapon_type_wrapper() throws RecognitionException {
		Weapon_type_wrapperContext _localctx = new Weapon_type_wrapperContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_weapon_type_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(860);
				match(T__73);
				setState(861);
				match(T__3);
				setState(862);
				((Weapon_type_wrapperContext) _localctx).wt = weapon_type();
				_localctx.value = ((Weapon_type_wrapperContext) _localctx).wt.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Weapon_typeContext extends ParserRuleContext {
		public WeaponType value;

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode SWORD() {
			return getToken(ItemDatasParser.SWORD, 0);
		}

		public TerminalNode BLUNT() {
			return getToken(ItemDatasParser.BLUNT, 0);
		}

		public TerminalNode BOW() {
			return getToken(ItemDatasParser.BOW, 0);
		}

		public TerminalNode POLE() {
			return getToken(ItemDatasParser.POLE, 0);
		}

		public TerminalNode DAGGER() {
			return getToken(ItemDatasParser.DAGGER, 0);
		}

		public TerminalNode DUAL() {
			return getToken(ItemDatasParser.DUAL, 0);
		}

		public TerminalNode FIST() {
			return getToken(ItemDatasParser.FIST, 0);
		}

		public TerminalNode DUALFIST() {
			return getToken(ItemDatasParser.DUALFIST, 0);
		}

		public TerminalNode FISHINGROD() {
			return getToken(ItemDatasParser.FISHINGROD, 0);
		}

		public TerminalNode RAPIER() {
			return getToken(ItemDatasParser.RAPIER, 0);
		}

		public TerminalNode ETC() {
			return getToken(ItemDatasParser.ETC, 0);
		}

		public TerminalNode ANCIENTSWORD() {
			return getToken(ItemDatasParser.ANCIENTSWORD, 0);
		}

		public TerminalNode CROSSBOW() {
			return getToken(ItemDatasParser.CROSSBOW, 0);
		}

		public TerminalNode FLAG() {
			return getToken(ItemDatasParser.FLAG, 0);
		}

		public TerminalNode OWNTHING() {
			return getToken(ItemDatasParser.OWNTHING, 0);
		}

		public TerminalNode DUALDAGGER() {
			return getToken(ItemDatasParser.DUALDAGGER, 0);
		}

		public Weapon_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_weapon_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterWeapon_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitWeapon_type(this);
		}
	}

	public final Weapon_typeContext weapon_type() throws RecognitionException {
		Weapon_typeContext _localctx = new Weapon_typeContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_weapon_type);
		try {
			setState(899);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(865);
					match(NONE);
					_localctx.value = WeaponType.NONE;
				}
				break;
				case SWORD:
					enterOuterAlt(_localctx, 2);
				{
					setState(867);
					match(SWORD);
					_localctx.value = WeaponType.SWORD;
				}
				break;
				case BLUNT:
					enterOuterAlt(_localctx, 3);
				{
					setState(869);
					match(BLUNT);
					_localctx.value = WeaponType.BLUNT;
				}
				break;
				case BOW:
					enterOuterAlt(_localctx, 4);
				{
					setState(871);
					match(BOW);
					_localctx.value = WeaponType.BOW;
				}
				break;
				case POLE:
					enterOuterAlt(_localctx, 5);
				{
					setState(873);
					match(POLE);
					_localctx.value = WeaponType.POLE;
				}
				break;
				case DAGGER:
					enterOuterAlt(_localctx, 6);
				{
					setState(875);
					match(DAGGER);
					_localctx.value = WeaponType.DAGGER;
				}
				break;
				case DUAL:
					enterOuterAlt(_localctx, 7);
				{
					setState(877);
					match(DUAL);
					_localctx.value = WeaponType.DUAL;
				}
				break;
				case FIST:
					enterOuterAlt(_localctx, 8);
				{
					setState(879);
					match(FIST);
					_localctx.value = WeaponType.FIST;
				}
				break;
				case DUALFIST:
					enterOuterAlt(_localctx, 9);
				{
					setState(881);
					match(DUALFIST);
					_localctx.value = WeaponType.DUALFIST;
				}
				break;
				case FISHINGROD:
					enterOuterAlt(_localctx, 10);
				{
					setState(883);
					match(FISHINGROD);
					_localctx.value = WeaponType.FISHINGROD;
				}
				break;
				case RAPIER:
					enterOuterAlt(_localctx, 11);
				{
					setState(885);
					match(RAPIER);
					_localctx.value = WeaponType.RAPIER;
				}
				break;
				case ETC:
					enterOuterAlt(_localctx, 12);
				{
					setState(887);
					match(ETC);
					_localctx.value = WeaponType.ETC;
				}
				break;
				case ANCIENTSWORD:
					enterOuterAlt(_localctx, 13);
				{
					setState(889);
					match(ANCIENTSWORD);
					_localctx.value = WeaponType.ANCIENTSWORD;
				}
				break;
				case CROSSBOW:
					enterOuterAlt(_localctx, 14);
				{
					setState(891);
					match(CROSSBOW);
					_localctx.value = WeaponType.CROSSBOW;
				}
				break;
				case FLAG:
					enterOuterAlt(_localctx, 15);
				{
					setState(893);
					match(FLAG);
					_localctx.value = WeaponType.FLAG;
				}
				break;
				case OWNTHING:
					enterOuterAlt(_localctx, 16);
				{
					setState(895);
					match(OWNTHING);
					_localctx.value = WeaponType.OWNTHING;
				}
				break;
				case DUALDAGGER:
					enterOuterAlt(_localctx, 17);
				{
					setState(897);
					match(DUALDAGGER);
					_localctx.value = WeaponType.DUALDAGGER;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_tradeContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Is_tradeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_is_trade;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIs_trade(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIs_trade(this);
		}
	}

	public final Is_tradeContext is_trade() throws RecognitionException {
		Is_tradeContext _localctx = new Is_tradeContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_is_trade);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(901);
				match(T__74);
				setState(902);
				match(T__3);
				setState(903);
				((Is_tradeContext) _localctx).bo = bool_object();
				_localctx.value = ((Is_tradeContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_dropContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Is_dropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_is_drop;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIs_drop(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIs_drop(this);
		}
	}

	public final Is_dropContext is_drop() throws RecognitionException {
		Is_dropContext _localctx = new Is_dropContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_is_drop);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(906);
				match(T__75);
				setState(907);
				match(T__3);
				setState(908);
				((Is_dropContext) _localctx).bo = bool_object();
				_localctx.value = ((Is_dropContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_destructContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Is_destructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_is_destruct;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIs_destruct(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIs_destruct(this);
		}
	}

	public final Is_destructContext is_destruct() throws RecognitionException {
		Is_destructContext _localctx = new Is_destructContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_is_destruct);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(911);
				match(T__76);
				setState(912);
				match(T__3);
				setState(913);
				((Is_destructContext) _localctx).bo = bool_object();
				_localctx.value = ((Is_destructContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_private_storeContext extends ParserRuleContext {
		public boolean value;
		public Bool_objectContext bo;

		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class, 0);
		}

		public Is_private_storeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_is_private_store;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIs_private_store(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIs_private_store(this);
		}
	}

	public final Is_private_storeContext is_private_store() throws RecognitionException {
		Is_private_storeContext _localctx = new Is_private_storeContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_is_private_store);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(916);
				match(T__77);
				setState(917);
				match(T__3);
				setState(918);
				((Is_private_storeContext) _localctx).bo = bool_object();
				_localctx.value = ((Is_private_storeContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Keep_typeContext extends ParserRuleContext {
		public byte value;
		public Byte_objectContext bo;

		public Byte_objectContext byte_object() {
			return getRuleContext(Byte_objectContext.class, 0);
		}

		public Keep_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_keep_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterKeep_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitKeep_type(this);
		}
	}

	public final Keep_typeContext keep_type() throws RecognitionException {
		Keep_typeContext _localctx = new Keep_typeContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_keep_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(921);
				match(T__78);
				setState(922);
				match(T__3);
				setState(923);
				((Keep_typeContext) _localctx).bo = byte_object();
				_localctx.value = ((Keep_typeContext) _localctx).bo.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Physical_damageContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Physical_damageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_physical_damage;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterPhysical_damage(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitPhysical_damage(this);
		}
	}

	public final Physical_damageContext physical_damage() throws RecognitionException {
		Physical_damageContext _localctx = new Physical_damageContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_physical_damage);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(926);
				match(T__79);
				setState(927);
				match(T__3);
				setState(928);
				((Physical_damageContext) _localctx).io = int_object();
				_localctx.value = ((Physical_damageContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Random_damageContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Random_damageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_random_damage;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterRandom_damage(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitRandom_damage(this);
		}
	}

	public final Random_damageContext random_damage() throws RecognitionException {
		Random_damageContext _localctx = new Random_damageContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_random_damage);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(931);
				match(T__80);
				setState(932);
				match(T__3);
				setState(933);
				((Random_damageContext) _localctx).io = int_object();
				_localctx.value = ((Random_damageContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CriticalContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public CriticalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_critical;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCritical(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCritical(this);
		}
	}

	public final CriticalContext critical() throws RecognitionException {
		CriticalContext _localctx = new CriticalContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_critical);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(936);
				match(T__81);
				setState(937);
				match(T__3);
				setState(938);
				((CriticalContext) _localctx).io = int_object();
				_localctx.value = ((CriticalContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Hit_modifyContext extends ParserRuleContext {
		public double value;
		public Double_objectContext d;

		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class, 0);
		}

		public Hit_modifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_hit_modify;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterHit_modify(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitHit_modify(this);
		}
	}

	public final Hit_modifyContext hit_modify() throws RecognitionException {
		Hit_modifyContext _localctx = new Hit_modifyContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_hit_modify);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(941);
				match(T__82);
				setState(942);
				match(T__3);
				setState(943);
				((Hit_modifyContext) _localctx).d = double_object();
				_localctx.value = ((Hit_modifyContext) _localctx).d.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attack_rangeContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Attack_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attack_range;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterAttack_range(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitAttack_range(this);
		}
	}

	public final Attack_rangeContext attack_range() throws RecognitionException {
		Attack_rangeContext _localctx = new Attack_rangeContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_attack_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(946);
				match(T__83);
				setState(947);
				match(T__3);
				setState(948);
				((Attack_rangeContext) _localctx).io = int_object();
				_localctx.value = ((Attack_rangeContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Damage_rangeContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Damage_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_damage_range;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDamage_range(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDamage_range(this);
		}
	}

	public final Damage_rangeContext damage_range() throws RecognitionException {
		Damage_rangeContext _localctx = new Damage_rangeContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_damage_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(951);
				match(T__84);
				setState(952);
				match(T__3);
				setState(953);
				((Damage_rangeContext) _localctx).il = int_list();
				_localctx.value = ((Damage_rangeContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attack_speedContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Attack_speedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attack_speed;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterAttack_speed(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitAttack_speed(this);
		}
	}

	public final Attack_speedContext attack_speed() throws RecognitionException {
		Attack_speedContext _localctx = new Attack_speedContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_attack_speed);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(956);
				match(T__85);
				setState(957);
				match(T__3);
				setState(958);
				((Attack_speedContext) _localctx).io = int_object();
				_localctx.value = ((Attack_speedContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Avoid_modifyContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Avoid_modifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_avoid_modify;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterAvoid_modify(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitAvoid_modify(this);
		}
	}

	public final Avoid_modifyContext avoid_modify() throws RecognitionException {
		Avoid_modifyContext _localctx = new Avoid_modifyContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_avoid_modify);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(961);
				match(T__86);
				setState(962);
				match(T__3);
				setState(963);
				((Avoid_modifyContext) _localctx).io = int_object();
				_localctx.value = ((Avoid_modifyContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Dual_fhit_rateContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Dual_fhit_rateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_dual_fhit_rate;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDual_fhit_rate(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDual_fhit_rate(this);
		}
	}

	public final Dual_fhit_rateContext dual_fhit_rate() throws RecognitionException {
		Dual_fhit_rateContext _localctx = new Dual_fhit_rateContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_dual_fhit_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(966);
				match(T__87);
				setState(967);
				match(T__3);
				setState(968);
				((Dual_fhit_rateContext) _localctx).io = int_object();
				_localctx.value = ((Dual_fhit_rateContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shield_defenseContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Shield_defenseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_shield_defense;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterShield_defense(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitShield_defense(this);
		}
	}

	public final Shield_defenseContext shield_defense() throws RecognitionException {
		Shield_defenseContext _localctx = new Shield_defenseContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_shield_defense);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(971);
				match(T__88);
				setState(972);
				match(T__3);
				setState(973);
				((Shield_defenseContext) _localctx).io = int_object();
				_localctx.value = ((Shield_defenseContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shield_defense_rateContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Shield_defense_rateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_shield_defense_rate;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterShield_defense_rate(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitShield_defense_rate(this);
		}
	}

	public final Shield_defense_rateContext shield_defense_rate() throws RecognitionException {
		Shield_defense_rateContext _localctx = new Shield_defense_rateContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_shield_defense_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(976);
				match(T__89);
				setState(977);
				match(T__3);
				setState(978);
				((Shield_defense_rateContext) _localctx).io = int_object();
				_localctx.value = ((Shield_defense_rateContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reuse_delayContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Reuse_delayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_reuse_delay;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterReuse_delay(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitReuse_delay(this);
		}
	}

	public final Reuse_delayContext reuse_delay() throws RecognitionException {
		Reuse_delayContext _localctx = new Reuse_delayContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_reuse_delay);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(981);
				match(T__90);
				setState(982);
				match(T__3);
				setState(983);
				((Reuse_delayContext) _localctx).io = int_object();
				_localctx.value = ((Reuse_delayContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Initial_countContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Initial_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_initial_count;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterInitial_count(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitInitial_count(this);
		}
	}

	public final Initial_countContext initial_count() throws RecognitionException {
		Initial_countContext _localctx = new Initial_countContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_initial_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(986);
				match(T__91);
				setState(987);
				match(T__3);
				setState(988);
				((Initial_countContext) _localctx).io = int_object();
				_localctx.value = ((Initial_countContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Soulshot_countContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Soulshot_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_soulshot_count;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSoulshot_count(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSoulshot_count(this);
		}
	}

	public final Soulshot_countContext soulshot_count() throws RecognitionException {
		Soulshot_countContext _localctx = new Soulshot_countContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_soulshot_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(991);
				match(T__92);
				setState(992);
				match(T__3);
				setState(993);
				((Soulshot_countContext) _localctx).io = int_object();
				_localctx.value = ((Soulshot_countContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Spiritshot_countContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Spiritshot_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_spiritshot_count;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSpiritshot_count(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSpiritshot_count(this);
		}
	}

	public final Spiritshot_countContext spiritshot_count() throws RecognitionException {
		Spiritshot_countContext _localctx = new Spiritshot_countContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_spiritshot_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(996);
				match(T__93);
				setState(997);
				match(T__3);
				setState(998);
				((Spiritshot_countContext) _localctx).io = int_object();
				_localctx.value = ((Spiritshot_countContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reduced_soulshotContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Reduced_soulshotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_reduced_soulshot;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterReduced_soulshot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitReduced_soulshot(this);
		}
	}

	public final Reduced_soulshotContext reduced_soulshot() throws RecognitionException {
		Reduced_soulshotContext _localctx = new Reduced_soulshotContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_reduced_soulshot);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1001);
				match(T__94);
				setState(1002);
				match(T__3);
				setState(1003);
				((Reduced_soulshotContext) _localctx).il = int_list();
				_localctx.value = ((Reduced_soulshotContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reduced_spiritshotContext extends ParserRuleContext {
		public List<Integer> value = new ArrayList<>();
		;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public Reduced_spiritshotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_reduced_spiritshot;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterReduced_spiritshot(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitReduced_spiritshot(this);
		}
	}

	public final Reduced_spiritshotContext reduced_spiritshot() throws RecognitionException {
		Reduced_spiritshotContext _localctx = new Reduced_spiritshotContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_reduced_spiritshot);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1006);
				match(T__95);
				setState(1007);
				match(T__3);
				setState(1008);
				empty_list();
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reduced_mp_consumeContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_listContext il;

		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class, 0);
		}

		public Reduced_mp_consumeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_reduced_mp_consume;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterReduced_mp_consume(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitReduced_mp_consume(this);
		}
	}

	public final Reduced_mp_consumeContext reduced_mp_consume() throws RecognitionException {
		Reduced_mp_consumeContext _localctx = new Reduced_mp_consumeContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_reduced_mp_consume);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1010);
				match(T__96);
				setState(1011);
				match(T__3);
				setState(1012);
				((Reduced_mp_consumeContext) _localctx).il = int_list();
				_localctx.value = ((Reduced_mp_consumeContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Immediate_effectContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Immediate_effectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_immediate_effect;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterImmediate_effect(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitImmediate_effect(this);
		}
	}

	public final Immediate_effectContext immediate_effect() throws RecognitionException {
		Immediate_effectContext _localctx = new Immediate_effectContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_immediate_effect);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1015);
				match(T__97);
				setState(1016);
				match(T__3);
				setState(1017);
				((Immediate_effectContext) _localctx).io = int_object();
				_localctx.value = ((Immediate_effectContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ex_immediate_effectContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Ex_immediate_effectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_ex_immediate_effect;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEx_immediate_effect(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEx_immediate_effect(this);
		}
	}

	public final Ex_immediate_effectContext ex_immediate_effect() throws RecognitionException {
		Ex_immediate_effectContext _localctx = new Ex_immediate_effectContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_ex_immediate_effect);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1020);
				match(T__98);
				setState(1021);
				match(T__3);
				setState(1022);
				((Ex_immediate_effectContext) _localctx).io = int_object();
				_localctx.value = ((Ex_immediate_effectContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_skill_distimeContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Use_skill_distimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_use_skill_distime;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterUse_skill_distime(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitUse_skill_distime(this);
		}
	}

	public final Use_skill_distimeContext use_skill_distime() throws RecognitionException {
		Use_skill_distimeContext _localctx = new Use_skill_distimeContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_use_skill_distime);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1025);
				match(T__99);
				setState(1026);
				match(T__3);
				setState(1027);
				((Use_skill_distimeContext) _localctx).io = int_object();
				_localctx.value = ((Use_skill_distimeContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_periodContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Drop_periodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_drop_period;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDrop_period(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDrop_period(this);
		}
	}

	public final Drop_periodContext drop_period() throws RecognitionException {
		Drop_periodContext _localctx = new Drop_periodContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_drop_period);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1030);
				match(T__100);
				setState(1031);
				match(T__3);
				setState(1032);
				((Drop_periodContext) _localctx).io = int_object();
				_localctx.value = ((Drop_periodContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DurationContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public DurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_duration;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDuration(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDuration(this);
		}
	}

	public final DurationContext duration() throws RecognitionException {
		DurationContext _localctx = new DurationContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_duration);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1035);
				match(T__101);
				setState(1036);
				match(T__3);
				setState(1037);
				((DurationContext) _localctx).io = int_object();
				_localctx.value = ((DurationContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PeriodContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public PeriodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_period;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterPeriod(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitPeriod(this);
		}
	}

	public final PeriodContext period() throws RecognitionException {
		PeriodContext _localctx = new PeriodContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_period);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1040);
				match(T__102);
				setState(1041);
				match(T__3);
				setState(1042);
				((PeriodContext) _localctx).io = int_object();
				_localctx.value = ((PeriodContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Equip_reuse_delayContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Equip_reuse_delayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_equip_reuse_delay;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEquip_reuse_delay(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEquip_reuse_delay(this);
		}
	}

	public final Equip_reuse_delayContext equip_reuse_delay() throws RecognitionException {
		Equip_reuse_delayContext _localctx = new Equip_reuse_delayContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_equip_reuse_delay);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1045);
				match(T__103);
				setState(1046);
				match(T__3);
				setState(1047);
				((Equip_reuse_delayContext) _localctx).io = int_object();
				_localctx.value = ((Equip_reuse_delayContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Capsuled_itemsContext extends ParserRuleContext {
		public List<CapsuledItemData> value = new ArrayList<>();
		public Capsuled_itemContext ci;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<Capsuled_itemContext> capsuled_item() {
			return getRuleContexts(Capsuled_itemContext.class);
		}

		public Capsuled_itemContext capsuled_item(int i) {
			return getRuleContext(Capsuled_itemContext.class, i);
		}

		public Capsuled_itemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_capsuled_items;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCapsuled_items(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCapsuled_items(this);
		}
	}

	public final Capsuled_itemsContext capsuled_items() throws RecognitionException {
		Capsuled_itemsContext _localctx = new Capsuled_itemsContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_capsuled_items);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1050);
				match(T__104);
				setState(1051);
				match(T__3);
				setState(1067);
				_errHandler.sync(this);
				switch (getInterpreter().adaptivePredict(_input, 15, _ctx)) {
					case 1: {
						setState(1052);
						empty_list();
					}
					break;
					case 2: {
						setState(1053);
						match(T__4);
						setState(1054);
						((Capsuled_itemsContext) _localctx).ci = capsuled_item();
						_localctx.value.add(((Capsuled_itemsContext) _localctx).ci.value);
						setState(1062);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == SEMICOLON) {
							{
								{
									setState(1056);
									match(SEMICOLON);
									setState(1057);
									((Capsuled_itemsContext) _localctx).ci = capsuled_item();
									_localctx.value.add(((Capsuled_itemsContext) _localctx).ci.value);
								}
							}
							setState(1064);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(1065);
						match(T__5);
					}
					break;
				}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Capsuled_itemContext extends ParserRuleContext {
		public CapsuledItemData value;
		public Name_objectContext p1;
		public Int_objectContext p2;
		public Int_objectContext p3;
		public Double_objectContext p4;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}

		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class, i);
		}

		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class, 0);
		}

		public Capsuled_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_capsuled_item;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCapsuled_item(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCapsuled_item(this);
		}
	}

	public final Capsuled_itemContext capsuled_item() throws RecognitionException {
		Capsuled_itemContext _localctx = new Capsuled_itemContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_capsuled_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1069);
				match(T__4);
				setState(1070);
				((Capsuled_itemContext) _localctx).p1 = name_object();
				setState(1071);
				match(SEMICOLON);
				setState(1072);
				((Capsuled_itemContext) _localctx).p2 = int_object();
				setState(1073);
				match(SEMICOLON);
				setState(1074);
				((Capsuled_itemContext) _localctx).p3 = int_object();
				setState(1075);
				match(SEMICOLON);
				setState(1076);
				((Capsuled_itemContext) _localctx).p4 = double_object();
				setState(1077);
				match(T__5);
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = new CapsuledItemData(((Capsuled_itemContext) _localctx).p1.value, ((Capsuled_itemContext) _localctx).p2.value, ((Capsuled_itemContext) _localctx).p3.value, ((Capsuled_itemContext) _localctx).p4.value);
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PriceContext extends ParserRuleContext {
		public long value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public PriceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_price;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterPrice(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitPrice(this);
		}
	}

	public final PriceContext price() throws RecognitionException {
		PriceContext _localctx = new PriceContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1079);
				match(T__105);
				setState(1080);
				match(T__3);
				setState(1081);
				((PriceContext) _localctx).io = int_object();
				_localctx.value = ((PriceContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Default_priceContext extends ParserRuleContext {
		public long value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Default_priceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_default_price;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDefault_price(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDefault_price(this);
		}
	}

	public final Default_priceContext default_price() throws RecognitionException {
		Default_priceContext _localctx = new Default_priceContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_default_price);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1084);
				match(T__106);
				setState(1085);
				match(T__3);
				setState(1086);
				((Default_priceContext) _localctx).io = int_object();
				_localctx.value = ((Default_priceContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_skillContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Item_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem_skill(this);
		}
	}

	public final Item_skillContext item_skill() throws RecognitionException {
		Item_skillContext _localctx = new Item_skillContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_item_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1089);
				match(T__107);
				setState(1090);
				match(T__3);
				setState(1091);
				((Item_skillContext) _localctx).no = name_object();
				_localctx.value = ((Item_skillContext) _localctx).no.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Critical_attack_skillContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Critical_attack_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_critical_attack_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCritical_attack_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCritical_attack_skill(this);
		}
	}

	public final Critical_attack_skillContext critical_attack_skill() throws RecognitionException {
		Critical_attack_skillContext _localctx = new Critical_attack_skillContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_critical_attack_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1094);
				match(T__108);
				setState(1095);
				match(T__3);
				setState(1096);
				((Critical_attack_skillContext) _localctx).no = name_object();
				_localctx.value = ((Critical_attack_skillContext) _localctx).no.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attack_skillContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Attack_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attack_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterAttack_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitAttack_skill(this);
		}
	}

	public final Attack_skillContext attack_skill() throws RecognitionException {
		Attack_skillContext _localctx = new Attack_skillContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_attack_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1099);
				match(T__109);
				setState(1100);
				match(T__3);
				setState(1101);
				((Attack_skillContext) _localctx).no = name_object();
				_localctx.value = ((Attack_skillContext) _localctx).no.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Magic_skillContext extends ParserRuleContext {
		public String value;
		public int unk;
		public Name_objectContext no;
		public Int_objectContext io;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Magic_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_magic_skill;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMagic_skill(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMagic_skill(this);
		}
	}

	public final Magic_skillContext magic_skill() throws RecognitionException {
		Magic_skillContext _localctx = new Magic_skillContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_magic_skill);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1104);
				match(T__110);
				setState(1105);
				match(T__3);
				setState(1106);
				((Magic_skillContext) _localctx).no = name_object();
				_localctx.value = ((Magic_skillContext) _localctx).no.value;
				setState(1112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(1108);
						match(SEMICOLON);
						setState(1109);
						((Magic_skillContext) _localctx).io = int_object();
						_localctx.unk = ((Magic_skillContext) _localctx).io.value;
					}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_skill_enchanted_fourContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;

		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public Item_skill_enchanted_fourContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_skill_enchanted_four;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).enterItem_skill_enchanted_four(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener)
				((ItemDatasListener) listener).exitItem_skill_enchanted_four(this);
		}
	}

	public final Item_skill_enchanted_fourContext item_skill_enchanted_four() throws RecognitionException {
		Item_skill_enchanted_fourContext _localctx = new Item_skill_enchanted_fourContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_item_skill_enchanted_four);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1114);
				match(T__111);
				setState(1115);
				match(T__3);
				setState(1116);
				((Item_skill_enchanted_fourContext) _localctx).no = name_object();
				_localctx.value = ((Item_skill_enchanted_fourContext) _localctx).no.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Crystal_type_wrapperContext extends ParserRuleContext {
		public CrystalType value;
		public Crystal_typeContext ct;

		public Crystal_typeContext crystal_type() {
			return getRuleContext(Crystal_typeContext.class, 0);
		}

		public Crystal_type_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_crystal_type_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCrystal_type_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCrystal_type_wrapper(this);
		}
	}

	public final Crystal_type_wrapperContext crystal_type_wrapper() throws RecognitionException {
		Crystal_type_wrapperContext _localctx = new Crystal_type_wrapperContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_crystal_type_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1119);
				match(T__112);
				setState(1120);
				match(T__3);
				setState(1121);
				((Crystal_type_wrapperContext) _localctx).ct = crystal_type();
				_localctx.value = ((Crystal_type_wrapperContext) _localctx).ct.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Crystal_typeContext extends ParserRuleContext {
		public CrystalType value;

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode CRYSTAL_FREE() {
			return getToken(ItemDatasParser.CRYSTAL_FREE, 0);
		}

		public TerminalNode EVENT() {
			return getToken(ItemDatasParser.EVENT, 0);
		}

		public TerminalNode D() {
			return getToken(ItemDatasParser.D, 0);
		}

		public TerminalNode C() {
			return getToken(ItemDatasParser.C, 0);
		}

		public TerminalNode B() {
			return getToken(ItemDatasParser.B, 0);
		}

		public TerminalNode A() {
			return getToken(ItemDatasParser.A, 0);
		}

		public TerminalNode S() {
			return getToken(ItemDatasParser.S, 0);
		}

		public TerminalNode S80() {
			return getToken(ItemDatasParser.S80, 0);
		}

		public TerminalNode S84() {
			return getToken(ItemDatasParser.S84, 0);
		}

		public Crystal_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_crystal_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCrystal_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCrystal_type(this);
		}
	}

	public final Crystal_typeContext crystal_type() throws RecognitionException {
		Crystal_typeContext _localctx = new Crystal_typeContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_crystal_type);
		try {
			setState(1144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(1124);
					match(NONE);
					_localctx.value = CrystalType.NONE;
				}
				break;
				case CRYSTAL_FREE:
					enterOuterAlt(_localctx, 2);
				{
					setState(1126);
					match(CRYSTAL_FREE);
					_localctx.value = CrystalType.CRYSTAL_FREE;
				}
				break;
				case EVENT:
					enterOuterAlt(_localctx, 3);
				{
					setState(1128);
					match(EVENT);
					_localctx.value = CrystalType.EVENT;
				}
				break;
				case D:
					enterOuterAlt(_localctx, 4);
				{
					setState(1130);
					match(D);
					_localctx.value = CrystalType.D;
				}
				break;
				case C:
					enterOuterAlt(_localctx, 5);
				{
					setState(1132);
					match(C);
					_localctx.value = CrystalType.C;
				}
				break;
				case B:
					enterOuterAlt(_localctx, 6);
				{
					setState(1134);
					match(B);
					_localctx.value = CrystalType.B;
				}
				break;
				case A:
					enterOuterAlt(_localctx, 7);
				{
					setState(1136);
					match(A);
					_localctx.value = CrystalType.A;
				}
				break;
				case S:
					enterOuterAlt(_localctx, 8);
				{
					setState(1138);
					match(S);
					_localctx.value = CrystalType.S;
				}
				break;
				case S80:
					enterOuterAlt(_localctx, 9);
				{
					setState(1140);
					match(S80);
					_localctx.value = CrystalType.S80;
				}
				break;
				case S84:
					enterOuterAlt(_localctx, 10);
				{
					setState(1142);
					match(S84);
					_localctx.value = CrystalType.S84;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Crystal_countContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Crystal_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_crystal_count;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCrystal_count(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCrystal_count(this);
		}
	}

	public final Crystal_countContext crystal_count() throws RecognitionException {
		Crystal_countContext _localctx = new Crystal_countContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_crystal_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1146);
				match(T__113);
				setState(1147);
				match(T__3);
				setState(1148);
				((Crystal_countContext) _localctx).io = int_object();
				_localctx.value = ((Crystal_countContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Material_type_wrapperContext extends ParserRuleContext {
		public MaterialType value;
		public Material_typeContext mt;

		public Material_typeContext material_type() {
			return getRuleContext(Material_typeContext.class, 0);
		}

		public Material_type_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_material_type_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMaterial_type_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMaterial_type_wrapper(this);
		}
	}

	public final Material_type_wrapperContext material_type_wrapper() throws RecognitionException {
		Material_type_wrapperContext _localctx = new Material_type_wrapperContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_material_type_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1151);
				match(T__114);
				setState(1152);
				match(T__3);
				setState(1153);
				((Material_type_wrapperContext) _localctx).mt = material_type();
				_localctx.value = ((Material_type_wrapperContext) _localctx).mt.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Material_typeContext extends ParserRuleContext {
		public MaterialType value;

		public TerminalNode STEEL() {
			return getToken(ItemDatasParser.STEEL, 0);
		}

		public TerminalNode FINE_STEEL() {
			return getToken(ItemDatasParser.FINE_STEEL, 0);
		}

		public TerminalNode WOOD() {
			return getToken(ItemDatasParser.WOOD, 0);
		}

		public TerminalNode CLOTH() {
			return getToken(ItemDatasParser.CLOTH, 0);
		}

		public TerminalNode LEATHER() {
			return getToken(ItemDatasParser.LEATHER, 0);
		}

		public TerminalNode BONE() {
			return getToken(ItemDatasParser.BONE, 0);
		}

		public TerminalNode BRONZE() {
			return getToken(ItemDatasParser.BRONZE, 0);
		}

		public TerminalNode ORIHARUKON() {
			return getToken(ItemDatasParser.ORIHARUKON, 0);
		}

		public TerminalNode MITHRIL() {
			return getToken(ItemDatasParser.MITHRIL, 0);
		}

		public TerminalNode DAMASCUS() {
			return getToken(ItemDatasParser.DAMASCUS, 0);
		}

		public TerminalNode ADAMANTAITE() {
			return getToken(ItemDatasParser.ADAMANTAITE, 0);
		}

		public TerminalNode BLOOD_STEEL() {
			return getToken(ItemDatasParser.BLOOD_STEEL, 0);
		}

		public TerminalNode PAPER() {
			return getToken(ItemDatasParser.PAPER, 0);
		}

		public TerminalNode GOLD() {
			return getToken(ItemDatasParser.GOLD, 0);
		}

		public TerminalNode LIQUID() {
			return getToken(ItemDatasParser.LIQUID, 0);
		}

		public TerminalNode FISH() {
			return getToken(ItemDatasParser.FISH, 0);
		}

		public TerminalNode SILVER() {
			return getToken(ItemDatasParser.SILVER, 0);
		}

		public TerminalNode CHRYSOLITE() {
			return getToken(ItemDatasParser.CHRYSOLITE, 0);
		}

		public TerminalNode CRYSTAL() {
			return getToken(ItemDatasParser.CRYSTAL, 0);
		}

		public TerminalNode HORN() {
			return getToken(ItemDatasParser.HORN, 0);
		}

		public TerminalNode SCALE_OF_DRAGON() {
			return getToken(ItemDatasParser.SCALE_OF_DRAGON, 0);
		}

		public TerminalNode COTTON() {
			return getToken(ItemDatasParser.COTTON, 0);
		}

		public TerminalNode DYESTUFF() {
			return getToken(ItemDatasParser.DYESTUFF, 0);
		}

		public TerminalNode COBWEB() {
			return getToken(ItemDatasParser.COBWEB, 0);
		}

		public TerminalNode RUNE_XP() {
			return getToken(ItemDatasParser.RUNE_XP, 0);
		}

		public TerminalNode RUNE_SP() {
			return getToken(ItemDatasParser.RUNE_SP, 0);
		}

		public TerminalNode RUNE_REMOVE_PENALTY() {
			return getToken(ItemDatasParser.RUNE_REMOVE_PENALTY, 0);
		}

		public Material_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_material_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterMaterial_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitMaterial_type(this);
		}
	}

	public final Material_typeContext material_type() throws RecognitionException {
		Material_typeContext _localctx = new Material_typeContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_material_type);
		try {
			setState(1210);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case STEEL:
					enterOuterAlt(_localctx, 1);
				{
					setState(1156);
					match(STEEL);
					_localctx.value = MaterialType.STEEL;
				}
				break;
				case FINE_STEEL:
					enterOuterAlt(_localctx, 2);
				{
					setState(1158);
					match(FINE_STEEL);
					_localctx.value = MaterialType.FINE_STEEL;
				}
				break;
				case WOOD:
					enterOuterAlt(_localctx, 3);
				{
					setState(1160);
					match(WOOD);
					_localctx.value = MaterialType.WOOD;
				}
				break;
				case CLOTH:
					enterOuterAlt(_localctx, 4);
				{
					setState(1162);
					match(CLOTH);
					_localctx.value = MaterialType.CLOTH;
				}
				break;
				case LEATHER:
					enterOuterAlt(_localctx, 5);
				{
					setState(1164);
					match(LEATHER);
					_localctx.value = MaterialType.LEATHER;
				}
				break;
				case BONE:
					enterOuterAlt(_localctx, 6);
				{
					setState(1166);
					match(BONE);
					_localctx.value = MaterialType.BONE;
				}
				break;
				case BRONZE:
					enterOuterAlt(_localctx, 7);
				{
					setState(1168);
					match(BRONZE);
					_localctx.value = MaterialType.BRONZE;
				}
				break;
				case ORIHARUKON:
					enterOuterAlt(_localctx, 8);
				{
					setState(1170);
					match(ORIHARUKON);
					_localctx.value = MaterialType.ORIHARUKON;
				}
				break;
				case MITHRIL:
					enterOuterAlt(_localctx, 9);
				{
					setState(1172);
					match(MITHRIL);
					_localctx.value = MaterialType.MITHRIL;
				}
				break;
				case DAMASCUS:
					enterOuterAlt(_localctx, 10);
				{
					setState(1174);
					match(DAMASCUS);
					_localctx.value = MaterialType.DAMASCUS;
				}
				break;
				case ADAMANTAITE:
					enterOuterAlt(_localctx, 11);
				{
					setState(1176);
					match(ADAMANTAITE);
					_localctx.value = MaterialType.ADAMANTAITE;
				}
				break;
				case BLOOD_STEEL:
					enterOuterAlt(_localctx, 12);
				{
					setState(1178);
					match(BLOOD_STEEL);
					_localctx.value = MaterialType.BLOOD_STEEL;
				}
				break;
				case PAPER:
					enterOuterAlt(_localctx, 13);
				{
					setState(1180);
					match(PAPER);
					_localctx.value = MaterialType.PAPER;
				}
				break;
				case GOLD:
					enterOuterAlt(_localctx, 14);
				{
					setState(1182);
					match(GOLD);
					_localctx.value = MaterialType.GOLD;
				}
				break;
				case LIQUID:
					enterOuterAlt(_localctx, 15);
				{
					setState(1184);
					match(LIQUID);
					_localctx.value = MaterialType.LIQUID;
				}
				break;
				case FISH:
					enterOuterAlt(_localctx, 16);
				{
					setState(1186);
					match(FISH);
					_localctx.value = MaterialType.FISH;
				}
				break;
				case SILVER:
					enterOuterAlt(_localctx, 17);
				{
					setState(1188);
					match(SILVER);
					_localctx.value = MaterialType.SILVER;
				}
				break;
				case CHRYSOLITE:
					enterOuterAlt(_localctx, 18);
				{
					setState(1190);
					match(CHRYSOLITE);
					_localctx.value = MaterialType.CHRYSOLITE;
				}
				break;
				case CRYSTAL:
					enterOuterAlt(_localctx, 19);
				{
					setState(1192);
					match(CRYSTAL);
					_localctx.value = MaterialType.CRYSTAL;
				}
				break;
				case HORN:
					enterOuterAlt(_localctx, 20);
				{
					setState(1194);
					match(HORN);
					_localctx.value = MaterialType.HORN;
				}
				break;
				case SCALE_OF_DRAGON:
					enterOuterAlt(_localctx, 21);
				{
					setState(1196);
					match(SCALE_OF_DRAGON);
					_localctx.value = MaterialType.SCALE_OF_DRAGON;
				}
				break;
				case COTTON:
					enterOuterAlt(_localctx, 22);
				{
					setState(1198);
					match(COTTON);
					_localctx.value = MaterialType.COTTON;
				}
				break;
				case DYESTUFF:
					enterOuterAlt(_localctx, 23);
				{
					setState(1200);
					match(DYESTUFF);
					_localctx.value = MaterialType.DYESTUFF;
				}
				break;
				case COBWEB:
					enterOuterAlt(_localctx, 24);
				{
					setState(1202);
					match(COBWEB);
					_localctx.value = MaterialType.COBWEB;
				}
				break;
				case RUNE_XP:
					enterOuterAlt(_localctx, 25);
				{
					setState(1204);
					match(RUNE_XP);
					_localctx.value = MaterialType.RUNE_XP;
				}
				break;
				case RUNE_SP:
					enterOuterAlt(_localctx, 26);
				{
					setState(1206);
					match(RUNE_SP);
					_localctx.value = MaterialType.RUNE_SP;
				}
				break;
				case RUNE_REMOVE_PENALTY:
					enterOuterAlt(_localctx, 27);
				{
					setState(1208);
					match(RUNE_REMOVE_PENALTY);
					_localctx.value = MaterialType.RUNE_REMOVE_PENALTY;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Consume_type_wrapperContext extends ParserRuleContext {
		public ConsumeType value;
		public Consume_typeContext ct;

		public Consume_typeContext consume_type() {
			return getRuleContext(Consume_typeContext.class, 0);
		}

		public Consume_type_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_consume_type_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterConsume_type_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitConsume_type_wrapper(this);
		}
	}

	public final Consume_type_wrapperContext consume_type_wrapper() throws RecognitionException {
		Consume_type_wrapperContext _localctx = new Consume_type_wrapperContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_consume_type_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1212);
				match(T__115);
				setState(1213);
				match(T__3);
				setState(1214);
				((Consume_type_wrapperContext) _localctx).ct = consume_type();
				_localctx.value = ((Consume_type_wrapperContext) _localctx).ct.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Consume_typeContext extends ParserRuleContext {
		public ConsumeType value;

		public TerminalNode CONSUME_TYPE_NORMAL() {
			return getToken(ItemDatasParser.CONSUME_TYPE_NORMAL, 0);
		}

		public TerminalNode CONSUME_TYPE_STACKABLE() {
			return getToken(ItemDatasParser.CONSUME_TYPE_STACKABLE, 0);
		}

		public TerminalNode CONSUME_TYPE_ASSET() {
			return getToken(ItemDatasParser.CONSUME_TYPE_ASSET, 0);
		}

		public Consume_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_consume_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterConsume_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitConsume_type(this);
		}
	}

	public final Consume_typeContext consume_type() throws RecognitionException {
		Consume_typeContext _localctx = new Consume_typeContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_consume_type);
		try {
			setState(1223);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case CONSUME_TYPE_NORMAL:
					enterOuterAlt(_localctx, 1);
				{
					setState(1217);
					match(CONSUME_TYPE_NORMAL);
					_localctx.value = ConsumeType.CONSUME_TYPE_NORMAL;
				}
				break;
				case CONSUME_TYPE_STACKABLE:
					enterOuterAlt(_localctx, 2);
				{
					setState(1219);
					match(CONSUME_TYPE_STACKABLE);
					_localctx.value = ConsumeType.CONSUME_TYPE_STACKABLE;
				}
				break;
				case CONSUME_TYPE_ASSET:
					enterOuterAlt(_localctx, 3);
				{
					setState(1221);
					match(CONSUME_TYPE_ASSET);
					_localctx.value = ConsumeType.CONSUME_TYPE_ASSET;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Default_action_wrapperContext extends ParserRuleContext {
		public DefaultAction value;
		public Default_actionContext da;

		public Default_actionContext default_action() {
			return getRuleContext(Default_actionContext.class, 0);
		}

		public Default_action_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_default_action_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDefault_action_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDefault_action_wrapper(this);
		}
	}

	public final Default_action_wrapperContext default_action_wrapper() throws RecognitionException {
		Default_action_wrapperContext _localctx = new Default_action_wrapperContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_default_action_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1225);
				match(T__116);
				setState(1226);
				match(T__3);
				setState(1227);
				((Default_action_wrapperContext) _localctx).da = default_action();
				_localctx.value = ((Default_action_wrapperContext) _localctx).da.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Default_actionContext extends ParserRuleContext {
		public DefaultAction value;

		public TerminalNode ACTION_NONE() {
			return getToken(ItemDatasParser.ACTION_NONE, 0);
		}

		public TerminalNode ACTION_EQUIP() {
			return getToken(ItemDatasParser.ACTION_EQUIP, 0);
		}

		public TerminalNode ACTION_PEEL() {
			return getToken(ItemDatasParser.ACTION_PEEL, 0);
		}

		public TerminalNode ACTION_SKILL_REDUCE() {
			return getToken(ItemDatasParser.ACTION_SKILL_REDUCE, 0);
		}

		public TerminalNode ACTION_SOULSHOT() {
			return getToken(ItemDatasParser.ACTION_SOULSHOT, 0);
		}

		public TerminalNode ACTION_RECIPE() {
			return getToken(ItemDatasParser.ACTION_RECIPE, 0);
		}

		public TerminalNode ACTION_SKILL_MAINTAIN() {
			return getToken(ItemDatasParser.ACTION_SKILL_MAINTAIN, 0);
		}

		public TerminalNode ACTION_SPIRITSHOT() {
			return getToken(ItemDatasParser.ACTION_SPIRITSHOT, 0);
		}

		public TerminalNode ACTION_DICE() {
			return getToken(ItemDatasParser.ACTION_DICE, 0);
		}

		public TerminalNode ACTION_CALC() {
			return getToken(ItemDatasParser.ACTION_CALC, 0);
		}

		public TerminalNode ACTION_SEED() {
			return getToken(ItemDatasParser.ACTION_SEED, 0);
		}

		public TerminalNode ACTION_HARVEST() {
			return getToken(ItemDatasParser.ACTION_HARVEST, 0);
		}

		public TerminalNode ACTION_CAPSULE() {
			return getToken(ItemDatasParser.ACTION_CAPSULE, 0);
		}

		public TerminalNode ACTION_XMAS_OPEN() {
			return getToken(ItemDatasParser.ACTION_XMAS_OPEN, 0);
		}

		public TerminalNode ACTION_SHOW_HTML() {
			return getToken(ItemDatasParser.ACTION_SHOW_HTML, 0);
		}

		public TerminalNode ACTION_SHOW_SSQ_STATUS() {
			return getToken(ItemDatasParser.ACTION_SHOW_SSQ_STATUS, 0);
		}

		public TerminalNode ACTION_FISHINGSHOT() {
			return getToken(ItemDatasParser.ACTION_FISHINGSHOT, 0);
		}

		public TerminalNode ACTION_SUMMON_SOULSHOT() {
			return getToken(ItemDatasParser.ACTION_SUMMON_SOULSHOT, 0);
		}

		public TerminalNode ACTION_SUMMON_SPIRITSHOT() {
			return getToken(ItemDatasParser.ACTION_SUMMON_SPIRITSHOT, 0);
		}

		public TerminalNode ACTION_CALL_SKILL() {
			return getToken(ItemDatasParser.ACTION_CALL_SKILL, 0);
		}

		public TerminalNode ACTION_SHOW_ADVENTURER_GUIDE_BOOK() {
			return getToken(ItemDatasParser.ACTION_SHOW_ADVENTURER_GUIDE_BOOK, 0);
		}

		public TerminalNode ACTION_KEEP_EXP() {
			return getToken(ItemDatasParser.ACTION_KEEP_EXP, 0);
		}

		public TerminalNode ACTION_CREATE_MPCC() {
			return getToken(ItemDatasParser.ACTION_CREATE_MPCC, 0);
		}

		public TerminalNode ACTION_NICK_COLOR() {
			return getToken(ItemDatasParser.ACTION_NICK_COLOR, 0);
		}

		public TerminalNode ACTION_HIDE_NAME() {
			return getToken(ItemDatasParser.ACTION_HIDE_NAME, 0);
		}

		public TerminalNode ACTION_START_QUEST() {
			return getToken(ItemDatasParser.ACTION_START_QUEST, 0);
		}

		public Default_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_default_action;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDefault_action(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDefault_action(this);
		}
	}

	public final Default_actionContext default_action() throws RecognitionException {
		Default_actionContext _localctx = new Default_actionContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_default_action);
		try {
			setState(1282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case ACTION_NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(1230);
					match(ACTION_NONE);
					_localctx.value = DefaultAction.ACTION_NONE;
				}
				break;
				case ACTION_EQUIP:
					enterOuterAlt(_localctx, 2);
				{
					setState(1232);
					match(ACTION_EQUIP);
					_localctx.value = DefaultAction.ACTION_EQUIP;
				}
				break;
				case ACTION_PEEL:
					enterOuterAlt(_localctx, 3);
				{
					setState(1234);
					match(ACTION_PEEL);
					_localctx.value = DefaultAction.ACTION_PEEL;
				}
				break;
				case ACTION_SKILL_REDUCE:
					enterOuterAlt(_localctx, 4);
				{
					setState(1236);
					match(ACTION_SKILL_REDUCE);
					_localctx.value = DefaultAction.ACTION_SKILL_REDUCE;
				}
				break;
				case ACTION_SOULSHOT:
					enterOuterAlt(_localctx, 5);
				{
					setState(1238);
					match(ACTION_SOULSHOT);
					_localctx.value = DefaultAction.ACTION_SOULSHOT;
				}
				break;
				case ACTION_RECIPE:
					enterOuterAlt(_localctx, 6);
				{
					setState(1240);
					match(ACTION_RECIPE);
					_localctx.value = DefaultAction.ACTION_RECIPE;
				}
				break;
				case ACTION_SKILL_MAINTAIN:
					enterOuterAlt(_localctx, 7);
				{
					setState(1242);
					match(ACTION_SKILL_MAINTAIN);
					_localctx.value = DefaultAction.ACTION_SKILL_MAINTAIN;
				}
				break;
				case ACTION_SPIRITSHOT:
					enterOuterAlt(_localctx, 8);
				{
					setState(1244);
					match(ACTION_SPIRITSHOT);
					_localctx.value = DefaultAction.ACTION_SPIRITSHOT;
				}
				break;
				case ACTION_DICE:
					enterOuterAlt(_localctx, 9);
				{
					setState(1246);
					match(ACTION_DICE);
					_localctx.value = DefaultAction.ACTION_DICE;
				}
				break;
				case ACTION_CALC:
					enterOuterAlt(_localctx, 10);
				{
					setState(1248);
					match(ACTION_CALC);
					_localctx.value = DefaultAction.ACTION_CALC;
				}
				break;
				case ACTION_SEED:
					enterOuterAlt(_localctx, 11);
				{
					setState(1250);
					match(ACTION_SEED);
					_localctx.value = DefaultAction.ACTION_SEED;
				}
				break;
				case ACTION_HARVEST:
					enterOuterAlt(_localctx, 12);
				{
					setState(1252);
					match(ACTION_HARVEST);
					_localctx.value = DefaultAction.ACTION_HARVEST;
				}
				break;
				case ACTION_CAPSULE:
					enterOuterAlt(_localctx, 13);
				{
					setState(1254);
					match(ACTION_CAPSULE);
					_localctx.value = DefaultAction.ACTION_CAPSULE;
				}
				break;
				case ACTION_XMAS_OPEN:
					enterOuterAlt(_localctx, 14);
				{
					setState(1256);
					match(ACTION_XMAS_OPEN);
					_localctx.value = DefaultAction.ACTION_XMAS_OPEN;
				}
				break;
				case ACTION_SHOW_HTML:
					enterOuterAlt(_localctx, 15);
				{
					setState(1258);
					match(ACTION_SHOW_HTML);
					_localctx.value = DefaultAction.ACTION_SHOW_HTML;
				}
				break;
				case ACTION_SHOW_SSQ_STATUS:
					enterOuterAlt(_localctx, 16);
				{
					setState(1260);
					match(ACTION_SHOW_SSQ_STATUS);
					_localctx.value = DefaultAction.ACTION_SHOW_SSQ_STATUS;
				}
				break;
				case ACTION_FISHINGSHOT:
					enterOuterAlt(_localctx, 17);
				{
					setState(1262);
					match(ACTION_FISHINGSHOT);
					_localctx.value = DefaultAction.ACTION_FISHINGSHOT;
				}
				break;
				case ACTION_SUMMON_SOULSHOT:
					enterOuterAlt(_localctx, 18);
				{
					setState(1264);
					match(ACTION_SUMMON_SOULSHOT);
					_localctx.value = DefaultAction.ACTION_SUMMON_SOULSHOT;
				}
				break;
				case ACTION_SUMMON_SPIRITSHOT:
					enterOuterAlt(_localctx, 19);
				{
					setState(1266);
					match(ACTION_SUMMON_SPIRITSHOT);
					_localctx.value = DefaultAction.ACTION_SUMMON_SPIRITSHOT;
				}
				break;
				case ACTION_CALL_SKILL:
					enterOuterAlt(_localctx, 20);
				{
					setState(1268);
					match(ACTION_CALL_SKILL);
					_localctx.value = DefaultAction.ACTION_CALL_SKILL;
				}
				break;
				case ACTION_SHOW_ADVENTURER_GUIDE_BOOK:
					enterOuterAlt(_localctx, 21);
				{
					setState(1270);
					match(ACTION_SHOW_ADVENTURER_GUIDE_BOOK);
					_localctx.value = DefaultAction.ACTION_SHOW_ADVENTURER_GUIDE_BOOK;
				}
				break;
				case ACTION_KEEP_EXP:
					enterOuterAlt(_localctx, 22);
				{
					setState(1272);
					match(ACTION_KEEP_EXP);
					_localctx.value = DefaultAction.ACTION_KEEP_EXP;
				}
				break;
				case ACTION_CREATE_MPCC:
					enterOuterAlt(_localctx, 23);
				{
					setState(1274);
					match(ACTION_CREATE_MPCC);
					_localctx.value = DefaultAction.ACTION_CREATE_MPCC;
				}
				break;
				case ACTION_NICK_COLOR:
					enterOuterAlt(_localctx, 24);
				{
					setState(1276);
					match(ACTION_NICK_COLOR);
					_localctx.value = DefaultAction.ACTION_NICK_COLOR;
				}
				break;
				case ACTION_HIDE_NAME:
					enterOuterAlt(_localctx, 25);
				{
					setState(1278);
					match(ACTION_HIDE_NAME);
					_localctx.value = DefaultAction.ACTION_HIDE_NAME;
				}
				break;
				case ACTION_START_QUEST:
					enterOuterAlt(_localctx, 26);
				{
					setState(1280);
					match(ACTION_START_QUEST);
					_localctx.value = DefaultAction.ACTION_START_QUEST;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Recipe_idContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Recipe_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_recipe_id;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterRecipe_id(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitRecipe_id(this);
		}
	}

	public final Recipe_idContext recipe_id() throws RecognitionException {
		Recipe_idContext _localctx = new Recipe_idContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_recipe_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1284);
				match(T__117);
				setState(1285);
				match(T__3);
				setState(1286);
				((Recipe_idContext) _localctx).io = int_object();
				_localctx.value = ((Recipe_idContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlessedContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public BlessedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_blessed;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterBlessed(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitBlessed(this);
		}
	}

	public final BlessedContext blessed() throws RecognitionException {
		BlessedContext _localctx = new BlessedContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_blessed);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1289);
				match(T__118);
				setState(1290);
				match(T__3);
				setState(1291);
				((BlessedContext) _localctx).io = int_object();
				_localctx.value = ((BlessedContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WeightContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public WeightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_weight;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterWeight(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitWeight(this);
		}
	}

	public final WeightContext weight() throws RecognitionException {
		WeightContext _localctx = new WeightContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_weight);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1294);
				match(T__119);
				setState(1295);
				match(T__3);
				setState(1296);
				((WeightContext) _localctx).io = int_object();
				_localctx.value = ((WeightContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_multi_skill_listContext extends ParserRuleContext {
		public List<String> value;
		public Identifier_listContext il;

		public Identifier_listContext identifier_list() {
			return getRuleContext(Identifier_listContext.class, 0);
		}

		public Item_multi_skill_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_multi_skill_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem_multi_skill_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem_multi_skill_list(this);
		}
	}

	public final Item_multi_skill_listContext item_multi_skill_list() throws RecognitionException {
		Item_multi_skill_listContext _localctx = new Item_multi_skill_listContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_item_multi_skill_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1299);
				match(T__120);
				setState(1300);
				match(T__3);
				setState(1301);
				((Item_multi_skill_listContext) _localctx).il = identifier_list();
				_localctx.value = ((Item_multi_skill_listContext) _localctx).il.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delay_share_groupContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Delay_share_groupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_delay_share_group;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDelay_share_group(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDelay_share_group(this);
		}
	}

	public final Delay_share_groupContext delay_share_group() throws RecognitionException {
		Delay_share_groupContext _localctx = new Delay_share_groupContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_delay_share_group);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1304);
				match(T__121);
				setState(1305);
				match(T__3);
				setState(1306);
				((Delay_share_groupContext) _localctx).io = int_object();
				_localctx.value = ((Delay_share_groupContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Etcitem_type_wrapperContext extends ParserRuleContext {
		public EtcItemType value;
		public Etcitem_typeContext et;

		public Etcitem_typeContext etcitem_type() {
			return getRuleContext(Etcitem_typeContext.class, 0);
		}

		public Etcitem_type_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_etcitem_type_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEtcitem_type_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEtcitem_type_wrapper(this);
		}
	}

	public final Etcitem_type_wrapperContext etcitem_type_wrapper() throws RecognitionException {
		Etcitem_type_wrapperContext _localctx = new Etcitem_type_wrapperContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_etcitem_type_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1309);
				match(T__122);
				setState(1310);
				match(T__3);
				setState(1311);
				((Etcitem_type_wrapperContext) _localctx).et = etcitem_type();
				_localctx.value = ((Etcitem_type_wrapperContext) _localctx).et.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Etcitem_typeContext extends ParserRuleContext {
		public EtcItemType value;

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode POTION() {
			return getToken(ItemDatasParser.POTION, 0);
		}

		public TerminalNode ARROW() {
			return getToken(ItemDatasParser.ARROW, 0);
		}

		public TerminalNode SCRL_ENCHANT_AM() {
			return getToken(ItemDatasParser.SCRL_ENCHANT_AM, 0);
		}

		public TerminalNode SCRL_ENCHANT_WP() {
			return getToken(ItemDatasParser.SCRL_ENCHANT_WP, 0);
		}

		public TerminalNode SCROLL() {
			return getToken(ItemDatasParser.SCROLL, 0);
		}

		public TerminalNode MATERIAL() {
			return getToken(ItemDatasParser.MATERIAL, 0);
		}

		public TerminalNode RECIPE() {
			return getToken(ItemDatasParser.RECIPE, 0);
		}

		public TerminalNode PET_COLLAR() {
			return getToken(ItemDatasParser.PET_COLLAR, 0);
		}

		public TerminalNode CASTLE_GUARD() {
			return getToken(ItemDatasParser.CASTLE_GUARD, 0);
		}

		public TerminalNode LOTTO() {
			return getToken(ItemDatasParser.LOTTO, 0);
		}

		public TerminalNode RACE_TICKET() {
			return getToken(ItemDatasParser.RACE_TICKET, 0);
		}

		public TerminalNode DYE() {
			return getToken(ItemDatasParser.DYE, 0);
		}

		public TerminalNode SEED() {
			return getToken(ItemDatasParser.SEED, 0);
		}

		public TerminalNode SEED2() {
			return getToken(ItemDatasParser.SEED2, 0);
		}

		public TerminalNode CROP() {
			return getToken(ItemDatasParser.CROP, 0);
		}

		public TerminalNode MATURECROP() {
			return getToken(ItemDatasParser.MATURECROP, 0);
		}

		public TerminalNode HARVEST() {
			return getToken(ItemDatasParser.HARVEST, 0);
		}

		public TerminalNode TICKET_OF_LORD() {
			return getToken(ItemDatasParser.TICKET_OF_LORD, 0);
		}

		public TerminalNode LURE() {
			return getToken(ItemDatasParser.LURE, 0);
		}

		public TerminalNode BLESS_SCRL_ENCHANT_AM() {
			return getToken(ItemDatasParser.BLESS_SCRL_ENCHANT_AM, 0);
		}

		public TerminalNode BLESS_SCRL_ENCHANT_WP() {
			return getToken(ItemDatasParser.BLESS_SCRL_ENCHANT_WP, 0);
		}

		public TerminalNode COUPON() {
			return getToken(ItemDatasParser.COUPON, 0);
		}

		public TerminalNode ELIXIR() {
			return getToken(ItemDatasParser.ELIXIR, 0);
		}

		public TerminalNode SCRL_ENCHANT_ATTR() {
			return getToken(ItemDatasParser.SCRL_ENCHANT_ATTR, 0);
		}

		public TerminalNode BOLT() {
			return getToken(ItemDatasParser.BOLT, 0);
		}

		public TerminalNode RUNE_SELECT() {
			return getToken(ItemDatasParser.RUNE_SELECT, 0);
		}

		public TerminalNode SCRL_INC_ENCHANT_PROP_WP() {
			return getToken(ItemDatasParser.SCRL_INC_ENCHANT_PROP_WP, 0);
		}

		public TerminalNode SCRL_INC_ENCHANT_PROP_AM() {
			return getToken(ItemDatasParser.SCRL_INC_ENCHANT_PROP_AM, 0);
		}

		public TerminalNode ANCIENT_CRYSTAL_ENCHANT_WP() {
			return getToken(ItemDatasParser.ANCIENT_CRYSTAL_ENCHANT_WP, 0);
		}

		public TerminalNode ANCIENT_CRYSTAL_ENCHANT_AM() {
			return getToken(ItemDatasParser.ANCIENT_CRYSTAL_ENCHANT_AM, 0);
		}

		public TerminalNode RUNE() {
			return getToken(ItemDatasParser.RUNE, 0);
		}

		public Etcitem_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_etcitem_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEtcitem_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEtcitem_type(this);
		}
	}

	public final Etcitem_typeContext etcitem_type() throws RecognitionException {
		Etcitem_typeContext _localctx = new Etcitem_typeContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_etcitem_type);
		try {
			setState(1378);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(1314);
					match(NONE);
					_localctx.value = EtcItemType.NONE;
				}
				break;
				case POTION:
					enterOuterAlt(_localctx, 2);
				{
					setState(1316);
					match(POTION);
					_localctx.value = EtcItemType.POTION;
				}
				break;
				case ARROW:
					enterOuterAlt(_localctx, 3);
				{
					setState(1318);
					match(ARROW);
					_localctx.value = EtcItemType.ARROW;
				}
				break;
				case SCRL_ENCHANT_AM:
					enterOuterAlt(_localctx, 4);
				{
					setState(1320);
					match(SCRL_ENCHANT_AM);
					_localctx.value = EtcItemType.SCRL_ENCHANT_AM;
				}
				break;
				case SCRL_ENCHANT_WP:
					enterOuterAlt(_localctx, 5);
				{
					setState(1322);
					match(SCRL_ENCHANT_WP);
					_localctx.value = EtcItemType.SCRL_ENCHANT_WP;
				}
				break;
				case SCROLL:
					enterOuterAlt(_localctx, 6);
				{
					setState(1324);
					match(SCROLL);
					_localctx.value = EtcItemType.SCROLL;
				}
				break;
				case MATERIAL:
					enterOuterAlt(_localctx, 7);
				{
					setState(1326);
					match(MATERIAL);
					_localctx.value = EtcItemType.MATERIAL;
				}
				break;
				case RECIPE:
					enterOuterAlt(_localctx, 8);
				{
					setState(1328);
					match(RECIPE);
					_localctx.value = EtcItemType.RECIPE;
				}
				break;
				case PET_COLLAR:
					enterOuterAlt(_localctx, 9);
				{
					setState(1330);
					match(PET_COLLAR);
					_localctx.value = EtcItemType.PET_COLLAR;
				}
				break;
				case CASTLE_GUARD:
					enterOuterAlt(_localctx, 10);
				{
					setState(1332);
					match(CASTLE_GUARD);
					_localctx.value = EtcItemType.CASTLE_GUARD;
				}
				break;
				case LOTTO:
					enterOuterAlt(_localctx, 11);
				{
					setState(1334);
					match(LOTTO);
					_localctx.value = EtcItemType.LOTTO;
				}
				break;
				case RACE_TICKET:
					enterOuterAlt(_localctx, 12);
				{
					setState(1336);
					match(RACE_TICKET);
					_localctx.value = EtcItemType.RACE_TICKET;
				}
				break;
				case DYE:
					enterOuterAlt(_localctx, 13);
				{
					setState(1338);
					match(DYE);
					_localctx.value = EtcItemType.DYE;
				}
				break;
				case SEED:
					enterOuterAlt(_localctx, 14);
				{
					setState(1340);
					match(SEED);
					_localctx.value = EtcItemType.SEED;
				}
				break;
				case SEED2:
					enterOuterAlt(_localctx, 15);
				{
					setState(1342);
					match(SEED2);
					_localctx.value = EtcItemType.SEED2;
				}
				break;
				case CROP:
					enterOuterAlt(_localctx, 16);
				{
					setState(1344);
					match(CROP);
					_localctx.value = EtcItemType.CROP;
				}
				break;
				case MATURECROP:
					enterOuterAlt(_localctx, 17);
				{
					setState(1346);
					match(MATURECROP);
					_localctx.value = EtcItemType.MATURECROP;
				}
				break;
				case HARVEST:
					enterOuterAlt(_localctx, 18);
				{
					setState(1348);
					match(HARVEST);
					_localctx.value = EtcItemType.HARVEST;
				}
				break;
				case TICKET_OF_LORD:
					enterOuterAlt(_localctx, 19);
				{
					setState(1350);
					match(TICKET_OF_LORD);
					_localctx.value = EtcItemType.TICKET_OF_LORD;
				}
				break;
				case LURE:
					enterOuterAlt(_localctx, 20);
				{
					setState(1352);
					match(LURE);
					_localctx.value = EtcItemType.LURE;
				}
				break;
				case BLESS_SCRL_ENCHANT_AM:
					enterOuterAlt(_localctx, 21);
				{
					setState(1354);
					match(BLESS_SCRL_ENCHANT_AM);
					_localctx.value = EtcItemType.BLESS_SCRL_ENCHANT_AM;
				}
				break;
				case BLESS_SCRL_ENCHANT_WP:
					enterOuterAlt(_localctx, 22);
				{
					setState(1356);
					match(BLESS_SCRL_ENCHANT_WP);
					_localctx.value = EtcItemType.BLESS_SCRL_ENCHANT_WP;
				}
				break;
				case COUPON:
					enterOuterAlt(_localctx, 23);
				{
					setState(1358);
					match(COUPON);
					_localctx.value = EtcItemType.COUPON;
				}
				break;
				case ELIXIR:
					enterOuterAlt(_localctx, 24);
				{
					setState(1360);
					match(ELIXIR);
					_localctx.value = EtcItemType.ELIXIR;
				}
				break;
				case SCRL_ENCHANT_ATTR:
					enterOuterAlt(_localctx, 25);
				{
					setState(1362);
					match(SCRL_ENCHANT_ATTR);
					_localctx.value = EtcItemType.SCRL_ENCHANT_ATTR;
				}
				break;
				case BOLT:
					enterOuterAlt(_localctx, 26);
				{
					setState(1364);
					match(BOLT);
					_localctx.value = EtcItemType.BOLT;
				}
				break;
				case RUNE_SELECT:
					enterOuterAlt(_localctx, 27);
				{
					setState(1366);
					match(RUNE_SELECT);
					_localctx.value = EtcItemType.RUNE_SELECT;
				}
				break;
				case SCRL_INC_ENCHANT_PROP_WP:
					enterOuterAlt(_localctx, 28);
				{
					setState(1368);
					match(SCRL_INC_ENCHANT_PROP_WP);
					_localctx.value = EtcItemType.SCRL_INC_ENCHANT_PROP_WP;
				}
				break;
				case SCRL_INC_ENCHANT_PROP_AM:
					enterOuterAlt(_localctx, 29);
				{
					setState(1370);
					match(SCRL_INC_ENCHANT_PROP_AM);
					_localctx.value = EtcItemType.SCRL_INC_ENCHANT_PROP_AM;
				}
				break;
				case ANCIENT_CRYSTAL_ENCHANT_WP:
					enterOuterAlt(_localctx, 30);
				{
					setState(1372);
					match(ANCIENT_CRYSTAL_ENCHANT_WP);
					_localctx.value = EtcItemType.ANCIENT_CRYSTAL_ENCHANT_WP;
				}
				break;
				case ANCIENT_CRYSTAL_ENCHANT_AM:
					enterOuterAlt(_localctx, 31);
				{
					setState(1374);
					match(ANCIENT_CRYSTAL_ENCHANT_AM);
					_localctx.value = EtcItemType.ANCIENT_CRYSTAL_ENCHANT_AM;
				}
				break;
				case RUNE:
					enterOuterAlt(_localctx, 32);
				{
					setState(1376);
					match(RUNE);
					_localctx.value = EtcItemType.RUNE;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Armor_type_wrapperContext extends ParserRuleContext {
		public ArmorType value;
		public Armor_typeContext at;

		public Armor_typeContext armor_type() {
			return getRuleContext(Armor_typeContext.class, 0);
		}

		public Armor_type_wrapperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_armor_type_wrapper;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterArmor_type_wrapper(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitArmor_type_wrapper(this);
		}
	}

	public final Armor_type_wrapperContext armor_type_wrapper() throws RecognitionException {
		Armor_type_wrapperContext _localctx = new Armor_type_wrapperContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_armor_type_wrapper);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1380);
				match(T__123);
				setState(1381);
				match(T__3);
				setState(1382);
				((Armor_type_wrapperContext) _localctx).at = armor_type();
				_localctx.value = ((Armor_type_wrapperContext) _localctx).at.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Armor_typeContext extends ParserRuleContext {
		public ArmorType value;

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode LIGHT() {
			return getToken(ItemDatasParser.LIGHT, 0);
		}

		public TerminalNode HEAVY() {
			return getToken(ItemDatasParser.HEAVY, 0);
		}

		public TerminalNode MAGIC() {
			return getToken(ItemDatasParser.MAGIC, 0);
		}

		public TerminalNode SIGIL() {
			return getToken(ItemDatasParser.SIGIL, 0);
		}

		public Armor_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_armor_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterArmor_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitArmor_type(this);
		}
	}

	public final Armor_typeContext armor_type() throws RecognitionException {
		Armor_typeContext _localctx = new Armor_typeContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_armor_type);
		try {
			setState(1395);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(1385);
					match(NONE);
					_localctx.value = ArmorType.NONE;
				}
				break;
				case LIGHT:
					enterOuterAlt(_localctx, 2);
				{
					setState(1387);
					match(LIGHT);
					_localctx.value = ArmorType.LIGHT;
				}
				break;
				case HEAVY:
					enterOuterAlt(_localctx, 3);
				{
					setState(1389);
					match(HEAVY);
					_localctx.value = ArmorType.HEAVY;
				}
				break;
				case MAGIC:
					enterOuterAlt(_localctx, 4);
				{
					setState(1391);
					match(MAGIC);
					_localctx.value = ArmorType.MAGIC;
				}
				break;
				case SIGIL:
					enterOuterAlt(_localctx, 5);
				{
					setState(1393);
					match(SIGIL);
					_localctx.value = ArmorType.SIGIL;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_bit_type_listContext extends ParserRuleContext {
		public List<SlotBitType> value = new ArrayList<>();
		;
		public Slot_bit_typeContext sbt;

		public List<Slot_bit_typeContext> slot_bit_type() {
			return getRuleContexts(Slot_bit_typeContext.class);
		}

		public Slot_bit_typeContext slot_bit_type(int i) {
			return getRuleContext(Slot_bit_typeContext.class, i);
		}

		public Slot_bit_type_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_bit_type_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_bit_type_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_bit_type_list(this);
		}
	}

	public final Slot_bit_type_listContext slot_bit_type_list() throws RecognitionException {
		Slot_bit_type_listContext _localctx = new Slot_bit_type_listContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_slot_bit_type_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1397);
				match(T__124);
				setState(1398);
				match(T__3);
				setState(1399);
				match(T__4);
				setState(1400);
				((Slot_bit_type_listContext) _localctx).sbt = slot_bit_type();
				_localctx.value.add(((Slot_bit_type_listContext) _localctx).sbt.value);
				setState(1406);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la == SEMICOLON) {
					{
						setState(1402);
						match(SEMICOLON);
						setState(1403);
						((Slot_bit_type_listContext) _localctx).sbt = slot_bit_type();
						_localctx.value.add(((Slot_bit_type_listContext) _localctx).sbt.value);
					}
				}

				setState(1408);
				match(T__5);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_bit_typeContext extends ParserRuleContext {
		public SlotBitType value;

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode RHAND() {
			return getToken(ItemDatasParser.RHAND, 0);
		}

		public TerminalNode LRHAND() {
			return getToken(ItemDatasParser.LRHAND, 0);
		}

		public TerminalNode LHAND() {
			return getToken(ItemDatasParser.LHAND, 0);
		}

		public TerminalNode CHEST() {
			return getToken(ItemDatasParser.CHEST, 0);
		}

		public TerminalNode LEGS() {
			return getToken(ItemDatasParser.LEGS, 0);
		}

		public TerminalNode FEET() {
			return getToken(ItemDatasParser.FEET, 0);
		}

		public TerminalNode HEAD() {
			return getToken(ItemDatasParser.HEAD, 0);
		}

		public TerminalNode GLOVES() {
			return getToken(ItemDatasParser.GLOVES, 0);
		}

		public TerminalNode ONEPIECE() {
			return getToken(ItemDatasParser.ONEPIECE, 0);
		}

		public TerminalNode REAR() {
			return getToken(ItemDatasParser.REAR, 0);
		}

		public TerminalNode LEAR() {
			return getToken(ItemDatasParser.LEAR, 0);
		}

		public TerminalNode LFINGER() {
			return getToken(ItemDatasParser.LFINGER, 0);
		}

		public TerminalNode RFINGER() {
			return getToken(ItemDatasParser.RFINGER, 0);
		}

		public TerminalNode NECK() {
			return getToken(ItemDatasParser.NECK, 0);
		}

		public TerminalNode BACK() {
			return getToken(ItemDatasParser.BACK, 0);
		}

		public TerminalNode UNDERWEAR() {
			return getToken(ItemDatasParser.UNDERWEAR, 0);
		}

		public TerminalNode HAIR() {
			return getToken(ItemDatasParser.HAIR, 0);
		}

		public TerminalNode HAIR2() {
			return getToken(ItemDatasParser.HAIR2, 0);
		}

		public TerminalNode HAIRALL() {
			return getToken(ItemDatasParser.HAIRALL, 0);
		}

		public TerminalNode ALLDRESS() {
			return getToken(ItemDatasParser.ALLDRESS, 0);
		}

		public TerminalNode RBRACELET() {
			return getToken(ItemDatasParser.RBRACELET, 0);
		}

		public TerminalNode LBRACELET() {
			return getToken(ItemDatasParser.LBRACELET, 0);
		}

		public TerminalNode WAIST() {
			return getToken(ItemDatasParser.WAIST, 0);
		}

		public TerminalNode DECO1() {
			return getToken(ItemDatasParser.DECO1, 0);
		}

		public Slot_bit_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_slot_bit_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterSlot_bit_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitSlot_bit_type(this);
		}
	}

	public final Slot_bit_typeContext slot_bit_type() throws RecognitionException {
		Slot_bit_typeContext _localctx = new Slot_bit_typeContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_slot_bit_type);
		try {
			setState(1460);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(1410);
					match(NONE);
					_localctx.value = SlotBitType.NONE;
				}
				break;
				case RHAND:
					enterOuterAlt(_localctx, 2);
				{
					setState(1412);
					match(RHAND);
					_localctx.value = SlotBitType.RHAND;
				}
				break;
				case LRHAND:
					enterOuterAlt(_localctx, 3);
				{
					setState(1414);
					match(LRHAND);
					_localctx.value = SlotBitType.LRHAND;
				}
				break;
				case LHAND:
					enterOuterAlt(_localctx, 4);
				{
					setState(1416);
					match(LHAND);
					_localctx.value = SlotBitType.LHAND;
				}
				break;
				case CHEST:
					enterOuterAlt(_localctx, 5);
				{
					setState(1418);
					match(CHEST);
					_localctx.value = SlotBitType.CHEST;
				}
				break;
				case LEGS:
					enterOuterAlt(_localctx, 6);
				{
					setState(1420);
					match(LEGS);
					_localctx.value = SlotBitType.LEGS;
				}
				break;
				case FEET:
					enterOuterAlt(_localctx, 7);
				{
					setState(1422);
					match(FEET);
					_localctx.value = SlotBitType.FEET;
				}
				break;
				case HEAD:
					enterOuterAlt(_localctx, 8);
				{
					setState(1424);
					match(HEAD);
					_localctx.value = SlotBitType.HEAD;
				}
				break;
				case GLOVES:
					enterOuterAlt(_localctx, 9);
				{
					setState(1426);
					match(GLOVES);
					_localctx.value = SlotBitType.GLOVES;
				}
				break;
				case ONEPIECE:
					enterOuterAlt(_localctx, 10);
				{
					setState(1428);
					match(ONEPIECE);
					_localctx.value = SlotBitType.ONEPIECE;
				}
				break;
				case REAR:
					enterOuterAlt(_localctx, 11);
				{
					setState(1430);
					match(REAR);
					_localctx.value = SlotBitType.REAR;
				}
				break;
				case LEAR:
					enterOuterAlt(_localctx, 12);
				{
					setState(1432);
					match(LEAR);
					_localctx.value = SlotBitType.LEAR;
				}
				break;
				case LFINGER:
					enterOuterAlt(_localctx, 13);
				{
					setState(1434);
					match(LFINGER);
					_localctx.value = SlotBitType.LFINGER;
				}
				break;
				case RFINGER:
					enterOuterAlt(_localctx, 14);
				{
					setState(1436);
					match(RFINGER);
					_localctx.value = SlotBitType.RFINGER;
				}
				break;
				case NECK:
					enterOuterAlt(_localctx, 15);
				{
					setState(1438);
					match(NECK);
					_localctx.value = SlotBitType.NECK;
				}
				break;
				case BACK:
					enterOuterAlt(_localctx, 16);
				{
					setState(1440);
					match(BACK);
					_localctx.value = SlotBitType.BACK;
				}
				break;
				case UNDERWEAR:
					enterOuterAlt(_localctx, 17);
				{
					setState(1442);
					match(UNDERWEAR);
					_localctx.value = SlotBitType.UNDERWEAR;
				}
				break;
				case HAIR:
					enterOuterAlt(_localctx, 18);
				{
					setState(1444);
					match(HAIR);
					_localctx.value = SlotBitType.HAIR;
				}
				break;
				case HAIR2:
					enterOuterAlt(_localctx, 19);
				{
					setState(1446);
					match(HAIR2);
					_localctx.value = SlotBitType.HAIR2;
				}
				break;
				case HAIRALL:
					enterOuterAlt(_localctx, 20);
				{
					setState(1448);
					match(HAIRALL);
					_localctx.value = SlotBitType.HAIRALL;
				}
				break;
				case ALLDRESS:
					enterOuterAlt(_localctx, 21);
				{
					setState(1450);
					match(ALLDRESS);
					_localctx.value = SlotBitType.ALLDRESS;
				}
				break;
				case RBRACELET:
					enterOuterAlt(_localctx, 22);
				{
					setState(1452);
					match(RBRACELET);
					_localctx.value = SlotBitType.RBRACELET;
				}
				break;
				case LBRACELET:
					enterOuterAlt(_localctx, 23);
				{
					setState(1454);
					match(LBRACELET);
					_localctx.value = SlotBitType.LBRACELET;
				}
				break;
				case WAIST:
					enterOuterAlt(_localctx, 24);
				{
					setState(1456);
					match(WAIST);
					_localctx.value = SlotBitType.WAIST;
				}
				break;
				case DECO1:
					enterOuterAlt(_localctx, 25);
				{
					setState(1458);
					match(DECO1);
					_localctx.value = SlotBitType.DECO1;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_typeContext extends ParserRuleContext {
		public ItemClass value;
		public Item_classContext ic;

		public Item_classContext item_class() {
			return getRuleContext(Item_classContext.class, 0);
		}

		public Item_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem_type(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem_type(this);
		}
	}

	public final Item_typeContext item_type() throws RecognitionException {
		Item_typeContext _localctx = new Item_typeContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_item_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1462);
				match(T__125);
				setState(1463);
				match(T__3);
				setState(1464);
				((Item_typeContext) _localctx).ic = item_class();
				_localctx.value = ((Item_typeContext) _localctx).ic.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_classContext extends ParserRuleContext {
		public ItemClass value;

		public TerminalNode WEAPON() {
			return getToken(ItemDatasParser.WEAPON, 0);
		}

		public TerminalNode ARMOR() {
			return getToken(ItemDatasParser.ARMOR, 0);
		}

		public TerminalNode ETCITEM() {
			return getToken(ItemDatasParser.ETCITEM, 0);
		}

		public TerminalNode ASSET() {
			return getToken(ItemDatasParser.ASSET, 0);
		}

		public TerminalNode ACCESSARY() {
			return getToken(ItemDatasParser.ACCESSARY, 0);
		}

		public TerminalNode QUESTITEM() {
			return getToken(ItemDatasParser.QUESTITEM, 0);
		}

		public Item_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_class;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem_class(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem_class(this);
		}
	}

	public final Item_classContext item_class() throws RecognitionException {
		Item_classContext _localctx = new Item_classContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_item_class);
		try {
			setState(1479);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case WEAPON:
					enterOuterAlt(_localctx, 1);
				{
					setState(1467);
					match(WEAPON);
					_localctx.value = ItemClass.WEAPON;
				}
				break;
				case ARMOR:
					enterOuterAlt(_localctx, 2);
				{
					setState(1469);
					match(ARMOR);
					_localctx.value = ItemClass.ARMOR;
				}
				break;
				case ETCITEM:
					enterOuterAlt(_localctx, 3);
				{
					setState(1471);
					match(ETCITEM);
					_localctx.value = ItemClass.ETCITEM;
				}
				break;
				case ASSET:
					enterOuterAlt(_localctx, 4);
				{
					setState(1473);
					match(ASSET);
					_localctx.value = ItemClass.ASSET;
				}
				break;
				case ACCESSARY:
					enterOuterAlt(_localctx, 5);
				{
					setState(1475);
					match(ACCESSARY);
					_localctx.value = ItemClass.ACCESSARY;
				}
				break;
				case QUESTITEM:
					enterOuterAlt(_localctx, 6);
				{
					setState(1477);
					match(QUESTITEM);
					_localctx.value = ItemClass.QUESTITEM;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Item_idContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Item_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_item_id;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterItem_id(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitItem_id(this);
		}
	}

	public final Item_idContext item_id() throws RecognitionException {
		Item_idContext _localctx = new Item_idContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_item_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1481);
				((Item_idContext) _localctx).io = int_object();
				_localctx.value = ((Item_idContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Identifier_objectContext extends ParserRuleContext {
		public String value;

		public TerminalNode DAGGER() {
			return getToken(ItemDatasParser.DAGGER, 0);
		}

		public TerminalNode BOW() {
			return getToken(ItemDatasParser.BOW, 0);
		}

		public TerminalNode CROSSBOW() {
			return getToken(ItemDatasParser.CROSSBOW, 0);
		}

		public TerminalNode RAPIER() {
			return getToken(ItemDatasParser.RAPIER, 0);
		}

		public TerminalNode GLOVES() {
			return getToken(ItemDatasParser.GLOVES, 0);
		}

		public TerminalNode STEEL() {
			return getToken(ItemDatasParser.STEEL, 0);
		}

		public TerminalNode LEATHER() {
			return getToken(ItemDatasParser.LEATHER, 0);
		}

		public TerminalNode ORIHARUKON() {
			return getToken(ItemDatasParser.ORIHARUKON, 0);
		}

		public TerminalNode NAME() {
			return getToken(ItemDatasParser.NAME, 0);
		}

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode ORC() {
			return getToken(ItemDatasParser.ORC, 0);
		}

		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifier_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIdentifier_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1484);
				_la = _input.LA(1);
				if (!(_la == T__10 || ((((_la - 216)) & ~0x3f) == 0 && ((1L << (_la - 216)) & ((1L << (NONE - 216)) | (1L << (ORC - 216)) | (1L << (BOW - 216)) | (1L << (DAGGER - 216)) | (1L << (RAPIER - 216)) | (1L << (CROSSBOW - 216)))) != 0) || ((((_la - 282)) & ~0x3f) == 0 && ((1L << (_la - 282)) & ((1L << (GLOVES - 282)) | (1L << (STEEL - 282)) | (1L << (LEATHER - 282)) | (1L << (ORIHARUKON - 282)) | (1L << (NAME - 282)))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = _input.getText(_localctx.start, _input.LT(-1));
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bool_objectContext extends ParserRuleContext {
		public boolean value;

		public TerminalNode BOOLEAN() {
			return getToken(ItemDatasParser.BOOLEAN, 0);
		}

		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_bool_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterBool_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1486);
				match(BOOLEAN);
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = _input.getText(_localctx.start, _input.LT(-1)).equals("1");
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Byte_objectContext extends ParserRuleContext {
		public byte value;

		public TerminalNode BOOLEAN() {
			return getToken(ItemDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(ItemDatasParser.INTEGER, 0);
		}

		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_byte_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterByte_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1488);
				_la = _input.LA(1);
				if (!(_la == BOOLEAN || _la == INTEGER)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Byte.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_objectContext extends ParserRuleContext {
		public int value;

		public TerminalNode BOOLEAN() {
			return getToken(ItemDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(ItemDatasParser.INTEGER, 0);
		}

		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_int_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterInt_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1490);
				_la = _input.LA(1);
				if (!(_la == BOOLEAN || _la == INTEGER)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Integer.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Long_objectContext extends ParserRuleContext {
		public long value;

		public TerminalNode BOOLEAN() {
			return getToken(ItemDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(ItemDatasParser.INTEGER, 0);
		}

		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_long_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterLong_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1492);
				_la = _input.LA(1);
				if (!(_la == BOOLEAN || _la == INTEGER)) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Long.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Double_objectContext extends ParserRuleContext {
		public double value;

		public TerminalNode BOOLEAN() {
			return getToken(ItemDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(ItemDatasParser.INTEGER, 0);
		}

		public TerminalNode DOUBLE() {
			return getToken(ItemDatasParser.DOUBLE, 0);
		}

		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_double_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDouble_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1494);
				_la = _input.LA(1);
				if (!(((((_la - 206)) & ~0x3f) == 0 && ((1L << (_la - 206)) & ((1L << (BOOLEAN - 206)) | (1L << (INTEGER - 206)) | (1L << (DOUBLE - 206)))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Double.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_objectContext extends ParserRuleContext {
		public String value;

		public TerminalNode BOOLEAN() {
			return getToken(ItemDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(ItemDatasParser.INTEGER, 0);
		}

		public TerminalNode DOUBLE() {
			return getToken(ItemDatasParser.DOUBLE, 0);
		}

		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_string_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterString_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1496);
				_la = _input.LA(1);
				if (!(((((_la - 206)) & ~0x3f) == 0 && ((1L << (_la - 206)) & ((1L << (BOOLEAN - 206)) | (1L << (INTEGER - 206)) | (1L << (DOUBLE - 206)))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					if (_input.LA(1) == Token.EOF) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = _input.getText(_localctx.start, _input.LT(-1));
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_objectContext extends ParserRuleContext {
		public String value;
		public Identifier_objectContext io;

		public Identifier_objectContext identifier_object() {
			return getRuleContext(Identifier_objectContext.class, 0);
		}

		public Name_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_name_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterName_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1498);
				match(T__126);
				setState(1499);
				((Name_objectContext) _localctx).io = identifier_object();
				setState(1500);
				match(T__127);
				_localctx.value = ((Name_objectContext) _localctx).io.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Category_objectContext extends ParserRuleContext {
		public TerminalNode CATEGORY() {
			return getToken(ItemDatasParser.CATEGORY, 0);
		}

		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_category_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCategory_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1503);
				match(CATEGORY);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Vector3D_objectContext extends ParserRuleContext {
		public Vector3D value;
		public Int_objectContext x;
		public Int_objectContext y;
		public Int_objectContext z;

		public List<TerminalNode> SEMICOLON() {
			return getTokens(ItemDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(ItemDatasParser.SEMICOLON, i);
		}

		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}

		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class, i);
		}

		public Vector3D_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_vector3D_object;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterVector3D_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 286, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1505);
				match(T__4);
				setState(1506);
				((Vector3D_objectContext) _localctx).x = int_object();
				setState(1507);
				match(SEMICOLON);
				setState(1508);
				((Vector3D_objectContext) _localctx).y = int_object();
				setState(1509);
				match(SEMICOLON);
				setState(1510);
				((Vector3D_objectContext) _localctx).z = int_object();
				setState(1511);
				match(T__5);
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = new Vector3D(((Vector3D_objectContext) _localctx).x.value, ((Vector3D_objectContext) _localctx).y.value, ((Vector3D_objectContext) _localctx).z.value);
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Empty_listContext extends ParserRuleContext {
		public List<String> value;

		public Empty_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_empty_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterEmpty_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 288, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1513);
				match(T__4);
				setState(1514);
				match(T__5);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Identifier_listContext extends ParserRuleContext {
		public List<String> value;
		public Identifier_objectContext io;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<Identifier_objectContext> identifier_object() {
			return getRuleContexts(Identifier_objectContext.class);
		}

		public Identifier_objectContext identifier_object(int i) {
			return getRuleContext(Identifier_objectContext.class, i);
		}

		public List<TerminalNode> SEMICOLON() {
			return getTokens(ItemDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(ItemDatasParser.SEMICOLON, i);
		}

		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_identifier_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterIdentifier_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 290, RULE_identifier_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(1531);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(1516);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(1517);
					match(T__4);
					setState(1518);
					((Identifier_listContext) _localctx).io = identifier_object();
					_localctx.value.add(((Identifier_listContext) _localctx).io.value);
					setState(1526);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(1520);
								match(SEMICOLON);
								setState(1521);
								((Identifier_listContext) _localctx).io = identifier_object();
								_localctx.value.add(((Identifier_listContext) _localctx).io.value);
							}
						}
						setState(1528);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1529);
					match(T__5);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_listContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_objectContext io;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}

		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class, i);
		}

		public List<TerminalNode> SEMICOLON() {
			return getTokens(ItemDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(ItemDatasParser.SEMICOLON, i);
		}

		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_int_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterInt_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 292, RULE_int_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(1548);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 29, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(1533);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(1534);
					match(T__4);
					setState(1535);
					((Int_listContext) _localctx).io = int_object();
					_localctx.value.add(((Int_listContext) _localctx).io.value);
					setState(1543);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(1537);
								match(SEMICOLON);
								setState(1538);
								((Int_listContext) _localctx).io = int_object();
								_localctx.value.add(((Int_listContext) _localctx).io.value);
							}
						}
						setState(1545);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1546);
					match(T__5);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Double_listContext extends ParserRuleContext {
		public List<Double> value;
		public Double_objectContext d;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<Double_objectContext> double_object() {
			return getRuleContexts(Double_objectContext.class);
		}

		public Double_objectContext double_object(int i) {
			return getRuleContext(Double_objectContext.class, i);
		}

		public List<TerminalNode> SEMICOLON() {
			return getTokens(ItemDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(ItemDatasParser.SEMICOLON, i);
		}

		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_double_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterDouble_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 294, RULE_double_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(1565);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 31, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(1550);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(1551);
					match(T__4);
					setState(1552);
					((Double_listContext) _localctx).d = double_object();
					_localctx.value.add(Double.valueOf((((Double_listContext) _localctx).d != null ? _input.getText(((Double_listContext) _localctx).d.start, ((Double_listContext) _localctx).d.stop) : null)));
					setState(1560);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(1554);
								match(SEMICOLON);
								setState(1555);
								((Double_listContext) _localctx).d = double_object();
								_localctx.value.add(Double.valueOf((((Double_listContext) _localctx).d != null ? _input.getText(((Double_listContext) _localctx).d.start, ((Double_listContext) _localctx).d.stop) : null)));
							}
						}
						setState(1562);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1563);
					match(T__5);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attribute_attackContext extends ParserRuleContext {
		public AttributeAttack value;
		public Attack_attributeContext aa;

		public Attack_attributeContext attack_attribute() {
			return getRuleContext(Attack_attributeContext.class, 0);
		}

		public Base_attribute_attackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_base_attribute_attack;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterBase_attribute_attack(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 296, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1567);
				match(T__128);
				setState(1568);
				match(T__3);
				setState(1569);
				((Base_attribute_attackContext) _localctx).aa = attack_attribute();
				_localctx.value = ((Base_attribute_attackContext) _localctx).aa.value;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attack_attributeContext extends ParserRuleContext {
		public AttributeAttack value;
		public AttributeContext attribute;
		public Int_objectContext io;

		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class, 0);
		}

		public TerminalNode SEMICOLON() {
			return getToken(ItemDatasParser.SEMICOLON, 0);
		}

		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attack_attribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterAttack_attribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 298, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(1572);
				match(T__4);
				setState(1573);
				((Attack_attributeContext) _localctx).attribute = attribute();
				setState(1574);
				match(SEMICOLON);
				setState(1575);
				((Attack_attributeContext) _localctx).io = int_object();
				setState(1576);
				match(T__5);
				_localctx.value = new AttributeAttack(((Attack_attributeContext) _localctx).attribute.value, ((Attack_attributeContext) _localctx).io.value);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public AttributeType value;

		public TerminalNode NONE() {
			return getToken(ItemDatasParser.NONE, 0);
		}

		public TerminalNode FIRE() {
			return getToken(ItemDatasParser.FIRE, 0);
		}

		public TerminalNode WATER() {
			return getToken(ItemDatasParser.WATER, 0);
		}

		public TerminalNode EARTH() {
			return getToken(ItemDatasParser.EARTH, 0);
		}

		public TerminalNode WIND() {
			return getToken(ItemDatasParser.WIND, 0);
		}

		public TerminalNode HOLY() {
			return getToken(ItemDatasParser.HOLY, 0);
		}

		public TerminalNode UNHOLY() {
			return getToken(ItemDatasParser.UNHOLY, 0);
		}

		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_attribute;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 300, RULE_attribute);
		try {
			setState(1593);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(1579);
					match(NONE);
					_localctx.value = AttributeType.NONE;
				}
				break;
				case FIRE:
					enterOuterAlt(_localctx, 2);
				{
					setState(1581);
					match(FIRE);
					_localctx.value = AttributeType.FIRE;
				}
				break;
				case WATER:
					enterOuterAlt(_localctx, 3);
				{
					setState(1583);
					match(WATER);
					_localctx.value = AttributeType.WATER;
				}
				break;
				case EARTH:
					enterOuterAlt(_localctx, 4);
				{
					setState(1585);
					match(EARTH);
					_localctx.value = AttributeType.EARTH;
				}
				break;
				case WIND:
					enterOuterAlt(_localctx, 5);
				{
					setState(1587);
					match(WIND);
					_localctx.value = AttributeType.WIND;
				}
				break;
				case HOLY:
					enterOuterAlt(_localctx, 6);
				{
					setState(1589);
					match(HOLY);
					_localctx.value = AttributeType.HOLY;
				}
				break;
				case UNHOLY:
					enterOuterAlt(_localctx, 7);
				{
					setState(1591);
					match(UNHOLY);
					_localctx.value = AttributeType.UNHOLY;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Category_listContext extends ParserRuleContext {
		public List<String> value;
		public Category_objectContext co;

		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class, 0);
		}

		public List<Category_objectContext> category_object() {
			return getRuleContexts(Category_objectContext.class);
		}

		public Category_objectContext category_object(int i) {
			return getRuleContext(Category_objectContext.class, i);
		}

		public List<TerminalNode> SEMICOLON() {
			return getTokens(ItemDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(ItemDatasParser.SEMICOLON, i);
		}

		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_category_list;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).enterCategory_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof ItemDatasListener) ((ItemDatasListener) listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 302, RULE_category_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(1610);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(1595);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(1596);
					match(T__4);
					setState(1597);
					((Category_listContext) _localctx).co = category_object();
					_localctx.value.add((((Category_listContext) _localctx).co != null ? _input.getText(((Category_listContext) _localctx).co.start, ((Category_listContext) _localctx).co.stop) : null));
					setState(1605);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(1599);
								match(SEMICOLON);
								setState(1600);
								((Category_listContext) _localctx).co = category_object();
								_localctx.value.add((((Category_listContext) _localctx).co != null ? _input.getText(((Category_listContext) _localctx).co.start, ((Category_listContext) _localctx).co.stop) : null));
							}
						}
						setState(1607);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(1608);
					match(T__5);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u014b\u064f\4\2\t" +
					"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
					"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
					"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t=" +
					"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I" +
					"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT" +
					"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4" +
					"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t" +
					"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4" +
					"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080" +
					"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085" +
					"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089" +
					"\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d\4\u008e" +
					"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092\t\u0092" +
					"\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096\4\u0097" +
					"\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\3\2\3\2\6\2\u0135\n\2\r\2\16" +
					"\2\u0136\3\3\3\3\3\3\3\3\5\3\u013d\n\3\3\3\5\3\u0140\n\3\3\3\5\3\u0143" +
					"\n\3\3\3\5\3\u0146\n\3\3\3\5\3\u0149\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5" +
					"\3\u0152\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3" +
					"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t" +
					"\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3" +
					"\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21" +
					"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24" +
					"\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31" +
					"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32" +
					"\7\32\u0211\n\32\f\32\16\32\u0214\13\32\3\32\3\32\5\32\u0218\n\32\3\33" +
					"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0224\n\33\f\33\16" +
					"\33\u0227\13\33\3\33\3\33\5\33\u022b\n\33\3\34\3\34\3\34\3\34\3\34\3\35" +
					"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35" +
					"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35" +
					"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35" +
					"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35" +
					"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35" +
					"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0284\n\35" +
					"\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 " +
					"\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3$\3" +
					"$\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3" +
					"(\3(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3" +
					",\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3" +
					"\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3" +
					"\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\66\3\66\3\66\3" +
					"\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\39\39\39\3" +
					"9\39\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3<\3<\3<\3<\3=\3=\3=\3=\3=\3>\3>\3" +
					">\3>\3>\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3B\3B\3B\3B\3B\3" +
					"C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3E\3E\3E\3E\3E\3F\3F\3F\3F\3F\3G\3G\3G\3" +
					"G\3G\3H\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3" +
					"I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\5I\u0386\nI\3J\3" +
					"J\3J\3J\3J\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3N\3N\3N\3N\3" +
					"N\3O\3O\3O\3O\3O\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3R\3S\3S\3" +
					"S\3S\3S\3T\3T\3T\3T\3T\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3" +
					"X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3\\\3\\\3\\" +
					"\3\\\3\\\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3_\3_\3_\3_\3`\3`\3`\3`\3`\3a\3" +
					"a\3a\3a\3a\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3d\3d\3d\3d\3d\3e\3e\3e\3e\3" +
					"e\3f\3f\3f\3f\3f\3g\3g\3g\3g\3g\3h\3h\3h\3h\3h\3h\3h\3h\3h\3h\7h\u0427" +
					"\nh\fh\16h\u042a\13h\3h\3h\5h\u042e\nh\3i\3i\3i\3i\3i\3i\3i\3i\3i\3i\3" +
					"j\3j\3j\3j\3j\3k\3k\3k\3k\3k\3l\3l\3l\3l\3l\3m\3m\3m\3m\3m\3n\3n\3n\3" +
					"n\3n\3o\3o\3o\3o\3o\3o\3o\3o\5o\u045b\no\3p\3p\3p\3p\3p\3q\3q\3q\3q\3" +
					"q\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\3r\5r\u047b" +
					"\nr\3s\3s\3s\3s\3s\3t\3t\3t\3t\3t\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u" +
					"\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u" +
					"\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\3u\5u\u04bd\nu" +
					"\3v\3v\3v\3v\3v\3w\3w\3w\3w\3w\3w\5w\u04ca\nw\3x\3x\3x\3x\3x\3y\3y\3y" +
					"\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y" +
					"\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y" +
					"\3y\3y\3y\5y\u0505\ny\3z\3z\3z\3z\3z\3{\3{\3{\3{\3{\3|\3|\3|\3|\3|\3}" +
					"\3}\3}\3}\3}\3~\3~\3~\3~\3~\3\177\3\177\3\177\3\177\3\177\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\5\u0080" +
					"\u0565\n\u0080\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0082\3\u0082" +
					"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\5\u0082" +
					"\u0576\n\u0082\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083" +
					"\3\u0083\3\u0083\5\u0083\u0581\n\u0083\3\u0083\3\u0083\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\5\u0084\u05b7\n\u0084\3\u0085\3\u0085\3\u0085" +
					"\3\u0085\3\u0085\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086" +
					"\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\5\u0086\u05ca\n\u0086\3\u0087" +
					"\3\u0087\3\u0087\3\u0088\3\u0088\3\u0089\3\u0089\3\u008a\3\u008a\3\u008b" +
					"\3\u008b\3\u008c\3\u008c\3\u008d\3\u008d\3\u008e\3\u008e\3\u008f\3\u008f" +
					"\3\u008f\3\u008f\3\u008f\3\u0090\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091" +
					"\3\u0091\3\u0091\3\u0091\3\u0091\3\u0092\3\u0092\3\u0092\3\u0093\3\u0093" +
					"\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\7\u0093\u05f7\n\u0093" +
					"\f\u0093\16\u0093\u05fa\13\u0093\3\u0093\3\u0093\5\u0093\u05fe\n\u0093" +
					"\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\7\u0094" +
					"\u0608\n\u0094\f\u0094\16\u0094\u060b\13\u0094\3\u0094\3\u0094\5\u0094" +
					"\u060f\n\u0094\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095" +
					"\3\u0095\7\u0095\u0619\n\u0095\f\u0095\16\u0095\u061c\13\u0095\3\u0095" +
					"\3\u0095\5\u0095\u0620\n\u0095\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096" +
					"\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0098\3\u0098" +
					"\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098" +
					"\3\u0098\3\u0098\3\u0098\5\u0098\u063c\n\u0098\3\u0099\3\u0099\3\u0099" +
					"\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\7\u0099\u0646\n\u0099\f\u0099" +
					"\16\u0099\u0649\13\u0099\3\u0099\3\u0099\5\u0099\u064d\n\u0099\3\u0099" +
					"\2\2\u009a\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66" +
					"8:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a" +
					"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2" +
					"\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba" +
					"\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2" +
					"\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea" +
					"\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102" +
					"\u0104\u0106\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118\u011a" +
					"\u011c\u011e\u0120\u0122\u0124\u0126\u0128\u012a\u012c\u012e\u0130\2\5" +
					"\16\2\r\r\u00da\u00da\u00ef\u00ef\u0103\u0103\u0105\u0105\u010a\u010a" +
					"\u010c\u010c\u011c\u011c\u012d\u012d\u0131\u0131\u0134\u0134\u0148\u0148" +
					"\3\2\u00d0\u00d1\3\2\u00d0\u00d2\2\u067c\2\u0134\3\2\2\2\4\u0138\3\2\2" +
					"\2\6\u015b\3\2\2\2\b\u0161\3\2\2\2\n\u0165\3\2\2\2\f\u0169\3\2\2\2\16" +
					"\u016d\3\2\2\2\20\u0171\3\2\2\2\22\u0175\3\2\2\2\24\u0179\3\2\2\2\26\u017d" +
					"\3\2\2\2\30\u0181\3\2\2\2\32\u0185\3\2\2\2\34\u0189\3\2\2\2\36\u018d\3" +
					"\2\2\2 \u0191\3\2\2\2\"\u0195\3\2\2\2$\u0199\3\2\2\2&\u019d\3\2\2\2(\u01a1" +
					"\3\2\2\2*\u01a5\3\2\2\2,\u01f7\3\2\2\2.\u01fc\3\2\2\2\60\u0201\3\2\2\2" +
					"\62\u0206\3\2\2\2\64\u0219\3\2\2\2\66\u022c\3\2\2\28\u0231\3\2\2\2:\u0287" +
					"\3\2\2\2<\u028c\3\2\2\2>\u0291\3\2\2\2@\u0296\3\2\2\2B\u029b\3\2\2\2D" +
					"\u02a0\3\2\2\2F\u02a5\3\2\2\2H\u02aa\3\2\2\2J\u02af\3\2\2\2L\u02b4\3\2" +
					"\2\2N\u02bb\3\2\2\2P\u02c0\3\2\2\2R\u02c5\3\2\2\2T\u02ce\3\2\2\2V\u02d3" +
					"\3\2\2\2X\u02d8\3\2\2\2Z\u02dd\3\2\2\2\\\u02e2\3\2\2\2^\u02e7\3\2\2\2" +
					"`\u02ec\3\2\2\2b\u02f1\3\2\2\2d\u02f6\3\2\2\2f\u02fb\3\2\2\2h\u0300\3" +
					"\2\2\2j\u0303\3\2\2\2l\u0308\3\2\2\2n\u030f\3\2\2\2p\u0314\3\2\2\2r\u0319" +
					"\3\2\2\2t\u031e\3\2\2\2v\u0323\3\2\2\2x\u0327\3\2\2\2z\u032c\3\2\2\2|" +
					"\u0331\3\2\2\2~\u0336\3\2\2\2\u0080\u033b\3\2\2\2\u0082\u0340\3\2\2\2" +
					"\u0084\u0345\3\2\2\2\u0086\u034a\3\2\2\2\u0088\u034f\3\2\2\2\u008a\u0354" +
					"\3\2\2\2\u008c\u0359\3\2\2\2\u008e\u035e\3\2\2\2\u0090\u0385\3\2\2\2\u0092" +
					"\u0387\3\2\2\2\u0094\u038c\3\2\2\2\u0096\u0391\3\2\2\2\u0098\u0396\3\2" +
					"\2\2\u009a\u039b\3\2\2\2\u009c\u03a0\3\2\2\2\u009e\u03a5\3\2\2\2\u00a0" +
					"\u03aa\3\2\2\2\u00a2\u03af\3\2\2\2\u00a4\u03b4\3\2\2\2\u00a6\u03b9\3\2" +
					"\2\2\u00a8\u03be\3\2\2\2\u00aa\u03c3\3\2\2\2\u00ac\u03c8\3\2\2\2\u00ae" +
					"\u03cd\3\2\2\2\u00b0\u03d2\3\2\2\2\u00b2\u03d7\3\2\2\2\u00b4\u03dc\3\2" +
					"\2\2\u00b6\u03e1\3\2\2\2\u00b8\u03e6\3\2\2\2\u00ba\u03eb\3\2\2\2\u00bc" +
					"\u03f0\3\2\2\2\u00be\u03f4\3\2\2\2\u00c0\u03f9\3\2\2\2\u00c2\u03fe\3\2" +
					"\2\2\u00c4\u0403\3\2\2\2\u00c6\u0408\3\2\2\2\u00c8\u040d\3\2\2\2\u00ca" +
					"\u0412\3\2\2\2\u00cc\u0417\3\2\2\2\u00ce\u041c\3\2\2\2\u00d0\u042f\3\2" +
					"\2\2\u00d2\u0439\3\2\2\2\u00d4\u043e\3\2\2\2\u00d6\u0443\3\2\2\2\u00d8" +
					"\u0448\3\2\2\2\u00da\u044d\3\2\2\2\u00dc\u0452\3\2\2\2\u00de\u045c\3\2" +
					"\2\2\u00e0\u0461\3\2\2\2\u00e2\u047a\3\2\2\2\u00e4\u047c\3\2\2\2\u00e6" +
					"\u0481\3\2\2\2\u00e8\u04bc\3\2\2\2\u00ea\u04be\3\2\2\2\u00ec\u04c9\3\2" +
					"\2\2\u00ee\u04cb\3\2\2\2\u00f0\u0504\3\2\2\2\u00f2\u0506\3\2\2\2\u00f4" +
					"\u050b\3\2\2\2\u00f6\u0510\3\2\2\2\u00f8\u0515\3\2\2\2\u00fa\u051a\3\2" +
					"\2\2\u00fc\u051f\3\2\2\2\u00fe\u0564\3\2\2\2\u0100\u0566\3\2\2\2\u0102" +
					"\u0575\3\2\2\2\u0104\u0577\3\2\2\2\u0106\u05b6\3\2\2\2\u0108\u05b8\3\2" +
					"\2\2\u010a\u05c9\3\2\2\2\u010c\u05cb\3\2\2\2\u010e\u05ce\3\2\2\2\u0110" +
					"\u05d0\3\2\2\2\u0112\u05d2\3\2\2\2\u0114\u05d4\3\2\2\2\u0116\u05d6\3\2" +
					"\2\2\u0118\u05d8\3\2\2\2\u011a\u05da\3\2\2\2\u011c\u05dc\3\2\2\2\u011e" +
					"\u05e1\3\2\2\2\u0120\u05e3\3\2\2\2\u0122\u05eb\3\2\2\2\u0124\u05fd\3\2" +
					"\2\2\u0126\u060e\3\2\2\2\u0128\u061f\3\2\2\2\u012a\u0621\3\2\2\2\u012c" +
					"\u0626\3\2\2\2\u012e\u063b\3\2\2\2\u0130\u064c\3\2\2\2\u0132\u0135\5*" +
					"\26\2\u0133\u0135\5\4\3\2\u0134\u0132\3\2\2\2\u0134\u0133\3\2\2\2\u0135" +
					"\u0136\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\3\3\2\2\2" +
					"\u0138\u0139\7\3\2\2\u0139\u013a\5\u0114\u008b\2\u013a\u013c\5\6\4\2\u013b" +
					"\u013d\5\b\5\2\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013f\3\2" +
					"\2\2\u013e\u0140\5\f\7\2\u013f\u013e\3\2\2\2\u013f\u0140\3\2\2\2\u0140" +
					"\u0142\3\2\2\2\u0141\u0143\5\16\b\2\u0142\u0141\3\2\2\2\u0142\u0143\3" +
					"\2\2\2\u0143\u0145\3\2\2\2\u0144\u0146\5\n\6\2\u0145\u0144\3\2\2\2\u0145" +
					"\u0146\3\2\2\2\u0146\u0148\3\2\2\2\u0147\u0149\5\20\t\2\u0148\u0147\3" +
					"\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\5\22\n\2\u014b" +
					"\u014c\5\24\13\2\u014c\u014d\5\26\f\2\u014d\u0151\5\30\r\2\u014e\u014f" +
					"\5\32\16\2\u014f\u0150\5\34\17\2\u0150\u0152\3\2\2\2\u0151\u014e\3\2\2" +
					"\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\5\36\20\2\u0154" +
					"\u0155\5 \21\2\u0155\u0156\5\"\22\2\u0156\u0157\5$\23\2\u0157\u0158\5" +
					"&\24\2\u0158\u0159\5(\25\2\u0159\u015a\7\4\2\2\u015a\5\3\2\2\2\u015b\u015c" +
					"\7\5\2\2\u015c\u015d\7\6\2\2\u015d\u015e\7\7\2\2\u015e\u015f\5\u0114\u008b" +
					"\2\u015f\u0160\7\b\2\2\u0160\7\3\2\2\2\u0161\u0162\7\t\2\2\u0162\u0163" +
					"\7\6\2\2\u0163\u0164\5\u0126\u0094\2\u0164\t\3\2\2\2\u0165\u0166\7\n\2" +
					"\2\u0166\u0167\7\6\2\2\u0167\u0168\5\u0126\u0094\2\u0168\13\3\2\2\2\u0169" +
					"\u016a\7\13\2\2\u016a\u016b\7\6\2\2\u016b\u016c\5\u0126\u0094\2\u016c" +
					"\r\3\2\2\2\u016d\u016e\7\f\2\2\u016e\u016f\7\6\2\2\u016f\u0170\5\u0126" +
					"\u0094\2\u0170\17\3\2\2\2\u0171\u0172\7\r\2\2\u0172\u0173\7\6\2\2\u0173" +
					"\u0174\5\u0126\u0094\2\u0174\21\3\2\2\2\u0175\u0176\7\16\2\2\u0176\u0177" +
					"\7\6\2\2\u0177\u0178\5\u011c\u008f\2\u0178\23\3\2\2\2\u0179\u017a\7\17" +
					"\2\2\u017a\u017b\7\6\2\2\u017b\u017c\5\u011c\u008f\2\u017c\25\3\2\2\2" +
					"\u017d\u017e\7\20\2\2\u017e\u017f\7\6\2\2\u017f\u0180\5\u011c\u008f\2" +
					"\u0180\27\3\2\2\2\u0181\u0182\7\21\2\2\u0182\u0183\7\6\2\2\u0183\u0184" +
					"\5\u011c\u008f\2\u0184\31\3\2\2\2\u0185\u0186\7\22\2\2\u0186\u0187\7\6" +
					"\2\2\u0187\u0188\5\u0114\u008b\2\u0188\33\3\2\2\2\u0189\u018a\7\23\2\2" +
					"\u018a\u018b\7\6\2\2\u018b\u018c\5\u011c\u008f\2\u018c\35\3\2\2\2\u018d" +
					"\u018e\7\24\2\2\u018e\u018f\7\6\2\2\u018f\u0190\5\u0126\u0094\2\u0190" +
					"\37\3\2\2\2\u0191\u0192\7\25\2\2\u0192\u0193\7\6\2\2\u0193\u0194\5\u0126" +
					"\u0094\2\u0194!\3\2\2\2\u0195\u0196\7\26\2\2\u0196\u0197\7\6\2\2\u0197" +
					"\u0198\5\u0126\u0094\2\u0198#\3\2\2\2\u0199\u019a\7\27\2\2\u019a\u019b" +
					"\7\6\2\2\u019b\u019c\5\u0126\u0094\2\u019c%\3\2\2\2\u019d\u019e\7\30\2" +
					"\2\u019e\u019f\7\6\2\2\u019f\u01a0\5\u0126\u0094\2\u01a0\'\3\2\2\2\u01a1" +
					"\u01a2\7\31\2\2\u01a2\u01a3\7\6\2\2\u01a3\u01a4\5\u0126\u0094\2\u01a4" +
					")\3\2\2\2\u01a5\u01a6\7\32\2\2\u01a6\u01a7\5\u010a\u0086\2\u01a7\u01a8" +
					"\5\u010c\u0087\2\u01a8\u01a9\5\u011c\u008f\2\u01a9\u01aa\5\u0108\u0085" +
					"\2\u01aa\u01ab\5\u0104\u0083\2\u01ab\u01ac\5\u0100\u0081\2\u01ac\u01ad" +
					"\5\u00fc\177\2\u01ad\u01ae\5\u00fa~\2\u01ae\u01af\5\u00f8}\2\u01af\u01b0" +
					"\5\u00f2z\2\u01b0\u01b1\5\u00f4{\2\u01b1\u01b2\5\u00f6|\2\u01b2\u01b3" +
					"\5\u00eex\2\u01b3\u01b4\5\u00eav\2\u01b4\u01b5\5\u00b4[\2\u01b5\u01b6" +
					"\5\u00b6\\\2\u01b6\u01b7\5\u00b8]\2\u01b7\u01b8\5\u00ba^\2\u01b8\u01b9" +
					"\5\u00bc_\2\u01b9\u01ba\5\u00be`\2\u01ba\u01bb\5\u00c0a\2\u01bb\u01bc" +
					"\5\u00c2b\2\u01bc\u01bd\5\u00c6d\2\u01bd\u01be\5\u00c8e\2\u01be\u01bf" +
					"\5\u00c4c\2\u01bf\u01c0\5\u00caf\2\u01c0\u01c1\5\u00ccg\2\u01c1\u01c2" +
					"\5\u00d2j\2\u01c2\u01c3\5\u00d4k\2\u01c3\u01c4\5\u00d6l\2\u01c4\u01c5" +
					"\5\u00d8m\2\u01c5\u01c6\5\u00dan\2\u01c6\u01c7\5\u00dco\2\u01c7\u01c8" +
					"\5\u00dep\2\u01c8\u01c9\5\u00ceh\2\u01c9\u01ca\5\u00e6t\2\u01ca\u01cb" +
					"\5\u00e0q\2\u01cb\u01cc\5\u00e4s\2\u01cc\u01cd\5\u0092J\2\u01cd\u01ce" +
					"\5\u0094K\2\u01ce\u01cf\5\u0096L\2\u01cf\u01d0\5\u0098M\2\u01d0\u01d1" +
					"\5\u009aN\2\u01d1\u01d2\5\u009cO\2\u01d2\u01d3\5\u009eP\2\u01d3\u01d4" +
					"\5\u008eH\2\u01d4\u01d5\5\u00a0Q\2\u01d5\u01d6\5\u00a2R\2\u01d6\u01d7" +
					"\5\u00aaV\2\u01d7\u01d8\5\u00acW\2\u01d8\u01d9\5\u00aeX\2\u01d9\u01da" +
					"\5\u00b0Y\2\u01da\u01db\5\u00a4S\2\u01db\u01dc\5\u00a6T\2\u01dc\u01dd" +
					"\5\u00a8U\2\u01dd\u01de\5\u00b2Z\2\u01de\u01df\5~@\2\u01df\u01e0\5\u0080" +
					"A\2\u01e0\u01e1\5\u0082B\2\u01e1\u01e2\5\u0084C\2\u01e2\u01e3\5\u0088" +
					"E\2\u01e3\u01e4\5\u008aF\2\u01e4\u01e5\5\u008cG\2\u01e5\u01e6\5v<\2\u01e6" +
					"\u01e7\5|?\2\u01e7\u01e8\5\u012a\u0096\2\u01e8\u01e9\5t;\2\u01e9\u01ea" +
					"\5r:\2\u01ea\u01eb\5\u0086D\2\u01eb\u01ec\5x=\2\u01ec\u01ed\5z>\2\u01ed" +
					"\u01ee\5p9\2\u01ee\u01ef\5n8\2\u01ef\u01f0\5\66\34\2\u01f0\u01f1\5\62" +
					"\32\2\u01f1\u01f2\5\64\33\2\u01f2\u01f3\5\60\31\2\u01f3\u01f4\5,\27\2" +
					"\u01f4\u01f5\5.\30\2\u01f5\u01f6\7\33\2\2\u01f6+\3\2\2\2\u01f7\u01f8\7" +
					"\34\2\2\u01f8\u01f9\7\6\2\2\u01f9\u01fa\5\u0110\u0089\2\u01fa\u01fb\b" +
					"\27\1\2\u01fb-\3\2\2\2\u01fc\u01fd\7\35\2\2\u01fd\u01fe\7\6\2\2\u01fe" +
					"\u01ff\5\u0110\u0089\2\u01ff\u0200\b\30\1\2\u0200/\3\2\2\2\u0201\u0202" +
					"\7\36\2\2\u0202\u0203\7\6\2\2\u0203\u0204\5\u0110\u0089\2\u0204\u0205" +
					"\b\31\1\2\u0205\61\3\2\2\2\u0206\u0207\7\37\2\2\u0207\u0217\7\6\2\2\u0208" +
					"\u0218\5\u0122\u0092\2\u0209\u020a\7\7\2\2\u020a\u020b\58\35\2\u020b\u0212" +
					"\b\32\1\2\u020c\u020d\7\u00d9\2\2\u020d\u020e\58\35\2\u020e\u020f\b\32" +
					"\1\2\u020f\u0211\3\2\2\2\u0210\u020c\3\2\2\2\u0211\u0214\3\2\2\2\u0212" +
					"\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0215\3\2\2\2\u0214\u0212\3\2" +
					"\2\2\u0215\u0216\7\b\2\2\u0216\u0218\3\2\2\2\u0217\u0208\3\2\2\2\u0217" +
					"\u0209\3\2\2\2\u0218\63\3\2\2\2\u0219\u021a\7 \2\2\u021a\u022a\7\6\2\2" +
					"\u021b\u022b\5\u0122\u0092\2\u021c\u021d\7\7\2\2\u021d\u021e\58\35\2\u021e" +
					"\u0225\b\33\1\2\u021f\u0220\7\u00d9\2\2\u0220\u0221\58\35\2\u0221\u0222" +
					"\b\33\1\2\u0222\u0224\3\2\2\2\u0223\u021f\3\2\2\2\u0224\u0227\3\2\2\2" +
					"\u0225\u0223\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0228\3\2\2\2\u0227\u0225" +
					"\3\2\2\2\u0228\u0229\7\b\2\2\u0229\u022b\3\2\2\2\u022a\u021b\3\2\2\2\u022a" +
					"\u021c\3\2\2\2\u022b\65\3\2\2\2\u022c\u022d\7!\2\2\u022d\u022e\7\6\2\2" +
					"\u022e\u022f\5\u0124\u0093\2\u022f\u0230\b\34\1\2\u0230\67\3\2\2\2\u0231" +
					"\u0283\7\7\2\2\u0232\u0233\5:\36\2\u0233\u0234\b\35\1\2\u0234\u0284\3" +
					"\2\2\2\u0235\u0236\5> \2\u0236\u0237\b\35\1\2\u0237\u0284\3\2\2\2\u0238" +
					"\u0239\5@!\2\u0239\u023a\b\35\1\2\u023a\u0284\3\2\2\2\u023b\u023c\5B\"" +
					"\2\u023c\u023d\b\35\1\2\u023d\u0284\3\2\2\2\u023e\u023f\5<\37\2\u023f" +
					"\u0240\b\35\1\2\u0240\u0284\3\2\2\2\u0241\u0242\5D#\2\u0242\u0243\b\35" +
					"\1\2\u0243\u0284\3\2\2\2\u0244\u0245\5F$\2\u0245\u0246\b\35\1\2\u0246" +
					"\u0284\3\2\2\2\u0247\u0248\5H%\2\u0248\u0249\b\35\1\2\u0249\u0284\3\2" +
					"\2\2\u024a\u024b\5J&\2\u024b\u024c\b\35\1\2\u024c\u0284\3\2\2\2\u024d" +
					"\u024e\5L\'\2\u024e\u024f\b\35\1\2\u024f\u0284\3\2\2\2\u0250\u0251\5N" +
					"(\2\u0251\u0252\b\35\1\2\u0252\u0284\3\2\2\2\u0253\u0254\5P)\2\u0254\u0255" +
					"\b\35\1\2\u0255\u0284\3\2\2\2\u0256\u0257\5R*\2\u0257\u0258\b\35\1\2\u0258" +
					"\u0284\3\2\2\2\u0259\u025a\5T+\2\u025a\u025b\b\35\1\2\u025b\u0284\3\2" +
					"\2\2\u025c\u025d\5V,\2\u025d\u025e\b\35\1\2\u025e\u0284\3\2\2\2\u025f" +
					"\u0260\5X-\2\u0260\u0261\b\35\1\2\u0261\u0284\3\2\2\2\u0262\u0263\5Z." +
					"\2\u0263\u0264\b\35\1\2\u0264\u0284\3\2\2\2\u0265\u0266\5\\/\2\u0266\u0267" +
					"\b\35\1\2\u0267\u0284\3\2\2\2\u0268\u0269\5^\60\2\u0269\u026a\b\35\1\2" +
					"\u026a\u0284\3\2\2\2\u026b\u026c\5`\61\2\u026c\u026d\b\35\1\2\u026d\u0284" +
					"\3\2\2\2\u026e\u026f\5b\62\2\u026f\u0270\b\35\1\2\u0270\u0284\3\2\2\2" +
					"\u0271\u0272\5d\63\2\u0272\u0273\b\35\1\2\u0273\u0284\3\2\2\2\u0274\u0275" +
					"\5f\64\2\u0275\u0276\b\35\1\2\u0276\u0284\3\2\2\2\u0277\u0278\5h\65\2" +
					"\u0278\u0279\b\35\1\2\u0279\u0284\3\2\2\2\u027a\u027b\5j\66\2\u027b\u027c" +
					"\b\35\1\2\u027c\u0284\3\2\2\2\u027d\u027e\5l\67\2\u027e\u027f\b\35\1\2" +
					"\u027f\u0284\3\2\2\2\u0280\u0281\5\u010e\u0088\2\u0281\u0282\b\35\1\2" +
					"\u0282\u0284\3\2\2\2\u0283\u0232\3\2\2\2\u0283\u0235\3\2\2\2\u0283\u0238" +
					"\3\2\2\2\u0283\u023b\3\2\2\2\u0283\u023e\3\2\2\2\u0283\u0241\3\2\2\2\u0283" +
					"\u0244\3\2\2\2\u0283\u0247\3\2\2\2\u0283\u024a\3\2\2\2\u0283\u024d\3\2" +
					"\2\2\u0283\u0250\3\2\2\2\u0283\u0253\3\2\2\2\u0283\u0256\3\2\2\2\u0283" +
					"\u0259\3\2\2\2\u0283\u025c\3\2\2\2\u0283\u025f\3\2\2\2\u0283\u0262\3\2" +
					"\2\2\u0283\u0265\3\2\2\2\u0283\u0268\3\2\2\2\u0283\u026b\3\2\2\2\u0283" +
					"\u026e\3\2\2\2\u0283\u0271\3\2\2\2\u0283\u0274\3\2\2\2\u0283\u0277\3\2" +
					"\2\2\u0283\u027a\3\2\2\2\u0283\u027d\3\2\2\2\u0283\u0280\3\2\2\2\u0284" +
					"\u0285\3\2\2\2\u0285\u0286\7\b\2\2\u02869\3\2\2\2\u0287\u0288\7\"\2\2" +
					"\u0288\u0289\7\u00d9\2\2\u0289\u028a\5\u0126\u0094\2\u028a\u028b\b\36" +
					"\1\2\u028b;\3\2\2\2\u028c\u028d\7#\2\2\u028d\u028e\7\u00d9\2\2\u028e\u028f" +
					"\5\u0126\u0094\2\u028f\u0290\b\37\1\2\u0290=\3\2\2\2\u0291\u0292\7$\2" +
					"\2\u0292\u0293\7\u00d9\2\2\u0293\u0294\5\u0124\u0093\2\u0294\u0295\b " +
					"\1\2\u0295?\3\2\2\2\u0296\u0297\7%\2\2\u0297\u0298\7\u00d9\2\2\u0298\u0299" +
					"\5\u0130\u0099\2\u0299\u029a\b!\1\2\u029aA\3\2\2\2\u029b\u029c\7&\2\2" +
					"\u029c\u029d\7\u00d9\2\2\u029d\u029e\5\u0130\u0099\2\u029e\u029f\b\"\1" +
					"\2\u029fC\3\2\2\2\u02a0\u02a1\7\'\2\2\u02a1\u02a2\7\u00d9\2\2\u02a2\u02a3" +
					"\5\u0110\u0089\2\u02a3\u02a4\b#\1\2\u02a4E\3\2\2\2\u02a5\u02a6\7(\2\2" +
					"\u02a6\u02a7\7\u00d9\2\2\u02a7\u02a8\5\u0110\u0089\2\u02a8\u02a9\b$\1" +
					"\2\u02a9G\3\2\2\2\u02aa\u02ab\7)\2\2\u02ab\u02ac\7\u00d9\2\2\u02ac\u02ad" +
					"\5\u0114\u008b\2\u02ad\u02ae\b%\1\2\u02aeI\3\2\2\2\u02af\u02b0\7*\2\2" +
					"\u02b0\u02b1\7\u00d9\2\2\u02b1\u02b2\5\u0110\u0089\2\u02b2\u02b3\b&\1" +
					"\2\u02b3K\3\2\2\2\u02b4\u02b5\7+\2\2\u02b5\u02b6\7\u00d9\2\2\u02b6\u02b7" +
					"\7\7\2\2\u02b7\u02b8\5\u0114\u008b\2\u02b8\u02b9\7\b\2\2\u02b9\u02ba\b" +
					"\'\1\2\u02baM\3\2\2\2\u02bb\u02bc\7,\2\2\u02bc\u02bd\7\u00d9\2\2\u02bd" +
					"\u02be\5\u0110\u0089\2\u02be\u02bf\b(\1\2\u02bfO\3\2\2\2\u02c0\u02c1\7" +
					"-\2\2\u02c1\u02c2\7\u00d9\2\2\u02c2\u02c3\5\u0112\u008a\2\u02c3\u02c4" +
					"\b)\1\2\u02c4Q\3\2\2\2\u02c5\u02c6\7.\2\2\u02c6\u02c7\7\u00d9\2\2\u02c7" +
					"\u02c8\7\7\2\2\u02c8\u02c9\5\u0114\u008b\2\u02c9\u02ca\7\u00d9\2\2\u02ca" +
					"\u02cb\5\u0114\u008b\2\u02cb\u02cc\7\b\2\2\u02cc\u02cd\b*\1\2\u02cdS\3" +
					"\2\2\2\u02ce\u02cf\7/\2\2\u02cf\u02d0\7\u00d9\2\2\u02d0\u02d1\5\u0114" +
					"\u008b\2\u02d1\u02d2\b+\1\2\u02d2U\3\2\2\2\u02d3\u02d4\7\60\2\2\u02d4" +
					"\u02d5\7\u00d9\2\2\u02d5\u02d6\5\u0114\u008b\2\u02d6\u02d7\b,\1\2\u02d7" +
					"W\3\2\2\2\u02d8\u02d9\7\61\2\2\u02d9\u02da\7\u00d9\2\2\u02da\u02db\5\u0114" +
					"\u008b\2\u02db\u02dc\b-\1\2\u02dcY\3\2\2\2\u02dd\u02de\7\62\2\2\u02de" +
					"\u02df\7\u00d9\2\2\u02df\u02e0\5\u0110\u0089\2\u02e0\u02e1\b.\1\2\u02e1" +
					"[\3\2\2\2\u02e2\u02e3\7\63\2\2\u02e3\u02e4\7\u00d9\2\2\u02e4\u02e5\5\u0110" +
					"\u0089\2\u02e5\u02e6\b/\1\2\u02e6]\3\2\2\2\u02e7\u02e8\7\64\2\2\u02e8" +
					"\u02e9\7\u00d9\2\2\u02e9\u02ea\5\u0110\u0089\2\u02ea\u02eb\b\60\1\2\u02eb" +
					"_\3\2\2\2\u02ec\u02ed\7\65\2\2\u02ed\u02ee\7\u00d9\2\2\u02ee\u02ef\5\u0124" +
					"\u0093\2\u02ef\u02f0\b\61\1\2\u02f0a\3\2\2\2\u02f1\u02f2\7\66\2\2\u02f2" +
					"\u02f3\7\u00d9\2\2\u02f3\u02f4\5\u0126\u0094\2\u02f4\u02f5\b\62\1\2\u02f5" +
					"c\3\2\2\2\u02f6\u02f7\7\67\2\2\u02f7\u02f8\7\u00d9\2\2\u02f8\u02f9\5\u0126" +
					"\u0094\2\u02f9\u02fa\b\63\1\2\u02fae\3\2\2\2\u02fb\u02fc\78\2\2\u02fc" +
					"\u02fd\7\u00d9\2\2\u02fd\u02fe\5\u0110\u0089\2\u02fe\u02ff\b\64\1\2\u02ff" +
					"g\3\2\2\2\u0300\u0301\79\2\2\u0301\u0302\b\65\1\2\u0302i\3\2\2\2\u0303" +
					"\u0304\7:\2\2\u0304\u0305\7\u00d9\2\2\u0305\u0306\5\u0110\u0089\2\u0306" +
					"\u0307\b\66\1\2\u0307k\3\2\2\2\u0308\u0309\7;\2\2\u0309\u030a\7\u00d9" +
					"\2\2\u030a\u030b\7\7\2\2\u030b\u030c\5\u0114\u008b\2\u030c\u030d\7\b\2" +
					"\2\u030d\u030e\b\67\1\2\u030em\3\2\2\2\u030f\u0310\7<\2\2\u0310\u0311" +
					"\7\6\2\2\u0311\u0312\5\u0110\u0089\2\u0312\u0313\b8\1\2\u0313o\3\2\2\2" +
					"\u0314\u0315\7=\2\2\u0315\u0316\7\6\2\2\u0316\u0317\5\u0124\u0093\2\u0317" +
					"\u0318\b9\1\2\u0318q\3\2\2\2\u0319\u031a\7>\2\2\u031a\u031b\7\6\2\2\u031b" +
					"\u031c\5\u011c\u008f\2\u031c\u031d\b:\1\2\u031ds\3\2\2\2\u031e\u031f\7" +
					"?\2\2\u031f\u0320\7\6\2\2\u0320\u0321\5\u0126\u0094\2\u0321\u0322\b;\1" +
					"\2\u0322u\3\2\2\2\u0323\u0324\7@\2\2\u0324\u0325\7\6\2\2\u0325\u0326\5" +
					"\u0122\u0092\2\u0326w\3\2\2\2\u0327\u0328\7A\2\2\u0328\u0329\7\6\2\2\u0329" +
					"\u032a\5\u0114\u008b\2\u032a\u032b\b=\1\2\u032by\3\2\2\2\u032c\u032d\7" +
					"B\2\2\u032d\u032e\7\6\2\2\u032e\u032f\5\u0110\u0089\2\u032f\u0330\b>\1" +
					"\2\u0330{\3\2\2\2\u0331\u0332\7C\2\2\u0332\u0333\7\6\2\2\u0333\u0334\5" +
					"\u0114\u008b\2\u0334\u0335\b?\1\2\u0335}\3\2\2\2\u0336\u0337\7D\2\2\u0337" +
					"\u0338\7\6\2\2\u0338\u0339\5\u0114\u008b\2\u0339\u033a\b@\1\2\u033a\177" +
					"\3\2\2\2\u033b\u033c\7E\2\2\u033c\u033d\7\6\2\2\u033d\u033e\5\u0114\u008b" +
					"\2\u033e\u033f\bA\1\2\u033f\u0081\3\2\2\2\u0340\u0341\7F\2\2\u0341\u0342" +
					"\7\6\2\2\u0342\u0343\5\u0114\u008b\2\u0343\u0344\bB\1\2\u0344\u0083\3" +
					"\2\2\2\u0345\u0346\7G\2\2\u0346\u0347\7\6\2\2\u0347\u0348\5\u0110\u0089" +
					"\2\u0348\u0349\bC\1\2\u0349\u0085\3\2\2\2\u034a\u034b\7H\2\2\u034b\u034c" +
					"\7\6\2\2\u034c\u034d\5\u0110\u0089\2\u034d\u034e\bD\1\2\u034e\u0087\3" +
					"\2\2\2\u034f\u0350\7I\2\2\u0350\u0351\7\6\2\2\u0351\u0352\5\u0114\u008b" +
					"\2\u0352\u0353\bE\1\2\u0353\u0089\3\2\2\2\u0354\u0355\7J\2\2\u0355\u0356" +
					"\7\6\2\2\u0356\u0357\5\u0114\u008b\2\u0357\u0358\bF\1\2\u0358\u008b\3" +
					"\2\2\2\u0359\u035a\7K\2\2\u035a\u035b\7\6\2\2\u035b\u035c\5\u0114\u008b" +
					"\2\u035c\u035d\bG\1\2\u035d\u008d\3\2\2\2\u035e\u035f\7L\2\2\u035f\u0360" +
					"\7\6\2\2\u0360\u0361\5\u0090I\2\u0361\u0362\bH\1\2\u0362\u008f\3\2\2\2" +
					"\u0363\u0364\7\u00da\2\2\u0364\u0386\bI\1\2\u0365\u0366\7\u0101\2\2\u0366" +
					"\u0386\bI\1\2\u0367\u0368\7\u0102\2\2\u0368\u0386\bI\1\2\u0369\u036a\7" +
					"\u0103\2\2\u036a\u0386\bI\1\2\u036b\u036c\7\u0104\2\2\u036c\u0386\bI\1" +
					"\2\u036d\u036e\7\u0105\2\2\u036e\u0386\bI\1\2\u036f\u0370\7\u0106\2\2" +
					"\u0370\u0386\bI\1\2\u0371\u0372\7\u0107\2\2\u0372\u0386\bI\1\2\u0373\u0374" +
					"\7\u0108\2\2\u0374\u0386\bI\1\2\u0375\u0376\7\u0109\2\2\u0376\u0386\b" +
					"I\1\2\u0377\u0378\7\u010a\2\2\u0378\u0386\bI\1\2\u0379\u037a\7\u0110\2" +
					"\2\u037a\u0386\bI\1\2\u037b\u037c\7\u010b\2\2\u037c\u0386\bI\1\2\u037d" +
					"\u037e\7\u010c\2\2\u037e\u0386\bI\1\2\u037f\u0380\7\u010d\2\2\u0380\u0386" +
					"\bI\1\2\u0381\u0382\7\u010e\2\2\u0382\u0386\bI\1\2\u0383\u0384\7\u010f" +
					"\2\2\u0384\u0386\bI\1\2\u0385\u0363\3\2\2\2\u0385\u0365\3\2\2\2\u0385" +
					"\u0367\3\2\2\2\u0385\u0369\3\2\2\2\u0385\u036b\3\2\2\2\u0385\u036d\3\2" +
					"\2\2\u0385\u036f\3\2\2\2\u0385\u0371\3\2\2\2\u0385\u0373\3\2\2\2\u0385" +
					"\u0375\3\2\2\2\u0385\u0377\3\2\2\2\u0385\u0379\3\2\2\2\u0385\u037b\3\2" +
					"\2\2\u0385\u037d\3\2\2\2\u0385\u037f\3\2\2\2\u0385\u0381\3\2\2\2\u0385" +
					"\u0383\3\2\2\2\u0386\u0091\3\2\2\2\u0387\u0388\7M\2\2\u0388\u0389\7\6" +
					"\2\2\u0389\u038a\5\u0110\u0089\2\u038a\u038b\bJ\1\2\u038b\u0093\3\2\2" +
					"\2\u038c\u038d\7N\2\2\u038d\u038e\7\6\2\2\u038e\u038f\5\u0110\u0089\2" +
					"\u038f\u0390\bK\1\2\u0390\u0095\3\2\2\2\u0391\u0392\7O\2\2\u0392\u0393" +
					"\7\6\2\2\u0393\u0394\5\u0110\u0089\2\u0394\u0395\bL\1\2\u0395\u0097\3" +
					"\2\2\2\u0396\u0397\7P\2\2\u0397\u0398\7\6\2\2\u0398\u0399\5\u0110\u0089" +
					"\2\u0399\u039a\bM\1\2\u039a\u0099\3\2\2\2\u039b\u039c\7Q\2\2\u039c\u039d" +
					"\7\6\2\2\u039d\u039e\5\u0112\u008a\2\u039e\u039f\bN\1\2\u039f\u009b\3" +
					"\2\2\2\u03a0\u03a1\7R\2\2\u03a1\u03a2\7\6\2\2\u03a2\u03a3\5\u0114\u008b" +
					"\2\u03a3\u03a4\bO\1\2\u03a4\u009d\3\2\2\2\u03a5\u03a6\7S\2\2\u03a6\u03a7" +
					"\7\6\2\2\u03a7\u03a8\5\u0114\u008b\2\u03a8\u03a9\bP\1\2\u03a9\u009f\3" +
					"\2\2\2\u03aa\u03ab\7T\2\2\u03ab\u03ac\7\6\2\2\u03ac\u03ad\5\u0114\u008b" +
					"\2\u03ad\u03ae\bQ\1\2\u03ae\u00a1\3\2\2\2\u03af\u03b0\7U\2\2\u03b0\u03b1" +
					"\7\6\2\2\u03b1\u03b2\5\u0118\u008d\2\u03b2\u03b3\bR\1\2\u03b3\u00a3\3" +
					"\2\2\2\u03b4\u03b5\7V\2\2\u03b5\u03b6\7\6\2\2\u03b6\u03b7\5\u0114\u008b" +
					"\2\u03b7\u03b8\bS\1\2\u03b8\u00a5\3\2\2\2\u03b9\u03ba\7W\2\2\u03ba\u03bb" +
					"\7\6\2\2\u03bb\u03bc\5\u0126\u0094\2\u03bc\u03bd\bT\1\2\u03bd\u00a7\3" +
					"\2\2\2\u03be\u03bf\7X\2\2\u03bf\u03c0\7\6\2\2\u03c0\u03c1\5\u0114\u008b" +
					"\2\u03c1\u03c2\bU\1\2\u03c2\u00a9\3\2\2\2\u03c3\u03c4\7Y\2\2\u03c4\u03c5" +
					"\7\6\2\2\u03c5\u03c6\5\u0114\u008b\2\u03c6\u03c7\bV\1\2\u03c7\u00ab\3" +
					"\2\2\2\u03c8\u03c9\7Z\2\2\u03c9\u03ca\7\6\2\2\u03ca\u03cb\5\u0114\u008b" +
					"\2\u03cb\u03cc\bW\1\2\u03cc\u00ad\3\2\2\2\u03cd\u03ce\7[\2\2\u03ce\u03cf" +
					"\7\6\2\2\u03cf\u03d0\5\u0114\u008b\2\u03d0\u03d1\bX\1\2\u03d1\u00af\3" +
					"\2\2\2\u03d2\u03d3\7\\\2\2\u03d3\u03d4\7\6\2\2\u03d4\u03d5\5\u0114\u008b" +
					"\2\u03d5\u03d6\bY\1\2\u03d6\u00b1\3\2\2\2\u03d7\u03d8\7]\2\2\u03d8\u03d9" +
					"\7\6\2\2\u03d9\u03da\5\u0114\u008b\2\u03da\u03db\bZ\1\2\u03db\u00b3\3" +
					"\2\2\2\u03dc\u03dd\7^\2\2\u03dd\u03de\7\6\2\2\u03de\u03df\5\u0114\u008b" +
					"\2\u03df\u03e0\b[\1\2\u03e0\u00b5\3\2\2\2\u03e1\u03e2\7_\2\2\u03e2\u03e3" +
					"\7\6\2\2\u03e3\u03e4\5\u0114\u008b\2\u03e4\u03e5\b\\\1\2\u03e5\u00b7\3" +
					"\2\2\2\u03e6\u03e7\7`\2\2\u03e7\u03e8\7\6\2\2\u03e8\u03e9\5\u0114\u008b" +
					"\2\u03e9\u03ea\b]\1\2\u03ea\u00b9\3\2\2\2\u03eb\u03ec\7a\2\2\u03ec\u03ed" +
					"\7\6\2\2\u03ed\u03ee\5\u0126\u0094\2\u03ee\u03ef\b^\1\2\u03ef\u00bb\3" +
					"\2\2\2\u03f0\u03f1\7b\2\2\u03f1\u03f2\7\6\2\2\u03f2\u03f3\5\u0122\u0092" +
					"\2\u03f3\u00bd\3\2\2\2\u03f4\u03f5\7c\2\2\u03f5\u03f6\7\6\2\2\u03f6\u03f7" +
					"\5\u0126\u0094\2\u03f7\u03f8\b`\1\2\u03f8\u00bf\3\2\2\2\u03f9\u03fa\7" +
					"d\2\2\u03fa\u03fb\7\6\2\2\u03fb\u03fc\5\u0114\u008b\2\u03fc\u03fd\ba\1" +
					"\2\u03fd\u00c1\3\2\2\2\u03fe\u03ff\7e\2\2\u03ff\u0400\7\6\2\2\u0400\u0401" +
					"\5\u0114\u008b\2\u0401\u0402\bb\1\2\u0402\u00c3\3\2\2\2\u0403\u0404\7" +
					"f\2\2\u0404\u0405\7\6\2\2\u0405\u0406\5\u0114\u008b\2\u0406\u0407\bc\1" +
					"\2\u0407\u00c5\3\2\2\2\u0408\u0409\7g\2\2\u0409\u040a\7\6\2\2\u040a\u040b" +
					"\5\u0114\u008b\2\u040b\u040c\bd\1\2\u040c\u00c7\3\2\2\2\u040d\u040e\7" +
					"h\2\2\u040e\u040f\7\6\2\2\u040f\u0410\5\u0114\u008b\2\u0410\u0411\be\1" +
					"\2\u0411\u00c9\3\2\2\2\u0412\u0413\7i\2\2\u0413\u0414\7\6\2\2\u0414\u0415" +
					"\5\u0114\u008b\2\u0415\u0416\bf\1\2\u0416\u00cb\3\2\2\2\u0417\u0418\7" +
					"j\2\2\u0418\u0419\7\6\2\2\u0419\u041a\5\u0114\u008b\2\u041a\u041b\bg\1" +
					"\2\u041b\u00cd\3\2\2\2\u041c\u041d\7k\2\2\u041d\u042d\7\6\2\2\u041e\u042e" +
					"\5\u0122\u0092\2\u041f\u0420\7\7\2\2\u0420\u0421\5\u00d0i\2\u0421\u0428" +
					"\bh\1\2\u0422\u0423\7\u00d9\2\2\u0423\u0424\5\u00d0i\2\u0424\u0425\bh" +
					"\1\2\u0425\u0427\3\2\2\2\u0426\u0422\3\2\2\2\u0427\u042a\3\2\2\2\u0428" +
					"\u0426\3\2\2\2\u0428\u0429\3\2\2\2\u0429\u042b\3\2\2\2\u042a\u0428\3\2" +
					"\2\2\u042b\u042c\7\b\2\2\u042c\u042e\3\2\2\2\u042d\u041e\3\2\2\2\u042d" +
					"\u041f\3\2\2\2\u042e\u00cf\3\2\2\2\u042f\u0430\7\7\2\2\u0430\u0431\5\u011c" +
					"\u008f\2\u0431\u0432\7\u00d9\2\2\u0432\u0433\5\u0114\u008b\2\u0433\u0434" +
					"\7\u00d9\2\2\u0434\u0435\5\u0114\u008b\2\u0435\u0436\7\u00d9\2\2\u0436" +
					"\u0437\5\u0118\u008d\2\u0437\u0438\7\b\2\2\u0438\u00d1\3\2\2\2\u0439\u043a" +
					"\7l\2\2\u043a\u043b\7\6\2\2\u043b\u043c\5\u0114\u008b\2\u043c\u043d\b" +
					"j\1\2\u043d\u00d3\3\2\2\2\u043e\u043f\7m\2\2\u043f\u0440\7\6\2\2\u0440" +
					"\u0441\5\u0114\u008b\2\u0441\u0442\bk\1\2\u0442\u00d5\3\2\2\2\u0443\u0444" +
					"\7n\2\2\u0444\u0445\7\6\2\2\u0445\u0446\5\u011c\u008f\2\u0446\u0447\b" +
					"l\1\2\u0447\u00d7\3\2\2\2\u0448\u0449\7o\2\2\u0449\u044a\7\6\2\2\u044a" +
					"\u044b\5\u011c\u008f\2\u044b\u044c\bm\1\2\u044c\u00d9\3\2\2\2\u044d\u044e" +
					"\7p\2\2\u044e\u044f\7\6\2\2\u044f\u0450\5\u011c\u008f\2\u0450\u0451\b" +
					"n\1\2\u0451\u00db\3\2\2\2\u0452\u0453\7q\2\2\u0453\u0454\7\6\2\2\u0454" +
					"\u0455\5\u011c\u008f\2\u0455\u045a\bo\1\2\u0456\u0457\7\u00d9\2\2\u0457" +
					"\u0458\5\u0114\u008b\2\u0458\u0459\bo\1\2\u0459\u045b\3\2\2\2\u045a\u0456" +
					"\3\2\2\2\u045a\u045b\3\2\2\2\u045b\u00dd\3\2\2\2\u045c\u045d\7r\2\2\u045d" +
					"\u045e\7\6\2\2\u045e\u045f\5\u011c\u008f\2\u045f\u0460\bp\1\2\u0460\u00df" +
					"\3\2\2\2\u0461\u0462\7s\2\2\u0462\u0463\7\6\2\2\u0463\u0464\5\u00e2r\2" +
					"\u0464\u0465\bq\1\2\u0465\u00e1\3\2\2\2\u0466\u0467\7\u00da\2\2\u0467" +
					"\u047b\br\1\2\u0468\u0469\7\u00c6\2\2\u0469\u047b\br\1\2\u046a\u046b\7" +
					"\u00c7\2\2\u046b\u047b\br\1\2\u046c\u046d\7\u00c8\2\2\u046d\u047b\br\1" +
					"\2\u046e\u046f\7\u00c9\2\2\u046f\u047b\br\1\2\u0470\u0471\7\u00ca\2\2" +
					"\u0471\u047b\br\1\2\u0472\u0473\7\u00cb\2\2\u0473\u047b\br\1\2\u0474\u0475" +
					"\7\u00ce\2\2\u0475\u047b\br\1\2\u0476\u0477\7\u00cc\2\2\u0477\u047b\b" +
					"r\1\2\u0478\u0479\7\u00cd\2\2\u0479\u047b\br\1\2\u047a\u0466\3\2\2\2\u047a" +
					"\u0468\3\2\2\2\u047a\u046a\3\2\2\2\u047a\u046c\3\2\2\2\u047a\u046e\3\2" +
					"\2\2\u047a\u0470\3\2\2\2\u047a\u0472\3\2\2\2\u047a\u0474\3\2\2\2\u047a" +
					"\u0476\3\2\2\2\u047a\u0478\3\2\2\2\u047b\u00e3\3\2\2\2\u047c\u047d\7t" +
					"\2\2\u047d\u047e\7\6\2\2\u047e\u047f\5\u0114\u008b\2\u047f\u0480\bs\1" +
					"\2\u0480\u00e5\3\2\2\2\u0481\u0482\7u\2\2\u0482\u0483\7\6\2\2\u0483\u0484" +
					"\5\u00e8u\2\u0484\u0485\bt\1\2\u0485\u00e7\3\2\2\2\u0486\u0487\7\u012d" +
					"\2\2\u0487\u04bd\bu\1\2\u0488\u0489\7\u012e\2\2\u0489\u04bd\bu\1\2\u048a" +
					"\u048b\7\u012f\2\2\u048b\u04bd\bu\1\2\u048c\u048d\7\u0130\2\2\u048d\u04bd" +
					"\bu\1\2\u048e\u048f\7\u0131\2\2\u048f\u04bd\bu\1\2\u0490\u0491\7\u0132" +
					"\2\2\u0491\u04bd\bu\1\2\u0492\u0493\7\u0133\2\2\u0493\u04bd\bu\1\2\u0494" +
					"\u0495\7\u0134\2\2\u0495\u04bd\bu\1\2\u0496\u0497\7\u0135\2\2\u0497\u04bd" +
					"\bu\1\2\u0498\u0499\7\u0136\2\2\u0499\u04bd\bu\1\2\u049a\u049b\7\u0137" +
					"\2\2\u049b\u04bd\bu\1\2\u049c\u049d\7\u0138\2\2\u049d\u04bd\bu\1\2\u049e" +
					"\u049f\7\u0139\2\2\u049f\u04bd\bu\1\2\u04a0\u04a1\7\u013a\2\2\u04a1\u04bd" +
					"\bu\1\2\u04a2\u04a3\7\u013b\2\2\u04a3\u04bd\bu\1\2\u04a4\u04a5\7\u013c" +
					"\2\2\u04a5\u04bd\bu\1\2\u04a6\u04a7\7\u013d\2\2\u04a7\u04bd\bu\1\2\u04a8" +
					"\u04a9\7\u013e\2\2\u04a9\u04bd\bu\1\2\u04aa\u04ab\7\u013f\2\2\u04ab\u04bd" +
					"\bu\1\2\u04ac\u04ad\7\u0140\2\2\u04ad\u04bd\bu\1\2\u04ae\u04af\7\u0141" +
					"\2\2\u04af\u04bd\bu\1\2\u04b0\u04b1\7\u0142\2\2\u04b1\u04bd\bu\1\2\u04b2" +
					"\u04b3\7\u0143\2\2\u04b3\u04bd\bu\1\2\u04b4\u04b5\7\u0144\2\2\u04b5\u04bd" +
					"\bu\1\2\u04b6\u04b7\7\u0145\2\2\u04b7\u04bd\bu\1\2\u04b8\u04b9\7\u0146" +
					"\2\2\u04b9\u04bd\bu\1\2\u04ba\u04bb\7\u0147\2\2\u04bb\u04bd\bu\1\2\u04bc" +
					"\u0486\3\2\2\2\u04bc\u0488\3\2\2\2\u04bc\u048a\3\2\2\2\u04bc\u048c\3\2" +
					"\2\2\u04bc\u048e\3\2\2\2\u04bc\u0490\3\2\2\2\u04bc\u0492\3\2\2\2\u04bc" +
					"\u0494\3\2\2\2\u04bc\u0496\3\2\2\2\u04bc\u0498\3\2\2\2\u04bc\u049a\3\2" +
					"\2\2\u04bc\u049c\3\2\2\2\u04bc\u049e\3\2\2\2\u04bc\u04a0\3\2\2\2\u04bc" +
					"\u04a2\3\2\2\2\u04bc\u04a4\3\2\2\2\u04bc\u04a6\3\2\2\2\u04bc\u04a8\3\2" +
					"\2\2\u04bc\u04aa\3\2\2\2\u04bc\u04ac\3\2\2\2\u04bc\u04ae\3\2\2\2\u04bc" +
					"\u04b0\3\2\2\2\u04bc\u04b2\3\2\2\2\u04bc\u04b4\3\2\2\2\u04bc\u04b6\3\2" +
					"\2\2\u04bc\u04b8\3\2\2\2\u04bc\u04ba\3\2\2\2\u04bd\u00e9\3\2\2\2\u04be" +
					"\u04bf\7v\2\2\u04bf\u04c0\7\6\2\2\u04c0\u04c1\5\u00ecw\2\u04c1\u04c2\b" +
					"v\1\2\u04c2\u00eb\3\2\2\2\u04c3\u04c4\7\u00c3\2\2\u04c4\u04ca\bw\1\2\u04c5" +
					"\u04c6\7\u00c4\2\2\u04c6\u04ca\bw\1\2\u04c7\u04c8\7\u00c5\2\2\u04c8\u04ca" +
					"\bw\1\2\u04c9\u04c3\3\2\2\2\u04c9\u04c5\3\2\2\2\u04c9\u04c7\3\2\2\2\u04ca" +
					"\u00ed\3\2\2\2\u04cb\u04cc\7w\2\2\u04cc\u04cd\7\6\2\2\u04cd\u04ce\5\u00f0" +
					"y\2\u04ce\u04cf\bx\1\2\u04cf\u00ef\3\2\2\2\u04d0\u04d1\7\u00a9\2\2\u04d1" +
					"\u0505\by\1\2\u04d2\u04d3\7\u00aa\2\2\u04d3\u0505\by\1\2\u04d4\u04d5\7" +
					"\u00ab\2\2\u04d5\u0505\by\1\2\u04d6\u04d7\7\u00ac\2\2\u04d7\u0505\by\1" +
					"\2\u04d8\u04d9\7\u00ae\2\2\u04d9\u0505\by\1\2\u04da\u04db\7\u00af\2\2" +
					"\u04db\u0505\by\1\2\u04dc\u04dd\7\u00b0\2\2\u04dd\u0505\by\1\2\u04de\u04df" +
					"\7\u00b1\2\2\u04df\u0505\by\1\2\u04e0\u04e1\7\u00b2\2\2\u04e1\u0505\b" +
					"y\1\2\u04e2\u04e3\7\u00b3\2\2\u04e3\u0505\by\1\2\u04e4\u04e5\7\u00b4\2" +
					"\2\u04e5\u0505\by\1\2\u04e6\u04e7\7\u00b5\2\2\u04e7\u0505\by\1\2\u04e8" +
					"\u04e9\7\u00b6\2\2\u04e9\u0505\by\1\2\u04ea\u04eb\7\u00b7\2\2\u04eb\u0505" +
					"\by\1\2\u04ec\u04ed\7\u00ad\2\2\u04ed\u0505\by\1\2\u04ee\u04ef\7\u00b8" +
					"\2\2\u04ef\u0505\by\1\2\u04f0\u04f1\7\u00b9\2\2\u04f1\u0505\by\1\2\u04f2" +
					"\u04f3\7\u00ba\2\2\u04f3\u0505\by\1\2\u04f4\u04f5\7\u00bb\2\2\u04f5\u0505" +
					"\by\1\2\u04f6\u04f7\7\u00bc\2\2\u04f7\u0505\by\1\2\u04f8\u04f9\7\u00bd" +
					"\2\2\u04f9\u0505\by\1\2\u04fa\u04fb\7\u00be\2\2\u04fb\u0505\by\1\2\u04fc" +
					"\u04fd\7\u00bf\2\2\u04fd\u0505\by\1\2\u04fe\u04ff\7\u00c0\2\2\u04ff\u0505" +
					"\by\1\2\u0500\u0501\7\u00c1\2\2\u0501\u0505\by\1\2\u0502\u0503\7\u00c2" +
					"\2\2\u0503\u0505\by\1\2\u0504\u04d0\3\2\2\2\u0504\u04d2\3\2\2\2\u0504" +
					"\u04d4\3\2\2\2\u0504\u04d6\3\2\2\2\u0504\u04d8\3\2\2\2\u0504\u04da\3\2" +
					"\2\2\u0504\u04dc\3\2\2\2\u0504\u04de\3\2\2\2\u0504\u04e0\3\2\2\2\u0504" +
					"\u04e2\3\2\2\2\u0504\u04e4\3\2\2\2\u0504\u04e6\3\2\2\2\u0504\u04e8\3\2" +
					"\2\2\u0504\u04ea\3\2\2\2\u0504\u04ec\3\2\2\2\u0504\u04ee\3\2\2\2\u0504" +
					"\u04f0\3\2\2\2\u0504\u04f2\3\2\2\2\u0504\u04f4\3\2\2\2\u0504\u04f6\3\2" +
					"\2\2\u0504\u04f8\3\2\2\2\u0504\u04fa\3\2\2\2\u0504\u04fc\3\2\2\2\u0504" +
					"\u04fe\3\2\2\2\u0504\u0500\3\2\2\2\u0504\u0502\3\2\2\2\u0505\u00f1\3\2" +
					"\2\2\u0506\u0507\7x\2\2\u0507\u0508\7\6\2\2\u0508\u0509\5\u0114\u008b" +
					"\2\u0509\u050a\bz\1\2\u050a\u00f3\3\2\2\2\u050b\u050c\7y\2\2\u050c\u050d" +
					"\7\6\2\2\u050d\u050e\5\u0114\u008b\2\u050e\u050f\b{\1\2\u050f\u00f5\3" +
					"\2\2\2\u0510\u0511\7z\2\2\u0511\u0512\7\6\2\2\u0512\u0513\5\u0114\u008b" +
					"\2\u0513\u0514\b|\1\2\u0514\u00f7\3\2\2\2\u0515\u0516\7{\2\2\u0516\u0517" +
					"\7\6\2\2\u0517\u0518\5\u0124\u0093\2\u0518\u0519\b}\1\2\u0519\u00f9\3" +
					"\2\2\2\u051a\u051b\7|\2\2\u051b\u051c\7\6\2\2\u051c\u051d\5\u0114\u008b" +
					"\2\u051d\u051e\b~\1\2\u051e\u00fb\3\2\2\2\u051f\u0520\7}\2\2\u0520\u0521" +
					"\7\6\2\2\u0521\u0522\5\u00fe\u0080\2\u0522\u0523\b\177\1\2\u0523\u00fd" +
					"\3\2\2\2\u0524\u0525\7\u00da\2\2\u0525\u0565\b\u0080\1\2\u0526\u0527\7" +
					"\u008a\2\2\u0527\u0565\b\u0080\1\2\u0528\u0529\7\u008b\2\2\u0529\u0565" +
					"\b\u0080\1\2\u052a\u052b\7\u008c\2\2\u052b\u0565\b\u0080\1\2\u052c\u052d" +
					"\7\u008d\2\2\u052d\u0565\b\u0080\1\2\u052e\u052f\7\u008e\2\2\u052f\u0565" +
					"\b\u0080\1\2\u0530\u0531\7\u008f\2\2\u0531\u0565\b\u0080\1\2\u0532\u0533" +
					"\7\u0090\2\2\u0533\u0565\b\u0080\1\2\u0534\u0535\7\u0091\2\2\u0535\u0565" +
					"\b\u0080\1\2\u0536\u0537\7\u0092\2\2\u0537\u0565\b\u0080\1\2\u0538\u0539" +
					"\7\u0093\2\2\u0539\u0565\b\u0080\1\2\u053a\u053b\7\u0094\2\2\u053b\u0565" +
					"\b\u0080\1\2\u053c\u053d\7\u0095\2\2\u053d\u0565\b\u0080\1\2\u053e\u053f" +
					"\7\u0096\2\2\u053f\u0565\b\u0080\1\2\u0540\u0541\7\u0097\2\2\u0541\u0565" +
					"\b\u0080\1\2\u0542\u0543\7\u0098\2\2\u0543\u0565\b\u0080\1\2\u0544\u0545" +
					"\7\u0099\2\2\u0545\u0565\b\u0080\1\2\u0546\u0547\7\u009a\2\2\u0547\u0565" +
					"\b\u0080\1\2\u0548\u0549\7\u009b\2\2\u0549\u0565\b\u0080\1\2\u054a\u054b" +
					"\7\u009c\2\2\u054b\u0565\b\u0080\1\2\u054c\u054d\7\u009d\2\2\u054d\u0565" +
					"\b\u0080\1\2\u054e\u054f\7\u009e\2\2\u054f\u0565\b\u0080\1\2\u0550\u0551" +
					"\7\u009f\2\2\u0551\u0565\b\u0080\1\2\u0552\u0553\7\u00a0\2\2\u0553\u0565" +
					"\b\u0080\1\2\u0554\u0555\7\u00a1\2\2\u0555\u0565\b\u0080\1\2\u0556\u0557" +
					"\7\u00a4\2\2\u0557\u0565\b\u0080\1\2\u0558\u0559\7\u00a7\2\2\u0559\u0565" +
					"\b\u0080\1\2\u055a\u055b\7\u00a2\2\2\u055b\u0565\b\u0080\1\2\u055c\u055d" +
					"\7\u00a3\2\2\u055d\u0565\b\u0080\1\2\u055e\u055f\7\u00a5\2\2\u055f\u0565" +
					"\b\u0080\1\2\u0560\u0561\7\u00a6\2\2\u0561\u0565\b\u0080\1\2\u0562\u0563" +
					"\7\u00a8\2\2\u0563\u0565\b\u0080\1\2\u0564\u0524\3\2\2\2\u0564\u0526\3" +
					"\2\2\2\u0564\u0528\3\2\2\2\u0564\u052a\3\2\2\2\u0564\u052c\3\2\2\2\u0564" +
					"\u052e\3\2\2\2\u0564\u0530\3\2\2\2\u0564\u0532\3\2\2\2\u0564\u0534\3\2" +
					"\2\2\u0564\u0536\3\2\2\2\u0564\u0538\3\2\2\2\u0564\u053a\3\2\2\2\u0564" +
					"\u053c\3\2\2\2\u0564\u053e\3\2\2\2\u0564\u0540\3\2\2\2\u0564\u0542\3\2" +
					"\2\2\u0564\u0544\3\2\2\2\u0564\u0546\3\2\2\2\u0564\u0548\3\2\2\2\u0564" +
					"\u054a\3\2\2\2\u0564\u054c\3\2\2\2\u0564\u054e\3\2\2\2\u0564\u0550\3\2" +
					"\2\2\u0564\u0552\3\2\2\2\u0564\u0554\3\2\2\2\u0564\u0556\3\2\2\2\u0564" +
					"\u0558\3\2\2\2\u0564\u055a\3\2\2\2\u0564\u055c\3\2\2\2\u0564\u055e\3\2" +
					"\2\2\u0564\u0560\3\2\2\2\u0564\u0562\3\2\2\2\u0565\u00ff\3\2\2\2\u0566" +
					"\u0567\7~\2\2\u0567\u0568\7\6\2\2\u0568\u0569\5\u0102\u0082\2\u0569\u056a" +
					"\b\u0081\1\2\u056a\u0101\3\2\2\2\u056b\u056c\7\u00da\2\2\u056c\u0576\b" +
					"\u0082\1\2\u056d\u056e\7\u0111\2\2\u056e\u0576\b\u0082\1\2\u056f\u0570" +
					"\7\u0112\2\2\u0570\u0576\b\u0082\1\2\u0571\u0572\7\u0113\2\2\u0572\u0576" +
					"\b\u0082\1\2\u0573\u0574\7\u0114\2\2\u0574\u0576\b\u0082\1\2\u0575\u056b" +
					"\3\2\2\2\u0575\u056d\3\2\2\2\u0575\u056f\3\2\2\2\u0575\u0571\3\2\2\2\u0575" +
					"\u0573\3\2\2\2\u0576\u0103\3\2\2\2\u0577\u0578\7\177\2\2\u0578\u0579\7" +
					"\6\2\2\u0579\u057a\7\7\2\2\u057a\u057b\5\u0106\u0084\2\u057b\u0580\b\u0083" +
					"\1\2\u057c\u057d\7\u00d9\2\2\u057d\u057e\5\u0106\u0084\2\u057e\u057f\b" +
					"\u0083\1\2\u057f\u0581\3\2\2\2\u0580\u057c\3\2\2\2\u0580\u0581\3\2\2\2" +
					"\u0581\u0582\3\2\2\2\u0582\u0583\7\b\2\2\u0583\u0105\3\2\2\2\u0584\u0585" +
					"\7\u00da\2\2\u0585\u05b7\b\u0084\1\2\u0586\u0587\7\u0115\2\2\u0587\u05b7" +
					"\b\u0084\1\2\u0588\u0589\7\u0116\2\2\u0589\u05b7\b\u0084\1\2\u058a\u058b" +
					"\7\u0117\2\2\u058b\u05b7\b\u0084\1\2\u058c\u058d\7\u0118\2\2\u058d\u05b7" +
					"\b\u0084\1\2\u058e\u058f\7\u0119\2\2\u058f\u05b7\b\u0084\1\2\u0590\u0591" +
					"\7\u011a\2\2\u0591\u05b7\b\u0084\1\2\u0592\u0593\7\u011b\2\2\u0593\u05b7" +
					"\b\u0084\1\2\u0594\u0595\7\u011c\2\2\u0595\u05b7\b\u0084\1\2\u0596\u0597" +
					"\7\u011d\2\2\u0597\u05b7\b\u0084\1\2\u0598\u0599\7\u011e\2\2\u0599\u05b7" +
					"\b\u0084\1\2\u059a\u059b\7\u011f\2\2\u059b\u05b7\b\u0084\1\2\u059c\u059d" +
					"\7\u0121\2\2\u059d\u05b7\b\u0084\1\2\u059e\u059f\7\u0120\2\2\u059f\u05b7" +
					"\b\u0084\1\2\u05a0\u05a1\7\u0122\2\2\u05a1\u05b7\b\u0084\1\2\u05a2\u05a3" +
					"\7\u0123\2\2\u05a3\u05b7\b\u0084\1\2\u05a4\u05a5\7\u0124\2\2\u05a5\u05b7" +
					"\b\u0084\1\2\u05a6\u05a7\7\u0125\2\2\u05a7\u05b7\b\u0084\1\2\u05a8\u05a9" +
					"\7\u0126\2\2\u05a9\u05b7\b\u0084\1\2\u05aa\u05ab\7\u0127\2\2\u05ab\u05b7" +
					"\b\u0084\1\2\u05ac\u05ad\7\u0128\2\2\u05ad\u05b7\b\u0084\1\2\u05ae\u05af" +
					"\7\u0129\2\2\u05af\u05b7\b\u0084\1\2\u05b0\u05b1\7\u012a\2\2\u05b1\u05b7" +
					"\b\u0084\1\2\u05b2\u05b3\7\u012b\2\2\u05b3\u05b7\b\u0084\1\2\u05b4\u05b5" +
					"\7\u012c\2\2\u05b5\u05b7\b\u0084\1\2\u05b6\u0584\3\2\2\2\u05b6\u0586\3" +
					"\2\2\2\u05b6\u0588\3\2\2\2\u05b6\u058a\3\2\2\2\u05b6\u058c\3\2\2\2\u05b6" +
					"\u058e\3\2\2\2\u05b6\u0590\3\2\2\2\u05b6\u0592\3\2\2\2\u05b6\u0594\3\2" +
					"\2\2\u05b6\u0596\3\2\2\2\u05b6\u0598\3\2\2\2\u05b6\u059a\3\2\2\2\u05b6" +
					"\u059c\3\2\2\2\u05b6\u059e\3\2\2\2\u05b6\u05a0\3\2\2\2\u05b6\u05a2\3\2" +
					"\2\2\u05b6\u05a4\3\2\2\2\u05b6\u05a6\3\2\2\2\u05b6\u05a8\3\2\2\2\u05b6" +
					"\u05aa\3\2\2\2\u05b6\u05ac\3\2\2\2\u05b6\u05ae\3\2\2\2\u05b6\u05b0\3\2" +
					"\2\2\u05b6\u05b2\3\2\2\2\u05b6\u05b4\3\2\2\2\u05b7\u0107\3\2\2\2\u05b8" +
					"\u05b9\7\u0080\2\2\u05b9\u05ba\7\6\2\2\u05ba\u05bb\5\u010a\u0086\2\u05bb" +
					"\u05bc\b\u0085\1\2\u05bc\u0109\3\2\2\2\u05bd\u05be\7\u0084\2\2\u05be\u05ca" +
					"\b\u0086\1\2\u05bf\u05c0\7\u0085\2\2\u05c0\u05ca\b\u0086\1\2\u05c1\u05c2" +
					"\7\u0086\2\2\u05c2\u05ca\b\u0086\1\2\u05c3\u05c4\7\u0087\2\2\u05c4\u05ca" +
					"\b\u0086\1\2\u05c5\u05c6\7\u0088\2\2\u05c6\u05ca\b\u0086\1\2\u05c7\u05c8" +
					"\7\u0089\2\2\u05c8\u05ca\b\u0086\1\2\u05c9\u05bd\3\2\2\2\u05c9\u05bf\3" +
					"\2\2\2\u05c9\u05c1\3\2\2\2\u05c9\u05c3\3\2\2\2\u05c9\u05c5\3\2\2\2\u05c9" +
					"\u05c7\3\2\2\2\u05ca\u010b\3\2\2\2\u05cb\u05cc\5\u0114\u008b\2\u05cc\u05cd" +
					"\b\u0087\1\2\u05cd\u010d\3\2\2\2\u05ce\u05cf\t\2\2\2\u05cf\u010f\3\2\2" +
					"\2\u05d0\u05d1\7\u00d0\2\2\u05d1\u0111\3\2\2\2\u05d2\u05d3\t\3\2\2\u05d3" +
					"\u0113\3\2\2\2\u05d4\u05d5\t\3\2\2\u05d5\u0115\3\2\2\2\u05d6\u05d7\t\3" +
					"\2\2\u05d7\u0117\3\2\2\2\u05d8\u05d9\t\4\2\2\u05d9\u0119\3\2\2\2\u05da" +
					"\u05db\t\4\2\2\u05db\u011b\3\2\2\2\u05dc\u05dd\7\u0081\2\2\u05dd\u05de" +
					"\5\u010e\u0088\2\u05de\u05df\7\u0082\2\2\u05df\u05e0\b\u008f\1\2\u05e0" +
					"\u011d\3\2\2\2\u05e1\u05e2\7\u00cf\2\2\u05e2\u011f\3\2\2\2\u05e3\u05e4" +
					"\7\7\2\2\u05e4\u05e5\5\u0114\u008b\2\u05e5\u05e6\7\u00d9\2\2\u05e6\u05e7" +
					"\5\u0114\u008b\2\u05e7\u05e8\7\u00d9\2\2\u05e8\u05e9\5\u0114\u008b\2\u05e9" +
					"\u05ea\7\b\2\2\u05ea\u0121\3\2\2\2\u05eb\u05ec\7\7\2\2\u05ec\u05ed\7\b" +
					"\2\2\u05ed\u0123\3\2\2\2\u05ee\u05fe\5\u0122\u0092\2\u05ef\u05f0\7\7\2" +
					"\2\u05f0\u05f1\5\u010e\u0088\2\u05f1\u05f8\b\u0093\1\2\u05f2\u05f3\7\u00d9" +
					"\2\2\u05f3\u05f4\5\u010e\u0088\2\u05f4\u05f5\b\u0093\1\2\u05f5\u05f7\3" +
					"\2\2\2\u05f6\u05f2\3\2\2\2\u05f7\u05fa\3\2\2\2\u05f8\u05f6\3\2\2\2\u05f8" +
					"\u05f9\3\2\2\2\u05f9\u05fb\3\2\2\2\u05fa\u05f8\3\2\2\2\u05fb\u05fc\7\b" +
					"\2\2\u05fc\u05fe\3\2\2\2\u05fd\u05ee\3\2\2\2\u05fd\u05ef\3\2\2\2\u05fe" +
					"\u0125\3\2\2\2\u05ff\u060f\5\u0122\u0092\2\u0600\u0601\7\7\2\2\u0601\u0602" +
					"\5\u0114\u008b\2\u0602\u0609\b\u0094\1\2\u0603\u0604\7\u00d9\2\2\u0604" +
					"\u0605\5\u0114\u008b\2\u0605\u0606\b\u0094\1\2\u0606\u0608\3\2\2\2\u0607" +
					"\u0603\3\2\2\2\u0608\u060b\3\2\2\2\u0609\u0607\3\2\2\2\u0609\u060a\3\2" +
					"\2\2\u060a\u060c\3\2\2\2\u060b\u0609\3\2\2\2\u060c\u060d\7\b\2\2\u060d" +
					"\u060f\3\2\2\2\u060e\u05ff\3\2\2\2\u060e\u0600\3\2\2\2\u060f\u0127\3\2" +
					"\2\2\u0610\u0620\5\u0122\u0092\2\u0611\u0612\7\7\2\2\u0612\u0613\5\u0118" +
					"\u008d\2\u0613\u061a\b\u0095\1\2\u0614\u0615\7\u00d9\2\2\u0615\u0616\5" +
					"\u0118\u008d\2\u0616\u0617\b\u0095\1\2\u0617\u0619\3\2\2\2\u0618\u0614" +
					"\3\2\2\2\u0619\u061c\3\2\2\2\u061a\u0618\3\2\2\2\u061a\u061b\3\2\2\2\u061b" +
					"\u061d\3\2\2\2\u061c\u061a\3\2\2\2\u061d\u061e\7\b\2\2\u061e\u0620\3\2" +
					"\2\2\u061f\u0610\3\2\2\2\u061f\u0611\3\2\2\2\u0620\u0129\3\2\2\2\u0621" +
					"\u0622\7\u0083\2\2\u0622\u0623\7\6\2\2\u0623\u0624\5\u012c\u0097\2\u0624" +
					"\u0625\b\u0096\1\2\u0625\u012b\3\2\2\2\u0626\u0627\7\7\2\2\u0627\u0628" +
					"\5\u012e\u0098\2\u0628\u0629\7\u00d9\2\2\u0629\u062a\5\u0114\u008b\2\u062a" +
					"\u062b\7\b\2\2\u062b\u062c\b\u0097\1\2\u062c\u012d\3\2\2\2\u062d\u062e" +
					"\7\u00da\2\2\u062e\u063c\b\u0098\1\2\u062f\u0630\7\u00d3\2\2\u0630\u063c" +
					"\b\u0098\1\2\u0631\u0632\7\u00d4\2\2\u0632\u063c\b\u0098\1\2\u0633\u0634" +
					"\7\u00d5\2\2\u0634\u063c\b\u0098\1\2\u0635\u0636\7\u00d6\2\2\u0636\u063c" +
					"\b\u0098\1\2\u0637\u0638\7\u00d8\2\2\u0638\u063c\b\u0098\1\2\u0639\u063a" +
					"\7\u00d7\2\2\u063a\u063c\b\u0098\1\2\u063b\u062d\3\2\2\2\u063b\u062f\3" +
					"\2\2\2\u063b\u0631\3\2\2\2\u063b\u0633\3\2\2\2\u063b\u0635\3\2\2\2\u063b" +
					"\u0637\3\2\2\2\u063b\u0639\3\2\2\2\u063c\u012f\3\2\2\2\u063d\u064d\5\u0122" +
					"\u0092\2\u063e\u063f\7\7\2\2\u063f\u0640\5\u011e\u0090\2\u0640\u0647\b" +
					"\u0099\1\2\u0641\u0642\7\u00d9\2\2\u0642\u0643\5\u011e\u0090\2\u0643\u0644" +
					"\b\u0099\1\2\u0644\u0646\3\2\2\2\u0645\u0641\3\2\2\2\u0646\u0649\3\2\2" +
					"\2\u0647\u0645\3\2\2\2\u0647\u0648\3\2\2\2\u0648\u064a\3\2\2\2\u0649\u0647" +
					"\3\2\2\2\u064a\u064b\7\b\2\2\u064b\u064d\3\2\2\2\u064c\u063d\3\2\2\2\u064c" +
					"\u063e\3\2\2\2\u064d\u0131\3\2\2\2%\u0134\u0136\u013c\u013f\u0142\u0145" +
					"\u0148\u0151\u0212\u0217\u0225\u022a\u0283\u0385\u0428\u042d\u045a\u047a" +
					"\u04bc\u04c9\u0504\u0564\u0575\u0580\u05b6\u05c9\u05f8\u05fd\u0609\u060e" +
					"\u061a\u061f\u063b\u0647\u064c";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}