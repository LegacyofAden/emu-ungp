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
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;

/**
 * Simple AI that manages special conditions for Divine Beast summon.
 *
 * @author UnAfraid
 */
public final class DivineBeast extends AbstractNpcAI {
	private static final int DIVINE_BEAST = 14870;
	private static final int TRANSFORMATION_ID = 258;
	private static final int CHECK_TIME = 2 * 1000;

	private DivineBeast() {
		addSummonSpawnId(DIVINE_BEAST);
	}

	@Override
	public void onSummonSpawn(Summon summon) {
		startQuestTimer("VALIDATE_TRANSFORMATION", CHECK_TIME, null, summon.getActingPlayer(), true);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, PlayerInstance player) {
		if ((player == null) || !player.hasServitors()) {
			cancelQuestTimer(event, npc, player);
		} else if (player.getTransformationId() != TRANSFORMATION_ID) {
			cancelQuestTimer(event, npc, player);
			player.getServitors().values().forEach(summon -> summon.unSummon(player));
		}

		return super.onAdvEvent(event, npc, player);
	}

	public static void main(String[] args) {
		new DivineBeast();
	}
}
