/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.itemhandlers;

import org.l2junity.gameserver.handler.IItemHandler;
import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.EnchantItemRequest;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.s2c.ChooseInventoryItem;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

public class EnchantScrolls implements IItemHandler {
	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean forceUse) {
		if (!playable.isPlayer()) {
			playable.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_THIS_ITEM);
			return false;
		}

		final Player activeChar = playable.getActingPlayer();
		if (activeChar.isCastingNow()) {
			return false;
		}

		if (activeChar.hasItemRequest()) {
			activeChar.sendPacket(SystemMessageId.ANOTHER_ENCHANTMENT_IS_IN_PROGRESS_PLEASE_COMPLETE_THE_PREVIOUS_TASK_THEN_TRY_AGAIN);
			return false;
		}

		activeChar.addRequest(new EnchantItemRequest(activeChar, item.getObjectId()));
		activeChar.sendPacket(new ChooseInventoryItem(item.getId()));
		return true;
	}

	public static void main(String[] args) {
		ItemHandler.getInstance().registerHandler(new EnchantScrolls());
	}
}