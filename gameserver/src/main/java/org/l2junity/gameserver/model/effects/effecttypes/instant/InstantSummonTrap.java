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
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.instance.TrapInstance;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Summon Trap effect implementation.
 *
 * @author Zoey76
 */
public final class InstantSummonTrap extends AbstractEffect {
	private static final Logger LOGGER = LoggerFactory.getLogger(InstantSummonTrap.class);

	private final int _npcId;

	public InstantSummonTrap(StatsSet params) {
		_npcId = params.getInt("npcId", 0);
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		if (casterPlayer.isAlikeDead() || casterPlayer.inObserverMode() || casterPlayer.isMounted()) {
			return;
		}

		if (_npcId <= 0) {
			LOGGER.warn("Invalid NPC ID: {} in skill ID: {}", _npcId, skill.getId());
			return;
		}

		// Unsummon previous trap
		if (casterPlayer.getTrap() != null) {
			casterPlayer.getTrap().unSummon();
		}

		final NpcTemplate npcTemplate = NpcData.getInstance().getTemplate(_npcId);
		if (npcTemplate == null) {
			LOGGER.warn("Spawn of the non-existing Trap ID: {} in skill ID: {}", _npcId, skill.getId());
			return;
		}

		final TrapInstance trap = new TrapInstance(npcTemplate, casterPlayer);
		trap.setCurrentHp(trap.getMaxHp());
		trap.setCurrentMp(trap.getMaxMp());
		trap.setIsInvul(true);
		trap.setHeading(casterPlayer.getHeading());
		trap.spawnMe(casterPlayer.getX(), casterPlayer.getY(), casterPlayer.getZ());
		casterPlayer.addSummonedNpc(trap); // player.setTrap(trap);
	}
}
