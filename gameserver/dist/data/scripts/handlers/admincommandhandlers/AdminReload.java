/*
 * Copyright (C) 2004-2015 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.admincommandhandlers;

import org.l2junity.commons.config.utils.ConfigLoader;
import org.l2junity.gameserver.data.HtmRepository;
import org.l2junity.gameserver.data.sql.impl.CrestTable;
import org.l2junity.gameserver.data.xml.impl.AdminData;
import org.l2junity.gameserver.data.xml.impl.EnchantItemGroupsData;
import org.l2junity.gameserver.handler.AdminCommandHandler;
import org.l2junity.gameserver.handler.IAdminCommandHandler;
import org.l2junity.gameserver.instancemanager.QuestManager;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.NpcHtmlMessage;
import org.l2junity.gameserver.scripting.GameScriptsLoader;
import org.l2junity.gameserver.scripting.ScriptEngineManager;
import org.l2junity.gameserver.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;
import java.util.StringTokenizer;

/**
 * @author NosBit
 */
public final class AdminReload implements IAdminCommandHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminReload.class);

	private static final String[] ADMIN_COMMANDS =
			{
					"admin_reload"
			};

	private static final String RELOAD_USAGE;

	static {
		final StringJoiner sj = new StringJoiner("|", "Usage: //reload <", ">");
		sj.add("config");
		sj.add("quest");
		sj.add("html");
		sj.add("crest");
		sj.add("effect");
		sj.add("enchant");
		RELOAD_USAGE = sj.toString();
	}

	@Override
	public boolean useAdminCommand(String command, PlayerInstance activeChar) {
		final StringTokenizer st = new StringTokenizer(command, " ");
		final String actualCommand = st.nextToken();
		if (actualCommand.equalsIgnoreCase("admin_reload")) {
			if (!st.hasMoreTokens()) {
				showPage(activeChar, "data/html/admin/reload.htm");
				activeChar.sendMessage(RELOAD_USAGE);
				return true;
			}

			final String type = st.nextToken().toLowerCase();
			switch (type) {
				case "config": {
					ConfigLoader.getInstance().loadConfigs();
					AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded Configs.");
					break;
				}
				case "quest": {
					if (st.hasMoreElements()) {
						String value = st.nextToken();
						if (!Util.isDigit(value)) {
							QuestManager.getInstance().reload(value);
							AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded Quest Name:" + value + ".");
						} else {
							final int questId = Integer.parseInt(value);
							QuestManager.getInstance().reload(questId);
							AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded Quest ID:" + questId + ".");
						}
					} else {
						QuestManager.getInstance().reloadAll();
						activeChar.sendMessage("All scripts have been reloaded.");
						AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded Quests.");
					}
					break;
				}
				case "html": {
					HtmRepository.getInstance().reload();
					activeChar.sendMessage("All HTML's have been reloaded.");
					break;
				}
				case "crest": {
					CrestTable.getInstance().reload();
					AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded Crests.");
					break;
				}
				case "effect": {
					try {
						ScriptEngineManager.getInstance().executeScript(GameScriptsLoader.SCRIPT_FOLDER, GameScriptsLoader.EFFECT_MASTER_HANDLER_FILE);
						AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded effect master handler.");
					} catch (Exception e) {
						LOGGER.warn("Failed executing effect master handler!", e);
						activeChar.sendMessage("Error reloading effect master handler!");
					}
					break;
				}
				case "enchant": {
					try {
						EnchantItemGroupsData.getInstance().reload();
						AdminData.getInstance().broadcastMessageToGMs(activeChar.getName() + ": Reloaded item enchanting data.");
					} catch (Exception e) {
						LOGGER.warn("Failed reloading item enchanting data!", e);
						activeChar.sendMessage("Error reloading item enchanting data!");
					}
					break;
				}
			}
			activeChar.sendMessage("WARNING: There are several known issues regarding this feature. Reloading server data during runtime is STRONGLY NOT RECOMMENDED for live servers, just for developing environments.");
		}
		return true;
	}

	private static void showPage(PlayerInstance activeChar, String filename) {
		final NpcHtmlMessage html = new NpcHtmlMessage();
		html.setFile(activeChar.getHtmlPrefix(), filename);
		html.replace("%reloadables%", RELOAD_USAGE);
		activeChar.sendPacket(html);
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}

	public static void main(String[] args) {
		AdminCommandHandler.getInstance().registerHandler(new AdminReload());
	}
}