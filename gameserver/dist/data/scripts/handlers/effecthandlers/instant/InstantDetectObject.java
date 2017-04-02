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

import org.l2junity.gameserver.enums.DoorOpenType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.DoorInstance;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * Detect Hidden Objects effect implementation.
 *
 * @author UnAfraid
 */
public final class InstantDetectObject extends AbstractEffect {
	public InstantDetectObject(StatsSet params) {
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final DoorInstance targetDoor = target.asDoor();
		if (targetDoor == null) {
			return;
		}

		if (targetDoor.getTemplate().isStealth()) {
			targetDoor.setMeshIndex(1);
			targetDoor.setTargetable(targetDoor.getTemplate().getOpenType() != DoorOpenType.NONE);
			targetDoor.broadcastStatusUpdate();
		}
	}
}
