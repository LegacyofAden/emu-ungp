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
package org.l2junity.gameserver.data.xml.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.startup.StartupComponent;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/**
 * @author NosBit
 */
@Slf4j
@StartupComponent("Data")
public class SecondaryAuthData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final SecondaryAuthData instance = new SecondaryAuthData();

	private final Set<String> _forbiddenPasswords = new HashSet<>();
	private boolean _enabled = false;
	private int _maxAttempts = 5;
	private int _banTime = 480;
	private String _recoveryLink = "";

	private SecondaryAuthData() {
		_forbiddenPasswords.clear();
		parseDatapackFile("config/xml/SecondaryAuth.xml");
		log.info("Loaded {} forbidden passwords.", _forbiddenPasswords.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		try {
			for (Node node = doc.getFirstChild(); node != null; node = node.getNextSibling()) {
				if ("list".equalsIgnoreCase(node.getNodeName())) {
					for (Node list_node = node.getFirstChild(); list_node != null; list_node = list_node.getNextSibling()) {
						if ("enabled".equalsIgnoreCase(list_node.getNodeName())) {
							_enabled = Boolean.parseBoolean(list_node.getTextContent());
						} else if ("maxAttempts".equalsIgnoreCase(list_node.getNodeName())) {
							_maxAttempts = Integer.parseInt(list_node.getTextContent());
						} else if ("banTime".equalsIgnoreCase(list_node.getNodeName())) {
							_banTime = Integer.parseInt(list_node.getTextContent());
						} else if ("recoveryLink".equalsIgnoreCase(list_node.getNodeName())) {
							_recoveryLink = list_node.getTextContent();
						} else if ("forbiddenPasswords".equalsIgnoreCase(list_node.getNodeName())) {
							for (Node forbiddenPasswords_node = list_node.getFirstChild(); forbiddenPasswords_node != null; forbiddenPasswords_node = forbiddenPasswords_node.getNextSibling()) {
								if ("password".equalsIgnoreCase(forbiddenPasswords_node.getNodeName())) {
									_forbiddenPasswords.add(forbiddenPasswords_node.getTextContent());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.warn("Failed to load secondary auth data from xml.", e);
		}
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public int getMaxAttempts() {
		return _maxAttempts;
	}

	public int getBanTime() {
		return _banTime;
	}

	public String getRecoveryLink() {
		return _recoveryLink;
	}

	public Set<String> getForbiddenPasswords() {
		return _forbiddenPasswords;
	}

	public boolean isForbiddenPassword(String password) {
		return _forbiddenPasswords.contains(password);
	}
}
