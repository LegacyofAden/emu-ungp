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
import org.l2junity.gameserver.data.xml.impl.EnchantSkillGroupsData;
import org.l2junity.gameserver.enums.SkillEnchantType;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.holders.EnchantSkillHolder;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Set;

/**
 * @author KenM
 */
public class ExEnchantSkillInfoDetail extends GameServerPacket {
	private final SkillEnchantType _type;
	private final int _skillId;
	private final int _skillLvl;
	private final int _skillSubLvl;
	private final EnchantSkillHolder _enchantSkillHolder;

	public ExEnchantSkillInfoDetail(SkillEnchantType type, int skillId, int skillLvl, int skillSubLvl, Player player) {
		_type = type;
		_skillId = skillId;
		_skillLvl = skillLvl;
		_skillSubLvl = skillSubLvl;

		_enchantSkillHolder = EnchantSkillGroupsData.getInstance().getEnchantSkillHolder(skillSubLvl % 1000);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ENCHANT_SKILL_INFO_DETAIL.writeId(body);

		body.writeD(_type.ordinal());
		body.writeD(_skillId);
		body.writeH(_skillLvl);
		body.writeH(_skillSubLvl);
		if (_enchantSkillHolder != null) {
			body.writeQ(_enchantSkillHolder.getSp(_type));
			body.writeD(_enchantSkillHolder.getChance(_type));
			final Set<ItemHolder> holders = _enchantSkillHolder.getRequiredItems(_type);
			body.writeD(holders.size());
			holders.forEach(holder ->
			{
				body.writeD(holder.getId());
				body.writeD((int) holder.getCount());
			});
		}
	}
}