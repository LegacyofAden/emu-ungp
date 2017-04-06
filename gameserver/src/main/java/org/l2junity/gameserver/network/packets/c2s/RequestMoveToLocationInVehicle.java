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
import org.l2junity.gameserver.instancemanager.BoatManager;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.BoatInstance;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.items.type.WeaponType;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.MoveToLocationInVehicle;
import org.l2junity.gameserver.network.packets.s2c.StopMoveInVehicle;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

public final class RequestMoveToLocationInVehicle extends GameClientPacket {
	private int _boatId;
	private int _targetX;
	private int _targetY;
	private int _targetZ;
	private int _originX;
	private int _originY;
	private int _originZ;

	@Override
	public void readImpl() {
		_boatId = readD(); // objectId of boat
		_targetX = readD();
		_targetY = readD();
		_targetZ = readD();
		_originX = readD();
		_originY = readD();
		_originZ = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if ((PlayerConfig.PLAYER_MOVEMENT_BLOCK_TIME > 0) && !activeChar.isGM() && (activeChar.getNotMoveUntil() > System.currentTimeMillis())) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_MOVE_WHILE_SPEAKING_TO_AN_NPC_ONE_MOMENT_PLEASE);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if ((_targetX == _originX) && (_targetY == _originY) && (_targetZ == _originZ)) {
			getClient().sendPacket(new StopMoveInVehicle(activeChar, _boatId));
			return;
		}

		if (activeChar.isAttackingNow() && (activeChar.getActiveWeaponItem() != null) && (activeChar.getActiveWeaponItem().getItemType() == WeaponType.BOW)) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isSitting() || activeChar.isMovementDisabled()) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.hasSummon()) {
			getClient().sendPacket(SystemMessageId.YOU_SHOULD_RELEASE_YOUR_PET_OR_SERVITOR_SO_THAT_IT_DOES_NOT_FALL_OFF_OF_THE_BOAT_AND_DROWN);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		if (activeChar.isTransformed()) {
			getClient().sendPacket(SystemMessageId.YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_BOAT);
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final BoatInstance boat;
		if (activeChar.isInBoat()) {
			boat = activeChar.getBoat();
			if (boat.getObjectId() != _boatId) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
		} else {
			boat = BoatManager.getInstance().getBoat(_boatId);
			if ((boat == null) || !boat.isInRadius3d(activeChar, 300)) {
				getClient().sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			activeChar.setVehicle(boat);
		}

		final Location pos = new Location(_targetX, _targetY, _targetZ);
		final Location originPos = new Location(_originX, _originY, _originZ);
		activeChar.setInVehiclePosition(pos);
		activeChar.broadcastPacket(new MoveToLocationInVehicle(activeChar, pos, originPos));
	}
}
