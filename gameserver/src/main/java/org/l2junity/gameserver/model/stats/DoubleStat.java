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
package org.l2junity.gameserver.model.stats;

import org.l2junity.commons.util.MathUtil;
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.stats.finalizers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.function.DoubleBinaryOperator;

/**
 * Enum of basic stats.
 *
 * @author mkizub, UnAfraid, NosBit, Sdw
 */
public enum DoubleStat {
	// HP, MP & CP
	MAX_HP("maxHp", new MaxHpFinalizer()),
	MAX_MP("maxMp", new MaxMpFinalizer()),
	MAX_MP_ADD("maxMpAdd"),
	MAX_CP("maxCp", new MaxCpFinalizer()),
	MAX_RECOVERABLE_HP("maxRecoverableHp"), // The maximum HP that is able to be recovered trough heals
	MAX_RECOVERABLE_MP("maxRecoverableMp"),
	MAX_RECOVERABLE_CP("maxRecoverableCp"),
	REGENERATE_HP_RATE("regHp", new RegenHPFinalizer()),
	REGENERATE_CP_RATE("regCp", new RegenCPFinalizer()),
	REGENERATE_MP_RATE("regMp", new RegenMPFinalizer()),
	REGENERATE_MP_RATE_ADD("regMpAdd"),
	MANA_CHARGE("manaCharge"),
	HEAL_EFFECT("healEffect"),

	// ATTACK & DEFENCE
	PHYSICAL_DEFENCE("pDef", new PDefenseFinalizer()),
	MAGICAL_DEFENCE("mDef", new MDefenseFinalizer()),
	PHYSICAL_ATTACK("pAtk", new PAttackFinalizer()),
	MAGIC_ATTACK("mAtk", new MAttackFinalizer()),
	MAGIC_ATTACK_ADD("mAtkAdd"),
	PHYSICAL_ATTACK_SPEED("pAtkSpd", new PAttackSpeedFinalizer()),
	MAGIC_ATTACK_SPEED("mAtkSpd", new MAttackSpeedFinalizer()), // Magic Skill Casting Time Rate
	ATK_REUSE("atkReuse"), // Bows Hits Reuse Rate
	SHIELD_DEFENCE("sDef", new ShieldDefenceFinalizer()),
	CRITICAL_DAMAGE("cAtk"),
	CRITICAL_DAMAGE_ADD("cAtkAdd"), // this is another type for special critical damage mods - vicious stance, critical power and critical damage SA
	HATE_ATTACK("attackHate"),

	// PVP BONUS
	PVP_PHYSICAL_ATTACK_DAMAGE("pvpPhysDmg"),
	PVP_MAGICAL_SKILL_DAMAGE("pvpMagicalDmg"),
	PVP_PHYSICAL_SKILL_DAMAGE("pvpPhysSkillsDmg"),
	PVP_PHYSICAL_ATTACK_DEFENCE("pvpPhysDef"),
	PVP_MAGICAL_SKILL_DEFENCE("pvpMagicalDef"),
	PVP_PHYSICAL_SKILL_DEFENCE("pvpPhysSkillsDef"),

	// PVE BONUS
	PVE_PHYSICAL_ATTACK_DAMAGE("pvePhysDmg"),
	PVE_PHYSICAL_SKILL_DAMAGE("pvePhysSkillsDmg"),
	PVE_MAGICAL_SKILL_DAMAGE("pveMagicalDmg"),
	PVE_PHYSICAL_ATTACK_DEFENCE("pvePhysDef"),
	PVE_PHYSICAL_SKILL_DEFENCE("pvePhysSkillsDef"),
	PVE_MAGICAL_SKILL_DEFENCE("pveMagicalDef"),
	PVE_RAID_PHYSICAL_ATTACK_DAMAGE("pveRaidPhysDmg"),
	PVE_RAID_PHYSICAL_SKILL_DAMAGE("pveRaidPhysSkillsDmg"),
	PVE_RAID_MAGICAL_SKILL_DAMAGE("pveRaidMagicalDmg"),
	PVE_RAID_PHYSICAL_ATTACK_DEFENCE("pveRaidPhysDef"),
	PVE_RAID_PHYSICAL_SKILL_DEFENCE("pveRaidPhysSkillsDef"),
	PVE_RAID_MAGICAL_SKILL_DEFENCE("pveRaidMagicalDef"),

	// FIXED BONUS
	PVP_DAMAGE_TAKEN("pvpDamageTaken"),
	PVE_DAMAGE_TAKEN("pveDamageTaken"),

	// ATTACK & DEFENCE RATES
	MAGIC_CRITICAL_DAMAGE("mCritPower"),
	PHYSICAL_SKILL_POWER("physicalSkillPower"), // Adding skill power (not multipliers) results in points added directly to final value unmodified by defence, traits, elements, criticals etc.
	// Even when damage is 0 due to general trait immune multiplier, added skill power is active and clearly visible (damage not being 0 but at the value of added skill power).
	MAGICAL_SKILL_POWER("magicalSkillPower"),
	CRITICAL_DAMAGE_SKILL("cAtkSkill"),
	CRITICAL_DAMAGE_SKILL_ADD("cAtkSkillAdd"),
	MAGIC_CRITICAL_DAMAGE_ADD("mCritPowerAdd"),
	SHIELD_DEFENCE_RATE("rShld", new ShieldDefenceRateFinalizer()),
	CRITICAL_RATE("rCrit", new PCriticalRateFinalizer(), MathUtil::add, MathUtil::add, 0, 1d),
	CRITICAL_RATE_SKILL("rCritSkill", DoubleStat::defaultValue, MathUtil::add, MathUtil::add, 0, 1d),
	MAGIC_CRITICAL_RATE("mCritRate", new MCritRateFinalizer()),
	BLOW_RATE("blowRate"),
	DEFENCE_CRITICAL_RATE("defCritRate"),
	DEFENCE_CRITICAL_RATE_ADD("defCritRateAdd"),
	DEFENCE_MAGIC_CRITICAL_RATE("defMCritRate"),
	DEFENCE_MAGIC_CRITICAL_RATE_ADD("defMCritRateAdd"),
	DEFENCE_CRITICAL_DAMAGE("defCritDamage"),
	DEFENCE_MAGIC_CRITICAL_DAMAGE("defMCritDamage"),
	DEFENCE_MAGIC_CRITICAL_DAMAGE_ADD("defMCritDamageAdd"),
	DEFENCE_CRITICAL_DAMAGE_ADD("defCritDamageAdd"), // Resistance to critical damage in value (Example: +100 will be 100 more critical damage, NOT 100% more).
	DEFENCE_CRITICAL_DAMAGE_SKILL("defCAtkSkill"),
	DEFENCE_CRITICAL_DAMAGE_SKILL_ADD("defCAtkSkillAdd"),
	INSTANT_KILL_RESIST("instantKillResist"),
	EXPSP_RATE("rExp"),
	BONUS_EXP("bonusExp"),
	BONUS_SP("bonusSp"),
	BONUS_DROP("bonusDrop"),
	BONUS_SPOIL("bonusSpoil"),
	BONUS_ADENA("bonusAdena"),
	ATTACK_CANCEL("cancel"),

	// ACCURACY & RANGE
	ACCURACY_COMBAT("accCombat", new PAccuracyFinalizer()),
	ACCURACY_MAGIC("accMagic", new MAccuracyFinalizer()),
	EVASION_RATE("rEvas", new PEvasionRateFinalizer()),
	MAGIC_EVASION_RATE("mEvas", new MEvasionRateFinalizer()),
	PHYSICAL_ATTACK_RANGE("pAtkRange", new PRangeFinalizer()),
	MAGIC_ATTACK_RANGE("mAtkRange"),
	ATTACK_COUNT_MAX("atkCountMax"),
	// Run speed, walk & escape speed are calculated proportionally, magic speed is a buff
	MOVE_SPEED("moveSpeed"),
	RUN_SPEED("runSpd", new SpeedFinalizer()),
	WALK_SPEED("walkSpd", new SpeedFinalizer()),
	SWIM_RUN_SPEED("fastSwimSpd", new SpeedFinalizer()),
	SWIM_WALK_SPEED("slowSimSpd", new SpeedFinalizer()),
	FLY_RUN_SPEED("fastFlySpd", new SpeedFinalizer()),
	FLY_WALK_SPEED("slowFlySpd", new SpeedFinalizer()),

	// BASIC STATS
	STAT_STR("STR", new BaseStatsFinalizer()),
	STAT_CON("CON", new BaseStatsFinalizer()),
	STAT_DEX("DEX", new BaseStatsFinalizer()),
	STAT_INT("INT", new BaseStatsFinalizer()),
	STAT_WIT("WIT", new BaseStatsFinalizer()),
	STAT_MEN("MEN", new BaseStatsFinalizer()),
	STAT_LUC("LUC", new BaseStatsFinalizer()),
	STAT_CHA("CHA", new BaseStatsFinalizer()),

	// Special stats, share one slot in Calculator

	// VARIOUS
	BREATH("breath"),
	FALL("fall"),

	// VULNERABILITIES
	DAMAGE_ZONE_VULN("damageZoneVuln"),
	RESIST_DISPEL_BUFF("cancelVuln"), // Resistance for cancel type skills
	RESIST_ABNORMAL_DEBUFF("debuffVuln"),

	// RESISTANCES
	FIRE_RES("fireRes", new AttributeFinalizer(AttributeType.FIRE, false)),
	WIND_RES("windRes", new AttributeFinalizer(AttributeType.WIND, false)),
	WATER_RES("waterRes", new AttributeFinalizer(AttributeType.WATER, false)),
	EARTH_RES("earthRes", new AttributeFinalizer(AttributeType.EARTH, false)),
	HOLY_RES("holyRes", new AttributeFinalizer(AttributeType.HOLY, false)),
	DARK_RES("darkRes", new AttributeFinalizer(AttributeType.DARK, false)),
	BASE_ATTRIBUTE_RES("baseAttrRes"),
	MAGIC_SUCCESS_RES("magicSuccRes"),
	// BUFF_IMMUNITY("buffImmunity"), //TODO: Implement me
	ABNORMAL_RESIST_PHYSICAL("abnormalResPhysical"),
	ABNORMAL_RESIST_MAGICAL("abnormalResMagical"),

	// ELEMENT POWER
	FIRE_POWER("firePower", new AttributeFinalizer(AttributeType.FIRE, true)),
	WATER_POWER("waterPower", new AttributeFinalizer(AttributeType.WATER, true)),
	WIND_POWER("windPower", new AttributeFinalizer(AttributeType.WIND, true)),
	EARTH_POWER("earthPower", new AttributeFinalizer(AttributeType.EARTH, true)),
	HOLY_POWER("holyPower", new AttributeFinalizer(AttributeType.HOLY, true)),
	DARK_POWER("darkPower", new AttributeFinalizer(AttributeType.DARK, true)),

	// PROFICIENCY
	REFLECT_DAMAGE_PERCENT("reflectDam"),
	REFLECT_DAMAGE_PERCENT_DEFENSE("reflectDamDef"),
	REFLECT_SKILL_MAGIC("reflectSkillMagic"), // Need rework
	REFLECT_SKILL_PHYSIC("reflectSkillPhysic"), // Need rework
	VENGEANCE_SKILL_MAGIC_DAMAGE("vengeanceMdam"),
	VENGEANCE_SKILL_PHYSICAL_DAMAGE("vengeancePdam"),
	ABSORB_DAMAGE_PERCENT("absorbDam"),
	ABSORB_DAMAGE_CHANCE("absorbDamChance", new VampiricChanceFinalizer()),
	ABSORB_DAMAGE_DEFENCE("absorbDamDefence"),
	TRANSFER_DAMAGE_SUMMON_PERCENT("transDam"),
	MANA_SHIELD_PERCENT("manaShield"),
	TRANSFER_DAMAGE_TO_PLAYER("transDamToPlayer"),
	ABSORB_MANA_DAMAGE_PERCENT("absorbDamMana"),

	WEIGHT_LIMIT("weightLimit"),
	WEIGHT_PENALTY("weightPenalty"),

	// ExSkill
	INVENTORY_NORMAL("inventoryLimit"),
	STORAGE_PRIVATE("whLimit"),
	TRADE_SELL("PrivateSellLimit"),
	TRADE_BUY("PrivateBuyLimit"),
	RECIPE_DWARVEN("DwarfRecipeLimit"),
	RECIPE_COMMON("CommonRecipeLimit"),

	// Skill mastery
	SKILL_CRITICAL("skillCritical"),
	SKILL_CRITICAL_PROBABILITY("skillCriticalProbability"),

	// Vitality
	VITALITY_POINTS_RATE("vitalityPointsRate"),
	VITALITY_EXP_RATE("vitalityExpRate"),

	// Souls
	MAX_SOULS("maxSouls"),

	REDUCE_EXP_LOST_BY_PVP("reduceExpLostByPvp"),
	REDUCE_EXP_LOST_BY_MOB("reduceExpLostByMob"),
	REDUCE_EXP_LOST_BY_RAID("reduceExpLostByRaid"),

	REDUCE_DEATH_PENALTY_BY_PVP("reduceDeathPenaltyByPvp"),
	REDUCE_DEATH_PENALTY_BY_MOB("reduceDeathPenaltyByMob"),
	REDUCE_DEATH_PENALTY_BY_RAID("reduceDeathPenaltyByRaid"),

	// Brooches
	BROOCH_JEWELS("broochJewels"),

	// Summon Points
	MAX_SUMMON_POINTS("summonPoints"),

	// Cubic Count
	MAX_CUBIC("cubicCount"),

	// The maximum allowed range to be damaged/debuffed from.
	SPHERIC_BARRIER_RANGE("sphericBarrier"),

	// Blocks given amount of debuffs.
	DEBUFF_BLOCK("debuffBlock"),

	// Affects the random weapon damage.
	RANDOM_DAMAGE("randomDamage", new RandomDamageFinalizer()),

	// Affects the random weapon damage.
	DAMAGE_LIMIT("damageCap"),

	// Maximun momentum one can charge
	MAX_MOMENTUM("maxMomentum"),

	// Which base stat ordinal should alter skill critical formula.
	STAT_BONUS_SKILL_CRITICAL("statSkillCritical"),
	STAT_BONUS_SPEED("statSpeed", DoubleStat::defaultValue, MathUtil::add, MathUtil::mul, -1, 1),
	CRAFTING_CRITICAL("craftingCritical"),
	SOULSHOTS_BONUS("soulshotBonus", new SoulshotsBonusFinalizer()),
	SPIRITSHOTS_BONUS("spiritshotBonus", new SpiritshotsBonusFinalizer()),
	BEAST_SOULSHOTS_BONUS("beastSoulshotBonus"),
	WORLD_CHAT_POINTS("worldChatPoints"),
	ATTACK_DAMAGE("attackDamage");

	static final Logger LOGGER = LoggerFactory.getLogger(DoubleStat.class);
	public static final int NUM_STATS = values().length;

	private final String _value;
	private final IStatsFunction _valueFinalizer;
	private final DoubleBinaryOperator _addFunction;
	private final DoubleBinaryOperator _mulFunction;
	private final double _resetAddValue;
	private final double _resetMulValue;

	public String getValue() {
		return _value;
	}

	DoubleStat(String xmlString) {
		this(xmlString, DoubleStat::defaultValue, MathUtil::add, MathUtil::mul, 0, 1);
	}

	DoubleStat(String xmlString, IStatsFunction valueFinalizer) {
		this(xmlString, valueFinalizer, MathUtil::add, MathUtil::mul, 0, 1);

	}

	DoubleStat(String xmlString, IStatsFunction valueFinalizer, DoubleBinaryOperator addFunction, DoubleBinaryOperator mulFunction, double resetAddValue, double resetMulValue) {
		_value = xmlString;
		_valueFinalizer = valueFinalizer;
		_addFunction = addFunction;
		_mulFunction = mulFunction;
		_resetAddValue = resetAddValue;
		_resetMulValue = resetMulValue;
	}

	public static DoubleStat valueOfXml(String name) {
		name = name.intern();
		for (DoubleStat s : values()) {
			if (s.getValue().equals(name)) {
				return s;
			}
		}

		throw new NoSuchElementException("Unknown name '" + name + "' for enum " + DoubleStat.class.getSimpleName());
	}

	/**
	 * @param creature
	 * @param baseValue
	 * @return the final value
	 */
	public double finalize(Creature creature, OptionalDouble baseValue) {
		try {
			return _valueFinalizer.calc(creature, baseValue, this);
		} catch (Exception e) {
			LOGGER.warn("Exception during finalization for : {} stat: {} : ", creature, toString(), e);
			return defaultValue(creature, baseValue, this);
		}
	}

	public double add(double oldValue, double value) {
		return _addFunction.applyAsDouble(oldValue, value);
	}

	public double mul(double oldValue, double value) {
		return _mulFunction.applyAsDouble(oldValue, value);
	}

	public double getResetAddValue() {
		return _resetAddValue;
	}

	public double getResetMulValue() {
		return _resetMulValue;
	}

	public static double weaponBaseValue(Creature creature, DoubleStat stat) {
		return stat._valueFinalizer.calcWeaponBaseValue(creature, stat);
	}

	public static double defaultValue(Creature creature, OptionalDouble base, DoubleStat stat) {
		final double mul = creature.getStat().getMul(stat);
		final double add = creature.getStat().getAdd(stat);
		return base.isPresent() ? defaultValue(creature, stat, base.getAsDouble()) : mul * (add + creature.getStat().getMoveTypeValue(stat, creature.getMoveType()));
	}

	public static double defaultValue(Creature creature, DoubleStat stat, double baseValue) {
		final double mul = creature.getStat().getMul(stat);
		final double add = creature.getStat().getAdd(stat);
		return (baseValue * mul) + add + creature.getStat().getMoveTypeValue(stat, creature.getMoveType());
	}
}
