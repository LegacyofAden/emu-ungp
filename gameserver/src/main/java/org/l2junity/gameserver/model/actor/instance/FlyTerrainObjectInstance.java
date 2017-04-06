/*
 * Copyright (C) 2004-2015 L2J Unity
 * 
 * This file is part of L2J Unity.
 * 
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2junity.gameserver.model.actor.instance;

import org.l2junity.gameserver.enums.InstanceType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.templates.NpcTemplate;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;

public final class FlyTerrainObjectInstance extends Npc {
	public FlyTerrainObjectInstance(NpcTemplate template) {
		super(template);
		setInstanceType(InstanceType.L2FlyTerrainObjectInstance);
		setIsFlying(true);
	}

	@Override
	public void onAction(Player player, boolean interact) {
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}

	@Override
	public void onActionShift(Player player) {
		if (player.isGM()) {
			super.onActionShift(player);
		} else {
			player.sendPacket(ActionFailed.STATIC_PACKET);
		}
	}
}