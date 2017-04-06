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
import org.l2junity.gameserver.network.packets.s2c.ExPutItemResultForVariationMake;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * Format:(ch) d
 *
 * @author -Wooden-
 */
public final class RequestConfirmTargetItem extends AbstractRefinePacket {
	private int _itemObjId;

	@Override
	public void readImpl() {
		_itemObjId = readD();

	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final ItemInstance item = activeChar.getInventory().getItemByObjectId(_itemObjId);
		if (item == null) {
			return;
		}

		if (!VariationData.getInstance().hasFeeData(item.getId())) {
			getClient().sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
			return;
		}

		if (!isValid(activeChar, item)) {
			// Different system message here
			if (item.isAugmented()) {
				getClient().sendPacket(SystemMessageId.ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN);
				return;
			}

			getClient().sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
			return;
		}

		getClient().sendPacket(new ExPutItemResultForVariationMake(_itemObjId, item.getId()));
	}
}
