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

import org.l2junity.commons.network.PacketBody;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.LinkedList;
import java.util.List;

public class QuestList extends GameServerPacket {
	private final List<QuestState> _activeQuests;
	private final byte[] _oneTimeQuestMask;

	public QuestList(Player player) {
		_activeQuests = new LinkedList<>();
		_oneTimeQuestMask = new byte[128];

		for (QuestState qs : player.getAllQuestStates()) {
			final int questId = qs.getQuestId();
			if (questId > 0) {
				if (qs.isStarted()) {
					_activeQuests.add(qs);
				} else if (qs.isCompleted() && !(((questId > 255) && (questId < 10256)) || (questId > 11023))) {
					_oneTimeQuestMask[(questId % 10000) / 8] |= 1 << (questId % 8);
				}
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.QUEST_LIST.writeId(body);
		body.writeH(_activeQuests.size());
		for (QuestState qs : _activeQuests) {
			body.writeD(qs.getQuestId()); // Quest ID
			body.writeD(qs.getRawCond()); // Quest state
		}
		body.writeB(_oneTimeQuestMask);
	}
}