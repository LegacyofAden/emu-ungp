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
package org.l2junity.gameserver.network.packets.s2c;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.network.PacketBody;
import org.l2junity.core.configs.FeatureConfig;
import org.l2junity.gameserver.data.sql.impl.ClanTable;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Calendar;

/**
 * Shows the Siege Info<BR>
 * <BR>
 * c = c9<BR>
 * d = CastleID<BR>
 * d = Show Owner Controls (0x00 default || >=0x02(mask?) owner)<BR>
 * d = Owner ClanID<BR>
 * S = Owner ClanName<BR>
 * S = Owner Clan LeaderName<BR>
 * d = Owner AllyID<BR>
 * S = Owner AllyName<BR>
 * d = current time (seconds)<BR>
 * d = Siege time (seconds) (0 for selectable)<BR>
 * d = (UNKNOW) Siege Time Select Related?
 *
 * @author KenM
 */
@Slf4j
public class SiegeInfo extends GameServerPacket {
	private final Castle _castle;
	private final Player _player;

	public SiegeInfo(Castle castle, Player player) {
		_castle = castle;
		_player = player;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CASTLE_SIEGE_INFO.writeId(body);

		if (_castle != null) {
			body.writeD(_castle.getResidenceId());

			final int ownerId = _castle.getOwnerId();

			body.writeD(((ownerId == _player.getClanId()) && (_player.isClanLeader())) ? 0x01 : 0x00);
			body.writeD(ownerId);
			if (ownerId > 0) {
				Clan owner = ClanTable.getInstance().getClan(ownerId);
				if (owner != null) {
					body.writeS(owner.getName()); // Clan Name
					body.writeS(owner.getLeaderName()); // Clan Leader Name
					body.writeD(owner.getAllyId()); // Ally ID
					body.writeS(owner.getAllyName()); // Ally Name
				} else {
					log.warn("Null owner for castle: " + _castle.getName());
				}
			} else {
				body.writeS(""); // Clan Name
				body.writeS(""); // Clan Leader Name
				body.writeD(0); // Ally ID
				body.writeS(""); // Ally Name
			}

			body.writeD((int) (System.currentTimeMillis() / 1000));
			if (!_castle.getIsTimeRegistrationOver() && _player.isClanLeader() && (_player.getClanId() == _castle.getOwnerId())) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(_castle.getSiegeDate().getTimeInMillis());
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);

				body.writeD(0x00);
				body.writeD(FeatureConfig.SIEGE_HOUR_LIST.size());
				for (int hour : FeatureConfig.SIEGE_HOUR_LIST) {
					cal.set(Calendar.HOUR_OF_DAY, hour);
					body.writeD((int) (cal.getTimeInMillis() / 1000));
				}
			} else {
				body.writeD((int) (_castle.getSiegeDate().getTimeInMillis() / 1000));
				body.writeD(0x00);
			}
		}
	}
}
