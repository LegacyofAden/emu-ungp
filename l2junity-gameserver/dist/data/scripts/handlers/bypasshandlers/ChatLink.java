/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.bypasshandlers;

import org.l2junity.gameserver.handler.BypassHandler;
import org.l2junity.gameserver.handler.IBypassHandler;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.Npc;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.character.npc.OnNpcFirstTalk;

public class ChatLink implements IBypassHandler
{
	private static final String[] COMMANDS =
	{
		"Chat"
	};
	
	@Override
	public boolean useBypass(String command, PlayerInstance activeChar, Creature target)
	{
		if (!target.isNpc())
		{
			return false;
		}
		
		int val = 0;
		try
		{
			val = Integer.parseInt(command.substring(5));
		}
		catch (Exception ioobe)
		{
			
		}
		
		final Npc npc = (Npc) target;
		if ((val == 0) && npc.hasListener(EventType.ON_NPC_FIRST_TALK))
		{
			EventDispatcher.getInstance().notifyEventAsync(new OnNpcFirstTalk(npc, activeChar), npc);
		}
		else
		{
			npc.showChatWindow(activeChar, val);
		}
		return false;
	}
	
	@Override
	public String[] getBypassList()
	{
		return COMMANDS;
	}
	
	public static void main(String[] args)
	{
		BypassHandler.getInstance().registerHandler(new ChatLink());
	}
}