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
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.interfaces.ILocational;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author KenM
 */
public final class FlyToLocation extends GameServerPacket {
	private final int _destX, _destY, _destZ;
	private final int _chaObjId, _chaX, _chaY, _chaZ;
	private final FlyType _type;
	private int _flySpeed;
	private int _flyDelay;
	private int _animationSpeed;

	public enum FlyType {
		THROW_UP,
		THROW_HORIZONTAL,
		DUMMY,
		CHARGE,
		PUSH_HORIZONTAL,
		JUMP_EFFECTED,
		NOT_USED,
		PUSH_DOWN_HORIZONTAL,
		WARP_BACK,
		WARP_FORWARD
	}

	public FlyToLocation(Creature cha, double destX, double destY, double destZ, FlyType type) {
		_chaObjId = cha.getObjectId();
		_chaX = (int) cha.getX();
		_chaY = (int) cha.getY();
		_chaZ = (int) cha.getZ();
		_destX = (int) destX;
		_destY = (int) destY;
		_destZ = (int) destZ;
		_type = type;
	}

	public FlyToLocation(Creature cha, double destX, double destY, double destZ, FlyType type, int flySpeed, int flyDelay, int animationSpeed) {
		_chaObjId = cha.getObjectId();
		_chaX = (int) cha.getX();
		_chaY = (int) cha.getY();
		_chaZ = (int) cha.getZ();
		_destX = (int) destX;
		_destY = (int) destY;
		_destZ = (int) destZ;
		_type = type;
		_flySpeed = flySpeed;
		_flyDelay = flyDelay;
		_animationSpeed = animationSpeed;
	}

	public FlyToLocation(Creature cha, ILocational dest, FlyType type) {
		this(cha, dest.getX(), dest.getY(), dest.getZ(), type);
	}

	public FlyToLocation(Creature cha, ILocational dest, FlyType type, int flySpeed, int flyDelay, int animationSpeed) {
		this(cha, dest.getX(), dest.getY(), dest.getZ(), type, flySpeed, flyDelay, animationSpeed);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.FLY_TO_LOCATION.writeId(body);

		body.writeD(_chaObjId);
		body.writeD(_destX);
		body.writeD(_destY);
		body.writeD(_destZ);
		body.writeD(_chaX);
		body.writeD(_chaY);
		body.writeD(_chaZ);
		body.writeD(_type.ordinal());
		body.writeD(_flySpeed);
		body.writeD(_flyDelay);
		body.writeD(_animationSpeed);
	}
}