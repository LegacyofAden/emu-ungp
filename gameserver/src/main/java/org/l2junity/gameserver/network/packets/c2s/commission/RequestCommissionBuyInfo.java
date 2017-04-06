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

import org.l2junity.gameserver.instancemanager.CommissionManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.commission.CommissionItem;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.commission.ExCloseCommission;
import org.l2junity.gameserver.network.packets.s2c.commission.ExResponseCommissionBuyInfo;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * @author NosBit
 */
public class RequestCommissionBuyInfo extends GameClientPacket {
	private long _commissionId;

	@Override
	public void readImpl() {
		_commissionId = readQ();
		// readD(); // CommissionItemType
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (!CommissionManager.isPlayerAllowedToInteract(player)) {
			getClient().sendPacket(ExCloseCommission.STATIC_PACKET);
			return;
		}

		if (!player.isInventoryUnder80(false) || (player.getWeightPenalty() >= 3)) {
			getClient().sendPacket(SystemMessageId.IF_THE_WEIGHT_IS_80_OR_MORE_AND_THE_INVENTORY_NUMBER_IS_90_OR_MORE_PURCHASE_CANCELLATION_IS_NOT_POSSIBLE);
			getClient().sendPacket(ExResponseCommissionBuyInfo.FAILED);
			return;
		}

		final CommissionItem commissionItem = CommissionManager.getInstance().getCommissionItem(_commissionId);
		if (commissionItem != null) {
			getClient().sendPacket(new ExResponseCommissionBuyInfo(commissionItem));
		} else {
			getClient().sendPacket(SystemMessageId.ITEM_PURCHASE_IS_NOT_AVAILABLE_BECAUSE_THE_CORRESPONDING_ITEM_DOES_NOT_EXIST);
			getClient().sendPacket(ExResponseCommissionBuyInfo.FAILED);
		}
	}
}