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
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

/**
 * @author Migi, DS
 */
@Slf4j
public class ExReplySentPost extends AbstractItemPacket {
	private final Message _msg;
	private Collection<ItemInstance> _items = null;

	public ExReplySentPost(Message msg) {
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
		GameServerPacketType.EX_REPLY_SENT_POST.writeId(body);

		body.writeD(0x00); // GOD
		body.writeD(_msg.getId());
		body.writeD(_msg.isLocked() ? 1 : 0);
		body.writeS(_msg.getReceiverName());
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
		body.writeD(_msg.hasAttachments() ? 0x01 : 0x00);
		body.writeD(_msg.isReturned() ? 0x01 : 00);
	}
}
