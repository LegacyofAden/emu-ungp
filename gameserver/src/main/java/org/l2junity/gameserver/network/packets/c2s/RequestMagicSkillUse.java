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

import lombok.extern.slf4j.Slf4j;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerUseSkill;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

@Slf4j
public final class RequestMagicSkillUse extends GameClientPacket {
	private int _magicId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;

	@Override
	public void readImpl() {
		_magicId = readD(); // Identifier of the used skill
		_ctrlPressed = readD() != 0; // True if it's a ForceAttack : Ctrl pressed
		_shiftPressed = readC() != 0; // True if Shift pressed
	}

	@Override
	public void runImpl() {
		// Get the current L2PcInstance of the player
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (activeChar.isSpawnProtected()) {
			activeChar.onActionRequest();
		}

		// Get the level of the used skill
		Skill skill = activeChar.getKnownSkill(_magicId);
		if (skill == null) {
			// Player doesn't know this skill, maybe it's the display Id.
			skill = activeChar.getCustomSkill(_magicId);
			if (skill == null) {
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				log.warn("Skill Id " + _magicId + " not found in player!");
				return;
			}
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerUseSkill(activeChar, skill, _ctrlPressed, _shiftPressed), activeChar, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		// Skill is blocked from player use.
		if (skill.isBlockActionUseSkill()) {
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Avoid Use of Skills in AirShip.
		if (activeChar.isInAirShip()) {
			activeChar.sendPacket(SystemMessageId.THIS_ACTION_IS_PROHIBITED_WHILE_MOUNTED_OR_ON_AN_AIRSHIP);
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		activeChar.useMagic(skill, activeChar.getTarget(), null, _ctrlPressed, _shiftPressed);
	}
}
