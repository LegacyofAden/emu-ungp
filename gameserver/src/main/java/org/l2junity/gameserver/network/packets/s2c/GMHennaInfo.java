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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.Henna;
import org.l2junity.gameserver.model.stats.BaseStats;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

/**
 * This server packet sends the player's henna information using the Game Master's UI.
 *
 * @author KenM, Zoey76
 */
public final class GMHennaInfo extends GameServerPacket {
	private final Player _activeChar;
	private final List<Henna> _hennas = new ArrayList<>();

	public GMHennaInfo(Player player) {
		_activeChar = player;
		for (Henna henna : _activeChar.getHennaList()) {
			if (henna != null) {
				_hennas.add(henna);
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.GMHENNA_INFO.writeId(body);

		body.writeH(_activeChar.getHennaValue(BaseStats.INT)); // equip INT
		body.writeH(_activeChar.getHennaValue(BaseStats.STR)); // equip STR
		body.writeH(_activeChar.getHennaValue(BaseStats.CON)); // equip CON
		body.writeH(_activeChar.getHennaValue(BaseStats.MEN)); // equip MEN
		body.writeH(_activeChar.getHennaValue(BaseStats.DEX)); // equip DEX
		body.writeH(_activeChar.getHennaValue(BaseStats.WIT)); // equip WIT
		body.writeH(_activeChar.getHennaValue(BaseStats.LUC)); // equip LUC
		body.writeH(_activeChar.getHennaValue(BaseStats.CHA)); // equip CHA
		body.writeD(3); // Slots
		body.writeD(_hennas.size()); // Size
		for (Henna henna : _hennas) {
			body.writeD(henna.getDyeId());
			body.writeD(0x01);
		}
		body.writeD(0x00);
		body.writeD(0x00);
		body.writeD(0x00);
	}
}