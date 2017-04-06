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
import org.l2junity.gameserver.enums.SkillEnchantType;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.LinkedList;
import java.util.List;

public class ExEnchantSkillList extends GameServerPacket {
	private final SkillEnchantType _type;
	private final List<Skill> _skills = new LinkedList<>();

	public ExEnchantSkillList(SkillEnchantType type) {
		_type = type;
	}

	public void addSkill(Skill skill) {
		_skills.add(skill);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ENCHANT_SKILL_LIST.writeId(body);

		body.writeD(_type.ordinal());
		body.writeD(_skills.size());
		for (Skill skill : _skills) {
			body.writeD(skill.getId());
			body.writeH(skill.getLevel());
			body.writeH(skill.getSubLevel());
		}
	}
}