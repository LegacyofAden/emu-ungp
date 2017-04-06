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
package org.l2junity.gameserver.model.skills.targettypes;

import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.handler.ITargetTypeHandler;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * Any friendly selected target. Works on dead targets or doors as well. Unable to force use.
 *
 * @author Nik
 */
public class EnemyNot implements ITargetTypeHandler {
	@Override
	public WorldObject getTarget(Creature activeChar, WorldObject selectedTarget, Skill skill, boolean forceUse, boolean dontMove, boolean sendMessage) {
		if (selectedTarget == null) {
			return null;
		}

		if (!selectedTarget.isCreature()) {
			return null;
		}

		final Creature target = (Creature) selectedTarget;

		// You can always target yourself.
		if (activeChar == target) {
			return target;
		}

		if (!target.isAutoAttackable(activeChar)) {
			// Check for cast range if character cannot move. TODO: char will start follow until within castrange, but if his moving is blocked by geodata, this msg will be sent.
			if (dontMove) {
				if (activeChar.distance3d(target) > skill.getCastRange()) {
					if (sendMessage) {
						activeChar.sendPacket(SystemMessageId.THE_DISTANCE_IS_TOO_FAR_AND_SO_THE_CASTING_HAS_BEEN_STOPPED);
					}

					return null;
				}
			}

			if ((skill.isFlyType()) && !GeoData.getInstance().canMove(activeChar, target)) {
				if (sendMessage) {
					activeChar.sendPacket(SystemMessageId.THE_TARGET_IS_LOCATED_WHERE_YOU_CANNOT_CHARGE);
				}
				return null;
			}

			// Geodata check when character is within range.
			if (!GeoData.getInstance().canSeeTarget(activeChar, target)) {
				if (sendMessage) {
					activeChar.sendPacket(SystemMessageId.CANNOT_SEE_TARGET);
				}

				return null;
			}

			return target;
		}

		if (sendMessage) {
			activeChar.sendPacket(SystemMessageId.INVALID_TARGET);
		}

		return null;
	}
}