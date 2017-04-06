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
import org.l2junity.gameserver.enums.InventorySlot;
import org.l2junity.gameserver.model.VariationInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.itemcontainer.PcInventory;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExUserInfoEquipSlot extends AbstractMaskPacket<InventorySlot> {
	private final Player _activeChar;

	private final byte[] _masks = new byte[]
			{
					(byte) 0x00,
					(byte) 0x00,
					(byte) 0x00,
					(byte) 0x00,
					(byte) 0x00
			};

	public ExUserInfoEquipSlot(Player cha) {
		this(cha, true);
	}

	public ExUserInfoEquipSlot(Player cha, boolean addAll) {
		_activeChar = cha;

		if (addAll) {
			addComponentType(InventorySlot.values());
		}
	}

	@Override
	protected byte[] getMasks() {
		return _masks;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_USER_INFO_EQUIP_SLOT.writeId(body);

		body.writeD(_activeChar.getObjectId());
		body.writeH(InventorySlot.values().length);
		body.writeB(_masks);

		final PcInventory inventory = _activeChar.getInventory();
		for (InventorySlot slot : InventorySlot.values()) {
			if (containsMask(slot)) {
				final VariationInstance augment = inventory.getPaperdollAugmentation(slot.getSlot());
				body.writeH(22); // 10 + 4 * 3
				body.writeD(inventory.getPaperdollObjectId(slot.getSlot()));
				body.writeD(inventory.getPaperdollItemId(slot.getSlot()));
				body.writeD(augment != null ? augment.getOption1Id() : 0);
				body.writeD(augment != null ? augment.getOption2Id() : 0);
				body.writeD(inventory.getPaperdollItemVisualId(slot.getSlot()));
			}
		}
	}
}