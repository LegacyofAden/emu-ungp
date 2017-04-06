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
package org.l2junity.gameserver.model.skills.affectobjecttypes;

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.IAffectObjectHandler;

/**
 * @author Nik
 */
public class Clan implements IAffectObjectHandler {
	@Override
	public boolean checkAffectedObject(Creature activeChar, Creature target) {
		if (activeChar == target) {
			return true;
		}

		Player player = activeChar.getActingPlayer();
		if (player != null) {
			org.l2junity.gameserver.model.Clan clan = player.getClan();
			if (clan != null) {
				return clan == target.getClan();
			}
		} else if (activeChar.isNpc() && target.isNpc()) {
			return ((Npc) activeChar).isInMyClan(((Npc) target));
		}

		return false;
	}
}