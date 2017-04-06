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
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author Luca Baldi
 */
public class EtcStatusUpdate extends GameServerPacket {
	private final Player _activeChar;
	private int _mask;

	public EtcStatusUpdate(Player activeChar) {
		_activeChar = activeChar;
		_mask = _activeChar.getMessageRefusal() || _activeChar.isChatBanned() || _activeChar.isSilenceMode() ? 1 : 0;
		_mask |= _activeChar.isInsideZone(ZoneId.DANGER_AREA) ? 2 : 0;
		_mask |= _activeChar.hasCharmOfCourage() ? 4 : 0;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ETC_STATUS_UPDATE.writeId(body);

		body.writeC(_activeChar.getCharges()); // 1-7 increase force, lvl
		body.writeD(_activeChar.getWeightPenalty()); // 1-4 weight penalty, lvl (1=50%, 2=66.6%, 3=80%, 4=100%)
		body.writeC(_activeChar.getExpertiseWeaponPenalty()); // Weapon Grade Penalty [1-4]
		body.writeC(_activeChar.getExpertiseArmorPenalty()); // Armor Grade Penalty [1-4]
		body.writeC(0); // Death Penalty [1-15, 0 = disabled)], not used anymore in Ertheia
		body.writeC(_activeChar.getChargedSouls());
		body.writeC(_mask);
	}
}