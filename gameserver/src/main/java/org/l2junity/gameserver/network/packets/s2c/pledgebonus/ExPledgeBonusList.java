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

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.data.xml.impl.ClanRewardData;
import org.l2junity.gameserver.enums.ClanRewardType;
import org.l2junity.gameserver.model.pledge.ClanRewardBonus;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

/**
 * @author UnAfraid
 */
public class ExPledgeBonusList extends GameServerPacket {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExPledgeBonusList.class);

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_PLEDGE_BONUS_LIST.writeId(body);
		for (ClanRewardType type : ClanRewardType.values()) {
			ClanRewardData.getInstance().getClanRewardBonuses(type).stream().sorted(Comparator.comparingInt(ClanRewardBonus::getLevel)).forEach(bonus ->
			{
				switch (type) {
					case MEMBERS_ONLINE: {
						if (bonus.getSkillReward() == null) {
							LOGGER.warn("Missing clan reward skill for reward level: {}", bonus.getLevel());
							body.writeD(0);
							return;
						}

						body.writeD(bonus.getSkillReward().getSkillId());
						break;
					}
					case HUNTING_MONSTERS: {
						if (bonus.getItemReward() == null) {
							LOGGER.warn("Missing clan reward skill for reward level: {}", bonus.getLevel());
							body.writeD(0);
							return;
						}

						body.writeD(bonus.getItemReward().getId());
						break;
					}
				}
			});
		}
	}
}
