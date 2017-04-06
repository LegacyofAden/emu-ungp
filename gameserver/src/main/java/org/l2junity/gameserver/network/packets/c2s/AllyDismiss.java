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
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


public final class AllyDismiss extends GameClientPacket {
	private String _clanName;

	@Override
	public void readImpl() {
		_clanName = readS();

	}

	@Override
	public void runImpl() {
		if (_clanName == null) {
			return;
		}

		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}
		if (player.getClan() == null) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_A_CLAN_MEMBER_AND_CANNOT_PERFORM_THIS_ACTION);
			return;
		}
		final Clan leaderClan = player.getClan();
		if (leaderClan.getAllyId() == 0) {
			player.sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_ALLIED_WITH_ANY_CLANS);
			return;
		}
		if (!player.isClanLeader() || (leaderClan.getId() != leaderClan.getAllyId())) {
			player.sendPacket(SystemMessageId.THIS_FEATURE_IS_ONLY_AVAILABLE_TO_ALLIANCE_LEADERS);
			return;
		}
		Clan clan = ClanTable.getInstance().getClanByName(_clanName);
		if (clan == null) {
			player.sendPacket(SystemMessageId.THAT_CLAN_DOES_NOT_EXIST);
			return;
		}
		if (clan.getId() == leaderClan.getId()) {
			player.sendPacket(SystemMessageId.ALLIANCE_LEADERS_CANNOT_WITHDRAW);
			return;
		}
		if (clan.getAllyId() != leaderClan.getAllyId()) {
			player.sendPacket(SystemMessageId.DIFFERENT_ALLIANCE);
			return;
		}

		long currentTime = System.currentTimeMillis();
		leaderClan.setAllyPenaltyExpiryTime(currentTime + (PlayerConfig.ALT_ACCEPT_CLAN_DAYS_WHEN_DISMISSED * 86400000L), Clan.PENALTY_TYPE_DISMISS_CLAN); // 24*60*60*1000 = 86400000
		leaderClan.updateClanInDB();

		clan.setAllyId(0);
		clan.setAllyName(null);
		clan.changeAllyCrest(0, true);
		clan.setAllyPenaltyExpiryTime(currentTime + (PlayerConfig.ALT_ALLY_JOIN_DAYS_WHEN_DISMISSED * 86400000L), Clan.PENALTY_TYPE_CLAN_DISMISSED); // 24*60*60*1000 = 86400000
		clan.updateClanInDB();

		player.sendPacket(SystemMessageId.YOU_HAVE_SUCCEEDED_IN_EXPELLING_THE_CLAN);
	}
}
