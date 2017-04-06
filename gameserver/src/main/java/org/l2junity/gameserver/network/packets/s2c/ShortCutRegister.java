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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

public final class ShortCutRegister extends GameServerPacket {
	private final Shortcut _shortcut;

	/**
	 * Register new skill shortcut
	 *
	 * @param shortcut
	 */
	public ShortCutRegister(Shortcut shortcut) {
		_shortcut = shortcut;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SHORT_CUT_REGISTER.writeId(body);

		body.writeD(_shortcut.getType().ordinal());
		body.writeD(_shortcut.getSlot() + (_shortcut.getPage() * 12)); // C4 Client
		switch (_shortcut.getType()) {
			case ITEM: {
				body.writeD(_shortcut.getId());
				body.writeD(_shortcut.getCharacterType());
				body.writeD(_shortcut.getSharedReuseGroup());
				body.writeD(0x00); // unknown
				body.writeD(0x00); // unknown
				body.writeD(0x00); // item augment id
				body.writeD(0x00); // TODO: Find me, item visual id ?
				break;
			}
			case SKILL: {
				body.writeD(_shortcut.getId());
				body.writeH(_shortcut.getLevel());
				body.writeH(_shortcut.getSubLevel());
				body.writeD(_shortcut.getSharedReuseGroup());
				body.writeC(0x00); // C5
				body.writeD(_shortcut.getCharacterType());
				body.writeD(0x00); // TODO: Find me
				body.writeD(0x00); // TODO: Find me
				break;
			}
			case ACTION:
			case MACRO:
			case RECIPE:
			case BOOKMARK: {
				body.writeD(_shortcut.getId());
				body.writeD(_shortcut.getCharacterType());
			}
		}
	}
}
