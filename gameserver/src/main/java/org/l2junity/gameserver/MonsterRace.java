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
package org.l2junity.gameserver;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.data.xml.impl.NpcData;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.templates.L2NpcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class MonsterRace {
	protected static final Logger LOGGER = LoggerFactory.getLogger(MonsterRace.class);

	private final Npc[] _monsters = new Npc[8];
	private int[][] _speeds = new int[8][20];
	private final int[] _first = new int[2];
	private final int[] _second = new int[2];

	protected MonsterRace() {
	}

	public void newRace() {
		int random = 0;

		for (int i = 0; i < 8; i++) {
			int id = 31003;
			random = Rnd.get(24);
			while (true) {
				for (int j = i - 1; j >= 0; j--) {
					if (_monsters[j].getTemplate().getId() == (id + random)) {
						random = Rnd.get(24);
						continue;
					}
				}
				break;
			}
			try {
				L2NpcTemplate template = NpcData.getInstance().getTemplate(id + random);
				Constructor<?> constructor = Class.forName("org.l2junity.gameserver.model.actor.instance." + template.getType() + "Instance").getConstructors()[0];
				_monsters[i] = (Npc) constructor.newInstance(template);
			} catch (Exception e) {
				LOGGER.warn("", e);
			}
			// LOGGER.info("Monster "+i+" is id: "+(id+random));
		}
		newSpeeds();
	}

	public void newSpeeds() {
		_speeds = new int[8][20];
		int total = 0;
		_first[1] = 0;
		_second[1] = 0;
		for (int i = 0; i < 8; i++) {
			total = 0;
			for (int j = 0; j < 20; j++) {
				if (j == 19) {
					_speeds[i][j] = 100;
				} else {
					_speeds[i][j] = Rnd.get(60) + 65;
				}
				total += _speeds[i][j];
			}
			if (total >= _first[1]) {
				_second[0] = _first[0];
				_second[1] = _first[1];
				_first[0] = 8 - i;
				_first[1] = total;
			} else if (total >= _second[1]) {
				_second[0] = 8 - i;
				_second[1] = total;
			}
		}
	}

	/**
	 * @return Returns the monsters.
	 */
	public Npc[] getMonsters() {
		return _monsters;
	}

	/**
	 * @return Returns the speeds.
	 */
	public int[][] getSpeeds() {
		return _speeds;
	}

	public int getFirstPlace() {
		return _first[0];
	}

	public int getSecondPlace() {
		return _second[0];
	}

	public static MonsterRace getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final MonsterRace INSTANCE = new MonsterRace();
	}
}
