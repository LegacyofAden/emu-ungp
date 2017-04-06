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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.network.packets.GameClientPacket;


/**
 * @author KenM, Gnacik
 */
public class RequestChangeNicknameColor extends GameClientPacket {
	private static final int COLORS[] =
			{
					0x9393FF, // Pink
					0x7C49FC, // Rose Pink
					0x97F8FC, // Lemon Yellow
					0xFA9AEE, // Lilac
					0xFF5D93, // Cobalt Violet
					0x00FCA0, // Mint Green
					0xA0A601, // Peacock Green
					0x7898AF, // Yellow Ochre
					0x486295, // Chocolate
					0x999999, // Silver
			};

	private int _colorNum, _itemId;
	private String _title;

	@Override
	public void readImpl() {
		_colorNum = readD();
		_title = readS();
		_itemId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if ((_colorNum < 0) || (_colorNum >= COLORS.length)) {
			return;
		}

		final ItemInstance item = activeChar.getInventory().getItemByItemId(_itemId);
		if ((item == null) || (item.getEtcItem() == null) || (item.getEtcItem().getHandlerName() == null) || !item.getEtcItem().getHandlerName().equalsIgnoreCase("NicknameColor")) {
			return;
		}

		if (activeChar.destroyItem("Consume", item, 1, null, true)) {
			activeChar.setTitle(_title);
			activeChar.getAppearance().setTitleColor(COLORS[_colorNum]);
			activeChar.broadcastUserInfo();
		}
	}
}
