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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author UnAfraid
 */
public class ExChangeClientEffectInfo extends GameServerPacket {
	public static final ExChangeClientEffectInfo STATIC_FREYA_DEFAULT = new ExChangeClientEffectInfo(0, 0, 1);
	public static final ExChangeClientEffectInfo STATIC_FREYA_DESTROYED = new ExChangeClientEffectInfo(0, 0, 2);

	private final int _type, _key, _value;

	/**
	 * @param type  <ul>
	 *              <li>0 - ChangeZoneState</li>
	 *              <li>1 - SetL2Fog</li>
	 *              <li>2 - postEffectData</li>
	 *              </ul>
	 * @param key
	 * @param value
	 */
	public ExChangeClientEffectInfo(int type, int key, int value) {
		_type = type;
		_key = key;
		_value = value;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_CHANGE_CLIENT_EFFECT_INFO.writeId(body);

		body.writeD(_type);
		body.writeD(_key);
		body.writeD(_value);
	}
}