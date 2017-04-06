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
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.entity.Hero;
import org.l2junity.gameserver.model.olympiad.Olympiad;
import org.l2junity.gameserver.network.packets.GameServerPacket;
import org.l2junity.gameserver.network.packets.GameServerPacketType;

import java.util.Map;

/**
 * @author -Wooden-, KenM, godson
 */
public class ExHeroList extends GameServerPacket {
	private final Map<Integer, StatsSet> _heroList;

	public ExHeroList() {
		_heroList = Hero.getInstance().getHeroes();
	}

	@Override
	protected void writeImpl(PacketBody body) {
		GameServerPacketType.EX_HERO_LIST.writeId(body);

		body.writeD(_heroList.size());
		for (Integer heroId : _heroList.keySet()) {
			StatsSet hero = _heroList.get(heroId);
			body.writeS(hero.getString(Olympiad.CHAR_NAME));
			body.writeD(hero.getInt(Olympiad.CLASS_ID));
			body.writeS(hero.getString(Hero.CLAN_NAME, ""));
			body.writeD(hero.getInt(Hero.CLAN_CREST, 0));
			body.writeS(hero.getString(Hero.ALLY_NAME, ""));
			body.writeD(hero.getInt(Hero.ALLY_CREST, 0));
			body.writeD(hero.getInt(Hero.COUNT));
			body.writeD(0x00);
		}
	}
}