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
package org.l2junity.gameserver.network.packets.s2c.pledgebonus;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.data.xml.impl.ClanRewardData;
import org.l2junity.gameserver.enums.ClanRewardType;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.pledge.ClanRewardBonus;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author UnAfraid
 */
@Slf4j
public class ExPledgeBonusOpen extends GameServerPacket {
	private final Player _player;

	public ExPledgeBonusOpen(Player player) {
		_player = player;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		final Clan clan = _player.getClan();
		if (clan == null) {
			log.warn("Player: {} attempting to write to a null clan!", _player);
			return;
		}

		final ClanRewardBonus highestMembersOnlineBonus = ClanRewardData.getInstance().getHighestReward(ClanRewardType.MEMBERS_ONLINE);
		final ClanRewardBonus highestHuntingBonus = ClanRewardData.getInstance().getHighestReward(ClanRewardType.HUNTING_MONSTERS);
		final ClanRewardBonus membersOnlineBonus = ClanRewardType.MEMBERS_ONLINE.getAvailableBonus(clan);
		final ClanRewardBonus huntingBonus = ClanRewardType.HUNTING_MONSTERS.getAvailableBonus(clan);
		if (highestMembersOnlineBonus == null) {
			log.warn("Couldn't find highest available clan members online bonus!!");
			return;
		} else if (highestHuntingBonus == null) {
			log.warn("Couldn't find highest available clan hunting bonus!!");
			return;
		} else if (highestMembersOnlineBonus.getSkillReward() == null) {
			log.warn("Couldn't find skill reward for highest available members online bonus!!");
			return;
		} else if (highestHuntingBonus.getItemReward() == null) {
			log.warn("Couldn't find item reward for highest available hunting bonus!!");
			return;
		}

		// General OP Code
		GameServerPacketType.EX_PLEDGE_BONUS_OPEN.writeId(body);

		// Members online bonus
		body.writeD(highestMembersOnlineBonus.getRequiredAmount());
		body.writeD(clan.getMaxOnlineMembers());
		body.writeD(highestMembersOnlineBonus.getSkillReward().getSkillId());
		body.writeC(membersOnlineBonus != null ? membersOnlineBonus.getLevel() : 0x00);
		body.writeC(membersOnlineBonus != null ? 0x01 : 0x00);

		// Hunting bonus
		body.writeD(highestHuntingBonus.getRequiredAmount());
		body.writeD(clan.getHuntingPoints());
		body.writeD(highestHuntingBonus.getItemReward().getId());
		body.writeC(huntingBonus != null ? huntingBonus.getLevel() : 0x00);
		body.writeC(huntingBonus != null ? 0x01 : 0x00);
	}
}
