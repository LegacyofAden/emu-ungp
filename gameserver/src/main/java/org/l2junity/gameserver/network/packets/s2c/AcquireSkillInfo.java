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
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.model.SkillLearn;
import org.l2junity.gameserver.model.base.AcquireSkillType;
import org.l2junity.gameserver.model.holders.ItemHolder;
import org.l2junity.gameserver.model.skills.CommonSkill;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.ArrayList;
import java.util.List;

/**
 * Acquire Skill Info server packet implementation.
 *
 * @author Zoey76
 */
public class AcquireSkillInfo extends GameServerPacket {
	private final AcquireSkillType _type;
	private final int _id;
	private final int _level;
	private final int _spCost;
	private final List<Req> _reqs;

	/**
	 * Private class containing learning skill requisites.
	 */
	private static class Req {
		public int itemId;
		public long count;
		public int type;
		public int unk;

		/**
		 * @param pType     TODO identify.
		 * @param pItemId   the item Id.
		 * @param itemCount the item count.
		 * @param pUnk      TODO identify.
		 */
		public Req(int pType, int pItemId, long itemCount, int pUnk) {
			itemId = pItemId;
			type = pType;
			count = itemCount;
			unk = pUnk;
		}
	}

	/**
	 * Constructor for the acquire skill info object.
	 *
	 * @param skillType  the skill learning type.
	 * @param skillLearn the skill learn.
	 */
	public AcquireSkillInfo(AcquireSkillType skillType, SkillLearn skillLearn) {
		_id = skillLearn.getSkillId();
		_level = skillLearn.getSkillLevel();
		_spCost = skillLearn.getLevelUpSp();
		_type = skillType;
		_reqs = new ArrayList<>();
		if ((skillType != AcquireSkillType.PLEDGE) || PlayerConfig.LIFE_CRYSTAL_NEEDED) {
			for (ItemHolder item : skillLearn.getRequiredItems()) {
				if (!PlayerConfig.DIVINE_SP_BOOK_NEEDED && (_id == CommonSkill.DIVINE_INSPIRATION.getId())) {
					continue;
				}
				_reqs.add(new Req(99, item.getId(), item.getCount(), 50));
			}
		}
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.ACQUIRE_SKILL_INFO.writeId(body);

		body.writeD(_id);
		body.writeD(_level);
		body.writeQ(_spCost);
		body.writeD(_type.getId());
		body.writeD(_reqs.size());
		for (Req temp : _reqs) {
			body.writeD(temp.type);
			body.writeD(temp.itemId);
			body.writeQ(temp.count);
			body.writeD(temp.unk);
		}
	}
}
