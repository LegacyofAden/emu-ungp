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
import org.l2junity.gameserver.data.xml.impl.HennaData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.Henna;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author Zoey76
 */
public class HennaEquipList extends GameServerPacket {
	private final Player _player;
	private final List<Henna> _hennaEquipList;

	public HennaEquipList(Player player) {
		_player = player;
		_hennaEquipList = HennaData.getInstance().getHennaList(player.getClassId());
	}

	public HennaEquipList(Player player, List<Henna> list) {
		_player = player;
		_hennaEquipList = list;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.HENNA_EQUIP_LIST.writeId(body);
		body.writeQ(_player.getAdena()); // activeChar current amount of Adena
		body.writeD(3); // available equip slot
		body.writeD(_hennaEquipList.size());

		for (Henna henna : _hennaEquipList) {
			// Player must have at least one dye in inventory
			// to be able to see the Henna that can be applied with it.
			if ((_player.getInventory().getItemByItemId(henna.getDyeItemId())) != null) {
				body.writeD(henna.getDyeId()); // dye Id
				body.writeD(henna.getDyeItemId()); // item Id of the dye
				body.writeQ(henna.getWearCount()); // amount of dyes required
				body.writeQ(henna.getWearFee()); // amount of Adena required
				body.writeD(henna.isAllowedClass(_player.getClassId()) ? 0x01 : 0x00); // meet the requirement or not
				body.writeD(0x00); // TODO: Find me!
			}
		}
	}
}
