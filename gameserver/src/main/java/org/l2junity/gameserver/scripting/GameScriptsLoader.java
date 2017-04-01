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
package org.l2junity.gameserver.scripting;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.tools.JavaFileObject.Kind;

import org.l2junity.commons.loader.annotations.InstanceGetter;
import org.l2junity.commons.loader.annotations.Load;
import org.l2junity.commons.loader.annotations.Reload;
import org.l2junity.commons.scripting.ScriptEngineManager;
import org.l2junity.commons.util.BasePathProvider;
import org.l2junity.commons.util.TimeAmountInterpreter;
import org.l2junity.gameserver.config.ServerConfig;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.handler.ActionHandler;
import org.l2junity.gameserver.handler.ActionShiftHandler;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.BypassHandler;
import org.l2junity.gameserver.handler.ChatHandler;
import org.l2junity.gameserver.handler.CommunityBoardHandler;
import org.l2junity.gameserver.handler.ItemHandler;
import org.l2junity.gameserver.handler.PunishmentHandler;
import org.l2junity.gameserver.handler.UserCommandHandler;
import org.l2junity.gameserver.handler.VoicedCommandHandler;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.l2junity.gameserver.loader.ScriptLoadGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * @author lord_rex
 */
public final class GameScriptsLoader implements IGameXmlReader
{
	private static final Logger LOGGER = LoggerFactory.getLogger(GameScriptsLoader.class);
	
	public static final Path SCRIPT_FOLDER = BasePathProvider.resolveDatapackPath(ServerConfig.DATAPACK_ROOT, Paths.get("data", "scripts"));
	public static final Path TARGET_MASTER_HANDLER_FILE = Paths.get("handlers", "TargetMasterHandler.java");
	public static final Path EFFECT_MASTER_HANDLER_FILE = Paths.get("handlers", "EffectMasterHandler.java");
	public static final Path SKILL_CONDITION_HANDLER_FILE = Paths.get("handlers", "SkillConditionMasterHandler.java");
	public static final Path CONDITION_HANDLER_FILE = Paths.get("handlers", "ConditionMasterHandler.java");
	public static final Path ONE_DAY_REWARD_MASTER_HANDLER = Paths.get("handlers", "OneDayRewardMasterHandler.java");
	
	private String[] _exclusions = new String[0];
	
	protected GameScriptsLoader()
	{
	}
	
	@Reload("scriptengine")
	@Load(group = ScriptLoadGroup.class)
	public void load() throws Exception
	{
		final long beginTime = System.currentTimeMillis();
		
		// load script configurations
		parseDatapackFile("config/scripts.xml");
		LOGGER.info("Loaded {} files to exclude.", _exclusions.length);
		
		LOGGER.info("Loading server script(s) ...");
		// load scripts
		ScriptEngineManager.getInstance().executeScriptList(SCRIPT_FOLDER, _exclusions);
		
		// report handlers
		ActionHandler.getInstance().report();
		ActionShiftHandler.getInstance().report();
		AdminCommandHandler.getInstance().report();
		BypassHandler.getInstance().report();
		ChatHandler.getInstance().report();
		CommunityBoardHandler.getInstance().report();
		ItemHandler.getInstance().report();
		PunishmentHandler.getInstance().report();
		UserCommandHandler.getInstance().report();
		VoicedCommandHandler.getInstance().report();
		
		// report loaded scripts
		QuestManager.getInstance().report();
		
		final long endTime = System.currentTimeMillis();
		LOGGER.info("Server script(s) are loaded successfully in {}.", TimeAmountInterpreter.consolidateMillis(endTime - beginTime));
	}
	
	@Override
	public void parseDocument(Document doc, Path path)
	{
		try
		{
			final Map<String, List<String>> excludePaths = new HashMap<>();
			forEach(doc, "list", listNode -> forEach(listNode, "exclude", excludeNode ->
			{
				final String excludeFile = parseString(excludeNode.getAttributes(), "file");
				excludePaths.putIfAbsent(excludeFile, new ArrayList<>());
				
				forEach(excludeNode, "include", includeNode -> excludePaths.get(excludeFile).add(parseString(includeNode.getAttributes(), "file")));
			}));
			
			final int nameCount = SCRIPT_FOLDER.getNameCount();
			final List<String> excludeFiles = new ArrayList<>();
			Files.walkFileTree(SCRIPT_FOLDER, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
				{
					final String fileName = file.getFileName().toString();
					if (fileName.endsWith(Kind.SOURCE.extension))
					{
						final Iterator<Path> relativePath = file.subpath(nameCount, file.getNameCount()).iterator();
						while (relativePath.hasNext())
						{
							final String nextPart = relativePath.next().toString();
							if (excludePaths.containsKey(nextPart))
							{
								boolean excludeScript = true;
								
								final List<String> includePath = excludePaths.get(nextPart);
								if (includePath != null)
								{
									while (relativePath.hasNext())
									{
										if (includePath.contains(relativePath.next().toString()))
										{
											excludeScript = false;
											break;
										}
									}
								}
								if (excludeScript)
								{
									excludeFiles.add(fileName);
									break;
								}
							}
						}
					}
					
					return super.visitFile(file, attrs);
				}
			});
			
			_exclusions = excludeFiles.toArray(new String[excludeFiles.size()]);
		}
		catch (final IOException e)
		{
			LOGGER.warn("Couldn't load script exclusions.", e);
		}
	}
	
	@InstanceGetter
	public static GameScriptsLoader getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		protected static final GameScriptsLoader INSTANCE = new GameScriptsLoader();
	}
}
