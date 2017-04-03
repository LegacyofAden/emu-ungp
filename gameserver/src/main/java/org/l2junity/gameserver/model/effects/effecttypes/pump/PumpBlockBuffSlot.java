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
package org.l2junity.gameserver.model.effects.effecttypes.pump;

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.skills.AbnormalType;
import org.l2junity.gameserver.model.skills.Skill;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Block Buff Slot effect implementation.
 *
 * @author Zoey76
 */
public final class PumpBlockBuffSlot extends AbstractEffect {
	private final Set<AbnormalType> _blockAbnormalSlots;

	public PumpBlockBuffSlot(StatsSet params) {
		String blockAbnormalSlots = params.getString("slot");
		if ((blockAbnormalSlots != null) && !blockAbnormalSlots.isEmpty()) {
			_blockAbnormalSlots = new HashSet<>();
			for (String slot : blockAbnormalSlots.split(";")) {
				_blockAbnormalSlots.add(AbnormalType.getAbnormalType(slot));
			}
		} else {
			_blockAbnormalSlots = Collections.<AbnormalType>emptySet();
		}
	}

	@Override
	public void pumpStart(Creature caster, Creature target, Skill skill) {
		target.getEffectList().addBlockedAbnormalTypes(_blockAbnormalSlots);
	}

	@Override
	public void pumpEnd(Creature caster, Creature target, Skill skill) {
		target.getEffectList().removeBlockedAbnormalTypes(_blockAbnormalSlots);
	}
}
