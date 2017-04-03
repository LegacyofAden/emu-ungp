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

import org.l2junity.gameserver.data.xml.impl.HennaData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.Henna;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.HennaItemDrawInfo;
import org.l2junity.network.PacketReader;

/**
 * @author Zoey76
 */
public final class RequestHennaItemInfo implements IClientIncomingPacket {
	private int _symbolId;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_symbolId = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}

		final Henna henna = HennaData.getInstance().getHenna(_symbolId);
		if (henna == null) {
			if (_symbolId != 0) {
				_log.warn(getClass().getSimpleName() + ": Invalid Henna Id: " + _symbolId + " from player " + activeChar);
			}
			client.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		client.sendPacket(new HennaItemDrawInfo(henna, activeChar));
	}
}
