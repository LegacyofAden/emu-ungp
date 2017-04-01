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
package org.l2junity.gameserver.network.client.send;

import java.util.Collection;

import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * @author ShanSoft
 */
public class ExBuySellList extends AbstractItemPacket
{
	private final Collection<ItemInstance> _sellList;
	private Collection<ItemInstance> _refundList = null;
	private final boolean _done;
	private final int _inventorySlots;
	private double _castleTaxRate = 1;
	
	public ExBuySellList(PlayerInstance player, boolean done)
	{
		final Summon pet = player.getPet();
		_sellList = player.getInventory().getItems(item -> !item.isEquipped() && item.isSellable() && ((pet == null) || (item.getObjectId() != pet.getControlObjectId())));
		_inventorySlots = player.getInventory().getItems((item) -> !item.isQuestItem()).size();
		if (player.hasRefund())
		{
			_refundList = player.getRefund().getItems();
		}
		_done = done;
	}
	
	public ExBuySellList(PlayerInstance player, boolean done, double castleTaxRate)
	{
		this(player, done);
		_castleTaxRate = 1 - castleTaxRate;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_BUY_SELL_LIST.writeId(packet);
		
		packet.writeD(0x01); // Type SELL
		packet.writeD(_inventorySlots);
		
		if ((_sellList != null))
		{
			packet.writeH(_sellList.size());
			for (ItemInstance item : _sellList)
			{
				writeItem(packet, item);
				packet.writeQ((long) ((item.getItem().getReferencePrice() / 2) * _castleTaxRate));
			}
		}
		else
		{
			packet.writeH(0x00);
		}
		
		if ((_refundList != null) && !_refundList.isEmpty())
		{
			packet.writeH(_refundList.size());
			int i = 0;
			for (ItemInstance item : _refundList)
			{
				writeItem(packet, item);
				packet.writeD(i++);
				packet.writeQ((item.getItem().getReferencePrice() / 2) * item.getCount());
			}
		}
		else
		{
			packet.writeH(0x00);
		}
		
		packet.writeC(_done ? 0x01 : 0x00);
		return true;
	}
}
