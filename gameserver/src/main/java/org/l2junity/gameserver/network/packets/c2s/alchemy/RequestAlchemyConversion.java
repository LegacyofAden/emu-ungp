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
package org.l2junity.gameserver.network.packets.c2s.alchemy;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.data.xml.impl.AlchemyData;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.enums.Race;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.alchemy.AlchemyCraftData;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.alchemy.ExAlchemyConversion;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.taskmanager.AttackStanceTaskManager;
import org.l2junity.network.PacketReader;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sdw
 */
@Slf4j
public class RequestAlchemyConversion extends GameClientPacket {
	private int _craftTimes;
	private int _skillId;
	private int _skillLevel;

	private final Set<ItemHolder> _ingredients = new HashSet<>();

	@Override
	public void readImpl() {
		_craftTimes = readD();
		readH(); // TODO: Find me
		_skillId = readD();
		_skillLevel = readD();

		final int ingredientsSize = readD();
		for (int i = 0; i < ingredientsSize; i++) {
			_ingredients.add(new ItemHolder(readD(), readQ()));
		}
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if ((player == null) || (player.getRace() != Race.ERTHEIA)) {
			return;
		}

		if (AttackStanceTaskManager.getInstance().hasAttackStanceTask(player)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_USE_ALCHEMY_DURING_BATTLE);
			return;
		} else if (player.isInStoreMode() || (player.getPrivateStoreType() != PrivateStoreType.NONE)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_USE_ALCHEMY_WHILE_TRADING_OR_USING_A_PRIVATE_STORE_OR_SHOP);
			return;
		} else if (player.isDead()) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_USE_ALCHEMY_WHILE_DEAD);
			return;
		} else if (player.isMovementDisabled()) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_USE_ALCHEMY_WHILE_IMMOBILE);
			return;
		}

		final AlchemyCraftData data = AlchemyData.getInstance().getCraftData(_skillId, _skillLevel);
		if (data == null) {
			log.warn("Missing AlchemyData for skillId: {}, skillLevel: {}", _skillId, _skillLevel);
			return;
		}

		// TODO : Implement this
		// if (!_ingredients.equals(data.getIngredients()))
		// {
		// LOGGER.warn("Client ingredients are not same as server ingredients for alchemy conversion player: {}", player);
		// return;
		// }

		// TODO: Figure out the chance
		final int baseChance;
		switch (data.getGrade()) {
			case 1: // Elementary
			{
				baseChance = 100;
				break;
			}
			case 2: // Intermediate
			{
				baseChance = 80;
				break;
			}
			case 3: // Advanced
			{
				baseChance = 60;
				break;
			}
			default: // Master
			{
				baseChance = 50;
				break;
			}
		}

		int successCount = 0;
		int failureCount = 0;

		// Run _craftItems iteration of item craft
		final InventoryUpdate ui = new InventoryUpdate();
		CRAFTLOOP:
		for (int i = 0; i < _craftTimes; i++) {
			// for each tries, check if player have enough items and destroy
			for (ItemHolder ingredient : data.getIngredients()) {
				final ItemInstance item = player.getInventory().getItemByItemId(ingredient.getId());
				if (item == null) {
					break CRAFTLOOP;
				}
				if (item.getCount() < ingredient.getCount()) {
					break CRAFTLOOP;
				}

				player.getInventory().destroyItem("Alchemy", item, ingredient.getCount(), player, null);
				ui.addItem(item);
			}

			if (Rnd.get(100) < baseChance) {
				successCount++;
			} else {
				failureCount++;
			}
		}

		if (successCount > 0) {
			final ItemInstance item = player.getInventory().addItem("Alchemy", data.getProductionSuccess().getId(), data.getProductionSuccess().getCount() * successCount, player, null);
			ui.addItem(item);
		}

		if (failureCount > 0) {
			final ItemInstance item = player.getInventory().addItem("Alchemy", data.getProductionFailure().getId(), data.getProductionFailure().getCount() * failureCount, player, null);
			ui.addItem(item);
		}

		player.sendPacket(new ExAlchemyConversion(successCount, failureCount));
		player.sendInventoryUpdate(ui);
	}
}
