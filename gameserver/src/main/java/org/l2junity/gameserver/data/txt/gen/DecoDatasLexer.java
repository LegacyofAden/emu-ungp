// Generated from org\l2junity\gameserver\data\txt\gen\DecoDatas.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.decodata.DecoCostData;
import org.l2junity.gameserver.data.txt.model.decodata.DecoFunctionType;


import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

import org.l2junity.gameserver.data.txt.model.constants.AttributeType;
import org.l2junity.gameserver.data.txt.model.item.AttributeAttack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DecoDatasLexer extends Lexer {
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
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "NONE", 
		"HP_REGEN", "MP_REGEN", "CP_REGEN", "XP_RESTORE", "TELEPORT", "BROADCAST", 
		"CURTAIN", "HANGING", "OUTERPLATFORM", "PLATFORM", "ITEM_CREATE", "BUFF", 
		"CATEGORY", "BOOLEAN", "INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", 
		"WIND", "UNHOLY", "HOLY", "DECIMAL", "MINUS", "BINARY_DIGIT", "DIGIT", 
		"NON_ZERO_DIGIT", "ZERO_DIGIT", "SEMICOLON", "FAIRY", "ANIMAL", "HUMANOID", 
		"PLANT", "UNDEAD", "CONSTRUCT", "BEAST", "BUG", "ELEMENTAL", "DEMONIC", 
		"GIANT", "DRAGON", "DIVINE", "SUMMON", "PET", "HOLYTHING", "DWARF", "MERCHANT", 
		"ELF", "KAMAEL", "ORC", "MERCENARY", "CASTLE_GUARD", "HUMAN", "BOSS", 
		"ZZOLDAGU", "WORLD_TRAP", "MONRACE", "DARKELF", "GUARD", "TELEPORTER", 
		"WAREHOUSE_KEEPER", "WARRIOR", "CITIZEN", "TREASURE", "FIELDBOSS", "BLACKSMITH", 
		"GUILD_MASTER", "GUILD_COACH", "SWORD", "BLUNT", "BOW", "POLE", "DAGGER", 
		"DUAL", "FIST", "DUALFIST", "FISHINGROD", "RAPIER", "ANCIENTSWORD", "CROSSBOW", 
		"FLAG", "OWNTHING", "DUALDAGGER", "ETC", "LIGHT", "HEAVY", "MAGIC", "SIGIL", 
		"RHAND", "LRHAND", "LHAND", "CHEST", "LEGS", "FEET", "HEAD", "GLOVES", 
		"ONEPIECE", "REAR", "LEAR", "RFINGER", "LFINGER", "NECK", "BACK", "UNDERWEAR", 
		"HAIR", "HAIR2", "HAIRALL", "ALLDRESS", "RBRACELET", "LBRACELET", "WAIST", 
		"DECO1", "STEEL", "FINE_STEEL", "WOOD", "CLOTH", "LEATHER", "BONE", "BRONZE", 
		"ORIHARUKON", "MITHRIL", "DAMASCUS", "ADAMANTAITE", "BLOOD_STEEL", "PAPER", 
		"GOLD", "LIQUID", "FISH", "SILVER", "CHRYSOLITE", "CRYSTAL", "HORN", "SCALE_OF_DRAGON", 
		"COTTON", "DYESTUFF", "COBWEB", "RUNE_XP", "RUNE_SP", "RUNE_REMOVE_PENALTY", 
		"NAME", "WS", "LINE_COMMENT", "STAR_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'deco_begin'", "'deco_end'", "'id'", "'='", "'name'", "'type'", 
		"'level'", "'depth'", "'func'", "'('", "')'", "'cost'", "'0'", "'1'", 
		"'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "'10'", "'11'", 
		"'12'", "'{'", "':'", "'}'", "'slot_lhand'", "'['", "']'", "'base_attribute_attack'", 
		"'none'", "'hp_regen'", "'mp_regen'", "'cp_regen'", "'xp_restore'", "'teleport'", 
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


	public DecoDatasLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DecoDatas.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\u00ac\u0629\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\t"+
		"T\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_"+
		"\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k"+
		"\tk\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv"+
		"\4w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t"+
		"\u0080\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084"+
		"\4\u0085\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089"+
		"\t\u0089\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d"+
		"\4\u008e\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092"+
		"\t\u0092\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096"+
		"\4\u0097\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b"+
		"\t\u009b\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f"+
		"\4\u00a0\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4"+
		"\t\u00a4\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8"+
		"\4\u00a9\t\u00a9\4\u00aa\t\u00aa\4\u00ab\t\u00ab\4\u00ac\t\u00ac\4\u00ad"+
		"\t\u00ad\4\u00ae\t\u00ae\4\u00af\t\u00af\4\u00b0\t\u00b0\4\u00b1\t\u00b1"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3)"+
		"\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3+"+
		"\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3-"+
		"\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3\60\3\60\3\61\5\61\u0265\n\61\3\61\3\61"+
		"\3\62\3\62\3\62\6\62\u026c\n\62\r\62\16\62\u026d\3\63\3\63\3\63\3\63\3"+
		"\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66\3"+
		"\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\3"+
		"9\39\39\79\u0295\n9\f9\169\u0298\139\59\u029a\n9\3:\3:\3;\3;\3<\3<\3="+
		"\3=\3>\3>\3?\3?\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3A\3B\3B\3B\3B\3B"+
		"\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3D\3D\3E\3E\3E\3E\3E\3E"+
		"\3E\3E\3E\3E\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3H\3H\3H\3H\3H\3H\3H\3H\3H"+
		"\3H\3I\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K\3L"+
		"\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3N\3N\3N\3N\3O\3O\3O\3O\3O\3O"+
		"\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R"+
		"\3S\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V"+
		"\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3Y"+
		"\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3["+
		"\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]\3]\3]\3]\3]\3^\3^\3"+
		"^\3^\3^\3^\3^\3^\3^\3^\3^\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3_\3"+
		"_\3_\3_\3`\3`\3`\3`\3`\3`\3`\3`\3a\3a\3a\3a\3a\3a\3a\3a\3b\3b\3b\3b\3"+
		"b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3c\3c\3c\3c\3c\3d\3d\3d\3d\3d\3d\3d\3d\3"+
		"d\3d\3d\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3f\3f\3f\3f\3f\3f\3f\3"+
		"f\3f\3f\3f\3f\3g\3g\3g\3g\3g\3g\3h\3h\3h\3h\3h\3h\3i\3i\3i\3i\3j\3j\3"+
		"j\3j\3j\3k\3k\3k\3k\3k\3k\3k\3l\3l\3l\3l\3l\3m\3m\3m\3m\3m\3n\3n\3n\3"+
		"n\3n\3n\3n\3n\3n\3o\3o\3o\3o\3o\3o\3o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3"+
		"p\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3q\3r\3r\3r\3r\3r\3r\3r\3r\3r\3"+
		"s\3s\3s\3s\3s\3t\3t\3t\3t\3t\3t\3t\3t\3t\3u\3u\3u\3u\3u\3u\3u\3u\3u\3"+
		"u\3u\3v\3v\3v\3v\3w\3w\3w\3w\3w\3w\3x\3x\3x\3x\3x\3x\3y\3y\3y\3y\3y\3"+
		"y\3z\3z\3z\3z\3z\3z\3{\3{\3{\3{\3{\3{\3|\3|\3|\3|\3|\3|\3|\3}\3}\3}\3"+
		"}\3}\3}\3~\3~\3~\3~\3~\3~\3\177\3\177\3\177\3\177\3\177\3\u0080\3\u0080"+
		"\3\u0080\3\u0080\3\u0080\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0082"+
		"\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0083\3\u0083\3\u0083"+
		"\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0084\3\u0084\3\u0084"+
		"\3\u0084\3\u0084\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0086\3\u0086"+
		"\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0087\3\u0087\3\u0087"+
		"\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088\3\u0088"+
		"\3\u0088\3\u0089\3\u0089\3\u0089\3\u0089\3\u0089\3\u008a\3\u008a\3\u008a"+
		"\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008b\3\u008b"+
		"\3\u008b\3\u008b\3\u008b\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c"+
		"\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008e"+
		"\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008f"+
		"\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f"+
		"\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090"+
		"\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0092\3\u0092"+
		"\3\u0092\3\u0092\3\u0092\3\u0092\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093"+
		"\3\u0093\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0096"+
		"\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0097\3\u0097\3\u0097\3\u0097"+
		"\3\u0097\3\u0097\3\u0097\3\u0097\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098"+
		"\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u009a\3\u009a"+
		"\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a"+
		"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009c"+
		"\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009d"+
		"\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\3\u009d\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e"+
		"\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009f\3\u009f\3\u009f\3\u009f"+
		"\3\u009f\3\u009f\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a1\3\u00a1"+
		"\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a2\3\u00a2\3\u00a2\3\u00a2"+
		"\3\u00a2\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a4"+
		"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4"+
		"\3\u00a4\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5"+
		"\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a7\3\u00a7\3\u00a7\3\u00a7"+
		"\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7"+
		"\3\u00a7\3\u00a7\3\u00a7\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8"+
		"\3\u00a8\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9"+
		"\3\u00a9\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00ab"+
		"\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ab\3\u00ac\3\u00ac"+
		"\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ad\3\u00ad\3\u00ad"+
		"\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad"+
		"\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ae"+
		"\6\u00ae\u05fb\n\u00ae\r\u00ae\16\u00ae\u05fc\3\u00ae\7\u00ae\u0600\n"+
		"\u00ae\f\u00ae\16\u00ae\u0603\13\u00ae\3\u00af\6\u00af\u0606\n\u00af\r"+
		"\u00af\16\u00af\u0607\3\u00af\3\u00af\3\u00b0\3\u00b0\3\u00b0\3\u00b0"+
		"\7\u00b0\u0610\n\u00b0\f\u00b0\16\u00b0\u0613\13\u00b0\3\u00b0\5\u00b0"+
		"\u0616\n\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b1\3\u00b1\3\u00b1"+
		"\3\u00b1\7\u00b1\u0620\n\u00b1\f\u00b1\16\u00b1\u0623\13\u00b1\3\u00b1"+
		"\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u0621\2\u00b2\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q\2s\2u\2w\2y\2{\2}:"+
		"\177;\u0081<\u0083=\u0085>\u0087?\u0089@\u008bA\u008dB\u008fC\u0091D\u0093"+
		"E\u0095F\u0097G\u0099H\u009bI\u009dJ\u009fK\u00a1L\u00a3M\u00a5N\u00a7"+
		"O\u00a9P\u00abQ\u00adR\u00afS\u00b1T\u00b3U\u00b5V\u00b7W\u00b9X\u00bb"+
		"Y\u00bdZ\u00bf[\u00c1\\\u00c3]\u00c5^\u00c7_\u00c9`\u00cba\u00cdb\u00cf"+
		"c\u00d1d\u00d3e\u00d5f\u00d7g\u00d9h\u00dbi\u00ddj\u00dfk\u00e1l\u00e3"+
		"m\u00e5n\u00e7o\u00e9p\u00ebq\u00edr\u00efs\u00f1t\u00f3u\u00f5v\u00f7"+
		"w\u00f9x\u00fby\u00fdz\u00ff{\u0101|\u0103}\u0105~\u0107\177\u0109\u0080"+
		"\u010b\u0081\u010d\u0082\u010f\u0083\u0111\u0084\u0113\u0085\u0115\u0086"+
		"\u0117\u0087\u0119\u0088\u011b\u0089\u011d\u008a\u011f\u008b\u0121\u008c"+
		"\u0123\u008d\u0125\u008e\u0127\u008f\u0129\u0090\u012b\u0091\u012d\u0092"+
		"\u012f\u0093\u0131\u0094\u0133\u0095\u0135\u0096\u0137\u0097\u0139\u0098"+
		"\u013b\u0099\u013d\u009a\u013f\u009b\u0141\u009c\u0143\u009d\u0145\u009e"+
		"\u0147\u009f\u0149\u00a0\u014b\u00a1\u014d\u00a2\u014f\u00a3\u0151\u00a4"+
		"\u0153\u00a5\u0155\u00a6\u0157\u00a7\u0159\u00a8\u015b\u00a9\u015d\u00aa"+
		"\u015f\u00ab\u0161\u00ac\3\2\n\3\2\62\63\3\2\62;\3\2\63;\3\2\62\62\6\2"+
		"\62;C\\aac|\n\2)),,/\60\62<C\\aac|\u0080\u0080\5\2\13\f\16\17\"\"\4\2"+
		"\f\f\17\17\2\u062c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k"+
		"\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2"+
		"\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2"+
		"\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d"+
		"\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2"+
		"\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af"+
		"\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2"+
		"\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1"+
		"\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2"+
		"\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3"+
		"\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2"+
		"\2\2\u00dd\3\2\2\2\2\u00df\3\2\2\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5"+
		"\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9\3\2\2\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2"+
		"\2\2\u00ef\3\2\2\2\2\u00f1\3\2\2\2\2\u00f3\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7"+
		"\3\2\2\2\2\u00f9\3\2\2\2\2\u00fb\3\2\2\2\2\u00fd\3\2\2\2\2\u00ff\3\2\2"+
		"\2\2\u0101\3\2\2\2\2\u0103\3\2\2\2\2\u0105\3\2\2\2\2\u0107\3\2\2\2\2\u0109"+
		"\3\2\2\2\2\u010b\3\2\2\2\2\u010d\3\2\2\2\2\u010f\3\2\2\2\2\u0111\3\2\2"+
		"\2\2\u0113\3\2\2\2\2\u0115\3\2\2\2\2\u0117\3\2\2\2\2\u0119\3\2\2\2\2\u011b"+
		"\3\2\2\2\2\u011d\3\2\2\2\2\u011f\3\2\2\2\2\u0121\3\2\2\2\2\u0123\3\2\2"+
		"\2\2\u0125\3\2\2\2\2\u0127\3\2\2\2\2\u0129\3\2\2\2\2\u012b\3\2\2\2\2\u012d"+
		"\3\2\2\2\2\u012f\3\2\2\2\2\u0131\3\2\2\2\2\u0133\3\2\2\2\2\u0135\3\2\2"+
		"\2\2\u0137\3\2\2\2\2\u0139\3\2\2\2\2\u013b\3\2\2\2\2\u013d\3\2\2\2\2\u013f"+
		"\3\2\2\2\2\u0141\3\2\2\2\2\u0143\3\2\2\2\2\u0145\3\2\2\2\2\u0147\3\2\2"+
		"\2\2\u0149\3\2\2\2\2\u014b\3\2\2\2\2\u014d\3\2\2\2\2\u014f\3\2\2\2\2\u0151"+
		"\3\2\2\2\2\u0153\3\2\2\2\2\u0155\3\2\2\2\2\u0157\3\2\2\2\2\u0159\3\2\2"+
		"\2\2\u015b\3\2\2\2\2\u015d\3\2\2\2\2\u015f\3\2\2\2\2\u0161\3\2\2\2\3\u0163"+
		"\3\2\2\2\5\u016e\3\2\2\2\7\u0177\3\2\2\2\t\u017a\3\2\2\2\13\u017c\3\2"+
		"\2\2\r\u0181\3\2\2\2\17\u0186\3\2\2\2\21\u018c\3\2\2\2\23\u0192\3\2\2"+
		"\2\25\u0197\3\2\2\2\27\u0199\3\2\2\2\31\u019b\3\2\2\2\33\u01a0\3\2\2\2"+
		"\35\u01a2\3\2\2\2\37\u01a4\3\2\2\2!\u01a6\3\2\2\2#\u01a8\3\2\2\2%\u01aa"+
		"\3\2\2\2\'\u01ac\3\2\2\2)\u01ae\3\2\2\2+\u01b0\3\2\2\2-\u01b2\3\2\2\2"+
		"/\u01b4\3\2\2\2\61\u01b7\3\2\2\2\63\u01ba\3\2\2\2\65\u01bd\3\2\2\2\67"+
		"\u01bf\3\2\2\29\u01c1\3\2\2\2;\u01c3\3\2\2\2=\u01ce\3\2\2\2?\u01d0\3\2"+
		"\2\2A\u01d2\3\2\2\2C\u01e8\3\2\2\2E\u01ed\3\2\2\2G\u01f6\3\2\2\2I\u01ff"+
		"\3\2\2\2K\u0208\3\2\2\2M\u0213\3\2\2\2O\u021c\3\2\2\2Q\u0226\3\2\2\2S"+
		"\u022e\3\2\2\2U\u0236\3\2\2\2W\u0244\3\2\2\2Y\u024d\3\2\2\2[\u0259\3\2"+
		"\2\2]\u025e\3\2\2\2_\u0261\3\2\2\2a\u0264\3\2\2\2c\u0268\3\2\2\2e\u026f"+
		"\3\2\2\2g\u0274\3\2\2\2i\u027a\3\2\2\2k\u0280\3\2\2\2m\u0285\3\2\2\2o"+
		"\u028c\3\2\2\2q\u0299\3\2\2\2s\u029b\3\2\2\2u\u029d\3\2\2\2w\u029f\3\2"+
		"\2\2y\u02a1\3\2\2\2{\u02a3\3\2\2\2}\u02a5\3\2\2\2\177\u02a7\3\2\2\2\u0081"+
		"\u02ad\3\2\2\2\u0083\u02b4\3\2\2\2\u0085\u02bd\3\2\2\2\u0087\u02c3\3\2"+
		"\2\2\u0089\u02ca\3\2\2\2\u008b\u02d4\3\2\2\2\u008d\u02da\3\2\2\2\u008f"+
		"\u02de\3\2\2\2\u0091\u02e8\3\2\2\2\u0093\u02f0\3\2\2\2\u0095\u02f6\3\2"+
		"\2\2\u0097\u02fd\3\2\2\2\u0099\u0304\3\2\2\2\u009b\u030b\3\2\2\2\u009d"+
		"\u030f\3\2\2\2\u009f\u0319\3\2\2\2\u00a1\u031f\3\2\2\2\u00a3\u0328\3\2"+
		"\2\2\u00a5\u032c\3\2\2\2\u00a7\u0333\3\2\2\2\u00a9\u0337\3\2\2\2\u00ab"+
		"\u0341\3\2\2\2\u00ad\u034e\3\2\2\2\u00af\u0354\3\2\2\2\u00b1\u0359\3\2"+
		"\2\2\u00b3\u0362\3\2\2\2\u00b5\u036d\3\2\2\2\u00b7\u0375\3\2\2\2\u00b9"+
		"\u037d\3\2\2\2\u00bb\u0383\3\2\2\2\u00bd\u038e\3\2\2\2\u00bf\u039f\3\2"+
		"\2\2\u00c1\u03a7\3\2\2\2\u00c3\u03af\3\2\2\2\u00c5\u03b8\3\2\2\2\u00c7"+
		"\u03c2\3\2\2\2\u00c9\u03cd\3\2\2\2\u00cb\u03da\3\2\2\2\u00cd\u03e6\3\2"+
		"\2\2\u00cf\u03ec\3\2\2\2\u00d1\u03f2\3\2\2\2\u00d3\u03f6\3\2\2\2\u00d5"+
		"\u03fb\3\2\2\2\u00d7\u0402\3\2\2\2\u00d9\u0407\3\2\2\2\u00db\u040c\3\2"+
		"\2\2\u00dd\u0415\3\2\2\2\u00df\u0420\3\2\2\2\u00e1\u0427\3\2\2\2\u00e3"+
		"\u0434\3\2\2\2\u00e5\u043d\3\2\2\2\u00e7\u0442\3\2\2\2\u00e9\u044b\3\2"+
		"\2\2\u00eb\u0456\3\2\2\2\u00ed\u045a\3\2\2\2\u00ef\u0460\3\2\2\2\u00f1"+
		"\u0466\3\2\2\2\u00f3\u046c\3\2\2\2\u00f5\u0472\3\2\2\2\u00f7\u0478\3\2"+
		"\2\2\u00f9\u047f\3\2\2\2\u00fb\u0485\3\2\2\2\u00fd\u048b\3\2\2\2\u00ff"+
		"\u0490\3\2\2\2\u0101\u0495\3\2\2\2\u0103\u049a\3\2\2\2\u0105\u04a1\3\2"+
		"\2\2\u0107\u04aa\3\2\2\2\u0109\u04af\3\2\2\2\u010b\u04b4\3\2\2\2\u010d"+
		"\u04bc\3\2\2\2\u010f\u04c4\3\2\2\2\u0111\u04c9\3\2\2\2\u0113\u04ce\3\2"+
		"\2\2\u0115\u04d8\3\2\2\2\u0117\u04dd\3\2\2\2\u0119\u04e3\3\2\2\2\u011b"+
		"\u04eb\3\2\2\2\u011d\u04f4\3\2\2\2\u011f\u04fe\3\2\2\2\u0121\u0508\3\2"+
		"\2\2\u0123\u050e\3\2\2\2\u0125\u0514\3\2\2\2\u0127\u051a\3\2\2\2\u0129"+
		"\u0525\3\2\2\2\u012b\u052a\3\2\2\2\u012d\u0530\3\2\2\2\u012f\u0538\3\2"+
		"\2\2\u0131\u053d\3\2\2\2\u0133\u0544\3\2\2\2\u0135\u054f\3\2\2\2\u0137"+
		"\u0557\3\2\2\2\u0139\u0560\3\2\2\2\u013b\u056c\3\2\2\2\u013d\u0578\3\2"+
		"\2\2\u013f\u057e\3\2\2\2\u0141\u0583\3\2\2\2\u0143\u058a\3\2\2\2\u0145"+
		"\u058f\3\2\2\2\u0147\u0596\3\2\2\2\u0149\u05a1\3\2\2\2\u014b\u05a9\3\2"+
		"\2\2\u014d\u05ae\3\2\2\2\u014f\u05be\3\2\2\2\u0151\u05c5\3\2\2\2\u0153"+
		"\u05ce\3\2\2\2\u0155\u05d5\3\2\2\2\u0157\u05dd\3\2\2\2\u0159\u05e5\3\2"+
		"\2\2\u015b\u05fa\3\2\2\2\u015d\u0605\3\2\2\2\u015f\u060b\3\2\2\2\u0161"+
		"\u061b\3\2\2\2\u0163\u0164\7f\2\2\u0164\u0165\7g\2\2\u0165\u0166\7e\2"+
		"\2\u0166\u0167\7q\2\2\u0167\u0168\7a\2\2\u0168\u0169\7d\2\2\u0169\u016a"+
		"\7g\2\2\u016a\u016b\7i\2\2\u016b\u016c\7k\2\2\u016c\u016d\7p\2\2\u016d"+
		"\4\3\2\2\2\u016e\u016f\7f\2\2\u016f\u0170\7g\2\2\u0170\u0171\7e\2\2\u0171"+
		"\u0172\7q\2\2\u0172\u0173\7a\2\2\u0173\u0174\7g\2\2\u0174\u0175\7p\2\2"+
		"\u0175\u0176\7f\2\2\u0176\6\3\2\2\2\u0177\u0178\7k\2\2\u0178\u0179\7f"+
		"\2\2\u0179\b\3\2\2\2\u017a\u017b\7?\2\2\u017b\n\3\2\2\2\u017c\u017d\7"+
		"p\2\2\u017d\u017e\7c\2\2\u017e\u017f\7o\2\2\u017f\u0180\7g\2\2\u0180\f"+
		"\3\2\2\2\u0181\u0182\7v\2\2\u0182\u0183\7{\2\2\u0183\u0184\7r\2\2\u0184"+
		"\u0185\7g\2\2\u0185\16\3\2\2\2\u0186\u0187\7n\2\2\u0187\u0188\7g\2\2\u0188"+
		"\u0189\7x\2\2\u0189\u018a\7g\2\2\u018a\u018b\7n\2\2\u018b\20\3\2\2\2\u018c"+
		"\u018d\7f\2\2\u018d\u018e\7g\2\2\u018e\u018f\7r\2\2\u018f\u0190\7v\2\2"+
		"\u0190\u0191\7j\2\2\u0191\22\3\2\2\2\u0192\u0193\7h\2\2\u0193\u0194\7"+
		"w\2\2\u0194\u0195\7p\2\2\u0195\u0196\7e\2\2\u0196\24\3\2\2\2\u0197\u0198"+
		"\7*\2\2\u0198\26\3\2\2\2\u0199\u019a\7+\2\2\u019a\30\3\2\2\2\u019b\u019c"+
		"\7e\2\2\u019c\u019d\7q\2\2\u019d\u019e\7u\2\2\u019e\u019f\7v\2\2\u019f"+
		"\32\3\2\2\2\u01a0\u01a1\7\62\2\2\u01a1\34\3\2\2\2\u01a2\u01a3\7\63\2\2"+
		"\u01a3\36\3\2\2\2\u01a4\u01a5\7\64\2\2\u01a5 \3\2\2\2\u01a6\u01a7\7\65"+
		"\2\2\u01a7\"\3\2\2\2\u01a8\u01a9\7\66\2\2\u01a9$\3\2\2\2\u01aa\u01ab\7"+
		"\67\2\2\u01ab&\3\2\2\2\u01ac\u01ad\78\2\2\u01ad(\3\2\2\2\u01ae\u01af\7"+
		"9\2\2\u01af*\3\2\2\2\u01b0\u01b1\7:\2\2\u01b1,\3\2\2\2\u01b2\u01b3\7;"+
		"\2\2\u01b3.\3\2\2\2\u01b4\u01b5\7\63\2\2\u01b5\u01b6\7\62\2\2\u01b6\60"+
		"\3\2\2\2\u01b7\u01b8\7\63\2\2\u01b8\u01b9\7\63\2\2\u01b9\62\3\2\2\2\u01ba"+
		"\u01bb\7\63\2\2\u01bb\u01bc\7\64\2\2\u01bc\64\3\2\2\2\u01bd\u01be\7}\2"+
		"\2\u01be\66\3\2\2\2\u01bf\u01c0\7<\2\2\u01c08\3\2\2\2\u01c1\u01c2\7\177"+
		"\2\2\u01c2:\3\2\2\2\u01c3\u01c4\7u\2\2\u01c4\u01c5\7n\2\2\u01c5\u01c6"+
		"\7q\2\2\u01c6\u01c7\7v\2\2\u01c7\u01c8\7a\2\2\u01c8\u01c9\7n\2\2\u01c9"+
		"\u01ca\7j\2\2\u01ca\u01cb\7c\2\2\u01cb\u01cc\7p\2\2\u01cc\u01cd\7f\2\2"+
		"\u01cd<\3\2\2\2\u01ce\u01cf\7]\2\2\u01cf>\3\2\2\2\u01d0\u01d1\7_\2\2\u01d1"+
		"@\3\2\2\2\u01d2\u01d3\7d\2\2\u01d3\u01d4\7c\2\2\u01d4\u01d5\7u\2\2\u01d5"+
		"\u01d6\7g\2\2\u01d6\u01d7\7a\2\2\u01d7\u01d8\7c\2\2\u01d8\u01d9\7v\2\2"+
		"\u01d9\u01da\7v\2\2\u01da\u01db\7t\2\2\u01db\u01dc\7k\2\2\u01dc\u01dd"+
		"\7d\2\2\u01dd\u01de\7w\2\2\u01de\u01df\7v\2\2\u01df\u01e0\7g\2\2\u01e0"+
		"\u01e1\7a\2\2\u01e1\u01e2\7c\2\2\u01e2\u01e3\7v\2\2\u01e3\u01e4\7v\2\2"+
		"\u01e4\u01e5\7c\2\2\u01e5\u01e6\7e\2\2\u01e6\u01e7\7m\2\2\u01e7B\3\2\2"+
		"\2\u01e8\u01e9\7p\2\2\u01e9\u01ea\7q\2\2\u01ea\u01eb\7p\2\2\u01eb\u01ec"+
		"\7g\2\2\u01ecD\3\2\2\2\u01ed\u01ee\7j\2\2\u01ee\u01ef\7r\2\2\u01ef\u01f0"+
		"\7a\2\2\u01f0\u01f1\7t\2\2\u01f1\u01f2\7g\2\2\u01f2\u01f3\7i\2\2\u01f3"+
		"\u01f4\7g\2\2\u01f4\u01f5\7p\2\2\u01f5F\3\2\2\2\u01f6\u01f7\7o\2\2\u01f7"+
		"\u01f8\7r\2\2\u01f8\u01f9\7a\2\2\u01f9\u01fa\7t\2\2\u01fa\u01fb\7g\2\2"+
		"\u01fb\u01fc\7i\2\2\u01fc\u01fd\7g\2\2\u01fd\u01fe\7p\2\2\u01feH\3\2\2"+
		"\2\u01ff\u0200\7e\2\2\u0200\u0201\7r\2\2\u0201\u0202\7a\2\2\u0202\u0203"+
		"\7t\2\2\u0203\u0204\7g\2\2\u0204\u0205\7i\2\2\u0205\u0206\7g\2\2\u0206"+
		"\u0207\7p\2\2\u0207J\3\2\2\2\u0208\u0209\7z\2\2\u0209\u020a\7r\2\2\u020a"+
		"\u020b\7a\2\2\u020b\u020c\7t\2\2\u020c\u020d\7g\2\2\u020d\u020e\7u\2\2"+
		"\u020e\u020f\7v\2\2\u020f\u0210\7q\2\2\u0210\u0211\7t\2\2\u0211\u0212"+
		"\7g\2\2\u0212L\3\2\2\2\u0213\u0214\7v\2\2\u0214\u0215\7g\2\2\u0215\u0216"+
		"\7n\2\2\u0216\u0217\7g\2\2\u0217\u0218\7r\2\2\u0218\u0219\7q\2\2\u0219"+
		"\u021a\7t\2\2\u021a\u021b\7v\2\2\u021bN\3\2\2\2\u021c\u021d\7d\2\2\u021d"+
		"\u021e\7t\2\2\u021e\u021f\7q\2\2\u021f\u0220\7c\2\2\u0220\u0221\7f\2\2"+
		"\u0221\u0222\7e\2\2\u0222\u0223\7c\2\2\u0223\u0224\7u\2\2\u0224\u0225"+
		"\7v\2\2\u0225P\3\2\2\2\u0226\u0227\7e\2\2\u0227\u0228\7w\2\2\u0228\u0229"+
		"\7t\2\2\u0229\u022a\7v\2\2\u022a\u022b\7c\2\2\u022b\u022c\7k\2\2\u022c"+
		"\u022d\7p\2\2\u022dR\3\2\2\2\u022e\u022f\7j\2\2\u022f\u0230\7c\2\2\u0230"+
		"\u0231\7p\2\2\u0231\u0232\7i\2\2\u0232\u0233\7k\2\2\u0233\u0234\7p\2\2"+
		"\u0234\u0235\7i\2\2\u0235T\3\2\2\2\u0236\u0237\7q\2\2\u0237\u0238\7w\2"+
		"\2\u0238\u0239\7v\2\2\u0239\u023a\7g\2\2\u023a\u023b\7t\2\2\u023b\u023c"+
		"\7r\2\2\u023c\u023d\7n\2\2\u023d\u023e\7c\2\2\u023e\u023f\7v\2\2\u023f"+
		"\u0240\7h\2\2\u0240\u0241\7q\2\2\u0241\u0242\7t\2\2\u0242\u0243\7o\2\2"+
		"\u0243V\3\2\2\2\u0244\u0245\7r\2\2\u0245\u0246\7n\2\2\u0246\u0247\7c\2"+
		"\2\u0247\u0248\7v\2\2\u0248\u0249\7h\2\2\u0249\u024a\7q\2\2\u024a\u024b"+
		"\7t\2\2\u024b\u024c\7o\2\2\u024cX\3\2\2\2\u024d\u024e\7k\2\2\u024e\u024f"+
		"\7v\2\2\u024f\u0250\7g\2\2\u0250\u0251\7o\2\2\u0251\u0252\7a\2\2\u0252"+
		"\u0253\7e\2\2\u0253\u0254\7t\2\2\u0254\u0255\7g\2\2\u0255\u0256\7c\2\2"+
		"\u0256\u0257\7v\2\2\u0257\u0258\7g\2\2\u0258Z\3\2\2\2\u0259\u025a\7d\2"+
		"\2\u025a\u025b\7w\2\2\u025b\u025c\7h\2\2\u025c\u025d\7h\2\2\u025d\\\3"+
		"\2\2\2\u025e\u025f\7B\2\2\u025f\u0260\5\u015b\u00ae\2\u0260^\3\2\2\2\u0261"+
		"\u0262\5u;\2\u0262`\3\2\2\2\u0263\u0265\5s:\2\u0264\u0263\3\2\2\2\u0264"+
		"\u0265\3\2\2\2\u0265\u0266\3\2\2\2\u0266\u0267\5q9\2\u0267b\3\2\2\2\u0268"+
		"\u0269\5a\61\2\u0269\u026b\7\60\2\2\u026a\u026c\5w<\2\u026b\u026a\3\2"+
		"\2\2\u026c\u026d\3\2\2\2\u026d\u026b\3\2\2\2\u026d\u026e\3\2\2\2\u026e"+
		"d\3\2\2\2\u026f\u0270\7h\2\2\u0270\u0271\7k\2\2\u0271\u0272\7t\2\2\u0272"+
		"\u0273\7g\2\2\u0273f\3\2\2\2\u0274\u0275\7y\2\2\u0275\u0276\7c\2\2\u0276"+
		"\u0277\7v\2\2\u0277\u0278\7g\2\2\u0278\u0279\7t\2\2\u0279h\3\2\2\2\u027a"+
		"\u027b\7g\2\2\u027b\u027c\7c\2\2\u027c\u027d\7t\2\2\u027d\u027e\7v\2\2"+
		"\u027e\u027f\7j\2\2\u027fj\3\2\2\2\u0280\u0281\7y\2\2\u0281\u0282\7k\2"+
		"\2\u0282\u0283\7p\2\2\u0283\u0284\7f\2\2\u0284l\3\2\2\2\u0285\u0286\7"+
		"w\2\2\u0286\u0287\7p\2\2\u0287\u0288\7j\2\2\u0288\u0289\7q\2\2\u0289\u028a"+
		"\7n\2\2\u028a\u028b\7{\2\2\u028bn\3\2\2\2\u028c\u028d\7j\2\2\u028d\u028e"+
		"\7q\2\2\u028e\u028f\7n\2\2\u028f\u0290\7{\2\2\u0290p\3\2\2\2\u0291\u029a"+
		"\5{>\2\u0292\u0296\5y=\2\u0293\u0295\5w<\2\u0294\u0293\3\2\2\2\u0295\u0298"+
		"\3\2\2\2\u0296\u0294\3\2\2\2\u0296\u0297\3\2\2\2\u0297\u029a\3\2\2\2\u0298"+
		"\u0296\3\2\2\2\u0299\u0291\3\2\2\2\u0299\u0292\3\2\2\2\u029ar\3\2\2\2"+
		"\u029b\u029c\7/\2\2\u029ct\3\2\2\2\u029d\u029e\t\2\2\2\u029ev\3\2\2\2"+
		"\u029f\u02a0\t\3\2\2\u02a0x\3\2\2\2\u02a1\u02a2\t\4\2\2\u02a2z\3\2\2\2"+
		"\u02a3\u02a4\t\5\2\2\u02a4|\3\2\2\2\u02a5\u02a6\7=\2\2\u02a6~\3\2\2\2"+
		"\u02a7\u02a8\7h\2\2\u02a8\u02a9\7c\2\2\u02a9\u02aa\7k\2\2\u02aa\u02ab"+
		"\7t\2\2\u02ab\u02ac\7{\2\2\u02ac\u0080\3\2\2\2\u02ad\u02ae\7c\2\2\u02ae"+
		"\u02af\7p\2\2\u02af\u02b0\7k\2\2\u02b0\u02b1\7o\2\2\u02b1\u02b2\7c\2\2"+
		"\u02b2\u02b3\7n\2\2\u02b3\u0082\3\2\2\2\u02b4\u02b5\7j\2\2\u02b5\u02b6"+
		"\7w\2\2\u02b6\u02b7\7o\2\2\u02b7\u02b8\7c\2\2\u02b8\u02b9\7p\2\2\u02b9"+
		"\u02ba\7q\2\2\u02ba\u02bb\7k\2\2\u02bb\u02bc\7f\2\2\u02bc\u0084\3\2\2"+
		"\2\u02bd\u02be\7r\2\2\u02be\u02bf\7n\2\2\u02bf\u02c0\7c\2\2\u02c0\u02c1"+
		"\7p\2\2\u02c1\u02c2\7v\2\2\u02c2\u0086\3\2\2\2\u02c3\u02c4\7w\2\2\u02c4"+
		"\u02c5\7p\2\2\u02c5\u02c6\7f\2\2\u02c6\u02c7\7g\2\2\u02c7\u02c8\7c\2\2"+
		"\u02c8\u02c9\7f\2\2\u02c9\u0088\3\2\2\2\u02ca\u02cb\7e\2\2\u02cb\u02cc"+
		"\7q\2\2\u02cc\u02cd\7p\2\2\u02cd\u02ce\7u\2\2\u02ce\u02cf\7v\2\2\u02cf"+
		"\u02d0\7t\2\2\u02d0\u02d1\7w\2\2\u02d1\u02d2\7e\2\2\u02d2\u02d3\7v\2\2"+
		"\u02d3\u008a\3\2\2\2\u02d4\u02d5\7d\2\2\u02d5\u02d6\7g\2\2\u02d6\u02d7"+
		"\7c\2\2\u02d7\u02d8\7u\2\2\u02d8\u02d9\7v\2\2\u02d9\u008c\3\2\2\2\u02da"+
		"\u02db\7d\2\2\u02db\u02dc\7w\2\2\u02dc\u02dd\7i\2\2\u02dd\u008e\3\2\2"+
		"\2\u02de\u02df\7g\2\2\u02df\u02e0\7n\2\2\u02e0\u02e1\7g\2\2\u02e1\u02e2"+
		"\7o\2\2\u02e2\u02e3\7g\2\2\u02e3\u02e4\7p\2\2\u02e4\u02e5\7v\2\2\u02e5"+
		"\u02e6\7c\2\2\u02e6\u02e7\7n\2\2\u02e7\u0090\3\2\2\2\u02e8\u02e9\7f\2"+
		"\2\u02e9\u02ea\7g\2\2\u02ea\u02eb\7o\2\2\u02eb\u02ec\7q\2\2\u02ec\u02ed"+
		"\7p\2\2\u02ed\u02ee\7k\2\2\u02ee\u02ef\7e\2\2\u02ef\u0092\3\2\2\2\u02f0"+
		"\u02f1\7i\2\2\u02f1\u02f2\7k\2\2\u02f2\u02f3\7c\2\2\u02f3\u02f4\7p\2\2"+
		"\u02f4\u02f5\7v\2\2\u02f5\u0094\3\2\2\2\u02f6\u02f7\7f\2\2\u02f7\u02f8"+
		"\7t\2\2\u02f8\u02f9\7c\2\2\u02f9\u02fa\7i\2\2\u02fa\u02fb\7q\2\2\u02fb"+
		"\u02fc\7p\2\2\u02fc\u0096\3\2\2\2\u02fd\u02fe\7f\2\2\u02fe\u02ff\7k\2"+
		"\2\u02ff\u0300\7x\2\2\u0300\u0301\7k\2\2\u0301\u0302\7p\2\2\u0302\u0303"+
		"\7g\2\2\u0303\u0098\3\2\2\2\u0304\u0305\7u\2\2\u0305\u0306\7w\2\2\u0306"+
		"\u0307\7o\2\2\u0307\u0308\7o\2\2\u0308\u0309\7q\2\2\u0309\u030a\7p\2\2"+
		"\u030a\u009a\3\2\2\2\u030b\u030c\7r\2\2\u030c\u030d\7g\2\2\u030d\u030e"+
		"\7v\2\2\u030e\u009c\3\2\2\2\u030f\u0310\7j\2\2\u0310\u0311\7q\2\2\u0311"+
		"\u0312\7n\2\2\u0312\u0313\7{\2\2\u0313\u0314\7v\2\2\u0314\u0315\7j\2\2"+
		"\u0315\u0316\7k\2\2\u0316\u0317\7p\2\2\u0317\u0318\7i\2\2\u0318\u009e"+
		"\3\2\2\2\u0319\u031a\7f\2\2\u031a\u031b\7y\2\2\u031b\u031c\7c\2\2\u031c"+
		"\u031d\7t\2\2\u031d\u031e\7h\2\2\u031e\u00a0\3\2\2\2\u031f\u0320\7o\2"+
		"\2\u0320\u0321\7g\2\2\u0321\u0322\7t\2\2\u0322\u0323\7e\2\2\u0323\u0324"+
		"\7j\2\2\u0324\u0325\7c\2\2\u0325\u0326\7p\2\2\u0326\u0327\7v\2\2\u0327"+
		"\u00a2\3\2\2\2\u0328\u0329\7g\2\2\u0329\u032a\7n\2\2\u032a\u032b\7h\2"+
		"\2\u032b\u00a4\3\2\2\2\u032c\u032d\7m\2\2\u032d\u032e\7c\2\2\u032e\u032f"+
		"\7o\2\2\u032f\u0330\7c\2\2\u0330\u0331\7g\2\2\u0331\u0332\7n\2\2\u0332"+
		"\u00a6\3\2\2\2\u0333\u0334\7q\2\2\u0334\u0335\7t\2\2\u0335\u0336\7e\2"+
		"\2\u0336\u00a8\3\2\2\2\u0337\u0338\7o\2\2\u0338\u0339\7g\2\2\u0339\u033a"+
		"\7t\2\2\u033a\u033b\7e\2\2\u033b\u033c\7g\2\2\u033c\u033d\7p\2\2\u033d"+
		"\u033e\7c\2\2\u033e\u033f\7t\2\2\u033f\u0340\7{\2\2\u0340\u00aa\3\2\2"+
		"\2\u0341\u0342\7e\2\2\u0342\u0343\7c\2\2\u0343\u0344\7u\2\2\u0344\u0345"+
		"\7v\2\2\u0345\u0346\7n\2\2\u0346\u0347\7g\2\2\u0347\u0348\7a\2\2\u0348"+
		"\u0349\7i\2\2\u0349\u034a\7w\2\2\u034a\u034b\7c\2\2\u034b\u034c\7t\2\2"+
		"\u034c\u034d\7f\2\2\u034d\u00ac\3\2\2\2\u034e\u034f\7j\2\2\u034f\u0350"+
		"\7w\2\2\u0350\u0351\7o\2\2\u0351\u0352\7c\2\2\u0352\u0353\7p\2\2\u0353"+
		"\u00ae\3\2\2\2\u0354\u0355\7d\2\2\u0355\u0356\7q\2\2\u0356\u0357\7u\2"+
		"\2\u0357\u0358\7u\2\2\u0358\u00b0\3\2\2\2\u0359\u035a\7|\2\2\u035a\u035b"+
		"\7|\2\2\u035b\u035c\7q\2\2\u035c\u035d\7n\2\2\u035d\u035e\7f\2\2\u035e"+
		"\u035f\7c\2\2\u035f\u0360\7i\2\2\u0360\u0361\7w\2\2\u0361\u00b2\3\2\2"+
		"\2\u0362\u0363\7y\2\2\u0363\u0364\7q\2\2\u0364\u0365\7t\2\2\u0365\u0366"+
		"\7n\2\2\u0366\u0367\7f\2\2\u0367\u0368\7a\2\2\u0368\u0369\7v\2\2\u0369"+
		"\u036a\7t\2\2\u036a\u036b\7c\2\2\u036b\u036c\7r\2\2\u036c\u00b4\3\2\2"+
		"\2\u036d\u036e\7o\2\2\u036e\u036f\7q\2\2\u036f\u0370\7p\2\2\u0370\u0371"+
		"\7t\2\2\u0371\u0372\7c\2\2\u0372\u0373\7e\2\2\u0373\u0374\7g\2\2\u0374"+
		"\u00b6\3\2\2\2\u0375\u0376\7f\2\2\u0376\u0377\7c\2\2\u0377\u0378\7t\2"+
		"\2\u0378\u0379\7m\2\2\u0379\u037a\7g\2\2\u037a\u037b\7n\2\2\u037b\u037c"+
		"\7h\2\2\u037c\u00b8\3\2\2\2\u037d\u037e\7i\2\2\u037e\u037f\7w\2\2\u037f"+
		"\u0380\7c\2\2\u0380\u0381\7t\2\2\u0381\u0382\7f\2\2\u0382\u00ba\3\2\2"+
		"\2\u0383\u0384\7v\2\2\u0384\u0385\7g\2\2\u0385\u0386\7n\2\2\u0386\u0387"+
		"\7g\2\2\u0387\u0388\7r\2\2\u0388\u0389\7q\2\2\u0389\u038a\7t\2\2\u038a"+
		"\u038b\7v\2\2\u038b\u038c\7g\2\2\u038c\u038d\7t\2\2\u038d\u00bc\3\2\2"+
		"\2\u038e\u038f\7y\2\2\u038f\u0390\7c\2\2\u0390\u0391\7t\2\2\u0391\u0392"+
		"\7g\2\2\u0392\u0393\7j\2\2\u0393\u0394\7q\2\2\u0394\u0395\7w\2\2\u0395"+
		"\u0396\7u\2\2\u0396\u0397\7g\2\2\u0397\u0398\7a\2\2\u0398\u0399\7m\2\2"+
		"\u0399\u039a\7g\2\2\u039a\u039b\7g\2\2\u039b\u039c\7r\2\2\u039c\u039d"+
		"\7g\2\2\u039d\u039e\7t\2\2\u039e\u00be\3\2\2\2\u039f\u03a0\7y\2\2\u03a0"+
		"\u03a1\7c\2\2\u03a1\u03a2\7t\2\2\u03a2\u03a3\7t\2\2\u03a3\u03a4\7k\2\2"+
		"\u03a4\u03a5\7q\2\2\u03a5\u03a6\7t\2\2\u03a6\u00c0\3\2\2\2\u03a7\u03a8"+
		"\7e\2\2\u03a8\u03a9\7k\2\2\u03a9\u03aa\7v\2\2\u03aa\u03ab\7k\2\2\u03ab"+
		"\u03ac\7|\2\2\u03ac\u03ad\7g\2\2\u03ad\u03ae\7p\2\2\u03ae\u00c2\3\2\2"+
		"\2\u03af\u03b0\7v\2\2\u03b0\u03b1\7t\2\2\u03b1\u03b2\7g\2\2\u03b2\u03b3"+
		"\7c\2\2\u03b3\u03b4\7u\2\2\u03b4\u03b5\7w\2\2\u03b5\u03b6\7t\2\2\u03b6"+
		"\u03b7\7g\2\2\u03b7\u00c4\3\2\2\2\u03b8\u03b9\7h\2\2\u03b9\u03ba\7k\2"+
		"\2\u03ba\u03bb\7g\2\2\u03bb\u03bc\7n\2\2\u03bc\u03bd\7f\2\2\u03bd\u03be"+
		"\7d\2\2\u03be\u03bf\7q\2\2\u03bf\u03c0\7u\2\2\u03c0\u03c1\7u\2\2\u03c1"+
		"\u00c6\3\2\2\2\u03c2\u03c3\7d\2\2\u03c3\u03c4\7n\2\2\u03c4\u03c5\7c\2"+
		"\2\u03c5\u03c6\7e\2\2\u03c6\u03c7\7m\2\2\u03c7\u03c8\7u\2\2\u03c8\u03c9"+
		"\7o\2\2\u03c9\u03ca\7k\2\2\u03ca\u03cb\7v\2\2\u03cb\u03cc\7j\2\2\u03cc"+
		"\u00c8\3\2\2\2\u03cd\u03ce\7i\2\2\u03ce\u03cf\7w\2\2\u03cf\u03d0\7k\2"+
		"\2\u03d0\u03d1\7n\2\2\u03d1\u03d2\7f\2\2\u03d2\u03d3\7a\2\2\u03d3\u03d4"+
		"\7o\2\2\u03d4\u03d5\7c\2\2\u03d5\u03d6\7u\2\2\u03d6\u03d7\7v\2\2\u03d7"+
		"\u03d8\7g\2\2\u03d8\u03d9\7t\2\2\u03d9\u00ca\3\2\2\2\u03da\u03db\7i\2"+
		"\2\u03db\u03dc\7w\2\2\u03dc\u03dd\7k\2\2\u03dd\u03de\7n\2\2\u03de\u03df"+
		"\7f\2\2\u03df\u03e0\7a\2\2\u03e0\u03e1\7e\2\2\u03e1\u03e2\7q\2\2\u03e2"+
		"\u03e3\7c\2\2\u03e3\u03e4\7e\2\2\u03e4\u03e5\7j\2\2\u03e5\u00cc\3\2\2"+
		"\2\u03e6\u03e7\7u\2\2\u03e7\u03e8\7y\2\2\u03e8\u03e9\7q\2\2\u03e9\u03ea"+
		"\7t\2\2\u03ea\u03eb\7f\2\2\u03eb\u00ce\3\2\2\2\u03ec\u03ed\7d\2\2\u03ed"+
		"\u03ee\7n\2\2\u03ee\u03ef\7w\2\2\u03ef\u03f0\7p\2\2\u03f0\u03f1\7v\2\2"+
		"\u03f1\u00d0\3\2\2\2\u03f2\u03f3\7d\2\2\u03f3\u03f4\7q\2\2\u03f4\u03f5"+
		"\7y\2\2\u03f5\u00d2\3\2\2\2\u03f6\u03f7\7r\2\2\u03f7\u03f8\7q\2\2\u03f8"+
		"\u03f9\7n\2\2\u03f9\u03fa\7g\2\2\u03fa\u00d4\3\2\2\2\u03fb\u03fc\7f\2"+
		"\2\u03fc\u03fd\7c\2\2\u03fd\u03fe\7i\2\2\u03fe\u03ff\7i\2\2\u03ff\u0400"+
		"\7g\2\2\u0400\u0401\7t\2\2\u0401\u00d6\3\2\2\2\u0402\u0403\7f\2\2\u0403"+
		"\u0404\7w\2\2\u0404\u0405\7c\2\2\u0405\u0406\7n\2\2\u0406\u00d8\3\2\2"+
		"\2\u0407\u0408\7h\2\2\u0408\u0409\7k\2\2\u0409\u040a\7u\2\2\u040a\u040b"+
		"\7v\2\2\u040b\u00da\3\2\2\2\u040c\u040d\7f\2\2\u040d\u040e\7w\2\2\u040e"+
		"\u040f\7c\2\2\u040f\u0410\7n\2\2\u0410\u0411\7h\2\2\u0411\u0412\7k\2\2"+
		"\u0412\u0413\7u\2\2\u0413\u0414\7v\2\2\u0414\u00dc\3\2\2\2\u0415\u0416"+
		"\7h\2\2\u0416\u0417\7k\2\2\u0417\u0418\7u\2\2\u0418\u0419\7j\2\2\u0419"+
		"\u041a\7k\2\2\u041a\u041b\7p\2\2\u041b\u041c\7i\2\2\u041c\u041d\7t\2\2"+
		"\u041d\u041e\7q\2\2\u041e\u041f\7f\2\2\u041f\u00de\3\2\2\2\u0420\u0421"+
		"\7t\2\2\u0421\u0422\7c\2\2\u0422\u0423\7r\2\2\u0423\u0424\7k\2\2\u0424"+
		"\u0425\7g\2\2\u0425\u0426\7t\2\2\u0426\u00e0\3\2\2\2\u0427\u0428\7c\2"+
		"\2\u0428\u0429\7p\2\2\u0429\u042a\7e\2\2\u042a\u042b\7k\2\2\u042b\u042c"+
		"\7g\2\2\u042c\u042d\7p\2\2\u042d\u042e\7v\2\2\u042e\u042f\7u\2\2\u042f"+
		"\u0430\7y\2\2\u0430\u0431\7q\2\2\u0431\u0432\7t\2\2\u0432\u0433\7f\2\2"+
		"\u0433\u00e2\3\2\2\2\u0434\u0435\7e\2\2\u0435\u0436\7t\2\2\u0436\u0437"+
		"\7q\2\2\u0437\u0438\7u\2\2\u0438\u0439\7u\2\2\u0439\u043a\7d\2\2\u043a"+
		"\u043b\7q\2\2\u043b\u043c\7y\2\2\u043c\u00e4\3\2\2\2\u043d\u043e\7h\2"+
		"\2\u043e\u043f\7n\2\2\u043f\u0440\7c\2\2\u0440\u0441\7i\2\2\u0441\u00e6"+
		"\3\2\2\2\u0442\u0443\7q\2\2\u0443\u0444\7y\2\2\u0444\u0445\7p\2\2\u0445"+
		"\u0446\7v\2\2\u0446\u0447\7j\2\2\u0447\u0448\7k\2\2\u0448\u0449\7p\2\2"+
		"\u0449\u044a\7i\2\2\u044a\u00e8\3\2\2\2\u044b\u044c\7f\2\2\u044c\u044d"+
		"\7w\2\2\u044d\u044e\7c\2\2\u044e\u044f\7n\2\2\u044f\u0450\7f\2\2\u0450"+
		"\u0451\7c\2\2\u0451\u0452\7i\2\2\u0452\u0453\7i\2\2\u0453\u0454\7g\2\2"+
		"\u0454\u0455\7t\2\2\u0455\u00ea\3\2\2\2\u0456\u0457\7g\2\2\u0457\u0458"+
		"\7v\2\2\u0458\u0459\7e\2\2\u0459\u00ec\3\2\2\2\u045a\u045b\7n\2\2\u045b"+
		"\u045c\7k\2\2\u045c\u045d\7i\2\2\u045d\u045e\7j\2\2\u045e\u045f\7v\2\2"+
		"\u045f\u00ee\3\2\2\2\u0460\u0461\7j\2\2\u0461\u0462\7g\2\2\u0462\u0463"+
		"\7c\2\2\u0463\u0464\7x\2\2\u0464\u0465\7{\2\2\u0465\u00f0\3\2\2\2\u0466"+
		"\u0467\7o\2\2\u0467\u0468\7c\2\2\u0468\u0469\7i\2\2\u0469\u046a\7k\2\2"+
		"\u046a\u046b\7e\2\2\u046b\u00f2\3\2\2\2\u046c\u046d\7u\2\2\u046d\u046e"+
		"\7k\2\2\u046e\u046f\7i\2\2\u046f\u0470\7k\2\2\u0470\u0471\7n\2\2\u0471"+
		"\u00f4\3\2\2\2\u0472\u0473\7t\2\2\u0473\u0474\7j\2\2\u0474\u0475\7c\2"+
		"\2\u0475\u0476\7p\2\2\u0476\u0477\7f\2\2\u0477\u00f6\3\2\2\2\u0478\u0479"+
		"\7n\2\2\u0479\u047a\7t\2\2\u047a\u047b\7j\2\2\u047b\u047c\7c\2\2\u047c"+
		"\u047d\7p\2\2\u047d\u047e\7f\2\2\u047e\u00f8\3\2\2\2\u047f\u0480\7n\2"+
		"\2\u0480\u0481\7j\2\2\u0481\u0482\7c\2\2\u0482\u0483\7p\2\2\u0483\u0484"+
		"\7f\2\2\u0484\u00fa\3\2\2\2\u0485\u0486\7e\2\2\u0486\u0487\7j\2\2\u0487"+
		"\u0488\7g\2\2\u0488\u0489\7u\2\2\u0489\u048a\7v\2\2\u048a\u00fc\3\2\2"+
		"\2\u048b\u048c\7n\2\2\u048c\u048d\7g\2\2\u048d\u048e\7i\2\2\u048e\u048f"+
		"\7u\2\2\u048f\u00fe\3\2\2\2\u0490\u0491\7h\2\2\u0491\u0492\7g\2\2\u0492"+
		"\u0493\7g\2\2\u0493\u0494\7v\2\2\u0494\u0100\3\2\2\2\u0495\u0496\7j\2"+
		"\2\u0496\u0497\7g\2\2\u0497\u0498\7c\2\2\u0498\u0499\7f\2\2\u0499\u0102"+
		"\3\2\2\2\u049a\u049b\7i\2\2\u049b\u049c\7n\2\2\u049c\u049d\7q\2\2\u049d"+
		"\u049e\7x\2\2\u049e\u049f\7g\2\2\u049f\u04a0\7u\2\2\u04a0\u0104\3\2\2"+
		"\2\u04a1\u04a2\7q\2\2\u04a2\u04a3\7p\2\2\u04a3\u04a4\7g\2\2\u04a4\u04a5"+
		"\7r\2\2\u04a5\u04a6\7k\2\2\u04a6\u04a7\7g\2\2\u04a7\u04a8\7e\2\2\u04a8"+
		"\u04a9\7g\2\2\u04a9\u0106\3\2\2\2\u04aa\u04ab\7t\2\2\u04ab\u04ac\7g\2"+
		"\2\u04ac\u04ad\7c\2\2\u04ad\u04ae\7t\2\2\u04ae\u0108\3\2\2\2\u04af\u04b0"+
		"\7n\2\2\u04b0\u04b1\7g\2\2\u04b1\u04b2\7c\2\2\u04b2\u04b3\7t\2\2\u04b3"+
		"\u010a\3\2\2\2\u04b4\u04b5\7t\2\2\u04b5\u04b6\7h\2\2\u04b6\u04b7\7k\2"+
		"\2\u04b7\u04b8\7p\2\2\u04b8\u04b9\7i\2\2\u04b9\u04ba\7g\2\2\u04ba\u04bb"+
		"\7t\2\2\u04bb\u010c\3\2\2\2\u04bc\u04bd\7n\2\2\u04bd\u04be\7h\2\2\u04be"+
		"\u04bf\7k\2\2\u04bf\u04c0\7p\2\2\u04c0\u04c1\7i\2\2\u04c1\u04c2\7g\2\2"+
		"\u04c2\u04c3\7t\2\2\u04c3\u010e\3\2\2\2\u04c4\u04c5\7p\2\2\u04c5\u04c6"+
		"\7g\2\2\u04c6\u04c7\7e\2\2\u04c7\u04c8\7m\2\2\u04c8\u0110\3\2\2\2\u04c9"+
		"\u04ca\7d\2\2\u04ca\u04cb\7c\2\2\u04cb\u04cc\7e\2\2\u04cc\u04cd\7m\2\2"+
		"\u04cd\u0112\3\2\2\2\u04ce\u04cf\7w\2\2\u04cf\u04d0\7p\2\2\u04d0\u04d1"+
		"\7f\2\2\u04d1\u04d2\7g\2\2\u04d2\u04d3\7t\2\2\u04d3\u04d4\7y\2\2\u04d4"+
		"\u04d5\7g\2\2\u04d5\u04d6\7c\2\2\u04d6\u04d7\7t\2\2\u04d7\u0114\3\2\2"+
		"\2\u04d8\u04d9\7j\2\2\u04d9\u04da\7c\2\2\u04da\u04db\7k\2\2\u04db\u04dc"+
		"\7t\2\2\u04dc\u0116\3\2\2\2\u04dd\u04de\7j\2\2\u04de\u04df\7c\2\2\u04df"+
		"\u04e0\7k\2\2\u04e0\u04e1\7t\2\2\u04e1\u04e2\7\64\2\2\u04e2\u0118\3\2"+
		"\2\2\u04e3\u04e4\7j\2\2\u04e4\u04e5\7c\2\2\u04e5\u04e6\7k\2\2\u04e6\u04e7"+
		"\7t\2\2\u04e7\u04e8\7c\2\2\u04e8\u04e9\7n\2\2\u04e9\u04ea\7n\2\2\u04ea"+
		"\u011a\3\2\2\2\u04eb\u04ec\7c\2\2\u04ec\u04ed\7n\2\2\u04ed\u04ee\7n\2"+
		"\2\u04ee\u04ef\7f\2\2\u04ef\u04f0\7t\2\2\u04f0\u04f1\7g\2\2\u04f1\u04f2"+
		"\7u\2\2\u04f2\u04f3\7u\2\2\u04f3\u011c\3\2\2\2\u04f4\u04f5\7t\2\2\u04f5"+
		"\u04f6\7d\2\2\u04f6\u04f7\7t\2\2\u04f7\u04f8\7c\2\2\u04f8\u04f9\7e\2\2"+
		"\u04f9\u04fa\7g\2\2\u04fa\u04fb\7n\2\2\u04fb\u04fc\7g\2\2\u04fc\u04fd"+
		"\7v\2\2\u04fd\u011e\3\2\2\2\u04fe\u04ff\7n\2\2\u04ff\u0500\7d\2\2\u0500"+
		"\u0501\7t\2\2\u0501\u0502\7c\2\2\u0502\u0503\7e\2\2\u0503\u0504\7g\2\2"+
		"\u0504\u0505\7n\2\2\u0505\u0506\7g\2\2\u0506\u0507\7v\2\2\u0507\u0120"+
		"\3\2\2\2\u0508\u0509\7y\2\2\u0509\u050a\7c\2\2\u050a\u050b\7k\2\2\u050b"+
		"\u050c\7u\2\2\u050c\u050d\7v\2\2\u050d\u0122\3\2\2\2\u050e\u050f\7f\2"+
		"\2\u050f\u0510\7g\2\2\u0510\u0511\7e\2\2\u0511\u0512\7q\2\2\u0512\u0513"+
		"\7\63\2\2\u0513\u0124\3\2\2\2\u0514\u0515\7u\2\2\u0515\u0516\7v\2\2\u0516"+
		"\u0517\7g\2\2\u0517\u0518\7g\2\2\u0518\u0519\7n\2\2\u0519\u0126\3\2\2"+
		"\2\u051a\u051b\7h\2\2\u051b\u051c\7k\2\2\u051c\u051d\7p\2\2\u051d\u051e"+
		"\7g\2\2\u051e\u051f\7a\2\2\u051f\u0520\7u\2\2\u0520\u0521\7v\2\2\u0521"+
		"\u0522\7g\2\2\u0522\u0523\7g\2\2\u0523\u0524\7n\2\2\u0524\u0128\3\2\2"+
		"\2\u0525\u0526\7y\2\2\u0526\u0527\7q\2\2\u0527\u0528\7q\2\2\u0528\u0529"+
		"\7f\2\2\u0529\u012a\3\2\2\2\u052a\u052b\7e\2\2\u052b\u052c\7n\2\2\u052c"+
		"\u052d\7q\2\2\u052d\u052e\7v\2\2\u052e\u052f\7j\2\2\u052f\u012c\3\2\2"+
		"\2\u0530\u0531\7n\2\2\u0531\u0532\7g\2\2\u0532\u0533\7c\2\2\u0533\u0534"+
		"\7v\2\2\u0534\u0535\7j\2\2\u0535\u0536\7g\2\2\u0536\u0537\7t\2\2\u0537"+
		"\u012e\3\2\2\2\u0538\u0539\7d\2\2\u0539\u053a\7q\2\2\u053a\u053b\7p\2"+
		"\2\u053b\u053c\7g\2\2\u053c\u0130\3\2\2\2\u053d\u053e\7d\2\2\u053e\u053f"+
		"\7t\2\2\u053f\u0540\7q\2\2\u0540\u0541\7p\2\2\u0541\u0542\7|\2\2\u0542"+
		"\u0543\7g\2\2\u0543\u0132\3\2\2\2\u0544\u0545\7q\2\2\u0545\u0546\7t\2"+
		"\2\u0546\u0547\7k\2\2\u0547\u0548\7j\2\2\u0548\u0549\7c\2\2\u0549\u054a"+
		"\7t\2\2\u054a\u054b\7w\2\2\u054b\u054c\7m\2\2\u054c\u054d\7q\2\2\u054d"+
		"\u054e\7p\2\2\u054e\u0134\3\2\2\2\u054f\u0550\7o\2\2\u0550\u0551\7k\2"+
		"\2\u0551\u0552\7v\2\2\u0552\u0553\7j\2\2\u0553\u0554\7t\2\2\u0554\u0555"+
		"\7k\2\2\u0555\u0556\7n\2\2\u0556\u0136\3\2\2\2\u0557\u0558\7f\2\2\u0558"+
		"\u0559\7c\2\2\u0559\u055a\7o\2\2\u055a\u055b\7c\2\2\u055b\u055c\7u\2\2"+
		"\u055c\u055d\7e\2\2\u055d\u055e\7w\2\2\u055e\u055f\7u\2\2\u055f\u0138"+
		"\3\2\2\2\u0560\u0561\7c\2\2\u0561\u0562\7f\2\2\u0562\u0563\7c\2\2\u0563"+
		"\u0564\7o\2\2\u0564\u0565\7c\2\2\u0565\u0566\7p\2\2\u0566\u0567\7v\2\2"+
		"\u0567\u0568\7c\2\2\u0568\u0569\7k\2\2\u0569\u056a\7v\2\2\u056a\u056b"+
		"\7g\2\2\u056b\u013a\3\2\2\2\u056c\u056d\7d\2\2\u056d\u056e\7n\2\2\u056e"+
		"\u056f\7q\2\2\u056f\u0570\7q\2\2\u0570\u0571\7f\2\2\u0571\u0572\7a\2\2"+
		"\u0572\u0573\7u\2\2\u0573\u0574\7v\2\2\u0574\u0575\7g\2\2\u0575\u0576"+
		"\7g\2\2\u0576\u0577\7n\2\2\u0577\u013c\3\2\2\2\u0578\u0579\7r\2\2\u0579"+
		"\u057a\7c\2\2\u057a\u057b\7r\2\2\u057b\u057c\7g\2\2\u057c\u057d\7t\2\2"+
		"\u057d\u013e\3\2\2\2\u057e\u057f\7i\2\2\u057f\u0580\7q\2\2\u0580\u0581"+
		"\7n\2\2\u0581\u0582\7f\2\2\u0582\u0140\3\2\2\2\u0583\u0584\7n\2\2\u0584"+
		"\u0585\7k\2\2\u0585\u0586\7s\2\2\u0586\u0587\7w\2\2\u0587\u0588\7k\2\2"+
		"\u0588\u0589\7f\2\2\u0589\u0142\3\2\2\2\u058a\u058b\7h\2\2\u058b\u058c"+
		"\7k\2\2\u058c\u058d\7u\2\2\u058d\u058e\7j\2\2\u058e\u0144\3\2\2\2\u058f"+
		"\u0590\7u\2\2\u0590\u0591\7k\2\2\u0591\u0592\7n\2\2\u0592\u0593\7x\2\2"+
		"\u0593\u0594\7g\2\2\u0594\u0595\7t\2\2\u0595\u0146\3\2\2\2\u0596\u0597"+
		"\7e\2\2\u0597\u0598\7j\2\2\u0598\u0599\7t\2\2\u0599\u059a\7{\2\2\u059a"+
		"\u059b\7u\2\2\u059b\u059c\7q\2\2\u059c\u059d\7n\2\2\u059d\u059e\7k\2\2"+
		"\u059e\u059f\7v\2\2\u059f\u05a0\7g\2\2\u05a0\u0148\3\2\2\2\u05a1\u05a2"+
		"\7e\2\2\u05a2\u05a3\7t\2\2\u05a3\u05a4\7{\2\2\u05a4\u05a5\7u\2\2\u05a5"+
		"\u05a6\7v\2\2\u05a6\u05a7\7c\2\2\u05a7\u05a8\7n\2\2\u05a8\u014a\3\2\2"+
		"\2\u05a9\u05aa\7j\2\2\u05aa\u05ab\7q\2\2\u05ab\u05ac\7t\2\2\u05ac\u05ad"+
		"\7p\2\2\u05ad\u014c\3\2\2\2\u05ae\u05af\7u\2\2\u05af\u05b0\7e\2\2\u05b0"+
		"\u05b1\7c\2\2\u05b1\u05b2\7n\2\2\u05b2\u05b3\7g\2\2\u05b3\u05b4\7a\2\2"+
		"\u05b4\u05b5\7q\2\2\u05b5\u05b6\7h\2\2\u05b6\u05b7\7a\2\2\u05b7\u05b8"+
		"\7f\2\2\u05b8\u05b9\7t\2\2\u05b9\u05ba\7c\2\2\u05ba\u05bb\7i\2\2\u05bb"+
		"\u05bc\7q\2\2\u05bc\u05bd\7p\2\2\u05bd\u014e\3\2\2\2\u05be\u05bf\7e\2"+
		"\2\u05bf\u05c0\7q\2\2\u05c0\u05c1\7v\2\2\u05c1\u05c2\7v\2\2\u05c2\u05c3"+
		"\7q\2\2\u05c3\u05c4\7p\2\2\u05c4\u0150\3\2\2\2\u05c5\u05c6\7f\2\2\u05c6"+
		"\u05c7\7{\2\2\u05c7\u05c8\7g\2\2\u05c8\u05c9\7u\2\2\u05c9\u05ca\7v\2\2"+
		"\u05ca\u05cb\7w\2\2\u05cb\u05cc\7h\2\2\u05cc\u05cd\7h\2\2\u05cd\u0152"+
		"\3\2\2\2\u05ce\u05cf\7e\2\2\u05cf\u05d0\7q\2\2\u05d0\u05d1\7d\2\2\u05d1"+
		"\u05d2\7y\2\2\u05d2\u05d3\7g\2\2\u05d3\u05d4\7d\2\2\u05d4\u0154\3\2\2"+
		"\2\u05d5\u05d6\7t\2\2\u05d6\u05d7\7w\2\2\u05d7\u05d8\7p\2\2\u05d8\u05d9"+
		"\7g\2\2\u05d9\u05da\7a\2\2\u05da\u05db\7z\2\2\u05db\u05dc\7r\2\2\u05dc"+
		"\u0156\3\2\2\2\u05dd\u05de\7t\2\2\u05de\u05df\7w\2\2\u05df\u05e0\7p\2"+
		"\2\u05e0\u05e1\7g\2\2\u05e1\u05e2\7a\2\2\u05e2\u05e3\7u\2\2\u05e3\u05e4"+
		"\7r\2\2\u05e4\u0158\3\2\2\2\u05e5\u05e6\7t\2\2\u05e6\u05e7\7w\2\2\u05e7"+
		"\u05e8\7p\2\2\u05e8\u05e9\7g\2\2\u05e9\u05ea\7a\2\2\u05ea\u05eb\7t\2\2"+
		"\u05eb\u05ec\7g\2\2\u05ec\u05ed\7o\2\2\u05ed\u05ee\7q\2\2\u05ee\u05ef"+
		"\7x\2\2\u05ef\u05f0\7g\2\2\u05f0\u05f1\7a\2\2\u05f1\u05f2\7r\2\2\u05f2"+
		"\u05f3\7g\2\2\u05f3\u05f4\7p\2\2\u05f4\u05f5\7c\2\2\u05f5\u05f6\7n\2\2"+
		"\u05f6\u05f7\7v\2\2\u05f7\u05f8\7{\2\2\u05f8\u015a\3\2\2\2\u05f9\u05fb"+
		"\t\6\2\2\u05fa\u05f9\3\2\2\2\u05fb\u05fc\3\2\2\2\u05fc\u05fa\3\2\2\2\u05fc"+
		"\u05fd\3\2\2\2\u05fd\u0601\3\2\2\2\u05fe\u0600\t\7\2\2\u05ff\u05fe\3\2"+
		"\2\2\u0600\u0603\3\2\2\2\u0601\u05ff\3\2\2\2\u0601\u0602\3\2\2\2\u0602"+
		"\u015c\3\2\2\2\u0603\u0601\3\2\2\2\u0604\u0606\t\b\2\2\u0605\u0604\3\2"+
		"\2\2\u0606\u0607\3\2\2\2\u0607\u0605\3\2\2\2\u0607\u0608\3\2\2\2\u0608"+
		"\u0609\3\2\2\2\u0609\u060a\b\u00af\2\2\u060a\u015e\3\2\2\2\u060b\u060c"+
		"\7\61\2\2\u060c\u060d\7\61\2\2\u060d\u0611\3\2\2\2\u060e\u0610\n\t\2\2"+
		"\u060f\u060e\3\2\2\2\u0610\u0613\3\2\2\2\u0611\u060f\3\2\2\2\u0611\u0612"+
		"\3\2\2\2\u0612\u0615\3\2\2\2\u0613\u0611\3\2\2\2\u0614\u0616\7\17\2\2"+
		"\u0615\u0614\3\2\2\2\u0615\u0616\3\2\2\2\u0616\u0617\3\2\2\2\u0617\u0618"+
		"\7\f\2\2\u0618\u0619\3\2\2\2\u0619\u061a\b\u00b0\2\2\u061a\u0160\3\2\2"+
		"\2\u061b\u061c\7\61\2\2\u061c\u061d\7,\2\2\u061d\u0621\3\2\2\2\u061e\u0620"+
		"\13\2\2\2\u061f\u061e\3\2\2\2\u0620\u0623\3\2\2\2\u0621\u0622\3\2\2\2"+
		"\u0621\u061f\3\2\2\2\u0622\u0624\3\2\2\2\u0623\u0621\3\2\2\2\u0624\u0625"+
		"\7,\2\2\u0625\u0626\7\61\2\2\u0626\u0627\3\2\2\2\u0627\u0628\b\u00b1\2"+
		"\2\u0628\u0162\3\2\2\2\r\2\u0264\u026d\u0296\u0299\u05fc\u0601\u0607\u0611"+
		"\u0615\u0621\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}