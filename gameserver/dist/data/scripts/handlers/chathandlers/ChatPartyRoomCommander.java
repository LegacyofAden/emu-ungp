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
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.s2c.CreatureSay;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

/**
 * Party Room Commander chat handler.
 *
 * @author durgus
 */
public final class ChatPartyRoomCommander implements IChatHandler {
	private static final ChatType[] CHAT_TYPES =
			{
					ChatType.PARTYROOM_COMMANDER,
			};

	@Override
	public void handleChat(ChatType type, Player activeChar, String target, String text) {
		if (activeChar.isInParty()) {
			if (activeChar.getParty().isInCommandChannel() && activeChar.getParty().getCommandChannel().getLeader().equals(activeChar)) {
				if (activeChar.isChatBanned() && GeneralConfig.BAN_CHAT_CHANNELS.contains(type)) {
					activeChar.sendPacket(SystemMessageId.CHATTING_IS_CURRENTLY_PROHIBITED_IF_YOU_TRY_TO_CHAT_BEFORE_THE_PROHIBITION_IS_REMOVED_THE_PROHIBITION_TIME_WILL_INCREASE_EVEN_FURTHER_CHATTING_BAN_TIME_REMAINING_S1_SECONDS);
					return;
				}
				activeChar.getParty().getCommandChannel().broadcastCreatureSay(new CreatureSay(activeChar.getObjectId(), type, activeChar.getName(), text), activeChar);
			}
		}
	}

	@Override
	public ChatType[] getChatTypeList() {
		return CHAT_TYPES;
	}

	public static void main(String[] args) {
		ChatHandler.getInstance().registerHandler(new ChatPartyRoomCommander());
	}
}