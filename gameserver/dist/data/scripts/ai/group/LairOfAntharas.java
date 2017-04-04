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
package ai.group;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.client.send.ValidateLocation;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

/**
 * Lair of Antharas AI.
 *
 * @author St3eT, UnAfraid
 */
public final class LairOfAntharas extends AbstractNpcAI {
	// NPC
	final private static int KNORIKS = 22857;
	final private static int DRAGON_KNIGHT = 22844;
	final private static int DRAGON_KNIGHT2 = 22845;
	final private static int ELITE_DRAGON_KNIGHT = 22846;

	final private static int DRAGON_GUARD = 22852;
	final private static int DRAGON_MAGE = 22853;
	// Misc
	final private static int KNIGHT_CHANCE = 30;
	final private static int KNORIKS_CHANCE = 60;
	final private static int KNORIKS_CHANCE2 = 50;

	private LairOfAntharas() {
		addKillId(DRAGON_KNIGHT, DRAGON_KNIGHT2, DRAGON_GUARD, DRAGON_MAGE);
		addSpawnId(DRAGON_KNIGHT, DRAGON_KNIGHT2, DRAGON_GUARD, DRAGON_MAGE);
		addMoveFinishedId(DRAGON_GUARD, DRAGON_MAGE);
		addAggroRangeEnterId(KNORIKS);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		if (event.equals("CHECK_HOME") && (npc != null) && !npc.isDead()) {
			if ((npc.distance2d(npc.getSpawn()) > 10) && !npc.isInCombat()) {
				((Attackable) npc).returnHome();
			} else if ((npc.getHeading() != npc.getSpawn().getHeading()) && !npc.isInCombat()) {
				npc.setHeading(npc.getSpawn().getHeading());
				npc.broadcastPacket(new ValidateLocation(npc));
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public String onAggroRangeEnter(Npc npc, Player player, boolean isSummon) {
		if (npc.isScriptValue(0) && (getRandom(100) < KNORIKS_CHANCE)) {
			if (getRandom(100) < KNORIKS_CHANCE2) {
				npc.setScriptValue(1);
			}
			npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.WHO_S_THERE_IF_YOU_DISTURB_THE_TEMPER_OF_THE_GREAT_LAND_DRAGON_ANTHARAS_I_WILL_NEVER_FORGIVE_YOU);
		}
		return super.onAggroRangeEnter(npc, player, isSummon);
	}

	@Override
	public String onKill(Npc npc, Player killer, boolean isSummon) {
		switch (npc.getId()) {
			case DRAGON_KNIGHT: {
				if (getRandom(100) > KNIGHT_CHANCE) {
					final Npc newKnight = addSpawn(DRAGON_KNIGHT2, npc, false, 0, true);
					npc.deleteMe();
					newKnight.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.THOSE_WHO_SET_FOOT_IN_THIS_PLACE_SHALL_NOT_LEAVE_ALIVE);
					addAttackPlayerDesire(newKnight, killer);
				}
				break;
			}
			case DRAGON_KNIGHT2: {
				if (getRandom(100) > KNIGHT_CHANCE) {
					final Npc eliteKnight = addSpawn(ELITE_DRAGON_KNIGHT, npc, false, 0, true);
					npc.deleteMe();
					eliteKnight.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.IF_YOU_WISH_TO_SEE_HELL_I_WILL_GRANT_YOU_YOUR_WISH);
					addAttackPlayerDesire(eliteKnight, killer);
				}
				break;
			}
			case DRAGON_GUARD:
			case DRAGON_MAGE: {
				cancelQuestTimer("CHECK_HOME", npc, null);
				break;
			}
		}
		return super.onKill(npc, killer, isSummon);
	}

	@Override
	public String onSpawn(Npc npc) {
		if ((npc.getId() == DRAGON_GUARD) || (npc.getId() == DRAGON_MAGE)) {
			npc.setRandomWalking(true);
			startQuestTimer("CHECK_HOME", 10000, npc, null, true);
		}
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new LairOfAntharas();
	}
}
