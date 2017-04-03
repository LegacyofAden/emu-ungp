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
package org.l2junity.gameserver.model.events.impl.character.player;

import org.l2junity.gameserver.model.actor.instance.Player;
import org.l2junity.gameserver.model.actor.templates.PcTemplate;
import org.l2junity.gameserver.model.events.EventType;
import org.l2junity.gameserver.model.events.impl.IBaseEvent;

/**
 * @author UnAfraid
 */
public class OnPlayerProfessionChange implements IBaseEvent {
	private final Player _activeChar;
	private final PcTemplate _template;
	private final boolean _isSubClass;

	public OnPlayerProfessionChange(Player activeChar, PcTemplate template, boolean isSubClass) {
		_activeChar = activeChar;
		_template = template;
		_isSubClass = isSubClass;
	}

	public Player getActiveChar() {
		return _activeChar;
	}

	public PcTemplate getTemplate() {
		return _template;
	}

	public boolean isSubClass() {
		return _isSubClass;
	}

	@Override
	public EventType getType() {
		return EventType.ON_PLAYER_PROFESSION_CHANGE;
	}
}
