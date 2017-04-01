/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.model.holders;

import java.util.List;
import java.util.Map;

import org.l2junity.commons.util.Rnd;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.handler.IConditionHandler;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * @author Sdw
 */
public class ExtendDropDataHolder
{
	private final int _id;
	private final List<ExtendDropItemHolder> _items;
	private final List<IConditionHandler> _conditions;
	private final Map<Long, SystemMessageId> _systemMessages;
	
	public ExtendDropDataHolder(StatsSet set)
	{
		_id = set.getInt("id");
		_items = set.getList("items", ExtendDropItemHolder.class);
		_conditions = set.getList("conditions", IConditionHandler.class);
		_systemMessages = set.getMap("systemMessages", Long.class, SystemMessageId.class);
	}
	
	public void reward(PlayerInstance player, Npc npc)
	{
		if (_conditions.isEmpty() || _conditions.stream().allMatch(cond -> cond.test(player, npc)))
		{
			_items.forEach(i ->
			{
				final long currentAmount = player.getVariables().getExtendDropCount(_id, i.getId());
				if ((Rnd.nextDouble() < i.getChance()) && (currentAmount < i.getMaxCount()))
				{
					boolean sendMessage = true;
					final long newAmount = currentAmount + i.getCount();
					if (_systemMessages != null)
					{
						final SystemMessageId systemMessageId = _systemMessages.get(newAmount);
						if (systemMessageId != null)
						{
							sendMessage = false;
							player.sendPacket(systemMessageId);
						}
					}
					player.addItem("ExtendDrop", i.getId(), i.getCount(), player, sendMessage);
					player.getVariables().updateExtendDrop(_id, i.getId(), newAmount);
				}
			});
		}
	}
}