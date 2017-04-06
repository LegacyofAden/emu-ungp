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
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.RecipeShopItemInfo;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


public final class RequestRecipeShopMakeInfo extends GameClientPacket {
	private int _playerObjectId;
	private int _recipeId;

	@Override
	public void readImpl() {
		_playerObjectId = readD();
		_recipeId = readD();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final Player manufacturer = player.getWorld().getPlayer(_playerObjectId);
		if ((manufacturer == null) || (manufacturer.getPrivateStoreType() != PrivateStoreType.MANUFACTURE)) {
			return;
		}

		final RecipeHolder recipe = RecipeData.getInstance().getRecipe(_recipeId);
		if (recipe == null) {
			player.sendPacket(SystemMessageId.THE_RECIPE_IS_INCORRECT);
			return;
		}

		final Long manufactureRecipeCost = manufacturer.getManufactureItems().get(_recipeId);
		if (manufactureRecipeCost == null) {
			return;
		}

		getClient().sendPacket(new RecipeShopItemInfo(manufacturer, _recipeId, manufactureRecipeCost, recipe.getMaxOffering()));
	}
}
