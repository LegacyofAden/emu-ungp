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

import org.l2junity.gameserver.network.packets.GameClientPacket;

/**
 * @author Sdw
 */
public class ExBookmarkPacket extends GameClientPacket {
	private GameClientPacket exBookmarkPacket;

	@Override
	public void readImpl() {
		int subId = readD();

		switch (subId) {
			case 0: {
				exBookmarkPacket = new RequestBookMarkSlotInfo();
				break;
			}
			case 1: {
				exBookmarkPacket = new RequestSaveBookMarkSlot();
				break;
			}
			case 2: {
				exBookmarkPacket = new RequestModifyBookMarkSlot();
				break;
			}
			case 3: {
				exBookmarkPacket = new RequestDeleteBookMarkSlot();
				break;
			}
			case 4: {
				exBookmarkPacket = new RequestTeleportBookMark();
				break;
			}
			case 5: {
				exBookmarkPacket = new RequestChangeBookMarkSlot();
				break;
			}
		}
		exBookmarkPacket.setBuffer(getBuffer());
		exBookmarkPacket.setClient(getClient());
		exBookmarkPacket.read();
	}

	@Override
	public void runImpl() {
		exBookmarkPacket.run();
	}
}