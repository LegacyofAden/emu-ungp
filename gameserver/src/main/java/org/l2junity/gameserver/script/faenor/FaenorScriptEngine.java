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
package org.l2junity.gameserver.script.faenor;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.script.Parser;
import org.l2junity.gameserver.script.ParserNotCreatedException;
import org.l2junity.gameserver.script.ScriptDocument;
import org.l2junity.gameserver.script.ScriptEngine;
import org.w3c.dom.Node;

import javax.script.ScriptContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Luis Arias
 */
@Slf4j
@StartupComponent("Scripts")
public final class FaenorScriptEngine {
	@Getter(lazy = true)
	private static final FaenorScriptEngine instance = new FaenorScriptEngine();

	private FaenorScriptEngine() {
		try {
			Files.walkFileTree(Paths.get("data", "faenor"), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					try (final InputStream in = Files.newInputStream(file)) {
						parseScript(new ScriptDocument(file.getFileName().toString(), in), null);
					}
					return super.visitFile(file, attrs);
				}
			});
		} catch (IOException e) {
			log.warn(e.getMessage(), e);
		}
	}

	protected void parseScript(ScriptDocument script, ScriptContext context) {
		Node node = script.getDocument().getFirstChild();
		String parserClass = "faenor.Faenor" + node.getNodeName() + "Parser";

		Parser parser = null;
		try {
			parser = ScriptEngine.createParser(parserClass);
		} catch (ParserNotCreatedException e) {
			log.warn("ERROR: No parser registered for Script: {}: ", parserClass, e);
		}

		if (parser == null) {
			log.warn("Unknown Script Type: {}", script.getName());
			return;
		}

		try {
			parser.parseScript(node, context);
			log.info("Loaded  {} successfully.", script.getName());
		} catch (Exception e) {
			log.warn("Script Parsing Failed: ", e);
		}
	}
}
