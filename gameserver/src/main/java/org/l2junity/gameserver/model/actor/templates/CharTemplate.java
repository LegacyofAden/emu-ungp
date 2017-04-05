/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.actor.templates;

import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.events.ListenersContainer;
import org.l2junity.gameserver.model.items.type.WeaponType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Character template.
 *
 * @author Zoey76
 */
public class CharTemplate extends ListenersContainer {
	// BaseStats
	private WeaponType _baseAttackType;

	/**
	 * For client info use {@link #_fCollisionRadius}
	 */
	private int _collisionRadius;
	/**
	 * For client info use {@link #_fCollisionHeight}
	 */
	private int _collisionHeight;

	private double _fCollisionRadius;
	private double _fCollisionHeight;

	protected final Map<DoubleStat, Double> _baseValues = new EnumMap<>(DoubleStat.class);

	/**
	 * The creature's race.
	 */
	private Race _race;

	public CharTemplate(StatsSet set) {
		set(set);
	}

	public void set(StatsSet set) {
		// Base stats
		_baseValues.put(DoubleStat.STAT_STR, set.getDouble("baseSTR", 0));
		_baseValues.put(DoubleStat.STAT_CON, set.getDouble("baseCON", 0));
		_baseValues.put(DoubleStat.STAT_DEX, set.getDouble("baseDEX", 0));
		_baseValues.put(DoubleStat.STAT_INT, set.getDouble("baseINT", 0));
		_baseValues.put(DoubleStat.STAT_WIT, set.getDouble("baseWIT", 0));
		_baseValues.put(DoubleStat.STAT_MEN, set.getDouble("baseMEN", 0));
		_baseValues.put(DoubleStat.STAT_LUC, set.getDouble("baseLUC", 0));
		_baseValues.put(DoubleStat.STAT_CHA, set.getDouble("baseCHA", 0));

		// Max HP/MP/CP
		_baseValues.put(DoubleStat.MAX_HP, set.getDouble("baseHpMax", 0));
		_baseValues.put(DoubleStat.MAX_MP, set.getDouble("baseMpMax", 0));
		_baseValues.put(DoubleStat.MAX_CP, set.getDouble("baseCpMax", 0));

		// Regenerate HP/MP/CP
		_baseValues.put(DoubleStat.REGENERATE_HP_RATE, set.getDouble("baseHpReg", 0));
		_baseValues.put(DoubleStat.REGENERATE_MP_RATE, set.getDouble("baseMpReg", 0));
		_baseValues.put(DoubleStat.REGENERATE_CP_RATE, set.getDouble("baseCpReg", 0));

		// Attack and Defense
		_baseValues.put(DoubleStat.PHYSICAL_ATTACK, set.getDouble("basePAtk", 0));
		_baseValues.put(DoubleStat.MAGIC_ATTACK, set.getDouble("baseMAtk", 0));
		_baseValues.put(DoubleStat.PHYSICAL_DEFENCE, set.getDouble("basePDef", 0));
		_baseValues.put(DoubleStat.MAGICAL_DEFENCE, set.getDouble("baseMDef", 0));

		// Attack speed
		_baseValues.put(DoubleStat.PHYSICAL_ATTACK_SPEED, set.getDouble("basePAtkSpd", 300));
		_baseValues.put(DoubleStat.MAGIC_ATTACK_SPEED, set.getDouble("baseMAtkSpd", 333));

		// Misc
		_baseValues.put(DoubleStat.SHIELD_DEFENCE, set.getDouble("baseShldDef", 0));
		_baseValues.put(DoubleStat.PHYSICAL_ATTACK_RANGE, set.getDouble("baseAtkRange", 40));
		_baseValues.put(DoubleStat.RANDOM_DAMAGE, set.getDouble("baseRndDam", 0));

		// Shield and critical rates
		_baseValues.put(DoubleStat.SHIELD_DEFENCE_RATE, set.getDouble("baseShldRate", 0));
		_baseValues.put(DoubleStat.CRITICAL_RATE, set.getDouble("baseCritRate", 4));
		_baseValues.put(DoubleStat.MAGIC_CRITICAL_RATE, set.getDouble("baseMCritRate", 5));

		// Breath under water
		_baseValues.put(DoubleStat.BREATH, set.getDouble("baseBreath", 100));

		// Elemental Attributes
		// Attack
		_baseValues.put(DoubleStat.FIRE_POWER, set.getDouble("baseFire", 0));
		_baseValues.put(DoubleStat.WIND_POWER, set.getDouble("baseWind", 0));
		_baseValues.put(DoubleStat.WATER_POWER, set.getDouble("baseWater", 0));
		_baseValues.put(DoubleStat.EARTH_POWER, set.getDouble("baseEarth", 0));
		_baseValues.put(DoubleStat.HOLY_POWER, set.getDouble("baseHoly", 0));
		_baseValues.put(DoubleStat.DARK_POWER, set.getDouble("baseDark", 0));

		// Defense
		_baseValues.put(DoubleStat.FIRE_RES, set.getDouble("baseFireRes", 0));
		_baseValues.put(DoubleStat.WIND_RES, set.getDouble("baseWindRes", 0));
		_baseValues.put(DoubleStat.WATER_RES, set.getDouble("baseWaterRes", 0));
		_baseValues.put(DoubleStat.EARTH_RES, set.getDouble("baseEarthRes", 0));
		_baseValues.put(DoubleStat.HOLY_RES, set.getDouble("baseHolyRes", 0));
		_baseValues.put(DoubleStat.DARK_RES, set.getDouble("baseDarkRes", 0));
		_baseValues.put(DoubleStat.BASE_ATTRIBUTE_RES, set.getDouble("baseElementRes", 0));

		// Geometry
		_fCollisionHeight = set.getDouble("collision_height", 0);
		_fCollisionRadius = set.getDouble("collision_radius", 0);
		_collisionRadius = (int) _fCollisionRadius;
		_collisionHeight = (int) _fCollisionHeight;

		// Speed
		_baseValues.put(DoubleStat.RUN_SPEED, set.getDouble("baseRunSpd", 120));
		_baseValues.put(DoubleStat.WALK_SPEED, set.getDouble("baseWalkSpd", 50));

		// Swimming
		_baseValues.put(DoubleStat.SWIM_RUN_SPEED, set.getDouble("baseSwimRunSpd", 120));
		_baseValues.put(DoubleStat.SWIM_WALK_SPEED, set.getDouble("baseSwimWalkSpd", 50));

		// Flying
		_baseValues.put(DoubleStat.FLY_RUN_SPEED, set.getDouble("baseFlyRunSpd", 120));
		_baseValues.put(DoubleStat.FLY_WALK_SPEED, set.getDouble("baseFlyWalkSpd", 50));

		// Attack type
		_baseAttackType = set.getEnum("baseAtkType", WeaponType.class, WeaponType.FIST);

		// Basic property
		_baseValues.put(DoubleStat.ABNORMAL_RESIST_PHYSICAL, set.getDouble("physicalAbnormalResist", 10));
		_baseValues.put(DoubleStat.ABNORMAL_RESIST_MAGICAL, set.getDouble("magicAbnormalResist", 10));

		// Accuracy / Evasion (Used for NPC only)
		_baseValues.put(DoubleStat.ACCURACY_COMBAT, set.getDouble("accuracy", 0));
		_baseValues.put(DoubleStat.EVASION_RATE, set.getDouble("evasion", 0));
	}

	/**
	 * @return the baseSTR
	 */
	public int getBaseSTR() {
		return _baseValues.getOrDefault(DoubleStat.STAT_STR, 0d).intValue();
	}

	/**
	 * @return the baseCON
	 */
	public int getBaseCON() {
		return _baseValues.getOrDefault(DoubleStat.STAT_CON, 0d).intValue();
	}

	/**
	 * @return the baseDEX
	 */
	public int getBaseDEX() {
		return _baseValues.getOrDefault(DoubleStat.STAT_DEX, 0d).intValue();
	}

	/**
	 * @return the baseINT
	 */
	public int getBaseINT() {
		return _baseValues.getOrDefault(DoubleStat.STAT_INT, 0d).intValue();
	}

	/**
	 * @return the baseWIT
	 */
	public int getBaseWIT() {
		return _baseValues.getOrDefault(DoubleStat.STAT_WIT, 0d).intValue();
	}

	/**
	 * @return the baseMEN
	 */
	public int getBaseMEN() {
		return _baseValues.getOrDefault(DoubleStat.STAT_MEN, 0d).intValue();
	}

	/**
	 * @return the baseLUC
	 */
	public int getBaseLUC() {
		return _baseValues.getOrDefault(DoubleStat.STAT_LUC, 0d).intValue();
	}

	/**
	 * @return the baseCHA
	 */
	public int getBaseCHA() {
		return _baseValues.getOrDefault(DoubleStat.STAT_CHA, 0d).intValue();
	}

	/**
	 * @return the baseHpMax
	 */
	public float getBaseHpMax() {
		return _baseValues.getOrDefault(DoubleStat.MAX_HP, 0d).floatValue();
	}

	/**
	 * @return the baseCpMax
	 */
	public float getBaseCpMax() {
		return _baseValues.getOrDefault(DoubleStat.MAX_CP, 0d).floatValue();
	}

	/**
	 * @return the baseMpMax
	 */
	public float getBaseMpMax() {
		return _baseValues.getOrDefault(DoubleStat.MAX_MP, 0d).floatValue();
	}

	/**
	 * @return the baseHpReg
	 */
	public float getBaseHpReg() {
		return _baseValues.getOrDefault(DoubleStat.REGENERATE_HP_RATE, 0d).floatValue();
	}

	/**
	 * @return the baseMpReg
	 */
	public float getBaseMpReg() {
		return _baseValues.getOrDefault(DoubleStat.REGENERATE_MP_RATE, 0d).floatValue();
	}

	/**
	 * @return the _baseFire
	 */
	public int getBaseFire() {
		return _baseValues.getOrDefault(DoubleStat.FIRE_POWER, 0d).intValue();
	}

	/**
	 * @return the _baseWind
	 */
	public int getBaseWind() {
		return _baseValues.getOrDefault(DoubleStat.WIND_POWER, 0d).intValue();
	}

	/**
	 * @return the _baseWater
	 */
	public int getBaseWater() {
		return _baseValues.getOrDefault(DoubleStat.WATER_POWER, 0d).intValue();
	}

	/**
	 * @return the _baseEarth
	 */
	public int getBaseEarth() {
		return _baseValues.getOrDefault(DoubleStat.EARTH_POWER, 0d).intValue();
	}

	/**
	 * @return the _baseHoly
	 */
	public int getBaseHoly() {
		return _baseValues.getOrDefault(DoubleStat.HOLY_POWER, 0d).intValue();
	}

	/**
	 * @return the _baseDark
	 */
	public int getBaseDark() {
		return _baseValues.getOrDefault(DoubleStat.DARK_POWER, 0d).intValue();
	}

	/**
	 * @return the _baseFireRes
	 */
	public double getBaseFireRes() {
		return _baseValues.getOrDefault(DoubleStat.FIRE_RES, 0d);
	}

	/**
	 * @return the _baseWindRes
	 */
	public double getBaseWindRes() {
		return _baseValues.getOrDefault(DoubleStat.WIND_RES, 0d);
	}

	/**
	 * @return the _baseWaterRes
	 */
	public double getBaseWaterRes() {
		return _baseValues.getOrDefault(DoubleStat.WATER_RES, 0d);
	}

	/**
	 * @return the _baseEarthRes
	 */
	public double getBaseEarthRes() {
		return _baseValues.getOrDefault(DoubleStat.EARTH_RES, 0d);
	}

	/**
	 * @return the _baseHolyRes
	 */
	public double getBaseHolyRes() {
		return _baseValues.getOrDefault(DoubleStat.HOLY_RES, 0d);
	}

	/**
	 * @return the _baseDarkRes
	 */
	public double getBaseDarkRes() {
		return _baseValues.getOrDefault(DoubleStat.DARK_RES, 0d);
	}

	/**
	 * @return the _baseElementRes
	 */
	public double getBaseElementRes() {
		return _baseValues.getOrDefault(DoubleStat.BASE_ATTRIBUTE_RES, 0d);
	}

	/**
	 * @return the basePAtk
	 */
	public int getBasePAtk() {
		return _baseValues.getOrDefault(DoubleStat.PHYSICAL_ATTACK, 0d).intValue();
	}

	/**
	 * @return the baseMAtk
	 */
	public int getBaseMAtk() {
		return _baseValues.getOrDefault(DoubleStat.MAGIC_ATTACK, 0d).intValue();
	}

	/**
	 * @return the basePDef
	 */
	public int getBasePDef() {
		return _baseValues.getOrDefault(DoubleStat.PHYSICAL_DEFENCE, 0d).intValue();
	}

	/**
	 * @return the baseMDef
	 */
	public int getBaseMDef() {
		return _baseValues.getOrDefault(DoubleStat.MAGICAL_DEFENCE, 0d).intValue();
	}

	/**
	 * @return the basePAtkSpd
	 */
	public int getBasePAtkSpd() {
		return _baseValues.getOrDefault(DoubleStat.PHYSICAL_ATTACK_SPEED, 0d).intValue();
	}

	/**
	 * @return the baseMAtkSpd
	 */
	public int getBaseMAtkSpd() {
		return _baseValues.getOrDefault(DoubleStat.MAGIC_ATTACK_SPEED, 0d).intValue();
	}

	/**
	 * @return the random damage
	 */
	public int getRandomDamage() {
		return _baseValues.getOrDefault(DoubleStat.RANDOM_DAMAGE, 0d).intValue();
	}

	/**
	 * @return the baseShldDef
	 */
	public int getBaseShldDef() {
		return _baseValues.getOrDefault(DoubleStat.SHIELD_DEFENCE, 0d).intValue();
	}

	/**
	 * @return the baseShldRate
	 */
	public int getBaseShldRate() {
		return _baseValues.getOrDefault(DoubleStat.SHIELD_DEFENCE_RATE, 0d).intValue();
	}

	/**
	 * @return the baseCritRate
	 */
	public int getBaseCritRate() {
		return _baseValues.getOrDefault(DoubleStat.CRITICAL_RATE, 0d).intValue();
	}

	/**
	 * @return the baseMCritRate
	 */
	public int getBaseMCritRate() {
		return _baseValues.getOrDefault(DoubleStat.MAGIC_CRITICAL_RATE, 0d).intValue();
	}

	/**
	 * @return the baseBreath
	 */
	public int getBaseBreath() {
		return _baseValues.getOrDefault(DoubleStat.BREATH, 0d).intValue();
	}

	/**
	 * @return base abnormal resist by basic property type.
	 */
	public int getBaseAbnormalResistPhysical() {
		return _baseValues.getOrDefault(DoubleStat.ABNORMAL_RESIST_PHYSICAL, 0d).intValue();
	}

	/**
	 * @return base abnormal resist by basic property type.
	 */
	public int getBaseAbnormalResistMagical() {
		return _baseValues.getOrDefault(DoubleStat.ABNORMAL_RESIST_MAGICAL, 0d).intValue();
	}

	/**
	 * @return the collisionRadius
	 */
	public int getCollisionRadius() {
		return _collisionRadius;
	}

	/**
	 * @return the collisionHeight
	 */
	public int getCollisionHeight() {
		return _collisionHeight;
	}

	/**
	 * @return the fCollisionRadius
	 */
	public double getfCollisionRadius() {
		return _fCollisionRadius;
	}

	/**
	 * @return the fCollisionHeight
	 */
	public double getfCollisionHeight() {
		return _fCollisionHeight;
	}

	/**
	 * @return the base attack type (Sword, Fist, Blunt, etc..)
	 */
	public WeaponType getBaseAttackType() {
		return _baseAttackType;
	}

	/**
	 * Sets base attack type.
	 *
	 * @param type
	 */
	public void setBaseAttackType(WeaponType type) {
		_baseAttackType = type;
	}

	/**
	 * @return the baseAtkRange
	 */
	public int getBaseAttackRange() {
		return _baseValues.getOrDefault(DoubleStat.PHYSICAL_ATTACK_RANGE, 0d).intValue();
	}

	/**
	 * Overridden in NpcTemplate
	 *
	 * @return the characters skills
	 */
	public Map<Integer, Skill> getSkills() {
		return Collections.emptyMap();
	}

	/**
	 * Gets the craeture's race.
	 *
	 * @return the race
	 */
	public Race getRace() {
		return _race;
	}

	/**
	 * Sets the creature's race.
	 *
	 * @param race the race
	 */
	public void setRace(Race race) {
		_race = race;
	}

	/**
	 * @param stat
	 * @param defaultValue
	 * @return
	 */
	public double getBaseValue(DoubleStat stat, double defaultValue) {
		return _baseValues.getOrDefault(stat, defaultValue);
	}
}
