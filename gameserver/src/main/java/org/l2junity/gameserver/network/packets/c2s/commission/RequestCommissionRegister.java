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
package org.l2junity.gameserver.network.packets.c2s.commission;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.instancemanager.CommissionManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.commission.ExCloseCommission;


/**
 * @author NosBit
 */
@Slf4j
public class RequestCommissionRegister extends GameClientPacket {
	private int _itemObjectId;
	private long _pricePerUnit;
	private long _itemCount;
	private int _durationType; // -1 = None, 0 = 1 Day, 1 = 3 Days, 2 = 5 Days, 3 = 7 Days

	@Override
	public void readImpl() {
		_itemObjectId = readD();
		readS(); // Item Name they use it for search we will use server side available names.
		_pricePerUnit = readQ();
		_itemCount = readQ();
		_durationType = readD();
		// readD(); // Unknown
		// readD(); // Unknown
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if ((_durationType < 0) || (_durationType > 3)) {
			log.warn("Player " + player + " sent incorrect commission duration type: " + _durationType + ".");
			return;
		}

		if (!CommissionManager.isPlayerAllowedToInteract(player)) {
			getClient().sendPacket(ExCloseCommission.STATIC_PACKET);
			return;
		}

		CommissionManager.getInstance().registerItem(player, _itemObjectId, _itemCount, _pricePerUnit, (byte) ((_durationType * 2) + 1));
	}
}
