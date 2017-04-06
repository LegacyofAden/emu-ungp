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
package org.l2junity.gameserver.network.packets.s2c.commission;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.commission.CommissionItem;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.s2c.AbstractItemPacket;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * @author NosBit
 */
public class ExResponseCommissionList extends AbstractItemPacket {
	public static final int MAX_CHUNK_SIZE = 120;

	private final CommissionListReplyType _replyType;
	private final List<CommissionItem> _items;
	private final int _chunkId;
	private final int _listIndexStart;

	public ExResponseCommissionList(CommissionListReplyType replyType) {
		this(replyType, Collections.emptyList(), 0);
	}

	public ExResponseCommissionList(CommissionListReplyType replyType, List<CommissionItem> items) {
		this(replyType, items, 0);
	}

	public ExResponseCommissionList(CommissionListReplyType replyType, List<CommissionItem> items, int chunkId) {
		this(replyType, items, chunkId, 0);
	}

	public ExResponseCommissionList(CommissionListReplyType replyType, List<CommissionItem> items, int chunkId, int listIndexStart) {
		_replyType = replyType;
		_items = items;
		_chunkId = chunkId;
		_listIndexStart = listIndexStart;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_RESPONSE_COMMISSION_LIST.writeId(body);

		body.writeD(_replyType.getClientId());
		switch (_replyType) {
			case PLAYER_AUCTIONS:
			case AUCTIONS: {
				body.writeD((int) Instant.now().getEpochSecond());
				body.writeD(_chunkId);

				int chunkSize = _items.size() - _listIndexStart;
				if (chunkSize > MAX_CHUNK_SIZE) {
					chunkSize = MAX_CHUNK_SIZE;
				}

				body.writeD(chunkSize);
				for (int i = _listIndexStart; i < (_listIndexStart + chunkSize); i++) {
					final CommissionItem commissionItem = _items.get(i);
					body.writeQ(commissionItem.getCommissionId());
					body.writeQ(commissionItem.getPricePerUnit());
					body.writeD(0); // CommissionItemType seems client does not really need it.
					body.writeD((commissionItem.getDurationInDays() - 1) / 2);
					body.writeD((int) commissionItem.getEndTime().getEpochSecond());
					body.writeS(null); // Seller Name its not displayed somewhere so i am not sending it to decrease traffic.
					writeItem(body, commissionItem.getItemInfo());
				}
				break;
			}
		}
	}

	public enum CommissionListReplyType {
		PLAYER_AUCTIONS_EMPTY(-2),
		ITEM_DOES_NOT_EXIST(-1),
		PLAYER_AUCTIONS(2),
		AUCTIONS(3);

		private final int _clientId;

		CommissionListReplyType(int clientId) {
			_clientId = clientId;
		}

		/**
		 * Gets the client id.
		 *
		 * @return the client id
		 */
		public int getClientId() {
			return _clientId;
		}
	}
}