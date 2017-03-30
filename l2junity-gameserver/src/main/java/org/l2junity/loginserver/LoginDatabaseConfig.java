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
package org.l2junity.loginserver;

import org.l2junity.commons.sql.IDatabaseConfig;
import org.l2junity.loginserver.config.LoginServerConfig;

/**
 * @author lord_rex
 */
public final class LoginDatabaseConfig implements IDatabaseConfig
{
	@Override
	public String getJdbcURL()
	{
		return LoginServerConfig.DATABASE_URL;
	}
	
	@Override
	public String getUsername()
	{
		return LoginServerConfig.DATABASE_LOGIN;
	}
	
	@Override
	public String getPassword()
	{
		return LoginServerConfig.DATABASE_PASSWORD;
	}
	
	@Override
	public int getMaxConnections()
	{
		return LoginServerConfig.DATABASE_MAX_CONNECTIONS;
	}
	
	@Override
	public int getMaxIdleTime()
	{
		return LoginServerConfig.DATABASE_MAX_IDLE_TIME;
	}
	
	@Override
	public long getConnectionCloseTime()
	{
		return LoginServerConfig.CONNECTION_CLOSE_TIME;
	}
}
