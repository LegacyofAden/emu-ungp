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
package org.l2junity.gameserver.model.conditions;

import org.l2junity.gameserver.model.actor.Creature;
import org.l2junity.gameserver.model.items.ItemTemplate;
import org.l2junity.gameserver.model.skills.Skill;
import org.l2junity.gameserver.network.client.send.string.CustomMessage;

/**
 * The Class Condition.
 *
 * @author mkizub
 */
public abstract class Condition {
	private CustomMessage _msg;
	private int _msgId;
	private boolean _addName = false;

	/**
	 * Sets the message.
	 *
	 * @param msg the new message
	 */
	public final void setMessage(CustomMessage msg) {
		_msg = msg;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public final CustomMessage getMessage() {
		return _msg;
	}

	/**
	 * Sets the message id.
	 *
	 * @param msgId the new message id
	 */
	public final void setMessageId(int msgId) {
		_msgId = msgId;
	}

	/**
	 * Gets the message id.
	 *
	 * @return the message id
	 */
	public final int getMessageId() {
		return _msgId;
	}

	/**
	 * Adds the name.
	 */
	public final void addName() {
		_addName = true;
	}

	/**
	 * Checks if is adds the name.
	 *
	 * @return true, if is adds the name
	 */
	public final boolean isAddName() {
		return _addName;
	}

	public final boolean test(Creature caster, Creature target, Skill skill) {
		return test(caster, target, skill, null);
	}

	public final boolean test(Creature caster, Creature target, Skill skill, ItemTemplate item) {
		return testImpl(caster, target, skill, item);
	}

	/**
	 * Test the condition.
	 *
	 * @param effector the effector
	 * @param effected the effected
	 * @param skill    the skill
	 * @param item     the item
	 * @return {@code true} if successful, {@code false} otherwise
	 */
	public abstract boolean testImpl(Creature effector, Creature effected, Skill skill, ItemTemplate item);
}
