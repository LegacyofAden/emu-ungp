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
package org.l2junity.gameserver.instancemanager;

import org.l2junity.commons.util.StringUtil;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.model.quest.Quest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Quests and scripts manager.
 *
 * @author Zoey76
 */
public final class QuestManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuestManager.class);

	/**
	 * Map containing all the quests.
	 */
	private final Map<String, Quest> _quests = new ConcurrentHashMap<>();

	/**
	 * Map containing all the scripts.
	 */
	private final Map<String, Quest> _scripts = new ConcurrentHashMap<>();

	protected QuestManager() {
	}

	public boolean reload(String questFolder) {
		final Quest q = getQuest(questFolder);
		if (q == null) {
			return false;
		}
		return q.reload();
	}

	/**
	 * Reloads a the quest by ID.
	 *
	 * @param questId the ID of the quest to be reloaded
	 * @return {@code true} if reload was successful, {@code false} otherwise
	 */
	public boolean reload(int questId) {
		final Quest q = getQuest(questId);
		if (q == null) {
			return false;
		}
		return q.reload();
	}

	/**
	 * Logs how many quests and scripts are loaded.
	 */
	public void report() {
		LOGGER.info("Loaded: {} quests.", _quests.size());
		LOGGER.info("Loaded: {} scripts.", _scripts.size());
	}

	/**
	 * Calls {@link Quest#onSave()} in all quests and scripts.
	 */
	public void save() {
		// Save quests.
		_quests.values().forEach(Quest::onSave);

		// Save scripts.
		_scripts.values().forEach(Quest::onSave);
	}

	/**
	 * Gets a quest by name.<br>
	 * <i>For backwards compatibility, verifies scripts with the given name if the quest is not found.</i>
	 *
	 * @param name the quest name
	 * @return the quest
	 */
	public Quest getQuest(String name) {
		return _quests.getOrDefault(name, _scripts.get(name));
	}

	/**
	 * Gets a quest by ID.
	 *
	 * @param questId the ID of the quest to get
	 * @return if found, the quest, {@code null} otherwise
	 */
	public Quest getQuest(int questId) {
		return _quests.values().stream().filter(quest -> quest.getId() == questId).findFirst().orElse(null);
	}

	/**
	 * Adds a new quest.
	 *
	 * @param quest the quest to be added
	 */
	public void addQuest(Quest quest) {
		if (quest == null) {
			throw new IllegalArgumentException("Quest argument cannot be null");
		}

		// FIXME: unloading the old quest at this point is a tad too late.
		// the new quest has already initialized itself and read the data, starting
		// an unpredictable number of tasks with that data. The old quest will now
		// save data which will never be read.
		// However, requesting the newQuest to re-read the data is not necessarily a
		// good option, since the newQuest may have already started timers, spawned NPCs
		// or taken any other action which it might re-take by re-reading the data.
		// the current solution properly closes the running tasks of the old quest but
		// ignores the data; perhaps the least of all evils...
		final Quest old = _quests.put(quest.getName(), quest);
		if (old != null) {
			old.unload();
			LOGGER.info("Replaced quest {} ({}) with a new version!", old.getName(), old.getId());

		}

		if (GeneralConfig.ALT_DEV_SHOW_QUESTS_LOAD_IN_LOGS) {
			final String questName = quest.getName().contains("_") ? quest.getName().substring(quest.getName().indexOf('_') + 1) : quest.getName();
			LOGGER.info("Loaded quest {}.", StringUtil.splitWords(questName));
		}
	}

	/**
	 * Removes a script.
	 *
	 * @param script the script to remove
	 * @return {@code true} if the script was removed, {@code false} otherwise
	 */
	public boolean removeScript(Quest script) {
		if (_quests.containsKey(script.getName())) {
			_quests.remove(script.getName());
			return true;
		} else if (_scripts.containsKey(script.getName())) {
			_scripts.remove(script.getName());
			return true;
		}
		return false;
	}

	public Map<String, Quest> getQuests() {
		return _quests;
	}

	public boolean unload(Quest ms) {
		ms.onSave();
		return removeScript(ms);
	}

	/**
	 * Gets all the registered scripts.
	 *
	 * @return all the scripts
	 */
	public Map<String, Quest> getScripts() {
		return _scripts;
	}

	/**
	 * Adds a script.
	 *
	 * @param script the script to be added
	 */
	public void addScript(Quest script) {
		final Quest old = _scripts.put(script.getName(), script);
		if (old != null) {
			old.unload();
			LOGGER.info("Replaced script {} with a new version!", old.getName());
		}

		if (GeneralConfig.ALT_DEV_SHOW_SCRIPTS_LOAD_IN_LOGS) {
			LOGGER.info("Loaded script {}.", StringUtil.splitWords(script.getName()));
		}
	}

	/**
	 * Gets the single instance of {@code QuestManager}.
	 *
	 * @return single instance of {@code QuestManager}
	 */
	public static QuestManager getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final QuestManager INSTANCE = new QuestManager();
	}
}
