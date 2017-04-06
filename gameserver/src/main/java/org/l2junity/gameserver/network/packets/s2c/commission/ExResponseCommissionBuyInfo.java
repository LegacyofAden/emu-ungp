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

/**
 * @author NosBit
 */
public class ExResponseCommissionBuyInfo extends AbstractItemPacket {
	public static final ExResponseCommissionBuyInfo FAILED = new ExResponseCommissionBuyInfo(null);

	private final CommissionItem _commissionItem;

	public ExResponseCommissionBuyInfo(CommissionItem commissionItem) {
		_commissionItem = commissionItem;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_RESPONSE_COMMISSION_BUY_INFO.writeId(body);

		body.writeD(_commissionItem != null ? 1 : 0);
		if (_commissionItem != null) {
			body.writeQ(_commissionItem.getPricePerUnit());
			body.writeQ(_commissionItem.getCommissionId());
			body.writeD(0); // CommissionItemType seems client does not really need it.
			writeItem(body, _commissionItem.getItemInfo());
		}
	}
}
