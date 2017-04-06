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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.SpawnItem;
import org.l2junity.gameserver.network.packets.s2c.UserInfo;
import org.l2junity.network.PacketReader;

public class RequestRecordInfo extends GameClientPacket {
	@Override
	public void readImpl() {
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		getClient().sendPacket(new UserInfo(activeChar));

		activeChar.getWorld().forEachVisibleObject(activeChar, WorldObject.class, object ->
		{
			if (object.getPoly().isMorphed() && object.getPoly().getPolyType().equals("item")) {
				getClient().sendPacket(new SpawnItem(object));
			} else {
				if (object.isVisibleFor(activeChar)) {
					object.sendInfo(activeChar);

					if (object.isCreature()) {
						// Update the state of the L2Character object client
						// side by sending Server->Client packet
						// MoveToPawn/CharMoveToLocation and AutoAttackStart to
						// the L2PcInstance
						final Creature obj = (Creature) object;
						if (obj.getAI() != null) {
							obj.getAI().describeStateToPlayer(activeChar);
						}
					}
				}
			}
		});
	}
}
