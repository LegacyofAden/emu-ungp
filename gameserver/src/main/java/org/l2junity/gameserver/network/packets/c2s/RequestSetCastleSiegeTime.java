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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.FeatureConfig;
import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.SiegeInfo;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Broadcast;
import org.l2junity.network.PacketReader;

import java.util.Calendar;
import java.util.Date;

/**
 * @author UnAfraid
 */
@Slf4j
public class RequestSetCastleSiegeTime extends GameClientPacket {
	private int _castleId;
	private long _time;

	@Override
	public void readImpl() {
		_castleId = readD();
		_time = readD();
		_time *= 1000;
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		final Castle castle = CastleManager.getInstance().getCastleById(_castleId);
		if ((activeChar == null) || (castle == null)) {
			log.warn(getClass().getSimpleName() + ": activeChar: " + activeChar + " castle: " + castle + " castleId: " + _castleId);
			return;
		}
		if ((castle.getOwnerId() > 0) && (castle.getOwnerId() != activeChar.getClanId())) {
			log.warn(getClass().getSimpleName() + ": activeChar: " + activeChar + " castle: " + castle + " castleId: " + _castleId + " is trying to change siege date of not his own castle!");
		} else if (!activeChar.isClanLeader()) {
			log.warn(getClass().getSimpleName() + ": activeChar: " + activeChar + " castle: " + castle + " castleId: " + _castleId + " is trying to change siege date but is not clan leader!");
		} else if (!castle.getIsTimeRegistrationOver()) {
			if (isSiegeTimeValid(castle.getSiegeDate().getTimeInMillis(), _time)) {
				castle.getSiegeDate().setTimeInMillis(_time);
				castle.setIsTimeRegistrationOver(true);
				castle.getSiege().saveSiegeDate();
				SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_ANNOUNCED_THE_NEXT_CASTLE_SIEGE_TIME);
				msg.addCastleId(_castleId);
				Broadcast.toAllOnlinePlayers(msg);
				activeChar.sendPacket(new SiegeInfo(castle, activeChar));
			} else {
				log.warn(getClass().getSimpleName() + ": activeChar: " + activeChar + " castle: " + castle + " castleId: " + _castleId + " is trying to an invalid time (" + new Date(_time) + " !");
			}
		} else {
			log.warn(getClass().getSimpleName() + ": activeChar: " + activeChar + " castle: " + castle + " castleId: " + _castleId + " is trying to change siege date but currently not possible!");
		}
	}

	private static boolean isSiegeTimeValid(long siegeDate, long choosenDate) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTimeInMillis(siegeDate);
		cal1.set(Calendar.MINUTE, 0);
		cal1.set(Calendar.SECOND, 0);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(choosenDate);

		for (int hour : FeatureConfig.SIEGE_HOUR_LIST) {
			cal1.set(Calendar.HOUR_OF_DAY, hour);
			if (isEqual(cal1, cal2, Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isEqual(Calendar cal1, Calendar cal2, int... fields) {
		for (int field : fields) {
			if (cal1.get(field) != cal2.get(field)) {
				return false;
			}
		}
		return true;
	}
}
