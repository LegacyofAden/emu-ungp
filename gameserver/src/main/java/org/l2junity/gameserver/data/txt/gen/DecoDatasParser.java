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
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecoDatasParser extends Parser {
	static {
		RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
			T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, CATEGORY = 17,
			BOOLEAN = 18, INTEGER = 19, DOUBLE = 20, FIRE = 21, WATER = 22, EARTH = 23, WIND = 24,
			UNHOLY = 25, HOLY = 26, SEMICOLON = 27, NONE = 28, FAIRY = 29, ANIMAL = 30, HUMANOID = 31,
			PLANT = 32, UNDEAD = 33, CONSTRUCT = 34, BEAST = 35, BUG = 36, ELEMENTAL = 37, DEMONIC = 38,
			GIANT = 39, DRAGON = 40, DIVINE = 41, SUMMON = 42, PET = 43, HOLYTHING = 44, DWARF = 45,
			MERCHANT = 46, ELF = 47, KAMAEL = 48, ORC = 49, MERCENARY = 50, CASTLE_GUARD = 51,
			HUMAN = 52, BOSS = 53, ZZOLDAGU = 54, WORLD_TRAP = 55, MONRACE = 56, DARKELF = 57,
			GUARD = 58, TELEPORTER = 59, WAREHOUSE_KEEPER = 60, WARRIOR = 61, CITIZEN = 62,
			TREASURE = 63, FIELDBOSS = 64, BLACKSMITH = 65, GUILD_MASTER = 66, GUILD_COACH = 67,
			SWORD = 68, BLUNT = 69, BOW = 70, POLE = 71, DAGGER = 72, DUAL = 73, FIST = 74, DUALFIST = 75,
			FISHINGROD = 76, RAPIER = 77, ANCIENTSWORD = 78, CROSSBOW = 79, FLAG = 80, OWNTHING = 81,
			DUALDAGGER = 82, ETC = 83, LIGHT = 84, HEAVY = 85, MAGIC = 86, SIGIL = 87, RHAND = 88,
			LRHAND = 89, LHAND = 90, CHEST = 91, LEGS = 92, FEET = 93, HEAD = 94, GLOVES = 95, ONEPIECE = 96,
			REAR = 97, LEAR = 98, RFINGER = 99, LFINGER = 100, NECK = 101, BACK = 102, UNDERWEAR = 103,
			HAIR = 104, HAIR2 = 105, HAIRALL = 106, ALLDRESS = 107, RBRACELET = 108, LBRACELET = 109,
			WAIST = 110, DECO1 = 111, STEEL = 112, FINE_STEEL = 113, WOOD = 114, CLOTH = 115,
			LEATHER = 116, BONE = 117, BRONZE = 118, ORIHARUKON = 119, MITHRIL = 120, DAMASCUS = 121,
			ADAMANTAITE = 122, BLOOD_STEEL = 123, PAPER = 124, GOLD = 125, LIQUID = 126, FISH = 127,
			SILVER = 128, CHRYSOLITE = 129, CRYSTAL = 130, HORN = 131, SCALE_OF_DRAGON = 132,
			COTTON = 133, DYESTUFF = 134, COBWEB = 135, RUNE_XP = 136, RUNE_SP = 137, RUNE_REMOVE_PENALTY = 138,
			NAME = 139, WS = 140, LINE_COMMENT = 141, STAR_COMMENT = 142;
	public static final int
			RULE_file = 0, RULE_deco_data = 1, RULE_id = 2, RULE_name = 3, RULE_type = 4,
			RULE_level = 5, RULE_depth_ = 6, RULE_func = 7, RULE_cost = 8, RULE_identifier_object = 9,
			RULE_bool_object = 10, RULE_byte_object = 11, RULE_int_object = 12, RULE_long_object = 13,
			RULE_double_object = 14, RULE_string_object = 15, RULE_name_object = 16,
			RULE_category_object = 17, RULE_vector3D_object = 18, RULE_empty_list = 19,
			RULE_identifier_list = 20, RULE_int_list = 21, RULE_double_list = 22,
			RULE_base_attribute_attack = 23, RULE_attack_attribute = 24, RULE_attribute = 25,
			RULE_category_list = 26;
	public static final String[] ruleNames = {
			"file", "deco_data", "id", "name", "type", "level", "depth_", "func",
			"cost", "identifier_object", "bool_object", "byte_object", "int_object",
			"long_object", "double_object", "string_object", "name_object", "category_object",
			"vector3D_object", "empty_list", "identifier_list", "int_list", "double_list",
			"base_attribute_attack", "attack_attribute", "attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
			null, "'deco_begin'", "'deco_end'", "'id'", "'='", "'name'", "'type'",
			"'level'", "'depth'", "'func'", "'cost'", "'slot_lhand'", "'['", "']'",
			"'{'", "'}'", "'base_attribute_attack'", null, null, null, null, "'fire'",
			"'water'", "'earth'", "'wind'", "'unholy'", "'holy'", "';'", "'none'",
			"'fairy'", "'animal'", "'humanoid'", "'plant'", "'undead'", "'construct'",
			"'beast'", "'bug'", "'elemental'", "'demonic'", "'giant'", "'dragon'",
			"'divine'", "'summon'", "'pet'", "'holything'", "'dwarf'", "'merchant'",
			"'elf'", "'kamael'", "'orc'", "'mercenary'", "'castle_guard'", "'human'",
			"'boss'", "'zzoldagu'", "'world_trap'", "'monrace'", "'darkelf'", "'guard'",
			"'teleporter'", "'warehouse_keeper'", "'warrior'", "'citizen'", "'treasure'",
			"'fieldboss'", "'blacksmith'", "'guild_master'", "'guild_coach'", "'sword'",
			"'blunt'", "'bow'", "'pole'", "'dagger'", "'dual'", "'fist'", "'dualfist'",
			"'fishingrod'", "'rapier'", "'ancientsword'", "'crossbow'", "'flag'",
			"'ownthing'", "'dualdagger'", "'etc'", "'light'", "'heavy'", "'magic'",
			"'sigil'", "'rhand'", "'lrhand'", "'lhand'", "'chest'", "'legs'", "'feet'",
			"'head'", "'gloves'", "'onepiece'", "'rear'", "'lear'", "'rfinger'", "'lfinger'",
			"'neck'", "'back'", "'underwear'", "'hair'", "'hair2'", "'hairall'", "'alldress'",
			"'rbracelet'", "'lbracelet'", "'waist'", "'deco1'", "'steel'", "'fine_steel'",
			"'wood'", "'cloth'", "'leather'", "'bone'", "'bronze'", "'oriharukon'",
			"'mithril'", "'damascus'", "'adamantaite'", "'blood_steel'", "'paper'",
			"'gold'", "'liquid'", "'fish'", "'silver'", "'chrysolite'", "'crystal'",
			"'horn'", "'scale_of_dragon'", "'cotton'", "'dyestuff'", "'cobweb'", "'rune_xp'",
			"'rune_sp'", "'rune_remove_penalty'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, "CATEGORY", "BOOLEAN", "INTEGER", "DOUBLE",
			"FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", "SEMICOLON", "NONE",
			"FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", "CONSTRUCT", "BEAST",
			"BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", "DIVINE", "SUMMON",
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
	public String getGrammarFileName() {
		return "DecoDatas.g4";
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

	public DecoDatasParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	public static class FileContext extends ParserRuleContext {
		public List<Deco_dataContext> deco_data() {
			return getRuleContexts(Deco_dataContext.class);
		}

		public Deco_dataContext deco_data(int i) {
			return getRuleContext(Deco_dataContext.class, i);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterFile(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
						{
							setState(54);
							deco_data();
						}
					}
					setState(57);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while (_la == T__0);
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

	public static class Deco_dataContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class, 0);
		}

		public NameContext name() {
			return getRuleContext(NameContext.class, 0);
		}

		public TypeContext type() {
			return getRuleContext(TypeContext.class, 0);
		}

		public LevelContext level() {
			return getRuleContext(LevelContext.class, 0);
		}

		public Depth_Context depth_() {
			return getRuleContext(Depth_Context.class, 0);
		}

		public FuncContext func() {
			return getRuleContext(FuncContext.class, 0);
		}

		public CostContext cost() {
			return getRuleContext(CostContext.class, 0);
		}

		public Deco_dataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_deco_data;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterDeco_data(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitDeco_data(this);
		}
	}

	public final Deco_dataContext deco_data() throws RecognitionException {
		Deco_dataContext _localctx = new Deco_dataContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_deco_data);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(59);
				match(T__0);
				setState(60);
				id();
				setState(61);
				name();
				setState(62);
				type();
				setState(63);
				level();
				setState(64);
				depth_();
				setState(65);
				func();
				setState(66);
				cost();
				setState(67);
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

	public static class IdContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_id;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterId(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(69);
				match(T__2);
				setState(70);
				match(T__3);
				setState(71);
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

	public static class NameContext extends ParserRuleContext {
		public Name_objectContext name_object() {
			return getRuleContext(Name_objectContext.class, 0);
		}

		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_name;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterName(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(73);
				match(T__4);
				setState(74);
				match(T__3);
				setState(75);
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

	public static class TypeContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_type;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterType(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(77);
				match(T__5);
				setState(78);
				match(T__3);
				setState(79);
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

	public static class LevelContext extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public LevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_level;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterLevel(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitLevel(this);
		}
	}

	public final LevelContext level() throws RecognitionException {
		LevelContext _localctx = new LevelContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_level);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(81);
				match(T__6);
				setState(82);
				match(T__3);
				setState(83);
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

	public static class Depth_Context extends ParserRuleContext {
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class, 0);
		}

		public Depth_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_depth_;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterDepth_(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitDepth_(this);
		}
	}

	public final Depth_Context depth_() throws RecognitionException {
		Depth_Context _localctx = new Depth_Context(_ctx, getState());
		enterRule(_localctx, 12, RULE_depth_);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(85);
				match(T__7);
				setState(86);
				match(T__3);
				setState(87);
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

	public static class FuncContext extends ParserRuleContext {
		public String_objectContext string_object() {
			return getRuleContext(String_objectContext.class, 0);
		}

		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_func;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterFunc(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitFunc(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_func);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(89);
				match(T__8);
				setState(90);
				match(T__3);
				setState(91);
				string_object();
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

	public static class CostContext extends ParserRuleContext {
		public String_objectContext string_object() {
			return getRuleContext(String_objectContext.class, 0);
		}

		public CostContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_cost;
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterCost(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitCost(this);
		}
	}

	public final CostContext cost() throws RecognitionException {
		CostContext _localctx = new CostContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cost);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(93);
				match(T__9);
				setState(94);
				match(T__3);
				setState(95);
				string_object();
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
			return getToken(DecoDatasParser.DAGGER, 0);
		}

		public TerminalNode BOW() {
			return getToken(DecoDatasParser.BOW, 0);
		}

		public TerminalNode CROSSBOW() {
			return getToken(DecoDatasParser.CROSSBOW, 0);
		}

		public TerminalNode RAPIER() {
			return getToken(DecoDatasParser.RAPIER, 0);
		}

		public TerminalNode GLOVES() {
			return getToken(DecoDatasParser.GLOVES, 0);
		}

		public TerminalNode STEEL() {
			return getToken(DecoDatasParser.STEEL, 0);
		}

		public TerminalNode LEATHER() {
			return getToken(DecoDatasParser.LEATHER, 0);
		}

		public TerminalNode ORIHARUKON() {
			return getToken(DecoDatasParser.ORIHARUKON, 0);
		}

		public TerminalNode NAME() {
			return getToken(DecoDatasParser.NAME, 0);
		}

		public TerminalNode NONE() {
			return getToken(DecoDatasParser.NONE, 0);
		}

		public TerminalNode ORC() {
			return getToken(DecoDatasParser.ORC, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterIdentifier_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(97);
				_la = _input.LA(1);
				if (!(((((_la - 11)) & ~0x3f) == 0 && ((1L << (_la - 11)) & ((1L << (T__10 - 11)) | (1L << (NONE - 11)) | (1L << (ORC - 11)) | (1L << (BOW - 11)) | (1L << (DAGGER - 11)))) != 0) || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (RAPIER - 77)) | (1L << (CROSSBOW - 77)) | (1L << (GLOVES - 77)) | (1L << (STEEL - 77)) | (1L << (LEATHER - 77)) | (1L << (ORIHARUKON - 77)) | (1L << (NAME - 77)))) != 0))) {
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
			return getToken(DecoDatasParser.BOOLEAN, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterBool_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(99);
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
			return getToken(DecoDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(DecoDatasParser.INTEGER, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterByte_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(101);
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
			return getToken(DecoDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(DecoDatasParser.INTEGER, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterInt_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(103);
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
			return getToken(DecoDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(DecoDatasParser.INTEGER, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterLong_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(105);
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
			return getToken(DecoDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(DecoDatasParser.INTEGER, 0);
		}

		public TerminalNode DOUBLE() {
			return getToken(DecoDatasParser.DOUBLE, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterDouble_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(107);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << DOUBLE))) != 0))) {
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
			return getToken(DecoDatasParser.BOOLEAN, 0);
		}

		public TerminalNode INTEGER() {
			return getToken(DecoDatasParser.INTEGER, 0);
		}

		public TerminalNode DOUBLE() {
			return getToken(DecoDatasParser.DOUBLE, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterString_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(109);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << INTEGER) | (1L << DOUBLE))) != 0))) {
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterName_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(111);
				match(T__11);
				setState(112);
				((Name_objectContext) _localctx).io = identifier_object();
				setState(113);
				match(T__12);
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
			return getToken(DecoDatasParser.CATEGORY, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterCategory_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(116);
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
			return getTokens(DecoDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterVector3D_object(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(118);
				match(T__13);
				setState(119);
				((Vector3D_objectContext) _localctx).x = int_object();
				setState(120);
				match(SEMICOLON);
				setState(121);
				((Vector3D_objectContext) _localctx).y = int_object();
				setState(122);
				match(SEMICOLON);
				setState(123);
				((Vector3D_objectContext) _localctx).z = int_object();
				setState(124);
				match(T__14);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterEmpty_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(126);
				match(T__13);
				setState(127);
				match(T__14);
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
			return getTokens(DecoDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterIdentifier_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_identifier_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(144);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 2, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(129);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(130);
					match(T__13);
					setState(131);
					((Identifier_listContext) _localctx).io = identifier_object();
					_localctx.value.add(((Identifier_listContext) _localctx).io.value);
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(133);
								match(SEMICOLON);
								setState(134);
								((Identifier_listContext) _localctx).io = identifier_object();
								_localctx.value.add(((Identifier_listContext) _localctx).io.value);
							}
						}
						setState(141);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(142);
					match(T__14);
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
			return getTokens(DecoDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterInt_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_int_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(161);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(146);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(147);
					match(T__13);
					setState(148);
					((Int_listContext) _localctx).io = int_object();
					_localctx.value.add(((Int_listContext) _localctx).io.value);
					setState(156);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(150);
								match(SEMICOLON);
								setState(151);
								((Int_listContext) _localctx).io = int_object();
								_localctx.value.add(((Int_listContext) _localctx).io.value);
							}
						}
						setState(158);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(159);
					match(T__14);
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
			return getTokens(DecoDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterDouble_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_double_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(178);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(163);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(164);
					match(T__13);
					setState(165);
					((Double_listContext) _localctx).d = double_object();
					_localctx.value.add(Double.valueOf((((Double_listContext) _localctx).d != null ? _input.getText(((Double_listContext) _localctx).d.start, ((Double_listContext) _localctx).d.stop) : null)));
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(167);
								match(SEMICOLON);
								setState(168);
								((Double_listContext) _localctx).d = double_object();
								_localctx.value.add(Double.valueOf((((Double_listContext) _localctx).d != null ? _input.getText(((Double_listContext) _localctx).d.start, ((Double_listContext) _localctx).d.stop) : null)));
							}
						}
						setState(175);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(176);
					match(T__14);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterBase_attribute_attack(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(180);
				match(T__15);
				setState(181);
				match(T__3);
				setState(182);
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
			return getToken(DecoDatasParser.SEMICOLON, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterAttack_attribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(185);
				match(T__13);
				setState(186);
				((Attack_attributeContext) _localctx).attribute = attribute();
				setState(187);
				match(SEMICOLON);
				setState(188);
				((Attack_attributeContext) _localctx).io = int_object();
				setState(189);
				match(T__14);
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
			return getToken(DecoDatasParser.NONE, 0);
		}

		public TerminalNode FIRE() {
			return getToken(DecoDatasParser.FIRE, 0);
		}

		public TerminalNode WATER() {
			return getToken(DecoDatasParser.WATER, 0);
		}

		public TerminalNode EARTH() {
			return getToken(DecoDatasParser.EARTH, 0);
		}

		public TerminalNode WIND() {
			return getToken(DecoDatasParser.WIND, 0);
		}

		public TerminalNode HOLY() {
			return getToken(DecoDatasParser.HOLY, 0);
		}

		public TerminalNode UNHOLY() {
			return getToken(DecoDatasParser.UNHOLY, 0);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterAttribute(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_attribute);
		try {
			setState(206);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
				case NONE:
					enterOuterAlt(_localctx, 1);
				{
					setState(192);
					match(NONE);
					_localctx.value = AttributeType.NONE;
				}
				break;
				case FIRE:
					enterOuterAlt(_localctx, 2);
				{
					setState(194);
					match(FIRE);
					_localctx.value = AttributeType.FIRE;
				}
				break;
				case WATER:
					enterOuterAlt(_localctx, 3);
				{
					setState(196);
					match(WATER);
					_localctx.value = AttributeType.WATER;
				}
				break;
				case EARTH:
					enterOuterAlt(_localctx, 4);
				{
					setState(198);
					match(EARTH);
					_localctx.value = AttributeType.EARTH;
				}
				break;
				case WIND:
					enterOuterAlt(_localctx, 5);
				{
					setState(200);
					match(WIND);
					_localctx.value = AttributeType.WIND;
				}
				break;
				case HOLY:
					enterOuterAlt(_localctx, 6);
				{
					setState(202);
					match(HOLY);
					_localctx.value = AttributeType.HOLY;
				}
				break;
				case UNHOLY:
					enterOuterAlt(_localctx, 7);
				{
					setState(204);
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
			return getTokens(DecoDatasParser.SEMICOLON);
		}

		public TerminalNode SEMICOLON(int i) {
			return getToken(DecoDatasParser.SEMICOLON, i);
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
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).enterCategory_list(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof DecoDatasListener) ((DecoDatasListener) listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_category_list);
		_localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(223);
			_errHandler.sync(this);
			switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(208);
					empty_list();
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(209);
					match(T__13);
					setState(210);
					((Category_listContext) _localctx).co = category_object();
					_localctx.value.add((((Category_listContext) _localctx).co != null ? _input.getText(((Category_listContext) _localctx).co.start, ((Category_listContext) _localctx).co.stop) : null));
					setState(218);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == SEMICOLON) {
						{
							{
								setState(212);
								match(SEMICOLON);
								setState(213);
								((Category_listContext) _localctx).co = category_object();
								_localctx.value.add((((Category_listContext) _localctx).co != null ? _input.getText(((Category_listContext) _localctx).co.start, ((Category_listContext) _localctx).co.stop) : null));
							}
						}
						setState(220);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(221);
					match(T__14);
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
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0090\u00e4\4\2\t" +
					"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13" +
					"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\3\2\6\2:\n\2\r\2\16\2;\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3" +
					"\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13" +
					"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22" +
					"\3\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25" +
					"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u008c\n\26\f\26" +
					"\16\26\u008f\13\26\3\26\3\26\5\26\u0093\n\26\3\27\3\27\3\27\3\27\3\27" +
					"\3\27\3\27\3\27\7\27\u009d\n\27\f\27\16\27\u00a0\13\27\3\27\3\27\5\27" +
					"\u00a4\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u00ae\n\30\f" +
					"\30\16\30\u00b1\13\30\3\30\3\30\5\30\u00b5\n\30\3\31\3\31\3\31\3\31\3" +
					"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3" +
					"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u00d1\n\33\3\34\3\34\3\34" +
					"\3\34\3\34\3\34\3\34\3\34\7\34\u00db\n\34\f\34\16\34\u00de\13\34\3\34" +
					"\3\34\5\34\u00e2\n\34\3\34\2\2\35\2\4\6\b\n\f\16\20\22\24\26\30\32\34" +
					"\36 \"$&(*,.\60\62\64\66\2\5\16\2\r\r\36\36\63\63HHJJOOQQaarrvvyy\u008d" +
					"\u008d\3\2\24\25\3\2\24\26\2\u00d7\29\3\2\2\2\4=\3\2\2\2\6G\3\2\2\2\b" +
					"K\3\2\2\2\nO\3\2\2\2\fS\3\2\2\2\16W\3\2\2\2\20[\3\2\2\2\22_\3\2\2\2\24" +
					"c\3\2\2\2\26e\3\2\2\2\30g\3\2\2\2\32i\3\2\2\2\34k\3\2\2\2\36m\3\2\2\2" +
					" o\3\2\2\2\"q\3\2\2\2$v\3\2\2\2&x\3\2\2\2(\u0080\3\2\2\2*\u0092\3\2\2" +
					"\2,\u00a3\3\2\2\2.\u00b4\3\2\2\2\60\u00b6\3\2\2\2\62\u00bb\3\2\2\2\64" +
					"\u00d0\3\2\2\2\66\u00e1\3\2\2\28:\5\4\3\298\3\2\2\2:;\3\2\2\2;9\3\2\2" +
					"\2;<\3\2\2\2<\3\3\2\2\2=>\7\3\2\2>?\5\6\4\2?@\5\b\5\2@A\5\n\6\2AB\5\f" +
					"\7\2BC\5\16\b\2CD\5\20\t\2DE\5\22\n\2EF\7\4\2\2F\5\3\2\2\2GH\7\5\2\2H" +
					"I\7\6\2\2IJ\5\32\16\2J\7\3\2\2\2KL\7\7\2\2LM\7\6\2\2MN\5\"\22\2N\t\3\2" +
					"\2\2OP\7\b\2\2PQ\7\6\2\2QR\5\32\16\2R\13\3\2\2\2ST\7\t\2\2TU\7\6\2\2U" +
					"V\5\32\16\2V\r\3\2\2\2WX\7\n\2\2XY\7\6\2\2YZ\5\32\16\2Z\17\3\2\2\2[\\" +
					"\7\13\2\2\\]\7\6\2\2]^\5 \21\2^\21\3\2\2\2_`\7\f\2\2`a\7\6\2\2ab\5 \21" +
					"\2b\23\3\2\2\2cd\t\2\2\2d\25\3\2\2\2ef\7\24\2\2f\27\3\2\2\2gh\t\3\2\2" +
					"h\31\3\2\2\2ij\t\3\2\2j\33\3\2\2\2kl\t\3\2\2l\35\3\2\2\2mn\t\4\2\2n\37" +
					"\3\2\2\2op\t\4\2\2p!\3\2\2\2qr\7\16\2\2rs\5\24\13\2st\7\17\2\2tu\b\22" +
					"\1\2u#\3\2\2\2vw\7\23\2\2w%\3\2\2\2xy\7\20\2\2yz\5\32\16\2z{\7\35\2\2" +
					"{|\5\32\16\2|}\7\35\2\2}~\5\32\16\2~\177\7\21\2\2\177\'\3\2\2\2\u0080" +
					"\u0081\7\20\2\2\u0081\u0082\7\21\2\2\u0082)\3\2\2\2\u0083\u0093\5(\25" +
					"\2\u0084\u0085\7\20\2\2\u0085\u0086\5\24\13\2\u0086\u008d\b\26\1\2\u0087" +
					"\u0088\7\35\2\2\u0088\u0089\5\24\13\2\u0089\u008a\b\26\1\2\u008a\u008c" +
					"\3\2\2\2\u008b\u0087\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d" +
					"\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7\21" +
					"\2\2\u0091\u0093\3\2\2\2\u0092\u0083\3\2\2\2\u0092\u0084\3\2\2\2\u0093" +
					"+\3\2\2\2\u0094\u00a4\5(\25\2\u0095\u0096\7\20\2\2\u0096\u0097\5\32\16" +
					"\2\u0097\u009e\b\27\1\2\u0098\u0099\7\35\2\2\u0099\u009a\5\32\16\2\u009a" +
					"\u009b\b\27\1\2\u009b\u009d\3\2\2\2\u009c\u0098\3\2\2\2\u009d\u00a0\3" +
					"\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0" +
					"\u009e\3\2\2\2\u00a1\u00a2\7\21\2\2\u00a2\u00a4\3\2\2\2\u00a3\u0094\3" +
					"\2\2\2\u00a3\u0095\3\2\2\2\u00a4-\3\2\2\2\u00a5\u00b5\5(\25\2\u00a6\u00a7" +
					"\7\20\2\2\u00a7\u00a8\5\36\20\2\u00a8\u00af\b\30\1\2\u00a9\u00aa\7\35" +
					"\2\2\u00aa\u00ab\5\36\20\2\u00ab\u00ac\b\30\1\2\u00ac\u00ae\3\2\2\2\u00ad" +
					"\u00a9\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2" +
					"\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7\21\2\2\u00b3" +
					"\u00b5\3\2\2\2\u00b4\u00a5\3\2\2\2\u00b4\u00a6\3\2\2\2\u00b5/\3\2\2\2" +
					"\u00b6\u00b7\7\22\2\2\u00b7\u00b8\7\6\2\2\u00b8\u00b9\5\62\32\2\u00b9" +
					"\u00ba\b\31\1\2\u00ba\61\3\2\2\2\u00bb\u00bc\7\20\2\2\u00bc\u00bd\5\64" +
					"\33\2\u00bd\u00be\7\35\2\2\u00be\u00bf\5\32\16\2\u00bf\u00c0\7\21\2\2" +
					"\u00c0\u00c1\b\32\1\2\u00c1\63\3\2\2\2\u00c2\u00c3\7\36\2\2\u00c3\u00d1" +
					"\b\33\1\2\u00c4\u00c5\7\27\2\2\u00c5\u00d1\b\33\1\2\u00c6\u00c7\7\30\2" +
					"\2\u00c7\u00d1\b\33\1\2\u00c8\u00c9\7\31\2\2\u00c9\u00d1\b\33\1\2\u00ca" +
					"\u00cb\7\32\2\2\u00cb\u00d1\b\33\1\2\u00cc\u00cd\7\34\2\2\u00cd\u00d1" +
					"\b\33\1\2\u00ce\u00cf\7\33\2\2\u00cf\u00d1\b\33\1\2\u00d0\u00c2\3\2\2" +
					"\2\u00d0\u00c4\3\2\2\2\u00d0\u00c6\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0\u00ca" +
					"\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\65\3\2\2\2\u00d2" +
					"\u00e2\5(\25\2\u00d3\u00d4\7\20\2\2\u00d4\u00d5\5$\23\2\u00d5\u00dc\b" +
					"\34\1\2\u00d6\u00d7\7\35\2\2\u00d7\u00d8\5$\23\2\u00d8\u00d9\b\34\1\2" +
					"\u00d9\u00db\3\2\2\2\u00da\u00d6\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da" +
					"\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df" +
					"\u00e0\7\21\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00d2\3\2\2\2\u00e1\u00d3\3" +
					"\2\2\2\u00e2\67\3\2\2\2\f;\u008d\u0092\u009e\u00a3\u00af\u00b4\u00d0\u00dc" +
					"\u00e1";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}