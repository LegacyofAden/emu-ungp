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

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

/**
 * @author devScarlet, mrTJO
 */
public final class ServerObjectInfo implements IClientOutgoingPacket {
	private final Npc _activeChar;
	private final int _x, _y, _z, _heading;
	private final int _idTemplate;
	private final boolean _isAttackable;
	private final double _collisionHeight, _collisionRadius;
	private final String _name;

	public ServerObjectInfo(Npc activeChar, Creature actor) {
		_activeChar = activeChar;
		_idTemplate = _activeChar.getTemplate().getDisplayId();
		_isAttackable = _activeChar.isAutoAttackable(actor);
		_collisionHeight = _activeChar.getCollisionHeight();
		_collisionRadius = _activeChar.getCollisionRadius();
		_x = (int) _activeChar.getX();
		_y = (int) _activeChar.getY();
		_z = (int) _activeChar.getZ();
		_heading = _activeChar.getHeading();
		_name = _activeChar.getTemplate().isUsingServerSideName() ? _activeChar.getTemplate().getName() : "";
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.SERVER_OBJECT_INFO.writeId(packet);

		packet.writeD(_activeChar.getObjectId());
		packet.writeD(_idTemplate + 1000000);
		packet.writeS(_name); // name
		packet.writeD(_isAttackable ? 1 : 0);
		packet.writeD(_x);
		packet.writeD(_y);
		packet.writeD(_z);
		packet.writeD(_heading);
		packet.writeF(1.0); // movement multiplier
		packet.writeF(1.0); // attack speed multiplier
		packet.writeF(_collisionRadius);
		packet.writeF(_collisionHeight);
		packet.writeD((int) (_isAttackable ? _activeChar.getCurrentHp() : 0));
		packet.writeD(_isAttackable ? _activeChar.getMaxHp() : 0);
		packet.writeD(0x01); // object type
		packet.writeD(0x00); // special effects
		return true;
	}
}
