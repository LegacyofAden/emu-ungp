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
import org.l2junity.gameserver.model.TimeStamp;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Skill Cool Time server packet implementation.
 *
 * @author KenM, Zoey76
 */
public class SkillCoolTime extends GameServerPacket {
	private final List<TimeStamp> _skillReuseTimeStamps = new ArrayList<>();

	public SkillCoolTime(Player player) {
		final Map<Long, TimeStamp> skillReuseTimeStamps = player.getSkillReuseTimeStamps();
		if (skillReuseTimeStamps != null) {
			for (TimeStamp ts : skillReuseTimeStamps.values()) {
				final Skill skill = SkillData.getInstance().getSkill(ts.getSkillId(), ts.getSkillLvl(), ts.getSkillSubLvl());
				if (ts.hasNotPassed() && !skill.isNotBroadcastable()) {
					_skillReuseTimeStamps.add(ts);
				}
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.SKILL_COOL_TIME.writeId(body);

		body.writeD(_skillReuseTimeStamps.size());
		for (TimeStamp ts : _skillReuseTimeStamps) {
			body.writeD(ts.getSkillId());
			body.writeD(0x00); // ?
			body.writeD((int) ts.getReuse() / 1000);
			body.writeD((int) ts.getRemaining() / 1000);
		}
	}
}
