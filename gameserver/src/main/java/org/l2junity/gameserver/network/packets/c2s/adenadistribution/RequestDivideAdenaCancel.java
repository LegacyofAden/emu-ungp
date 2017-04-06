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
package org.l2junity.gameserver.network.packets.c2s.adenadistribution;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.request.AdenaDistributionRequest;
import org.l2junity.gameserver.network.packets.GameClientPacket;
import org.l2junity.gameserver.network.packets.s2c.adenadistribution.ExDivideAdenaCancel;
import org.l2junity.gameserver.network.packets.s2c.string.SystemMessageId;

import java.util.Objects;

/**
 * @author Sdw
 */
public class RequestDivideAdenaCancel extends GameClientPacket {
	private boolean _cancel;

	@Override
	public void readImpl() {
		_cancel = readC() == 0;
	}

	@Override
	public void runImpl() {
		final Player player = getClient().getActiveChar();
		if (player == null) {
			return;
		}

		if (_cancel) {
			final AdenaDistributionRequest request = player.getRequest(AdenaDistributionRequest.class);
			if (request != null) {
				request.getPlayers().stream().filter(Objects::nonNull).forEach(p ->
				{
					p.sendPacket(SystemMessageId.ADENA_DISTRIBUTION_HAS_BEEN_CANCELLED);
					p.sendPacket(ExDivideAdenaCancel.STATIC_PACKET);
					p.removeRequest(AdenaDistributionRequest.class);
				});
			}
		}
	}
}