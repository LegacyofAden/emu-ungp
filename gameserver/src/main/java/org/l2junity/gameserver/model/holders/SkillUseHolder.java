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
package org.l2junity.gameserver.model.holders;

import org.l2junity.gameserver.model.items.instance.ItemInstance;
import org.l2junity.gameserver.model.skills.Skill;

/**
 * @author UnAfraid
 */
public class SkillUseHolder extends SkillHolder {
	private static final long serialVersionUID = 6598919289888032393L;
	private final ItemInstance _item;
	private final boolean _ctrlPressed;
	private final boolean _shiftPressed;

	public SkillUseHolder(Skill skill, ItemInstance item, boolean ctrlPressed, boolean shiftPressed) {
		super(skill);
		_item = item;
		_ctrlPressed = ctrlPressed;
		_shiftPressed = shiftPressed;
	}

	public ItemInstance getItem() {
		return _item;
	}

	public boolean isCtrlPressed() {
		return _ctrlPressed;
	}

	public boolean isShiftPressed() {
		return _shiftPressed;
	}
}
