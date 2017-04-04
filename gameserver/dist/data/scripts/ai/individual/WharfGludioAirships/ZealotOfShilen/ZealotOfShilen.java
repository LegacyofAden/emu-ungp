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
package ai.individual.WharfGludioAirships.ZealotOfShilen;

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.MonsterInstance;
import org.l2junity.gameserver.model.actor.instance.Player;

import ai.AbstractNpcAI;

/**
 * Zealot of Shilen AI.
 *
 * @author nonom
 */
public final class ZealotOfShilen extends AbstractNpcAI {
	// NPCs
	private static final int ZEALOT = 18782;
	private static final int[] GUARDS =
			{
					32628,
					32629
			};

	public ZealotOfShilen() {
		addSpawnId(ZEALOT);
		addSpawnId(GUARDS);
		addFirstTalkId(GUARDS);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		if (npc == null) {
			return null;
		}

		startQuestTimer("WATCHING", 10000, npc, null, true);
		if (event.equalsIgnoreCase("WATCHING") && !npc.isAttackingNow()) {
			npc.getWorld().forEachVisibleObject(npc, MonsterInstance.class, character ->
			{
				if (!character.isDead() && !character.isDecayed()) {
					npc.setRunning();
					((Attackable) npc).addDamageHate(character, 0, 999);
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, character, null);
				}
			});
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public String onFirstTalk(Npc npc, Player player) {
		return (npc.isAttackingNow()) ? "32628-01.html" : npc.getId() + ".html";
	}

	@Override
	public String onSpawn(Npc npc) {
		if (npc.getId() == ZEALOT) {
			npc.setRandomWalking(true);
		} else {
			npc.setIsInvul(true);
			((Attackable) npc).setCanReturnToSpawnPoint(false);
			startQuestTimer("WATCHING", 10000, npc, null, true);
		}
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new ZealotOfShilen();
	}
}
