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

import org.l2junity.gameserver.instancemanager.GameTimeManager;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

public class CharSelected implements IClientOutgoingPacket
{
	private final PlayerInstance _activeChar;
	private final int _sessionId;
	
	/**
	 * @param cha
	 * @param sessionId
	 */
	public CharSelected(PlayerInstance cha, int sessionId)
	{
		_activeChar = cha;
		_sessionId = sessionId;
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.CHARACTER_SELECTED.writeId(packet);
		
		packet.writeS(_activeChar.getName());
		packet.writeD(_activeChar.getObjectId());
		packet.writeS(_activeChar.getTitle());
		packet.writeD(_sessionId);
		packet.writeD(_activeChar.getClanId());
		packet.writeD(0x00); // ??
		packet.writeD(_activeChar.getAppearance().getSex() ? 1 : 0);
		packet.writeD(_activeChar.getRace().ordinal());
		packet.writeD(_activeChar.getClassId().getId());
		packet.writeD(0x01); // active ??
		packet.writeD((int) _activeChar.getX());
		packet.writeD((int) _activeChar.getY());
		packet.writeD((int) _activeChar.getZ());
		packet.writeF(_activeChar.getCurrentHp());
		packet.writeF(_activeChar.getCurrentMp());
		packet.writeQ(_activeChar.getSp());
		packet.writeQ(_activeChar.getExp());
		packet.writeD(_activeChar.getLevel());
		packet.writeD(_activeChar.getReputation());
		packet.writeD(_activeChar.getPkKills());
		packet.writeD(GameTimeManager.getInstance().getGameTimeInMinutesOfDay());
		packet.writeD(0x00);
		packet.writeD(_activeChar.getClassId().getId());
		
		packet.writeB(new byte[16]);
		
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		
		packet.writeD(0x00);
		
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		packet.writeD(0x00);
		
		packet.writeB(new byte[28]);
		packet.writeD(0x00);
		return true;
	}
}
