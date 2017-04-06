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
import org.l2junity.gameserver.enums.HtmlActionScope;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * NpcQuestHtmlMessage server packet implementation.
 *
 * @author HorridoJoho
 */
public final class NpcQuestHtmlMessage extends AbstractHtmlPacket {
	private final int _questId;

	public NpcQuestHtmlMessage(int npcObjId, int questId) {
		super(npcObjId);
		_questId = questId;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_NPC_QUEST_HTML_MESSAGE.writeId(body);

		body.writeD(getNpcObjId());
		body.writeS(getHtml());
		body.writeD(_questId);
	}

	@Override
	protected String getChatName() {
		return "Quest HTML"; // TODO confirm me please...
	}

	@Override
	public HtmlActionScope getScope() {
		return HtmlActionScope.NPC_QUEST_HTML;
	}
}
