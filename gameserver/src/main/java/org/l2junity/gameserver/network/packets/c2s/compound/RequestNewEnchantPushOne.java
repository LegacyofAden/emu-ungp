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
package org.l2junity.gameserver.network.packets.c2s.compound;

import org.l2junity.gameserver.data.xml.impl.CombinationItemsData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.CompoundRequest;
import org.l2junity.gameserver.model.items.combination.CombinationItem;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.compound.ExEnchantOneFail;
import org.l2junity.gameserver.network.packets.s2c.compound.ExEnchantOneOK;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.List;

/**
 * @author UnAfraid
 */
public class RequestNewEnchantPushOne extends GameClientPacket {
	private int _objectId;

	@Override
	public void readImpl() {
		_objectId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		} else if (activeChar.isInStoreMode()) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_DO_THAT_WHILE_IN_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP);
			getClient().sendPacket(ExEnchantOneFail.STATIC_PACKET);
			return;
		} else if (activeChar.isProcessingTransaction() || activeChar.isProcessingRequest()) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_USE_THIS_SYSTEM_DURING_TRADING_PRIVATE_STORE_AND_WORKSHOP_SETUP);
			getClient().sendPacket(ExEnchantOneFail.STATIC_PACKET);
			return;
		}

		CompoundRequest request = activeChar.getRequest(CompoundRequest.class);
		if (request == null) {
			request = new CompoundRequest(activeChar);
			if (!activeChar.addRequest(request)) {
				getClient().sendPacket(ExEnchantOneFail.STATIC_PACKET);
				return;
			}
		} else if (request.isProcessing()) {
			getClient().sendPacket(ExEnchantOneFail.STATIC_PACKET);
			return;
		}

		// Make sure player owns this item.
		request.setItemOne(_objectId);
		final ItemInstance itemOne = request.getItemOne();
		if (itemOne == null) {
			getClient().sendPacket(ExEnchantOneFail.STATIC_PACKET);
			activeChar.removeRequest(request.getClass());
			return;
		}

		final List<CombinationItem> combinationItems = CombinationItemsData.getInstance().getItemsByFirstSlot(itemOne.getId());

		// Not implemented or not able to merge!
		if (combinationItems.isEmpty()) {
			getClient().sendPacket(ExEnchantOneFail.STATIC_PACKET);
			activeChar.removeRequest(request.getClass());
			return;
		}

		getClient().sendPacket(ExEnchantOneOK.STATIC_PACKET);
	}
}
