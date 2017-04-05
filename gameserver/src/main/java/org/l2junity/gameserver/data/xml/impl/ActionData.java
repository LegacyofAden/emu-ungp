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
import org.l2junity.gameserver.model.ActionDataHolder;
import org.l2junity.gameserver.model.StatsSet;
import org.w3c.dom.Document;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author UnAfraid
 */
@Slf4j
@StartupComponent("Data")
public class ActionData implements IGameXmlReader {
	@Getter(lazy = true)
	private static final ActionData instance = new ActionData();

	private final Map<Integer, ActionDataHolder> _actionData = new HashMap<>();
	private final Map<Integer, Integer> _actionSkillsData = new HashMap<>(); // skillId, actionId

	private ActionData() {
		_actionData.clear();
		_actionSkillsData.clear();
		parseDatapackFile("data/ActionData.xml");
		_actionData.values().stream().filter(h -> h.getHandler().equals("PetSkillUse") || h.getHandler().equals("ServitorSkillUse")).forEach(h -> _actionSkillsData.put(h.getOptionId(), h.getId()));
		log.info("Loaded {} player actions.", _actionData.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode -> forEach(listNode, "action", actionNode ->
		{
			final ActionDataHolder holder = new ActionDataHolder(new StatsSet(parseAttributes(actionNode)));
			_actionData.put(holder.getId(), holder);
		}));
	}

	public int getLoadedElementsCount() {
		return _actionData.size() + _actionSkillsData.size();
	}

	/**
	 * @param id
	 * @return the ActionDataHolder for specified id
	 */
	public ActionDataHolder getActionData(int id) {
		return _actionData.get(id);
	}

	/**
	 * @param skillId
	 * @return the actionId corresponding to the skillId or -1 if no actionId is found for the specified skill.
	 */
	public int getSkillActionId(int skillId) {
		return _actionSkillsData.getOrDefault(skillId, -1);
	}
}
