// Generated from org\l2junity\gameserver\data\txt\gen\Settings.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.constants.CharacterClass;
import org.l2junity.gameserver.data.txt.model.constants.CharacterRace;
import org.l2junity.gameserver.data.txt.model.setting.CharacterStatTemplate;
import org.l2junity.gameserver.data.txt.model.setting.CharacterStatTemplate.*;

import java.util.Map;
import java.util.HashMap;


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
public class SettingsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, HUMAN_FIGHTER=24, 
		HUMAN_MAGICAN=25, ELF_FIGHTER=26, ELF_MAGICAN=27, DARKELF_FIGHTER=28, 
		DARKELF_MAGICAN=29, ORC_FIGHTER=30, ORC_SHAMAN=31, DWARF_APPRENTICE=32, 
		DWARF_MAGE=33, KAMAEL_M_SOLDIER=34, KAMAEL_F_SOLDIER=35, CATEGORY=36, 
		BOOLEAN=37, INTEGER=38, DOUBLE=39, FIRE=40, WATER=41, EARTH=42, WIND=43, 
		UNHOLY=44, HOLY=45, SEMICOLON=46, NONE=47, FAIRY=48, ANIMAL=49, HUMANOID=50, 
		PLANT=51, UNDEAD=52, CONSTRUCT=53, BEAST=54, BUG=55, ELEMENTAL=56, DEMONIC=57, 
		GIANT=58, DRAGON=59, DIVINE=60, SUMMON=61, PET=62, HOLYTHING=63, DWARF=64, 
		MERCHANT=65, ELF=66, KAMAEL=67, ORC=68, MERCENARY=69, CASTLE_GUARD=70, 
		HUMAN=71, BOSS=72, ZZOLDAGU=73, WORLD_TRAP=74, MONRACE=75, DARKELF=76, 
		GUARD=77, TELEPORTER=78, WAREHOUSE_KEEPER=79, WARRIOR=80, CITIZEN=81, 
		TREASURE=82, FIELDBOSS=83, BLACKSMITH=84, GUILD_MASTER=85, GUILD_COACH=86, 
		SWORD=87, BLUNT=88, BOW=89, POLE=90, DAGGER=91, DUAL=92, FIST=93, DUALFIST=94, 
		FISHINGROD=95, RAPIER=96, ANCIENTSWORD=97, CROSSBOW=98, FLAG=99, OWNTHING=100, 
		DUALDAGGER=101, ETC=102, LIGHT=103, HEAVY=104, MAGIC=105, SIGIL=106, RHAND=107, 
		LRHAND=108, LHAND=109, CHEST=110, LEGS=111, FEET=112, HEAD=113, GLOVES=114, 
		ONEPIECE=115, REAR=116, LEAR=117, RFINGER=118, LFINGER=119, NECK=120, 
		BACK=121, UNDERWEAR=122, HAIR=123, HAIR2=124, HAIRALL=125, ALLDRESS=126, 
		RBRACELET=127, LBRACELET=128, WAIST=129, DECO1=130, STEEL=131, FINE_STEEL=132, 
		WOOD=133, CLOTH=134, LEATHER=135, BONE=136, BRONZE=137, ORIHARUKON=138, 
		MITHRIL=139, DAMASCUS=140, ADAMANTAITE=141, BLOOD_STEEL=142, PAPER=143, 
		GOLD=144, LIQUID=145, FISH=146, SILVER=147, CHRYSOLITE=148, CRYSTAL=149, 
		HORN=150, SCALE_OF_DRAGON=151, COTTON=152, DYESTUFF=153, COBWEB=154, RUNE_XP=155, 
		RUNE_SP=156, RUNE_REMOVE_PENALTY=157, NAME=158, WS=159, LINE_COMMENT=160, 
		STAR_COMMENT=161;
	public static final int
		RULE_file = 0, RULE_initial_equipment = 1, RULE_initial_custom_equipment = 2, 
		RULE_character_equipment = 3, RULE_equipment_array = 4, RULE_equipment = 5, 
		RULE_initial_start_point = 6, RULE_start_point = 7, RULE_point = 8, RULE_classes = 9, 
		RULE_minimum_stat = 10, RULE_maximum_stat = 11, RULE_recommended_stat = 12, 
		RULE_character_stat = 13, RULE_character_race_class = 14, RULE_identifier_object = 15, 
		RULE_bool_object = 16, RULE_byte_object = 17, RULE_int_object = 18, RULE_long_object = 19, 
		RULE_double_object = 20, RULE_string_object = 21, RULE_name_object = 22, 
		RULE_category_object = 23, RULE_vector3D_object = 24, RULE_empty_list = 25, 
		RULE_identifier_list = 26, RULE_int_list = 27, RULE_double_list = 28, 
		RULE_base_attribute_attack = 29, RULE_attack_attribute = 30, RULE_attribute = 31, 
		RULE_category_list = 32;
	public static final String[] ruleNames = {
		"file", "initial_equipment", "initial_custom_equipment", "character_equipment", 
		"equipment_array", "equipment", "initial_start_point", "start_point", 
		"point", "classes", "minimum_stat", "maximum_stat", "recommended_stat", 
		"character_stat", "character_race_class", "identifier_object", "bool_object", 
		"byte_object", "int_object", "long_object", "double_object", "string_object", 
		"name_object", "category_object", "vector3D_object", "empty_list", "identifier_list", 
		"int_list", "double_list", "base_attribute_attack", "attack_attribute", 
		"attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'initial_equipment_begin'", "'initial_equipment_end'", "'initial_custom_equipment_begin'", 
		"'initial_custom_equipment_end'", "'='", "'{'", "'}'", "'initial_start_point_begin'", 
		"'initial_start_point_end'", "'point_begin'", "'point_end'", "'point'", 
		"'class'", "'minimum_stat_begin'", "'minimum_stat_end'", "'maximum_stat_begin'", 
		"'maximum_stat_end'", "'recommended_stat_begin'", "'recommended_stat_end'", 
		"'slot_lhand'", "'['", "']'", "'base_attribute_attack'", "'human_fighter'", 
		"'human_magician'", "'elf_fighter'", "'elf_magician'", "'darkelf_fighter'", 
		"'darkelf_magician'", "'orc_fighter'", "'orc_shaman'", "'dwarf_apprentice'", 
		"'dwarf_mage'", "'kamael_m_soldier'", "'kamael_f_soldier'", null, null, 
		null, null, "'fire'", "'water'", "'earth'", "'wind'", "'unholy'", "'holy'", 
		"';'", "'none'", "'fairy'", "'animal'", "'humanoid'", "'plant'", "'undead'", 
		"'construct'", "'beast'", "'bug'", "'elemental'", "'demonic'", "'giant'", 
		"'dragon'", "'divine'", "'summon'", "'pet'", "'holything'", "'dwarf'", 
		"'merchant'", "'elf'", "'kamael'", "'orc'", "'mercenary'", "'castle_guard'", 
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
		"HUMAN_FIGHTER", "HUMAN_MAGICAN", "ELF_FIGHTER", "ELF_MAGICAN", "DARKELF_FIGHTER", 
		"DARKELF_MAGICAN", "ORC_FIGHTER", "ORC_SHAMAN", "DWARF_APPRENTICE", "DWARF_MAGE", 
		"KAMAEL_M_SOLDIER", "KAMAEL_F_SOLDIER", "CATEGORY", "BOOLEAN", "INTEGER", 
		"DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", "SEMICOLON", 
		"NONE", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", "CONSTRUCT", 
		"BEAST", "BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", "DIVINE", "SUMMON", 
		"PET", "HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", "ORC", "MERCENARY", 
		"CASTLE_GUARD", "HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", "MONRACE", 
		"DARKELF", "GUARD", "TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", "CITIZEN", 
		"TREASURE", "FIELDBOSS", "BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", 
		"SWORD", "BLUNT", "BOW", "POLE", "DAGGER", "DUAL", "FIST", "DUALFIST", 
		"FISHINGROD", "RAPIER", "ANCIENTSWORD", "CROSSBOW", "FLAG", "OWNTHING", 
		"DUALDAGGER", "ETC", "LIGHT", "HEAVY", "MAGIC", "SIGIL", "RHAND", "LRHAND", 
		"LHAND", "CHEST", "LEGS", "FEET", "HEAD", "GLOVES", "ONEPIECE", "REAR", 
		"LEAR", "RFINGER", "LFINGER", "NECK", "BACK", "UNDERWEAR", "HAIR", "HAIR2", 
		"HAIRALL", "ALLDRESS", "RBRACELET", "LBRACELET", "WAIST", "DECO1", "STEEL", 
		"FINE_STEEL", "WOOD", "CLOTH", "LEATHER", "BONE", "BRONZE", "ORIHARUKON", 
		"MITHRIL", "DAMASCUS", "ADAMANTAITE", "BLOOD_STEEL", "PAPER", "GOLD", 
		"LIQUID", "FISH", "SILVER", "CHRYSOLITE", "CRYSTAL", "HORN", "SCALE_OF_DRAGON", 
		"COTTON", "DYESTUFF", "COBWEB", "RUNE_XP", "RUNE_SP", "RUNE_REMOVE_PENALTY", 
		"NAME", "WS", "LINE_COMMENT", "STAR_COMMENT"
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
	public String getGrammarFileName() { return "Settings.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SettingsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public Initial_equipmentContext initial_equipment() {
			return getRuleContext(Initial_equipmentContext.class,0);
		}
		public Initial_custom_equipmentContext initial_custom_equipment() {
			return getRuleContext(Initial_custom_equipmentContext.class,0);
		}
		public Initial_start_pointContext initial_start_point() {
			return getRuleContext(Initial_start_pointContext.class,0);
		}
		public Minimum_statContext minimum_stat() {
			return getRuleContext(Minimum_statContext.class,0);
		}
		public Maximum_statContext maximum_stat() {
			return getRuleContext(Maximum_statContext.class,0);
		}
		public Recommended_statContext recommended_stat() {
			return getRuleContext(Recommended_statContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			initial_equipment();
			setState(67);
			initial_custom_equipment();
			setState(68);
			initial_start_point();
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(69);
					matchWildcard();
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(75);
			minimum_stat();
			setState(76);
			maximum_stat();
			setState(77);
			recommended_stat();
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(78);
					matchWildcard();
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class Initial_equipmentContext extends ParserRuleContext {
		public List<Character_equipmentContext> character_equipment() {
			return getRuleContexts(Character_equipmentContext.class);
		}
		public Character_equipmentContext character_equipment(int i) {
			return getRuleContext(Character_equipmentContext.class,i);
		}
		public Initial_equipmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial_equipment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterInitial_equipment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitInitial_equipment(this);
		}
	}

	public final Initial_equipmentContext initial_equipment() throws RecognitionException {
		Initial_equipmentContext _localctx = new Initial_equipmentContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_initial_equipment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(T__0);
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85);
				character_equipment();
				}
				}
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HUMAN_FIGHTER) | (1L << HUMAN_MAGICAN) | (1L << ELF_FIGHTER) | (1L << ELF_MAGICAN) | (1L << DARKELF_FIGHTER) | (1L << DARKELF_MAGICAN) | (1L << ORC_FIGHTER) | (1L << ORC_SHAMAN) | (1L << DWARF_APPRENTICE) | (1L << DWARF_MAGE) | (1L << KAMAEL_M_SOLDIER) | (1L << KAMAEL_F_SOLDIER))) != 0) );
			setState(90);
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

	public static class Initial_custom_equipmentContext extends ParserRuleContext {
		public List<Character_equipmentContext> character_equipment() {
			return getRuleContexts(Character_equipmentContext.class);
		}
		public Character_equipmentContext character_equipment(int i) {
			return getRuleContext(Character_equipmentContext.class,i);
		}
		public Initial_custom_equipmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial_custom_equipment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterInitial_custom_equipment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitInitial_custom_equipment(this);
		}
	}

	public final Initial_custom_equipmentContext initial_custom_equipment() throws RecognitionException {
		Initial_custom_equipmentContext _localctx = new Initial_custom_equipmentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_initial_custom_equipment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__2);
			setState(94); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(93);
				character_equipment();
				}
				}
				setState(96); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HUMAN_FIGHTER) | (1L << HUMAN_MAGICAN) | (1L << ELF_FIGHTER) | (1L << ELF_MAGICAN) | (1L << DARKELF_FIGHTER) | (1L << DARKELF_MAGICAN) | (1L << ORC_FIGHTER) | (1L << ORC_SHAMAN) | (1L << DWARF_APPRENTICE) | (1L << DWARF_MAGE) | (1L << KAMAEL_M_SOLDIER) | (1L << KAMAEL_F_SOLDIER))) != 0) );
			setState(98);
			match(T__3);
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

	public static class Character_equipmentContext extends ParserRuleContext {
		public CharacterClass klass;
		public Map<String, Integer> equipmentMap;
		public Character_race_classContext crc;
		public Equipment_arrayContext ea;
		public Character_race_classContext character_race_class() {
			return getRuleContext(Character_race_classContext.class,0);
		}
		public Equipment_arrayContext equipment_array() {
			return getRuleContext(Equipment_arrayContext.class,0);
		}
		public Character_equipmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character_equipment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterCharacter_equipment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitCharacter_equipment(this);
		}
	}

	public final Character_equipmentContext character_equipment() throws RecognitionException {
		Character_equipmentContext _localctx = new Character_equipmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_character_equipment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			((Character_equipmentContext)_localctx).crc = character_race_class();
			setState(101);
			match(T__4);
			setState(102);
			((Character_equipmentContext)_localctx).ea = equipment_array();
			_localctx.klass = ((Character_equipmentContext)_localctx).crc.klass; _localctx.equipmentMap = ((Character_equipmentContext)_localctx).ea.value;
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

	public static class Equipment_arrayContext extends ParserRuleContext {
		public Map<String, Integer> value = new HashMap<>();;
		public EquipmentContext e;
		public List<EquipmentContext> equipment() {
			return getRuleContexts(EquipmentContext.class);
		}
		public EquipmentContext equipment(int i) {
			return getRuleContext(EquipmentContext.class,i);
		}
		public Equipment_arrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equipment_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterEquipment_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitEquipment_array(this);
		}
	}

	public final Equipment_arrayContext equipment_array() throws RecognitionException {
		Equipment_arrayContext _localctx = new Equipment_arrayContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_equipment_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__5);
			setState(106);
			((Equipment_arrayContext)_localctx).e = equipment();
			_localctx.value.put(((Equipment_arrayContext)_localctx).e.name, ((Equipment_arrayContext)_localctx).e.count);
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(108);
				match(SEMICOLON);
				setState(109);
				((Equipment_arrayContext)_localctx).e = equipment();
				_localctx.value.put(((Equipment_arrayContext)_localctx).e.name, ((Equipment_arrayContext)_localctx).e.count);
				}
				}
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			match(T__6);
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

	public static class EquipmentContext extends ParserRuleContext {
		public String name;
		public int count;
		public Name_objectContext no;
		public Int_objectContext io;
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public EquipmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equipment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterEquipment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitEquipment(this);
		}
	}

	public final EquipmentContext equipment() throws RecognitionException {
		EquipmentContext _localctx = new EquipmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_equipment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__5);
			setState(120);
			((EquipmentContext)_localctx).no = name_object();
			setState(121);
			match(SEMICOLON);
			setState(122);
			((EquipmentContext)_localctx).io = int_object();
			setState(123);
			match(T__6);
			_localctx.name = ((EquipmentContext)_localctx).no.value; _localctx.count=((EquipmentContext)_localctx).io.value;
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

	public static class Initial_start_pointContext extends ParserRuleContext {
		public List<Start_pointContext> start_point() {
			return getRuleContexts(Start_pointContext.class);
		}
		public Start_pointContext start_point(int i) {
			return getRuleContext(Start_pointContext.class,i);
		}
		public Initial_start_pointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial_start_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterInitial_start_point(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitInitial_start_point(this);
		}
	}

	public final Initial_start_pointContext initial_start_point() throws RecognitionException {
		Initial_start_pointContext _localctx = new Initial_start_pointContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_initial_start_point);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(T__7);
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127);
				start_point();
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__9 );
			setState(132);
			match(T__8);
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

	public static class Start_pointContext extends ParserRuleContext {
		public List<CharacterClass> klasses = new ArrayList<>();
		public List<Vector3D> points = new ArrayList<>();
		public PointContext point;
		public ClassesContext classes;
		public ClassesContext classes() {
			return getRuleContext(ClassesContext.class,0);
		}
		public List<PointContext> point() {
			return getRuleContexts(PointContext.class);
		}
		public PointContext point(int i) {
			return getRuleContext(PointContext.class,i);
		}
		public Start_pointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterStart_point(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitStart_point(this);
		}
	}

	public final Start_pointContext start_point() throws RecognitionException {
		Start_pointContext _localctx = new Start_pointContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_start_point);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__9);
			setState(138); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(135);
				((Start_pointContext)_localctx).point = point();
				_localctx.points.add(((Start_pointContext)_localctx).point.value);
				}
				}
				setState(140); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 );
			setState(142);
			((Start_pointContext)_localctx).classes = classes();
			_localctx.klasses = ((Start_pointContext)_localctx).classes.value;
			setState(144);
			match(T__10);
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

	public static class PointContext extends ParserRuleContext {
		public Vector3D value;
		public Vector3D_objectContext vo;
		public Vector3D_objectContext vector3D_object() {
			return getRuleContext(Vector3D_objectContext.class,0);
		}
		public PointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitPoint(this);
		}
	}

	public final PointContext point() throws RecognitionException {
		PointContext _localctx = new PointContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(T__11);
			setState(147);
			match(T__4);
			setState(148);
			((PointContext)_localctx).vo = vector3D_object();
			_localctx.value = ((PointContext)_localctx).vo.value;
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

	public static class ClassesContext extends ParserRuleContext {
		public List<CharacterClass> value = new ArrayList<>();
		public Character_race_classContext crc;
		public List<Character_race_classContext> character_race_class() {
			return getRuleContexts(Character_race_classContext.class);
		}
		public Character_race_classContext character_race_class(int i) {
			return getRuleContext(Character_race_classContext.class,i);
		}
		public ClassesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterClasses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitClasses(this);
		}
	}

	public final ClassesContext classes() throws RecognitionException {
		ClassesContext _localctx = new ClassesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(T__12);
			setState(152);
			match(T__4);
			setState(153);
			match(T__5);
			setState(154);
			((ClassesContext)_localctx).crc = character_race_class();
			 _localctx.value.add(((ClassesContext)_localctx).crc.klass); 
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(156);
				match(SEMICOLON);
				setState(157);
				((ClassesContext)_localctx).crc = character_race_class();
				 _localctx.value.add(((ClassesContext)_localctx).crc.klass); 
				}
				}
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			match(T__6);
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

	public static class Minimum_statContext extends ParserRuleContext {
		public List<CharacterStatTemplate> value = new ArrayList<>();
		public Character_statContext cs;
		public List<Character_statContext> character_stat() {
			return getRuleContexts(Character_statContext.class);
		}
		public Character_statContext character_stat(int i) {
			return getRuleContext(Character_statContext.class,i);
		}
		public Minimum_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minimum_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterMinimum_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitMinimum_stat(this);
		}
	}

	public final Minimum_statContext minimum_stat() throws RecognitionException {
		Minimum_statContext _localctx = new Minimum_statContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_minimum_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(T__13);
			setState(171); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(168);
				((Minimum_statContext)_localctx).cs = character_stat();
				_localctx.value.add(((Minimum_statContext)_localctx).cs.value);
				}
				}
				setState(173); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HUMAN_FIGHTER) | (1L << HUMAN_MAGICAN) | (1L << ELF_FIGHTER) | (1L << ELF_MAGICAN) | (1L << DARKELF_FIGHTER) | (1L << DARKELF_MAGICAN) | (1L << ORC_FIGHTER) | (1L << ORC_SHAMAN) | (1L << DWARF_APPRENTICE) | (1L << DWARF_MAGE) | (1L << KAMAEL_M_SOLDIER) | (1L << KAMAEL_F_SOLDIER))) != 0) );
			setState(175);
			match(T__14);
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

	public static class Maximum_statContext extends ParserRuleContext {
		public List<CharacterStatTemplate> value = new ArrayList<>();
		public Character_statContext cs;
		public List<Character_statContext> character_stat() {
			return getRuleContexts(Character_statContext.class);
		}
		public Character_statContext character_stat(int i) {
			return getRuleContext(Character_statContext.class,i);
		}
		public Maximum_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maximum_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterMaximum_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitMaximum_stat(this);
		}
	}

	public final Maximum_statContext maximum_stat() throws RecognitionException {
		Maximum_statContext _localctx = new Maximum_statContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_maximum_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__15);
			setState(181); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(178);
				((Maximum_statContext)_localctx).cs = character_stat();
				_localctx.value.add(((Maximum_statContext)_localctx).cs.value);
				}
				}
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HUMAN_FIGHTER) | (1L << HUMAN_MAGICAN) | (1L << ELF_FIGHTER) | (1L << ELF_MAGICAN) | (1L << DARKELF_FIGHTER) | (1L << DARKELF_MAGICAN) | (1L << ORC_FIGHTER) | (1L << ORC_SHAMAN) | (1L << DWARF_APPRENTICE) | (1L << DWARF_MAGE) | (1L << KAMAEL_M_SOLDIER) | (1L << KAMAEL_F_SOLDIER))) != 0) );
			setState(185);
			match(T__16);
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

	public static class Recommended_statContext extends ParserRuleContext {
		public List<CharacterStatTemplate> value = new ArrayList<>();
		public Character_statContext cs;
		public List<Character_statContext> character_stat() {
			return getRuleContexts(Character_statContext.class);
		}
		public Character_statContext character_stat(int i) {
			return getRuleContext(Character_statContext.class,i);
		}
		public Recommended_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_recommended_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterRecommended_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitRecommended_stat(this);
		}
	}

	public final Recommended_statContext recommended_stat() throws RecognitionException {
		Recommended_statContext _localctx = new Recommended_statContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_recommended_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(T__17);
			setState(191); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(188);
				((Recommended_statContext)_localctx).cs = character_stat();
				_localctx.value.add(((Recommended_statContext)_localctx).cs.value);
				}
				}
				setState(193); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HUMAN_FIGHTER) | (1L << HUMAN_MAGICAN) | (1L << ELF_FIGHTER) | (1L << ELF_MAGICAN) | (1L << DARKELF_FIGHTER) | (1L << DARKELF_MAGICAN) | (1L << ORC_FIGHTER) | (1L << ORC_SHAMAN) | (1L << DWARF_APPRENTICE) | (1L << DWARF_MAGE) | (1L << KAMAEL_M_SOLDIER) | (1L << KAMAEL_F_SOLDIER))) != 0) );
			setState(195);
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

	public static class Character_statContext extends ParserRuleContext {
		public CharacterStatTemplate value;
		public Character_race_classContext crc;
		public Int_listContext il;
		public Character_race_classContext character_race_class() {
			return getRuleContext(Character_race_classContext.class,0);
		}
		public Int_listContext int_list() {
			return getRuleContext(Int_listContext.class,0);
		}
		public Character_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterCharacter_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitCharacter_stat(this);
		}
	}

	public final Character_statContext character_stat() throws RecognitionException {
		Character_statContext _localctx = new Character_statContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_character_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			((Character_statContext)_localctx).crc = character_race_class();
			setState(198);
			match(T__4);
			setState(199);
			((Character_statContext)_localctx).il = int_list();
			_localctx.value = new CharacterStatTemplate(((Character_statContext)_localctx).crc.race, ((Character_statContext)_localctx).crc.klass, ((Character_statContext)_localctx).il.value);
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

	public static class Character_race_classContext extends ParserRuleContext {
		public CharacterRace race;
		public CharacterClass klass;
		public TerminalNode HUMAN_FIGHTER() { return getToken(SettingsParser.HUMAN_FIGHTER, 0); }
		public TerminalNode HUMAN_MAGICAN() { return getToken(SettingsParser.HUMAN_MAGICAN, 0); }
		public TerminalNode ELF_FIGHTER() { return getToken(SettingsParser.ELF_FIGHTER, 0); }
		public TerminalNode ELF_MAGICAN() { return getToken(SettingsParser.ELF_MAGICAN, 0); }
		public TerminalNode DARKELF_FIGHTER() { return getToken(SettingsParser.DARKELF_FIGHTER, 0); }
		public TerminalNode DARKELF_MAGICAN() { return getToken(SettingsParser.DARKELF_MAGICAN, 0); }
		public TerminalNode ORC_FIGHTER() { return getToken(SettingsParser.ORC_FIGHTER, 0); }
		public TerminalNode ORC_SHAMAN() { return getToken(SettingsParser.ORC_SHAMAN, 0); }
		public TerminalNode DWARF_APPRENTICE() { return getToken(SettingsParser.DWARF_APPRENTICE, 0); }
		public TerminalNode DWARF_MAGE() { return getToken(SettingsParser.DWARF_MAGE, 0); }
		public TerminalNode KAMAEL_M_SOLDIER() { return getToken(SettingsParser.KAMAEL_M_SOLDIER, 0); }
		public TerminalNode KAMAEL_F_SOLDIER() { return getToken(SettingsParser.KAMAEL_F_SOLDIER, 0); }
		public Character_race_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character_race_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterCharacter_race_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitCharacter_race_class(this);
		}
	}

	public final Character_race_classContext character_race_class() throws RecognitionException {
		Character_race_classContext _localctx = new Character_race_classContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_character_race_class);
		try {
			setState(226);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HUMAN_FIGHTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				match(HUMAN_FIGHTER);
				_localctx.race = CharacterRace.HUMAN; _localctx.klass = CharacterClass.HUMAN_FIGHTER;
				}
				break;
			case HUMAN_MAGICAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				match(HUMAN_MAGICAN);
				_localctx.race = CharacterRace.HUMAN; _localctx.klass = CharacterClass.HUMAN_MAGICIAN;
				}
				break;
			case ELF_FIGHTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(206);
				match(ELF_FIGHTER);
				_localctx.race = CharacterRace.ELF; _localctx.klass = CharacterClass.ELF_FIGHTER;
				}
				break;
			case ELF_MAGICAN:
				enterOuterAlt(_localctx, 4);
				{
				setState(208);
				match(ELF_MAGICAN);
				_localctx.race = CharacterRace.ELF; _localctx.klass = CharacterClass.ELF_MAGICIAN;
				}
				break;
			case DARKELF_FIGHTER:
				enterOuterAlt(_localctx, 5);
				{
				setState(210);
				match(DARKELF_FIGHTER);
				_localctx.race = CharacterRace.DARKELF; _localctx.klass = CharacterClass.DARKELF_FIGHTER;
				}
				break;
			case DARKELF_MAGICAN:
				enterOuterAlt(_localctx, 6);
				{
				setState(212);
				match(DARKELF_MAGICAN);
				_localctx.race = CharacterRace.DARKELF; _localctx.klass = CharacterClass.DARKELF_MAGICIAN;
				}
				break;
			case ORC_FIGHTER:
				enterOuterAlt(_localctx, 7);
				{
				setState(214);
				match(ORC_FIGHTER);
				_localctx.race = CharacterRace.ORC; _localctx.klass = CharacterClass.ORC_FIGHTER;
				}
				break;
			case ORC_SHAMAN:
				enterOuterAlt(_localctx, 8);
				{
				setState(216);
				match(ORC_SHAMAN);
				_localctx.race = CharacterRace.ORC; _localctx.klass = CharacterClass.ORC_SHAMAN;
				}
				break;
			case DWARF_APPRENTICE:
				enterOuterAlt(_localctx, 9);
				{
				setState(218);
				match(DWARF_APPRENTICE);
				_localctx.race = CharacterRace.DWARF; _localctx.klass = CharacterClass.DWARF_APPRENTICE;
				}
				break;
			case DWARF_MAGE:
				enterOuterAlt(_localctx, 10);
				{
				setState(220);
				match(DWARF_MAGE);
				_localctx.race = CharacterRace.DWARF; _localctx.klass = CharacterClass.DWARF_MAGE;
				}
				break;
			case KAMAEL_M_SOLDIER:
				enterOuterAlt(_localctx, 11);
				{
				setState(222);
				match(KAMAEL_M_SOLDIER);
				_localctx.race = CharacterRace.KAMAEL; _localctx.klass = CharacterClass.KAMAEL_M_SOLDIER;
				}
				break;
			case KAMAEL_F_SOLDIER:
				enterOuterAlt(_localctx, 12);
				{
				setState(224);
				match(KAMAEL_F_SOLDIER);
				_localctx.race = CharacterRace.KAMAEL; _localctx.klass = CharacterClass.KAMAEL_F_SOLDIER;
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

	public static class Identifier_objectContext extends ParserRuleContext {
		public String value;
		public TerminalNode DAGGER() { return getToken(SettingsParser.DAGGER, 0); }
		public TerminalNode BOW() { return getToken(SettingsParser.BOW, 0); }
		public TerminalNode CROSSBOW() { return getToken(SettingsParser.CROSSBOW, 0); }
		public TerminalNode RAPIER() { return getToken(SettingsParser.RAPIER, 0); }
		public TerminalNode GLOVES() { return getToken(SettingsParser.GLOVES, 0); }
		public TerminalNode STEEL() { return getToken(SettingsParser.STEEL, 0); }
		public TerminalNode LEATHER() { return getToken(SettingsParser.LEATHER, 0); }
		public TerminalNode ORIHARUKON() { return getToken(SettingsParser.ORIHARUKON, 0); }
		public TerminalNode NAME() { return getToken(SettingsParser.NAME, 0); }
		public TerminalNode NONE() { return getToken(SettingsParser.NONE, 0); }
		public TerminalNode ORC() { return getToken(SettingsParser.ORC, 0); }
		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterIdentifier_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_la = _input.LA(1);
			if ( !(_la==T__19 || _la==NONE || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (ORC - 68)) | (1L << (BOW - 68)) | (1L << (DAGGER - 68)) | (1L << (RAPIER - 68)) | (1L << (CROSSBOW - 68)) | (1L << (GLOVES - 68)) | (1L << (STEEL - 68)))) != 0) || ((((_la - 135)) & ~0x3f) == 0 && ((1L << (_la - 135)) & ((1L << (LEATHER - 135)) | (1L << (ORIHARUKON - 135)) | (1L << (NAME - 135)))) != 0)) ) {
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
		public TerminalNode BOOLEAN() { return getToken(SettingsParser.BOOLEAN, 0); }
		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterBool_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
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
		public TerminalNode BOOLEAN() { return getToken(SettingsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(SettingsParser.INTEGER, 0); }
		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterByte_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
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
		public TerminalNode BOOLEAN() { return getToken(SettingsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(SettingsParser.INTEGER, 0); }
		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterInt_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(234);
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
		public TerminalNode BOOLEAN() { return getToken(SettingsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(SettingsParser.INTEGER, 0); }
		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterLong_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
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
		public TerminalNode BOOLEAN() { return getToken(SettingsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(SettingsParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(SettingsParser.DOUBLE, 0); }
		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterDouble_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << DOUBLE))) != 0)) ) {
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
		public TerminalNode BOOLEAN() { return getToken(SettingsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(SettingsParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(SettingsParser.DOUBLE, 0); }
		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterString_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << DOUBLE))) != 0)) ) {
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
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterName_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(T__20);
			setState(243);
			((Name_objectContext)_localctx).io = identifier_object();
			setState(244);
			match(T__21);
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
		public TerminalNode CATEGORY() { return getToken(SettingsParser.CATEGORY, 0); }
		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterCategory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(SettingsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SettingsParser.SEMICOLON, i);
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
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterVector3D_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(T__5);
			setState(250);
			((Vector3D_objectContext)_localctx).x = int_object();
			setState(251);
			match(SEMICOLON);
			setState(252);
			((Vector3D_objectContext)_localctx).y = int_object();
			setState(253);
			match(SEMICOLON);
			setState(254);
			((Vector3D_objectContext)_localctx).z = int_object();
			setState(255);
			match(T__6);
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
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterEmpty_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(T__5);
			setState(258);
			match(T__6);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(SettingsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SettingsParser.SEMICOLON, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_identifier_list);
		 _localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(T__5);
				setState(262);
				((Identifier_listContext)_localctx).io = identifier_object();
				 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(264);
					match(SEMICOLON);
					setState(265);
					((Identifier_listContext)_localctx).io = identifier_object();
					 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
					}
					}
					setState(272);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(273);
				match(T__6);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(SettingsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SettingsParser.SEMICOLON, i);
		}
		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterInt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_int_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(292);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(278);
				match(T__5);
				setState(279);
				((Int_listContext)_localctx).io = int_object();
				 _localctx.value.add(((Int_listContext)_localctx).io.value); 
				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(281);
					match(SEMICOLON);
					setState(282);
					((Int_listContext)_localctx).io = int_object();
					_localctx.value.add(((Int_listContext)_localctx).io.value);
					}
					}
					setState(289);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(290);
				match(T__6);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(SettingsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SettingsParser.SEMICOLON, i);
		}
		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterDouble_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_double_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(294);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(T__5);
				setState(296);
				((Double_listContext)_localctx).d = double_object();
				 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(298);
					match(SEMICOLON);
					setState(299);
					((Double_listContext)_localctx).d = double_object();
					 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
					}
					}
					setState(306);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(307);
				match(T__6);
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
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterBase_attribute_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(T__22);
			setState(312);
			match(T__4);
			setState(313);
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
		public TerminalNode SEMICOLON() { return getToken(SettingsParser.SEMICOLON, 0); }
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterAttack_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			match(T__5);
			setState(317);
			((Attack_attributeContext)_localctx).attribute = attribute();
			setState(318);
			match(SEMICOLON);
			setState(319);
			((Attack_attributeContext)_localctx).io = int_object();
			setState(320);
			match(T__6);
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
		public TerminalNode NONE() { return getToken(SettingsParser.NONE, 0); }
		public TerminalNode FIRE() { return getToken(SettingsParser.FIRE, 0); }
		public TerminalNode WATER() { return getToken(SettingsParser.WATER, 0); }
		public TerminalNode EARTH() { return getToken(SettingsParser.EARTH, 0); }
		public TerminalNode WIND() { return getToken(SettingsParser.WIND, 0); }
		public TerminalNode HOLY() { return getToken(SettingsParser.HOLY, 0); }
		public TerminalNode UNHOLY() { return getToken(SettingsParser.UNHOLY, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_attribute);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				match(NONE);
				_localctx.value = AttributeType.NONE;
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				match(FIRE);
				_localctx.value = AttributeType.FIRE;
				}
				break;
			case WATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(327);
				match(WATER);
				_localctx.value = AttributeType.WATER;
				}
				break;
			case EARTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(329);
				match(EARTH);
				_localctx.value = AttributeType.EARTH;
				}
				break;
			case WIND:
				enterOuterAlt(_localctx, 5);
				{
				setState(331);
				match(WIND);
				_localctx.value = AttributeType.WIND;
				}
				break;
			case HOLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(333);
				match(HOLY);
				_localctx.value = AttributeType.HOLY;
				}
				break;
			case UNHOLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(335);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(SettingsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SettingsParser.SEMICOLON, i);
		}
		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).enterCategory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SettingsListener ) ((SettingsListener)listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_category_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				match(T__5);
				setState(341);
				((Category_listContext)_localctx).co = category_object();
				 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(343);
					match(SEMICOLON);
					setState(344);
					((Category_listContext)_localctx).co = category_object();
					 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
					}
					}
					setState(351);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(352);
				match(T__6);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00a3\u0167\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\7\2I\n\2\f\2\16\2L\13\2\3\2\3\2\3\2\3\2\7"+
		"\2R\n\2\f\2\16\2U\13\2\3\3\3\3\6\3Y\n\3\r\3\16\3Z\3\3\3\3\3\4\3\4\6\4"+
		"a\n\4\r\4\16\4b\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\7\6s\n\6\f\6\16\6v\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\6\b\u0083\n\b\r\b\16\b\u0084\3\b\3\b\3\t\3\t\3\t\3\t\6\t\u008d\n\t\r"+
		"\t\16\t\u008e\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\7\13\u00a3\n\13\f\13\16\13\u00a6\13\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\6\f\u00ae\n\f\r\f\16\f\u00af\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\6\r\u00b8\n\r\r\r\16\r\u00b9\3\r\3\r\3\16\3\16\3\16\3\16\6\16\u00c2"+
		"\n\16\r\16\16\16\u00c3\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00e5\n\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u010f\n\34\f\34\16"+
		"\34\u0112\13\34\3\34\3\34\5\34\u0116\n\34\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\7\35\u0120\n\35\f\35\16\35\u0123\13\35\3\35\3\35\5\35\u0127"+
		"\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u0131\n\36\f\36\16"+
		"\36\u0134\13\36\3\36\3\36\5\36\u0138\n\36\3\37\3\37\3\37\3\37\3\37\3 "+
		"\3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0154"+
		"\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\7\"\u015e\n\"\f\"\16\"\u0161\13\""+
		"\3\"\3\"\5\"\u0165\n\"\3\"\4JS\2#\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\62\64\668:<>@B\2\5\16\2\26\26\61\61FF[[]]bbddtt\u0085"+
		"\u0085\u0089\u0089\u008c\u008c\u00a0\u00a0\3\2\'(\3\2\')\2\u0169\2D\3"+
		"\2\2\2\4V\3\2\2\2\6^\3\2\2\2\bf\3\2\2\2\nk\3\2\2\2\fy\3\2\2\2\16\u0080"+
		"\3\2\2\2\20\u0088\3\2\2\2\22\u0094\3\2\2\2\24\u0099\3\2\2\2\26\u00a9\3"+
		"\2\2\2\30\u00b3\3\2\2\2\32\u00bd\3\2\2\2\34\u00c7\3\2\2\2\36\u00e4\3\2"+
		"\2\2 \u00e6\3\2\2\2\"\u00e8\3\2\2\2$\u00ea\3\2\2\2&\u00ec\3\2\2\2(\u00ee"+
		"\3\2\2\2*\u00f0\3\2\2\2,\u00f2\3\2\2\2.\u00f4\3\2\2\2\60\u00f9\3\2\2\2"+
		"\62\u00fb\3\2\2\2\64\u0103\3\2\2\2\66\u0115\3\2\2\28\u0126\3\2\2\2:\u0137"+
		"\3\2\2\2<\u0139\3\2\2\2>\u013e\3\2\2\2@\u0153\3\2\2\2B\u0164\3\2\2\2D"+
		"E\5\4\3\2EF\5\6\4\2FJ\5\16\b\2GI\13\2\2\2HG\3\2\2\2IL\3\2\2\2JK\3\2\2"+
		"\2JH\3\2\2\2KM\3\2\2\2LJ\3\2\2\2MN\5\26\f\2NO\5\30\r\2OS\5\32\16\2PR\13"+
		"\2\2\2QP\3\2\2\2RU\3\2\2\2ST\3\2\2\2SQ\3\2\2\2T\3\3\2\2\2US\3\2\2\2VX"+
		"\7\3\2\2WY\5\b\5\2XW\3\2\2\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2"+
		"\\]\7\4\2\2]\5\3\2\2\2^`\7\5\2\2_a\5\b\5\2`_\3\2\2\2ab\3\2\2\2b`\3\2\2"+
		"\2bc\3\2\2\2cd\3\2\2\2de\7\6\2\2e\7\3\2\2\2fg\5\36\20\2gh\7\7\2\2hi\5"+
		"\n\6\2ij\b\5\1\2j\t\3\2\2\2kl\7\b\2\2lm\5\f\7\2mt\b\6\1\2no\7\60\2\2o"+
		"p\5\f\7\2pq\b\6\1\2qs\3\2\2\2rn\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2"+
		"uw\3\2\2\2vt\3\2\2\2wx\7\t\2\2x\13\3\2\2\2yz\7\b\2\2z{\5.\30\2{|\7\60"+
		"\2\2|}\5&\24\2}~\7\t\2\2~\177\b\7\1\2\177\r\3\2\2\2\u0080\u0082\7\n\2"+
		"\2\u0081\u0083\5\20\t\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\13"+
		"\2\2\u0087\17\3\2\2\2\u0088\u008c\7\f\2\2\u0089\u008a\5\22\n\2\u008a\u008b"+
		"\b\t\1\2\u008b\u008d\3\2\2\2\u008c\u0089\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\5\24"+
		"\13\2\u0091\u0092\b\t\1\2\u0092\u0093\7\r\2\2\u0093\21\3\2\2\2\u0094\u0095"+
		"\7\16\2\2\u0095\u0096\7\7\2\2\u0096\u0097\5\62\32\2\u0097\u0098\b\n\1"+
		"\2\u0098\23\3\2\2\2\u0099\u009a\7\17\2\2\u009a\u009b\7\7\2\2\u009b\u009c"+
		"\7\b\2\2\u009c\u009d\5\36\20\2\u009d\u00a4\b\13\1\2\u009e\u009f\7\60\2"+
		"\2\u009f\u00a0\5\36\20\2\u00a0\u00a1\b\13\1\2\u00a1\u00a3\3\2\2\2\u00a2"+
		"\u009e\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7\t\2\2\u00a8"+
		"\25\3\2\2\2\u00a9\u00ad\7\20\2\2\u00aa\u00ab\5\34\17\2\u00ab\u00ac\b\f"+
		"\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7\21"+
		"\2\2\u00b2\27\3\2\2\2\u00b3\u00b7\7\22\2\2\u00b4\u00b5\5\34\17\2\u00b5"+
		"\u00b6\b\r\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00b4\3\2\2\2\u00b8\u00b9\3\2"+
		"\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bc\7\23\2\2\u00bc\31\3\2\2\2\u00bd\u00c1\7\24\2\2\u00be\u00bf\5\34"+
		"\17\2\u00bf\u00c0\b\16\1\2\u00c0\u00c2\3\2\2\2\u00c1\u00be\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2"+
		"\2\2\u00c5\u00c6\7\25\2\2\u00c6\33\3\2\2\2\u00c7\u00c8\5\36\20\2\u00c8"+
		"\u00c9\7\7\2\2\u00c9\u00ca\58\35\2\u00ca\u00cb\b\17\1\2\u00cb\35\3\2\2"+
		"\2\u00cc\u00cd\7\32\2\2\u00cd\u00e5\b\20\1\2\u00ce\u00cf\7\33\2\2\u00cf"+
		"\u00e5\b\20\1\2\u00d0\u00d1\7\34\2\2\u00d1\u00e5\b\20\1\2\u00d2\u00d3"+
		"\7\35\2\2\u00d3\u00e5\b\20\1\2\u00d4\u00d5\7\36\2\2\u00d5\u00e5\b\20\1"+
		"\2\u00d6\u00d7\7\37\2\2\u00d7\u00e5\b\20\1\2\u00d8\u00d9\7 \2\2\u00d9"+
		"\u00e5\b\20\1\2\u00da\u00db\7!\2\2\u00db\u00e5\b\20\1\2\u00dc\u00dd\7"+
		"\"\2\2\u00dd\u00e5\b\20\1\2\u00de\u00df\7#\2\2\u00df\u00e5\b\20\1\2\u00e0"+
		"\u00e1\7$\2\2\u00e1\u00e5\b\20\1\2\u00e2\u00e3\7%\2\2\u00e3\u00e5\b\20"+
		"\1\2\u00e4\u00cc\3\2\2\2\u00e4\u00ce\3\2\2\2\u00e4\u00d0\3\2\2\2\u00e4"+
		"\u00d2\3\2\2\2\u00e4\u00d4\3\2\2\2\u00e4\u00d6\3\2\2\2\u00e4\u00d8\3\2"+
		"\2\2\u00e4\u00da\3\2\2\2\u00e4\u00dc\3\2\2\2\u00e4\u00de\3\2\2\2\u00e4"+
		"\u00e0\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\37\3\2\2\2\u00e6\u00e7\t\2\2"+
		"\2\u00e7!\3\2\2\2\u00e8\u00e9\7\'\2\2\u00e9#\3\2\2\2\u00ea\u00eb\t\3\2"+
		"\2\u00eb%\3\2\2\2\u00ec\u00ed\t\3\2\2\u00ed\'\3\2\2\2\u00ee\u00ef\t\3"+
		"\2\2\u00ef)\3\2\2\2\u00f0\u00f1\t\4\2\2\u00f1+\3\2\2\2\u00f2\u00f3\t\4"+
		"\2\2\u00f3-\3\2\2\2\u00f4\u00f5\7\27\2\2\u00f5\u00f6\5 \21\2\u00f6\u00f7"+
		"\7\30\2\2\u00f7\u00f8\b\30\1\2\u00f8/\3\2\2\2\u00f9\u00fa\7&\2\2\u00fa"+
		"\61\3\2\2\2\u00fb\u00fc\7\b\2\2\u00fc\u00fd\5&\24\2\u00fd\u00fe\7\60\2"+
		"\2\u00fe\u00ff\5&\24\2\u00ff\u0100\7\60\2\2\u0100\u0101\5&\24\2\u0101"+
		"\u0102\7\t\2\2\u0102\63\3\2\2\2\u0103\u0104\7\b\2\2\u0104\u0105\7\t\2"+
		"\2\u0105\65\3\2\2\2\u0106\u0116\5\64\33\2\u0107\u0108\7\b\2\2\u0108\u0109"+
		"\5 \21\2\u0109\u0110\b\34\1\2\u010a\u010b\7\60\2\2\u010b\u010c\5 \21\2"+
		"\u010c\u010d\b\34\1\2\u010d\u010f\3\2\2\2\u010e\u010a\3\2\2\2\u010f\u0112"+
		"\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112"+
		"\u0110\3\2\2\2\u0113\u0114\7\t\2\2\u0114\u0116\3\2\2\2\u0115\u0106\3\2"+
		"\2\2\u0115\u0107\3\2\2\2\u0116\67\3\2\2\2\u0117\u0127\5\64\33\2\u0118"+
		"\u0119\7\b\2\2\u0119\u011a\5&\24\2\u011a\u0121\b\35\1\2\u011b\u011c\7"+
		"\60\2\2\u011c\u011d\5&\24\2\u011d\u011e\b\35\1\2\u011e\u0120\3\2\2\2\u011f"+
		"\u011b\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122\3\2"+
		"\2\2\u0122\u0124\3\2\2\2\u0123\u0121\3\2\2\2\u0124\u0125\7\t\2\2\u0125"+
		"\u0127\3\2\2\2\u0126\u0117\3\2\2\2\u0126\u0118\3\2\2\2\u01279\3\2\2\2"+
		"\u0128\u0138\5\64\33\2\u0129\u012a\7\b\2\2\u012a\u012b\5*\26\2\u012b\u0132"+
		"\b\36\1\2\u012c\u012d\7\60\2\2\u012d\u012e\5*\26\2\u012e\u012f\b\36\1"+
		"\2\u012f\u0131\3\2\2\2\u0130\u012c\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130"+
		"\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135"+
		"\u0136\7\t\2\2\u0136\u0138\3\2\2\2\u0137\u0128\3\2\2\2\u0137\u0129\3\2"+
		"\2\2\u0138;\3\2\2\2\u0139\u013a\7\31\2\2\u013a\u013b\7\7\2\2\u013b\u013c"+
		"\5> \2\u013c\u013d\b\37\1\2\u013d=\3\2\2\2\u013e\u013f\7\b\2\2\u013f\u0140"+
		"\5@!\2\u0140\u0141\7\60\2\2\u0141\u0142\5&\24\2\u0142\u0143\7\t\2\2\u0143"+
		"\u0144\b \1\2\u0144?\3\2\2\2\u0145\u0146\7\61\2\2\u0146\u0154\b!\1\2\u0147"+
		"\u0148\7*\2\2\u0148\u0154\b!\1\2\u0149\u014a\7+\2\2\u014a\u0154\b!\1\2"+
		"\u014b\u014c\7,\2\2\u014c\u0154\b!\1\2\u014d\u014e\7-\2\2\u014e\u0154"+
		"\b!\1\2\u014f\u0150\7/\2\2\u0150\u0154\b!\1\2\u0151\u0152\7.\2\2\u0152"+
		"\u0154\b!\1\2\u0153\u0145\3\2\2\2\u0153\u0147\3\2\2\2\u0153\u0149\3\2"+
		"\2\2\u0153\u014b\3\2\2\2\u0153\u014d\3\2\2\2\u0153\u014f\3\2\2\2\u0153"+
		"\u0151\3\2\2\2\u0154A\3\2\2\2\u0155\u0165\5\64\33\2\u0156\u0157\7\b\2"+
		"\2\u0157\u0158\5\60\31\2\u0158\u015f\b\"\1\2\u0159\u015a\7\60\2\2\u015a"+
		"\u015b\5\60\31\2\u015b\u015c\b\"\1\2\u015c\u015e\3\2\2\2\u015d\u0159\3"+
		"\2\2\2\u015e\u0161\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162\u0163\7\t\2\2\u0163\u0165\3\2"+
		"\2\2\u0164\u0155\3\2\2\2\u0164\u0156\3\2\2\2\u0165C\3\2\2\2\27JSZbt\u0084"+
		"\u008e\u00a4\u00af\u00b9\u00c3\u00e4\u0110\u0115\u0121\u0126\u0132\u0137"+
		"\u0153\u015f\u0164";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}