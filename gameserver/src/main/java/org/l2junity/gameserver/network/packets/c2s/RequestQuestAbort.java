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
package org.l2junity.gameserver.network.packets.c2s;

import org.l2junity.gameserver.enums.QuestType;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerQuestAbort;
import org.l2junity.gameserver.model.quest.Quest;
import org.l2junity.gameserver.model.quest.QuestState;
import org.l2junity.gameserver.network.packets.GameClientPacket;


public final class RequestQuestAbort extends GameClientPacket {
	private int _questId;

	@Override
	public void readImpl() {
		_questId = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final Quest qe = QuestManager.getInstance().getQuest(_questId);
		if (qe != null) {
			final QuestState qs = activeChar.getQuestState(qe.getName());
			if (qs != null) {
				// Exit quest
				qs.exitQuest(QuestType.REPEATABLE);
				// Notify listeners
				EventDispatcher.getInstance().notifyEventAsync(new OnPlayerQuestAbort(activeChar, _questId), activeChar);
			}
		}
	}
}