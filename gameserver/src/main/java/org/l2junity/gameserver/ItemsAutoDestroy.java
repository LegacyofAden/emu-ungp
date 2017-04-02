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
package org.l2junity.gameserver;

import lombok.Getter;
import org.l2junity.commons.threading.AbstractPeriodicTaskManager;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.instancemanager.ItemsOnGroundManager;
import org.l2junity.gameserver.model.items.instance.ItemInstance;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@StartupComponent("Service")
public final class ItemsAutoDestroy extends AbstractPeriodicTaskManager {
	@Getter(lazy = true)
	private static final ItemsAutoDestroy instance = new ItemsAutoDestroy();

	private static final List<ItemInstance> _items = new LinkedList<>();

	private ItemsAutoDestroy() {
		super(5000);
	}

	public synchronized void addItem(ItemInstance item) {
		item.setDropTime(System.currentTimeMillis());
		_items.add(item);
	}

	@Override
	public void run() {
		if (GeneralConfig.AUTODESTROY_ITEM_AFTER == 0 && GeneralConfig.HERB_AUTO_DESTROY_TIME == 0) {
			return;
		}

		if (_items.isEmpty()) {
			return;
		}

		long curtime = System.currentTimeMillis();
		Iterator<ItemInstance> itemIterator = _items.iterator();
		while (itemIterator.hasNext()) {
			final ItemInstance item = itemIterator.next();
			if ((item.getDropTime() == 0) || (item.getItemLocation() != ItemLocation.VOID)) {
				itemIterator.remove();
			} else {
				final long autoDestroyTime;
				if (item.getItem().getAutoDestroyTime() > 0) {
					autoDestroyTime = item.getItem().getAutoDestroyTime();
				} else if (item.getItem().hasExImmediateEffect()) {
					autoDestroyTime = GeneralConfig.HERB_AUTO_DESTROY_TIME;
				} else {
					autoDestroyTime = ((GeneralConfig.AUTODESTROY_ITEM_AFTER == 0) ? 3600000 : GeneralConfig.AUTODESTROY_ITEM_AFTER * 1000);
				}

				if ((curtime - item.getDropTime()) > autoDestroyTime) {
					item.decayMe();
					itemIterator.remove();
					if (GeneralConfig.SAVE_DROPPED_ITEM) {
						ItemsOnGroundManager.getInstance().removeObject(item);
					}
				}
			}
		}
	}
}