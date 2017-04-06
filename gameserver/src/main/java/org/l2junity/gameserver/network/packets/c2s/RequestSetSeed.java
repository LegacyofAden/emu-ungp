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

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.instancemanager.CastleManorManager;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.L2Seed;
import org.l2junity.gameserver.model.SeedProduction;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.network.PacketReader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author l3x
 */
public class RequestSetSeed extends GameClientPacket {
	private static final int BATCH_LENGTH = 20; // length of the one item

	private int _manorId;
	private List<SeedProduction> _items;

	@Override
	public void readImpl() {
		_manorId = readD();
		final int count = readD();
		if ((count <= 0) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}

		_items = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			final int itemId = readD();
			final long sales = readQ();
			final long price = readQ();
			if ((itemId < 1) || (sales < 0) || (price < 0)) {
				_items.clear();
				return;
			}

			if (sales > 0) {
				_items.add(new SeedProduction(itemId, sales, price, sales));
			}
		}
	}

	@Override
	public void runImpl() {
		if (_items.isEmpty()) {
			return;
		}

		final CastleManorManager manor = CastleManorManager.getInstance();
		if (!manor.isModifiablePeriod()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Check player privileges
		final Player player = getClient().getActiveChar();
		if ((player == null) || (player.getClan() == null) || (player.getClan().getCastleId() != _manorId) || !player.hasClanPrivilege(ClanPrivilege.CS_MANOR_ADMIN) || !player.getLastFolkNPC().canInteract(player)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Filter seeds with start amount lower than 0 and incorrect price
		final List<SeedProduction> list = new ArrayList<>(_items.size());
		for (SeedProduction sp : _items) {
			final L2Seed s = manor.getSeed(sp.getId());
			if ((s != null) && (sp.getStartAmount() <= s.getSeedLimit()) && (sp.getPrice() >= s.getSeedMinPrice()) && (sp.getPrice() <= s.getSeedMaxPrice())) {
				list.add(sp);
			}
		}

		// Save new list
		manor.setNextSeedProduction(list, _manorId);
	}
}