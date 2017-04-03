/*
 * Copyright (C) 2004-2016 L2J Unity
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
package org.l2junity.gameserver.model.onedayreward.handlers;

import org.l2junity.core.configs.PlayerConfig;
import org.l2junity.gameserver.enums.OneDayRewardStatus;
import org.l2junity.gameserver.model.CommandChannel;
import org.l2junity.gameserver.model.OneDayRewardDataHolder;
import org.l2junity.gameserver.model.OneDayRewardPlayerEntry;
import org.l2junity.gameserver.model.Party;
import org.l2junity.gameserver.model.actor.Attackable;
import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.events.Containers;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.character.npc.OnAttackableKill;
import org.l2junity.gameserver.model.events.listeners.ConsumerEventListener;
import org.l2junity.gameserver.model.onedayreward.AbstractOneDayRewardHandler;

import java.util.List;

/**
 * @author UnAfraid
 */
public class BossOneDayRewardHandler extends AbstractOneDayRewardHandler {
	private final int _amount;

	public BossOneDayRewardHandler(OneDayRewardDataHolder holder) {
		super(holder);
		_amount = holder.getRequiredCompletions();
	}

	@Override
	public void init() {
		Containers.Monsters().addListener(new ConsumerEventListener(this, EventType.ON_ATTACKABLE_KILL, (OnAttackableKill event) -> onAttackableKill(event), this));
	}

	@Override
	public boolean isAvailable(Player player) {
		final OneDayRewardPlayerEntry entry = getPlayerEntry(player.getObjectId(), false);
		if (entry != null) {
			switch (entry.getStatus()) {
				case NOT_AVAILABLE: // Initial state
				{
					if (entry.getProgress() >= _amount) {
						entry.setStatus(OneDayRewardStatus.AVAILABLE);
						storePlayerEntry(entry);
					}
					break;
				}
				case AVAILABLE: {
					return true;
				}
			}
		}
		return false;
	}

	private void onAttackableKill(OnAttackableKill event) {
		final Attackable monster = event.getTarget();
		final Player player = event.getAttacker();
		if (monster.isRaid() && (monster.getInstanceId() > 0) && (player != null)) {
			final Party party = player.getParty();
			if (party != null) {
				final CommandChannel channel = party.getCommandChannel();
				final List<Player> members = channel != null ? channel.getMembers() : party.getMembers();
				members.stream().filter(member -> member.distance3d(monster) <= PlayerConfig.ALT_PARTY_RANGE).forEach(this::processPlayerProgress);
			} else {
				processPlayerProgress(player);
			}
		}
	}

	private void processPlayerProgress(Player player) {
		final OneDayRewardPlayerEntry entry = getPlayerEntry(player.getObjectId(), true);
		if (entry.getStatus() == OneDayRewardStatus.NOT_AVAILABLE) {
			if (entry.increaseProgress() >= _amount) {
				entry.setStatus(OneDayRewardStatus.AVAILABLE);
			}
			storePlayerEntry(entry);
		}
	}
}
