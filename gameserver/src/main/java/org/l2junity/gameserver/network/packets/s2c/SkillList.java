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
import org.l2junity.gameserver.data.xml.impl.SkillData;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class SkillList extends GameServerPacket {
	private final List<Skill> _skills = new ArrayList<>();
	private int _lastLearnedSkillId = 0;

	static class Skill {
		public int id;
		public int reuseDelayGroup;
		public int level;
		public int subLevel;
		public boolean passive;
		public boolean disabled;
		public boolean enchanted;

		Skill(int pId, int pReuseDelayGroup, int pLevel, int pSubLevel, boolean pPassive, boolean pDisabled, boolean pEnchanted) {
			id = pId;
			reuseDelayGroup = pReuseDelayGroup;
			level = pLevel;
			subLevel = pSubLevel;
			passive = pPassive;
			disabled = pDisabled;
			enchanted = pEnchanted;
		}
	}

	public void addSkill(int id, int reuseDelayGroup, int level, int subLevel, boolean passive, boolean disabled, boolean enchanted) {
		_skills.add(new Skill(id, reuseDelayGroup, level, subLevel, passive, disabled, enchanted));
	}

	public void setLastLearnedSkillId(int lastLearnedSkillId) {
		_lastLearnedSkillId = lastLearnedSkillId;
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SKILL_LIST.writeId(body);
		_skills.sort(Comparator.comparing(s -> SkillData.getInstance().getSkill(s.id, s.level, s.subLevel).isToggle() ? 1 : 0));
		body.writeD(_skills.size());
		for (Skill temp : _skills) {
			body.writeD(temp.passive ? 1 : 0);
			body.writeH(temp.level);
			body.writeH(temp.subLevel);
			body.writeD(temp.id);
			body.writeD(temp.reuseDelayGroup); // GOD ReuseDelayShareGroupID
			body.writeC(temp.disabled ? 1 : 0); // iSkillDisabled
			body.writeC(temp.enchanted ? 1 : 0); // CanEnchant
		}
		body.writeD(_lastLearnedSkillId);
	}
}