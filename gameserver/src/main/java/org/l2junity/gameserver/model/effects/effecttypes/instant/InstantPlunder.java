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

import org.l2junity.gameserver.ai.CtrlEvent;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.MonsterInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.model.stats.Formulas;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

import java.util.Collection;

/**
 * @author Sdw
 */
public final class InstantPlunder extends AbstractEffect {
	public InstantPlunder(StatsSet params) {
	}

	@Override
	public boolean calcSuccess(Creature caster, WorldObject target, Skill skill) {
		return target.isMonster() && Formulas.calcMagicSuccess(caster, target.asMonster(), skill);
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		final MonsterInstance targetMonster = target.asMonster();
		if (targetMonster == null) {
			casterPlayer.sendPacket(SystemMessageId.INVALID_TARGET);
			return;
		}

		if (targetMonster.isDead()) {
			casterPlayer.sendPacket(SystemMessageId.INVALID_TARGET);
			return;
		}

		if (targetMonster.isSpoiled()) {
			casterPlayer.sendPacket(SystemMessageId.PLUNDER_SKILL_HAS_BEEN_ALREADY_USED_ON_THIS_TARGET);
			return;
		}

		targetMonster.setSpoilerObjectId(caster.getObjectId());
		if (targetMonster.isSweepActive()) {
			final Collection<ItemHolder> items = targetMonster.takeSweep();
			if (items != null) {
				for (ItemHolder sweepedItem : items) {
					final Party party = casterPlayer.getParty();
					if (party != null) {
						party.distributeItem(casterPlayer, sweepedItem, true, targetMonster);
					} else {
						casterPlayer.addItem("Sweeper", sweepedItem, targetMonster, true);
					}
				}
			}
		}
		targetMonster.getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, casterPlayer);
	}
}
