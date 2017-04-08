// Generated from org\l2junity\gameserver\data\txt\gen\Lang.g4 by ANTLR 4.7
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
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, CATEGORY=9, 
		BOOLEAN=10, INTEGER=11, DOUBLE=12, FIRE=13, WATER=14, EARTH=15, WIND=16, 
		UNHOLY=17, HOLY=18, SEMICOLON=19, NONE=20, FAIRY=21, ANIMAL=22, HUMANOID=23, 
		PLANT=24, UNDEAD=25, CONSTRUCT=26, BEAST=27, BUG=28, ELEMENTAL=29, DEMONIC=30, 
		GIANT=31, DRAGON=32, DIVINE=33, SUMMON=34, PET=35, HOLYTHING=36, DWARF=37, 
		MERCHANT=38, ELF=39, KAMAEL=40, ORC=41, SIEGE_WEAPON=42, FRIEND=43, MERCENARY=44, 
		CASTLE_GUARD=45, HUMAN=46, BOSS=47, ZZOLDAGU=48, WORLD_TRAP=49, MONRACE=50, 
		DARKELF=51, GUARD=52, TELEPORTER=53, WAREHOUSE_KEEPER=54, WARRIOR=55, 
		CITIZEN=56, TREASURE=57, FIELDBOSS=58, BLACKSMITH=59, GUILD_MASTER=60, 
		GUILD_COACH=61, PC_TRAP=62, XMASTREE=63, DOPPELGANGER=64, OWNTHING=65, 
		SIEGE_ATTACKER=66, MRKEEPER=67, COLLECTION=68, PACKAGE_KEEPER=69, SWORD=70, 
		BLUNT=71, BOW=72, POLE=73, DAGGER=74, DUAL=75, FIST=76, DUALFIST=77, FISHINGROD=78, 
		RAPIER=79, ANCIENTSWORD=80, CROSSBOW=81, FLAG=82, DUALDAGGER=83, ETC=84, 
		LIGHT=85, HEAVY=86, MAGIC=87, SIGIL=88, RHAND=89, LRHAND=90, LHAND=91, 
		CHEST=92, LEGS=93, FEET=94, HEAD=95, GLOVES=96, ONEPIECE=97, REAR=98, 
		LEAR=99, RFINGER=100, LFINGER=101, NECK=102, BACK=103, UNDERWEAR=104, 
		HAIR=105, HAIR2=106, HAIRALL=107, ALLDRESS=108, RBRACELET=109, LBRACELET=110, 
		WAIST=111, DECO1=112, STEEL=113, FINE_STEEL=114, WOOD=115, CLOTH=116, 
		LEATHER=117, BONE=118, BRONZE=119, ORIHARUKON=120, MITHRIL=121, DAMASCUS=122, 
		ADAMANTAITE=123, BLOOD_STEEL=124, PAPER=125, GOLD=126, LIQUID=127, FISH=128, 
		SILVER=129, CHRYSOLITE=130, CRYSTAL=131, HORN=132, SCALE_OF_DRAGON=133, 
		COTTON=134, DYESTUFF=135, COBWEB=136, RUNE_XP=137, RUNE_SP=138, RUNE_REMOVE_PENALTY=139, 
		NAME=140, WS=141, LINE_COMMENT=142, STAR_COMMENT=143;
	public static final int
		RULE_identifier_object = 0, RULE_bool_object = 1, RULE_byte_object = 2, 
		RULE_int_object = 3, RULE_long_object = 4, RULE_double_object = 5, RULE_string_object = 6, 
		RULE_name_object = 7, RULE_category_object = 8, RULE_vector3D_object = 9, 
		RULE_empty_list = 10, RULE_identifier_list = 11, RULE_int_list = 12, RULE_double_list = 13, 
		RULE_base_attribute_attack = 14, RULE_attack_attribute = 15, RULE_attribute = 16, 
		RULE_category_list = 17;
	public static final String[] ruleNames = {
		"identifier_object", "bool_object", "byte_object", "int_object", "long_object", 
		"double_object", "string_object", "name_object", "category_object", "vector3D_object", 
		"empty_list", "identifier_list", "int_list", "double_list", "base_attribute_attack", 
		"attack_attribute", "attribute", "category_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'slot_lhand'", "'con'", "'['", "']'", "'{'", "'}'", "'base_attribute_attack'", 
		"'='", null, null, null, null, "'fire'", "'water'", "'earth'", "'wind'", 
		"'unholy'", "'holy'", "';'", "'none'", "'fairy'", "'animal'", "'humanoid'", 
		"'plant'", "'undead'", "'construct'", "'beast'", "'bug'", "'elemental'", 
		"'demonic'", "'giant'", "'dragon'", "'divine'", "'summon'", "'pet'", "'holything'", 
		"'dwarf'", "'merchant'", "'elf'", "'kamael'", "'orc'", "'siege_weapon'", 
		"'friend'", "'mercenary'", "'castle_guard'", "'human'", "'boss'", "'zzoldagu'", 
		"'world_trap'", "'monrace'", "'darkelf'", "'guard'", "'teleporter'", "'warehouse_keeper'", 
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
		null, null, null, null, null, null, null, null, null, "CATEGORY", "BOOLEAN", 
		"INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", 
		"SEMICOLON", "NONE", "FAIRY", "ANIMAL", "HUMANOID", "PLANT", "UNDEAD", 
		"CONSTRUCT", "BEAST", "BUG", "ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", 
		"DIVINE", "SUMMON", "PET", "HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", 
		"ORC", "SIEGE_WEAPON", "FRIEND", "MERCENARY", "CASTLE_GUARD", "HUMAN", 
		"BOSS", "ZZOLDAGU", "WORLD_TRAP", "MONRACE", "DARKELF", "GUARD", "TELEPORTER", 
		"WAREHOUSE_KEEPER", "WARRIOR", "CITIZEN", "TREASURE", "FIELDBOSS", "BLACKSMITH", 
		"GUILD_MASTER", "GUILD_COACH", "PC_TRAP", "XMASTREE", "DOPPELGANGER", 
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
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Identifier_objectContext extends ParserRuleContext {
		public String value;
		public TerminalNode DAGGER() { return getToken(LangParser.DAGGER, 0); }
		public TerminalNode BOW() { return getToken(LangParser.BOW, 0); }
		public TerminalNode CROSSBOW() { return getToken(LangParser.CROSSBOW, 0); }
		public TerminalNode RAPIER() { return getToken(LangParser.RAPIER, 0); }
		public TerminalNode GLOVES() { return getToken(LangParser.GLOVES, 0); }
		public TerminalNode STEEL() { return getToken(LangParser.STEEL, 0); }
		public TerminalNode LEATHER() { return getToken(LangParser.LEATHER, 0); }
		public TerminalNode ORIHARUKON() { return getToken(LangParser.ORIHARUKON, 0); }
		public TerminalNode NAME() { return getToken(LangParser.NAME, 0); }
		public TerminalNode NONE() { return getToken(LangParser.NONE, 0); }
		public TerminalNode ORC() { return getToken(LangParser.ORC, 0); }
		public TerminalNode BOSS() { return getToken(LangParser.BOSS, 0); }
		public Identifier_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIdentifier_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIdentifier_object(this);
		}
	}

	public final Identifier_objectContext identifier_object() throws RecognitionException {
		Identifier_objectContext _localctx = new Identifier_objectContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_identifier_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << NONE) | (1L << ORC) | (1L << BOSS))) != 0) || ((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & ((1L << (BOW - 72)) | (1L << (DAGGER - 72)) | (1L << (RAPIER - 72)) | (1L << (CROSSBOW - 72)) | (1L << (GLOVES - 72)) | (1L << (STEEL - 72)) | (1L << (LEATHER - 72)) | (1L << (ORIHARUKON - 72)))) != 0) || _la==NAME) ) {
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
		public TerminalNode BOOLEAN() { return getToken(LangParser.BOOLEAN, 0); }
		public Bool_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBool_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBool_object(this);
		}
	}

	public final Bool_objectContext bool_object() throws RecognitionException {
		Bool_objectContext _localctx = new Bool_objectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bool_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
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
		public TerminalNode BOOLEAN() { return getToken(LangParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public Byte_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_byte_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterByte_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitByte_object(this);
		}
	}

	public final Byte_objectContext byte_object() throws RecognitionException {
		Byte_objectContext _localctx = new Byte_objectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_byte_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
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
		public TerminalNode BOOLEAN() { return getToken(LangParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public Int_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterInt_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitInt_object(this);
		}
	}

	public final Int_objectContext int_object() throws RecognitionException {
		Int_objectContext _localctx = new Int_objectContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_int_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
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
		public TerminalNode BOOLEAN() { return getToken(LangParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public Long_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_long_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterLong_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitLong_object(this);
		}
	}

	public final Long_objectContext long_object() throws RecognitionException {
		Long_objectContext _localctx = new Long_objectContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_long_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
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
		public TerminalNode BOOLEAN() { return getToken(LangParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(LangParser.DOUBLE, 0); }
		public Double_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDouble_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDouble_object(this);
		}
	}

	public final Double_objectContext double_object() throws RecognitionException {
		Double_objectContext _localctx = new Double_objectContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_double_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
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
		public TerminalNode BOOLEAN() { return getToken(LangParser.BOOLEAN, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public TerminalNode DOUBLE() { return getToken(LangParser.DOUBLE, 0); }
		public String_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterString_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitString_object(this);
		}
	}

	public final String_objectContext string_object() throws RecognitionException {
		String_objectContext _localctx = new String_objectContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_string_object);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
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
			if ( listener instanceof LangListener ) ((LangListener)listener).enterName_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitName_object(this);
		}
	}

	public final Name_objectContext name_object() throws RecognitionException {
		Name_objectContext _localctx = new Name_objectContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_name_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__2);
			setState(51);
			((Name_objectContext)_localctx).io = identifier_object();
			setState(52);
			match(T__3);
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
		public TerminalNode CATEGORY() { return getToken(LangParser.CATEGORY, 0); }
		public Category_objectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCategory_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCategory_object(this);
		}
	}

	public final Category_objectContext category_object() throws RecognitionException {
		Category_objectContext _localctx = new Category_objectContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_category_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(LangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LangParser.SEMICOLON, i);
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
			if ( listener instanceof LangListener ) ((LangListener)listener).enterVector3D_object(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitVector3D_object(this);
		}
	}

	public final Vector3D_objectContext vector3D_object() throws RecognitionException {
		Vector3D_objectContext _localctx = new Vector3D_objectContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_vector3D_object);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__4);
			setState(58);
			((Vector3D_objectContext)_localctx).x = int_object();
			setState(59);
			match(SEMICOLON);
			setState(60);
			((Vector3D_objectContext)_localctx).y = int_object();
			setState(61);
			match(SEMICOLON);
			setState(62);
			((Vector3D_objectContext)_localctx).z = int_object();
			setState(63);
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
			if ( listener instanceof LangListener ) ((LangListener)listener).enterEmpty_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitEmpty_list(this);
		}
	}

	public final Empty_listContext empty_list() throws RecognitionException {
		Empty_listContext _localctx = new Empty_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_empty_list);
		_localctx.value = new ArrayList<>();
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__4);
			setState(66);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(LangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LangParser.SEMICOLON, i);
		}
		public Identifier_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIdentifier_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIdentifier_list(this);
		}
	}

	public final Identifier_listContext identifier_list() throws RecognitionException {
		Identifier_listContext _localctx = new Identifier_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_identifier_list);
		 _localctx.value = new ArrayList<>();
		int _la;
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__4);
				setState(70);
				((Identifier_listContext)_localctx).io = identifier_object();
				 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(72);
					match(SEMICOLON);
					setState(73);
					((Identifier_listContext)_localctx).io = identifier_object();
					 _localctx.value.add(((Identifier_listContext)_localctx).io.value); 
					}
					}
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(81);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(LangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LangParser.SEMICOLON, i);
		}
		public Int_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterInt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitInt_list(this);
		}
	}

	public final Int_listContext int_list() throws RecognitionException {
		Int_listContext _localctx = new Int_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_int_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(85);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				match(T__4);
				setState(87);
				((Int_listContext)_localctx).io = int_object();
				 _localctx.value.add(((Int_listContext)_localctx).io.value); 
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(89);
					match(SEMICOLON);
					setState(90);
					((Int_listContext)_localctx).io = int_object();
					_localctx.value.add(((Int_listContext)_localctx).io.value);
					}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(98);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(LangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LangParser.SEMICOLON, i);
		}
		public Double_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDouble_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDouble_list(this);
		}
	}

	public final Double_listContext double_list() throws RecognitionException {
		Double_listContext _localctx = new Double_listContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_double_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(T__4);
				setState(104);
				((Double_listContext)_localctx).d = double_object();
				 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(106);
					match(SEMICOLON);
					setState(107);
					((Double_listContext)_localctx).d = double_object();
					 _localctx.value.add(Double.valueOf((((Double_listContext)_localctx).d!=null?_input.getText(((Double_listContext)_localctx).d.start,((Double_listContext)_localctx).d.stop):null))); 
					}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(115);
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
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBase_attribute_attack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBase_attribute_attack(this);
		}
	}

	public final Base_attribute_attackContext base_attribute_attack() throws RecognitionException {
		Base_attribute_attackContext _localctx = new Base_attribute_attackContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_base_attribute_attack);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__6);
			setState(120);
			match(T__7);
			setState(121);
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
		public TerminalNode SEMICOLON() { return getToken(LangParser.SEMICOLON, 0); }
		public Int_objectContext int_object() {
			return getRuleContext(Int_objectContext.class,0);
		}
		public Attack_attributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attack_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAttack_attribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAttack_attribute(this);
		}
	}

	public final Attack_attributeContext attack_attribute() throws RecognitionException {
		Attack_attributeContext _localctx = new Attack_attributeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_attack_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__4);
			setState(125);
			((Attack_attributeContext)_localctx).attribute = attribute();
			setState(126);
			match(SEMICOLON);
			setState(127);
			((Attack_attributeContext)_localctx).io = int_object();
			setState(128);
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
		public TerminalNode NONE() { return getToken(LangParser.NONE, 0); }
		public TerminalNode FIRE() { return getToken(LangParser.FIRE, 0); }
		public TerminalNode WATER() { return getToken(LangParser.WATER, 0); }
		public TerminalNode EARTH() { return getToken(LangParser.EARTH, 0); }
		public TerminalNode WIND() { return getToken(LangParser.WIND, 0); }
		public TerminalNode HOLY() { return getToken(LangParser.HOLY, 0); }
		public TerminalNode UNHOLY() { return getToken(LangParser.UNHOLY, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_attribute);
		try {
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONE:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				match(NONE);
				_localctx.value = AttributeType.NONE;
				}
				break;
			case FIRE:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(FIRE);
				_localctx.value = AttributeType.FIRE;
				}
				break;
			case WATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(135);
				match(WATER);
				_localctx.value = AttributeType.WATER;
				}
				break;
			case EARTH:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				match(EARTH);
				_localctx.value = AttributeType.EARTH;
				}
				break;
			case WIND:
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				match(WIND);
				_localctx.value = AttributeType.WIND;
				}
				break;
			case HOLY:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				match(HOLY);
				_localctx.value = AttributeType.HOLY;
				}
				break;
			case UNHOLY:
				enterOuterAlt(_localctx, 7);
				{
				setState(143);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(LangParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(LangParser.SEMICOLON, i);
		}
		public Category_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_category_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCategory_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCategory_list(this);
		}
	}

	public final Category_listContext category_list() throws RecognitionException {
		Category_listContext _localctx = new Category_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_category_list);
		 _localctx.value = new ArrayList<>(); 
		int _la;
		try {
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				empty_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(148);
				match(T__4);
				setState(149);
				((Category_listContext)_localctx).co = category_object();
				 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SEMICOLON) {
					{
					{
					setState(151);
					match(SEMICOLON);
					setState(152);
					((Category_listContext)_localctx).co = category_object();
					 _localctx.value.add((((Category_listContext)_localctx).co!=null?_input.getText(((Category_listContext)_localctx).co.start,((Category_listContext)_localctx).co.stop):null)); 
					}
					}
					setState(159);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(160);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0091\u00a7\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\rO\n\r\f\r\16\rR\13\r\3\r\3\r"+
		"\5\rV\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16`\n\16\f\16\16\16"+
		"c\13\16\3\16\3\16\5\16g\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7"+
		"\17q\n\17\f\17\16\17t\13\17\3\17\3\17\5\17x\n\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0094\n\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\7\23\u009e\n\23\f\23\16\23\u00a1\13\23\3\23"+
		"\3\23\5\23\u00a5\n\23\3\23\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$\2\5\17\2\3\4\26\26++\61\61JJLLQQSSbbsswwzz\u008e\u008e\3\2\f\r"+
		"\3\2\f\16\2\u00a2\2&\3\2\2\2\4(\3\2\2\2\6*\3\2\2\2\b,\3\2\2\2\n.\3\2\2"+
		"\2\f\60\3\2\2\2\16\62\3\2\2\2\20\64\3\2\2\2\229\3\2\2\2\24;\3\2\2\2\26"+
		"C\3\2\2\2\30U\3\2\2\2\32f\3\2\2\2\34w\3\2\2\2\36y\3\2\2\2 ~\3\2\2\2\""+
		"\u0093\3\2\2\2$\u00a4\3\2\2\2&\'\t\2\2\2\'\3\3\2\2\2()\7\f\2\2)\5\3\2"+
		"\2\2*+\t\3\2\2+\7\3\2\2\2,-\t\3\2\2-\t\3\2\2\2./\t\3\2\2/\13\3\2\2\2\60"+
		"\61\t\4\2\2\61\r\3\2\2\2\62\63\t\4\2\2\63\17\3\2\2\2\64\65\7\5\2\2\65"+
		"\66\5\2\2\2\66\67\7\6\2\2\678\b\t\1\28\21\3\2\2\29:\7\13\2\2:\23\3\2\2"+
		"\2;<\7\7\2\2<=\5\b\5\2=>\7\25\2\2>?\5\b\5\2?@\7\25\2\2@A\5\b\5\2AB\7\b"+
		"\2\2B\25\3\2\2\2CD\7\7\2\2DE\7\b\2\2E\27\3\2\2\2FV\5\26\f\2GH\7\7\2\2"+
		"HI\5\2\2\2IP\b\r\1\2JK\7\25\2\2KL\5\2\2\2LM\b\r\1\2MO\3\2\2\2NJ\3\2\2"+
		"\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7\b\2\2TV\3\2\2"+
		"\2UF\3\2\2\2UG\3\2\2\2V\31\3\2\2\2Wg\5\26\f\2XY\7\7\2\2YZ\5\b\5\2Za\b"+
		"\16\1\2[\\\7\25\2\2\\]\5\b\5\2]^\b\16\1\2^`\3\2\2\2_[\3\2\2\2`c\3\2\2"+
		"\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7\b\2\2eg\3\2\2\2fW\3\2\2"+
		"\2fX\3\2\2\2g\33\3\2\2\2hx\5\26\f\2ij\7\7\2\2jk\5\f\7\2kr\b\17\1\2lm\7"+
		"\25\2\2mn\5\f\7\2no\b\17\1\2oq\3\2\2\2pl\3\2\2\2qt\3\2\2\2rp\3\2\2\2r"+
		"s\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7\b\2\2vx\3\2\2\2wh\3\2\2\2wi\3\2\2\2"+
		"x\35\3\2\2\2yz\7\t\2\2z{\7\n\2\2{|\5 \21\2|}\b\20\1\2}\37\3\2\2\2~\177"+
		"\7\7\2\2\177\u0080\5\"\22\2\u0080\u0081\7\25\2\2\u0081\u0082\5\b\5\2\u0082"+
		"\u0083\7\b\2\2\u0083\u0084\b\21\1\2\u0084!\3\2\2\2\u0085\u0086\7\26\2"+
		"\2\u0086\u0094\b\22\1\2\u0087\u0088\7\17\2\2\u0088\u0094\b\22\1\2\u0089"+
		"\u008a\7\20\2\2\u008a\u0094\b\22\1\2\u008b\u008c\7\21\2\2\u008c\u0094"+
		"\b\22\1\2\u008d\u008e\7\22\2\2\u008e\u0094\b\22\1\2\u008f\u0090\7\24\2"+
		"\2\u0090\u0094\b\22\1\2\u0091\u0092\7\23\2\2\u0092\u0094\b\22\1\2\u0093"+
		"\u0085\3\2\2\2\u0093\u0087\3\2\2\2\u0093\u0089\3\2\2\2\u0093\u008b\3\2"+
		"\2\2\u0093\u008d\3\2\2\2\u0093\u008f\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"#\3\2\2\2\u0095\u00a5\5\26\f\2\u0096\u0097\7\7\2\2\u0097\u0098\5\22\n"+
		"\2\u0098\u009f\b\23\1\2\u0099\u009a\7\25\2\2\u009a\u009b\5\22\n\2\u009b"+
		"\u009c\b\23\1\2\u009c\u009e\3\2\2\2\u009d\u0099\3\2\2\2\u009e\u00a1\3"+
		"\2\2\2\u009f\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a2\u00a3\7\b\2\2\u00a3\u00a5\3\2\2\2\u00a4\u0095\3\2"+
		"\2\2\u00a4\u0096\3\2\2\2\u00a5%\3\2\2\2\13PUafrw\u0093\u009f\u00a4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}