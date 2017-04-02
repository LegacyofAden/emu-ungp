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
package handlers.chathandlers;

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.handler.ChatHandler;
import org.l2junity.gameserver.handler.IChatHandler;
import org.l2junity.gameserver.model.BlockList;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.CreatureSay;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * Hero chat handler.
 *
 * @author durgus
 */
public final class ChatHeroVoice implements IChatHandler {
	private static final ChatType[] CHAT_TYPES =
			{
					ChatType.HERO_VOICE,
			};

	@Override
	public void handleChat(ChatType type, PlayerInstance activeChar, String target, String text) {
		if (!activeChar.isHero() && !activeChar.canOverrideCond(PcCondOverride.CHAT_CONDITIONS)) {
			activeChar.sendPacket(SystemMessageId.ONLY_HEROES_CAN_ENTER_THE_HERO_CHANNEL);
			return;
		}

		if (activeChar.isChatBanned() && GeneralConfig.BAN_CHAT_CHANNELS.contains(type)) {
			activeChar.sendPacket(SystemMessageId.CHATTING_IS_CURRENTLY_PROHIBITED_IF_YOU_TRY_TO_CHAT_BEFORE_THE_PROHIBITION_IS_REMOVED_THE_PROHIBITION_TIME_WILL_INCREASE_EVEN_FURTHER_CHATTING_BAN_TIME_REMAINING_S1_SECONDS);
			return;
		}

		if (!activeChar.getFloodProtectors().getHeroVoice().tryPerformAction("hero voice")) {
			activeChar.sendMessage("Action failed. Heroes are only able to speak in the global channel once every 10 seconds.");
			return;
		}

		final CreatureSay cs = new CreatureSay(activeChar.getObjectId(), type, activeChar.getName(), text);
		for (PlayerInstance player : World.getInstance().getPlayers()) {
			if ((player != null) && !BlockList.isBlocked(player, activeChar)) {
				player.sendPacket(cs);
			}
		}
	}

	@Override
	public ChatType[] getChatTypeList() {
		return CHAT_TYPES;
	}

	public static void main(String[] args) {
		ChatHandler.getInstance().registerHandler(new ChatHeroVoice());
	}
}