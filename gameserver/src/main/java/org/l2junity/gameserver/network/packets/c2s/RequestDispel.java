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
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.model.actor.Summon;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.AbnormalType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.network.PacketReader;

/**
 * @author KenM
 */
public class RequestDispel extends GameClientPacket {
	private int _objectId;
	private int _skillId;
	private int _skillLevel;
	private int _skillSubLevel;

	@Override
	public void readImpl() {
		_objectId = readD();
		_skillId = readD();
		_skillLevel = readH();
		_skillSubLevel = readH();
	}

	@Override
	public void runImpl() {
		if ((_skillId <= 0) || (_skillLevel <= 0)) {
			return;
		}
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}
		final Skill skill = SkillData.getInstance().getSkill(_skillId, _skillLevel, _skillSubLevel);
		if (skill == null) {
			return;
		}
		if (!skill.canBeDispelled() || skill.isStayAfterDeath() || skill.isDebuff()) {
			return;
		}
		if (skill.getAbnormalType() == AbnormalType.TRANSFORM) {
			return;
		}
		if (skill.isDance() && !PlayerConfig.DANCE_CANCEL_BUFF) {
			return;
		}
		if (activeChar.getObjectId() == _objectId) {
			activeChar.stopSkillEffects(true, _skillId);
		} else {
			final Summon pet = activeChar.getPet();
			if ((pet != null) && (pet.getObjectId() == _objectId)) {
				pet.stopSkillEffects(true, _skillId);
			}

			final Summon servitor = activeChar.getServitor(_objectId);
			if (servitor != null) {
				servitor.stopSkillEffects(true, _skillId);
			}
		}
	}
}
