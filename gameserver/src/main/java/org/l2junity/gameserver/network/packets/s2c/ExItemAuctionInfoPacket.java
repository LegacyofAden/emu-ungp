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
import org.l2junity.gameserver.model.itemauction.ItemAuction;
import org.l2junity.gameserver.model.itemauction.ItemAuctionBid;
import org.l2junity.gameserver.model.itemauction.ItemAuctionState;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Forsaiken
 */
public final class ExItemAuctionInfoPacket extends AbstractItemPacket {
	private final boolean _refresh;
	private final int _timeRemaining;
	private final ItemAuction _currentAuction;
	private final ItemAuction _nextAuction;

	public ExItemAuctionInfoPacket(final boolean refresh, final ItemAuction currentAuction, final ItemAuction nextAuction) {
		if (currentAuction == null) {
			throw new NullPointerException();
		}

		if (currentAuction.getAuctionState() != ItemAuctionState.STARTED) {
			_timeRemaining = 0;
		} else {
			_timeRemaining = (int) (currentAuction.getFinishingTimeRemaining() / 1000); // in seconds
		}

		_refresh = refresh;
		_currentAuction = currentAuction;
		_nextAuction = nextAuction;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ITEM_AUCTION_INFO.writeId(body);

		body.writeC(_refresh ? 0x00 : 0x01);
		body.writeD(_currentAuction.getInstanceId());

		final ItemAuctionBid highestBid = _currentAuction.getHighestBid();
		body.writeQ(highestBid != null ? highestBid.getLastBid() : _currentAuction.getAuctionInitBid());

		body.writeD(_timeRemaining);
		writeItem(body, _currentAuction.getItemInfo());

		if (_nextAuction != null) {
			body.writeQ(_nextAuction.getAuctionInitBid());
			body.writeD((int) (_nextAuction.getStartingTime() / 1000)); // unix time in seconds
			writeItem(body, _nextAuction.getItemInfo());
		}
	}
}