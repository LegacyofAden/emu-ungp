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

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.util.Util;

import ai.AbstractNpcAI;

/**
 * Flee Monsters AI.
 * @author Pandragon, NosBit
 */
public final class FleeMonsters extends AbstractNpcAI
{
	// NPCs
	private static final int[] MOBS =
	{
		18150, // Victim
		18151, // Victim
		18152, // Victim
		18153, // Victim
		18154, // Victim
		18155, // Victim
		18156, // Victim
		18157, // Victim
		20002, // Rabbit
		20432, // Elpy
		22228, // Grey Elpy
		25604, // Mutated Elpy
	};
	// Misc
	private static final int FLEE_DISTANCE = 500;
	
	private FleeMonsters()
	{
		addAttackId(MOBS);
	}
	
	@Override
	public String onAttack(Npc npc, PlayerInstance attacker, int damage, boolean isSummon)
	{
		npc.disableCoreAI(true);
		npc.setRunning();
		
		final Summon summon = isSummon ? attacker.getServitors().values().stream().findFirst().orElse(attacker.getPet()) : null;
		final ILocational attackerLoc = summon == null ? attacker : summon;
		final double radians = Math.toRadians(Util.calculateAngleFrom(attackerLoc, npc));
		final double posX = npc.getX() + (FLEE_DISTANCE * Math.cos(radians));
		final double posY = npc.getY() + (FLEE_DISTANCE * Math.sin(radians));
		final double posZ = npc.getZ();
		
		final Location destination = GeoData.getInstance().moveCheck(npc.getX(), npc.getY(), npc.getZ(), posX, posY, posZ, attacker.getInstanceWorld());
		npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, destination);
		return super.onAttack(npc, attacker, damage, isSummon);
	}
	
	public static void main(String[] args)
	{
		new FleeMonsters();
	}
}
