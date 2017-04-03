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
import org.l2junity.commons.util.ArrayUtil;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;

/**
 * Area Skill Npc AI.
 *
 * @author St3eT
 */
public final class AreaSkillNpc extends AbstractNpcAI {
	// NPCs
	private static final int[] BASIC = // area_skill_npc
			{
					13018, // Maximum Defense
					13019, // Anti-Music
					13020, // Maximum Resist Status
					13021, // Maximum Recovery
					13022, // Recover Force
					13023, // Maximize long-range weapon use
					13024, // Smokescreen
					13028, // Day of Doom
					13030, // Anti-Summoning Field
					13323, // Whisper of Fear
					13324, // Whisper of Fear
					13325, // Whisper of Fear
					13458, // Whisper of Fear
					13459, // Whisper of Fear
					13460, // Whisper of Fear
					13310, // Solo Dance
					13377, // Solo Dance
					13378, // Solo Dance
					13379, // Solo Dance
					13380, // Solo Dance
					13381, // Solo Dance
					13452, // Solo Dance
					13453, // Solo Dance
					13454, // Solo Dance
			};
	private static final int[] TOTEMS = // ai_totem_of_body
			{
					143, // Totem of Body
					144, // Totem of Spirit
					145, // Totem of Bravery
					146, // Totem of Fortitude
			};
	private static final int[] DECOY = // ai_decoy
			{
					13071, // Virtual Image
					13072, // Virtual Image
					13073, // Virtual Image
					13074, // Virtual Image
					13075, // Virtual Image
					13076, // Virtual Image
					13257, // Virtual Image
					13258, // Virtual Image
					13259, // Virtual Image
					13260, // Virtual Image
					13261, // Virtual Image
					13262, // Virtual Image
					13263, // Virtual Image
					13264, // Virtual Image
					13265, // Virtual Image
					13266, // Virtual Image
					13267, // Virtual Image
					13328, // Decoy
			};

	private AreaSkillNpc() {
		addSpawnId(BASIC);
		addSpawnId(TOTEMS);
		addSpawnId(DECOY);
	}

	@Override
	public String onSpawn(Npc npc) {
		final Creature summoner = npc.getSummoner();
		if ((summoner != null) && summoner.isPlayer()) {
			if (ArrayUtil.contains(BASIC, npc.getId()) || ArrayUtil.contains(TOTEMS, npc.getId())) {
				final int despawnTime = npc.getTemplate().getParameters().getInt("despawn_time", 7);
				getTimers().addTimer("START_SKILL_CAST", 100, npc, null);
				getTimers().addTimer("DELETE_ME", (despawnTime * 1000), npc, null);
			} else if (ArrayUtil.contains(DECOY, npc.getId())) {
				final int castTime = npc.getTemplate().getParameters().getInt("i_use_term_time", 5000);
				final int despawnTime = npc.getTemplate().getParameters().getInt("i_despawn_time", 30000);
				onTimerEvent("SKILL_CAST_BASIC", null, npc, null);
				getTimers().addTimer("START_SKILL_CAST", castTime, npc, null);
				getTimers().addTimer("DELETE_ME", despawnTime, npc, null);
			}
		}
		return super.onSpawn(npc);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		switch (event) {
			case "START_SKILL_CAST": {
				final SkillHolder skill = npc.getParameters().getSkillHolder(ArrayUtil.contains(DECOY, npc.getId()) ? "decoy_skill" : "union_skill");
				final int skillDelay = npc.getParameters().getInt("skill_delay", 2);
				if (skill != null) {
					npc.doCast(skill.getSkill());
					getTimers().addRepeatingTimer("SKILL_CAST_BASIC", skillDelay * 1000, npc, null);
				}
				break;
			}
			case "SKILL_CAST_BASIC": {
				final SkillHolder skill = npc.getParameters().getSkillHolder(ArrayUtil.contains(DECOY, npc.getId()) ? "decoy_skill" : "union_skill");
				if (skill != null) {
					npc.doCast(skill.getSkill());
				}
				break;
			}
			case "DELETE_ME": {
				getTimers().cancelTimer("SKILL_CAST_BASIC", npc, null);
				npc.deleteMe();
				break;
			}
		}
	}

	public static void main(String[] args) {
		new AreaSkillNpc();
	}
}