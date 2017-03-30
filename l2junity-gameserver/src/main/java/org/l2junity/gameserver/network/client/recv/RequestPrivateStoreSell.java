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
package org.l2junity.gameserver.network.client.recv;

import static org.l2junity.gameserver.model.actor.Npc.INTERACTION_DISTANCE;

import org.l2junity.gameserver.config.PlayerConfig;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.ItemRequest;
import org.l2junity.gameserver.model.TradeList;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * This class ...
 * @version $Revision: 1.2.2.1.2.4 $ $Date: 2005/03/27 15:29:30 $
 */
public final class RequestPrivateStoreSell implements IClientIncomingPacket
{
	private int _storePlayerId;
	private ItemRequest[] _items = null;
	
	@Override
	public boolean read(L2GameClient client, PacketReader packet)
	{
		_storePlayerId = packet.readD();
		int itemsCount = packet.readD();
		if ((itemsCount <= 0) || (itemsCount > PlayerConfig.MAX_ITEM_IN_PACKET))
		{
			return false;
		}
		_items = new ItemRequest[itemsCount];
		
		for (int i = 0; i < itemsCount; i++)
		{
			final int slot = packet.readD();
			final int itemId = packet.readD();
			packet.readH(); // TODO analyse this
			packet.readH(); // TODO analyse this
			final long count = packet.readQ();
			final long price = packet.readQ();
			packet.readD(); // visual id
			packet.readD(); // option 1
			packet.readD(); // option 2
			int soulCrystals = packet.readC();
			for (int s = 0; s < soulCrystals; s++)
			{
				packet.readD(); // soul crystal option
			}
			int soulCrystals2 = packet.readC();
			for (int s = 0; s < soulCrystals2; s++)
			{
				packet.readD(); // soul crystal option
			}
			if ((slot < 1) || (itemId < 1) || (count < 1) || (price < 0))
			{
				_items = null;
				return false;
			}
			_items[i] = new ItemRequest(slot, itemId, count, price);
		}
		return true;
	}
	
	@Override
	public void run(L2GameClient client)
	{
		final PlayerInstance player = client.getActiveChar();
		if (player == null)
		{
			return;
		}
		
		if (_items == null)
		{
			client.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		// Cannot set private store in Ceremony of Chaos event.
		if (player.isOnEvent(CeremonyOfChaosEvent.class))
		{
			client.sendPacket(SystemMessageId.YOU_CANNOT_OPEN_A_PRIVATE_STORE_OR_WORKSHOP_IN_THE_CEREMONY_OF_CHAOS);
			return;
		}
		
		if (!client.getFloodProtectors().getTransaction().tryPerformAction("privatestoresell"))
		{
			player.sendMessage("You are selling items too fast.");
			return;
		}
		
		final PlayerInstance storePlayer = World.getInstance().getPlayer(_storePlayerId);
		if ((storePlayer == null) || !player.isInRadius3d(storePlayer, INTERACTION_DISTANCE))
		{
			return;
		}
		
		if (player.getInstanceWorld() != storePlayer.getInstanceWorld())
		{
			return;
		}
		
		if ((storePlayer.getPrivateStoreType() != PrivateStoreType.BUY) || player.isCursedWeaponEquipped())
		{
			return;
		}
		
		TradeList storeList = storePlayer.getBuyList();
		if (storeList == null)
		{
			return;
		}
		
		if (!player.getAccessLevel().allowTransaction())
		{
			player.sendMessage("Transactions are disabled for your Access Level.");
			client.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		if (!storeList.privateStoreSell(player, _items))
		{
			client.sendPacket(ActionFailed.STATIC_PACKET);
			_log.warn("PrivateStore sell has failed due to invalid list or request. Player: " + player.getName() + ", Private store of: " + storePlayer.getName());
			return;
		}
		
		if (storeList.getItemCount() == 0)
		{
			storePlayer.setPrivateStoreType(PrivateStoreType.NONE);
			storePlayer.broadcastUserInfo();
		}
	}
}
