/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.effecthandlers.instant;

import org.l2junity.gameserver.enums.ShotType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.AbnormalType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.Formulas;

/**
 * Magical Attack By Abnormal Slot effect implementation.
 * @author Sdw
 */
public final class InstantMAttackByAbnormalSlot extends AbstractEffect
{
	private final double _power;
	private final AbnormalType _abnormalType;
	
	public InstantMAttackByAbnormalSlot(StatsSet params)
	{
		_power = params.getDouble("power", 0);
		_abnormalType = AbnormalType.getAbnormalType(params.getString("abnormalType", null));
	}
	
	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.MAGICAL_ATTACK;
	}
	
	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item)
	{
		final Creature targetCreature = target.asCreature();
		if (targetCreature == null)
		{
			return;
		}

		if (caster.isAlikeDead() || !targetCreature.getEffectList().stopEffects(_abnormalType))
		{
			return;
		}
		
		if (targetCreature.isPlayer() && targetCreature.asPlayer().isFakeDeath())
		{
			targetCreature.asPlayer().stopFakeDeath(true);
		}

		boolean sps = skill.useSpiritShot() && caster.isChargedShot(ShotType.SPIRITSHOTS);
		boolean bss = skill.useSpiritShot() && caster.isChargedShot(ShotType.BLESSED_SPIRITSHOTS);
		final boolean mcrit = Formulas.calcCrit(skill.getMagicCriticalRate(), caster, targetCreature, skill);
		double damage = Formulas.calcMagicDam(caster, targetCreature, skill, caster.getMAtk(), _power, targetCreature.getMDef(), sps, bss, mcrit);
		
		caster.doAttack(damage, targetCreature, skill, false, false, mcrit, false);
	}
}