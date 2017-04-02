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

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.effects.L2EffectType;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.AbnormalType;
import org.l2junity.gameserver.model.skills.Skill;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Dispel By Slot effect implementation.
 *
 * @author Gnacik, Zoey76, Adry_85
 */
public final class InstantDispelBySlotMyself extends AbstractEffect {
	private final Set<AbnormalType> _dispelAbnormals;

	public InstantDispelBySlotMyself(StatsSet params) {
		String dispel = params.getString("dispel");
		if ((dispel != null) && !dispel.isEmpty()) {
			_dispelAbnormals = new HashSet<>();
			for (String slot : dispel.split(";")) {
				_dispelAbnormals.add(AbnormalType.getAbnormalType(slot));
			}
		} else {
			_dispelAbnormals = Collections.<AbnormalType>emptySet();
		}
	}

	@Override
	public L2EffectType getEffectType() {
		return L2EffectType.DISPEL_BY_SLOT;
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Creature targetCreature = target.asCreature();
		if (targetCreature == null) {
			return;
		}

		if (_dispelAbnormals.isEmpty()) {
			return;
		}

		// The effectlist should already check if it has buff with this abnormal type or not.
		targetCreature.getEffectList().stopEffects(info -> !info.getSkill().isIrreplacableBuff() && _dispelAbnormals.contains(info.getSkill().getAbnormalType()), true, true);
	}
}
