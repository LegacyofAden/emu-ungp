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
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Sdw
 */
public class AcquireSkillList extends GameServerPacket {
	final Player _activeChar;
	final List<SkillLearn> _learnable;

	public AcquireSkillList(Player activeChar) {
		_activeChar = activeChar;
		_learnable = SkillTreesData.getInstance().getAvailableSkills(activeChar, activeChar.getClassId(), false, false);
		_learnable.addAll(SkillTreesData.getInstance().getNextAvailableSkills(activeChar, activeChar.getClassId(), false, false));
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ACQUIRE_SKILL_LIST.writeId(body);

		body.writeH(_learnable.size());
		for (SkillLearn skill : _learnable) {
			body.writeD(skill.getSkillId());
			body.writeD(skill.getSkillLevel());
			body.writeQ(skill.getLevelUpSp());
			body.writeC(skill.getGetLevel());
			body.writeC(skill.getDualClassLevel());
			body.writeC(skill.getRequiredItems().size());
			for (ItemHolder item : skill.getRequiredItems()) {
				body.writeD(item.getId());
				body.writeQ(item.getCount());
			}

			final List<Skill> skillRem = skill.getRemoveSkills().stream().map(_activeChar::getKnownSkill).filter(Objects::nonNull).collect(Collectors.toList());

			body.writeC(skillRem.size());
			for (Skill skillRemove : skillRem) {
				body.writeD(skillRemove.getId());
				body.writeD(skillRemove.getLevel());
			}
		}
	}
}