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
package org.l2junity.gameserver.model.events.impl.ceremonyofchaos;

import org.l2junity.gameserver.model.ceremonyofchaos.CeremonyOfChaosMember;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.IBaseEvent;

import java.util.List;

/**
 * @author UnAfraid
 */
public class OnCeremonyOfChaosMatchResult implements IBaseEvent {
	private final List<CeremonyOfChaosMember> _winners;
	private final List<CeremonyOfChaosMember> _members;

	public OnCeremonyOfChaosMatchResult(List<CeremonyOfChaosMember> winners, List<CeremonyOfChaosMember> members) {
		_winners = winners;
		_members = members;
	}

	public List<CeremonyOfChaosMember> getWinners() {
		return _winners;
	}

	public List<CeremonyOfChaosMember> getMembers() {
		return _members;
	}

	@Override
	public EventType getType() {
		return EventType.ON_CEREMONY_OF_CHAOS_MATCH_RESULT;
	}
}