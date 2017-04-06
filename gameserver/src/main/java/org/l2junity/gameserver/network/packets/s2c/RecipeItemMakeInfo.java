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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.data.xml.impl.RecipeData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.RecipeHolder;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

@Slf4j
public class RecipeItemMakeInfo extends GameServerPacket {
	private final int _id;
	private final Player _activeChar;
	private final Boolean _success;
	private final long _offeringMaximumAdena;

	public RecipeItemMakeInfo(int id, Player player, boolean success, long offeringMaximumAdena) {
		_id = id;
		_activeChar = player;
		_success = success;
		_offeringMaximumAdena = offeringMaximumAdena;
	}

	public RecipeItemMakeInfo(int id, Player player, boolean success) {
		_id = id;
		_activeChar = player;
		_success = success;
		_offeringMaximumAdena = 0;
	}

	public RecipeItemMakeInfo(int id, Player player, long offeringMaximumAdena) {
		_id = id;
		_activeChar = player;
		_success = null;
		_offeringMaximumAdena = offeringMaximumAdena;
	}

	public RecipeItemMakeInfo(int id, Player player) {
		_id = id;
		_activeChar = player;
		_success = null;
		_offeringMaximumAdena = 0;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		final RecipeHolder recipe = RecipeData.getInstance().getRecipe(_id);
		if (recipe == null) {
			log.info("Character: {}: Requested unexisting recipe with id = {}", _activeChar, _id);
			return;
		}
		GameServerPacketType.RECIPE_ITEM_MAKE_INFO.writeId(body);
		body.writeD(_id);
		body.writeD(recipe.isDwarvenRecipe() ? 0 : 1); // 0 = Dwarven - 1 = Common
		body.writeD((int) _activeChar.getCurrentMp());
		body.writeD(_activeChar.getMaxMp());
		body.writeD(_success == null ? -1 : (_success ? 1 : 0)); // item creation none/success/failed
		body.writeC(_offeringMaximumAdena > 0 ? 1 : 0); // Show offering window.
		body.writeQ(_offeringMaximumAdena); // Adena worth of items for maximum offering.
	}
}