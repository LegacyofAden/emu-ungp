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
package handlers.effecthandlers.pump;

import org.l2junity.gameserver.enums.DamageByAttackType;
import org.l2junity.gameserver.enums.StatModifierType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.DoubleStat;

/**
 * @author Nik
 */
public class PumpPveMagicalSkillDmgBonus extends AbstractEffect {
	private final double _amount;
	private final DamageByAttackType _type;
	private final StatModifierType _mode;

	public PumpPveMagicalSkillDmgBonus(StatsSet params) {
		_amount = params.getDouble("amount", 0);
		_type = params.getEnum("type", DamageByAttackType.class, DamageByAttackType.NONE);
		_mode = params.getEnum("mode", StatModifierType.class, StatModifierType.DIFF);
	}

	@Override
	public void pump(Creature target, Skill skill) {
		switch (_type) {
			case MOB: {
				switch (_mode) {
					case DIFF: {
						target.getStat().mergeAdd(DoubleStat.PVE_MAGICAL_SKILL_DAMAGE, _amount);
						break;
					}
					case PER: {
						target.getStat().mergeMul(DoubleStat.PVE_MAGICAL_SKILL_DAMAGE, (_amount / 100) + 1);
						break;
					}
				}
				break;
			}
			case BOSS: {
				switch (_mode) {
					case DIFF: {
						target.getStat().mergeAdd(DoubleStat.PVE_RAID_MAGICAL_SKILL_DAMAGE, _amount);
						break;
					}
					case PER: {
						target.getStat().mergeMul(DoubleStat.PVE_RAID_MAGICAL_SKILL_DAMAGE, (_amount / 100) + 1);
						break;
					}
				}
				break;
			}
			case ENEMY_ALL: {
				switch (_mode) {
					case DIFF: {
						target.getStat().mergeAdd(DoubleStat.PVE_MAGICAL_SKILL_DAMAGE, _amount);
						target.getStat().mergeAdd(DoubleStat.PVE_RAID_MAGICAL_SKILL_DAMAGE, _amount);
						break;
					}
					case PER: {
						target.getStat().mergeMul(DoubleStat.PVE_MAGICAL_SKILL_DAMAGE, (_amount / 100) + 1);
						target.getStat().mergeMul(DoubleStat.PVE_RAID_MAGICAL_SKILL_DAMAGE, (_amount / 100) + 1);
						break;
					}
				}
				break;
			}
		}
	}
}
