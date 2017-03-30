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

import org.l2junity.commons.util.ArrayUtil;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2MonsterInstance;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;

import ai.AbstractNpcAI;

/**
 * AI for mobs in Plains of Dion (near Floran Village).
 * @author Gladicek
 */
public final class PlainsOfDion extends AbstractNpcAI
{
	private static final int DELU_LIZARDMEN[] =
	{
		21104, // Delu Lizardman Supplier
		21105, // Delu Lizardman Special Agent
		21107, // Delu Lizardman Commander
	};
	
	private static final NpcStringId[] MONSTERS_MSG =
	{
		NpcStringId.S1_HOW_DARE_YOU_INTERRUPT_OUR_FIGHT_HEY_GUYS_HELP,
		NpcStringId.S1_HEY_WE_RE_HAVING_A_DUEL_HERE,
		NpcStringId.THE_DUEL_IS_OVER_ATTACK,
		NpcStringId.FOUL_KILL_THE_COWARD,
		NpcStringId.HOW_DARE_YOU_INTERRUPT_A_SACRED_DUEL_YOU_MUST_BE_TAUGHT_A_LESSON
	};
	
	private static final NpcStringId[] MONSTERS_ASSIST_MSG =
	{
		NpcStringId.DIE_YOU_COWARD,
		NpcStringId.KILL_THE_COWARD,
		NpcStringId.WHAT_ARE_YOU_LOOKING_AT
	};
	
	private PlainsOfDion()
	{
		addAttackId(DELU_LIZARDMEN);
	}
	
	@Override
	public String onAttack(Npc npc, PlayerInstance player, int damage, boolean isSummon)
	{
		if (npc.isScriptValue(0))
		{
			final int i = getRandom(5);
			if (i < 2)
			{
				npc.broadcastSay(ChatType.NPC_GENERAL, MONSTERS_MSG[i], player.getName());
			}
			else
			{
				npc.broadcastSay(ChatType.NPC_GENERAL, MONSTERS_MSG[i]);
			}
			
			World.getInstance().forEachVisibleObjectInRadius(npc, L2MonsterInstance.class, npc.getTemplate().getClanHelpRange(), obj ->
			{
				if (ArrayUtil.contains(DELU_LIZARDMEN, obj.getId()) && !obj.isAttackingNow() && !obj.isDead() && GeoData.getInstance().canSeeTarget(npc, obj))
				{
					addAttackPlayerDesire(obj, player);
					obj.broadcastSay(ChatType.NPC_GENERAL, MONSTERS_ASSIST_MSG[getRandom(3)]);
				}
			});
			npc.setScriptValue(1);
		}
		return super.onAttack(npc, player, damage, isSummon);
	}
	
	public static void main(String[] args)
	{
		new PlainsOfDion();
	}
}
