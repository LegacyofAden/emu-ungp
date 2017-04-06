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
package org.l2junity.gameserver.network.packets.s2c.mentoring;

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.instancemanager.MentorManager;
import org.l2junity.gameserver.model.Mentee;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @author UnAfraid
 */
public class ExMentorList extends GameServerPacket {
	private final int _type;
	private final Collection<Mentee> _mentees;

	public ExMentorList(Player activeChar) {
		if (activeChar.isMentor()) {
			_type = 0x01;
			_mentees = MentorManager.getInstance().getMentees(activeChar.getObjectId());
		} else if (activeChar.isMentee()) {
			_type = 0x02;
			_mentees = Arrays.asList(MentorManager.getInstance().getMentor(activeChar.getObjectId()));
		} else if (activeChar.isAwakenedClass()) // Not a mentor, Not a mentee, so can be a mentor
		{
			_mentees = Collections.emptyList();
			_type = 0x01;
		} else {
			_mentees = Collections.emptyList();
			_type = 0x00;
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_MENTOR_LIST.writeId(body);

		body.writeD(_type);
		body.writeD(0x00);
		body.writeD(_mentees.size());
		for (Mentee mentee : _mentees) {
			body.writeD(mentee.getObjectId());
			body.writeS(mentee.getName());
			body.writeD(mentee.getClassId());
			body.writeD(mentee.getLevel());
			body.writeD(mentee.isOnlineInt());
		}
	}
}
