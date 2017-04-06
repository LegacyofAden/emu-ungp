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
package org.l2junity.gameserver.network.packets.s2c.primeshop;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.interfaces.IIdentifiable;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Gnacik, UnAfraid
 */
public class ExBRBuyProduct extends GameServerPacket {
	public enum ExBrProductReplyType implements IIdentifiable {
		SUCCESS(1),
		LACK_OF_POINT(-1),
		INVALID_PRODUCT(-2),
		USER_CANCEL(-3),
		INVENTROY_OVERFLOW(-4),
		CLOSED_PRODUCT(-5),
		SERVER_ERROR(-6),
		BEFORE_SALE_DATE(-7),
		AFTER_SALE_DATE(-8),
		INVALID_USER(-9),
		INVALID_ITEM(-10),
		INVALID_USER_STATE(-11),
		NOT_DAY_OF_WEEK(-12),
		NOT_TIME_OF_DAY(-13),
		SOLD_OUT(-14);
		private final int _id;

		ExBrProductReplyType(int id) {
			_id = id;
		}

		@Override
		public int getId() {
			return _id;
		}
	}

	private final int _reply;

	public ExBRBuyProduct(final ExBrProductReplyType type) {
		_reply = type.getId();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_BR_BUY_PRODUCT.writeId(body);

		body.writeD(_reply);
	}
}
