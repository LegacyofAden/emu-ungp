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
package org.l2junity.gameserver.model.quest;

import org.l2junity.gameserver.model.KeyValuePair;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author UnAfraid
 */
public class QuestCondition {
	private final Predicate<Player> _condition;
	private Map<Integer, String> _perNpcDialog;
	private final String _html;

	public QuestCondition(Predicate<Player> cond, String html) {
		_condition = cond;
		_html = html;
	}

	@SafeVarargs
	public QuestCondition(Predicate<Player> cond, KeyValuePair<Integer, String>... pairs) {
		_condition = cond;
		_html = null;
		_perNpcDialog = new HashMap<>();
		Stream.of(pairs).forEach(pair -> _perNpcDialog.put(pair.getKey(), pair.getValue()));
	}

	public boolean test(Player player) {
		return _condition.test(player);
	}

	public String getHtml(Npc npc) {
		return _perNpcDialog != null ? _perNpcDialog.get(npc.getId()) : _html;
	}
}
