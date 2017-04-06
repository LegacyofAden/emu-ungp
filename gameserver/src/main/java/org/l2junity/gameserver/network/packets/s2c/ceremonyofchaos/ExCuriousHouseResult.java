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
package org.l2junity.gameserver.network.packets.s2c.ceremonyofchaos;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.enums.CeremonyOfChaosResult;
import org.l2junity.gameserver.instancemanager.CeremonyOfChaosManager;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExCuriousHouseResult extends GameServerPacket {
	private final CeremonyOfChaosResult _result;
	private final CeremonyOfChaosEvent _event;

	public ExCuriousHouseResult(CeremonyOfChaosResult result, CeremonyOfChaosEvent event) {
		_result = result;
		_event = event;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_CURIOUS_HOUSE_RESULT.writeId(body);
		body.writeD(_event.getId());
		body.writeH(_result.ordinal());
		body.writeD(CeremonyOfChaosManager.getInstance().getMaxPlayersInArena());
		body.writeD(_event.getMembers().size());
		_event.getMembers().values().forEach(m ->
		{
			body.writeD(m.getObjectId());
			body.writeD(m.getPosition());
			body.writeD(m.getClassId());
			body.writeD(m.getLifeTime());
			body.writeD(m.getScore());
		});
	}
}