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
import org.l2junity.gameserver.instancemanager.InstanceManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.instancezone.Instance;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * @author UnAfraid
 */
public class ExInzoneWaiting extends GameServerPacket {
	private final int _currentTemplateId;
	private final Map<Integer, Long> _instanceTimes;

	public ExInzoneWaiting(Player activeChar) {
		final Instance instance = InstanceManager.getInstance().getPlayerInstance(activeChar, false);
		_currentTemplateId = ((instance != null) && (instance.getTemplateId() >= 0)) ? instance.getTemplateId() : -1;
		_instanceTimes = InstanceManager.getInstance().getAllInstanceTimes(activeChar);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_INZONE_WAITING_INFO.writeId(body);

		body.writeD(_currentTemplateId);
		body.writeD(_instanceTimes.size());
		for (Entry<Integer, Long> entry : _instanceTimes.entrySet()) {
			final long instanceTime = TimeUnit.MILLISECONDS.toSeconds(entry.getValue() - System.currentTimeMillis());
			body.writeD(entry.getKey());
			body.writeD((int) instanceTime);
		}
	}
}