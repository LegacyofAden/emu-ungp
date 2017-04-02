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
package handlers.targethandlers.affectobject;

import org.l2junity.gameserver.handler.IAffectObjectHandler;
import org.l2junity.gameserver.model.actor.Creature;

/**
 * Undead enemy npc affect object implementation.
 *
 * @author Nik
 */
public class UndeadRealEnemy implements IAffectObjectHandler {
	@Override
	public boolean checkAffectedObject(Creature activeChar, Creature target) {
		// You are not an enemy of yourself.
		if (activeChar == target) {
			return false;
		}

		return target.isUndead() && target.isAutoAttackable(activeChar);
	}
}