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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author UnAfraid
 */
public class IgnoreSkillHolder extends SkillHolder {
	private static final long serialVersionUID = 5390641853521384336L;
	private final AtomicInteger _instances = new AtomicInteger(1);

	public IgnoreSkillHolder(int skillId, int skillLevel) {
		super(skillId, skillLevel);
	}

	public IgnoreSkillHolder(SkillHolder holder) {
		super(holder.getSkill());
	}

	public int getInstances() {
		return _instances.get();
	}

	public int increaseInstances() {
		return _instances.incrementAndGet();
	}

	public int decreaseInstances() {
		return _instances.decrementAndGet();
	}
}
