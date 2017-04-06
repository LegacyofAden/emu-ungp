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

/**
 * @author Forsaiken, UnAfraid
 */
public final class SystemMessage extends AbstractMessagePacket<SystemMessage> {
	private SystemMessage(final SystemMessageId smId) {
		super(smId);
	}

	public static SystemMessage sendString(final String text) {
		if (text == null) {
			throw new NullPointerException();
		}

		final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S13);
		sm.addString(text);
		return sm;
	}

	public static SystemMessage getSystemMessage(final SystemMessageId smId) {
		SystemMessage sm = smId.getStaticSystemMessage();
		if (sm != null) {
			return sm;
		}

		sm = new SystemMessage(smId);
		if (smId.getParamCount() == 0) {
			smId.setStaticSystemMessage(sm);
		}

		return sm;
	}

	/**
	 * Use {@link #getSystemMessage(SystemMessageId)} where possible instead
	 *
	 * @param id
	 * @return the system message associated to the given Id.
	 */
	public static SystemMessage getSystemMessage(int id) {
		return getSystemMessage(SystemMessageId.getSystemMessageId(id));
	}

	/**
	 * Use SystemMessage.getSystemMessage(SystemMessageId smId) where possible instead
	 *
	 * @param id
	 * @deprecated
	 */
	@Deprecated
	private SystemMessage(final int id) {
		this(SystemMessageId.getSystemMessageId(id));
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SYSTEM_MESSAGE.writeId(body);

		body.writeH(getId());
		writeMe(body);
	}
}
