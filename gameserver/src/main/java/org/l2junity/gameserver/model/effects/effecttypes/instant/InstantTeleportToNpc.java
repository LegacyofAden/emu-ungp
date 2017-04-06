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

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.FlyToLocation;
import org.l2junity.gameserver.network.packets.s2c.FlyToLocation.FlyType;
import org.l2junity.gameserver.network.packets.s2c.ValidateLocation;

/**
 * Teleport player to summoned npc effect implementation.
 *
 * @author Nik
 */
public final class InstantTeleportToNpc extends AbstractEffect {
	private final int _npcId;

	public InstantTeleportToNpc(StatsSet params) {
		_npcId = params.getInt("npcId");
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Creature targetCreature = target.asCreature();
		if (targetCreature == null) {
			return;
		}

		final ILocational teleLocation = caster.getSummonedNpcs().stream().filter(npc -> npc.getId() == _npcId).findAny().orElse(null);
		if (teleLocation != null) {
			if (targetCreature.isInRadius2d(teleLocation, 900)) {
				targetCreature.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
				targetCreature.broadcastPacket(new FlyToLocation(targetCreature, teleLocation, FlyType.DUMMY));
				targetCreature.abortAttack();
				targetCreature.abortCast();
				targetCreature.setXYZ(teleLocation);
				targetCreature.broadcastPacket(new ValidateLocation(targetCreature));
				targetCreature.revalidateZone(true);
			} else {
				targetCreature.teleToLocation(teleLocation);
			}
		}
	}
}
