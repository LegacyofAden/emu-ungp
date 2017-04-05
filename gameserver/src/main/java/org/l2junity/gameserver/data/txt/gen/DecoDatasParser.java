// Generated from org\l2junity\gameserver\data\txt\gen\DecoDatas.g4 by ANTLR 4.7
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
import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.decodata.DecoCostData;
import org.l2junity.gameserver.data.txt.model.decodata.DecoFunctionType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecoDatasParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, NONE=33, HP_REGEN=34, MP_REGEN=35, CP_REGEN=36, XP_RESTORE=37, 
		TELEPORT=38, BROADCAST=39, CURTAIN=40, HANGING=41, OUTERPLATFORM=42, PLATFORM=43, 
		ITEM_CREATE=44, BUFF=45, CATEGORY=46, BOOLEAN=47, INTEGER=48, DOUBLE=49, 
		FIRE=50, WATER=51, EARTH=52, WIND=53, UNHOLY=54, HOLY=55, SEMICOLON=56, 
		FAIRY=57, ANIMAL=58, HUMANOID=59, PLANT=60, UNDEAD=61, CONSTRUCT=62, BEAST=63, 
		BUG=64, ELEMENTAL=65, DEMONIC=66, GIANT=67, DRAGON=68, DIVINE=69, SUMMON=70, 
		PET=71, HOLYTHING=72, DWARF=73, MERCHANT=74, ELF=75, KAMAEL=76, ORC=77, 
		MERCENARY=78, CASTLE_GUARD=79, HUMAN=80, BOSS=81, ZZOLDAGU=82, WORLD_TRAP=83, 
		MONRACE=84, DARKELF=85, GUARD=86, TELEPORTER=87, WAREHOUSE_KEEPER=88, 
		WARRIOR=89, CITIZEN=90, TREASURE=91, FIELDBOSS=92, BLACKSMITH=93, GUILD_MASTER=94, 
		GUILD_COACH=95, SWORD=96, BLUNT=97, BOW=98, POLE=99, DAGGER=100, DUAL=101, 
		FIST=102, DUALFIST=103, FISHINGROD=104, RAPIER=105, ANCIENTSWORD=106, 
		CROSSBOW=107, FLAG=108, OWNTHING=109, DUALDAGGER=110, ETC=111, LIGHT=112, 
		HEAVY=113, MAGIC=114, SIGIL=115, RHAND=116, LRHAND=117, LHAND=118, CHEST=119, 
		LEGS=120, FEET=121, HEAD=122, GLOVES=123, ONEPIECE=124, REAR=125, LEAR=126, 
		RFINGER=127, LFINGER=128, NECK=129, BACK=130, UNDERWEAR=131, HAIR=132, 
		HAIR2=133, HAIRALL=134, ALLDRESS=135, RBRACELET=136, LBRACELET=137, WAIST=138, 
		DECO1=139, STEEL=140, FINE_STEEL=141, WOOD=142, CLOTH=143, LEATHER=144, 
		BONE=145, BRONZE=146, ORIHARUKON=147, MITHRIL=148, DAMASCUS=149, ADAMANTAITE=150, 
		BLOOD_STEEL=151, PAPER=152, GOLD=153, LIQUID=154, FISH=155, SILVER=156, 
		CHRYSOLITE=157, CRYSTAL=158, HORN=159, SCALE_OF_DRAGON=160, COTTON=161, 
		DYESTUFF=162, COBWEB=163, RUNE_XP=164, RUNE_SP=165, RUNE_REMOVE_PENALTY=166, 
		NAME=167, WS=168, LINE_COMMENT=169, STAR_COMMENT=170;
	public static final int
		RULE_file = 0, RULE_decoData = 1, RULE_id = 2, RULE_name = 3, RULE_type = 4,
			RULE_level = 5, RULE_depth_ = 6, RULE_funcValue = 7, RULE_cost = 8, RULE_deco_type = 9,
		RULE_cost_object = 10, RULE_identifier_object = 11, RULE_bool_object = 12, 
		RULE_byte_object = 13, RULE_int_object = 14, RULE_long_object = 15, RULE_double_object = 16, 
		RULE_string_object = 17, RULE_name_object = 18, RULE_category_object = 19, 
		RULE_vector3D_object = 20, RULE_empty_list = 21, RULE_identifier_list = 22, 
		RULE_int_list = 23, RULE_double_list = 24, RULE_base_attribute_attack = 25, 
		RULE_attack_attribute = 26, RULE_attribute = 27, RULE_category_list = 28;
	public static final String[] ruleNames = {
		"file", "decoData", "id", "name", "type", "level", "depth_", "funcValue",
			"cost", "deco_type", "cost_object", "identifier_object", "bool_object",
		"byte_object", "int_object", "long_object", "double_object", "string_object", 
		"name_object", "category_object", "vector3D_object", "empty_list", "identifier_list", 
		"int_list", "double_list", "base_attribute_attack", "attack_attribute", 
		"attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'deco_begin'", "'deco_end'", "'id'", "'='", "'name'", "'type'", 
		"'level'", "'depth'", "'func'", "'('", "')'", "'cost'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "'10'", "'11'", 
		"'12'", "'{'", "':'", "'}'", "'slot_lhand'", "'['", "']'", "'base_attribute_attack'",
			"'none'", "'hp_regen'", "'mp_regen'", "'cp_regen'", "'exp_restore'", "'teleport'",
		"'broadcast'", "'curtain'", "'hanging'", "'outerplatform'", "'platform'", 
		"'item_create'", "'buff'", null, null, null, null, "'fire'", "'water'", 
		"'earth'", "'wind'", "'unholy'", "'holy'", "';'", "'fairy'", "'animal'", 
		"'humanoid'", "'plant'", "'undead'", "'construct'", "'beast'", "'bug'", 
		"'elemental'", "'demonic'", "'giant'", "'dragon'", "'divine'", "'summon'", 
		"'pet'", "'holything'", "'dwarf'", "'merchant'", "'elf'", "'kamael'", 
		"'orc'", "'mercenary'", "'castle_guard'", "'human'", "'boss'", "'zzoldagu'", 
		"'world_trap'", "'monrace'", "'darkelf'", "'guard'", "'teleporter'", "'warehouse_keeper'", 
		"'warrior'", "'citizen'", "'treasure'", "'fieldboss'", "'blacksmith'", 
		"'guild_master'", "'guild_coach'", "'sword'", "'blunt'", "'bow'", "'pole'", 
		"'dagger'", "'dual'", "'fist'", "'dualfist'", "'fishingrod'", "'rapier'", 
		"'ancientsword'", "'crossbow'", "'flag'", "'ownthing'", "'dualdagger'", 
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
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "NONE", "HP_REGEN", 
		"MP_REGEN", "CP_REGEN", "XP_RESTORE", "TELEPORT", "BROADCAST", "CURTAIN", 
		"HANGING", "OUTERPLATFORM", "PLATFORM", "ITEM_CREATE", "BUFF", "CATEGORY", 
		"BOOLEAN", "INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", 
		"HOLY", "SEMICOLON", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", 
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
	public String getGrammarFileName() { return "DecoDatas.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DecoDatasParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public List<DecoDataContext> decoData() {
			return getRuleContexts(DecoDataContext.class);
		}
		public DecoDataContext decoData(int i) {
			return getRuleContext(DecoDataContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58);
				decoData();
				}
				}
				setState(61); 
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

	public static class DecoDataContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LevelContext level() {
			return getRuleContext(LevelContext.class,0);
		}
		public Depth_Context depth_() {
			return getRuleContext(Depth_Context.class,0);
		}
		public FuncValueContext funcValue() {
			return getRuleContext(FuncValueContext.class,0);
		}
		public CostContext cost() {
			return getRuleContext(CostContext.class,0);
		}
		public DecoDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decoData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterDecoData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitDecoData(this);
		}
	}

	public final DecoDataContext decoData() throws RecognitionException {
		DecoDataContext _localctx = new DecoDataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decoData);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__0);
			setState(64);
			id();
			setState(65);
			name();
			setState(66);
			type();
			setState(67);
			level();
			setState(68);
			depth_();
			setState(69);
			funcValue();
			setState(70);
			cost();
			setState(71);
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

	public static class IdContext extends ParserRuleContext {
		public int value;
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__2);
			setState(74);
			match(T__3);
			setState(75);
			((IdContext)_localctx).io = int_object();
			_localctx.value = ((IdContext)_localctx).io.value;
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

	public static class NameContext extends ParserRuleContext {
		public String value;
		public Name_objectContext no;
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(T__4);
			setState(79);
			match(T__3);
			setState(80);
			((NameContext)_localctx).no = name_object();
			_localctx.value = ((NameContext)_localctx).no.value;
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

	public static class TypeContext extends ParserRuleContext {
		public DecoFunctionType value;
		public Deco_typeContext dt;

		public Deco_typeContext deco_type() {
			return getRuleContext(Deco_typeContext.class, 0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__5);
			setState(84);
			match(T__3);
			setState(85);
				((TypeContext) _localctx).dt = deco_type();
			_localctx.value = ((TypeContext)_localctx).dt.value;
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
		public int value;
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
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterLevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitLevel(this);
		}
	}

	public final LevelContext level() throws RecognitionException {
		LevelContext _localctx = new LevelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_level);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__6);
			setState(89);
			match(T__3);
			setState(90);
			((LevelContext)_localctx).io = int_object();
			_localctx.value = ((LevelContext)_localctx).io.value;
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

	public static class Depth_Context extends ParserRuleContext {
		public int value;
		public Int_objectContext io;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Depth_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_depth_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterDepth_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitDepth_(this);
		}
	}

	public final Depth_Context depth_() throws RecognitionException {
		Depth_Context _localctx = new Depth_Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_depth_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(T__7);
			setState(94);
			match(T__3);
			setState(95);
			((Depth_Context)_localctx).io = int_object();
			_localctx.value = ((Depth_Context)_localctx).io.value;
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

	public static class FuncValueContext extends ParserRuleContext {
		public double value;
		public Double_objectContext d;
		public TerminalNode NONE() { return getToken(DecoDatasParser.NONE, 0); }
		public TerminalNode HP_REGEN() { return getToken(DecoDatasParser.HP_REGEN, 0); }
		public TerminalNode MP_REGEN() { return getToken(DecoDatasParser.MP_REGEN, 0); }
		public TerminalNode XP_RESTORE() { return getToken(DecoDatasParser.XP_RESTORE, 0); }
		public Double_objectContext double_object() {
			return getRuleContext(Double_objectContext.class,0);
		}
		public FuncValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterFuncValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitFuncValue(this);
		}
	}

	public final FuncValueContext funcValue() throws RecognitionException {
		FuncValueContext _localctx = new FuncValueContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__8);
			setState(99);
			match(T__3);
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				{
				setState(100);
				match(NONE);
				}
				break;
			case HP_REGEN:
			case MP_REGEN:
			case XP_RESTORE:
				{
				setState(101);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HP_REGEN) | (1L << MP_REGEN) | (1L << XP_RESTORE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(102);
				match(T__9);
				setState(103);
				((FuncValueContext)_localctx).d = double_object();
				_localctx.value=((FuncValueContext)_localctx).d.value;
				setState(105);
				match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class CostContext extends ParserRuleContext {
		public DecoCostData value;
		public Cost_objectContext co;
		public Cost_objectContext cost_object() {
			return getRuleContext(Cost_objectContext.class,0);
		}
		public CostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cost; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterCost(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitCost(this);
		}
	}

	public final CostContext cost() throws RecognitionException {
		CostContext _localctx = new CostContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cost);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__11);
			setState(110);
			match(T__3);
			setState(111);
			((CostContext)_localctx).co = cost_object();
			_localctx.value = ((CostContext)_localctx).co.value;
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

	public static class Deco_typeContext extends ParserRuleContext {
		public DecoFunctionType value;

		public Deco_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_deco_type;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterDeco_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitDeco_type(this);
		}
	}

	public final Deco_typeContext deco_type() throws RecognitionException {
		Deco_typeContext _localctx = new Deco_typeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_deco_type);
		try {
			setState(140);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(T__12);
				_localctx.value = DecoFunctionType.NONE;
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(116);
				match(T__13);
				_localctx.value = DecoFunctionType.HP_REGEN;
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				match(T__14);
				_localctx.value = DecoFunctionType.MP_REGEN;
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				match(T__15);
				_localctx.value = DecoFunctionType.CP_REGEN;
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				match(T__16);
				_localctx.value = DecoFunctionType.XP_RESTORE;
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 6);
				{
				setState(124);
				match(T__17);
				_localctx.value = DecoFunctionType.TELEPORT;
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 7);
				{
				setState(126);
				match(T__18);
				_localctx.value = DecoFunctionType.BROADCAST;
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 8);
				{
				setState(128);
				match(T__19);
				_localctx.value = DecoFunctionType.CURTAIN;
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 9);
				{
				setState(130);
				match(T__20);
				_localctx.value = DecoFunctionType.HANGING;
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 10);
				{
				setState(132);
				match(T__21);
				_localctx.value = DecoFunctionType.OUTERPLATFORM;
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 11);
				{
				setState(134);
				match(T__22);
				_localctx.value = DecoFunctionType.PLATFORM;
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 12);
				{
				setState(136);
				match(T__23);
				_localctx.value = DecoFunctionType.ITEM_CREATE;
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 13);
				{
				setState(138);
				match(T__24);
				_localctx.value = DecoFunctionType.BUFF;
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

	public static class Cost_objectContext extends ParserRuleContext {
		public DecoCostData value;
		public Int_objectContext days;
		public Int_objectContext adena;
		public List<Int_objectContext> int_object() {
			return getRuleContexts(Int_objectContext.class);
		}
		public Int_objectContext int_object(int i) {
			return getRuleContext(Int_objectContext.class,i);
		}
		public Cost_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cost_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterCost_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitCost_object(this);
		}
	}

	public final Cost_objectContext cost_object() throws RecognitionException {
		Cost_objectContext _localctx = new Cost_objectContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cost_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__25);
			setState(143);
			((Cost_objectContext)_localctx).days = int_object();
			setState(144);
			match(T__26);
			setState(145);
			((Cost_objectContext)_localctx).adena = int_object();
			setState(146);
			match(T__27);
			}
			_ctx.stop = _input.LT(-1);
			_localctx.value = new DecoCostData(((Cost_objectContext)_localctx).days.value, ((Cost_objectContext)_localctx).adena.value);
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
		public TerminalNode DAGGER() { return getToken(DecoDatasParser.DAGGER, 0); }
		public TerminalNode BOW() { return getToken(DecoDatasParser.BOW, 0); }
		public TerminalNode CROSSBOW() { return getToken(DecoDatasParser.CROSSBOW, 0); }
		public TerminalNode RAPIER() { return getToken(DecoDatasParser.RAPIER, 0); }
		public TerminalNode GLOVES() { return getToken(DecoDatasParser.GLOVES, 0); }
		public TerminalNode STEEL() { return getToken(DecoDatasParser.STEEL, 0); }
		public TerminalNode LEATHER() { return getToken(DecoDatasParser.LEATHER, 0); }
		public TerminalNode ORIHARUKON() { return getToken(DecoDatasParser.ORIHARUKON, 0); }
		public TerminalNode NAME() { return getToken(DecoDatasParser.NAME, 0); }
		public TerminalNode NONE() { return getToken(DecoDatasParser.NONE, 0); }
		public TerminalNode ORC() { return getToken(DecoDatasParser.ORC, 0); }
		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterIdentifier_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_la = _input.LA(1);
			if ( !(_la==T__28 || _la==NONE || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (ORC - 77)) | (1L << (BOW - 77)) | (1L << (DAGGER - 77)) | (1L << (RAPIER - 77)) | (1L << (CROSSBOW - 77)) | (1L << (GLOVES - 77)) | (1L << (STEEL - 77)))) != 0) || ((((_la - 144)) & ~0x3f) == 0 && ((1L << (_la - 144)) & ((1L << (LEATHER - 144)) | (1L << (ORIHARUKON - 144)) | (1L << (NAME - 144)))) != 0)) ) {
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
		public TerminalNode BOOLEAN() { return getToken(DecoDatasParser.BOOLEAN, 0); }
		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterBool_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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
		public TerminalNode BOOLEAN() { return getToken(DecoDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(DecoDatasParser.INTEGER, 0); }
		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterByte_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
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
		public TerminalNode BOOLEAN() { return getToken(DecoDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(DecoDatasParser.INTEGER, 0); }
		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterInt_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
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
		public TerminalNode BOOLEAN() { return getToken(DecoDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(DecoDatasParser.INTEGER, 0); }
		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterLong_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
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
		public TerminalNode BOOLEAN() { return getToken(DecoDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(DecoDatasParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(DecoDatasParser.DOUBLE, 0); }
		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterDouble_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
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
		public TerminalNode BOOLEAN() { return getToken(DecoDatasParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(DecoDatasParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(DecoDatasParser.DOUBLE, 0); }
		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterString_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
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
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterName_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__29);
			setState(163);
			((Name_objectContext)_localctx).io = identifier_object();
			setState(164);
			match(T__30);
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
		public TerminalNode CATEGORY() { return getToken(DecoDatasParser.CATEGORY, 0); }
		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterCategory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(DecoDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
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
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterVector3D_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__25);
			setState(170);
			((Vector3D_objectContext)_localctx).x = int_object();
			setState(171);
			match(SEMICOLON);
			setState(172);
			((Vector3D_objectContext)_localctx).y = int_object();
			setState(173);
			match(SEMICOLON);
			setState(174);
			((Vector3D_objectContext)_localctx).z = int_object();
			setState(175);
			match(T__27);
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
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterEmpty_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			match(T__25);
			setState(178);
			match(T__27);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(DecoDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_identifier_list);
		 _localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(T__25);
				setState(182);
				((Identifier_listContext)_localctx).io = identifier_object();
				 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(184);
					match(SEMICOLON);
					setState(185);
					((Identifier_listContext)_localctx).io = identifier_object();
					 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
					}
					}
					setState(192);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(193);
				match(T__27);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(DecoDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
		}
		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterInt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_int_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(212);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				match(T__25);
				setState(199);
				((Int_listContext)_localctx).io = int_object();
				 _localctx.value.add(((Int_listContext)_localctx).io.value); 
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(201);
					match(SEMICOLON);
					setState(202);
					((Int_listContext)_localctx).io = int_object();
					_localctx.value.add(((Int_listContext)_localctx).io.value);
					}
					}
					setState(209);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(210);
				match(T__27);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(DecoDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
		}
		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterDouble_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_double_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				match(T__25);
				setState(216);
				((Double_listContext)_localctx).d = double_object();
				 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(218);
					match(SEMICOLON);
					setState(219);
					((Double_listContext)_localctx).d = double_object();
					 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
					}
					}
					setState(226);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(227);
				match(T__27);
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
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterBase_attribute_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(T__31);
			setState(232);
			match(T__3);
			setState(233);
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
		public TerminalNode SEMICOLON() { return getToken(DecoDatasParser.SEMICOLON, 0); }
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterAttack_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(236);
			match(T__25);
			setState(237);
			((Attack_attributeContext)_localctx).attribute = attribute();
			setState(238);
			match(SEMICOLON);
			setState(239);
			((Attack_attributeContext)_localctx).io = int_object();
			setState(240);
			match(T__27);
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
		public TerminalNode NONE() { return getToken(DecoDatasParser.NONE, 0); }
		public TerminalNode FIRE() { return getToken(DecoDatasParser.FIRE, 0); }
		public TerminalNode WATER() { return getToken(DecoDatasParser.WATER, 0); }
		public TerminalNode EARTH() { return getToken(DecoDatasParser.EARTH, 0); }
		public TerminalNode WIND() { return getToken(DecoDatasParser.WIND, 0); }
		public TerminalNode HOLY() { return getToken(DecoDatasParser.HOLY, 0); }
		public TerminalNode UNHOLY() { return getToken(DecoDatasParser.UNHOLY, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_attribute);
		try {
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				match(NONE);
				_localctx.value = AttributeType.NONE;
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(FIRE);
				_localctx.value = AttributeType.FIRE;
				}
				break;
			case WATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(247);
				match(WATER);
				_localctx.value = AttributeType.WATER;
				}
				break;
			case EARTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(249);
				match(EARTH);
				_localctx.value = AttributeType.EARTH;
				}
				break;
			case WIND:
				enterOuterAlt(_localctx, 5);
				{
				setState(251);
				match(WIND);
				_localctx.value = AttributeType.WIND;
				}
				break;
			case HOLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(253);
				match(HOLY);
				_localctx.value = AttributeType.HOLY;
				}
				break;
			case UNHOLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(255);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(DecoDatasParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
		}
		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).enterCategory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DecoDatasListener ) ((DecoDatasListener)listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_category_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(274);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				match(T__25);
				setState(261);
				((Category_listContext)_localctx).co = category_object();
				 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(263);
					match(SEMICOLON);
					setState(264);
					((Category_listContext)_localctx).co = category_object();
					 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
					}
					}
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(272);
				match(T__27);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00ac\u0117\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\6\2>\n\2\r\2\16"+
		"\2?\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tn\n\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008f"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\7\30\u00bf\n\30\f\30\16\30\u00c2\13\30\3\30\3\30\5"+
		"\30\u00c6\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u00d0\n\31"+
		"\f\31\16\31\u00d3\13\31\3\31\3\31\5\31\u00d7\n\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\7\32\u00e1\n\32\f\32\16\32\u00e4\13\32\3\32\3\32"+
		"\5\32\u00e8\n\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\5\35\u0104\n\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u010e"+
		"\n\36\f\36\16\36\u0111\13\36\3\36\3\36\5\36\u0115\n\36\3\36\2\2\37\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\6\4\2$%\'"+
		"\'\16\2\37\37##OOddffkkmm}}\u008e\u008e\u0092\u0092\u0095\u0095\u00a9"+
		"\u00a9\3\2\61\62\3\2\61\63\2\u0115\2=\3\2\2\2\4A\3\2\2\2\6K\3\2\2\2\b"+
		"P\3\2\2\2\nU\3\2\2\2\fZ\3\2\2\2\16_\3\2\2\2\20d\3\2\2\2\22o\3\2\2\2\24"+
		"\u008e\3\2\2\2\26\u0090\3\2\2\2\30\u0096\3\2\2\2\32\u0098\3\2\2\2\34\u009a"+
		"\3\2\2\2\36\u009c\3\2\2\2 \u009e\3\2\2\2\"\u00a0\3\2\2\2$\u00a2\3\2\2"+
		"\2&\u00a4\3\2\2\2(\u00a9\3\2\2\2*\u00ab\3\2\2\2,\u00b3\3\2\2\2.\u00c5"+
		"\3\2\2\2\60\u00d6\3\2\2\2\62\u00e7\3\2\2\2\64\u00e9\3\2\2\2\66\u00ee\3"+
		"\2\2\28\u0103\3\2\2\2:\u0114\3\2\2\2<>\5\4\3\2=<\3\2\2\2>?\3\2\2\2?=\3"+
		"\2\2\2?@\3\2\2\2@\3\3\2\2\2AB\7\3\2\2BC\5\6\4\2CD\5\b\5\2DE\5\n\6\2EF"+
		"\5\f\7\2FG\5\16\b\2GH\5\20\t\2HI\5\22\n\2IJ\7\4\2\2J\5\3\2\2\2KL\7\5\2"+
		"\2LM\7\6\2\2MN\5\36\20\2NO\b\4\1\2O\7\3\2\2\2PQ\7\7\2\2QR\7\6\2\2RS\5"+
		"&\24\2ST\b\5\1\2T\t\3\2\2\2UV\7\b\2\2VW\7\6\2\2WX\5\24\13\2XY\b\6\1\2"+
		"Y\13\3\2\2\2Z[\7\t\2\2[\\\7\6\2\2\\]\5\36\20\2]^\b\7\1\2^\r\3\2\2\2_`"+
		"\7\n\2\2`a\7\6\2\2ab\5\36\20\2bc\b\b\1\2c\17\3\2\2\2de\7\13\2\2em\7\6"+
		"\2\2fn\7#\2\2gh\t\2\2\2hi\7\f\2\2ij\5\"\22\2jk\b\t\1\2kl\7\r\2\2ln\3\2"+
		"\2\2mf\3\2\2\2mg\3\2\2\2n\21\3\2\2\2op\7\16\2\2pq\7\6\2\2qr\5\26\f\2r"+
		"s\b\n\1\2s\23\3\2\2\2tu\7\17\2\2u\u008f\b\13\1\2vw\7\20\2\2w\u008f\b\13"+
		"\1\2xy\7\21\2\2y\u008f\b\13\1\2z{\7\22\2\2{\u008f\b\13\1\2|}\7\23\2\2"+
		"}\u008f\b\13\1\2~\177\7\24\2\2\177\u008f\b\13\1\2\u0080\u0081\7\25\2\2"+
		"\u0081\u008f\b\13\1\2\u0082\u0083\7\26\2\2\u0083\u008f\b\13\1\2\u0084"+
		"\u0085\7\27\2\2\u0085\u008f\b\13\1\2\u0086\u0087\7\30\2\2\u0087\u008f"+
		"\b\13\1\2\u0088\u0089\7\31\2\2\u0089\u008f\b\13\1\2\u008a\u008b\7\32\2"+
		"\2\u008b\u008f\b\13\1\2\u008c\u008d\7\33\2\2\u008d\u008f\b\13\1\2\u008e"+
		"t\3\2\2\2\u008ev\3\2\2\2\u008ex\3\2\2\2\u008ez\3\2\2\2\u008e|\3\2\2\2"+
		"\u008e~\3\2\2\2\u008e\u0080\3\2\2\2\u008e\u0082\3\2\2\2\u008e\u0084\3"+
		"\2\2\2\u008e\u0086\3\2\2\2\u008e\u0088\3\2\2\2\u008e\u008a\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\25\3\2\2\2\u0090\u0091\7\34\2\2\u0091\u0092\5\36"+
		"\20\2\u0092\u0093\7\35\2\2\u0093\u0094\5\36\20\2\u0094\u0095\7\36\2\2"+
		"\u0095\27\3\2\2\2\u0096\u0097\t\3\2\2\u0097\31\3\2\2\2\u0098\u0099\7\61"+
		"\2\2\u0099\33\3\2\2\2\u009a\u009b\t\4\2\2\u009b\35\3\2\2\2\u009c\u009d"+
		"\t\4\2\2\u009d\37\3\2\2\2\u009e\u009f\t\4\2\2\u009f!\3\2\2\2\u00a0\u00a1"+
		"\t\5\2\2\u00a1#\3\2\2\2\u00a2\u00a3\t\5\2\2\u00a3%\3\2\2\2\u00a4\u00a5"+
		"\7 \2\2\u00a5\u00a6\5\30\r\2\u00a6\u00a7\7!\2\2\u00a7\u00a8\b\24\1\2\u00a8"+
		"\'\3\2\2\2\u00a9\u00aa\7\60\2\2\u00aa)\3\2\2\2\u00ab\u00ac\7\34\2\2\u00ac"+
		"\u00ad\5\36\20\2\u00ad\u00ae\7:\2\2\u00ae\u00af\5\36\20\2\u00af\u00b0"+
		"\7:\2\2\u00b0\u00b1\5\36\20\2\u00b1\u00b2\7\36\2\2\u00b2+\3\2\2\2\u00b3"+
		"\u00b4\7\34\2\2\u00b4\u00b5\7\36\2\2\u00b5-\3\2\2\2\u00b6\u00c6\5,\27"+
		"\2\u00b7\u00b8\7\34\2\2\u00b8\u00b9\5\30\r\2\u00b9\u00c0\b\30\1\2\u00ba"+
		"\u00bb\7:\2\2\u00bb\u00bc\5\30\r\2\u00bc\u00bd\b\30\1\2\u00bd\u00bf\3"+
		"\2\2\2\u00be\u00ba\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\7\36"+
		"\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00b6\3\2\2\2\u00c5\u00b7\3\2\2\2\u00c6"+
		"/\3\2\2\2\u00c7\u00d7\5,\27\2\u00c8\u00c9\7\34\2\2\u00c9\u00ca\5\36\20"+
		"\2\u00ca\u00d1\b\31\1\2\u00cb\u00cc\7:\2\2\u00cc\u00cd\5\36\20\2\u00cd"+
		"\u00ce\b\31\1\2\u00ce\u00d0\3\2\2\2\u00cf\u00cb\3\2\2\2\u00d0\u00d3\3"+
		"\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d4\u00d5\7\36\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00c7\3"+
		"\2\2\2\u00d6\u00c8\3\2\2\2\u00d7\61\3\2\2\2\u00d8\u00e8\5,\27\2\u00d9"+
		"\u00da\7\34\2\2\u00da\u00db\5\"\22\2\u00db\u00e2\b\32\1\2\u00dc\u00dd"+
		"\7:\2\2\u00dd\u00de\5\"\22\2\u00de\u00df\b\32\1\2\u00df\u00e1\3\2\2\2"+
		"\u00e0\u00dc\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3"+
		"\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\7\36\2\2"+
		"\u00e6\u00e8\3\2\2\2\u00e7\u00d8\3\2\2\2\u00e7\u00d9\3\2\2\2\u00e8\63"+
		"\3\2\2\2\u00e9\u00ea\7\"\2\2\u00ea\u00eb\7\6\2\2\u00eb\u00ec\5\66\34\2"+
		"\u00ec\u00ed\b\33\1\2\u00ed\65\3\2\2\2\u00ee\u00ef\7\34\2\2\u00ef\u00f0"+
		"\58\35\2\u00f0\u00f1\7:\2\2\u00f1\u00f2\5\36\20\2\u00f2\u00f3\7\36\2\2"+
		"\u00f3\u00f4\b\34\1\2\u00f4\67\3\2\2\2\u00f5\u00f6\7#\2\2\u00f6\u0104"+
		"\b\35\1\2\u00f7\u00f8\7\64\2\2\u00f8\u0104\b\35\1\2\u00f9\u00fa\7\65\2"+
		"\2\u00fa\u0104\b\35\1\2\u00fb\u00fc\7\66\2\2\u00fc\u0104\b\35\1\2\u00fd"+
		"\u00fe\7\67\2\2\u00fe\u0104\b\35\1\2\u00ff\u0100\79\2\2\u0100\u0104\b"+
		"\35\1\2\u0101\u0102\78\2\2\u0102\u0104\b\35\1\2\u0103\u00f5\3\2\2\2\u0103"+
		"\u00f7\3\2\2\2\u0103\u00f9\3\2\2\2\u0103\u00fb\3\2\2\2\u0103\u00fd\3\2"+
		"\2\2\u0103\u00ff\3\2\2\2\u0103\u0101\3\2\2\2\u01049\3\2\2\2\u0105\u0115"+
		"\5,\27\2\u0106\u0107\7\34\2\2\u0107\u0108\5(\25\2\u0108\u010f\b\36\1\2"+
		"\u0109\u010a\7:\2\2\u010a\u010b\5(\25\2\u010b\u010c\b\36\1\2\u010c\u010e"+
		"\3\2\2\2\u010d\u0109\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7\36"+
		"\2\2\u0113\u0115\3\2\2\2\u0114\u0105\3\2\2\2\u0114\u0106\3\2\2\2\u0115"+
		";\3\2\2\2\16?m\u008e\u00c0\u00c5\u00d1\u00d6\u00e2\u00e7\u0103\u010f\u0114";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}