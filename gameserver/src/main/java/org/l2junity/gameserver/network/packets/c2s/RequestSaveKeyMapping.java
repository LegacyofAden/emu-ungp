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

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.UIData;
import org.l2junity.gameserver.model.ActionKey;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClientState;
import org.l2junity.gameserver.network.packets.GameClientPacket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Request Save Key Mapping client packet.
 *
 * @author mrTJO, Zoey76
 */
public class RequestSaveKeyMapping extends GameClientPacket {
	private final Map<Integer, List<ActionKey>> _keyMap = new HashMap<>();
	private final Map<Integer, List<Integer>> _catMap = new HashMap<>();

	@Override
	public void readImpl() {
		int category = 0;

		readD(); // Unknown
		readD(); // Unknown
		final int _tabNum = readD();
		for (int i = 0; i < _tabNum; i++) {
			int cmd1Size = readC();
			for (int j = 0; j < cmd1Size; j++) {
				UIData.addCategory(_catMap, category, readC());
			}
			category++;

			int cmd2Size = readC();
			for (int j = 0; j < cmd2Size; j++) {
				UIData.addCategory(_catMap, category, readC());
			}
			category++;

			int cmdSize = readD();
			for (int j = 0; j < cmdSize; j++) {
				int cmd = readD();
				int key = readD();
				int tgKey1 = readD();
				int tgKey2 = readD();
				int show = readD();
				UIData.addKey(_keyMap, i, new ActionKey(i, cmd, key, tgKey1, tgKey2, show));
			}
		}
		readD();
		readD();

	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (!PlayerConfig.STORE_UI_SETTINGS || (player == null) || (getClient().getState() != GameClientState.IN_GAME)) {
			return;
		}
		player.getUISettings().storeAll(_catMap, _keyMap);
	}
}
