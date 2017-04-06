/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ai.group;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.ExShowScreenMessage;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

/**
 * Altar of Evil AI.
 *
 * @author St3eT
 */
public final class AltarOfEvil extends AbstractNpcAI {
	// NPCs
	private static final int RIFTER = 23179; // Dimensional Rifter
	// Skill
	private static final SkillHolder SKILL = new SkillHolder(14643, 1); // Summon

	public AltarOfEvil() {
		addAttackId(RIFTER);
		addNpcHateId(RIFTER);
		addSpellFinishedId(RIFTER);
	}

	@Override
	public boolean onNpcHate(Attackable mob, Player player, boolean isSummon) {
		teleportPlayer(mob, player);
		return super.onNpcHate(mob, player, isSummon);
	}

	@Override
	public String onAttack(Npc npc, Player attacker, int damage, boolean isSummon) {
		teleportPlayer(npc, attacker);
		return super.onAttack(npc, attacker, damage, isSummon);
	}

	@Override
	public String onSpellFinished(Npc npc, Player player, Skill skill) {
		if (skill.getId() == SKILL.getSkillId()) {
			showOnScreenMsg(player, NpcStringId.DIMENSIONAL_RIFTER_SUMMONED_YOU, ExShowScreenMessage.TOP_CENTER, 5000);
			player.teleToLocation(npc);
		}
		return super.onSpellFinished(npc, player, skill);
	}

	private void teleportPlayer(Npc npc, Player player) {
		if (npc.isScriptValue(0) && !npc.isInRadius3d(player, 200)) {
			addSkillCastDesire(npc, player, SKILL, 23);
			npc.setScriptValue(1);
		}
	}

	public static void main(String[] args) {
		new AltarOfEvil();
	}
}