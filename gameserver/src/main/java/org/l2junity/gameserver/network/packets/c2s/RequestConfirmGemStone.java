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

import org.l2junity.gameserver.data.xml.impl.VariationData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.options.VariationFee;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.s2c.ExPutCommissionResultForVariationMake;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * Format:(ch) dddd
 *
 * @author -Wooden-
 */
public final class RequestConfirmGemStone extends AbstractRefinePacket {
	private int _targetItemObjId;
	private int _mineralItemObjId;
	private int _feeItemObjId;
	private long _feeCount;

	@Override
	public void readImpl() {
		_targetItemObjId = readD();
		_mineralItemObjId = readD();
		_feeItemObjId = readD();
		_feeCount = readQ();

	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final ItemInstance targetItem = activeChar.getInventory().getItemByObjectId(_targetItemObjId);
		if (targetItem == null) {
			return;
		}

		final ItemInstance refinerItem = activeChar.getInventory().getItemByObjectId(_mineralItemObjId);
		if (refinerItem == null) {
			return;
		}

		final ItemInstance gemStoneItem = activeChar.getInventory().getItemByObjectId(_feeItemObjId);
		if (gemStoneItem == null) {
			return;
		}

		final VariationFee fee = VariationData.getInstance().getFee(targetItem.getId(), refinerItem.getId());
		if (!isValid(activeChar, targetItem, refinerItem, gemStoneItem, fee)) {
			getClient().sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
			return;
		}

		// Check for fee count
		if (_feeCount != fee.getItemCount()) {
			getClient().sendPacket(SystemMessageId.GEMSTONE_QUANTITY_IS_INCORRECT);
			return;
		}

		getClient().sendPacket(new ExPutCommissionResultForVariationMake(_feeItemObjId, _feeCount, gemStoneItem.getId()));
	}
}
