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
import org.l2junity.gameserver.enums.EEnvType;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public class ChangeMoveType extends GameServerPacket {
	public static final int WALK = 0;
	public static final int RUN = 1;

	private final int objectId;
	private final boolean isRunning;
	private final EEnvType environment;

	public ChangeMoveType(Creature character) {
		objectId = character.getObjectId();
		isRunning = character.isRunning();
		environment = character.getEnvironment();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CHANGE_MOVE_TYPE.writeId(body);

		body.writeD(objectId);
		body.writeD(isRunning ? RUN : WALK);
		body.writeD(environment.getType()); // c2
	}
}