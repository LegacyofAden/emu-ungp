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
package ai.uncategorized;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;

/**
 * Gordon AI
 *
 * @author TOFIZ, malyelfik
 */
public final class Gordon extends AbstractNpcAI {
	private static final int GORDON = 29095;

	private Gordon() {
		addSpawnId(GORDON);
		addSeeCreatureId(GORDON);
	}

	@Override
	public String onSeeCreature(Npc npc, Creature creature, boolean isSummon) {
		if (creature.isPlayer() && ((PlayerInstance) creature).isCursedWeaponEquipped()) {
			addAttackPlayerDesire(npc, (PlayerInstance) creature);
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}

	@Override
	public String onSpawn(Npc npc) {
		((Attackable) npc).setCanReturnToSpawnPoint(false);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new Gordon();
	}
}