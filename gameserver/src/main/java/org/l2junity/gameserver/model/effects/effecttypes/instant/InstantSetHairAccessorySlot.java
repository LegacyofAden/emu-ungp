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

import org.l2junity.gameserver.enums.UserInfoType;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * @author UnAfraid
 */
public class InstantSetHairAccessorySlot extends AbstractEffect {
	public InstantSetHairAccessorySlot(StatsSet params) {
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		if (casterPlayer.isAlikeDead()) {
			return;
		}

		casterPlayer.setHairAccessoryEnabled(!casterPlayer.isHairAccessoryEnabled());
		casterPlayer.broadcastUserInfo(UserInfoType.APPAREANCE);
		casterPlayer.sendPacket(casterPlayer.isHairAccessoryEnabled() ? SystemMessageId.HAIR_ACCESSORIES_WILL_BE_DISPLAYED_FROM_NOW_ON : SystemMessageId.HAIR_ACCESSORIES_WILL_NO_LONGER_BE_DISPLAYED);
	}
}