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
		SIEGE_WEAPON=78, FRIEND=79, MERCENARY=80, CASTLE_GUARD=81, HUMAN=82, BOSS=83, 
		ZZOLDAGU=84, WORLD_TRAP=85, MONRACE=86, DARKELF=87, GUARD=88, TELEPORTER=89, 
		WAREHOUSE_KEEPER=90, WARRIOR=91, CITIZEN=92, TREASURE=93, FIELDBOSS=94, 
		BLACKSMITH=95, GUILD_MASTER=96, GUILD_COACH=97, PC_TRAP=98, XMASTREE=99, 
		DOPPELGANGER=100, OWNTHING=101, SIEGE_ATTACKER=102, MRKEEPER=103, COLLECTION=104, 
		PACKAGE_KEEPER=105, SWORD=106, BLUNT=107, BOW=108, POLE=109, DAGGER=110, 
		DUAL=111, FIST=112, DUALFIST=113, FISHINGROD=114, RAPIER=115, ANCIENTSWORD=116, 
		CROSSBOW=117, FLAG=118, DUALDAGGER=119, ETC=120, LIGHT=121, HEAVY=122, 
		MAGIC=123, SIGIL=124, RHAND=125, LRHAND=126, LHAND=127, CHEST=128, LEGS=129, 
		FEET=130, HEAD=131, GLOVES=132, ONEPIECE=133, REAR=134, LEAR=135, RFINGER=136, 
		LFINGER=137, NECK=138, BACK=139, UNDERWEAR=140, HAIR=141, HAIR2=142, HAIRALL=143, 
		ALLDRESS=144, RBRACELET=145, LBRACELET=146, WAIST=147, DECO1=148, STEEL=149, 
		FINE_STEEL=150, WOOD=151, CLOTH=152, LEATHER=153, BONE=154, BRONZE=155, 
		ORIHARUKON=156, MITHRIL=157, DAMASCUS=158, ADAMANTAITE=159, BLOOD_STEEL=160, 
		PAPER=161, GOLD=162, LIQUID=163, FISH=164, SILVER=165, CHRYSOLITE=166, 
		CRYSTAL=167, HORN=168, SCALE_OF_DRAGON=169, COTTON=170, DYESTUFF=171, 
		COBWEB=172, RUNE_XP=173, RUNE_SP=174, RUNE_REMOVE_PENALTY=175, NAME=176, 
		WS=177, LINE_COMMENT=178, STAR_COMMENT=179;
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
		"'orc'", "'siege_weapon'", "'friend'", "'mercenary'", "'castle_guard'", 
		"'human'", "'boss'", "'zzoldagu'", "'world_trap'", "'monrace'", "'darkelf'", 
		"'guard'", "'teleporter'", "'warehouse_keeper'", "'warrior'", "'citizen'", 
		"'treasure'", "'fieldboss'", "'blacksmith'", "'guild_master'", "'guild_coach'", 
		"'pc_trap'", "'xmastree'", "'doppelganger'", "'ownthing'", "'siege_attacker'", 
		"'mrkeeper'", "'collection'", "'package_keeper'", "'sword'", "'blunt'", 
		"'bow'", "'pole'", "'dagger'", "'dual'", "'fist'", "'dualfist'", "'fishingrod'", 
		"'rapier'", "'ancientsword'", "'crossbow'", "'flag'", "'dualdagger'", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\u00b5\u06a0\b\1\4"+
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
		"\4\u00b2\t\u00b2\4\u00b3\t\u00b3\4\u00b4\t\u00b4\4\u00b5\t\u00b5\4\u00b6"+
		"\t\u00b6\4\u00b7\t\u00b7\4\u00b8\t\u00b8\4\u00b9\t\u00b9\4\u00ba\t\u00ba"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21"+
		"\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3 \3"+
		" \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3"+
		"\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3\'"+
		"\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3"+
		")\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3"+
		"+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\3-\3"+
		"-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3\60\3\60\3\61\5\61\u0278\n\61\3\61\3"+
		"\61\3\62\3\62\3\62\6\62\u027f\n\62\r\62\16\62\u0280\3\63\3\63\3\63\3\63"+
		"\3\63\3\64\3\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3\65\3\66"+
		"\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\3"+
		"8\39\39\39\79\u02a8\n9\f9\169\u02ab\139\59\u02ad\n9\3:\3:\3;\3;\3<\3<"+
		"\3=\3=\3>\3>\3?\3?\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3A\3B\3B\3B\3B"+
		"\3B\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3D\3D\3E\3E\3E\3E\3E"+
		"\3E\3E\3E\3E\3E\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3H\3H\3H\3H\3H\3H\3H\3H"+
		"\3H\3H\3I\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K"+
		"\3L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3N\3N\3N\3N\3O\3O\3O\3O\3O"+
		"\3O\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R"+
		"\3R\3S\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U"+
		"\3U\3U\3V\3V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X"+
		"\3X\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3[\3[\3["+
		"\3[\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]\3"+
		"]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3^\3^\3_\3_\3_\3_\3_\3_\3`\3`\3`\3"+
		"`\3`\3`\3`\3`\3`\3`\3`\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3a\3"+
		"a\3a\3b\3b\3b\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3c\3c\3c\3d\3d\3d\3d\3d\3"+
		"d\3d\3d\3d\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3f\3f\3f\3f\3f\3f\3f\3f\3f\3"+
		"f\3f\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3h\3h\3h\3h\3h\3h\3h\3h\3"+
		"h\3h\3h\3h\3i\3i\3i\3i\3i\3i\3i\3i\3j\3j\3j\3j\3j\3j\3j\3j\3j\3k\3k\3"+
		"k\3k\3k\3k\3k\3k\3k\3k\3k\3k\3k\3l\3l\3l\3l\3l\3l\3l\3l\3l\3m\3m\3m\3"+
		"m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3n\3n\3n\3n\3n\3n\3n\3n\3n\3o\3o\3"+
		"o\3o\3o\3o\3o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3"+
		"p\3q\3q\3q\3q\3q\3q\3r\3r\3r\3r\3r\3r\3s\3s\3s\3s\3t\3t\3t\3t\3t\3u\3"+
		"u\3u\3u\3u\3u\3u\3v\3v\3v\3v\3v\3w\3w\3w\3w\3w\3x\3x\3x\3x\3x\3x\3x\3"+
		"x\3x\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3y\3z\3z\3z\3z\3z\3z\3z\3{\3{\3{\3"+
		"{\3{\3{\3{\3{\3{\3{\3{\3{\3{\3|\3|\3|\3|\3|\3|\3|\3|\3|\3}\3}\3}\3}\3"+
		"}\3~\3~\3~\3~\3~\3~\3~\3~\3~\3~\3~\3\177\3\177\3\177\3\177\3\u0080\3\u0080"+
		"\3\u0080\3\u0080\3\u0080\3\u0080\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081"+
		"\3\u0081\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0083\3\u0083"+
		"\3\u0083\3\u0083\3\u0083\3\u0083\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084"+
		"\3\u0084\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0086"+
		"\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0087\3\u0087\3\u0087\3\u0087"+
		"\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0089\3\u0089"+
		"\3\u0089\3\u0089\3\u0089\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008b"+
		"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008c\3\u008c\3\u008c"+
		"\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008d\3\u008d\3\u008d"+
		"\3\u008d\3\u008d\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008f\3\u008f"+
		"\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u0090\3\u0090\3\u0090"+
		"\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091"+
		"\3\u0091\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\3\u0093\3\u0093\3\u0093"+
		"\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095"+
		"\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0097"+
		"\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0098"+
		"\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098"+
		"\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099"+
		"\3\u0099\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009b\3\u009b"+
		"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c"+
		"\3\u009c\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\3\u009d\3\u009d\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009f"+
		"\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u00a0\3\u00a0\3\u00a0\3\u00a0"+
		"\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1"+
		"\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a3\3\u00a3"+
		"\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3"+
		"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a5"+
		"\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a5\3\u00a6"+
		"\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6"+
		"\3\u00a6\3\u00a6\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7"+
		"\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a8\3\u00a8\3\u00a8\3\u00a8"+
		"\3\u00a8\3\u00a8\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00a9\3\u00aa\3\u00aa"+
		"\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00ab\3\u00ab\3\u00ab\3\u00ab"+
		"\3\u00ab\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ac\3\u00ad"+
		"\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad\3\u00ad"+
		"\3\u00ad\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae\3\u00ae"+
		"\3\u00af\3\u00af\3\u00af\3\u00af\3\u00af\3\u00b0\3\u00b0\3\u00b0\3\u00b0"+
		"\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0\3\u00b0"+
		"\3\u00b0\3\u00b0\3\u00b0\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1\3\u00b1"+
		"\3\u00b1\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2\3\u00b2"+
		"\3\u00b2\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b3\3\u00b4"+
		"\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b4\3\u00b5\3\u00b5"+
		"\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b6\3\u00b6\3\u00b6"+
		"\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6"+
		"\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b6\3\u00b7"+
		"\6\u00b7\u0672\n\u00b7\r\u00b7\16\u00b7\u0673\3\u00b7\7\u00b7\u0677\n"+
		"\u00b7\f\u00b7\16\u00b7\u067a\13\u00b7\3\u00b8\6\u00b8\u067d\n\u00b8\r"+
		"\u00b8\16\u00b8\u067e\3\u00b8\3\u00b8\3\u00b9\3\u00b9\3\u00b9\3\u00b9"+
		"\7\u00b9\u0687\n\u00b9\f\u00b9\16\u00b9\u068a\13\u00b9\3\u00b9\5\u00b9"+
		"\u068d\n\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00ba\3\u00ba\3\u00ba"+
		"\3\u00ba\7\u00ba\u0697\n\u00ba\f\u00ba\16\u00ba\u069a\13\u00ba\3\u00ba"+
		"\3\u00ba\3\u00ba\3\u00ba\3\u00ba\3\u0698\2\u00bb\3\3\5\4\7\5\t\6\13\7"+
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
		"\u015f\u00ab\u0161\u00ac\u0163\u00ad\u0165\u00ae\u0167\u00af\u0169\u00b0"+
		"\u016b\u00b1\u016d\u00b2\u016f\u00b3\u0171\u00b4\u0173\u00b5\3\2\n\3\2"+
		"\62\63\3\2\62;\3\2\63;\3\2\62\62\6\2\62;C\\aac|\n\2)),,/\60\62<C\\aac"+
		"|\u0080\u0080\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u06a3\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2"+
		"\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2"+
		"\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2}"+
		"\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2"+
		"\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f"+
		"\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2"+
		"\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1"+
		"\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2"+
		"\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3"+
		"\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2"+
		"\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5"+
		"\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2"+
		"\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7"+
		"\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2\2\2\u00df\3\2\2"+
		"\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9"+
		"\3\2\2\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2\2\2\u00ef\3\2\2\2\2\u00f1\3\2\2"+
		"\2\2\u00f3\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7\3\2\2\2\2\u00f9\3\2\2\2\2\u00fb"+
		"\3\2\2\2\2\u00fd\3\2\2\2\2\u00ff\3\2\2\2\2\u0101\3\2\2\2\2\u0103\3\2\2"+
		"\2\2\u0105\3\2\2\2\2\u0107\3\2\2\2\2\u0109\3\2\2\2\2\u010b\3\2\2\2\2\u010d"+
		"\3\2\2\2\2\u010f\3\2\2\2\2\u0111\3\2\2\2\2\u0113\3\2\2\2\2\u0115\3\2\2"+
		"\2\2\u0117\3\2\2\2\2\u0119\3\2\2\2\2\u011b\3\2\2\2\2\u011d\3\2\2\2\2\u011f"+
		"\3\2\2\2\2\u0121\3\2\2\2\2\u0123\3\2\2\2\2\u0125\3\2\2\2\2\u0127\3\2\2"+
		"\2\2\u0129\3\2\2\2\2\u012b\3\2\2\2\2\u012d\3\2\2\2\2\u012f\3\2\2\2\2\u0131"+
		"\3\2\2\2\2\u0133\3\2\2\2\2\u0135\3\2\2\2\2\u0137\3\2\2\2\2\u0139\3\2\2"+
		"\2\2\u013b\3\2\2\2\2\u013d\3\2\2\2\2\u013f\3\2\2\2\2\u0141\3\2\2\2\2\u0143"+
		"\3\2\2\2\2\u0145\3\2\2\2\2\u0147\3\2\2\2\2\u0149\3\2\2\2\2\u014b\3\2\2"+
		"\2\2\u014d\3\2\2\2\2\u014f\3\2\2\2\2\u0151\3\2\2\2\2\u0153\3\2\2\2\2\u0155"+
		"\3\2\2\2\2\u0157\3\2\2\2\2\u0159\3\2\2\2\2\u015b\3\2\2\2\2\u015d\3\2\2"+
		"\2\2\u015f\3\2\2\2\2\u0161\3\2\2\2\2\u0163\3\2\2\2\2\u0165\3\2\2\2\2\u0167"+
		"\3\2\2\2\2\u0169\3\2\2\2\2\u016b\3\2\2\2\2\u016d\3\2\2\2\2\u016f\3\2\2"+
		"\2\2\u0171\3\2\2\2\2\u0173\3\2\2\2\3\u0175\3\2\2\2\5\u0180\3\2\2\2\7\u0189"+
		"\3\2\2\2\t\u018c\3\2\2\2\13\u018e\3\2\2\2\r\u0193\3\2\2\2\17\u0198\3\2"+
		"\2\2\21\u019e\3\2\2\2\23\u01a4\3\2\2\2\25\u01a9\3\2\2\2\27\u01ab\3\2\2"+
		"\2\31\u01ad\3\2\2\2\33\u01b2\3\2\2\2\35\u01b4\3\2\2\2\37\u01b6\3\2\2\2"+
		"!\u01b8\3\2\2\2#\u01ba\3\2\2\2%\u01bc\3\2\2\2\'\u01be\3\2\2\2)\u01c0\3"+
		"\2\2\2+\u01c2\3\2\2\2-\u01c4\3\2\2\2/\u01c6\3\2\2\2\61\u01c9\3\2\2\2\63"+
		"\u01cc\3\2\2\2\65\u01cf\3\2\2\2\67\u01d1\3\2\2\29\u01d3\3\2\2\2;\u01d5"+
		"\3\2\2\2=\u01e0\3\2\2\2?\u01e2\3\2\2\2A\u01e4\3\2\2\2C\u01fa\3\2\2\2E"+
		"\u01ff\3\2\2\2G\u0208\3\2\2\2I\u0211\3\2\2\2K\u021a\3\2\2\2M\u0226\3\2"+
		"\2\2O\u022f\3\2\2\2Q\u0239\3\2\2\2S\u0241\3\2\2\2U\u0249\3\2\2\2W\u0257"+
		"\3\2\2\2Y\u0260\3\2\2\2[\u026c\3\2\2\2]\u0271\3\2\2\2_\u0274\3\2\2\2a"+
		"\u0277\3\2\2\2c\u027b\3\2\2\2e\u0282\3\2\2\2g\u0287\3\2\2\2i\u028d\3\2"+
		"\2\2k\u0293\3\2\2\2m\u0298\3\2\2\2o\u029f\3\2\2\2q\u02ac\3\2\2\2s\u02ae"+
		"\3\2\2\2u\u02b0\3\2\2\2w\u02b2\3\2\2\2y\u02b4\3\2\2\2{\u02b6\3\2\2\2}"+
		"\u02b8\3\2\2\2\177\u02ba\3\2\2\2\u0081\u02c0\3\2\2\2\u0083\u02c7\3\2\2"+
		"\2\u0085\u02d0\3\2\2\2\u0087\u02d6\3\2\2\2\u0089\u02dd\3\2\2\2\u008b\u02e7"+
		"\3\2\2\2\u008d\u02ed\3\2\2\2\u008f\u02f1\3\2\2\2\u0091\u02fb\3\2\2\2\u0093"+
		"\u0303\3\2\2\2\u0095\u0309\3\2\2\2\u0097\u0310\3\2\2\2\u0099\u0317\3\2"+
		"\2\2\u009b\u031e\3\2\2\2\u009d\u0322\3\2\2\2\u009f\u032c\3\2\2\2\u00a1"+
		"\u0332\3\2\2\2\u00a3\u033b\3\2\2\2\u00a5\u033f\3\2\2\2\u00a7\u0346\3\2"+
		"\2\2\u00a9\u034a\3\2\2\2\u00ab\u0357\3\2\2\2\u00ad\u035e\3\2\2\2\u00af"+
		"\u0368\3\2\2\2\u00b1\u0375\3\2\2\2\u00b3\u037b\3\2\2\2\u00b5\u0380\3\2"+
		"\2\2\u00b7\u0389\3\2\2\2\u00b9\u0394\3\2\2\2\u00bb\u039c\3\2\2\2\u00bd"+
		"\u03a4\3\2\2\2\u00bf\u03aa\3\2\2\2\u00c1\u03b5\3\2\2\2\u00c3\u03c6\3\2"+
		"\2\2\u00c5\u03ce\3\2\2\2\u00c7\u03d6\3\2\2\2\u00c9\u03df\3\2\2\2\u00cb"+
		"\u03e9\3\2\2\2\u00cd\u03f4\3\2\2\2\u00cf\u0401\3\2\2\2\u00d1\u040d\3\2"+
		"\2\2\u00d3\u0415\3\2\2\2\u00d5\u041e\3\2\2\2\u00d7\u042b\3\2\2\2\u00d9"+
		"\u0434\3\2\2\2\u00db\u0443\3\2\2\2\u00dd\u044c\3\2\2\2\u00df\u0457\3\2"+
		"\2\2\u00e1\u0466\3\2\2\2\u00e3\u046c\3\2\2\2\u00e5\u0472\3\2\2\2\u00e7"+
		"\u0476\3\2\2\2\u00e9\u047b\3\2\2\2\u00eb\u0482\3\2\2\2\u00ed\u0487\3\2"+
		"\2\2\u00ef\u048c\3\2\2\2\u00f1\u0495\3\2\2\2\u00f3\u04a0\3\2\2\2\u00f5"+
		"\u04a7\3\2\2\2\u00f7\u04b4\3\2\2\2\u00f9\u04bd\3\2\2\2\u00fb\u04c2\3\2"+
		"\2\2\u00fd\u04cd\3\2\2\2\u00ff\u04d1\3\2\2\2\u0101\u04d7\3\2\2\2\u0103"+
		"\u04dd\3\2\2\2\u0105\u04e3\3\2\2\2\u0107\u04e9\3\2\2\2\u0109\u04ef\3\2"+
		"\2\2\u010b\u04f6\3\2\2\2\u010d\u04fc\3\2\2\2\u010f\u0502\3\2\2\2\u0111"+
		"\u0507\3\2\2\2\u0113\u050c\3\2\2\2\u0115\u0511\3\2\2\2\u0117\u0518\3\2"+
		"\2\2\u0119\u0521\3\2\2\2\u011b\u0526\3\2\2\2\u011d\u052b\3\2\2\2\u011f"+
		"\u0533\3\2\2\2\u0121\u053b\3\2\2\2\u0123\u0540\3\2\2\2\u0125\u0545\3\2"+
		"\2\2\u0127\u054f\3\2\2\2\u0129\u0554\3\2\2\2\u012b\u055a\3\2\2\2\u012d"+
		"\u0562\3\2\2\2\u012f\u056b\3\2\2\2\u0131\u0575\3\2\2\2\u0133\u057f\3\2"+
		"\2\2\u0135\u0585\3\2\2\2\u0137\u058b\3\2\2\2\u0139\u0591\3\2\2\2\u013b"+
		"\u059c\3\2\2\2\u013d\u05a1\3\2\2\2\u013f\u05a7\3\2\2\2\u0141\u05af\3\2"+
		"\2\2\u0143\u05b4\3\2\2\2\u0145\u05bb\3\2\2\2\u0147\u05c6\3\2\2\2\u0149"+
		"\u05ce\3\2\2\2\u014b\u05d7\3\2\2\2\u014d\u05e3\3\2\2\2\u014f\u05ef\3\2"+
		"\2\2\u0151\u05f5\3\2\2\2\u0153\u05fa\3\2\2\2\u0155\u0601\3\2\2\2\u0157"+
		"\u0606\3\2\2\2\u0159\u060d\3\2\2\2\u015b\u0618\3\2\2\2\u015d\u0620\3\2"+
		"\2\2\u015f\u0625\3\2\2\2\u0161\u0635\3\2\2\2\u0163\u063c\3\2\2\2\u0165"+
		"\u0645\3\2\2\2\u0167\u064c\3\2\2\2\u0169\u0654\3\2\2\2\u016b\u065c\3\2"+
		"\2\2\u016d\u0671\3\2\2\2\u016f\u067c\3\2\2\2\u0171\u0682\3\2\2\2\u0173"+
		"\u0692\3\2\2\2\u0175\u0176\7f\2\2\u0176\u0177\7g\2\2\u0177\u0178\7e\2"+
		"\2\u0178\u0179\7q\2\2\u0179\u017a\7a\2\2\u017a\u017b\7d\2\2\u017b\u017c"+
		"\7g\2\2\u017c\u017d\7i\2\2\u017d\u017e\7k\2\2\u017e\u017f\7p\2\2\u017f"+
		"\4\3\2\2\2\u0180\u0181\7f\2\2\u0181\u0182\7g\2\2\u0182\u0183\7e\2\2\u0183"+
		"\u0184\7q\2\2\u0184\u0185\7a\2\2\u0185\u0186\7g\2\2\u0186\u0187\7p\2\2"+
		"\u0187\u0188\7f\2\2\u0188\6\3\2\2\2\u0189\u018a\7k\2\2\u018a\u018b\7f"+
		"\2\2\u018b\b\3\2\2\2\u018c\u018d\7?\2\2\u018d\n\3\2\2\2\u018e\u018f\7"+
		"p\2\2\u018f\u0190\7c\2\2\u0190\u0191\7o\2\2\u0191\u0192\7g\2\2\u0192\f"+
		"\3\2\2\2\u0193\u0194\7v\2\2\u0194\u0195\7{\2\2\u0195\u0196\7r\2\2\u0196"+
		"\u0197\7g\2\2\u0197\16\3\2\2\2\u0198\u0199\7n\2\2\u0199\u019a\7g\2\2\u019a"+
		"\u019b\7x\2\2\u019b\u019c\7g\2\2\u019c\u019d\7n\2\2\u019d\20\3\2\2\2\u019e"+
		"\u019f\7f\2\2\u019f\u01a0\7g\2\2\u01a0\u01a1\7r\2\2\u01a1\u01a2\7v\2\2"+
		"\u01a2\u01a3\7j\2\2\u01a3\22\3\2\2\2\u01a4\u01a5\7h\2\2\u01a5\u01a6\7"+
		"w\2\2\u01a6\u01a7\7p\2\2\u01a7\u01a8\7e\2\2\u01a8\24\3\2\2\2\u01a9\u01aa"+
		"\7*\2\2\u01aa\26\3\2\2\2\u01ab\u01ac\7+\2\2\u01ac\30\3\2\2\2\u01ad\u01ae"+
		"\7e\2\2\u01ae\u01af\7q\2\2\u01af\u01b0\7u\2\2\u01b0\u01b1\7v\2\2\u01b1"+
		"\32\3\2\2\2\u01b2\u01b3\7\62\2\2\u01b3\34\3\2\2\2\u01b4\u01b5\7\63\2\2"+
		"\u01b5\36\3\2\2\2\u01b6\u01b7\7\64\2\2\u01b7 \3\2\2\2\u01b8\u01b9\7\65"+
		"\2\2\u01b9\"\3\2\2\2\u01ba\u01bb\7\66\2\2\u01bb$\3\2\2\2\u01bc\u01bd\7"+
		"\67\2\2\u01bd&\3\2\2\2\u01be\u01bf\78\2\2\u01bf(\3\2\2\2\u01c0\u01c1\7"+
		"9\2\2\u01c1*\3\2\2\2\u01c2\u01c3\7:\2\2\u01c3,\3\2\2\2\u01c4\u01c5\7;"+
		"\2\2\u01c5.\3\2\2\2\u01c6\u01c7\7\63\2\2\u01c7\u01c8\7\62\2\2\u01c8\60"+
		"\3\2\2\2\u01c9\u01ca\7\63\2\2\u01ca\u01cb\7\63\2\2\u01cb\62\3\2\2\2\u01cc"+
		"\u01cd\7\63\2\2\u01cd\u01ce\7\64\2\2\u01ce\64\3\2\2\2\u01cf\u01d0\7}\2"+
		"\2\u01d0\66\3\2\2\2\u01d1\u01d2\7<\2\2\u01d28\3\2\2\2\u01d3\u01d4\7\177"+
		"\2\2\u01d4:\3\2\2\2\u01d5\u01d6\7u\2\2\u01d6\u01d7\7n\2\2\u01d7\u01d8"+
		"\7q\2\2\u01d8\u01d9\7v\2\2\u01d9\u01da\7a\2\2\u01da\u01db\7n\2\2\u01db"+
		"\u01dc\7j\2\2\u01dc\u01dd\7c\2\2\u01dd\u01de\7p\2\2\u01de\u01df\7f\2\2"+
		"\u01df<\3\2\2\2\u01e0\u01e1\7]\2\2\u01e1>\3\2\2\2\u01e2\u01e3\7_\2\2\u01e3"+
		"@\3\2\2\2\u01e4\u01e5\7d\2\2\u01e5\u01e6\7c\2\2\u01e6\u01e7\7u\2\2\u01e7"+
		"\u01e8\7g\2\2\u01e8\u01e9\7a\2\2\u01e9\u01ea\7c\2\2\u01ea\u01eb\7v\2\2"+
		"\u01eb\u01ec\7v\2\2\u01ec\u01ed\7t\2\2\u01ed\u01ee\7k\2\2\u01ee\u01ef"+
		"\7d\2\2\u01ef\u01f0\7w\2\2\u01f0\u01f1\7v\2\2\u01f1\u01f2\7g\2\2\u01f2"+
		"\u01f3\7a\2\2\u01f3\u01f4\7c\2\2\u01f4\u01f5\7v\2\2\u01f5\u01f6\7v\2\2"+
		"\u01f6\u01f7\7c\2\2\u01f7\u01f8\7e\2\2\u01f8\u01f9\7m\2\2\u01f9B\3\2\2"+
		"\2\u01fa\u01fb\7p\2\2\u01fb\u01fc\7q\2\2\u01fc\u01fd\7p\2\2\u01fd\u01fe"+
		"\7g\2\2\u01feD\3\2\2\2\u01ff\u0200\7j\2\2\u0200\u0201\7r\2\2\u0201\u0202"+
		"\7a\2\2\u0202\u0203\7t\2\2\u0203\u0204\7g\2\2\u0204\u0205\7i\2\2\u0205"+
		"\u0206\7g\2\2\u0206\u0207\7p\2\2\u0207F\3\2\2\2\u0208\u0209\7o\2\2\u0209"+
		"\u020a\7r\2\2\u020a\u020b\7a\2\2\u020b\u020c\7t\2\2\u020c\u020d\7g\2\2"+
		"\u020d\u020e\7i\2\2\u020e\u020f\7g\2\2\u020f\u0210\7p\2\2\u0210H\3\2\2"+
		"\2\u0211\u0212\7e\2\2\u0212\u0213\7r\2\2\u0213\u0214\7a\2\2\u0214\u0215"+
		"\7t\2\2\u0215\u0216\7g\2\2\u0216\u0217\7i\2\2\u0217\u0218\7g\2\2\u0218"+
		"\u0219\7p\2\2\u0219J\3\2\2\2\u021a\u021b\7g\2\2\u021b\u021c\7z\2\2\u021c"+
		"\u021d\7r\2\2\u021d\u021e\7a\2\2\u021e\u021f\7t\2\2\u021f\u0220\7g\2\2"+
		"\u0220\u0221\7u\2\2\u0221\u0222\7v\2\2\u0222\u0223\7q\2\2\u0223\u0224"+
		"\7t\2\2\u0224\u0225\7g\2\2\u0225L\3\2\2\2\u0226\u0227\7v\2\2\u0227\u0228"+
		"\7g\2\2\u0228\u0229\7n\2\2\u0229\u022a\7g\2\2\u022a\u022b\7r\2\2\u022b"+
		"\u022c\7q\2\2\u022c\u022d\7t\2\2\u022d\u022e\7v\2\2\u022eN\3\2\2\2\u022f"+
		"\u0230\7d\2\2\u0230\u0231\7t\2\2\u0231\u0232\7q\2\2\u0232\u0233\7c\2\2"+
		"\u0233\u0234\7f\2\2\u0234\u0235\7e\2\2\u0235\u0236\7c\2\2\u0236\u0237"+
		"\7u\2\2\u0237\u0238\7v\2\2\u0238P\3\2\2\2\u0239\u023a\7e\2\2\u023a\u023b"+
		"\7w\2\2\u023b\u023c\7t\2\2\u023c\u023d\7v\2\2\u023d\u023e\7c\2\2\u023e"+
		"\u023f\7k\2\2\u023f\u0240\7p\2\2\u0240R\3\2\2\2\u0241\u0242\7j\2\2\u0242"+
		"\u0243\7c\2\2\u0243\u0244\7p\2\2\u0244\u0245\7i\2\2\u0245\u0246\7k\2\2"+
		"\u0246\u0247\7p\2\2\u0247\u0248\7i\2\2\u0248T\3\2\2\2\u0249\u024a\7q\2"+
		"\2\u024a\u024b\7w\2\2\u024b\u024c\7v\2\2\u024c\u024d\7g\2\2\u024d\u024e"+
		"\7t\2\2\u024e\u024f\7r\2\2\u024f\u0250\7n\2\2\u0250\u0251\7c\2\2\u0251"+
		"\u0252\7v\2\2\u0252\u0253\7h\2\2\u0253\u0254\7q\2\2\u0254\u0255\7t\2\2"+
		"\u0255\u0256\7o\2\2\u0256V\3\2\2\2\u0257\u0258\7r\2\2\u0258\u0259\7n\2"+
		"\2\u0259\u025a\7c\2\2\u025a\u025b\7v\2\2\u025b\u025c\7h\2\2\u025c\u025d"+
		"\7q\2\2\u025d\u025e\7t\2\2\u025e\u025f\7o\2\2\u025fX\3\2\2\2\u0260\u0261"+
		"\7k\2\2\u0261\u0262\7v\2\2\u0262\u0263\7g\2\2\u0263\u0264\7o\2\2\u0264"+
		"\u0265\7a\2\2\u0265\u0266\7e\2\2\u0266\u0267\7t\2\2\u0267\u0268\7g\2\2"+
		"\u0268\u0269\7c\2\2\u0269\u026a\7v\2\2\u026a\u026b\7g\2\2\u026bZ\3\2\2"+
		"\2\u026c\u026d\7d\2\2\u026d\u026e\7w\2\2\u026e\u026f\7h\2\2\u026f\u0270"+
		"\7h\2\2\u0270\\\3\2\2\2\u0271\u0272\7B\2\2\u0272\u0273\5\u016d\u00b7\2"+
		"\u0273^\3\2\2\2\u0274\u0275\5u;\2\u0275`\3\2\2\2\u0276\u0278\5s:\2\u0277"+
		"\u0276\3\2\2\2\u0277\u0278\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027a\5q"+
		"9\2\u027ab\3\2\2\2\u027b\u027c\5a\61\2\u027c\u027e\7\60\2\2\u027d\u027f"+
		"\5w<\2\u027e\u027d\3\2\2\2\u027f\u0280\3\2\2\2\u0280\u027e\3\2\2\2\u0280"+
		"\u0281\3\2\2\2\u0281d\3\2\2\2\u0282\u0283\7h\2\2\u0283\u0284\7k\2\2\u0284"+
		"\u0285\7t\2\2\u0285\u0286\7g\2\2\u0286f\3\2\2\2\u0287\u0288\7y\2\2\u0288"+
		"\u0289\7c\2\2\u0289\u028a\7v\2\2\u028a\u028b\7g\2\2\u028b\u028c\7t\2\2"+
		"\u028ch\3\2\2\2\u028d\u028e\7g\2\2\u028e\u028f\7c\2\2\u028f\u0290\7t\2"+
		"\2\u0290\u0291\7v\2\2\u0291\u0292\7j\2\2\u0292j\3\2\2\2\u0293\u0294\7"+
		"y\2\2\u0294\u0295\7k\2\2\u0295\u0296\7p\2\2\u0296\u0297\7f\2\2\u0297l"+
		"\3\2\2\2\u0298\u0299\7w\2\2\u0299\u029a\7p\2\2\u029a\u029b\7j\2\2\u029b"+
		"\u029c\7q\2\2\u029c\u029d\7n\2\2\u029d\u029e\7{\2\2\u029en\3\2\2\2\u029f"+
		"\u02a0\7j\2\2\u02a0\u02a1\7q\2\2\u02a1\u02a2\7n\2\2\u02a2\u02a3\7{\2\2"+
		"\u02a3p\3\2\2\2\u02a4\u02ad\5{>\2\u02a5\u02a9\5y=\2\u02a6\u02a8\5w<\2"+
		"\u02a7\u02a6\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9\u02a7\3\2\2\2\u02a9\u02aa"+
		"\3\2\2\2\u02aa\u02ad\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ac\u02a4\3\2\2\2\u02ac"+
		"\u02a5\3\2\2\2\u02adr\3\2\2\2\u02ae\u02af\7/\2\2\u02aft\3\2\2\2\u02b0"+
		"\u02b1\t\2\2\2\u02b1v\3\2\2\2\u02b2\u02b3\t\3\2\2\u02b3x\3\2\2\2\u02b4"+
		"\u02b5\t\4\2\2\u02b5z\3\2\2\2\u02b6\u02b7\t\5\2\2\u02b7|\3\2\2\2\u02b8"+
		"\u02b9\7=\2\2\u02b9~\3\2\2\2\u02ba\u02bb\7h\2\2\u02bb\u02bc\7c\2\2\u02bc"+
		"\u02bd\7k\2\2\u02bd\u02be\7t\2\2\u02be\u02bf\7{\2\2\u02bf\u0080\3\2\2"+
		"\2\u02c0\u02c1\7c\2\2\u02c1\u02c2\7p\2\2\u02c2\u02c3\7k\2\2\u02c3\u02c4"+
		"\7o\2\2\u02c4\u02c5\7c\2\2\u02c5\u02c6\7n\2\2\u02c6\u0082\3\2\2\2\u02c7"+
		"\u02c8\7j\2\2\u02c8\u02c9\7w\2\2\u02c9\u02ca\7o\2\2\u02ca\u02cb\7c\2\2"+
		"\u02cb\u02cc\7p\2\2\u02cc\u02cd\7q\2\2\u02cd\u02ce\7k\2\2\u02ce\u02cf"+
		"\7f\2\2\u02cf\u0084\3\2\2\2\u02d0\u02d1\7r\2\2\u02d1\u02d2\7n\2\2\u02d2"+
		"\u02d3\7c\2\2\u02d3\u02d4\7p\2\2\u02d4\u02d5\7v\2\2\u02d5\u0086\3\2\2"+
		"\2\u02d6\u02d7\7w\2\2\u02d7\u02d8\7p\2\2\u02d8\u02d9\7f\2\2\u02d9\u02da"+
		"\7g\2\2\u02da\u02db\7c\2\2\u02db\u02dc\7f\2\2\u02dc\u0088\3\2\2\2\u02dd"+
		"\u02de\7e\2\2\u02de\u02df\7q\2\2\u02df\u02e0\7p\2\2\u02e0\u02e1\7u\2\2"+
		"\u02e1\u02e2\7v\2\2\u02e2\u02e3\7t\2\2\u02e3\u02e4\7w\2\2\u02e4\u02e5"+
		"\7e\2\2\u02e5\u02e6\7v\2\2\u02e6\u008a\3\2\2\2\u02e7\u02e8\7d\2\2\u02e8"+
		"\u02e9\7g\2\2\u02e9\u02ea\7c\2\2\u02ea\u02eb\7u\2\2\u02eb\u02ec\7v\2\2"+
		"\u02ec\u008c\3\2\2\2\u02ed\u02ee\7d\2\2\u02ee\u02ef\7w\2\2\u02ef\u02f0"+
		"\7i\2\2\u02f0\u008e\3\2\2\2\u02f1\u02f2\7g\2\2\u02f2\u02f3\7n\2\2\u02f3"+
		"\u02f4\7g\2\2\u02f4\u02f5\7o\2\2\u02f5\u02f6\7g\2\2\u02f6\u02f7\7p\2\2"+
		"\u02f7\u02f8\7v\2\2\u02f8\u02f9\7c\2\2\u02f9\u02fa\7n\2\2\u02fa\u0090"+
		"\3\2\2\2\u02fb\u02fc\7f\2\2\u02fc\u02fd\7g\2\2\u02fd\u02fe\7o\2\2\u02fe"+
		"\u02ff\7q\2\2\u02ff\u0300\7p\2\2\u0300\u0301\7k\2\2\u0301\u0302\7e\2\2"+
		"\u0302\u0092\3\2\2\2\u0303\u0304\7i\2\2\u0304\u0305\7k\2\2\u0305\u0306"+
		"\7c\2\2\u0306\u0307\7p\2\2\u0307\u0308\7v\2\2\u0308\u0094\3\2\2\2\u0309"+
		"\u030a\7f\2\2\u030a\u030b\7t\2\2\u030b\u030c\7c\2\2\u030c\u030d\7i\2\2"+
		"\u030d\u030e\7q\2\2\u030e\u030f\7p\2\2\u030f\u0096\3\2\2\2\u0310\u0311"+
		"\7f\2\2\u0311\u0312\7k\2\2\u0312\u0313\7x\2\2\u0313\u0314\7k\2\2\u0314"+
		"\u0315\7p\2\2\u0315\u0316\7g\2\2\u0316\u0098\3\2\2\2\u0317\u0318\7u\2"+
		"\2\u0318\u0319\7w\2\2\u0319\u031a\7o\2\2\u031a\u031b\7o\2\2\u031b\u031c"+
		"\7q\2\2\u031c\u031d\7p\2\2\u031d\u009a\3\2\2\2\u031e\u031f\7r\2\2\u031f"+
		"\u0320\7g\2\2\u0320\u0321\7v\2\2\u0321\u009c\3\2\2\2\u0322\u0323\7j\2"+
		"\2\u0323\u0324\7q\2\2\u0324\u0325\7n\2\2\u0325\u0326\7{\2\2\u0326\u0327"+
		"\7v\2\2\u0327\u0328\7j\2\2\u0328\u0329\7k\2\2\u0329\u032a\7p\2\2\u032a"+
		"\u032b\7i\2\2\u032b\u009e\3\2\2\2\u032c\u032d\7f\2\2\u032d\u032e\7y\2"+
		"\2\u032e\u032f\7c\2\2\u032f\u0330\7t\2\2\u0330\u0331\7h\2\2\u0331\u00a0"+
		"\3\2\2\2\u0332\u0333\7o\2\2\u0333\u0334\7g\2\2\u0334\u0335\7t\2\2\u0335"+
		"\u0336\7e\2\2\u0336\u0337\7j\2\2\u0337\u0338\7c\2\2\u0338\u0339\7p\2\2"+
		"\u0339\u033a\7v\2\2\u033a\u00a2\3\2\2\2\u033b\u033c\7g\2\2\u033c\u033d"+
		"\7n\2\2\u033d\u033e\7h\2\2\u033e\u00a4\3\2\2\2\u033f\u0340\7m\2\2\u0340"+
		"\u0341\7c\2\2\u0341\u0342\7o\2\2\u0342\u0343\7c\2\2\u0343\u0344\7g\2\2"+
		"\u0344\u0345\7n\2\2\u0345\u00a6\3\2\2\2\u0346\u0347\7q\2\2\u0347\u0348"+
		"\7t\2\2\u0348\u0349\7e\2\2\u0349\u00a8\3\2\2\2\u034a\u034b\7u\2\2\u034b"+
		"\u034c\7k\2\2\u034c\u034d\7g\2\2\u034d\u034e\7i\2\2\u034e\u034f\7g\2\2"+
		"\u034f\u0350\7a\2\2\u0350\u0351\7y\2\2\u0351\u0352\7g\2\2\u0352\u0353"+
		"\7c\2\2\u0353\u0354\7r\2\2\u0354\u0355\7q\2\2\u0355\u0356\7p\2\2\u0356"+
		"\u00aa\3\2\2\2\u0357\u0358\7h\2\2\u0358\u0359\7t\2\2\u0359\u035a\7k\2"+
		"\2\u035a\u035b\7g\2\2\u035b\u035c\7p\2\2\u035c\u035d\7f\2\2\u035d\u00ac"+
		"\3\2\2\2\u035e\u035f\7o\2\2\u035f\u0360\7g\2\2\u0360\u0361\7t\2\2\u0361"+
		"\u0362\7e\2\2\u0362\u0363\7g\2\2\u0363\u0364\7p\2\2\u0364\u0365\7c\2\2"+
		"\u0365\u0366\7t\2\2\u0366\u0367\7{\2\2\u0367\u00ae\3\2\2\2\u0368\u0369"+
		"\7e\2\2\u0369\u036a\7c\2\2\u036a\u036b\7u\2\2\u036b\u036c\7v\2\2\u036c"+
		"\u036d\7n\2\2\u036d\u036e\7g\2\2\u036e\u036f\7a\2\2\u036f\u0370\7i\2\2"+
		"\u0370\u0371\7w\2\2\u0371\u0372\7c\2\2\u0372\u0373\7t\2\2\u0373\u0374"+
		"\7f\2\2\u0374\u00b0\3\2\2\2\u0375\u0376\7j\2\2\u0376\u0377\7w\2\2\u0377"+
		"\u0378\7o\2\2\u0378\u0379\7c\2\2\u0379\u037a\7p\2\2\u037a\u00b2\3\2\2"+
		"\2\u037b\u037c\7d\2\2\u037c\u037d\7q\2\2\u037d\u037e\7u\2\2\u037e\u037f"+
		"\7u\2\2\u037f\u00b4\3\2\2\2\u0380\u0381\7|\2\2\u0381\u0382\7|\2\2\u0382"+
		"\u0383\7q\2\2\u0383\u0384\7n\2\2\u0384\u0385\7f\2\2\u0385\u0386\7c\2\2"+
		"\u0386\u0387\7i\2\2\u0387\u0388\7w\2\2\u0388\u00b6\3\2\2\2\u0389\u038a"+
		"\7y\2\2\u038a\u038b\7q\2\2\u038b\u038c\7t\2\2\u038c\u038d\7n\2\2\u038d"+
		"\u038e\7f\2\2\u038e\u038f\7a\2\2\u038f\u0390\7v\2\2\u0390\u0391\7t\2\2"+
		"\u0391\u0392\7c\2\2\u0392\u0393\7r\2\2\u0393\u00b8\3\2\2\2\u0394\u0395"+
		"\7o\2\2\u0395\u0396\7q\2\2\u0396\u0397\7p\2\2\u0397\u0398\7t\2\2\u0398"+
		"\u0399\7c\2\2\u0399\u039a\7e\2\2\u039a\u039b\7g\2\2\u039b\u00ba\3\2\2"+
		"\2\u039c\u039d\7f\2\2\u039d\u039e\7c\2\2\u039e\u039f\7t\2\2\u039f\u03a0"+
		"\7m\2\2\u03a0\u03a1\7g\2\2\u03a1\u03a2\7n\2\2\u03a2\u03a3\7h\2\2\u03a3"+
		"\u00bc\3\2\2\2\u03a4\u03a5\7i\2\2\u03a5\u03a6\7w\2\2\u03a6\u03a7\7c\2"+
		"\2\u03a7\u03a8\7t\2\2\u03a8\u03a9\7f\2\2\u03a9\u00be\3\2\2\2\u03aa\u03ab"+
		"\7v\2\2\u03ab\u03ac\7g\2\2\u03ac\u03ad\7n\2\2\u03ad\u03ae\7g\2\2\u03ae"+
		"\u03af\7r\2\2\u03af\u03b0\7q\2\2\u03b0\u03b1\7t\2\2\u03b1\u03b2\7v\2\2"+
		"\u03b2\u03b3\7g\2\2\u03b3\u03b4\7t\2\2\u03b4\u00c0\3\2\2\2\u03b5\u03b6"+
		"\7y\2\2\u03b6\u03b7\7c\2\2\u03b7\u03b8\7t\2\2\u03b8\u03b9\7g\2\2\u03b9"+
		"\u03ba\7j\2\2\u03ba\u03bb\7q\2\2\u03bb\u03bc\7w\2\2\u03bc\u03bd\7u\2\2"+
		"\u03bd\u03be\7g\2\2\u03be\u03bf\7a\2\2\u03bf\u03c0\7m\2\2\u03c0\u03c1"+
		"\7g\2\2\u03c1\u03c2\7g\2\2\u03c2\u03c3\7r\2\2\u03c3\u03c4\7g\2\2\u03c4"+
		"\u03c5\7t\2\2\u03c5\u00c2\3\2\2\2\u03c6\u03c7\7y\2\2\u03c7\u03c8\7c\2"+
		"\2\u03c8\u03c9\7t\2\2\u03c9\u03ca\7t\2\2\u03ca\u03cb\7k\2\2\u03cb\u03cc"+
		"\7q\2\2\u03cc\u03cd\7t\2\2\u03cd\u00c4\3\2\2\2\u03ce\u03cf\7e\2\2\u03cf"+
		"\u03d0\7k\2\2\u03d0\u03d1\7v\2\2\u03d1\u03d2\7k\2\2\u03d2\u03d3\7|\2\2"+
		"\u03d3\u03d4\7g\2\2\u03d4\u03d5\7p\2\2\u03d5\u00c6\3\2\2\2\u03d6\u03d7"+
		"\7v\2\2\u03d7\u03d8\7t\2\2\u03d8\u03d9\7g\2\2\u03d9\u03da\7c\2\2\u03da"+
		"\u03db\7u\2\2\u03db\u03dc\7w\2\2\u03dc\u03dd\7t\2\2\u03dd\u03de\7g\2\2"+
		"\u03de\u00c8\3\2\2\2\u03df\u03e0\7h\2\2\u03e0\u03e1\7k\2\2\u03e1\u03e2"+
		"\7g\2\2\u03e2\u03e3\7n\2\2\u03e3\u03e4\7f\2\2\u03e4\u03e5\7d\2\2\u03e5"+
		"\u03e6\7q\2\2\u03e6\u03e7\7u\2\2\u03e7\u03e8\7u\2\2\u03e8\u00ca\3\2\2"+
		"\2\u03e9\u03ea\7d\2\2\u03ea\u03eb\7n\2\2\u03eb\u03ec\7c\2\2\u03ec\u03ed"+
		"\7e\2\2\u03ed\u03ee\7m\2\2\u03ee\u03ef\7u\2\2\u03ef\u03f0\7o\2\2\u03f0"+
		"\u03f1\7k\2\2\u03f1\u03f2\7v\2\2\u03f2\u03f3\7j\2\2\u03f3\u00cc\3\2\2"+
		"\2\u03f4\u03f5\7i\2\2\u03f5\u03f6\7w\2\2\u03f6\u03f7\7k\2\2\u03f7\u03f8"+
		"\7n\2\2\u03f8\u03f9\7f\2\2\u03f9\u03fa\7a\2\2\u03fa\u03fb\7o\2\2\u03fb"+
		"\u03fc\7c\2\2\u03fc\u03fd\7u\2\2\u03fd\u03fe\7v\2\2\u03fe\u03ff\7g\2\2"+
		"\u03ff\u0400\7t\2\2\u0400\u00ce\3\2\2\2\u0401\u0402\7i\2\2\u0402\u0403"+
		"\7w\2\2\u0403\u0404\7k\2\2\u0404\u0405\7n\2\2\u0405\u0406\7f\2\2\u0406"+
		"\u0407\7a\2\2\u0407\u0408\7e\2\2\u0408\u0409\7q\2\2\u0409\u040a\7c\2\2"+
		"\u040a\u040b\7e\2\2\u040b\u040c\7j\2\2\u040c\u00d0\3\2\2\2\u040d\u040e"+
		"\7r\2\2\u040e\u040f\7e\2\2\u040f\u0410\7a\2\2\u0410\u0411\7v\2\2\u0411"+
		"\u0412\7t\2\2\u0412\u0413\7c\2\2\u0413\u0414\7r\2\2\u0414\u00d2\3\2\2"+
		"\2\u0415\u0416\7z\2\2\u0416\u0417\7o\2\2\u0417\u0418\7c\2\2\u0418\u0419"+
		"\7u\2\2\u0419\u041a\7v\2\2\u041a\u041b\7t\2\2\u041b\u041c\7g\2\2\u041c"+
		"\u041d\7g\2\2\u041d\u00d4\3\2\2\2\u041e\u041f\7f\2\2\u041f\u0420\7q\2"+
		"\2\u0420\u0421\7r\2\2\u0421\u0422\7r\2\2\u0422\u0423\7g\2\2\u0423\u0424"+
		"\7n\2\2\u0424\u0425\7i\2\2\u0425\u0426\7c\2\2\u0426\u0427\7p\2\2\u0427"+
		"\u0428\7i\2\2\u0428\u0429\7g\2\2\u0429\u042a\7t\2\2\u042a\u00d6\3\2\2"+
		"\2\u042b\u042c\7q\2\2\u042c\u042d\7y\2\2\u042d\u042e\7p\2\2\u042e\u042f"+
		"\7v\2\2\u042f\u0430\7j\2\2\u0430\u0431\7k\2\2\u0431\u0432\7p\2\2\u0432"+
		"\u0433\7i\2\2\u0433\u00d8\3\2\2\2\u0434\u0435\7u\2\2\u0435\u0436\7k\2"+
		"\2\u0436\u0437\7g\2\2\u0437\u0438\7i\2\2\u0438\u0439\7g\2\2\u0439\u043a"+
		"\7a\2\2\u043a\u043b\7c\2\2\u043b\u043c\7v\2\2\u043c\u043d\7v\2\2\u043d"+
		"\u043e\7c\2\2\u043e\u043f\7e\2\2\u043f\u0440\7m\2\2\u0440\u0441\7g\2\2"+
		"\u0441\u0442\7t\2\2\u0442\u00da\3\2\2\2\u0443\u0444\7o\2\2\u0444\u0445"+
		"\7t\2\2\u0445\u0446\7m\2\2\u0446\u0447\7g\2\2\u0447\u0448\7g\2\2\u0448"+
		"\u0449\7r\2\2\u0449\u044a\7g\2\2\u044a\u044b\7t\2\2\u044b\u00dc\3\2\2"+
		"\2\u044c\u044d\7e\2\2\u044d\u044e\7q\2\2\u044e\u044f\7n\2\2\u044f\u0450"+
		"\7n\2\2\u0450\u0451\7g\2\2\u0451\u0452\7e\2\2\u0452\u0453\7v\2\2\u0453"+
		"\u0454\7k\2\2\u0454\u0455\7q\2\2\u0455\u0456\7p\2\2\u0456\u00de\3\2\2"+
		"\2\u0457\u0458\7r\2\2\u0458\u0459\7c\2\2\u0459\u045a\7e\2\2\u045a\u045b"+
		"\7m\2\2\u045b\u045c\7c\2\2\u045c\u045d\7i\2\2\u045d\u045e\7g\2\2\u045e"+
		"\u045f\7a\2\2\u045f\u0460\7m\2\2\u0460\u0461\7g\2\2\u0461\u0462\7g\2\2"+
		"\u0462\u0463\7r\2\2\u0463\u0464\7g\2\2\u0464\u0465\7t\2\2\u0465\u00e0"+
		"\3\2\2\2\u0466\u0467\7u\2\2\u0467\u0468\7y\2\2\u0468\u0469\7q\2\2\u0469"+
		"\u046a\7t\2\2\u046a\u046b\7f\2\2\u046b\u00e2\3\2\2\2\u046c\u046d\7d\2"+
		"\2\u046d\u046e\7n\2\2\u046e\u046f\7w\2\2\u046f\u0470\7p\2\2\u0470\u0471"+
		"\7v\2\2\u0471\u00e4\3\2\2\2\u0472\u0473\7d\2\2\u0473\u0474\7q\2\2\u0474"+
		"\u0475\7y\2\2\u0475\u00e6\3\2\2\2\u0476\u0477\7r\2\2\u0477\u0478\7q\2"+
		"\2\u0478\u0479\7n\2\2\u0479\u047a\7g\2\2\u047a\u00e8\3\2\2\2\u047b\u047c"+
		"\7f\2\2\u047c\u047d\7c\2\2\u047d\u047e\7i\2\2\u047e\u047f\7i\2\2\u047f"+
		"\u0480\7g\2\2\u0480\u0481\7t\2\2\u0481\u00ea\3\2\2\2\u0482\u0483\7f\2"+
		"\2\u0483\u0484\7w\2\2\u0484\u0485\7c\2\2\u0485\u0486\7n\2\2\u0486\u00ec"+
		"\3\2\2\2\u0487\u0488\7h\2\2\u0488\u0489\7k\2\2\u0489\u048a\7u\2\2\u048a"+
		"\u048b\7v\2\2\u048b\u00ee\3\2\2\2\u048c\u048d\7f\2\2\u048d\u048e\7w\2"+
		"\2\u048e\u048f\7c\2\2\u048f\u0490\7n\2\2\u0490\u0491\7h\2\2\u0491\u0492"+
		"\7k\2\2\u0492\u0493\7u\2\2\u0493\u0494\7v\2\2\u0494\u00f0\3\2\2\2\u0495"+
		"\u0496\7h\2\2\u0496\u0497\7k\2\2\u0497\u0498\7u\2\2\u0498\u0499\7j\2\2"+
		"\u0499\u049a\7k\2\2\u049a\u049b\7p\2\2\u049b\u049c\7i\2\2\u049c\u049d"+
		"\7t\2\2\u049d\u049e\7q\2\2\u049e\u049f\7f\2\2\u049f\u00f2\3\2\2\2\u04a0"+
		"\u04a1\7t\2\2\u04a1\u04a2\7c\2\2\u04a2\u04a3\7r\2\2\u04a3\u04a4\7k\2\2"+
		"\u04a4\u04a5\7g\2\2\u04a5\u04a6\7t\2\2\u04a6\u00f4\3\2\2\2\u04a7\u04a8"+
		"\7c\2\2\u04a8\u04a9\7p\2\2\u04a9\u04aa\7e\2\2\u04aa\u04ab\7k\2\2\u04ab"+
		"\u04ac\7g\2\2\u04ac\u04ad\7p\2\2\u04ad\u04ae\7v\2\2\u04ae\u04af\7u\2\2"+
		"\u04af\u04b0\7y\2\2\u04b0\u04b1\7q\2\2\u04b1\u04b2\7t\2\2\u04b2\u04b3"+
		"\7f\2\2\u04b3\u00f6\3\2\2\2\u04b4\u04b5\7e\2\2\u04b5\u04b6\7t\2\2\u04b6"+
		"\u04b7\7q\2\2\u04b7\u04b8\7u\2\2\u04b8\u04b9\7u\2\2\u04b9\u04ba\7d\2\2"+
		"\u04ba\u04bb\7q\2\2\u04bb\u04bc\7y\2\2\u04bc\u00f8\3\2\2\2\u04bd\u04be"+
		"\7h\2\2\u04be\u04bf\7n\2\2\u04bf\u04c0\7c\2\2\u04c0\u04c1\7i\2\2\u04c1"+
		"\u00fa\3\2\2\2\u04c2\u04c3\7f\2\2\u04c3\u04c4\7w\2\2\u04c4\u04c5\7c\2"+
		"\2\u04c5\u04c6\7n\2\2\u04c6\u04c7\7f\2\2\u04c7\u04c8\7c\2\2\u04c8\u04c9"+
		"\7i\2\2\u04c9\u04ca\7i\2\2\u04ca\u04cb\7g\2\2\u04cb\u04cc\7t\2\2\u04cc"+
		"\u00fc\3\2\2\2\u04cd\u04ce\7g\2\2\u04ce\u04cf\7v\2\2\u04cf\u04d0\7e\2"+
		"\2\u04d0\u00fe\3\2\2\2\u04d1\u04d2\7n\2\2\u04d2\u04d3\7k\2\2\u04d3\u04d4"+
		"\7i\2\2\u04d4\u04d5\7j\2\2\u04d5\u04d6\7v\2\2\u04d6\u0100\3\2\2\2\u04d7"+
		"\u04d8\7j\2\2\u04d8\u04d9\7g\2\2\u04d9\u04da\7c\2\2\u04da\u04db\7x\2\2"+
		"\u04db\u04dc\7{\2\2\u04dc\u0102\3\2\2\2\u04dd\u04de\7o\2\2\u04de\u04df"+
		"\7c\2\2\u04df\u04e0\7i\2\2\u04e0\u04e1\7k\2\2\u04e1\u04e2\7e\2\2\u04e2"+
		"\u0104\3\2\2\2\u04e3\u04e4\7u\2\2\u04e4\u04e5\7k\2\2\u04e5\u04e6\7i\2"+
		"\2\u04e6\u04e7\7k\2\2\u04e7\u04e8\7n\2\2\u04e8\u0106\3\2\2\2\u04e9\u04ea"+
		"\7t\2\2\u04ea\u04eb\7j\2\2\u04eb\u04ec\7c\2\2\u04ec\u04ed\7p\2\2\u04ed"+
		"\u04ee\7f\2\2\u04ee\u0108\3\2\2\2\u04ef\u04f0\7n\2\2\u04f0\u04f1\7t\2"+
		"\2\u04f1\u04f2\7j\2\2\u04f2\u04f3\7c\2\2\u04f3\u04f4\7p\2\2\u04f4\u04f5"+
		"\7f\2\2\u04f5\u010a\3\2\2\2\u04f6\u04f7\7n\2\2\u04f7\u04f8\7j\2\2\u04f8"+
		"\u04f9\7c\2\2\u04f9\u04fa\7p\2\2\u04fa\u04fb\7f\2\2\u04fb\u010c\3\2\2"+
		"\2\u04fc\u04fd\7e\2\2\u04fd\u04fe\7j\2\2\u04fe\u04ff\7g\2\2\u04ff\u0500"+
		"\7u\2\2\u0500\u0501\7v\2\2\u0501\u010e\3\2\2\2\u0502\u0503\7n\2\2\u0503"+
		"\u0504\7g\2\2\u0504\u0505\7i\2\2\u0505\u0506\7u\2\2\u0506\u0110\3\2\2"+
		"\2\u0507\u0508\7h\2\2\u0508\u0509\7g\2\2\u0509\u050a\7g\2\2\u050a\u050b"+
		"\7v\2\2\u050b\u0112\3\2\2\2\u050c\u050d\7j\2\2\u050d\u050e\7g\2\2\u050e"+
		"\u050f\7c\2\2\u050f\u0510\7f\2\2\u0510\u0114\3\2\2\2\u0511\u0512\7i\2"+
		"\2\u0512\u0513\7n\2\2\u0513\u0514\7q\2\2\u0514\u0515\7x\2\2\u0515\u0516"+
		"\7g\2\2\u0516\u0517\7u\2\2\u0517\u0116\3\2\2\2\u0518\u0519\7q\2\2\u0519"+
		"\u051a\7p\2\2\u051a\u051b\7g\2\2\u051b\u051c\7r\2\2\u051c\u051d\7k\2\2"+
		"\u051d\u051e\7g\2\2\u051e\u051f\7e\2\2\u051f\u0520\7g\2\2\u0520\u0118"+
		"\3\2\2\2\u0521\u0522\7t\2\2\u0522\u0523\7g\2\2\u0523\u0524\7c\2\2\u0524"+
		"\u0525\7t\2\2\u0525\u011a\3\2\2\2\u0526\u0527\7n\2\2\u0527\u0528\7g\2"+
		"\2\u0528\u0529\7c\2\2\u0529\u052a\7t\2\2\u052a\u011c\3\2\2\2\u052b\u052c"+
		"\7t\2\2\u052c\u052d\7h\2\2\u052d\u052e\7k\2\2\u052e\u052f\7p\2\2\u052f"+
		"\u0530\7i\2\2\u0530\u0531\7g\2\2\u0531\u0532\7t\2\2\u0532\u011e\3\2\2"+
		"\2\u0533\u0534\7n\2\2\u0534\u0535\7h\2\2\u0535\u0536\7k\2\2\u0536\u0537"+
		"\7p\2\2\u0537\u0538\7i\2\2\u0538\u0539\7g\2\2\u0539\u053a\7t\2\2\u053a"+
		"\u0120\3\2\2\2\u053b\u053c\7p\2\2\u053c\u053d\7g\2\2\u053d\u053e\7e\2"+
		"\2\u053e\u053f\7m\2\2\u053f\u0122\3\2\2\2\u0540\u0541\7d\2\2\u0541\u0542"+
		"\7c\2\2\u0542\u0543\7e\2\2\u0543\u0544\7m\2\2\u0544\u0124\3\2\2\2\u0545"+
		"\u0546\7w\2\2\u0546\u0547\7p\2\2\u0547\u0548\7f\2\2\u0548\u0549\7g\2\2"+
		"\u0549\u054a\7t\2\2\u054a\u054b\7y\2\2\u054b\u054c\7g\2\2\u054c\u054d"+
		"\7c\2\2\u054d\u054e\7t\2\2\u054e\u0126\3\2\2\2\u054f\u0550\7j\2\2\u0550"+
		"\u0551\7c\2\2\u0551\u0552\7k\2\2\u0552\u0553\7t\2\2\u0553\u0128\3\2\2"+
		"\2\u0554\u0555\7j\2\2\u0555\u0556\7c\2\2\u0556\u0557\7k\2\2\u0557\u0558"+
		"\7t\2\2\u0558\u0559\7\64\2\2\u0559\u012a\3\2\2\2\u055a\u055b\7j\2\2\u055b"+
		"\u055c\7c\2\2\u055c\u055d\7k\2\2\u055d\u055e\7t\2\2\u055e\u055f\7c\2\2"+
		"\u055f\u0560\7n\2\2\u0560\u0561\7n\2\2\u0561\u012c\3\2\2\2\u0562\u0563"+
		"\7c\2\2\u0563\u0564\7n\2\2\u0564\u0565\7n\2\2\u0565\u0566\7f\2\2\u0566"+
		"\u0567\7t\2\2\u0567\u0568\7g\2\2\u0568\u0569\7u\2\2\u0569\u056a\7u\2\2"+
		"\u056a\u012e\3\2\2\2\u056b\u056c\7t\2\2\u056c\u056d\7d\2\2\u056d\u056e"+
		"\7t\2\2\u056e\u056f\7c\2\2\u056f\u0570\7e\2\2\u0570\u0571\7g\2\2\u0571"+
		"\u0572\7n\2\2\u0572\u0573\7g\2\2\u0573\u0574\7v\2\2\u0574\u0130\3\2\2"+
		"\2\u0575\u0576\7n\2\2\u0576\u0577\7d\2\2\u0577\u0578\7t\2\2\u0578\u0579"+
		"\7c\2\2\u0579\u057a\7e\2\2\u057a\u057b\7g\2\2\u057b\u057c\7n\2\2\u057c"+
		"\u057d\7g\2\2\u057d\u057e\7v\2\2\u057e\u0132\3\2\2\2\u057f\u0580\7y\2"+
		"\2\u0580\u0581\7c\2\2\u0581\u0582\7k\2\2\u0582\u0583\7u\2\2\u0583\u0584"+
		"\7v\2\2\u0584\u0134\3\2\2\2\u0585\u0586\7f\2\2\u0586\u0587\7g\2\2\u0587"+
		"\u0588\7e\2\2\u0588\u0589\7q\2\2\u0589\u058a\7\63\2\2\u058a\u0136\3\2"+
		"\2\2\u058b\u058c\7u\2\2\u058c\u058d\7v\2\2\u058d\u058e\7g\2\2\u058e\u058f"+
		"\7g\2\2\u058f\u0590\7n\2\2\u0590\u0138\3\2\2\2\u0591\u0592\7h\2\2\u0592"+
		"\u0593\7k\2\2\u0593\u0594\7p\2\2\u0594\u0595\7g\2\2\u0595\u0596\7a\2\2"+
		"\u0596\u0597\7u\2\2\u0597\u0598\7v\2\2\u0598\u0599\7g\2\2\u0599\u059a"+
		"\7g\2\2\u059a\u059b\7n\2\2\u059b\u013a\3\2\2\2\u059c\u059d\7y\2\2\u059d"+
		"\u059e\7q\2\2\u059e\u059f\7q\2\2\u059f\u05a0\7f\2\2\u05a0\u013c\3\2\2"+
		"\2\u05a1\u05a2\7e\2\2\u05a2\u05a3\7n\2\2\u05a3\u05a4\7q\2\2\u05a4\u05a5"+
		"\7v\2\2\u05a5\u05a6\7j\2\2\u05a6\u013e\3\2\2\2\u05a7\u05a8\7n\2\2\u05a8"+
		"\u05a9\7g\2\2\u05a9\u05aa\7c\2\2\u05aa\u05ab\7v\2\2\u05ab\u05ac\7j\2\2"+
		"\u05ac\u05ad\7g\2\2\u05ad\u05ae\7t\2\2\u05ae\u0140\3\2\2\2\u05af\u05b0"+
		"\7d\2\2\u05b0\u05b1\7q\2\2\u05b1\u05b2\7p\2\2\u05b2\u05b3\7g\2\2\u05b3"+
		"\u0142\3\2\2\2\u05b4\u05b5\7d\2\2\u05b5\u05b6\7t\2\2\u05b6\u05b7\7q\2"+
		"\2\u05b7\u05b8\7p\2\2\u05b8\u05b9\7|\2\2\u05b9\u05ba\7g\2\2\u05ba\u0144"+
		"\3\2\2\2\u05bb\u05bc\7q\2\2\u05bc\u05bd\7t\2\2\u05bd\u05be\7k\2\2\u05be"+
		"\u05bf\7j\2\2\u05bf\u05c0\7c\2\2\u05c0\u05c1\7t\2\2\u05c1\u05c2\7w\2\2"+
		"\u05c2\u05c3\7m\2\2\u05c3\u05c4\7q\2\2\u05c4\u05c5\7p\2\2\u05c5\u0146"+
		"\3\2\2\2\u05c6\u05c7\7o\2\2\u05c7\u05c8\7k\2\2\u05c8\u05c9\7v\2\2\u05c9"+
		"\u05ca\7j\2\2\u05ca\u05cb\7t\2\2\u05cb\u05cc\7k\2\2\u05cc\u05cd\7n\2\2"+
		"\u05cd\u0148\3\2\2\2\u05ce\u05cf\7f\2\2\u05cf\u05d0\7c\2\2\u05d0\u05d1"+
		"\7o\2\2\u05d1\u05d2\7c\2\2\u05d2\u05d3\7u\2\2\u05d3\u05d4\7e\2\2\u05d4"+
		"\u05d5\7w\2\2\u05d5\u05d6\7u\2\2\u05d6\u014a\3\2\2\2\u05d7\u05d8\7c\2"+
		"\2\u05d8\u05d9\7f\2\2\u05d9\u05da\7c\2\2\u05da\u05db\7o\2\2\u05db\u05dc"+
		"\7c\2\2\u05dc\u05dd\7p\2\2\u05dd\u05de\7v\2\2\u05de\u05df\7c\2\2\u05df"+
		"\u05e0\7k\2\2\u05e0\u05e1\7v\2\2\u05e1\u05e2\7g\2\2\u05e2\u014c\3\2\2"+
		"\2\u05e3\u05e4\7d\2\2\u05e4\u05e5\7n\2\2\u05e5\u05e6\7q\2\2\u05e6\u05e7"+
		"\7q\2\2\u05e7\u05e8\7f\2\2\u05e8\u05e9\7a\2\2\u05e9\u05ea\7u\2\2\u05ea"+
		"\u05eb\7v\2\2\u05eb\u05ec\7g\2\2\u05ec\u05ed\7g\2\2\u05ed\u05ee\7n\2\2"+
		"\u05ee\u014e\3\2\2\2\u05ef\u05f0\7r\2\2\u05f0\u05f1\7c\2\2\u05f1\u05f2"+
		"\7r\2\2\u05f2\u05f3\7g\2\2\u05f3\u05f4\7t\2\2\u05f4\u0150\3\2\2\2\u05f5"+
		"\u05f6\7i\2\2\u05f6\u05f7\7q\2\2\u05f7\u05f8\7n\2\2\u05f8\u05f9\7f\2\2"+
		"\u05f9\u0152\3\2\2\2\u05fa\u05fb\7n\2\2\u05fb\u05fc\7k\2\2\u05fc\u05fd"+
		"\7s\2\2\u05fd\u05fe\7w\2\2\u05fe\u05ff\7k\2\2\u05ff\u0600\7f\2\2\u0600"+
		"\u0154\3\2\2\2\u0601\u0602\7h\2\2\u0602\u0603\7k\2\2\u0603\u0604\7u\2"+
		"\2\u0604\u0605\7j\2\2\u0605\u0156\3\2\2\2\u0606\u0607\7u\2\2\u0607\u0608"+
		"\7k\2\2\u0608\u0609\7n\2\2\u0609\u060a\7x\2\2\u060a\u060b\7g\2\2\u060b"+
		"\u060c\7t\2\2\u060c\u0158\3\2\2\2\u060d\u060e\7e\2\2\u060e\u060f\7j\2"+
		"\2\u060f\u0610\7t\2\2\u0610\u0611\7{\2\2\u0611\u0612\7u\2\2\u0612\u0613"+
		"\7q\2\2\u0613\u0614\7n\2\2\u0614\u0615\7k\2\2\u0615\u0616\7v\2\2\u0616"+
		"\u0617\7g\2\2\u0617\u015a\3\2\2\2\u0618\u0619\7e\2\2\u0619\u061a\7t\2"+
		"\2\u061a\u061b\7{\2\2\u061b\u061c\7u\2\2\u061c\u061d\7v\2\2\u061d\u061e"+
		"\7c\2\2\u061e\u061f\7n\2\2\u061f\u015c\3\2\2\2\u0620\u0621\7j\2\2\u0621"+
		"\u0622\7q\2\2\u0622\u0623\7t\2\2\u0623\u0624\7p\2\2\u0624\u015e\3\2\2"+
		"\2\u0625\u0626\7u\2\2\u0626\u0627\7e\2\2\u0627\u0628\7c\2\2\u0628\u0629"+
		"\7n\2\2\u0629\u062a\7g\2\2\u062a\u062b\7a\2\2\u062b\u062c\7q\2\2\u062c"+
		"\u062d\7h\2\2\u062d\u062e\7a\2\2\u062e\u062f\7f\2\2\u062f\u0630\7t\2\2"+
		"\u0630\u0631\7c\2\2\u0631\u0632\7i\2\2\u0632\u0633\7q\2\2\u0633\u0634"+
		"\7p\2\2\u0634\u0160\3\2\2\2\u0635\u0636\7e\2\2\u0636\u0637\7q\2\2\u0637"+
		"\u0638\7v\2\2\u0638\u0639\7v\2\2\u0639\u063a\7q\2\2\u063a\u063b\7p\2\2"+
		"\u063b\u0162\3\2\2\2\u063c\u063d\7f\2\2\u063d\u063e\7{\2\2\u063e\u063f"+
		"\7g\2\2\u063f\u0640\7u\2\2\u0640\u0641\7v\2\2\u0641\u0642\7w\2\2\u0642"+
		"\u0643\7h\2\2\u0643\u0644\7h\2\2\u0644\u0164\3\2\2\2\u0645\u0646\7e\2"+
		"\2\u0646\u0647\7q\2\2\u0647\u0648\7d\2\2\u0648\u0649\7y\2\2\u0649\u064a"+
		"\7g\2\2\u064a\u064b\7d\2\2\u064b\u0166\3\2\2\2\u064c\u064d\7t\2\2\u064d"+
		"\u064e\7w\2\2\u064e\u064f\7p\2\2\u064f\u0650\7g\2\2\u0650\u0651\7a\2\2"+
		"\u0651\u0652\7z\2\2\u0652\u0653\7r\2\2\u0653\u0168\3\2\2\2\u0654\u0655"+
		"\7t\2\2\u0655\u0656\7w\2\2\u0656\u0657\7p\2\2\u0657\u0658\7g\2\2\u0658"+
		"\u0659\7a\2\2\u0659\u065a\7u\2\2\u065a\u065b\7r\2\2\u065b\u016a\3\2\2"+
		"\2\u065c\u065d\7t\2\2\u065d\u065e\7w\2\2\u065e\u065f\7p\2\2\u065f\u0660"+
		"\7g\2\2\u0660\u0661\7a\2\2\u0661\u0662\7t\2\2\u0662\u0663\7g\2\2\u0663"+
		"\u0664\7o\2\2\u0664\u0665\7q\2\2\u0665\u0666\7x\2\2\u0666\u0667\7g\2\2"+
		"\u0667\u0668\7a\2\2\u0668\u0669\7r\2\2\u0669\u066a\7g\2\2\u066a\u066b"+
		"\7p\2\2\u066b\u066c\7c\2\2\u066c\u066d\7n\2\2\u066d\u066e\7v\2\2\u066e"+
		"\u066f\7{\2\2\u066f\u016c\3\2\2\2\u0670\u0672\t\6\2\2\u0671\u0670\3\2"+
		"\2\2\u0672\u0673\3\2\2\2\u0673\u0671\3\2\2\2\u0673\u0674\3\2\2\2\u0674"+
		"\u0678\3\2\2\2\u0675\u0677\t\7\2\2\u0676\u0675\3\2\2\2\u0677\u067a\3\2"+
		"\2\2\u0678\u0676\3\2\2\2\u0678\u0679\3\2\2\2\u0679\u016e\3\2\2\2\u067a"+
		"\u0678\3\2\2\2\u067b\u067d\t\b\2\2\u067c\u067b\3\2\2\2\u067d\u067e\3\2"+
		"\2\2\u067e\u067c\3\2\2\2\u067e\u067f\3\2\2\2\u067f\u0680\3\2\2\2\u0680"+
		"\u0681\b\u00b8\2\2\u0681\u0170\3\2\2\2\u0682\u0683\7\61\2\2\u0683\u0684"+
		"\7\61\2\2\u0684\u0688\3\2\2\2\u0685\u0687\n\t\2\2\u0686\u0685\3\2\2\2"+
		"\u0687\u068a\3\2\2\2\u0688\u0686\3\2\2\2\u0688\u0689\3\2\2\2\u0689\u068c"+
		"\3\2\2\2\u068a\u0688\3\2\2\2\u068b\u068d\7\17\2\2\u068c\u068b\3\2\2\2"+
		"\u068c\u068d\3\2\2\2\u068d\u068e\3\2\2\2\u068e\u068f\7\f\2\2\u068f\u0690"+
		"\3\2\2\2\u0690\u0691\b\u00b9\2\2\u0691\u0172\3\2\2\2\u0692\u0693\7\61"+
		"\2\2\u0693\u0694\7,\2\2\u0694\u0698\3\2\2\2\u0695\u0697\13\2\2\2\u0696"+
		"\u0695\3\2\2\2\u0697\u069a\3\2\2\2\u0698\u0699\3\2\2\2\u0698\u0696\3\2"+
		"\2\2\u0699\u069b\3\2\2\2\u069a\u0698\3\2\2\2\u069b\u069c\7,\2\2\u069c"+
		"\u069d\7\61\2\2\u069d\u069e\3\2\2\2\u069e\u069f\b\u00ba\2\2\u069f\u0174"+
		"\3\2\2\2\r\2\u0277\u0280\u02a9\u02ac\u0673\u0678\u067e\u0688\u068c\u0698"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}