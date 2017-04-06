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
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author Migi, DS
 */
public class ExShowSentPostList extends GameServerPacket {
	private final List<Message> _outbox;

	public ExShowSentPostList(int objectId) {
		_outbox = MailManager.getInstance().getOutbox(objectId);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_SHOW_SENT_POST_LIST.writeId(body);

		body.writeD((int) (System.currentTimeMillis() / 1000));
		if ((_outbox != null) && (_outbox.size() > 0)) {
			body.writeD(_outbox.size());
			for (Message msg : _outbox) {
				body.writeD(msg.getId());
				body.writeS(msg.getSubject());
				body.writeS(msg.getReceiverName());
				body.writeD(msg.isLocked() ? 0x01 : 0x00);
				body.writeD(msg.getExpirationSeconds());
				body.writeD(msg.isUnread() ? 0x01 : 0x00);
				body.writeD(0x01);
				body.writeD(msg.hasAttachments() ? 0x01 : 0x00);
				body.writeD(0x00);
			}
		} else {
			body.writeD(0x00);
		}
	}
}
