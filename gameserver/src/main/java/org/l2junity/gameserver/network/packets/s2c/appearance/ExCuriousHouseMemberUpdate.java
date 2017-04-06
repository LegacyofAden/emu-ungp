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
package org.l2junity.gameserver.network.packets.s2c.appearance;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosMember;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Sdw
 */
public class ExCuriousHouseMemberUpdate extends GameServerPacket {
	public final int _objId;
	public final int _maxHp;
	public final int _maxCp;
	public final int _currentHp;
	public final int _currentCp;

	public ExCuriousHouseMemberUpdate(CeremonyOfChaosMember member) {
		_objId = member.getObjectId();
		final Player player = member.getPlayer();
		if (player != null) {
			_maxHp = player.getMaxHp();
			_maxCp = player.getMaxCp();
			_currentHp = (int) player.getCurrentHp();
			_currentCp = (int) player.getCurrentCp();
		} else {
			_maxHp = 0;
			_maxCp = 0;
			_currentHp = 0;
			_currentCp = 0;
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_CURIOUS_HOUSE_MEMBER_UPDATE.writeId(body);

		body.writeD(_objId);
		body.writeD(_maxHp);
		body.writeD(_maxCp);
		body.writeD(_currentHp);
		body.writeD(_currentCp);
	}
}
