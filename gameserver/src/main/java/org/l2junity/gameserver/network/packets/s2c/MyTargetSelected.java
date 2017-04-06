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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.ControllableAirShipInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * MyTargetSelected server packet implementation.
 *
 * @author UnAfraid
 */
public class MyTargetSelected extends GameServerPacket {
	private final int _objectId;
	private final int _color;

	/**
	 * @param player
	 * @param target
	 */
	public MyTargetSelected(Player player, Creature target) {
		_objectId = (target instanceof ControllableAirShipInstance) ? ((ControllableAirShipInstance) target).getHelmObjectId() : target.getObjectId();
		_color = target.isAutoAttackable(player) ? (player.getLevel() - target.getLevel()) : 0;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.MY_TARGET_SELECTED.writeId(body);

		body.writeD(_objectId);
		body.writeH(_color);
		body.writeD(0x00);
	}
}