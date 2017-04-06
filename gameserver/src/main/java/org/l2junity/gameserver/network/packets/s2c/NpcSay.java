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
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kerberos
 */
public final class NpcSay extends GameServerPacket {
	private final int _objectId;
	private final ChatType _textType;
	private final int _npcId;
	private String _text;
	private final int _npcString;
	private List<String> _parameters;

	/**
	 * @param objectId
	 * @param messageType
	 * @param npcId
	 * @param text
	 */
	public NpcSay(int objectId, ChatType messageType, int npcId, String text) {
		_objectId = objectId;
		_textType = messageType;
		_npcId = 1000000 + npcId;
		_npcString = -1;
		_text = text;
	}

	public NpcSay(Npc npc, ChatType messageType, String text) {
		_objectId = npc.getObjectId();
		_textType = messageType;
		_npcId = 1000000 + npc.getTemplate().getDisplayId();
		_npcString = -1;
		_text = text;
	}

	public NpcSay(int objectId, ChatType messageType, int npcId, NpcStringId npcString) {
		_objectId = objectId;
		_textType = messageType;
		_npcId = 1000000 + npcId;
		_npcString = npcString.getId();
	}

	public NpcSay(Npc npc, ChatType messageType, NpcStringId npcString) {
		_objectId = npc.getObjectId();
		_textType = messageType;
		_npcId = 1000000 + npc.getTemplate().getDisplayId();
		_npcString = npcString.getId();
	}

	/**
	 * @param text the text to add as a parameter for this packet's message (replaces S1, S2 etc.)
	 * @return this NpcSay packet object
	 */
	public NpcSay addStringParameter(String text) {
		if (_parameters == null) {
			_parameters = new ArrayList<>();
		}
		_parameters.add(text);
		return this;
	}

	/**
	 * @param params a list of strings to add as parameters for this packet's message (replaces S1, S2 etc.)
	 * @return this NpcSay packet object
	 */
	public NpcSay addStringParameters(String... params) {
		if ((params != null) && (params.length > 0)) {
			if (_parameters == null) {
				_parameters = new ArrayList<>();
			}

			for (String item : params) {
				if ((item != null) && (item.length() > 0)) {
					_parameters.add(item);
				}
			}
		}
		return this;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.NPC_SAY.writeId(body);

		body.writeD(_objectId);
		body.writeD(_textType.getClientId());
		body.writeD(_npcId);
		body.writeD(_npcString);
		if (_npcString == -1) {
			body.writeS(_text);
		} else if (_parameters != null) {
			for (String s : _parameters) {
				body.writeS(s);
			}
		}
	}
}