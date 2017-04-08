package org.l2junity.gameserver.data.txt.model.constants;

import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Camelion
 * @since 07.01.16
 */
public enum AttributeType {
	// TODO: 07.01.16 correct values
	NONE(-2),
	FIRE(0),
	WATER(1),
	WIND(2),
	EARTH(3),
	HOLY(4),
	UNHOLY(5);

	private final int id;

	AttributeType(int id) {
		this.id = id;
	}

	public DoubleStat getAttackStat() {
		switch (this) {
			case NONE:
				return null;
			case FIRE:
				return DoubleStat.FIRE_POWER;
			case WATER:
				return DoubleStat.WATER_POWER;
			case WIND:
				return DoubleStat.WIND_POWER;
			case EARTH:
				return DoubleStat.EARTH_POWER;
			case HOLY:
				return DoubleStat.HOLY_POWER;
			case UNHOLY:
				return DoubleStat.DARK_POWER;
		}
		return null;
	}
}
