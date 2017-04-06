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
import org.l2junity.gameserver.model.olympiad.Participant;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author godson
 */
public class ExOlympiadUserInfo extends GameServerPacket {
	private final Player _player;
	private Participant _par = null;
	private int _curHp;
	private int _maxHp;
	private int _curCp;
	private int _maxCp;

	public ExOlympiadUserInfo(Player player) {
		_player = player;
		if (_player != null) {
			_curHp = (int) _player.getCurrentHp();
			_maxHp = _player.getMaxHp();
			_curCp = (int) _player.getCurrentCp();
			_maxCp = _player.getMaxCp();
		} else {
			_curHp = 0;
			_maxHp = 100;
			_curCp = 0;
			_maxCp = 100;
		}
	}

	public ExOlympiadUserInfo(Participant par) {
		_par = par;
		_player = par.getPlayer();
		if (_player != null) {
			_curHp = (int) _player.getCurrentHp();
			_maxHp = _player.getMaxHp();
			_curCp = (int) _player.getCurrentCp();
			_maxCp = _player.getMaxCp();
		} else {
			_curHp = 0;
			_maxHp = 100;
			_curCp = 0;
			_maxCp = 100;
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_OLYMPIAD_USER_INFO.writeId(body);

		if (_player != null) {
			body.writeC(_player.getOlympiadSide());
			body.writeD(_player.getObjectId());
			body.writeS(_player.getName());
			body.writeD(_player.getClassId().getId());
		} else {
			body.writeC(_par.getSide());
			body.writeD(_par.getObjectId());
			body.writeS(_par.getName());
			body.writeD(_par.getBaseClass());
		}

		body.writeD(_curHp);
		body.writeD(_maxHp);
		body.writeD(_curCp);
		body.writeD(_maxCp);
	}
}