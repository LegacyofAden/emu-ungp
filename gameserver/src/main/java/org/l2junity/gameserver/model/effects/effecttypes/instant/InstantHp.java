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

import org.l2junity.gameserver.enums.StatModifierType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * HP change effect. It is mostly used for potions and static damage.
 *
 * @author Nik
 */
public final class InstantHp extends AbstractEffect {
	private final int _amount;
	private final StatModifierType _mode;

	public InstantHp(StatsSet params) {
		_amount = params.getInt("amount", 0);
		_mode = params.getEnum("mode", StatModifierType.class, StatModifierType.DIFF);
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Creature targetCreature = target.asCreature();
		if (targetCreature == null) {
			return;
		}

		if (targetCreature.isDead() || targetCreature.isDoor() || targetCreature.isHpBlocked()) {
			return;
		}

		double amount = 0;
		switch (_mode) {
			case DIFF: {
				amount = Math.min(_amount, targetCreature.getMaxRecoverableHp() - targetCreature.getCurrentHp());
				break;
			}
			case PER: {
				amount = Math.min((targetCreature.getCurrentHp() * _amount) / 100.0, targetCreature.getMaxRecoverableHp() - targetCreature.getCurrentHp());
				break;
			}
		}

		if (amount >= 0) {
			if (amount != 0) {
				final double newHp = amount + targetCreature.getCurrentHp();
				targetCreature.setCurrentHp(newHp, false);
				targetCreature.broadcastStatusUpdate(caster);
			}

			SystemMessage sm;
			if (caster.getObjectId() != targetCreature.getObjectId()) {
				sm = SystemMessage.getSystemMessage(SystemMessageId.S2_HP_HAS_BEEN_RESTORED_BY_C1);
				sm.addCharName(caster);
			} else {
				sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HP_HAS_BEEN_RESTORED);
			}
			sm.addInt((int) amount);
			targetCreature.sendPacket(sm);
		} else {
			final double damage = -amount;
			targetCreature.reduceCurrentHp(damage, caster, skill, false, false, false, false);
			caster.sendDamageMessage(targetCreature, skill, (int) damage, false, false);
		}
	}
}
