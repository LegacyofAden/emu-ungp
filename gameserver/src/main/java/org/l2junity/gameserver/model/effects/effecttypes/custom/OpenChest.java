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
package org.l2junity.gameserver.model.effects.effecttypes.custom;

import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.actor.instance.TreasureInstance;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * Open Chest effect implementation.
 *
 * @author Adry_85
 */
public final class OpenChest extends AbstractEffect {
	public OpenChest(StatsSet params) {
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final PlayerInstance casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		final TreasureInstance targetTreasure = target.asTreasure();
		if (targetTreasure == null) {
			return;
		}

		if (targetTreasure.isDead() || (casterPlayer.getInstanceWorld() != targetTreasure.getInstanceWorld())) {
			return;
		}

		if (((casterPlayer.getLevel() <= 77) && (Math.abs(targetTreasure.getLevel() - casterPlayer.getLevel()) <= 6)) || ((casterPlayer.getLevel() >= 78) && (Math.abs(targetTreasure.getLevel() - casterPlayer.getLevel()) <= 5))) {
			casterPlayer.broadcastSocialAction(3);
			targetTreasure.setDropEnabled(true);
			targetTreasure.setMustRewardExpSp(false);
			targetTreasure.reduceCurrentHp(targetTreasure.getMaxHp(), casterPlayer, skill);
		} else {
			casterPlayer.broadcastSocialAction(13);
			targetTreasure.addDamageHate(casterPlayer, 0, 1);
			targetTreasure.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, casterPlayer);
		}
	}
}
