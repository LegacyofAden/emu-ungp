// Generated from org\l2junity\gameserver\data\txt\gen\UserBasicActions.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.l2junity.gameserver.data.txt.model.constants.ActionHandlerType;
import org.l2junity.gameserver.data.txt.model.action.ActionTemplate;


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
public class UserBasicActionsLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "SIT_STAND", "WALK_RUN", "ATTACK", "TRADE", 
		"CLIENT_ACTION", "PRIVATE_STORE", "SOCIAL_ACTION", "PET_ACTION", "PET_DEPOSIT", 
		"SUMMON_ACTION", "PRIVATE_BUY", "MAKE_ITEM", "MAKE_ITEM2", "RIDE", "SUMMON_DESPAWN", 
		"PACKAGE_PRIVATE_STORE", "TELEPORT_BOOKMARK", "BOT_REPORT", "AIRSHIP_ACTION", 
		"COUPLE_ACTION", "CATEGORY", "BOOLEAN", "INTEGER", "DOUBLE", "FIRE", "WATER", 
		"EARTH", "WIND", "UNHOLY", "HOLY", "DECIMAL", "MINUS", "BINARY_DIGIT", 
		"DIGIT", "NON_ZERO_DIGIT", "ZERO_DIGIT", "SEMICOLON", "NONE", "FAIRY", 
		"ANIMAL", "HUMANOID", "PLANT", "UNDEAD", "CONSTRUCT", "BEAST", "BUG", 
		"ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", "DIVINE", "SUMMON", "PET", 
		"HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", "ORC", "SIEGE_WEAPON", 
		"FRIEND", "MERCENARY", "CASTLE_GUARD", "HUMAN", "BOSS", "ZZOLDAGU", "WORLD_TRAP", 
		"MONRACE", "DARKELF", "GUARD", "TELEPORTER", "WAREHOUSE_KEEPER", "WARRIOR", 
		"CITIZEN", "TREASURE", "FIELDBOSS", "BLACKSMITH", "GUILD_MASTER", "GUILD_COACH", 
		"PC_TRAP", "XMASTREE", "DOPPELGANGER", "OWNTHING", "SIEGE_ATTACKER", "MRKEEPER", 
		"COLLECTION", "PACKAGE_KEEPER", "SWORD", "BLUNT", "BOW", "POLE", "DAGGER", 
		"DUAL", "FIST", "DUALFIST", "FISHINGROD", "RAPIER", "ANCIENTSWORD", "CROSSBOW", 
		"FLAG", "DUALDAGGER", "ETC", "LIGHT", "HEAVY", "MAGIC", "SIGIL", "RHAND", 
		"LRHAND", "LHAND", "CHEST", "LEGS", "FEET", "HEAD", "GLOVES", "ONEPIECE", 
		"REAR", "LEAR", "RFINGER", "LFINGER", "NECK", "BACK", "UNDERWEAR", "HAIR", 
		"HAIR2", "HAIRALL", "ALLDRESS", "RBRACELET", "LBRACELET", "WAIST", "DECO1", 
		"STEEL", "FINE_STEEL", "WOOD", "CLOTH", "LEATHER", "BONE", "BRONZE", "ORIHARUKON", 
		"MITHRIL", "DAMASCUS", "ADAMANTAITE", "BLOOD_STEEL", "PAPER", "GOLD", 
		"LIQUID", "FISH", "SILVER", "CHRYSOLITE", "CRYSTAL", "HORN", "SCALE_OF_DRAGON", 
		"COTTON", "DYESTUFF", "COBWEB", "RUNE_XP", "RUNE_SP", "RUNE_REMOVE_PENALTY", 
		"NAME", "WS", "LINE_COMMENT", "STAR_COMMENT"
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


	public UserBasicActionsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "UserBasicActions.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\u00a9\u06da\b\1\4"+
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
		"\t\u00ad\4\u00ae\t\u00ae\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\""+
		"\3#\3#\3$\5$\u02ad\n$\3$\3$\3%\3%\3%\6%\u02b4\n%\r%\16%\u02b5\3&\3&\3"+
		"&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)\3)\3)\3*\3"+
		"*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\7,\u02dd\n,\f,\16,\u02e0\13,"+
		"\5,\u02e2\n,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63"+
		"\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65"+
		"\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67"+
		"\3\67\3\67\3\67\38\38\38\38\38\38\38\39\39\39\39\39\39\39\39\39\39\3:"+
		"\3:\3:\3:\3:\3:\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3<\3<\3<\3<\3=\3=\3=\3="+
		"\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@"+
		"\3@\3A\3A\3A\3A\3A\3A\3A\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3C\3C\3C\3C\3D"+
		"\3D\3D\3D\3D\3D\3E\3E\3E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3G\3G\3G\3G\3G"+
		"\3G\3G\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J"+
		"\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L"+
		"\3L\3L\3L\3M\3M\3M\3M\3M\3M\3N\3N\3N\3N\3N\3O\3O\3O\3O\3O\3O\3O\3O\3O"+
		"\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R"+
		"\3R\3R\3R\3R\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3U\3U"+
		"\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3V\3V"+
		"\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y"+
		"\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3["+
		"\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]"+
		"\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3^\3^\3^\3_\3_\3_\3_\3_\3_\3_\3_"+
		"\3_\3_\3_\3_\3_\3`\3`\3`\3`\3`\3`\3`\3`\3`\3a\3a\3a\3a\3a\3a\3a\3a\3a"+
		"\3a\3a\3a\3a\3a\3a\3b\3b\3b\3b\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3c\3c\3c"+
		"\3c\3c\3c\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3d\3e\3e\3e\3e\3e"+
		"\3e\3f\3f\3f\3f\3f\3f\3g\3g\3g\3g\3h\3h\3h\3h\3h\3i\3i\3i\3i\3i\3i\3i"+
		"\3j\3j\3j\3j\3j\3k\3k\3k\3k\3k\3l\3l\3l\3l\3l\3l\3l\3l\3l\3m\3m\3m\3m"+
		"\3m\3m\3m\3m\3m\3m\3m\3n\3n\3n\3n\3n\3n\3n\3o\3o\3o\3o\3o\3o\3o\3o\3o"+
		"\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3p\3p\3p\3q\3q\3q\3q\3q\3r\3r\3r\3r\3r"+
		"\3r\3r\3r\3r\3r\3r\3s\3s\3s\3s\3t\3t\3t\3t\3t\3t\3u\3u\3u\3u\3u\3u\3v"+
		"\3v\3v\3v\3v\3v\3w\3w\3w\3w\3w\3w\3x\3x\3x\3x\3x\3x\3y\3y\3y\3y\3y\3y"+
		"\3y\3z\3z\3z\3z\3z\3z\3{\3{\3{\3{\3{\3{\3|\3|\3|\3|\3|\3}\3}\3}\3}\3}"+
		"\3~\3~\3~\3~\3~\3\177\3\177\3\177\3\177\3\177\3\177\3\177\3\u0080\3\u0080"+
		"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0081\3\u0081"+
		"\3\u0081\3\u0081\3\u0081\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0083"+
		"\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0084\3\u0084"+
		"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0085\3\u0085\3\u0085"+
		"\3\u0085\3\u0085\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0087\3\u0087"+
		"\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0088"+
		"\3\u0088\3\u0088\3\u0088\3\u0088\3\u0089\3\u0089\3\u0089\3\u0089\3\u0089"+
		"\3\u0089\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a"+
		"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b"+
		"\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c"+
		"\3\u008c\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d"+
		"\3\u008d\3\u008d\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008f"+
		"\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u0090\3\u0090\3\u0090\3\u0090"+
		"\3\u0090\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091"+
		"\3\u0091\3\u0091\3\u0091\3\u0091\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092"+
		"\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095\3\u0095"+
		"\3\u0095\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0097"+
		"\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097"+
		"\3\u0097\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098"+
		"\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099"+
		"\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a"+
		"\3\u009a\3\u009a\3\u009a\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b"+
		"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009c\3\u009c\3\u009c"+
		"\3\u009c\3\u009c\3\u009c\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009e"+
		"\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009f\3\u009f\3\u009f"+
		"\3\u009f\3\u009f\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0"+
		"\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1"+
		"\3\u00a1\3\u00a1\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2"+
		"\3\u00a2\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a4\3\u00a4\3\u00a4"+
		"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4"+
		"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5"+
		"\3\u00a5\3\u00a5\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6"+
		"\3\u00a6\3\u00a6\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7"+
		"\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a9"+
		"\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00aa\3\u00aa"+
		"\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa"+
		"\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa"+
		"\3\u00ab\6\u00ab\u06ac\n\u00ab\r\u00ab\16\u00ab\u06ad\3\u00ab\7\u00ab"+
		"\u06b1\n\u00ab\f\u00ab\16\u00ab\u06b4\13\u00ab\3\u00ac\6\u00ac\u06b7\n"+
		"\u00ac\r\u00ac\16\u00ac\u06b8\3\u00ac\3\u00ac\3\u00ad\3\u00ad\3\u00ad"+
		"\3\u00ad\7\u00ad\u06c1\n\u00ad\f\u00ad\16\u00ad\u06c4\13\u00ad\3\u00ad"+
		"\5\u00ad\u06c7\n\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ae\3\u00ae"+
		"\3\u00ae\3\u00ae\7\u00ae\u06d1\n\u00ae\f\u00ae\16\u00ae\u06d4\13\u00ae"+
		"\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u06d2\2\u00af\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W\2Y\2[\2]\2_\2a\2c-e.g/i\60k\61m\62o\63q\64s\65u\66w"+
		"\67y8{9}:\177;\u0081<\u0083=\u0085>\u0087?\u0089@\u008bA\u008dB\u008f"+
		"C\u0091D\u0093E\u0095F\u0097G\u0099H\u009bI\u009dJ\u009fK\u00a1L\u00a3"+
		"M\u00a5N\u00a7O\u00a9P\u00abQ\u00adR\u00afS\u00b1T\u00b3U\u00b5V\u00b7"+
		"W\u00b9X\u00bbY\u00bdZ\u00bf[\u00c1\\\u00c3]\u00c5^\u00c7_\u00c9`\u00cb"+
		"a\u00cdb\u00cfc\u00d1d\u00d3e\u00d5f\u00d7g\u00d9h\u00dbi\u00ddj\u00df"+
		"k\u00e1l\u00e3m\u00e5n\u00e7o\u00e9p\u00ebq\u00edr\u00efs\u00f1t\u00f3"+
		"u\u00f5v\u00f7w\u00f9x\u00fby\u00fdz\u00ff{\u0101|\u0103}\u0105~\u0107"+
		"\177\u0109\u0080\u010b\u0081\u010d\u0082\u010f\u0083\u0111\u0084\u0113"+
		"\u0085\u0115\u0086\u0117\u0087\u0119\u0088\u011b\u0089\u011d\u008a\u011f"+
		"\u008b\u0121\u008c\u0123\u008d\u0125\u008e\u0127\u008f\u0129\u0090\u012b"+
		"\u0091\u012d\u0092\u012f\u0093\u0131\u0094\u0133\u0095\u0135\u0096\u0137"+
		"\u0097\u0139\u0098\u013b\u0099\u013d\u009a\u013f\u009b\u0141\u009c\u0143"+
		"\u009d\u0145\u009e\u0147\u009f\u0149\u00a0\u014b\u00a1\u014d\u00a2\u014f"+
		"\u00a3\u0151\u00a4\u0153\u00a5\u0155\u00a6\u0157\u00a7\u0159\u00a8\u015b"+
		"\u00a9\3\2\n\3\2\62\63\3\2\62;\3\2\63;\3\2\62\62\6\2\62;C\\aac|\n\2))"+
		",,/\60\62<C\\aac|\u0080\u0080\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u06dd"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3"+
		"\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2"+
		"\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2"+
		"\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d"+
		"\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2"+
		"\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f"+
		"\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2"+
		"\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1"+
		"\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2"+
		"\2\2\u00bb\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3"+
		"\3\2\2\2\2\u00c5\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2"+
		"\2\2\u00cd\3\2\2\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5"+
		"\3\2\2\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2"+
		"\2\2\u00df\3\2\2\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7"+
		"\3\2\2\2\2\u00e9\3\2\2\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2\2\2\u00ef\3\2\2"+
		"\2\2\u00f1\3\2\2\2\2\u00f3\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7\3\2\2\2\2\u00f9"+
		"\3\2\2\2\2\u00fb\3\2\2\2\2\u00fd\3\2\2\2\2\u00ff\3\2\2\2\2\u0101\3\2\2"+
		"\2\2\u0103\3\2\2\2\2\u0105\3\2\2\2\2\u0107\3\2\2\2\2\u0109\3\2\2\2\2\u010b"+
		"\3\2\2\2\2\u010d\3\2\2\2\2\u010f\3\2\2\2\2\u0111\3\2\2\2\2\u0113\3\2\2"+
		"\2\2\u0115\3\2\2\2\2\u0117\3\2\2\2\2\u0119\3\2\2\2\2\u011b\3\2\2\2\2\u011d"+
		"\3\2\2\2\2\u011f\3\2\2\2\2\u0121\3\2\2\2\2\u0123\3\2\2\2\2\u0125\3\2\2"+
		"\2\2\u0127\3\2\2\2\2\u0129\3\2\2\2\2\u012b\3\2\2\2\2\u012d\3\2\2\2\2\u012f"+
		"\3\2\2\2\2\u0131\3\2\2\2\2\u0133\3\2\2\2\2\u0135\3\2\2\2\2\u0137\3\2\2"+
		"\2\2\u0139\3\2\2\2\2\u013b\3\2\2\2\2\u013d\3\2\2\2\2\u013f\3\2\2\2\2\u0141"+
		"\3\2\2\2\2\u0143\3\2\2\2\2\u0145\3\2\2\2\2\u0147\3\2\2\2\2\u0149\3\2\2"+
		"\2\2\u014b\3\2\2\2\2\u014d\3\2\2\2\2\u014f\3\2\2\2\2\u0151\3\2\2\2\2\u0153"+
		"\3\2\2\2\2\u0155\3\2\2\2\2\u0157\3\2\2\2\2\u0159\3\2\2\2\2\u015b\3\2\2"+
		"\2\3\u015d\3\2\2\2\5\u016a\3\2\2\2\7\u0175\3\2\2\2\t\u0178\3\2\2\2\13"+
		"\u017a\3\2\2\2\r\u0182\3\2\2\2\17\u0184\3\2\2\2\21\u0186\3\2\2\2\23\u018d"+
		"\3\2\2\2\25\u0198\3\2\2\2\27\u019a\3\2\2\2\31\u019c\3\2\2\2\33\u01b2\3"+
		"\2\2\2\35\u01bc\3\2\2\2\37\u01c5\3\2\2\2!\u01cc\3\2\2\2#\u01d2\3\2\2\2"+
		"%\u01e0\3\2\2\2\'\u01ee\3\2\2\2)\u01fc\3\2\2\2+\u0207\3\2\2\2-\u0213\3"+
		"\2\2\2/\u0221\3\2\2\2\61\u022d\3\2\2\2\63\u0237\3\2\2\2\65\u0242\3\2\2"+
		"\2\67\u0247\3\2\2\29\u0256\3\2\2\2;\u026c\3\2\2\2=\u027e\3\2\2\2?\u0289"+
		"\3\2\2\2A\u0298\3\2\2\2C\u02a6\3\2\2\2E\u02a9\3\2\2\2G\u02ac\3\2\2\2I"+
		"\u02b0\3\2\2\2K\u02b7\3\2\2\2M\u02bc\3\2\2\2O\u02c2\3\2\2\2Q\u02c8\3\2"+
		"\2\2S\u02cd\3\2\2\2U\u02d4\3\2\2\2W\u02e1\3\2\2\2Y\u02e3\3\2\2\2[\u02e5"+
		"\3\2\2\2]\u02e7\3\2\2\2_\u02e9\3\2\2\2a\u02eb\3\2\2\2c\u02ed\3\2\2\2e"+
		"\u02ef\3\2\2\2g\u02f4\3\2\2\2i\u02fa\3\2\2\2k\u0301\3\2\2\2m\u030a\3\2"+
		"\2\2o\u0310\3\2\2\2q\u0317\3\2\2\2s\u0321\3\2\2\2u\u0327\3\2\2\2w\u032b"+
		"\3\2\2\2y\u0335\3\2\2\2{\u033d\3\2\2\2}\u0343\3\2\2\2\177\u034a\3\2\2"+
		"\2\u0081\u0351\3\2\2\2\u0083\u0358\3\2\2\2\u0085\u035c\3\2\2\2\u0087\u0366"+
		"\3\2\2\2\u0089\u036c\3\2\2\2\u008b\u0375\3\2\2\2\u008d\u0379\3\2\2\2\u008f"+
		"\u0380\3\2\2\2\u0091\u0384\3\2\2\2\u0093\u0391\3\2\2\2\u0095\u0398\3\2"+
		"\2\2\u0097\u03a2\3\2\2\2\u0099\u03af\3\2\2\2\u009b\u03b5\3\2\2\2\u009d"+
		"\u03ba\3\2\2\2\u009f\u03c3\3\2\2\2\u00a1\u03ce\3\2\2\2\u00a3\u03d6\3\2"+
		"\2\2\u00a5\u03de\3\2\2\2\u00a7\u03e4\3\2\2\2\u00a9\u03ef\3\2\2\2\u00ab"+
		"\u0400\3\2\2\2\u00ad\u0408\3\2\2\2\u00af\u0410\3\2\2\2\u00b1\u0419\3\2"+
		"\2\2\u00b3\u0423\3\2\2\2\u00b5\u042e\3\2\2\2\u00b7\u043b\3\2\2\2\u00b9"+
		"\u0447\3\2\2\2\u00bb\u044f\3\2\2\2\u00bd\u0458\3\2\2\2\u00bf\u0465\3\2"+
		"\2\2\u00c1\u046e\3\2\2\2\u00c3\u047d\3\2\2\2\u00c5\u0486\3\2\2\2\u00c7"+
		"\u0491\3\2\2\2\u00c9\u04a0\3\2\2\2\u00cb\u04a6\3\2\2\2\u00cd\u04ac\3\2"+
		"\2\2\u00cf\u04b0\3\2\2\2\u00d1\u04b5\3\2\2\2\u00d3\u04bc\3\2\2\2\u00d5"+
		"\u04c1\3\2\2\2\u00d7\u04c6\3\2\2\2\u00d9\u04cf\3\2\2\2\u00db\u04da\3\2"+
		"\2\2\u00dd\u04e1\3\2\2\2\u00df\u04ee\3\2\2\2\u00e1\u04f7\3\2\2\2\u00e3"+
		"\u04fc\3\2\2\2\u00e5\u0507\3\2\2\2\u00e7\u050b\3\2\2\2\u00e9\u0511\3\2"+
		"\2\2\u00eb\u0517\3\2\2\2\u00ed\u051d\3\2\2\2\u00ef\u0523\3\2\2\2\u00f1"+
		"\u0529\3\2\2\2\u00f3\u0530\3\2\2\2\u00f5\u0536\3\2\2\2\u00f7\u053c\3\2"+
		"\2\2\u00f9\u0541\3\2\2\2\u00fb\u0546\3\2\2\2\u00fd\u054b\3\2\2\2\u00ff"+
		"\u0552\3\2\2\2\u0101\u055b\3\2\2\2\u0103\u0560\3\2\2\2\u0105\u0565\3\2"+
		"\2\2\u0107\u056d\3\2\2\2\u0109\u0575\3\2\2\2\u010b\u057a\3\2\2\2\u010d"+
		"\u057f\3\2\2\2\u010f\u0589\3\2\2\2\u0111\u058e\3\2\2\2\u0113\u0594\3\2"+
		"\2\2\u0115\u059c\3\2\2\2\u0117\u05a5\3\2\2\2\u0119\u05af\3\2\2\2\u011b"+
		"\u05b9\3\2\2\2\u011d\u05bf\3\2\2\2\u011f\u05c5\3\2\2\2\u0121\u05cb\3\2"+
		"\2\2\u0123\u05d6\3\2\2\2\u0125\u05db\3\2\2\2\u0127\u05e1\3\2\2\2\u0129"+
		"\u05e9\3\2\2\2\u012b\u05ee\3\2\2\2\u012d\u05f5\3\2\2\2\u012f\u0600\3\2"+
		"\2\2\u0131\u0608\3\2\2\2\u0133\u0611\3\2\2\2\u0135\u061d\3\2\2\2\u0137"+
		"\u0629\3\2\2\2\u0139\u062f\3\2\2\2\u013b\u0634\3\2\2\2\u013d\u063b\3\2"+
		"\2\2\u013f\u0640\3\2\2\2\u0141\u0647\3\2\2\2\u0143\u0652\3\2\2\2\u0145"+
		"\u065a\3\2\2\2\u0147\u065f\3\2\2\2\u0149\u066f\3\2\2\2\u014b\u0676\3\2"+
		"\2\2\u014d\u067f\3\2\2\2\u014f\u0686\3\2\2\2\u0151\u068e\3\2\2\2\u0153"+
		"\u0696\3\2\2\2\u0155\u06ab\3\2\2\2\u0157\u06b6\3\2\2\2\u0159\u06bc\3\2"+
		"\2\2\u015b\u06cc\3\2\2\2\u015d\u015e\7c\2\2\u015e\u015f\7e\2\2\u015f\u0160"+
		"\7v\2\2\u0160\u0161\7k\2\2\u0161\u0162\7q\2\2\u0162\u0163\7p\2\2\u0163"+
		"\u0164\7a\2\2\u0164\u0165\7d\2\2\u0165\u0166\7g\2\2\u0166\u0167\7i\2\2"+
		"\u0167\u0168\7k\2\2\u0168\u0169\7p\2\2\u0169\4\3\2\2\2\u016a\u016b\7c"+
		"\2\2\u016b\u016c\7e\2\2\u016c\u016d\7v\2\2\u016d\u016e\7k\2\2\u016e\u016f"+
		"\7q\2\2\u016f\u0170\7p\2\2\u0170\u0171\7a\2\2\u0171\u0172\7g\2\2\u0172"+
		"\u0173\7p\2\2\u0173\u0174\7f\2\2\u0174\6\3\2\2\2\u0175\u0176\7k\2\2\u0176"+
		"\u0177\7f\2\2\u0177\b\3\2\2\2\u0178\u0179\7?\2\2\u0179\n\3\2\2\2\u017a"+
		"\u017b\7j\2\2\u017b\u017c\7c\2\2\u017c\u017d\7p\2\2\u017d\u017e\7f\2\2"+
		"\u017e\u017f\7n\2\2\u017f\u0180\7g\2\2\u0180\u0181\7t\2\2\u0181\f\3\2"+
		"\2\2\u0182\u0183\7]\2\2\u0183\16\3\2\2\2\u0184\u0185\7_\2\2\u0185\20\3"+
		"\2\2\2\u0186\u0187\7q\2\2\u0187\u0188\7r\2\2\u0188\u0189\7v\2\2\u0189"+
		"\u018a\7k\2\2\u018a\u018b\7q\2\2\u018b\u018c\7p\2\2\u018c\22\3\2\2\2\u018d"+
		"\u018e\7u\2\2\u018e\u018f\7n\2\2\u018f\u0190\7q\2\2\u0190\u0191\7v\2\2"+
		"\u0191\u0192\7a\2\2\u0192\u0193\7n\2\2\u0193\u0194\7j\2\2\u0194\u0195"+
		"\7c\2\2\u0195\u0196\7p\2\2\u0196\u0197\7f\2\2\u0197\24\3\2\2\2\u0198\u0199"+
		"\7}\2\2\u0199\26\3\2\2\2\u019a\u019b\7\177\2\2\u019b\30\3\2\2\2\u019c"+
		"\u019d\7d\2\2\u019d\u019e\7c\2\2\u019e\u019f\7u\2\2\u019f\u01a0\7g\2\2"+
		"\u01a0\u01a1\7a\2\2\u01a1\u01a2\7c\2\2\u01a2\u01a3\7v\2\2\u01a3\u01a4"+
		"\7v\2\2\u01a4\u01a5\7t\2\2\u01a5\u01a6\7k\2\2\u01a6\u01a7\7d\2\2\u01a7"+
		"\u01a8\7w\2\2\u01a8\u01a9\7v\2\2\u01a9\u01aa\7g\2\2\u01aa\u01ab\7a\2\2"+
		"\u01ab\u01ac\7c\2\2\u01ac\u01ad\7v\2\2\u01ad\u01ae\7v\2\2\u01ae\u01af"+
		"\7c\2\2\u01af\u01b0\7e\2\2\u01b0\u01b1\7m\2\2\u01b1\32\3\2\2\2\u01b2\u01b3"+
		"\7U\2\2\u01b3\u01b4\7K\2\2\u01b4\u01b5\7V\2\2\u01b5\u01b6\7a\2\2\u01b6"+
		"\u01b7\7U\2\2\u01b7\u01b8\7V\2\2\u01b8\u01b9\7C\2\2\u01b9\u01ba\7P\2\2"+
		"\u01ba\u01bb\7F\2\2\u01bb\34\3\2\2\2\u01bc\u01bd\7Y\2\2\u01bd\u01be\7"+
		"C\2\2\u01be\u01bf\7N\2\2\u01bf\u01c0\7M\2\2\u01c0\u01c1\7a\2\2\u01c1\u01c2"+
		"\7T\2\2\u01c2\u01c3\7W\2\2\u01c3\u01c4\7P\2\2\u01c4\36\3\2\2\2\u01c5\u01c6"+
		"\7C\2\2\u01c6\u01c7\7V\2\2\u01c7\u01c8\7V\2\2\u01c8\u01c9\7C\2\2\u01c9"+
		"\u01ca\7E\2\2\u01ca\u01cb\7M\2\2\u01cb \3\2\2\2\u01cc\u01cd\7V\2\2\u01cd"+
		"\u01ce\7T\2\2\u01ce\u01cf\7C\2\2\u01cf\u01d0\7F\2\2\u01d0\u01d1\7G\2\2"+
		"\u01d1\"\3\2\2\2\u01d2\u01d3\7E\2\2\u01d3\u01d4\7N\2\2\u01d4\u01d5\7K"+
		"\2\2\u01d5\u01d6\7G\2\2\u01d6\u01d7\7P\2\2\u01d7\u01d8\7V\2\2\u01d8\u01d9"+
		"\7a\2\2\u01d9\u01da\7C\2\2\u01da\u01db\7E\2\2\u01db\u01dc\7V\2\2\u01dc"+
		"\u01dd\7K\2\2\u01dd\u01de\7Q\2\2\u01de\u01df\7P\2\2\u01df$\3\2\2\2\u01e0"+
		"\u01e1\7R\2\2\u01e1\u01e2\7T\2\2\u01e2\u01e3\7K\2\2\u01e3\u01e4\7X\2\2"+
		"\u01e4\u01e5\7C\2\2\u01e5\u01e6\7V\2\2\u01e6\u01e7\7G\2\2\u01e7\u01e8"+
		"\7a\2\2\u01e8\u01e9\7U\2\2\u01e9\u01ea\7V\2\2\u01ea\u01eb\7Q\2\2\u01eb"+
		"\u01ec\7T\2\2\u01ec\u01ed\7G\2\2\u01ed&\3\2\2\2\u01ee\u01ef\7U\2\2\u01ef"+
		"\u01f0\7Q\2\2\u01f0\u01f1\7E\2\2\u01f1\u01f2\7K\2\2\u01f2\u01f3\7C\2\2"+
		"\u01f3\u01f4\7N\2\2\u01f4\u01f5\7a\2\2\u01f5\u01f6\7C\2\2\u01f6\u01f7"+
		"\7E\2\2\u01f7\u01f8\7V\2\2\u01f8\u01f9\7K\2\2\u01f9\u01fa\7Q\2\2\u01fa"+
		"\u01fb\7P\2\2\u01fb(\3\2\2\2\u01fc\u01fd\7R\2\2\u01fd\u01fe\7G\2\2\u01fe"+
		"\u01ff\7V\2\2\u01ff\u0200\7a\2\2\u0200\u0201\7C\2\2\u0201\u0202\7E\2\2"+
		"\u0202\u0203\7V\2\2\u0203\u0204\7K\2\2\u0204\u0205\7Q\2\2\u0205\u0206"+
		"\7P\2\2\u0206*\3\2\2\2\u0207\u0208\7R\2\2\u0208\u0209\7G\2\2\u0209\u020a"+
		"\7V\2\2\u020a\u020b\7a\2\2\u020b\u020c\7F\2\2\u020c\u020d\7G\2\2\u020d"+
		"\u020e\7R\2\2\u020e\u020f\7Q\2\2\u020f\u0210\7U\2\2\u0210\u0211\7K\2\2"+
		"\u0211\u0212\7V\2\2\u0212,\3\2\2\2\u0213\u0214\7U\2\2\u0214\u0215\7W\2"+
		"\2\u0215\u0216\7O\2\2\u0216\u0217\7O\2\2\u0217\u0218\7Q\2\2\u0218\u0219"+
		"\7P\2\2\u0219\u021a\7a\2\2\u021a\u021b\7C\2\2\u021b\u021c\7E\2\2\u021c"+
		"\u021d\7V\2\2\u021d\u021e\7K\2\2\u021e\u021f\7Q\2\2\u021f\u0220\7P\2\2"+
		"\u0220.\3\2\2\2\u0221\u0222\7R\2\2\u0222\u0223\7T\2\2\u0223\u0224\7K\2"+
		"\2\u0224\u0225\7X\2\2\u0225\u0226\7C\2\2\u0226\u0227\7V\2\2\u0227\u0228"+
		"\7G\2\2\u0228\u0229\7a\2\2\u0229\u022a\7D\2\2\u022a\u022b\7W\2\2\u022b"+
		"\u022c\7[\2\2\u022c\60\3\2\2\2\u022d\u022e\7O\2\2\u022e\u022f\7C\2\2\u022f"+
		"\u0230\7M\2\2\u0230\u0231\7G\2\2\u0231\u0232\7a\2\2\u0232\u0233\7K\2\2"+
		"\u0233\u0234\7V\2\2\u0234\u0235\7G\2\2\u0235\u0236\7O\2\2\u0236\62\3\2"+
		"\2\2\u0237\u0238\7O\2\2\u0238\u0239\7C\2\2\u0239\u023a\7M\2\2\u023a\u023b"+
		"\7G\2\2\u023b\u023c\7a\2\2\u023c\u023d\7K\2\2\u023d\u023e\7V\2\2\u023e"+
		"\u023f\7G\2\2\u023f\u0240\7O\2\2\u0240\u0241\7\64\2\2\u0241\64\3\2\2\2"+
		"\u0242\u0243\7T\2\2\u0243\u0244\7K\2\2\u0244\u0245\7F\2\2\u0245\u0246"+
		"\7G\2\2\u0246\66\3\2\2\2\u0247\u0248\7U\2\2\u0248\u0249\7W\2\2\u0249\u024a"+
		"\7O\2\2\u024a\u024b\7O\2\2\u024b\u024c\7Q\2\2\u024c\u024d\7P\2\2\u024d"+
		"\u024e\7a\2\2\u024e\u024f\7F\2\2\u024f\u0250\7G\2\2\u0250\u0251\7U\2\2"+
		"\u0251\u0252\7R\2\2\u0252\u0253\7C\2\2\u0253\u0254\7Y\2\2\u0254\u0255"+
		"\7P\2\2\u02558\3\2\2\2\u0256\u0257\7R\2\2\u0257\u0258\7C\2\2\u0258\u0259"+
		"\7E\2\2\u0259\u025a\7M\2\2\u025a\u025b\7C\2\2\u025b\u025c\7I\2\2\u025c"+
		"\u025d\7G\2\2\u025d\u025e\7a\2\2\u025e\u025f\7R\2\2\u025f\u0260\7T\2\2"+
		"\u0260\u0261\7K\2\2\u0261\u0262\7X\2\2\u0262\u0263\7C\2\2\u0263\u0264"+
		"\7V\2\2\u0264\u0265\7G\2\2\u0265\u0266\7a\2\2\u0266\u0267\7U\2\2\u0267"+
		"\u0268\7V\2\2\u0268\u0269\7Q\2\2\u0269\u026a\7T\2\2\u026a\u026b\7G\2\2"+
		"\u026b:\3\2\2\2\u026c\u026d\7V\2\2\u026d\u026e\7G\2\2\u026e\u026f\7N\2"+
		"\2\u026f\u0270\7G\2\2\u0270\u0271\7R\2\2\u0271\u0272\7Q\2\2\u0272\u0273"+
		"\7T\2\2\u0273\u0274\7V\2\2\u0274\u0275\7a\2\2\u0275\u0276\7D\2\2\u0276"+
		"\u0277\7Q\2\2\u0277\u0278\7Q\2\2\u0278\u0279\7M\2\2\u0279\u027a\7O\2\2"+
		"\u027a\u027b\7C\2\2\u027b\u027c\7T\2\2\u027c\u027d\7M\2\2\u027d<\3\2\2"+
		"\2\u027e\u027f\7D\2\2\u027f\u0280\7Q\2\2\u0280\u0281\7V\2\2\u0281\u0282"+
		"\7a\2\2\u0282\u0283\7T\2\2\u0283\u0284\7G\2\2\u0284\u0285\7R\2\2\u0285"+
		"\u0286\7Q\2\2\u0286\u0287\7T\2\2\u0287\u0288\7V\2\2\u0288>\3\2\2\2\u0289"+
		"\u028a\7C\2\2\u028a\u028b\7K\2\2\u028b\u028c\7T\2\2\u028c\u028d\7U\2\2"+
		"\u028d\u028e\7J\2\2\u028e\u028f\7K\2\2\u028f\u0290\7R\2\2\u0290\u0291"+
		"\7a\2\2\u0291\u0292\7C\2\2\u0292\u0293\7E\2\2\u0293\u0294\7V\2\2\u0294"+
		"\u0295\7K\2\2\u0295\u0296\7Q\2\2\u0296\u0297\7P\2\2\u0297@\3\2\2\2\u0298"+
		"\u0299\7E\2\2\u0299\u029a\7Q\2\2\u029a\u029b\7W\2\2\u029b\u029c\7R\2\2"+
		"\u029c\u029d\7N\2\2\u029d\u029e\7G\2\2\u029e\u029f\7a\2\2\u029f\u02a0"+
		"\7C\2\2\u02a0\u02a1\7E\2\2\u02a1\u02a2\7V\2\2\u02a2\u02a3\7K\2\2\u02a3"+
		"\u02a4\7Q\2\2\u02a4\u02a5\7P\2\2\u02a5B\3\2\2\2\u02a6\u02a7\7B\2\2\u02a7"+
		"\u02a8\5\u0155\u00ab\2\u02a8D\3\2\2\2\u02a9\u02aa\5[.\2\u02aaF\3\2\2\2"+
		"\u02ab\u02ad\5Y-\2\u02ac\u02ab\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02ae"+
		"\3\2\2\2\u02ae\u02af\5W,\2\u02afH\3\2\2\2\u02b0\u02b1\5G$\2\u02b1\u02b3"+
		"\7\60\2\2\u02b2\u02b4\5]/\2\u02b3\u02b2\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5"+
		"\u02b3\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6J\3\2\2\2\u02b7\u02b8\7h\2\2\u02b8"+
		"\u02b9\7k\2\2\u02b9\u02ba\7t\2\2\u02ba\u02bb\7g\2\2\u02bbL\3\2\2\2\u02bc"+
		"\u02bd\7y\2\2\u02bd\u02be\7c\2\2\u02be\u02bf\7v\2\2\u02bf\u02c0\7g\2\2"+
		"\u02c0\u02c1\7t\2\2\u02c1N\3\2\2\2\u02c2\u02c3\7g\2\2\u02c3\u02c4\7c\2"+
		"\2\u02c4\u02c5\7t\2\2\u02c5\u02c6\7v\2\2\u02c6\u02c7\7j\2\2\u02c7P\3\2"+
		"\2\2\u02c8\u02c9\7y\2\2\u02c9\u02ca\7k\2\2\u02ca\u02cb\7p\2\2\u02cb\u02cc"+
		"\7f\2\2\u02ccR\3\2\2\2\u02cd\u02ce\7w\2\2\u02ce\u02cf\7p\2\2\u02cf\u02d0"+
		"\7j\2\2\u02d0\u02d1\7q\2\2\u02d1\u02d2\7n\2\2\u02d2\u02d3\7{\2\2\u02d3"+
		"T\3\2\2\2\u02d4\u02d5\7j\2\2\u02d5\u02d6\7q\2\2\u02d6\u02d7\7n\2\2\u02d7"+
		"\u02d8\7{\2\2\u02d8V\3\2\2\2\u02d9\u02e2\5a\61\2\u02da\u02de\5_\60\2\u02db"+
		"\u02dd\5]/\2\u02dc\u02db\3\2\2\2\u02dd\u02e0\3\2\2\2\u02de\u02dc\3\2\2"+
		"\2\u02de\u02df\3\2\2\2\u02df\u02e2\3\2\2\2\u02e0\u02de\3\2\2\2\u02e1\u02d9"+
		"\3\2\2\2\u02e1\u02da\3\2\2\2\u02e2X\3\2\2\2\u02e3\u02e4\7/\2\2\u02e4Z"+
		"\3\2\2\2\u02e5\u02e6\t\2\2\2\u02e6\\\3\2\2\2\u02e7\u02e8\t\3\2\2\u02e8"+
		"^\3\2\2\2\u02e9\u02ea\t\4\2\2\u02ea`\3\2\2\2\u02eb\u02ec\t\5\2\2\u02ec"+
		"b\3\2\2\2\u02ed\u02ee\7=\2\2\u02eed\3\2\2\2\u02ef\u02f0\7p\2\2\u02f0\u02f1"+
		"\7q\2\2\u02f1\u02f2\7p\2\2\u02f2\u02f3\7g\2\2\u02f3f\3\2\2\2\u02f4\u02f5"+
		"\7h\2\2\u02f5\u02f6\7c\2\2\u02f6\u02f7\7k\2\2\u02f7\u02f8\7t\2\2\u02f8"+
		"\u02f9\7{\2\2\u02f9h\3\2\2\2\u02fa\u02fb\7c\2\2\u02fb\u02fc\7p\2\2\u02fc"+
		"\u02fd\7k\2\2\u02fd\u02fe\7o\2\2\u02fe\u02ff\7c\2\2\u02ff\u0300\7n\2\2"+
		"\u0300j\3\2\2\2\u0301\u0302\7j\2\2\u0302\u0303\7w\2\2\u0303\u0304\7o\2"+
		"\2\u0304\u0305\7c\2\2\u0305\u0306\7p\2\2\u0306\u0307\7q\2\2\u0307\u0308"+
		"\7k\2\2\u0308\u0309\7f\2\2\u0309l\3\2\2\2\u030a\u030b\7r\2\2\u030b\u030c"+
		"\7n\2\2\u030c\u030d\7c\2\2\u030d\u030e\7p\2\2\u030e\u030f\7v\2\2\u030f"+
		"n\3\2\2\2\u0310\u0311\7w\2\2\u0311\u0312\7p\2\2\u0312\u0313\7f\2\2\u0313"+
		"\u0314\7g\2\2\u0314\u0315\7c\2\2\u0315\u0316\7f\2\2\u0316p\3\2\2\2\u0317"+
		"\u0318\7e\2\2\u0318\u0319\7q\2\2\u0319\u031a\7p\2\2\u031a\u031b\7u\2\2"+
		"\u031b\u031c\7v\2\2\u031c\u031d\7t\2\2\u031d\u031e\7w\2\2\u031e\u031f"+
		"\7e\2\2\u031f\u0320\7v\2\2\u0320r\3\2\2\2\u0321\u0322\7d\2\2\u0322\u0323"+
		"\7g\2\2\u0323\u0324\7c\2\2\u0324\u0325\7u\2\2\u0325\u0326\7v\2\2\u0326"+
		"t\3\2\2\2\u0327\u0328\7d\2\2\u0328\u0329\7w\2\2\u0329\u032a\7i\2\2\u032a"+
		"v\3\2\2\2\u032b\u032c\7g\2\2\u032c\u032d\7n\2\2\u032d\u032e\7g\2\2\u032e"+
		"\u032f\7o\2\2\u032f\u0330\7g\2\2\u0330\u0331\7p\2\2\u0331\u0332\7v\2\2"+
		"\u0332\u0333\7c\2\2\u0333\u0334\7n\2\2\u0334x\3\2\2\2\u0335\u0336\7f\2"+
		"\2\u0336\u0337\7g\2\2\u0337\u0338\7o\2\2\u0338\u0339\7q\2\2\u0339\u033a"+
		"\7p\2\2\u033a\u033b\7k\2\2\u033b\u033c\7e\2\2\u033cz\3\2\2\2\u033d\u033e"+
		"\7i\2\2\u033e\u033f\7k\2\2\u033f\u0340\7c\2\2\u0340\u0341\7p\2\2\u0341"+
		"\u0342\7v\2\2\u0342|\3\2\2\2\u0343\u0344\7f\2\2\u0344\u0345\7t\2\2\u0345"+
		"\u0346\7c\2\2\u0346\u0347\7i\2\2\u0347\u0348\7q\2\2\u0348\u0349\7p\2\2"+
		"\u0349~\3\2\2\2\u034a\u034b\7f\2\2\u034b\u034c\7k\2\2\u034c\u034d\7x\2"+
		"\2\u034d\u034e\7k\2\2\u034e\u034f\7p\2\2\u034f\u0350\7g\2\2\u0350\u0080"+
		"\3\2\2\2\u0351\u0352\7u\2\2\u0352\u0353\7w\2\2\u0353\u0354\7o\2\2\u0354"+
		"\u0355\7o\2\2\u0355\u0356\7q\2\2\u0356\u0357\7p\2\2\u0357\u0082\3\2\2"+
		"\2\u0358\u0359\7r\2\2\u0359\u035a\7g\2\2\u035a\u035b\7v\2\2\u035b\u0084"+
		"\3\2\2\2\u035c\u035d\7j\2\2\u035d\u035e\7q\2\2\u035e\u035f\7n\2\2\u035f"+
		"\u0360\7{\2\2\u0360\u0361\7v\2\2\u0361\u0362\7j\2\2\u0362\u0363\7k\2\2"+
		"\u0363\u0364\7p\2\2\u0364\u0365\7i\2\2\u0365\u0086\3\2\2\2\u0366\u0367"+
		"\7f\2\2\u0367\u0368\7y\2\2\u0368\u0369\7c\2\2\u0369\u036a\7t\2\2\u036a"+
		"\u036b\7h\2\2\u036b\u0088\3\2\2\2\u036c\u036d\7o\2\2\u036d\u036e\7g\2"+
		"\2\u036e\u036f\7t\2\2\u036f\u0370\7e\2\2\u0370\u0371\7j\2\2\u0371\u0372"+
		"\7c\2\2\u0372\u0373\7p\2\2\u0373\u0374\7v\2\2\u0374\u008a\3\2\2\2\u0375"+
		"\u0376\7g\2\2\u0376\u0377\7n\2\2\u0377\u0378\7h\2\2\u0378\u008c\3\2\2"+
		"\2\u0379\u037a\7m\2\2\u037a\u037b\7c\2\2\u037b\u037c\7o\2\2\u037c\u037d"+
		"\7c\2\2\u037d\u037e\7g\2\2\u037e\u037f\7n\2\2\u037f\u008e\3\2\2\2\u0380"+
		"\u0381\7q\2\2\u0381\u0382\7t\2\2\u0382\u0383\7e\2\2\u0383\u0090\3\2\2"+
		"\2\u0384\u0385\7u\2\2\u0385\u0386\7k\2\2\u0386\u0387\7g\2\2\u0387\u0388"+
		"\7i\2\2\u0388\u0389\7g\2\2\u0389\u038a\7a\2\2\u038a\u038b\7y\2\2\u038b"+
		"\u038c\7g\2\2\u038c\u038d\7c\2\2\u038d\u038e\7r\2\2\u038e\u038f\7q\2\2"+
		"\u038f\u0390\7p\2\2\u0390\u0092\3\2\2\2\u0391\u0392\7h\2\2\u0392\u0393"+
		"\7t\2\2\u0393\u0394\7k\2\2\u0394\u0395\7g\2\2\u0395\u0396\7p\2\2\u0396"+
		"\u0397\7f\2\2\u0397\u0094\3\2\2\2\u0398\u0399\7o\2\2\u0399\u039a\7g\2"+
		"\2\u039a\u039b\7t\2\2\u039b\u039c\7e\2\2\u039c\u039d\7g\2\2\u039d\u039e"+
		"\7p\2\2\u039e\u039f\7c\2\2\u039f\u03a0\7t\2\2\u03a0\u03a1\7{\2\2\u03a1"+
		"\u0096\3\2\2\2\u03a2\u03a3\7e\2\2\u03a3\u03a4\7c\2\2\u03a4\u03a5\7u\2"+
		"\2\u03a5\u03a6\7v\2\2\u03a6\u03a7\7n\2\2\u03a7\u03a8\7g\2\2\u03a8\u03a9"+
		"\7a\2\2\u03a9\u03aa\7i\2\2\u03aa\u03ab\7w\2\2\u03ab\u03ac\7c\2\2\u03ac"+
		"\u03ad\7t\2\2\u03ad\u03ae\7f\2\2\u03ae\u0098\3\2\2\2\u03af\u03b0\7j\2"+
		"\2\u03b0\u03b1\7w\2\2\u03b1\u03b2\7o\2\2\u03b2\u03b3\7c\2\2\u03b3\u03b4"+
		"\7p\2\2\u03b4\u009a\3\2\2\2\u03b5\u03b6\7d\2\2\u03b6\u03b7\7q\2\2\u03b7"+
		"\u03b8\7u\2\2\u03b8\u03b9\7u\2\2\u03b9\u009c\3\2\2\2\u03ba\u03bb\7|\2"+
		"\2\u03bb\u03bc\7|\2\2\u03bc\u03bd\7q\2\2\u03bd\u03be\7n\2\2\u03be\u03bf"+
		"\7f\2\2\u03bf\u03c0\7c\2\2\u03c0\u03c1\7i\2\2\u03c1\u03c2\7w\2\2\u03c2"+
		"\u009e\3\2\2\2\u03c3\u03c4\7y\2\2\u03c4\u03c5\7q\2\2\u03c5\u03c6\7t\2"+
		"\2\u03c6\u03c7\7n\2\2\u03c7\u03c8\7f\2\2\u03c8\u03c9\7a\2\2\u03c9\u03ca"+
		"\7v\2\2\u03ca\u03cb\7t\2\2\u03cb\u03cc\7c\2\2\u03cc\u03cd\7r\2\2\u03cd"+
		"\u00a0\3\2\2\2\u03ce\u03cf\7o\2\2\u03cf\u03d0\7q\2\2\u03d0\u03d1\7p\2"+
		"\2\u03d1\u03d2\7t\2\2\u03d2\u03d3\7c\2\2\u03d3\u03d4\7e\2\2\u03d4\u03d5"+
		"\7g\2\2\u03d5\u00a2\3\2\2\2\u03d6\u03d7\7f\2\2\u03d7\u03d8\7c\2\2\u03d8"+
		"\u03d9\7t\2\2\u03d9\u03da\7m\2\2\u03da\u03db\7g\2\2\u03db\u03dc\7n\2\2"+
		"\u03dc\u03dd\7h\2\2\u03dd\u00a4\3\2\2\2\u03de\u03df\7i\2\2\u03df\u03e0"+
		"\7w\2\2\u03e0\u03e1\7c\2\2\u03e1\u03e2\7t\2\2\u03e2\u03e3\7f\2\2\u03e3"+
		"\u00a6\3\2\2\2\u03e4\u03e5\7v\2\2\u03e5\u03e6\7g\2\2\u03e6\u03e7\7n\2"+
		"\2\u03e7\u03e8\7g\2\2\u03e8\u03e9\7r\2\2\u03e9\u03ea\7q\2\2\u03ea\u03eb"+
		"\7t\2\2\u03eb\u03ec\7v\2\2\u03ec\u03ed\7g\2\2\u03ed\u03ee\7t\2\2\u03ee"+
		"\u00a8\3\2\2\2\u03ef\u03f0\7y\2\2\u03f0\u03f1\7c\2\2\u03f1\u03f2\7t\2"+
		"\2\u03f2\u03f3\7g\2\2\u03f3\u03f4\7j\2\2\u03f4\u03f5\7q\2\2\u03f5\u03f6"+
		"\7w\2\2\u03f6\u03f7\7u\2\2\u03f7\u03f8\7g\2\2\u03f8\u03f9\7a\2\2\u03f9"+
		"\u03fa\7m\2\2\u03fa\u03fb\7g\2\2\u03fb\u03fc\7g\2\2\u03fc\u03fd\7r\2\2"+
		"\u03fd\u03fe\7g\2\2\u03fe\u03ff\7t\2\2\u03ff\u00aa\3\2\2\2\u0400\u0401"+
		"\7y\2\2\u0401\u0402\7c\2\2\u0402\u0403\7t\2\2\u0403\u0404\7t\2\2\u0404"+
		"\u0405\7k\2\2\u0405\u0406\7q\2\2\u0406\u0407\7t\2\2\u0407\u00ac\3\2\2"+
		"\2\u0408\u0409\7e\2\2\u0409\u040a\7k\2\2\u040a\u040b\7v\2\2\u040b\u040c"+
		"\7k\2\2\u040c\u040d\7|\2\2\u040d\u040e\7g\2\2\u040e\u040f\7p\2\2\u040f"+
		"\u00ae\3\2\2\2\u0410\u0411\7v\2\2\u0411\u0412\7t\2\2\u0412\u0413\7g\2"+
		"\2\u0413\u0414\7c\2\2\u0414\u0415\7u\2\2\u0415\u0416\7w\2\2\u0416\u0417"+
		"\7t\2\2\u0417\u0418\7g\2\2\u0418\u00b0\3\2\2\2\u0419\u041a\7h\2\2\u041a"+
		"\u041b\7k\2\2\u041b\u041c\7g\2\2\u041c\u041d\7n\2\2\u041d\u041e\7f\2\2"+
		"\u041e\u041f\7d\2\2\u041f\u0420\7q\2\2\u0420\u0421\7u\2\2\u0421\u0422"+
		"\7u\2\2\u0422\u00b2\3\2\2\2\u0423\u0424\7d\2\2\u0424\u0425\7n\2\2\u0425"+
		"\u0426\7c\2\2\u0426\u0427\7e\2\2\u0427\u0428\7m\2\2\u0428\u0429\7u\2\2"+
		"\u0429\u042a\7o\2\2\u042a\u042b\7k\2\2\u042b\u042c\7v\2\2\u042c\u042d"+
		"\7j\2\2\u042d\u00b4\3\2\2\2\u042e\u042f\7i\2\2\u042f\u0430\7w\2\2\u0430"+
		"\u0431\7k\2\2\u0431\u0432\7n\2\2\u0432\u0433\7f\2\2\u0433\u0434\7a\2\2"+
		"\u0434\u0435\7o\2\2\u0435\u0436\7c\2\2\u0436\u0437\7u\2\2\u0437\u0438"+
		"\7v\2\2\u0438\u0439\7g\2\2\u0439\u043a\7t\2\2\u043a\u00b6\3\2\2\2\u043b"+
		"\u043c\7i\2\2\u043c\u043d\7w\2\2\u043d\u043e\7k\2\2\u043e\u043f\7n\2\2"+
		"\u043f\u0440\7f\2\2\u0440\u0441\7a\2\2\u0441\u0442\7e\2\2\u0442\u0443"+
		"\7q\2\2\u0443\u0444\7c\2\2\u0444\u0445\7e\2\2\u0445\u0446\7j\2\2\u0446"+
		"\u00b8\3\2\2\2\u0447\u0448\7r\2\2\u0448\u0449\7e\2\2\u0449\u044a\7a\2"+
		"\2\u044a\u044b\7v\2\2\u044b\u044c\7t\2\2\u044c\u044d\7c\2\2\u044d\u044e"+
		"\7r\2\2\u044e\u00ba\3\2\2\2\u044f\u0450\7z\2\2\u0450\u0451\7o\2\2\u0451"+
		"\u0452\7c\2\2\u0452\u0453\7u\2\2\u0453\u0454\7v\2\2\u0454\u0455\7t\2\2"+
		"\u0455\u0456\7g\2\2\u0456\u0457\7g\2\2\u0457\u00bc\3\2\2\2\u0458\u0459"+
		"\7f\2\2\u0459\u045a\7q\2\2\u045a\u045b\7r\2\2\u045b\u045c\7r\2\2\u045c"+
		"\u045d\7g\2\2\u045d\u045e\7n\2\2\u045e\u045f\7i\2\2\u045f\u0460\7c\2\2"+
		"\u0460\u0461\7p\2\2\u0461\u0462\7i\2\2\u0462\u0463\7g\2\2\u0463\u0464"+
		"\7t\2\2\u0464\u00be\3\2\2\2\u0465\u0466\7q\2\2\u0466\u0467\7y\2\2\u0467"+
		"\u0468\7p\2\2\u0468\u0469\7v\2\2\u0469\u046a\7j\2\2\u046a\u046b\7k\2\2"+
		"\u046b\u046c\7p\2\2\u046c\u046d\7i\2\2\u046d\u00c0\3\2\2\2\u046e\u046f"+
		"\7u\2\2\u046f\u0470\7k\2\2\u0470\u0471\7g\2\2\u0471\u0472\7i\2\2\u0472"+
		"\u0473\7g\2\2\u0473\u0474\7a\2\2\u0474\u0475\7c\2\2\u0475\u0476\7v\2\2"+
		"\u0476\u0477\7v\2\2\u0477\u0478\7c\2\2\u0478\u0479\7e\2\2\u0479\u047a"+
		"\7m\2\2\u047a\u047b\7g\2\2\u047b\u047c\7t\2\2\u047c\u00c2\3\2\2\2\u047d"+
		"\u047e\7o\2\2\u047e\u047f\7t\2\2\u047f\u0480\7m\2\2\u0480\u0481\7g\2\2"+
		"\u0481\u0482\7g\2\2\u0482\u0483\7r\2\2\u0483\u0484\7g\2\2\u0484\u0485"+
		"\7t\2\2\u0485\u00c4\3\2\2\2\u0486\u0487\7e\2\2\u0487\u0488\7q\2\2\u0488"+
		"\u0489\7n\2\2\u0489\u048a\7n\2\2\u048a\u048b\7g\2\2\u048b\u048c\7e\2\2"+
		"\u048c\u048d\7v\2\2\u048d\u048e\7k\2\2\u048e\u048f\7q\2\2\u048f\u0490"+
		"\7p\2\2\u0490\u00c6\3\2\2\2\u0491\u0492\7r\2\2\u0492\u0493\7c\2\2\u0493"+
		"\u0494\7e\2\2\u0494\u0495\7m\2\2\u0495\u0496\7c\2\2\u0496\u0497\7i\2\2"+
		"\u0497\u0498\7g\2\2\u0498\u0499\7a\2\2\u0499\u049a\7m\2\2\u049a\u049b"+
		"\7g\2\2\u049b\u049c\7g\2\2\u049c\u049d\7r\2\2\u049d\u049e\7g\2\2\u049e"+
		"\u049f\7t\2\2\u049f\u00c8\3\2\2\2\u04a0\u04a1\7u\2\2\u04a1\u04a2\7y\2"+
		"\2\u04a2\u04a3\7q\2\2\u04a3\u04a4\7t\2\2\u04a4\u04a5\7f\2\2\u04a5\u00ca"+
		"\3\2\2\2\u04a6\u04a7\7d\2\2\u04a7\u04a8\7n\2\2\u04a8\u04a9\7w\2\2\u04a9"+
		"\u04aa\7p\2\2\u04aa\u04ab\7v\2\2\u04ab\u00cc\3\2\2\2\u04ac\u04ad\7d\2"+
		"\2\u04ad\u04ae\7q\2\2\u04ae\u04af\7y\2\2\u04af\u00ce\3\2\2\2\u04b0\u04b1"+
		"\7r\2\2\u04b1\u04b2\7q\2\2\u04b2\u04b3\7n\2\2\u04b3\u04b4\7g\2\2\u04b4"+
		"\u00d0\3\2\2\2\u04b5\u04b6\7f\2\2\u04b6\u04b7\7c\2\2\u04b7\u04b8\7i\2"+
		"\2\u04b8\u04b9\7i\2\2\u04b9\u04ba\7g\2\2\u04ba\u04bb\7t\2\2\u04bb\u00d2"+
		"\3\2\2\2\u04bc\u04bd\7f\2\2\u04bd\u04be\7w\2\2\u04be\u04bf\7c\2\2\u04bf"+
		"\u04c0\7n\2\2\u04c0\u00d4\3\2\2\2\u04c1\u04c2\7h\2\2\u04c2\u04c3\7k\2"+
		"\2\u04c3\u04c4\7u\2\2\u04c4\u04c5\7v\2\2\u04c5\u00d6\3\2\2\2\u04c6\u04c7"+
		"\7f\2\2\u04c7\u04c8\7w\2\2\u04c8\u04c9\7c\2\2\u04c9\u04ca\7n\2\2\u04ca"+
		"\u04cb\7h\2\2\u04cb\u04cc\7k\2\2\u04cc\u04cd\7u\2\2\u04cd\u04ce\7v\2\2"+
		"\u04ce\u00d8\3\2\2\2\u04cf\u04d0\7h\2\2\u04d0\u04d1\7k\2\2\u04d1\u04d2"+
		"\7u\2\2\u04d2\u04d3\7j\2\2\u04d3\u04d4\7k\2\2\u04d4\u04d5\7p\2\2\u04d5"+
		"\u04d6\7i\2\2\u04d6\u04d7\7t\2\2\u04d7\u04d8\7q\2\2\u04d8\u04d9\7f\2\2"+
		"\u04d9\u00da\3\2\2\2\u04da\u04db\7t\2\2\u04db\u04dc\7c\2\2\u04dc\u04dd"+
		"\7r\2\2\u04dd\u04de\7k\2\2\u04de\u04df\7g\2\2\u04df\u04e0\7t\2\2\u04e0"+
		"\u00dc\3\2\2\2\u04e1\u04e2\7c\2\2\u04e2\u04e3\7p\2\2\u04e3\u04e4\7e\2"+
		"\2\u04e4\u04e5\7k\2\2\u04e5\u04e6\7g\2\2\u04e6\u04e7\7p\2\2\u04e7\u04e8"+
		"\7v\2\2\u04e8\u04e9\7u\2\2\u04e9\u04ea\7y\2\2\u04ea\u04eb\7q\2\2\u04eb"+
		"\u04ec\7t\2\2\u04ec\u04ed\7f\2\2\u04ed\u00de\3\2\2\2\u04ee\u04ef\7e\2"+
		"\2\u04ef\u04f0\7t\2\2\u04f0\u04f1\7q\2\2\u04f1\u04f2\7u\2\2\u04f2\u04f3"+
		"\7u\2\2\u04f3\u04f4\7d\2\2\u04f4\u04f5\7q\2\2\u04f5\u04f6\7y\2\2\u04f6"+
		"\u00e0\3\2\2\2\u04f7\u04f8\7h\2\2\u04f8\u04f9\7n\2\2\u04f9\u04fa\7c\2"+
		"\2\u04fa\u04fb\7i\2\2\u04fb\u00e2\3\2\2\2\u04fc\u04fd\7f\2\2\u04fd\u04fe"+
		"\7w\2\2\u04fe\u04ff\7c\2\2\u04ff\u0500\7n\2\2\u0500\u0501\7f\2\2\u0501"+
		"\u0502\7c\2\2\u0502\u0503\7i\2\2\u0503\u0504\7i\2\2\u0504\u0505\7g\2\2"+
		"\u0505\u0506\7t\2\2\u0506\u00e4\3\2\2\2\u0507\u0508\7g\2\2\u0508\u0509"+
		"\7v\2\2\u0509\u050a\7e\2\2\u050a\u00e6\3\2\2\2\u050b\u050c\7n\2\2\u050c"+
		"\u050d\7k\2\2\u050d\u050e\7i\2\2\u050e\u050f\7j\2\2\u050f\u0510\7v\2\2"+
		"\u0510\u00e8\3\2\2\2\u0511\u0512\7j\2\2\u0512\u0513\7g\2\2\u0513\u0514"+
		"\7c\2\2\u0514\u0515\7x\2\2\u0515\u0516\7{\2\2\u0516\u00ea\3\2\2\2\u0517"+
		"\u0518\7o\2\2\u0518\u0519\7c\2\2\u0519\u051a\7i\2\2\u051a\u051b\7k\2\2"+
		"\u051b\u051c\7e\2\2\u051c\u00ec\3\2\2\2\u051d\u051e\7u\2\2\u051e\u051f"+
		"\7k\2\2\u051f\u0520\7i\2\2\u0520\u0521\7k\2\2\u0521\u0522\7n\2\2\u0522"+
		"\u00ee\3\2\2\2\u0523\u0524\7t\2\2\u0524\u0525\7j\2\2\u0525\u0526\7c\2"+
		"\2\u0526\u0527\7p\2\2\u0527\u0528\7f\2\2\u0528\u00f0\3\2\2\2\u0529\u052a"+
		"\7n\2\2\u052a\u052b\7t\2\2\u052b\u052c\7j\2\2\u052c\u052d\7c\2\2\u052d"+
		"\u052e\7p\2\2\u052e\u052f\7f\2\2\u052f\u00f2\3\2\2\2\u0530\u0531\7n\2"+
		"\2\u0531\u0532\7j\2\2\u0532\u0533\7c\2\2\u0533\u0534\7p\2\2\u0534\u0535"+
		"\7f\2\2\u0535\u00f4\3\2\2\2\u0536\u0537\7e\2\2\u0537\u0538\7j\2\2\u0538"+
		"\u0539\7g\2\2\u0539\u053a\7u\2\2\u053a\u053b\7v\2\2\u053b\u00f6\3\2\2"+
		"\2\u053c\u053d\7n\2\2\u053d\u053e\7g\2\2\u053e\u053f\7i\2\2\u053f\u0540"+
		"\7u\2\2\u0540\u00f8\3\2\2\2\u0541\u0542\7h\2\2\u0542\u0543\7g\2\2\u0543"+
		"\u0544\7g\2\2\u0544\u0545\7v\2\2\u0545\u00fa\3\2\2\2\u0546\u0547\7j\2"+
		"\2\u0547\u0548\7g\2\2\u0548\u0549\7c\2\2\u0549\u054a\7f\2\2\u054a\u00fc"+
		"\3\2\2\2\u054b\u054c\7i\2\2\u054c\u054d\7n\2\2\u054d\u054e\7q\2\2\u054e"+
		"\u054f\7x\2\2\u054f\u0550\7g\2\2\u0550\u0551\7u\2\2\u0551\u00fe\3\2\2"+
		"\2\u0552\u0553\7q\2\2\u0553\u0554\7p\2\2\u0554\u0555\7g\2\2\u0555\u0556"+
		"\7r\2\2\u0556\u0557\7k\2\2\u0557\u0558\7g\2\2\u0558\u0559\7e\2\2\u0559"+
		"\u055a\7g\2\2\u055a\u0100\3\2\2\2\u055b\u055c\7t\2\2\u055c\u055d\7g\2"+
		"\2\u055d\u055e\7c\2\2\u055e\u055f\7t\2\2\u055f\u0102\3\2\2\2\u0560\u0561"+
		"\7n\2\2\u0561\u0562\7g\2\2\u0562\u0563\7c\2\2\u0563\u0564\7t\2\2\u0564"+
		"\u0104\3\2\2\2\u0565\u0566\7t\2\2\u0566\u0567\7h\2\2\u0567\u0568\7k\2"+
		"\2\u0568\u0569\7p\2\2\u0569\u056a\7i\2\2\u056a\u056b\7g\2\2\u056b\u056c"+
		"\7t\2\2\u056c\u0106\3\2\2\2\u056d\u056e\7n\2\2\u056e\u056f\7h\2\2\u056f"+
		"\u0570\7k\2\2\u0570\u0571\7p\2\2\u0571\u0572\7i\2\2\u0572\u0573\7g\2\2"+
		"\u0573\u0574\7t\2\2\u0574\u0108\3\2\2\2\u0575\u0576\7p\2\2\u0576\u0577"+
		"\7g\2\2\u0577\u0578\7e\2\2\u0578\u0579\7m\2\2\u0579\u010a\3\2\2\2\u057a"+
		"\u057b\7d\2\2\u057b\u057c\7c\2\2\u057c\u057d\7e\2\2\u057d\u057e\7m\2\2"+
		"\u057e\u010c\3\2\2\2\u057f\u0580\7w\2\2\u0580\u0581\7p\2\2\u0581\u0582"+
		"\7f\2\2\u0582\u0583\7g\2\2\u0583\u0584\7t\2\2\u0584\u0585\7y\2\2\u0585"+
		"\u0586\7g\2\2\u0586\u0587\7c\2\2\u0587\u0588\7t\2\2\u0588\u010e\3\2\2"+
		"\2\u0589\u058a\7j\2\2\u058a\u058b\7c\2\2\u058b\u058c\7k\2\2\u058c\u058d"+
		"\7t\2\2\u058d\u0110\3\2\2\2\u058e\u058f\7j\2\2\u058f\u0590\7c\2\2\u0590"+
		"\u0591\7k\2\2\u0591\u0592\7t\2\2\u0592\u0593\7\64\2\2\u0593\u0112\3\2"+
		"\2\2\u0594\u0595\7j\2\2\u0595\u0596\7c\2\2\u0596\u0597\7k\2\2\u0597\u0598"+
		"\7t\2\2\u0598\u0599\7c\2\2\u0599\u059a\7n\2\2\u059a\u059b\7n\2\2\u059b"+
		"\u0114\3\2\2\2\u059c\u059d\7c\2\2\u059d\u059e\7n\2\2\u059e\u059f\7n\2"+
		"\2\u059f\u05a0\7f\2\2\u05a0\u05a1\7t\2\2\u05a1\u05a2\7g\2\2\u05a2\u05a3"+
		"\7u\2\2\u05a3\u05a4\7u\2\2\u05a4\u0116\3\2\2\2\u05a5\u05a6\7t\2\2\u05a6"+
		"\u05a7\7d\2\2\u05a7\u05a8\7t\2\2\u05a8\u05a9\7c\2\2\u05a9\u05aa\7e\2\2"+
		"\u05aa\u05ab\7g\2\2\u05ab\u05ac\7n\2\2\u05ac\u05ad\7g\2\2\u05ad\u05ae"+
		"\7v\2\2\u05ae\u0118\3\2\2\2\u05af\u05b0\7n\2\2\u05b0\u05b1\7d\2\2\u05b1"+
		"\u05b2\7t\2\2\u05b2\u05b3\7c\2\2\u05b3\u05b4\7e\2\2\u05b4\u05b5\7g\2\2"+
		"\u05b5\u05b6\7n\2\2\u05b6\u05b7\7g\2\2\u05b7\u05b8\7v\2\2\u05b8\u011a"+
		"\3\2\2\2\u05b9\u05ba\7y\2\2\u05ba\u05bb\7c\2\2\u05bb\u05bc\7k\2\2\u05bc"+
		"\u05bd\7u\2\2\u05bd\u05be\7v\2\2\u05be\u011c\3\2\2\2\u05bf\u05c0\7f\2"+
		"\2\u05c0\u05c1\7g\2\2\u05c1\u05c2\7e\2\2\u05c2\u05c3\7q\2\2\u05c3\u05c4"+
		"\7\63\2\2\u05c4\u011e\3\2\2\2\u05c5\u05c6\7u\2\2\u05c6\u05c7\7v\2\2\u05c7"+
		"\u05c8\7g\2\2\u05c8\u05c9\7g\2\2\u05c9\u05ca\7n\2\2\u05ca\u0120\3\2\2"+
		"\2\u05cb\u05cc\7h\2\2\u05cc\u05cd\7k\2\2\u05cd\u05ce\7p\2\2\u05ce\u05cf"+
		"\7g\2\2\u05cf\u05d0\7a\2\2\u05d0\u05d1\7u\2\2\u05d1\u05d2\7v\2\2\u05d2"+
		"\u05d3\7g\2\2\u05d3\u05d4\7g\2\2\u05d4\u05d5\7n\2\2\u05d5\u0122\3\2\2"+
		"\2\u05d6\u05d7\7y\2\2\u05d7\u05d8\7q\2\2\u05d8\u05d9\7q\2\2\u05d9\u05da"+
		"\7f\2\2\u05da\u0124\3\2\2\2\u05db\u05dc\7e\2\2\u05dc\u05dd\7n\2\2\u05dd"+
		"\u05de\7q\2\2\u05de\u05df\7v\2\2\u05df\u05e0\7j\2\2\u05e0\u0126\3\2\2"+
		"\2\u05e1\u05e2\7n\2\2\u05e2\u05e3\7g\2\2\u05e3\u05e4\7c\2\2\u05e4\u05e5"+
		"\7v\2\2\u05e5\u05e6\7j\2\2\u05e6\u05e7\7g\2\2\u05e7\u05e8\7t\2\2\u05e8"+
		"\u0128\3\2\2\2\u05e9\u05ea\7d\2\2\u05ea\u05eb\7q\2\2\u05eb\u05ec\7p\2"+
		"\2\u05ec\u05ed\7g\2\2\u05ed\u012a\3\2\2\2\u05ee\u05ef\7d\2\2\u05ef\u05f0"+
		"\7t\2\2\u05f0\u05f1\7q\2\2\u05f1\u05f2\7p\2\2\u05f2\u05f3\7|\2\2\u05f3"+
		"\u05f4\7g\2\2\u05f4\u012c\3\2\2\2\u05f5\u05f6\7q\2\2\u05f6\u05f7\7t\2"+
		"\2\u05f7\u05f8\7k\2\2\u05f8\u05f9\7j\2\2\u05f9\u05fa\7c\2\2\u05fa\u05fb"+
		"\7t\2\2\u05fb\u05fc\7w\2\2\u05fc\u05fd\7m\2\2\u05fd\u05fe\7q\2\2\u05fe"+
		"\u05ff\7p\2\2\u05ff\u012e\3\2\2\2\u0600\u0601\7o\2\2\u0601\u0602\7k\2"+
		"\2\u0602\u0603\7v\2\2\u0603\u0604\7j\2\2\u0604\u0605\7t\2\2\u0605\u0606"+
		"\7k\2\2\u0606\u0607\7n\2\2\u0607\u0130\3\2\2\2\u0608\u0609\7f\2\2\u0609"+
		"\u060a\7c\2\2\u060a\u060b\7o\2\2\u060b\u060c\7c\2\2\u060c\u060d\7u\2\2"+
		"\u060d\u060e\7e\2\2\u060e\u060f\7w\2\2\u060f\u0610\7u\2\2\u0610\u0132"+
		"\3\2\2\2\u0611\u0612\7c\2\2\u0612\u0613\7f\2\2\u0613\u0614\7c\2\2\u0614"+
		"\u0615\7o\2\2\u0615\u0616\7c\2\2\u0616\u0617\7p\2\2\u0617\u0618\7v\2\2"+
		"\u0618\u0619\7c\2\2\u0619\u061a\7k\2\2\u061a\u061b\7v\2\2\u061b\u061c"+
		"\7g\2\2\u061c\u0134\3\2\2\2\u061d\u061e\7d\2\2\u061e\u061f\7n\2\2\u061f"+
		"\u0620\7q\2\2\u0620\u0621\7q\2\2\u0621\u0622\7f\2\2\u0622\u0623\7a\2\2"+
		"\u0623\u0624\7u\2\2\u0624\u0625\7v\2\2\u0625\u0626\7g\2\2\u0626\u0627"+
		"\7g\2\2\u0627\u0628\7n\2\2\u0628\u0136\3\2\2\2\u0629\u062a\7r\2\2\u062a"+
		"\u062b\7c\2\2\u062b\u062c\7r\2\2\u062c\u062d\7g\2\2\u062d\u062e\7t\2\2"+
		"\u062e\u0138\3\2\2\2\u062f\u0630\7i\2\2\u0630\u0631\7q\2\2\u0631\u0632"+
		"\7n\2\2\u0632\u0633\7f\2\2\u0633\u013a\3\2\2\2\u0634\u0635\7n\2\2\u0635"+
		"\u0636\7k\2\2\u0636\u0637\7s\2\2\u0637\u0638\7w\2\2\u0638\u0639\7k\2\2"+
		"\u0639\u063a\7f\2\2\u063a\u013c\3\2\2\2\u063b\u063c\7h\2\2\u063c\u063d"+
		"\7k\2\2\u063d\u063e\7u\2\2\u063e\u063f\7j\2\2\u063f\u013e\3\2\2\2\u0640"+
		"\u0641\7u\2\2\u0641\u0642\7k\2\2\u0642\u0643\7n\2\2\u0643\u0644\7x\2\2"+
		"\u0644\u0645\7g\2\2\u0645\u0646\7t\2\2\u0646\u0140\3\2\2\2\u0647\u0648"+
		"\7e\2\2\u0648\u0649\7j\2\2\u0649\u064a\7t\2\2\u064a\u064b\7{\2\2\u064b"+
		"\u064c\7u\2\2\u064c\u064d\7q\2\2\u064d\u064e\7n\2\2\u064e\u064f\7k\2\2"+
		"\u064f\u0650\7v\2\2\u0650\u0651\7g\2\2\u0651\u0142\3\2\2\2\u0652\u0653"+
		"\7e\2\2\u0653\u0654\7t\2\2\u0654\u0655\7{\2\2\u0655\u0656\7u\2\2\u0656"+
		"\u0657\7v\2\2\u0657\u0658\7c\2\2\u0658\u0659\7n\2\2\u0659\u0144\3\2\2"+
		"\2\u065a\u065b\7j\2\2\u065b\u065c\7q\2\2\u065c\u065d\7t\2\2\u065d\u065e"+
		"\7p\2\2\u065e\u0146\3\2\2\2\u065f\u0660\7u\2\2\u0660\u0661\7e\2\2\u0661"+
		"\u0662\7c\2\2\u0662\u0663\7n\2\2\u0663\u0664\7g\2\2\u0664\u0665\7a\2\2"+
		"\u0665\u0666\7q\2\2\u0666\u0667\7h\2\2\u0667\u0668\7a\2\2\u0668\u0669"+
		"\7f\2\2\u0669\u066a\7t\2\2\u066a\u066b\7c\2\2\u066b\u066c\7i\2\2\u066c"+
		"\u066d\7q\2\2\u066d\u066e\7p\2\2\u066e\u0148\3\2\2\2\u066f\u0670\7e\2"+
		"\2\u0670\u0671\7q\2\2\u0671\u0672\7v\2\2\u0672\u0673\7v\2\2\u0673\u0674"+
		"\7q\2\2\u0674\u0675\7p\2\2\u0675\u014a\3\2\2\2\u0676\u0677\7f\2\2\u0677"+
		"\u0678\7{\2\2\u0678\u0679\7g\2\2\u0679\u067a\7u\2\2\u067a\u067b\7v\2\2"+
		"\u067b\u067c\7w\2\2\u067c\u067d\7h\2\2\u067d\u067e\7h\2\2\u067e\u014c"+
		"\3\2\2\2\u067f\u0680\7e\2\2\u0680\u0681\7q\2\2\u0681\u0682\7d\2\2\u0682"+
		"\u0683\7y\2\2\u0683\u0684\7g\2\2\u0684\u0685\7d\2\2\u0685\u014e\3\2\2"+
		"\2\u0686\u0687\7t\2\2\u0687\u0688\7w\2\2\u0688\u0689\7p\2\2\u0689\u068a"+
		"\7g\2\2\u068a\u068b\7a\2\2\u068b\u068c\7z\2\2\u068c\u068d\7r\2\2\u068d"+
		"\u0150\3\2\2\2\u068e\u068f\7t\2\2\u068f\u0690\7w\2\2\u0690\u0691\7p\2"+
		"\2\u0691\u0692\7g\2\2\u0692\u0693\7a\2\2\u0693\u0694\7u\2\2\u0694\u0695"+
		"\7r\2\2\u0695\u0152\3\2\2\2\u0696\u0697\7t\2\2\u0697\u0698\7w\2\2\u0698"+
		"\u0699\7p\2\2\u0699\u069a\7g\2\2\u069a\u069b\7a\2\2\u069b\u069c\7t\2\2"+
		"\u069c\u069d\7g\2\2\u069d\u069e\7o\2\2\u069e\u069f\7q\2\2\u069f\u06a0"+
		"\7x\2\2\u06a0\u06a1\7g\2\2\u06a1\u06a2\7a\2\2\u06a2\u06a3\7r\2\2\u06a3"+
		"\u06a4\7g\2\2\u06a4\u06a5\7p\2\2\u06a5\u06a6\7c\2\2\u06a6\u06a7\7n\2\2"+
		"\u06a7\u06a8\7v\2\2\u06a8\u06a9\7{\2\2\u06a9\u0154\3\2\2\2\u06aa\u06ac"+
		"\t\6\2\2\u06ab\u06aa\3\2\2\2\u06ac\u06ad\3\2\2\2\u06ad\u06ab\3\2\2\2\u06ad"+
		"\u06ae\3\2\2\2\u06ae\u06b2\3\2\2\2\u06af\u06b1\t\7\2\2\u06b0\u06af\3\2"+
		"\2\2\u06b1\u06b4\3\2\2\2\u06b2\u06b0\3\2\2\2\u06b2\u06b3\3\2\2\2\u06b3"+
		"\u0156\3\2\2\2\u06b4\u06b2\3\2\2\2\u06b5\u06b7\t\b\2\2\u06b6\u06b5\3\2"+
		"\2\2\u06b7\u06b8\3\2\2\2\u06b8\u06b6\3\2\2\2\u06b8\u06b9\3\2\2\2\u06b9"+
		"\u06ba\3\2\2\2\u06ba\u06bb\b\u00ac\2\2\u06bb\u0158\3\2\2\2\u06bc\u06bd"+
		"\7\61\2\2\u06bd\u06be\7\61\2\2\u06be\u06c2\3\2\2\2\u06bf\u06c1\n\t\2\2"+
		"\u06c0\u06bf\3\2\2\2\u06c1\u06c4\3\2\2\2\u06c2\u06c0\3\2\2\2\u06c2\u06c3"+
		"\3\2\2\2\u06c3\u06c6\3\2\2\2\u06c4\u06c2\3\2\2\2\u06c5\u06c7\7\17\2\2"+
		"\u06c6\u06c5\3\2\2\2\u06c6\u06c7\3\2\2\2\u06c7\u06c8\3\2\2\2\u06c8\u06c9"+
		"\7\f\2\2\u06c9\u06ca\3\2\2\2\u06ca\u06cb\b\u00ad\2\2\u06cb\u015a\3\2\2"+
		"\2\u06cc\u06cd\7\61\2\2\u06cd\u06ce\7,\2\2\u06ce\u06d2\3\2\2\2\u06cf\u06d1"+
		"\13\2\2\2\u06d0\u06cf\3\2\2\2\u06d1\u06d4\3\2\2\2\u06d2\u06d3\3\2\2\2"+
		"\u06d2\u06d0\3\2\2\2\u06d3\u06d5\3\2\2\2\u06d4\u06d2\3\2\2\2\u06d5\u06d6"+
		"\7,\2\2\u06d6\u06d7\7\61\2\2\u06d7\u06d8\3\2\2\2\u06d8\u06d9\b\u00ae\2"+
		"\2\u06d9\u015c\3\2\2\2\r\2\u02ac\u02b5\u02de\u02e1\u06ad\u06b2\u06b8\u06c2"+
		"\u06c6\u06d2\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}