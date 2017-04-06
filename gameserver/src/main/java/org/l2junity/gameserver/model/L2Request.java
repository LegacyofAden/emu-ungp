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
package org.l2junity.gameserver.model;

import org.l2junity.commons.threading.ThreadPool;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.c2s.IClientIncomingPacket;
import org.l2junity.gameserver.network.packets.s2c.SystemMessage;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.concurrent.TimeUnit;

/**
 * This class manages requests (transactions) between two L2PcInstance.
 *
 * @author kriau
 */
public class L2Request {
	private static final int REQUEST_TIMEOUT = 15; // in secs

	protected Player _player;
	protected Player _partner;
	protected boolean _isRequestor;
	protected boolean _isAnswerer;
	protected GameClientPacket _requestPacket;

	public L2Request(Player player) {
		_player = player;
	}

	protected void clear() {
		_partner = null;
		_requestPacket = null;
		_isRequestor = false;
		_isAnswerer = false;
	}

	/**
	 * Set the L2PcInstance member of a transaction (ex : FriendInvite, JoinAlly, JoinParty...).
	 *
	 * @param partner
	 */
	private synchronized void setPartner(Player partner) {
		_partner = partner;
	}

	/**
	 * @return the L2PcInstance member of a transaction (ex : FriendInvite, JoinAlly, JoinParty...).
	 */
	public Player getPartner() {
		return _partner;
	}

	/**
	 * Set the packet incomed from requester.
	 *
	 * @param packet
	 */
	private synchronized void setRequestPacket(GameClientPacket packet) {
		_requestPacket = packet;
	}

	/**
	 * Return the packet originally incomed from requester.
	 *
	 * @return
	 */
	public GameClientPacket getRequestPacket() {
		return _requestPacket;
	}

	/**
	 * Checks if request can be made and in success case puts both PC on request state.
	 *
	 * @param partner
	 * @param packet
	 * @return
	 */
	public synchronized boolean setRequest(Player partner, GameClientPacket packet) {
		if (partner == null) {
			_player.sendPacket(SystemMessageId.YOU_HAVE_INVITED_THE_WRONG_TARGET);
			return false;
		}
		if (partner.getRequest().isProcessingRequest()) {
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ON_ANOTHER_TASK_PLEASE_TRY_AGAIN_LATER);
			sm.addString(partner.getName());
			_player.sendPacket(sm);
			return false;
		}
		if (isProcessingRequest()) {
			_player.sendPacket(SystemMessageId.WAITING_FOR_ANOTHER_REPLY);
			return false;
		}

		_partner = partner;
		_requestPacket = packet;
		setOnRequestTimer(true);
		_partner.getRequest().setPartner(_player);
		_partner.getRequest().setRequestPacket(packet);
		_partner.getRequest().setOnRequestTimer(false);
		return true;
	}

	private void setOnRequestTimer(boolean isRequestor) {
		_isRequestor = isRequestor;
		_isAnswerer = !isRequestor;
		ThreadPool.getInstance().scheduleGeneral(this::clear, REQUEST_TIMEOUT * 1000, TimeUnit.MILLISECONDS);
	}

	/**
	 * Clears PC request state. Should be called after answer packet receive.
	 */
	public void onRequestResponse() {
		if (_partner != null) {
			_partner.getRequest().clear();
		}
		clear();
	}

	/**
	 * @return {@code true} if a transaction is in progress.
	 */
	public boolean isProcessingRequest() {
		return _partner != null;
	}
}
