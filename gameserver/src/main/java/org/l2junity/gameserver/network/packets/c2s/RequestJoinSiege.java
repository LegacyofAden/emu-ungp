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

import org.l2junity.gameserver.instancemanager.CastleManager;
import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.ClanPrivilege;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.entity.Castle;
import org.l2junity.gameserver.model.events.EventDispatcher;
import org.l2junity.gameserver.model.events.impl.restriction.CanPlayerJoinSiege;
import org.l2junity.gameserver.model.events.returns.BooleanReturn;
import org.l2junity.gameserver.network.GameClient;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;
import org.l2junity.network.PacketReader;

/**
 * @author KenM
 */
public final class RequestJoinSiege extends GameClientPacket {
	private int _castleId;
	private int _isAttacker;
	private int _isJoining;

	@Override
	public void readImpl() {
		_castleId = readD();
		_isAttacker = readD();
		_isJoining = readD();
	}

	@Override
	public void runImpl() {
		final Player activeChar = getClient().getActiveChar();
		if (activeChar == null) {
			return;
		}

		final BooleanReturn term = EventDispatcher.getInstance().notifyEvent(new CanPlayerJoinSiege(activeChar, _castleId), activeChar, BooleanReturn.class);
		if ((term != null) && !term.getValue()) {
			return;
		}

		if (!activeChar.hasClanPrivilege(ClanPrivilege.CS_MANAGE_SIEGE)) {
			getClient().sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
			return;
		}

		Clan clan = activeChar.getClan();
		if (clan == null) {
			return;
		}

		Castle castle = CastleManager.getInstance().getCastleById(_castleId);
		if (castle != null) {
			if (_isJoining == 1) {
				if (System.currentTimeMillis() < clan.getDissolvingExpiryTime()) {
					getClient().sendPacket(SystemMessageId.YOUR_CLAN_MAY_NOT_REGISTER_TO_PARTICIPATE_IN_A_SIEGE_WHILE_UNDER_A_GRACE_PERIOD_OF_THE_CLAN_S_DISSOLUTION);
					return;
				}
				if (_isAttacker == 1) {
					castle.getSiege().registerAttacker(activeChar);
				} else {
					castle.getSiege().registerDefender(activeChar);
				}
			} else {
				castle.getSiege().removeSiegeClan(activeChar);
			}
			castle.getSiege().listRegisterClan(activeChar);
		}
	}
}
