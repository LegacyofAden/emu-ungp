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
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

/**
 * @author -Wooden-
 */
public class PledgeSkillList extends GameServerPacket {
	private final Skill[] _skills;
	private final SubPledgeSkill[] _subSkills;

	public static class SubPledgeSkill {
		int _subType;
		int _skillId;
		int _skillLvl;

		public SubPledgeSkill(int subType, int skillId, int skillLvl) {
			_subType = subType;
			_skillId = skillId;
			_skillLvl = skillLvl;
		}
	}

	public PledgeSkillList(Clan clan) {
		_skills = clan.getAllSkills();
		_subSkills = clan.getAllSubSkills();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.PLEDGE_SKILL_LIST.writeId(body);

		body.writeD(_skills.length);
		body.writeD(_subSkills.length); // Squad skill length
		for (Skill sk : _skills) {
			body.writeD(sk.getDisplayId());
			body.writeH(sk.getDisplayLevel());
			body.writeH(0x00); // Sub level
		}
		for (SubPledgeSkill sk : _subSkills) {
			body.writeD(sk._subType); // Clan Sub-unit types
			body.writeD(sk._skillId);
			body.writeH(sk._skillLvl);
			body.writeH(0x00); // Sub level
		}
	}
}