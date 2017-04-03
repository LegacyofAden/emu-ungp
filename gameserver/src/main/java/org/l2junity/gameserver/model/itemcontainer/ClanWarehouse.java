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
package org.l2junity.gameserver.model.itemcontainer;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanWHItemAdd;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanWHItemDestroy;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerClanWHItemTransfer;
import org.l2junity.gameserver.model.items.instance.ItemInstance;

public final class ClanWarehouse extends Warehouse {
	private final L2Clan _clan;

	public ClanWarehouse(L2Clan clan) {
		_clan = clan;
	}

	@Override
	public String getName() {
		return "ClanWarehouse";
	}

	@Override
	public int getOwnerId() {
		return _clan.getId();
	}

	@Override
	public Player getOwner() {
		return _clan.getLeader().getPlayerInstance();
	}

	@Override
	public ItemLocation getBaseLocation() {
		return ItemLocation.CLANWH;
	}

	@Override
	public boolean validateCapacity(long slots) {
		return ((_items.size() + slots) <= PlayerConfig.WAREHOUSE_SLOTS_CLAN);
	}

	@Override
	public ItemInstance addItem(String process, int itemId, long count, Player actor, Object reference) {
		final ItemInstance item = super.addItem(process, itemId, count, actor, reference);

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerClanWHItemAdd(process, actor, item, this), item.getItem());
		return item;
	}

	@Override
	public ItemInstance addItem(String process, ItemInstance item, Player actor, Object reference) {
		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerClanWHItemAdd(process, actor, item, this), item.getItem());
		return super.addItem(process, item, actor, reference);
	}

	@Override
	public ItemInstance destroyItem(String process, ItemInstance item, long count, Player actor, Object reference) {
		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerClanWHItemDestroy(process, actor, item, count, this), item.getItem());
		return super.destroyItem(process, item, count, actor, reference);
	}

	@Override
	public ItemInstance transferItem(String process, int objectId, long count, ItemContainer target, Player actor, Object reference) {
		final ItemInstance item = getItemByObjectId(objectId);

		// Notify to scripts
		EventDispatcher.getInstance().notifyEventAsync(new OnPlayerClanWHItemTransfer(process, actor, item, count, target), item.getItem());
		return super.transferItem(process, objectId, count, target, actor, reference);
	}
}
