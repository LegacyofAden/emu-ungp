package org.l2junity.gameserver.data.txt.model.setting;

import org.l2junity.gameserver.data.txt.model.constants.CharacterClass;
import org.l2junity.gameserver.data.txt.model.constants.CharacterRace;

import java.util.List;

/**
 * @author Camelion
 * @since 18.12.15
 */
public class CharacterStatTemplate implements Cloneable {
	public static final int INT = 0;
	public static final int STR = 1;
	public static final int CON = 2;
	public static final int MEN = 3;
	public static final int DEX = 4;
	public static final int WIT = 5;

	private CharacterRace race;

	private CharacterClass class_;

	// INT, STR, CON, MEN, DEX, WIT
	private List<Integer> stats;

	public CharacterStatTemplate(final CharacterRace race, final CharacterClass class_, final List<Integer> stats) {
		this.race = race;
		this.class_ = class_;
		this.stats = stats;
	}

	public final int getForType(final int type) {
		assert type >= 0 && type < stats.size() : "Unknown stat type: " + type + " possible 0-5, INT, STR, CON, MEN, DEX, WIT";

		return stats.get(type);
	}

	@Override
	public final Object clone() {
		try {
			return super.clone();
		} catch (final CloneNotSupportedException e) {
			// this shouldn't happen, since we are Cloneable
			throw new InternalError(e);
		}
	}

	public final CharacterRace getRace() {
		return race;
	}

	public final CharacterClass getClass_() {
		return class_;
	}
}
