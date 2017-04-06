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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author UnAfraid
 */
public class ExAcquireSkillInfo extends GameServerPacket {
	private final int _id;
	private final int _level;
	private final int _dualClassLevel;
	private final int _spCost;
	private final int _minLevel;
	private final List<ItemHolder> _itemReq;
	private final List<Skill> _skillRem;

	/**
	 * Special constructor for Alternate Skill Learning system.<br>
	 * Sets a custom amount of SP.
	 *
	 * @param player
	 * @param skillLearn the skill learn.
	 */
	public ExAcquireSkillInfo(Player player, SkillLearn skillLearn) {
		_id = skillLearn.getSkillId();
		_level = skillLearn.getSkillLevel();
		_dualClassLevel = skillLearn.getDualClassLevel();
		_spCost = skillLearn.getLevelUpSp();
		_minLevel = skillLearn.getGetLevel();
		_itemReq = skillLearn.getRequiredItems();
		_skillRem = skillLearn.getRemoveSkills().stream().map(player::getKnownSkill).filter(Objects::nonNull).collect(Collectors.toList());
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ACQUIRE_SKILL_INFO.writeId(body);

		body.writeD(_id);
		body.writeD(_level);
		body.writeQ(_spCost);
		body.writeH(_minLevel);
		body.writeH(_dualClassLevel);
		body.writeD(_itemReq.size());
		for (ItemHolder holder : _itemReq) {
			body.writeD(holder.getId());
			body.writeQ(holder.getCount());
		}

		body.writeD(_skillRem.size());
		for (Skill skill : _skillRem) {
			body.writeD(skill.getId());
			body.writeD(skill.getLevel());
		}
	}
}
