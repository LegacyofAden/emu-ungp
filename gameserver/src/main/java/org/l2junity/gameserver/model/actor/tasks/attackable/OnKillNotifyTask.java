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
package org.l2junity.gameserver.model.actor.tasks.attackable;

import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.quest.Quest;

/**
 * @author xban1x
 */
public final class OnKillNotifyTask implements Runnable {
	private final Attackable _attackable;
	private final Quest _quest;
	private final Player _killer;
	private final boolean _isSummon;

	public OnKillNotifyTask(Attackable attackable, Quest quest, Player killer, boolean isSummon) {
		_attackable = attackable;
		_quest = quest;
		_killer = killer;
		_isSummon = isSummon;
	}

	@Override
	public void run() {
		if ((_quest != null) && (_attackable != null) && (_killer != null)) {
			_quest.notifyKill(_attackable, _killer, _isSummon);
		}
	}
}
