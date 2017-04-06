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
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.base.AcquireSkillType;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;

/**
 * @author UnAfraid
 */
public class ExAcquirableSkillListByClass extends GameServerPacket {
	final List<SkillLearn> _learnable;
	final AcquireSkillType _type;

	public ExAcquirableSkillListByClass(List<SkillLearn> learnable, AcquireSkillType type) {
		_learnable = learnable;
		_type = type;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ACQUIRABLE_SKILL_LIST_BY_CLASS.writeId(body);

		body.writeH(_type.getId());
		body.writeH(_learnable.size());
		for (SkillLearn skill : _learnable) {
			body.writeD(skill.getSkillId());
			body.writeH(skill.getSkillLevel());
			body.writeH(skill.getSkillLevel()); // Max level
			body.writeC(skill.getGetLevel());
			body.writeQ(skill.getLevelUpSp());
			body.writeC(skill.getRequiredItems().size());
			if (_type == AcquireSkillType.SUBPLEDGE) {
				body.writeH(0x00);
			}
		}
	}
}
