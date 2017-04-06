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
import org.l2junity.gameserver.model.Shortcut;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class ShortCutInit extends GameServerPacket {
	private Shortcut[] _shortCuts;

	public ShortCutInit(Player activeChar) {
		if (activeChar == null) {
			return;
		}

		_shortCuts = activeChar.getAllShortCuts();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SHORT_CUT_INIT.writeId(body);

		body.writeD(_shortCuts.length);
		for (Shortcut sc : _shortCuts) {
			body.writeD(sc.getType().ordinal());
			body.writeD(sc.getSlot() + (sc.getPage() * 12));

			switch (sc.getType()) {
				case ITEM: {
					body.writeD(sc.getId());
					body.writeD(0x01); // Enabled or not
					body.writeD(sc.getSharedReuseGroup());
					body.writeD(0x00);
					body.writeD(0x00);
					body.writeQ(0x00); // Augment id
					body.writeD(0x00); // Visual id
					break;
				}
				case SKILL: {
					body.writeD(sc.getId());
					body.writeH(sc.getLevel());
					body.writeH(sc.getSubLevel());
					body.writeD(sc.getSharedReuseGroup());
					body.writeC(0x00); // C5
					body.writeD(0x01); // C6
					break;
				}
				case ACTION:
				case MACRO:
				case RECIPE:
				case BOOKMARK: {
					body.writeD(sc.getId());
					body.writeD(0x01); // C6
				}
			}
		}
	}
}