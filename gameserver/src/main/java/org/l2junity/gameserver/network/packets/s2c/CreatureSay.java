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
import org.l2junity.gameserver.instancemanager.MentorManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.s2c.string.NpcStringId;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.ArrayList;
import java.util.List;

public final class CreatureSay extends GameServerPacket {
	private final int _objectId;
	private final ChatType _textType;
	private String _charName = null;
	private int _charId = 0;
	private String _text = null;
	private int _npcString = -1;
	private int _mask;
	private int _charLevel = -1;
	private List<String> _parameters;

	/**
	 * @param sender
	 * @param receiver
	 * @param name
	 * @param messageType
	 * @param text
	 */
	public CreatureSay(Player sender, Player receiver, String name, ChatType messageType, String text) {
		_objectId = sender.getObjectId();
		_charName = name;
		_charLevel = sender.getLevel();
		_textType = messageType;
		_text = text;
		if (receiver.getFriendList().contains(sender.getObjectId())) {
			_mask |= 0x01;
		}
		if ((receiver.getClanId() > 0) && (receiver.getClanId() == sender.getClanId())) {
			_mask |= 0x02;
		}
		if ((MentorManager.getInstance().getMentee(receiver.getObjectId(), sender.getObjectId()) != null) || (MentorManager.getInstance().getMentee(sender.getObjectId(), receiver.getObjectId()) != null)) {
			_mask |= 0x04;
		}
		if ((receiver.getAllyId() > 0) && (receiver.getAllyId() == sender.getAllyId())) {
			_mask |= 0x08;
		}

		// Does not shows level
		if (sender.isGM()) {
			_mask |= 0x10;
		}
	}

	/**
	 * @param objectId
	 * @param messageType
	 * @param charName
	 * @param text
	 */
	public CreatureSay(int objectId, ChatType messageType, String charName, String text) {
		_objectId = objectId;
		_textType = messageType;
		_charName = charName;
		_text = text;
	}

	public CreatureSay(Player player, ChatType messageType, String text) {
		_objectId = player.getObjectId();
		_textType = messageType;
		_charName = player.getAppearance().getVisibleName();
		_text = text;
	}

	public CreatureSay(int objectId, ChatType messageType, int charId, NpcStringId npcString) {
		_objectId = objectId;
		_textType = messageType;
		_charId = charId;
		_npcString = npcString.getId();
	}

	public CreatureSay(int objectId, ChatType messageType, String charName, NpcStringId npcString) {
		_objectId = objectId;
		_textType = messageType;
		_charName = charName;
		_npcString = npcString.getId();
	}

	public CreatureSay(int objectId, ChatType messageType, int charId, SystemMessageId sysString) {
		_objectId = objectId;
		_textType = messageType;
		_charId = charId;
		_npcString = sysString.getId();
	}

	public CreatureSay(int objectId, ChatType messageType, String charName, SystemMessageId sysString) {
		_objectId = objectId;
		_textType = messageType;
		_charName = charName;
		_npcString = sysString.getId();
	}

	/**
	 * String parameter for argument S1,S2,.. in npcstring-e.dat
	 *
	 * @param text
	 */
	public void addStringParameter(String text) {
		if (_parameters == null) {
			_parameters = new ArrayList<>();
		}
		_parameters.add(text);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SAY2.writeId(body);

		body.writeD(_objectId);
		body.writeD(_textType.getClientId());
		if (_charName != null) {
			body.writeS(_charName);
		} else {
			body.writeD(_charId);
		}
		body.writeD(_npcString); // High Five NPCString ID
		if (_text != null) {
			body.writeS(_text);
			if ((_charLevel > 0) && (_textType == ChatType.WHISPER)) {
				body.writeC(_mask);
				if ((_mask & 0x10) == 0) {
					body.writeC(_charLevel);
				}
			}
		} else if (_parameters != null) {
			for (String s : _parameters) {
				body.writeS(s);
			}
		}
	}

	@Override
	public final void run(Player player) {
		if (player != null) {
			player.broadcastSnoop(_textType, _charName, _text);
		}
	}
}
