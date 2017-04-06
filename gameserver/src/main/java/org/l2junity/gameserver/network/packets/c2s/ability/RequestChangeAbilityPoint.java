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
package org.l2junity.gameserver.network.packets.c2s.ability;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.xml.impl.AbilityPointsData;
import org.l2junity.gameserver.enums.UserInfoType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.UserInfo;
import org.l2junity.gameserver.network.packets.s2c.ability.ExAcquireAPSkillList;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;


/**
 * @author UnAfraid
 */
public class RequestChangeAbilityPoint extends GameClientPacket {
	@Override
	public void readImpl() {

	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (activeChar.isSubClassActive() && !activeChar.isDualClassActive()) {
			return;
		}

		if ((activeChar.getLevel() < 99) || !activeChar.isNoble()) {
			activeChar.sendPacket(SystemMessageId.ABILITIES_CAN_BE_USED_BY_NOBLESSE_EXALTED_LV_99_OR_ABOVE);
			return;
		}

		if (activeChar.getAbilityPoints() >= PlayerConfig.ABILITY_MAX_POINTS) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_ACQUIRE_ANY_MORE_ABILITY_POINTS);
			return;
		}

		if (activeChar.isInOlympiadMode() || activeChar.isOnEvent(CeremonyOfChaosEvent.class)) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_USE_OR_RESET_ABILITY_POINTS_WHILE_PARTICIPATING_IN_THE_OLYMPIAD_OR_CEREMONY_OF_CHAOS);
			return;
		}

		long spRequired = AbilityPointsData.getInstance().getPrice(activeChar.getAbilityPoints());
		if (spRequired > activeChar.getSp()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_NEED_S1_SP_TO_CONVERT_TO1_ABILITY_POINT);
			sm.addLong(spRequired);
			activeChar.sendPacket(sm);
			return;
		}

		if (activeChar.getStat().removeSp(spRequired)) {
			activeChar.setAbilityPoints(activeChar.getAbilityPoints() + 1);
			final UserInfo info = new UserInfo(activeChar, false);
			info.addComponentType(UserInfoType.SLOTS, UserInfoType.CURRENT_HPMPCP_EXP_SP);
			activeChar.sendPacket(info);
			activeChar.sendPacket(new ExAcquireAPSkillList(activeChar));
		}
	}
}
