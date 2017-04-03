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
package org.l2junity.gameserver.model.skills.affectscopetypes;

import org.l2junity.gameserver.model.skills.IAffectScopeHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.util.Util;

import java.util.function.Consumer;

/**
 * @author Nik
 */
public class SummonExceptMaster implements IAffectScopeHandler {
	@Override
	public void forEachAffected(Creature activeChar, WorldObject target, Skill skill, Consumer<? super WorldObject> action) {
		final int affectRange = skill.getAffectRange();
		final int affectLimit = skill.getAffectLimit();

		if (target.isPlayable()) {
			final Player player = target.getActingPlayer();
			//@formatter:off
			player.getServitorsAndPets().stream()
					.filter(c -> !c.isDead())
					.filter(c -> affectRange > 0 ? Util.checkIfInRange(affectRange, c, target, true) : true)
					.filter(c -> skill.getAffectObjectType().checkAffectedObject(activeChar, c))
					.limit(affectLimit > 0 ? affectLimit : Long.MAX_VALUE)
					.forEach(action);
			//@formatter:on
		}
	}
}