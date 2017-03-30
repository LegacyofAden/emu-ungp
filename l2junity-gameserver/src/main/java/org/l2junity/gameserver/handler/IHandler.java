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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author UnAfraid
 * @param <K>
 * @param <V>
 */
public interface IHandler<K, V>
{
	Logger LOGGER = LoggerFactory.getLogger(IHandler.class);
	
	void registerHandler(K handler);
	
	void removeHandler(K handler);
	
	K getHandler(V val);
	
	int size();
	
	default void report()
	{
		LOGGER.info("Loaded {} {}(s).", size(), getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2"));
	}
}
