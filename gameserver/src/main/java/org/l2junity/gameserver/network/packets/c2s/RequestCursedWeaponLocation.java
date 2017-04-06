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

import org.l2junity.gameserver.instancemanager.CursedWeaponsManager;
import org.l2junity.gameserver.model.CursedWeapon;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExCursedWeaponLocation;
import org.l2junity.gameserver.network.packets.s2c.ExCursedWeaponLocation.CursedWeaponInfo;
import org.l2junity.network.PacketReader;

import java.util.LinkedList;
import java.util.List;

/**
 * Format: (ch)
 *
 * @author -Wooden-
 */
public final class RequestCursedWeaponLocation extends GameClientPacket {
	@Override
	public void readImpl() {
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final List<CursedWeaponInfo> list = new LinkedList<>();
		for (CursedWeapon cw : CursedWeaponsManager.getInstance().getCursedWeapons()) {
			if (!cw.isActive()) {
				continue;
			}

			Location pos = cw.getWorldPosition();
			if (pos != null) {
				list.add(new CursedWeaponInfo(pos, cw.getItemId(), cw.isActivated() ? 1 : 0));
			}
		}

		// send the ExCursedWeaponLocation
		if (!list.isEmpty()) {
			getClient().sendPacket(new ExCursedWeaponLocation(list));
		}
	}
}
