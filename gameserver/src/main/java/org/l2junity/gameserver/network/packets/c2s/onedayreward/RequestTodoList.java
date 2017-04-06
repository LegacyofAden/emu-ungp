/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.network.packets.c2s.onedayreward;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.onedayreward.ExOneDayReceiveRewardList;
import org.l2junity.network.PacketReader;

/**
 * @author UnAfraid
 */
public class RequestTodoList extends GameClientPacket {
	private int _tab;
	@SuppressWarnings("unused")
	private int _allLevels;

	@Override
	public void readImpl() {
		_tab = readC();
		_allLevels = readC();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		switch (_tab) {
			case 9: // Daily Rewards
			{
				// Initial EW request should be false
				player.sendPacket(new ExOneDayReceiveRewardList(player, true));
				break;
			}
		}
	}
}
