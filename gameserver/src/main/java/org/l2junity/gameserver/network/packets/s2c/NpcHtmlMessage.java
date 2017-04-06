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
 * NpcHtmlMessage server packet implementation.
 *
 * @author HorridoJoho
 */
public final class NpcHtmlMessage extends AbstractHtmlPacket {
	private final int _itemId;

	public NpcHtmlMessage() {
		_itemId = 0;
	}

	public NpcHtmlMessage(int npcObjId) {
		super(npcObjId);
		_itemId = 0;
	}

	public NpcHtmlMessage(String html) {
		super(html);
		_itemId = 0;
	}

	public NpcHtmlMessage(int npcObjId, String html) {
		super(npcObjId, html);
		_itemId = 0;
	}

	public NpcHtmlMessage(int npcObjId, int itemId) {
		super(npcObjId);

		if (itemId < 0) {
			throw new IllegalArgumentException();
		}

		_itemId = itemId;
	}

	public NpcHtmlMessage(int npcObjId, int itemId, String html) {
		super(npcObjId, html);

		if (itemId < 0) {
			throw new IllegalArgumentException();
		}

		_itemId = itemId;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.NPC_HTML_MESSAGE.writeId(body);

		body.writeD(getNpcObjId());
		body.writeS(getHtml());
		body.writeD(_itemId);
		body.writeD(0x00); // TODO: Find me!
	}

	@Override
	protected String getChatName() {
		return "HTML";
	}

	@Override
	public HtmlActionScope getScope() {
		return _itemId == 0 ? HtmlActionScope.NPC_HTML : HtmlActionScope.NPC_ITEM_HTML;
	}
}
