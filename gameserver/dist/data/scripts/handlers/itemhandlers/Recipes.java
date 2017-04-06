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

import org.l2junity.gameserver.data.xml.impl.RecipeData;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.handler.IItemHandler;
import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.model.actor.Playable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.RecipeHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * @author Zoey76
 */
public class Recipes implements IItemHandler {
	@Override
	public boolean useItem(Playable playable, ItemInstance item, boolean forceUse) {
		if (!playable.isPlayer()) {
			playable.sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_THIS_ITEM);
			return false;
		}

		final Player activeChar = playable.getActingPlayer();
		if ((activeChar.getPrivateStoreType() == PrivateStoreType.MANUFACTURE) || activeChar.isCrafting()) {
			activeChar.sendPacket(SystemMessageId.YOU_MAY_NOT_ALTER_YOUR_RECIPE_BOOK_WHILE_ENGAGED_IN_MANUFACTURING);
			return false;
		}

		final RecipeHolder rp = RecipeData.getInstance().getRecipeByRecipeItemId(item.getId());
		if (rp == null) {
			activeChar.sendPacket(SystemMessageId.THE_RECIPE_IS_INCORRECT);
			return false;
		}

		if (activeChar.hasRecipeList(rp.getId())) {
			activeChar.sendPacket(SystemMessageId.THAT_RECIPE_IS_ALREADY_REGISTERED);
			return false;
		}

		boolean canCraft = false;
		boolean recipeLevel = false;
		boolean recipeLimit = false;
		if (rp.isDwarvenRecipe()) {
			canCraft = activeChar.getCreateItemLevel() > 0;
			recipeLevel = (rp.getLevel() > activeChar.getCreateItemLevel());
			recipeLimit = (activeChar.getDwarvenRecipeBook().size() >= activeChar.getDwarfRecipeLimit());
		} else {
			canCraft = activeChar.getCreateCommonItemLevel() > 0;
			recipeLevel = (rp.getLevel() > activeChar.getCreateCommonItemLevel());
			recipeLimit = (activeChar.getCommonRecipeBook().size() >= activeChar.getCommonRecipeLimit());
		}

		if (!canCraft) {
			activeChar.sendPacket(SystemMessageId.THE_RECIPE_CANNOT_BE_REGISTERED_YOU_DO_NOT_HAVE_THE_ABILITY_TO_CREATE_ITEMS);
			return false;
		}

		if (recipeLevel) {
			activeChar.sendPacket(SystemMessageId.YOUR_CREATE_ITEM_LEVEL_IS_TOO_LOW_TO_REGISTER_THIS_RECIPE);
			return false;
		}

		if (recipeLimit) {
			final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.UP_TO_S1_RECIPES_CAN_BE_REGISTERED);
			sm.addInt(rp.isDwarvenRecipe() ? activeChar.getDwarfRecipeLimit() : activeChar.getCommonRecipeLimit());
			activeChar.sendPacket(sm);
			return false;
		}

		if (rp.isDwarvenRecipe()) {
			activeChar.registerDwarvenRecipeList(rp, true);
		} else {
			activeChar.registerCommonRecipeList(rp, true);
		}

		activeChar.destroyItem("Consume", item.getObjectId(), 1, null, false);
		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_BEEN_ADDED);
		sm.addItemName(item);
		activeChar.sendPacket(sm);
		return true;
	}

	public static void main(String[] args) {
		ItemHandler.getInstance().registerHandler(new Recipes());
	}
}