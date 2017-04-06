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

import org.l2junity.core.configs.GeneralConfig;
import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.PrivateStoreType;
import org.l2junity.gameserver.model.BlockList;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerTrade;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.ActionFailed;
import org.l2junity.gameserver.network.packets.s2c.SendTradeRequest;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * This packet manages the trade request.
 */
public final class TradeRequest extends GameClientPacket {
	private int _objectId;

	@Override
	public void readImpl() {
		_objectId = readD();
	}

	@Override
	public void runImpl() {
		Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (!player.getAccessLevel().allowTransaction()) {
			player.sendMessage("Transactions are disabled for your current Access Level.");
			getClient().sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}

		final WorldObject target = player.getWorld().findObject(_objectId);
		// If there is no target, target is far away or
		// they are in different instances
		// trade request is ignored and there is no system message.
		if ((target == null) || !player.isInSurroundingRegion(target) || (target.getInstanceWorld() != player.getInstanceWorld())) {
			return;
		}

		// If target and acting player are the same, trade request is ignored
		// and the following system message is sent to acting player.
		if (target.getObjectId() == player.getObjectId()) {
			getClient().sendPacket(SystemMessageId.THAT_IS_AN_INCORRECT_TARGET);
			return;
		}

		if (!target.isPlayer()) {
			getClient().sendPacket(SystemMessageId.INVALID_TARGET);
			return;
		}

		final Player partner = target.getActingPlayer();

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerTrade(player, partner), player, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (partner.isInOlympiadMode() || player.isInOlympiadMode()) {
			player.sendMessage("A user currently participating in the Olympiad cannot accept or request a trade.");
			return;
		}

		// L2J Customs: Karma punishment
		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_TRADE && (player.getReputation() < 0)) {
			player.sendMessage("You cannot trade while you are in a chaotic state.");
			return;
		}

		if (!PlayerConfig.ALT_GAME_KARMA_PLAYER_CAN_TRADE && (partner.getReputation() < 0)) {
			player.sendMessage("You cannot request a trade while your target is in a chaotic state.");
			return;
		}

		if (GeneralConfig.JAIL_DISABLE_TRANSACTION && (player.isJailed() || partner.isJailed())) {
			player.sendMessage("You cannot trade while you are in in Jail.");
			return;
		}

		if ((player.getPrivateStoreType() != PrivateStoreType.NONE) || (partner.getPrivateStoreType() != PrivateStoreType.NONE)) {
			getClient().sendPacket(SystemMessageId.WHILE_OPERATING_A_PRIVATE_STORE_OR_WORKSHOP_YOU_CANNOT_DISCARD_DESTROY_OR_TRADE_AN_ITEM);
			return;
		}

		if (player.isProcessingTransaction()) {
			getClient().sendPacket(SystemMessageId.YOU_ARE_ALREADY_TRADING_WITH_SOMEONE);
			return;
		}

		SystemMessage sm;
		if (partner.isProcessingRequest() || partner.isProcessingTransaction()) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ON_ANOTHER_TASK_PLEASE_TRY_AGAIN_LATER);
			sm.addString(partner.getName());
			getClient().sendPacket(sm);
			return;
		}

		if (partner.getTradeRefusal()) {
			player.sendMessage("That person is in trade refusal mode.");
			return;
		}

		if (BlockList.isBlocked(partner, player)) {
			sm = SystemMessage.getSystemMessage(SystemMessageId.C1_HAS_PLACED_YOU_ON_HIS_HER_IGNORE_LIST);
			sm.addCharName(partner);
			getClient().sendPacket(sm);
			return;
		}

		if (!player.isInRadius3d(partner, 150)) {
			getClient().sendPacket(SystemMessageId.YOUR_TARGET_IS_OUT_OF_RANGE);
			return;
		}

		player.onTransactionRequest(partner);
		partner.sendPacket(new SendTradeRequest(player.getObjectId()));
		sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_HAVE_REQUESTED_A_TRADE_WITH_C1);
		sm.addString(partner.getName());
		getClient().sendPacket(sm);
	}
}
