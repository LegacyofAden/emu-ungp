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

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.commons.util.TimeAmountInterpreter;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.handler.*;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.w3c.dom.Document;

import javax.tools.JavaFileObject.Kind;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * @author lord_rex
 */
@Slf4j
@StartupComponent("Scripts")
public final class GameScriptsLoader implements IGameXmlReader {
	@Getter(lazy = true)
	private static final GameScriptsLoader instance = new GameScriptsLoader();

	public static final Path SCRIPT_FOLDER = Paths.get("data", "scripts");
	public static final Path TARGET_MASTER_HANDLER_FILE = Paths.get("handlers", "TargetMasterHandler.java");
	public static final Path EFFECT_MASTER_HANDLER_FILE = Paths.get("handlers", "EffectMasterHandler.java");
	public static final Path SKILL_CONDITION_HANDLER_FILE = Paths.get("handlers", "SkillConditionMasterHandler.java");
	public static final Path CONDITION_HANDLER_FILE = Paths.get("handlers", "ConditionMasterHandler.java");
	public static final Path ONE_DAY_REWARD_MASTER_HANDLER = Paths.get("handlers", "OneDayRewardMasterHandler.java");

	private String[] _exclusions = new String[0];

	private GameScriptsLoader() {
		final long beginTime = System.currentTimeMillis();

		try {
			// load script configurations
			parseDatapackFile("config/xml/scripts.xml");
			LOGGER.info("Loaded {} files to exclude.", _exclusions.length);

			LOGGER.info("Loading server script(s) ...");
			// load scripts
			ScriptEngineManager.getInstance().executeScriptList(SCRIPT_FOLDER, _exclusions);
		} catch (Exception e) {
			log.error("Error while loading datapack scripts.");
		}


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
	public void parseDocument(Document doc, Path path) {
		try {
			final Map<String, List<String>> excludePaths = new HashMap<>();
			forEach(doc, "list", listNode -> forEach(listNode, "exclude", excludeNode ->
			{
				final String excludeFile = parseString(excludeNode.getAttributes(), "file");
				excludePaths.putIfAbsent(excludeFile, new ArrayList<>());

				forEach(excludeNode, "include", includeNode -> excludePaths.get(excludeFile).add(parseString(includeNode.getAttributes(), "file")));
			}));

			final int nameCount = SCRIPT_FOLDER.getNameCount();
			final List<String> excludeFiles = new ArrayList<>();
			Files.walkFileTree(SCRIPT_FOLDER, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					final String fileName = file.getFileName().toString();
					if (fileName.endsWith(Kind.SOURCE.extension)) {
						final Iterator<Path> relativePath = file.subpath(nameCount, file.getNameCount()).iterator();
						while (relativePath.hasNext()) {
							final String nextPart = relativePath.next().toString();
							if (excludePaths.containsKey(nextPart)) {
								boolean excludeScript = true;

								final List<String> includePath = excludePaths.get(nextPart);
								if (includePath != null) {
									while (relativePath.hasNext()) {
										if (includePath.contains(relativePath.next().toString())) {
											excludeScript = false;
											break;
										}
									}
								}
								if (excludeScript) {
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
		} catch (final IOException e) {
			LOGGER.warn("Couldn't load script exclusions.", e);
		}
	}
}
