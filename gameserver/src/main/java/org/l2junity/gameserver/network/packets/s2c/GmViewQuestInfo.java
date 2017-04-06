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
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author Tempy
 */
public class GmViewQuestInfo extends GameServerPacket {
	private final Player _activeChar;
	private final List<Quest> _questList;

	public GmViewQuestInfo(Player cha) {
		_activeChar = cha;
		_questList = cha.getAllActiveQuests();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.GM_VIEW_QUEST_INFO.writeId(body);
		body.writeS(_activeChar.getName());
		body.writeH(_questList.size()); // quest count

		for (Quest quest : _questList) {
			final QuestState qs = _activeChar.getQuestState(quest.getName());

			body.writeD(quest.getId());
			body.writeD(qs == null ? 0 : qs.getCond());
		}
		body.writeH(0x00); // some size
		// for size; ddQQ
	}
}
