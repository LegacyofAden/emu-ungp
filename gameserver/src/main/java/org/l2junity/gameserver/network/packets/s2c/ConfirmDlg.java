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
import org.l2junity.gameserver.network.packets.GameServerPacketType;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketWriter;

/**
 * ConfirmDlg server packet implementation.
 *
 * @author kombat, UnAfraid
 */
public class ConfirmDlg extends AbstractMessagePacket<ConfirmDlg> {
	private int _time;
	private int _requesterId;

	public ConfirmDlg(SystemMessageId smId) {
		super(smId);
	}

	public ConfirmDlg(int id) {
		this(SystemMessageId.getSystemMessageId(id));
	}

	public ConfirmDlg(String text) {
		this(SystemMessageId.S13);
		addString(text);
	}

	public ConfirmDlg addTime(int time) {
		_time = time;
		return this;
	}

	public ConfirmDlg addRequesterId(int id) {
		_requesterId = id;
		return this;
	}

	@Override
	protected void writeParamsSize(PacketBody body, int size) {
		body.writeD(size);
	}

	@Override
	protected void writeParamType(PacketBody body, int type) {
		body.writeD(type);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.CONFIRM_DLG.writeId(body);

		body.writeD(getId());
		writeMe(body);
		body.writeD(_time);
		body.writeD(_requesterId);
	}
}
