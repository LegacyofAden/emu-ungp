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
package org.l2junity.gameserver.network.client.send.pledgebonus;

import org.l2junity.gameserver.data.xml.impl.ClanRewardData;
import org.l2junity.gameserver.enums.ClanRewardType;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.pledge.ClanRewardBonus;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.gameserver.network.client.send.IClientOutgoingPacket;
import org.l2junity.network.PacketWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author UnAfraid
 */
public class ExPledgeBonusOpen implements IClientOutgoingPacket {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExPledgeBonusOpen.class);

	private final Player _player;

	public ExPledgeBonusOpen(Player player) {
		_player = player;
	}

	@Override
	public boolean write(PacketWriter packet) {
		final Clan clan = _player.getClan();
		if (clan == null) {
			LOGGER.warn("Player: {} attempting to write to a null clan!", _player);
			return false;
		}

		final ClanRewardBonus highestMembersOnlineBonus = ClanRewardData.getInstance().getHighestReward(ClanRewardType.MEMBERS_ONLINE);
		final ClanRewardBonus highestHuntingBonus = ClanRewardData.getInstance().getHighestReward(ClanRewardType.HUNTING_MONSTERS);
		final ClanRewardBonus membersOnlineBonus = ClanRewardType.MEMBERS_ONLINE.getAvailableBonus(clan);
		final ClanRewardBonus huntingBonus = ClanRewardType.HUNTING_MONSTERS.getAvailableBonus(clan);
		if (highestMembersOnlineBonus == null) {
			LOGGER.warn("Couldn't find highest available clan members online bonus!!");
			return false;
		} else if (highestHuntingBonus == null) {
			LOGGER.warn("Couldn't find highest available clan hunting bonus!!");
			return false;
		} else if (highestMembersOnlineBonus.getSkillReward() == null) {
			LOGGER.warn("Couldn't find skill reward for highest available members online bonus!!");
			return false;
		} else if (highestHuntingBonus.getItemReward() == null) {
			LOGGER.warn("Couldn't find item reward for highest available hunting bonus!!");
			return false;
		}

		// General OP Code
		OutgoingPackets.EX_PLEDGE_BONUS_OPEN.writeId(packet);

		// Members online bonus
		packet.writeD(highestMembersOnlineBonus.getRequiredAmount());
		packet.writeD(clan.getMaxOnlineMembers());
		packet.writeD(highestMembersOnlineBonus.getSkillReward().getSkillId());
		packet.writeC(membersOnlineBonus != null ? membersOnlineBonus.getLevel() : 0x00);
		packet.writeC(membersOnlineBonus != null ? 0x01 : 0x00);

		// Hunting bonus
		packet.writeD(highestHuntingBonus.getRequiredAmount());
		packet.writeD(clan.getHuntingPoints());
		packet.writeD(highestHuntingBonus.getItemReward().getId());
		packet.writeC(huntingBonus != null ? huntingBonus.getLevel() : 0x00);
		packet.writeC(huntingBonus != null ? 0x01 : 0x00);
		return true;
	}
}
