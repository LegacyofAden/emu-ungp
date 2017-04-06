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
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * Mana Heal By Level Self effect implementation.
 *
 * @author UnAfraid
 */
public final class InstantMpByLevelSelf extends AbstractEffect {
	private final double _power;

	public InstantMpByLevelSelf(StatsSet params) {
		_power = params.getDouble("power", 0);
	}

	@Override
	public L2EffectType getEffectType() {
		return L2EffectType.MANAHEAL_BY_LEVEL;
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		if (caster.isDead() || caster.isMpBlocked()) {
			return;
		}

		double power = 0;
		final int levelDiff = caster.getLevel() - skill.getMagicLevel();
		if (levelDiff <= 9) {
			power = _power * ((10 * (10 - CommonUtil.constrain(levelDiff, 0, 9))) / 100);
		}

		final double healedAmount = CommonUtil.constrain(power, 0, caster.getMaxRecoverableMp() - caster.getCurrentMp());
		caster.setCurrentMp(caster.getCurrentMp() + healedAmount);

		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_MP_HAS_BEEN_RESTORED);
		sm.addInt((int) healedAmount);
		caster.sendPacket(sm);
	}
}
