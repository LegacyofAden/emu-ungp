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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExChangePostState;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


/**
 * @author Migi, DS
 */
public final class RequestDeleteSentPost extends GameClientPacket {
	private static final int BATCH_LENGTH = 4; // length of the one item

	int[] _msgIds = null;

	@Override
	public void readImpl() {
		int count = readD();
		if ((count <= 0) || (count > PlayerConfig.MAX_ITEM_IN_PACKET) || ((count * BATCH_LENGTH) != getAvailableBytes())) {
			return;
		}

		_msgIds = new int[count];
		for (int i = 0; i < count; i++) {
			_msgIds[i] = readD();
		}
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if ((activeChar == null) || (_msgIds == null) || !GeneralConfig.ALLOW_MAIL) {
			return;
		}

		if (!activeChar.isInsideZone(ZoneId.PEACE)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_OR_SEND_MAIL_WITH_ATTACHED_ITEMS_IN_NON_PEACE_ZONE_REGIONS);
			return;
		}

		for (int msgId : _msgIds) {
			Message msg = MailManager.getInstance().getMessage(msgId);
			if (msg == null) {
				continue;
			}
			if (msg.getSenderId() != activeChar.getObjectId()) {
				Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to delete not own post!", GeneralConfig.DEFAULT_PUNISH);
				return;
			}

			if (msg.hasAttachments() || msg.isDeletedBySender()) {
				return;
			}

			msg.setDeletedBySender();
		}
		getClient().sendPacket(new ExChangePostState(false, _msgIds, Message.DELETED));
	}
}
