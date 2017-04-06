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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.data.xml.impl.VariationData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExVariationCancelResult;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


/**
 * Format(ch) d
 *
 * @author -Wooden-
 */
public final class RequestRefineCancel extends GameClientPacket {
	private int _targetItemObjId;

	@Override
	public void readImpl() {
		_targetItemObjId = readD();
	}

	@Override
	public void runImpl() {
		Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final ItemInstance targetItem = activeChar.getInventory().getItemByObjectId(_targetItemObjId);
		if (targetItem == null) {
			getClient().sendPacket(ExVariationCancelResult.STATIC_PACKET_FAILURE);
			return;
		}

		if (targetItem.getOwnerId() != activeChar.getObjectId()) {
			Util.handleIllegalPlayerAction(getClient().getActiveChar(), "Warning!! Character " + getClient().getActiveChar().getName() + " of account " + getClient().getActiveChar().getAccountName() + " tryied to augment item that doesn't own.", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		// cannot remove augmentation from a not augmented item
		if (!targetItem.isAugmented()) {
			getClient().sendPacket(SystemMessageId.AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM);
			getClient().sendPacket(ExVariationCancelResult.STATIC_PACKET_FAILURE);
			return;
		}

		// get the price
		final long price = VariationData.getInstance().getCancelFee(targetItem.getId(), targetItem.getAugmentation().getMineralId());
		if (price < 0) {
			getClient().sendPacket(ExVariationCancelResult.STATIC_PACKET_FAILURE);
			return;
		}

		// try to reduce the players adena
		if (!activeChar.reduceAdena("RequestRefineCancel", price, targetItem, true)) {
			getClient().sendPacket(ExVariationCancelResult.STATIC_PACKET_FAILURE);
			getClient().sendPacket(SystemMessageId.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			return;
		}

		// unequip item
		if (targetItem.isEquipped()) {
			activeChar.unequipWeapon();
		}

		// remove the augmentation
		targetItem.removeAugmentation();

		// send ExVariationCancelResult
		getClient().sendPacket(ExVariationCancelResult.STATIC_PACKET_SUCCESS);

		// send inventory update
		InventoryUpdate iu = new InventoryUpdate();
		iu.addModifiedItem(targetItem);
		activeChar.sendInventoryUpdate(iu);
	}
}
