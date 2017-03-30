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
package org.l2junity.loginserver;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.l2junity.loginserver.config.LoginServerConfig;

/**
 * @author KenM
 */
public class GameServerListener extends FloodProtectedListener
{
	private static List<GameServerThread> _gameServers = new CopyOnWriteArrayList<>();
	
	public GameServerListener() throws IOException
	{
		super(LoginServerConfig.GAME_SERVER_LOGIN_HOST, LoginServerConfig.GAME_SERVER_LOGIN_PORT);
		setName(getClass().getSimpleName());
	}
	
	@Override
	public void addClient(Socket s)
	{
		_gameServers.add(new GameServerThread(s));
	}
	
	public void removeGameServer(GameServerThread gst)
	{
		_gameServers.remove(gst);
	}
}
