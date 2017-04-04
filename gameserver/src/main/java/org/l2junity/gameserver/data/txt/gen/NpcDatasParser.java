// Generated from org\l2junity\gameserver\data\txt\gen\NpcDatas.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.constants.NpcType;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class NpcDatasParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, MALE=90, FEMALE=91, ETC=92, CATEGORY=93, BOOLEAN=94, 
		INTEGER=95, DOUBLE=96, FIRE=97, WATER=98, EARTH=99, WIND=100, UNHOLY=101, 
		HOLY=102, SEMICOLON=103, NONE=104, FAIRY=105, ANIMAL=106, HUMANOID=107, 
		PLANT=108, UNDEAD=109, CONSTRUCT=110, BEAST=111, BUG=112, ELEMENTAL=113, 
		DEMONIC=114, GIANT=115, DRAGON=116, DIVINE=117, SUMMON=118, PET=119, HOLYTHING=120, 
		DWARF=121, MERCHANT=122, ELF=123, KAMAEL=124, ORC=125, MERCENARY=126, 
		CASTLE_GUARD=127, HUMAN=128, BOSS=129, ZZOLDAGU=130, WORLD_TRAP=131, MONRACE=132, 
		DARKELF=133, GUARD=134, TELEPORTER=135, WAREHOUSE_KEEPER=136, WARRIOR=137, 
		CITIZEN=138, TREASURE=139, FIELDBOSS=140, BLACKSMITH=141, GUILD_MASTER=142, 
		GUILD_COACH=143, SWORD=144, BLUNT=145, BOW=146, POLE=147, DAGGER=148, 
		DUAL=149, FIST=150, DUALFIST=151, FISHINGROD=152, RAPIER=153, ANCIENTSWORD=154, 
		CROSSBOW=155, FLAG=156, OWNTHING=157, DUALDAGGER=158, LIGHT=159, HEAVY=160, 
		MAGIC=161, SIGIL=162, RHAND=163, LRHAND=164, LHAND=165, CHEST=166, LEGS=167, 
		FEET=168, HEAD=169, GLOVES=170, ONEPIECE=171, REAR=172, LEAR=173, RFINGER=174, 
		LFINGER=175, NECK=176, BACK=177, UNDERWEAR=178, HAIR=179, HAIR2=180, HAIRALL=181, 
		ALLDRESS=182, RBRACELET=183, LBRACELET=184, WAIST=185, DECO1=186, STEEL=187, 
		FINE_STEEL=188, WOOD=189, CLOTH=190, LEATHER=191, BONE=192, BRONZE=193, 
		ORIHARUKON=194, MITHRIL=195, DAMASCUS=196, ADAMANTAITE=197, BLOOD_STEEL=198, 
		PAPER=199, GOLD=200, LIQUID=201, FISH=202, SILVER=203, CHRYSOLITE=204, 
		CRYSTAL=205, HORN=206, SCALE_OF_DRAGON=207, COTTON=208, DYESTUFF=209, 
		COBWEB=210, RUNE_XP=211, RUNE_SP=212, RUNE_REMOVE_PENALTY=213, NAME=214, 
		WS=215, LINE_COMMENT=216, STAR_COMMENT=217;
	public static final int
		RULE_file = 0, RULE_npc = 1, RULE_npc_type = 2, RULE_npc_id = 3, RULE_npc_name = 4, 
		RULE_category = 5, RULE_level = 6, RULE_exp = 7, RULE_ex_crt_effect = 8, 
		RULE_unique = 9, RULE_s_npc_prop_hp_rate = 10, RULE_race = 11, RULE_sex = 12, 
		RULE_skill_list = 13, RULE_slot_chest = 14, RULE_slot_rhand = 15, RULE_slot_lhand = 16, 
		RULE_empty_name_object = 17, RULE_collision_radius = 18, RULE_collision_height = 19, 
		RULE_hit_time_factor = 20, RULE_hit_time_factor_skill = 21, RULE_ground_high = 22, 
		RULE_ground_low = 23, RULE_str = 24, RULE_int_ = 25, RULE_dex = 26, RULE_wit = 27, 
		RULE_con = 28, RULE_men = 29, RULE_org_hp = 30, RULE_org_hp_regen = 31, 
		RULE_org_mp = 32, RULE_org_mp_regen = 33, RULE_base_attack_type = 34, 
		RULE_attack_type = 35, RULE_base_attack_range = 36, RULE_base_damage_range = 37, 
		RULE_base_rand_dam = 38, RULE_base_physical_attack = 39, RULE_base_critical = 40, 
		RULE_physical_hit_modify = 41, RULE_base_attack_speed = 42, RULE_base_reuse_delay = 43, 
		RULE_base_magic_attack = 44, RULE_base_defend = 45, RULE_base_magic_defend = 46, 
		RULE_base_attribute_defend = 47, RULE_physical_avoid_modify = 48, RULE_shield_defense_rate = 49, 
		RULE_shield_defense = 50, RULE_safe_height = 51, RULE_soulshot_count = 52, 
		RULE_spiritshot_count = 53, RULE_clan = 54, RULE_ignore_clan_list = 55, 
		RULE_clan_help_range = 56, RULE_undying = 57, RULE_can_be_attacked = 58, 
		RULE_corpse_time = 59, RULE_no_sleep_mode = 60, RULE_agro_range = 61, 
		RULE_passable_door = 62, RULE_can_move = 63, RULE_flying = 64, RULE_has_summoner = 65, 
		RULE_targetable = 66, RULE_show_name_tag = 67, RULE_abnormal_resist = 68, 
		RULE_is_death_penalty = 69, RULE_npc_ai = 70, RULE_ai_param = 71, RULE_param_value = 72, 
		RULE_npc_privates = 73, RULE_fstring_object = 74, RULE_event_flag = 75, 
		RULE_unsowing = 76, RULE_private_respawn_log = 77, RULE_acquire_exp_rate = 78, 
		RULE_acquire_sp = 79, RULE_acquire_rp = 80, RULE_corpse_make_list = 81, 
		RULE_make_item_list = 82, RULE_make_item = 83, RULE_additional_make_list = 84, 
		RULE_additional_make_multi_list = 85, RULE_make_group_list = 86, RULE_ex_item_drop_list = 87, 
		RULE_vitality_item_drop_list = 88, RULE_mp_reward = 89, RULE_fake_class_id = 90, 
		RULE_event_drop = 91, RULE_ex_drop = 92, RULE_enable_move_after_talk = 93, 
		RULE_broadcast_cond = 94, RULE_identifier_object = 95, RULE_bool_object = 96, 
		RULE_byte_object = 97, RULE_int_object = 98, RULE_long_object = 99, RULE_double_object = 100, 
		RULE_string_object = 101, RULE_name_object = 102, RULE_category_object = 103, 
		RULE_vector3D_object = 104, RULE_empty_list = 105, RULE_identifier_list = 106, 
		RULE_int_list = 107, RULE_double_list = 108, RULE_base_attribute_attack = 109, 
		RULE_attack_attribute = 110, RULE_attribute = 111, RULE_category_list = 112;
	public static final String[] ruleNames = {
		"file", "npc", "npc_type", "npc_id", "npc_name", "category", "level", 
		"exp", "ex_crt_effect", "unique", "s_npc_prop_hp_rate", "race", "sex", 
		"skill_list", "slot_chest", "slot_rhand", "slot_lhand", "empty_name_object", 
		"collision_radius", "collision_height", "hit_time_factor", "hit_time_factor_skill", 
		"ground_high", "ground_low", "str", "int_", "dex", "wit", "con", "men", 
		"org_hp", "org_hp_regen", "org_mp", "org_mp_regen", "base_attack_type", 
		"attack_type", "base_attack_range", "base_damage_range", "base_rand_dam", 
		"base_physical_attack", "base_critical", "physical_hit_modify", "base_attack_speed", 
		"base_reuse_delay", "base_magic_attack", "base_defend", "base_magic_defend", 
		"base_attribute_defend", "physical_avoid_modify", "shield_defense_rate", 
		"shield_defense", "safe_height", "soulshot_count", "spiritshot_count", 
		"clan", "ignore_clan_list", "clan_help_range", "undying", "can_be_attacked", 
		"corpse_time", "no_sleep_mode", "agro_range", "passable_door", "can_move", 
		"flying", "has_summoner", "targetable", "show_name_tag", "abnormal_resist", 
		"is_death_penalty", "npc_ai", "ai_param", "param_value", "npc_privates", 
		"fstring_object", "event_flag", "unsowing", "private_respawn_log", "acquire_exp_rate", 
		"acquire_sp", "acquire_rp", "corpse_make_list", "make_item_list", "make_item", 
		"additional_make_list", "additional_make_multi_list", "make_group_list", 
		"ex_item_drop_list", "vitality_item_drop_list", "mp_reward", "fake_class_id", 
		"event_drop", "ex_drop", "enable_move_after_talk", "broadcast_cond", "identifier_object", 
		"bool_object", "byte_object", "int_object", "long_object", "double_object", 
		"string_object", "name_object", "category_object", "vector3D_object", 
		"empty_list", "identifier_list", "int_list", "double_list", "base_attribute_attack", 
		"attack_attribute", "attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'npc_begin'", "'npc_end'", "'category'", "'='", "'{'", "'}'", "'level'", 
		"'exp'", "'ex_crt_effect'", "'unique'", "'s_npc_prop_hp_rate'", "'race'", 
		"'sex'", "'skill_list'", "'slot_chest'", "'slot_rhand'", "'slot_lhand'", 
		"'['", "']'", "'collision_radius'", "'collision_height'", "'hit_time_factor'", 
		"'hit_time_factor_skill'", "'ground_high'", "'ground_low'", "'str'", "'int'", 
		"'dex'", "'wit'", "'con'", "'men'", "'org_hp'", "'org_hp_regen'", "'org_mp'", 
		"'org_mp_regen'", "'base_attack_type'", "'base_attack_range'", "'base_damage_range'", 
		"'base_rand_dam'", "'base_physical_attack'", "'base_critical'", "'physical_hit_modify'", 
		"'base_attack_speed'", "'base_reuse_delay'", "'base_magic_attack'", "'base_defend'", 
		"'base_magic_defend'", "'base_attribute_defend'", "'physical_avoid_modify'", 
		"'shield_defense_rate'", "'shield_defense'", "'safe_height'", "'soulshot_count'", 
		"'spiritshot_count'", "'clan'", "'ignore_clan_list'", "'clan_help_range'", 
		"'undying'", "'can_be_attacked'", "'corpse_time'", "'no_sleep_mode'", 
		"'agro_range'", "'passable_door'", "'can_move'", "'flying'", "'has_summoner'", 
		"'targetable'", "'show_name_tag'", "'abnormal_resist'", "'is_death_penalty'", 
		"'npc_ai'", "'event_flag'", "'unsowing'", "'private_respawn_log'", "'acquire_exp_rate'", 
		"'acquire_sp'", "'acquire_rp'", "'corpse_make_list'", "'additional_make_list'", 
		"'additional_make_multi_list'", "'ex_item_drop_list'", "'vitality_item_drop_list'", 
		"'mp_reward'", "'fake_class_id'", "'event_drop'", "'ex_drop'", "'enable_move_after_talk'", 
		"'broadcast_cond'", "'base_attribute_attack'", "'male'", "'female'", "'etc'", 
		null, null, null, null, "'fire'", "'water'", "'earth'", "'wind'", "'unholy'", 
		"'holy'", "';'", "'none'", "'fairy'", "'animal'", "'humanoid'", "'plant'", 
		"'undead'", "'construct'", "'beast'", "'bug'", "'elemental'", "'demonic'", 
		"'giant'", "'dragon'", "'divine'", "'summon'", "'pet'", "'holything'", 
		"'dwarf'", "'merchant'", "'elf'", "'kamael'", "'orc'", "'mercenary'", 
		"'castle_guard'", "'human'", "'boss'", "'zzoldagu'", "'world_trap'", "'monrace'", 
		"'darkelf'", "'guard'", "'teleporter'", "'warehouse_keeper'", "'warrior'", 
		"'citizen'", "'treasure'", "'fieldboss'", "'blacksmith'", "'guild_master'", 
		"'guild_coach'", "'sword'", "'blunt'", "'bow'", "'pole'", "'dagger'", 
		"'dual'", "'fist'", "'dualfist'", "'fishingrod'", "'rapier'", "'ancientsword'", 
		"'crossbow'", "'flag'", "'ownthing'", "'dualdagger'", "'light'", "'heavy'", 
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
		null, null, null, null, null, null, "MALE", "FEMALE", "ETC", "CATEGORY", 
		"BOOLEAN", "INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", 
		"HOLY", "SEMICOLON", "NONE", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", 
		"CONSTRUCT", "BEAST", "BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", 
		"DIVINE", "SUMMON", "PET", "HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", 
		"ORC", "MERCENARY", "CASTLE_GUARD", "HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", 
		"MONRACE", "DARKELF", "GUARD", "TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", 
		"CITIZEN", "TREASURE", "FIELDBOSS", "BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", 
		"SWORD", "BLUNT", "BOW", "POLE", "DAGGER", "DUAL", "FIST", "DUALFIST", 
		"FISHINGROD", "RAPIER", "ANCIENTSWORD", "CROSSBOW", "FLAG", "OWNTHING", 
		"DUALDAGGER", "LIGHT", "HEAVY", "MAGIC", "SIGIL", "RHAND", "LRHAND", "LHAND", 
		"CHEST", "LEGS", "FEET", "HEAD", "GLOVES", "ONEPIECE", "REAR", "LEAR", 
		"RFINGER", "LFINGER", "NECK", "BACK", "UNDERWEAR", "HAIR", "HAIR2", "HAIRALL", 
		"ALLDRESS", "RBRACELET", "LBRACELET", "WAIST", "DECO1", "STEEL", "FINE_STEEL", 
		"WOOD", "CLOTH", "LEATHER", "BONE", "BRONZE", "ORIHARUKON", "MITHRIL", 
		"DAMASCUS", "ADAMANTAITE", "BLOOD_STEEL", "PAPER", "GOLD", "LIQUID", "FISH", 
		"SILVER", "CHRYSOLITE", "CRYSTAL", "HORN", "SCALE_OF_DRAGON", "COTTON", 
		"DYESTUFF", "COBWEB", "RUNE_XP", "RUNE_SP", "RUNE_REMOVE_PENALTY", "NAME", 
		"WS", "LINE_COMMENT", "STAR_COMMENT"
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
	public String getGrammarFileName() { return "NpcDatas.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public NpcDatasParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public List<NpcContext> npc() {
			return getRuleContexts(NpcContext.class);
		}
		public NpcContext npc(int i) {
			return getRuleContext(NpcContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(226);
				npc();
				}
				}
				setState(229); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NpcContext extends ParserRuleContext {
		public Npc_typeContext npc_type() {
			return getRuleContext(Npc_typeContext.class,0);
		}
		public Npc_idContext npc_id() {
			return getRuleContext(Npc_idContext.class,0);
		}
		public Npc_nameContext npc_name() {
			return getRuleContext(Npc_nameContext.class,0);
		}
		public CategoryContext category() {
			return getRuleContext(CategoryContext.class,0);
		}
		public LevelContext level() {
			return getRuleContext(LevelContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public Ex_crt_effectContext ex_crt_effect() {
			return getRuleContext(Ex_crt_effectContext.class,0);
		}
		public UniqueContext unique() {
			return getRuleContext(UniqueContext.class,0);
		}
		public S_npc_prop_hp_rateContext s_npc_prop_hp_rate() {
			return getRuleContext(S_npc_prop_hp_rateContext.class,0);
		}
		public RaceContext race() {
			return getRuleContext(RaceContext.class,0);
		}
		public SexContext sex() {
			return getRuleContext(SexContext.class,0);
		}
		public Skill_listContext skill_list() {
			return getRuleContext(Skill_listContext.class,0);
		}
		public Slot_chestContext slot_chest() {
			return getRuleContext(Slot_chestContext.class,0);
		}
		public Slot_rhandContext slot_rhand() {
			return getRuleContext(Slot_rhandContext.class,0);
		}
		public Slot_lhandContext slot_lhand() {
			return getRuleContext(Slot_lhandContext.class,0);
		}
		public Collision_radiusContext collision_radius() {
			return getRuleContext(Collision_radiusContext.class,0);
		}
		public Collision_heightContext collision_height() {
			return getRuleContext(Collision_heightContext.class,0);
		}
		public Hit_time_factorContext hit_time_factor() {
			return getRuleContext(Hit_time_factorContext.class,0);
		}
		public Hit_time_factor_skillContext hit_time_factor_skill() {
			return getRuleContext(Hit_time_factor_skillContext.class,0);
		}
		public Ground_highContext ground_high() {
			return getRuleContext(Ground_highContext.class,0);
		}
		public Ground_lowContext ground_low() {
			return getRuleContext(Ground_lowContext.class,0);
		}
		public StrContext str() {
			return getRuleContext(StrContext.class,0);
		}
		public Int_Context int_() {
			return getRuleContext(Int_Context.class,0);
		}
		public DexContext dex() {
			return getRuleContext(DexContext.class,0);
		}
		public WitContext wit() {
			return getRuleContext(WitContext.class,0);
		}
		public ConContext con() {
			return getRuleContext(ConContext.class,0);
		}
		public MenContext men() {
			return getRuleContext(MenContext.class,0);
		}
		public Org_hpContext org_hp() {
			return getRuleContext(Org_hpContext.class,0);
		}
		public Org_hp_regenContext org_hp_regen() {
			return getRuleContext(Org_hp_regenContext.class,0);
		}
		public Org_mpContext org_mp() {
			return getRuleContext(Org_mpContext.class,0);
		}
		public Org_mp_regenContext org_mp_regen() {
			return getRuleContext(Org_mp_regenContext.class,0);
		}
		public Base_attack_typeContext base_attack_type() {
			return getRuleContext(Base_attack_typeContext.class,0);
		}
		public Base_attack_rangeContext base_attack_range() {
			return getRuleContext(Base_attack_rangeContext.class,0);
		}
		public Base_damage_rangeContext base_damage_range() {
			return getRuleContext(Base_damage_rangeContext.class,0);
		}
		public Base_rand_damContext base_rand_dam() {
			return getRuleContext(Base_rand_damContext.class,0);
		}
		public Base_physical_attackContext base_physical_attack() {
			return getRuleContext(Base_physical_attackContext.class,0);
		}
		public Base_criticalContext base_critical() {
			return getRuleContext(Base_criticalContext.class,0);
		}
		public Physical_hit_modifyContext physical_hit_modify() {
			return getRuleContext(Physical_hit_modifyContext.class,0);
		}
		public Base_attack_speedContext base_attack_speed() {
			return getRuleContext(Base_attack_speedContext.class,0);
		}
		public Base_reuse_delayContext base_reuse_delay() {
			return getRuleContext(Base_reuse_delayContext.class,0);
		}
		public Base_magic_attackContext base_magic_attack() {
			return getRuleContext(Base_magic_attackContext.class,0);
		}
		public Base_defendContext base_defend() {
			return getRuleContext(Base_defendContext.class,0);
		}
		public Base_magic_defendContext base_magic_defend() {
			return getRuleContext(Base_magic_defendContext.class,0);
		}
		public Base_attribute_attackContext base_attribute_attack() {
			return getRuleContext(Base_attribute_attackContext.class,0);
		}
		public Base_attribute_defendContext base_attribute_defend() {
			return getRuleContext(Base_attribute_defendContext.class,0);
		}
		public Physical_avoid_modifyContext physical_avoid_modify() {
			return getRuleContext(Physical_avoid_modifyContext.class,0);
		}
		public Shield_defense_rateContext shield_defense_rate() {
			return getRuleContext(Shield_defense_rateContext.class,0);
		}
		public Shield_defenseContext shield_defense() {
			return getRuleContext(Shield_defenseContext.class,0);
		}
		public Safe_heightContext safe_height() {
			return getRuleContext(Safe_heightContext.class,0);
		}
		public Soulshot_countContext soulshot_count() {
			return getRuleContext(Soulshot_countContext.class,0);
		}
		public Spiritshot_countContext spiritshot_count() {
			return getRuleContext(Spiritshot_countContext.class,0);
		}
		public ClanContext clan() {
			return getRuleContext(ClanContext.class,0);
		}
		public Ignore_clan_listContext ignore_clan_list() {
			return getRuleContext(Ignore_clan_listContext.class,0);
		}
		public Clan_help_rangeContext clan_help_range() {
			return getRuleContext(Clan_help_rangeContext.class,0);
		}
		public UndyingContext undying() {
			return getRuleContext(UndyingContext.class,0);
		}
		public Can_be_attackedContext can_be_attacked() {
			return getRuleContext(Can_be_attackedContext.class,0);
		}
		public Corpse_timeContext corpse_time() {
			return getRuleContext(Corpse_timeContext.class,0);
		}
		public No_sleep_modeContext no_sleep_mode() {
			return getRuleContext(No_sleep_modeContext.class,0);
		}
		public Agro_rangeContext agro_range() {
			return getRuleContext(Agro_rangeContext.class,0);
		}
		public Passable_doorContext passable_door() {
			return getRuleContext(Passable_doorContext.class,0);
		}
		public Can_moveContext can_move() {
			return getRuleContext(Can_moveContext.class,0);
		}
		public FlyingContext flying() {
			return getRuleContext(FlyingContext.class,0);
		}
		public Has_summonerContext has_summoner() {
			return getRuleContext(Has_summonerContext.class,0);
		}
		public TargetableContext targetable() {
			return getRuleContext(TargetableContext.class,0);
		}
		public Show_name_tagContext show_name_tag() {
			return getRuleContext(Show_name_tagContext.class,0);
		}
		public Abnormal_resistContext abnormal_resist() {
			return getRuleContext(Abnormal_resistContext.class,0);
		}
		public Is_death_penaltyContext is_death_penalty() {
			return getRuleContext(Is_death_penaltyContext.class,0);
		}
		public Npc_aiContext npc_ai() {
			return getRuleContext(Npc_aiContext.class,0);
		}
		public Event_flagContext event_flag() {
			return getRuleContext(Event_flagContext.class,0);
		}
		public UnsowingContext unsowing() {
			return getRuleContext(UnsowingContext.class,0);
		}
		public Private_respawn_logContext private_respawn_log() {
			return getRuleContext(Private_respawn_logContext.class,0);
		}
		public Acquire_exp_rateContext acquire_exp_rate() {
			return getRuleContext(Acquire_exp_rateContext.class,0);
		}
		public Acquire_spContext acquire_sp() {
			return getRuleContext(Acquire_spContext.class,0);
		}
		public Acquire_rpContext acquire_rp() {
			return getRuleContext(Acquire_rpContext.class,0);
		}
		public Corpse_make_listContext corpse_make_list() {
			return getRuleContext(Corpse_make_listContext.class,0);
		}
		public Additional_make_listContext additional_make_list() {
			return getRuleContext(Additional_make_listContext.class,0);
		}
		public Additional_make_multi_listContext additional_make_multi_list() {
			return getRuleContext(Additional_make_multi_listContext.class,0);
		}
		public Ex_item_drop_listContext ex_item_drop_list() {
			return getRuleContext(Ex_item_drop_listContext.class,0);
		}
		public Vitality_item_drop_listContext vitality_item_drop_list() {
			return getRuleContext(Vitality_item_drop_listContext.class,0);
		}
		public Mp_rewardContext mp_reward() {
			return getRuleContext(Mp_rewardContext.class,0);
		}
		public Fake_class_idContext fake_class_id() {
			return getRuleContext(Fake_class_idContext.class,0);
		}
		public Event_dropContext event_drop() {
			return getRuleContext(Event_dropContext.class,0);
		}
		public Ex_dropContext ex_drop() {
			return getRuleContext(Ex_dropContext.class,0);
		}
		public Enable_move_after_talkContext enable_move_after_talk() {
			return getRuleContext(Enable_move_after_talkContext.class,0);
		}
		public Broadcast_condContext broadcast_cond() {
			return getRuleContext(Broadcast_condContext.class,0);
		}
		public NpcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_npc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNpc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNpc(this);
		}
	}

	public final NpcContext npc() throws RecognitionException {
		NpcContext _localctx = new NpcContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_npc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(T__0);
			setState(232);
			npc_type();
			setState(233);
			npc_id();
			setState(234);
			npc_name();
			setState(235);
			category();
			setState(236);
			level();
			setState(237);
			exp();
			setState(238);
			ex_crt_effect();
			setState(239);
			unique();
			setState(240);
			s_npc_prop_hp_rate();
			setState(241);
			race();
			setState(242);
			sex();
			setState(243);
			skill_list();
			setState(244);
			slot_chest();
			setState(245);
			slot_rhand();
			setState(246);
			slot_lhand();
			setState(247);
			collision_radius();
			setState(248);
			collision_height();
			setState(249);
			hit_time_factor();
			setState(250);
			hit_time_factor_skill();
			setState(251);
			ground_high();
			setState(252);
			ground_low();
			setState(253);
			str();
			setState(254);
			int_();
			setState(255);
			dex();
			setState(256);
			wit();
			setState(257);
			con();
			setState(258);
			men();
			setState(259);
			org_hp();
			setState(260);
			org_hp_regen();
			setState(261);
			org_mp();
			setState(262);
			org_mp_regen();
			setState(263);
			base_attack_type();
			setState(264);
			base_attack_range();
			setState(265);
			base_damage_range();
			setState(266);
			base_rand_dam();
			setState(267);
			base_physical_attack();
			setState(268);
			base_critical();
			setState(269);
			physical_hit_modify();
			setState(270);
			base_attack_speed();
			setState(271);
			base_reuse_delay();
			setState(272);
			base_magic_attack();
			setState(273);
			base_defend();
			setState(274);
			base_magic_defend();
			setState(275);
			base_attribute_attack();
			setState(276);
			base_attribute_defend();
			setState(277);
			physical_avoid_modify();
			setState(278);
			shield_defense_rate();
			setState(279);
			shield_defense();
			setState(280);
			safe_height();
			setState(281);
			soulshot_count();
			setState(282);
			spiritshot_count();
			setState(283);
			clan();
			setState(284);
			ignore_clan_list();
			setState(285);
			clan_help_range();
			setState(286);
			undying();
			setState(287);
			can_be_attacked();
			setState(288);
			corpse_time();
			setState(289);
			no_sleep_mode();
			setState(290);
			agro_range();
			setState(291);
			passable_door();
			setState(292);
			can_move();
			setState(293);
			flying();
			setState(294);
			has_summoner();
			setState(295);
			targetable();
			setState(296);
			show_name_tag();
			setState(297);
			abnormal_resist();
			setState(298);
			is_death_penalty();
			setState(299);
			npc_ai();
			setState(300);
			event_flag();
			setState(301);
			unsowing();
			setState(302);
			private_respawn_log();
			setState(303);
			acquire_exp_rate();
			setState(304);
			acquire_sp();
			setState(305);
			acquire_rp();
			setState(306);
			corpse_make_list();
			setState(307);
			additional_make_list();
			setState(308);
			additional_make_multi_list();
			setState(309);
			ex_item_drop_list();
			setState(310);
			vitality_item_drop_list();
			setState(311);
			mp_reward();
			setState(312);
			fake_class_id();
			setState(313);
			event_drop();
			setState(314);
			ex_drop();
			setState(315);
			enable_move_after_talk();
			setState(316);
			broadcast_cond();
			setState(317);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Npc_typeContext extends ParserRuleContext {
		public NpcType value;
		public TerminalNode WARRIOR() { return getToken(NpcDatasParser.WARRIOR, 0); }
		public TerminalNode TREASURE() { return getToken(NpcDatasParser.TREASURE, 0); }
		public TerminalNode CITIZEN() { return getToken(NpcDatasParser.CITIZEN, 0); }
		public TerminalNode ZZOLDAGU() { return getToken(NpcDatasParser.ZZOLDAGU, 0); }
		public TerminalNode BOSS() { return getToken(NpcDatasParser.BOSS, 0); }
		public TerminalNode FIELDBOSS() { return getToken(NpcDatasParser.FIELDBOSS, 0); }
		public TerminalNode WORLD_TRAP() { return getToken(NpcDatasParser.WORLD_TRAP, 0); }
		public TerminalNode MERCHANT() { return getToken(NpcDatasParser.MERCHANT, 0); }
		public TerminalNode WAREHOUSE_KEEPER() { return getToken(NpcDatasParser.WAREHOUSE_KEEPER, 0); }
		public TerminalNode BLACKSMITH() { return getToken(NpcDatasParser.BLACKSMITH, 0); }
		public TerminalNode TELEPORTER() { return getToken(NpcDatasParser.TELEPORTER, 0); }
		public TerminalNode GUILD_MASTER() { return getToken(NpcDatasParser.GUILD_MASTER, 0); }
		public TerminalNode GUILD_COACH() { return getToken(NpcDatasParser.GUILD_COACH, 0); }
		public TerminalNode GUARD() { return getToken(NpcDatasParser.GUARD, 0); }
		public TerminalNode MONRACE() { return getToken(NpcDatasParser.MONRACE, 0); }
		public Npc_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_npc_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNpc_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNpc_type(this);
		}
	}

	public final Npc_typeContext npc_type() throws RecognitionException {
		Npc_typeContext _localctx = new Npc_typeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_npc_type);
		try {
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WARRIOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(WARRIOR);
				_localctx.value = NpcType.WARRIOR;
				}
				break;
			case TREASURE:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
				match(TREASURE);
				_localctx.value = NpcType.TREASURE;
				}
				break;
			case CITIZEN:
				enterOuterAlt(_localctx, 3);
				{
				setState(323);
				match(CITIZEN);
				_localctx.value = NpcType.CITIZEN;
				}
				break;
			case ZZOLDAGU:
				enterOuterAlt(_localctx, 4);
				{
				setState(325);
				match(ZZOLDAGU);
				_localctx.value = NpcType.ZZOLDAGU;
				}
				break;
			case BOSS:
				enterOuterAlt(_localctx, 5);
				{
				setState(327);
				match(BOSS);
				_localctx.value = NpcType.BOSS;
				}
				break;
			case FIELDBOSS:
				enterOuterAlt(_localctx, 6);
				{
				setState(329);
				match(FIELDBOSS);
				_localctx.value = NpcType.FIELDBOSS;
				}
				break;
			case WORLD_TRAP:
				enterOuterAlt(_localctx, 7);
				{
				setState(331);
				match(WORLD_TRAP);
				_localctx.value = NpcType.WORLD_TRAP;
				}
				break;
			case MERCHANT:
				enterOuterAlt(_localctx, 8);
				{
				setState(333);
				match(MERCHANT);
				_localctx.value = NpcType.MERCHANT;
				}
				break;
			case WAREHOUSE_KEEPER:
				enterOuterAlt(_localctx, 9);
				{
				setState(335);
				match(WAREHOUSE_KEEPER);
				_localctx.value = NpcType.WAREHOUSE_KEEPER;
				}
				break;
			case BLACKSMITH:
				enterOuterAlt(_localctx, 10);
				{
				setState(337);
				match(BLACKSMITH);
				_localctx.value = NpcType.BLACKSMITH;
				}
				break;
			case TELEPORTER:
				enterOuterAlt(_localctx, 11);
				{
				setState(339);
				match(TELEPORTER);
				_localctx.value = NpcType.TELEPORTER;
				}
				break;
			case GUILD_MASTER:
				enterOuterAlt(_localctx, 12);
				{
				setState(341);
				match(GUILD_MASTER);
				_localctx.value = NpcType.GUILD_MASTER;
				}
				break;
			case GUILD_COACH:
				enterOuterAlt(_localctx, 13);
				{
				setState(343);
				match(GUILD_COACH);
				_localctx.value = NpcType.GUILD_COACH;
				}
				break;
			case GUARD:
				enterOuterAlt(_localctx, 14);
				{
				setState(345);
				match(GUARD);
				_localctx.value = NpcType.GUARD;
				}
				break;
			case MONRACE:
				enterOuterAlt(_localctx, 15);
				{
				setState(347);
				match(MONRACE);
				_localctx.value = NpcType.MONRACE;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Npc_idContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Npc_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_npc_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNpc_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNpc_id(this);
		}
	}

	public final Npc_idContext npc_id() throws RecognitionException {
		Npc_idContext _localctx = new Npc_idContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_npc_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			((Npc_idContext)_localctx).io = int_object();
			_localctx.value = ((Npc_idContext)_localctx).io.value;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Npc_nameContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public Npc_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_npc_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNpc_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNpc_name(this);
		}
	}

	public final Npc_nameContext npc_name() throws RecognitionException {
		Npc_nameContext _localctx = new Npc_nameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_npc_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			((Npc_nameContext)_localctx).no = name_object();
			_localctx.value = ((Npc_nameContext)_localctx).no.value;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CategoryContext extends ParserRuleContext {
		public CategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCategory(this);
		}
	}

	public final CategoryContext category() throws RecognitionException {
		CategoryContext _localctx = new CategoryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_category);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			match(T__2);
			setState(358);
			match(T__3);
			setState(359);
			match(T__4);
			setState(360);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LevelContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public LevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterLevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitLevel(this);
		}
	}

	public final LevelContext level() throws RecognitionException {
		LevelContext _localctx = new LevelContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_level);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(T__6);
			setState(363);
			match(T__3);
			setState(364);
			((LevelContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Long_objectContext lo;
		public Long_objectContext long_object() {
			return getRuleContext(Long_objectContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(T__7);
			setState(367);
			match(T__3);
			setState(368);
			((ExpContext)_localctx).lo = long_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ex_crt_effectContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Ex_crt_effectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ex_crt_effect; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEx_crt_effect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEx_crt_effect(this);
		}
	}

	public final Ex_crt_effectContext ex_crt_effect() throws RecognitionException {
		Ex_crt_effectContext _localctx = new Ex_crt_effectContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ex_crt_effect);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(T__8);
			setState(371);
			match(T__3);
			setState(372);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UniqueContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public UniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterUnique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitUnique(this);
		}
	}

	public final UniqueContext unique() throws RecognitionException {
		UniqueContext _localctx = new UniqueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unique);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(T__9);
			setState(375);
			match(T__3);
			setState(376);
			((UniqueContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class S_npc_prop_hp_rateContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public S_npc_prop_hp_rateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s_npc_prop_hp_rate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterS_npc_prop_hp_rate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitS_npc_prop_hp_rate(this);
		}
	}

	public final S_npc_prop_hp_rateContext s_npc_prop_hp_rate() throws RecognitionException {
		S_npc_prop_hp_rateContext _localctx = new S_npc_prop_hp_rateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_s_npc_prop_hp_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(T__10);
			setState(379);
			match(T__3);
			setState(380);
			((S_npc_prop_hp_rateContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RaceContext extends ParserRuleContext {
		public TerminalNode FAIRY() { return getToken(NpcDatasParser.FAIRY, 0); }
		public TerminalNode ANIMAL() { return getToken(NpcDatasParser.ANIMAL, 0); }
		public TerminalNode HUMANOID() { return getToken(NpcDatasParser.HUMANOID, 0); }
		public TerminalNode PLANT() { return getToken(NpcDatasParser.PLANT, 0); }
		public TerminalNode UNDEAD() { return getToken(NpcDatasParser.UNDEAD, 0); }
		public TerminalNode CONSTRUCT() { return getToken(NpcDatasParser.CONSTRUCT, 0); }
		public TerminalNode BEAST() { return getToken(NpcDatasParser.BEAST, 0); }
		public TerminalNode BUG() { return getToken(NpcDatasParser.BUG, 0); }
		public TerminalNode ELEMENTAL() { return getToken(NpcDatasParser.ELEMENTAL, 0); }
		public TerminalNode DEMONIC() { return getToken(NpcDatasParser.DEMONIC, 0); }
		public TerminalNode DRAGON() { return getToken(NpcDatasParser.DRAGON, 0); }
		public TerminalNode GIANT() { return getToken(NpcDatasParser.GIANT, 0); }
		public TerminalNode DIVINE() { return getToken(NpcDatasParser.DIVINE, 0); }
		public TerminalNode ETC() { return getToken(NpcDatasParser.ETC, 0); }
		public TerminalNode SUMMON() { return getToken(NpcDatasParser.SUMMON, 0); }
		public TerminalNode PET() { return getToken(NpcDatasParser.PET, 0); }
		public TerminalNode HOLYTHING() { return getToken(NpcDatasParser.HOLYTHING, 0); }
		public TerminalNode DWARF() { return getToken(NpcDatasParser.DWARF, 0); }
		public TerminalNode MERCHANT() { return getToken(NpcDatasParser.MERCHANT, 0); }
		public TerminalNode ELF() { return getToken(NpcDatasParser.ELF, 0); }
		public TerminalNode KAMAEL() { return getToken(NpcDatasParser.KAMAEL, 0); }
		public TerminalNode ORC() { return getToken(NpcDatasParser.ORC, 0); }
		public TerminalNode MERCENARY() { return getToken(NpcDatasParser.MERCENARY, 0); }
		public TerminalNode CASTLE_GUARD() { return getToken(NpcDatasParser.CASTLE_GUARD, 0); }
		public TerminalNode HUMAN() { return getToken(NpcDatasParser.HUMAN, 0); }
		public TerminalNode BOSS() { return getToken(NpcDatasParser.BOSS, 0); }
		public TerminalNode ZZOLDAGU() { return getToken(NpcDatasParser.ZZOLDAGU, 0); }
		public TerminalNode WORLD_TRAP() { return getToken(NpcDatasParser.WORLD_TRAP, 0); }
		public TerminalNode MONRACE() { return getToken(NpcDatasParser.MONRACE, 0); }
		public TerminalNode GUARD() { return getToken(NpcDatasParser.GUARD, 0); }
		public TerminalNode TELEPORTER() { return getToken(NpcDatasParser.TELEPORTER, 0); }
		public TerminalNode WAREHOUSE_KEEPER() { return getToken(NpcDatasParser.WAREHOUSE_KEEPER, 0); }
		public TerminalNode DARKELF() { return getToken(NpcDatasParser.DARKELF, 0); }
		public RaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_race; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterRace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitRace(this);
		}
	}

	public final RaceContext race() throws RecognitionException {
		RaceContext _localctx = new RaceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_race);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(T__11);
			setState(383);
			match(T__3);
			setState(384);
			_la = _input.LA(1);
			if ( !(((((_la - 92)) & ~0x3f) == 0 && ((1L << (_la - 92)) & ((1L << (ETC - 92)) | (1L << (FAIRY - 92)) | (1L << (ANIMAL - 92)) | (1L << (HUMANOID - 92)) | (1L << (PLANT - 92)) | (1L << (UNDEAD - 92)) | (1L << (CONSTRUCT - 92)) | (1L << (BEAST - 92)) | (1L << (BUG - 92)) | (1L << (ELEMENTAL - 92)) | (1L << (DEMONIC - 92)) | (1L << (GIANT - 92)) | (1L << (DRAGON - 92)) | (1L << (DIVINE - 92)) | (1L << (SUMMON - 92)) | (1L << (PET - 92)) | (1L << (HOLYTHING - 92)) | (1L << (DWARF - 92)) | (1L << (MERCHANT - 92)) | (1L << (ELF - 92)) | (1L << (KAMAEL - 92)) | (1L << (ORC - 92)) | (1L << (MERCENARY - 92)) | (1L << (CASTLE_GUARD - 92)) | (1L << (HUMAN - 92)) | (1L << (BOSS - 92)) | (1L << (ZZOLDAGU - 92)) | (1L << (WORLD_TRAP - 92)) | (1L << (MONRACE - 92)) | (1L << (DARKELF - 92)) | (1L << (GUARD - 92)) | (1L << (TELEPORTER - 92)) | (1L << (WAREHOUSE_KEEPER - 92)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SexContext extends ParserRuleContext {
		public TerminalNode MALE() { return getToken(NpcDatasParser.MALE, 0); }
		public TerminalNode FEMALE() { return getToken(NpcDatasParser.FEMALE, 0); }
		public TerminalNode ETC() { return getToken(NpcDatasParser.ETC, 0); }
		public SexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSex(this);
		}
	}

	public final SexContext sex() throws RecognitionException {
		SexContext _localctx = new SexContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			match(T__12);
			setState(387);
			match(T__3);
			setState(388);
			_la = _input.LA(1);
			if ( !(((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (MALE - 90)) | (1L << (FEMALE - 90)) | (1L << (ETC - 90)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Skill_listContext extends ParserRuleContext {
		public Category_listContext category_list() {
			return getRuleContext(Category_listContext.class,0);
		}
		public Skill_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skill_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSkill_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSkill_list(this);
		}
	}

	public final Skill_listContext skill_list() throws RecognitionException {
		Skill_listContext _localctx = new Skill_listContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_skill_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			match(T__13);
			setState(391);
			match(T__3);
			setState(392);
			category_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_chestContext extends ParserRuleContext {
		public Empty_name_objectContext empty_name_object() {
			return getRuleContext(Empty_name_objectContext.class,0);
		}
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public Slot_chestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slot_chest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSlot_chest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSlot_chest(this);
		}
	}

	public final Slot_chestContext slot_chest() throws RecognitionException {
		Slot_chestContext _localctx = new Slot_chestContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_slot_chest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			match(T__14);
			setState(395);
			match(T__3);
			setState(398);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(396);
				empty_name_object();
				}
				break;
			case 2:
				{
				setState(397);
				name_object();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_rhandContext extends ParserRuleContext {
		public Empty_name_objectContext empty_name_object() {
			return getRuleContext(Empty_name_objectContext.class,0);
		}
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public Slot_rhandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slot_rhand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSlot_rhand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSlot_rhand(this);
		}
	}

	public final Slot_rhandContext slot_rhand() throws RecognitionException {
		Slot_rhandContext _localctx = new Slot_rhandContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_slot_rhand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(T__15);
			setState(401);
			match(T__3);
			setState(404);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(402);
				empty_name_object();
				}
				break;
			case 2:
				{
				setState(403);
				name_object();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Slot_lhandContext extends ParserRuleContext {
		public Empty_name_objectContext empty_name_object() {
			return getRuleContext(Empty_name_objectContext.class,0);
		}
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public Slot_lhandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slot_lhand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSlot_lhand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSlot_lhand(this);
		}
	}

	public final Slot_lhandContext slot_lhand() throws RecognitionException {
		Slot_lhandContext _localctx = new Slot_lhandContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_slot_lhand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(T__16);
			setState(407);
			match(T__3);
			setState(410);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(408);
				empty_name_object();
				}
				break;
			case 2:
				{
				setState(409);
				name_object();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Empty_name_objectContext extends ParserRuleContext {
		public Empty_name_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty_name_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEmpty_name_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEmpty_name_object(this);
		}
	}

	public final Empty_name_objectContext empty_name_object() throws RecognitionException {
		Empty_name_objectContext _localctx = new Empty_name_objectContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_empty_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			match(T__17);
			setState(413);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Collision_radiusContext extends ParserRuleContext {
		public Double_listContext double_list() {
			return getRuleContext(Double_listContext.class,0);
		}
		public Collision_radiusContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collision_radius; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCollision_radius(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCollision_radius(this);
		}
	}

	public final Collision_radiusContext collision_radius() throws RecognitionException {
		Collision_radiusContext _localctx = new Collision_radiusContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_collision_radius);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(T__19);
			setState(416);
			match(T__3);
			setState(417);
			double_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Collision_heightContext extends ParserRuleContext {
		public Double_listContext double_list() {
			return getRuleContext(Double_listContext.class,0);
		}
		public Collision_heightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collision_height; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCollision_height(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCollision_height(this);
		}
	}

	public final Collision_heightContext collision_height() throws RecognitionException {
		Collision_heightContext _localctx = new Collision_heightContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_collision_height);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(T__20);
			setState(420);
			match(T__3);
			setState(421);
			double_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Hit_time_factorContext extends ParserRuleContext {
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Hit_time_factorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hit_time_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterHit_time_factor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitHit_time_factor(this);
		}
	}

	public final Hit_time_factorContext hit_time_factor() throws RecognitionException {
		Hit_time_factorContext _localctx = new Hit_time_factorContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_hit_time_factor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(T__21);
			setState(424);
			match(T__3);
			setState(425);
			double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Hit_time_factor_skillContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Hit_time_factor_skillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hit_time_factor_skill; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterHit_time_factor_skill(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitHit_time_factor_skill(this);
		}
	}

	public final Hit_time_factor_skillContext hit_time_factor_skill() throws RecognitionException {
		Hit_time_factor_skillContext _localctx = new Hit_time_factor_skillContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_hit_time_factor_skill);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			match(T__22);
			setState(428);
			match(T__3);
			setState(429);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ground_highContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Ground_highContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ground_high; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterGround_high(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitGround_high(this);
		}
	}

	public final Ground_highContext ground_high() throws RecognitionException {
		Ground_highContext _localctx = new Ground_highContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_ground_high);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(T__23);
			setState(432);
			match(T__3);
			setState(433);
			int_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ground_lowContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Ground_lowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ground_low; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterGround_low(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitGround_low(this);
		}
	}

	public final Ground_lowContext ground_low() throws RecognitionException {
		Ground_lowContext _localctx = new Ground_lowContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ground_low);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(T__24);
			setState(436);
			match(T__3);
			setState(437);
			int_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public StrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_str; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterStr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitStr(this);
		}
	}

	public final StrContext str() throws RecognitionException {
		StrContext _localctx = new StrContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_str);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			match(T__25);
			setState(440);
			match(T__3);
			setState(441);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_Context extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Int_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterInt_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitInt_(this);
		}
	}

	public final Int_Context int_() throws RecognitionException {
		Int_Context _localctx = new Int_Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_int_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(443);
			match(T__26);
			setState(444);
			match(T__3);
			setState(445);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DexContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public DexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterDex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitDex(this);
		}
	}

	public final DexContext dex() throws RecognitionException {
		DexContext _localctx = new DexContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_dex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			match(T__27);
			setState(448);
			match(T__3);
			setState(449);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WitContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public WitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterWit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitWit(this);
		}
	}

	public final WitContext wit() throws RecognitionException {
		WitContext _localctx = new WitContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_wit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(T__28);
			setState(452);
			match(T__3);
			setState(453);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public ConContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_con; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCon(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCon(this);
		}
	}

	public final ConContext con() throws RecognitionException {
		ConContext _localctx = new ConContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_con);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455);
			match(T__29);
			setState(456);
			match(T__3);
			setState(457);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MenContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public MenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_men; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterMen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitMen(this);
		}
	}

	public final MenContext men() throws RecognitionException {
		MenContext _localctx = new MenContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_men);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(459);
			match(T__30);
			setState(460);
			match(T__3);
			setState(461);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Org_hpContext extends ParserRuleContext {
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Org_hpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_org_hp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterOrg_hp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitOrg_hp(this);
		}
	}

	public final Org_hpContext org_hp() throws RecognitionException {
		Org_hpContext _localctx = new Org_hpContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_org_hp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(T__31);
			setState(464);
			match(T__3);
			setState(465);
			double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Org_hp_regenContext extends ParserRuleContext {
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Org_hp_regenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_org_hp_regen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterOrg_hp_regen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitOrg_hp_regen(this);
		}
	}

	public final Org_hp_regenContext org_hp_regen() throws RecognitionException {
		Org_hp_regenContext _localctx = new Org_hp_regenContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_org_hp_regen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			match(T__32);
			setState(468);
			match(T__3);
			setState(469);
			double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Org_mpContext extends ParserRuleContext {
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Org_mpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_org_mp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterOrg_mp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitOrg_mp(this);
		}
	}

	public final Org_mpContext org_mp() throws RecognitionException {
		Org_mpContext _localctx = new Org_mpContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_org_mp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			match(T__33);
			setState(472);
			match(T__3);
			setState(473);
			double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Org_mp_regenContext extends ParserRuleContext {
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Org_mp_regenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_org_mp_regen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterOrg_mp_regen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitOrg_mp_regen(this);
		}
	}

	public final Org_mp_regenContext org_mp_regen() throws RecognitionException {
		Org_mp_regenContext _localctx = new Org_mp_regenContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_org_mp_regen);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			match(T__34);
			setState(476);
			match(T__3);
			setState(477);
			double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attack_typeContext extends ParserRuleContext {
		public Attack_typeContext attack_type() {
			return getRuleContext(Attack_typeContext.class,0);
		}
		public Base_attack_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_attack_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_attack_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_attack_type(this);
		}
	}

	public final Base_attack_typeContext base_attack_type() throws RecognitionException {
		Base_attack_typeContext _localctx = new Base_attack_typeContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_base_attack_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			match(T__35);
			setState(480);
			match(T__3);
			setState(481);
			attack_type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attack_typeContext extends ParserRuleContext {
		public TerminalNode SWORD() { return getToken(NpcDatasParser.SWORD, 0); }
		public TerminalNode BLUNT() { return getToken(NpcDatasParser.BLUNT, 0); }
		public TerminalNode BOW() { return getToken(NpcDatasParser.BOW, 0); }
		public TerminalNode DAGGER() { return getToken(NpcDatasParser.DAGGER, 0); }
		public TerminalNode FIST() { return getToken(NpcDatasParser.FIST, 0); }
		public TerminalNode POLE() { return getToken(NpcDatasParser.POLE, 0); }
		public TerminalNode DUAL() { return getToken(NpcDatasParser.DUAL, 0); }
		public TerminalNode DUALFIST() { return getToken(NpcDatasParser.DUALFIST, 0); }
		public Attack_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAttack_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAttack_type(this);
		}
	}

	public final Attack_typeContext attack_type() throws RecognitionException {
		Attack_typeContext _localctx = new Attack_typeContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_attack_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			_la = _input.LA(1);
			if ( !(((((_la - 144)) & ~0x3f) == 0 && ((1L << (_la - 144)) & ((1L << (SWORD - 144)) | (1L << (BLUNT - 144)) | (1L << (BOW - 144)) | (1L << (POLE - 144)) | (1L << (DAGGER - 144)) | (1L << (DUAL - 144)) | (1L << (FIST - 144)) | (1L << (DUALFIST - 144)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attack_rangeContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Base_attack_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_attack_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_attack_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_attack_range(this);
		}
	}

	public final Base_attack_rangeContext base_attack_range() throws RecognitionException {
		Base_attack_rangeContext _localctx = new Base_attack_rangeContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_base_attack_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			match(T__36);
			setState(486);
			match(T__3);
			setState(487);
			((Base_attack_rangeContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_damage_rangeContext extends ParserRuleContext {
		public Int_listContext il;
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Base_damage_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_damage_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_damage_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_damage_range(this);
		}
	}

	public final Base_damage_rangeContext base_damage_range() throws RecognitionException {
		Base_damage_rangeContext _localctx = new Base_damage_rangeContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_base_damage_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			match(T__37);
			setState(490);
			match(T__3);
			setState(491);
			((Base_damage_rangeContext)_localctx).il = int_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_rand_damContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Base_rand_damContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_rand_dam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_rand_dam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_rand_dam(this);
		}
	}

	public final Base_rand_damContext base_rand_dam() throws RecognitionException {
		Base_rand_damContext _localctx = new Base_rand_damContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_base_rand_dam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(493);
			match(T__38);
			setState(494);
			match(T__3);
			setState(495);
			((Base_rand_damContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_physical_attackContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Base_physical_attackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_physical_attack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_physical_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_physical_attack(this);
		}
	}

	public final Base_physical_attackContext base_physical_attack() throws RecognitionException {
		Base_physical_attackContext _localctx = new Base_physical_attackContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_base_physical_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			match(T__39);
			setState(498);
			match(T__3);
			setState(499);
			((Base_physical_attackContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_criticalContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Base_criticalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_critical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_critical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_critical(this);
		}
	}

	public final Base_criticalContext base_critical() throws RecognitionException {
		Base_criticalContext _localctx = new Base_criticalContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_base_critical);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(501);
			match(T__40);
			setState(502);
			match(T__3);
			setState(503);
			((Base_criticalContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Physical_hit_modifyContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Physical_hit_modifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_physical_hit_modify; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterPhysical_hit_modify(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitPhysical_hit_modify(this);
		}
	}

	public final Physical_hit_modifyContext physical_hit_modify() throws RecognitionException {
		Physical_hit_modifyContext _localctx = new Physical_hit_modifyContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_physical_hit_modify);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			match(T__41);
			setState(506);
			match(T__3);
			setState(507);
			((Physical_hit_modifyContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attack_speedContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Base_attack_speedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_attack_speed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_attack_speed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_attack_speed(this);
		}
	}

	public final Base_attack_speedContext base_attack_speed() throws RecognitionException {
		Base_attack_speedContext _localctx = new Base_attack_speedContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_base_attack_speed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			match(T__42);
			setState(510);
			match(T__3);
			setState(511);
			((Base_attack_speedContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_reuse_delayContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Base_reuse_delayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_reuse_delay; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_reuse_delay(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_reuse_delay(this);
		}
	}

	public final Base_reuse_delayContext base_reuse_delay() throws RecognitionException {
		Base_reuse_delayContext _localctx = new Base_reuse_delayContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_base_reuse_delay);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(513);
			match(T__43);
			setState(514);
			match(T__3);
			setState(515);
			((Base_reuse_delayContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_magic_attackContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Base_magic_attackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_magic_attack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_magic_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_magic_attack(this);
		}
	}

	public final Base_magic_attackContext base_magic_attack() throws RecognitionException {
		Base_magic_attackContext _localctx = new Base_magic_attackContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_base_magic_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			match(T__44);
			setState(518);
			match(T__3);
			setState(519);
			((Base_magic_attackContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_defendContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Base_defendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_defend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_defend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_defend(this);
		}
	}

	public final Base_defendContext base_defend() throws RecognitionException {
		Base_defendContext _localctx = new Base_defendContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_base_defend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			match(T__45);
			setState(522);
			match(T__3);
			setState(523);
			((Base_defendContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_magic_defendContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Base_magic_defendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_magic_defend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_magic_defend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_magic_defend(this);
		}
	}

	public final Base_magic_defendContext base_magic_defend() throws RecognitionException {
		Base_magic_defendContext _localctx = new Base_magic_defendContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_base_magic_defend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(525);
			match(T__46);
			setState(526);
			match(T__3);
			setState(527);
			((Base_magic_defendContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attribute_defendContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_listContext il;
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Base_attribute_defendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_attribute_defend; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_attribute_defend(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_attribute_defend(this);
		}
	}

	public final Base_attribute_defendContext base_attribute_defend() throws RecognitionException {
		Base_attribute_defendContext _localctx = new Base_attribute_defendContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_base_attribute_defend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(529);
			match(T__47);
			setState(530);
			match(T__3);
			setState(531);
			((Base_attribute_defendContext)_localctx).il = int_list();
			_localctx.value = ((Base_attribute_defendContext)_localctx).il.value;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Physical_avoid_modifyContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Physical_avoid_modifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_physical_avoid_modify; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterPhysical_avoid_modify(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitPhysical_avoid_modify(this);
		}
	}

	public final Physical_avoid_modifyContext physical_avoid_modify() throws RecognitionException {
		Physical_avoid_modifyContext _localctx = new Physical_avoid_modifyContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_physical_avoid_modify);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			match(T__48);
			setState(535);
			match(T__3);
			setState(536);
			((Physical_avoid_modifyContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shield_defense_rateContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Shield_defense_rateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shield_defense_rate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterShield_defense_rate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitShield_defense_rate(this);
		}
	}

	public final Shield_defense_rateContext shield_defense_rate() throws RecognitionException {
		Shield_defense_rateContext _localctx = new Shield_defense_rateContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_shield_defense_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			match(T__49);
			setState(539);
			match(T__3);
			setState(540);
			((Shield_defense_rateContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shield_defenseContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Shield_defenseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shield_defense; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterShield_defense(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitShield_defense(this);
		}
	}

	public final Shield_defenseContext shield_defense() throws RecognitionException {
		Shield_defenseContext _localctx = new Shield_defenseContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_shield_defense);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			match(T__50);
			setState(543);
			match(T__3);
			setState(544);
			((Shield_defenseContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Safe_heightContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Safe_heightContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_safe_height; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSafe_height(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSafe_height(this);
		}
	}

	public final Safe_heightContext safe_height() throws RecognitionException {
		Safe_heightContext _localctx = new Safe_heightContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_safe_height);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			match(T__51);
			setState(547);
			match(T__3);
			setState(548);
			((Safe_heightContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Soulshot_countContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Soulshot_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_soulshot_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSoulshot_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSoulshot_count(this);
		}
	}

	public final Soulshot_countContext soulshot_count() throws RecognitionException {
		Soulshot_countContext _localctx = new Soulshot_countContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_soulshot_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(T__52);
			setState(551);
			match(T__3);
			setState(552);
			((Soulshot_countContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Spiritshot_countContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Spiritshot_countContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spiritshot_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterSpiritshot_count(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitSpiritshot_count(this);
		}
	}

	public final Spiritshot_countContext spiritshot_count() throws RecognitionException {
		Spiritshot_countContext _localctx = new Spiritshot_countContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_spiritshot_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554);
			match(T__53);
			setState(555);
			match(T__3);
			setState(556);
			((Spiritshot_countContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClanContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Category_listContext category_list() {
			return getRuleContext(Category_listContext.class,0);
		}
		public ClanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterClan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitClan(this);
		}
	}

	public final ClanContext clan() throws RecognitionException {
		ClanContext _localctx = new ClanContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_clan);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			match(T__54);
			setState(559);
			match(T__3);
			setState(565);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(560);
				match(T__4);
				setState(561);
				int_object();
				setState(562);
				match(T__5);
				}
				break;
			case 2:
				{
				setState(564);
				category_list();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ignore_clan_listContext extends ParserRuleContext {
		public Category_listContext category_list() {
			return getRuleContext(Category_listContext.class,0);
		}
		public Ignore_clan_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ignore_clan_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterIgnore_clan_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitIgnore_clan_list(this);
		}
	}

	public final Ignore_clan_listContext ignore_clan_list() throws RecognitionException {
		Ignore_clan_listContext _localctx = new Ignore_clan_listContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_ignore_clan_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(567);
			match(T__55);
			setState(568);
			match(T__3);
			setState(569);
			category_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Clan_help_rangeContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Clan_help_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clan_help_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterClan_help_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitClan_help_range(this);
		}
	}

	public final Clan_help_rangeContext clan_help_range() throws RecognitionException {
		Clan_help_rangeContext _localctx = new Clan_help_rangeContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_clan_help_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			match(T__56);
			setState(572);
			match(T__3);
			setState(573);
			((Clan_help_rangeContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UndyingContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public UndyingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_undying; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterUndying(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitUndying(this);
		}
	}

	public final UndyingContext undying() throws RecognitionException {
		UndyingContext _localctx = new UndyingContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_undying);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(575);
			match(T__57);
			setState(576);
			match(T__3);
			setState(577);
			((UndyingContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Can_be_attackedContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Can_be_attackedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_can_be_attacked; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCan_be_attacked(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCan_be_attacked(this);
		}
	}

	public final Can_be_attackedContext can_be_attacked() throws RecognitionException {
		Can_be_attackedContext _localctx = new Can_be_attackedContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_can_be_attacked);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(579);
			match(T__58);
			setState(580);
			match(T__3);
			setState(581);
			((Can_be_attackedContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Corpse_timeContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Corpse_timeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_corpse_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCorpse_time(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCorpse_time(this);
		}
	}

	public final Corpse_timeContext corpse_time() throws RecognitionException {
		Corpse_timeContext _localctx = new Corpse_timeContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_corpse_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583);
			match(T__59);
			setState(584);
			match(T__3);
			setState(585);
			((Corpse_timeContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class No_sleep_modeContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public No_sleep_modeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_no_sleep_mode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNo_sleep_mode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNo_sleep_mode(this);
		}
	}

	public final No_sleep_modeContext no_sleep_mode() throws RecognitionException {
		No_sleep_modeContext _localctx = new No_sleep_modeContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_no_sleep_mode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587);
			match(T__60);
			setState(588);
			match(T__3);
			setState(589);
			((No_sleep_modeContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Agro_rangeContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Agro_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agro_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAgro_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAgro_range(this);
		}
	}

	public final Agro_rangeContext agro_range() throws RecognitionException {
		Agro_rangeContext _localctx = new Agro_rangeContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_agro_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(591);
			match(T__61);
			setState(592);
			match(T__3);
			setState(593);
			((Agro_rangeContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Passable_doorContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Passable_doorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passable_door; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterPassable_door(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitPassable_door(this);
		}
	}

	public final Passable_doorContext passable_door() throws RecognitionException {
		Passable_doorContext _localctx = new Passable_doorContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_passable_door);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			match(T__62);
			setState(596);
			match(T__3);
			setState(597);
			((Passable_doorContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Can_moveContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Can_moveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_can_move; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCan_move(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCan_move(this);
		}
	}

	public final Can_moveContext can_move() throws RecognitionException {
		Can_moveContext _localctx = new Can_moveContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_can_move);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(599);
			match(T__63);
			setState(600);
			match(T__3);
			setState(601);
			((Can_moveContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FlyingContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public FlyingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flying; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterFlying(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitFlying(this);
		}
	}

	public final FlyingContext flying() throws RecognitionException {
		FlyingContext _localctx = new FlyingContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_flying);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(603);
			match(T__64);
			setState(604);
			match(T__3);
			setState(605);
			((FlyingContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Has_summonerContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Has_summonerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_has_summoner; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterHas_summoner(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitHas_summoner(this);
		}
	}

	public final Has_summonerContext has_summoner() throws RecognitionException {
		Has_summonerContext _localctx = new Has_summonerContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_has_summoner);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607);
			match(T__65);
			setState(608);
			match(T__3);
			setState(609);
			((Has_summonerContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TargetableContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public TargetableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_targetable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterTargetable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitTargetable(this);
		}
	}

	public final TargetableContext targetable() throws RecognitionException {
		TargetableContext _localctx = new TargetableContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_targetable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611);
			match(T__66);
			setState(612);
			match(T__3);
			setState(613);
			((TargetableContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_name_tagContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Show_name_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_name_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterShow_name_tag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitShow_name_tag(this);
		}
	}

	public final Show_name_tagContext show_name_tag() throws RecognitionException {
		Show_name_tagContext _localctx = new Show_name_tagContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_show_name_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615);
			match(T__67);
			setState(616);
			match(T__3);
			setState(617);
			((Show_name_tagContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Abnormal_resistContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Abnormal_resistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abnormal_resist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAbnormal_resist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAbnormal_resist(this);
		}
	}

	public final Abnormal_resistContext abnormal_resist() throws RecognitionException {
		Abnormal_resistContext _localctx = new Abnormal_resistContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_abnormal_resist);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			match(T__68);
			setState(620);
			match(T__3);
			setState(621);
			int_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Is_death_penaltyContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Is_death_penaltyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_is_death_penalty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterIs_death_penalty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitIs_death_penalty(this);
		}
	}

	public final Is_death_penaltyContext is_death_penalty() throws RecognitionException {
		Is_death_penaltyContext _localctx = new Is_death_penaltyContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_is_death_penalty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(623);
			match(T__69);
			setState(624);
			match(T__3);
			setState(625);
			((Is_death_penaltyContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Npc_aiContext extends ParserRuleContext {
		public Name_objectContext no;
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public List<Ai_paramContext> ai_param() {
			return getRuleContexts(Ai_paramContext.class);
		}
		public Ai_paramContext ai_param(int i) {
			return getRuleContext(Ai_paramContext.class,i);
		}
		public Npc_aiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_npc_ai; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNpc_ai(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNpc_ai(this);
		}
	}

	public final Npc_aiContext npc_ai() throws RecognitionException {
		Npc_aiContext _localctx = new Npc_aiContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_npc_ai);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(627);
			match(T__70);
			setState(628);
			match(T__3);
			setState(629);
			match(T__4);
			setState(630);
			((Npc_aiContext)_localctx).no = name_object();
			setState(635);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(631);
				match(SEMICOLON);
				setState(632);
				ai_param();
				}
				}
				setState(637);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(638);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ai_paramContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public Param_valueContext param_value() {
			return getRuleContext(Param_valueContext.class,0);
		}
		public Ai_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ai_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAi_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAi_param(this);
		}
	}

	public final Ai_paramContext ai_param() throws RecognitionException {
		Ai_paramContext _localctx = new Ai_paramContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_ai_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			match(T__4);
			setState(641);
			name_object();
			setState(642);
			match(T__3);
			setState(643);
			param_value();
			setState(644);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_valueContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Category_objectContext category_object() {
			return getRuleContext(Category_objectContext.class,0);
		}
		public Npc_privatesContext npc_privates() {
			return getRuleContext(Npc_privatesContext.class,0);
		}
		public Fstring_objectContext fstring_object() {
			return getRuleContext(Fstring_objectContext.class,0);
		}
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Identifier_objectContext identifier_object() {
			return getRuleContext(Identifier_objectContext.class,0);
		}
		public Param_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterParam_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitParam_value(this);
		}
	}

	public final Param_valueContext param_value() throws RecognitionException {
		Param_valueContext _localctx = new Param_valueContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_param_value);
		try {
			setState(652);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(646);
				int_object();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(647);
				category_object();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(648);
				npc_privates();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(649);
				fstring_object();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(650);
				double_object();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(651);
				identifier_object();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Npc_privatesContext extends ParserRuleContext {
		public List<Identifier_objectContext> identifier_object() {
			return getRuleContexts(Identifier_objectContext.class);
		}
		public Identifier_objectContext identifier_object(int i) {
			return getRuleContext(Identifier_objectContext.class,i);
		}
		public Npc_privatesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_npc_privates; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterNpc_privates(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitNpc_privates(this);
		}
	}

	public final Npc_privatesContext npc_privates() throws RecognitionException {
		Npc_privatesContext _localctx = new Npc_privatesContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_npc_privates);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			match(T__17);
			setState(655);
			identifier_object();
			setState(660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(656);
				match(SEMICOLON);
				setState(657);
				identifier_object();
				}
				}
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(663);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fstring_objectContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Fstring_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fstring_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterFstring_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitFstring_object(this);
		}
	}

	public final Fstring_objectContext fstring_object() throws RecognitionException {
		Fstring_objectContext _localctx = new Fstring_objectContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_fstring_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(665);
			match(T__17);
			setState(666);
			((Fstring_objectContext)_localctx).io = int_object();
			setState(667);
			match(T__18);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Event_flagContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Event_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEvent_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEvent_flag(this);
		}
	}

	public final Event_flagContext event_flag() throws RecognitionException {
		Event_flagContext _localctx = new Event_flagContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_event_flag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			match(T__71);
			setState(670);
			match(T__3);
			setState(671);
			match(T__4);
			setState(672);
			int_object();
			setState(673);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnsowingContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public UnsowingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsowing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterUnsowing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitUnsowing(this);
		}
	}

	public final UnsowingContext unsowing() throws RecognitionException {
		UnsowingContext _localctx = new UnsowingContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_unsowing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(675);
			match(T__72);
			setState(676);
			match(T__3);
			setState(677);
			((UnsowingContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Private_respawn_logContext extends ParserRuleContext {
		public Bool_objectContext bo;
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Private_respawn_logContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_private_respawn_log; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterPrivate_respawn_log(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitPrivate_respawn_log(this);
		}
	}

	public final Private_respawn_logContext private_respawn_log() throws RecognitionException {
		Private_respawn_logContext _localctx = new Private_respawn_logContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_private_respawn_log);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(679);
			match(T__73);
			setState(680);
			match(T__3);
			setState(681);
			((Private_respawn_logContext)_localctx).bo = bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Acquire_exp_rateContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Acquire_exp_rateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acquire_exp_rate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAcquire_exp_rate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAcquire_exp_rate(this);
		}
	}

	public final Acquire_exp_rateContext acquire_exp_rate() throws RecognitionException {
		Acquire_exp_rateContext _localctx = new Acquire_exp_rateContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_acquire_exp_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(683);
			match(T__74);
			setState(684);
			match(T__3);
			setState(685);
			((Acquire_exp_rateContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Acquire_spContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Acquire_spContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acquire_sp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAcquire_sp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAcquire_sp(this);
		}
	}

	public final Acquire_spContext acquire_sp() throws RecognitionException {
		Acquire_spContext _localctx = new Acquire_spContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_acquire_sp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(687);
			match(T__75);
			setState(688);
			match(T__3);
			setState(689);
			((Acquire_spContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Acquire_rpContext extends ParserRuleContext {
		public Double_objectContext d;
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Acquire_rpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_acquire_rp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAcquire_rp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAcquire_rp(this);
		}
	}

	public final Acquire_rpContext acquire_rp() throws RecognitionException {
		Acquire_rpContext _localctx = new Acquire_rpContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_acquire_rp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(691);
			match(T__76);
			setState(692);
			match(T__3);
			setState(693);
			((Acquire_rpContext)_localctx).d = double_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Corpse_make_listContext extends ParserRuleContext {
		public Make_item_listContext make_item_list() {
			return getRuleContext(Make_item_listContext.class,0);
		}
		public Corpse_make_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_corpse_make_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCorpse_make_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCorpse_make_list(this);
		}
	}

	public final Corpse_make_listContext corpse_make_list() throws RecognitionException {
		Corpse_make_listContext _localctx = new Corpse_make_listContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_corpse_make_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(695);
			match(T__77);
			setState(696);
			match(T__3);
			setState(697);
			make_item_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Make_item_listContext extends ParserRuleContext {
		public List<Make_itemContext> make_item() {
			return getRuleContexts(Make_itemContext.class);
		}
		public Make_itemContext make_item(int i) {
			return getRuleContext(Make_itemContext.class,i);
		}
		public Make_item_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_make_item_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterMake_item_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitMake_item_list(this);
		}
	}

	public final Make_item_listContext make_item_list() throws RecognitionException {
		Make_item_listContext _localctx = new Make_item_listContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_make_item_list);
		int _la;
		try {
			setState(712);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(699);
				match(T__4);
				setState(700);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(701);
				match(T__4);
				setState(702);
				make_item();
				setState(707);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(703);
					match(SEMICOLON);
					setState(704);
					make_item();
					}
					}
					setState(709);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(710);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Make_itemContext extends ParserRuleContext {
		public Int_objectContext min;
		public Int_objectContext max;
		public Double_objectContext chance;
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}
		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class,i);
		}
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public Make_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_make_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterMake_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitMake_item(this);
		}
	}

	public final Make_itemContext make_item() throws RecognitionException {
		Make_itemContext _localctx = new Make_itemContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_make_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(714);
			match(T__4);
			setState(715);
			name_object();
			setState(716);
			match(SEMICOLON);
			setState(717);
			((Make_itemContext)_localctx).min = int_object();
			setState(718);
			match(SEMICOLON);
			setState(719);
			((Make_itemContext)_localctx).max = int_object();
			setState(720);
			match(SEMICOLON);
			setState(721);
			((Make_itemContext)_localctx).chance = double_object();
			setState(722);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Additional_make_listContext extends ParserRuleContext {
		public Make_item_listContext make_item_list() {
			return getRuleContext(Make_item_listContext.class,0);
		}
		public Additional_make_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additional_make_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAdditional_make_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAdditional_make_list(this);
		}
	}

	public final Additional_make_listContext additional_make_list() throws RecognitionException {
		Additional_make_listContext _localctx = new Additional_make_listContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_additional_make_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			match(T__78);
			setState(725);
			match(T__3);
			setState(726);
			make_item_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Additional_make_multi_listContext extends ParserRuleContext {
		public Make_group_listContext make_group_list() {
			return getRuleContext(Make_group_listContext.class,0);
		}
		public Additional_make_multi_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additional_make_multi_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAdditional_make_multi_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAdditional_make_multi_list(this);
		}
	}

	public final Additional_make_multi_listContext additional_make_multi_list() throws RecognitionException {
		Additional_make_multi_listContext _localctx = new Additional_make_multi_listContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_additional_make_multi_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			match(T__79);
			setState(729);
			match(T__3);
			setState(730);
			make_group_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Make_group_listContext extends ParserRuleContext {
		public Double_objectContext gc;
		public List<Make_item_listContext> make_item_list() {
			return getRuleContexts(Make_item_listContext.class);
		}
		public Make_item_listContext make_item_list(int i) {
			return getRuleContext(Make_item_listContext.class,i);
		}
		public List<Double_objectContext> double_object() {
			return getRuleContexts(Double_objectContext.class);
		}
		public Double_objectContext double_object(int i) {
			return getRuleContext(Double_objectContext.class,i);
		}
		public Make_group_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_make_group_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterMake_group_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitMake_group_list(this);
		}
	}

	public final Make_group_listContext make_group_list() throws RecognitionException {
		Make_group_listContext _localctx = new Make_group_listContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_make_group_list);
		int _la;
		try {
			setState(754);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(732);
				match(T__4);
				setState(733);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(734);
				match(T__4);
				setState(735);
				match(T__4);
				setState(736);
				make_item_list();
				setState(737);
				match(SEMICOLON);
				setState(738);
				((Make_group_listContext)_localctx).gc = double_object();
				setState(739);
				match(T__5);
				setState(749);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(740);
					match(SEMICOLON);
					setState(741);
					match(T__4);
					setState(742);
					make_item_list();
					setState(743);
					match(SEMICOLON);
					setState(744);
					((Make_group_listContext)_localctx).gc = double_object();
					setState(745);
					match(T__5);
					}
					}
					setState(751);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(752);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ex_item_drop_listContext extends ParserRuleContext {
		public Make_group_listContext make_group_list() {
			return getRuleContext(Make_group_listContext.class,0);
		}
		public Ex_item_drop_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ex_item_drop_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEx_item_drop_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEx_item_drop_list(this);
		}
	}

	public final Ex_item_drop_listContext ex_item_drop_list() throws RecognitionException {
		Ex_item_drop_listContext _localctx = new Ex_item_drop_listContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_ex_item_drop_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(756);
			match(T__80);
			setState(757);
			match(T__3);
			setState(758);
			make_group_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Vitality_item_drop_listContext extends ParserRuleContext {
		public Make_group_listContext make_group_list() {
			return getRuleContext(Make_group_listContext.class,0);
		}
		public Vitality_item_drop_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vitality_item_drop_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterVitality_item_drop_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitVitality_item_drop_list(this);
		}
	}

	public final Vitality_item_drop_listContext vitality_item_drop_list() throws RecognitionException {
		Vitality_item_drop_listContext _localctx = new Vitality_item_drop_listContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_vitality_item_drop_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(760);
			match(T__81);
			setState(761);
			match(T__3);
			setState(762);
			make_group_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Mp_rewardContext extends ParserRuleContext {
		public String_objectContext string_object() {
			return getRuleContext(String_objectContext.class,0);
		}
		public Mp_rewardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mp_reward; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterMp_reward(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitMp_reward(this);
		}
	}

	public final Mp_rewardContext mp_reward() throws RecognitionException {
		Mp_rewardContext _localctx = new Mp_rewardContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_mp_reward);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(764);
			match(T__82);
			setState(765);
			match(T__3);
			setState(766);
			string_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fake_class_idContext extends ParserRuleContext {
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Fake_class_idContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fake_class_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterFake_class_id(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitFake_class_id(this);
		}
	}

	public final Fake_class_idContext fake_class_id() throws RecognitionException {
		Fake_class_idContext _localctx = new Fake_class_idContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_fake_class_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			match(T__83);
			setState(769);
			match(T__3);
			setState(770);
			((Fake_class_idContext)_localctx).io = int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Event_dropContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Event_dropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event_drop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEvent_drop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEvent_drop(this);
		}
	}

	public final Event_dropContext event_drop() throws RecognitionException {
		Event_dropContext _localctx = new Event_dropContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_event_drop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772);
			match(T__84);
			setState(773);
			match(T__3);
			setState(774);
			int_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Ex_dropContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Ex_dropContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ex_drop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEx_drop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEx_drop(this);
		}
	}

	public final Ex_dropContext ex_drop() throws RecognitionException {
		Ex_dropContext _localctx = new Ex_dropContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_ex_drop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(776);
			match(T__85);
			setState(777);
			match(T__3);
			setState(778);
			int_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enable_move_after_talkContext extends ParserRuleContext {
		public Bool_objectContext bool_object() {
			return getRuleContext(Bool_objectContext.class,0);
		}
		public Enable_move_after_talkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enable_move_after_talk; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEnable_move_after_talk(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEnable_move_after_talk(this);
		}
	}

	public final Enable_move_after_talkContext enable_move_after_talk() throws RecognitionException {
		Enable_move_after_talkContext _localctx = new Enable_move_after_talkContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_enable_move_after_talk);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(780);
			match(T__86);
			setState(781);
			match(T__3);
			setState(782);
			bool_object();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Broadcast_condContext extends ParserRuleContext {
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Broadcast_condContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_broadcast_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBroadcast_cond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBroadcast_cond(this);
		}
	}

	public final Broadcast_condContext broadcast_cond() throws RecognitionException {
		Broadcast_condContext _localctx = new Broadcast_condContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_broadcast_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(784);
			match(T__87);
			setState(785);
			match(T__3);
			setState(786);
			int_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Identifier_objectContext extends ParserRuleContext {
		public String value;
		public TerminalNode DAGGER() { return getToken(NpcDatasParser.DAGGER, 0); }
		public TerminalNode BOW() { return getToken(NpcDatasParser.BOW, 0); }
		public TerminalNode CROSSBOW() { return getToken(NpcDatasParser.CROSSBOW, 0); }
		public TerminalNode RAPIER() { return getToken(NpcDatasParser.RAPIER, 0); }
		public TerminalNode GLOVES() { return getToken(NpcDatasParser.GLOVES, 0); }
		public TerminalNode STEEL() { return getToken(NpcDatasParser.STEEL, 0); }
		public TerminalNode LEATHER() { return getToken(NpcDatasParser.LEATHER, 0); }
		public TerminalNode ORIHARUKON() { return getToken(NpcDatasParser.ORIHARUKON, 0); }
		public TerminalNode NAME() { return getToken(NpcDatasParser.NAME, 0); }
		public TerminalNode NONE() { return getToken(NpcDatasParser.NONE, 0); }
		public TerminalNode ORC() { return getToken(NpcDatasParser.ORC, 0); }
		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterIdentifier_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(788);
			_la = _input.LA(1);
			if ( !(_la==T__16 || ((((_la - 104)) & ~0x3f) == 0 && ((1L << (_la - 104)) & ((1L << (NONE - 104)) | (1L << (ORC - 104)) | (1L << (BOW - 104)) | (1L << (DAGGER - 104)) | (1L << (RAPIER - 104)) | (1L << (CROSSBOW - 104)))) != 0) || ((((_la - 170)) & ~0x3f) == 0 && ((1L << (_la - 170)) & ((1L << (GLOVES - 170)) | (1L << (STEEL - 170)) | (1L << (LEATHER - 170)) | (1L << (ORIHARUKON - 170)) | (1L << (NAME - 170)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = _input.getText(_localctx.start, _input.LT(-1));
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bool_objectContext extends ParserRuleContext {
		public boolean value;
		public TerminalNode BOOLEAN() { return getToken(NpcDatasParser.BOOLEAN, 0); }
		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBool_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(790);
			match(BOOLEAN);
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = _input.getText(_localctx.start, _input.LT(-1)).equals("1");
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Byte_objectContext extends ParserRuleContext {
		public byte value;
		public TerminalNode BOOLEAN() { return getToken(NpcDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(NpcDatasParser.INTEGER, 0); }
		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterByte_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792);
			_la = _input.LA(1);
			if ( !(_la==BOOLEAN || _la==INTEGER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Byte.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_objectContext extends ParserRuleContext {
		public int value;
		public TerminalNode BOOLEAN() { return getToken(NpcDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(NpcDatasParser.INTEGER, 0); }
		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterInt_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(794);
			_la = _input.LA(1);
			if ( !(_la==BOOLEAN || _la==INTEGER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Integer.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Long_objectContext extends ParserRuleContext {
		public long value;
		public TerminalNode BOOLEAN() { return getToken(NpcDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(NpcDatasParser.INTEGER, 0); }
		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterLong_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(796);
			_la = _input.LA(1);
			if ( !(_la==BOOLEAN || _la==INTEGER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Long.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Double_objectContext extends ParserRuleContext {
		public double value;
		public TerminalNode BOOLEAN() { return getToken(NpcDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(NpcDatasParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(NpcDatasParser.DOUBLE, 0); }
		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterDouble_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(798);
			_la = _input.LA(1);
			if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (BOOLEAN - 94)) | (1L << (INTEGER - 94)) | (1L << (DOUBLE - 94)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = Double.valueOf(_input.getText(_localctx.start, _input.LT(-1)));
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_objectContext extends ParserRuleContext {
		public String value;
		public TerminalNode BOOLEAN() { return getToken(NpcDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(NpcDatasParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(NpcDatasParser.DOUBLE, 0); }
		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterString_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(800);
			_la = _input.LA(1);
			if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & ((1L << (BOOLEAN - 94)) | (1L << (INTEGER - 94)) | (1L << (DOUBLE - 94)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = _input.getText(_localctx.start, _input.LT(-1));
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Name_objectContext extends ParserRuleContext {
		public String value;
		public Identifier_objectContext io;
		public Identifier_objectContext identifier_object() {
			return getRuleContext(Identifier_objectContext.class,0);
		}
		public Name_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterName_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(802);
			match(T__17);
			setState(803);
			((Name_objectContext)_localctx).io = identifier_object();
			setState(804);
			match(T__18);
			 _localctx.value = ((Name_objectContext)_localctx).io.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Category_objectContext extends ParserRuleContext {
		public TerminalNode CATEGORY() { return getToken(NpcDatasParser.CATEGORY, 0); }
		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCategory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(807);
			match(CATEGORY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Vector3D_objectContext extends ParserRuleContext {
		public Vector3D value;
		public Int_objectContext x;
		public Int_objectContext y;
		public Int_objectContext z;
		public List<TerminalNode> SEMICOLON() { return getTokens(NpcDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(NpcDatasParser.SEMICOLON, i);
		}
		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}
		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class,i);
		}
		public Vector3D_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vector3D_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterVector3D_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(809);
			match(T__4);
			setState(810);
			((Vector3D_objectContext)_localctx).x = int_object();
			setState(811);
			match(SEMICOLON);
			setState(812);
			((Vector3D_objectContext)_localctx).y = int_object();
			setState(813);
			match(SEMICOLON);
			setState(814);
			((Vector3D_objectContext)_localctx).z = int_object();
			setState(815);
			match(T__5);
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = new Vector3D(((Vector3D_objectContext)_localctx).x.value, ((Vector3D_objectContext)_localctx).y.value, ((Vector3D_objectContext)_localctx).z.value);
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Empty_listContext extends ParserRuleContext {
		public List<String> value;
		public Empty_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_empty_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterEmpty_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(817);
			match(T__4);
			setState(818);
			match(T__5);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Identifier_listContext extends ParserRuleContext {
		public List<String> value;
		public Identifier_objectContext io;
		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class,0);
		}
		public List<Identifier_objectContext> identifier_object() {
			return getRuleContexts(Identifier_objectContext.class);
		}
		public Identifier_objectContext identifier_object(int i) {
			return getRuleContext(Identifier_objectContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(NpcDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(NpcDatasParser.SEMICOLON, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_identifier_list);
		 _localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(835);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(820);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(821);
				match(T__4);
				setState(822);
				((Identifier_listContext)_localctx).io = identifier_object();
				 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
				setState(830);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(824);
					match(SEMICOLON);
					setState(825);
					((Identifier_listContext)_localctx).io = identifier_object();
					 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
					}
					}
					setState(832);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(833);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_listContext extends ParserRuleContext {
		public List<Integer> value;
		public Int_objectContext io;
		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class,0);
		}
		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}
		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(NpcDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(NpcDatasParser.SEMICOLON, i);
		}
		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterInt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_int_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(852);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(837);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(838);
				match(T__4);
				setState(839);
				((Int_listContext)_localctx).io = int_object();
				 _localctx.value.add(((Int_listContext)_localctx).io.value); 
				setState(847);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(841);
					match(SEMICOLON);
					setState(842);
					((Int_listContext)_localctx).io = int_object();
					_localctx.value.add(((Int_listContext)_localctx).io.value);
					}
					}
					setState(849);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(850);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Double_listContext extends ParserRuleContext {
		public List<Double> value;
		public Double_objectContext d;
		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class,0);
		}
		public List<Double_objectContext> double_object() {
			return getRuleContexts(Double_objectContext.class);
		}
		public Double_objectContext double_object(int i) {
			return getRuleContext(Double_objectContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(NpcDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(NpcDatasParser.SEMICOLON, i);
		}
		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterDouble_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_double_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(869);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(854);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(855);
				match(T__4);
				setState(856);
				((Double_listContext)_localctx).d = double_object();
				 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
				setState(864);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(858);
					match(SEMICOLON);
					setState(859);
					((Double_listContext)_localctx).d = double_object();
					 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
					}
					}
					setState(866);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(867);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_attribute_attackContext extends ParserRuleContext {
		public AttributeAttack value;
		public Attack_attributeContext aa;
		public Attack_attributeContext attack_attribute() {
			return getRuleContext(Attack_attributeContext.class,0);
		}
		public Base_attribute_attackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_attribute_attack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterBase_attribute_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(871);
			match(T__88);
			setState(872);
			match(T__3);
			setState(873);
			((Base_attribute_attackContext)_localctx).aa = attack_attribute();
			_localctx.value = ((Base_attribute_attackContext)_localctx).aa.value;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attack_attributeContext extends ParserRuleContext {
		public AttributeAttack value;
		public AttributeContext attribute;
		public Int_objectContext io;
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(NpcDatasParser.SEMICOLON, 0); }
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAttack_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(876);
			match(T__4);
			setState(877);
			((Attack_attributeContext)_localctx).attribute = attribute();
			setState(878);
			match(SEMICOLON);
			setState(879);
			((Attack_attributeContext)_localctx).io = int_object();
			setState(880);
			match(T__5);
			_localctx.value = new AttributeAttack(((Attack_attributeContext)_localctx).attribute.value, ((Attack_attributeContext)_localctx).io.value);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public AttributeType value;
		public TerminalNode NONE() { return getToken(NpcDatasParser.NONE, 0); }
		public TerminalNode FIRE() { return getToken(NpcDatasParser.FIRE, 0); }
		public TerminalNode WATER() { return getToken(NpcDatasParser.WATER, 0); }
		public TerminalNode EARTH() { return getToken(NpcDatasParser.EARTH, 0); }
		public TerminalNode WIND() { return getToken(NpcDatasParser.WIND, 0); }
		public TerminalNode HOLY() { return getToken(NpcDatasParser.HOLY, 0); }
		public TerminalNode UNHOLY() { return getToken(NpcDatasParser.UNHOLY, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_attribute);
		try {
			setState(897);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(883);
				match(NONE);
				_localctx.value = AttributeType.NONE;
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 2);
				{
				setState(885);
				match(FIRE);
				_localctx.value = AttributeType.FIRE;
				}
				break;
			case WATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(887);
				match(WATER);
				_localctx.value = AttributeType.WATER;
				}
				break;
			case EARTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(889);
				match(EARTH);
				_localctx.value = AttributeType.EARTH;
				}
				break;
			case WIND:
				enterOuterAlt(_localctx, 5);
				{
				setState(891);
				match(WIND);
				_localctx.value = AttributeType.WIND;
				}
				break;
			case HOLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(893);
				match(HOLY);
				_localctx.value = AttributeType.HOLY;
				}
				break;
			case UNHOLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(895);
				match(UNHOLY);
				_localctx.value = AttributeType.UNHOLY;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Category_listContext extends ParserRuleContext {
		public List<String> value;
		public Category_objectContext co;
		public Empty_listContext empty_list() {
			return getRuleContext(Empty_listContext.class,0);
		}
		public List<Category_objectContext> category_object() {
			return getRuleContexts(Category_objectContext.class);
		}
		public Category_objectContext category_object(int i) {
			return getRuleContext(Category_objectContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(NpcDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(NpcDatasParser.SEMICOLON, i);
		}
		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).enterCategory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof NpcDatasListener ) ((NpcDatasListener)listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_category_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(914);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(899);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(900);
				match(T__4);
				setState(901);
				((Category_listContext)_localctx).co = category_object();
				 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
				setState(909);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(903);
					match(SEMICOLON);
					setState(904);
					((Category_listContext)_localctx).co = category_object();
					 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
					}
					}
					setState(911);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(912);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00db\u0397\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\3\2\6\2\u00e6\n\2\r\2\16\2"+
		"\u00e7\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0160\n"+
		"\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\5\20"+
		"\u0191\n\20\3\21\3\21\3\21\3\21\5\21\u0197\n\21\3\22\3\22\3\22\3\22\5"+
		"\22\u019d\n\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3&\3&\3"+
		"&\3&\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3+\3+\3+\3+\3"+
		",\3,\3,\3,\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61"+
		"\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67"+
		"\38\38\38\38\38\38\38\58\u0238\n8\39\39\39\39\3:\3:\3:\3:\3;\3;\3;\3;"+
		"\3<\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>\3?\3?\3?\3?\3@\3@\3@\3@\3A\3A\3A"+
		"\3A\3B\3B\3B\3B\3C\3C\3C\3C\3D\3D\3D\3D\3E\3E\3E\3E\3F\3F\3F\3F\3G\3G"+
		"\3G\3G\3H\3H\3H\3H\3H\3H\7H\u027c\nH\fH\16H\u027f\13H\3H\3H\3I\3I\3I\3"+
		"I\3I\3I\3J\3J\3J\3J\3J\3J\5J\u028f\nJ\3K\3K\3K\3K\7K\u0295\nK\fK\16K\u0298"+
		"\13K\3K\3K\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3N\3N\3N\3N\3O\3O\3O\3O\3P\3"+
		"P\3P\3P\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\7T\u02c4"+
		"\nT\fT\16T\u02c7\13T\3T\3T\5T\u02cb\nT\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3"+
		"V\3V\3V\3V\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\7"+
		"X\u02ee\nX\fX\16X\u02f1\13X\3X\3X\5X\u02f5\nX\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z"+
		"\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3]\3]\3]\3]\3^\3^\3^\3^\3_\3_\3_\3_\3`\3"+
		"`\3`\3`\3a\3a\3b\3b\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\3h\3h\3h\3h\3h\3i\3"+
		"i\3j\3j\3j\3j\3j\3j\3j\3j\3k\3k\3k\3l\3l\3l\3l\3l\3l\3l\3l\7l\u033f\n"+
		"l\fl\16l\u0342\13l\3l\3l\5l\u0346\nl\3m\3m\3m\3m\3m\3m\3m\3m\7m\u0350"+
		"\nm\fm\16m\u0353\13m\3m\3m\5m\u0357\nm\3n\3n\3n\3n\3n\3n\3n\3n\7n\u0361"+
		"\nn\fn\16n\u0364\13n\3n\3n\5n\u0368\nn\3o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3"+
		"p\3p\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\5q\u0384\nq\3r\3r\3r\3"+
		"r\3r\3r\3r\3r\7r\u038e\nr\fr\16r\u0391\13r\3r\3r\5r\u0395\nr\3r\2\2s\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL"+
		"NPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c\u008e"+
		"\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4\u00a6"+
		"\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc\u00be"+
		"\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4\u00d6"+
		"\u00d8\u00da\u00dc\u00de\u00e0\u00e2\2\b\4\2^^k\u008a\3\2\\^\3\2\u0092"+
		"\u0099\16\2\23\23jj\177\177\u0094\u0094\u0096\u0096\u009b\u009b\u009d"+
		"\u009d\u00ac\u00ac\u00bd\u00bd\u00c1\u00c1\u00c4\u00c4\u00d8\u00d8\3\2"+
		"`a\3\2`b\2\u0351\2\u00e5\3\2\2\2\4\u00e9\3\2\2\2\6\u015f\3\2\2\2\b\u0161"+
		"\3\2\2\2\n\u0164\3\2\2\2\f\u0167\3\2\2\2\16\u016c\3\2\2\2\20\u0170\3\2"+
		"\2\2\22\u0174\3\2\2\2\24\u0178\3\2\2\2\26\u017c\3\2\2\2\30\u0180\3\2\2"+
		"\2\32\u0184\3\2\2\2\34\u0188\3\2\2\2\36\u018c\3\2\2\2 \u0192\3\2\2\2\""+
		"\u0198\3\2\2\2$\u019e\3\2\2\2&\u01a1\3\2\2\2(\u01a5\3\2\2\2*\u01a9\3\2"+
		"\2\2,\u01ad\3\2\2\2.\u01b1\3\2\2\2\60\u01b5\3\2\2\2\62\u01b9\3\2\2\2\64"+
		"\u01bd\3\2\2\2\66\u01c1\3\2\2\28\u01c5\3\2\2\2:\u01c9\3\2\2\2<\u01cd\3"+
		"\2\2\2>\u01d1\3\2\2\2@\u01d5\3\2\2\2B\u01d9\3\2\2\2D\u01dd\3\2\2\2F\u01e1"+
		"\3\2\2\2H\u01e5\3\2\2\2J\u01e7\3\2\2\2L\u01eb\3\2\2\2N\u01ef\3\2\2\2P"+
		"\u01f3\3\2\2\2R\u01f7\3\2\2\2T\u01fb\3\2\2\2V\u01ff\3\2\2\2X\u0203\3\2"+
		"\2\2Z\u0207\3\2\2\2\\\u020b\3\2\2\2^\u020f\3\2\2\2`\u0213\3\2\2\2b\u0218"+
		"\3\2\2\2d\u021c\3\2\2\2f\u0220\3\2\2\2h\u0224\3\2\2\2j\u0228\3\2\2\2l"+
		"\u022c\3\2\2\2n\u0230\3\2\2\2p\u0239\3\2\2\2r\u023d\3\2\2\2t\u0241\3\2"+
		"\2\2v\u0245\3\2\2\2x\u0249\3\2\2\2z\u024d\3\2\2\2|\u0251\3\2\2\2~\u0255"+
		"\3\2\2\2\u0080\u0259\3\2\2\2\u0082\u025d\3\2\2\2\u0084\u0261\3\2\2\2\u0086"+
		"\u0265\3\2\2\2\u0088\u0269\3\2\2\2\u008a\u026d\3\2\2\2\u008c\u0271\3\2"+
		"\2\2\u008e\u0275\3\2\2\2\u0090\u0282\3\2\2\2\u0092\u028e\3\2\2\2\u0094"+
		"\u0290\3\2\2\2\u0096\u029b\3\2\2\2\u0098\u029f\3\2\2\2\u009a\u02a5\3\2"+
		"\2\2\u009c\u02a9\3\2\2\2\u009e\u02ad\3\2\2\2\u00a0\u02b1\3\2\2\2\u00a2"+
		"\u02b5\3\2\2\2\u00a4\u02b9\3\2\2\2\u00a6\u02ca\3\2\2\2\u00a8\u02cc\3\2"+
		"\2\2\u00aa\u02d6\3\2\2\2\u00ac\u02da\3\2\2\2\u00ae\u02f4\3\2\2\2\u00b0"+
		"\u02f6\3\2\2\2\u00b2\u02fa\3\2\2\2\u00b4\u02fe\3\2\2\2\u00b6\u0302\3\2"+
		"\2\2\u00b8\u0306\3\2\2\2\u00ba\u030a\3\2\2\2\u00bc\u030e\3\2\2\2\u00be"+
		"\u0312\3\2\2\2\u00c0\u0316\3\2\2\2\u00c2\u0318\3\2\2\2\u00c4\u031a\3\2"+
		"\2\2\u00c6\u031c\3\2\2\2\u00c8\u031e\3\2\2\2\u00ca\u0320\3\2\2\2\u00cc"+
		"\u0322\3\2\2\2\u00ce\u0324\3\2\2\2\u00d0\u0329\3\2\2\2\u00d2\u032b\3\2"+
		"\2\2\u00d4\u0333\3\2\2\2\u00d6\u0345\3\2\2\2\u00d8\u0356\3\2\2\2\u00da"+
		"\u0367\3\2\2\2\u00dc\u0369\3\2\2\2\u00de\u036e\3\2\2\2\u00e0\u0383\3\2"+
		"\2\2\u00e2\u0394\3\2\2\2\u00e4\u00e6\5\4\3\2\u00e5\u00e4\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\3\3\2\2\2"+
		"\u00e9\u00ea\7\3\2\2\u00ea\u00eb\5\6\4\2\u00eb\u00ec\5\b\5\2\u00ec\u00ed"+
		"\5\n\6\2\u00ed\u00ee\5\f\7\2\u00ee\u00ef\5\16\b\2\u00ef\u00f0\5\20\t\2"+
		"\u00f0\u00f1\5\22\n\2\u00f1\u00f2\5\24\13\2\u00f2\u00f3\5\26\f\2\u00f3"+
		"\u00f4\5\30\r\2\u00f4\u00f5\5\32\16\2\u00f5\u00f6\5\34\17\2\u00f6\u00f7"+
		"\5\36\20\2\u00f7\u00f8\5 \21\2\u00f8\u00f9\5\"\22\2\u00f9\u00fa\5&\24"+
		"\2\u00fa\u00fb\5(\25\2\u00fb\u00fc\5*\26\2\u00fc\u00fd\5,\27\2\u00fd\u00fe"+
		"\5.\30\2\u00fe\u00ff\5\60\31\2\u00ff\u0100\5\62\32\2\u0100\u0101\5\64"+
		"\33\2\u0101\u0102\5\66\34\2\u0102\u0103\58\35\2\u0103\u0104\5:\36\2\u0104"+
		"\u0105\5<\37\2\u0105\u0106\5> \2\u0106\u0107\5@!\2\u0107\u0108\5B\"\2"+
		"\u0108\u0109\5D#\2\u0109\u010a\5F$\2\u010a\u010b\5J&\2\u010b\u010c\5L"+
		"\'\2\u010c\u010d\5N(\2\u010d\u010e\5P)\2\u010e\u010f\5R*\2\u010f\u0110"+
		"\5T+\2\u0110\u0111\5V,\2\u0111\u0112\5X-\2\u0112\u0113\5Z.\2\u0113\u0114"+
		"\5\\/\2\u0114\u0115\5^\60\2\u0115\u0116\5\u00dco\2\u0116\u0117\5`\61\2"+
		"\u0117\u0118\5b\62\2\u0118\u0119\5d\63\2\u0119\u011a\5f\64\2\u011a\u011b"+
		"\5h\65\2\u011b\u011c\5j\66\2\u011c\u011d\5l\67\2\u011d\u011e\5n8\2\u011e"+
		"\u011f\5p9\2\u011f\u0120\5r:\2\u0120\u0121\5t;\2\u0121\u0122\5v<\2\u0122"+
		"\u0123\5x=\2\u0123\u0124\5z>\2\u0124\u0125\5|?\2\u0125\u0126\5~@\2\u0126"+
		"\u0127\5\u0080A\2\u0127\u0128\5\u0082B\2\u0128\u0129\5\u0084C\2\u0129"+
		"\u012a\5\u0086D\2\u012a\u012b\5\u0088E\2\u012b\u012c\5\u008aF\2\u012c"+
		"\u012d\5\u008cG\2\u012d\u012e\5\u008eH\2\u012e\u012f\5\u0098M\2\u012f"+
		"\u0130\5\u009aN\2\u0130\u0131\5\u009cO\2\u0131\u0132\5\u009eP\2\u0132"+
		"\u0133\5\u00a0Q\2\u0133\u0134\5\u00a2R\2\u0134\u0135\5\u00a4S\2\u0135"+
		"\u0136\5\u00aaV\2\u0136\u0137\5\u00acW\2\u0137\u0138\5\u00b0Y\2\u0138"+
		"\u0139\5\u00b2Z\2\u0139\u013a\5\u00b4[\2\u013a\u013b\5\u00b6\\\2\u013b"+
		"\u013c\5\u00b8]\2\u013c\u013d\5\u00ba^\2\u013d\u013e\5\u00bc_\2\u013e"+
		"\u013f\5\u00be`\2\u013f\u0140\7\4\2\2\u0140\5\3\2\2\2\u0141\u0142\7\u008b"+
		"\2\2\u0142\u0160\b\4\1\2\u0143\u0144\7\u008d\2\2\u0144\u0160\b\4\1\2\u0145"+
		"\u0146\7\u008c\2\2\u0146\u0160\b\4\1\2\u0147\u0148\7\u0084\2\2\u0148\u0160"+
		"\b\4\1\2\u0149\u014a\7\u0083\2\2\u014a\u0160\b\4\1\2\u014b\u014c\7\u008e"+
		"\2\2\u014c\u0160\b\4\1\2\u014d\u014e\7\u0085\2\2\u014e\u0160\b\4\1\2\u014f"+
		"\u0150\7|\2\2\u0150\u0160\b\4\1\2\u0151\u0152\7\u008a\2\2\u0152\u0160"+
		"\b\4\1\2\u0153\u0154\7\u008f\2\2\u0154\u0160\b\4\1\2\u0155\u0156\7\u0089"+
		"\2\2\u0156\u0160\b\4\1\2\u0157\u0158\7\u0090\2\2\u0158\u0160\b\4\1\2\u0159"+
		"\u015a\7\u0091\2\2\u015a\u0160\b\4\1\2\u015b\u015c\7\u0088\2\2\u015c\u0160"+
		"\b\4\1\2\u015d\u015e\7\u0086\2\2\u015e\u0160\b\4\1\2\u015f\u0141\3\2\2"+
		"\2\u015f\u0143\3\2\2\2\u015f\u0145\3\2\2\2\u015f\u0147\3\2\2\2\u015f\u0149"+
		"\3\2\2\2\u015f\u014b\3\2\2\2\u015f\u014d\3\2\2\2\u015f\u014f\3\2\2\2\u015f"+
		"\u0151\3\2\2\2\u015f\u0153\3\2\2\2\u015f\u0155\3\2\2\2\u015f\u0157\3\2"+
		"\2\2\u015f\u0159\3\2\2\2\u015f\u015b\3\2\2\2\u015f\u015d\3\2\2\2\u0160"+
		"\7\3\2\2\2\u0161\u0162\5\u00c6d\2\u0162\u0163\b\5\1\2\u0163\t\3\2\2\2"+
		"\u0164\u0165\5\u00ceh\2\u0165\u0166\b\6\1\2\u0166\13\3\2\2\2\u0167\u0168"+
		"\7\5\2\2\u0168\u0169\7\6\2\2\u0169\u016a\7\7\2\2\u016a\u016b\7\b\2\2\u016b"+
		"\r\3\2\2\2\u016c\u016d\7\t\2\2\u016d\u016e\7\6\2\2\u016e\u016f\5\u00c6"+
		"d\2\u016f\17\3\2\2\2\u0170\u0171\7\n\2\2\u0171\u0172\7\6\2\2\u0172\u0173"+
		"\5\u00c8e\2\u0173\21\3\2\2\2\u0174\u0175\7\13\2\2\u0175\u0176\7\6\2\2"+
		"\u0176\u0177\5\u00c6d\2\u0177\23\3\2\2\2\u0178\u0179\7\f\2\2\u0179\u017a"+
		"\7\6\2\2\u017a\u017b\5\u00c2b\2\u017b\25\3\2\2\2\u017c\u017d\7\r\2\2\u017d"+
		"\u017e\7\6\2\2\u017e\u017f\5\u00caf\2\u017f\27\3\2\2\2\u0180\u0181\7\16"+
		"\2\2\u0181\u0182\7\6\2\2\u0182\u0183\t\2\2\2\u0183\31\3\2\2\2\u0184\u0185"+
		"\7\17\2\2\u0185\u0186\7\6\2\2\u0186\u0187\t\3\2\2\u0187\33\3\2\2\2\u0188"+
		"\u0189\7\20\2\2\u0189\u018a\7\6\2\2\u018a\u018b\5\u00e2r\2\u018b\35\3"+
		"\2\2\2\u018c\u018d\7\21\2\2\u018d\u0190\7\6\2\2\u018e\u0191\5$\23\2\u018f"+
		"\u0191\5\u00ceh\2\u0190\u018e\3\2\2\2\u0190\u018f\3\2\2\2\u0191\37\3\2"+
		"\2\2\u0192\u0193\7\22\2\2\u0193\u0196\7\6\2\2\u0194\u0197\5$\23\2\u0195"+
		"\u0197\5\u00ceh\2\u0196\u0194\3\2\2\2\u0196\u0195\3\2\2\2\u0197!\3\2\2"+
		"\2\u0198\u0199\7\23\2\2\u0199\u019c\7\6\2\2\u019a\u019d\5$\23\2\u019b"+
		"\u019d\5\u00ceh\2\u019c\u019a\3\2\2\2\u019c\u019b\3\2\2\2\u019d#\3\2\2"+
		"\2\u019e\u019f\7\24\2\2\u019f\u01a0\7\25\2\2\u01a0%\3\2\2\2\u01a1\u01a2"+
		"\7\26\2\2\u01a2\u01a3\7\6\2\2\u01a3\u01a4\5\u00dan\2\u01a4\'\3\2\2\2\u01a5"+
		"\u01a6\7\27\2\2\u01a6\u01a7\7\6\2\2\u01a7\u01a8\5\u00dan\2\u01a8)\3\2"+
		"\2\2\u01a9\u01aa\7\30\2\2\u01aa\u01ab\7\6\2\2\u01ab\u01ac\5\u00caf\2\u01ac"+
		"+\3\2\2\2\u01ad\u01ae\7\31\2\2\u01ae\u01af\7\6\2\2\u01af\u01b0\5\u00c6"+
		"d\2\u01b0-\3\2\2\2\u01b1\u01b2\7\32\2\2\u01b2\u01b3\7\6\2\2\u01b3\u01b4"+
		"\5\u00d8m\2\u01b4/\3\2\2\2\u01b5\u01b6\7\33\2\2\u01b6\u01b7\7\6\2\2\u01b7"+
		"\u01b8\5\u00d8m\2\u01b8\61\3\2\2\2\u01b9\u01ba\7\34\2\2\u01ba\u01bb\7"+
		"\6\2\2\u01bb\u01bc\5\u00c6d\2\u01bc\63\3\2\2\2\u01bd\u01be\7\35\2\2\u01be"+
		"\u01bf\7\6\2\2\u01bf\u01c0\5\u00c6d\2\u01c0\65\3\2\2\2\u01c1\u01c2\7\36"+
		"\2\2\u01c2\u01c3\7\6\2\2\u01c3\u01c4\5\u00c6d\2\u01c4\67\3\2\2\2\u01c5"+
		"\u01c6\7\37\2\2\u01c6\u01c7\7\6\2\2\u01c7\u01c8\5\u00c6d\2\u01c89\3\2"+
		"\2\2\u01c9\u01ca\7 \2\2\u01ca\u01cb\7\6\2\2\u01cb\u01cc\5\u00c6d\2\u01cc"+
		";\3\2\2\2\u01cd\u01ce\7!\2\2\u01ce\u01cf\7\6\2\2\u01cf\u01d0\5\u00c6d"+
		"\2\u01d0=\3\2\2\2\u01d1\u01d2\7\"\2\2\u01d2\u01d3\7\6\2\2\u01d3\u01d4"+
		"\5\u00caf\2\u01d4?\3\2\2\2\u01d5\u01d6\7#\2\2\u01d6\u01d7\7\6\2\2\u01d7"+
		"\u01d8\5\u00caf\2\u01d8A\3\2\2\2\u01d9\u01da\7$\2\2\u01da\u01db\7\6\2"+
		"\2\u01db\u01dc\5\u00caf\2\u01dcC\3\2\2\2\u01dd\u01de\7%\2\2\u01de\u01df"+
		"\7\6\2\2\u01df\u01e0\5\u00caf\2\u01e0E\3\2\2\2\u01e1\u01e2\7&\2\2\u01e2"+
		"\u01e3\7\6\2\2\u01e3\u01e4\5H%\2\u01e4G\3\2\2\2\u01e5\u01e6\t\4\2\2\u01e6"+
		"I\3\2\2\2\u01e7\u01e8\7\'\2\2\u01e8\u01e9\7\6\2\2\u01e9\u01ea\5\u00c6"+
		"d\2\u01eaK\3\2\2\2\u01eb\u01ec\7(\2\2\u01ec\u01ed\7\6\2\2\u01ed\u01ee"+
		"\5\u00d8m\2\u01eeM\3\2\2\2\u01ef\u01f0\7)\2\2\u01f0\u01f1\7\6\2\2\u01f1"+
		"\u01f2\5\u00c6d\2\u01f2O\3\2\2\2\u01f3\u01f4\7*\2\2\u01f4\u01f5\7\6\2"+
		"\2\u01f5\u01f6\5\u00caf\2\u01f6Q\3\2\2\2\u01f7\u01f8\7+\2\2\u01f8\u01f9"+
		"\7\6\2\2\u01f9\u01fa\5\u00c6d\2\u01faS\3\2\2\2\u01fb\u01fc\7,\2\2\u01fc"+
		"\u01fd\7\6\2\2\u01fd\u01fe\5\u00caf\2\u01feU\3\2\2\2\u01ff\u0200\7-\2"+
		"\2\u0200\u0201\7\6\2\2\u0201\u0202\5\u00c6d\2\u0202W\3\2\2\2\u0203\u0204"+
		"\7.\2\2\u0204\u0205\7\6\2\2\u0205\u0206\5\u00c6d\2\u0206Y\3\2\2\2\u0207"+
		"\u0208\7/\2\2\u0208\u0209\7\6\2\2\u0209\u020a\5\u00caf\2\u020a[\3\2\2"+
		"\2\u020b\u020c\7\60\2\2\u020c\u020d\7\6\2\2\u020d\u020e\5\u00caf\2\u020e"+
		"]\3\2\2\2\u020f\u0210\7\61\2\2\u0210\u0211\7\6\2\2\u0211\u0212\5\u00ca"+
		"f\2\u0212_\3\2\2\2\u0213\u0214\7\62\2\2\u0214\u0215\7\6\2\2\u0215\u0216"+
		"\5\u00d8m\2\u0216\u0217\b\61\1\2\u0217a\3\2\2\2\u0218\u0219\7\63\2\2\u0219"+
		"\u021a\7\6\2\2\u021a\u021b\5\u00c6d\2\u021bc\3\2\2\2\u021c\u021d\7\64"+
		"\2\2\u021d\u021e\7\6\2\2\u021e\u021f\5\u00c6d\2\u021fe\3\2\2\2\u0220\u0221"+
		"\7\65\2\2\u0221\u0222\7\6\2\2\u0222\u0223\5\u00caf\2\u0223g\3\2\2\2\u0224"+
		"\u0225\7\66\2\2\u0225\u0226\7\6\2\2\u0226\u0227\5\u00c6d\2\u0227i\3\2"+
		"\2\2\u0228\u0229\7\67\2\2\u0229\u022a\7\6\2\2\u022a\u022b\5\u00c6d\2\u022b"+
		"k\3\2\2\2\u022c\u022d\78\2\2\u022d\u022e\7\6\2\2\u022e\u022f\5\u00c6d"+
		"\2\u022fm\3\2\2\2\u0230\u0231\79\2\2\u0231\u0237\7\6\2\2\u0232\u0233\7"+
		"\7\2\2\u0233\u0234\5\u00c6d\2\u0234\u0235\7\b\2\2\u0235\u0238\3\2\2\2"+
		"\u0236\u0238\5\u00e2r\2\u0237\u0232\3\2\2\2\u0237\u0236\3\2\2\2\u0238"+
		"o\3\2\2\2\u0239\u023a\7:\2\2\u023a\u023b\7\6\2\2\u023b\u023c\5\u00e2r"+
		"\2\u023cq\3\2\2\2\u023d\u023e\7;\2\2\u023e\u023f\7\6\2\2\u023f\u0240\5"+
		"\u00c6d\2\u0240s\3\2\2\2\u0241\u0242\7<\2\2\u0242\u0243\7\6\2\2\u0243"+
		"\u0244\5\u00c2b\2\u0244u\3\2\2\2\u0245\u0246\7=\2\2\u0246\u0247\7\6\2"+
		"\2\u0247\u0248\5\u00c2b\2\u0248w\3\2\2\2\u0249\u024a\7>\2\2\u024a\u024b"+
		"\7\6\2\2\u024b\u024c\5\u00c6d\2\u024cy\3\2\2\2\u024d\u024e\7?\2\2\u024e"+
		"\u024f\7\6\2\2\u024f\u0250\5\u00c2b\2\u0250{\3\2\2\2\u0251\u0252\7@\2"+
		"\2\u0252\u0253\7\6\2\2\u0253\u0254\5\u00c6d\2\u0254}\3\2\2\2\u0255\u0256"+
		"\7A\2\2\u0256\u0257\7\6\2\2\u0257\u0258\5\u00c2b\2\u0258\177\3\2\2\2\u0259"+
		"\u025a\7B\2\2\u025a\u025b\7\6\2\2\u025b\u025c\5\u00c2b\2\u025c\u0081\3"+
		"\2\2\2\u025d\u025e\7C\2\2\u025e\u025f\7\6\2\2\u025f\u0260\5\u00c2b\2\u0260"+
		"\u0083\3\2\2\2\u0261\u0262\7D\2\2\u0262\u0263\7\6\2\2\u0263\u0264\5\u00c2"+
		"b\2\u0264\u0085\3\2\2\2\u0265\u0266\7E\2\2\u0266\u0267\7\6\2\2\u0267\u0268"+
		"\5\u00c2b\2\u0268\u0087\3\2\2\2\u0269\u026a\7F\2\2\u026a\u026b\7\6\2\2"+
		"\u026b\u026c\5\u00c2b\2\u026c\u0089\3\2\2\2\u026d\u026e\7G\2\2\u026e\u026f"+
		"\7\6\2\2\u026f\u0270\5\u00d8m\2\u0270\u008b\3\2\2\2\u0271\u0272\7H\2\2"+
		"\u0272\u0273\7\6\2\2\u0273\u0274\5\u00c2b\2\u0274\u008d\3\2\2\2\u0275"+
		"\u0276\7I\2\2\u0276\u0277\7\6\2\2\u0277\u0278\7\7\2\2\u0278\u027d\5\u00ce"+
		"h\2\u0279\u027a\7i\2\2\u027a\u027c\5\u0090I\2\u027b\u0279\3\2\2\2\u027c"+
		"\u027f\3\2\2\2\u027d\u027b\3\2\2\2\u027d\u027e\3\2\2\2\u027e\u0280\3\2"+
		"\2\2\u027f\u027d\3\2\2\2\u0280\u0281\7\b\2\2\u0281\u008f\3\2\2\2\u0282"+
		"\u0283\7\7\2\2\u0283\u0284\5\u00ceh\2\u0284\u0285\7\6\2\2\u0285\u0286"+
		"\5\u0092J\2\u0286\u0287\7\b\2\2\u0287\u0091\3\2\2\2\u0288\u028f\5\u00c6"+
		"d\2\u0289\u028f\5\u00d0i\2\u028a\u028f\5\u0094K\2\u028b\u028f\5\u0096"+
		"L\2\u028c\u028f\5\u00caf\2\u028d\u028f\5\u00c0a\2\u028e\u0288\3\2\2\2"+
		"\u028e\u0289\3\2\2\2\u028e\u028a\3\2\2\2\u028e\u028b\3\2\2\2\u028e\u028c"+
		"\3\2\2\2\u028e\u028d\3\2\2\2\u028f\u0093\3\2\2\2\u0290\u0291\7\24\2\2"+
		"\u0291\u0296\5\u00c0a\2\u0292\u0293\7i\2\2\u0293\u0295\5\u00c0a\2\u0294"+
		"\u0292\3\2\2\2\u0295\u0298\3\2\2\2\u0296\u0294\3\2\2\2\u0296\u0297\3\2"+
		"\2\2\u0297\u0299\3\2\2\2\u0298\u0296\3\2\2\2\u0299\u029a\7\25\2\2\u029a"+
		"\u0095\3\2\2\2\u029b\u029c\7\24\2\2\u029c\u029d\5\u00c6d\2\u029d\u029e"+
		"\7\25\2\2\u029e\u0097\3\2\2\2\u029f\u02a0\7J\2\2\u02a0\u02a1\7\6\2\2\u02a1"+
		"\u02a2\7\7\2\2\u02a2\u02a3\5\u00c6d\2\u02a3\u02a4\7\b\2\2\u02a4\u0099"+
		"\3\2\2\2\u02a5\u02a6\7K\2\2\u02a6\u02a7\7\6\2\2\u02a7\u02a8\5\u00c2b\2"+
		"\u02a8\u009b\3\2\2\2\u02a9\u02aa\7L\2\2\u02aa\u02ab\7\6\2\2\u02ab\u02ac"+
		"\5\u00c2b\2\u02ac\u009d\3\2\2\2\u02ad\u02ae\7M\2\2\u02ae\u02af\7\6\2\2"+
		"\u02af\u02b0\5\u00caf\2\u02b0\u009f\3\2\2\2\u02b1\u02b2\7N\2\2\u02b2\u02b3"+
		"\7\6\2\2\u02b3\u02b4\5\u00caf\2\u02b4\u00a1\3\2\2\2\u02b5\u02b6\7O\2\2"+
		"\u02b6\u02b7\7\6\2\2\u02b7\u02b8\5\u00caf\2\u02b8\u00a3\3\2\2\2\u02b9"+
		"\u02ba\7P\2\2\u02ba\u02bb\7\6\2\2\u02bb\u02bc\5\u00a6T\2\u02bc\u00a5\3"+
		"\2\2\2\u02bd\u02be\7\7\2\2\u02be\u02cb\7\b\2\2\u02bf\u02c0\7\7\2\2\u02c0"+
		"\u02c5\5\u00a8U\2\u02c1\u02c2\7i\2\2\u02c2\u02c4\5\u00a8U\2\u02c3\u02c1"+
		"\3\2\2\2\u02c4\u02c7\3\2\2\2\u02c5\u02c3\3\2\2\2\u02c5\u02c6\3\2\2\2\u02c6"+
		"\u02c8\3\2\2\2\u02c7\u02c5\3\2\2\2\u02c8\u02c9\7\b\2\2\u02c9\u02cb\3\2"+
		"\2\2\u02ca\u02bd\3\2\2\2\u02ca\u02bf\3\2\2\2\u02cb\u00a7\3\2\2\2\u02cc"+
		"\u02cd\7\7\2\2\u02cd\u02ce\5\u00ceh\2\u02ce\u02cf\7i\2\2\u02cf\u02d0\5"+
		"\u00c6d\2\u02d0\u02d1\7i\2\2\u02d1\u02d2\5\u00c6d\2\u02d2\u02d3\7i\2\2"+
		"\u02d3\u02d4\5\u00caf\2\u02d4\u02d5\7\b\2\2\u02d5\u00a9\3\2\2\2\u02d6"+
		"\u02d7\7Q\2\2\u02d7\u02d8\7\6\2\2\u02d8\u02d9\5\u00a6T\2\u02d9\u00ab\3"+
		"\2\2\2\u02da\u02db\7R\2\2\u02db\u02dc\7\6\2\2\u02dc\u02dd\5\u00aeX\2\u02dd"+
		"\u00ad\3\2\2\2\u02de\u02df\7\7\2\2\u02df\u02f5\7\b\2\2\u02e0\u02e1\7\7"+
		"\2\2\u02e1\u02e2\7\7\2\2\u02e2\u02e3\5\u00a6T\2\u02e3\u02e4\7i\2\2\u02e4"+
		"\u02e5\5\u00caf\2\u02e5\u02ef\7\b\2\2\u02e6\u02e7\7i\2\2\u02e7\u02e8\7"+
		"\7\2\2\u02e8\u02e9\5\u00a6T\2\u02e9\u02ea\7i\2\2\u02ea\u02eb\5\u00caf"+
		"\2\u02eb\u02ec\7\b\2\2\u02ec\u02ee\3\2\2\2\u02ed\u02e6\3\2\2\2\u02ee\u02f1"+
		"\3\2\2\2\u02ef\u02ed\3\2\2\2\u02ef\u02f0\3\2\2\2\u02f0\u02f2\3\2\2\2\u02f1"+
		"\u02ef\3\2\2\2\u02f2\u02f3\7\b\2\2\u02f3\u02f5\3\2\2\2\u02f4\u02de\3\2"+
		"\2\2\u02f4\u02e0\3\2\2\2\u02f5\u00af\3\2\2\2\u02f6\u02f7\7S\2\2\u02f7"+
		"\u02f8\7\6\2\2\u02f8\u02f9\5\u00aeX\2\u02f9\u00b1\3\2\2\2\u02fa\u02fb"+
		"\7T\2\2\u02fb\u02fc\7\6\2\2\u02fc\u02fd\5\u00aeX\2\u02fd\u00b3\3\2\2\2"+
		"\u02fe\u02ff\7U\2\2\u02ff\u0300\7\6\2\2\u0300\u0301\5\u00ccg\2\u0301\u00b5"+
		"\3\2\2\2\u0302\u0303\7V\2\2\u0303\u0304\7\6\2\2\u0304\u0305\5\u00c6d\2"+
		"\u0305\u00b7\3\2\2\2\u0306\u0307\7W\2\2\u0307\u0308\7\6\2\2\u0308\u0309"+
		"\5\u00c6d\2\u0309\u00b9\3\2\2\2\u030a\u030b\7X\2\2\u030b\u030c\7\6\2\2"+
		"\u030c\u030d\5\u00d8m\2\u030d\u00bb\3\2\2\2\u030e\u030f\7Y\2\2\u030f\u0310"+
		"\7\6\2\2\u0310\u0311\5\u00c2b\2\u0311\u00bd\3\2\2\2\u0312\u0313\7Z\2\2"+
		"\u0313\u0314\7\6\2\2\u0314\u0315\5\u00d8m\2\u0315\u00bf\3\2\2\2\u0316"+
		"\u0317\t\5\2\2\u0317\u00c1\3\2\2\2\u0318\u0319\7`\2\2\u0319\u00c3\3\2"+
		"\2\2\u031a\u031b\t\6\2\2\u031b\u00c5\3\2\2\2\u031c\u031d\t\6\2\2\u031d"+
		"\u00c7\3\2\2\2\u031e\u031f\t\6\2\2\u031f\u00c9\3\2\2\2\u0320\u0321\t\7"+
		"\2\2\u0321\u00cb\3\2\2\2\u0322\u0323\t\7\2\2\u0323\u00cd\3\2\2\2\u0324"+
		"\u0325\7\24\2\2\u0325\u0326\5\u00c0a\2\u0326\u0327\7\25\2\2\u0327\u0328"+
		"\bh\1\2\u0328\u00cf\3\2\2\2\u0329\u032a\7_\2\2\u032a\u00d1\3\2\2\2\u032b"+
		"\u032c\7\7\2\2\u032c\u032d\5\u00c6d\2\u032d\u032e\7i\2\2\u032e\u032f\5"+
		"\u00c6d\2\u032f\u0330\7i\2\2\u0330\u0331\5\u00c6d\2\u0331\u0332\7\b\2"+
		"\2\u0332\u00d3\3\2\2\2\u0333\u0334\7\7\2\2\u0334\u0335\7\b\2\2\u0335\u00d5"+
		"\3\2\2\2\u0336\u0346\5\u00d4k\2\u0337\u0338\7\7\2\2\u0338\u0339\5\u00c0"+
		"a\2\u0339\u0340\bl\1\2\u033a\u033b\7i\2\2\u033b\u033c\5\u00c0a\2\u033c"+
		"\u033d\bl\1\2\u033d\u033f\3\2\2\2\u033e\u033a\3\2\2\2\u033f\u0342\3\2"+
		"\2\2\u0340\u033e\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0343\3\2\2\2\u0342"+
		"\u0340\3\2\2\2\u0343\u0344\7\b\2\2\u0344\u0346\3\2\2\2\u0345\u0336\3\2"+
		"\2\2\u0345\u0337\3\2\2\2\u0346\u00d7\3\2\2\2\u0347\u0357\5\u00d4k\2\u0348"+
		"\u0349\7\7\2\2\u0349\u034a\5\u00c6d\2\u034a\u0351\bm\1\2\u034b\u034c\7"+
		"i\2\2\u034c\u034d\5\u00c6d\2\u034d\u034e\bm\1\2\u034e\u0350\3\2\2\2\u034f"+
		"\u034b\3\2\2\2\u0350\u0353\3\2\2\2\u0351\u034f\3\2\2\2\u0351\u0352\3\2"+
		"\2\2\u0352\u0354\3\2\2\2\u0353\u0351\3\2\2\2\u0354\u0355\7\b\2\2\u0355"+
		"\u0357\3\2\2\2\u0356\u0347\3\2\2\2\u0356\u0348\3\2\2\2\u0357\u00d9\3\2"+
		"\2\2\u0358\u0368\5\u00d4k\2\u0359\u035a\7\7\2\2\u035a\u035b\5\u00caf\2"+
		"\u035b\u0362\bn\1\2\u035c\u035d\7i\2\2\u035d\u035e\5\u00caf\2\u035e\u035f"+
		"\bn\1\2\u035f\u0361\3\2\2\2\u0360\u035c\3\2\2\2\u0361\u0364\3\2\2\2\u0362"+
		"\u0360\3\2\2\2\u0362\u0363\3\2\2\2\u0363\u0365\3\2\2\2\u0364\u0362\3\2"+
		"\2\2\u0365\u0366\7\b\2\2\u0366\u0368\3\2\2\2\u0367\u0358\3\2\2\2\u0367"+
		"\u0359\3\2\2\2\u0368\u00db\3\2\2\2\u0369\u036a\7[\2\2\u036a\u036b\7\6"+
		"\2\2\u036b\u036c\5\u00dep\2\u036c\u036d\bo\1\2\u036d\u00dd\3\2\2\2\u036e"+
		"\u036f\7\7\2\2\u036f\u0370\5\u00e0q\2\u0370\u0371\7i\2\2\u0371\u0372\5"+
		"\u00c6d\2\u0372\u0373\7\b\2\2\u0373\u0374\bp\1\2\u0374\u00df\3\2\2\2\u0375"+
		"\u0376\7j\2\2\u0376\u0384\bq\1\2\u0377\u0378\7c\2\2\u0378\u0384\bq\1\2"+
		"\u0379\u037a\7d\2\2\u037a\u0384\bq\1\2\u037b\u037c\7e\2\2\u037c\u0384"+
		"\bq\1\2\u037d\u037e\7f\2\2\u037e\u0384\bq\1\2\u037f\u0380\7h\2\2\u0380"+
		"\u0384\bq\1\2\u0381\u0382\7g\2\2\u0382\u0384\bq\1\2\u0383\u0375\3\2\2"+
		"\2\u0383\u0377\3\2\2\2\u0383\u0379\3\2\2\2\u0383\u037b\3\2\2\2\u0383\u037d"+
		"\3\2\2\2\u0383\u037f\3\2\2\2\u0383\u0381\3\2\2\2\u0384\u00e1\3\2\2\2\u0385"+
		"\u0395\5\u00d4k\2\u0386\u0387\7\7\2\2\u0387\u0388\5\u00d0i\2\u0388\u038f"+
		"\br\1\2\u0389\u038a\7i\2\2\u038a\u038b\5\u00d0i\2\u038b\u038c\br\1\2\u038c"+
		"\u038e\3\2\2\2\u038d\u0389\3\2\2\2\u038e\u0391\3\2\2\2\u038f\u038d\3\2"+
		"\2\2\u038f\u0390\3\2\2\2\u0390\u0392\3\2\2\2\u0391\u038f\3\2\2\2\u0392"+
		"\u0393\7\b\2\2\u0393\u0395\3\2\2\2\u0394\u0385\3\2\2\2\u0394\u0386\3\2"+
		"\2\2\u0395\u00e3\3\2\2\2\30\u00e7\u015f\u0190\u0196\u019c\u0237\u027d"+
		"\u028e\u0296\u02c5\u02ca\u02ef\u02f4\u0340\u0345\u0351\u0356\u0362\u0367"+
		"\u0383\u038f\u0394";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}