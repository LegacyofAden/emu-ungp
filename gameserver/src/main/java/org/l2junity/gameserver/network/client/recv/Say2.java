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
package org.l2junity.gameserver.network.client.recv;

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.L2JModsConfig;
import org.l2junity.gameserver.enums.ChatType;
import org.l2junity.gameserver.handler.ChatHandler;
import org.l2junity.gameserver.handler.IChatHandler;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosEvent;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.character.player.OnPlayerChat;
import org.l2junity.gameserver.model.events.returns.ChatFilterReturn;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.olympiad.OlympiadManager;
import org.l2junity.gameserver.model.stats.BooleanStat;
import org.l2junity.gameserver.model.world.WorldManager;
import org.l2junity.gameserver.network.client.Disconnection;
import org.l2junity.gameserver.network.client.L2GameClient;
import org.l2junity.gameserver.network.client.send.ActionFailed;
import org.l2junity.gameserver.network.client.send.string.SystemMessageId;
import org.l2junity.gameserver.util.Util;
import org.l2junity.network.PacketReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Say2 implements IClientIncomingPacket {
	private static Logger _logChat = LoggerFactory.getLogger("chat");

	private static final String[] WALKER_COMMAND_LIST =
			{
					"USESKILL",
					"USEITEM",
					"BUYITEM",
					"SELLITEM",
					"SAVEITEM",
					"LOADITEM",
					"MSG",
					"DELAY",
					"LABEL",
					"JMP",
					"CALL",
					"RETURN",
					"MOVETO",
					"NPCSEL",
					"NPCDLG",
					"DLGSEL",
					"CHARSTATUS",
					"POSOUTRANGE",
					"POSINRANGE",
					"GOHOME",
					"SAY",
					"EXIT",
					"PAUSE",
					"STRINDLG",
					"STRNOTINDLG",
					"CHANGEWAITTYPE",
					"FORCEATTACK",
					"ISMEMBER",
					"REQUESTJOINPARTY",
					"REQUESTOUTPARTY",
					"QUITPARTY",
					"MEMBERSTATUS",
					"CHARBUFFS",
					"ITEMCOUNT",
					"FOLLOWTELEPORT"
			};

	private String _text;
	private int _type;
	private String _target;

	@Override
	public boolean read(L2GameClient client, PacketReader packet) {
		_text = packet.readS();
		_type = packet.readD();
		_target = (_type == ChatType.WHISPER.getClientId()) ? packet.readS() : null;
		return true;
	}

	@Override
	public void run(L2GameClient client) {
		final Player activeChar = client.getActiveChar();
		if (activeChar == null) {
			return;
		}

		ChatType chatType = ChatType.findByClientId(_type);
		if (chatType == null) {
			_log.warn("Say2: Invalid type: " + _type + " Player : " + activeChar.getName() + " text: " + String.valueOf(_text));
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			Disconnection.of(activeChar).defaultSequence(false);
			return;
		}

		if (_text.isEmpty()) {
			_log.warn(activeChar.getName() + ": sending empty text. Possible packet hack!");
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			Disconnection.of(activeChar).defaultSequence(false);
			return;
		}

		// Even though the client can handle more characters than it's current limit allows, an overflow (critical error) happens if you pass a huge (1000+) message.
		// July 11, 2011 - Verified on High Five 4 official client as 105.
		// Allow higher limit if player shift some item (text is longer then).
		if (!activeChar.isGM() && (((_text.indexOf(8) >= 0) && (_text.length() > 500)) || ((_text.indexOf(8) < 0) && (_text.length() > 105)))) {
			activeChar.sendPacket(SystemMessageId.WHEN_A_USER_S_KEYBOARD_INPUT_EXCEEDS_A_CERTAIN_CUMULATIVE_SCORE_A_CHAT_BAN_WILL_BE_APPLIED_THIS_IS_DONE_TO_DISCOURAGE_SPAMMING_PLEASE_AVOID_POSTING_THE_SAME_MESSAGE_MULTIPLE_TIMES_DURING_A_SHORT_PERIOD);
			return;
		}

		if (L2JModsConfig.L2WALKER_PROTECTION && (chatType == ChatType.WHISPER) && checkBot(_text)) {
			Util.handleIllegalPlayerAction(activeChar, "Client Emulator Detect: Player " + activeChar.getName() + " using l2walker.", GeneralConfig.DEFAULT_PUNISH);
			return;
		}

		if (activeChar.isCursedWeaponEquipped() && ((chatType == ChatType.TRADE) || (chatType == ChatType.SHOUT))) {
			activeChar.sendPacket(SystemMessageId.SHOUT_AND_TRADE_CHATTING_CANNOT_BE_USED_WHILE_POSSESSING_A_CURSED_WEAPON);
			return;
		}

		if (activeChar.isChatBanned() && (_text.charAt(0) != '.')) {
			if (activeChar.getStat().has(BooleanStat.BLOCK_CHAT)) {
				activeChar.sendPacket(SystemMessageId.YOU_HAVE_BEEN_REPORTED_AS_AN_ILLEGAL_PROGRAM_USER_SO_CHATTING_IS_NOT_ALLOWED);
			} else {
				if (GeneralConfig.BAN_CHAT_CHANNELS.contains(chatType)) {
					activeChar.sendPacket(SystemMessageId.CHATTING_IS_CURRENTLY_PROHIBITED_IF_YOU_TRY_TO_CHAT_BEFORE_THE_PROHIBITION_IS_REMOVED_THE_PROHIBITION_TIME_WILL_INCREASE_EVEN_FURTHER_CHATTING_BAN_TIME_REMAINING_S1_SECONDS);
				}
			}
			return;
		}

		if (activeChar.isInOlympiadMode() || OlympiadManager.getInstance().isRegistered(activeChar)) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_CHAT_WHILE_PARTICIPATING_IN_THE_OLYMPIAD);
			return;
		}

		if (activeChar.isOnEvent(CeremonyOfChaosEvent.class)) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_CHAT_IN_THE_CEREMONY_OF_CHAOS);
			return;
		}

		if (activeChar.isJailed() && GeneralConfig.JAIL_DISABLE_CHAT) {
			if ((chatType == ChatType.WHISPER) || (chatType == ChatType.SHOUT) || (chatType == ChatType.TRADE) || (chatType == ChatType.HERO_VOICE)) {
				activeChar.sendMessage("You can not chat with players outside of the jail.");
				return;
			}
		}

		if ((chatType == ChatType.PETITION_PLAYER) && activeChar.isGM()) {
			chatType = ChatType.PETITION_GM;
		}

		if (GeneralConfig.LOG_CHAT) {
			if (chatType == ChatType.WHISPER) {
				_logChat.info("{} [{} to {}] {}", chatType.name(), activeChar, _target, _text);
			} else {
				_logChat.info("{} [{}] {}", chatType.name(), activeChar, _text);
			}

		}

		if (_text.indexOf(8) >= 0) {
			if (!parseAndPublishItem(client, activeChar)) {
				return;
			}
		}

		final ChatFilterReturn filter = EventDispatcher.getInstance().notifyEvent(new OnPlayerChat(activeChar, WorldManager.getInstance().getPlayer(_target), _text, chatType), activeChar, ChatFilterReturn.class);
		if (filter != null) {
			if (filter.terminate()) {
				return;
			}
			_text = filter.getFilteredText();
			chatType = filter.getChatType();
		}

		// Say Filter implementation
		if (GeneralConfig.USE_SAY_FILTER) {
			checkText();
		}

		final IChatHandler handler = ChatHandler.getInstance().getHandler(chatType);
		if (handler != null) {
			handler.handleChat(chatType, activeChar, _target, _text);
		} else {
			_log.info("No handler registered for ChatType: " + _type + " Player: " + client);
		}
	}

	private boolean checkBot(String text) {
		for (String botCommand : WALKER_COMMAND_LIST) {
			if (text.startsWith(botCommand)) {
				return true;
			}
		}
		return false;
	}

	private void checkText() {
		// TODO: Obscene filter
		_text = _text;
	}

	private boolean parseAndPublishItem(L2GameClient client, Player owner) {
		int pos1 = -1;
		while ((pos1 = _text.indexOf(8, pos1)) > -1) {
			int pos = _text.indexOf("ID=", pos1);
			if (pos == -1) {
				return false;
			}
			StringBuilder result = new StringBuilder(9);
			pos += 3;
			while (Character.isDigit(_text.charAt(pos))) {
				result.append(_text.charAt(pos++));
			}
			final int id = Integer.parseInt(result.toString());
			final ItemInstance item = owner.getInventory().getItemByObjectId(id);

			if (item == null) {
				_log.info(client + " trying publish item which doesnt own! ID:" + id);
				return false;
			}
			item.publish();

			pos1 = _text.indexOf(8, pos) + 1;
			if (pos1 == 0) // missing ending tag
			{
				_log.info(client + " sent invalid publish item msg! ID:" + id);
				return false;
			}
		}
		return true;
	}
}
