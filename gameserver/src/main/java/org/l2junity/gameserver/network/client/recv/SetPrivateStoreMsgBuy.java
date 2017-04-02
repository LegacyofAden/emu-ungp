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
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.PrivateStoreMsgBuy;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

public final class SetPrivateStoreMsgBuy implements IClientIncomingPacket {
	private static final int MAX_MSG_LENGTH = 29;

	private String _storeMsg;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_storeMsg = packet.readS();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final PlayerInstance player = client.getActiveChar();
		if ((player == null) || (player.getBuyList() == null)) {
			return;
		}

		if ((_storeMsg != null) && (_storeMsg.length() > MAX_MSG_LENGTH)) {
			Util.handleIllegalPlayerAction(player, "Player " + player.getName() + " tried to overflow private store buy message", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		player.getBuyList().setTitle(_storeMsg);
		client.sendPacket(new PrivateStoreMsgBuy(player));
	}
}
