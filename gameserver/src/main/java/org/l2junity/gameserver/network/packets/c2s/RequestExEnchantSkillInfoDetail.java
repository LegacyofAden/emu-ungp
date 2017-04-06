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

import org.l2junity.gameserver.enums.SkillEnchantType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExEnchantSkillInfoDetail;


/**
 * @author -Wooden-
 */
public final class RequestExEnchantSkillInfoDetail extends GameClientPacket {
	private SkillEnchantType _type;
	private int _skillId;
	private int _skillLvl;
	private int _skillSubLvl;

	@Override
	public void readImpl() {
		_type = SkillEnchantType.values()[readD()];
		_skillId = readD();
		_skillLvl = readH();
		_skillSubLvl = readH();
	}

	@Override
	public void runImpl() {
		if ((_skillId <= 0) || (_skillLvl <= 0) || (_skillSubLvl < 0)) {
			return;
		}

		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}
		activeChar.sendPacket(new ExEnchantSkillInfoDetail(_type, _skillId, _skillLvl, _skillSubLvl, activeChar));
	}
}