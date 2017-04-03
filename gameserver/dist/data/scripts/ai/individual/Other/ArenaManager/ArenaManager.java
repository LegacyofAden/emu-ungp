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
package ai.individual.Other.ArenaManager;

import ai.AbstractNpcAI;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * Arena Manager AI.
 *
 * @author St3eT
 */
public final class ArenaManager extends AbstractNpcAI {
	// NPCs
	private static final int[] ARENA_MANAGER =
			{
					31226, // Arena Director (MDT)
					31225, // Arena Manager (Coliseum)
			};
	// Skills
	private static final SkillHolder[] BUFFS =
			{
					new SkillHolder(6805, 1), // Arena Empower
					new SkillHolder(6806, 1), // Arena Acumen
					new SkillHolder(6807, 1), // Arena Concentration
					new SkillHolder(6808, 1), // Arena Might
					new SkillHolder(6804, 1), // Arena Wind Walk
					new SkillHolder(6812, 1), // Arena Berserker Spirit
			};
	private static final SkillHolder CP_RECOVERY = new SkillHolder(4380, 1); // Arena: CP Recovery
	private static final SkillHolder HP_RECOVERY = new SkillHolder(6817, 1); // Arena HP Recovery
	// Misc
	private static final int CP_COST = 1000;
	private static final int HP_COST = 1000;
	private static final int BUFF_COST = 2000;

	private ArenaManager() {
		addStartNpc(ARENA_MANAGER);
		addTalkId(ARENA_MANAGER);
		addFirstTalkId(ARENA_MANAGER);
	}

	@Override
	public String onAdvEvent(String event, Npc npc, Player player) {
		switch (event) {
			case "CPrecovery": {
				if (player.getAdena() >= CP_COST) {
					takeItems(player, Inventory.ADENA_ID, CP_COST);
					getTimers().addTimer("CPrecovery_delay", 2000, npc, player);
				} else {
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
				break;
			}
			case "HPrecovery": {
				if (player.getAdena() >= HP_COST) {
					takeItems(player, Inventory.ADENA_ID, HP_COST);
					getTimers().addTimer("HPrecovery_delay", 2000, npc, player);
				} else {
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
				break;
			}
			case "Buff": {
				if (player.getAdena() >= BUFF_COST) {
					takeItems(player, Inventory.ADENA_ID, BUFF_COST);
					npc.setTarget(player);
					for (SkillHolder skill : BUFFS) {
						npc.doCast(skill.getSkill());
					}
				} else {
					player.sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
				}
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		if ((player != null) && !player.isInsideZone(ZoneId.PVP)) {
			if (event.equals("CPrecovery_delay")) {
				npc.setTarget(player);
				npc.doCast(CP_RECOVERY.getSkill());
			} else if (event.equals("HPrecovery_delay")) {
				npc.setTarget(player);
				npc.doCast(HP_RECOVERY.getSkill());
			}
		}
	}

	public static void main(String[] args) {
		new ArenaManager();
	}
}