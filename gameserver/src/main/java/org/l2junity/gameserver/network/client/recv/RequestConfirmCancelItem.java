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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.VariationData;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExPutItemResultForVariationCancel;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

/**
 * Format(ch) d
 *
 * @author -Wooden-
 */
public final class RequestConfirmCancelItem implements IClientIncomingPacket {
	private int _objectId;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_objectId = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final PlayerInstance activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}
		final ItemInstance item = activeChar.getInventory().getItemByObjectId(_objectId);
		if (item == null) {
			return;
		}

		if (item.getOwnerId() != activeChar.getObjectId()) {
			Util.handleIllegalPlayerAction(client.getActiveChar(), "Warning!! Character " + client.getActiveChar().getName() + " of account " + client.getActiveChar().getAccountName() + " tryied to destroy augment on item that doesn't own.", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (!item.isAugmented()) {
			activeChar.sendPacket(SystemMessageId.AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM);
			return;
		}

		if (item.isPvp() && !PlayerConfig.ALT_ALLOW_AUGMENT_PVP_ITEMS) {
			activeChar.sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
			return;
		}

		final long price = VariationData.getInstance().getCancelFee(item.getId(), item.getAugmentation().getMineralId());
		if (price < 0) {
			activeChar.sendPacket(SystemMessageId.THIS_IS_NOT_A_SUITABLE_ITEM);
			return;
		}

		activeChar.sendPacket(new ExPutItemResultForVariationCancel(item, price));
	}
}
