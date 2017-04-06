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
import org.l2junity.gameserver.enums.AttributeType;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class ExAttributeEnchantResult extends GameServerPacket {
	private final int _result;
	private final int _isWeapon;
	private final int _type;
	private final int _before;
	private final int _after;
	private final int _successCount;
	private final int _failedCount;

	public ExAttributeEnchantResult(int result, boolean isWeapon, AttributeType type, int before, int after, int successCount, int failedCount) {
		_result = result;
		_isWeapon = isWeapon ? 1 : 0;
		_type = type.getClientId();
		_before = before;
		_after = after;
		_successCount = successCount;
		_failedCount = failedCount;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ATTRIBUTE_ENCHANT_RESULT.writeId(body);

		body.writeD(_result);
		body.writeC(_isWeapon);
		body.writeH(_type);
		body.writeH(_before);
		body.writeH(_after);
		body.writeH(_successCount);
		body.writeH(_failedCount);
	}
}
