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
package org.l2junity.gameserver.network.client.send;

import org.l2junity.gameserver.enums.SubclassInfoType;
import org.l2junity.gameserver.enums.SubclassType;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.base.SubClass;
import org.l2junity.gameserver.network.client.OutgoingPackets;
import org.l2junity.network.PacketWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sdw
 */
public class ExSubjobInfo implements IClientOutgoingPacket {
	private final int _currClassId;
	private final int _currRace;
	private final int _type;
	private final List<SubInfo> _subs;

	public ExSubjobInfo(PlayerInstance player, SubclassInfoType type) {
		_currClassId = player.getClassId().getId();
		_currRace = player.getRace().ordinal();
		_type = type.ordinal();

		_subs = new ArrayList<>();
		_subs.add(0, new SubInfo(player));

		for (SubClass sub : player.getSubClasses().values()) {
			_subs.add(new SubInfo(sub));
		}
	}

	private final class SubInfo {
		private final int _index;
		private final int _classId;
		private final int _level;
		private final int _type;

		public SubInfo(SubClass sub) {
			_index = sub.getClassIndex();
			_classId = sub.getClassId();
			_level = sub.getLevel();
			_type = sub.isDualClass() ? SubclassType.DUALCLASS.ordinal() : SubclassType.SUBCLASS.ordinal();
		}

		public SubInfo(PlayerInstance player) {
			_index = 0;
			_classId = player.getBaseClass();
			_level = player.getStat().getBaseLevel();
			_type = SubclassType.BASECLASS.ordinal();
		}

		public int getIndex() {
			return _index;
		}

		public int getClassId() {
			return _classId;
		}

		public int getLevel() {
			return _level;
		}

		public int getType() {
			return _type;
		}
	}

	@Override
	public boolean write(PacketWriter packet) {
		OutgoingPackets.EX_SUBJOB_INFO.writeId(packet);

		packet.writeC(_type);
		packet.writeD(_currClassId);
		packet.writeD(_currRace);
		packet.writeD(_subs.size());
		for (SubInfo sub : _subs) {
			packet.writeD(sub.getIndex());
			packet.writeD(sub.getClassId());
			packet.writeD(sub.getLevel());
			packet.writeC(sub.getType());
		}
		return true;
	}
}