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
package handlers.admincommandhandlers;

import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * @author
 */
public class AdminRide implements IAdminCommandHandler
{
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_ride_horse",
		"admin_ride_bike",
		"admin_ride_wyvern",
		"admin_ride_strider",
		"admin_unride_wyvern",
		"admin_unride_strider",
		"admin_unride",
		"admin_ride_wolf",
		"admin_unride_wolf",
	};
	private int _petRideId;
	
	private static final int PURPLE_MANED_HORSE_TRANSFORMATION_ID = 106;
	
	private static final int JET_BIKE_TRANSFORMATION_ID = 20001;
	
	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar)
	{
		PlayerInstance player = getRideTarget(activeChar);
		if (player == null)
		{
			return false;
		}
		
		if (command.startsWith("admin_ride"))
		{
			if (player.isMounted() || player.hasSummon())
			{
				activeChar.sendMessage("Target already have a summon.");
				return false;
			}
			if (command.startsWith("admin_ride_wyvern"))
			{
				_petRideId = 12621;
			}
			else if (command.startsWith("admin_ride_strider"))
			{
				_petRideId = 12526;
			}
			else if (command.startsWith("admin_ride_wolf"))
			{
				_petRideId = 16041;
			}
			else if (command.startsWith("admin_ride_horse")) // handled using transformation
			{
				if (player.isTransformed())
				{
					activeChar.sendPacket(SystemMessageId.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				}
				else
				{
					player.transform(PURPLE_MANED_HORSE_TRANSFORMATION_ID, true);
				}
				
				return true;
			}
			else if (command.startsWith("admin_ride_bike")) // handled using transformation
			{
				if (player.isTransformed())
				{
					activeChar.sendPacket(SystemMessageId.YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN);
				}
				else
				{
					player.transform(JET_BIKE_TRANSFORMATION_ID, true);
				}
				
				return true;
			}
			else
			{
				activeChar.sendMessage("Command '" + command + "' not recognized");
				return false;
			}
			
			player.mount(_petRideId, 0, false);
			
			return false;
		}
		else if (command.startsWith("admin_unride"))
		{
			if (player.getTransformationId() == PURPLE_MANED_HORSE_TRANSFORMATION_ID)
			{
				player.untransform();
			}
			
			if (player.getTransformationId() == JET_BIKE_TRANSFORMATION_ID)
			{
				player.untransform();
			}
			else
			{
				player.dismount();
			}
		}
		return true;
	}
	
	private PlayerInstance getRideTarget(PlayerInstance activeChar)
	{
		PlayerInstance player = null;
		
		if ((activeChar.getTarget() == null) || (activeChar.getTarget().getObjectId() == activeChar.getObjectId()) || !(activeChar.getTarget() instanceof PlayerInstance))
		{
			player = activeChar;
		}
		else
		{
			player = (PlayerInstance) activeChar.getTarget();
		}
		
		return player;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
	
	public static void main(String[] args)
	{
		AdminCommandHandler.getInstance().registerHandler(new AdminRide());
	}
}