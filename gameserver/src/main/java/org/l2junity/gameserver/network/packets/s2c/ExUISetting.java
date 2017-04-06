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
import org.l2junity.gameserver.model.ActionKey;
import org.l2junity.gameserver.model.UIKeysSettings;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author mrTJO
 */
public class ExUISetting extends GameServerPacket {
	private final UIKeysSettings _uiSettings;
	private int buffsize, categories;

	public ExUISetting(Player player) {
		_uiSettings = player.getUISettings();
		calcSize();
	}

	private void calcSize() {
		int size = 16; // initial header and footer
		int category = 0;
		int numKeyCt = _uiSettings.getKeys().size();
		for (int i = 0; i < numKeyCt; i++) {
			size++;
			if (_uiSettings.getCategories().containsKey(category)) {
				List<Integer> catElList1 = _uiSettings.getCategories().get(category);
				size = size + catElList1.size();
			}
			category++;
			size++;
			if (_uiSettings.getCategories().containsKey(category)) {
				List<Integer> catElList2 = _uiSettings.getCategories().get(category);
				size = size + catElList2.size();
			}
			category++;
			size = size + 4;
			if (_uiSettings.getKeys().containsKey(i)) {
				List<ActionKey> keyElList = _uiSettings.getKeys().get(i);
				size = size + (keyElList.size() * 20);
			}
		}
		buffsize = size;
		categories = category;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_UI_SETTING.writeId(body);

		body.writeD(buffsize);
		body.writeD(categories);

		int category = 0;

		int numKeyCt = _uiSettings.getKeys().size();
		body.writeD(numKeyCt);
		for (int i = 0; i < numKeyCt; i++) {
			if (_uiSettings.getCategories().containsKey(category)) {
				List<Integer> catElList1 = _uiSettings.getCategories().get(category);
				body.writeC(catElList1.size());
				for (int cmd : catElList1) {
					body.writeC(cmd);
				}
			} else {
				body.writeC(0x00);
			}
			category++;

			if (_uiSettings.getCategories().containsKey(category)) {
				List<Integer> catElList2 = _uiSettings.getCategories().get(category);
				body.writeC(catElList2.size());
				for (int cmd : catElList2) {
					body.writeC(cmd);
				}
			} else {
				body.writeC(0x00);
			}
			category++;

			if (_uiSettings.getKeys().containsKey(i)) {
				List<ActionKey> keyElList = _uiSettings.getKeys().get(i);
				body.writeD(keyElList.size());
				for (ActionKey akey : keyElList) {
					body.writeD(akey.getCommandId());
					body.writeD(akey.getKeyId());
					body.writeD(akey.getToogleKey1());
					body.writeD(akey.getToogleKey2());
					body.writeD(akey.getShowStatus());
				}
			} else {
				body.writeD(0x00);
			}
		}
		body.writeD(0x11);
		body.writeD(0x10);
	}
}