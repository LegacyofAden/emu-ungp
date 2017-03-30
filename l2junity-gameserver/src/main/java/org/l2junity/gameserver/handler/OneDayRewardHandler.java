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
package org.l2junity.gameserver.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.l2junity.commons.loader.annotations.InstanceGetter;
import org.l2junity.commons.loader.annotations.Load;
import org.l2junity.commons.scripting.ScriptEngineManager;
import org.l2junity.gameserver.loader.LoadGroup;
import org.l2junity.gameserver.model.OneDayRewardDataHolder;
import org.l2junity.gameserver.scripting.GameScriptsLoader;

/**
 * @author Sdw
 */
public class OneDayRewardHandler
{
	private final Map<String, Function<OneDayRewardDataHolder, AbstractOneDayRewardHandler>> _handlerFactories = new HashMap<>();
	
	public void registerHandler(String name, Function<OneDayRewardDataHolder, AbstractOneDayRewardHandler> handlerFactory)
	{
		_handlerFactories.put(name, handlerFactory);
	}
	
	public Function<OneDayRewardDataHolder, AbstractOneDayRewardHandler> getHandler(String name)
	{
		return _handlerFactories.get(name);
	}
	
	public int size()
	{
		return _handlerFactories.size();
	}
	
	protected OneDayRewardHandler()
	{
	}
	
	@Load(group = LoadGroup.class)
	private void load()
	{
		try
		{
			ScriptEngineManager.getInstance().executeScript(GameScriptsLoader.SCRIPT_FOLDER, GameScriptsLoader.ONE_DAY_REWARD_MASTER_HANDLER);
		}
		catch (Exception e)
		{
			throw new Error("Problems while running OneDayRewardMasterHandler", e);
		}
	}
	
	@InstanceGetter
	public static OneDayRewardHandler getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final OneDayRewardHandler _instance = new OneDayRewardHandler();
	}
}
