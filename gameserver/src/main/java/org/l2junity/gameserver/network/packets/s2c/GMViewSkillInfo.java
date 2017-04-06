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
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Collection;

public class GMViewSkillInfo extends GameServerPacket {
	private final Player _activeChar;
	private final Collection<Skill> _skills;

	public GMViewSkillInfo(Player cha) {
		_activeChar = cha;
		_skills = _activeChar.getSkillList();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.GM_VIEW_SKILL_INFO.writeId(body);

		body.writeS(_activeChar.getName());
		body.writeD(_skills.size());

		boolean isDisabled = (_activeChar.getClan() != null) && (_activeChar.getClan().getReputationScore() < 0);

		for (Skill skill : _skills) {
			body.writeD(skill.isPassive() ? 1 : 0);
			body.writeH(skill.getDisplayLevel());
			body.writeH(skill.getSubLevel());
			body.writeD(skill.getDisplayId());
			body.writeD(0x00);
			body.writeC(isDisabled && skill.isClanSkill() ? 1 : 0);
			body.writeC(skill.isEnchantable() ? 1 : 0);
		}
	}
}