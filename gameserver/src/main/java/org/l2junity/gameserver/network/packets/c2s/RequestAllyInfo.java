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

import org.l2junity.gameserver.model.ClanInfo;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.AllianceInfo;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestAllyInfo extends GameClientPacket {
	@Override
	public void readImpl() {
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		SystemMessage sm;
		final int allianceId = activeChar.getAllyId();
		if (allianceId > 0) {
			final AllianceInfo ai = new AllianceInfo(allianceId);
			getClient().sendPacket(ai);

			// send for player
			sm = SystemMessage.getSystemMessage(SystemMessageId.ALLIANCE_INFORMATION);
			getClient().sendPacket(sm);

			sm = SystemMessage.getSystemMessage(SystemMessageId.ALLIANCE_NAME_S1);
			sm.addString(ai.getName());
			getClient().sendPacket(sm);

			sm = SystemMessage.getSystemMessage(SystemMessageId.ALLIANCE_LEADER_S2_OF_S1);
			sm.addString(ai.getLeaderC());
			sm.addString(ai.getLeaderP());
			getClient().sendPacket(sm);

			sm = SystemMessage.getSystemMessage(SystemMessageId.CONNECTION_S1_TOTAL_S2);
			sm.addInt(ai.getOnline());
			sm.addInt(ai.getTotal());
			getClient().sendPacket(sm);

			sm = SystemMessage.getSystemMessage(SystemMessageId.AFFILIATED_CLANS_TOTAL_S1_CLAN_S);
			sm.addInt(ai.getAllies().length);
			getClient().sendPacket(sm);

			sm = SystemMessage.getSystemMessage(SystemMessageId.CLAN_INFORMATION);
			for (final ClanInfo aci : ai.getAllies()) {
				getClient().sendPacket(sm);

				sm = SystemMessage.getSystemMessage(SystemMessageId.CLAN_NAME_S1);
				sm.addString(aci.getClan().getName());
				getClient().sendPacket(sm);

				sm = SystemMessage.getSystemMessage(SystemMessageId.CLAN_LEADER_S1);
				sm.addString(aci.getClan().getLeaderName());
				getClient().sendPacket(sm);

				sm = SystemMessage.getSystemMessage(SystemMessageId.CLAN_LEVEL_S1);
				sm.addInt(aci.getClan().getLevel());
				getClient().sendPacket(sm);

				sm = SystemMessage.getSystemMessage(SystemMessageId.CONNECTION_S1_TOTAL_S2);
				sm.addInt(aci.getOnline());
				sm.addInt(aci.getTotal());
				getClient().sendPacket(sm);

				sm = SystemMessage.getSystemMessage(SystemMessageId.EMPTY4);
			}

			sm = SystemMessage.getSystemMessage(SystemMessageId.EMPTY5);
			getClient().sendPacket(sm);
		} else {
			getClient().sendPacket(SystemMessageId.YOU_ARE_NOT_CURRENTLY_ALLIED_WITH_ANY_CLANS);
		}
	}
}
