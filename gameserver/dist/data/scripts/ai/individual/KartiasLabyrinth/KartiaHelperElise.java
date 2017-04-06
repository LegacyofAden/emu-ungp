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
package ai.individual.KartiasLabyrinth;

import ai.AbstractNpcAI;
import org.l2junity.commons.util.ArrayUtil;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.FriendlyNpcInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceStatusChange;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;
import org.l2junity.gameserver.util.Util;

/**
 * Kartia Helper Elise AI.
 *
 * @author St3eT
 */
public final class KartiaHelperElise extends AbstractNpcAI {
	// NPCs
	private static final int[] KARTIA_ELISE =
			{
					33617, // Elise (Kartia 85)
					33628, // Elise (Kartia 90)
					33639, // Elise (Kartia 95)
			};
	private static final int[] KARTIA_ADOLPH =
			{
					33609, // Adolph (Kartia 85)
					33620, // Adolph (Kartia 90)
					33631, // Adolph (Kartia 95)
			};
	private static final int[] KARTIA_BARTON =
			{
					33611, // Barton (Kartia 85)
					33622, // Barton (Kartia 90)
					33633, // Barton (Kartia 95)
			};
	private static final int[] KARTIA_ELIYAH =
			{
					33615, // Eliyah (Kartia 85)
					33626, // Eliyah (Kartia 90)
					33637, // Eliyah (Kartia 95)
			};
	private static final int[] KARTIA_HAYUK =
			{
					33613, // Hayuk (Kartia 85)
					33624, // Hayuk (Kartia 90)
					33635, // Hayuk (Kartia 95)
			};
	private static final int HEALING_TREE = 19256;
	// Skill
	private static final SkillHolder TREE_HEAL_SKILL = new SkillHolder(15003, 1); // Summon Tree of Life - NPC
	// Misc
	private static final int[] KARTIA_SOLO_INSTANCES =
			{
					205, // Solo 85
					206, // Solo 90
					207, // Solo 95
			};

	private KartiaHelperElise() {
		setInstanceStatusChangeId(this::onInstanceStatusChange, KARTIA_SOLO_INSTANCES);
		addSeeCreatureId(KARTIA_ELISE);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		final Instance instance = npc.getInstanceWorld();
		if ((instance != null) && event.equals("CHECK_ACTION")) {
			final StatsSet npcVars = npc.getVariables();
			final StatsSet instParams = instance.getTemplateParameters();

			player = npcVars.getObject("PLAYER_OBJECT", Player.class);
			final FriendlyNpcInstance adolph = npcVars.getObject("ADOLPH_OBJECT", FriendlyNpcInstance.class);
			final FriendlyNpcInstance barton = npcVars.getObject("BARTON_OBJECT", FriendlyNpcInstance.class);
			final FriendlyNpcInstance eliyah = npcVars.getObject("ELIYAH_OBJECT", FriendlyNpcInstance.class);
			final FriendlyNpcInstance hayuk = npcVars.getObject("HAYUK_OBJECT", FriendlyNpcInstance.class);

			if ((player != null) && !player.isDead() && ((player.getCurrentHpPercent() < 75) || (player.getCurrentMpPercent() < 30))) {
				final int hpPer = player.getCurrentHpPercent();
				if ((hpPer < 40) && npcVars.getBoolean("CAN_USE_TREE", true)) {
					summonHealingTree(npc, player);
				} else if (hpPer < 60) {
					final SkillHolder chainSkill = instParams.getSkillHolder("eliseChainHeal");
					if (chainSkill != null) {
						addSkillCastDesire(npc, player, chainSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				} else if (hpPer < 75) {
					final SkillHolder healSkill = instParams.getSkillHolder("eliseHeal");
					if (healSkill != null) {
						addSkillCastDesire(npc, player, healSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				} else if (player.getCurrentMpPercent() < 30) {
					final SkillHolder rechargeSkill = instParams.getSkillHolder("eliseRecharge");
					if (rechargeSkill != null) {
						addSkillCastDesire(npc, player, rechargeSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.MEN_FOCUS_CHARGING_COMPLETE);
					}
				}
			} else if ((adolph != null) && !adolph.isDead() && (adolph.getCurrentHpPercent() < 75)) {
				final int hpPer = adolph.getCurrentHpPercent();
				if ((hpPer < 40) && npcVars.getBoolean("CAN_USE_TREE", true)) {
					summonHealingTree(npc, adolph);
				} else if (hpPer < 60) {
					final SkillHolder chainSkill = instParams.getSkillHolder("eliseChainHeal");
					if (chainSkill != null) {
						addSkillCastDesire(npc, adolph, chainSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				} else {
					final SkillHolder healSkill = instParams.getSkillHolder("eliseHeal");
					if (healSkill != null) {
						addSkillCastDesire(npc, adolph, healSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				}
			} else if ((barton != null) && !barton.isDead() && (barton.getCurrentHpPercent() < 60)) {
				final int hpPer = barton.getCurrentHpPercent();
				if ((hpPer < 30) && npcVars.getBoolean("CAN_USE_TREE", true)) {
					summonHealingTree(npc, barton);
				} else {
					final SkillHolder chainSkill = instParams.getSkillHolder("eliseChainHeal");
					if (chainSkill != null) {
						addSkillCastDesire(npc, barton, chainSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				}
			} else if ((eliyah != null) && !eliyah.isDead() && (eliyah.getCurrentHpPercent() < 60)) {
				final int hpPer = eliyah.getCurrentHpPercent();
				if ((hpPer < 30) && npcVars.getBoolean("CAN_USE_TREE", true)) {
					summonHealingTree(npc, eliyah);
				} else {
					final SkillHolder chainSkill = instParams.getSkillHolder("eliseChainHeal");
					if (chainSkill != null) {
						addSkillCastDesire(npc, eliyah, chainSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				}
			} else if ((hayuk != null) && !hayuk.isDead() && (hayuk.getCurrentHpPercent() < 60)) {
				final int hpPer = hayuk.getCurrentHpPercent();
				if ((hpPer < 30) && npcVars.getBoolean("CAN_USE_TREE", true)) {
					summonHealingTree(npc, hayuk);
				} else {
					final SkillHolder chainSkill = instParams.getSkillHolder("eliseChainHeal");
					if (chainSkill != null) {
						addSkillCastDesire(npc, hayuk, chainSkill, 23);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.POWER_OF_LIGHT_PROTECT_US);
					}
				}
			} else if (adolph != null) {
				final double distance = npc.distance2d(adolph);
				if (distance > 200) {
					final Location loc = new Location(adolph.getX() + getRandom(-100, 100), adolph.getY() + getRandom(-100, 100), adolph.getZ() + 50);
					if (distance > 500) {
						npc.teleToLocation(loc);
					} else {
						npc.setIsRunning(true);
						addMoveToDesire(npc, loc, 23);
					}
				}
			}
		}
	}

	public void onInstanceStatusChange(OnInstanceStatusChange event) {
		final Instance instance = event.getWorld();
		final int status = event.getStatus();
		switch (status) {
			case 1: {
				instance.getAliveNpcs(KARTIA_ELISE).forEach(elise -> getTimers().addRepeatingTimer("CHECK_ACTION", 1000, elise, null));
				break;
			}
			case 2:
			case 3: {
				final Location loc = instance.getTemplateParameters().getLocation("eliseTeleportStatus" + status);
				if (loc != null) {
					instance.getAliveNpcs(KARTIA_ELISE).forEach(elise -> elise.teleToLocation(loc));
				}
				break;
			}
		}
	}

	@Override
	public String onSeeCreature(Npc npc, Creature creature, boolean isSummon) {
		if (creature.isPlayer() || (creature instanceof FriendlyNpcInstance)) {
			final StatsSet npcVars = npc.getVariables();

			if (creature.isPlayer()) {
				npcVars.set("PLAYER_OBJECT", creature.getActingPlayer());
			} else if (ArrayUtil.contains(KARTIA_ADOLPH, creature.getId())) {
				npcVars.set("ADOLPH_OBJECT", creature);
			} else if (ArrayUtil.contains(KARTIA_BARTON, creature.getId())) {
				npcVars.set("BARTON_OBJECT", creature);
			} else if (ArrayUtil.contains(KARTIA_ELIYAH, creature.getId())) {
				npcVars.set("ELIYAH_OBJECT", creature);
			} else if (ArrayUtil.contains(KARTIA_HAYUK, creature.getId())) {
				npcVars.set("HAYUK_OBJECT", creature);
			}
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}

	private void summonHealingTree(Npc npc, Creature target) {
		npc.getVariables().set("CAN_USE_TREE", false);
		npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.COME_FORTH_TREE_OF_LIFE);
		final Npc tree = addSpawn(HEALING_TREE, Util.getRandomPosition(target, 20, 50), false, 0, false, npc.getInstanceId());
		getTimers().addTimer("TREE_REUSE", 10000, evnt -> npc.getVariables().set("CAN_USE_TREE", true));
		getTimers().addTimer("TREE_HEAL", 3000, evnt -> addSkillCastDesire(tree, target, TREE_HEAL_SKILL, 23));
	}

	public static void main(String[] args) {
		new KartiaHelperElise();
	}
}