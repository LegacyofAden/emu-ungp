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

import org.l2junity.gameserver.data.xml.impl.RecipeData;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.RecipeHolder;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.RecipeBookItemList;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestRecipeBookDestroy extends GameClientPacket {
	private int _recipeID;

	@Override
	public void readImpl() {
		_recipeID = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("RecipeDestroy")) {
			return;
		}

		if ((activeChar.getPrivateStoreType() == PrivateStoreType.MANUFACTURE) || activeChar.isCrafting()) {
			activeChar.sendPacket(SystemMessageId.YOU_MAY_NOT_ALTER_YOUR_RECIPE_BOOK_WHILE_ENGAGED_IN_MANUFACTURING);
			return;
		}

		final RecipeHolder rp = RecipeData.getInstance().getRecipe(_recipeID);
		if (rp == null) {
			getClient().sendPacket(SystemMessageId.THE_RECIPE_IS_INCORRECT);
			return;
		}

		// Remove the recipe from the list.
		activeChar.unregisterRecipeList(_recipeID);

		// Send the new recipe book.
		final RecipeBookItemList response = new RecipeBookItemList(activeChar, rp.isDwarvenRecipe());
		activeChar.sendPacket(response);
	}
}