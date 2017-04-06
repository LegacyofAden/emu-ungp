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
package org.l2junity.gameserver.network.packets.s2c;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.data.xml.impl.RecipeData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.RecipeHolder;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

public class RecipeShopManageList extends GameServerPacket {
	private final Player _seller;
	private final boolean _isDwarven;
	private final Collection<RecipeHolder> _recipes;
	private List<Entry<Integer, Long>> _manufacture;

	public RecipeShopManageList(Player seller, boolean isDwarven) {
		_seller = seller;
		_isDwarven = isDwarven;
		_recipes = (isDwarven && (_seller.getCreateItemLevel() > 0)) ? _seller.getDwarvenRecipeBook() : _seller.getCommonRecipeBook();

		if (_seller.hasManufactureShop()) {
			_manufacture = new ArrayList<>();
			for (Entry<Integer, Long> item : _seller.getManufactureItems().entrySet()) {
				final RecipeHolder recipe = RecipeData.getInstance().getRecipe(item.getKey());
				if (((recipe != null) && (recipe.isDwarvenRecipe() == _isDwarven)) && seller.hasRecipeList(recipe.getId())) {
					_manufacture.add(item);
				}
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.RECIPE_SHOP_MANAGE_LIST.writeId(body);

		body.writeD(_seller.getObjectId());
		body.writeD((int) _seller.getAdena());
		body.writeD(_isDwarven ? 0x00 : 0x01);

		if ((_recipes == null) || _recipes.isEmpty()) {
			body.writeD(0);
		} else {
			body.writeD(_recipes.size());// number of items in recipe book

			int i = 1;
			for (RecipeHolder recipe : _recipes) {
				body.writeD(recipe.getId());
				body.writeD(i++);
			}
		}

		if ((_manufacture == null) || _manufacture.isEmpty()) {
			body.writeD(0x00);
		} else {
			body.writeD(_manufacture.size());
			for (Entry<Integer, Long> item : _manufacture) {
				body.writeD(item.getKey());
				body.writeD(0x00); // CanCraft?
				body.writeQ(item.getValue());
			}
		}
	}
}
