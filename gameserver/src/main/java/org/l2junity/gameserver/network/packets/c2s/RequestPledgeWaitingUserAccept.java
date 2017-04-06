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

import org.l2junity.gameserver.enums.UserInfoType;
import org.l2junity.gameserver.instancemanager.ClanEntryManager;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.JoinPledge;
import org.l2junity.gameserver.network.packets.s2c.UserInfo;


/**
 * @author Sdw
 */
public class RequestPledgeWaitingUserAccept extends GameClientPacket {
	private boolean _acceptRequest;
	private int _playerId;
	private int _clanId;

	@Override
	public void readImpl() {
		_acceptRequest = readD() == 1;
		_playerId = readD();
		_clanId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if ((activeChar == null) || (activeChar.getClan() == null)) {
			return;
		}

		if (_acceptRequest) {
			final Player player = WorldManager.getInstance().getPlayer(_playerId);
			if (player != null) {
				final Clan clan = activeChar.getClan();
				clan.addClanMember(player);
				player.sendPacket(new JoinPledge(_clanId));
				final UserInfo ui = new UserInfo(player);
				ui.addComponentType(UserInfoType.CLAN);
				player.sendPacket(ui);
				player.broadcastInfo();

				ClanEntryManager.getInstance().removePlayerApplication(clan.getId(), _playerId);
			}
		} else {
			ClanEntryManager.getInstance().removePlayerApplication(activeChar.getClanId(), _playerId);
		}
	}
}
