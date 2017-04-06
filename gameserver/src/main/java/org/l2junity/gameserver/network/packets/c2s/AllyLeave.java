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
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


public final class AllyLeave extends GameClientPacket {
	@Override
	public void readImpl() {

	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (player.getClan() == null) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER_AND_CANNOT_PERFORM_THIS_ACTION);
			return;
		}
		if (!player.isClanLeader()) {
			player.sendPacket(SystemMessageId.ONLY_THE_CLAN_LEADER_MAY_APPLY_FOR_WITHDRAWAL_FROM_THE_ALLIANCE);
			return;
		}
		Clan clan = player.getClan();
		if (clan.getAllyId() == 0) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_ALLIED_WITH_ANY_CLANS);
			return;
		}
		if (clan.getId() == clan.getAllyId()) {
			player.sendPacket(SystemMessageId.ALLIANCE_LEADERS_CANNOT_WITHDRAW);
			return;
		}

		long currentTime = System.currentTimeMillis();
		clan.setAllyId(0);
		clan.setAllyName(null);
		clan.changeAllyCrest(0, true);
		clan.setAllyPenaltyExpiryTime(currentTime + (PlayerConfig.ALT_ALLY_JOIN_DAYS_WHEN_LEAVED * 86400000L), Clan.PENALTY_TYPE_CLAN_LEAVED); // 24*60*60*1000 = 86400000
		clan.updateClanInDB();

		player.sendPacket(SystemMessageId.YOU_HAVE_WITHDRAWN_FROM_THE_ALLIANCE);
	}
}