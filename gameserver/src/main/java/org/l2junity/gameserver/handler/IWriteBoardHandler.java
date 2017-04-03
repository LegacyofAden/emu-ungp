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
package org.l2junity.gameserver.handler;

import org.l2junity.gameserver.model.actor.instance.Player;

/**
 * Community Board interface.
 *
 * @author Zoey76
 */
public interface IWriteBoardHandler extends IParseBoardHandler {
	/**
	 * Writes a community board command into the client.
	 *
	 * @param player the player
	 * @param arg1   the first argument
	 * @param arg2   the second argument
	 * @param arg3   the third argument
	 * @param arg4   the fourth argument
	 * @param arg5   the fifth argument
	 * @return
	 */
	boolean writeCommunityBoardCommand(Player player, String arg1, String arg2, String arg3, String arg4, String arg5);
}
