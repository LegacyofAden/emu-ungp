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

import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ExRpItemLink;
import org.l2junity.network.PacketReader;

/**
 * @author KenM
 */
public class RequestExRqItemLink implements IClientIncomingPacket {
	private int _objectId;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_objectId = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final WorldObject object = WorldManager.getInstance().getMainWorld().findObject(_objectId);
		if (object instanceof ItemInstance) {
			final ItemInstance item = (ItemInstance) object;
			if (item.isPublished()) {
				client.sendPacket(new ExRpItemLink(item));
			}
		}
	}
}
