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
import org.l2junity.gameserver.model.actor.templates.PcTemplate;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

public final class NewCharacterSuccess extends GameServerPacket {
	private final List<PcTemplate> _chars = new ArrayList<>();

	public void addChar(PcTemplate template) {
		_chars.add(template);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.NEW_CHARACTER_SUCCESS.writeId(body);

		body.writeD(_chars.size());
		for (PcTemplate chr : _chars) {
			if (chr == null) {
				continue;
			}

			// TODO: Unhardcode these
			body.writeD(chr.getRace().ordinal());
			body.writeD(chr.getClassId().getId());

			body.writeD(99);
			body.writeD(chr.getBaseSTR());
			body.writeD(1);

			body.writeD(99);
			body.writeD(chr.getBaseDEX());
			body.writeD(1);

			body.writeD(99);
			body.writeD(chr.getBaseCON());
			body.writeD(1);

			body.writeD(99);
			body.writeD(chr.getBaseINT());
			body.writeD(1);

			body.writeD(99);
			body.writeD(chr.getBaseWIT());
			body.writeD(1);

			body.writeD(99);
			body.writeD(chr.getBaseMEN());
			body.writeD(1);
		}
	}
}