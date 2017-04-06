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
import org.l2junity.gameserver.data.xml.impl.SkillTreesData;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.CommonSkill;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author UnAfraid
 */
public class ExAlchemySkillList extends GameServerPacket {
	private final List<Skill> _skills = new ArrayList<>();

	public ExAlchemySkillList(final Player player) {
		_skills.addAll(player.getAllSkills().stream().filter(s -> SkillTreesData.getInstance().isAlchemySkill(s.getId(), s.getLevel())).collect(Collectors.toList()));
		_skills.add(CommonSkill.ALCHEMY_CUBE.getSkill());
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ALCHEMY_SKILL_LIST.writeId(body);

		body.writeD(_skills.size());
		for (Skill skill : _skills) {
			body.writeD(skill.getId());
			body.writeD(skill.getLevel());
			body.writeQ(0x00); // Always 0 on Naia, SP i guess?
			body.writeC(skill.getId() == CommonSkill.ALCHEMY_CUBE.getId() ? 0 : 1); // This is type in flash, visible or not
		}
	}
}
