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
import org.l2junity.gameserver.enums.MailType;
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExChangePostState;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;


/**
 * @author Migi, DS
 */
public final class RequestRejectPostAttachment extends GameClientPacket {
	private int _msgId;

	@Override
	public void readImpl() {
		_msgId = readD();
	}

	@Override
	public void runImpl() {
		if (!GeneralConfig.ALLOW_MAIL || !GeneralConfig.ALLOW_ATTACHMENTS) {
			return;
		}

		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!getClient().getFloodProtectors().getTransaction().tryPerformAction("rejectattach")) {
			return;
		}

		if (!activeChar.isInsideZone(ZoneId.PEACE)) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_RECEIVE_OR_SEND_MAIL_WITH_ATTACHED_ITEMS_IN_NON_PEACE_ZONE_REGIONS);
			return;
		}

		Message msg = MailManager.getInstance().getMessage(_msgId);
		if (msg == null) {
			return;
		}

		if (msg.getReceiverId() != activeChar.getObjectId()) {
			Util.handleIllegalPlayerAction(activeChar, "Player " + activeChar.getName() + " tried to reject not own attachment!", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (!msg.hasAttachments() || (msg.getMailType() != MailType.REGULAR)) {
			return;
		}

		MailManager.getInstance().sendMessage(new Message(msg));

		getClient().sendPacket(SystemMessageId.MAIL_SUCCESSFULLY_RETURNED);
		getClient().sendPacket(new ExChangePostState(true, _msgId, Message.REJECTED));

		final Player sender = WorldManager.getInstance().getPlayer(msg.getSenderId());
		if (sender != null) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_RETURNED_THE_MAIL);
			sm.addCharName(activeChar);
			sender.sendPacket(sm);
		}
	}
}
