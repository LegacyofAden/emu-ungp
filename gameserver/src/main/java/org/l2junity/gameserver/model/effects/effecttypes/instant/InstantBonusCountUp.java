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

import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.ExVoteSystemInfo;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.UserInfo;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * Give Recommendation effect implementation.
 *
 * @author NosBit
 */
public final class InstantBonusCountUp extends AbstractEffect {
	private final int _amount;

	public InstantBonusCountUp(StatsSet params) {
		_amount = params.getInt("amount", 0);
		if (_amount == 0) {
			throw new IllegalArgumentException("amount parameter is missing or set to 0.");
		}
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player targetPlayer = target.asPlayer();
		if (targetPlayer == null) {
			return;
		}

		int recommendationsGiven = _amount;
		if ((targetPlayer.getRecomHave() + _amount) >= 255) {
			recommendationsGiven = 255 - targetPlayer.getRecomHave();
		}

		if (recommendationsGiven > 0) {
			targetPlayer.setRecomHave(targetPlayer.getRecomHave() + recommendationsGiven);

			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_OBTAINED_S1_RECOMMENDATION_S);
			sm.addInt(recommendationsGiven);
			targetPlayer.sendPacket(sm);
			targetPlayer.sendPacket(new UserInfo(targetPlayer));
			targetPlayer.sendPacket(new ExVoteSystemInfo(targetPlayer));
		} else {
			final Player casterPlayer = caster.asPlayer();
			if (casterPlayer != null) {
				casterPlayer.sendPacket(SystemMessageId.NOTHING_HAPPENED);
			}
		}
	}
}
