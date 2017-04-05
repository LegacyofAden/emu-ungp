// Generated from org\l2junity\gameserver\data\txt\gen\PCParameters.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

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
public class PCParametersParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, FFIGHTER=11, MFIGHTER=12, FMAGIC=13, MMAGIC=14, FELF_FIGHTER=15, 
		MELF_FIGHTER=16, FELF_MAGIC=17, MELF_MAGIC=18, FDARKELF_FIGHTER=19, MDARKELF_FIGHTER=20, 
		FDARKELF_MAGIC=21, MDARKELF_MAGIC=22, FORC_FIGHTER=23, MORC_FIGHTER=24, 
		FSHAMAN=25, MSHAMAN=26, FDWARF_FIGHTER=27, MDWARF_FIGHTER=28, FDWARF_MAGE=29, 
		MDWARF_MAGE=30, FKAMAEL_SOLDIER=31, MKAMAEL_SOLDIER=32, FKAMAEL_MAGE=33, 
		MKAMAEL_MAGE=34, CATEGORY=35, BOOLEAN=36, INTEGER=37, DOUBLE=38, FIRE=39, 
		WATER=40, EARTH=41, WIND=42, UNHOLY=43, HOLY=44, SEMICOLON=45, NONE=46, 
		FAIRY=47, ANIMAL=48, HUMANOID=49, PLANT=50, UNDEAD=51, CONSTRUCT=52, BEAST=53, 
		BUG=54, ELEMENTAL=55, DEMONIC=56, GIANT=57, DRAGON=58, DIVINE=59, SUMMON=60, 
		PET=61, HOLYTHING=62, DWARF=63, MERCHANT=64, ELF=65, KAMAEL=66, ORC=67, 
		MERCENARY=68, CASTLE_GUARD=69, HUMAN=70, BOSS=71, ZZOLDAGU=72, WORLD_TRAP=73, 
		MONRACE=74, DARKELF=75, GUARD=76, TELEPORTER=77, WAREHOUSE_KEEPER=78, 
		WARRIOR=79, CITIZEN=80, TREASURE=81, FIELDBOSS=82, BLACKSMITH=83, GUILD_MASTER=84, 
		GUILD_COACH=85, SWORD=86, BLUNT=87, BOW=88, POLE=89, DAGGER=90, DUAL=91, 
		FIST=92, DUALFIST=93, FISHINGROD=94, RAPIER=95, ANCIENTSWORD=96, CROSSBOW=97, 
		FLAG=98, OWNTHING=99, DUALDAGGER=100, ETC=101, LIGHT=102, HEAVY=103, MAGIC=104, 
		SIGIL=105, RHAND=106, LRHAND=107, LHAND=108, CHEST=109, LEGS=110, FEET=111, 
		HEAD=112, GLOVES=113, ONEPIECE=114, REAR=115, LEAR=116, RFINGER=117, LFINGER=118, 
		NECK=119, BACK=120, UNDERWEAR=121, HAIR=122, HAIR2=123, HAIRALL=124, ALLDRESS=125, 
		RBRACELET=126, LBRACELET=127, WAIST=128, DECO1=129, STEEL=130, FINE_STEEL=131, 
		WOOD=132, CLOTH=133, LEATHER=134, BONE=135, BRONZE=136, ORIHARUKON=137, 
		MITHRIL=138, DAMASCUS=139, ADAMANTAITE=140, BLOOD_STEEL=141, PAPER=142, 
		GOLD=143, LIQUID=144, FISH=145, SILVER=146, CHRYSOLITE=147, CRYSTAL=148, 
		HORN=149, SCALE_OF_DRAGON=150, COTTON=151, DYESTUFF=152, COBWEB=153, RUNE_XP=154, 
		RUNE_SP=155, RUNE_REMOVE_PENALTY=156, NAME=157, WS=158, LINE_COMMENT=159, 
		STAR_COMMENT=160;
	public static final int
		RULE_file = 0, RULE_base_physical_attack_table = 1, RULE_base_physical_attack = 2, 
		RULE_pc_collision_box_table = 3, RULE_collision_stat = 4, RULE_pc_name = 5, 
		RULE_identifier_object = 6, RULE_bool_object = 7, RULE_byte_object = 8, 
		RULE_int_object = 9, RULE_long_object = 10, RULE_double_object = 11, RULE_string_object = 12, 
		RULE_name_object = 13, RULE_category_object = 14, RULE_vector3D_object = 15, 
		RULE_empty_list = 16, RULE_identifier_list = 17, RULE_int_list = 18, RULE_double_list = 19, 
		RULE_base_attribute_attack = 20, RULE_attack_attribute = 21, RULE_attribute = 22, 
		RULE_category_list = 23;
	public static final String[] ruleNames = {
		"file", "base_physical_attack_table", "base_physical_attack", "pc_collision_box_table", 
		"collision_stat", "pc_name", "identifier_object", "bool_object", "byte_object", 
		"int_object", "long_object", "double_object", "string_object", "name_object", 
		"category_object", "vector3D_object", "empty_list", "identifier_list", 
		"int_list", "double_list", "base_attribute_attack", "attack_attribute", 
		"attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'base_physical_attack_begin'", "'='", "'pc_collision_box_table_begin'", 
		"'pc_collision_box_table_end'", "'slot_lhand'", "'['", "']'", "'{'", "'}'", 
		"'base_attribute_attack'", "'FFighter'", "'MFighter'", "'FMagic'", "'MMagic'", 
		"'FElfFighter'", "'MElfFighter'", "'FElfMagic'", "'MElfMagic'", "'FDarkelfFighter'", 
		"'MDarkelfFighter'", "'FDarkelfMagic'", "'MDarkelfMagic'", "'FOrcFighter'", 
		"'MOrcFighter'", "'FShaman'", "'MShaman'", "'FDwarfFighter'", "'MDwarfFighter'", 
		"'DwarfMage'", "'MDwarfMage'", "'FKamaelSoldier'", "'MKamaelSoldier'", 
		"'FKamaelMage'", "'MKamaelMage'", null, null, null, null, "'fire'", "'water'", 
		"'earth'", "'wind'", "'unholy'", "'holy'", "';'", "'none'", "'fairy'", 
		"'animal'", "'humanoid'", "'plant'", "'undead'", "'construct'", "'beast'", 
		"'bug'", "'elemental'", "'demonic'", "'giant'", "'dragon'", "'divine'", 
		"'summon'", "'pet'", "'holything'", "'dwarf'", "'merchant'", "'elf'", 
		"'kamael'", "'orc'", "'mercenary'", "'castle_guard'", "'human'", "'boss'", 
		"'zzoldagu'", "'world_trap'", "'monrace'", "'darkelf'", "'guard'", "'teleporter'", 
		"'warehouse_keeper'", "'warrior'", "'citizen'", "'treasure'", "'fieldboss'", 
		"'blacksmith'", "'guild_master'", "'guild_coach'", "'sword'", "'blunt'", 
		"'bow'", "'pole'", "'dagger'", "'dual'", "'fist'", "'dualfist'", "'fishingrod'", 
		"'rapier'", "'ancientsword'", "'crossbow'", "'flag'", "'ownthing'", "'dualdagger'", 
		"'etc'", "'light'", "'heavy'", "'magic'", "'sigil'", "'rhand'", "'lrhand'", 
		"'lhand'", "'chest'", "'legs'", "'feet'", "'head'", "'gloves'", "'onepiece'", 
		"'rear'", "'lear'", "'rfinger'", "'lfinger'", "'neck'", "'back'", "'underwear'", 
		"'hair'", "'hair2'", "'hairall'", "'alldress'", "'rbracelet'", "'lbracelet'", 
		"'waist'", "'deco1'", "'steel'", "'fine_steel'", "'wood'", "'cloth'", 
		"'leather'", "'bone'", "'bronze'", "'oriharukon'", "'mithril'", "'damascus'", 
		"'adamantaite'", "'blood_steel'", "'paper'", "'gold'", "'liquid'", "'fish'", 
		"'silver'", "'chrysolite'", "'crystal'", "'horn'", "'scale_of_dragon'", 
		"'cotton'", "'dyestuff'", "'cobweb'", "'rune_xp'", "'rune_sp'", "'rune_remove_penalty'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, "FFIGHTER", 
		"MFIGHTER", "FMAGIC", "MMAGIC", "FELF_FIGHTER", "MELF_FIGHTER", "FELF_MAGIC", 
		"MELF_MAGIC", "FDARKELF_FIGHTER", "MDARKELF_FIGHTER", "FDARKELF_MAGIC", 
		"MDARKELF_MAGIC", "FORC_FIGHTER", "MORC_FIGHTER", "FSHAMAN", "MSHAMAN", 
		"FDWARF_FIGHTER", "MDWARF_FIGHTER", "FDWARF_MAGE", "MDWARF_MAGE", "FKAMAEL_SOLDIER", 
		"MKAMAEL_SOLDIER", "FKAMAEL_MAGE", "MKAMAEL_MAGE", "CATEGORY", "BOOLEAN", 
		"INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", 
		"SEMICOLON", "NONE", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", 
		"CONSTRUCT", "BEAST", "BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", 
		"DIVINE", "SUMMON", "PET", "HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", 
		"ORC", "MERCENARY", "CASTLE_GUARD", "HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", 
		"MONRACE", "DARKELF", "GUARD", "TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", 
		"CITIZEN", "TREASURE", "FIELDBOSS", "BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", 
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
	public String getGrammarFileName() { return "PCParameters.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PCParametersParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public Base_physical_attack_tableContext base_physical_attack_table() {
			return getRuleContext(Base_physical_attack_tableContext.class,0);
		}
		public Pc_collision_box_tableContext pc_collision_box_table() {
			return getRuleContext(Pc_collision_box_tableContext.class,0);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(48);
					matchWildcard();
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(54);
			base_physical_attack_table();
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(55);
					matchWildcard();
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(61);
			pc_collision_box_table();
			setState(65);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(62);
					matchWildcard();
					}
					} 
				}
				setState(67);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class Base_physical_attack_tableContext extends ParserRuleContext {
		public List<Base_physical_attackContext> base_physical_attack() {
			return getRuleContexts(Base_physical_attackContext.class);
		}
		public Base_physical_attackContext base_physical_attack(int i) {
			return getRuleContext(Base_physical_attackContext.class,i);
		}
		public Base_physical_attack_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_physical_attack_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterBase_physical_attack_table(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitBase_physical_attack_table(this);
		}
	}

	public final Base_physical_attack_tableContext base_physical_attack_table() throws RecognitionException {
		Base_physical_attack_tableContext _localctx = new Base_physical_attack_tableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_base_physical_attack_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__0);
			setState(70); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(69);
				base_physical_attack();
				}
				}
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FFIGHTER) | (1L << MFIGHTER) | (1L << FMAGIC) | (1L << MMAGIC) | (1L << FELF_FIGHTER) | (1L << MELF_FIGHTER) | (1L << FELF_MAGIC) | (1L << MELF_MAGIC) | (1L << FDARKELF_FIGHTER) | (1L << MDARKELF_FIGHTER) | (1L << FDARKELF_MAGIC) | (1L << MDARKELF_MAGIC) | (1L << FORC_FIGHTER) | (1L << MORC_FIGHTER) | (1L << FSHAMAN) | (1L << MSHAMAN) | (1L << FDWARF_FIGHTER) | (1L << MDWARF_FIGHTER) | (1L << FDWARF_MAGE) | (1L << MDWARF_MAGE) | (1L << FKAMAEL_SOLDIER) | (1L << MKAMAEL_SOLDIER) | (1L << FKAMAEL_MAGE) | (1L << MKAMAEL_MAGE))) != 0) );
			setState(74);
			match(T__0);
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
		public Pc_nameContext pc_name() {
			return getRuleContext(Pc_nameContext.class,0);
		}
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Base_physical_attackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_physical_attack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterBase_physical_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitBase_physical_attack(this);
		}
	}

	public final Base_physical_attackContext base_physical_attack() throws RecognitionException {
		Base_physical_attackContext _localctx = new Base_physical_attackContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_base_physical_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			pc_name();
			setState(77);
			match(T__1);
			setState(78);
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

	public static class Pc_collision_box_tableContext extends ParserRuleContext {
		public List<Collision_statContext> collision_stat() {
			return getRuleContexts(Collision_statContext.class);
		}
		public Collision_statContext collision_stat(int i) {
			return getRuleContext(Collision_statContext.class,i);
		}
		public Pc_collision_box_tableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pc_collision_box_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterPc_collision_box_table(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitPc_collision_box_table(this);
		}
	}

	public final Pc_collision_box_tableContext pc_collision_box_table() throws RecognitionException {
		Pc_collision_box_tableContext _localctx = new Pc_collision_box_tableContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pc_collision_box_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__2);
			setState(82); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(81);
				collision_stat();
				}
				}
				setState(84); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FFIGHTER) | (1L << MFIGHTER) | (1L << FMAGIC) | (1L << MMAGIC) | (1L << FELF_FIGHTER) | (1L << MELF_FIGHTER) | (1L << FELF_MAGIC) | (1L << MELF_MAGIC) | (1L << FDARKELF_FIGHTER) | (1L << MDARKELF_FIGHTER) | (1L << FDARKELF_MAGIC) | (1L << MDARKELF_MAGIC) | (1L << FORC_FIGHTER) | (1L << MORC_FIGHTER) | (1L << FSHAMAN) | (1L << MSHAMAN) | (1L << FDWARF_FIGHTER) | (1L << MDWARF_FIGHTER) | (1L << FDWARF_MAGE) | (1L << MDWARF_MAGE) | (1L << FKAMAEL_SOLDIER) | (1L << MKAMAEL_SOLDIER) | (1L << FKAMAEL_MAGE) | (1L << MKAMAEL_MAGE))) != 0) );
			setState(86);
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

	public static class Collision_statContext extends ParserRuleContext {
		public Pc_nameContext pc_name() {
			return getRuleContext(Pc_nameContext.class,0);
		}
		public Double_listContext double_list() {
			return getRuleContext(Double_listContext.class,0);
		}
		public Collision_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collision_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterCollision_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitCollision_stat(this);
		}
	}

	public final Collision_statContext collision_stat() throws RecognitionException {
		Collision_statContext _localctx = new Collision_statContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_collision_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			pc_name();
			setState(89);
			match(T__1);
			setState(90);
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

	public static class Pc_nameContext extends ParserRuleContext {
		public TerminalNode FFIGHTER() { return getToken(PCParametersParser.FFIGHTER, 0); }
		public TerminalNode MFIGHTER() { return getToken(PCParametersParser.MFIGHTER, 0); }
		public TerminalNode FMAGIC() { return getToken(PCParametersParser.FMAGIC, 0); }
		public TerminalNode MMAGIC() { return getToken(PCParametersParser.MMAGIC, 0); }
		public TerminalNode FELF_FIGHTER() { return getToken(PCParametersParser.FELF_FIGHTER, 0); }
		public TerminalNode MELF_FIGHTER() { return getToken(PCParametersParser.MELF_FIGHTER, 0); }
		public TerminalNode FELF_MAGIC() { return getToken(PCParametersParser.FELF_MAGIC, 0); }
		public TerminalNode MELF_MAGIC() { return getToken(PCParametersParser.MELF_MAGIC, 0); }
		public TerminalNode FDARKELF_FIGHTER() { return getToken(PCParametersParser.FDARKELF_FIGHTER, 0); }
		public TerminalNode MDARKELF_FIGHTER() { return getToken(PCParametersParser.MDARKELF_FIGHTER, 0); }
		public TerminalNode FDARKELF_MAGIC() { return getToken(PCParametersParser.FDARKELF_MAGIC, 0); }
		public TerminalNode MDARKELF_MAGIC() { return getToken(PCParametersParser.MDARKELF_MAGIC, 0); }
		public TerminalNode FORC_FIGHTER() { return getToken(PCParametersParser.FORC_FIGHTER, 0); }
		public TerminalNode MORC_FIGHTER() { return getToken(PCParametersParser.MORC_FIGHTER, 0); }
		public TerminalNode FSHAMAN() { return getToken(PCParametersParser.FSHAMAN, 0); }
		public TerminalNode MSHAMAN() { return getToken(PCParametersParser.MSHAMAN, 0); }
		public TerminalNode FDWARF_FIGHTER() { return getToken(PCParametersParser.FDWARF_FIGHTER, 0); }
		public TerminalNode MDWARF_FIGHTER() { return getToken(PCParametersParser.MDWARF_FIGHTER, 0); }
		public TerminalNode FDWARF_MAGE() { return getToken(PCParametersParser.FDWARF_MAGE, 0); }
		public TerminalNode MDWARF_MAGE() { return getToken(PCParametersParser.MDWARF_MAGE, 0); }
		public TerminalNode FKAMAEL_SOLDIER() { return getToken(PCParametersParser.FKAMAEL_SOLDIER, 0); }
		public TerminalNode MKAMAEL_SOLDIER() { return getToken(PCParametersParser.MKAMAEL_SOLDIER, 0); }
		public TerminalNode FKAMAEL_MAGE() { return getToken(PCParametersParser.FKAMAEL_MAGE, 0); }
		public TerminalNode MKAMAEL_MAGE() { return getToken(PCParametersParser.MKAMAEL_MAGE, 0); }
		public Pc_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pc_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterPc_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitPc_name(this);
		}
	}

	public final Pc_nameContext pc_name() throws RecognitionException {
		Pc_nameContext _localctx = new Pc_nameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_pc_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FFIGHTER) | (1L << MFIGHTER) | (1L << FMAGIC) | (1L << MMAGIC) | (1L << FELF_FIGHTER) | (1L << MELF_FIGHTER) | (1L << FELF_MAGIC) | (1L << MELF_MAGIC) | (1L << FDARKELF_FIGHTER) | (1L << MDARKELF_FIGHTER) | (1L << FDARKELF_MAGIC) | (1L << MDARKELF_MAGIC) | (1L << FORC_FIGHTER) | (1L << MORC_FIGHTER) | (1L << FSHAMAN) | (1L << MSHAMAN) | (1L << FDWARF_FIGHTER) | (1L << MDWARF_FIGHTER) | (1L << FDWARF_MAGE) | (1L << MDWARF_MAGE) | (1L << FKAMAEL_SOLDIER) | (1L << MKAMAEL_SOLDIER) | (1L << FKAMAEL_MAGE) | (1L << MKAMAEL_MAGE))) != 0)) ) {
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

	public static class Identifier_objectContext extends ParserRuleContext {
		public String value;
		public TerminalNode DAGGER() { return getToken(PCParametersParser.DAGGER, 0); }
		public TerminalNode BOW() { return getToken(PCParametersParser.BOW, 0); }
		public TerminalNode CROSSBOW() { return getToken(PCParametersParser.CROSSBOW, 0); }
		public TerminalNode RAPIER() { return getToken(PCParametersParser.RAPIER, 0); }
		public TerminalNode GLOVES() { return getToken(PCParametersParser.GLOVES, 0); }
		public TerminalNode STEEL() { return getToken(PCParametersParser.STEEL, 0); }
		public TerminalNode LEATHER() { return getToken(PCParametersParser.LEATHER, 0); }
		public TerminalNode ORIHARUKON() { return getToken(PCParametersParser.ORIHARUKON, 0); }
		public TerminalNode NAME() { return getToken(PCParametersParser.NAME, 0); }
		public TerminalNode NONE() { return getToken(PCParametersParser.NONE, 0); }
		public TerminalNode ORC() { return getToken(PCParametersParser.ORC, 0); }
		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterIdentifier_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==NONE || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (ORC - 67)) | (1L << (BOW - 67)) | (1L << (DAGGER - 67)) | (1L << (RAPIER - 67)) | (1L << (CROSSBOW - 67)) | (1L << (GLOVES - 67)) | (1L << (STEEL - 67)))) != 0) || ((((_la - 134)) & ~0x3f) == 0 && ((1L << (_la - 134)) & ((1L << (LEATHER - 134)) | (1L << (ORIHARUKON - 134)) | (1L << (NAME - 134)))) != 0)) ) {
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
		public TerminalNode BOOLEAN() { return getToken(PCParametersParser.BOOLEAN, 0); }
		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterBool_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
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
		public TerminalNode BOOLEAN() { return getToken(PCParametersParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PCParametersParser.INTEGER, 0); }
		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterByte_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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
		public TerminalNode BOOLEAN() { return getToken(PCParametersParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PCParametersParser.INTEGER, 0); }
		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterInt_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
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
		public TerminalNode BOOLEAN() { return getToken(PCParametersParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PCParametersParser.INTEGER, 0); }
		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterLong_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
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
		public TerminalNode BOOLEAN() { return getToken(PCParametersParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PCParametersParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(PCParametersParser.DOUBLE, 0); }
		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterDouble_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
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
		public TerminalNode BOOLEAN() { return getToken(PCParametersParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(PCParametersParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(PCParametersParser.DOUBLE, 0); }
		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterString_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
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
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterName_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__5);
			setState(109);
			((Name_objectContext)_localctx).io = identifier_object();
			setState(110);
			match(T__6);
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
		public TerminalNode CATEGORY() { return getToken(PCParametersParser.CATEGORY, 0); }
		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterCategory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(PCParametersParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PCParametersParser.SEMICOLON, i);
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
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterVector3D_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__7);
			setState(116);
			((Vector3D_objectContext)_localctx).x = int_object();
			setState(117);
			match(SEMICOLON);
			setState(118);
			((Vector3D_objectContext)_localctx).y = int_object();
			setState(119);
			match(SEMICOLON);
			setState(120);
			((Vector3D_objectContext)_localctx).z = int_object();
			setState(121);
			match(T__8);
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
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterEmpty_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(T__7);
			setState(124);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(PCParametersParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PCParametersParser.SEMICOLON, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_identifier_list);
		 _localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				match(T__7);
				setState(128);
				((Identifier_listContext)_localctx).io = identifier_object();
				 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(130);
					match(SEMICOLON);
					setState(131);
					((Identifier_listContext)_localctx).io = identifier_object();
					 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(139);
				match(T__8);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(PCParametersParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PCParametersParser.SEMICOLON, i);
		}
		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterInt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_int_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				match(T__7);
				setState(145);
				((Int_listContext)_localctx).io = int_object();
				 _localctx.value.add(((Int_listContext)_localctx).io.value); 
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(147);
					match(SEMICOLON);
					setState(148);
					((Int_listContext)_localctx).io = int_object();
					_localctx.value.add(((Int_listContext)_localctx).io.value);
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(156);
				match(T__8);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(PCParametersParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PCParametersParser.SEMICOLON, i);
		}
		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterDouble_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_double_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(175);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				match(T__7);
				setState(162);
				((Double_listContext)_localctx).d = double_object();
				 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(164);
					match(SEMICOLON);
					setState(165);
					((Double_listContext)_localctx).d = double_object();
					 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
					}
					}
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(173);
				match(T__8);
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
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterBase_attribute_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__9);
			setState(178);
			match(T__1);
			setState(179);
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
		public TerminalNode SEMICOLON() { return getToken(PCParametersParser.SEMICOLON, 0); }
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterAttack_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(T__7);
			setState(183);
			((Attack_attributeContext)_localctx).attribute = attribute();
			setState(184);
			match(SEMICOLON);
			setState(185);
			((Attack_attributeContext)_localctx).io = int_object();
			setState(186);
			match(T__8);
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
		public TerminalNode NONE() { return getToken(PCParametersParser.NONE, 0); }
		public TerminalNode FIRE() { return getToken(PCParametersParser.FIRE, 0); }
		public TerminalNode WATER() { return getToken(PCParametersParser.WATER, 0); }
		public TerminalNode EARTH() { return getToken(PCParametersParser.EARTH, 0); }
		public TerminalNode WIND() { return getToken(PCParametersParser.WIND, 0); }
		public TerminalNode HOLY() { return getToken(PCParametersParser.HOLY, 0); }
		public TerminalNode UNHOLY() { return getToken(PCParametersParser.UNHOLY, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_attribute);
		try {
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(NONE);
				_localctx.value = AttributeType.NONE;
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(FIRE);
				_localctx.value = AttributeType.FIRE;
				}
				break;
			case WATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(193);
				match(WATER);
				_localctx.value = AttributeType.WATER;
				}
				break;
			case EARTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(195);
				match(EARTH);
				_localctx.value = AttributeType.EARTH;
				}
				break;
			case WIND:
				enterOuterAlt(_localctx, 5);
				{
				setState(197);
				match(WIND);
				_localctx.value = AttributeType.WIND;
				}
				break;
			case HOLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(199);
				match(HOLY);
				_localctx.value = AttributeType.HOLY;
				}
				break;
			case UNHOLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(201);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(PCParametersParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PCParametersParser.SEMICOLON, i);
		}
		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).enterCategory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCParametersListener ) ((PCParametersListener)listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_category_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				match(T__7);
				setState(207);
				((Category_listContext)_localctx).co = category_object();
				 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(209);
					match(SEMICOLON);
					setState(210);
					((Category_listContext)_localctx).co = category_object();
					 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(218);
				match(T__8);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00a2\u00e1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\7\2\64\n\2\f\2\16\2\67\13\2\3\2\3\2\7\2;\n\2\f\2\16\2>\13\2\3\2\3"+
		"\2\7\2B\n\2\f\2\16\2E\13\2\3\3\3\3\6\3I\n\3\r\3\16\3J\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\6\5U\n\5\r\5\16\5V\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u0089\n\23\f\23"+
		"\16\23\u008c\13\23\3\23\3\23\5\23\u0090\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\7\24\u009a\n\24\f\24\16\24\u009d\13\24\3\24\3\24\5\24"+
		"\u00a1\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00ab\n\25\f"+
		"\25\16\25\u00ae\13\25\3\25\3\25\5\25\u00b2\n\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u00ce\n\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\7\31\u00d8\n\31\f\31\16\31\u00db\13\31\3\31"+
		"\3\31\5\31\u00df\n\31\3\31\5\65<C\2\32\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\2\6\3\2\r$\16\2\7\7\60\60EEZZ\\\\aaccss\u0084\u0084"+
		"\u0088\u0088\u008b\u008b\u009f\u009f\3\2&\'\3\2&(\2\u00db\2\65\3\2\2\2"+
		"\4F\3\2\2\2\6N\3\2\2\2\bR\3\2\2\2\nZ\3\2\2\2\f^\3\2\2\2\16`\3\2\2\2\20"+
		"b\3\2\2\2\22d\3\2\2\2\24f\3\2\2\2\26h\3\2\2\2\30j\3\2\2\2\32l\3\2\2\2"+
		"\34n\3\2\2\2\36s\3\2\2\2 u\3\2\2\2\"}\3\2\2\2$\u008f\3\2\2\2&\u00a0\3"+
		"\2\2\2(\u00b1\3\2\2\2*\u00b3\3\2\2\2,\u00b8\3\2\2\2.\u00cd\3\2\2\2\60"+
		"\u00de\3\2\2\2\62\64\13\2\2\2\63\62\3\2\2\2\64\67\3\2\2\2\65\66\3\2\2"+
		"\2\65\63\3\2\2\2\668\3\2\2\2\67\65\3\2\2\28<\5\4\3\29;\13\2\2\2:9\3\2"+
		"\2\2;>\3\2\2\2<=\3\2\2\2<:\3\2\2\2=?\3\2\2\2><\3\2\2\2?C\5\b\5\2@B\13"+
		"\2\2\2A@\3\2\2\2BE\3\2\2\2CD\3\2\2\2CA\3\2\2\2D\3\3\2\2\2EC\3\2\2\2FH"+
		"\7\3\2\2GI\5\6\4\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2L"+
		"M\7\3\2\2M\5\3\2\2\2NO\5\f\7\2OP\7\4\2\2PQ\5\24\13\2Q\7\3\2\2\2RT\7\5"+
		"\2\2SU\5\n\6\2TS\3\2\2\2UV\3\2\2\2VT\3\2\2\2VW\3\2\2\2WX\3\2\2\2XY\7\6"+
		"\2\2Y\t\3\2\2\2Z[\5\f\7\2[\\\7\4\2\2\\]\5(\25\2]\13\3\2\2\2^_\t\2\2\2"+
		"_\r\3\2\2\2`a\t\3\2\2a\17\3\2\2\2bc\7&\2\2c\21\3\2\2\2de\t\4\2\2e\23\3"+
		"\2\2\2fg\t\4\2\2g\25\3\2\2\2hi\t\4\2\2i\27\3\2\2\2jk\t\5\2\2k\31\3\2\2"+
		"\2lm\t\5\2\2m\33\3\2\2\2no\7\b\2\2op\5\16\b\2pq\7\t\2\2qr\b\17\1\2r\35"+
		"\3\2\2\2st\7%\2\2t\37\3\2\2\2uv\7\n\2\2vw\5\24\13\2wx\7/\2\2xy\5\24\13"+
		"\2yz\7/\2\2z{\5\24\13\2{|\7\13\2\2|!\3\2\2\2}~\7\n\2\2~\177\7\13\2\2\177"+
		"#\3\2\2\2\u0080\u0090\5\"\22\2\u0081\u0082\7\n\2\2\u0082\u0083\5\16\b"+
		"\2\u0083\u008a\b\23\1\2\u0084\u0085\7/\2\2\u0085\u0086\5\16\b\2\u0086"+
		"\u0087\b\23\1\2\u0087\u0089\3\2\2\2\u0088\u0084\3\2\2\2\u0089\u008c\3"+
		"\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008d\u008e\7\13\2\2\u008e\u0090\3\2\2\2\u008f\u0080\3"+
		"\2\2\2\u008f\u0081\3\2\2\2\u0090%\3\2\2\2\u0091\u00a1\5\"\22\2\u0092\u0093"+
		"\7\n\2\2\u0093\u0094\5\24\13\2\u0094\u009b\b\24\1\2\u0095\u0096\7/\2\2"+
		"\u0096\u0097\5\24\13\2\u0097\u0098\b\24\1\2\u0098\u009a\3\2\2\2\u0099"+
		"\u0095\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e\u009f\7\13\2\2\u009f"+
		"\u00a1\3\2\2\2\u00a0\u0091\3\2\2\2\u00a0\u0092\3\2\2\2\u00a1\'\3\2\2\2"+
		"\u00a2\u00b2\5\"\22\2\u00a3\u00a4\7\n\2\2\u00a4\u00a5\5\30\r\2\u00a5\u00ac"+
		"\b\25\1\2\u00a6\u00a7\7/\2\2\u00a7\u00a8\5\30\r\2\u00a8\u00a9\b\25\1\2"+
		"\u00a9\u00ab\3\2\2\2\u00aa\u00a6\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af"+
		"\u00b0\7\13\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00a2\3\2\2\2\u00b1\u00a3\3"+
		"\2\2\2\u00b2)\3\2\2\2\u00b3\u00b4\7\f\2\2\u00b4\u00b5\7\4\2\2\u00b5\u00b6"+
		"\5,\27\2\u00b6\u00b7\b\26\1\2\u00b7+\3\2\2\2\u00b8\u00b9\7\n\2\2\u00b9"+
		"\u00ba\5.\30\2\u00ba\u00bb\7/\2\2\u00bb\u00bc\5\24\13\2\u00bc\u00bd\7"+
		"\13\2\2\u00bd\u00be\b\27\1\2\u00be-\3\2\2\2\u00bf\u00c0\7\60\2\2\u00c0"+
		"\u00ce\b\30\1\2\u00c1\u00c2\7)\2\2\u00c2\u00ce\b\30\1\2\u00c3\u00c4\7"+
		"*\2\2\u00c4\u00ce\b\30\1\2\u00c5\u00c6\7+\2\2\u00c6\u00ce\b\30\1\2\u00c7"+
		"\u00c8\7,\2\2\u00c8\u00ce\b\30\1\2\u00c9\u00ca\7.\2\2\u00ca\u00ce\b\30"+
		"\1\2\u00cb\u00cc\7-\2\2\u00cc\u00ce\b\30\1\2\u00cd\u00bf\3\2\2\2\u00cd"+
		"\u00c1\3\2\2\2\u00cd\u00c3\3\2\2\2\u00cd\u00c5\3\2\2\2\u00cd\u00c7\3\2"+
		"\2\2\u00cd\u00c9\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce/\3\2\2\2\u00cf\u00df"+
		"\5\"\22\2\u00d0\u00d1\7\n\2\2\u00d1\u00d2\5\36\20\2\u00d2\u00d9\b\31\1"+
		"\2\u00d3\u00d4\7/\2\2\u00d4\u00d5\5\36\20\2\u00d5\u00d6\b\31\1\2\u00d6"+
		"\u00d8\3\2\2\2\u00d7\u00d3\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2"+
		"\2\2\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc"+
		"\u00dd\7\13\2\2\u00dd\u00df\3\2\2\2\u00de\u00cf\3\2\2\2\u00de\u00d0\3"+
		"\2\2\2\u00df\61\3\2\2\2\20\65<CJV\u008a\u008f\u009b\u00a0\u00ac\u00b1"+
		"\u00cd\u00d9\u00de";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}