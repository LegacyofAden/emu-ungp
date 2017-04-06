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

/**
 * @author Zoey76
 */
public final class HennaItemRemoveInfo extends GameServerPacket {
	private final Player _activeChar;
	private final Henna _henna;

	public HennaItemRemoveInfo(Henna henna, Player player) {
		_henna = henna;
		_activeChar = player;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.HENNA_UNEQUIP_INFO.writeId(body);

		body.writeD(_henna.getDyeId()); // symbol Id
		body.writeD(_henna.getDyeItemId()); // item id of dye
		body.writeQ(_henna.getCancelCount()); // total amount of dye require
		body.writeQ(_henna.getCancelFee()); // total amount of Adena require to remove symbol
		body.writeD(_henna.isAllowedClass(_activeChar.getClassId()) ? 0x01 : 0x00); // able to remove or not
		body.writeQ(_activeChar.getAdena());
		body.writeD(_activeChar.getINT()); // current INT
		body.writeH(_activeChar.getINT() - _activeChar.getHennaValue(BaseStats.INT)); // equip INT
		body.writeD(_activeChar.getSTR()); // current STR
		body.writeH(_activeChar.getSTR() - _activeChar.getHennaValue(BaseStats.STR)); // equip STR
		body.writeD(_activeChar.getCON()); // current CON
		body.writeH(_activeChar.getCON() - _activeChar.getHennaValue(BaseStats.CON)); // equip CON
		body.writeD(_activeChar.getMEN()); // current MEN
		body.writeH(_activeChar.getMEN() - _activeChar.getHennaValue(BaseStats.MEN)); // equip MEN
		body.writeD(_activeChar.getDEX()); // current DEX
		body.writeH(_activeChar.getDEX() - _activeChar.getHennaValue(BaseStats.DEX)); // equip DEX
		body.writeD(_activeChar.getWIT()); // current WIT
		body.writeH(_activeChar.getWIT() - _activeChar.getHennaValue(BaseStats.WIT)); // equip WIT
		body.writeD(_activeChar.getLUC()); // current LUC
		body.writeH(_activeChar.getLUC() - _activeChar.getHennaValue(BaseStats.LUC)); // equip LUC
		body.writeD(_activeChar.getCHA()); // current CHA
		body.writeH(_activeChar.getCHA() - _activeChar.getHennaValue(BaseStats.CHA)); // equip CHA
		body.writeD(0x00);
	}
}