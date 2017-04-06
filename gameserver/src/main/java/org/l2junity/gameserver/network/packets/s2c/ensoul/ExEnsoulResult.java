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
package org.l2junity.gameserver.network.packets.s2c.ensoul;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.ensoul.EnsoulOption;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExEnsoulResult extends GameServerPacket {
	private final int _success;
	private final ItemInstance _item;

	public ExEnsoulResult(int success, ItemInstance item) {
		_success = success;
		_item = item;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ENSOUL_RESULT.writeId(body);
		body.writeC(_success); // success / failure
		body.writeC(_item.getSpecialAbilities().size());
		for (EnsoulOption option : _item.getSpecialAbilities()) {
			body.writeD(option.getId());
		}
		body.writeC(_item.getAdditionalSpecialAbilities().size());
		for (EnsoulOption option : _item.getAdditionalSpecialAbilities()) {
			body.writeD(option.getId());
		}
	}
}
