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
package org.l2junity.gameserver.model.effects.effecttypes.instant;

import org.l2junity.gameserver.model.Clan;
import org.l2junity.gameserver.model.StatsSet;
import org.l2junity.gameserver.model.WorldObject;
import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.effects.AbstractEffect;
import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.SystemMessage;

/**
 * Sends a specific system message ID to the clan. Used in skills such as Clan Gate.
 *
 * @author Nik
 */
public class InstantPledgeSendSystemMessage extends AbstractEffect {
	private final SystemMessage _message;

	public InstantPledgeSendSystemMessage(StatsSet params) {
		int id = params.getInt("id", 0);
		_message = SystemMessage.getSystemMessage(id);
		if (_message == null) {
			throw new IllegalArgumentException("SystemMessageId not found for id: " + id);
		}
	}

	@Override
	public void instant(Creature caster, WorldObject target, Skill skill, ItemInstance item) {
		final Player casterPlayer = caster.asPlayer();
		if (casterPlayer == null) {
			return;
		}

		final Clan clan = casterPlayer.getClan();
		if (clan != null) {
			clan.broadcastToOnlineMembers(_message);
		}
	}
}
