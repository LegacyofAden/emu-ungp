/*
 * Copyright (C) 2004-2017 L2J Unity
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
package org.l2junity.gameserver.model.actor.request;

import org.l2junity.gameserver.model.L2Clan;
import org.l2junity.gameserver.model.actor.instance.PlayerInstance;

import java.util.Objects;

/**
 * @author Sdw
 */
public class ClanInvitationRequest extends AbstractRequest {
	private final PlayerInstance _targetPlayer;
	private final L2Clan _clan;
	private final int _pledgeType;

	public ClanInvitationRequest(PlayerInstance activeChar, PlayerInstance targetPlayer, int pledgeType) {
		super(activeChar);
		Objects.requireNonNull(targetPlayer);
		Objects.requireNonNull(activeChar.getClan());
		_targetPlayer = targetPlayer;
		_clan = activeChar.getClan();
		_pledgeType = pledgeType;
	}

	public PlayerInstance getTargetPlayer() {
		return _targetPlayer;
	}

	public L2Clan getClan() {
		return _clan;
	}

	public int getPledgeType() {
		return _pledgeType;
	}

	@Override
	public boolean isUsing(int objectId) {
		return false;
	}

	@Override
	public void onTimeout() {
		super.onTimeout();
		getActiveChar().removeRequest(getClass());
		_targetPlayer.removeRequest(getClass());
	}
}
