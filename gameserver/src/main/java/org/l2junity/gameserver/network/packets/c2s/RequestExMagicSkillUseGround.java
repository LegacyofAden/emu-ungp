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
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.model.Location;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.ValidateLocation;
import org.l2junity.gameserver.util.Broadcast;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;

/**
 * Fromat:(ch) dddddc
 *
 * @author -Wooden-
 */
@Slf4j
public final class RequestExMagicSkillUseGround extends GameClientPacket {
	private int _x;
	private int _y;
	private int _z;
	private int _skillId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;

	@Override
	public void readImpl() {
		_x = readD();
		_y = readD();
		_z = readD();
		_skillId = readD();
		_ctrlPressed = readD() != 0;
		_shiftPressed = readC() != 0;
	}

	@Override
	public void runImpl() {
		// Get the current L2PcInstance of the player
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		// Get the level of the used skill
		int level = activeChar.getSkillLevel(_skillId);
		if (level <= 0) {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		// Get the L2Skill template corresponding to the skillID received from the client
		Skill skill = SkillData.getInstance().getSkill(_skillId, level);

		// Check the validity of the skill
		if (skill != null) {
			// normally magicskilluse packet turns char client side but for these skills, it doesn't (even with correct target)
			activeChar.setHeading(Util.calculateHeadingFrom(activeChar.getX(), activeChar.getY(), _x, _y));
			Broadcast.toKnownPlayers(activeChar, new ValidateLocation(activeChar));

			activeChar.useMagic(skill, new Location(_x, _y, _z), null, _ctrlPressed, _shiftPressed);
		} else {
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			log.warn("No skill found with id " + _skillId + " and level " + level + " !!");
		}
	}
}
