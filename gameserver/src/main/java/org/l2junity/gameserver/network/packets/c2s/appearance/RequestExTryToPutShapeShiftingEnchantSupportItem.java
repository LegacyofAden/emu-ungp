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
package org.l2junity.gameserver.network.packets.c2s.appearance;

import org.l2junity.gameserver.data.xml.impl.AppearanceItemData;
import org.l2junity.gameserver.enums.ItemLocation;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.ShapeShiftingItemRequest;
import org.l2junity.gameserver.model.itemcontainer.PcInventory;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.items.appearance.AppearanceStone;
import org.l2junity.gameserver.model.items.appearance.AppearanceType;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.appearance.ExPutShapeShiftingExtractionItemResult;
import org.l2junity.gameserver.network.packets.s2c.appearance.ExPutShapeShiftingTargetItemResult;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * @author UnAfraid
 */
public class RequestExTryToPutShapeShiftingEnchantSupportItem extends GameClientPacket {
	private int _targetItemObjId;
	private int _extracItemObjId;

	@Override
	public void readImpl() {
		_targetItemObjId = readD();
		_extracItemObjId = readD();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final ShapeShiftingItemRequest request = player.getRequest(ShapeShiftingItemRequest.class);

		if (player.isInStoreMode() || player.isCrafting() || player.isProcessingRequest() || player.isProcessingTransaction() || (request == null)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_USE_THIS_SYSTEM_DURING_TRADING_PRIVATE_STORE_AND_WORKSHOP_SETUP);
			return;
		}

		final PcInventory inventory = player.getInventory();
		final ItemInstance targetItem = inventory.getItemByObjectId(_targetItemObjId);
		final ItemInstance extractItem = inventory.getItemByObjectId(_extracItemObjId);
		ItemInstance stone = request.getAppearanceStone();
		if ((targetItem == null) || (extractItem == null) || (stone == null)) {
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if ((stone.getOwnerId() != player.getObjectId()) || (targetItem.getOwnerId() != player.getObjectId()) || (extractItem.getOwnerId() != player.getObjectId())) {
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if (!extractItem.getItem().isAppearanceable()) {
			player.sendPacket(SystemMessageId.THIS_ITEM_DOES_NOT_MEET_REQUIREMENTS);
			getClient().sendPacket(ExPutShapeShiftingExtractionItemResult.FAILED);
			return;
		}

		if ((extractItem.getItemLocation() != ItemLocation.INVENTORY) && (extractItem.getItemLocation() != ItemLocation.PAPERDOLL)) {
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if ((stone = inventory.getItemByObjectId(stone.getObjectId())) == null) {
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}
		final AppearanceStone appearanceStone = AppearanceItemData.getInstance().getStone(stone.getId());
		if (appearanceStone == null) {
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if ((appearanceStone.getType() == AppearanceType.RESTORE) || (appearanceStone.getType() == AppearanceType.FIXED)) {
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if (extractItem.getVisualId() > 0) {
			getClient().sendPacket(ExPutShapeShiftingExtractionItemResult.FAILED);
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_EXTRACT_FROM_A_MODIFIED_ITEM);
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if ((extractItem.getItemLocation() != ItemLocation.INVENTORY) && (extractItem.getItemLocation() != ItemLocation.PAPERDOLL)) {
			getClient().sendPacket(ExPutShapeShiftingExtractionItemResult.FAILED);
			player.removeRequest(ShapeShiftingItemRequest.class);
			return;
		}

		if ((extractItem.getItemType() != targetItem.getItemType()) || (extractItem.getId() == targetItem.getId()) || (extractItem.getObjectId() == targetItem.getObjectId())) {
			player.sendPacket(SystemMessageId.THIS_ITEM_DOES_NOT_MEET_REQUIREMENTS);
			getClient().sendPacket(ExPutShapeShiftingExtractionItemResult.FAILED);
			return;
		}

		if ((extractItem.getItem().getBodyPart() != targetItem.getItem().getBodyPart()) && ((extractItem.getItem().getBodyPart() != ItemTemplate.SLOT_FULL_ARMOR) || (targetItem.getItem().getBodyPart() != ItemTemplate.SLOT_CHEST))) {
			player.sendPacket(SystemMessageId.THIS_ITEM_DOES_NOT_MEET_REQUIREMENTS);
			getClient().sendPacket(ExPutShapeShiftingExtractionItemResult.FAILED);
			return;
		}

		if (extractItem.getItem().getCrystalType().isGreater(targetItem.getItem().getCrystalType())) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_EXTRACT_FROM_ITEMS_THAT_ARE_HIGHER_GRADE_THAN_ITEMS_TO_BE_MODIFIED);
			player.sendPacket(ExPutShapeShiftingExtractionItemResult.FAILED);
			return;
		}

		if (!appearanceStone.checkConditions(player, targetItem)) {
			player.sendPacket(ExPutShapeShiftingTargetItemResult.FAILED);
			return;
		}

		request.setAppearanceExtractItem(extractItem);
		getClient().sendPacket(ExPutShapeShiftingExtractionItemResult.SUCCESS);
	}
}
