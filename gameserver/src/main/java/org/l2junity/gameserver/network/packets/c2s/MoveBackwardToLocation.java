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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.ai.CtrlIntention;
import org.l2junity.gameserver.enums.AdminTeleportType;
import org.l2junity.gameserver.enums.SayuneType;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.SayuneEntry;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerMoveRequest;
import org.l2junity.gameserver.model.events.returns.TerminateReturn;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.*;
import org.l2junity.gameserver.network.packets.s2c.FlyToLocation.FlyType;
import org.l2junity.gameserver.network.packets.s2c.sayune.ExFlyMove;
import org.l2junity.gameserver.network.packets.s2c.sayune.ExFlyMoveBroadcast;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.gameserver.util.Broadcast;

import java.util.Arrays;

public class MoveBackwardToLocation extends GameClientPacket {
	// cdddddd
	private int _targetX;
	private int _targetY;
	private int _targetZ;
	private int _originX;
	private int _originY;
	private int _originZ;
	@SuppressWarnings("unused")
	private int _moveMovement;

	// For geodata
	private int _curX;
	private int _curY;
	@SuppressWarnings("unused")
	private int _curZ;

	@Override
	public void readImpl() {
		_targetX = readD();
		_targetY = readD();
		_targetZ = readD();
		_originX = readD();
		_originY = readD();
		_originZ = readD();
		_moveMovement = readD(); // is 0 if cursor keys are used 1 if mouse is used
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (activeChar.isInTraingCamp()) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isSpawnProtected()) {
			activeChar.onActionRequest();
		}

		if ((PlayerConfig.PLAYER_MOVEMENT_BLOCK_TIME > 0) && !activeChar.isGM() && (activeChar.getNotMoveUntil() > System.currentTimeMillis())) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_MOVE_WHILE_SPEAKING_TO_AN_NPC_ONE_MOMENT_PLEASE);
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if ((_targetX == _originX) && (_targetY == _originY) && (_targetZ == _originZ)) {
			activeChar.sendPacket(new StopMove(activeChar));
			return;
		}

		// Correcting targetZ from floor level to head level (?)
		// Client is giving floor level as targetZ but that floor level doesn't
		// match our current geodata and teleport coords as good as head level!
		// L2J uses floor, not head level as char coordinates. This is some
		// sort of incompatibility fix.
		// Validate position packets sends head level.
		_targetZ += activeChar.getTemplate().getCollisionHeight();

		final TerminateReturn terminate = EventDispatcher.getInstance().notifyEvent(new OnPlayerMoveRequest(activeChar, new Location(_targetX, _targetY, _targetZ)), activeChar, TerminateReturn.class);
		if ((terminate != null) && terminate.terminate()) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		_curX = (int) activeChar.getX();
		_curY = (int) activeChar.getY();
		_curZ = (int) activeChar.getZ();

		switch (activeChar.getTeleMode()) {
			case DEMONIC: {
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				activeChar.teleToLocation(new Location(_targetX, _targetY, _targetZ));
				activeChar.setTeleMode(AdminTeleportType.NORMAL);
				break;
			}
			case SAYUNE: {
				activeChar.sendPacket(new ExFlyMove(activeChar, SayuneType.ONE_WAY_LOC, -1, Arrays.asList(new SayuneEntry(false, -1, _targetX, _targetY, _targetZ))));
				activeChar.setXYZ(_targetX, _targetY, _targetZ);
				Broadcast.toKnownPlayers(activeChar, new ExFlyMoveBroadcast(activeChar, SayuneType.ONE_WAY_LOC, -1, new Location(_targetX, _targetY, _targetZ)));
				activeChar.setTeleMode(AdminTeleportType.NORMAL);
				break;
			}
			case CHARGE: {
				activeChar.setXYZ(_targetX, _targetY, _targetZ);
				Broadcast.toSelfAndKnownPlayers(activeChar, new MagicSkillUse(activeChar, 30012, 10, 500, 0));
				Broadcast.toSelfAndKnownPlayers(activeChar, new FlyToLocation(activeChar, _targetX, _targetY, _targetZ, FlyType.CHARGE));
				Broadcast.toSelfAndKnownPlayers(activeChar, new MagicSkillLaunched(activeChar, 30012, 10));
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				break;
			}
			default: {
				double dx = _targetX - _curX;
				double dy = _targetY - _curY;
				// Can't move if character is confused, or trying to move a huge distance
				if (((dx * dx) + (dy * dy)) > 98010000) // 9900*9900
				{
					activeChar.sendPacket(ActionFailed.STATIC_PACKET);
					return;
				}
				activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new Location(_targetX, _targetY, _targetZ));
				break;
			}
		}
	}
}
