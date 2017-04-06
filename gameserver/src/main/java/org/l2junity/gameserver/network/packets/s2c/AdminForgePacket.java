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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is made to create packets with any format
 *
 * @author Maktakien
 */
public class AdminForgePacket extends GameServerPacket {
	private final List<Part> _parts = new ArrayList<>();

	private static class Part {
		public byte b;
		public String str;

		public Part(byte bb, String string) {
			b = bb;
			str = string;
		}
	}

	public AdminForgePacket() {

	}

	@Override
	protected void writeImpl(PacketBody body) {
		for (Part p : _parts) {
			generate(body, p.b, p.str);
		}
	}

	public boolean generate(PacketBody body, byte type, String value) {
		if ((type == 'C') || (type == 'c')) {
			body.writeC(Integer.decode(value));
			return true;
		} else if ((type == 'D') || (type == 'd')) {
			body.writeD(Integer.decode(value));
			return true;
		} else if ((type == 'H') || (type == 'h')) {
			body.writeH(Integer.decode(value));
			return true;
		} else if ((type == 'F') || (type == 'f')) {
			body.writeF(Double.parseDouble(value));
			return true;
		} else if ((type == 'S') || (type == 's')) {
			body.writeS(value);
			return true;
		} else if ((type == 'B') || (type == 'b') || (type == 'X') || (type == 'x')) {
			body.writeB(new BigInteger(value).toByteArray());
			return true;
		} else if ((type == 'Q') || (type == 'q')) {
			body.writeQ(Long.decode(value));
			return true;
		}
		return false;
	}

	public void addPart(byte b, String string) {
		_parts.add(new Part(b, string));
	}
}