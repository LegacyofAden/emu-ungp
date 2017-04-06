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
package org.l2junity.gameserver.network.packets.s2c.onedayreward;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.data.xml.impl.OneDayRewardData;
import org.l2junity.gameserver.model.OneDayRewardDataHolder;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Sdw
 */
public class ExOneDayReceiveRewardList extends GameServerPacket {
	final Player _player;
	private final Collection<OneDayRewardDataHolder> _rewards;

	public ExOneDayReceiveRewardList(Player player, boolean sendRewards) {
		_player = player;
		_rewards = sendRewards ? OneDayRewardData.getInstance().getOneDayRewardData(player) : Collections.emptyList();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ONE_DAY_RECEIVE_REWARD_LIST.writeId(body);

		body.writeC(0x17);
		body.writeD(_player.getClassId().getId());
		body.writeD(LocalDate.now().getDayOfWeek().ordinal()); // Day of week
		body.writeD(_rewards.size());
		for (OneDayRewardDataHolder reward : _rewards) {
			body.writeH(reward.getId());
			body.writeC(reward.getStatus(_player));
			body.writeC(reward.getRequiredCompletions() > 0 ? 0x01 : 0x00);
			body.writeD(reward.getProgress(_player));
			body.writeD(reward.getRequiredCompletions());
		}
	}
}
