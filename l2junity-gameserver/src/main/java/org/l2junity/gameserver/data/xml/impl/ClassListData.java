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

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.l2junity.commons.loader.annotations.InstanceGetter;
import org.l2junity.commons.loader.annotations.Load;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.loader.LoadGroup;
import org.l2junity.gameserver.model.base.ClassId;
import org.l2junity.gameserver.model.base.ClassInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Loads the the list of classes and its info.
 * @author Zoey76
 */
public final class ClassListData implements IGameXmlReader
{
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassListData.class);
	
	private final Map<ClassId, ClassInfo> _classData = new HashMap<>();
	
	/**
	 * Instantiates a new class list data.
	 */
	protected ClassListData()
	{
	}
	
	@Load(group = LoadGroup.class)
	private void load() throws Exception
	{
		_classData.clear();
		parseDatapackFile("data/stats/chars/classList.xml");
		LOGGER.info("Loaded {} Class data.", _classData.size());
	}
	
	@Override
	public void parseDocument(Document doc, Path path)
	{
		NamedNodeMap attrs;
		Node attr;
		ClassId classId;
		String className;
		ClassId parentClassId;
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equals(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					attrs = d.getAttributes();
					if ("class".equals(d.getNodeName()))
					{
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
	
	public int getLoadedElementsCount()
	{
		return _classData.size();
	}
	
	/**
	 * Gets the class list.
	 * @return the complete class list.
	 */
	public Map<ClassId, ClassInfo> getClassList()
	{
		return _classData;
	}
	
	/**
	 * Gets the class info.
	 * @param classId the class Id.
	 * @return the class info related to the given {@code classId}.
	 */
	public ClassInfo getClass(ClassId classId)
	{
		return _classData.get(classId);
	}
	
	/**
	 * Gets the class info.
	 * @param classId the class Id as integer.
	 * @return the class info related to the given {@code classId}.
	 */
	public ClassInfo getClass(int classId)
	{
		final ClassId id = ClassId.getClassId(classId);
		return (id != null) ? _classData.get(id) : null;
	}
	
	/**
	 * Gets the single instance of ClassListData.
	 * @return single instance of ClassListData
	 */
	@InstanceGetter
	public static ClassListData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final ClassListData _instance = new ClassListData();
	}
}
