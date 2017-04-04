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
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.base.ClassInfo;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the the list of classes and its info.
 *
 * @author Zoey76
 */
@Slf4j
@StartupComponent("Data")
public final class ClassListData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ClassListData instance = new ClassListData();

	private final Map<ClassId, ClassInfo> _classData = new HashMap<>();

	private ClassListData() {
		_classData.clear();
		parseDatapackFile("data/stats/chars/classList.xml");
		log.info("Loaded {} Class data.", _classData.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		NamedNodeMap attrs;
		Node attr;
		ClassId classId;
		String className;
		ClassId parentClassId;
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling()) {
			if ("list".equals(n.getNodeName())) {
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling()) {
					attrs = d.getAttributes();
					if ("class".equals(d.getNodeName())) {
						attr = attrs.getNamedItem("classId");
						classId = ClassId.getClassId(parseInteger(attr));
						attr = attrs.getNamedItem("name");
						className = attr.getNodeValue();
						attr = attrs.getNamedItem("parentClassId");
						parentClassId = (attr != null) ? ClassId.getClassId(parseInteger(attr)) : null;
						_classData.put(classId, new ClassInfo(classId, className, parentClassId));
					}
				}
			}
		}
	}

	public int getLoadedElementsCount() {
		return _classData.size();
	}

	/**
	 * Gets the class list.
	 *
	 * @return the complete class list.
	 */
	public Map<ClassId, ClassInfo> getClassList() {
		return _classData;
	}

	/**
	 * Gets the class info.
	 *
	 * @param classId the class Id.
	 * @return the class info related to the given {@code classId}.
	 */
	public ClassInfo getClass(ClassId classId) {
		return _classData.get(classId);
	}

	/**
	 * Gets the class info.
	 *
	 * @param classId the class Id as integer.
	 * @return the class info related to the given {@code classId}.
	 */
	public ClassInfo getClass(int classId) {
		final ClassId id = ClassId.getClassId(classId);
		return (id != null) ? _classData.get(id) : null;
	}
}
