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
package org.l2junity.commons.scripting;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.l2junity.commons.scripting.java.JavaScriptingEngine;
import org.l2junity.commons.util.ArrayUtil;
import org.l2junity.commons.util.BasePathProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Caches script engines and provides functionality for executing and managing scripts.
 * @author KenM, HorridoJoho
 */
public final class ScriptEngineManager
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptEngineManager.class);
	
	private static final Path CONFIG_FILE = BasePathProvider.resolvePath(Paths.get("config", "ScriptEngines.properties"));
	private static final Pattern ENV_PATTERN = Pattern.compile("\\%([a-zA-Z0-9.\\-_]*)\\%");
	
	private final Map<String, IExecutionContext> _extEngines = new HashMap<>();
	private IExecutionContext _currentExecutionContext = null;
	
	protected ScriptEngineManager()
	{
		if (Files.notExists(CONFIG_FILE))
		{
			LOGGER.info("Configuration not found, disabled!");
			return;
		}
		
		// register engines
		final Properties props = loadProperties();
		
		// Default java engine implementation
		registerEngine(new JavaScriptingEngine(), props);
		
		// Load external script engines
		ServiceLoader.load(IScriptingEngine.class).forEach(engine -> registerEngine(engine, props));
	}
	
	private static Properties loadProperties()
	{
		final Properties properties = new Properties();
		try (final InputStream is = Files.newInputStream(CONFIG_FILE))
		{
			properties.load(is);
		}
		catch (Exception e)
		{
			LOGGER.warn("Couldn't load ScriptEngines.properties!", e);
		}
		
		return properties;
	}
	
	private void registerEngine(final IScriptingEngine engine, final Properties props)
	{
		maybeSetProperties("language." + engine.getLanguageName() + ".", props, engine);
		final IExecutionContext context = engine.createExecutionContext();
		for (String commonExtension : engine.getCommonFileExtensions())
		{
			_extEngines.put(commonExtension, context);
		}
		
		LOGGER.info(engine.getEngineName() + " " + engine.getEngineVersion() + " (" + engine.getLanguageName() + " " + engine.getLanguageVersion() + ")");
	}
	
	private static void maybeSetProperties(final String propPrefix, final Properties props, final IScriptingEngine engine)
	{
		if (props == null)
		{
			return;
		}
		
		for (final Entry<Object, Object> prop : props.entrySet())
		{
			String key = (String) prop.getKey();
			String value = (String) prop.getValue();
			
			if (key.startsWith(propPrefix))
			{
				key = key.substring(propPrefix.length());
				if (value.contains("%"))
				{
					final Matcher matcher = ENV_PATTERN.matcher(value);
					while (matcher.find())
					{
						value = value.replace("%" + matcher.group(1) + "%", System.getProperty(matcher.group(1)));
					}
				}
				
				engine.setProperty(key, value);
			}
		}
	}
	
	private IExecutionContext getEngineByExtension(String ext)
	{
		return _extEngines.get(ext);
	}
	
	private static String getFileExtension(final Path p)
	{
		final String name = p.getFileName().toString();
		final int lastDotIdx = name.lastIndexOf('.');
		if (lastDotIdx == -1)
		{
			return null;
		}
		
		final String extension = name.substring(lastDotIdx + 1);
		if (extension.isEmpty())
		{
			return null;
		}
		
		return extension;
	}
	
	private static void checkExistingFile(final String messagePre, final Path filePath) throws Exception
	{
		if (!Files.exists(filePath))
		{
			throw new Exception(messagePre + ": " + filePath + " does not exists!");
		}
		else if (!Files.isRegularFile(filePath))
		{
			throw new Exception(messagePre + ": " + filePath + " is not a file!");
		}
	}
	
	public void executeScriptList(final Path scriptFolder, final String... doNotLoad) throws Exception
	{
		final Map<IExecutionContext, List<Path>> files = new LinkedHashMap<>();
		processDirectory(scriptFolder, files, doNotLoad);
		
		for (Entry<IExecutionContext, List<Path>> entry : files.entrySet())
		{
			_currentExecutionContext = entry.getKey();
			try
			{
				Map<Path, Throwable> invocationErrors = entry.getKey().executeScripts(entry.getValue());
				for (Entry<Path, Throwable> entry2 : invocationErrors.entrySet())
				{
					LOGGER.warn(entry2.getKey() + " failed execution!", entry2.getValue());
				}
			}
			finally
			{
				_currentExecutionContext = null;
			}
		}
	}
	
	private void processDirectory(final Path dir, final Map<IExecutionContext, List<Path>> files, final String... doNotLoad)
	{
		try
		{
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>()
			{
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
				{
					processFile(file, files, doNotLoad);
					return super.visitFile(file, attrs);
				}
			});
		}
		catch (final IOException e)
		{
			LOGGER.warn("Couldn't load directory: " + dir, e);
		}
	}
	
	protected void processFile(final Path path, final Map<IExecutionContext, List<Path>> files, final String... doNotLoad)
	{
		if (ArrayUtil.contains(doNotLoad, path.getFileName().toString()))
		{
			return;
		}
		
		try
		{
			checkExistingFile("ScriptFile", path);
		}
		catch (Exception e)
		{
			LOGGER.warn(e.getMessage());
			return;
		}
		
		final Path sourceFile = path.toAbsolutePath();
		final String ext = getFileExtension(sourceFile);
		if (ext == null)
		{
			LOGGER.warn(sourceFile + " does not have an extension to determine the script engine!");
			return;
		}
		
		final IExecutionContext engine = getEngineByExtension(ext);
		if (engine == null)
		{
			return;
		}
		
		files.computeIfAbsent(engine, k -> new LinkedList<>()).add(sourceFile);
	}
	
	public void executeScript(final Path scriptFolder, Path sourceFile) throws Exception
	{
		Objects.requireNonNull(sourceFile);
		
		if (!sourceFile.isAbsolute())
		{
			sourceFile = scriptFolder.resolve(sourceFile);
		}
		
		// throws exception if not exists or not file
		checkExistingFile("ScriptFile", sourceFile);
		
		sourceFile = sourceFile.toAbsolutePath();
		String ext = getFileExtension(sourceFile);
		Objects.requireNonNull(sourceFile, "ScriptFile: " + sourceFile + " does not have an extension to determine the script engine!");
		
		IExecutionContext engine = getEngineByExtension(ext);
		Objects.requireNonNull(engine, "ScriptEngine: No engine registered for extension " + ext + "!");
		
		_currentExecutionContext = engine;
		try
		{
			Entry<Path, Throwable> error = engine.executeScript(sourceFile);
			if (error != null)
			{
				throw new Exception("ScriptEngine: " + error.getKey() + " failed execution!", error.getValue());
			}
		}
		finally
		{
			_currentExecutionContext = null;
		}
	}
	
	public Path getCurrentLoadingScript()
	{
		return _currentExecutionContext != null ? _currentExecutionContext.getCurrentExecutingScript() : null;
	}
	
	public static ScriptEngineManager getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static final class SingletonHolder
	{
		protected static final ScriptEngineManager INSTANCE = new ScriptEngineManager();
	}
}