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

import org.l2junity.gameserver.data.sql.impl.CrestTable;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.Crest;
import org.l2junity.gameserver.model.Crest.CrestType;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * Format : chdb c (id) 0xD0 h (subid) 0x11 d data size b raw data (picture i think ;) )
 *
 * @author -Wooden-
 */
public final class RequestExSetPledgeCrestLarge extends GameClientPacket {
	private int _length;
	private byte[] _data = null;

	@Override
	public void readImpl() {
		_length = readD();
		if (_length > 2176) {
			return;
		}

		_data = readB(_length);
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final Clan clan = activeChar.getClan();
		if (clan == null) {
			return;
		}

		if ((_length < 0) || (_length > 2176)) {
			getClient().sendPacket(SystemMessageId.THE_SIZE_OF_THE_UPLOADED_SYMBOL_DOES_NOT_MEET_THE_STANDARD_REQUIREMENTS);
			return;
		}

		if (clan.getDissolvingExpiryTime() > System.currentTimeMillis()) {
			getClient().sendPacket(SystemMessageId.AS_YOU_ARE_CURRENTLY_SCHEDULE_FOR_CLAN_DISSOLUTION_YOU_CANNOT_REGISTER_OR_DELETE_A_CLAN_CREST);
			return;
		}

		if (!activeChar.hasClanPrivilege(ClanPrivilege.CL_REGISTER_CREST)) {
			getClient().sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return;
		}

		if (_length == 0) {
			if (clan.getCrestLargeId() != 0) {
				clan.changeLargeCrest(0);
				getClient().sendPacket(SystemMessageId.THE_CLAN_MARK_HAS_BEEN_DELETED);
			}
		} else {
			if (clan.getLevel() < 3) {
				getClient().sendPacket(SystemMessageId.A_CLAN_CREST_CAN_ONLY_BE_REGISTERED_WHEN_THE_CLAN_S_SKILL_LEVEL_IS_3_OR_ABOVE);
				return;
			}

			final Crest crest = CrestTable.getInstance().createCrest(_data, CrestType.PLEDGE_LARGE);
			if (crest != null) {
				clan.changeLargeCrest(crest.getId());
				getClient().sendPacket(SystemMessageId.THE_CLAN_MARK_WAS_SUCCESSFULLY_REGISTERED_THE_SYMBOL_WILL_APPEAR_ON_THE_CLAN_FLAG_AND_THE_INSIGNIA_IS_ONLY_DISPLAYED_ON_ITEMS_PERTAINING_TO_A_CLAN_THAT_OWNS_A_CLAN_HALL_OR_CASTLE);
			}
		}
	}
}
