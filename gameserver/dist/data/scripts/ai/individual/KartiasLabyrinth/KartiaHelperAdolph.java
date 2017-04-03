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
import org.l2junity.gameserver.geodata.GeoData;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.L2MonsterInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureAttacked;
import org.l2junity.gameserver.model.events.impl.character.OnCreatureDeath;
import org.l2junity.gameserver.model.events.impl.instance.OnInstanceStatusChange;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.skills.SkillCaster;
import org.l2junity.gameserver.network.client.send.string.NpcStringId;
import org.l2junity.gameserver.util.Util;

import java.util.List;

/**
 * Kartia Helper Adolph AI.
 *
 * @author St3eT
 */
public final class KartiaHelperAdolph extends AbstractNpcAI {
	// NPCs
	private static final int[] KARTIA_ADOLPH =
			{
					33609, // Adolph (Kartia 85)
					33620, // Adolph (Kartia 90)
					33631, // Adolph (Kartia 95)
			};
	private static final int[] MIRRORS =
			{
					33798, // Life Plunderer (85)
					33799, // Life Plunderer (90)
					33800, // Life Plunderer (95)
			};
	// Misc
	private static final int[] KARTIA_SOLO_INSTANCES =
			{
					205, // Solo 85
					206, // Solo 90
					207, // Solo 95
			};

	private KartiaHelperAdolph() {
		setCreatureKillId(this::onCreatureKill, KARTIA_ADOLPH);
		setCreatureAttackedId(this::onCreatureAttacked, KARTIA_ADOLPH);
		addSpellFinishedId(KARTIA_ADOLPH);
		addSeeCreatureId(KARTIA_ADOLPH);
		setInstanceStatusChangeId(this::onInstanceStatusChange, KARTIA_SOLO_INSTANCES);
	}

	@Override
	public void onTimerEvent(String event, StatsSet params, Npc npc, Player player) {
		final Instance instance = npc.getInstanceWorld();
		if ((instance != null) && event.equals("CHECK_ACTION")) {
			final StatsSet instParams = instance.getTemplateParameters();
			boolean actionFound = false;

			if (!npc.isInCombat() || !npc.isAttackingNow() || (npc.getTarget() == null)) {
				final List<L2MonsterInstance> monsterList = World.getInstance().getVisibleObjects(npc, L2MonsterInstance.class, 500);
				if (!monsterList.isEmpty()) {
					final L2MonsterInstance monster = monsterList.get(getRandom(monsterList.size()));

					if (monster.isTargetable() && GeoData.getInstance().canSeeTarget(npc, monster) && !ArrayUtil.contains(MIRRORS, monster.getId())) {
						actionFound = true;
						addAttackDesire(npc, monster);
					}
				}
			}

			if (!actionFound) {
				final SkillHolder hateSkill = instParams.getSkillHolder("adolphHate");
				if (npc.isInCombat() && (hateSkill != null) && SkillCaster.checkUseConditions(npc, hateSkill.getSkill())) {
					addSkillCastDesire(npc, npc.getTarget(), hateSkill, 23);
				} else {
					final Player instancePlayer = npc.getVariables().getObject("PLAYER_OBJECT", Player.class);
					if (instancePlayer != null) {
						final double radian = Math.toRadians(Util.convertHeadingToDegree(instancePlayer.getHeading()));
						final int X = (int) (instancePlayer.getX() + (Math.cos(radian) * 150));
						final int Y = (int) (instancePlayer.getY() + (Math.sin(radian) * 150));
						final Location loc = GeoData.getInstance().moveCheck(instancePlayer.getX(), instancePlayer.getY(), instancePlayer.getZ(), X, Y, instancePlayer.getZ(), instance);

						if (!npc.isInRadius3d(loc, 50)) {
							npc.setIsRunning(true);
							addMoveToDesire(npc, loc, 23);
						}
					}
				}
			}
		}
	}

	@Override
	public String onSpellFinished(Npc npc, Player player, Skill skill) {
		final Instance instance = npc.getInstanceWorld();
		if (instance != null) {
			final StatsSet instParams = instance.getTemplateParameters();
			final SkillHolder hateSkill = instParams.getSkillHolder("adolphHate");
			final SkillHolder shieldSkill = instParams.getSkillHolder("adolphShield");
			final SkillHolder punishSkill = instParams.getSkillHolder("adolphPunish");

			if ((hateSkill != null) && (skill.getId() == hateSkill.getSkillId())) {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.YOU_FILTHY_MONSTERS_I_WILL_TAKE_YOU_ON);
			} else if ((shieldSkill != null) && (skill.getId() == shieldSkill.getSkillId())) {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.STOP_RIGHT_THERE_I_WILL_BE_YOUR_OPPONENT);
			} else if ((punishSkill != null) && (skill.getId() == punishSkill.getSkillId())) {
				npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.I_WILL_SHOW_YOU_THE_JUSTICE_OF_ADEN);
			}
		}
		return super.onSpellFinished(npc, player, skill);
	}

	public void onCreatureAttacked(OnCreatureAttacked event) {
		if (event.getTarget().isNpc()) {
			final Npc npc = (Npc) event.getTarget();
			final Instance instance = npc.getInstanceWorld();
			if ((instance != null) && !event.getAttacker().isPlayable()) {
				final StatsSet instParams = instance.getTemplateParameters();
				final int random = getRandom(1000);

				if (random < 333) {
					final SkillHolder shieldSkill = instParams.getSkillHolder("adolphShield");
					if ((shieldSkill != null) && SkillCaster.checkUseConditions(npc, shieldSkill.getSkill())) {
						addSkillCastDesire(npc, npc.getTarget(), shieldSkill, 23);
					}
				} else if (random < 666) {
					final SkillHolder punishSkill = instParams.getSkillHolder("adolphPunish");
					if ((punishSkill != null) && SkillCaster.checkUseConditions(npc, punishSkill.getSkill())) {
						addSkillCastDesire(npc, npc.getTarget(), punishSkill, 23);
					}
				}

				if ((npc.getCurrentHpPercent() < 30) && npc.isScriptValue(0)) {
					final SkillHolder ultimateSkill = instParams.getSkillHolder("adolphUltimate");
					if ((ultimateSkill != null) && !npc.isAffectedBySkill(ultimateSkill.getSkillId()) && SkillCaster.checkUseConditions(npc, ultimateSkill.getSkill())) {
						npc.setScriptValue(1);
						npc.broadcastSay(ChatType.NPC_GENERAL, NpcStringId.IT_S_NOT_OVER);
						addSkillCastDesire(npc, npc.getTarget(), ultimateSkill, 23);
						getTimers().addTimer("RESTORE_SCRIPTVAL", 10000, n -> npc.setScriptValue(0));
					}
				}
			}
		}
	}

	public void onCreatureKill(OnCreatureDeath event) {
		final Npc npc = (Npc) event.getTarget();
		final Instance world = npc.getInstanceWorld();
		if (world != null) {
			world.destroy(); // Kartia can't continue without Adolph
		}
	}

	public void onInstanceStatusChange(OnInstanceStatusChange event) {
		final Instance instance = event.getWorld();
		final int status = event.getStatus();
		switch (status) {
			case 1: {
				instance.getAliveNpcs(KARTIA_ADOLPH).forEach(adolph -> getTimers().addRepeatingTimer("CHECK_ACTION", 1000, adolph, null));
				break;
			}
			case 2:
			case 3: {
				final Location loc = instance.getTemplateParameters().getLocation("adolphTeleportStatus" + status);
				if (loc != null) {
					instance.getAliveNpcs(KARTIA_ADOLPH).forEach(adolph -> adolph.teleToLocation(loc));
				}
				break;
			}
		}
	}

	@Override
	public String onSeeCreature(Npc npc, Creature creature, boolean isSummon) {
		if (creature.isPlayer()) {
			npc.getVariables().set("PLAYER_OBJECT", creature.getActingPlayer());
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}

	public static void main(String[] args) {
		new KartiaHelperAdolph();
	}
}