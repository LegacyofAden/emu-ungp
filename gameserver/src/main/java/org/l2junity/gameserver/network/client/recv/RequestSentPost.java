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
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExReplySentPost;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

/**
 * @author Migi, DS
 */
public final class RequestSentPost implements IClientIncomingPacket {
	private int _msgId;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_msgId = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if ((activeChar == null) || !GeneralConfig.ALLOW_MAIL) {
			return;
		}

		Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null) {
			return;
		}

		if (!activeChar.isInsideZone(ZoneId.PEACE) && msg.hasAttachments()) {
			client.sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_OR_SEND_MAIL_WITH_ATTACHED_ITEMS_IN_NON_PEACE_ZONE_REGIONS);
			return;
		}

		if (msg.getSenderId() != activeChar.getObjectId()) {
			Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to read not own post!", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (msg.isDeletedBySender()) {
			return;
		}

		client.sendPacket(new ExReplySentPost(msg));
	}
}
