// Generated from org\l2junity\gameserver\data\txt\gen\Settings.g4 by ANTLR 4.7
package org.l2junity.gameserver.data.txt.gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SettingsLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	public static final int
			T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
			T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
			T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, HUMAN_FIGHTER = 24,
			HUMAN_MAGICAN = 25, ELF_FIGHTER = 26, ELF_MAGICAN = 27, DARKELF_FIGHTER = 28,
			DARKELF_MAGICAN = 29, ORC_FIGHTER = 30, ORC_SHAMAN = 31, DWARF_APPRENTICE = 32,
			DWARF_MAGE = 33, KAMAEL_M_SOLDIER = 34, KAMAEL_F_SOLDIER = 35, CATEGORY = 36,
			BOOLEAN = 37, INTEGER = 38, DOUBLE = 39, FIRE = 40, WATER = 41, EARTH = 42, WIND = 43,
			UNHOLY = 44, HOLY = 45, SEMICOLON = 46, NONE = 47, FAIRY = 48, ANIMAL = 49, HUMANOID = 50,
			PLANT = 51, UNDEAD = 52, CONSTRUCT = 53, BEAST = 54, BUG = 55, ELEMENTAL = 56, DEMONIC = 57,
			GIANT = 58, DRAGON = 59, DIVINE = 60, SUMMON = 61, PET = 62, HOLYTHING = 63, DWARF = 64,
			MERCHANT = 65, ELF = 66, KAMAEL = 67, ORC = 68, MERCENARY = 69, CASTLE_GUARD = 70,
			HUMAN = 71, BOSS = 72, ZZOLDAGU = 73, WORLD_TRAP = 74, MONRACE = 75, DARKELF = 76,
			GUARD = 77, TELEPORTER = 78, WAREHOUSE_KEEPER = 79, WARRIOR = 80, CITIZEN = 81,
			TREASURE = 82, FIELDBOSS = 83, BLACKSMITH = 84, GUILD_MASTER = 85, GUILD_COACH = 86,
			SWORD = 87, BLUNT = 88, BOW = 89, POLE = 90, DAGGER = 91, DUAL = 92, FIST = 93, DUALFIST = 94,
			FISHINGROD = 95, RAPIER = 96, ANCIENTSWORD = 97, CROSSBOW = 98, FLAG = 99, OWNTHING = 100,
			DUALDAGGER = 101, ETC = 102, LIGHT = 103, HEAVY = 104, MAGIC = 105, SIGIL = 106, RHAND = 107,
			LRHAND = 108, LHAND = 109, CHEST = 110, LEGS = 111, FEET = 112, HEAD = 113, GLOVES = 114,
			ONEPIECE = 115, REAR = 116, LEAR = 117, RFINGER = 118, LFINGER = 119, NECK = 120,
			BACK = 121, UNDERWEAR = 122, HAIR = 123, HAIR2 = 124, HAIRALL = 125, ALLDRESS = 126,
			RBRACELET = 127, LBRACELET = 128, WAIST = 129, DECO1 = 130, STEEL = 131, FINE_STEEL = 132,
			WOOD = 133, CLOTH = 134, LEATHER = 135, BONE = 136, BRONZE = 137, ORIHARUKON = 138,
			MITHRIL = 139, DAMASCUS = 140, ADAMANTAITE = 141, BLOOD_STEEL = 142, PAPER = 143,
			GOLD = 144, LIQUID = 145, FISH = 146, SILVER = 147, CHRYSOLITE = 148, CRYSTAL = 149,
			HORN = 150, SCALE_OF_DRAGON = 151, COTTON = 152, DYESTUFF = 153, COBWEB = 154, RUNE_XP = 155,
			RUNE_SP = 156, RUNE_REMOVE_PENALTY = 157, NAME = 158, WS = 159, LINE_COMMENT = 160,
			STAR_COMMENT = 161;
	public static String[] channelNames = {
			"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
			"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16",
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "HUMAN_FIGHTER",
			"HUMAN_MAGICAN", "ELF_FIGHTER", "ELF_MAGICAN", "DARKELF_FIGHTER", "DARKELF_MAGICAN",
			"ORC_FIGHTER", "ORC_SHAMAN", "DWARF_APPRENTICE", "DWARF_MAGE", "KAMAEL_M_SOLDIER",
			"KAMAEL_F_SOLDIER", "CATEGORY", "BOOLEAN", "INTEGER", "DOUBLE", "FIRE",
			"WATER", "EARTH", "WIND", "UNHOLY", "HOLY", "DECIMAL", "MINUS", "BINARY_DIGIT",
			"DIGIT", "NON_ZERO_DIGIT", "ZERO_DIGIT", "SEMICOLON", "NONE", "FAIRY",
			"ANIMAL", "HUMANOID", "PLANT", "UNDEAD", "CONSTRUCT", "BEAST", "BUG",
			"ELEMENTAL", "DEMONIC", "GIANT", "DRAGON", "DIVINE", "SUMMON", "PET",
			"HOLYTHING", "DWARF", "MERCHANT", "ELF", "KAMAEL", "ORC", "MERCENARY",
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


	public SettingsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public String getGrammarFileName() {
		return "Settings.g4";
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
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public static final String _serializedATN =
			"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\u00a3\u072a\b\1\4" +
					"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n" +
					"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22" +
					"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31" +
					"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t" +
					" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t" +
					"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64" +
					"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t" +
					"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4" +
					"I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\t" +
					"T\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_" +
					"\4`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k" +
					"\tk\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv" +
					"\4w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t" +
					"\u0080\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084" +
					"\4\u0085\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089" +
					"\t\u0089\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d" +
					"\4\u008e\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092" +
					"\t\u0092\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096" +
					"\4\u0097\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b" +
					"\t\u009b\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f" +
					"\4\u00a0\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4" +
					"\t\u00a4\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8" +
					"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3" +
					"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3" +
					"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3" +
					"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4" +
					"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3" +
					"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5" +
					"\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3" +
					"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n" +
					"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3" +
					"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3" +
					"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3" +
					"\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3" +
					"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3" +
					"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3" +
					"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3" +
					"\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3" +
					"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3" +
					"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3" +
					"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3" +
					"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3" +
					"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3" +
					"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3" +
					"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3" +
					"\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3" +
					"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3" +
					"\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3" +
					"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3" +
					"\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3" +
					"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3" +
					"\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!" +
					"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3" +
					"\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3" +
					"#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3&\3" +
					"&\3\'\5\'\u0361\n\'\3\'\3\'\3(\3(\3(\6(\u0368\n(\r(\16(\u0369\3)\3)\3" +
					")\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3-\3-\3-\3" +
					"-\3-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\7/\u0391\n/\f/\16/\u0394\13/\5/\u0396" +
					"\n/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3" +
					"\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\38" +
					"\39\39\39\39\39\39\39\39\39\3:\3:\3:\3:\3:\3:\3;\3;\3;\3;\3;\3;\3;\3<" +
					"\3<\3<\3<\3<\3<\3<\3<\3<\3<\3=\3=\3=\3=\3=\3=\3>\3>\3>\3>\3?\3?\3?\3?" +
					"\3?\3?\3?\3?\3?\3?\3@\3@\3@\3@\3@\3@\3@\3@\3A\3A\3A\3A\3A\3A\3B\3B\3B" +
					"\3B\3B\3B\3B\3C\3C\3C\3C\3C\3C\3C\3D\3D\3D\3D\3D\3D\3D\3E\3E\3E\3E\3F" +
					"\3F\3F\3F\3F\3F\3F\3F\3F\3F\3G\3G\3G\3G\3G\3G\3H\3H\3H\3H\3H\3H\3H\3H" +
					"\3H\3I\3I\3I\3I\3J\3J\3J\3J\3J\3J\3J\3K\3K\3K\3K\3L\3L\3L\3L\3L\3L\3L" +
					"\3L\3L\3L\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3M\3N\3N\3N\3N\3N\3N\3O" +
					"\3O\3O\3O\3O\3P\3P\3P\3P\3P\3P\3P\3P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q\3Q" +
					"\3Q\3R\3R\3R\3R\3R\3R\3R\3R\3S\3S\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T" +
					"\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3U\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V" +
					"\3V\3V\3V\3V\3V\3W\3W\3W\3W\3W\3W\3W\3W\3X\3X\3X\3X\3X\3X\3X\3X\3Y\3Y" +
					"\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3[" +
					"\3[\3[\3[\3[\3[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3" +
					"]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3]\3^\3^\3^\3^\3^\3^\3_\3_\3_\3_\3_\3" +
					"_\3`\3`\3`\3`\3a\3a\3a\3a\3a\3b\3b\3b\3b\3b\3b\3b\3c\3c\3c\3c\3c\3d\3" +
					"d\3d\3d\3d\3e\3e\3e\3e\3e\3e\3e\3e\3e\3f\3f\3f\3f\3f\3f\3f\3f\3f\3f\3" +
					"f\3g\3g\3g\3g\3g\3g\3g\3h\3h\3h\3h\3h\3h\3h\3h\3h\3h\3h\3h\3h\3i\3i\3" +
					"i\3i\3i\3i\3i\3i\3i\3j\3j\3j\3j\3j\3k\3k\3k\3k\3k\3k\3k\3k\3k\3l\3l\3" +
					"l\3l\3l\3l\3l\3l\3l\3l\3l\3m\3m\3m\3m\3n\3n\3n\3n\3n\3n\3o\3o\3o\3o\3" +
					"o\3o\3p\3p\3p\3p\3p\3p\3q\3q\3q\3q\3q\3q\3r\3r\3r\3r\3r\3r\3s\3s\3s\3" +
					"s\3s\3s\3s\3t\3t\3t\3t\3t\3t\3u\3u\3u\3u\3u\3u\3v\3v\3v\3v\3v\3w\3w\3" +
					"w\3w\3w\3x\3x\3x\3x\3x\3y\3y\3y\3y\3y\3y\3y\3z\3z\3z\3z\3z\3z\3z\3z\3" +
					"z\3{\3{\3{\3{\3{\3|\3|\3|\3|\3|\3}\3}\3}\3}\3}\3}\3}\3}\3~\3~\3~\3~\3" +
					"~\3~\3~\3~\3\177\3\177\3\177\3\177\3\177\3\u0080\3\u0080\3\u0080\3\u0080" +
					"\3\u0080\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081\3\u0081" +
					"\3\u0081\3\u0081\3\u0082\3\u0082\3\u0082\3\u0082\3\u0082\3\u0083\3\u0083" +
					"\3\u0083\3\u0083\3\u0083\3\u0083\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084" +
					"\3\u0084\3\u0084\3\u0084\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085" +
					"\3\u0085\3\u0085\3\u0085\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086\3\u0086" +
					"\3\u0086\3\u0086\3\u0086\3\u0086\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087" +
					"\3\u0087\3\u0087\3\u0087\3\u0087\3\u0087\3\u0088\3\u0088\3\u0088\3\u0088" +
					"\3\u0088\3\u0088\3\u0089\3\u0089\3\u0089\3\u0089\3\u0089\3\u0089\3\u008a" +
					"\3\u008a\3\u008a\3\u008a\3\u008a\3\u008a\3\u008b\3\u008b\3\u008b\3\u008b" +
					"\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008b\3\u008c\3\u008c" +
					"\3\u008c\3\u008c\3\u008c\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d\3\u008d" +
					"\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008e\3\u008f" +
					"\3\u008f\3\u008f\3\u008f\3\u008f\3\u0090\3\u0090\3\u0090\3\u0090\3\u0090" +
					"\3\u0090\3\u0090\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091" +
					"\3\u0091\3\u0091\3\u0091\3\u0091\3\u0092\3\u0092\3\u0092\3\u0092\3\u0092" +
					"\3\u0092\3\u0092\3\u0092\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093\3\u0093" +
					"\3\u0093\3\u0093\3\u0093\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094" +
					"\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0094\3\u0095\3\u0095\3\u0095" +
					"\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095\3\u0095" +
					"\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0097\3\u0097\3\u0097" +
					"\3\u0097\3\u0097\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098" +
					"\3\u0099\3\u0099\3\u0099\3\u0099\3\u0099\3\u009a\3\u009a\3\u009a\3\u009a" +
					"\3\u009a\3\u009a\3\u009a\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b" +
					"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009c\3\u009c\3\u009c\3\u009c" +
					"\3\u009c\3\u009c\3\u009c\3\u009c\3\u009d\3\u009d\3\u009d\3\u009d\3\u009d" +
					"\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e" +
					"\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009e\3\u009f\3\u009f" +
					"\3\u009f\3\u009f\3\u009f\3\u009f\3\u009f\3\u00a0\3\u00a0\3\u00a0\3\u00a0" +
					"\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a0\3\u00a1\3\u00a1\3\u00a1\3\u00a1" +
					"\3\u00a1\3\u00a1\3\u00a1\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2\3\u00a2" +
					"\3\u00a2\3\u00a2\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3\3\u00a3" +
					"\3\u00a3\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4" +
					"\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4\3\u00a4" +
					"\3\u00a4\3\u00a4\3\u00a4\3\u00a5\6\u00a5\u06fc\n\u00a5\r\u00a5\16\u00a5" +
					"\u06fd\3\u00a5\7\u00a5\u0701\n\u00a5\f\u00a5\16\u00a5\u0704\13\u00a5\3" +
					"\u00a6\6\u00a6\u0707\n\u00a6\r\u00a6\16\u00a6\u0708\3\u00a6\3\u00a6\3" +
					"\u00a7\3\u00a7\3\u00a7\3\u00a7\7\u00a7\u0711\n\u00a7\f\u00a7\16\u00a7" +
					"\u0714\13\u00a7\3\u00a7\5\u00a7\u0717\n\u00a7\3\u00a7\3\u00a7\3\u00a7" +
					"\3\u00a7\3\u00a8\3\u00a8\3\u00a8\3\u00a8\7\u00a8\u0721\n\u00a8\f\u00a8" +
					"\16\u00a8\u0724\13\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u00a8\3\u0722" +
					"\2\u00a9\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17" +
					"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35" +
					"9\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\2_\2a\2c\2e\2g\2i\60k\61m" +
					"\62o\63q\64s\65u\66w\67y8{9}:\177;\u0081<\u0083=\u0085>\u0087?\u0089@" +
					"\u008bA\u008dB\u008fC\u0091D\u0093E\u0095F\u0097G\u0099H\u009bI\u009d" +
					"J\u009fK\u00a1L\u00a3M\u00a5N\u00a7O\u00a9P\u00abQ\u00adR\u00afS\u00b1" +
					"T\u00b3U\u00b5V\u00b7W\u00b9X\u00bbY\u00bdZ\u00bf[\u00c1\\\u00c3]\u00c5" +
					"^\u00c7_\u00c9`\u00cba\u00cdb\u00cfc\u00d1d\u00d3e\u00d5f\u00d7g\u00d9" +
					"h\u00dbi\u00ddj\u00dfk\u00e1l\u00e3m\u00e5n\u00e7o\u00e9p\u00ebq\u00ed" +
					"r\u00efs\u00f1t\u00f3u\u00f5v\u00f7w\u00f9x\u00fby\u00fdz\u00ff{\u0101" +
					"|\u0103}\u0105~\u0107\177\u0109\u0080\u010b\u0081\u010d\u0082\u010f\u0083" +
					"\u0111\u0084\u0113\u0085\u0115\u0086\u0117\u0087\u0119\u0088\u011b\u0089" +
					"\u011d\u008a\u011f\u008b\u0121\u008c\u0123\u008d\u0125\u008e\u0127\u008f" +
					"\u0129\u0090\u012b\u0091\u012d\u0092\u012f\u0093\u0131\u0094\u0133\u0095" +
					"\u0135\u0096\u0137\u0097\u0139\u0098\u013b\u0099\u013d\u009a\u013f\u009b" +
					"\u0141\u009c\u0143\u009d\u0145\u009e\u0147\u009f\u0149\u00a0\u014b\u00a1" +
					"\u014d\u00a2\u014f\u00a3\3\2\n\3\2\62\63\3\2\62;\3\2\63;\3\2\62\62\6\2" +
					"\62;C\\aac|\n\2)),,/\60\62<C\\aac|\u0080\u0080\5\2\13\f\16\17\"\"\4\2" +
					"\f\f\17\17\2\u072d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13" +
					"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2" +
					"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2" +
					"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3" +
					"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2" +
					"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E" +
					"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2" +
					"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2i\3\2\2\2" +
					"\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w" +
					"\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2" +
					"\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b" +
					"\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2" +
					"\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d" +
					"\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2" +
					"\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af" +
					"\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2" +
					"\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1" +
					"\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2" +
					"\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3" +
					"\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2" +
					"\2\2\u00dd\3\2\2\2\2\u00df\3\2\2\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5" +
					"\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9\3\2\2\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2" +
					"\2\2\u00ef\3\2\2\2\2\u00f1\3\2\2\2\2\u00f3\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7" +
					"\3\2\2\2\2\u00f9\3\2\2\2\2\u00fb\3\2\2\2\2\u00fd\3\2\2\2\2\u00ff\3\2\2" +
					"\2\2\u0101\3\2\2\2\2\u0103\3\2\2\2\2\u0105\3\2\2\2\2\u0107\3\2\2\2\2\u0109" +
					"\3\2\2\2\2\u010b\3\2\2\2\2\u010d\3\2\2\2\2\u010f\3\2\2\2\2\u0111\3\2\2" +
					"\2\2\u0113\3\2\2\2\2\u0115\3\2\2\2\2\u0117\3\2\2\2\2\u0119\3\2\2\2\2\u011b" +
					"\3\2\2\2\2\u011d\3\2\2\2\2\u011f\3\2\2\2\2\u0121\3\2\2\2\2\u0123\3\2\2" +
					"\2\2\u0125\3\2\2\2\2\u0127\3\2\2\2\2\u0129\3\2\2\2\2\u012b\3\2\2\2\2\u012d" +
					"\3\2\2\2\2\u012f\3\2\2\2\2\u0131\3\2\2\2\2\u0133\3\2\2\2\2\u0135\3\2\2" +
					"\2\2\u0137\3\2\2\2\2\u0139\3\2\2\2\2\u013b\3\2\2\2\2\u013d\3\2\2\2\2\u013f" +
					"\3\2\2\2\2\u0141\3\2\2\2\2\u0143\3\2\2\2\2\u0145\3\2\2\2\2\u0147\3\2\2" +
					"\2\2\u0149\3\2\2\2\2\u014b\3\2\2\2\2\u014d\3\2\2\2\2\u014f\3\2\2\2\3\u0151" +
					"\3\2\2\2\5\u0169\3\2\2\2\7\u017f\3\2\2\2\t\u019e\3\2\2\2\13\u01bb\3\2" +
					"\2\2\r\u01bd\3\2\2\2\17\u01bf\3\2\2\2\21\u01c1\3\2\2\2\23\u01db\3\2\2" +
					"\2\25\u01f3\3\2\2\2\27\u01ff\3\2\2\2\31\u0209\3\2\2\2\33\u020f\3\2\2\2" +
					"\35\u0215\3\2\2\2\37\u0228\3\2\2\2!\u0239\3\2\2\2#\u024c\3\2\2\2%\u025d" +
					"\3\2\2\2\'\u0274\3\2\2\2)\u0289\3\2\2\2+\u0294\3\2\2\2-\u0296\3\2\2\2" +
					"/\u0298\3\2\2\2\61\u02ae\3\2\2\2\63\u02bc\3\2\2\2\65\u02cb\3\2\2\2\67" +
					"\u02d7\3\2\2\29\u02e4\3\2\2\2;\u02f4\3\2\2\2=\u0305\3\2\2\2?\u0311\3\2" +
					"\2\2A\u031c\3\2\2\2C\u032d\3\2\2\2E\u0338\3\2\2\2G\u0349\3\2\2\2I\u035a" +
					"\3\2\2\2K\u035d\3\2\2\2M\u0360\3\2\2\2O\u0364\3\2\2\2Q\u036b\3\2\2\2S" +
					"\u0370\3\2\2\2U\u0376\3\2\2\2W\u037c\3\2\2\2Y\u0381\3\2\2\2[\u0388\3\2" +
					"\2\2]\u0395\3\2\2\2_\u0397\3\2\2\2a\u0399\3\2\2\2c\u039b\3\2\2\2e\u039d" +
					"\3\2\2\2g\u039f\3\2\2\2i\u03a1\3\2\2\2k\u03a3\3\2\2\2m\u03a8\3\2\2\2o" +
					"\u03ae\3\2\2\2q\u03b5\3\2\2\2s\u03be\3\2\2\2u\u03c4\3\2\2\2w\u03cb\3\2" +
					"\2\2y\u03d5\3\2\2\2{\u03db\3\2\2\2}\u03df\3\2\2\2\177\u03e9\3\2\2\2\u0081" +
					"\u03f1\3\2\2\2\u0083\u03f7\3\2\2\2\u0085\u03fe\3\2\2\2\u0087\u0405\3\2" +
					"\2\2\u0089\u040c\3\2\2\2\u008b\u0410\3\2\2\2\u008d\u041a\3\2\2\2\u008f" +
					"\u0420\3\2\2\2\u0091\u0429\3\2\2\2\u0093\u042d\3\2\2\2\u0095\u0434\3\2" +
					"\2\2\u0097\u0438\3\2\2\2\u0099\u0442\3\2\2\2\u009b\u044f\3\2\2\2\u009d" +
					"\u0455\3\2\2\2\u009f\u045a\3\2\2\2\u00a1\u0463\3\2\2\2\u00a3\u046e\3\2" +
					"\2\2\u00a5\u0476\3\2\2\2\u00a7\u047e\3\2\2\2\u00a9\u0484\3\2\2\2\u00ab" +
					"\u048f\3\2\2\2\u00ad\u04a0\3\2\2\2\u00af\u04a8\3\2\2\2\u00b1\u04b0\3\2" +
					"\2\2\u00b3\u04b9\3\2\2\2\u00b5\u04c3\3\2\2\2\u00b7\u04ce\3\2\2\2\u00b9" +
					"\u04db\3\2\2\2\u00bb\u04e7\3\2\2\2\u00bd\u04ed\3\2\2\2\u00bf\u04f3\3\2" +
					"\2\2\u00c1\u04f7\3\2\2\2\u00c3\u04fc\3\2\2\2\u00c5\u0503\3\2\2\2\u00c7" +
					"\u0508\3\2\2\2\u00c9\u050d\3\2\2\2\u00cb\u0516\3\2\2\2\u00cd\u0521\3\2" +
					"\2\2\u00cf\u0528\3\2\2\2\u00d1\u0535\3\2\2\2\u00d3\u053e\3\2\2\2\u00d5" +
					"\u0543\3\2\2\2\u00d7\u054c\3\2\2\2\u00d9\u0557\3\2\2\2\u00db\u055b\3\2" +
					"\2\2\u00dd\u0561\3\2\2\2\u00df\u0567\3\2\2\2\u00e1\u056d\3\2\2\2\u00e3" +
					"\u0573\3\2\2\2\u00e5\u0579\3\2\2\2\u00e7\u0580\3\2\2\2\u00e9\u0586\3\2" +
					"\2\2\u00eb\u058c\3\2\2\2\u00ed\u0591\3\2\2\2\u00ef\u0596\3\2\2\2\u00f1" +
					"\u059b\3\2\2\2\u00f3\u05a2\3\2\2\2\u00f5\u05ab\3\2\2\2\u00f7\u05b0\3\2" +
					"\2\2\u00f9\u05b5\3\2\2\2\u00fb\u05bd\3\2\2\2\u00fd\u05c5\3\2\2\2\u00ff" +
					"\u05ca\3\2\2\2\u0101\u05cf\3\2\2\2\u0103\u05d9\3\2\2\2\u0105\u05de\3\2" +
					"\2\2\u0107\u05e4\3\2\2\2\u0109\u05ec\3\2\2\2\u010b\u05f5\3\2\2\2\u010d" +
					"\u05ff\3\2\2\2\u010f\u0609\3\2\2\2\u0111\u060f\3\2\2\2\u0113\u0615\3\2" +
					"\2\2\u0115\u061b\3\2\2\2\u0117\u0626\3\2\2\2\u0119\u062b\3\2\2\2\u011b" +
					"\u0631\3\2\2\2\u011d\u0639\3\2\2\2\u011f\u063e\3\2\2\2\u0121\u0645\3\2" +
					"\2\2\u0123\u0650\3\2\2\2\u0125\u0658\3\2\2\2\u0127\u0661\3\2\2\2\u0129" +
					"\u066d\3\2\2\2\u012b\u0679\3\2\2\2\u012d\u067f\3\2\2\2\u012f\u0684\3\2" +
					"\2\2\u0131\u068b\3\2\2\2\u0133\u0690\3\2\2\2\u0135\u0697\3\2\2\2\u0137" +
					"\u06a2\3\2\2\2\u0139\u06aa\3\2\2\2\u013b\u06af\3\2\2\2\u013d\u06bf\3\2" +
					"\2\2\u013f\u06c6\3\2\2\2\u0141\u06cf\3\2\2\2\u0143\u06d6\3\2\2\2\u0145" +
					"\u06de\3\2\2\2\u0147\u06e6\3\2\2\2\u0149\u06fb\3\2\2\2\u014b\u0706\3\2" +
					"\2\2\u014d\u070c\3\2\2\2\u014f\u071c\3\2\2\2\u0151\u0152\7k\2\2\u0152" +
					"\u0153\7p\2\2\u0153\u0154\7k\2\2\u0154\u0155\7v\2\2\u0155\u0156\7k\2\2" +
					"\u0156\u0157\7c\2\2\u0157\u0158\7n\2\2\u0158\u0159\7a\2\2\u0159\u015a" +
					"\7g\2\2\u015a\u015b\7s\2\2\u015b\u015c\7w\2\2\u015c\u015d\7k\2\2\u015d" +
					"\u015e\7r\2\2\u015e\u015f\7o\2\2\u015f\u0160\7g\2\2\u0160\u0161\7p\2\2" +
					"\u0161\u0162\7v\2\2\u0162\u0163\7a\2\2\u0163\u0164\7d\2\2\u0164\u0165" +
					"\7g\2\2\u0165\u0166\7i\2\2\u0166\u0167\7k\2\2\u0167\u0168\7p\2\2\u0168" +
					"\4\3\2\2\2\u0169\u016a\7k\2\2\u016a\u016b\7p\2\2\u016b\u016c\7k\2\2\u016c" +
					"\u016d\7v\2\2\u016d\u016e\7k\2\2\u016e\u016f\7c\2\2\u016f\u0170\7n\2\2" +
					"\u0170\u0171\7a\2\2\u0171\u0172\7g\2\2\u0172\u0173\7s\2\2\u0173\u0174" +
					"\7w\2\2\u0174\u0175\7k\2\2\u0175\u0176\7r\2\2\u0176\u0177\7o\2\2\u0177" +
					"\u0178\7g\2\2\u0178\u0179\7p\2\2\u0179\u017a\7v\2\2\u017a\u017b\7a\2\2" +
					"\u017b\u017c\7g\2\2\u017c\u017d\7p\2\2\u017d\u017e\7f\2\2\u017e\6\3\2" +
					"\2\2\u017f\u0180\7k\2\2\u0180\u0181\7p\2\2\u0181\u0182\7k\2\2\u0182\u0183" +
					"\7v\2\2\u0183\u0184\7k\2\2\u0184\u0185\7c\2\2\u0185\u0186\7n\2\2\u0186" +
					"\u0187\7a\2\2\u0187\u0188\7e\2\2\u0188\u0189\7w\2\2\u0189\u018a\7u\2\2" +
					"\u018a\u018b\7v\2\2\u018b\u018c\7q\2\2\u018c\u018d\7o\2\2\u018d\u018e" +
					"\7a\2\2\u018e\u018f\7g\2\2\u018f\u0190\7s\2\2\u0190\u0191\7w\2\2\u0191" +
					"\u0192\7k\2\2\u0192\u0193\7r\2\2\u0193\u0194\7o\2\2\u0194\u0195\7g\2\2" +
					"\u0195\u0196\7p\2\2\u0196\u0197\7v\2\2\u0197\u0198\7a\2\2\u0198\u0199" +
					"\7d\2\2\u0199\u019a\7g\2\2\u019a\u019b\7i\2\2\u019b\u019c\7k\2\2\u019c" +
					"\u019d\7p\2\2\u019d\b\3\2\2\2\u019e\u019f\7k\2\2\u019f\u01a0\7p\2\2\u01a0" +
					"\u01a1\7k\2\2\u01a1\u01a2\7v\2\2\u01a2\u01a3\7k\2\2\u01a3\u01a4\7c\2\2" +
					"\u01a4\u01a5\7n\2\2\u01a5\u01a6\7a\2\2\u01a6\u01a7\7e\2\2\u01a7\u01a8" +
					"\7w\2\2\u01a8\u01a9\7u\2\2\u01a9\u01aa\7v\2\2\u01aa\u01ab\7q\2\2\u01ab" +
					"\u01ac\7o\2\2\u01ac\u01ad\7a\2\2\u01ad\u01ae\7g\2\2\u01ae\u01af\7s\2\2" +
					"\u01af\u01b0\7w\2\2\u01b0\u01b1\7k\2\2\u01b1\u01b2\7r\2\2\u01b2\u01b3" +
					"\7o\2\2\u01b3\u01b4\7g\2\2\u01b4\u01b5\7p\2\2\u01b5\u01b6\7v\2\2\u01b6" +
					"\u01b7\7a\2\2\u01b7\u01b8\7g\2\2\u01b8\u01b9\7p\2\2\u01b9\u01ba\7f\2\2" +
					"\u01ba\n\3\2\2\2\u01bb\u01bc\7?\2\2\u01bc\f\3\2\2\2\u01bd\u01be\7}\2\2" +
					"\u01be\16\3\2\2\2\u01bf\u01c0\7\177\2\2\u01c0\20\3\2\2\2\u01c1\u01c2\7" +
					"k\2\2\u01c2\u01c3\7p\2\2\u01c3\u01c4\7k\2\2\u01c4\u01c5\7v\2\2\u01c5\u01c6" +
					"\7k\2\2\u01c6\u01c7\7c\2\2\u01c7\u01c8\7n\2\2\u01c8\u01c9\7a\2\2\u01c9" +
					"\u01ca\7u\2\2\u01ca\u01cb\7v\2\2\u01cb\u01cc\7c\2\2\u01cc\u01cd\7t\2\2" +
					"\u01cd\u01ce\7v\2\2\u01ce\u01cf\7a\2\2\u01cf\u01d0\7r\2\2\u01d0\u01d1" +
					"\7q\2\2\u01d1\u01d2\7k\2\2\u01d2\u01d3\7p\2\2\u01d3\u01d4\7v\2\2\u01d4" +
					"\u01d5\7a\2\2\u01d5\u01d6\7d\2\2\u01d6\u01d7\7g\2\2\u01d7\u01d8\7i\2\2" +
					"\u01d8\u01d9\7k\2\2\u01d9\u01da\7p\2\2\u01da\22\3\2\2\2\u01db\u01dc\7" +
					"k\2\2\u01dc\u01dd\7p\2\2\u01dd\u01de\7k\2\2\u01de\u01df\7v\2\2\u01df\u01e0" +
					"\7k\2\2\u01e0\u01e1\7c\2\2\u01e1\u01e2\7n\2\2\u01e2\u01e3\7a\2\2\u01e3" +
					"\u01e4\7u\2\2\u01e4\u01e5\7v\2\2\u01e5\u01e6\7c\2\2\u01e6\u01e7\7t\2\2" +
					"\u01e7\u01e8\7v\2\2\u01e8\u01e9\7a\2\2\u01e9\u01ea\7r\2\2\u01ea\u01eb" +
					"\7q\2\2\u01eb\u01ec\7k\2\2\u01ec\u01ed\7p\2\2\u01ed\u01ee\7v\2\2\u01ee" +
					"\u01ef\7a\2\2\u01ef\u01f0\7g\2\2\u01f0\u01f1\7p\2\2\u01f1\u01f2\7f\2\2" +
					"\u01f2\24\3\2\2\2\u01f3\u01f4\7r\2\2\u01f4\u01f5\7q\2\2\u01f5\u01f6\7" +
					"k\2\2\u01f6\u01f7\7p\2\2\u01f7\u01f8\7v\2\2\u01f8\u01f9\7a\2\2\u01f9\u01fa" +
					"\7d\2\2\u01fa\u01fb\7g\2\2\u01fb\u01fc\7i\2\2\u01fc\u01fd\7k\2\2\u01fd" +
					"\u01fe\7p\2\2\u01fe\26\3\2\2\2\u01ff\u0200\7r\2\2\u0200\u0201\7q\2\2\u0201" +
					"\u0202\7k\2\2\u0202\u0203\7p\2\2\u0203\u0204\7v\2\2\u0204\u0205\7a\2\2" +
					"\u0205\u0206\7g\2\2\u0206\u0207\7p\2\2\u0207\u0208\7f\2\2\u0208\30\3\2" +
					"\2\2\u0209\u020a\7r\2\2\u020a\u020b\7q\2\2\u020b\u020c\7k\2\2\u020c\u020d" +
					"\7p\2\2\u020d\u020e\7v\2\2\u020e\32\3\2\2\2\u020f\u0210\7e\2\2\u0210\u0211" +
					"\7n\2\2\u0211\u0212\7c\2\2\u0212\u0213\7u\2\2\u0213\u0214\7u\2\2\u0214" +
					"\34\3\2\2\2\u0215\u0216\7o\2\2\u0216\u0217\7k\2\2\u0217\u0218\7p\2\2\u0218" +
					"\u0219\7k\2\2\u0219\u021a\7o\2\2\u021a\u021b\7w\2\2\u021b\u021c\7o\2\2" +
					"\u021c\u021d\7a\2\2\u021d\u021e\7u\2\2\u021e\u021f\7v\2\2\u021f\u0220" +
					"\7c\2\2\u0220\u0221\7v\2\2\u0221\u0222\7a\2\2\u0222\u0223\7d\2\2\u0223" +
					"\u0224\7g\2\2\u0224\u0225\7i\2\2\u0225\u0226\7k\2\2\u0226\u0227\7p\2\2" +
					"\u0227\36\3\2\2\2\u0228\u0229\7o\2\2\u0229\u022a\7k\2\2\u022a\u022b\7" +
					"p\2\2\u022b\u022c\7k\2\2\u022c\u022d\7o\2\2\u022d\u022e\7w\2\2\u022e\u022f" +
					"\7o\2\2\u022f\u0230\7a\2\2\u0230\u0231\7u\2\2\u0231\u0232\7v\2\2\u0232" +
					"\u0233\7c\2\2\u0233\u0234\7v\2\2\u0234\u0235\7a\2\2\u0235\u0236\7g\2\2" +
					"\u0236\u0237\7p\2\2\u0237\u0238\7f\2\2\u0238 \3\2\2\2\u0239\u023a\7o\2" +
					"\2\u023a\u023b\7c\2\2\u023b\u023c\7z\2\2\u023c\u023d\7k\2\2\u023d\u023e" +
					"\7o\2\2\u023e\u023f\7w\2\2\u023f\u0240\7o\2\2\u0240\u0241\7a\2\2\u0241" +
					"\u0242\7u\2\2\u0242\u0243\7v\2\2\u0243\u0244\7c\2\2\u0244\u0245\7v\2\2" +
					"\u0245\u0246\7a\2\2\u0246\u0247\7d\2\2\u0247\u0248\7g\2\2\u0248\u0249" +
					"\7i\2\2\u0249\u024a\7k\2\2\u024a\u024b\7p\2\2\u024b\"\3\2\2\2\u024c\u024d" +
					"\7o\2\2\u024d\u024e\7c\2\2\u024e\u024f\7z\2\2\u024f\u0250\7k\2\2\u0250" +
					"\u0251\7o\2\2\u0251\u0252\7w\2\2\u0252\u0253\7o\2\2\u0253\u0254\7a\2\2" +
					"\u0254\u0255\7u\2\2\u0255\u0256\7v\2\2\u0256\u0257\7c\2\2\u0257\u0258" +
					"\7v\2\2\u0258\u0259\7a\2\2\u0259\u025a\7g\2\2\u025a\u025b\7p\2\2\u025b" +
					"\u025c\7f\2\2\u025c$\3\2\2\2\u025d\u025e\7t\2\2\u025e\u025f\7g\2\2\u025f" +
					"\u0260\7e\2\2\u0260\u0261\7q\2\2\u0261\u0262\7o\2\2\u0262\u0263\7o\2\2" +
					"\u0263\u0264\7g\2\2\u0264\u0265\7p\2\2\u0265\u0266\7f\2\2\u0266\u0267" +
					"\7g\2\2\u0267\u0268\7f\2\2\u0268\u0269\7a\2\2\u0269\u026a\7u\2\2\u026a" +
					"\u026b\7v\2\2\u026b\u026c\7c\2\2\u026c\u026d\7v\2\2\u026d\u026e\7a\2\2" +
					"\u026e\u026f\7d\2\2\u026f\u0270\7g\2\2\u0270\u0271\7i\2\2\u0271\u0272" +
					"\7k\2\2\u0272\u0273\7p\2\2\u0273&\3\2\2\2\u0274\u0275\7t\2\2\u0275\u0276" +
					"\7g\2\2\u0276\u0277\7e\2\2\u0277\u0278\7q\2\2\u0278\u0279\7o\2\2\u0279" +
					"\u027a\7o\2\2\u027a\u027b\7g\2\2\u027b\u027c\7p\2\2\u027c\u027d\7f\2\2" +
					"\u027d\u027e\7g\2\2\u027e\u027f\7f\2\2\u027f\u0280\7a\2\2\u0280\u0281" +
					"\7u\2\2\u0281\u0282\7v\2\2\u0282\u0283\7c\2\2\u0283\u0284\7v\2\2\u0284" +
					"\u0285\7a\2\2\u0285\u0286\7g\2\2\u0286\u0287\7p\2\2\u0287\u0288\7f\2\2" +
					"\u0288(\3\2\2\2\u0289\u028a\7u\2\2\u028a\u028b\7n\2\2\u028b\u028c\7q\2" +
					"\2\u028c\u028d\7v\2\2\u028d\u028e\7a\2\2\u028e\u028f\7n\2\2\u028f\u0290" +
					"\7j\2\2\u0290\u0291\7c\2\2\u0291\u0292\7p\2\2\u0292\u0293\7f\2\2\u0293" +
					"*\3\2\2\2\u0294\u0295\7]\2\2\u0295,\3\2\2\2\u0296\u0297\7_\2\2\u0297." +
					"\3\2\2\2\u0298\u0299\7d\2\2\u0299\u029a\7c\2\2\u029a\u029b\7u\2\2\u029b" +
					"\u029c\7g\2\2\u029c\u029d\7a\2\2\u029d\u029e\7c\2\2\u029e\u029f\7v\2\2" +
					"\u029f\u02a0\7v\2\2\u02a0\u02a1\7t\2\2\u02a1\u02a2\7k\2\2\u02a2\u02a3" +
					"\7d\2\2\u02a3\u02a4\7w\2\2\u02a4\u02a5\7v\2\2\u02a5\u02a6\7g\2\2\u02a6" +
					"\u02a7\7a\2\2\u02a7\u02a8\7c\2\2\u02a8\u02a9\7v\2\2\u02a9\u02aa\7v\2\2" +
					"\u02aa\u02ab\7c\2\2\u02ab\u02ac\7e\2\2\u02ac\u02ad\7m\2\2\u02ad\60\3\2" +
					"\2\2\u02ae\u02af\7j\2\2\u02af\u02b0\7w\2\2\u02b0\u02b1\7o\2\2\u02b1\u02b2" +
					"\7c\2\2\u02b2\u02b3\7p\2\2\u02b3\u02b4\7a\2\2\u02b4\u02b5\7h\2\2\u02b5" +
					"\u02b6\7k\2\2\u02b6\u02b7\7i\2\2\u02b7\u02b8\7j\2\2\u02b8\u02b9\7v\2\2" +
					"\u02b9\u02ba\7g\2\2\u02ba\u02bb\7t\2\2\u02bb\62\3\2\2\2\u02bc\u02bd\7" +
					"j\2\2\u02bd\u02be\7w\2\2\u02be\u02bf\7o\2\2\u02bf\u02c0\7c\2\2\u02c0\u02c1" +
					"\7p\2\2\u02c1\u02c2\7a\2\2\u02c2\u02c3\7o\2\2\u02c3\u02c4\7c\2\2\u02c4" +
					"\u02c5\7i\2\2\u02c5\u02c6\7k\2\2\u02c6\u02c7\7e\2\2\u02c7\u02c8\7k\2\2" +
					"\u02c8\u02c9\7c\2\2\u02c9\u02ca\7p\2\2\u02ca\64\3\2\2\2\u02cb\u02cc\7" +
					"g\2\2\u02cc\u02cd\7n\2\2\u02cd\u02ce\7h\2\2\u02ce\u02cf\7a\2\2\u02cf\u02d0" +
					"\7h\2\2\u02d0\u02d1\7k\2\2\u02d1\u02d2\7i\2\2\u02d2\u02d3\7j\2\2\u02d3" +
					"\u02d4\7v\2\2\u02d4\u02d5\7g\2\2\u02d5\u02d6\7t\2\2\u02d6\66\3\2\2\2\u02d7" +
					"\u02d8\7g\2\2\u02d8\u02d9\7n\2\2\u02d9\u02da\7h\2\2\u02da\u02db\7a\2\2" +
					"\u02db\u02dc\7o\2\2\u02dc\u02dd\7c\2\2\u02dd\u02de\7i\2\2\u02de\u02df" +
					"\7k\2\2\u02df\u02e0\7e\2\2\u02e0\u02e1\7k\2\2\u02e1\u02e2\7c\2\2\u02e2" +
					"\u02e3\7p\2\2\u02e38\3\2\2\2\u02e4\u02e5\7f\2\2\u02e5\u02e6\7c\2\2\u02e6" +
					"\u02e7\7t\2\2\u02e7\u02e8\7m\2\2\u02e8\u02e9\7g\2\2\u02e9\u02ea\7n\2\2" +
					"\u02ea\u02eb\7h\2\2\u02eb\u02ec\7a\2\2\u02ec\u02ed\7h\2\2\u02ed\u02ee" +
					"\7k\2\2\u02ee\u02ef\7i\2\2\u02ef\u02f0\7j\2\2\u02f0\u02f1\7v\2\2\u02f1" +
					"\u02f2\7g\2\2\u02f2\u02f3\7t\2\2\u02f3:\3\2\2\2\u02f4\u02f5\7f\2\2\u02f5" +
					"\u02f6\7c\2\2\u02f6\u02f7\7t\2\2\u02f7\u02f8\7m\2\2\u02f8\u02f9\7g\2\2" +
					"\u02f9\u02fa\7n\2\2\u02fa\u02fb\7h\2\2\u02fb\u02fc\7a\2\2\u02fc\u02fd" +
					"\7o\2\2\u02fd\u02fe\7c\2\2\u02fe\u02ff\7i\2\2\u02ff\u0300\7k\2\2\u0300" +
					"\u0301\7e\2\2\u0301\u0302\7k\2\2\u0302\u0303\7c\2\2\u0303\u0304\7p\2\2" +
					"\u0304<\3\2\2\2\u0305\u0306\7q\2\2\u0306\u0307\7t\2\2\u0307\u0308\7e\2" +
					"\2\u0308\u0309\7a\2\2\u0309\u030a\7h\2\2\u030a\u030b\7k\2\2\u030b\u030c" +
					"\7i\2\2\u030c\u030d\7j\2\2\u030d\u030e\7v\2\2\u030e\u030f\7g\2\2\u030f" +
					"\u0310\7t\2\2\u0310>\3\2\2\2\u0311\u0312\7q\2\2\u0312\u0313\7t\2\2\u0313" +
					"\u0314\7e\2\2\u0314\u0315\7a\2\2\u0315\u0316\7u\2\2\u0316\u0317\7j\2\2" +
					"\u0317\u0318\7c\2\2\u0318\u0319\7o\2\2\u0319\u031a\7c\2\2\u031a\u031b" +
					"\7p\2\2\u031b@\3\2\2\2\u031c\u031d\7f\2\2\u031d\u031e\7y\2\2\u031e\u031f" +
					"\7c\2\2\u031f\u0320\7t\2\2\u0320\u0321\7h\2\2\u0321\u0322\7a\2\2\u0322" +
					"\u0323\7c\2\2\u0323\u0324\7r\2\2\u0324\u0325\7r\2\2\u0325\u0326\7t\2\2" +
					"\u0326\u0327\7g\2\2\u0327\u0328\7p\2\2\u0328\u0329\7v\2\2\u0329\u032a" +
					"\7k\2\2\u032a\u032b\7e\2\2\u032b\u032c\7g\2\2\u032cB\3\2\2\2\u032d\u032e" +
					"\7f\2\2\u032e\u032f\7y\2\2\u032f\u0330\7c\2\2\u0330\u0331\7t\2\2\u0331" +
					"\u0332\7h\2\2\u0332\u0333\7a\2\2\u0333\u0334\7o\2\2\u0334\u0335\7c\2\2" +
					"\u0335\u0336\7i\2\2\u0336\u0337\7g\2\2\u0337D\3\2\2\2\u0338\u0339\7m\2" +
					"\2\u0339\u033a\7c\2\2\u033a\u033b\7o\2\2\u033b\u033c\7c\2\2\u033c\u033d" +
					"\7g\2\2\u033d\u033e\7n\2\2\u033e\u033f\7a\2\2\u033f\u0340\7o\2\2\u0340" +
					"\u0341\7a\2\2\u0341\u0342\7u\2\2\u0342\u0343\7q\2\2\u0343\u0344\7n\2\2" +
					"\u0344\u0345\7f\2\2\u0345\u0346\7k\2\2\u0346\u0347\7g\2\2\u0347\u0348" +
					"\7t\2\2\u0348F\3\2\2\2\u0349\u034a\7m\2\2\u034a\u034b\7c\2\2\u034b\u034c" +
					"\7o\2\2\u034c\u034d\7c\2\2\u034d\u034e\7g\2\2\u034e\u034f\7n\2\2\u034f" +
					"\u0350\7a\2\2\u0350\u0351\7h\2\2\u0351\u0352\7a\2\2\u0352\u0353\7u\2\2" +
					"\u0353\u0354\7q\2\2\u0354\u0355\7n\2\2\u0355\u0356\7f\2\2\u0356\u0357" +
					"\7k\2\2\u0357\u0358\7g\2\2\u0358\u0359\7t\2\2\u0359H\3\2\2\2\u035a\u035b" +
					"\7B\2\2\u035b\u035c\5\u0149\u00a5\2\u035cJ\3\2\2\2\u035d\u035e\5a\61\2" +
					"\u035eL\3\2\2\2\u035f\u0361\5_\60\2\u0360\u035f\3\2\2\2\u0360\u0361\3" +
					"\2\2\2\u0361\u0362\3\2\2\2\u0362\u0363\5]/\2\u0363N\3\2\2\2\u0364\u0365" +
					"\5M\'\2\u0365\u0367\7\60\2\2\u0366\u0368\5c\62\2\u0367\u0366\3\2\2\2\u0368" +
					"\u0369\3\2\2\2\u0369\u0367\3\2\2\2\u0369\u036a\3\2\2\2\u036aP\3\2\2\2" +
					"\u036b\u036c\7h\2\2\u036c\u036d\7k\2\2\u036d\u036e\7t\2\2\u036e\u036f" +
					"\7g\2\2\u036fR\3\2\2\2\u0370\u0371\7y\2\2\u0371\u0372\7c\2\2\u0372\u0373" +
					"\7v\2\2\u0373\u0374\7g\2\2\u0374\u0375\7t\2\2\u0375T\3\2\2\2\u0376\u0377" +
					"\7g\2\2\u0377\u0378\7c\2\2\u0378\u0379\7t\2\2\u0379\u037a\7v\2\2\u037a" +
					"\u037b\7j\2\2\u037bV\3\2\2\2\u037c\u037d\7y\2\2\u037d\u037e\7k\2\2\u037e" +
					"\u037f\7p\2\2\u037f\u0380\7f\2\2\u0380X\3\2\2\2\u0381\u0382\7w\2\2\u0382" +
					"\u0383\7p\2\2\u0383\u0384\7j\2\2\u0384\u0385\7q\2\2\u0385\u0386\7n\2\2" +
					"\u0386\u0387\7{\2\2\u0387Z\3\2\2\2\u0388\u0389\7j\2\2\u0389\u038a\7q\2" +
					"\2\u038a\u038b\7n\2\2\u038b\u038c\7{\2\2\u038c\\\3\2\2\2\u038d\u0396\5" +
					"g\64\2\u038e\u0392\5e\63\2\u038f\u0391\5c\62\2\u0390\u038f\3\2\2\2\u0391" +
					"\u0394\3\2\2\2\u0392\u0390\3\2\2\2\u0392\u0393\3\2\2\2\u0393\u0396\3\2" +
					"\2\2\u0394\u0392\3\2\2\2\u0395\u038d\3\2\2\2\u0395\u038e\3\2\2\2\u0396" +
					"^\3\2\2\2\u0397\u0398\7/\2\2\u0398`\3\2\2\2\u0399\u039a\t\2\2\2\u039a" +
					"b\3\2\2\2\u039b\u039c\t\3\2\2\u039cd\3\2\2\2\u039d\u039e\t\4\2\2\u039e" +
					"f\3\2\2\2\u039f\u03a0\t\5\2\2\u03a0h\3\2\2\2\u03a1\u03a2\7=\2\2\u03a2" +
					"j\3\2\2\2\u03a3\u03a4\7p\2\2\u03a4\u03a5\7q\2\2\u03a5\u03a6\7p\2\2\u03a6" +
					"\u03a7\7g\2\2\u03a7l\3\2\2\2\u03a8\u03a9\7h\2\2\u03a9\u03aa\7c\2\2\u03aa" +
					"\u03ab\7k\2\2\u03ab\u03ac\7t\2\2\u03ac\u03ad\7{\2\2\u03adn\3\2\2\2\u03ae" +
					"\u03af\7c\2\2\u03af\u03b0\7p\2\2\u03b0\u03b1\7k\2\2\u03b1\u03b2\7o\2\2" +
					"\u03b2\u03b3\7c\2\2\u03b3\u03b4\7n\2\2\u03b4p\3\2\2\2\u03b5\u03b6\7j\2" +
					"\2\u03b6\u03b7\7w\2\2\u03b7\u03b8\7o\2\2\u03b8\u03b9\7c\2\2\u03b9\u03ba" +
					"\7p\2\2\u03ba\u03bb\7q\2\2\u03bb\u03bc\7k\2\2\u03bc\u03bd\7f\2\2\u03bd" +
					"r\3\2\2\2\u03be\u03bf\7r\2\2\u03bf\u03c0\7n\2\2\u03c0\u03c1\7c\2\2\u03c1" +
					"\u03c2\7p\2\2\u03c2\u03c3\7v\2\2\u03c3t\3\2\2\2\u03c4\u03c5\7w\2\2\u03c5" +
					"\u03c6\7p\2\2\u03c6\u03c7\7f\2\2\u03c7\u03c8\7g\2\2\u03c8\u03c9\7c\2\2" +
					"\u03c9\u03ca\7f\2\2\u03cav\3\2\2\2\u03cb\u03cc\7e\2\2\u03cc\u03cd\7q\2" +
					"\2\u03cd\u03ce\7p\2\2\u03ce\u03cf\7u\2\2\u03cf\u03d0\7v\2\2\u03d0\u03d1" +
					"\7t\2\2\u03d1\u03d2\7w\2\2\u03d2\u03d3\7e\2\2\u03d3\u03d4\7v\2\2\u03d4" +
					"x\3\2\2\2\u03d5\u03d6\7d\2\2\u03d6\u03d7\7g\2\2\u03d7\u03d8\7c\2\2\u03d8" +
					"\u03d9\7u\2\2\u03d9\u03da\7v\2\2\u03daz\3\2\2\2\u03db\u03dc\7d\2\2\u03dc" +
					"\u03dd\7w\2\2\u03dd\u03de\7i\2\2\u03de|\3\2\2\2\u03df\u03e0\7g\2\2\u03e0" +
					"\u03e1\7n\2\2\u03e1\u03e2\7g\2\2\u03e2\u03e3\7o\2\2\u03e3\u03e4\7g\2\2" +
					"\u03e4\u03e5\7p\2\2\u03e5\u03e6\7v\2\2\u03e6\u03e7\7c\2\2\u03e7\u03e8" +
					"\7n\2\2\u03e8~\3\2\2\2\u03e9\u03ea\7f\2\2\u03ea\u03eb\7g\2\2\u03eb\u03ec" +
					"\7o\2\2\u03ec\u03ed\7q\2\2\u03ed\u03ee\7p\2\2\u03ee\u03ef\7k\2\2\u03ef" +
					"\u03f0\7e\2\2\u03f0\u0080\3\2\2\2\u03f1\u03f2\7i\2\2\u03f2\u03f3\7k\2" +
					"\2\u03f3\u03f4\7c\2\2\u03f4\u03f5\7p\2\2\u03f5\u03f6\7v\2\2\u03f6\u0082" +
					"\3\2\2\2\u03f7\u03f8\7f\2\2\u03f8\u03f9\7t\2\2\u03f9\u03fa\7c\2\2\u03fa" +
					"\u03fb\7i\2\2\u03fb\u03fc\7q\2\2\u03fc\u03fd\7p\2\2\u03fd\u0084\3\2\2" +
					"\2\u03fe\u03ff\7f\2\2\u03ff\u0400\7k\2\2\u0400\u0401\7x\2\2\u0401\u0402" +
					"\7k\2\2\u0402\u0403\7p\2\2\u0403\u0404\7g\2\2\u0404\u0086\3\2\2\2\u0405" +
					"\u0406\7u\2\2\u0406\u0407\7w\2\2\u0407\u0408\7o\2\2\u0408\u0409\7o\2\2" +
					"\u0409\u040a\7q\2\2\u040a\u040b\7p\2\2\u040b\u0088\3\2\2\2\u040c\u040d" +
					"\7r\2\2\u040d\u040e\7g\2\2\u040e\u040f\7v\2\2\u040f\u008a\3\2\2\2\u0410" +
					"\u0411\7j\2\2\u0411\u0412\7q\2\2\u0412\u0413\7n\2\2\u0413\u0414\7{\2\2" +
					"\u0414\u0415\7v\2\2\u0415\u0416\7j\2\2\u0416\u0417\7k\2\2\u0417\u0418" +
					"\7p\2\2\u0418\u0419\7i\2\2\u0419\u008c\3\2\2\2\u041a\u041b\7f\2\2\u041b" +
					"\u041c\7y\2\2\u041c\u041d\7c\2\2\u041d\u041e\7t\2\2\u041e\u041f\7h\2\2" +
					"\u041f\u008e\3\2\2\2\u0420\u0421\7o\2\2\u0421\u0422\7g\2\2\u0422\u0423" +
					"\7t\2\2\u0423\u0424\7e\2\2\u0424\u0425\7j\2\2\u0425\u0426\7c\2\2\u0426" +
					"\u0427\7p\2\2\u0427\u0428\7v\2\2\u0428\u0090\3\2\2\2\u0429\u042a\7g\2" +
					"\2\u042a\u042b\7n\2\2\u042b\u042c\7h\2\2\u042c\u0092\3\2\2\2\u042d\u042e" +
					"\7m\2\2\u042e\u042f\7c\2\2\u042f\u0430\7o\2\2\u0430\u0431\7c\2\2\u0431" +
					"\u0432\7g\2\2\u0432\u0433\7n\2\2\u0433\u0094\3\2\2\2\u0434\u0435\7q\2" +
					"\2\u0435\u0436\7t\2\2\u0436\u0437\7e\2\2\u0437\u0096\3\2\2\2\u0438\u0439" +
					"\7o\2\2\u0439\u043a\7g\2\2\u043a\u043b\7t\2\2\u043b\u043c\7e\2\2\u043c" +
					"\u043d\7g\2\2\u043d\u043e\7p\2\2\u043e\u043f\7c\2\2\u043f\u0440\7t\2\2" +
					"\u0440\u0441\7{\2\2\u0441\u0098\3\2\2\2\u0442\u0443\7e\2\2\u0443\u0444" +
					"\7c\2\2\u0444\u0445\7u\2\2\u0445\u0446\7v\2\2\u0446\u0447\7n\2\2\u0447" +
					"\u0448\7g\2\2\u0448\u0449\7a\2\2\u0449\u044a\7i\2\2\u044a\u044b\7w\2\2" +
					"\u044b\u044c\7c\2\2\u044c\u044d\7t\2\2\u044d\u044e\7f\2\2\u044e\u009a" +
					"\3\2\2\2\u044f\u0450\7j\2\2\u0450\u0451\7w\2\2\u0451\u0452\7o\2\2\u0452" +
					"\u0453\7c\2\2\u0453\u0454\7p\2\2\u0454\u009c\3\2\2\2\u0455\u0456\7d\2" +
					"\2\u0456\u0457\7q\2\2\u0457\u0458\7u\2\2\u0458\u0459\7u\2\2\u0459\u009e" +
					"\3\2\2\2\u045a\u045b\7|\2\2\u045b\u045c\7|\2\2\u045c\u045d\7q\2\2\u045d" +
					"\u045e\7n\2\2\u045e\u045f\7f\2\2\u045f\u0460\7c\2\2\u0460\u0461\7i\2\2" +
					"\u0461\u0462\7w\2\2\u0462\u00a0\3\2\2\2\u0463\u0464\7y\2\2\u0464\u0465" +
					"\7q\2\2\u0465\u0466\7t\2\2\u0466\u0467\7n\2\2\u0467\u0468\7f\2\2\u0468" +
					"\u0469\7a\2\2\u0469\u046a\7v\2\2\u046a\u046b\7t\2\2\u046b\u046c\7c\2\2" +
					"\u046c\u046d\7r\2\2\u046d\u00a2\3\2\2\2\u046e\u046f\7o\2\2\u046f\u0470" +
					"\7q\2\2\u0470\u0471\7p\2\2\u0471\u0472\7t\2\2\u0472\u0473\7c\2\2\u0473" +
					"\u0474\7e\2\2\u0474\u0475\7g\2\2\u0475\u00a4\3\2\2\2\u0476\u0477\7f\2" +
					"\2\u0477\u0478\7c\2\2\u0478\u0479\7t\2\2\u0479\u047a\7m\2\2\u047a\u047b" +
					"\7g\2\2\u047b\u047c\7n\2\2\u047c\u047d\7h\2\2\u047d\u00a6\3\2\2\2\u047e" +
					"\u047f\7i\2\2\u047f\u0480\7w\2\2\u0480\u0481\7c\2\2\u0481\u0482\7t\2\2" +
					"\u0482\u0483\7f\2\2\u0483\u00a8\3\2\2\2\u0484\u0485\7v\2\2\u0485\u0486" +
					"\7g\2\2\u0486\u0487\7n\2\2\u0487\u0488\7g\2\2\u0488\u0489\7r\2\2\u0489" +
					"\u048a\7q\2\2\u048a\u048b\7t\2\2\u048b\u048c\7v\2\2\u048c\u048d\7g\2\2" +
					"\u048d\u048e\7t\2\2\u048e\u00aa\3\2\2\2\u048f\u0490\7y\2\2\u0490\u0491" +
					"\7c\2\2\u0491\u0492\7t\2\2\u0492\u0493\7g\2\2\u0493\u0494\7j\2\2\u0494" +
					"\u0495\7q\2\2\u0495\u0496\7w\2\2\u0496\u0497\7u\2\2\u0497\u0498\7g\2\2" +
					"\u0498\u0499\7a\2\2\u0499\u049a\7m\2\2\u049a\u049b\7g\2\2\u049b\u049c" +
					"\7g\2\2\u049c\u049d\7r\2\2\u049d\u049e\7g\2\2\u049e\u049f\7t\2\2\u049f" +
					"\u00ac\3\2\2\2\u04a0\u04a1\7y\2\2\u04a1\u04a2\7c\2\2\u04a2\u04a3\7t\2" +
					"\2\u04a3\u04a4\7t\2\2\u04a4\u04a5\7k\2\2\u04a5\u04a6\7q\2\2\u04a6\u04a7" +
					"\7t\2\2\u04a7\u00ae\3\2\2\2\u04a8\u04a9\7e\2\2\u04a9\u04aa\7k\2\2\u04aa" +
					"\u04ab\7v\2\2\u04ab\u04ac\7k\2\2\u04ac\u04ad\7|\2\2\u04ad\u04ae\7g\2\2" +
					"\u04ae\u04af\7p\2\2\u04af\u00b0\3\2\2\2\u04b0\u04b1\7v\2\2\u04b1\u04b2" +
					"\7t\2\2\u04b2\u04b3\7g\2\2\u04b3\u04b4\7c\2\2\u04b4\u04b5\7u\2\2\u04b5" +
					"\u04b6\7w\2\2\u04b6\u04b7\7t\2\2\u04b7\u04b8\7g\2\2\u04b8\u00b2\3\2\2" +
					"\2\u04b9\u04ba\7h\2\2\u04ba\u04bb\7k\2\2\u04bb\u04bc\7g\2\2\u04bc\u04bd" +
					"\7n\2\2\u04bd\u04be\7f\2\2\u04be\u04bf\7d\2\2\u04bf\u04c0\7q\2\2\u04c0" +
					"\u04c1\7u\2\2\u04c1\u04c2\7u\2\2\u04c2\u00b4\3\2\2\2\u04c3\u04c4\7d\2" +
					"\2\u04c4\u04c5\7n\2\2\u04c5\u04c6\7c\2\2\u04c6\u04c7\7e\2\2\u04c7\u04c8" +
					"\7m\2\2\u04c8\u04c9\7u\2\2\u04c9\u04ca\7o\2\2\u04ca\u04cb\7k\2\2\u04cb" +
					"\u04cc\7v\2\2\u04cc\u04cd\7j\2\2\u04cd\u00b6\3\2\2\2\u04ce\u04cf\7i\2" +
					"\2\u04cf\u04d0\7w\2\2\u04d0\u04d1\7k\2\2\u04d1\u04d2\7n\2\2\u04d2\u04d3" +
					"\7f\2\2\u04d3\u04d4\7a\2\2\u04d4\u04d5\7o\2\2\u04d5\u04d6\7c\2\2\u04d6" +
					"\u04d7\7u\2\2\u04d7\u04d8\7v\2\2\u04d8\u04d9\7g\2\2\u04d9\u04da\7t\2\2" +
					"\u04da\u00b8\3\2\2\2\u04db\u04dc\7i\2\2\u04dc\u04dd\7w\2\2\u04dd\u04de" +
					"\7k\2\2\u04de\u04df\7n\2\2\u04df\u04e0\7f\2\2\u04e0\u04e1\7a\2\2\u04e1" +
					"\u04e2\7e\2\2\u04e2\u04e3\7q\2\2\u04e3\u04e4\7c\2\2\u04e4\u04e5\7e\2\2" +
					"\u04e5\u04e6\7j\2\2\u04e6\u00ba\3\2\2\2\u04e7\u04e8\7u\2\2\u04e8\u04e9" +
					"\7y\2\2\u04e9\u04ea\7q\2\2\u04ea\u04eb\7t\2\2\u04eb\u04ec\7f\2\2\u04ec" +
					"\u00bc\3\2\2\2\u04ed\u04ee\7d\2\2\u04ee\u04ef\7n\2\2\u04ef\u04f0\7w\2" +
					"\2\u04f0\u04f1\7p\2\2\u04f1\u04f2\7v\2\2\u04f2\u00be\3\2\2\2\u04f3\u04f4" +
					"\7d\2\2\u04f4\u04f5\7q\2\2\u04f5\u04f6\7y\2\2\u04f6\u00c0\3\2\2\2\u04f7" +
					"\u04f8\7r\2\2\u04f8\u04f9\7q\2\2\u04f9\u04fa\7n\2\2\u04fa\u04fb\7g\2\2" +
					"\u04fb\u00c2\3\2\2\2\u04fc\u04fd\7f\2\2\u04fd\u04fe\7c\2\2\u04fe\u04ff" +
					"\7i\2\2\u04ff\u0500\7i\2\2\u0500\u0501\7g\2\2\u0501\u0502\7t\2\2\u0502" +
					"\u00c4\3\2\2\2\u0503\u0504\7f\2\2\u0504\u0505\7w\2\2\u0505\u0506\7c\2" +
					"\2\u0506\u0507\7n\2\2\u0507\u00c6\3\2\2\2\u0508\u0509\7h\2\2\u0509\u050a" +
					"\7k\2\2\u050a\u050b\7u\2\2\u050b\u050c\7v\2\2\u050c\u00c8\3\2\2\2\u050d" +
					"\u050e\7f\2\2\u050e\u050f\7w\2\2\u050f\u0510\7c\2\2\u0510\u0511\7n\2\2" +
					"\u0511\u0512\7h\2\2\u0512\u0513\7k\2\2\u0513\u0514\7u\2\2\u0514\u0515" +
					"\7v\2\2\u0515\u00ca\3\2\2\2\u0516\u0517\7h\2\2\u0517\u0518\7k\2\2\u0518" +
					"\u0519\7u\2\2\u0519\u051a\7j\2\2\u051a\u051b\7k\2\2\u051b\u051c\7p\2\2" +
					"\u051c\u051d\7i\2\2\u051d\u051e\7t\2\2\u051e\u051f\7q\2\2\u051f\u0520" +
					"\7f\2\2\u0520\u00cc\3\2\2\2\u0521\u0522\7t\2\2\u0522\u0523\7c\2\2\u0523" +
					"\u0524\7r\2\2\u0524\u0525\7k\2\2\u0525\u0526\7g\2\2\u0526\u0527\7t\2\2" +
					"\u0527\u00ce\3\2\2\2\u0528\u0529\7c\2\2\u0529\u052a\7p\2\2\u052a\u052b" +
					"\7e\2\2\u052b\u052c\7k\2\2\u052c\u052d\7g\2\2\u052d\u052e\7p\2\2\u052e" +
					"\u052f\7v\2\2\u052f\u0530\7u\2\2\u0530\u0531\7y\2\2\u0531\u0532\7q\2\2" +
					"\u0532\u0533\7t\2\2\u0533\u0534\7f\2\2\u0534\u00d0\3\2\2\2\u0535\u0536" +
					"\7e\2\2\u0536\u0537\7t\2\2\u0537\u0538\7q\2\2\u0538\u0539\7u\2\2\u0539" +
					"\u053a\7u\2\2\u053a\u053b\7d\2\2\u053b\u053c\7q\2\2\u053c\u053d\7y\2\2" +
					"\u053d\u00d2\3\2\2\2\u053e\u053f\7h\2\2\u053f\u0540\7n\2\2\u0540\u0541" +
					"\7c\2\2\u0541\u0542\7i\2\2\u0542\u00d4\3\2\2\2\u0543\u0544\7q\2\2\u0544" +
					"\u0545\7y\2\2\u0545\u0546\7p\2\2\u0546\u0547\7v\2\2\u0547\u0548\7j\2\2" +
					"\u0548\u0549\7k\2\2\u0549\u054a\7p\2\2\u054a\u054b\7i\2\2\u054b\u00d6" +
					"\3\2\2\2\u054c\u054d\7f\2\2\u054d\u054e\7w\2\2\u054e\u054f\7c\2\2\u054f" +
					"\u0550\7n\2\2\u0550\u0551\7f\2\2\u0551\u0552\7c\2\2\u0552\u0553\7i\2\2" +
					"\u0553\u0554\7i\2\2\u0554\u0555\7g\2\2\u0555\u0556\7t\2\2\u0556\u00d8" +
					"\3\2\2\2\u0557\u0558\7g\2\2\u0558\u0559\7v\2\2\u0559\u055a\7e\2\2\u055a" +
					"\u00da\3\2\2\2\u055b\u055c\7n\2\2\u055c\u055d\7k\2\2\u055d\u055e\7i\2" +
					"\2\u055e\u055f\7j\2\2\u055f\u0560\7v\2\2\u0560\u00dc\3\2\2\2\u0561\u0562" +
					"\7j\2\2\u0562\u0563\7g\2\2\u0563\u0564\7c\2\2\u0564\u0565\7x\2\2\u0565" +
					"\u0566\7{\2\2\u0566\u00de\3\2\2\2\u0567\u0568\7o\2\2\u0568\u0569\7c\2" +
					"\2\u0569\u056a\7i\2\2\u056a\u056b\7k\2\2\u056b\u056c\7e\2\2\u056c\u00e0" +
					"\3\2\2\2\u056d\u056e\7u\2\2\u056e\u056f\7k\2\2\u056f\u0570\7i\2\2\u0570" +
					"\u0571\7k\2\2\u0571\u0572\7n\2\2\u0572\u00e2\3\2\2\2\u0573\u0574\7t\2" +
					"\2\u0574\u0575\7j\2\2\u0575\u0576\7c\2\2\u0576\u0577\7p\2\2\u0577\u0578" +
					"\7f\2\2\u0578\u00e4\3\2\2\2\u0579\u057a\7n\2\2\u057a\u057b\7t\2\2\u057b" +
					"\u057c\7j\2\2\u057c\u057d\7c\2\2\u057d\u057e\7p\2\2\u057e\u057f\7f\2\2" +
					"\u057f\u00e6\3\2\2\2\u0580\u0581\7n\2\2\u0581\u0582\7j\2\2\u0582\u0583" +
					"\7c\2\2\u0583\u0584\7p\2\2\u0584\u0585\7f\2\2\u0585\u00e8\3\2\2\2\u0586" +
					"\u0587\7e\2\2\u0587\u0588\7j\2\2\u0588\u0589\7g\2\2\u0589\u058a\7u\2\2" +
					"\u058a\u058b\7v\2\2\u058b\u00ea\3\2\2\2\u058c\u058d\7n\2\2\u058d\u058e" +
					"\7g\2\2\u058e\u058f\7i\2\2\u058f\u0590\7u\2\2\u0590\u00ec\3\2\2\2\u0591" +
					"\u0592\7h\2\2\u0592\u0593\7g\2\2\u0593\u0594\7g\2\2\u0594\u0595\7v\2\2" +
					"\u0595\u00ee\3\2\2\2\u0596\u0597\7j\2\2\u0597\u0598\7g\2\2\u0598\u0599" +
					"\7c\2\2\u0599\u059a\7f\2\2\u059a\u00f0\3\2\2\2\u059b\u059c\7i\2\2\u059c" +
					"\u059d\7n\2\2\u059d\u059e\7q\2\2\u059e\u059f\7x\2\2\u059f\u05a0\7g\2\2" +
					"\u05a0\u05a1\7u\2\2\u05a1\u00f2\3\2\2\2\u05a2\u05a3\7q\2\2\u05a3\u05a4" +
					"\7p\2\2\u05a4\u05a5\7g\2\2\u05a5\u05a6\7r\2\2\u05a6\u05a7\7k\2\2\u05a7" +
					"\u05a8\7g\2\2\u05a8\u05a9\7e\2\2\u05a9\u05aa\7g\2\2\u05aa\u00f4\3\2\2" +
					"\2\u05ab\u05ac\7t\2\2\u05ac\u05ad\7g\2\2\u05ad\u05ae\7c\2\2\u05ae\u05af" +
					"\7t\2\2\u05af\u00f6\3\2\2\2\u05b0\u05b1\7n\2\2\u05b1\u05b2\7g\2\2\u05b2" +
					"\u05b3\7c\2\2\u05b3\u05b4\7t\2\2\u05b4\u00f8\3\2\2\2\u05b5\u05b6\7t\2" +
					"\2\u05b6\u05b7\7h\2\2\u05b7\u05b8\7k\2\2\u05b8\u05b9\7p\2\2\u05b9\u05ba" +
					"\7i\2\2\u05ba\u05bb\7g\2\2\u05bb\u05bc\7t\2\2\u05bc\u00fa\3\2\2\2\u05bd" +
					"\u05be\7n\2\2\u05be\u05bf\7h\2\2\u05bf\u05c0\7k\2\2\u05c0\u05c1\7p\2\2" +
					"\u05c1\u05c2\7i\2\2\u05c2\u05c3\7g\2\2\u05c3\u05c4\7t\2\2\u05c4\u00fc" +
					"\3\2\2\2\u05c5\u05c6\7p\2\2\u05c6\u05c7\7g\2\2\u05c7\u05c8\7e\2\2\u05c8" +
					"\u05c9\7m\2\2\u05c9\u00fe\3\2\2\2\u05ca\u05cb\7d\2\2\u05cb\u05cc\7c\2" +
					"\2\u05cc\u05cd\7e\2\2\u05cd\u05ce\7m\2\2\u05ce\u0100\3\2\2\2\u05cf\u05d0" +
					"\7w\2\2\u05d0\u05d1\7p\2\2\u05d1\u05d2\7f\2\2\u05d2\u05d3\7g\2\2\u05d3" +
					"\u05d4\7t\2\2\u05d4\u05d5\7y\2\2\u05d5\u05d6\7g\2\2\u05d6\u05d7\7c\2\2" +
					"\u05d7\u05d8\7t\2\2\u05d8\u0102\3\2\2\2\u05d9\u05da\7j\2\2\u05da\u05db" +
					"\7c\2\2\u05db\u05dc\7k\2\2\u05dc\u05dd\7t\2\2\u05dd\u0104\3\2\2\2\u05de" +
					"\u05df\7j\2\2\u05df\u05e0\7c\2\2\u05e0\u05e1\7k\2\2\u05e1\u05e2\7t\2\2" +
					"\u05e2\u05e3\7\64\2\2\u05e3\u0106\3\2\2\2\u05e4\u05e5\7j\2\2\u05e5\u05e6" +
					"\7c\2\2\u05e6\u05e7\7k\2\2\u05e7\u05e8\7t\2\2\u05e8\u05e9\7c\2\2\u05e9" +
					"\u05ea\7n\2\2\u05ea\u05eb\7n\2\2\u05eb\u0108\3\2\2\2\u05ec\u05ed\7c\2" +
					"\2\u05ed\u05ee\7n\2\2\u05ee\u05ef\7n\2\2\u05ef\u05f0\7f\2\2\u05f0\u05f1" +
					"\7t\2\2\u05f1\u05f2\7g\2\2\u05f2\u05f3\7u\2\2\u05f3\u05f4\7u\2\2\u05f4" +
					"\u010a\3\2\2\2\u05f5\u05f6\7t\2\2\u05f6\u05f7\7d\2\2\u05f7\u05f8\7t\2" +
					"\2\u05f8\u05f9\7c\2\2\u05f9\u05fa\7e\2\2\u05fa\u05fb\7g\2\2\u05fb\u05fc" +
					"\7n\2\2\u05fc\u05fd\7g\2\2\u05fd\u05fe\7v\2\2\u05fe\u010c\3\2\2\2\u05ff" +
					"\u0600\7n\2\2\u0600\u0601\7d\2\2\u0601\u0602\7t\2\2\u0602\u0603\7c\2\2" +
					"\u0603\u0604\7e\2\2\u0604\u0605\7g\2\2\u0605\u0606\7n\2\2\u0606\u0607" +
					"\7g\2\2\u0607\u0608\7v\2\2\u0608\u010e\3\2\2\2\u0609\u060a\7y\2\2\u060a" +
					"\u060b\7c\2\2\u060b\u060c\7k\2\2\u060c\u060d\7u\2\2\u060d\u060e\7v\2\2" +
					"\u060e\u0110\3\2\2\2\u060f\u0610\7f\2\2\u0610\u0611\7g\2\2\u0611\u0612" +
					"\7e\2\2\u0612\u0613\7q\2\2\u0613\u0614\7\63\2\2\u0614\u0112\3\2\2\2\u0615" +
					"\u0616\7u\2\2\u0616\u0617\7v\2\2\u0617\u0618\7g\2\2\u0618\u0619\7g\2\2" +
					"\u0619\u061a\7n\2\2\u061a\u0114\3\2\2\2\u061b\u061c\7h\2\2\u061c\u061d" +
					"\7k\2\2\u061d\u061e\7p\2\2\u061e\u061f\7g\2\2\u061f\u0620\7a\2\2\u0620" +
					"\u0621\7u\2\2\u0621\u0622\7v\2\2\u0622\u0623\7g\2\2\u0623\u0624\7g\2\2" +
					"\u0624\u0625\7n\2\2\u0625\u0116\3\2\2\2\u0626\u0627\7y\2\2\u0627\u0628" +
					"\7q\2\2\u0628\u0629\7q\2\2\u0629\u062a\7f\2\2\u062a\u0118\3\2\2\2\u062b" +
					"\u062c\7e\2\2\u062c\u062d\7n\2\2\u062d\u062e\7q\2\2\u062e\u062f\7v\2\2" +
					"\u062f\u0630\7j\2\2\u0630\u011a\3\2\2\2\u0631\u0632\7n\2\2\u0632\u0633" +
					"\7g\2\2\u0633\u0634\7c\2\2\u0634\u0635\7v\2\2\u0635\u0636\7j\2\2\u0636" +
					"\u0637\7g\2\2\u0637\u0638\7t\2\2\u0638\u011c\3\2\2\2\u0639\u063a\7d\2" +
					"\2\u063a\u063b\7q\2\2\u063b\u063c\7p\2\2\u063c\u063d\7g\2\2\u063d\u011e" +
					"\3\2\2\2\u063e\u063f\7d\2\2\u063f\u0640\7t\2\2\u0640\u0641\7q\2\2\u0641" +
					"\u0642\7p\2\2\u0642\u0643\7|\2\2\u0643\u0644\7g\2\2\u0644\u0120\3\2\2" +
					"\2\u0645\u0646\7q\2\2\u0646\u0647\7t\2\2\u0647\u0648\7k\2\2\u0648\u0649" +
					"\7j\2\2\u0649\u064a\7c\2\2\u064a\u064b\7t\2\2\u064b\u064c\7w\2\2\u064c" +
					"\u064d\7m\2\2\u064d\u064e\7q\2\2\u064e\u064f\7p\2\2\u064f\u0122\3\2\2" +
					"\2\u0650\u0651\7o\2\2\u0651\u0652\7k\2\2\u0652\u0653\7v\2\2\u0653\u0654" +
					"\7j\2\2\u0654\u0655\7t\2\2\u0655\u0656\7k\2\2\u0656\u0657\7n\2\2\u0657" +
					"\u0124\3\2\2\2\u0658\u0659\7f\2\2\u0659\u065a\7c\2\2\u065a\u065b\7o\2" +
					"\2\u065b\u065c\7c\2\2\u065c\u065d\7u\2\2\u065d\u065e\7e\2\2\u065e\u065f" +
					"\7w\2\2\u065f\u0660\7u\2\2\u0660\u0126\3\2\2\2\u0661\u0662\7c\2\2\u0662" +
					"\u0663\7f\2\2\u0663\u0664\7c\2\2\u0664\u0665\7o\2\2\u0665\u0666\7c\2\2" +
					"\u0666\u0667\7p\2\2\u0667\u0668\7v\2\2\u0668\u0669\7c\2\2\u0669\u066a" +
					"\7k\2\2\u066a\u066b\7v\2\2\u066b\u066c\7g\2\2\u066c\u0128\3\2\2\2\u066d" +
					"\u066e\7d\2\2\u066e\u066f\7n\2\2\u066f\u0670\7q\2\2\u0670\u0671\7q\2\2" +
					"\u0671\u0672\7f\2\2\u0672\u0673\7a\2\2\u0673\u0674\7u\2\2\u0674\u0675" +
					"\7v\2\2\u0675\u0676\7g\2\2\u0676\u0677\7g\2\2\u0677\u0678\7n\2\2\u0678" +
					"\u012a\3\2\2\2\u0679\u067a\7r\2\2\u067a\u067b\7c\2\2\u067b\u067c\7r\2" +
					"\2\u067c\u067d\7g\2\2\u067d\u067e\7t\2\2\u067e\u012c\3\2\2\2\u067f\u0680" +
					"\7i\2\2\u0680\u0681\7q\2\2\u0681\u0682\7n\2\2\u0682\u0683\7f\2\2\u0683" +
					"\u012e\3\2\2\2\u0684\u0685\7n\2\2\u0685\u0686\7k\2\2\u0686\u0687\7s\2" +
					"\2\u0687\u0688\7w\2\2\u0688\u0689\7k\2\2\u0689\u068a\7f\2\2\u068a\u0130" +
					"\3\2\2\2\u068b\u068c\7h\2\2\u068c\u068d\7k\2\2\u068d\u068e\7u\2\2\u068e" +
					"\u068f\7j\2\2\u068f\u0132\3\2\2\2\u0690\u0691\7u\2\2\u0691\u0692\7k\2" +
					"\2\u0692\u0693\7n\2\2\u0693\u0694\7x\2\2\u0694\u0695\7g\2\2\u0695\u0696" +
					"\7t\2\2\u0696\u0134\3\2\2\2\u0697\u0698\7e\2\2\u0698\u0699\7j\2\2\u0699" +
					"\u069a\7t\2\2\u069a\u069b\7{\2\2\u069b\u069c\7u\2\2\u069c\u069d\7q\2\2" +
					"\u069d\u069e\7n\2\2\u069e\u069f\7k\2\2\u069f\u06a0\7v\2\2\u06a0\u06a1" +
					"\7g\2\2\u06a1\u0136\3\2\2\2\u06a2\u06a3\7e\2\2\u06a3\u06a4\7t\2\2\u06a4" +
					"\u06a5\7{\2\2\u06a5\u06a6\7u\2\2\u06a6\u06a7\7v\2\2\u06a7\u06a8\7c\2\2" +
					"\u06a8\u06a9\7n\2\2\u06a9\u0138\3\2\2\2\u06aa\u06ab\7j\2\2\u06ab\u06ac" +
					"\7q\2\2\u06ac\u06ad\7t\2\2\u06ad\u06ae\7p\2\2\u06ae\u013a\3\2\2\2\u06af" +
					"\u06b0\7u\2\2\u06b0\u06b1\7e\2\2\u06b1\u06b2\7c\2\2\u06b2\u06b3\7n\2\2" +
					"\u06b3\u06b4\7g\2\2\u06b4\u06b5\7a\2\2\u06b5\u06b6\7q\2\2\u06b6\u06b7" +
					"\7h\2\2\u06b7\u06b8\7a\2\2\u06b8\u06b9\7f\2\2\u06b9\u06ba\7t\2\2\u06ba" +
					"\u06bb\7c\2\2\u06bb\u06bc\7i\2\2\u06bc\u06bd\7q\2\2\u06bd\u06be\7p\2\2" +
					"\u06be\u013c\3\2\2\2\u06bf\u06c0\7e\2\2\u06c0\u06c1\7q\2\2\u06c1\u06c2" +
					"\7v\2\2\u06c2\u06c3\7v\2\2\u06c3\u06c4\7q\2\2\u06c4\u06c5\7p\2\2\u06c5" +
					"\u013e\3\2\2\2\u06c6\u06c7\7f\2\2\u06c7\u06c8\7{\2\2\u06c8\u06c9\7g\2" +
					"\2\u06c9\u06ca\7u\2\2\u06ca\u06cb\7v\2\2\u06cb\u06cc\7w\2\2\u06cc\u06cd" +
					"\7h\2\2\u06cd\u06ce\7h\2\2\u06ce\u0140\3\2\2\2\u06cf\u06d0\7e\2\2\u06d0" +
					"\u06d1\7q\2\2\u06d1\u06d2\7d\2\2\u06d2\u06d3\7y\2\2\u06d3\u06d4\7g\2\2" +
					"\u06d4\u06d5\7d\2\2\u06d5\u0142\3\2\2\2\u06d6\u06d7\7t\2\2\u06d7\u06d8" +
					"\7w\2\2\u06d8\u06d9\7p\2\2\u06d9\u06da\7g\2\2\u06da\u06db\7a\2\2\u06db" +
					"\u06dc\7z\2\2\u06dc\u06dd\7r\2\2\u06dd\u0144\3\2\2\2\u06de\u06df\7t\2" +
					"\2\u06df\u06e0\7w\2\2\u06e0\u06e1\7p\2\2\u06e1\u06e2\7g\2\2\u06e2\u06e3" +
					"\7a\2\2\u06e3\u06e4\7u\2\2\u06e4\u06e5\7r\2\2\u06e5\u0146\3\2\2\2\u06e6" +
					"\u06e7\7t\2\2\u06e7\u06e8\7w\2\2\u06e8\u06e9\7p\2\2\u06e9\u06ea\7g\2\2" +
					"\u06ea\u06eb\7a\2\2\u06eb\u06ec\7t\2\2\u06ec\u06ed\7g\2\2\u06ed\u06ee" +
					"\7o\2\2\u06ee\u06ef\7q\2\2\u06ef\u06f0\7x\2\2\u06f0\u06f1\7g\2\2\u06f1" +
					"\u06f2\7a\2\2\u06f2\u06f3\7r\2\2\u06f3\u06f4\7g\2\2\u06f4\u06f5\7p\2\2" +
					"\u06f5\u06f6\7c\2\2\u06f6\u06f7\7n\2\2\u06f7\u06f8\7v\2\2\u06f8\u06f9" +
					"\7{\2\2\u06f9\u0148\3\2\2\2\u06fa\u06fc\t\6\2\2\u06fb\u06fa\3\2\2\2\u06fc" +
					"\u06fd\3\2\2\2\u06fd\u06fb\3\2\2\2\u06fd\u06fe\3\2\2\2\u06fe\u0702\3\2" +
					"\2\2\u06ff\u0701\t\7\2\2\u0700\u06ff\3\2\2\2\u0701\u0704\3\2\2\2\u0702" +
					"\u0700\3\2\2\2\u0702\u0703\3\2\2\2\u0703\u014a\3\2\2\2\u0704\u0702\3\2" +
					"\2\2\u0705\u0707\t\b\2\2\u0706\u0705\3\2\2\2\u0707\u0708\3\2\2\2\u0708" +
					"\u0706\3\2\2\2\u0708\u0709\3\2\2\2\u0709\u070a\3\2\2\2\u070a\u070b\b\u00a6" +
					"\2\2\u070b\u014c\3\2\2\2\u070c\u070d\7\61\2\2\u070d\u070e\7\61\2\2\u070e" +
					"\u0712\3\2\2\2\u070f\u0711\n\t\2\2\u0710\u070f\3\2\2\2\u0711\u0714\3\2" +
					"\2\2\u0712\u0710\3\2\2\2\u0712\u0713\3\2\2\2\u0713\u0716\3\2\2\2\u0714" +
					"\u0712\3\2\2\2\u0715\u0717\7\17\2\2\u0716\u0715\3\2\2\2\u0716\u0717\3" +
					"\2\2\2\u0717\u0718\3\2\2\2\u0718\u0719\7\f\2\2\u0719\u071a\3\2\2\2\u071a" +
					"\u071b\b\u00a7\2\2\u071b\u014e\3\2\2\2\u071c\u071d\7\61\2\2\u071d\u071e" +
					"\7,\2\2\u071e\u0722\3\2\2\2\u071f\u0721\13\2\2\2\u0720\u071f\3\2\2\2\u0721" +
					"\u0724\3\2\2\2\u0722\u0723\3\2\2\2\u0722\u0720\3\2\2\2\u0723\u0725\3\2" +
					"\2\2\u0724\u0722\3\2\2\2\u0725\u0726\7,\2\2\u0726\u0727\7\61\2\2\u0727" +
					"\u0728\3\2\2\2\u0728\u0729\b\u00a8\2\2\u0729\u0150\3\2\2\2\r\2\u0360\u0369" +
					"\u0392\u0395\u06fd\u0702\u0708\u0712\u0716\u0722\3\b\2\2";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}