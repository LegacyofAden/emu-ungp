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
package instances.PailakaSongOfIceAndFire;

import instances.AbstractInstance;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.model.zone.ZoneType;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

/**
 * Pailaka Song of Ice and Fire Instance zone.
 *
 * @author Gnacik, St3eT
 */
public final class PailakaSongOfIceAndFire extends AbstractInstance {
	// NPCs
	private static final int ADLER1 = 32497;
	private static final int GARGOS = 18607;
	private static final int BLOOM = 18616;
	private static final int BOTTLE = 32492;
	private static final int BRAZIER = 32493;
	// Items
	private static final int FIRE_ENHANCER = 13040;
	private static final int WATER_ENHANCER = 13041;
	private static final int SHIELD_POTION = 13032;
	private static final int HEAL_POTION = 13033;
	// Location
	private static final Location TELEPORT = new Location(-52875, 188232, -4696);
	// Misc
	private static final int TEMPLATE_ID = 43;
	private static final int ZONE = 20108;

	public PailakaSongOfIceAndFire() {
		super(TEMPLATE_ID);
		addStartNpc(ADLER1);
		addTalkId(ADLER1);
		addAttackId(BOTTLE, BRAZIER);
		addExitZoneId(ZONE);
		addSeeCreatureId(GARGOS);
		addSpawnId(BLOOM);
		addKillId(BLOOM);
	}

	@Override
	public final String onAdvEvent(String event, Npc npc, Player player) {
		switch (event) {
			case "enter": {
				enterInstance(player, npc, TEMPLATE_ID);
				return "32497-01.html";
			}
			case "GARGOS_LAUGH": {
				npc.broadcastSay(ChatType.NPC_SHOUT, NpcStringId.OHH_OH_OH);
				break;
			}
			case "TELEPORT": {
				player.teleToLocation(TELEPORT);
				break;
			}
			case "DELETE": {
				if (npc != null) {
					npc.deleteMe();
				}
				break;
			}
			case "BLOOM_TIMER": {
				startQuestTimer("BLOOM_TIMER2", getRandom(2, 4) * 60 * 1000, npc, null);
				break;
			}
			case "BLOOM_TIMER2": {
				npc.setInvisible(!npc.isInvisible());
				startQuestTimer("BLOOM_TIMER", 5000, npc, null);
				break;
			}
		}
		return super.onAdvEvent(event, npc, player);
	}

	@Override
	public final String onAttack(Npc npc, Player player, int damage, boolean isSummon) {
		if ((damage > 0) && npc.isScriptValue(0)) {
			switch (getRandom(6)) {
				case 0: {
					if (npc.getId() == BOTTLE) {
						npc.dropItem(player, WATER_ENHANCER, getRandom(1, 6));
					}
					break;
				}
				case 1: {
					if (npc.getId() == BRAZIER) {
						npc.dropItem(player, FIRE_ENHANCER, getRandom(1, 6));
					}
					break;
				}
				case 2:
				case 3: {
					npc.dropItem(player, SHIELD_POTION, getRandom(1, 10));
					break;
				}
				case 4:
				case 5: {
					npc.dropItem(player, HEAL_POTION, getRandom(1, 10));
					break;
				}
			}
			npc.setScriptValue(1);
			startQuestTimer("DELETE", 3000, npc, null);
		}
		return super.onAttack(npc, player, damage, isSummon);
	}

	@Override
	public final String onKill(Npc npc, Player player, boolean isSummon) {
		npc.dropItem(player, getRandomBoolean() ? SHIELD_POTION : HEAL_POTION, getRandom(1, 7));
		return super.onKill(npc, player, isSummon);
	}

	@Override
	public String onExitZone(Creature character, ZoneType zone) {
		if ((character.isPlayer()) && !character.isDead() && !character.isTeleporting() && ((Player) character).isOnline()) {
			final Instance world = character.getInstanceWorld();
			if ((world != null) && (world.getTemplateId() == TEMPLATE_ID)) {
				startQuestTimer("TELEPORT", 1000, null, character.getActingPlayer());
			}
		}
		return super.onExitZone(character, zone);
	}

	@Override
	public String onSeeCreature(Npc npc, Creature creature, boolean isSummon) {
		if (npc.isScriptValue(0) && creature.isPlayer()) {
			npc.setScriptValue(1);
			startQuestTimer("GARGOS_LAUGH", 1000, npc, creature.getActingPlayer());
		}
		return super.onSeeCreature(npc, creature, isSummon);
	}

	@Override
	public String onSpawn(Npc npc) {
		npc.setInvisible(true);
		startQuestTimer("BLOOM_TIMER", 1000, npc, null);
		return super.onSpawn(npc);
	}

	public static void main(String[] args) {
		new PailakaSongOfIceAndFire();
	}
}