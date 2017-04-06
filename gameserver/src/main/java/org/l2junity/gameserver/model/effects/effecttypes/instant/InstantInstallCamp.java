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
import org.l2junity.gameserver.model.actor.instance.SiegeFlagInstance;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * Headquarter Create effect implementation.
 *
 * @author Adry_85
 */
public final class InstantInstallCamp extends AbstractEffect {
	private static final int HQ_NPC_ID = 35062;

	public InstantInstallCamp(StatsSet params) {
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		if ((casterPlayer.getClan() == null) || (casterPlayer.getClan().getLeaderId() != casterPlayer.getObjectId())) {
			return;
		}

		final SiegeFlagInstance flag = new SiegeFlagInstance(casterPlayer, NpcData.getInstance().getTemplate(HQ_NPC_ID), false);
		flag.setTitle(casterPlayer.getClan().getName());
		flag.setCurrentHpMp(flag.getMaxHp(), flag.getMaxMp());
		flag.setHeading(casterPlayer.getHeading());
		flag.spawnMe(casterPlayer.getX(), casterPlayer.getY(), casterPlayer.getZ() + 50);
	}
}
