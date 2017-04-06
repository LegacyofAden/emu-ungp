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

import org.l2junity.commons.util.CommonUtil;
import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.data.xml.impl.CombinationItemsData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.CompoundRequest;
import org.l2junity.gameserver.model.debugger.DebugType;
import org.l2junity.gameserver.model.items.combination.CombinationItem;
import org.l2junity.gameserver.model.items.combination.CombinationItemReward;
import org.l2junity.gameserver.model.items.combination.CombinationItemType;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.compound.ExEnchantFail;
import org.l2junity.gameserver.network.packets.s2c.compound.ExEnchantOneFail;
import org.l2junity.gameserver.network.packets.s2c.compound.ExEnchantSucess;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * @author UnAfraid
 */
public class RequestNewEnchantTry extends GameClientPacket {
	@Override
	public void readImpl() {
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

		final CompoundRequest request = activeChar.getRequest(CompoundRequest.class);
		if ((request == null) || request.isProcessing()) {
			getClient().sendPacket(ExEnchantFail.STATIC_PACKET);
			return;
		}

		request.setProcessing(true);

		final ItemInstance itemOne = request.getItemOne();
		final ItemInstance itemTwo = request.getItemTwo();
		if ((itemOne == null) || (itemTwo == null)) {
			getClient().sendPacket(ExEnchantFail.STATIC_PACKET);
			activeChar.removeRequest(request.getClass());
			return;
		}

		// Lets prevent using same item twice
		if (itemOne.getObjectId() == itemTwo.getObjectId()) {
			getClient().sendPacket(new ExEnchantFail(itemOne.getId(), itemTwo.getId()));
			activeChar.removeRequest(request.getClass());
			return;
		}

		final CombinationItem combinationItem = CombinationItemsData.getInstance().getItemsBySlots(itemOne.getId(), itemTwo.getId());

		// Not implemented or not able to merge!
		if (combinationItem == null) {
			getClient().sendPacket(new ExEnchantFail(itemOne.getId(), itemTwo.getId()));
			activeChar.removeRequest(request.getClass());
			return;
		}

		final InventoryUpdate iu = new InventoryUpdate();
		iu.addRemovedItem(itemOne);
		iu.addRemovedItem(itemTwo);

		if (activeChar.destroyItem("Compound-Item-One", itemOne, 1, null, true) && activeChar.destroyItem("Compound-Item-Two", itemTwo, 1, null, true)) {
			final double random = (Rnd.nextDouble() * 100);
			final boolean success = random <= combinationItem.getChance();
			final CombinationItemReward rewardItem = combinationItem.getReward(success ? CombinationItemType.ON_SUCCESS : CombinationItemType.ON_FAILURE);
			final ItemInstance item = activeChar.addItem("Compound-Result", rewardItem.getId(), rewardItem.getCount(), null, true);

			if (success) {
				getClient().sendPacket(new ExEnchantSucess(item.getId()));
			} else {
				getClient().sendPacket(new ExEnchantFail(itemOne.getId(), itemTwo.getId()));
			}
			activeChar.sendDebugMessage("Random: " + CommonUtil.formatDouble(random, "#.##") + " chance: " + combinationItem.getChance() + " success: " + success, DebugType.ITEMS);
		}

		activeChar.sendInventoryUpdate(iu);
		activeChar.removeRequest(request.getClass());
	}
}
