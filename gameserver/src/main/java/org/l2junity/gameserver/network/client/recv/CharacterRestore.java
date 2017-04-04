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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.gameserver.model.CharSelectInfoPackage;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerRestore;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.CharSelectionInfo;
import org.l2junity.network.PacketReader;

public final class CharacterRestore implements IClientIncomingPacket {
	// cd
	private int charSlot;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		charSlot = packet.readD();
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		if (!client.getFloodProtectors().getCharacterSelect().tryPerformAction("CharacterRestore")) {
			return;
		}

		client.restore(charSlot);
		CharSelectionInfo cl = new CharSelectionInfo(client.getSessionInfo(), 0);
		client.sendPacket(cl);
		client.setCharSelection(cl.getCharInfo());
		final CharSelectInfoPackage charInfo = client.getCharSelection(charSlot);
		EventDispatcher.getInstance().notifyEvent(new OnPlayerRestore(charInfo.getObjectId(), charInfo.getName(), client));
	}
}
