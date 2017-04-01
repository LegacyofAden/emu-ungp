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
import org.l2junity.commons.loader.annotations.Reload;
import org.l2junity.gameserver.data.xml.IGameXmlReader;
import org.l2junity.gameserver.loader.LoadGroup;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.actor.transform.Transform;
import org.l2junity.gameserver.model.actor.transform.TransformLevelData;
import org.l2junity.gameserver.model.actor.transform.TransformTemplate;
import org.l2junity.gameserver.model.holders.AdditionalItemHolder;
import org.l2junity.gameserver.model.holders.AdditionalSkillHolder;
import org.l2junity.gameserver.model.holders.SkillHolder;
import org.l2junity.gameserver.network.client.send.ExBasicActionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author UnAfraid
 */
public final class TransformData implements IGameXmlReader
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TransformData.class);
	
	private final Map<Integer, Transform> _transformData = new HashMap<>();
	
	protected TransformData()
	{
	}
	
	@Reload("transform")
	@Load(group = LoadGroup.class)
	private void load() throws Exception
	{
		_transformData.clear();
		parseDatapackDirectory("data/stats/transformations", false);
		LOGGER.info("Loaded: {} transform templates.", _transformData.size());
	}
	
	@Override
	public void parseDocument(Document doc, Path path)
	{
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("transform".equalsIgnoreCase(d.getNodeName()))
					{
						NamedNodeMap attrs = d.getAttributes();
						StatsSet set = new StatsSet();
						for (int i = 0; i < attrs.getLength(); i++)
						{
							Node att = attrs.item(i);
							set.set(att.getNodeName(), att.getNodeValue());
						}
						final Transform transform = new Transform(set);
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
						{
							boolean isMale = "Male".equalsIgnoreCase(cd.getNodeName());
							if ("Male".equalsIgnoreCase(cd.getNodeName()) || "Female".equalsIgnoreCase(cd.getNodeName()))
							{
								TransformTemplate templateData = null;
								for (Node z = cd.getFirstChild(); z != null; z = z.getNextSibling())
								{
									switch (z.getNodeName())
									{
										case "common":
										{
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												switch (s.getNodeName())
												{
													case "base":
													case "stats":
													case "defense":
													case "magicDefense":
													case "collision":
													case "moving":
													{
														attrs = s.getAttributes();
														for (int i = 0; i < attrs.getLength(); i++)
														{
															Node att = attrs.item(i);
															set.set(att.getNodeName(), att.getNodeValue());
														}
														break;
													}
												}
											}
											templateData = new TransformTemplate(set);
											transform.setTemplate(isMale, templateData);
											break;
										}
										case "skills":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("skill".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													int skillId = parseInteger(attrs, "id");
													int skillLevel = parseInteger(attrs, "level");
													templateData.addSkill(new SkillHolder(skillId, skillLevel));
												}
											}
											break;
										}
										case "actions":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											set.set("actions", z.getTextContent());
											final int[] actions = set.getIntArray("actions", " ");
											templateData.setBasicActionList(new ExBasicActionList(actions));
											break;
										}
										case "additionalSkills":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("skill".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													int skillId = parseInteger(attrs, "id");
													int skillLevel = parseInteger(attrs, "level");
													int minLevel = parseInteger(attrs, "minLevel");
													templateData.addAdditionalSkill(new AdditionalSkillHolder(skillId, skillLevel, minLevel));
												}
											}
											break;
										}
										case "items":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("item".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													int itemId = parseInteger(attrs, "id");
													boolean allowed = parseBoolean(attrs, "allowed");
													templateData.addAdditionalItem(new AdditionalItemHolder(itemId, allowed));
												}
											}
											break;
										}
										case "levels":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											
											final StatsSet levelsSet = new StatsSet();
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("level".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													for (int i = 0; i < attrs.getLength(); i++)
													{
														Node att = attrs.item(i);
														levelsSet.set(att.getNodeName(), att.getNodeValue());
													}
												}
											}
											templateData.addLevelData(new TransformLevelData(levelsSet));
											break;
										}
									}
								}
							}
						}
						_transformData.put(transform.getId(), transform);
					}
				}
			}
		}
	}
	
	public int getTransformCount()
	{
		return _transformData.size();
	}
	
	public Transform getTransform(int id)
	{
		return _transformData.get(id);
	}
	
	@InstanceGetter
	public static TransformData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final TransformData _instance = new TransformData();
	}
}
