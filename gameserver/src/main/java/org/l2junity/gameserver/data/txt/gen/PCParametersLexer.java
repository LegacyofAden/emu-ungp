// Generated from org\l2junity\gameserver\data\txt\gen\PCParameters.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

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
public class PCParametersLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "FFIGHTER", "MFIGHTER", "FMAGIC", "MMAGIC", "FELF_FIGHTER", "MELF_FIGHTER", 
		"FELF_MAGIC", "MELF_MAGIC", "FDARKELF_FIGHTER", "MDARKELF_FIGHTER", "FDARKELF_MAGIC", 
		"MDARKELF_MAGIC", "FORC_FIGHTER", "MORC_FIGHTER", "FSHAMAN", "MSHAMAN", 
		"FDWARF_FIGHTER", "MDWARF_FIGHTER", "FDWARF_MAGE", "MDWARF_MAGE", "FKAMAEL_SOLDIER", 
		"MKAMAEL_SOLDIER", "FKAMAEL_MAGE", "MKAMAEL_MAGE", "CATEGORY", "BOOLEAN", 
		"INTEGER", "DOUBLE", "FIRE", "WATER", "EARTH", "WIND", "UNHOLY", "HOLY", 
		"DECIMAL", "MINUS", "BINARY_DIGIT", "DIGIT", "NON_ZERO_DIGIT", "ZERO_DIGIT", 
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


	public PCParametersLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PCParameters.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\u00a2\u06b4\b\1\4"+
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
		"\t\u00a4\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3"+
		"!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3%\3%\3&\5&\u02eb"+
		"\n&\3&\3&\3\'\3\'\3\'\6\'\u02f2\n\'\r\'\16\'\u02f3\3(\3(\3(\3(\3(\3)\3"+
		")\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3"+
		"-\3-\3-\3-\3-\3.\3.\3.\7.\u031b\n.\f.\16.\u031e\13.\5.\u0320\n.\3/\3/"+
		"\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\65\3\65"+
		"\3\65\3\66\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67"+
		"\38\38\38\38\38\38\38\38\38\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3:\3;"+
		"\3;\3;\3;\3;\3;\3;\3;\3;\3;\3<\3<\3<\3<\3<\3<\3=\3=\3=\3=\3>\3>\3>\3>"+
		"\3>\3>\3>\3>\3>\3>\3?\3?\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3A\3A\3A"+
		"\3A\3A\3A\3A\3B\3B\3B\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3E"+
		"\3E\3E\3E\3E\3E\3E\3E\3E\3E\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3G\3G\3G\3G"+
		"\3G\3H\3H\3H\3H\3I\3I\3I\3I\3I\3I\3I\3J\3J\3J\3J\3K\3K\3K\3K\3K\3K\3K"+
		"\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3L\3M\3M\3M\3M\3M\3M\3N"+
		"\3N\3N\3N\3N\3O\3O\3O\3O\3O\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3P\3P\3P\3P"+
		"\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3R\3R\3R\3R\3S\3S\3S\3S\3S\3S"+
		"\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3T\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U"+
		"\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X"+
		"\3X\3X\3X\3X\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z"+
		"\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\"+
		"\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3"+
		"_\3_\3_\3_\3`\3`\3`\3`\3`\3a\3a\3a\3a\3a\3a\3a\3b\3b\3b\3b\3b\3c\3c\3"+
		"c\3c\3c\3d\3d\3d\3d\3d\3d\3d\3d\3d\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3e\3"+
		"f\3f\3f\3f\3f\3f\3f\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3g\3h\3h\3h\3"+
		"h\3h\3h\3h\3h\3h\3i\3i\3i\3i\3i\3j\3j\3j\3j\3j\3j\3j\3j\3j\3k\3k\3k\3"+
		"k\3k\3k\3k\3k\3k\3k\3k\3l\3l\3l\3l\3m\3m\3m\3m\3m\3m\3n\3n\3n\3n\3n\3"+
		"n\3o\3o\3o\3o\3o\3o\3p\3p\3p\3p\3p\3p\3q\3q\3q\3q\3q\3q\3r\3r\3r\3r\3"+
		"r\3r\3r\3s\3s\3s\3s\3s\3s\3t\3t\3t\3t\3t\3t\3u\3u\3u\3u\3u\3v\3v\3v\3"+
		"v\3v\3w\3w\3w\3w\3w\3x\3x\3x\3x\3x\3x\3x\3y\3y\3y\3y\3y\3y\3y\3y\3y\3"+
		"z\3z\3z\3z\3z\3{\3{\3{\3{\3{\3|\3|\3|\3|\3|\3|\3|\3|\3}\3}\3}\3}\3}\3"+
		"}\3}\3}\3~\3~\3~\3~\3~\3\177\3\177\3\177\3\177\3\177\3\u0080\3\u0080\3"+
		"\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0080\3\u0081"+
		"\3\u0081\3\u0081\3\u0081\3\u0081\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082"+
		"\3\u0082\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083\3\u0083"+
		"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084"+
		"\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085"+
		"\3\u0085\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086"+
		"\3\u0086\3\u0086\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0088"+
		"\3\u0088\3\u0088\3\u0088\3\u0088\3\u0088\3\u0089\3\u0089\3\u0089\3\u0089"+
		"\3\u0089\3\u0089\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a"+
		"\3\u008a\3\u008a\3\u008a\3\u008a\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b"+
		"\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008c\3\u008d\3\u008d\3\u008d"+
		"\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008e\3\u008e\3\u008e\3\u008e"+
		"\3\u008e\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u008f\3\u0090"+
		"\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090"+
		"\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091"+
		"\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092"+
		"\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093"+
		"\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094"+
		"\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095"+
		"\3\u0095\3\u0095\3\u0095\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0097"+
		"\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\3\u0098\3\u0098\3\u0098"+
		"\3\u0098\3\u0098\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099"+
		"\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a\3\u009a"+
		"\3\u009a\3\u009a\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b"+
		"\3\u009b\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\3\u009d\3\u009d\3\u009d\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e"+
		"\3\u009e\3\u009e\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f"+
		"\3\u009f\3\u009f\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0"+
		"\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a1\3\u00a2"+
		"\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a3\3\u00a3"+
		"\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3"+
		"\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3"+
		"\3\u00a4\6\u00a4\u0686\n\u00a4\r\u00a4\16\u00a4\u0687\3\u00a4\7\u00a4"+
		"\u068b\n\u00a4\f\u00a4\16\u00a4\u068e\13\u00a4\3\u00a5\6\u00a5\u0691\n"+
		"\u00a5\r\u00a5\16\u00a5\u0692\3\u00a5\3\u00a5\3\u00a6\3\u00a6\3\u00a6"+
		"\3\u00a6\7\u00a6\u069b\n\u00a6\f\u00a6\16\u00a6\u069e\13\u00a6\3\u00a6"+
		"\5\u00a6\u06a1\n\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a6\3\u00a7\3\u00a7"+
		"\3\u00a7\3\u00a7\7\u00a7\u06ab\n\u00a7\f\u00a7\16\u00a7\u06ae\13\u00a7"+
		"\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u00a7\3\u06ac\2\u00a8\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q*S+U,W-Y.[\2]\2_\2a\2c\2e\2g/i\60k\61m\62o\63q\64s\65u\66w"+
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
		"\u009d\u0145\u009e\u0147\u009f\u0149\u00a0\u014b\u00a1\u014d\u00a2\3\2"+
		"\n\3\2\62\63\3\2\62;\3\2\63;\3\2\62\62\6\2\62;C\\aac|\n\2)),,/\60\62<"+
		"C\\aac|\u0080\u0080\5\2\13\f\16\17\"\"\4\2\f\f\17\17\2\u06b7\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2"+
		"\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3"+
		"\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2"+
		"\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2"+
		"W\3\2\2\2\2Y\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3"+
		"\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2"+
		"\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3"+
		"\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2"+
		"\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097"+
		"\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2"+
		"\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2\2\2\u00a9"+
		"\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1\3\2\2"+
		"\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2\2\2\u00bb"+
		"\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3\3\2\2"+
		"\2\2\u00c5\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2\2\2\u00cd"+
		"\3\2\2\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5\3\2\2"+
		"\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2\2\2\u00df"+
		"\3\2\2\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7\3\2\2"+
		"\2\2\u00e9\3\2\2\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2\2\2\u00ef\3\2\2\2\2\u00f1"+
		"\3\2\2\2\2\u00f3\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7\3\2\2\2\2\u00f9\3\2\2"+
		"\2\2\u00fb\3\2\2\2\2\u00fd\3\2\2\2\2\u00ff\3\2\2\2\2\u0101\3\2\2\2\2\u0103"+
		"\3\2\2\2\2\u0105\3\2\2\2\2\u0107\3\2\2\2\2\u0109\3\2\2\2\2\u010b\3\2\2"+
		"\2\2\u010d\3\2\2\2\2\u010f\3\2\2\2\2\u0111\3\2\2\2\2\u0113\3\2\2\2\2\u0115"+
		"\3\2\2\2\2\u0117\3\2\2\2\2\u0119\3\2\2\2\2\u011b\3\2\2\2\2\u011d\3\2\2"+
		"\2\2\u011f\3\2\2\2\2\u0121\3\2\2\2\2\u0123\3\2\2\2\2\u0125\3\2\2\2\2\u0127"+
		"\3\2\2\2\2\u0129\3\2\2\2\2\u012b\3\2\2\2\2\u012d\3\2\2\2\2\u012f\3\2\2"+
		"\2\2\u0131\3\2\2\2\2\u0133\3\2\2\2\2\u0135\3\2\2\2\2\u0137\3\2\2\2\2\u0139"+
		"\3\2\2\2\2\u013b\3\2\2\2\2\u013d\3\2\2\2\2\u013f\3\2\2\2\2\u0141\3\2\2"+
		"\2\2\u0143\3\2\2\2\2\u0145\3\2\2\2\2\u0147\3\2\2\2\2\u0149\3\2\2\2\2\u014b"+
		"\3\2\2\2\2\u014d\3\2\2\2\3\u014f\3\2\2\2\5\u016a\3\2\2\2\7\u016c\3\2\2"+
		"\2\t\u0189\3\2\2\2\13\u01a4\3\2\2\2\r\u01af\3\2\2\2\17\u01b1\3\2\2\2\21"+
		"\u01b3\3\2\2\2\23\u01b5\3\2\2\2\25\u01b7\3\2\2\2\27\u01cd\3\2\2\2\31\u01d6"+
		"\3\2\2\2\33\u01df\3\2\2\2\35\u01e6\3\2\2\2\37\u01ed\3\2\2\2!\u01f9\3\2"+
		"\2\2#\u0205\3\2\2\2%\u020f\3\2\2\2\'\u0219\3\2\2\2)\u0229\3\2\2\2+\u0239"+
		"\3\2\2\2-\u0247\3\2\2\2/\u0255\3\2\2\2\61\u0261\3\2\2\2\63\u026d\3\2\2"+
		"\2\65\u0275\3\2\2\2\67\u027d\3\2\2\29\u028b\3\2\2\2;\u0299\3\2\2\2=\u02a3"+
		"\3\2\2\2?\u02ae\3\2\2\2A\u02bd\3\2\2\2C\u02cc\3\2\2\2E\u02d8\3\2\2\2G"+
		"\u02e4\3\2\2\2I\u02e7\3\2\2\2K\u02ea\3\2\2\2M\u02ee\3\2\2\2O\u02f5\3\2"+
		"\2\2Q\u02fa\3\2\2\2S\u0300\3\2\2\2U\u0306\3\2\2\2W\u030b\3\2\2\2Y\u0312"+
		"\3\2\2\2[\u031f\3\2\2\2]\u0321\3\2\2\2_\u0323\3\2\2\2a\u0325\3\2\2\2c"+
		"\u0327\3\2\2\2e\u0329\3\2\2\2g\u032b\3\2\2\2i\u032d\3\2\2\2k\u0332\3\2"+
		"\2\2m\u0338\3\2\2\2o\u033f\3\2\2\2q\u0348\3\2\2\2s\u034e\3\2\2\2u\u0355"+
		"\3\2\2\2w\u035f\3\2\2\2y\u0365\3\2\2\2{\u0369\3\2\2\2}\u0373\3\2\2\2\177"+
		"\u037b\3\2\2\2\u0081\u0381\3\2\2\2\u0083\u0388\3\2\2\2\u0085\u038f\3\2"+
		"\2\2\u0087\u0396\3\2\2\2\u0089\u039a\3\2\2\2\u008b\u03a4\3\2\2\2\u008d"+
		"\u03aa\3\2\2\2\u008f\u03b3\3\2\2\2\u0091\u03b7\3\2\2\2\u0093\u03be\3\2"+
		"\2\2\u0095\u03c2\3\2\2\2\u0097\u03cc\3\2\2\2\u0099\u03d9\3\2\2\2\u009b"+
		"\u03df\3\2\2\2\u009d\u03e4\3\2\2\2\u009f\u03ed\3\2\2\2\u00a1\u03f8\3\2"+
		"\2\2\u00a3\u0400\3\2\2\2\u00a5\u0408\3\2\2\2\u00a7\u040e\3\2\2\2\u00a9"+
		"\u0419\3\2\2\2\u00ab\u042a\3\2\2\2\u00ad\u0432\3\2\2\2\u00af\u043a\3\2"+
		"\2\2\u00b1\u0443\3\2\2\2\u00b3\u044d\3\2\2\2\u00b5\u0458\3\2\2\2\u00b7"+
		"\u0465\3\2\2\2\u00b9\u0471\3\2\2\2\u00bb\u0477\3\2\2\2\u00bd\u047d\3\2"+
		"\2\2\u00bf\u0481\3\2\2\2\u00c1\u0486\3\2\2\2\u00c3\u048d\3\2\2\2\u00c5"+
		"\u0492\3\2\2\2\u00c7\u0497\3\2\2\2\u00c9\u04a0\3\2\2\2\u00cb\u04ab\3\2"+
		"\2\2\u00cd\u04b2\3\2\2\2\u00cf\u04bf\3\2\2\2\u00d1\u04c8\3\2\2\2\u00d3"+
		"\u04cd\3\2\2\2\u00d5\u04d6\3\2\2\2\u00d7\u04e1\3\2\2\2\u00d9\u04e5\3\2"+
		"\2\2\u00db\u04eb\3\2\2\2\u00dd\u04f1\3\2\2\2\u00df\u04f7\3\2\2\2\u00e1"+
		"\u04fd\3\2\2\2\u00e3\u0503\3\2\2\2\u00e5\u050a\3\2\2\2\u00e7\u0510\3\2"+
		"\2\2\u00e9\u0516\3\2\2\2\u00eb\u051b\3\2\2\2\u00ed\u0520\3\2\2\2\u00ef"+
		"\u0525\3\2\2\2\u00f1\u052c\3\2\2\2\u00f3\u0535\3\2\2\2\u00f5\u053a\3\2"+
		"\2\2\u00f7\u053f\3\2\2\2\u00f9\u0547\3\2\2\2\u00fb\u054f\3\2\2\2\u00fd"+
		"\u0554\3\2\2\2\u00ff\u0559\3\2\2\2\u0101\u0563\3\2\2\2\u0103\u0568\3\2"+
		"\2\2\u0105\u056e\3\2\2\2\u0107\u0576\3\2\2\2\u0109\u057f\3\2\2\2\u010b"+
		"\u0589\3\2\2\2\u010d\u0593\3\2\2\2\u010f\u0599\3\2\2\2\u0111\u059f\3\2"+
		"\2\2\u0113\u05a5\3\2\2\2\u0115\u05b0\3\2\2\2\u0117\u05b5\3\2\2\2\u0119"+
		"\u05bb\3\2\2\2\u011b\u05c3\3\2\2\2\u011d\u05c8\3\2\2\2\u011f\u05cf\3\2"+
		"\2\2\u0121\u05da\3\2\2\2\u0123\u05e2\3\2\2\2\u0125\u05eb\3\2\2\2\u0127"+
		"\u05f7\3\2\2\2\u0129\u0603\3\2\2\2\u012b\u0609\3\2\2\2\u012d\u060e\3\2"+
		"\2\2\u012f\u0615\3\2\2\2\u0131\u061a\3\2\2\2\u0133\u0621\3\2\2\2\u0135"+
		"\u062c\3\2\2\2\u0137\u0634\3\2\2\2\u0139\u0639\3\2\2\2\u013b\u0649\3\2"+
		"\2\2\u013d\u0650\3\2\2\2\u013f\u0659\3\2\2\2\u0141\u0660\3\2\2\2\u0143"+
		"\u0668\3\2\2\2\u0145\u0670\3\2\2\2\u0147\u0685\3\2\2\2\u0149\u0690\3\2"+
		"\2\2\u014b\u0696\3\2\2\2\u014d\u06a6\3\2\2\2\u014f\u0150\7d\2\2\u0150"+
		"\u0151\7c\2\2\u0151\u0152\7u\2\2\u0152\u0153\7g\2\2\u0153\u0154\7a\2\2"+
		"\u0154\u0155\7r\2\2\u0155\u0156\7j\2\2\u0156\u0157\7{\2\2\u0157\u0158"+
		"\7u\2\2\u0158\u0159\7k\2\2\u0159\u015a\7e\2\2\u015a\u015b\7c\2\2\u015b"+
		"\u015c\7n\2\2\u015c\u015d\7a\2\2\u015d\u015e\7c\2\2\u015e\u015f\7v\2\2"+
		"\u015f\u0160\7v\2\2\u0160\u0161\7c\2\2\u0161\u0162\7e\2\2\u0162\u0163"+
		"\7m\2\2\u0163\u0164\7a\2\2\u0164\u0165\7d\2\2\u0165\u0166\7g\2\2\u0166"+
		"\u0167\7i\2\2\u0167\u0168\7k\2\2\u0168\u0169\7p\2\2\u0169\4\3\2\2\2\u016a"+
		"\u016b\7?\2\2\u016b\6\3\2\2\2\u016c\u016d\7r\2\2\u016d\u016e\7e\2\2\u016e"+
		"\u016f\7a\2\2\u016f\u0170\7e\2\2\u0170\u0171\7q\2\2\u0171\u0172\7n\2\2"+
		"\u0172\u0173\7n\2\2\u0173\u0174\7k\2\2\u0174\u0175\7u\2\2\u0175\u0176"+
		"\7k\2\2\u0176\u0177\7q\2\2\u0177\u0178\7p\2\2\u0178\u0179\7a\2\2\u0179"+
		"\u017a\7d\2\2\u017a\u017b\7q\2\2\u017b\u017c\7z\2\2\u017c\u017d\7a\2\2"+
		"\u017d\u017e\7v\2\2\u017e\u017f\7c\2\2\u017f\u0180\7d\2\2\u0180\u0181"+
		"\7n\2\2\u0181\u0182\7g\2\2\u0182\u0183\7a\2\2\u0183\u0184\7d\2\2\u0184"+
		"\u0185\7g\2\2\u0185\u0186\7i\2\2\u0186\u0187\7k\2\2\u0187\u0188\7p\2\2"+
		"\u0188\b\3\2\2\2\u0189\u018a\7r\2\2\u018a\u018b\7e\2\2\u018b\u018c\7a"+
		"\2\2\u018c\u018d\7e\2\2\u018d\u018e\7q\2\2\u018e\u018f\7n\2\2\u018f\u0190"+
		"\7n\2\2\u0190\u0191\7k\2\2\u0191\u0192\7u\2\2\u0192\u0193\7k\2\2\u0193"+
		"\u0194\7q\2\2\u0194\u0195\7p\2\2\u0195\u0196\7a\2\2\u0196\u0197\7d\2\2"+
		"\u0197\u0198\7q\2\2\u0198\u0199\7z\2\2\u0199\u019a\7a\2\2\u019a\u019b"+
		"\7v\2\2\u019b\u019c\7c\2\2\u019c\u019d\7d\2\2\u019d\u019e\7n\2\2\u019e"+
		"\u019f\7g\2\2\u019f\u01a0\7a\2\2\u01a0\u01a1\7g\2\2\u01a1\u01a2\7p\2\2"+
		"\u01a2\u01a3\7f\2\2\u01a3\n\3\2\2\2\u01a4\u01a5\7u\2\2\u01a5\u01a6\7n"+
		"\2\2\u01a6\u01a7\7q\2\2\u01a7\u01a8\7v\2\2\u01a8\u01a9\7a\2\2\u01a9\u01aa"+
		"\7n\2\2\u01aa\u01ab\7j\2\2\u01ab\u01ac\7c\2\2\u01ac\u01ad\7p\2\2\u01ad"+
		"\u01ae\7f\2\2\u01ae\f\3\2\2\2\u01af\u01b0\7]\2\2\u01b0\16\3\2\2\2\u01b1"+
		"\u01b2\7_\2\2\u01b2\20\3\2\2\2\u01b3\u01b4\7}\2\2\u01b4\22\3\2\2\2\u01b5"+
		"\u01b6\7\177\2\2\u01b6\24\3\2\2\2\u01b7\u01b8\7d\2\2\u01b8\u01b9\7c\2"+
		"\2\u01b9\u01ba\7u\2\2\u01ba\u01bb\7g\2\2\u01bb\u01bc\7a\2\2\u01bc\u01bd"+
		"\7c\2\2\u01bd\u01be\7v\2\2\u01be\u01bf\7v\2\2\u01bf\u01c0\7t\2\2\u01c0"+
		"\u01c1\7k\2\2\u01c1\u01c2\7d\2\2\u01c2\u01c3\7w\2\2\u01c3\u01c4\7v\2\2"+
		"\u01c4\u01c5\7g\2\2\u01c5\u01c6\7a\2\2\u01c6\u01c7\7c\2\2\u01c7\u01c8"+
		"\7v\2\2\u01c8\u01c9\7v\2\2\u01c9\u01ca\7c\2\2\u01ca\u01cb\7e\2\2\u01cb"+
		"\u01cc\7m\2\2\u01cc\26\3\2\2\2\u01cd\u01ce\7H\2\2\u01ce\u01cf\7H\2\2\u01cf"+
		"\u01d0\7k\2\2\u01d0\u01d1\7i\2\2\u01d1\u01d2\7j\2\2\u01d2\u01d3\7v\2\2"+
		"\u01d3\u01d4\7g\2\2\u01d4\u01d5\7t\2\2\u01d5\30\3\2\2\2\u01d6\u01d7\7"+
		"O\2\2\u01d7\u01d8\7H\2\2\u01d8\u01d9\7k\2\2\u01d9\u01da\7i\2\2\u01da\u01db"+
		"\7j\2\2\u01db\u01dc\7v\2\2\u01dc\u01dd\7g\2\2\u01dd\u01de\7t\2\2\u01de"+
		"\32\3\2\2\2\u01df\u01e0\7H\2\2\u01e0\u01e1\7O\2\2\u01e1\u01e2\7c\2\2\u01e2"+
		"\u01e3\7i\2\2\u01e3\u01e4\7k\2\2\u01e4\u01e5\7e\2\2\u01e5\34\3\2\2\2\u01e6"+
		"\u01e7\7O\2\2\u01e7\u01e8\7O\2\2\u01e8\u01e9\7c\2\2\u01e9\u01ea\7i\2\2"+
		"\u01ea\u01eb\7k\2\2\u01eb\u01ec\7e\2\2\u01ec\36\3\2\2\2\u01ed\u01ee\7"+
		"H\2\2\u01ee\u01ef\7G\2\2\u01ef\u01f0\7n\2\2\u01f0\u01f1\7h\2\2\u01f1\u01f2"+
		"\7H\2\2\u01f2\u01f3\7k\2\2\u01f3\u01f4\7i\2\2\u01f4\u01f5\7j\2\2\u01f5"+
		"\u01f6\7v\2\2\u01f6\u01f7\7g\2\2\u01f7\u01f8\7t\2\2\u01f8 \3\2\2\2\u01f9"+
		"\u01fa\7O\2\2\u01fa\u01fb\7G\2\2\u01fb\u01fc\7n\2\2\u01fc\u01fd\7h\2\2"+
		"\u01fd\u01fe\7H\2\2\u01fe\u01ff\7k\2\2\u01ff\u0200\7i\2\2\u0200\u0201"+
		"\7j\2\2\u0201\u0202\7v\2\2\u0202\u0203\7g\2\2\u0203\u0204\7t\2\2\u0204"+
		"\"\3\2\2\2\u0205\u0206\7H\2\2\u0206\u0207\7G\2\2\u0207\u0208\7n\2\2\u0208"+
		"\u0209\7h\2\2\u0209\u020a\7O\2\2\u020a\u020b\7c\2\2\u020b\u020c\7i\2\2"+
		"\u020c\u020d\7k\2\2\u020d\u020e\7e\2\2\u020e$\3\2\2\2\u020f\u0210\7O\2"+
		"\2\u0210\u0211\7G\2\2\u0211\u0212\7n\2\2\u0212\u0213\7h\2\2\u0213\u0214"+
		"\7O\2\2\u0214\u0215\7c\2\2\u0215\u0216\7i\2\2\u0216\u0217\7k\2\2\u0217"+
		"\u0218\7e\2\2\u0218&\3\2\2\2\u0219\u021a\7H\2\2\u021a\u021b\7F\2\2\u021b"+
		"\u021c\7c\2\2\u021c\u021d\7t\2\2\u021d\u021e\7m\2\2\u021e\u021f\7g\2\2"+
		"\u021f\u0220\7n\2\2\u0220\u0221\7h\2\2\u0221\u0222\7H\2\2\u0222\u0223"+
		"\7k\2\2\u0223\u0224\7i\2\2\u0224\u0225\7j\2\2\u0225\u0226\7v\2\2\u0226"+
		"\u0227\7g\2\2\u0227\u0228\7t\2\2\u0228(\3\2\2\2\u0229\u022a\7O\2\2\u022a"+
		"\u022b\7F\2\2\u022b\u022c\7c\2\2\u022c\u022d\7t\2\2\u022d\u022e\7m\2\2"+
		"\u022e\u022f\7g\2\2\u022f\u0230\7n\2\2\u0230\u0231\7h\2\2\u0231\u0232"+
		"\7H\2\2\u0232\u0233\7k\2\2\u0233\u0234\7i\2\2\u0234\u0235\7j\2\2\u0235"+
		"\u0236\7v\2\2\u0236\u0237\7g\2\2\u0237\u0238\7t\2\2\u0238*\3\2\2\2\u0239"+
		"\u023a\7H\2\2\u023a\u023b\7F\2\2\u023b\u023c\7c\2\2\u023c\u023d\7t\2\2"+
		"\u023d\u023e\7m\2\2\u023e\u023f\7g\2\2\u023f\u0240\7n\2\2\u0240\u0241"+
		"\7h\2\2\u0241\u0242\7O\2\2\u0242\u0243\7c\2\2\u0243\u0244\7i\2\2\u0244"+
		"\u0245\7k\2\2\u0245\u0246\7e\2\2\u0246,\3\2\2\2\u0247\u0248\7O\2\2\u0248"+
		"\u0249\7F\2\2\u0249\u024a\7c\2\2\u024a\u024b\7t\2\2\u024b\u024c\7m\2\2"+
		"\u024c\u024d\7g\2\2\u024d\u024e\7n\2\2\u024e\u024f\7h\2\2\u024f\u0250"+
		"\7O\2\2\u0250\u0251\7c\2\2\u0251\u0252\7i\2\2\u0252\u0253\7k\2\2\u0253"+
		"\u0254\7e\2\2\u0254.\3\2\2\2\u0255\u0256\7H\2\2\u0256\u0257\7Q\2\2\u0257"+
		"\u0258\7t\2\2\u0258\u0259\7e\2\2\u0259\u025a\7H\2\2\u025a\u025b\7k\2\2"+
		"\u025b\u025c\7i\2\2\u025c\u025d\7j\2\2\u025d\u025e\7v\2\2\u025e\u025f"+
		"\7g\2\2\u025f\u0260\7t\2\2\u0260\60\3\2\2\2\u0261\u0262\7O\2\2\u0262\u0263"+
		"\7Q\2\2\u0263\u0264\7t\2\2\u0264\u0265\7e\2\2\u0265\u0266\7H\2\2\u0266"+
		"\u0267\7k\2\2\u0267\u0268\7i\2\2\u0268\u0269\7j\2\2\u0269\u026a\7v\2\2"+
		"\u026a\u026b\7g\2\2\u026b\u026c\7t\2\2\u026c\62\3\2\2\2\u026d\u026e\7"+
		"H\2\2\u026e\u026f\7U\2\2\u026f\u0270\7j\2\2\u0270\u0271\7c\2\2\u0271\u0272"+
		"\7o\2\2\u0272\u0273\7c\2\2\u0273\u0274\7p\2\2\u0274\64\3\2\2\2\u0275\u0276"+
		"\7O\2\2\u0276\u0277\7U\2\2\u0277\u0278\7j\2\2\u0278\u0279\7c\2\2\u0279"+
		"\u027a\7o\2\2\u027a\u027b\7c\2\2\u027b\u027c\7p\2\2\u027c\66\3\2\2\2\u027d"+
		"\u027e\7H\2\2\u027e\u027f\7F\2\2\u027f\u0280\7y\2\2\u0280\u0281\7c\2\2"+
		"\u0281\u0282\7t\2\2\u0282\u0283\7h\2\2\u0283\u0284\7H\2\2\u0284\u0285"+
		"\7k\2\2\u0285\u0286\7i\2\2\u0286\u0287\7j\2\2\u0287\u0288\7v\2\2\u0288"+
		"\u0289\7g\2\2\u0289\u028a\7t\2\2\u028a8\3\2\2\2\u028b\u028c\7O\2\2\u028c"+
		"\u028d\7F\2\2\u028d\u028e\7y\2\2\u028e\u028f\7c\2\2\u028f\u0290\7t\2\2"+
		"\u0290\u0291\7h\2\2\u0291\u0292\7H\2\2\u0292\u0293\7k\2\2\u0293\u0294"+
		"\7i\2\2\u0294\u0295\7j\2\2\u0295\u0296\7v\2\2\u0296\u0297\7g\2\2\u0297"+
		"\u0298\7t\2\2\u0298:\3\2\2\2\u0299\u029a\7F\2\2\u029a\u029b\7y\2\2\u029b"+
		"\u029c\7c\2\2\u029c\u029d\7t\2\2\u029d\u029e\7h\2\2\u029e\u029f\7O\2\2"+
		"\u029f\u02a0\7c\2\2\u02a0\u02a1\7i\2\2\u02a1\u02a2\7g\2\2\u02a2<\3\2\2"+
		"\2\u02a3\u02a4\7O\2\2\u02a4\u02a5\7F\2\2\u02a5\u02a6\7y\2\2\u02a6\u02a7"+
		"\7c\2\2\u02a7\u02a8\7t\2\2\u02a8\u02a9\7h\2\2\u02a9\u02aa\7O\2\2\u02aa"+
		"\u02ab\7c\2\2\u02ab\u02ac\7i\2\2\u02ac\u02ad\7g\2\2\u02ad>\3\2\2\2\u02ae"+
		"\u02af\7H\2\2\u02af\u02b0\7M\2\2\u02b0\u02b1\7c\2\2\u02b1\u02b2\7o\2\2"+
		"\u02b2\u02b3\7c\2\2\u02b3\u02b4\7g\2\2\u02b4\u02b5\7n\2\2\u02b5\u02b6"+
		"\7U\2\2\u02b6\u02b7\7q\2\2\u02b7\u02b8\7n\2\2\u02b8\u02b9\7f\2\2\u02b9"+
		"\u02ba\7k\2\2\u02ba\u02bb\7g\2\2\u02bb\u02bc\7t\2\2\u02bc@\3\2\2\2\u02bd"+
		"\u02be\7O\2\2\u02be\u02bf\7M\2\2\u02bf\u02c0\7c\2\2\u02c0\u02c1\7o\2\2"+
		"\u02c1\u02c2\7c\2\2\u02c2\u02c3\7g\2\2\u02c3\u02c4\7n\2\2\u02c4\u02c5"+
		"\7U\2\2\u02c5\u02c6\7q\2\2\u02c6\u02c7\7n\2\2\u02c7\u02c8\7f\2\2\u02c8"+
		"\u02c9\7k\2\2\u02c9\u02ca\7g\2\2\u02ca\u02cb\7t\2\2\u02cbB\3\2\2\2\u02cc"+
		"\u02cd\7H\2\2\u02cd\u02ce\7M\2\2\u02ce\u02cf\7c\2\2\u02cf\u02d0\7o\2\2"+
		"\u02d0\u02d1\7c\2\2\u02d1\u02d2\7g\2\2\u02d2\u02d3\7n\2\2\u02d3\u02d4"+
		"\7O\2\2\u02d4\u02d5\7c\2\2\u02d5\u02d6\7i\2\2\u02d6\u02d7\7g\2\2\u02d7"+
		"D\3\2\2\2\u02d8\u02d9\7O\2\2\u02d9\u02da\7M\2\2\u02da\u02db\7c\2\2\u02db"+
		"\u02dc\7o\2\2\u02dc\u02dd\7c\2\2\u02dd\u02de\7g\2\2\u02de\u02df\7n\2\2"+
		"\u02df\u02e0\7O\2\2\u02e0\u02e1\7c\2\2\u02e1\u02e2\7i\2\2\u02e2\u02e3"+
		"\7g\2\2\u02e3F\3\2\2\2\u02e4\u02e5\7B\2\2\u02e5\u02e6\5\u0147\u00a4\2"+
		"\u02e6H\3\2\2\2\u02e7\u02e8\5_\60\2\u02e8J\3\2\2\2\u02e9\u02eb\5]/\2\u02ea"+
		"\u02e9\3\2\2\2\u02ea\u02eb\3\2\2\2\u02eb\u02ec\3\2\2\2\u02ec\u02ed\5["+
		".\2\u02edL\3\2\2\2\u02ee\u02ef\5K&\2\u02ef\u02f1\7\60\2\2\u02f0\u02f2"+
		"\5a\61\2\u02f1\u02f0\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3\u02f1\3\2\2\2\u02f3"+
		"\u02f4\3\2\2\2\u02f4N\3\2\2\2\u02f5\u02f6\7h\2\2\u02f6\u02f7\7k\2\2\u02f7"+
		"\u02f8\7t\2\2\u02f8\u02f9\7g\2\2\u02f9P\3\2\2\2\u02fa\u02fb\7y\2\2\u02fb"+
		"\u02fc\7c\2\2\u02fc\u02fd\7v\2\2\u02fd\u02fe\7g\2\2\u02fe\u02ff\7t\2\2"+
		"\u02ffR\3\2\2\2\u0300\u0301\7g\2\2\u0301\u0302\7c\2\2\u0302\u0303\7t\2"+
		"\2\u0303\u0304\7v\2\2\u0304\u0305\7j\2\2\u0305T\3\2\2\2\u0306\u0307\7"+
		"y\2\2\u0307\u0308\7k\2\2\u0308\u0309\7p\2\2\u0309\u030a\7f\2\2\u030aV"+
		"\3\2\2\2\u030b\u030c\7w\2\2\u030c\u030d\7p\2\2\u030d\u030e\7j\2\2\u030e"+
		"\u030f\7q\2\2\u030f\u0310\7n\2\2\u0310\u0311\7{\2\2\u0311X\3\2\2\2\u0312"+
		"\u0313\7j\2\2\u0313\u0314\7q\2\2\u0314\u0315\7n\2\2\u0315\u0316\7{\2\2"+
		"\u0316Z\3\2\2\2\u0317\u0320\5e\63\2\u0318\u031c\5c\62\2\u0319\u031b\5"+
		"a\61\2\u031a\u0319\3\2\2\2\u031b\u031e\3\2\2\2\u031c\u031a\3\2\2\2\u031c"+
		"\u031d\3\2\2\2\u031d\u0320\3\2\2\2\u031e\u031c\3\2\2\2\u031f\u0317\3\2"+
		"\2\2\u031f\u0318\3\2\2\2\u0320\\\3\2\2\2\u0321\u0322\7/\2\2\u0322^\3\2"+
		"\2\2\u0323\u0324\t\2\2\2\u0324`\3\2\2\2\u0325\u0326\t\3\2\2\u0326b\3\2"+
		"\2\2\u0327\u0328\t\4\2\2\u0328d\3\2\2\2\u0329\u032a\t\5\2\2\u032af\3\2"+
		"\2\2\u032b\u032c\7=\2\2\u032ch\3\2\2\2\u032d\u032e\7p\2\2\u032e\u032f"+
		"\7q\2\2\u032f\u0330\7p\2\2\u0330\u0331\7g\2\2\u0331j\3\2\2\2\u0332\u0333"+
		"\7h\2\2\u0333\u0334\7c\2\2\u0334\u0335\7k\2\2\u0335\u0336\7t\2\2\u0336"+
		"\u0337\7{\2\2\u0337l\3\2\2\2\u0338\u0339\7c\2\2\u0339\u033a\7p\2\2\u033a"+
		"\u033b\7k\2\2\u033b\u033c\7o\2\2\u033c\u033d\7c\2\2\u033d\u033e\7n\2\2"+
		"\u033en\3\2\2\2\u033f\u0340\7j\2\2\u0340\u0341\7w\2\2\u0341\u0342\7o\2"+
		"\2\u0342\u0343\7c\2\2\u0343\u0344\7p\2\2\u0344\u0345\7q\2\2\u0345\u0346"+
		"\7k\2\2\u0346\u0347\7f\2\2\u0347p\3\2\2\2\u0348\u0349\7r\2\2\u0349\u034a"+
		"\7n\2\2\u034a\u034b\7c\2\2\u034b\u034c\7p\2\2\u034c\u034d\7v\2\2\u034d"+
		"r\3\2\2\2\u034e\u034f\7w\2\2\u034f\u0350\7p\2\2\u0350\u0351\7f\2\2\u0351"+
		"\u0352\7g\2\2\u0352\u0353\7c\2\2\u0353\u0354\7f\2\2\u0354t\3\2\2\2\u0355"+
		"\u0356\7e\2\2\u0356\u0357\7q\2\2\u0357\u0358\7p\2\2\u0358\u0359\7u\2\2"+
		"\u0359\u035a\7v\2\2\u035a\u035b\7t\2\2\u035b\u035c\7w\2\2\u035c\u035d"+
		"\7e\2\2\u035d\u035e\7v\2\2\u035ev\3\2\2\2\u035f\u0360\7d\2\2\u0360\u0361"+
		"\7g\2\2\u0361\u0362\7c\2\2\u0362\u0363\7u\2\2\u0363\u0364\7v\2\2\u0364"+
		"x\3\2\2\2\u0365\u0366\7d\2\2\u0366\u0367\7w\2\2\u0367\u0368\7i\2\2\u0368"+
		"z\3\2\2\2\u0369\u036a\7g\2\2\u036a\u036b\7n\2\2\u036b\u036c\7g\2\2\u036c"+
		"\u036d\7o\2\2\u036d\u036e\7g\2\2\u036e\u036f\7p\2\2\u036f\u0370\7v\2\2"+
		"\u0370\u0371\7c\2\2\u0371\u0372\7n\2\2\u0372|\3\2\2\2\u0373\u0374\7f\2"+
		"\2\u0374\u0375\7g\2\2\u0375\u0376\7o\2\2\u0376\u0377\7q\2\2\u0377\u0378"+
		"\7p\2\2\u0378\u0379\7k\2\2\u0379\u037a\7e\2\2\u037a~\3\2\2\2\u037b\u037c"+
		"\7i\2\2\u037c\u037d\7k\2\2\u037d\u037e\7c\2\2\u037e\u037f\7p\2\2\u037f"+
		"\u0380\7v\2\2\u0380\u0080\3\2\2\2\u0381\u0382\7f\2\2\u0382\u0383\7t\2"+
		"\2\u0383\u0384\7c\2\2\u0384\u0385\7i\2\2\u0385\u0386\7q\2\2\u0386\u0387"+
		"\7p\2\2\u0387\u0082\3\2\2\2\u0388\u0389\7f\2\2\u0389\u038a\7k\2\2\u038a"+
		"\u038b\7x\2\2\u038b\u038c\7k\2\2\u038c\u038d\7p\2\2\u038d\u038e\7g\2\2"+
		"\u038e\u0084\3\2\2\2\u038f\u0390\7u\2\2\u0390\u0391\7w\2\2\u0391\u0392"+
		"\7o\2\2\u0392\u0393\7o\2\2\u0393\u0394\7q\2\2\u0394\u0395\7p\2\2\u0395"+
		"\u0086\3\2\2\2\u0396\u0397\7r\2\2\u0397\u0398\7g\2\2\u0398\u0399\7v\2"+
		"\2\u0399\u0088\3\2\2\2\u039a\u039b\7j\2\2\u039b\u039c\7q\2\2\u039c\u039d"+
		"\7n\2\2\u039d\u039e\7{\2\2\u039e\u039f\7v\2\2\u039f\u03a0\7j\2\2\u03a0"+
		"\u03a1\7k\2\2\u03a1\u03a2\7p\2\2\u03a2\u03a3\7i\2\2\u03a3\u008a\3\2\2"+
		"\2\u03a4\u03a5\7f\2\2\u03a5\u03a6\7y\2\2\u03a6\u03a7\7c\2\2\u03a7\u03a8"+
		"\7t\2\2\u03a8\u03a9\7h\2\2\u03a9\u008c\3\2\2\2\u03aa\u03ab\7o\2\2\u03ab"+
		"\u03ac\7g\2\2\u03ac\u03ad\7t\2\2\u03ad\u03ae\7e\2\2\u03ae\u03af\7j\2\2"+
		"\u03af\u03b0\7c\2\2\u03b0\u03b1\7p\2\2\u03b1\u03b2\7v\2\2\u03b2\u008e"+
		"\3\2\2\2\u03b3\u03b4\7g\2\2\u03b4\u03b5\7n\2\2\u03b5\u03b6\7h\2\2\u03b6"+
		"\u0090\3\2\2\2\u03b7\u03b8\7m\2\2\u03b8\u03b9\7c\2\2\u03b9\u03ba\7o\2"+
		"\2\u03ba\u03bb\7c\2\2\u03bb\u03bc\7g\2\2\u03bc\u03bd\7n\2\2\u03bd\u0092"+
		"\3\2\2\2\u03be\u03bf\7q\2\2\u03bf\u03c0\7t\2\2\u03c0\u03c1\7e\2\2\u03c1"+
		"\u0094\3\2\2\2\u03c2\u03c3\7o\2\2\u03c3\u03c4\7g\2\2\u03c4\u03c5\7t\2"+
		"\2\u03c5\u03c6\7e\2\2\u03c6\u03c7\7g\2\2\u03c7\u03c8\7p\2\2\u03c8\u03c9"+
		"\7c\2\2\u03c9\u03ca\7t\2\2\u03ca\u03cb\7{\2\2\u03cb\u0096\3\2\2\2\u03cc"+
		"\u03cd\7e\2\2\u03cd\u03ce\7c\2\2\u03ce\u03cf\7u\2\2\u03cf\u03d0\7v\2\2"+
		"\u03d0\u03d1\7n\2\2\u03d1\u03d2\7g\2\2\u03d2\u03d3\7a\2\2\u03d3\u03d4"+
		"\7i\2\2\u03d4\u03d5\7w\2\2\u03d5\u03d6\7c\2\2\u03d6\u03d7\7t\2\2\u03d7"+
		"\u03d8\7f\2\2\u03d8\u0098\3\2\2\2\u03d9\u03da\7j\2\2\u03da\u03db\7w\2"+
		"\2\u03db\u03dc\7o\2\2\u03dc\u03dd\7c\2\2\u03dd\u03de\7p\2\2\u03de\u009a"+
		"\3\2\2\2\u03df\u03e0\7d\2\2\u03e0\u03e1\7q\2\2\u03e1\u03e2\7u\2\2\u03e2"+
		"\u03e3\7u\2\2\u03e3\u009c\3\2\2\2\u03e4\u03e5\7|\2\2\u03e5\u03e6\7|\2"+
		"\2\u03e6\u03e7\7q\2\2\u03e7\u03e8\7n\2\2\u03e8\u03e9\7f\2\2\u03e9\u03ea"+
		"\7c\2\2\u03ea\u03eb\7i\2\2\u03eb\u03ec\7w\2\2\u03ec\u009e\3\2\2\2\u03ed"+
		"\u03ee\7y\2\2\u03ee\u03ef\7q\2\2\u03ef\u03f0\7t\2\2\u03f0\u03f1\7n\2\2"+
		"\u03f1\u03f2\7f\2\2\u03f2\u03f3\7a\2\2\u03f3\u03f4\7v\2\2\u03f4\u03f5"+
		"\7t\2\2\u03f5\u03f6\7c\2\2\u03f6\u03f7\7r\2\2\u03f7\u00a0\3\2\2\2\u03f8"+
		"\u03f9\7o\2\2\u03f9\u03fa\7q\2\2\u03fa\u03fb\7p\2\2\u03fb\u03fc\7t\2\2"+
		"\u03fc\u03fd\7c\2\2\u03fd\u03fe\7e\2\2\u03fe\u03ff\7g\2\2\u03ff\u00a2"+
		"\3\2\2\2\u0400\u0401\7f\2\2\u0401\u0402\7c\2\2\u0402\u0403\7t\2\2\u0403"+
		"\u0404\7m\2\2\u0404\u0405\7g\2\2\u0405\u0406\7n\2\2\u0406\u0407\7h\2\2"+
		"\u0407\u00a4\3\2\2\2\u0408\u0409\7i\2\2\u0409\u040a\7w\2\2\u040a\u040b"+
		"\7c\2\2\u040b\u040c\7t\2\2\u040c\u040d\7f\2\2\u040d\u00a6\3\2\2\2\u040e"+
		"\u040f\7v\2\2\u040f\u0410\7g\2\2\u0410\u0411\7n\2\2\u0411\u0412\7g\2\2"+
		"\u0412\u0413\7r\2\2\u0413\u0414\7q\2\2\u0414\u0415\7t\2\2\u0415\u0416"+
		"\7v\2\2\u0416\u0417\7g\2\2\u0417\u0418\7t\2\2\u0418\u00a8\3\2\2\2\u0419"+
		"\u041a\7y\2\2\u041a\u041b\7c\2\2\u041b\u041c\7t\2\2\u041c\u041d\7g\2\2"+
		"\u041d\u041e\7j\2\2\u041e\u041f\7q\2\2\u041f\u0420\7w\2\2\u0420\u0421"+
		"\7u\2\2\u0421\u0422\7g\2\2\u0422\u0423\7a\2\2\u0423\u0424\7m\2\2\u0424"+
		"\u0425\7g\2\2\u0425\u0426\7g\2\2\u0426\u0427\7r\2\2\u0427\u0428\7g\2\2"+
		"\u0428\u0429\7t\2\2\u0429\u00aa\3\2\2\2\u042a\u042b\7y\2\2\u042b\u042c"+
		"\7c\2\2\u042c\u042d\7t\2\2\u042d\u042e\7t\2\2\u042e\u042f\7k\2\2\u042f"+
		"\u0430\7q\2\2\u0430\u0431\7t\2\2\u0431\u00ac\3\2\2\2\u0432\u0433\7e\2"+
		"\2\u0433\u0434\7k\2\2\u0434\u0435\7v\2\2\u0435\u0436\7k\2\2\u0436\u0437"+
		"\7|\2\2\u0437\u0438\7g\2\2\u0438\u0439\7p\2\2\u0439\u00ae\3\2\2\2\u043a"+
		"\u043b\7v\2\2\u043b\u043c\7t\2\2\u043c\u043d\7g\2\2\u043d\u043e\7c\2\2"+
		"\u043e\u043f\7u\2\2\u043f\u0440\7w\2\2\u0440\u0441\7t\2\2\u0441\u0442"+
		"\7g\2\2\u0442\u00b0\3\2\2\2\u0443\u0444\7h\2\2\u0444\u0445\7k\2\2\u0445"+
		"\u0446\7g\2\2\u0446\u0447\7n\2\2\u0447\u0448\7f\2\2\u0448\u0449\7d\2\2"+
		"\u0449\u044a\7q\2\2\u044a\u044b\7u\2\2\u044b\u044c\7u\2\2\u044c\u00b2"+
		"\3\2\2\2\u044d\u044e\7d\2\2\u044e\u044f\7n\2\2\u044f\u0450\7c\2\2\u0450"+
		"\u0451\7e\2\2\u0451\u0452\7m\2\2\u0452\u0453\7u\2\2\u0453\u0454\7o\2\2"+
		"\u0454\u0455\7k\2\2\u0455\u0456\7v\2\2\u0456\u0457\7j\2\2\u0457\u00b4"+
		"\3\2\2\2\u0458\u0459\7i\2\2\u0459\u045a\7w\2\2\u045a\u045b\7k\2\2\u045b"+
		"\u045c\7n\2\2\u045c\u045d\7f\2\2\u045d\u045e\7a\2\2\u045e\u045f\7o\2\2"+
		"\u045f\u0460\7c\2\2\u0460\u0461\7u\2\2\u0461\u0462\7v\2\2\u0462\u0463"+
		"\7g\2\2\u0463\u0464\7t\2\2\u0464\u00b6\3\2\2\2\u0465\u0466\7i\2\2\u0466"+
		"\u0467\7w\2\2\u0467\u0468\7k\2\2\u0468\u0469\7n\2\2\u0469\u046a\7f\2\2"+
		"\u046a\u046b\7a\2\2\u046b\u046c\7e\2\2\u046c\u046d\7q\2\2\u046d\u046e"+
		"\7c\2\2\u046e\u046f\7e\2\2\u046f\u0470\7j\2\2\u0470\u00b8\3\2\2\2\u0471"+
		"\u0472\7u\2\2\u0472\u0473\7y\2\2\u0473\u0474\7q\2\2\u0474\u0475\7t\2\2"+
		"\u0475\u0476\7f\2\2\u0476\u00ba\3\2\2\2\u0477\u0478\7d\2\2\u0478\u0479"+
		"\7n\2\2\u0479\u047a\7w\2\2\u047a\u047b\7p\2\2\u047b\u047c\7v\2\2\u047c"+
		"\u00bc\3\2\2\2\u047d\u047e\7d\2\2\u047e\u047f\7q\2\2\u047f\u0480\7y\2"+
		"\2\u0480\u00be\3\2\2\2\u0481\u0482\7r\2\2\u0482\u0483\7q\2\2\u0483\u0484"+
		"\7n\2\2\u0484\u0485\7g\2\2\u0485\u00c0\3\2\2\2\u0486\u0487\7f\2\2\u0487"+
		"\u0488\7c\2\2\u0488\u0489\7i\2\2\u0489\u048a\7i\2\2\u048a\u048b\7g\2\2"+
		"\u048b\u048c\7t\2\2\u048c\u00c2\3\2\2\2\u048d\u048e\7f\2\2\u048e\u048f"+
		"\7w\2\2\u048f\u0490\7c\2\2\u0490\u0491\7n\2\2\u0491\u00c4\3\2\2\2\u0492"+
		"\u0493\7h\2\2\u0493\u0494\7k\2\2\u0494\u0495\7u\2\2\u0495\u0496\7v\2\2"+
		"\u0496\u00c6\3\2\2\2\u0497\u0498\7f\2\2\u0498\u0499\7w\2\2\u0499\u049a"+
		"\7c\2\2\u049a\u049b\7n\2\2\u049b\u049c\7h\2\2\u049c\u049d\7k\2\2\u049d"+
		"\u049e\7u\2\2\u049e\u049f\7v\2\2\u049f\u00c8\3\2\2\2\u04a0\u04a1\7h\2"+
		"\2\u04a1\u04a2\7k\2\2\u04a2\u04a3\7u\2\2\u04a3\u04a4\7j\2\2\u04a4\u04a5"+
		"\7k\2\2\u04a5\u04a6\7p\2\2\u04a6\u04a7\7i\2\2\u04a7\u04a8\7t\2\2\u04a8"+
		"\u04a9\7q\2\2\u04a9\u04aa\7f\2\2\u04aa\u00ca\3\2\2\2\u04ab\u04ac\7t\2"+
		"\2\u04ac\u04ad\7c\2\2\u04ad\u04ae\7r\2\2\u04ae\u04af\7k\2\2\u04af\u04b0"+
		"\7g\2\2\u04b0\u04b1\7t\2\2\u04b1\u00cc\3\2\2\2\u04b2\u04b3\7c\2\2\u04b3"+
		"\u04b4\7p\2\2\u04b4\u04b5\7e\2\2\u04b5\u04b6\7k\2\2\u04b6\u04b7\7g\2\2"+
		"\u04b7\u04b8\7p\2\2\u04b8\u04b9\7v\2\2\u04b9\u04ba\7u\2\2\u04ba\u04bb"+
		"\7y\2\2\u04bb\u04bc\7q\2\2\u04bc\u04bd\7t\2\2\u04bd\u04be\7f\2\2\u04be"+
		"\u00ce\3\2\2\2\u04bf\u04c0\7e\2\2\u04c0\u04c1\7t\2\2\u04c1\u04c2\7q\2"+
		"\2\u04c2\u04c3\7u\2\2\u04c3\u04c4\7u\2\2\u04c4\u04c5\7d\2\2\u04c5\u04c6"+
		"\7q\2\2\u04c6\u04c7\7y\2\2\u04c7\u00d0\3\2\2\2\u04c8\u04c9\7h\2\2\u04c9"+
		"\u04ca\7n\2\2\u04ca\u04cb\7c\2\2\u04cb\u04cc\7i\2\2\u04cc\u00d2\3\2\2"+
		"\2\u04cd\u04ce\7q\2\2\u04ce\u04cf\7y\2\2\u04cf\u04d0\7p\2\2\u04d0\u04d1"+
		"\7v\2\2\u04d1\u04d2\7j\2\2\u04d2\u04d3\7k\2\2\u04d3\u04d4\7p\2\2\u04d4"+
		"\u04d5\7i\2\2\u04d5\u00d4\3\2\2\2\u04d6\u04d7\7f\2\2\u04d7\u04d8\7w\2"+
		"\2\u04d8\u04d9\7c\2\2\u04d9\u04da\7n\2\2\u04da\u04db\7f\2\2\u04db\u04dc"+
		"\7c\2\2\u04dc\u04dd\7i\2\2\u04dd\u04de\7i\2\2\u04de\u04df\7g\2\2\u04df"+
		"\u04e0\7t\2\2\u04e0\u00d6\3\2\2\2\u04e1\u04e2\7g\2\2\u04e2\u04e3\7v\2"+
		"\2\u04e3\u04e4\7e\2\2\u04e4\u00d8\3\2\2\2\u04e5\u04e6\7n\2\2\u04e6\u04e7"+
		"\7k\2\2\u04e7\u04e8\7i\2\2\u04e8\u04e9\7j\2\2\u04e9\u04ea\7v\2\2\u04ea"+
		"\u00da\3\2\2\2\u04eb\u04ec\7j\2\2\u04ec\u04ed\7g\2\2\u04ed\u04ee\7c\2"+
		"\2\u04ee\u04ef\7x\2\2\u04ef\u04f0\7{\2\2\u04f0\u00dc\3\2\2\2\u04f1\u04f2"+
		"\7o\2\2\u04f2\u04f3\7c\2\2\u04f3\u04f4\7i\2\2\u04f4\u04f5\7k\2\2\u04f5"+
		"\u04f6\7e\2\2\u04f6\u00de\3\2\2\2\u04f7\u04f8\7u\2\2\u04f8\u04f9\7k\2"+
		"\2\u04f9\u04fa\7i\2\2\u04fa\u04fb\7k\2\2\u04fb\u04fc\7n\2\2\u04fc\u00e0"+
		"\3\2\2\2\u04fd\u04fe\7t\2\2\u04fe\u04ff\7j\2\2\u04ff\u0500\7c\2\2\u0500"+
		"\u0501\7p\2\2\u0501\u0502\7f\2\2\u0502\u00e2\3\2\2\2\u0503\u0504\7n\2"+
		"\2\u0504\u0505\7t\2\2\u0505\u0506\7j\2\2\u0506\u0507\7c\2\2\u0507\u0508"+
		"\7p\2\2\u0508\u0509\7f\2\2\u0509\u00e4\3\2\2\2\u050a\u050b\7n\2\2\u050b"+
		"\u050c\7j\2\2\u050c\u050d\7c\2\2\u050d\u050e\7p\2\2\u050e\u050f\7f\2\2"+
		"\u050f\u00e6\3\2\2\2\u0510\u0511\7e\2\2\u0511\u0512\7j\2\2\u0512\u0513"+
		"\7g\2\2\u0513\u0514\7u\2\2\u0514\u0515\7v\2\2\u0515\u00e8\3\2\2\2\u0516"+
		"\u0517\7n\2\2\u0517\u0518\7g\2\2\u0518\u0519\7i\2\2\u0519\u051a\7u\2\2"+
		"\u051a\u00ea\3\2\2\2\u051b\u051c\7h\2\2\u051c\u051d\7g\2\2\u051d\u051e"+
		"\7g\2\2\u051e\u051f\7v\2\2\u051f\u00ec\3\2\2\2\u0520\u0521\7j\2\2\u0521"+
		"\u0522\7g\2\2\u0522\u0523\7c\2\2\u0523\u0524\7f\2\2\u0524\u00ee\3\2\2"+
		"\2\u0525\u0526\7i\2\2\u0526\u0527\7n\2\2\u0527\u0528\7q\2\2\u0528\u0529"+
		"\7x\2\2\u0529\u052a\7g\2\2\u052a\u052b\7u\2\2\u052b\u00f0\3\2\2\2\u052c"+
		"\u052d\7q\2\2\u052d\u052e\7p\2\2\u052e\u052f\7g\2\2\u052f\u0530\7r\2\2"+
		"\u0530\u0531\7k\2\2\u0531\u0532\7g\2\2\u0532\u0533\7e\2\2\u0533\u0534"+
		"\7g\2\2\u0534\u00f2\3\2\2\2\u0535\u0536\7t\2\2\u0536\u0537\7g\2\2\u0537"+
		"\u0538\7c\2\2\u0538\u0539\7t\2\2\u0539\u00f4\3\2\2\2\u053a\u053b\7n\2"+
		"\2\u053b\u053c\7g\2\2\u053c\u053d\7c\2\2\u053d\u053e\7t\2\2\u053e\u00f6"+
		"\3\2\2\2\u053f\u0540\7t\2\2\u0540\u0541\7h\2\2\u0541\u0542\7k\2\2\u0542"+
		"\u0543\7p\2\2\u0543\u0544\7i\2\2\u0544\u0545\7g\2\2\u0545\u0546\7t\2\2"+
		"\u0546\u00f8\3\2\2\2\u0547\u0548\7n\2\2\u0548\u0549\7h\2\2\u0549\u054a"+
		"\7k\2\2\u054a\u054b\7p\2\2\u054b\u054c\7i\2\2\u054c\u054d\7g\2\2\u054d"+
		"\u054e\7t\2\2\u054e\u00fa\3\2\2\2\u054f\u0550\7p\2\2\u0550\u0551\7g\2"+
		"\2\u0551\u0552\7e\2\2\u0552\u0553\7m\2\2\u0553\u00fc\3\2\2\2\u0554\u0555"+
		"\7d\2\2\u0555\u0556\7c\2\2\u0556\u0557\7e\2\2\u0557\u0558\7m\2\2\u0558"+
		"\u00fe\3\2\2\2\u0559\u055a\7w\2\2\u055a\u055b\7p\2\2\u055b\u055c\7f\2"+
		"\2\u055c\u055d\7g\2\2\u055d\u055e\7t\2\2\u055e\u055f\7y\2\2\u055f\u0560"+
		"\7g\2\2\u0560\u0561\7c\2\2\u0561\u0562\7t\2\2\u0562\u0100\3\2\2\2\u0563"+
		"\u0564\7j\2\2\u0564\u0565\7c\2\2\u0565\u0566\7k\2\2\u0566\u0567\7t\2\2"+
		"\u0567\u0102\3\2\2\2\u0568\u0569\7j\2\2\u0569\u056a\7c\2\2\u056a\u056b"+
		"\7k\2\2\u056b\u056c\7t\2\2\u056c\u056d\7\64\2\2\u056d\u0104\3\2\2\2\u056e"+
		"\u056f\7j\2\2\u056f\u0570\7c\2\2\u0570\u0571\7k\2\2\u0571\u0572\7t\2\2"+
		"\u0572\u0573\7c\2\2\u0573\u0574\7n\2\2\u0574\u0575\7n\2\2\u0575\u0106"+
		"\3\2\2\2\u0576\u0577\7c\2\2\u0577\u0578\7n\2\2\u0578\u0579\7n\2\2\u0579"+
		"\u057a\7f\2\2\u057a\u057b\7t\2\2\u057b\u057c\7g\2\2\u057c\u057d\7u\2\2"+
		"\u057d\u057e\7u\2\2\u057e\u0108\3\2\2\2\u057f\u0580\7t\2\2\u0580\u0581"+
		"\7d\2\2\u0581\u0582\7t\2\2\u0582\u0583\7c\2\2\u0583\u0584\7e\2\2\u0584"+
		"\u0585\7g\2\2\u0585\u0586\7n\2\2\u0586\u0587\7g\2\2\u0587\u0588\7v\2\2"+
		"\u0588\u010a\3\2\2\2\u0589\u058a\7n\2\2\u058a\u058b\7d\2\2\u058b\u058c"+
		"\7t\2\2\u058c\u058d\7c\2\2\u058d\u058e\7e\2\2\u058e\u058f\7g\2\2\u058f"+
		"\u0590\7n\2\2\u0590\u0591\7g\2\2\u0591\u0592\7v\2\2\u0592\u010c\3\2\2"+
		"\2\u0593\u0594\7y\2\2\u0594\u0595\7c\2\2\u0595\u0596\7k\2\2\u0596\u0597"+
		"\7u\2\2\u0597\u0598\7v\2\2\u0598\u010e\3\2\2\2\u0599\u059a\7f\2\2\u059a"+
		"\u059b\7g\2\2\u059b\u059c\7e\2\2\u059c\u059d\7q\2\2\u059d\u059e\7\63\2"+
		"\2\u059e\u0110\3\2\2\2\u059f\u05a0\7u\2\2\u05a0\u05a1\7v\2\2\u05a1\u05a2"+
		"\7g\2\2\u05a2\u05a3\7g\2\2\u05a3\u05a4\7n\2\2\u05a4\u0112\3\2\2\2\u05a5"+
		"\u05a6\7h\2\2\u05a6\u05a7\7k\2\2\u05a7\u05a8\7p\2\2\u05a8\u05a9\7g\2\2"+
		"\u05a9\u05aa\7a\2\2\u05aa\u05ab\7u\2\2\u05ab\u05ac\7v\2\2\u05ac\u05ad"+
		"\7g\2\2\u05ad\u05ae\7g\2\2\u05ae\u05af\7n\2\2\u05af\u0114\3\2\2\2\u05b0"+
		"\u05b1\7y\2\2\u05b1\u05b2\7q\2\2\u05b2\u05b3\7q\2\2\u05b3\u05b4\7f\2\2"+
		"\u05b4\u0116\3\2\2\2\u05b5\u05b6\7e\2\2\u05b6\u05b7\7n\2\2\u05b7\u05b8"+
		"\7q\2\2\u05b8\u05b9\7v\2\2\u05b9\u05ba\7j\2\2\u05ba\u0118\3\2\2\2\u05bb"+
		"\u05bc\7n\2\2\u05bc\u05bd\7g\2\2\u05bd\u05be\7c\2\2\u05be\u05bf\7v\2\2"+
		"\u05bf\u05c0\7j\2\2\u05c0\u05c1\7g\2\2\u05c1\u05c2\7t\2\2\u05c2\u011a"+
		"\3\2\2\2\u05c3\u05c4\7d\2\2\u05c4\u05c5\7q\2\2\u05c5\u05c6\7p\2\2\u05c6"+
		"\u05c7\7g\2\2\u05c7\u011c\3\2\2\2\u05c8\u05c9\7d\2\2\u05c9\u05ca\7t\2"+
		"\2\u05ca\u05cb\7q\2\2\u05cb\u05cc\7p\2\2\u05cc\u05cd\7|\2\2\u05cd\u05ce"+
		"\7g\2\2\u05ce\u011e\3\2\2\2\u05cf\u05d0\7q\2\2\u05d0\u05d1\7t\2\2\u05d1"+
		"\u05d2\7k\2\2\u05d2\u05d3\7j\2\2\u05d3\u05d4\7c\2\2\u05d4\u05d5\7t\2\2"+
		"\u05d5\u05d6\7w\2\2\u05d6\u05d7\7m\2\2\u05d7\u05d8\7q\2\2\u05d8\u05d9"+
		"\7p\2\2\u05d9\u0120\3\2\2\2\u05da\u05db\7o\2\2\u05db\u05dc\7k\2\2\u05dc"+
		"\u05dd\7v\2\2\u05dd\u05de\7j\2\2\u05de\u05df\7t\2\2\u05df\u05e0\7k\2\2"+
		"\u05e0\u05e1\7n\2\2\u05e1\u0122\3\2\2\2\u05e2\u05e3\7f\2\2\u05e3\u05e4"+
		"\7c\2\2\u05e4\u05e5\7o\2\2\u05e5\u05e6\7c\2\2\u05e6\u05e7\7u\2\2\u05e7"+
		"\u05e8\7e\2\2\u05e8\u05e9\7w\2\2\u05e9\u05ea\7u\2\2\u05ea\u0124\3\2\2"+
		"\2\u05eb\u05ec\7c\2\2\u05ec\u05ed\7f\2\2\u05ed\u05ee\7c\2\2\u05ee\u05ef"+
		"\7o\2\2\u05ef\u05f0\7c\2\2\u05f0\u05f1\7p\2\2\u05f1\u05f2\7v\2\2\u05f2"+
		"\u05f3\7c\2\2\u05f3\u05f4\7k\2\2\u05f4\u05f5\7v\2\2\u05f5\u05f6\7g\2\2"+
		"\u05f6\u0126\3\2\2\2\u05f7\u05f8\7d\2\2\u05f8\u05f9\7n\2\2\u05f9\u05fa"+
		"\7q\2\2\u05fa\u05fb\7q\2\2\u05fb\u05fc\7f\2\2\u05fc\u05fd\7a\2\2\u05fd"+
		"\u05fe\7u\2\2\u05fe\u05ff\7v\2\2\u05ff\u0600\7g\2\2\u0600\u0601\7g\2\2"+
		"\u0601\u0602\7n\2\2\u0602\u0128\3\2\2\2\u0603\u0604\7r\2\2\u0604\u0605"+
		"\7c\2\2\u0605\u0606\7r\2\2\u0606\u0607\7g\2\2\u0607\u0608\7t\2\2\u0608"+
		"\u012a\3\2\2\2\u0609\u060a\7i\2\2\u060a\u060b\7q\2\2\u060b\u060c\7n\2"+
		"\2\u060c\u060d\7f\2\2\u060d\u012c\3\2\2\2\u060e\u060f\7n\2\2\u060f\u0610"+
		"\7k\2\2\u0610\u0611\7s\2\2\u0611\u0612\7w\2\2\u0612\u0613\7k\2\2\u0613"+
		"\u0614\7f\2\2\u0614\u012e\3\2\2\2\u0615\u0616\7h\2\2\u0616\u0617\7k\2"+
		"\2\u0617\u0618\7u\2\2\u0618\u0619\7j\2\2\u0619\u0130\3\2\2\2\u061a\u061b"+
		"\7u\2\2\u061b\u061c\7k\2\2\u061c\u061d\7n\2\2\u061d\u061e\7x\2\2\u061e"+
		"\u061f\7g\2\2\u061f\u0620\7t\2\2\u0620\u0132\3\2\2\2\u0621\u0622\7e\2"+
		"\2\u0622\u0623\7j\2\2\u0623\u0624\7t\2\2\u0624\u0625\7{\2\2\u0625\u0626"+
		"\7u\2\2\u0626\u0627\7q\2\2\u0627\u0628\7n\2\2\u0628\u0629\7k\2\2\u0629"+
		"\u062a\7v\2\2\u062a\u062b\7g\2\2\u062b\u0134\3\2\2\2\u062c\u062d\7e\2"+
		"\2\u062d\u062e\7t\2\2\u062e\u062f\7{\2\2\u062f\u0630\7u\2\2\u0630\u0631"+
		"\7v\2\2\u0631\u0632\7c\2\2\u0632\u0633\7n\2\2\u0633\u0136\3\2\2\2\u0634"+
		"\u0635\7j\2\2\u0635\u0636\7q\2\2\u0636\u0637\7t\2\2\u0637\u0638\7p\2\2"+
		"\u0638\u0138\3\2\2\2\u0639\u063a\7u\2\2\u063a\u063b\7e\2\2\u063b\u063c"+
		"\7c\2\2\u063c\u063d\7n\2\2\u063d\u063e\7g\2\2\u063e\u063f\7a\2\2\u063f"+
		"\u0640\7q\2\2\u0640\u0641\7h\2\2\u0641\u0642\7a\2\2\u0642\u0643\7f\2\2"+
		"\u0643\u0644\7t\2\2\u0644\u0645\7c\2\2\u0645\u0646\7i\2\2\u0646\u0647"+
		"\7q\2\2\u0647\u0648\7p\2\2\u0648\u013a\3\2\2\2\u0649\u064a\7e\2\2\u064a"+
		"\u064b\7q\2\2\u064b\u064c\7v\2\2\u064c\u064d\7v\2\2\u064d\u064e\7q\2\2"+
		"\u064e\u064f\7p\2\2\u064f\u013c\3\2\2\2\u0650\u0651\7f\2\2\u0651\u0652"+
		"\7{\2\2\u0652\u0653\7g\2\2\u0653\u0654\7u\2\2\u0654\u0655\7v\2\2\u0655"+
		"\u0656\7w\2\2\u0656\u0657\7h\2\2\u0657\u0658\7h\2\2\u0658\u013e\3\2\2"+
		"\2\u0659\u065a\7e\2\2\u065a\u065b\7q\2\2\u065b\u065c\7d\2\2\u065c\u065d"+
		"\7y\2\2\u065d\u065e\7g\2\2\u065e\u065f\7d\2\2\u065f\u0140\3\2\2\2\u0660"+
		"\u0661\7t\2\2\u0661\u0662\7w\2\2\u0662\u0663\7p\2\2\u0663\u0664\7g\2\2"+
		"\u0664\u0665\7a\2\2\u0665\u0666\7z\2\2\u0666\u0667\7r\2\2\u0667\u0142"+
		"\3\2\2\2\u0668\u0669\7t\2\2\u0669\u066a\7w\2\2\u066a\u066b\7p\2\2\u066b"+
		"\u066c\7g\2\2\u066c\u066d\7a\2\2\u066d\u066e\7u\2\2\u066e\u066f\7r\2\2"+
		"\u066f\u0144\3\2\2\2\u0670\u0671\7t\2\2\u0671\u0672\7w\2\2\u0672\u0673"+
		"\7p\2\2\u0673\u0674\7g\2\2\u0674\u0675\7a\2\2\u0675\u0676\7t\2\2\u0676"+
		"\u0677\7g\2\2\u0677\u0678\7o\2\2\u0678\u0679\7q\2\2\u0679\u067a\7x\2\2"+
		"\u067a\u067b\7g\2\2\u067b\u067c\7a\2\2\u067c\u067d\7r\2\2\u067d\u067e"+
		"\7g\2\2\u067e\u067f\7p\2\2\u067f\u0680\7c\2\2\u0680\u0681\7n\2\2\u0681"+
		"\u0682\7v\2\2\u0682\u0683\7{\2\2\u0683\u0146\3\2\2\2\u0684\u0686\t\6\2"+
		"\2\u0685\u0684\3\2\2\2\u0686\u0687\3\2\2\2\u0687\u0685\3\2\2\2\u0687\u0688"+
		"\3\2\2\2\u0688\u068c\3\2\2\2\u0689\u068b\t\7\2\2\u068a\u0689\3\2\2\2\u068b"+
		"\u068e\3\2\2\2\u068c\u068a\3\2\2\2\u068c\u068d\3\2\2\2\u068d\u0148\3\2"+
		"\2\2\u068e\u068c\3\2\2\2\u068f\u0691\t\b\2\2\u0690\u068f\3\2\2\2\u0691"+
		"\u0692\3\2\2\2\u0692\u0690\3\2\2\2\u0692\u0693\3\2\2\2\u0693\u0694\3\2"+
		"\2\2\u0694\u0695\b\u00a5\2\2\u0695\u014a\3\2\2\2\u0696\u0697\7\61\2\2"+
		"\u0697\u0698\7\61\2\2\u0698\u069c\3\2\2\2\u0699\u069b\n\t\2\2\u069a\u0699"+
		"\3\2\2\2\u069b\u069e\3\2\2\2\u069c\u069a\3\2\2\2\u069c\u069d\3\2\2\2\u069d"+
		"\u06a0\3\2\2\2\u069e\u069c\3\2\2\2\u069f\u06a1\7\17\2\2\u06a0\u069f\3"+
		"\2\2\2\u06a0\u06a1\3\2\2\2\u06a1\u06a2\3\2\2\2\u06a2\u06a3\7\f\2\2\u06a3"+
		"\u06a4\3\2\2\2\u06a4\u06a5\b\u00a6\2\2\u06a5\u014c\3\2\2\2\u06a6\u06a7"+
		"\7\61\2\2\u06a7\u06a8\7,\2\2\u06a8\u06ac\3\2\2\2\u06a9\u06ab\13\2\2\2"+
		"\u06aa\u06a9\3\2\2\2\u06ab\u06ae\3\2\2\2\u06ac\u06ad\3\2\2\2\u06ac\u06aa"+
		"\3\2\2\2\u06ad\u06af\3\2\2\2\u06ae\u06ac\3\2\2\2\u06af\u06b0\7,\2\2\u06b0"+
		"\u06b1\7\61\2\2\u06b1\u06b2\3\2\2\2\u06b2\u06b3\b\u00a7\2\2\u06b3\u014e"+
		"\3\2\2\2\r\2\u02ea\u02f3\u031c\u031f\u0687\u068c\u0692\u069c\u06a0\u06ac"+
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