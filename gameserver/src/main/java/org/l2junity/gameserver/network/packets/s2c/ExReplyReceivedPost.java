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
import org.l2junity.gameserver.enums.MailType;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.Collection;

/**
 * @author Migi, DS
 */
@Slf4j
public class ExReplyReceivedPost extends AbstractItemPacket {
	private final Message _msg;
	private Collection<ItemInstance> _items = null;

	public ExReplyReceivedPost(Message msg) {
		_msg = msg;
		if (msg.hasAttachments()) {
			final ItemContainer attachments = msg.getAttachments();
			if ((attachments != null) && (attachments.getSize() > 0)) {
				_items = attachments.getItems();
			} else {
				log.warn("Message " + msg.getId() + " has attachments but itemcontainer is empty.");
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_REPLY_RECEIVED_POST.writeId(body);

		body.writeD(_msg.getMailType().ordinal()); // GOD
		if (_msg.getMailType() == MailType.COMMISSION_ITEM_RETURNED) {
			body.writeD(SystemMessageId.THE_REGISTRATION_PERIOD_FOR_THE_ITEM_YOU_REGISTERED_HAS_EXPIRED.getId());
			body.writeD(SystemMessageId.THE_AUCTION_HOUSE_REGISTRATION_PERIOD_HAS_EXPIRED_AND_THE_CORRESPONDING_ITEM_IS_BEING_FORWARDED.getId());
		} else if (_msg.getMailType() == MailType.COMMISSION_ITEM_SOLD) {
			body.writeD(_msg.getItemId());
			body.writeD(_msg.getEnchantLvl());
			for (int i = 0; i < 6; i++) {
				body.writeD(_msg.getElementals()[i]);
			}
			body.writeD(SystemMessageId.THE_ITEM_YOU_REGISTERED_HAS_BEEN_SOLD.getId());
			body.writeD(SystemMessageId.S1_HAS_BEEN_SOLD.getId());
		}
		body.writeD(_msg.getId());
		body.writeD(_msg.isLocked() ? 1 : 0);
		body.writeD(0x00); // Unknown
		body.writeS(_msg.getSenderName());
		body.writeS(_msg.getSubject());
		body.writeS(_msg.getContent());

		if ((_items != null) && !_items.isEmpty()) {
			body.writeD(_items.size());
			for (ItemInstance item : _items) {
				writeItem(body, item);
				body.writeD(item.getObjectId());
			}
		} else {
			body.writeD(0x00);
		}

		body.writeQ(_msg.getReqAdena());
		body.writeD(_msg.hasAttachments() ? 1 : 0);
		body.writeD(_msg.isReturned() ? 1 : 0);
	}
}