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
package org.l2junity.gameserver.network.packets.s2c.luckygame;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.enums.LuckyGameItemType;
import org.l2junity.gameserver.enums.LuckyGameResultType;
import org.l2junity.gameserver.enums.LuckyGameType;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Sdw
 */
public class ExBettingLuckyGameResult extends GameServerPacket {
	public static final ExBettingLuckyGameResult NORMAL_INVALID_ITEM_COUNT = new ExBettingLuckyGameResult(LuckyGameResultType.INVALID_ITEM_COUNT, LuckyGameType.NORMAL);
	public static final ExBettingLuckyGameResult LUXURY_INVALID_ITEM_COUNT = new ExBettingLuckyGameResult(LuckyGameResultType.INVALID_ITEM_COUNT, LuckyGameType.LUXURY);
	public static final ExBettingLuckyGameResult NORMAL_INVALID_CAPACITY = new ExBettingLuckyGameResult(LuckyGameResultType.INVALID_CAPACITY, LuckyGameType.NORMAL);
	public static final ExBettingLuckyGameResult LUXURY_INVALID_CAPACITY = new ExBettingLuckyGameResult(LuckyGameResultType.INVALID_CAPACITY, LuckyGameType.LUXURY);

	private final LuckyGameResultType _result;
	private final LuckyGameType _type;
	private final EnumMap<LuckyGameItemType, List<ItemHolder>> _rewards;
	private final int _ticketCount;
	private final int _size;

	public ExBettingLuckyGameResult(LuckyGameResultType result, LuckyGameType type) {
		_result = result;
		_type = type;
		_rewards = new EnumMap<>(LuckyGameItemType.class);
		_ticketCount = 0;
		_size = 0;
	}

	public ExBettingLuckyGameResult(LuckyGameResultType result, LuckyGameType type, EnumMap<LuckyGameItemType, List<ItemHolder>> rewards, int ticketCount) {
		_result = result;
		_type = type;
		_rewards = rewards;
		_ticketCount = ticketCount;
		_size = (int) rewards.values().stream().mapToLong(i -> i.stream().count()).sum();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BETTING_LUCKY_GAME_RESULT.writeId(body);
		body.writeD(_result.getClientId());
		body.writeD(_type.ordinal());
		body.writeD(_ticketCount);
		body.writeD(_size);
		for (Entry<LuckyGameItemType, List<ItemHolder>> reward : _rewards.entrySet()) {
			for (ItemHolder item : reward.getValue()) {
				body.writeD(reward.getKey().getClientId());
				body.writeD(item.getId());
				body.writeD((int) item.getCount());
			}
		}
	}
}
