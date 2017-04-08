// Generated from org\l2junity\gameserver\data\txt\gen\UserBasicActions.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.constants.ActionHandlerType;
import org.l2junity.gameserver.data.txt.model.action.ActionTemplate;


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
public class UserBasicActionsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, SIT_STAND=13, WALK_RUN=14, ATTACK=15, TRADE=16, 
		CLIENT_ACTION=17, PRIVATE_STORE=18, SOCIAL_ACTION=19, PET_ACTION=20, PET_DEPOSIT=21, 
		SUMMON_ACTION=22, PRIVATE_BUY=23, MAKE_ITEM=24, MAKE_ITEM2=25, RIDE=26, 
		SUMMON_DESPAWN=27, PACKAGE_PRIVATE_STORE=28, TELEPORT_BOOKMARK=29, BOT_REPORT=30, 
		AIRSHIP_ACTION=31, COUPLE_ACTION=32, CATEGORY=33, BOOLEAN=34, INTEGER=35, 
		DOUBLE=36, FIRE=37, WATER=38, EARTH=39, WIND=40, UNHOLY=41, HOLY=42, SEMICOLON=43, 
		NONE=44, FAIRY=45, ANIMAL=46, HUMANOID=47, PLANT=48, UNDEAD=49, CONSTRUCT=50, 
		BEAST=51, BUG=52, ELEMENTAL=53, DEMONIC=54, GIANT=55, DRAGON=56, DIVINE=57, 
		SUMMON=58, PET=59, HOLYTHING=60, DWARF=61, MERCHANT=62, ELF=63, KAMAEL=64, 
		ORC=65, SIEGE_WEAPON=66, FRIEND=67, MERCENARY=68, CASTLE_GUARD=69, HUMAN=70, 
		BOSS=71, ZZOLDAGU=72, WORLD_TRAP=73, MONRACE=74, DARKELF=75, GUARD=76, 
		TELEPORTER=77, WAREHOUSE_KEEPER=78, WARRIOR=79, CITIZEN=80, TREASURE=81, 
		FIELDBOSS=82, BLACKSMITH=83, GUILD_MASTER=84, GUILD_COACH=85, PC_TRAP=86, 
		XMASTREE=87, DOPPELGANGER=88, OWNTHING=89, SIEGE_ATTACKER=90, MRKEEPER=91, 
		COLLECTION=92, PACKAGE_KEEPER=93, SWORD=94, BLUNT=95, BOW=96, POLE=97, 
		DAGGER=98, DUAL=99, FIST=100, DUALFIST=101, FISHINGROD=102, RAPIER=103, 
		ANCIENTSWORD=104, CROSSBOW=105, FLAG=106, DUALDAGGER=107, ETC=108, LIGHT=109, 
		HEAVY=110, MAGIC=111, SIGIL=112, RHAND=113, LRHAND=114, LHAND=115, CHEST=116, 
		LEGS=117, FEET=118, HEAD=119, GLOVES=120, ONEPIECE=121, REAR=122, LEAR=123, 
		RFINGER=124, LFINGER=125, NECK=126, BACK=127, UNDERWEAR=128, HAIR=129, 
		HAIR2=130, HAIRALL=131, ALLDRESS=132, RBRACELET=133, LBRACELET=134, WAIST=135, 
		DECO1=136, STEEL=137, FINE_STEEL=138, WOOD=139, CLOTH=140, LEATHER=141, 
		BONE=142, BRONZE=143, ORIHARUKON=144, MITHRIL=145, DAMASCUS=146, ADAMANTAITE=147, 
		BLOOD_STEEL=148, PAPER=149, GOLD=150, LIQUID=151, FISH=152, SILVER=153, 
		CHRYSOLITE=154, CRYSTAL=155, HORN=156, SCALE_OF_DRAGON=157, COTTON=158, 
		DYESTUFF=159, COBWEB=160, RUNE_XP=161, RUNE_SP=162, RUNE_REMOVE_PENALTY=163, 
		NAME=164, WS=165, LINE_COMMENT=166, STAR_COMMENT=167;
	public static final int
		RULE_file = 0, RULE_action = 1, RULE_id = 2, RULE_handler = 3, RULE_option = 4, 
		RULE_handler_type = 5, RULE_identifier_object = 6, RULE_bool_object = 7, 
		RULE_byte_object = 8, RULE_int_object = 9, RULE_long_object = 10, RULE_double_object = 11, 
		RULE_string_object = 12, RULE_name_object = 13, RULE_category_object = 14, 
		RULE_vector3D_object = 15, RULE_empty_list = 16, RULE_identifier_list = 17, 
		RULE_int_list = 18, RULE_double_list = 19, RULE_base_attribute_attack = 20, 
		RULE_attack_attribute = 21, RULE_attribute = 22, RULE_category_list = 23;
	public static final String[] ruleNames = {
		"file", "action", "id", "handler", "option", "handler_type", "identifier_object", 
		"bool_object", "byte_object", "int_object", "long_object", "double_object", 
		"string_object", "name_object", "category_object", "vector3D_object", 
		"empty_list", "identifier_list", "int_list", "double_list", "base_attribute_attack", 
		"attack_attribute", "attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'action_begin'", "'action_end'", "'id'", "'='", "'handler'", "'['", 
		"']'", "'option'", "'slot_lhand'", "'{'", "'}'", "'base_attribute_attack'", 
		"'SIT_STAND'", "'WALK_RUN'", "'ATTACK'", "'TRADE'", "'CLIENT_ACTION'", 
		"'PRIVATE_STORE'", "'SOCIAL_ACTION'", "'PET_ACTION'", "'PET_DEPOSIT'", 
		"'SUMMON_ACTION'", "'PRIVATE_BUY'", "'MAKE_ITEM'", "'MAKE_ITEM2'", "'RIDE'", 
		"'SUMMON_DESPAWN'", "'PACKAGE_PRIVATE_STORE'", "'TELEPORT_BOOKMARK'", 
		"'BOT_REPORT'", "'AIRSHIP_ACTION'", "'COUPLE_ACTION'", null, null, null, 
		null, "'fire'", "'water'", "'earth'", "'wind'", "'unholy'", "'holy'", 
		"';'", "'none'", "'fairy'", "'animal'", "'humanoid'", "'plant'", "'undead'", 
		"'construct'", "'beast'", "'bug'", "'elemental'", "'demonic'", "'giant'", 
		"'dragon'", "'divine'", "'summon'", "'pet'", "'holything'", "'dwarf'", 
		"'merchant'", "'elf'", "'kamael'", "'orc'", "'siege_weapon'", "'friend'", 
		"'mercenary'", "'castle_guard'", "'human'", "'boss'", "'zzoldagu'", "'world_trap'", 
		"'monrace'", "'darkelf'", "'guard'", "'teleporter'", "'warehouse_keeper'", 
		"'warrior'", "'citizen'", "'treasure'", "'fieldboss'", "'blacksmith'", 
		"'guild_master'", "'guild_coach'", "'pc_trap'", "'xmastree'", "'doppelganger'", 
		"'ownthing'", "'siege_attacker'", "'mrkeeper'", "'collection'", "'package_keeper'", 
		"'sword'", "'blunt'", "'bow'", "'pole'", "'dagger'", "'dual'", "'fist'", 
		"'dualfist'", "'fishingrod'", "'rapier'", "'ancientsword'", "'crossbow'", 
		"'flag'", "'dualdagger'", "'etc'", "'light'", "'heavy'", "'magic'", "'sigil'", 
		"'rhand'", "'lrhand'", "'lhand'", "'chest'", "'legs'", "'feet'", "'head'", 
		"'gloves'", "'onepiece'", "'rear'", "'lear'", "'rfinger'", "'lfinger'", 
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
		null, "SIT_STAND", "WALK_RUN", "ATTACK", "TRADE", "CLIENT_ACTION", "PRIVATE_STORE", 
		"SOCIAL_ACTION", "PET_ACTION", "PET_DEPOSIT", "SUMMON_ACTION", "PRIVATE_BUY", 
		"MAKE_ITEM", "MAKE_ITEM2", "RIDE", "SUMMON_DESPAWN", "PACKAGE_PRIVATE_STORE", 
		"TELEPORT_BOOKMARK", "BOT_REPORT", "AIRSHIP_ACTION", "COUPLE_ACTION", 
		"CATEGORY", "BOOLEAN", "INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", 
		"WIND", "UNHOLY", "HOLY", "SEMICOLON", "NONE", "FAIRY", "ANIMAL", "HUMANOID", 
		"PLANT", "UNDEAD", "CONSTRUCT", "BEAST", "BUG", "ELEMENTAL", "DEMONIC", 
		"GIANT", "DRAGON", "DIVINE", "SUMMON", "PET", "HOLYTHING", "DWARF", "MERCHANT", 
		"ELF", "KAMAEL", "ORC", "SIEGE_WEAPON", "FRIEND", "MERCENARY", "CASTLE_GUARD", 
		"HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", "MONRACE", "DARKELF", "GUARD", 
		"TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", "CITIZEN", "TREASURE", "FIELDBOSS", 
		"BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", "PC_TRAP", "XMASTREE", "DOPPELGANGER", 
		"OWNTHING", "SIEGE_ATTACKER", "MRKEEPER", "COLLECTION", "PACKAGE_KEEPER", 
		"SWORD", "BLUNT", "BOW", "POLE", "DAGGER", "DUAL", "FIST", "DUALFIST", 
		"FISHINGROD", "RAPIER", "ANCIENTSWORD", "CROSSBOW", "FLAG", "DUALDAGGER", 
		"ETC", "LIGHT", "HEAVY", "MAGIC", "SIGIL", "RHAND", "LRHAND", "LHAND", 
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
	public String getGrammarFileName() { return "UserBasicActions.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public UserBasicActionsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public List<ActionContext> action() {
			return getRuleContexts(ActionContext.class);
		}
		public ActionContext action(int i) {
			return getRuleContext(ActionContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitFile(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(48);
				action();
				}
				}
				setState(51); 
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

	public static class ActionContext extends ParserRuleContext {
		public ActionTemplate value;
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public HandlerContext handler() {
			return getRuleContext(HandlerContext.class,0);
		}
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitAction(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T__0);
			setState(54);
			id();
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(55);
				handler();
				}
			}

			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(58);
				option();
				}
			}

			setState(61);
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
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitId(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__2);
			setState(64);
			match(T__3);
			setState(65);
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

	public static class HandlerContext extends ParserRuleContext {
		public ActionHandlerType value;
		public Handler_typeContext ht;
		public Handler_typeContext handler_type() {
			return getRuleContext(Handler_typeContext.class,0);
		}
		public HandlerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_handler; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterHandler(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitHandler(this);
		}
	}

	public final HandlerContext handler() throws RecognitionException {
		HandlerContext _localctx = new HandlerContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_handler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__4);
			setState(69);
			match(T__3);
			setState(70);
			match(T__5);
			setState(71);
			((HandlerContext)_localctx).ht = handler_type();
			setState(72);
			match(T__6);
			_localctx.value = ((HandlerContext)_localctx).ht.value;
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

	public static class OptionContext extends ParserRuleContext {
		public Object value;
		public Int_objectContext io;
		public Identifier_objectContext iobj;
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Identifier_objectContext identifier_object() {
			return getRuleContext(Identifier_objectContext.class,0);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitOption(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__7);
			setState(76);
			match(T__3);
			setState(83);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case INTEGER:
				{
				setState(77);
				((OptionContext)_localctx).io = int_object();
				_localctx.value = ((OptionContext)_localctx).io.value;
				}
				break;
			case T__8:
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
			case NAME:
				{
				setState(80);
				((OptionContext)_localctx).iobj = identifier_object();
				_localctx.value = ((OptionContext)_localctx).iobj.value;
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

	public static class Handler_typeContext extends ParserRuleContext {
		public ActionHandlerType value;
		public TerminalNode SIT_STAND() { return getToken(UserBasicActionsParser.SIT_STAND, 0); }
		public TerminalNode WALK_RUN() { return getToken(UserBasicActionsParser.WALK_RUN, 0); }
		public TerminalNode ATTACK() { return getToken(UserBasicActionsParser.ATTACK, 0); }
		public TerminalNode TRADE() { return getToken(UserBasicActionsParser.TRADE, 0); }
		public TerminalNode CLIENT_ACTION() { return getToken(UserBasicActionsParser.CLIENT_ACTION, 0); }
		public TerminalNode PRIVATE_STORE() { return getToken(UserBasicActionsParser.PRIVATE_STORE, 0); }
		public TerminalNode PACKAGE_PRIVATE_STORE() { return getToken(UserBasicActionsParser.PACKAGE_PRIVATE_STORE, 0); }
		public TerminalNode SOCIAL_ACTION() { return getToken(UserBasicActionsParser.SOCIAL_ACTION, 0); }
		public TerminalNode PET_ACTION() { return getToken(UserBasicActionsParser.PET_ACTION, 0); }
		public TerminalNode PET_DEPOSIT() { return getToken(UserBasicActionsParser.PET_DEPOSIT, 0); }
		public TerminalNode SUMMON_ACTION() { return getToken(UserBasicActionsParser.SUMMON_ACTION, 0); }
		public TerminalNode SUMMON_DESPAWN() { return getToken(UserBasicActionsParser.SUMMON_DESPAWN, 0); }
		public TerminalNode PRIVATE_BUY() { return getToken(UserBasicActionsParser.PRIVATE_BUY, 0); }
		public TerminalNode MAKE_ITEM() { return getToken(UserBasicActionsParser.MAKE_ITEM, 0); }
		public TerminalNode MAKE_ITEM2() { return getToken(UserBasicActionsParser.MAKE_ITEM2, 0); }
		public TerminalNode RIDE() { return getToken(UserBasicActionsParser.RIDE, 0); }
		public TerminalNode TELEPORT_BOOKMARK() { return getToken(UserBasicActionsParser.TELEPORT_BOOKMARK, 0); }
		public TerminalNode BOT_REPORT() { return getToken(UserBasicActionsParser.BOT_REPORT, 0); }
		public TerminalNode AIRSHIP_ACTION() { return getToken(UserBasicActionsParser.AIRSHIP_ACTION, 0); }
		public TerminalNode COUPLE_ACTION() { return getToken(UserBasicActionsParser.COUPLE_ACTION, 0); }
		public Handler_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_handler_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterHandler_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitHandler_type(this);
		}
	}

	public final Handler_typeContext handler_type() throws RecognitionException {
		Handler_typeContext _localctx = new Handler_typeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_handler_type);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SIT_STAND:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				match(SIT_STAND);
				_localctx.value = ActionHandlerType.SIT_STAND;
				}
				break;
			case WALK_RUN:
				enterOuterAlt(_localctx, 2);
				{
				setState(87);
				match(WALK_RUN);
				_localctx.value = ActionHandlerType.WALK_RUN;
				}
				break;
			case ATTACK:
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				match(ATTACK);
				_localctx.value = ActionHandlerType.ATTACK;
				}
				break;
			case TRADE:
				enterOuterAlt(_localctx, 4);
				{
				setState(91);
				match(TRADE);
				_localctx.value = ActionHandlerType.TRADE;
				}
				break;
			case CLIENT_ACTION:
				enterOuterAlt(_localctx, 5);
				{
				setState(93);
				match(CLIENT_ACTION);
				_localctx.value = ActionHandlerType.CLIENT_ACTION;
				}
				break;
			case PRIVATE_STORE:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				match(PRIVATE_STORE);
				_localctx.value = ActionHandlerType.PRIVATE_STORE;
				}
				break;
			case PACKAGE_PRIVATE_STORE:
				enterOuterAlt(_localctx, 7);
				{
				setState(97);
				match(PACKAGE_PRIVATE_STORE);
				_localctx.value = ActionHandlerType.PACKAGE_PRIVATE_STORE;
				}
				break;
			case SOCIAL_ACTION:
				enterOuterAlt(_localctx, 8);
				{
				setState(99);
				match(SOCIAL_ACTION);
				_localctx.value = ActionHandlerType.SOCIAL_ACTION;
				}
				break;
			case PET_ACTION:
				enterOuterAlt(_localctx, 9);
				{
				setState(101);
				match(PET_ACTION);
				_localctx.value = ActionHandlerType.PET_ACTION;
				}
				break;
			case PET_DEPOSIT:
				enterOuterAlt(_localctx, 10);
				{
				setState(103);
				match(PET_DEPOSIT);
				_localctx.value = ActionHandlerType.PET_DEPOSIT;
				}
				break;
			case SUMMON_ACTION:
				enterOuterAlt(_localctx, 11);
				{
				setState(105);
				match(SUMMON_ACTION);
				_localctx.value = ActionHandlerType.SUMMON_ACTION;
				}
				break;
			case SUMMON_DESPAWN:
				enterOuterAlt(_localctx, 12);
				{
				setState(107);
				match(SUMMON_DESPAWN);
				_localctx.value = ActionHandlerType.SUMMON_DESPAWN;
				}
				break;
			case PRIVATE_BUY:
				enterOuterAlt(_localctx, 13);
				{
				setState(109);
				match(PRIVATE_BUY);
				_localctx.value = ActionHandlerType.PRIVATE_BUY;
				}
				break;
			case MAKE_ITEM:
				enterOuterAlt(_localctx, 14);
				{
				setState(111);
				match(MAKE_ITEM);
				_localctx.value = ActionHandlerType.MAKE_ITEM;
				}
				break;
			case MAKE_ITEM2:
				enterOuterAlt(_localctx, 15);
				{
				setState(113);
				match(MAKE_ITEM2);
				_localctx.value = ActionHandlerType.MAKE_ITEM2;
				}
				break;
			case RIDE:
				enterOuterAlt(_localctx, 16);
				{
				setState(115);
				match(RIDE);
				_localctx.value = ActionHandlerType.RIDE;
				}
				break;
			case TELEPORT_BOOKMARK:
				enterOuterAlt(_localctx, 17);
				{
				setState(117);
				match(TELEPORT_BOOKMARK);
				_localctx.value = ActionHandlerType.TELEPORT_BOOKMARK;
				}
				break;
			case BOT_REPORT:
				enterOuterAlt(_localctx, 18);
				{
				setState(119);
				match(BOT_REPORT);
				_localctx.value = ActionHandlerType.BOT_REPORT;
				}
				break;
			case AIRSHIP_ACTION:
				enterOuterAlt(_localctx, 19);
				{
				setState(121);
				match(AIRSHIP_ACTION);
				_localctx.value = ActionHandlerType.AIRSHIP_ACTION;
				}
				break;
			case COUPLE_ACTION:
				enterOuterAlt(_localctx, 20);
				{
				setState(123);
				match(COUPLE_ACTION);
				_localctx.value = ActionHandlerType.COUPLE_ACTION;
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
		public TerminalNode DAGGER() { return getToken(UserBasicActionsParser.DAGGER, 0); }
		public TerminalNode BOW() { return getToken(UserBasicActionsParser.BOW, 0); }
		public TerminalNode CROSSBOW() { return getToken(UserBasicActionsParser.CROSSBOW, 0); }
		public TerminalNode RAPIER() { return getToken(UserBasicActionsParser.RAPIER, 0); }
		public TerminalNode GLOVES() { return getToken(UserBasicActionsParser.GLOVES, 0); }
		public TerminalNode STEEL() { return getToken(UserBasicActionsParser.STEEL, 0); }
		public TerminalNode LEATHER() { return getToken(UserBasicActionsParser.LEATHER, 0); }
		public TerminalNode ORIHARUKON() { return getToken(UserBasicActionsParser.ORIHARUKON, 0); }
		public TerminalNode NAME() { return getToken(UserBasicActionsParser.NAME, 0); }
		public TerminalNode NONE() { return getToken(UserBasicActionsParser.NONE, 0); }
		public TerminalNode ORC() { return getToken(UserBasicActionsParser.ORC, 0); }
		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterIdentifier_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			_la = _input.LA(1);
			if ( !(_la==T__8 || _la==NONE || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (ORC - 65)) | (1L << (BOW - 65)) | (1L << (DAGGER - 65)) | (1L << (RAPIER - 65)) | (1L << (CROSSBOW - 65)) | (1L << (GLOVES - 65)))) != 0) || ((((_la - 137)) & ~0x3f) == 0 && ((1L << (_la - 137)) & ((1L << (STEEL - 137)) | (1L << (LEATHER - 137)) | (1L << (ORIHARUKON - 137)) | (1L << (NAME - 137)))) != 0)) ) {
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
		public TerminalNode BOOLEAN() { return getToken(UserBasicActionsParser.BOOLEAN, 0); }
		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterBool_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129);
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
		public TerminalNode BOOLEAN() { return getToken(UserBasicActionsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(UserBasicActionsParser.INTEGER, 0); }
		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterByte_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
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
		public TerminalNode BOOLEAN() { return getToken(UserBasicActionsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(UserBasicActionsParser.INTEGER, 0); }
		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterInt_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
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
		public TerminalNode BOOLEAN() { return getToken(UserBasicActionsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(UserBasicActionsParser.INTEGER, 0); }
		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterLong_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
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
		public TerminalNode BOOLEAN() { return getToken(UserBasicActionsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(UserBasicActionsParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(UserBasicActionsParser.DOUBLE, 0); }
		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterDouble_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
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
		public TerminalNode BOOLEAN() { return getToken(UserBasicActionsParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(UserBasicActionsParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(UserBasicActionsParser.DOUBLE, 0); }
		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterString_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
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
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterName_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__5);
			setState(142);
			((Name_objectContext)_localctx).io = identifier_object();
			setState(143);
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
		public TerminalNode CATEGORY() { return getToken(UserBasicActionsParser.CATEGORY, 0); }
		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterCategory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(UserBasicActionsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(UserBasicActionsParser.SEMICOLON, i);
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
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterVector3D_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T__9);
			setState(149);
			((Vector3D_objectContext)_localctx).x = int_object();
			setState(150);
			match(SEMICOLON);
			setState(151);
			((Vector3D_objectContext)_localctx).y = int_object();
			setState(152);
			match(SEMICOLON);
			setState(153);
			((Vector3D_objectContext)_localctx).z = int_object();
			setState(154);
			match(T__10);
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
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterEmpty_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__9);
			setState(157);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(UserBasicActionsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(UserBasicActionsParser.SEMICOLON, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_identifier_list);
		 _localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				match(T__9);
				setState(161);
				((Identifier_listContext)_localctx).io = identifier_object();
				 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(163);
					match(SEMICOLON);
					setState(164);
					((Identifier_listContext)_localctx).io = identifier_object();
					 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
					}
					}
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(172);
				match(T__10);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(UserBasicActionsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(UserBasicActionsParser.SEMICOLON, i);
		}
		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterInt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_int_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(191);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(T__9);
				setState(178);
				((Int_listContext)_localctx).io = int_object();
				 _localctx.value.add(((Int_listContext)_localctx).io.value); 
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(180);
					match(SEMICOLON);
					setState(181);
					((Int_listContext)_localctx).io = int_object();
					_localctx.value.add(((Int_listContext)_localctx).io.value);
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(189);
				match(T__10);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(UserBasicActionsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(UserBasicActionsParser.SEMICOLON, i);
		}
		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterDouble_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_double_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(193);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(T__9);
				setState(195);
				((Double_listContext)_localctx).d = double_object();
				 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(197);
					match(SEMICOLON);
					setState(198);
					((Double_listContext)_localctx).d = double_object();
					 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
					}
					}
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(206);
				match(T__10);
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
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterBase_attribute_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(T__11);
			setState(211);
			match(T__3);
			setState(212);
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
		public TerminalNode SEMICOLON() { return getToken(UserBasicActionsParser.SEMICOLON, 0); }
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterAttack_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(T__9);
			setState(216);
			((Attack_attributeContext)_localctx).attribute = attribute();
			setState(217);
			match(SEMICOLON);
			setState(218);
			((Attack_attributeContext)_localctx).io = int_object();
			setState(219);
			match(T__10);
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
		public TerminalNode NONE() { return getToken(UserBasicActionsParser.NONE, 0); }
		public TerminalNode FIRE() { return getToken(UserBasicActionsParser.FIRE, 0); }
		public TerminalNode WATER() { return getToken(UserBasicActionsParser.WATER, 0); }
		public TerminalNode EARTH() { return getToken(UserBasicActionsParser.EARTH, 0); }
		public TerminalNode WIND() { return getToken(UserBasicActionsParser.WIND, 0); }
		public TerminalNode HOLY() { return getToken(UserBasicActionsParser.HOLY, 0); }
		public TerminalNode UNHOLY() { return getToken(UserBasicActionsParser.UNHOLY, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_attribute);
		try {
			setState(236);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				match(NONE);
				_localctx.value = AttributeType.NONE;
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 2);
				{
				setState(224);
				match(FIRE);
				_localctx.value = AttributeType.FIRE;
				}
				break;
			case WATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				match(WATER);
				_localctx.value = AttributeType.WATER;
				}
				break;
			case EARTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(228);
				match(EARTH);
				_localctx.value = AttributeType.EARTH;
				}
				break;
			case WIND:
				enterOuterAlt(_localctx, 5);
				{
				setState(230);
				match(WIND);
				_localctx.value = AttributeType.WIND;
				}
				break;
			case HOLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(232);
				match(HOLY);
				_localctx.value = AttributeType.HOLY;
				}
				break;
			case UNHOLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(234);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(UserBasicActionsParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(UserBasicActionsParser.SEMICOLON, i);
		}
		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).enterCategory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UserBasicActionsListener ) ((UserBasicActionsListener)listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_category_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(238);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(T__9);
				setState(240);
				((Category_listContext)_localctx).co = category_object();
				 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(242);
					match(SEMICOLON);
					setState(243);
					((Category_listContext)_localctx).co = category_object();
					 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
					}
					}
					setState(250);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(251);
				match(T__10);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00a9\u0102\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\6\2\64\n\2\r\2\16\2\65\3\3\3\3\3\3\5\3;\n\3\3\3\5\3>\n\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\5\6V\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u0080\n\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u00aa\n\23\f\23\16\23\u00ad\13"+
		"\23\3\23\3\23\5\23\u00b1\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\7\24\u00bb\n\24\f\24\16\24\u00be\13\24\3\24\3\24\5\24\u00c2\n\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u00cc\n\25\f\25\16\25\u00cf\13"+
		"\25\3\25\3\25\5\25\u00d3\n\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\5\30\u00ef\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\7\31\u00f9\n\31\f\31\16\31\u00fc\13\31\3\31\3\31\5\31\u0100\n\31"+
		"\3\31\2\2\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\2\5\16"+
		"\2\13\13..CCbbddiikkzz\u008b\u008b\u008f\u008f\u0092\u0092\u00a6\u00a6"+
		"\3\2$%\3\2$&\2\u010e\2\63\3\2\2\2\4\67\3\2\2\2\6A\3\2\2\2\bF\3\2\2\2\n"+
		"M\3\2\2\2\f\177\3\2\2\2\16\u0081\3\2\2\2\20\u0083\3\2\2\2\22\u0085\3\2"+
		"\2\2\24\u0087\3\2\2\2\26\u0089\3\2\2\2\30\u008b\3\2\2\2\32\u008d\3\2\2"+
		"\2\34\u008f\3\2\2\2\36\u0094\3\2\2\2 \u0096\3\2\2\2\"\u009e\3\2\2\2$\u00b0"+
		"\3\2\2\2&\u00c1\3\2\2\2(\u00d2\3\2\2\2*\u00d4\3\2\2\2,\u00d9\3\2\2\2."+
		"\u00ee\3\2\2\2\60\u00ff\3\2\2\2\62\64\5\4\3\2\63\62\3\2\2\2\64\65\3\2"+
		"\2\2\65\63\3\2\2\2\65\66\3\2\2\2\66\3\3\2\2\2\678\7\3\2\28:\5\6\4\29;"+
		"\5\b\5\2:9\3\2\2\2:;\3\2\2\2;=\3\2\2\2<>\5\n\6\2=<\3\2\2\2=>\3\2\2\2>"+
		"?\3\2\2\2?@\7\4\2\2@\5\3\2\2\2AB\7\5\2\2BC\7\6\2\2CD\5\24\13\2DE\b\4\1"+
		"\2E\7\3\2\2\2FG\7\7\2\2GH\7\6\2\2HI\7\b\2\2IJ\5\f\7\2JK\7\t\2\2KL\b\5"+
		"\1\2L\t\3\2\2\2MN\7\n\2\2NU\7\6\2\2OP\5\24\13\2PQ\b\6\1\2QV\3\2\2\2RS"+
		"\5\16\b\2ST\b\6\1\2TV\3\2\2\2UO\3\2\2\2UR\3\2\2\2V\13\3\2\2\2WX\7\17\2"+
		"\2X\u0080\b\7\1\2YZ\7\20\2\2Z\u0080\b\7\1\2[\\\7\21\2\2\\\u0080\b\7\1"+
		"\2]^\7\22\2\2^\u0080\b\7\1\2_`\7\23\2\2`\u0080\b\7\1\2ab\7\24\2\2b\u0080"+
		"\b\7\1\2cd\7\36\2\2d\u0080\b\7\1\2ef\7\25\2\2f\u0080\b\7\1\2gh\7\26\2"+
		"\2h\u0080\b\7\1\2ij\7\27\2\2j\u0080\b\7\1\2kl\7\30\2\2l\u0080\b\7\1\2"+
		"mn\7\35\2\2n\u0080\b\7\1\2op\7\31\2\2p\u0080\b\7\1\2qr\7\32\2\2r\u0080"+
		"\b\7\1\2st\7\33\2\2t\u0080\b\7\1\2uv\7\34\2\2v\u0080\b\7\1\2wx\7\37\2"+
		"\2x\u0080\b\7\1\2yz\7 \2\2z\u0080\b\7\1\2{|\7!\2\2|\u0080\b\7\1\2}~\7"+
		"\"\2\2~\u0080\b\7\1\2\177W\3\2\2\2\177Y\3\2\2\2\177[\3\2\2\2\177]\3\2"+
		"\2\2\177_\3\2\2\2\177a\3\2\2\2\177c\3\2\2\2\177e\3\2\2\2\177g\3\2\2\2"+
		"\177i\3\2\2\2\177k\3\2\2\2\177m\3\2\2\2\177o\3\2\2\2\177q\3\2\2\2\177"+
		"s\3\2\2\2\177u\3\2\2\2\177w\3\2\2\2\177y\3\2\2\2\177{\3\2\2\2\177}\3\2"+
		"\2\2\u0080\r\3\2\2\2\u0081\u0082\t\2\2\2\u0082\17\3\2\2\2\u0083\u0084"+
		"\7$\2\2\u0084\21\3\2\2\2\u0085\u0086\t\3\2\2\u0086\23\3\2\2\2\u0087\u0088"+
		"\t\3\2\2\u0088\25\3\2\2\2\u0089\u008a\t\3\2\2\u008a\27\3\2\2\2\u008b\u008c"+
		"\t\4\2\2\u008c\31\3\2\2\2\u008d\u008e\t\4\2\2\u008e\33\3\2\2\2\u008f\u0090"+
		"\7\b\2\2\u0090\u0091\5\16\b\2\u0091\u0092\7\t\2\2\u0092\u0093\b\17\1\2"+
		"\u0093\35\3\2\2\2\u0094\u0095\7#\2\2\u0095\37\3\2\2\2\u0096\u0097\7\f"+
		"\2\2\u0097\u0098\5\24\13\2\u0098\u0099\7-\2\2\u0099\u009a\5\24\13\2\u009a"+
		"\u009b\7-\2\2\u009b\u009c\5\24\13\2\u009c\u009d\7\r\2\2\u009d!\3\2\2\2"+
		"\u009e\u009f\7\f\2\2\u009f\u00a0\7\r\2\2\u00a0#\3\2\2\2\u00a1\u00b1\5"+
		"\"\22\2\u00a2\u00a3\7\f\2\2\u00a3\u00a4\5\16\b\2\u00a4\u00ab\b\23\1\2"+
		"\u00a5\u00a6\7-\2\2\u00a6\u00a7\5\16\b\2\u00a7\u00a8\b\23\1\2\u00a8\u00aa"+
		"\3\2\2\2\u00a9\u00a5\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab"+
		"\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7\r"+
		"\2\2\u00af\u00b1\3\2\2\2\u00b0\u00a1\3\2\2\2\u00b0\u00a2\3\2\2\2\u00b1"+
		"%\3\2\2\2\u00b2\u00c2\5\"\22\2\u00b3\u00b4\7\f\2\2\u00b4\u00b5\5\24\13"+
		"\2\u00b5\u00bc\b\24\1\2\u00b6\u00b7\7-\2\2\u00b7\u00b8\5\24\13\2\u00b8"+
		"\u00b9\b\24\1\2\u00b9\u00bb\3\2\2\2\u00ba\u00b6\3\2\2\2\u00bb\u00be\3"+
		"\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00bf\u00c0\7\r\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00b2\3\2"+
		"\2\2\u00c1\u00b3\3\2\2\2\u00c2\'\3\2\2\2\u00c3\u00d3\5\"\22\2\u00c4\u00c5"+
		"\7\f\2\2\u00c5\u00c6\5\30\r\2\u00c6\u00cd\b\25\1\2\u00c7\u00c8\7-\2\2"+
		"\u00c8\u00c9\5\30\r\2\u00c9\u00ca\b\25\1\2\u00ca\u00cc\3\2\2\2\u00cb\u00c7"+
		"\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce"+
		"\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d1\7\r\2\2\u00d1\u00d3\3\2"+
		"\2\2\u00d2\u00c3\3\2\2\2\u00d2\u00c4\3\2\2\2\u00d3)\3\2\2\2\u00d4\u00d5"+
		"\7\16\2\2\u00d5\u00d6\7\6\2\2\u00d6\u00d7\5,\27\2\u00d7\u00d8\b\26\1\2"+
		"\u00d8+\3\2\2\2\u00d9\u00da\7\f\2\2\u00da\u00db\5.\30\2\u00db\u00dc\7"+
		"-\2\2\u00dc\u00dd\5\24\13\2\u00dd\u00de\7\r\2\2\u00de\u00df\b\27\1\2\u00df"+
		"-\3\2\2\2\u00e0\u00e1\7.\2\2\u00e1\u00ef\b\30\1\2\u00e2\u00e3\7\'\2\2"+
		"\u00e3\u00ef\b\30\1\2\u00e4\u00e5\7(\2\2\u00e5\u00ef\b\30\1\2\u00e6\u00e7"+
		"\7)\2\2\u00e7\u00ef\b\30\1\2\u00e8\u00e9\7*\2\2\u00e9\u00ef\b\30\1\2\u00ea"+
		"\u00eb\7,\2\2\u00eb\u00ef\b\30\1\2\u00ec\u00ed\7+\2\2\u00ed\u00ef\b\30"+
		"\1\2\u00ee\u00e0\3\2\2\2\u00ee\u00e2\3\2\2\2\u00ee\u00e4\3\2\2\2\u00ee"+
		"\u00e6\3\2\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00ea\3\2\2\2\u00ee\u00ec\3\2"+
		"\2\2\u00ef/\3\2\2\2\u00f0\u0100\5\"\22\2\u00f1\u00f2\7\f\2\2\u00f2\u00f3"+
		"\5\36\20\2\u00f3\u00fa\b\31\1\2\u00f4\u00f5\7-\2\2\u00f5\u00f6\5\36\20"+
		"\2\u00f6\u00f7\b\31\1\2\u00f7\u00f9\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fd\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00fe\7\r\2\2\u00fe\u0100\3\2\2\2\u00ff"+
		"\u00f0\3\2\2\2\u00ff\u00f1\3\2\2\2\u0100\61\3\2\2\2\20\65:=U\177\u00ab"+
		"\u00b0\u00bc\u00c1\u00cd\u00d2\u00ee\u00fa\u00ff";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}