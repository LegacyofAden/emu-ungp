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

import org.l2junity.gameserver.config.GeneralConfig;
import org.l2junity.gameserver.config.PlayerConfig;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.handler.ChatHandler;
import org.l2junity.gameserver.handler.IChatHandler;
import org.l2junity.gameserver.model.BlockList;
import org.l2junity.gameserver.model.PcCondOverride;
import org.l2junity.gameserver.model.World;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;
import org.l2junity.gameserver.network.client.send.CreatureSay;
import org.l2junity.gameserver.network.client.send.SystemMessage;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;

/**
 * Tell chat handler.
 * @author durgus
 */
public final class ChatWhisper implements IChatHandler
{
	private static final ChatType[] CHAT_TYPES =
	{
		ChatType.WHISPER,
	};
	
	@Override
	public void handleChat(ChatType type, PlayerInstance activeChar, String target, String text)
	{
		if (activeChar.isChatBanned() && GeneralConfig.BAN_CHAT_CHANNELS.contains(type))
		{
			activeChar.sendPacket(SystemMessageId.CHATTING_IS_CURRENTLY_PROHIBITED_IF_YOU_TRY_TO_CHAT_BEFORE_THE_PROHIBITION_IS_REMOVED_THE_PROHIBITION_TIME_WILL_INCREASE_EVEN_FURTHER_CHATTING_BAN_TIME_REMAINING_S1_SECONDS);
			return;
		}
		
		if (GeneralConfig.JAIL_DISABLE_CHAT && activeChar.isJailed() && !activeChar.canOverrideCond(PcCondOverride.CHAT_CONDITIONS))
		{
			activeChar.sendPacket(SystemMessageId.CHATTING_IS_CURRENTLY_PROHIBITED);
			return;
		}
		
		// Return if no target is set
		if (target == null)
		{
			return;
		}
		
		final PlayerInstance receiver = World.getInstance().getPlayer(target);
		
		if ((receiver != null) && !receiver.isSilenceMode(activeChar.getObjectId()))
		{
			if (GeneralConfig.JAIL_DISABLE_CHAT && receiver.isJailed() && !activeChar.canOverrideCond(PcCondOverride.CHAT_CONDITIONS))
			{
				activeChar.sendMessage("Player is in jail.");
				return;
			}
			else if (receiver.isChatBanned())
			{
				activeChar.sendPacket(SystemMessageId.THAT_PERSON_IS_IN_MESSAGE_REFUSAL_MODE);
				return;
			}
			else if ((receiver.getClient() == null) || receiver.getClient().isDetached())
			{
				activeChar.sendMessage("Player is in offline mode.");
				return;
			}
			else if ((activeChar.getLevel() < GeneralConfig.MINIMUM_CHAT_LEVEL) && !activeChar.getWhisperers().contains(receiver.getObjectId()) && !activeChar.canOverrideCond(PcCondOverride.CHAT_CONDITIONS))
			{
				activeChar.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.PLAYERS_CAN_RESPOND_TO_A_WHISPER_BUT_CANNOT_INITIATE_A_WHISPER_UNTIL_LV_S1).addInt(GeneralConfig.MINIMUM_CHAT_LEVEL));
				return;
			}
			else if (!BlockList.isBlocked(receiver, activeChar))
			{
				// Allow reciever to send PMs to this char, which is in silence mode.
				if (PlayerConfig.SILENCE_MODE_EXCLUDE && activeChar.isSilenceMode())
				{
					activeChar.addSilenceModeExcluded(receiver.getObjectId());
				}
				
				receiver.getWhisperers().add(activeChar.getObjectId());
				receiver.sendPacket(new CreatureSay(activeChar, receiver, activeChar.getName(), type, text));
				activeChar.sendPacket(new CreatureSay(activeChar, receiver, "->" + receiver.getName(), type, text));
			}
			else
			{
				activeChar.sendPacket(SystemMessageId.THAT_PERSON_IS_IN_MESSAGE_REFUSAL_MODE);
			}
		}
		else
		{
			activeChar.sendPacket(SystemMessageId.THAT_PLAYER_IS_NOT_ONLINE);
		}
	}
	
	@Override
	public ChatType[] getChatTypeList()
	{
		return CHAT_TYPES;
	}
	
	public static void main(String[] args)
	{
		ChatHandler.getInstance().registerHandler(new ChatWhisper());
	}
}