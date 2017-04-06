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
package org.l2junity.gameserver.network.packets.c2s.onedayreward;

import org.l2junity.gameserver.data.xml.impl.OneDayRewardData;
import org.l2junity.gameserver.model.OneDayRewardDataHolder;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExConnectedTimeAndGettableReward;
import org.l2junity.gameserver.network.packets.s2c.onedayreward.ExOneDayReceiveRewardList;
import org.l2junity.network.PacketReader;

import java.util.Collection;

/**
 * @author Sdw
 */
public class RequestOneDayRewardReceive extends GameClientPacket {
	private int _id;

	@Override
	public void readImpl() {
		_id = readH();
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		final Collection<OneDayRewardDataHolder> reward = OneDayRewardData.getInstance().getOneDayRewardData(_id);
		if ((reward == null) || reward.isEmpty()) {
			return;
		}

		reward.stream().filter(o -> o.isDisplayable(player)).forEach(r -> r.requestReward(player));
		player.sendPacket(new ExConnectedTimeAndGettableReward(player));
		player.sendPacket(new ExOneDayReceiveRewardList(player, true));
	}
}
