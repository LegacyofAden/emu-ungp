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
package handlers.voicedcommandhandlers;

import org.l2junity.gameserver.handler.IVoicedCommandHandler;
import org.l2junity.gameserver.handler.VoicedCommandHandler;
import org.l2junity.gameserver.model.Language;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.NpcHtmlMessage;

import java.util.StringTokenizer;

public class Lang implements IVoicedCommandHandler {
	private static final String[] VOICED_COMMANDS =
			{
					"lang"
			};

	@Override
	public boolean useVoicedCommand(String command, Player activeChar, String params) {
		final NpcHtmlMessage msg = new NpcHtmlMessage();
		if (params == null) {
			final StringBuilder html = new StringBuilder(100);
			for (Language lang : Language.values()) {
				html.append("<button value=\"").append(lang.getShortName()).append("\" action=\"bypass -h voice .lang ").append(lang.toString()).append("\" width=60 height=21 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"><br>");
			}

			msg.setFile(activeChar.getLang(), "mods/lang/languageselect.htm");
			msg.replace("%list%", html.toString());
			activeChar.sendPacket(msg);
			return true;
		}

		final StringTokenizer st = new StringTokenizer(params);
		if (st.hasMoreTokens()) {
			final String lang = st.nextToken().trim();
			try {
				Language language = Language.valueOf(lang);
				activeChar.setLang(language);
				msg.setFile(language, "mods/lang/ok.htm");
			}
			catch (Exception e) {
				msg.setFile(activeChar.getLang(), "mods/lang/error.htm");
			}
			activeChar.sendPacket(msg);
			return true;
		}
		return false;
	}

	@Override
	public String[] getVoicedCommandList() {
		return VOICED_COMMANDS;
	}

	public static void main(String[] args) {
		VoicedCommandHandler.getInstance().registerHandler(new Lang());
	}
}