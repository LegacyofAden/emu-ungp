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
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Set;

public final class ExEnchantSkillInfo extends GameServerPacket {
	private final Set<Integer> _routes;

	private final int _skillId;
	private final int _skillLevel;
	private final int _skillSubLevel;
	private final int _currentSubLevel;

	public ExEnchantSkillInfo(int skillId, int skillLevel, int skillSubLevel, int currentSubLevel) {
		_skillId = skillId;
		_skillLevel = skillLevel;
		_skillSubLevel = skillSubLevel;
		_currentSubLevel = currentSubLevel;
		_routes = EnchantSkillGroupsData.getInstance().getRouteForSkill(_skillId, _skillLevel);
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_ENCHANT_SKILL_INFO.writeId(body);
		body.writeD(_skillId);
		body.writeH(_skillLevel);
		body.writeH(_skillSubLevel);
		body.writeD((_skillSubLevel % 1000) == EnchantSkillGroupsData.MAX_ENCHANT_LEVEL ? 0 : 1);
		body.writeD(_skillSubLevel > 1000 ? 1 : 0);
		body.writeD(_routes.size());
		_routes.forEach(route ->
		{
			final int routeId = route / 1000;
			final int currentRouteId = _skillSubLevel / 1000;
			final int subLevel = _currentSubLevel > 0 ? (route + (_currentSubLevel % 1000)) - 1 : route;
			body.writeH(_skillLevel);
			body.writeH(currentRouteId != routeId ? subLevel : subLevel + 1);
		});
	}
}