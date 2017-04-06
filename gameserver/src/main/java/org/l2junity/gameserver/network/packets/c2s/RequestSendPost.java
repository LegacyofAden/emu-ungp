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
package org.l2junity.gameserver.network.packets.c2s;

import lombok.extern.slf4j.Slf4j;
import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.data.sql.impl.CharNameTable;
import org.l2junity.gameserver.data.xml.impl.AdminData;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.instancemanager.MailManager;
import org.l2junity.gameserver.model.AccessLevel;
import org.l2junity.gameserver.model.BlockList;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Message;
import org.l2junity.gameserver.model.itemcontainer.Inventory;
import org.l2junity.gameserver.model.itemcontainer.ItemContainer;
import org.l2junity.gameserver.model.itemcontainer.Mail;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.zone.ZoneId;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ExNoticePostSent;
import org.l2junity.gameserver.network.packets.s2c.InventoryUpdate;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

import static org.l2junity.gameserver.model.itemcontainer.Inventory.ADENA_ID;

/**
 * @author Migi, DS
 */
@Slf4j
public final class RequestSendPost extends GameClientPacket {
	private static final int BATCH_LENGTH = 12; // length of the one item

	private static final int MAX_RECV_LENGTH = 16;
	private static final int MAX_SUBJ_LENGTH = 128;
	private static final int MAX_TEXT_LENGTH = 512;
	private static final int MAX_ATTACHMENTS = 8;
	private static final int INBOX_SIZE = 240;
	private static final int OUTBOX_SIZE = 240;

	private String _receiver;
	private boolean _isCod;
	private String _subject;
	private String _text;
	private AttachmentItem _items[] = null;
	private long _reqAdena;

	public RequestSendPost() {
	}

	@Override
	public void readImpl() {
		_receiver = readS();
		_isCod = readD() != 0;
		_subject = readS();
		_text = readS();

		int attachCount = readD();
		if ((attachCount < 0) || (attachCount > PlayerConfig.MAX_ITEM_IN_PACKET) || (((attachCount * BATCH_LENGTH) + 8) != getAvailableBytes())) {
			return;
		}

		if (attachCount > 0) {
			_items = new AttachmentItem[attachCount];
			for (int i = 0; i < attachCount; i++) {
				int objectId = readD();
				long count = readQ();
				if ((objectId < 1) || (count < 0)) {
					_items = null;
					return;
				}
				_items[i] = new AttachmentItem(objectId, count);
			}
		}

		_reqAdena = readQ();

	}

	@Override
	public void runImpl() {
		if (!GeneralConfig.ALLOW_MAIL) {
			return;
		}

		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		if (!GeneralConfig.ALLOW_ATTACHMENTS) {
			_items = null;
			_isCod = false;
			_reqAdena = 0;
		}

		if (!activeChar.getAccessLevel().allowTransaction()) {
			activeChar.sendMessage("Transactions are disabled for your Access Level.");
			return;
		}

		if (!activeChar.isInsideZone(ZoneId.PEACE) && (_items != null)) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_FORWARD_IN_A_NON_PEACE_ZONE_LOCATION);
			return;
		}

		if (activeChar.getActiveTradeList() != null) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_FORWARD_DURING_AN_EXCHANGE);
			return;
		}

		if (activeChar.hasItemRequest()) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_SEND_MAIL_WHILE_ENCHANTING_AN_ITEM_BESTOWING_AN_ATTRIBUTE_OR_COMBINING_JEWELS);
			return;
		}

		if (activeChar.getPrivateStoreType() != PrivateStoreType.NONE) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_FORWARD_BECAUSE_THE_PRIVATE_STORE_OR_WORKSHOP_IS_IN_PROGRESS);
			return;
		}

		if (_receiver.length() > MAX_RECV_LENGTH) {
			activeChar.sendPacket(SystemMessageId.THE_ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED);
			return;
		}

		if (_subject.length() > MAX_SUBJ_LENGTH) {
			activeChar.sendPacket(SystemMessageId.THE_ALLOWED_LENGTH_FOR_A_TITLE_EXCEEDED);
			return;
		}

		if (_text.length() > MAX_TEXT_LENGTH) {
			// not found message for this
			activeChar.sendPacket(SystemMessageId.THE_ALLOWED_LENGTH_FOR_A_TITLE_EXCEEDED);
			return;
		}

		if ((_items != null) && (_items.length > MAX_ATTACHMENTS)) {
			activeChar.sendPacket(SystemMessageId.ITEM_SELECTION_IS_POSSIBLE_UP_TO_8);
			return;
		}

		if (!ItemContainer.validateCount(Inventory.ADENA_ID, _reqAdena)) {
			return;
		}

		if (_isCod) {
			if (_reqAdena == 0) {
				activeChar.sendPacket(SystemMessageId.WHEN_NOT_ENTERING_THE_AMOUNT_FOR_THE_PAYMENT_REQUEST_YOU_CANNOT_SEND_ANY_MAIL);
				return;
			}
			if ((_items == null) || (_items.length == 0)) {
				activeChar.sendPacket(SystemMessageId.IT_S_A_PAYMENT_REQUEST_TRANSACTION_PLEASE_ATTACH_THE_ITEM);
				return;
			}
		}

		final int receiverId = CharNameTable.getInstance().getIdByName(_receiver);
		if (receiverId <= 0) {
			activeChar.sendPacket(SystemMessageId.WHEN_THE_RECIPIENT_DOESN_T_EXIST_OR_THE_CHARACTER_HAS_BEEN_DELETED_SENDING_MAIL_IS_NOT_POSSIBLE);
			return;
		}

		if (receiverId == activeChar.getObjectId()) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_SEND_A_MAIL_TO_YOURSELF);
			return;
		}

		final int level = CharNameTable.getInstance().getAccessLevelById(receiverId);
		final AccessLevel accessLevel = AdminData.getInstance().getAccessLevel(level);

		if ((accessLevel != null) && accessLevel.isGm() && !activeChar.getAccessLevel().isGm()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_MESSAGE_TO_C1_DID_NOT_REACH_ITS_RECIPIENT_YOU_CANNOT_SEND_MAIL_TO_THE_GM_STAFF);
			sm.addString(_receiver);
			activeChar.sendPacket(sm);
			return;
		}

		if (activeChar.isJailed() && ((GeneralConfig.JAIL_DISABLE_TRANSACTION && (_items != null)) || GeneralConfig.JAIL_DISABLE_CHAT)) {
			activeChar.sendPacket(SystemMessageId.YOU_CANNOT_FORWARD_IN_A_NON_PEACE_ZONE_LOCATION);
			return;
		}

		if (BlockList.isInBlockList(receiverId, activeChar.getObjectId())) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_BLOCKED_YOU_YOU_CANNOT_SEND_MAIL_TO_C1);
			sm.addString(_receiver);
			activeChar.sendPacket(sm);
			return;
		}

		if (MailManager.getInstance().getOutboxSize(activeChar.getObjectId()) >= OUTBOX_SIZE) {
			activeChar.sendPacket(SystemMessageId.THE_MAIL_LIMIT_240_HAS_BEEN_EXCEEDED_AND_THIS_CANNOT_BE_FORWARDED);
			return;
		}

		if (MailManager.getInstance().getInboxSize(receiverId) >= INBOX_SIZE) {
			activeChar.sendPacket(SystemMessageId.THE_MAIL_LIMIT_240_HAS_BEEN_EXCEEDED_AND_THIS_CANNOT_BE_FORWARDED);
			return;
		}

		if (!getClient().getFloodProtectors().getSendMail().tryPerformAction("sendmail")) {
			activeChar.sendPacket(SystemMessageId.THE_PREVIOUS_MAIL_WAS_FORWARDED_LESS_THAN_1_MINUTE_AGO_AND_THIS_CANNOT_BE_FORWARDED);
			return;
		}

		Message msg = new Message(activeChar.getObjectId(), receiverId, _isCod, _subject, _text, _reqAdena);
		if (removeItems(activeChar, msg)) {
			MailManager.getInstance().sendMessage(msg);
			activeChar.sendPacket(ExNoticePostSent.valueOf(true));
			activeChar.sendPacket(SystemMessageId.MAIL_SUCCESSFULLY_SENT);
		}
	}

	private boolean removeItems(Player player, Message msg) {
		long currentAdena = player.getAdena();
		long fee = PlayerConfig.ALT_MESSAGE_FEE;

		if (_items != null) {
			for (AttachmentItem i : _items) {
				// Check validity of requested item
				ItemInstance item = player.checkItemManipulation(i.getObjectId(), i.getCount(), "attach");
				if ((item == null) || !item.isTradeable() || item.isEquipped()) {
					player.sendPacket(SystemMessageId.THE_ITEM_THAT_YOU_RE_TRYING_TO_SEND_CANNOT_BE_FORWARDED_BECAUSE_IT_ISN_T_PROPER);
					return false;
				}

				fee += PlayerConfig.ALT_MESSAGE_FEE_PER_SLOT;

				if (item.getId() == ADENA_ID) {
					currentAdena -= i.getCount();
				}
			}
		}

		// Check if enough adena and charge the fee
		if ((currentAdena < fee) || !player.reduceAdena("MailFee", fee, null, false)) {
			player.sendPacket(SystemMessageId.YOU_CANNOT_FORWARD_BECAUSE_YOU_DON_T_HAVE_ENOUGH_ADENA);
			return false;
		}

		if (_items == null) {
			return true;
		}

		Mail attachments = msg.createAttachments();

		// message already has attachments ? oO
		if (attachments == null) {
			return false;
		}

		// Proceed to the transfer
		InventoryUpdate playerIU = GeneralConfig.FORCE_INVENTORY_UPDATE ? null : new InventoryUpdate();
		for (AttachmentItem i : _items) {
			// Check validity of requested item
			ItemInstance oldItem = player.checkItemManipulation(i.getObjectId(), i.getCount(), "attach");
			if ((oldItem == null) || !oldItem.isTradeable() || oldItem.isEquipped()) {
				log.warn("Error adding attachment for char " + player.getName() + " (olditem == null)");
				return false;
			}

			final ItemInstance newItem = player.getInventory().transferItem("SendMail", i.getObjectId(), i.getCount(), attachments, player, msg.getReceiverName() + "[" + msg.getReceiverId() + "]");
			if (newItem == null) {
				log.warn("Error adding attachment for char " + player.getName() + " (newitem == null)");
				continue;
			}
			newItem.setItemLocation(newItem.getItemLocation(), msg.getId());

			if (playerIU != null) {
				if ((oldItem.getCount() > 0) && (oldItem != newItem)) {
					playerIU.addModifiedItem(oldItem);
				} else {
					playerIU.addRemovedItem(oldItem);
				}
			}
		}

		// Send updated item list to the player
		if (playerIU != null) {
			player.sendInventoryUpdate(playerIU);
		} else {
			player.sendItemList(false);
		}
		return true;

	}

	private static class AttachmentItem {
		private final int _objectId;
		private final long _count;

		public AttachmentItem(int id, long num) {
			_objectId = id;
			_count = num;
		}

		public int getObjectId() {
			return _objectId;
		}

		public long getCount() {
			return _count;
		}
	}
}
